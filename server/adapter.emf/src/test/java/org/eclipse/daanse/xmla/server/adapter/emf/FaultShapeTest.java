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

import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.daanse.xmla.api.XmlaConnector;
import org.eclipse.daanse.xmla.api.XmlaRefusedException;
import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.Execute;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    private static final String EXECUTE = """
            <Execute xmlns="urn:schemas-microsoft-com:xml-analysis">\
            <Command><Statement>SELECT FROM [C]</Statement></Command>\
            <Properties><PropertyList/></Properties></Execute>""";

    private static String send(String body) {
        return send(STUB, body);
    }

    private static String send(XmlaConnector connector, String body) {
        ByteArrayOutputStream answer = new ByteArrayOutputStream();
        new EmfXmlaAdapter(connector, null, null).handle(
                new ByteArrayInputStream(ENVELOPE.formatted(body).getBytes(StandardCharsets.UTF_8)), answer,
                XmlaRequest.anonymous());
        return answer.toString(StandardCharsets.UTF_8);
    }

    /** A connector that refuses every request with the given refusal. */
    private static XmlaConnector refusing(XmlaRefusedException refusal) {
        return new XmlaConnector() {

            @Override
            public List<EObject> discover(Discover request, XmlaRequest context) {
                throw refusal;
            }

            @Override
            public EObject execute(Execute request, XmlaRequest context) {
                throw refusal;
            }
        };
    }

    private static Document parsed(String answer) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        return factory.newDocumentBuilder().parse(new ByteArrayInputStream(answer.getBytes(StandardCharsets.UTF_8)));
    }

    private static String faultCodeOf(Document answer) {
        return answer.getElementsByTagNameNS("*", "faultcode").item(0).getTextContent();
    }

    private static Element errorOf(Document answer) {
        return (Element) answer.getElementsByTagNameNS("*", "Error").item(0);
    }

    @Test
    void anUnknownRequestTypeFaultsWithItsCode() throws Exception {
        Document answer = parsed(send(discoverOf("NO_SUCH_ROWSET")));

        // Present, and a decimal unsigned 32-bit number - the client parses it as one; the fault code names the same
        // number in hexadecimal, as Analysis Services writes it.
        assertThat(errorOf(answer).getAttribute("ErrorCode")).isEqualTo("3238789172");
        assertThat(faultCodeOf(answer)).isEqualTo("XMLAnalysisError.0xc10c0034");
    }

    /**
     * A trace that exists already, as Analysis Services 2017 answers it: {@code XMLAnalysisError.0xc1140002} with
     * {@code ErrorCode} 3239313410 and the sentence as {@code Description}. A connector's refusal with that number is
     * answered alike, on Discover and on Execute.
     */
    @Test
    void aRefusalWithANumberFaultsAsAnalysisServicesDoes() throws Exception {
        XmlaRefusedException refusal = new XmlaRefusedException(XmlaRefusedException.Side.CLIENT, 0xC1140002L,
                "Errors in the metadata manager. The trace with the name of 'T' already exists in the 'S' server.");

        for (String request : List.of(discoverOf("DISCOVER_TRACES"), EXECUTE)) {
            Document answer = parsed(send(refusing(refusal), request));

            assertThat(faultCodeOf(answer)).isEqualTo("XMLAnalysisError.0xc1140002");
            assertThat(errorOf(answer).getAttribute("ErrorCode")).isEqualTo("3239313410");
            assertThat(errorOf(answer).getAttribute("Description")).isEqualTo(refusal.getMessage());
        }
    }

    /**
     * An event number Analysis Services does not know is refused with two errors, the range error and the parse error
     * naming where the number stood; the fault lists both in that order, and its code and faultstring are the second's.
     */
    @Test
    void aRefusalWithTwoErrorsListsBothAndIsCodedByTheLast() throws Exception {
        XmlaRefusedException refusal = new XmlaRefusedException(XmlaRefusedException.Side.CLIENT, List.of(
                new XmlaRefusedException.Error(0xC11B0002L, "Trace: An event number encountered while evaluating the"
                        + " trace definition was not within the range of acceptable values."),
                new XmlaRefusedException.Error(0xC10F0014L, "An error occurred while parsing the 'EventID' element.")));

        Document answer = parsed(send(refusing(refusal), EXECUTE));

        assertThat(faultCodeOf(answer)).isEqualTo("XMLAnalysisError.0xc10f0014");
        assertThat(answer.getElementsByTagNameNS("*", "faultstring").item(0).getTextContent())
                .isEqualTo("An error occurred while parsing the 'EventID' element.");
        var errors = answer.getElementsByTagNameNS("*", "Error");
        assertThat(errors.getLength()).isEqualTo(2);
        assertThat(((Element) errors.item(0)).getAttribute("ErrorCode")).isEqualTo("3239772162");
        assertThat(((Element) errors.item(1)).getAttribute("ErrorCode")).isEqualTo("3238985748");
    }

    /**
     * An element an object definition cannot hold is refused as Analysis Services refuses it: {@code 0xC10F0007}, the
     * element named with its line, column and namespace, and the path of the element it stands in.
     */
    @Test
    void anElementThatCannotAppearInATraceIsRefusedWhereItStands() throws Exception {
        String create = """
                <Execute xmlns="urn:schemas-microsoft-com:xml-analysis"><Command>\
                <Create xmlns="http://schemas.microsoft.com/analysisservices/2003/engine"><ObjectDefinition><Trace>\
                <ID>T</ID><Name>T</Name><EventType><Events/></EventType></Trace></ObjectDefinition></Create>\
                </Command><Properties><PropertyList/></Properties></Execute>""";

        Document answer = parsed(send(create));

        assertThat(faultCodeOf(answer)).isEqualTo("XMLAnalysisError.0xc10f0007");
        assertThat(errorOf(answer).getAttribute("Description")).matches("The EventType element at line 1, column \\d+"
                + " \\(namespace http://schemas.microsoft.com/analysisservices/2003/engine\\) cannot appear under"
                + " Envelope/Body/Execute/Command/Create/ObjectDefinition/Trace\\.");
    }

    @Test
    void aRefusalWithoutANumberKeepsSoapsOwnCode() throws Exception {
        Document answer = parsed(send(refusing(new XmlaRefusedException(XmlaRefusedException.Side.CLIENT,
                "A trace with a log file (LogFileName) is not supported by this server.")), EXECUTE));

        assertThat(faultCodeOf(answer)).isEqualTo("soap:Client");
        assertThat(errorOf(answer)).as("no invented number").isNull();
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
