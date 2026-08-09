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

import java.security.Principal;
import java.util.Set;

import org.eclipse.daanse.xmla.api.XmlaRequest;

/**
 * The in-band {@code Authenticate} handshake of the specification.
 * <p>
 * XMLA has its own login besides HTTP: the client posts {@code <Authenticate>}
 * (ext namespace) carrying an SSPI blob in the SOAP body, the server answers
 * {@code <AuthenticateResponse>} with its own blob, and the exchange repeats
 * until the security package on both ends is done. What is authenticated
 * afterwards is not the connection — HTTP has none to speak of — but the
 * <em>session</em>: the handshake runs inside a session opened with
 * {@code BeginSession}, and every later request in that session carries the
 * identity established here.
 * <p>
 * This is a separate contract from {@link XmlaAuthenticator} because it lives
 * on the other side of the SOAP boundary: the transport never sees it, the SOAP
 * layer drives it, and only a backend that actually implements a security
 * package (Kerberos via GSS-API, NTLM) registers one. Without it, an
 * {@code Authenticate} is refused with the specification's own error for an
 * unsupported authentication method ({@code 0xC10E0002}) — which a real
 * {@code msolap.dll} reads and reports, rather than retrying forever.
 */
public interface InbandAuthenticator {

    /**
     * Advances the handshake by one round.
     *
     * @param sspiBlob the client's token, exactly as carried in
     *                 {@code <SspiHandshake>}
     * @param request  the request the handshake arrived on — its session id is the
     *                 state key
     */
    Result authenticate(byte[] sspiBlob, XmlaRequest request);

    /** The outcome of one handshake round. */
    sealed interface Result {

        /**
         * Not done yet: send this blob back in {@code AuthenticateResponse} and wait.
         */
        record Continue(byte[] sspiBlob) implements Result {
        }

        /**
         * Done: the peer is this principal. The final blob (possibly empty) still goes
         * back — some packages end with a mutual-authentication token the client
         * verifies.
         */
        record Done(Principal principal, Set<String> roles, byte[] sspiBlob) implements Result {
        }

        /**
         * The handshake failed. Answered as the specification's authentication fault.
         */
        record Refused(String reason) implements Result {
        }
    }
}
