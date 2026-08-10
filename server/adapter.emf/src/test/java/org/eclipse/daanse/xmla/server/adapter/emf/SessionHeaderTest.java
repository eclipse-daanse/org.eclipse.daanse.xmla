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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.daanse.xmla.api.SimpleSessionHandler;
import org.eclipse.daanse.xmla.api.XmlaConnector;
import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.Execute;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;

/**
 * The session rule [MS-SSAS] 3.1.3.1 states as a MUST, and the two ordering
 * decisions around it.
 */
class SessionHeaderTest {

    private static final Pattern SESSION_ID = Pattern.compile("SessionId=\"([^\"]*)\"");

    private static final String ENVELOPE = """
            <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">\
            <soap:Header>%s</soap:Header>\
            <soap:Body>%s</soap:Body></soap:Envelope>""";

    private static final String BEGIN = "<BeginSession xmlns=\"urn:schemas-microsoft-com:xml-analysis\"/>";

    private static final String DISCOVER = """
            <Discover xmlns="urn:schemas-microsoft-com:xml-analysis">\
            <RequestType>DISCOVER_PROPERTIES</RequestType>\
            <Restrictions><RestrictionList/></Restrictions>\
            <Properties><PropertyList/></Properties></Discover>""";

    private final Sessions sessions = new Sessions();

    private static final class Sessions extends SimpleSessionHandler {

        private int opened;
        private final List<String> ended = new java.util.ArrayList<>();

        @Override
        protected void onBeginSession(String sessionId, XmlaRequest request) {
            opened++;
        }

        @Override
        protected void onEndSession(String sessionId) {
            ended.add(sessionId);
        }
    }

    private static final XmlaConnector STUB = new XmlaConnector() {

        @Override
        public List<EObject> discover(Discover request, XmlaRequest context) {
            return List.of();
        }

        @Override
        public EObject execute(Execute request, XmlaRequest context) {
            return null;
        }
    };

    private static String session(String sessionId) {
        return "<Session xmlns=\"urn:schemas-microsoft-com:xml-analysis\" SessionId=\"" + sessionId + "\"/>";
    }

    private static String endSession(String sessionId) {
        return "<EndSession xmlns=\"urn:schemas-microsoft-com:xml-analysis\" SessionId=\"" + sessionId + "\"/>";
    }

    private String send(String headers, String body) {
        EmfXmlaAdapter adapter = new EmfXmlaAdapter(STUB, sessions, null);
        ByteArrayOutputStream answer = new ByteArrayOutputStream();
        adapter.handle(new ByteArrayInputStream(ENVELOPE.formatted(headers, body).getBytes(StandardCharsets.UTF_8)),
                answer, XmlaRequest.anonymous());
        return answer.toString(StandardCharsets.UTF_8);
    }

    private static String sessionIdOf(String message) {
        Matcher matcher = SESSION_ID.matcher(message);
        return matcher.find() ? matcher.group(1) : null;
    }

    @Test
    void aSessionIdTheServerDoesNotHonourIsAFault() {
        String answer = send(session("11111111-2222-3333-4444-555555555555"), DISCOVER);

        assertThat(answer).contains("soap:Client");
        assertThat(answer).contains("is not valid or has timed out");
        // The specification names no error code for this fault, so none is invented.
        assertThat(answer).doesNotContain("ErrorCode");
    }

    @Test
    void endingASessionTheServerDoesNotHonourIsTheSameFault() {
        String answer = send(endSession("11111111-2222-3333-4444-555555555555"), DISCOVER);

        assertThat(answer).contains("is not valid or has timed out");
    }

    @Test
    void aSessionIsOpenedAndThenHonoured() {
        String opened = sessionIdOf(send(BEGIN, DISCOVER));
        assertThat(opened).isNotNull();

        String answer = send(session(opened), DISCOVER);
        assertThat(answer).doesNotContain("Fault");
        assertThat(sessionIdOf(answer)).isEqualTo(opened);
    }

    @Test
    void aValidSessionOutranksBeginSessionInTheSameMessage() {
        String opened = sessionIdOf(send(BEGIN, DISCOVER));
        int before = sessions.opened;

        String answer = send(BEGIN + session(opened), DISCOVER);

        // A second id would need a second response header, and nobody could ever end
        // it.
        assertThat(sessions.opened).isEqualTo(before);
        assertThat(sessionIdOf(answer)).isEqualTo(opened);
    }

    @Test
    void aStaleSessionBesideBeginSessionIsStillAFault() {
        String answer = send(BEGIN + session("11111111-2222-3333-4444-555555555555"), DISCOVER);

        assertThat(answer).contains("is not valid or has timed out");
    }

    @Test
    void endSessionClosesItAndTheNextRequestIsRefused() {
        String opened = sessionIdOf(send(BEGIN, DISCOVER));

        assertThat(send(endSession(opened), DISCOVER)).doesNotContain("Fault");
        assertThat(sessions.ended).containsExactly(opened);
        assertThat(send(session(opened), DISCOVER)).contains("is not valid or has timed out");
    }

    @Test
    void aSessionOpenedByAMessageThatFaultsDoesNotSurviveIt() {
        int before = sessions.ended.size();

        String answer = send(BEGIN, "<NotXmlaAtAll/>");

        assertThat(answer).contains("Fault");
        // The client was never told the id, so nothing could ever end it.
        assertThat(sessions.ended).hasSize(before + 1);
    }

    @Test
    void aHeaderDemandingUnderstandingIsRefusedBeforeASessionIsOpened() {
        int before = sessions.opened;

        String answer = send(BEGIN + "<Whatever xmlns=\"urn:example\" soap:mustUnderstand=\"1\"/>", DISCOVER);

        assertThat(answer).contains("MustUnderstand");
        assertThat(sessions.opened).as("a refused message opens nothing").isEqualTo(before);
    }
}
