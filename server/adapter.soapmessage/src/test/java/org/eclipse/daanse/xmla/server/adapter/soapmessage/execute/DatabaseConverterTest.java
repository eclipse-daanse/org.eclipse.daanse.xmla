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

import org.eclipse.daanse.xmla.api.xmla.Account;
import org.eclipse.daanse.xmla.api.xmla.Assembly;
import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.api.xmla.Database;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter.DatabaseConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

class DatabaseConverterTest {

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
    class DatabaseTests {

        @Test
        void getDatabase_withNameAndId() throws Exception {
            String xml = "<Database><Name>TestDB</Name><ID>db-123</ID></Database>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Database db = DatabaseConverter.getDatabase(nl, mockCommandParser, mockAssemblySupplier);

            assertNotNull(db);
            assertEquals("TestDB", db.name());
            assertEquals("db-123", db.id());
        }

        @Test
        void getDatabase_withDescription() throws Exception {
            String xml = "<Database><Name>TestDB</Name><Description>A test database</Description></Database>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Database db = DatabaseConverter.getDatabase(nl, mockCommandParser, mockAssemblySupplier);

            assertEquals("A test database", db.description());
        }

        @Test
        void getDatabase_withCollation() throws Exception {
            String xml = "<Database><Name>TestDB</Name><Collation>Latin1_General_CI_AS</Collation></Database>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Database db = DatabaseConverter.getDatabase(nl, mockCommandParser, mockAssemblySupplier);

            assertEquals("Latin1_General_CI_AS", db.collation());
        }

        @Test
        void getDatabase_withReadWriteMode() throws Exception {
            String xml = "<Database><Name>TestDB</Name><ReadWriteMode>ReadWrite</ReadWriteMode></Database>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Database db = DatabaseConverter.getDatabase(nl, mockCommandParser, mockAssemblySupplier);

            assertEquals("ReadWrite", db.readWriteMode());
        }

        @Test
        void getDatabase_withCompatibilityLevel() throws Exception {
            String xml = "<Database><Name>TestDB</Name><CompatibilityLevel>1500</CompatibilityLevel></Database>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Database db = DatabaseConverter.getDatabase(nl, mockCommandParser, mockAssemblySupplier);

            assertNotNull(db.compatibilityLevel());
            assertEquals(1500, db.compatibilityLevel().intValue());
        }

        @Test
        void getDatabase_withVisible() throws Exception {
            String xml = "<Database><Name>TestDB</Name><Visible>true</Visible></Database>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Database db = DatabaseConverter.getDatabase(nl, mockCommandParser, mockAssemblySupplier);

            assertTrue(db.visible());
        }

        @Test
        void getDatabaseList_multipleItems() throws Exception {
            String xml = "<root><Database><Name>DB1</Name></Database>" +
                    "<Database><Name>DB2</Name></Database></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            List<Database> list = DatabaseConverter.getDatabaseList(nl, mockCommandParser, mockAssemblySupplier);

            assertEquals(2, list.size());
            assertEquals("DB1", list.get(0).name());
            assertEquals("DB2", list.get(1).name());
        }
    }

    @Nested
    class AccountTests {

        @Test
        void getAccount_withAccountType() throws Exception {
            String xml = "<Account><AccountType>Revenue</AccountType></Account>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Account account = DatabaseConverter.getAccount(nl);

            assertNotNull(account);
            assertEquals("Revenue", account.accountType());
        }

        @Test
        void getAccount_withAggregationFunction() throws Exception {
            String xml = "<Account><AccountType>Revenue</AccountType><AggregationFunction>Sum</AggregationFunction></Account>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Account account = DatabaseConverter.getAccount(nl);

            assertEquals("Sum", account.aggregationFunction());
        }

        @Test
        void getAccount_withAliases() throws Exception {
            String xml = "<Account><AccountType>Revenue</AccountType><Aliases><Alias>Sales</Alias><Alias>Income</Alias></Aliases></Account>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Account account = DatabaseConverter.getAccount(nl);

            assertNotNull(account.aliases());
            assertEquals(2, account.aliases().size());
            assertTrue(account.aliases().contains("Sales"));
            assertTrue(account.aliases().contains("Income"));
        }

        @Test
        void getAccountList_multipleItems() throws Exception {
            String xml = "<root><Account><AccountType>Revenue</AccountType></Account>" +
                    "<Account><AccountType>Expense</AccountType></Account></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            List<Account> list = DatabaseConverter.getAccountList(nl);

            assertEquals(2, list.size());
            assertEquals("Revenue", list.get(0).accountType());
            assertEquals("Expense", list.get(1).accountType());
        }
    }
}
