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

import org.eclipse.daanse.xmla.api.XmlaConnector;
import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.Execute;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;

/**
 * An envelope with nothing to say carries no {@code Header} element.
 * <p>
 * This looks like tidiness and is not. {@code <soap:Header/>} is one
 * self-closing node: a reader that pairs {@code ReadStartElement} with
 * {@code ReadEndElement} consumes the entire element on the first call, then
 * takes the <em>next</em> end element with the second. From there it is one
 * element out of step for the whole document and eventually reads past the end,
 * which surfaces as a parse failure at the last byte, nowhere near the cause. A
 * recorded Analysis Services writes {@code <soap:Envelope><soap:Body>} with no
 * header at all.
 */
class EmptyHeaderTest {

    private static final String ENVELOPE = """
            <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">\
            <soap:Body>%s</soap:Body></soap:Envelope>""";

    private static final String DISCOVER = """
            <Discover xmlns="urn:schemas-microsoft-com:xml-analysis">\
            <RequestType>DISCOVER_SCHEMA_ROWSETS</RequestType>\
            <Restrictions><RestrictionList/></Restrictions>\
            <Properties><PropertyList/></Properties></Discover>""";

    /** A request that opens a session, which is what puts a header in the answer. */
    private static final String BEGIN_SESSION = """
            <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">\
            <soap:Header><BeginSession xmlns="urn:schemas-microsoft-com:xml-analysis" \
            soap:mustUnderstand="1"/></soap:Header>\
            <soap:Body><Execute xmlns="urn:schemas-microsoft-com:xml-analysis">\
            <Command><Statement/></Command><Properties><PropertyList/></Properties>\
            </Execute></soap:Body></soap:Envelope>""";

    /** A session handler, because only a session puts a header in an answer. */
    private static final class Sessions extends org.eclipse.daanse.xmla.api.SimpleSessionHandler {
    }

    private static String send(String message) {
        return send(message, null);
    }

    private static String send(String message, Sessions sessions) {
        XmlaConnector connector = new XmlaConnector() {

            @Override
            public List<EObject> discover(Discover request, XmlaRequest context) {
                return List.of();
            }

            @Override
            public EObject execute(Execute request, XmlaRequest context) {
                return null;
            }
        };
        ByteArrayOutputStream answer = new ByteArrayOutputStream();
        new EmfXmlaAdapter(connector, sessions, null).handle(
                new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8)), answer, XmlaRequest.anonymous());
        return answer.toString(StandardCharsets.UTF_8);
    }

    @Test
    void anAnswerWithNoHeadersHasNoHeaderElement() {
        String answer = send(ENVELOPE.formatted(DISCOVER));

        assertThat(answer).as("an empty Header puts a paired-read parser one element out of step")
                .doesNotContain("Header");
        assertThat(answer).as("the body still follows the envelope directly").contains("Envelope").contains("Body");
    }

    /**
     * The other half: when there <em>is</em> something to say, it must still be
     * said. Removing the element unconditionally would silence the session id.
     */
    @Test
    void anAnswerWithAHeaderStillCarriesIt() {
        String answer = send(BEGIN_SESSION, new Sessions());

        assertThat(answer).as("BeginSession is answered with a Session header").contains("Header")
                .contains("Session");
    }
}
