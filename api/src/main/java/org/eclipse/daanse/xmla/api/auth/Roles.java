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
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

/**
 * Puts {@link RoleProvider} and {@link RoleMapping} together, the same way
 * every time.
 * <p>
 * A mechanism establishes who is calling and what its own source said; the
 * roles are then the union of that and what every registered provider grants,
 * and the mapping translates that union - once, as a whole, so a rule may
 * depend on a combination.
 * <p>
 * {@link RoleResolution} is the service form of this and is what a mechanism
 * should use. These static methods are the implementation behind it, for a
 * caller that already holds the providers.
 */
public final class Roles {

    // java.base's logger, so the API bundle carries no logging dependency of its
    // own
    private static final Logger LOGGER = System.getLogger(Roles.class.getName());

    private Roles() {
        // static access only
    }

    /**
     * The catalog roles a caller holds: the union of what every provider grants and
     * what the mechanism read itself, translated by the mapping.
     *
     * @param external  names the mechanism read itself, as a token claim or a proxy
     *                  header
     * @param providers every registered provider; a deployment may read groups from
     *                  a directory and extra roles from a table
     * @param mapping   the translation, or {@code null} for none
     */
    public static Set<String> resolve(Principal principal, Claims claims, Collection<String> external,
            Collection<RoleProvider> providers, RoleMapping mapping) {
        Set<String> names = new LinkedHashSet<>();
        if (external != null) {
            names.addAll(external);
        }
        if (providers != null) {
            for (RoleProvider provider : providers) {
                names.addAll(askOne(provider, principal, claims));
            }
        }
        return map(names, mapping);
    }

    public static Set<String> resolve(Principal principal, Claims claims, Collection<RoleProvider> providers,
            RoleMapping mapping) {
        return resolve(principal, claims, List.of(), providers, mapping);
    }

    /**
     * One provider's answer, or none.
     * <p>
     * A provider that cannot reach its directory must not decide the request: the
     * contract says a provider that knows nothing answers empty, and a provider
     * that throws is treated the same way rather than turning one unreachable
     * source into a failed request.
     */
    private static Set<String> askOne(RoleProvider provider, Principal principal, Claims claims) {
        try {
            Set<String> granted = provider.rolesOf(principal, claims);
            return granted == null ? Set.of() : granted;
        } catch (RuntimeException failed) {
            LOGGER.log(Level.WARNING,
                    "the role provider " + provider.getClass().getName() + " failed and granted nothing", failed);
            return Set.of();
        }
    }

    /** The catalog roles these external names stand for. */
    public static Set<String> map(Collection<String> external, RoleMapping mapping) {
        Set<String> names = external == null ? Set.of() : new LinkedHashSet<>(external);
        if (mapping == null) {
            return Set.copyOf(names);
        }
        Set<String> mapped = mapping.map(names);
        return mapped == null ? Set.of() : Set.copyOf(mapped);
    }

    /** The values of one claim, as the list a mapping works from. */
    public static List<String> fromClaim(Claims claims, String claimName) {
        return claims == null ? List.of() : claims.all(claimName);
    }
}
