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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.daanse.xmla.api.xmla.Assembly;
import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.api.xmla.Server;
import org.eclipse.daanse.xmla.api.xmla.ServerProperty;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter.ServerConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

class ServerConverterTest {

    private DocumentBuilder documentBuilder;
    private Function<NodeList, Command> mockCommandParser;
    private Function<NodeList, List<Assembly>> mockAssemblySupplier;

    @BeforeEach
    void setUp() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        documentBuilder = factory.newDocumentBuilder();

        // Simple mocks that return null/empty - sufficient for basic field tests
        mockCommandParser = _ -> null;
        mockAssemblySupplier = _ -> new ArrayList<>();
    }

    private Document parseXml(String xml) throws Exception {
        return documentBuilder.parse(new InputSource(new StringReader(xml)));
    }

    @Nested
    class ServerTests {

        @Test
        void getServer_withNameAndId() throws Exception {
            String xml = "<Server><Name>TestServer</Name><ID>srv-123</ID></Server>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Server server = ServerConverter.getServer(nl, mockCommandParser, mockAssemblySupplier);

            assertNotNull(server);
            assertEquals("TestServer", server.name());
            assertEquals("srv-123", server.id());
        }

        @Test
        void getServer_withProductName() throws Exception {
            String xml = "<Server><Name>Test</Name><ProductName>Microsoft Analysis Services</ProductName></Server>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Server server = ServerConverter.getServer(nl, mockCommandParser, mockAssemblySupplier);

            assertEquals("Microsoft Analysis Services", server.productName());
        }

        @Test
        void getServer_withEdition() throws Exception {
            String xml = "<Server><Name>Test</Name><Edition>Enterprise Edition (64-bit)</Edition></Server>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Server server = ServerConverter.getServer(nl, mockCommandParser, mockAssemblySupplier);

            assertEquals("Enterprise Edition (64-bit)", server.edition());
        }

        @Test
        void getServer_withEditionID() throws Exception {
            String xml = "<Server><Name>Test</Name><EditionID>1</EditionID></Server>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Server server = ServerConverter.getServer(nl, mockCommandParser, mockAssemblySupplier);

            assertEquals(1L, server.editionID());
        }

        @Test
        void getServer_withVersion() throws Exception {
            String xml = "<Server><Name>Test</Name><Version>15.0.35.39</Version></Server>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Server server = ServerConverter.getServer(nl, mockCommandParser, mockAssemblySupplier);

            assertEquals("15.0.35.39", server.version());
        }

        @Test
        void getServer_withServerMode() throws Exception {
            String xml = "<Server><Name>Test</Name><ServerMode>Multidimensional</ServerMode></Server>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Server server = ServerConverter.getServer(nl, mockCommandParser, mockAssemblySupplier);

            assertEquals("Multidimensional", server.serverMode());
        }

        @Test
        void getServer_withProductLevel() throws Exception {
            String xml = "<Server><Name>Test</Name><ProductLevel>SP2</ProductLevel></Server>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Server server = ServerConverter.getServer(nl, mockCommandParser, mockAssemblySupplier);

            assertEquals("SP2", server.productLevel());
        }

        @Test
        void getServer_withDefaultCompatibilityLevel() throws Exception {
            String xml = "<Server><Name>Test</Name><DefaultCompatibilityLevel>1500</DefaultCompatibilityLevel></Server>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Server server = ServerConverter.getServer(nl, mockCommandParser, mockAssemblySupplier);

            assertEquals(1500L, server.defaultCompatibilityLevel());
        }

        @Test
        void getServer_withSupportedCompatibilityLevels() throws Exception {
            String xml = "<Server><Name>Test</Name><SupportedCompatibilityLevels>1100,1103,1200,1400,1500</SupportedCompatibilityLevels></Server>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Server server = ServerConverter.getServer(nl, mockCommandParser, mockAssemblySupplier);

            assertEquals("1100,1103,1200,1400,1500", server.supportedCompatibilityLevels());
        }
    }

    @Nested
    class ServerPropertyTests {

        @Test
        void getServerProperty_withName() throws Exception {
            String xml = "<ServerProperty><Name>MaxConnections</Name></ServerProperty>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            ServerProperty prop = ServerConverter.getServerProperty(nl);

            assertNotNull(prop);
            assertEquals("MaxConnections", prop.name());
        }

        @Test
        void getServerProperty_withValue() throws Exception {
            String xml = "<ServerProperty><Name>Test</Name><Value>100</Value></ServerProperty>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            ServerProperty prop = ServerConverter.getServerProperty(nl);

            assertEquals("100", prop.value());
        }

        @Test
        void getServerProperty_withRequiresRestart() throws Exception {
            String xml = "<ServerProperty><Name>Test</Name><RequiresRestart>true</RequiresRestart></ServerProperty>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            ServerProperty prop = ServerConverter.getServerProperty(nl);

            assertTrue(prop.requiresRestart());
        }

        @Test
        void getServerProperty_withPendingValue() throws Exception {
            String xml = "<ServerProperty><Name>Test</Name><PendingValue>200</PendingValue></ServerProperty>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            ServerProperty prop = ServerConverter.getServerProperty(nl);

            assertEquals("200", prop.pendingValue());
        }

        @Test
        void getServerProperty_withDefaultValue() throws Exception {
            String xml = "<ServerProperty><Name>Test</Name><DefaultValue>50</DefaultValue></ServerProperty>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            ServerProperty prop = ServerConverter.getServerProperty(nl);

            assertEquals("50", prop.defaultValue());
        }

        @Test
        void getServerProperty_withDisplayFlag() throws Exception {
            String xml = "<ServerProperty><Name>Test</Name><DisplayFlag>true</DisplayFlag></ServerProperty>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            ServerProperty prop = ServerConverter.getServerProperty(nl);

            assertTrue(prop.displayFlag());
        }

        @Test
        void getServerProperty_withType() throws Exception {
            String xml = "<ServerProperty><Name>Test</Name><Type>integer</Type></ServerProperty>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            ServerProperty prop = ServerConverter.getServerProperty(nl);

            assertEquals("integer", prop.type());
        }

        @Test
        void getServerPropertyList_multipleItems() throws Exception {
            String xml = "<root><ServerProperty><Name>Prop1</Name></ServerProperty>" +
                    "<ServerProperty><Name>Prop2</Name></ServerProperty></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            List<ServerProperty> list = ServerConverter.getServerPropertyList(nl);

            assertEquals(2, list.size());
            assertEquals("Prop1", list.get(0).name());
            assertEquals("Prop2", list.get(1).name());
        }
    }
}
