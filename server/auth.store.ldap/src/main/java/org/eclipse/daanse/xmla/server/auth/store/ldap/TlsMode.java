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
package org.eclipse.daanse.xmla.server.auth.store.ldap;

/** How the connection to the directory is protected. */
public enum TlsMode {

    /** TLS from the first byte, on the {@code ldaps://} port. */
    LDAPS,

    /**
     * Plain connection upgraded by the StartTLS extended operation, which is the
     * usual arrangement on port 389 in an Active Directory domain.
     */
    STARTTLS,

    /**
     * No protection. A simple bind then sends the user's password in the clear, so
     * it has to be confirmed separately.
     */
    NONE
}
