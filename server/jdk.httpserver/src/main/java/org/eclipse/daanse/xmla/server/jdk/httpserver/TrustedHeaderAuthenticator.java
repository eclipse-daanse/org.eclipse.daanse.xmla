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

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.daanse.xmla.spi.XmlaRequest;
import org.eclipse.daanse.xmla.spi.auth.XmlaAuthenticator;

/**
 * Identity forwarded by a trusted front — a reverse proxy that already
 * authenticated the caller.
 * <p>
 * Authelia, oauth2-proxy and their kind sit in front of an endpoint, do the
 * login, and forward who it was in headers: {@code Remote-User},
 * {@code Remote-Groups}. This authenticator turns those into the principal.
 * <p>
 * It must sit behind a proxy that strips and sets these headers, never on an
 * open port, otherwise anyone can send a header and be anyone. That is a
 * deployment property this class cannot check.
 * <p>
 * It never challenges: a caller the proxy did not identify is simply anonymous
 * here, and whether anonymous is enough for a given rowset stays the
 * connector's decision.
 */
public class TrustedHeaderAuthenticator implements XmlaAuthenticator {

    private final String userHeader;
    private final String rolesHeader;

    /**
     * @param userHeader  the header naming the user, conventionally
     *                    {@code Remote-User}
     * @param rolesHeader the header carrying comma-separated groups, conventionally
     *                    {@code Remote-Groups}
     */
    public TrustedHeaderAuthenticator(String userHeader, String rolesHeader) {
        this.userHeader = userHeader;
        this.rolesHeader = rolesHeader;
    }

    @Override
    public String scheme() {
        return "TrustedHeader";
    }

    @Override
    public String challenge() {
        // A proxy identity cannot be asked for; the challenge, if any, is the proxy's
        // own.
        return "";
    }

    @Override
    public Result authenticate(XmlaRequest request) {
        String user = request.header(userHeader);
        if (user == null || user.isBlank()) {
            return new Result.NotMine();
        }
        String groups = request.header(rolesHeader);
        Set<String> roles = new LinkedHashSet<>();
        if (groups != null) {
            for (String group : groups.split(",")) {
                if (!group.isBlank()) {
                    roles.add(group.trim());
                }
            }
        }
        String name = user.trim();
        return new Result.Authenticated(() -> name, roles);
    }
}
