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
 * One pluggable authentication mechanism at the HTTP boundary.
 * <p>
 * The transport holds a chain of these - Basic, SPNEGO/Negotiate, OIDC Bearer
 * and a trusted proxy header are separate bundles, registered as OSGi services,
 * with no transport change. Three modes of operation fall out of the same
 * chain:
 * <ol>
 * <li><strong>fully anonymous</strong> — nothing registered, every request
 * passes through anonymous, and whether that is enough is the connector's
 * decision;</li>
 * <li><strong>fronted</strong> — a reverse proxy (Authelia and the like)
 * authenticates and forwards the identity in trusted headers, which a
 * header-reading implementation turns into the principal;</li>
 * <li><strong>own mechanisms</strong> — the transport challenges and validates
 * itself.</li>
 * </ol>
 * <p>
 * Each mechanism reads the request itself — the {@code Authorization} header
 * for a scheme-based one, a proxy header for a fronted one — and says
 * {@link Result.NotMine} when the request carries nothing it recognises. A
 * request nobody claims is <em>not</em> challenged up front: XMLA clients probe
 * {@code DISCOVER_PROPERTIES} and {@code DISCOVER_DATASOURCES} before they log
 * in, so the challenge comes when the backend refuses the anonymous request —
 * see {@code AuthenticationRequiredException}.
 */
public interface XmlaAuthenticator {

    /**
     * The scheme this mechanism answers to — {@code Basic}, {@code Negotiate},
     * {@code Bearer}. Informational; matching is this mechanism's own business
     * inside {@link #authenticate}.
     */
    String scheme();

    /**
     * The challenge to offer an unauthenticated client in {@code WWW-Authenticate},
     * e.g. {@code Basic realm="Daanse XMLA"} or plain {@code Negotiate}.
     *
     * @return the challenge, or the empty string for a mechanism that cannot be
     *         asked for — a trusted proxy header is presented by the proxy or not
     *         at all
     */
    String challenge();

    /**
     * Judges the request.
     * <p>
     * Called for every request, in the order {@link AuthenticationChain} defines,
     * until a mechanism answers something other than {@link Result.NotMine}. The
     * request is complete — headers, URL — so a mechanism that needs more than a
     * credential string (SPNEGO's handshake, a Bearer token's audience) has what
     * there is to have.
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
         * @param responseHeaderValue a {@code WWW-Authenticate} value to write on the
         *                            successful response, or {@code null} for none -
         *                            SPNEGO's mutual authentication ends with a token
         *                            the client verifies, and dropping it leaves a
         *                            client that asked for mutual authentication
         *                            waiting for something that never comes
         */
        record Authenticated(AuthenticatedIdentity identity, String responseHeaderValue) implements Result {

            public static Authenticated of(AuthenticatedIdentity identity) {
                return new Authenticated(identity, null);
            }
        }

        /**
         * Nobody was identified, and this is who to serve the request as.
         * <p>
         * Distinct from {@link Authenticated} because it proves nothing: the chain
         * applies it only after every mechanism has answered {@link NotMine}, an access
         * rule that demands authentication does not accept it, and a session or
         * container identity displaces it. A mechanism that answered
         * {@code Authenticated} instead would silently switch all of that off.
         */
        record Fallback(AuthenticatedIdentity identity) implements Result {
        }

        /**
         * The handshake continues: answer 401 with this exact {@code WWW-Authenticate}
         * value.
         * <p>
         * This is SPNEGO's shape — the server's token rides back in the challenge
         * ({@code Negotiate <base64>}), and the client's next request carries the next
         * step.
         */
        record Challenge(String headerValue) implements Result {
        }

        /** The credentials are this mechanism's kind, and they are wrong. */
        record Refused(String reason) implements Result {
        }
    }
}
