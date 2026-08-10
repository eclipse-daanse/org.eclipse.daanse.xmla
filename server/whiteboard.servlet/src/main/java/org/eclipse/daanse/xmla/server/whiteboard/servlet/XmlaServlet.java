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
package org.eclipse.daanse.xmla.server.whiteboard.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.daanse.xmla.server.adapter.emf.AccessPolicy;
import org.eclipse.daanse.xmla.server.adapter.emf.EmfXmlaAdapter;
import org.eclipse.daanse.xmla.api.AuthenticationRequiredException;
import org.eclipse.daanse.xmla.api.XmlaConnector;
import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.api.XmlaSessionHandler;
import org.eclipse.daanse.xmla.api.auth.InbandAuthenticator;
import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;
import org.eclipse.daanse.xmla.api.auth.AuthenticationChain;
import org.eclipse.daanse.xmla.api.auth.Claims;
import org.eclipse.daanse.xmla.api.auth.IdentitySource;
import org.eclipse.daanse.xmla.api.auth.RoleResolution;
import org.eclipse.daanse.xmla.api.auth.XmlaAuthenticator;
import org.eclipse.daanse.xmla.server.whiteboard.servlet.api.ocd.ServletConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.util.converter.Converters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The XMLA endpoint as a servlet, registered through the OSGi HTTP Whiteboard.
 * <p>
 * The counterpart to the standalone JDK HTTP server: the same
 * {@link EmfXmlaAdapter} over the same {@link XmlaConnector}, but sharing host,
 * port, context path and servlet filters with whatever else the application
 * serves. The whiteboard properties - above all
 * {@code osgi.http.whiteboard.servlet.pattern} - come from this component's
 * configuration.
 * <p>
 * Streaming and the lazily written status line work as they do there: the
 * adapter writes onto the response as rows are produced, and the {@code 200} is
 * committed on the first byte, so anything decided before then - an
 * authentication challenge, a connector demanding a login - still chooses its
 * own status.
 * <p>
 * CORS is left to the servlet filters of the container, which is the point of
 * being a servlet at all.
 * <p>
 * One consequence of that is worth knowing before configuring a mechanism that
 * cares who the peer is. The address reported here is {@code getRemoteAddr()},
 * and a container that processes {@code Forwarded}/{@code X-Forwarded-For} -
 * Tomcat's {@code RemoteIpValve}, Jetty's {@code ForwardedRequestCustomizer} -
 * has already replaced it with the original client's; the socket peer is then
 * unavailable to anything running here. A mechanism that must know the request
 * came from a particular front needs a proof that does not depend on the
 * address, which is why the trusted-header mechanism offers a shared secret
 * alongside its address list.
 */
