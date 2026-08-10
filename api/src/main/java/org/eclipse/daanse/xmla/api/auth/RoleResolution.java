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
import java.util.List;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Turns a caller into the catalog roles it holds.
 * <p>
 * One service instead of every mechanism collecting {@link RoleProvider}s and a
 * {@link RoleMapping} for itself. That is not only less repetition: a mechanism
 * that reads groups of its own must hand them in <em>here</em> rather than
 * mapping them separately, so the mapping sees the whole set at once and a rule
 * like "holds A and B, therefore Admin" can fire at all.
 */
@ProviderType
public interface RoleResolution {

    /**
     * The catalog roles this caller holds.
     *
     * @param external group or role names the mechanism read itself, as the
     *                 identity provider spells them; every registered provider is
     *                 asked as well and the union is mapped once
     */
    Set<String> resolve(Principal principal, Claims claims, Collection<String> external);

    /** For a mechanism that read no names of its own. */
    default Set<String> resolve(Principal principal, Claims claims) {
        return resolve(principal, claims, List.of());
    }
}
