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
package org.eclipse.daanse.xmla.spi.auth;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Decides whether a user name and password are good, and which roles that user
 * holds.
 * <p>
 * XMLA has no credential-bearing SOAP header — there is no WS-Security
 * {@code UsernameToken} in the protocol and none should be invented — so HTTP
 * transport authentication is the only place a real
 * {@link java.security.Principal} can come from. This is the seam a deployment
 * fills; this bundle deliberately ships no implementation, because a default
 * one would either be a fixed password or an accept-everything, and both are
 * worse than not starting.
 * <p>
 * {@code Roles} and {@code EffectiveUserName} in a request's
 * {@code <PropertyList>} are a <em>request</em>, not a fact: what a user may
 * actually claim is what this service says.
 */
@ProviderType
public interface XmlaCredentialValidator {

    /**
     * @return {@code true} if the credentials are valid. Implementations should
     *         compare in constant time and should not distinguish "no such user"
     *         from "wrong password" to the caller — the difference is only useful
     *         to someone enumerating accounts
     */
    boolean isValid(String userName, String password);

    /**
     * The roles the user holds. Consulted only after {@link #isValid} has said yes.
     *
     * @return the roles, or an empty set for a user with none
     */
    Set<String> rolesOf(String userName);
}
