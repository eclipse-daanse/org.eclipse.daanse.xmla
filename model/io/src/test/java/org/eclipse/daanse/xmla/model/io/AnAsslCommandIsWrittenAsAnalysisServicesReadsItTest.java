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
package org.eclipse.daanse.xmla.model.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import org.eclipse.daanse.xmla.model.engine.EngineFactory;
import org.eclipse.daanse.xmla.model.engine.Event;
import org.eclipse.daanse.xmla.model.engine.EventColumnID;
import org.eclipse.daanse.xmla.model.engine.EventType;
import org.eclipse.daanse.xmla.model.engine.MajorObject;
import org.eclipse.daanse.xmla.model.engine.Trace;
import org.eclipse.daanse.xmla.model.xmla.Command;
import org.eclipse.daanse.xmla.model.xmla.Create;
import org.eclipse.daanse.xmla.model.xmla.Execute;
import org.eclipse.daanse.xmla.model.xmla.Statement;
import org.eclipse.daanse.xmla.model.xmla.XmlaFactory;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * An ASSL command leaves the client in the shape Analysis Services takes: the command and everything under it in the
 * engine namespace, the events of a trace as {@code <Trace><Events><Event>}. Analysis Services 2017 refused both other
 * forms the codec wrote before: a {@code Create} in the XMLA namespace ("The Create element ... cannot appear under
 * Envelope/Body/Execute/Command") and, since the element of {@code Trace.eventType} is {@code Events}, a second
 * {@code Events} the old wrapper of {@code EventType.events} put inside it. A {@code Statement} stays in the XMLA
 * namespace.
 */
class AnAsslCommandIsWrittenAsAnalysisServicesReadsItTest {

    private static final EngineFactory ENGINE = EngineFactory.eINSTANCE;
    private static final XmlaFactory XMLA = XmlaFactory.eINSTANCE;

    private static Create createOfATrace() {
        Trace trace = ENGINE.createTrace();
        trace.setId("T1");
        trace.setName("T1");
        EventType events = ENGINE.createEventType();
        for (String id : List.of("9", "10")) {
            Event event = ENGINE.createEvent();
            event.setEventID(id);
            EventColumnID columns = ENGINE.createEventColumnID();
            columns.getColumnID().addAll(List.of("0", "42"));
            event.setColumns(columns);
            events.getEvents().add(event);
        }
        trace.setEventType(events);
        MajorObject definition = ENGINE.createMajorObject();
        definition.setTrace(trace);
        Create create = XMLA.createCreate();
        create.setObjectDefinition(definition);
        return create;
    }

    private static byte[] written(Command command) throws Exception {
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        XmlaMessageCodec.writeExecuteRequest(target, List.of(), command, null);
        return target.toByteArray();
    }

    private static Element commandOf(byte[] request) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(request));
        Element command = (Element) document.getElementsByTagNameNS(XmlaNamespaces.XMLA, "Command").item(0);
        return children(command).get(0);
    }

    private static List<Element> children(Element parent) {
        List<Element> children = new ArrayList<>();
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof Element element) {
                children.add(element);
            }
        }
        return children;
    }

    private static Element only(Element parent, String name) {
        List<Element> named = children(parent).stream().filter(child -> child.getLocalName().equals(name)).toList();
        assertThat(named).as(parent.getLocalName() + "/" + name).hasSize(1);
        return named.get(0);
    }

    @Test
    void aCreateAndItsTraceAreInTheEngineNamespace() throws Exception {
        Element create = commandOf(written(createOfATrace()));

        assertThat(create.getLocalName()).isEqualTo("Create");
        assertThat(create.getNamespaceURI()).isEqualTo(XmlaNamespaces.ENGINE);
        Element trace = only(only(create, "ObjectDefinition"), "Trace");
        assertThat(trace.getNamespaceURI()).isEqualTo(XmlaNamespaces.ENGINE);
        assertThat(only(trace, "ID").getNamespaceURI()).isEqualTo(XmlaNamespaces.ENGINE);
    }

    @Test
    void theEventsOfATraceAreOneEventsElementOfEvents() throws Exception {
        Element events = only(only(only(commandOf(written(createOfATrace())), "ObjectDefinition"), "Trace"), "Events");

        assertThat(children(events)).extracting(Element::getLocalName).containsExactly("Event", "Event");
        assertThat(only(children(events).get(0), "EventID").getTextContent()).isEqualTo("9");
    }

    @Test
    void aStatementStaysInTheXmlaNamespace() throws Exception {
        Statement statement = XMLA.createStatement();
        statement.setStatement("SELECT FROM [C]");

        Element written = commandOf(written(statement));

        assertThat(written.getLocalName()).isEqualTo("Statement");
        assertThat(written.getNamespaceURI()).isEqualTo(XmlaNamespaces.XMLA);
    }

    /**
     * A command written alone -- what a server trace reports as the text of Command Begin and End -- is its own element in
     * its own namespace, as Analysis Services 2017 writes it there ({@code <Create xmlns=".../2003/engine">}).
     */
    @Test
    void aCommandWrittenAloneIsItsElementInItsNamespace() throws Exception {
        java.io.StringWriter text = new java.io.StringWriter();
        javax.xml.stream.XMLStreamWriter out = javax.xml.stream.XMLOutputFactory.newFactory().createXMLStreamWriter(text);
        ExecuteRequestWriter.writeCommand(out, createOfATrace());
        out.close();

        assertThat(text.toString()).startsWith("<Create xmlns=\"" + XmlaNamespaces.ENGINE + "\"><ObjectDefinition><Trace>");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Element create = factory.newDocumentBuilder()
                .parse(new ByteArrayInputStream(text.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8)))
                .getDocumentElement();
        assertThat(create.getNamespaceURI()).isEqualTo(XmlaNamespaces.ENGINE);
        assertThat(only(only(create, "ObjectDefinition"), "Trace").getNamespaceURI()).isEqualTo(XmlaNamespaces.ENGINE);
    }

    @Test
    void whatIsWrittenIsReadBackWithItsEvents() throws Exception {
        XMLStreamReader in = XMLInputFactory.newFactory().createXMLStreamReader(
                new ByteArrayInputStream(written(createOfATrace())));
        while (!(in.next() == XMLStreamConstants.START_ELEMENT && in.getLocalName().equals("Execute"))) {
            // to the Execute element
        }

        Execute read = ExecuteRequestReader.read(in);

        Trace trace = ((Create) read.getCommand()).getObjectDefinition().getTrace();
        assertThat(trace.getEventType().getEvents()).extracting(Event::getEventID).containsExactly("9", "10");
        assertThat(trace.getEventType().getEvents().get(0).getColumns().getColumnID()).containsExactly("0", "42");
    }
}
