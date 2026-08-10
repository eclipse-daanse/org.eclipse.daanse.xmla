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

import java.util.Set;

import org.osgi.annotation.versioning.ConsumerType;

/**
 * Translates the names an identity provider uses into the roles a catalog
 * defines.
 * <p>
 * The names rarely match: a token says {@code bi-admin}, a directory says
 * {@code CN=BI Admins,OU=Groups,DC=example,DC=org}, and the catalog knows a
 * role called {@code Admin}. Without a translation step every deployment would
 * have to name its catalog roles after its directory, which is backwards.
 * <p>
 * A mapping that does not recognise a name drops it. Dropping is right: a
 * catalog refuses a role it does not define, so passing an unmapped name
 * through would turn an unknown group into a failed request rather than into no
 * extra access.
 */
@ConsumerType
public interface RoleMapping {

    /**
     * The catalog roles these external names stand for.
     * <p>
     * The whole set arrives at once, so a rule may depend on a combination - "holds
     * A and B, therefore Admin" - which is impossible if the caller's names are
     * mapped in pieces.
     *
     * @param external group or claim values as the identity provider spells them
     * @return never {@code null}; an empty set means none of them stands for a
     *         catalog role
     */
    Set<String> map(Set<String> external);

    /**
     * A mapping that passes every name through unchanged.
     * <p>
     * For embedding and for tests. It is deliberately not a registered service: a
     * deployment that wants pass-through says so in the configuration of a real
     * mapping component, where the decision is visible.
     */
    static RoleMapping identity() {
        return external -> external == null ? Set.of() : Set.copyOf(external);
    }
}
