/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.server.auth.dummy;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.api.auth.AuthRanking;
import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;
import org.eclipse.daanse.xmla.api.auth.Claims;
import org.eclipse.daanse.xmla.api.auth.NamedPrincipal;
import org.eclipse.daanse.xmla.api.auth.XmlaAuthenticator;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The caller says who they are and which roles they hold, in the user name, and
 * this believes them.
 * <p>
 * {@code UserName|Role1|Role2} in HTTP Basic, password ignored - the same
 * convention the servlet filter of the same purpose already uses, so both
 * endpoints of a deployment behave alike. Logging in as
 * {@code someone|California manager} is how you see a catalog the way that role
 * sees it, which is otherwise only reachable by running a real directory.
 * <p>
 * It verifies nothing. Unlike {@link FixedIdentityAuthenticator} it does answer
 * {@link Result.Authenticated}, because a caller who sent credentials has made a
 * claim about themselves and a rule that demands authentication should see it
 * satisfied - which is exactly what makes this dangerous outside a development
 * server, and why it too refuses to come up without being told so.
 */
@Component(service = XmlaAuthenticator.class, configurationPolicy = ConfigurationPolicy.REQUIRE, property = "service.ranking:Integer="
        + AuthRanking.BASIC)
@Designate(ocd = BasicAuthPipeRoleAuthenticator.Config.class)
public class BasicAuthPipeRoleAuthenticator implements XmlaAuthenticator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthPipeRoleAuthenticator.class);

    private static final String PREFIX = "Basic ";

    private volatile String realm;

    @ObjectClassDefinition
    @interface Config {

        /**
         * Confirms that this endpoint accepts whatever the caller claims. Without it the
         * component does not come up, so the decision cannot be made by accident.
         */
        boolean acknowledgeUnverifiedCredentials() default false;

        /** What the challenge names, which is what a client shows in its login prompt. */
        String realm() default "Daanse";
    }

    @Activate
    void activate(Config config) {
        if (!config.acknowledgeUnverifiedCredentials()) {
            throw new IllegalStateException("this component grants the roles a caller asks for without verifying "
                    + "anything; set acknowledgeUnverifiedCredentials to confirm that is intended");
        }
        realm = config.realm();
        LOGGER.warn("callers are granted the roles they name in the user name ('UserName|Role1|Role2'): "
                + "credentials are not verified");
    }

    @Override
    public String scheme() {
        return "Basic";
    }

    @Override
    public String challenge() {
        return PREFIX + "realm=\"" + realm + "\"";
    }

    @Override
    public Result authenticate(XmlaRequest request) {
        String header = authorization(request);
        if (header == null || !header.regionMatches(true, 0, PREFIX, 0, PREFIX.length())) {
            return new Result.NotMine();
        }
        String decoded;
        try {
            decoded = new String(Base64.getDecoder().decode(header.substring(PREFIX.length()).trim()),
                    StandardCharsets.UTF_8);
        } catch (IllegalArgumentException notBase64) {
            return new Result.Refused("the Basic credentials are not valid base64");
        }
        // The password is the part this deliberately does not look at.
        int colon = decoded.indexOf(':');
        String userPart = colon < 0 ? decoded : decoded.substring(0, colon);

        String[] pieces = userPart.split("\\|");
        Set<String> roles = new LinkedHashSet<>();
        for (int i = 1; i < pieces.length; i++) {
            if (!pieces[i].isBlank()) {
                roles.add(pieces[i].trim());
            }
        }
        String userName = pieces.length == 0 ? "" : pieces[0].trim();
        LOGGER.debug("caller '{}' claims roles {}", userName, roles);
        return new Result.Authenticated(new AuthenticatedIdentity(new NamedPrincipal(userName), roles, Claims.none()),
                null);
    }

    /** Header names are case-insensitive, and containers disagree about the case. */
    private static String authorization(XmlaRequest request) {
        for (Map.Entry<String, List<String>> header : request.headers().entrySet()) {
            if ("Authorization".equalsIgnoreCase(header.getKey()) && !header.getValue().isEmpty()) {
                return header.getValue().getFirst();
            }
        }
        return null;
    }
}
