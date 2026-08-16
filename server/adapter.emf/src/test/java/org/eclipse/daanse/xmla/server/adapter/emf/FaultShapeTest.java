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
 * The shape of a fault, and the one rule that makes it readable at the far end.
 * <p>
 * A client reads {@code Error/@ErrorCode} without asking whether it is there:
 * ADOMD hands the attribute straight to {@code XmlConvert.ToUInt32}, which
 * throws on {@code null} and is not caught. An {@code <Error>} without a code
 * therefore replaces our message with the client's own parse failure. Since a
 * fault needs either that element or a {@code faultstring}, and the
 * {@code faultstring} is always written, the element is simply left out where
 * no code is known — rather than carrying an invented one.
 */
class FaultShapeTest {

    private static final String ENVELOPE = """
            <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">\
            <soap:Body>%s</soap:Body></soap:Envelope>""";

    private static String discoverOf(String requestType) {
        return """
                <Discover xmlns="urn:schemas-microsoft-com:xml-analysis">\
                <RequestType>%s</RequestType>\
                <Restrictions><RestrictionList/></Restrictions>\
                <Properties><PropertyList/></Properties></Discover>""".formatted(requestType);
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

    private static String send(String body) {
        ByteArrayOutputStream answer = new ByteArrayOutputStream();
        new EmfXmlaAdapter(STUB, null, null).handle(
                new ByteArrayInputStream(ENVELOPE.formatted(body).getBytes(StandardCharsets.UTF_8)), answer,
                XmlaRequest.anonymous());
        return answer.toString(StandardCharsets.UTF_8);
    }

    @Test
    void anUnknownRequestTypeFaultsWithItsCode() {
        String answer = send(discoverOf("NO_SUCH_ROWSET"));

        assertThat(answer).contains("Fault");
        assertThat(answer).contains("Error");
        // Present, and a decimal unsigned 32-bit number - the client parses it as one.
        assertThat(answer).containsPattern("ErrorCode=\"\\d+\"");
    }

    @Test
    void aFaultWithoutAKnownCodeCarriesNoErrorElement() {
        String answer = send("<NotXmlaAtAll/>");

        assertThat(answer).contains("Fault");
        assertThat(answer).as("the message travels in faultstring").contains("faultstring");
        // Neither the element nor an empty attribute: both would be read as a code.
        assertThat(answer).doesNotContain("ErrorCode");
        assertThat(answer).doesNotContain("<detail>");
    }
}
