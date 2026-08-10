/*
* Copyright (c) 2023 Contributors to the Eclipse Foundation.
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
import java.net.InetSocketAddress;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.eclipse.daanse.xmla.server.adapter.emf.AccessPolicy;
import org.eclipse.daanse.xmla.server.adapter.emf.EmfXmlaAdapter;
import org.eclipse.daanse.xmla.api.XmlaConnector;
import org.eclipse.daanse.xmla.api.XmlaSessionHandler;
import org.eclipse.daanse.xmla.api.auth.InbandAuthenticator;
import org.eclipse.daanse.xmla.api.auth.AuthenticationChain;
import org.eclipse.daanse.xmla.api.auth.XmlaAuthenticator;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.net.httpserver.HttpServer;

/**
 * The HTTP front of the EMF stack: one endpoint, served by whatever
 * {@link XmlaConnector} is registered.
 * <p>
 * Authentication is composed, not configured: every mechanism - Basic,
 * Negotiate, Bearer, a trusted proxy header - is an {@link XmlaAuthenticator}
 * service, and the chain is whatever is registered. Registering nothing is the
 * fully anonymous endpoint. The chain is read per request, so a mechanism that
 * appears later joins without restarting this endpoint.
 */
@Component(scope = ServiceScope.PROTOTYPE, immediate = true)
public class JdkHttpServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdkHttpServer.class);
    private HttpServer server = null;

    /** The backend this endpoint serves. */
    @Reference(cardinality = ReferenceCardinality.MANDATORY)
    private XmlaConnector connector;

    /** Optional: without it the endpoint is stateless and opens no sessions. */
    @Reference(cardinality = ReferenceCardinality.OPTIONAL, policyOption = ReferencePolicyOption.GREEDY)
    private volatile XmlaSessionHandler sessionHandler;

    /**
     * Optional: without it an in-band Authenticate is refused with the spec's own
     * error.
     */
    @Reference(cardinality = ReferenceCardinality.OPTIONAL, policyOption = ReferencePolicyOption.GREEDY)
    private volatile InbandAuthenticator inbandAuthenticator;

    /**
     * Every additionally registered mechanism joins the chain - SPNEGO/Kerberos and
     * OIDC live here, as services, with no change to this class.
     */
    /**
     * Bound through methods rather than into a collection, because the chain has to
     * see each mechanism's service properties: the ranking is what decides the
     * order they are asked in, and a collection reference carries no ranking at
     * all.
     */
    private final AuthenticationChain authenticators = new AuthenticationChain();

    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    void bindAuthenticator(XmlaAuthenticator mechanism, Map<String, Object> properties) {
        authenticators.add(mechanism, properties);
    }

    void unbindAuthenticator(XmlaAuthenticator mechanism) {
        authenticators.remove(mechanism);
    }

    @ObjectClassDefinition
    @interface Config {
        int port() default 8090;

        String contextPath() default "/xmla";

        /**
         * Origins a browser client may call this endpoint from: empty switches CORS
         * off, {@code *} allows any (echoed per request, credentials stay allowed),
         * otherwise a comma-separated list.
         */
        String corsAllowedOrigins() default "";

        /**
         * Scheme, host and port this server is reachable under from the outside, e.g.
         * {@code https://bi.example.org}. Prepended to the request URI to form the URL
         * reported in {@code DISCOVER_DATASOURCES}. Empty means "report the path only".
         */
        String publicBaseUrl() default "";

        /**
         * Whether unauthenticated requests are restricted to the anonymous rowsets. Off
         * means fully anonymous operation.
         */
        boolean requirePrincipal() default false;

        /**
         * The rowsets an unauthenticated client may still ask when requirePrincipal is
         * on - what a client needs before it can authenticate meaningfully.
         */
        String[] anonymousRowsets() default { "DISCOVER_PROPERTIES", "DISCOVER_DATASOURCES", "DISCOVER_SCHEMA_ROWSETS",
                "DISCOVER_ENUMERATORS", "DISCOVER_KEYWORDS", "DISCOVER_LITERALS" };

        int backlog() default 0;

        int maxThreads() default 50;

        int stopDelaySeconds() default 5;
    }

    @Activate
    public void activate(Config config) throws IOException {
        LOGGER.debug("Starting JDK HTTP server");
        server = HttpServer.create(new InetSocketAddress(config.port()), config.backlog());

        EmfXmlaAdapter adapter = new EmfXmlaAdapter(connector, sessionHandler, inbandAuthenticator,
                new AccessPolicy(config.requirePrincipal(), anonymousRowsets(config)));
        server.createContext(config.contextPath(), new EmfXmlaHttpHandler(adapter, config.publicBaseUrl(),
                () -> authenticators, config.corsAllowedOrigins()));

        server.setExecutor(
                new ThreadPoolExecutor(0, config.maxThreads(), 60L, TimeUnit.SECONDS, new SynchronousQueue<>()));
        server.start();
        LOGGER.debug("JDK HTTP server started on port {}", config.port());
    }

    /**
     * A duplicate would make {@code Set.of} throw and the endpoint never come up,
     * so the repetition is reported and ignored instead.
     */
    private static Set<String> anonymousRowsets(Config config) {
        Set<String> named = new LinkedHashSet<>();
        for (String rowset : config.anonymousRowsets()) {
            if (!named.add(rowset)) {
                LOGGER.warn("the rowset {} is listed more than once as anonymously readable", rowset);
            }
        }
        return Set.copyOf(named);
    }

    @Deactivate
    public void deactivate(Config config) {
        LOGGER.debug("Stopping JDK HTTP server");
        if (server != null) {
            server.stop(config.stopDelaySeconds());
            server = null;
        }
        LOGGER.debug("JDK HTTP server stopped");
    }
}
