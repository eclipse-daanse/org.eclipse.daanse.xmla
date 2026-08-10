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
 * One pluggable authentication mechanism at the HTTP boundary. Registered as an OSGi
 * service; see the package documentation for how the chain uses it.
 * <p>
 * A mechanism reads the request itself and answers {@link Result.NotMine} when it
 * finds nothing it recognises.
 */
public interface XmlaAuthenticator {

    /**
     * The scheme this mechanism answers to — {@code Basic}, {@code Negotiate},
     * {@code Bearer}. Informational; matching happens in {@link #authenticate}.
     */
    String scheme();

    /**
     * The {@code WWW-Authenticate} value to offer an unauthenticated client.
     *
     * @return the challenge, or the empty string for a mechanism that cannot be asked
     *         for — a trusted proxy header is presented by the proxy or not at all
     */
    String challenge();

    /**
     * Judges the request. Called with the complete request, so a mechanism needing
     * more than a credential string has what there is to have.
     */
    Result authenticate(XmlaRequest request);

    /** The outcome of one authentication attempt. */
    sealed interface Result {

        /**
         * The request carries nothing this mechanism recognises. The chain moves on.
         */
        record NotMine() implements Result {
        }

        /**
         * The credentials identify this caller.
         *
         * @param responseHeaderValue a {@code WWW-Authenticate} value for the successful
         *                            response, or {@code null}. SPNEGO mutual
         *                            authentication needs it: dropping it leaves a client
         *                            that asked for mutual authentication waiting
         */
        record Authenticated(AuthenticatedIdentity identity, String responseHeaderValue) implements Result {

            public static Authenticated of(AuthenticatedIdentity identity) {
                return new Authenticated(identity, null);
            }
        }

        /**
         * Nobody was identified, and this is who to serve the request as.
         * <p>
         * Separate from {@link Authenticated} because it proves nothing: it is applied
         * only after every mechanism passed, it never satisfies a rule that demands
         * authentication, and a session or container identity displaces it.
         */
        record Fallback(AuthenticatedIdentity identity) implements Result {
        }

        /**
         * The handshake continues: answer 401 with this exact
         * {@code WWW-Authenticate} value. SPNEGO's shape — the server's token rides
         * back in the challenge and the client's next request carries the next step.
         */
        record Challenge(String headerValue) implements Result {
        }

        /** The credentials are this mechanism's kind, and they are wrong. */
        record Refused(String reason) implements Result {
        }
    }
}
