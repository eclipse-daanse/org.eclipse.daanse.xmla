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
package org.eclipse.daanse.xmla.server.adapter.emf;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.daanse.xmla.api.AuthenticationRequiredException;
import org.eclipse.daanse.xmla.api.SimpleSessionHandler;
import org.eclipse.daanse.xmla.api.XmlaConnector;
import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.model.mddataset.MdDatasetFactory;
import org.eclipse.daanse.xmla.model.rowset.multidimensional.MdschemaCubesRow;
import org.eclipse.daanse.xmla.model.rowset.multidimensional.RowsetMultidimensionalFactory;
import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.Execute;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;

/**
 * What happens when {@code BeginSession} rides on a request that actually asks
 * for something.
 * <p>
 * Every recorded client opens its session with an {@code Execute} whose command
 * is an empty {@code <Statement/>}, and it is easy to read that as the rule. It
 * is not. XMLA 1.1 p. 29 says the statement <em>may</em> be empty and that this
 * <em>can</em> be used to carry the header; [MS-SSAS] 3.1.3.1 shows
 * {@code BeginSession} above a body commented "Discover or Execute element goes
 * here", and 4.20.1 pairs it with a real {@code BeginTransaction}. So a client
 * may open a session and ask its question in one round trip, and these tests
 * hold what such a message gets back.
 * <p>
 * The order the adapter works in decides the answer, and it is: session headers
 * first ({@code applySessionHeaders}), identity second, the access policy only
 * when the body has been read and dispatched. Which means the session is minted
 * <em>before</em> the policy is consulted - and the {@code finally} in
 * {@code handle} is what keeps that from leaking a session to a caller who is
 * then refused.
 */
class SessionOpenedByARealRequestTest {

    private static final Pattern SESSION_ID = Pattern.compile("SessionId=\"([^\"]*)\"");

    private static final String ENVELOPE = """
            <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">\
            <soap:Header>%s</soap:Header>\
            <soap:Body>%s</soap:Body></soap:Envelope>""";

    private static final String BEGIN = "<BeginSession xmlns=\"urn:schemas-microsoft-com:xml-analysis\"/>";

    /**
     * Not the empty statement every recorded client uses: a real rowset request.
     */
    private static final String CUBES = """
            <Discover xmlns="urn:schemas-microsoft-com:xml-analysis">\
            <RequestType>MDSCHEMA_CUBES</RequestType>\
            <Restrictions><RestrictionList/></Restrictions>\
            <Properties><PropertyList/></Properties></Discover>""";

    /** Nor an empty command: a real query. */
    private static final String MDX = """
            <Execute xmlns="urn:schemas-microsoft-com:xml-analysis">\
            <Command><Statement>SELECT [Measures].MEMBERS ON 0 FROM [Adventure Works]</Statement></Command>\
            <Properties><PropertyList/></Properties></Execute>""";

    private final Sessions sessions = new Sessions();
    private final Connector connector = new Connector();

    private static final class Sessions extends SimpleSessionHandler {

        private int opened;
        private final List<String> ended = new ArrayList<>();

        @Override
        protected void onBeginSession(String sessionId, XmlaRequest request) {
            opened++;
        }

        @Override
        protected void onEndSession(String sessionId) {
            ended.add(sessionId);
        }

        private int live() {
            return opened - ended.size();
        }
    }

    /**
     * Answers with something recognizable, and records the session it was called
     * under.
     */
    private static final class Connector implements XmlaConnector {

        private final List<String> sawSession = new ArrayList<>();

        @Override
        public List<EObject> discover(Discover request, XmlaRequest context) {
            sawSession.add(context.sessionId());
            MdschemaCubesRow row = RowsetMultidimensionalFactory.eINSTANCE.createMdschemaCubesRow();
            row.setCatalogName("Adventure Works DW");
            row.setCubeName("Adventure Works");
            return List.of(row);
        }

        @Override
        public EObject execute(Execute request, XmlaRequest context) {
            sawSession.add(context.sessionId());
            return MdDatasetFactory.eINSTANCE.createMdDataset();
        }
    }

    private String send(String headers, String body, AccessPolicy policy) {
        EmfXmlaAdapter adapter = new EmfXmlaAdapter(connector, sessions, null, policy);
        ByteArrayOutputStream answer = new ByteArrayOutputStream();
        adapter.handle(new ByteArrayInputStream(ENVELOPE.formatted(headers, body).getBytes(StandardCharsets.UTF_8)),
                answer, XmlaRequest.anonymous());
        return answer.toString(StandardCharsets.UTF_8);
    }

    private String send(String headers, String body) {
        return send(headers, body, AccessPolicy.OPEN);
    }

    private static String sessionIdOf(String message) {
        Matcher matcher = SESSION_ID.matcher(message);
        return matcher.find() ? matcher.group(1) : null;
    }

    @Test
    void aRealDiscoverOpensTheSessionAndAnswersInTheSameMessage() {
        String answer = send(BEGIN, CUBES);

        assertThat(answer).doesNotContain("Fault");
        // Both halves come back together: there is no second round trip for the rows.
        assertThat(sessionIdOf(answer)).as("the new session id").isNotNull();
        assertThat(answer).as("and the rows that were asked for").contains("Adventure Works");
    }

    @Test
    void theRequestThatOpensTheSessionIsAlreadyInIt() {
        String answer = send(BEGIN, CUBES);

        // applySessionHeaders runs before dispatch, so the connector sees the id on the
        // very request that minted it - not from the next one onwards.
        assertThat(connector.sawSession).containsExactly(sessionIdOf(answer));
    }

    @Test
    void anMdxStatementOpensASessionJustAsWell() {
        String answer = send(BEGIN, MDX);

        assertThat(answer).doesNotContain("Fault");
        assertThat(sessionIdOf(answer)).isNotNull();
        assertThat(connector.sawSession).containsExactly(sessionIdOf(answer));
    }

    @Test
    void aRowsetTheClientMayNotHaveAnonymouslyLeavesNoSessionBehind() {
        AccessPolicy guarded = new AccessPolicy(true, Set.of("DISCOVER_PROPERTIES"));

        // Not a SOAP fault: the transport owns the challenge, so this leaves the
        // adapter.
        assertThatThrownBy(() -> send(BEGIN, CUBES, guarded)).isInstanceOf(AuthenticationRequiredException.class)
                .hasMessageContaining("MDSCHEMA_CUBES");

        // The policy is consulted after the session is minted, so one was opened and
        // had
        // to be given back. A refused caller must not be able to consume session slots.
        assertThat(sessions.opened).isEqualTo(1);
        assertThat(sessions.live()).as("nothing is left open for a caller who was refused").isZero();
    }

    @Test
    void aCommandIsNeverRunAnonymouslyUnderAGuardedPolicy() {
        AccessPolicy guarded = new AccessPolicy(true, Set.of("DISCOVER_PROPERTIES"));

        assertThatThrownBy(() -> send(BEGIN, MDX, guarded)).isInstanceOf(AuthenticationRequiredException.class)
                .hasMessageContaining("anonymously");

        assertThat(sessions.live()).isZero();
        assertThat(connector.sawSession).as("the connector is never reached").isEmpty();
    }
}
