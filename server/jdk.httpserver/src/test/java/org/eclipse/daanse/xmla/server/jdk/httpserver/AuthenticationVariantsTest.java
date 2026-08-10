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

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.daanse.xmla.api.SimpleSessionHandler;
import org.eclipse.daanse.xmla.api.XmlaConnector;
import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.api.auth.AuthRanking;
import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;
import org.eclipse.daanse.xmla.api.auth.AuthenticationChain;
import org.eclipse.daanse.xmla.api.auth.Claims;
import org.eclipse.daanse.xmla.api.auth.NamedPrincipal;
import org.eclipse.daanse.xmla.api.auth.XmlaAuthenticator;
import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.Execute;
import org.eclipse.daanse.xmla.server.adapter.emf.AccessPolicy;
import org.eclipse.daanse.xmla.server.adapter.emf.EmfXmlaAdapter;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Every combination that was never played through: with and without each
 * mechanism, with and without a session, with and without a stand-in identity,
 * and in both registration orders.
 * <p>
 * A real endpoint over a real socket, because the questions are about what the
 * transport does with a status line and a {@code WWW-Authenticate} header, and
 * because registration order is exactly the thing a unit test on the chain
 * alone would let itself get right by accident.
 */
class AuthenticationVariantsTest {

    private static final Pattern SESSION_ID = Pattern.compile("SessionId=\"([^\"]*)\"");

    private static final String DISCOVER = """
            <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">\
            <soap:Header>%s</soap:Header>\
            <soap:Body><Discover xmlns="urn:schemas-microsoft-com:xml-analysis">\
            <RequestType>DISCOVER_PROPERTIES</RequestType>\
            <Restrictions><RestrictionList/></Restrictions>\
            <Properties><PropertyList/></Properties></Discover>\
            </soap:Body></soap:Envelope>""";

    private static final String BEGIN = "<BeginSession xmlns=\"urn:schemas-microsoft-com:xml-analysis\"/>";

    private HttpServerUnderTest endpoint;

    @AfterEach
    void stop() {
        if (endpoint != null) {
            endpoint.close();
        }
    }

    /** Records who the connector was told is calling. */
    private static final class Recording implements XmlaConnector {

        private final List<XmlaRequest> seen = new ArrayList<>();

        @Override
        public List<EObject> discover(Discover request, XmlaRequest context) {
            seen.add(context);
            return List.of();
        }

        @Override
        public EObject execute(Execute request, XmlaRequest context) {
            seen.add(context);
            return null;
        }

        private XmlaRequest last() {
            return seen.isEmpty() ? null : seen.get(seen.size() - 1);
        }
    }

    private final class HttpServerUnderTest implements AutoCloseable {

        private final com.sun.net.httpserver.HttpServer server;
        private final Recording connector = new Recording();
        private final URI uri;

        private HttpServerUnderTest(AuthenticationChain chain, boolean requirePrincipal) throws IOException {
            EmfXmlaAdapter adapter = new EmfXmlaAdapter(connector, new SimpleSessionHandler() {
            }, null, new AccessPolicy(requirePrincipal, Set.of("DISCOVER_DATASOURCES")));
            server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
            server.createContext("/xmla", new EmfXmlaHttpHandler(adapter, "", () -> chain));
            server.start();
            uri = URI.create("http://127.0.0.1:" + server.getAddress().getPort() + "/xmla");
        }

        @Override
        public void close() {
            server.stop(0);
        }
    }

    private HttpServerUnderTest serving(AuthenticationChain chain, boolean requirePrincipal) throws IOException {
        endpoint = new HttpServerUnderTest(chain, requirePrincipal);
        return endpoint;
    }

    private static HttpResponse<String> post(HttpServerUnderTest endpoint, String body, String authorization,
            String session) throws Exception {
        String headers = session == null ? ""
                : "<Session xmlns=\"urn:schemas-microsoft-com:xml-analysis\" SessionId=\"" + session + "\"/>";
        HttpRequest.Builder request = HttpRequest.newBuilder(endpoint.uri)
                .POST(HttpRequest.BodyPublishers.ofString(body.formatted(headers), StandardCharsets.UTF_8));
        if (authorization != null) {
            request.header("Authorization", authorization);
        }
        return HttpClient.newHttpClient().send(request.build(), HttpResponse.BodyHandlers.ofString());
    }

    private static String basic(String user, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((user + ":" + password).getBytes(StandardCharsets.UTF_8));
    }

