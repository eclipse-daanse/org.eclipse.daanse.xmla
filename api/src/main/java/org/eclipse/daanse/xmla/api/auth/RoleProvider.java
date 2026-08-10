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
import java.util.Set;

import org.osgi.annotation.versioning.ConsumerType;

/**
 * Looks up what a caller may do, once a mechanism established who they are.
 * <p>
 * The second half of authorization, split from {@link XmlaCredentials} so that
 * the source of the roles is independent of the mechanism that authenticated: a
 * caller who arrived with a Bearer token may still have their roles read from a
 * directory, and a caller who arrived over Kerberos may have them read from an
 * internal table.
 * <p>
 * Several providers may be registered; the roles they answer are the union. A
 * provider that knows nothing about a caller answers the empty set rather than
 * failing - not knowing is not the same as refusing, and the refusal belongs to
 * the access policy.
 */
@ConsumerType
public interface RoleProvider {

    /**
     * The roles this provider grants the caller.
     *
     * @param principal who was authenticated
     * @param claims    what the mechanism learned - a token's claim set, a
     *                  directory's groups - which a provider may use as its lookup
     *                  key instead of the name
     */
    Set<String> rolesOf(Principal principal, Claims claims);
}
