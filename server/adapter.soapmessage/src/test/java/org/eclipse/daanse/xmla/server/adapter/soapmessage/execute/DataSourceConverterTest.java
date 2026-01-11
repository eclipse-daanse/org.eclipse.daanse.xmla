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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.daanse.xmla.api.engine.ImpersonationInfo;
import org.eclipse.daanse.xmla.api.xmla.DataSource;
import org.eclipse.daanse.xmla.api.xmla.DataSourceView;
import org.eclipse.daanse.xmla.api.xmla.DataSourceViewBinding;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter.DataSourceConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

class DataSourceConverterTest {

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
    class DataSourceViewBindingTests {

        @Test
        void getDataSourceViewBinding_withDataSourceViewID() throws Exception {
            String xml = "<Binding><DataSourceViewID>dsv-123</DataSourceViewID></Binding>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DataSourceViewBinding binding = DataSourceConverter.getDataSourceViewBinding(nl);

            assertNotNull(binding);
            assertEquals("dsv-123", binding.dataSourceViewID());
        }

        @Test
        void getDataSourceViewBinding_withEmptyId() throws Exception {
            String xml = "<Binding></Binding>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DataSourceViewBinding binding = DataSourceConverter.getDataSourceViewBinding(nl);

            assertNotNull(binding);
            assertNull(binding.dataSourceViewID());
        }
    }

    @Nested
    class DataSourceViewTests {

        @Test
        void getDataSourceView_withNameAndId() throws Exception {
            String xml = "<DataSourceView><Name>TestDSV</Name><ID>dsv-1</ID></DataSourceView>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DataSourceView dsv = DataSourceConverter.getDataSourceView(nl);

            assertNotNull(dsv);
            assertEquals("TestDSV", dsv.name());
            assertEquals("dsv-1", dsv.id());
        }

        @Test
        void getDataSourceView_withDataSourceID() throws Exception {
            String xml = "<DataSourceView><Name>Test</Name><DataSourceID>ds-ref-1</DataSourceID></DataSourceView>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DataSourceView dsv = DataSourceConverter.getDataSourceView(nl);

            assertEquals("ds-ref-1", dsv.dataSourceID());
        }

        @Test
        void getDataSourceView_withDescription() throws Exception {
            String xml = "<DataSourceView><Name>Test</Name><Description>A test DSV</Description></DataSourceView>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DataSourceView dsv = DataSourceConverter.getDataSourceView(nl);

            assertEquals("A test DSV", dsv.description());
        }

        @Test
        void getDataSourceViewList_multipleItems() throws Exception {
            String xml = "<root><DataSourceView><Name>DSV1</Name></DataSourceView>" +
                    "<DataSourceView><Name>DSV2</Name></DataSourceView></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            List<DataSourceView> list = DataSourceConverter.getDataSourceViewList(nl);

            assertEquals(2, list.size());
            assertEquals("DSV1", list.get(0).name());
            assertEquals("DSV2", list.get(1).name());
        }
    }

    @Nested
    class DataSourceTests {

        @Test
        void getDataSource_withNameAndId() throws Exception {
            String xml = "<DataSource><Name>TestDS</Name><ID>ds-1</ID></DataSource>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DataSource ds = DataSourceConverter.getDataSource(nl);

            assertNotNull(ds);
            assertEquals("TestDS", ds.name());
            assertEquals("ds-1", ds.id());
        }

        @Test
        void getDataSource_withConnectionString() throws Exception {
            String xml = "<DataSource><Name>Test</Name><ConnectionString>Server=localhost;Database=test</ConnectionString></DataSource>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DataSource ds = DataSourceConverter.getDataSource(nl);

            assertEquals("Server=localhost;Database=test", ds.connectionString());
        }

        @Test
        void getDataSource_withManagedProvider() throws Exception {
            String xml = "<DataSource><Name>Test</Name><ManagedProvider>MSOLAP</ManagedProvider></DataSource>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DataSource ds = DataSourceConverter.getDataSource(nl);

            assertEquals("MSOLAP", ds.managedProvider());
        }

        @Test
        void getDataSource_withTimeout() throws Exception {
            String xml = "<DataSource><Name>Test</Name><Timeout>PT30S</Timeout></DataSource>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DataSource ds = DataSourceConverter.getDataSource(nl);

            assertNotNull(ds.timeout());
        }

        @Test
        void getDataSource_withIsolation() throws Exception {
            String xml = "<DataSource><Name>Test</Name><Isolation>ReadCommitted</Isolation></DataSource>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DataSource ds = DataSourceConverter.getDataSource(nl);

            assertEquals("ReadCommitted", ds.isolation());
        }

        @Test
        void getDataSourceList_multipleItems() throws Exception {
            String xml = "<root><DataSource><Name>DS1</Name></DataSource>" +
                    "<DataSource><Name>DS2</Name></DataSource></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            List<DataSource> list = DataSourceConverter.getDataSourceList(nl);

            assertEquals(2, list.size());
            assertEquals("DS1", list.get(0).name());
            assertEquals("DS2", list.get(1).name());
        }
    }

    @Nested
    class ImpersonationInfoTests {

        @Test
        void getImpersonationInfo_withMode() throws Exception {
            String xml = "<ImpersonationInfo><ImpersonationMode>ImpersonateCurrentUser</ImpersonationMode></ImpersonationInfo>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            ImpersonationInfo info = DataSourceConverter.getImpersonationInfo(nl);

            assertNotNull(info);
            assertEquals("ImpersonateCurrentUser", info.impersonationMode());
        }

        @Test
        void getImpersonationInfo_withAccount() throws Exception {
            String xml = "<ImpersonationInfo><ImpersonationMode>ImpersonateAccount</ImpersonationMode>" +
                    "<Account>domain\\user</Account></ImpersonationInfo>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            ImpersonationInfo info = DataSourceConverter.getImpersonationInfo(nl);

            assertTrue(info.account().isPresent());
            assertEquals("domain\\user", info.account().get());
        }

        @Test
        void getImpersonationInfo_withPassword() throws Exception {
            String xml = "<ImpersonationInfo><ImpersonationMode>ImpersonateAccount</ImpersonationMode>" +
                    "<Password>secret123</Password></ImpersonationInfo>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            ImpersonationInfo info = DataSourceConverter.getImpersonationInfo(nl);

            assertTrue(info.password().isPresent());
            assertEquals("secret123", info.password().get());
        }

        @Test
        void getImpersonationInfo_withSecurity() throws Exception {
            String xml = "<ImpersonationInfo><ImpersonationMode>ImpersonateAccount</ImpersonationMode>" +
                    "<ImpersonationInfoSecurity>PasswordRemoved</ImpersonationInfoSecurity></ImpersonationInfo>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            ImpersonationInfo info = DataSourceConverter.getImpersonationInfo(nl);

            assertTrue(info.impersonationInfoSecurity().isPresent());
            assertEquals("PasswordRemoved", info.impersonationInfoSecurity().get());
        }
    }
}