    private static AuthenticatedIdentity identity(String name, String... roles) {
        return new AuthenticatedIdentity(new NamedPrincipal(name), Set.of(roles), Claims.none());
    }

    private static XmlaAuthenticator mechanism(String name, String challenge,
            Function<XmlaRequest, XmlaAuthenticator.Result> answer) {
        return new XmlaAuthenticator() {

            @Override
            public String scheme() {
                return name;
            }

            @Override
            public String challenge() {
                return challenge;
            }

            @Override
            public Result authenticate(XmlaRequest request) {
                return answer.apply(request);
            }
        };
    }

    /** Accepts alice/secret and refuses anything else that names the scheme. */
    private static XmlaAuthenticator basicMechanism() {
        return mechanism("Basic", "Basic realm=\"test\"", request -> {
            String header = request.header("Authorization");
            if (header == null || !header.startsWith("Basic ")) {
                return new XmlaAuthenticator.Result.NotMine();
            }
            String decoded = new String(Base64.getDecoder().decode(header.substring(6)), StandardCharsets.UTF_8);
            return "alice:secret".equals(decoded)
                    ? XmlaAuthenticator.Result.Authenticated.of(identity("alice", "Admin"))
                    : new XmlaAuthenticator.Result.Refused("no");
        });
    }

    private static XmlaAuthenticator dummyMechanism() {
        return mechanism("Fixed", "", request -> new XmlaAuthenticator.Result.Fallback(identity("nobody")));
    }

    private static Map<String, Object> ranked(String ranking, long serviceId) {
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("service.ranking", Integer.valueOf(ranking));
        properties.put("service.id", serviceId);
        return properties;
    }

    private static AuthenticationChain chainOf(boolean reversed, boolean withBasic, boolean withDummy) {
        AuthenticationChain chain = new AuthenticationChain();
        Runnable addBasic = () -> chain.add(basicMechanism(), ranked(AuthRanking.BASIC, 1));
        Runnable addDummy = () -> chain.add(dummyMechanism(), ranked(AuthRanking.FIXED, 2));
        List<Runnable> order = new ArrayList<>();
        if (withBasic) {
            order.add(addBasic);
        }
        if (withDummy) {
            order.add(addDummy);
        }
        if (reversed) {
            java.util.Collections.reverse(order);
        }
        order.forEach(Runnable::run);
        return chain;
    }

    // --- no mechanism at all ---

    @Test
    void withNothingRegisteredAndNothingRequiredEveryoneIsServedAnonymously() throws Exception {
        HttpServerUnderTest endpoint = serving(new AuthenticationChain(), false);

        HttpResponse<String> answer = post(endpoint, DISCOVER, null, null);

        assertThat(answer.statusCode()).isEqualTo(200);
        assertThat(endpoint.connector.last().isAnonymous()).isTrue();
    }

    @Test
    void withNothingRegisteredAndAPrincipalRequiredTheAnswerSaysThereIsNothingToTry() throws Exception {
        HttpServerUnderTest endpoint = serving(new AuthenticationChain(), true);

        HttpResponse<String> answer = post(endpoint, DISCOVER, null, null);

        // 403, not 401: a challenge nobody can answer would be a lie.
        assertThat(answer.statusCode()).isEqualTo(403);
        assertThat(answer.headers().allValues("WWW-Authenticate")).isEmpty();
    }

    // --- with Basic ---

    @Test
    void withBasicTheRightCredentialsAreServedWithTheirRoles() throws Exception {
        HttpServerUnderTest endpoint = serving(chainOf(false, true, false), true);

        HttpResponse<String> answer = post(endpoint, DISCOVER, basic("alice", "secret"), null);

        assertThat(answer.statusCode()).isEqualTo(200);
        assertThat(endpoint.connector.last().userName()).isEqualTo("alice");
        assertThat(endpoint.connector.last().hasRole("Admin")).isTrue();
    }

    @Test
    void withBasicTheWrongCredentialsAreChallenged() throws Exception {
        HttpServerUnderTest endpoint = serving(chainOf(false, true, false), true);

        HttpResponse<String> answer = post(endpoint, DISCOVER, basic("alice", "wrong"), null);

        assertThat(answer.statusCode()).isEqualTo(401);
        assertThat(answer.headers().allValues("WWW-Authenticate")).contains("Basic realm=\"test\"");
    }

