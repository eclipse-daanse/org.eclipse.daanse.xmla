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

import java.util.LinkedHashSet;
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
 * Every caller nobody identified is the same configured user.
 * <p>
 * An endpoint with no mechanism registered serves everyone anonymously, which
 * is a valid way to run and the default. What it cannot do is exercise anything
 * that depends on <em>who</em> is calling: a catalog's roles, a connector's
 * per-user behaviour, a rowset restricted to authenticated callers. This fills
 * that gap by declaring one identity for callers nothing else claimed.
 * <p>
 * It verifies nothing, and three things follow from that, all of them load
 * bearing. It answers {@link Result.Fallback}, so it can never displace a
 * mechanism that would have authenticated the caller for real, never displace
 * the identity a session carries, and never satisfy an access rule that demands
 * authentication. It ranks below everything. And it requires a configuration
 * that says out loud that the endpoint is unprotected, so that installing the
 * bundle is not by itself enough to name every caller.
 */
@Component(service = XmlaAuthenticator.class, configurationPolicy = ConfigurationPolicy.REQUIRE, property = "service.ranking:Integer="
        + AuthRanking.FIXED)
@Designate(ocd = FixedIdentityAuthenticator.Config.class)
public class FixedIdentityAuthenticator implements XmlaAuthenticator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FixedIdentityAuthenticator.class);

    private volatile AuthenticatedIdentity identity;

    @ObjectClassDefinition
    @interface Config {

        /**
         * Confirms that this endpoint is knowingly left unauthenticated. Without it the
         * component does not come up, so the decision cannot be made by accident.
         */
        boolean acknowledgeUnauthenticated() default false;

        /** The name every unidentified caller is given. */
        String userName() default "daanse";

        /** The roles that caller holds. Empty means the catalog's default role. */
        String[] roles() default {};
    }

    @Activate
    void activate(Config config) {
        if (!config.acknowledgeUnauthenticated()) {
            throw new IllegalStateException("this component names every caller without verifying anything; "
                    + "set acknowledgeUnauthenticated to confirm that is intended");
        }
        Set<String> granted = new LinkedHashSet<>();
        for (String role : config.roles()) {
            if (role != null && !role.isBlank()) {
                granted.add(role.trim());
            }
        }
        identity = new AuthenticatedIdentity(new NamedPrincipal(config.userName()), granted, Claims.none());
        LOGGER.warn("callers nobody identified are served as '{}' with roles {}: this endpoint is not authenticated",
                config.userName(), granted);
    }

    @Override
    public String scheme() {
        return "Fixed";
    }

    @Override
    public String challenge() {
        // Nothing to ask a client for; the identity does not come from the request.
        return "";
    }

    @Override
    public Result authenticate(XmlaRequest request) {
        return new Result.Fallback(identity);
    }
}
