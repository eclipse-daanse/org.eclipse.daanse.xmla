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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.daanse.xmla.api.xmla.DataSourcePermission;
import org.eclipse.daanse.xmla.api.xmla.DatabasePermission;
import org.eclipse.daanse.xmla.api.xmla.Permission;
import org.eclipse.daanse.xmla.api.xmla.ReadDefinitionEnum;
import org.eclipse.daanse.xmla.api.xmla.ReadWritePermissionEnum;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter.PermissionConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

class PermissionConverterTest {

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
    class PermissionTests {

        @Test
        void getPermission_withRoleID() throws Exception {
            String xml = "<Permission><RoleID>role-123</RoleID></Permission>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Permission permission = PermissionConverter.getPermission(nl);

            assertNotNull(permission);
            assertEquals("role-123", permission.roleID());
        }

        @Test
        void getPermission_withProcess() throws Exception {
            String xml = "<Permission><RoleID>role-1</RoleID><Process>true</Process></Permission>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Permission permission = PermissionConverter.getPermission(nl);

            assertTrue(permission.process().isPresent());
            assertTrue(permission.process().get());
        }

        @Test
        void getPermission_withReadDefinition() throws Exception {
            String xml = "<Permission><RoleID>role-1</RoleID><ReadDefinition>Allowed</ReadDefinition></Permission>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Permission permission = PermissionConverter.getPermission(nl);

            assertTrue(permission.readDefinition().isPresent());
            assertEquals(ReadDefinitionEnum.ALLOWED, permission.readDefinition().get());
        }

        @Test
        void getPermission_withReadWrite() throws Exception {
            String xml = "<Permission><RoleID>role-1</RoleID><Read>Allowed</Read><Write>Allowed</Write></Permission>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Permission permission = PermissionConverter.getPermission(nl);

            assertTrue(permission.read().isPresent());
            assertEquals(ReadWritePermissionEnum.ALLOWED, permission.read().get());
            assertTrue(permission.write().isPresent());
            assertEquals(ReadWritePermissionEnum.ALLOWED, permission.write().get());
        }
    }

    @Nested
    class DataSourcePermissionTests {

        @Test
        void getDataSourcePermission_withNameAndRoleID() throws Exception {
            String xml = "<DataSourcePermission><Name>DSPerm1</Name><RoleID>role-1</RoleID></DataSourcePermission>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DataSourcePermission permission = PermissionConverter.getDataSourcePermission(nl);

            assertNotNull(permission);
            assertEquals("DSPerm1", permission.name());
            assertEquals("role-1", permission.roleID());
        }

        @Test
        void getDataSourcePermission_withId() throws Exception {
            String xml = "<DataSourcePermission><Name>DSPerm1</Name><ID>dsp-123</ID><RoleID>role-1</RoleID></DataSourcePermission>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DataSourcePermission permission = PermissionConverter.getDataSourcePermission(nl);

            assertTrue(permission.id().isPresent());
            assertEquals("dsp-123", permission.id().get());
        }

        @Test
        void getDataSourcePermissionList_multipleItems() throws Exception {
            String xml = "<root><DataSourcePermission><Name>P1</Name><RoleID>r1</RoleID></DataSourcePermission>" +
                    "<DataSourcePermission><Name>P2</Name><RoleID>r2</RoleID></DataSourcePermission></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            List<DataSourcePermission> list = PermissionConverter.getDataSourcePermissionList(nl);

            assertEquals(2, list.size());
            assertEquals("P1", list.get(0).name());
            assertEquals("P2", list.get(1).name());
        }

        @Test
        void getDataSourcePermission_withReadWritePermissions() throws Exception {
            String xml = "<DataSourcePermission><Name>DSPerm</Name><RoleID>role-1</RoleID>" +
                    "<Read>Allowed</Read><Write>None</Write></DataSourcePermission>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DataSourcePermission permission = PermissionConverter.getDataSourcePermission(nl);

            assertTrue(permission.read().isPresent());
            assertEquals(ReadWritePermissionEnum.ALLOWED, permission.read().get());
            assertTrue(permission.write().isPresent());
            assertEquals(ReadWritePermissionEnum.NONE, permission.write().get());
        }
    }

    @Nested
    class DatabasePermissionTests {

        @Test
        void getDatabasePermission_withNameAndRoleID() throws Exception {
            String xml = "<DatabasePermission><Name>DBPerm1</Name><RoleID>role-1</RoleID></DatabasePermission>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DatabasePermission permission = PermissionConverter.getDatabasePermission(nl);

            assertNotNull(permission);
            assertEquals("DBPerm1", permission.name());
            assertEquals("role-1", permission.roleID());
        }

        @Test
        void getDatabasePermission_withAdminister() throws Exception {
            String xml = "<DatabasePermission><Name>DBPerm1</Name><RoleID>role-1</RoleID><Administer>true</Administer></DatabasePermission>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DatabasePermission permission = PermissionConverter.getDatabasePermission(nl);

            assertTrue(permission.administer().isPresent());
            assertTrue(permission.administer().get());
        }

        @Test
        void getDatabasePermission_withAdministerFalse() throws Exception {
            String xml = "<DatabasePermission><Name>DBPerm1</Name><RoleID>role-1</RoleID><Administer>false</Administer></DatabasePermission>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DatabasePermission permission = PermissionConverter.getDatabasePermission(nl);

            assertTrue(permission.administer().isPresent());
            assertFalse(permission.administer().get());
        }

        @Test
        void getDatabasePermissionList_multipleItems() throws Exception {
            String xml = "<root><DatabasePermission><Name>P1</Name><RoleID>r1</RoleID></DatabasePermission>" +
                    "<DatabasePermission><Name>P2</Name><RoleID>r2</RoleID></DatabasePermission></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            List<DatabasePermission> list = PermissionConverter.getDatabasePermissionList(nl);

            assertEquals(2, list.size());
            assertEquals("P1", list.get(0).name());
            assertEquals("P2", list.get(1).name());
        }

        @Test
        void getDatabasePermission_withDescription() throws Exception {
            String xml = "<DatabasePermission><Name>DBPerm1</Name><RoleID>role-1</RoleID>" +
                    "<Description>Admin permissions for database</Description></DatabasePermission>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DatabasePermission permission = PermissionConverter.getDatabasePermission(nl);

            assertTrue(permission.description().isPresent());
            assertEquals("Admin permissions for database", permission.description().get());
        }

        @Test
        void getDatabasePermission_withReadDefinition() throws Exception {
            String xml = "<DatabasePermission><Name>DBPerm1</Name><RoleID>role-1</RoleID>" +
                    "<ReadDefinition>Basic</ReadDefinition></DatabasePermission>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            DatabasePermission permission = PermissionConverter.getDatabasePermission(nl);

            assertTrue(permission.readDefinition().isPresent());
            assertEquals(ReadDefinitionEnum.BASIC, permission.readDefinition().get());
        }
    }
}
