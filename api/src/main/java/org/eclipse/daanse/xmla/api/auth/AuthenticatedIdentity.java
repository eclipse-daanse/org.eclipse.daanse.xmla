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
package org.eclipse.daanse.xmla.api.auth;

import java.security.Principal;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Who the caller is, what they may do, and what the mechanism knew about them.
 * <p>
 * The one shape every mechanism answers with, whether it authenticated over
 * HTTP or through the in-band {@code Authenticate} handshake, so that the roles
 * a caller holds are a set the server can enumerate, log and bind to a session
 * - not a predicate that can only ever be asked one name at a time.
 *
 * @param principal who was authenticated; never {@code null}
 * @param roles     the catalog roles this caller holds, already resolved and
 *                  mapped
 * @param claims    what the mechanism learned, for a {@link RoleProvider} or a
 *                  {@link RoleMapping} that runs afterwards
 */
public record AuthenticatedIdentity(Principal principal, Set<String> roles, Claims claims) {

    public AuthenticatedIdentity {
        if (principal == null) {
            throw new IllegalArgumentException("an authenticated identity needs a principal");
        }
        roles = roles == null ? Set.of() : Set.copyOf(roles);
        claims = claims == null ? Claims.none() : claims;
    }

    /** An identity with a name and nothing else known yet. */
    public static AuthenticatedIdentity of(Principal principal) {
        return new AuthenticatedIdentity(principal, Set.of(), Claims.none());
    }

    /**
     * An identity carrying the claims its mechanism read, roles still to resolve.
     */
    public static AuthenticatedIdentity of(Principal principal, Claims claims) {
        return new AuthenticatedIdentity(principal, Set.of(), claims);
    }

    /** The same identity with these roles added to the ones it already holds. */
    public AuthenticatedIdentity withRoles(Set<String> more) {
        if (more == null || more.isEmpty()) {
            return this;
        }
        Set<String> merged = new LinkedHashSet<>(roles);
        merged.addAll(more);
        return new AuthenticatedIdentity(principal, merged, claims);
    }

    public String name() {
        return principal.getName();
    }
}
