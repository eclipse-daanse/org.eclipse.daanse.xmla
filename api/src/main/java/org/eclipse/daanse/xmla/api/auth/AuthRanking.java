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
 * The order the shipped mechanisms are asked in, as {@code service.ranking} values.
 * <p>
 * The chain stops at the first mechanism that claims a request, so this order decides
 * who authenticates. Without it the order is whatever Declarative Services bound in,
 * which differs between restarts. Gaps of 100 leave room for a deployment's own
 * mechanism between two shipped ones.
 */
public final class AuthRanking {

    /** The only multi-round mechanism: a handshake in flight must not be pre-empted. */
    public static final String NEGOTIATE = "400";

    /** A scoped, expiring token outranks a long-lived password. */
    public static final String BEARER = "300";

    /** A credential the caller presented outranks one merely asserted about them. */
    public static final String BASIC = "200";

    /**
     * Ambient and keyed only on the peer address. Last of the real mechanisms, so a
     * service account can still log in through a front that asserts someone else.
     */
    public static final String TRUSTED_HEADER = "100";

    /** Answers for every caller, so anything after it would never be asked. */
    public static final String FIXED = "-1000";

    private AuthRanking() {
        // static access only
    }
}
