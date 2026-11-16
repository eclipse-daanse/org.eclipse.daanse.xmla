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
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.model.io;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.StringReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import org.eclipse.daanse.xmla.model.engine.Event;
import org.eclipse.daanse.xmla.model.engine.Trace;
import org.eclipse.daanse.xmla.model.xmla.Create;
import org.eclipse.daanse.xmla.model.xmla.Execute;
import org.eclipse.daanse.xmla.model.xmla.Statement;
import org.junit.jupiter.api.Test;

/**
 * A command is read with where each of its parts stood -- the path from the envelope down, the line and the column --
 * so a refusal of what it says can name the place as Analysis Services does; and an object definition refuses an
 * element that cannot appear in it, in Analysis Services' words.
 */
class ACommandIsReadWithWhereItStoodTest {

    private static final String ENGINE = "http://schemas.microsoft.com/analysisservices/2003/engine";

    private static Execute read(String command) throws Exception {
        String xml = "<Execute xmlns=\"urn:schemas-microsoft-com:xml-analysis\"><Command>" + command
                + "</Command><Properties><PropertyList/></Properties></Execute>";
        XMLStreamReader in = XMLInputFactory.newFactory().createXMLStreamReader(new StringReader(xml));
        while (in.next() != XMLStreamConstants.START_ELEMENT) {
            // to <Execute>
        }
        return ExecuteRequestReader.read(in);
    }

    private static String trace(String inside) {
        return "<Create xmlns=\"" + ENGINE + "\"><ObjectDefinition><Trace><ID>T</ID><Name>T</Name>" + inside
                + "</Trace></ObjectDefinition></Create>";
    }

    @Test
    void anEventKnowsWhereItAndItsEventIdStood() throws Exception {
        Execute execute = read(trace("<Events><Event><EventID>10</EventID></Event></Events>"));
        Trace definition = ((Create) execute.getCommand()).getObjectDefinition().getTrace();
        Event event = definition.getEventType().getEvents().get(0);

        ElementLocation location = ElementLocation.of(event).orElseThrow();
        assertThat(location.start().path())
                .isEqualTo("Envelope/Body/Execute/Command/Create/ObjectDefinition/Trace/Events/Event");
        assertThat(location.start().namespace()).isEqualTo(ENGINE);
        assertThat(location.start().line()).isEqualTo(1);
        assertThat(location.end()).hasValueSatisfying(
                end -> assertThat(end.column()).isGreaterThan(location.start().column()));
        assertThat(location.child("EventID")).hasValueSatisfying(id -> {
            assertThat(id.path()).isEqualTo(location.start().path() + "/EventID");
            assertThat(id.column()).isGreaterThan(location.start().column());
        });
    }

    @Test
    void anElementThatCannotAppearInATraceIsRefusedWithItsPlace() {
        assertThatThrownBy(() -> read(trace("<EventType><Events/></EventType>")))
                .isInstanceOfSatisfying(XmlaParseException.class, refused -> {
                    assertThat(refused.errorCode()).isEqualTo(0xC10F0007L);
                    assertThat(refused).hasMessageMatching("The EventType element at line 1, column \\d+ \\(namespace "
                            + ENGINE.replace(".", "\\.") + "\\) cannot appear under"
                            + " Envelope/Body/Execute/Command/Create/ObjectDefinition/Trace\\.");
                });
    }

    @Test
    void aStatementPassesOverWhatItDoesNotKnow() throws Exception {
        Execute execute = read("<Statement>SELECT FROM [Sales]</Statement>");

        assertThat(((Statement) execute.getCommand()).getStatement()).isEqualTo("SELECT FROM [Sales]");
    }
}
