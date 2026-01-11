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

import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.daanse.xmla.api.xmla.Partition;
import org.eclipse.daanse.xmla.api.xmla.PartitionStorageModeEnumType;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter.PartitionConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

class PartitionConverterTest {

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
    class PartitionTests {

        @Test
        void getPartition_withNameAndId() throws Exception {
            String xml = "<Partition><Name>TestPartition</Name><ID>part-123</ID></Partition>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Partition partition = PartitionConverter.getPartition(nl);

            assertNotNull(partition);
            assertEquals("TestPartition", partition.name());
            assertEquals("part-123", partition.id());
        }

        @Test
        void getPartition_withDescription() throws Exception {
            String xml = "<Partition><Name>Test</Name><Description>A test partition</Description></Partition>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Partition partition = PartitionConverter.getPartition(nl);

            assertEquals("A test partition", partition.description());
        }

        @Test
        void getPartition_withAggregationPrefix() throws Exception {
            String xml = "<Partition><Name>Test</Name><AggregationPrefix>Agg_</AggregationPrefix></Partition>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Partition partition = PartitionConverter.getPartition(nl);

            assertEquals("Agg_", partition.aggregationPrefix());
        }

        @Test
        void getPartition_withProcessingMode() throws Exception {
            String xml = "<Partition><Name>Test</Name><ProcessingMode>Regular</ProcessingMode></Partition>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Partition partition = PartitionConverter.getPartition(nl);

            assertEquals("Regular", partition.processingMode());
        }

        @Test
        void getPartition_withStorageLocation() throws Exception {
            String xml = "<Partition><Name>Test</Name><StorageLocation>/data/partition1</StorageLocation></Partition>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Partition partition = PartitionConverter.getPartition(nl);

            assertEquals("/data/partition1", partition.storageLocation());
        }

        @Test
        void getPartition_withSlice() throws Exception {
            String xml = "<Partition><Name>Test</Name><Slice>[Year].[2023]</Slice></Partition>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Partition partition = PartitionConverter.getPartition(nl);

            assertEquals("[Year].[2023]", partition.slice());
        }

        @Test
        void getPartition_withType() throws Exception {
            String xml = "<Partition><Name>Test</Name><Type>Data</Type></Partition>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Partition partition = PartitionConverter.getPartition(nl);

            assertEquals("Data", partition.type());
        }

        @Test
        void getPartition_withEstimatedSize() throws Exception {
            String xml = "<Partition><Name>Test</Name><EstimatedSize>1024000</EstimatedSize></Partition>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Partition partition = PartitionConverter.getPartition(nl);

            assertEquals(1024000L, partition.estimatedSize());
        }

        @Test
        void getPartition_withEstimatedRows() throws Exception {
            String xml = "<Partition><Name>Test</Name><EstimatedRows>50000</EstimatedRows></Partition>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Partition partition = PartitionConverter.getPartition(nl);

            assertEquals(50000L, partition.estimatedRows());
        }

        @Test
        void getPartition_withState() throws Exception {
            String xml = "<Partition><Name>Test</Name><State>Processed</State></Partition>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Partition partition = PartitionConverter.getPartition(nl);

            assertEquals("Processed", partition.state());
        }

        @Test
        void getPartition_withDirectQueryUsage() throws Exception {
            String xml = "<Partition><Name>Test</Name><DirectQueryUsage>InMemoryOnly</DirectQueryUsage></Partition>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Partition partition = PartitionConverter.getPartition(nl);

            assertEquals("InMemoryOnly", partition.directQueryUsage());
        }

        @Test
        void getPartitionList_multipleItems() throws Exception {
            String xml = "<root><Partition><Name>P1</Name></Partition>" +
                    "<Partition><Name>P2</Name></Partition></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            List<Partition> list = PartitionConverter.getPartitionList(nl);

            assertEquals(2, list.size());
            assertEquals("P1", list.get(0).name());
            assertEquals("P2", list.get(1).name());
        }
    }

    @Nested
    class StorageModeTests {

        @Test
        void getPartitionStorageMode_withMolap() throws Exception {
            String xml = "<StorageMode>Molap</StorageMode>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Partition.StorageMode mode = PartitionConverter.getPartitionStorageMode(nl);

            assertNotNull(mode);
            assertEquals(PartitionStorageModeEnumType.MOLAP, mode.value());
        }

        @Test
        void getPartitionStorageMode_withRolap() throws Exception {
            String xml = "<StorageMode>Rolap</StorageMode>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Partition.StorageMode mode = PartitionConverter.getPartitionStorageMode(nl);

            assertEquals(PartitionStorageModeEnumType.ROLAP, mode.value());
        }

        @Test
        void getPartitionStorageMode_withHolap() throws Exception {
            String xml = "<StorageMode>Holap</StorageMode>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Partition.StorageMode mode = PartitionConverter.getPartitionStorageMode(nl);

            assertEquals(PartitionStorageModeEnumType.HOLAP, mode.value());
        }
    }

    @Nested
    class CurrentStorageModeTests {

        @Test
        void getPartitionCurrentStorageMode_withMolap() throws Exception {
            String xml = "<CurrentStorageMode>Molap</CurrentStorageMode>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Partition.CurrentStorageMode mode = PartitionConverter.getPartitionCurrentStorageMode(nl);

            assertNotNull(mode);
        }
    }
}
