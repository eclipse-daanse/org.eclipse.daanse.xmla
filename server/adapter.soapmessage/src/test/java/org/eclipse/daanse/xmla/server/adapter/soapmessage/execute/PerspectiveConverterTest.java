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
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.daanse.xmla.api.xmla.Perspective;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveAction;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveAttribute;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveCalculation;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveDimension;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveHierarchy;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveKpi;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveMeasure;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveMeasureGroup;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter.PerspectiveConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

class PerspectiveConverterTest {

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
    class PerspectiveTests {

        @Test
        void getPerspective_withNameAndId() throws Exception {
            String xml = "<Perspective><Name>TestPerspective</Name><ID>persp-1</ID></Perspective>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Perspective perspective = PerspectiveConverter.getPerspective(nl);

            assertNotNull(perspective);
            assertEquals("TestPerspective", perspective.name());
            assertEquals("persp-1", perspective.id());
        }

        @Test
        void getPerspective_withDefaultMeasure() throws Exception {
            String xml = "<Perspective><Name>Test</Name><DefaultMeasure>SalesMeasure</DefaultMeasure></Perspective>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Perspective perspective = PerspectiveConverter.getPerspective(nl);

            assertEquals("SalesMeasure", perspective.defaultMeasure());
        }

        @Test
        void getPerspectiveList_multipleItems() throws Exception {
            String xml = "<root><Perspective><Name>P1</Name></Perspective><Perspective><Name>P2</Name></Perspective></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            List<Perspective> list = PerspectiveConverter.getPerspectiveList(nl);

            assertEquals(2, list.size());
            assertEquals("P1", list.get(0).name());
            assertEquals("P2", list.get(1).name());
        }
    }

    @Nested
    class PerspectiveActionTests {

        @Test
        void getPerspectiveAction_withActionID() throws Exception {
            String xml = "<Action><ActionID>action-123</ActionID></Action>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            PerspectiveAction action = PerspectiveConverter.getPerspectiveAction(nl);

            assertNotNull(action);
            assertEquals("action-123", action.actionID());
        }

        @Test
        void getPerspectiveActionList_multipleItems() throws Exception {
            String xml = "<root><Action><ActionID>a1</ActionID></Action><Action><ActionID>a2</ActionID></Action></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            List<PerspectiveAction> list = PerspectiveConverter.getPerspectiveActionList(nl);

            assertEquals(2, list.size());
        }
    }

    @Nested
    class PerspectiveKpiTests {

        @Test
        void getPerspectiveKpi_withKpiID() throws Exception {
            String xml = "<Kpi><KpiID>kpi-456</KpiID></Kpi>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            PerspectiveKpi kpi = PerspectiveConverter.getPerspectiveKpi(nl);

            assertNotNull(kpi);
            assertEquals("kpi-456", kpi.kpiID());
        }
    }

    @Nested
    class PerspectiveCalculationTests {

        @Test
        void getPerspectiveCalculation_withNameAndType() throws Exception {
            String xml = "<Calculation><Name>CalcMember</Name><Type>Member</Type></Calculation>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            PerspectiveCalculation calc = PerspectiveConverter.getPerspectiveCalculation(nl);

            assertNotNull(calc);
            assertEquals("CalcMember", calc.name());
            assertEquals("Member", calc.type());
        }
    }

    @Nested
    class PerspectiveMeasureGroupTests {

        @Test
        void getPerspectiveMeasureGroup_withMeasureGroupID() throws Exception {
            String xml = "<MeasureGroup><MeasureGroupID>mg-1</MeasureGroupID></MeasureGroup>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            PerspectiveMeasureGroup mg = PerspectiveConverter.getPerspectiveMeasureGroup(nl);

            assertNotNull(mg);
            assertEquals("mg-1", mg.measureGroupID());
        }
    }

    @Nested
    class PerspectiveMeasureTests {

        @Test
        void getPerspectiveMeasure_withMeasureID() throws Exception {
            String xml = "<Measure><MeasureID>measure-1</MeasureID></Measure>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            PerspectiveMeasure measure = PerspectiveConverter.getPerspectiveMeasure(nl);

            assertNotNull(measure);
            assertEquals("measure-1", measure.measureID());
        }
    }

    @Nested
    class PerspectiveDimensionTests {

        @Test
        void getPerspectiveDimension_withCubeDimensionID() throws Exception {
            String xml = "<Dimension><CubeDimensionID>dim-1</CubeDimensionID></Dimension>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            PerspectiveDimension dim = PerspectiveConverter.getPerspectiveDimension(nl);

            assertNotNull(dim);
            assertEquals("dim-1", dim.cubeDimensionID());
        }
    }

    @Nested
    class PerspectiveHierarchyTests {

        @Test
        void getPerspectiveHierarchy_withHierarchyID() throws Exception {
            String xml = "<Hierarchy><HierarchyID>hier-1</HierarchyID></Hierarchy>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            PerspectiveHierarchy hierarchy = PerspectiveConverter.getPerspectiveHierarchy(nl);

            assertNotNull(hierarchy);
            assertEquals("hier-1", hierarchy.hierarchyID());
        }
    }

    @Nested
    class PerspectiveAttributeTests {

        @Test
        void getPerspectiveAttribute_withAttributeID() throws Exception {
            String xml = "<Attribute><AttributeID>attr-1</AttributeID></Attribute>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            PerspectiveAttribute attr = PerspectiveConverter.getPerspectiveAttribute(nl);

            assertNotNull(attr);
            assertEquals("attr-1", attr.attributeID());
        }

        @Test
        void getPerspectiveAttribute_withAttributeHierarchyVisible() throws Exception {
            String xml = "<Attribute><AttributeID>attr-1</AttributeID><AttributeHierarchyVisible>true</AttributeHierarchyVisible></Attribute>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            PerspectiveAttribute attr = PerspectiveConverter.getPerspectiveAttribute(nl);

            assertTrue(attr.attributeHierarchyVisible().isPresent());
            assertTrue(attr.attributeHierarchyVisible().get());
        }

        @Test
        void getPerspectiveAttribute_withDefaultMember() throws Exception {
            String xml = "<Attribute><AttributeID>attr-1</AttributeID><DefaultMember>[Product].[All]</DefaultMember></Attribute>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            PerspectiveAttribute attr = PerspectiveConverter.getPerspectiveAttribute(nl);

            assertTrue(attr.defaultMember().isPresent());
            assertEquals("[Product].[All]", attr.defaultMember().get());
        }
    }
}
