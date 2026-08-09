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
package org.eclipse.daanse.xmla.spi;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * What the transport knows about a request and the message does not.
 * <p>
 * The principal decides which roles a request may claim, {@code User-Agent} is
 * what a connector can use to tell clients apart, and the URL is what
 * {@code DISCOVER_DATASOURCES} reports back so a client can reconnect. The
 * session id lives in a SOAP header rather than in HTTP, so the transport
 * starts the request without one and the SOAP layer adds it via
 * {@link #withSession}.
 *
 * @param principal who was authenticated, or {@code null} for an anonymous
 *                  request — whether by a transport mechanism (Basic, SPNEGO,
 *                  Bearer), by a trusted proxy header, or by the in-band
 *                  {@code Authenticate} handshake
 * @param roles     the roles that principal holds
 * @param headers   the HTTP request headers
 * @param url       the URL this endpoint was reached at
 * @param sessionId the session this request belongs to, or {@code null} for
 *                  none
 */
public record XmlaRequest(Principal principal, Set<String> roles, Map<String, List<String>> headers, String url,
        String sessionId) {

    public XmlaRequest {
        roles = roles == null ? Set.of() : Set.copyOf(roles);
        headers = headers == null ? Map.of() : Map.copyOf(headers);
    }

    /** What a transport can know: everything but the session. */
    public XmlaRequest(Principal principal, Set<String> roles, Map<String, List<String>> headers, String url) {
        this(principal, roles, headers, url, null);
    }

    /** An anonymous request with nothing known about it. */
    public static XmlaRequest anonymous() {
        return new XmlaRequest(null, Set.of(), Map.of(), null, null);
    }

    /** The same request, once the session header has been read and accepted. */
    public XmlaRequest withSession(String sessionId) {
        return new XmlaRequest(principal, roles, headers, url, sessionId);
    }

    /**
     * The same request, once a principal has been established after the fact — the
     * in-band {@code Authenticate} handshake ends with one.
     */
    public XmlaRequest withPrincipal(Principal principal, Set<String> roles) {
        return new XmlaRequest(principal, roles, headers, url, sessionId);
    }

    /** Whether anyone was authenticated at all. */
    public boolean isAnonymous() {
        return principal == null;
    }

    /**
     * A header value, or {@code null}.
     * <p>
     * HTTP header names are case-insensitive (RFC 9110 §5.1) and clients differ:
     * some send {@code User-Agent}, some {@code user-agent}.
     */
    public String header(String name) {
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(name)) {
                List<String> values = entry.getValue();
                return values == null || values.isEmpty() ? null : values.get(0);
            }
        }
        return null;
    }

    /** Whether the authenticated principal holds a role. */
    public boolean hasRole(String role) {
        for (String held : roles) {
            if (held.equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }

    /** The principal's name, or the empty string when the request is anonymous. */
    public String userName() {
        return principal == null ? "" : principal.getName();
    }
}
