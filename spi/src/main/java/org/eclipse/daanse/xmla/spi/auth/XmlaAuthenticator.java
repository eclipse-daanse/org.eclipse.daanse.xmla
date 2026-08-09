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
package org.eclipse.daanse.xmla.spi.auth;

import java.security.Principal;
import java.util.Set;

import org.eclipse.daanse.xmla.spi.XmlaRequest;

/**
 * One pluggable authentication mechanism at the HTTP boundary.
 * <p>
 * The transport holds a chain of these — Basic today; SPNEGO/Kerberos and OIDC
 * Bearer are further implementations, registered as OSGi services, with no
 * transport change. Three modes of operation fall out of the same chain:
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
     * Called for every request, in chain order, until a mechanism answers something
     * other than {@link Result.NotMine}. The request is complete — headers, URL —
     * so a mechanism that needs more than a credential string (SPNEGO's handshake,
     * a Bearer token's audience) has what there is to have.
     */
    Result authenticate(XmlaRequest request);

    /** The outcome of one authentication attempt. */
    sealed interface Result {

        /**
         * The request carries nothing this mechanism recognises. The chain moves on.
         */
        record NotMine() implements Result {
        }

        /** The credentials identify this principal, holding these roles. */
        record Authenticated(Principal principal, Set<String> roles) implements Result {
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
