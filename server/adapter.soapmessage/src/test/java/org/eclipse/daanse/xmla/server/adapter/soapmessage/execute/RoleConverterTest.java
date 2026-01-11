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

import org.eclipse.daanse.xmla.api.xmla.Member;
import org.eclipse.daanse.xmla.api.xmla.Role;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter.RoleConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

class RoleConverterTest {

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
    class RoleTests {

        @Test
        void getRole_withName() throws Exception {
            String xml = "<Role><Name>AdminRole</Name></Role>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Role role = RoleConverter.getRole(nl);

            assertNotNull(role);
            assertEquals("AdminRole", role.name());
        }

        @Test
        void getRole_withId() throws Exception {
            String xml = "<Role><Name>Test</Name><ID>role-123</ID></Role>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Role role = RoleConverter.getRole(nl);

            assertTrue(role.id().isPresent());
            assertEquals("role-123", role.id().get());
        }

        @Test
        void getRole_withDescription() throws Exception {
            String xml = "<Role><Name>Test</Name><Description>A test role</Description></Role>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Role role = RoleConverter.getRole(nl);

            assertTrue(role.description().isPresent());
            assertEquals("A test role", role.description().get());
        }

        @Test
        void getRole_withMembers() throws Exception {
            String xml = "<Role><Name>Test</Name><Members><Member><Name>User1</Name></Member></Members></Role>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Role role = RoleConverter.getRole(nl);

            assertTrue(role.members().isPresent());
            assertEquals(1, role.members().get().size());
        }

        @Test
        void getRoleList_multipleItems() throws Exception {
            String xml = "<root><Role><Name>Role1</Name></Role>" +
                    "<Role><Name>Role2</Name></Role></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            List<Role> list = RoleConverter.getRoleList(nl, "Role");

            assertEquals(2, list.size());
            assertEquals("Role1", list.get(0).name());
            assertEquals("Role2", list.get(1).name());
        }
    }

    @Nested
    class MemberTests {

        @Test
        void getMember_withName() throws Exception {
            String xml = "<Member><Name>JohnDoe</Name></Member>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Member member = RoleConverter.getMember(nl);

            assertNotNull(member);
            assertTrue(member.name().isPresent());
            assertEquals("JohnDoe", member.name().get());
        }

        @Test
        void getMember_withSid() throws Exception {
            String xml = "<Member><Name>Test</Name><Sid>S-1-5-21-123456789</Sid></Member>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Member member = RoleConverter.getMember(nl);

            assertTrue(member.sid().isPresent());
            assertEquals("S-1-5-21-123456789", member.sid().get());
        }

        @Test
        void getMemberList_multipleItems() throws Exception {
            String xml = "<root><Member><Name>User1</Name></Member>" +
                    "<Member><Name>User2</Name></Member></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            List<Member> list = RoleConverter.getMemberList(nl);

            assertEquals(2, list.size());
            assertTrue(list.get(0).name().isPresent());
            assertEquals("User1", list.get(0).name().get());
            assertTrue(list.get(1).name().isPresent());
            assertEquals("User2", list.get(1).name().get());
        }

        @Test
        void getMember_empty() throws Exception {
            String xml = "<Member></Member>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Member member = RoleConverter.getMember(nl);

            assertNotNull(member);
            assertFalse(member.name().isPresent());
            assertFalse(member.sid().isPresent());
        }
    }
}
