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
 * Looks up what a caller may do, once a mechanism established who they are. Split from
 * {@link XmlaCredentials} so the source of the roles is independent of the mechanism
 * that authenticated.
 * <p>
 * Several may be registered and their answers are unioned. One that knows nothing about
 * a caller answers the empty set: not knowing is not refusing, and refusing belongs to
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
