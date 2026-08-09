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
package org.eclipse.daanse.xmla.server.jdk.httpserver;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.api.auth.XmlaAuthenticator;
import org.eclipse.daanse.xmla.api.auth.XmlaCredentialValidator;

/**
 * HTTP BASIC over an {@link XmlaCredentialValidator}.
 * <p>
 * BASIC sends the password in the clear on every request, so this is only
 * honest over TLS or on a loopback interface. It is here because it is what
 * Analysis Services clients — Excel, msolap.dll, every XMLA library — actually
 * send.
 * <p>
 * Note what this does <em>not</em> do: challenge a request that carries no
 * credentials. That is the chain's contract — an anonymous request passes
 * through, and the challenge comes back only when the backend refuses it. A
 * client that wants to probe {@code DISCOVER_PROPERTIES} before logging in gets
 * to.
 */
public class BasicXmlaAuthenticator implements XmlaAuthenticator {

    private final String realm;
    private final XmlaCredentialValidator validator;

    /**
     * @throws IllegalStateException if there is no validator, which would leave the
     *                               endpoint either refusing everyone or admitting
     *                               everyone while its configuration says BASIC
     */
    public BasicXmlaAuthenticator(String realm, XmlaCredentialValidator validator) {
        if (validator == null) {
            throw new IllegalStateException("authentication is configured as BASIC, but no "
                    + "XmlaCredentialValidator service is registered; refusing to start rather "
                    + "than serve every request anonymously");
        }
        this.realm = realm;
        this.validator = validator;
    }

    @Override
    public String scheme() {
        return "Basic";
    }

    @Override
    public String challenge() {
        return "Basic realm=\"" + realm + "\"";
    }

    @Override
    public Result authenticate(XmlaRequest request) {
        String authorization = request.header("Authorization");
        if (authorization == null || !authorization.regionMatches(true, 0, "Basic ", 0, 6)) {
            return new Result.NotMine();
        }
        String decoded;
        try {
            decoded = new String(Base64.getDecoder().decode(authorization.substring(6).trim()), StandardCharsets.UTF_8);
        } catch (IllegalArgumentException notBase64) {
            return new Result.Refused("the Basic credentials are not base64");
        }
        int colon = decoded.indexOf(':');
        if (colon < 0) {
            return new Result.Refused("the Basic credentials carry no ':' separator");
        }
        String user = decoded.substring(0, colon);
        String password = decoded.substring(colon + 1);
        if (!validator.isValid(user, password)) {
            // "no such user" and "wrong password" answer identically on purpose - the
            // difference is only useful to someone enumerating accounts.
            return new Result.Refused("the credentials were not accepted");
        }
        return new Result.Authenticated(() -> user, validator.rolesOf(user));
    }
}
