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
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.daanse.xmla.api.xmla.Aggregation;
import org.eclipse.daanse.xmla.api.xmla.AggregationDesign;
import org.eclipse.daanse.xmla.api.xmla.AggregationDimension;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter.AggregationConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

class AggregationConverterTest {

    private DocumentBuilder documentBuilder;
    private AggregationConverter.AnnotationListParser annotationParser;

    @BeforeEach
    void setUp() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        documentBuilder = factory.newDocumentBuilder();
        annotationParser = _ -> Collections.emptyList();
    }

    private Document parseXml(String xml) throws Exception {
        return documentBuilder.parse(new InputSource(new StringReader(xml)));
    }

    @Nested
    class AggregationDesignTests {

        @Test
        void getAggregationDesign_withName() throws Exception {
            String xml = "<root><Name>TestDesign</Name></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            AggregationDesign design = AggregationConverter.getAggregationDesign(nl, annotationParser);

            assertNotNull(design);
            assertEquals("TestDesign", design.name());
        }

        @Test
        void getAggregationDesign_withEstimatedRows() throws Exception {
            String xml = "<root><Name>Test</Name><EstimatedRows>1000</EstimatedRows></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            AggregationDesign design = AggregationConverter.getAggregationDesign(nl, annotationParser);

            assertTrue(design.estimatedRows().isPresent());
            assertEquals(1000L, design.estimatedRows().get());
        }

        @Test
        void getAggregationDesign_withEstimatedPerformanceGain() throws Exception {
            String xml = "<root><Name>Test</Name><EstimatedPerformanceGain>50</EstimatedPerformanceGain></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            AggregationDesign design = AggregationConverter.getAggregationDesign(nl, annotationParser);

            assertTrue(design.estimatedPerformanceGain().isPresent());
            assertEquals(50, design.estimatedPerformanceGain().get());
        }
    }

    @Nested
    class AggregationTests {

        @Test
        void getAggregation_withName() throws Exception {
            String xml = "<Aggregation><Name>TestAgg</Name></Aggregation>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Aggregation agg = AggregationConverter.getAggregation(nl, annotationParser);

            assertNotNull(agg);
            assertEquals("TestAgg", agg.name());
        }

        @Test
        void getAggregation_withId() throws Exception {
            String xml = "<Aggregation><ID>agg-123</ID><Name>Test</Name></Aggregation>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Aggregation agg = AggregationConverter.getAggregation(nl, annotationParser);

            assertTrue(agg.id().isPresent());
            assertEquals("agg-123", agg.id().get());
        }
    }

    @Nested
    class AggregationDimensionTests {

        @Test
        void getAggregationDimension_withCubeDimensionID() throws Exception {
            String xml = "<Dimension><CubeDimensionID>dim-1</CubeDimensionID></Dimension>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            AggregationDimension dim = AggregationConverter.getAggregationDimension(nl, annotationParser);

            assertNotNull(dim);
            assertEquals("dim-1", dim.cubeDimensionID());
        }
    }

    @Nested
    class AggregationDesignAttributeTests {

        @Test
        void getAggregationDesignAttribute_withAttributeID() throws Exception {
            String xml = "<Attribute><AttributeID>attr-1</AttributeID></Attribute>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            var attr = AggregationConverter.getAggregationDesignAttribute(nl);

            assertNotNull(attr);
            assertEquals("attr-1", attr.attributeID());
        }

        @Test
        void getAggregationDesignAttribute_withEstimatedCount() throws Exception {
            String xml = "<Attribute><AttributeID>attr-1</AttributeID><EstimatedCount>500</EstimatedCount></Attribute>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            var attr = AggregationConverter.getAggregationDesignAttribute(nl);

            assertTrue(attr.estimatedCount().isPresent());
            assertEquals(500L, attr.estimatedCount().get());
        }
    }
}
