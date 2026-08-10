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
 * The order the shipped mechanisms are asked in, as {@code service.ranking}
 * values.
 * <p>
 * They are compile-time constants so a component declaration and a test that
 * pins the order read the same source. Without them the order is whatever
 * Declarative Services happened to bind in, which differs between restarts -
 * and the chain stops at the first mechanism that claims a request, so the
 * order decides who authenticates.
 * <table>
 * <caption>Why this order</caption>
 * <tr>
 * <td>{@link #NEGOTIATE}</td>
 * <td>the only multi-round mechanism; a handshake in flight must not be
 * pre-empted by one that would refuse and restart it</td>
 * </tr>
 * <tr>
 * <td>{@link #BEARER}</td>
 * <td>a scoped, expiring token validated in this process outranks a long-lived
 * password</td>
 * </tr>
 * <tr>
 * <td>{@link #BASIC}</td>
 * <td>a credential the caller presented outranks one merely asserted about
 * them</td>
 * </tr>
 * <tr>
 * <td>{@link #TRUSTED_HEADER}</td>
 * <td>ambient, and keyed only on the peer address; ranking it last lets a
 * service account log in through a front that also asserts an interactive
 * identity</td>
 * </tr>
 * <tr>
 * <td>{@link #FIXED}</td>
 * <td>answers for every caller, so anything after it would never be asked</td>
 * </tr>
 * </table>
 * The gaps of 100 are there so a deployment can slot its own mechanism between
 * two shipped ones without rewriting either.
 */
public final class AuthRanking {

    public static final String NEGOTIATE = "400";
    public static final String BEARER = "300";
    public static final String BASIC = "200";
    public static final String TRUSTED_HEADER = "100";

    /**
     * Below everything, including anything a deployment adds without thinking about
     * it.
     */
    public static final String FIXED = "-1000";

    private AuthRanking() {
        // static access only
    }
}
