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
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.daanse.xmla.api.xmla.Action;
import org.eclipse.daanse.xmla.model.record.xmla.DrillThroughActionR;
import org.eclipse.daanse.xmla.model.record.xmla.ReportActionR;
import org.eclipse.daanse.xmla.model.record.xmla.StandardActionR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter.ActionConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

class ActionConverterTest {

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
    class StandardActionTests {

        @Test
        void getStandardAction_withNameAndExpression() throws Exception {
            String xml = "<Action><Name>TestAction</Name><Expression>SELECT * FROM Sales</Expression></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Action action = ActionConverter.getStandardAction(nl);

            assertNotNull(action);
            assertInstanceOf(StandardActionR.class, action);
            StandardActionR std = (StandardActionR) action;
            assertEquals("TestAction", std.name());
            assertEquals("SELECT * FROM Sales", std.expression());
        }

        @Test
        void getStandardAction_withId() throws Exception {
            String xml = "<Action><Name>Test</Name><ID>action-123</ID></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Action action = ActionConverter.getStandardAction(nl);

            StandardActionR std = (StandardActionR) action;
            assertTrue(std.id().isPresent());
            assertEquals("action-123", std.id().get());
        }

        @Test
        void getStandardAction_withCaption() throws Exception {
            String xml = "<Action><Name>Test</Name><Caption>Test Caption</Caption><CaptionIsMdx>true</CaptionIsMdx></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Action action = ActionConverter.getStandardAction(nl);

            StandardActionR std = (StandardActionR) action;
            assertTrue(std.caption().isPresent());
            assertEquals("Test Caption", std.caption().get());
            assertTrue(std.captionIsMdx().isPresent());
            assertTrue(std.captionIsMdx().get());
        }

        @Test
        void getStandardAction_withDescription() throws Exception {
            String xml = "<Action><Name>Test</Name><Description>A test action</Description></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Action action = ActionConverter.getStandardAction(nl);

            StandardActionR std = (StandardActionR) action;
            assertTrue(std.description().isPresent());
            assertEquals("A test action", std.description().get());
        }
    }

    @Nested
    class ReportActionTests {

        @Test
        void getReportAction_withReportServer() throws Exception {
            String xml = "<Action><Name>ReportTest</Name><ReportServer>http://reports.example.com</ReportServer></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Action action = ActionConverter.getReportAction(nl);

            assertNotNull(action);
            assertInstanceOf(ReportActionR.class, action);
            ReportActionR report = (ReportActionR) action;
            assertEquals("ReportTest", report.name());
            assertEquals("http://reports.example.com", report.reportServer());
        }

        @Test
        void getReportAction_withPath() throws Exception {
            String xml = "<Action><Name>Test</Name><ReportServer>http://reports</ReportServer><Path>/Sales/Monthly</Path></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Action action = ActionConverter.getReportAction(nl);

            ReportActionR report = (ReportActionR) action;
            assertTrue(report.path().isPresent());
            assertEquals("/Sales/Monthly", report.path().get());
        }

        @Test
        void getReportAction_withId() throws Exception {
            String xml = "<Action><Name>Test</Name><ID>report-456</ID><ReportServer>http://reports</ReportServer></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Action action = ActionConverter.getReportAction(nl);

            ReportActionR report = (ReportActionR) action;
            assertTrue(report.id().isPresent());
            assertEquals("report-456", report.id().get());
        }
    }

    @Nested
    class DrillThroughActionTests {

        @Test
        void getDrillThroughAction_withName() throws Exception {
            String xml = "<Action><Name>DrillTest</Name></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Action action = ActionConverter.getDrillThroughAction(nl);

            assertNotNull(action);
            assertInstanceOf(DrillThroughActionR.class, action);
            DrillThroughActionR drill = (DrillThroughActionR) action;
            assertEquals("DrillTest", drill.name());
        }

        @Test
        void getDrillThroughAction_withDefault() throws Exception {
            String xml = "<Action><Name>Test</Name><Default>true</Default></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Action action = ActionConverter.getDrillThroughAction(nl);

            DrillThroughActionR drill = (DrillThroughActionR) action;
            assertTrue(drill.defaultAction().isPresent());
            assertTrue(drill.defaultAction().get());
        }

        @Test
        void getDrillThroughAction_withMaximumRows() throws Exception {
            String xml = "<Action><Name>Test</Name><MaximumRows>1000</MaximumRows></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Action action = ActionConverter.getDrillThroughAction(nl);

            DrillThroughActionR drill = (DrillThroughActionR) action;
            assertTrue(drill.maximumRows().isPresent());
            assertEquals(Integer.valueOf(1000), drill.maximumRows().get());
        }

        @Test
        void getDrillThroughAction_withId() throws Exception {
            String xml = "<Action><Name>Test</Name><ID>drill-789</ID></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Action action = ActionConverter.getDrillThroughAction(nl);

            DrillThroughActionR drill = (DrillThroughActionR) action;
            assertTrue(drill.id().isPresent());
            assertEquals("drill-789", drill.id().get());
        }
    }

    @Nested
    class ActionDispatchTests {

        @Test
        void getAction_dispatchesToStandardAction() throws Exception {
            String xml = "<Action><Name>Test</Name><Expression>SELECT 1</Expression></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Action action = ActionConverter.getAction(nl, "StandardAction");

            assertNotNull(action);
            assertInstanceOf(StandardActionR.class, action);
        }

        @Test
        void getAction_dispatchesToReportAction() throws Exception {
            String xml = "<Action><Name>Test</Name><ReportServer>http://reports</ReportServer></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Action action = ActionConverter.getAction(nl, "ReportAction");

            assertNotNull(action);
            assertInstanceOf(ReportActionR.class, action);
        }

        @Test
        void getAction_dispatchesToDrillThroughAction() throws Exception {
            String xml = "<Action><Name>Test</Name></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Action action = ActionConverter.getAction(nl, "DrillThroughAction");

            assertNotNull(action);
            assertInstanceOf(DrillThroughActionR.class, action);
        }

        @Test
        void getAction_returnsNullForUnknownType() throws Exception {
            String xml = "<Action><Name>Test</Name></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Action action = ActionConverter.getAction(nl, "UnknownAction");

            assertNull(action);
        }
    }

    @Nested
    class ActionListTests {

        @Test
        void getActionList_multipleStandardActions() throws Exception {
            String xml = "<root>" +
                    "<Action xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"StandardAction\"><Name>A1</Name></Action>" +
                    "<Action xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"StandardAction\"><Name>A2</Name></Action>" +
                    "</root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            List<Action> list = ActionConverter.getActionList(nl);

            assertEquals(2, list.size());
        }

        @Test
        void getActionList_emptyList() throws Exception {
            String xml = "<root></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            List<Action> list = ActionConverter.getActionList(nl);

            assertNotNull(list);
            assertTrue(list.isEmpty());
        }
    }
}
