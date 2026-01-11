/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.daanse.xmla.server.adapter.soapmessage.execute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.daanse.xmla.api.xmla.BoolBinop;
import org.eclipse.daanse.xmla.api.xmla.Event;
import org.eclipse.daanse.xmla.api.xmla.EventType;
import org.eclipse.daanse.xmla.api.xmla.NotType;
import org.eclipse.daanse.xmla.api.xmla.Trace;
import org.eclipse.daanse.xmla.api.xmla.TraceFilter;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter.TraceEventConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

class TraceEventConverterTest {

    private DocumentBuilder documentBuilder;

    @BeforeEach
    void setUp() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        documentBuilder = factory.newDocumentBuilder();
    }

    private Document parseXml(String xml) throws Exception {
        return documentBuilder.parse(new InputSource(new StringReader(xml)));
    }

    @Nested
    class TraceTests {

        @Test
        void getTrace_withLogFileName() throws Exception {
            String xml = "<Trace><LogFileName>/var/log/trace.log</LogFileName></Trace>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Trace trace = TraceEventConverter.getTrace(nl);

            assertNotNull(trace);
            assertEquals("/var/log/trace.log", trace.logFileName());
        }

        @Test
        void getTrace_withAuditTrue() throws Exception {
            String xml = "<Trace><Audit>true</Audit></Trace>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Trace trace = TraceEventConverter.getTrace(nl);

            assertTrue(trace.audit());
        }

        @Test
        void getTrace_withLogFileSize() throws Exception {
            String xml = "<Trace><LogFileSize>1024000</LogFileSize></Trace>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Trace trace = TraceEventConverter.getTrace(nl);

            assertEquals(1024000L, trace.logFileSize());
        }
    }

    @Nested
    class TraceFilterTests {

        @Test
        void getTraceFilter_withEqual() throws Exception {
            String xml = "<Filter><Equal><ColumnID>col1</ColumnID><Value>test</Value></Equal></Filter>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            TraceFilter filter = TraceEventConverter.getTraceFilter(nl);

            assertNotNull(filter);
            assertNotNull(filter.isEqual());
            assertEquals("col1", filter.isEqual().columnID());
            assertEquals("test", filter.isEqual().value());
        }

        @Test
        void getTraceFilter_withNotEqual() throws Exception {
            String xml = "<Filter><NotEqual><ColumnID>col2</ColumnID><Value>excluded</Value></NotEqual></Filter>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            TraceFilter filter = TraceEventConverter.getTraceFilter(nl);

            assertNotNull(filter.notEqual());
            assertEquals("col2", filter.notEqual().columnID());
        }

        @Test
        void getTraceFilter_withLike() throws Exception {
            String xml = "<Filter><Like><ColumnID>name</ColumnID><Value>%pattern%</Value></Like></Filter>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            TraceFilter filter = TraceEventConverter.getTraceFilter(nl);

            assertNotNull(filter.like());
            assertEquals("%pattern%", filter.like().value());
        }
    }

    @Nested
    class BoolBinopTests {

        @Test
        void getBoolBinop_withColumnIDAndValue() throws Exception {
            String xml = "<BoolBinop><ColumnID>testCol</ColumnID><Value>testVal</Value></BoolBinop>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            BoolBinop binop = TraceEventConverter.getBoolBinop(nl);

            assertNotNull(binop);
            assertEquals("testCol", binop.columnID());
            assertEquals("testVal", binop.value());
        }
    }

    @Nested
    class EventTests {

        @Test
        void getEvent_withEventID() throws Exception {
            String xml = "<Event><EventID>evt-123</EventID></Event>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Event event = TraceEventConverter.getEvent(nl);

            assertNotNull(event);
            assertEquals("evt-123", event.eventID());
        }
    }

    @Nested
    class EventTypeTests {

        @Test
        void getEventType_empty() throws Exception {
            String xml = "<EventType></EventType>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            EventType eventType = TraceEventConverter.getEventType(nl);

            assertNotNull(eventType);
        }
    }

    @Nested
    class NotTypeTests {

        @Test
        void getNotType_withEqual() throws Exception {
            String xml = "<Not><Equal><ColumnID>col</ColumnID><Value>val</Value></Equal></Not>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            NotType notType = TraceEventConverter.getNotType(nl);

            assertNotNull(notType);
            assertNotNull(notType.isEqual());
        }
    }
}