    @Test
    void withBasicAndNoCredentialsAnAnonymousProbeStillGetsThrough() throws Exception {
        // XMLA clients ask what a server is before they log in.
        HttpServerUnderTest endpoint = serving(chainOf(false, true, false), false);

        assertThat(post(endpoint, DISCOVER, null, null).statusCode()).isEqualTo(200);
        assertThat(endpoint.connector.last().isAnonymous()).isTrue();
    }

    // --- the stand-in, in both registration orders ---

    @Test
    void theStandInNeverDisplacesAMechanismThatWouldHaveAnswered() throws Exception {
        for (boolean reversed : List.of(false, true)) {
            HttpServerUnderTest endpoint = serving(chainOf(reversed, true, true), false);

            HttpResponse<String> answer = post(endpoint, DISCOVER, basic("alice", "secret"), null);

            assertThat(answer.statusCode()).as("registered reversed=%s", reversed).isEqualTo(200);
            assertThat(endpoint.connector.last().userName()).as("registered reversed=%s", reversed).isEqualTo("alice");
            stop();
        }
    }

    @Test
    void theStandInAnswersOnlyForCallersNobodyClaimed() throws Exception {
        HttpServerUnderTest endpoint = serving(chainOf(false, true, true), false);

        assertThat(post(endpoint, DISCOVER, null, null).statusCode()).isEqualTo(200);
        assertThat(endpoint.connector.last().userName()).isEqualTo("nobody");
    }

    @Test
    void theStandInDoesNotSatisfyARuleThatDemandsALogin() throws Exception {
        // It names a caller without proving anything. Answering Authenticated here is
        // what used to switch the whole access rule off.
        HttpServerUnderTest endpoint = serving(chainOf(false, false, true), true);

        assertThat(post(endpoint, DISCOVER, null, null).statusCode()).isIn(401, 403);
    }

    @Test
    void wrongCredentialsAreStillRefusedWhenAStandInIsRegistered() throws Exception {
        HttpServerUnderTest endpoint = serving(chainOf(false, true, true), false);

        assertThat(post(endpoint, DISCOVER, basic("alice", "wrong"), null).statusCode()).isEqualTo(401);
    }

    // --- sessions ---

    @Test
    void aSessionIsOpenedAndCarriedBack() throws Exception {
        HttpServerUnderTest endpoint = serving(chainOf(false, true, false), false);

        HttpResponse<String> answer = post(endpoint, DISCOVER.formatted(BEGIN + "%s"), null, null);

        assertThat(answer.statusCode()).isEqualTo(200);
        assertThat(sessionOf(answer.body())).isNotNull();
    }

    @Test
    void anUnknownSessionIsAFaultRatherThanASilentDowngrade() throws Exception {
        HttpServerUnderTest endpoint = serving(chainOf(false, true, false), false);

        HttpResponse<String> answer = post(endpoint, DISCOVER, null, "11111111-2222-3333-4444-555555555555");

        assertThat(answer.body()).contains("is not valid or has timed out");
    }

    @Test
    void aSessionIsHonouredOnTheNextRequest() throws Exception {
        HttpServerUnderTest endpoint = serving(chainOf(false, true, false), false);
        String session = sessionOf(post(endpoint, DISCOVER.formatted(BEGIN + "%s"), null, null).body());

        HttpResponse<String> answer = post(endpoint, DISCOVER, null, session);

        assertThat(answer.statusCode()).isEqualTo(200);
        assertThat(answer.body()).doesNotContain("Fault");
        assertThat(endpoint.connector.last().sessionId()).isEqualTo(session);
    }

    @Test
    void aStandInDoesNotDisplaceWhatASessionCarries() throws Exception {
        // The direct regression for the finding that installing the fixed-identity
        // bundle switched the in-band identity restore off.
        AuthenticationChain chain = chainOf(false, true, true);
        HttpServerUnderTest endpoint = serving(chain, false);
        String session = sessionOf(
                post(endpoint, DISCOVER.formatted(BEGIN + "%s"), basic("alice", "secret"), null).body());
        endpoint.connector.seen.clear();

        // A later request with no credentials at all, bearing only the session.
        post(endpoint, DISCOVER, null, session);

        assertThat(endpoint.connector.last().userName()).isEqualTo("alice");
    }

    private static String sessionOf(String message) {
        Matcher matcher = SESSION_ID.matcher(message);
        return matcher.find() ? matcher.group(1) : null;
    }
}
