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

import java.util.Optional;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Verifies a user name and password, and nothing else.
 * <p>
 * Deliberately separate from {@link RoleProvider}: a deployment may check the
 * password against LDAP and read the roles from somewhere else entirely, or
 * check against an internal store and take the roles from a directory. Mixing
 * both into one contract forces every implementation to answer a question it
 * may have no source for.
 * <p>
 * Two implementations ship: the deployment's own configuration, and a directory
 * bind. A server configured for a password-based mechanism without any of them
 * refuses to start, which is the honest failure - the alternative is an
 * endpoint that looks secured and admits everyone.
 */
@ProviderType
public interface XmlaCredentials {

    /**
     * The identity behind these credentials, or empty when they are not accepted.
     * <p>
     * The returned identity may already carry claims the store knows (a directory's
     * groups, an account's attributes); resolving those into catalog roles is a
     * {@link RoleProvider}'s and a {@link RoleMapping}'s business.
     *
     * @param userName the name as the client sent it
     * @param password the password as the client sent it; the caller clears it
     *                 afterwards, so an implementation that needs it later must
     *                 copy it
     */
    Optional<AuthenticatedIdentity> verify(String userName, char[] password);
}
