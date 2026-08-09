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
package org.eclipse.daanse.xmla.server.jdk.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.daanse.xmla.server.adapter.emf.EmfXmlaAdapter;
import org.eclipse.daanse.xmla.spi.AuthenticationRequiredException;
import org.eclipse.daanse.xmla.spi.XmlaRequest;
import org.eclipse.daanse.xmla.spi.auth.XmlaAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * The XMLA endpoint: HTTP in, HTTP out, nothing in between held in memory.
 * <p>
 * The response streams: the adapter writes straight onto the socket, so a
 * result of any size costs one row at a time. A status code cannot be taken
 * back once the first byte is out, so the {@code 200} is sent lazily, on the
 * first write - everything decided before then, such as an authentication
 * challenge or a connector demanding a login, still chooses its own status
 * line.
 * <p>
 * Authentication is a chain of {@link XmlaAuthenticator}s and permissive at
 * this layer: a request nobody claims runs anonymously, because XMLA clients
 * probe {@code DISCOVER_PROPERTIES} before they log in. The refusal, if any,
 * comes from the backend as {@link AuthenticationRequiredException} and is
 * answered {@code 401} with the challenge of every mechanism that has one, or
 * {@code 403} when none does.
 */
public class EmfXmlaHttpHandler implements HttpHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmfXmlaHttpHandler.class);

    private final EmfXmlaAdapter adapter;
    private final String publicBaseUrl;
    private final List<XmlaAuthenticator> authenticators;
    private final String corsAllowedOrigins;

    public EmfXmlaHttpHandler(EmfXmlaAdapter adapter, String publicBaseUrl, List<XmlaAuthenticator> authenticators) {
        this(adapter, publicBaseUrl, authenticators, "");
    }

    /**
     * As above, with cross-origin support: {@code corsAllowedOrigins} names the
     * origins a browser client may call this endpoint from - empty switches CORS
     * off, {@code *} allows any. Without these headers a browser blocks the call
     * before a single XMLA byte is exchanged.
     */
    public EmfXmlaHttpHandler(EmfXmlaAdapter adapter, String publicBaseUrl, List<XmlaAuthenticator> authenticators,
            String corsAllowedOrigins) {
        this.adapter = adapter;
        this.publicBaseUrl = publicBaseUrl == null ? "" : publicBaseUrl;
        this.authenticators = List.copyOf(authenticators);
        this.corsAllowedOrigins = corsAllowedOrigins == null ? "" : corsAllowedOrigins.trim();
    }

    /**
     * The CORS answer for one exchange, or nothing when CORS is off or the origin
     * is not allowed. Credentials are always allowed, so a {@code *} configuration
     * echoes the caller's origin instead of the literal star, which browsers refuse
     * in combination with credentials.
     */
    private void corsHeaders(HttpExchange exchange) {
        if (corsAllowedOrigins.isEmpty()) {
            return;
        }
        String origin = exchange.getRequestHeaders().getFirst("Origin");
        if (origin == null) {
            return;
        }
        boolean allowed = "*".equals(corsAllowedOrigins);
        if (!allowed) {
            for (String candidate : corsAllowedOrigins.split(",")) {
                if (candidate.trim().equals(origin)) {
                    allowed = true;
                }
            }
        }
        if (!allowed) {
            return;
        }
        var headers = exchange.getResponseHeaders();
        headers.set("Access-Control-Allow-Origin", origin);
        headers.set("Access-Control-Allow-Credentials", "true");
        headers.set("Vary", "Origin");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            corsHeaders(exchange);
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                // The browser's preflight. Answered before authentication on purpose:
                // a preflight carries no credentials, and a 401 here would read to the
                // browser as "CORS forbidden" rather than "log in first".
                var headers = exchange.getResponseHeaders();
                headers.set("Access-Control-Allow-Methods", "POST, OPTIONS");
                String requested = exchange.getRequestHeaders().getFirst("Access-Control-Request-Headers");
                headers.set("Access-Control-Allow-Headers",
                        requested == null || requested.isEmpty() ? "Content-Type, Authorization, SOAPAction"
                                : requested);
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                // XMLA is POST-only; answering a GET with a fault would suggest the request
                // was understood.
                exchange.getResponseHeaders().set("Allow", "POST");
                exchange.sendResponseHeaders(405, -1);
                return;
            }

            XmlaRequest request = requestOf(exchange);
            XmlaRequest authenticated = authenticate(exchange, request);
            if (authenticated == null) {
                return; // a challenge or a refusal already went out
            }

            exchange.getResponseHeaders().set("Content-Type", "text/xml; charset=utf-8");
            LazyBody body = new LazyBody(exchange);
            try (InputStream in = exchange.getRequestBody()) {
                adapter.handle(in, body, authenticated);
                body.closeIfStarted();
            } catch (AuthenticationRequiredException refused) {
                // Thrown before the first byte by construction, so the status line is still
                // free to choose.
                challenge(exchange, refused.getMessage());
            }
        } catch (IOException | RuntimeException e) {
            LOGGER.error("failed while serving {}", exchange.getRequestURI(), e);
            throw e;
        } finally {
            exchange.close();
        }
    }

    /**
     * Runs the chain.
     *
     * @return the request, possibly with a principal — or {@code null} when a
     *         challenge or refusal has been sent and the request is over
     */
    private XmlaRequest authenticate(HttpExchange exchange, XmlaRequest request) throws IOException {
        for (XmlaAuthenticator authenticator : authenticators) {
            XmlaAuthenticator.Result result = authenticator.authenticate(request);
            if (result instanceof XmlaAuthenticator.Result.NotMine) {
                continue;
            }
            if (result instanceof XmlaAuthenticator.Result.Authenticated who) {
                return request.withPrincipal(who.principal(), who.roles());
            }
            if (result instanceof XmlaAuthenticator.Result.Challenge next) {
                // A handshake in progress - SPNEGO's server token rides back in the challenge.
                exchange.getResponseHeaders().add("WWW-Authenticate", next.headerValue());
                exchange.sendResponseHeaders(401, -1);
                return null;
            }
            XmlaAuthenticator.Result.Refused refused = (XmlaAuthenticator.Result.Refused) result;
            LOGGER.debug("refused by {}: {}", authenticator.scheme(), refused.reason());
            challenge(exchange, refused.reason());
            return null;
        }
        // Nobody claimed it: anonymous, and whether that is enough is the connector's
        // decision.
        return request;
    }

    /**
     * 401 with every challenge the chain can offer, or 403 when it has none to
     * offer.
     */
    private void challenge(HttpExchange exchange, String reason) throws IOException {
        boolean offered = false;
        for (XmlaAuthenticator authenticator : authenticators) {
            if (!authenticator.challenge().isEmpty()) {
                exchange.getResponseHeaders().add("WWW-Authenticate", authenticator.challenge());
                offered = true;
            }
        }
        LOGGER.debug("authentication required: {}", reason);
        exchange.sendResponseHeaders(offered ? 401 : 403, -1);
    }

    private XmlaRequest requestOf(HttpExchange exchange) {
        Map<String, List<String>> headers = new LinkedHashMap<>();
        for (Map.Entry<String, List<String>> entry : exchange.getRequestHeaders().entrySet()) {
            headers.put(entry.getKey(), List.copyOf(entry.getValue()));
        }
        return new XmlaRequest(null, null, headers, url(exchange));
    }

    /**
     * What {@code DISCOVER_DATASOURCES} reports as the URL to reconnect to.
     * <p>
     * Behind a reverse proxy the request URI is the internal path, which a client
     * cannot reach; {@code publicBaseUrl} is how the deployment says what the
     * outside sees.
     */
    private String url(HttpExchange exchange) {
        return publicBaseUrl.isEmpty() ? exchange.getRequestURI().toString() : publicBaseUrl + exchange.getRequestURI();
    }

    /**
     * The response body, with the {@code 200} sent on the first byte rather than up
     * front.
     * <p>
     * Everything the adapter decides before it writes, including a connector's
     * {@link AuthenticationRequiredException}, happens while the status line is
     * still free to choose. After the first byte the stream is the answer.
     */
    private static final class LazyBody extends OutputStream {

        private final HttpExchange exchange;
        private OutputStream target;

        private LazyBody(HttpExchange exchange) {
            this.exchange = exchange;
        }

        private OutputStream started() throws IOException {
            if (target == null) {
                exchange.sendResponseHeaders(200, 0);
                target = exchange.getResponseBody();
            }
            return target;
        }

        @Override
        public void write(int single) throws IOException {
            started().write(single);
        }

        @Override
        public void write(byte[] bytes, int offset, int length) throws IOException {
            started().write(bytes, offset, length);
        }

        @Override
        public void flush() throws IOException {
            if (target != null) {
                target.flush();
            }
        }

        void closeIfStarted() throws IOException {
            if (target != null) {
                target.close();
            }
        }
    }
}
