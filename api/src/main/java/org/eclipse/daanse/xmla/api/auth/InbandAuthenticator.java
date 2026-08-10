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
 * The in-band {@code Authenticate} handshake of the specification.
 * <p>
 * XMLA has its own login besides HTTP: the client posts {@code <Authenticate>}
 * (ext namespace) carrying a security token in the SOAP body, the server
 * answers {@code <AuthenticateResponse>} with its own, and the exchange repeats
 * until the security package on both ends is done ([MS-SSAS] 3.2.2: GSS-API per
 * [RFC4178], "until GSS-API reports completion or error"). The last leg carries
 * an empty token.
 * <p>
 * <strong>Where the result lives.</strong> The specification describes this as
 * a property of the <em>connection</em> and uses it over TCP; over HTTP there
 * is no connection to hold it. This server therefore binds the established
 * identity to the <em>session</em>, opening one during the handshake when the
 * client has not, and answering with the {@code <Session>} header so the client
 * can carry it. Every later request bearing that session id runs as the
 * identity established here. That binding is this project's decision, not the
 * specification's.
 * <p>
 * A separate contract from {@link XmlaAuthenticator} because it lives on the
 * other side of the SOAP boundary: the transport never sees it, the SOAP layer
 * drives it. Without a registered implementation an {@code Authenticate} is
 * refused with the error for an unsupported authentication method
 * ({@code 0xC10E0002}), which a real {@code msolap.dll} reads and reports
 * rather than retrying.
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