@Component(service = Servlet.class, scope = ServiceScope.PROTOTYPE, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = ServletConfig.class, factory = true)
public class XmlaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlaServlet.class);

    /** The backend this endpoint serves. */
    @Reference(cardinality = ReferenceCardinality.MANDATORY)
    private transient XmlaConnector connector;

    /** Optional: without it the endpoint is stateless and opens no sessions. */
    @Reference(cardinality = ReferenceCardinality.OPTIONAL, policyOption = ReferencePolicyOption.GREEDY)
    private volatile transient XmlaSessionHandler sessionHandler;

    /**
     * Optional: without it an in-band Authenticate is refused with the spec's own
     * error.
     */
    @Reference(cardinality = ReferenceCardinality.OPTIONAL, policyOption = ReferencePolicyOption.GREEDY)
    private volatile transient InbandAuthenticator inbandAuthenticator;

    /**
     * Mechanisms registered as services join the chain. There is no
     * container-configured mechanism here: HTTP authentication in a servlet
     * deployment belongs to the container or to a filter in front.
     * <p>
     * They are bound through methods rather than into a collection, because the
     * chain has to see each mechanism's service properties: the ranking is what
     * decides the order they are asked in.
     */
    private final transient AuthenticationChain authenticators = new AuthenticationChain();

    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    void bindAuthenticator(XmlaAuthenticator mechanism, Map<String, Object> properties) {
        authenticators.add(mechanism, properties);
    }

    void unbindAuthenticator(XmlaAuthenticator mechanism) {
        authenticators.remove(mechanism);
    }

    @Reference(cardinality = ReferenceCardinality.MANDATORY)
    private volatile transient RoleResolution roles;

    private volatile transient List<String> containerRoles = List.of();

    private transient EmfXmlaAdapter adapter;

    @Activate
    void activate(Map<String, Object> properties) {
        ServletConfig config = Converters.standardConverter().convert(properties).to(ServletConfig.class);
        containerRoles = List.of(config.containerRoles());
        adapter = new EmfXmlaAdapter(connector, sessionHandler, inbandAuthenticator,
                new AccessPolicy(config.requirePrincipal(), anonymousRowsets(config)));
        if (config.requirePrincipal() && authenticators.isEmpty() && containerRoles.isEmpty()) {
            LOGGER.warn("a principal is required but no mechanism is registered; every request will be refused "
                    + "with nothing to try again with");
        }
        LOGGER.debug("XMLA servlet ready");
    }

    /**
     * A duplicate would make {@code Set.of} throw and the endpoint never come up,
     * so the repetition is reported and ignored instead.
     */
    private static Set<String> anonymousRowsets(ServletConfig config) {
        Set<String> named = new LinkedHashSet<>();
        for (String rowset : config.anonymousRowsets()) {
            if (!named.add(rowset)) {
                LOGGER.warn("the rowset {} is listed more than once as anonymously readable", rowset);
            }
        }
        return Set.copyOf(named);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xml; charset=utf-8");

        XmlaRequest xmlaRequest = authenticate(request, response, requestOf(request));
        if (xmlaRequest == null) {
            return; // a challenge or a refusal already went out
        }

        LazyBody body = new LazyBody(response);
        try (InputStream in = request.getInputStream()) {
            adapter.handle(in, body, xmlaRequest);
            body.closeIfStarted();
        } catch (AuthenticationRequiredException refused) {
            // Thrown before the first byte by construction, so the status line is still
            // free to choose.
            LOGGER.debug("authentication required: {}", refused.getMessage());
            refuse(response, authenticators.challenges());
        }
    }

    /**
     * Runs the chain.
     *
     * @return the request, possibly with a principal - or {@code null} when a
     *         challenge or refusal has been sent and the request is over
     */
    private XmlaRequest authenticate(HttpServletRequest request, HttpServletResponse response, XmlaRequest xmlaRequest)
            throws IOException {
        // The container is asked first among the things that prove something: it
        // authenticated before this servlet was reached, and a stand-in identity from
        // the chain must not shadow a caller the container actually logged in.
        XmlaRequest incoming = fromContainer(request, xmlaRequest);
        if (incoming.isAuthenticated()) {
            return incoming;
        }

        AuthenticationChain.Outcome outcome = authenticators.run(incoming);
        if (outcome instanceof AuthenticationChain.Outcome.Proceed proceed) {
            for (String header : proceed.responseHeaders()) {
                response.addHeader("WWW-Authenticate", header);
            }
            return proceed.request();
        }
        AuthenticationChain.Outcome.Reject reject = (AuthenticationChain.Outcome.Reject) outcome;
        LOGGER.debug("not served: {}", reject.reason());
        refuse(response, reject.challenges());
        return null;
    }

    /**
     * What the container authenticated, if anything.
     * <p>
     * A servlet deployment may sit behind an authenticating filter or the
     * container's own login, which wrap the request with the principal they
     * established. That identity is taken as it stands, and its roles are the ones
     * every mechanism resolves plus whichever of the configured names the container
     * confirms - the Servlet API can only be asked about a role by name, so naming
     * them is the only way to learn them at all.
     */
    private XmlaRequest fromContainer(HttpServletRequest request, XmlaRequest xmlaRequest) {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return xmlaRequest;
        }
        Set<String> granted = new LinkedHashSet<>(roles.resolve(principal, Claims.none()));
        for (String role : containerRoles) {
            if (request.isUserInRole(role)) {
                granted.add(role);
            }
        }
        return xmlaRequest.withIdentity(new AuthenticatedIdentity(principal, granted, Claims.none()),
                IdentitySource.CONTAINER);
    }

    /**
     * 401 with the challenges offered, or 403 when there are none - there is
     * nothing for the client to try again with.
     */
    private void refuse(HttpServletResponse response, List<String> challenges) throws IOException {
        for (String challenge : challenges) {
            response.addHeader("WWW-Authenticate", challenge);
        }
        response.sendError(
                challenges.isEmpty() ? HttpServletResponse.SC_FORBIDDEN : HttpServletResponse.SC_UNAUTHORIZED);
    }

    private XmlaRequest requestOf(HttpServletRequest request) {
        Map<String, List<String>> headers = new LinkedHashMap<>();
        for (String name : Collections.list(request.getHeaderNames())) {
            headers.put(name, new ArrayList<>(Collections.list(request.getHeaders(name))));
        }
        return new XmlaRequest(null, null, headers, url(request), request.getRemoteAddr());
    }

    /**
     * What {@code DISCOVER_DATASOURCES} reports as the URL to reconnect to: the URL
     * this request came in on, which the container has already resolved against the
     * whiteboard pattern this servlet is registered under.
     */
    private String url(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    /**
     * The response body, with the status committed on the first byte rather than up
     * front, so that everything the adapter decides before it writes can still
     * choose its own status line.
     */
    private static final class LazyBody extends OutputStream {

        private final HttpServletResponse response;
        private OutputStream target;

        private LazyBody(HttpServletResponse response) {
            this.response = response;
        }

        private OutputStream started() throws IOException {
            if (target == null) {
                response.setStatus(HttpServletResponse.SC_OK);
                target = response.getOutputStream();
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
