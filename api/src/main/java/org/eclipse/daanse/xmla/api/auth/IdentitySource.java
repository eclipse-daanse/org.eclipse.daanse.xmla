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

/**
 * Where a request's identity came from.
 * <p>
 * It is not decoration: {@link #FALLBACK} is an identity nobody proved, so an
 * access rule that demands authentication must not accept it, and any real
 * source must be able to displace it.
 */
public enum IdentitySource {

    /** Nobody was identified. */
    NONE,

    /** A mechanism validated a credential the caller presented. */
    MECHANISM,

    /** Restored from the session the in-band handshake was bound to. */
    SESSION,

    /** The servlet container authenticated before this endpoint was reached. */
    CONTAINER,

    /** A configured stand-in for callers nothing identified. */
    FALLBACK
}
