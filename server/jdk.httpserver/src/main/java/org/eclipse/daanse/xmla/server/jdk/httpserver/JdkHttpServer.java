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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.eclipse.daanse.xmla.server.adapter.emf.AccessPolicy;
import org.eclipse.daanse.xmla.server.adapter.emf.EmfXmlaAdapter;
import org.eclipse.daanse.xmla.spi.XmlaConnector;
import org.eclipse.daanse.xmla.spi.XmlaSessionHandler;
import org.eclipse.daanse.xmla.spi.auth.InbandAuthenticator;
import org.eclipse.daanse.xmla.spi.auth.XmlaCredentialValidator;
import org.eclipse.daanse.xmla.spi.auth.XmlaAuthenticator;
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
 * {@link XmlaConnector} is registered, with authentication assembled from
 * configuration and from any {@link XmlaAuthenticator} services present.
 * <p>
 * Three modes of authentication, and all of them are compositions rather than
 * code paths: fully anonymous (register nothing, configure NONE), fronted by a
 * proxy (TRUSTED_HEADER - Authelia and its kind set the headers and this server
 * takes their word), or the server's own mechanisms (BASIC from configuration,
 * SPNEGO/Bearer as additional registered services).
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
    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    private final List<XmlaAuthenticator> registeredAuthenticators = new CopyOnWriteArrayList<>();

    /**
     * Optional: without it BASIC authentication cannot be configured. Deliberately
     * not defaulted - see {@link BasicXmlaAuthenticator}.
     */
    @Reference(cardinality = ReferenceCardinality.OPTIONAL, policyOption = ReferencePolicyOption.GREEDY)
    private volatile XmlaCredentialValidator credentialValidator;

    /**
     * How this endpoint identifies its callers, beyond any registered authenticator
     * services.
     */
    public enum Authentication {
        /**
         * Nothing configured. Anonymous unless a registered authenticator says
         * otherwise.
         */
        NONE,
        /** HTTP BASIC against an {@link XmlaCredentialValidator}. */
        BASIC,
        /**
         * A trusted front (reverse proxy) sets the user in headers, which are taken as
         * given.
         */
        TRUSTED_HEADER
    }

    @ObjectClassDefinition
    @interface Config {
        int port() default 8090;

        String contextPath() default "/xmla";

        Authentication authentication() default Authentication.NONE;

        /** The realm name a BASIC challenge shows the user. */
        String realm() default "Daanse XMLA";

        /**
         * Origins a browser client may call this endpoint from: empty switches CORS
         * off, {@code *} allows any (echoed per request, credentials stay allowed),
         * otherwise a comma-separated list.
         */
        String corsAllowedOrigins() default "";

        /** The header a trusted front puts the user name in. */
        String trustedUserHeader() default "Remote-User";

        /** The header a trusted front puts the comma-separated groups in. */
        String trustedGroupsHeader() default "Remote-Groups";

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

        List<XmlaAuthenticator> chain = new ArrayList<>();
        switch (config.authentication()) {
        case BASIC -> chain.add(new BasicXmlaAuthenticator(config.realm(), credentialValidator));
        case TRUSTED_HEADER ->
            chain.add(new TrustedHeaderAuthenticator(config.trustedUserHeader(), config.trustedGroupsHeader()));
        case NONE -> {
            /* anonymous unless a registered authenticator says otherwise */ }
        }
        chain.addAll(registeredAuthenticators);

        EmfXmlaAdapter adapter = new EmfXmlaAdapter(connector, sessionHandler, inbandAuthenticator,
                new AccessPolicy(config.requirePrincipal(), Set.of(config.anonymousRowsets())));
        server.createContext(config.contextPath(),
                new EmfXmlaHttpHandler(adapter, config.publicBaseUrl(), chain, config.corsAllowedOrigins()));

        server.setExecutor(
                new ThreadPoolExecutor(0, config.maxThreads(), 60L, TimeUnit.SECONDS, new SynchronousQueue<>()));
        server.start();
        LOGGER.debug("JDK HTTP server started on port {}", config.port());
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
