/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.api.engine;

import java.util.Optional;

/**
 * The ImpersonationInfo complex type represents impersonation settings for an object or operation.
 */
public interface ImpersonationInfo {

    /**
     * A string that specifies the credentials to use for impersonation. The enumeration values are as
     * follows: Default - The server uses the impersonation method that it deems to be appropriate for
     * the context in which impersonation is used. ImpersonateServiceAccount - Use the user account that
     * the server is running as. ImpersonateAnonymous - Use the anonymous user account.
     * ImpersonateCurrentUser - Use the user account that the client is connecting as.
     * ImpersonateAccount - Use the specified user account
     *
     * @return
     */
    String impersonationMode();

    /**
     * The user account to impersonate when ImpersonationMode=ImpersonateAccount.
     *
     * @return
     */
    Optional<String> account();

    /**
     * @return The password of the user account when ImpersonationMode=ImpersonateAccount.
     */
    Optional<String> password();

    /**
     * @return Specifies whether the password was removed.
     */
    Optional<String> impersonationInfoSecurity();

}
