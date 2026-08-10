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
 * Separate from {@link RoleProvider} so that where the password is checked and where
 * the roles come from can be different sources.
 * <p>
 * A server configured for a password-based mechanism with none of these registered
 * refuses to start, rather than looking secured and admitting everyone.
 */
@ProviderType
public interface XmlaCredentials {

    /**
     * The identity behind these credentials, or empty when they are not accepted. It
     * may carry claims the store knows; turning those into catalog roles is a
     * {@link RoleProvider}'s and {@link RoleMapping}'s business.
     *
     * @param userName the name as the client sent it
     * @param password the password as the client sent it. The caller clears it
     *                 afterwards, so an implementation that needs it later must copy it
     */
    Optional<AuthenticatedIdentity> verify(String userName, char[] password);
}
