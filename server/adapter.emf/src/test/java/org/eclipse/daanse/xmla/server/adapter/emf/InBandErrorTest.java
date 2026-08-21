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

import org.eclipse.daanse.xmla.api.XmlaCommandFailedException;
import org.eclipse.daanse.xmla.api.XmlaConnector;
import org.eclipse.daanse.xmla.api.XmlaRefusedException;
import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.Execute;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;

/**
 * The two ways a response says something went wrong, and the difference between
 * them.
 * <p>
 * A Microsoft server answers a writeback commit it cannot honour with HTTP 200,
 * an ordinary result root, and the error inside it - the session survives. A
 * fault means the message itself was not processed. Both shapes are observable
 * on the wire, and a client tells them apart, so this fixes which exception
 * produces which.
 */
class InBandErrorTest {

    private static final String ENVELOPE = """
            <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">\
            <soap:Body>%s</soap:Body></soap:Envelope>""";

    private static final String EXECUTE = """
            <Execute xmlns="urn:schemas-microsoft-com:xml-analysis">\
            <Command><Statement>COMMIT TRANSACTION</Statement></Command>\
            <Properties><PropertyList/></Properties></Execute>""";

    private static final String DISCOVER = """
            <Discover xmlns="urn:schemas-microsoft-com:xml-analysis">\
            <RequestType>DISCOVER_PROPERTIES</RequestType>\
            <Restrictions><RestrictionList/></Restrictions>\
            <Properties><PropertyList/></Properties></Discover>""";

    private static String send(XmlaConnector connector, String body) {
        ByteArrayOutputStream answer = new ByteArrayOutputStream();
        new EmfXmlaAdapter(connector).handle(
                new ByteArrayInputStream(ENVELOPE.formatted(body).getBytes(StandardCharsets.UTF_8)), answer,
                XmlaRequest.anonymous());
        return answer.toString(StandardCharsets.UTF_8);
    }

    private static XmlaConnector throwing(RuntimeException failure) {
        return new XmlaConnector() {

            @Override
            public List<EObject> discover(Discover request, XmlaRequest context) {
                throw failure;
            }

            @Override
            public EObject execute(Execute request, XmlaRequest context) {
                throw failure;
            }
        };
    }

    @Test
    void aFailedCommandIsAnErrorInsideTheEmptyRoot() {
        String answer = send(throwing(new XmlaCommandFailedException(1234L,
                "Cell writeback errors: no writeback table", "Eclipse Daanse OLAP", null)), EXECUTE);

        assertThat(answer).doesNotContain("Fault");
        assertThat(answer).contains("urn:schemas-microsoft-com:xml-analysis:empty");
        assertThat(answer).contains("Exception");
        assertThat(answer).contains("Cell writeback errors: no writeback table");
        assertThat(answer).contains("ErrorCode=\"1234\"");
        assertThat(answer).contains("Source=\"Eclipse Daanse OLAP\"");
    }

    @Test
    void anErrorCodeThisServerDoesNotHaveIsLeftOffRatherThanInvented() {
        String answer = send(throwing(new XmlaCommandFailedException("the commit did not happen")), EXECUTE);

        assertThat(answer).contains("the commit did not happen");
        assertThat(answer).doesNotContain("ErrorCode");
        assertThat(answer).doesNotContain("Source=");
    }

    @Test
    void aDiscoverReportsTheSameWay() {
        String answer = send(throwing(new XmlaCommandFailedException("this rowset could not be built")), DISCOVER);

        assertThat(answer).doesNotContain("Fault");
        assertThat(answer).contains("this rowset could not be built");
    }

    @Test
    void aRefusalIsStillAFault() {
        // The distinction is the point: refusing is about the request, failing is
        // about the command.
        String answer = send(throwing(new XmlaRefusedException(XmlaRefusedException.Side.CLIENT, "not this caller")),
                EXECUTE);

        assertThat(answer).contains("soap:Fault");
        assertThat(answer).contains("not this caller");
    }
}
