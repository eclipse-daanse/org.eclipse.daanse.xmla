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
import java.net.InetSocketAddress;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.daanse.xmla.server.adapter.emf.EmfXmlaAdapter;
import org.eclipse.daanse.xmla.api.AuthenticationRequiredException;
import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.api.auth.AuthenticationChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * The XMLA endpoint: HTTP in, HTTP out, nothing in between held in memory.
 * <p>
 * The response streams: the adapter writes straight onto the socket, so a
 * result of any size costs one row at a time. The {@code 200} is sent lazily on
 * the first write, since a status cannot be taken back once a byte is out -
 * anything decided before then still picks its own status line.
 * <p>
 * Authentication is an {@link AuthenticationChain} and permissive here: a
 * request nobody claims runs anonymously, because clients probe
 * {@code DISCOVER_PROPERTIES} before they log in. The refusal comes from the
 * backend as {@link AuthenticationRequiredException} and is answered
 * {@code 401} with the challenge of every mechanism that has one, {@code 403}
 * when none does.
 */
public class EmfXmlaHttpHandler implements HttpHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmfXmlaHttpHandler.class);

    private final EmfXmlaAdapter adapter;
    private final String publicBaseUrl;
    private final Supplier<AuthenticationChain> authenticators;
    private final String corsAllowedOrigins;

    public EmfXmlaHttpHandler(EmfXmlaAdapter adapter, String publicBaseUrl,
            Supplier<AuthenticationChain> authenticators) {
        this(adapter, publicBaseUrl, authenticators, "");
    }

    /**
     * As above, with cross-origin support: {@code corsAllowedOrigins} names the
     * origins a browser client may call this endpoint from - empty switches CORS
     * off, {@code *} allows any. Without these headers a browser blocks the call
     * before a single XMLA byte is exchanged.
     */
    public EmfXmlaHttpHandler(EmfXmlaAdapter adapter, String publicBaseUrl,
            Supplier<AuthenticationChain> authenticators, String corsAllowedOrigins) {
        this.adapter = adapter;
        this.publicBaseUrl = publicBaseUrl == null ? "" : publicBaseUrl;
        // A supplier, not a copy: a mechanism registered after this endpoint came up
        // has to join the chain without reactivating it.
        this.authenticators = authenticators;
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
                LOGGER.debug("authentication required: {}", refused.getMessage());
                refuse(exchange, authenticators.get().challenges());
            }
        } catch (IOException | RuntimeException e) {
            LOGGER.error("failed while serving {}", exchange.getRequestURI(), e);
            throw e;
        } catch (Error e) {
            // An Error is not this handler's to recover from, but dropping it here would
            // close the connection with nothing written and nothing logged - the client
            // sees a reset and the server looks healthy. It is logged and rethrown.
            LOGGER.error("error while serving {}", exchange.getRequestURI(), e);
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
        AuthenticationChain.Outcome outcome = authenticators.get().run(request);
        if (outcome instanceof AuthenticationChain.Outcome.Proceed proceed) {
            // SPNEGO ends with a token the client verifies; it has to travel on the
            // successful response, not on a challenge.
            for (String header : proceed.responseHeaders()) {
                exchange.getResponseHeaders().add("WWW-Authenticate", header);
            }
            return proceed.request();
        }
        AuthenticationChain.Outcome.Reject reject = (AuthenticationChain.Outcome.Reject) outcome;
        LOGGER.debug("not served: {}", reject.reason());
        return refuse(exchange, reject.challenges());
    }

    /**
     * 401 with the challenges offered, or 403 when there are none - there is
     * nothing for the client to try again with.
     */
    private XmlaRequest refuse(HttpExchange exchange, List<String> challenges) throws IOException {
        for (String challenge : challenges) {
            exchange.getResponseHeaders().add("WWW-Authenticate", challenge);
        }
        exchange.sendResponseHeaders(challenges.isEmpty() ? 403 : 401, -1);
        return null;
    }

    private XmlaRequest requestOf(HttpExchange exchange) {
        Map<String, List<String>> headers = new LinkedHashMap<>();
        for (Map.Entry<String, List<String>> entry : exchange.getRequestHeaders().entrySet()) {
            headers.put(entry.getKey(), List.copyOf(entry.getValue()));
        }
        return new XmlaRequest(null, null, headers, url(exchange), peerOf(exchange));
    }

    private static String peerOf(HttpExchange exchange) {
        InetSocketAddress remote = exchange.getRemoteAddress();
        if (remote == null || remote.getAddress() == null) {
            // Unresolved, which is what an address the JDK could not resolve looks like.
            return null;
        }
        return remote.getAddress().getHostAddress();
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
