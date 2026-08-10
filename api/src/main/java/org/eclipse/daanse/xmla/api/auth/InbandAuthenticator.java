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

import org.eclipse.daanse.xmla.api.XmlaRequest;

/**
 * The in-band {@code Authenticate} handshake: the client posts a security token in the
 * SOAP body and the server answers with its own, repeating until the security package
 * on both ends is done ([MS-SSAS] 3.2.2, GSS-API per [RFC4178]). The last leg carries
 * an empty token.
 * <p>
 * The specification binds the result to the <em>connection</em>, which HTTP does not
 * have; this server binds it to the session instead, opening one during the handshake
 * if the client has none. That is this project's decision, not the specification's.
 * <p>
 * Separate from {@link XmlaAuthenticator} because it sits on the other side of the SOAP
 * boundary: the SOAP layer drives it, the transport never sees it. With no
 * implementation registered, an {@code Authenticate} is refused as an unsupported
 * authentication method ({@code 0xC10E0002}).
 */
public interface InbandAuthenticator {

    /**
     * Advances the handshake by one round.
     *
     * @param token   the client's security token, exactly as carried in
     *                {@code <SspiHandshake>}
     * @param request the request the handshake arrived on; its session id is the
     *                state key for a handshake that needs more than one round, and
     *                the server guarantees one is present
     */
    Result authenticate(byte[] token, XmlaRequest request);

    /** The outcome of one handshake round. */
    sealed interface Result {

        /**
         * Not done yet: send this token back in {@code AuthenticateResponse} and wait
         * for the next round.
         */
        record Continue(byte[] token) implements Result {
        }

        /**
         * Done: the peer is this identity, which the server binds to the session. The
         * final token still goes back - some packages end with a mutual-authentication
         * token the client verifies - and may be empty.
         */
        record Done(AuthenticatedIdentity identity, byte[] token) implements Result {
        }

        /**
         * The handshake failed. Answered as the specification's authentication fault.
         */
        record Refused(String reason) implements Result {
        }
    }
}
