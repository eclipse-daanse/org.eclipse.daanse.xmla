/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.math.BigInteger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.api.xmla.ObjectReference;
import org.eclipse.daanse.xmla.model.record.xmla.CancelR;
import org.eclipse.daanse.xmla.model.record.xmla.ClearCacheR;
import org.eclipse.daanse.xmla.model.record.xmla.StatementR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

class CommandParserTest {

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
    class StatementCommand {

        @Test
        void getCommand_statementCommand() throws Exception {
            Document doc = parseXml("<root><Statement>SELECT * FROM table</Statement></root>");
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Command command = CommandParser.getCommand(nl, null);

            assertNotNull(command);
            assertInstanceOf(StatementR.class, command);
            StatementR statement = (StatementR) command;
            assertEquals("SELECT * FROM table", statement.statement());
        }

        @Test
        void getCommand_statementWithMdxQuery() throws Exception {
            Document doc = parseXml(
                    "<root><Statement>SELECT [Measures].Members ON COLUMNS FROM [Sales]</Statement></root>");
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Command command = CommandParser.getCommand(nl, null);

            assertInstanceOf(StatementR.class, command);
            assertEquals("SELECT [Measures].Members ON COLUMNS FROM [Sales]", ((StatementR) command).statement());
        }
    }

    @Nested
    class CancelCommand {

        @Test
        void getCancelCommand_allFields() throws Exception {
            String xml = """
                    <Cancel>
                        <ConnectionID>123</ConnectionID>
                        <SessionID>session-abc-123</SessionID>
                        <SPID>456</SPID>
                        <CancelAssociated>true</CancelAssociated>
                    </Cancel>
                    """;
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Command command = CommandParser.getCancelCommand(nl);

            assertInstanceOf(CancelR.class, command);
            CancelR cancel = (CancelR) command;
            assertEquals(BigInteger.valueOf(123), cancel.connectionID());
            assertEquals("session-abc-123", cancel.sessionID());
            assertEquals(BigInteger.valueOf(456), cancel.spid());
            assertTrue(cancel.cancelAssociated());
        }

        @Test
        void getCancelCommand_minimalFields() throws Exception {
            String xml = """
                    <Cancel>
                        <SessionID>my-session</SessionID>
                    </Cancel>
                    """;
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Command command = CommandParser.getCancelCommand(nl);

            assertInstanceOf(CancelR.class, command);
            CancelR cancel = (CancelR) command;
            assertNull(cancel.connectionID());
            assertEquals("my-session", cancel.sessionID());
            assertNull(cancel.spid());
            assertNull(cancel.cancelAssociated());
        }

        @Test
        void getCommand_cancelFromNode() throws Exception {
            String xml = """
                    <root>
                        <Cancel>
                            <SessionID>test-session</SessionID>
                        </Cancel>
                    </root>
                    """;
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Command command = CommandParser.getCommand(nl, null);

            assertInstanceOf(CancelR.class, command);
        }
    }

    @Nested
    class ClearCacheCommand {

        @Test
        void getClearCacheCommand_withObject() throws Exception {
            String xml = """
                    <ClearCache>
                        <Object>
                            <DatabaseID>AdventureWorks</DatabaseID>
                            <CubeID>Sales</CubeID>
                        </Object>
                    </ClearCache>
                    """;
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Command command = CommandParser.getClearCacheCommand(nl);

            assertInstanceOf(ClearCacheR.class, command);
            ClearCacheR clearCache = (ClearCacheR) command;
            assertNotNull(clearCache.object());
            assertEquals("AdventureWorks", clearCache.object().databaseID());
            assertEquals("Sales", clearCache.object().cubeID());
        }

        @Test
        void getClearCacheCommand_emptyObject() throws Exception {
            String xml = "<ClearCache></ClearCache>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Command command = CommandParser.getClearCacheCommand(nl);

            assertInstanceOf(ClearCacheR.class, command);
            ClearCacheR clearCache = (ClearCacheR) command;
            assertNull(clearCache.object());
        }

        @Test
        void getCommand_clearCacheFromNode() throws Exception {
            String xml = """
                    <root>
                        <ClearCache>
                            <Object>
                                <DatabaseID>TestDB</DatabaseID>
                            </Object>
                        </ClearCache>
                    </root>
                    """;
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Command command = CommandParser.getCommand(nl, null);

            assertInstanceOf(ClearCacheR.class, command);
        }
    }

    @Nested
    class ObjectReferenceParsing {

        @Test
        void getObjectReference_allFields() throws Exception {
            String xml = """
                    <Object>
                        <ServerID>server1</ServerID>
                        <DatabaseID>db1</DatabaseID>
                        <RoleID>role1</RoleID>
                        <TraceID>trace1</TraceID>
                        <AssemblyID>assembly1</AssemblyID>
                        <DimensionID>dim1</DimensionID>
                        <DimensionPermissionID>dimPerm1</DimensionPermissionID>
                        <DataSourceID>ds1</DataSourceID>
                        <DataSourcePermissionID>dsPerm1</DataSourcePermissionID>
                        <DatabasePermissionID>dbPerm1</DatabasePermissionID>
                        <DataSourceViewID>dsv1</DataSourceViewID>
                        <CubeID>cube1</CubeID>
                        <MiningStructureID>mining1</MiningStructureID>
                        <MeasureGroupID>mg1</MeasureGroupID>
                        <PerspectiveID>persp1</PerspectiveID>
                        <CubePermissionID>cubePerm1</CubePermissionID>
                        <MdxScriptID>mdx1</MdxScriptID>
                        <PartitionID>part1</PartitionID>
                        <AggregationDesignID>agg1</AggregationDesignID>
                        <MiningModelID>model1</MiningModelID>
                        <MiningModelPermissionID>modelPerm1</MiningModelPermissionID>
                        <MiningStructurePermissionID>structPerm1</MiningStructurePermissionID>
                    </Object>
                    """;
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            ObjectReference ref = CommandParser.getObjectReference(nl);

            assertNotNull(ref);
            assertEquals("server1", ref.serverID());
            assertEquals("db1", ref.databaseID());
            assertEquals("role1", ref.roleID());
            assertEquals("trace1", ref.traceID());
            assertEquals("assembly1", ref.assemblyID());
            assertEquals("dim1", ref.dimensionID());
            assertEquals("dimPerm1", ref.dimensionPermissionID());
            assertEquals("ds1", ref.dataSourceID());
            assertEquals("dsPerm1", ref.dataSourcePermissionID());
            assertEquals("dbPerm1", ref.databasePermissionID());
            assertEquals("dsv1", ref.dataSourceViewID());
            assertEquals("cube1", ref.cubeID());
            assertEquals("mining1", ref.miningStructureID());
            assertEquals("mg1", ref.measureGroupID());
            assertEquals("persp1", ref.perspectiveID());
            assertEquals("cubePerm1", ref.cubePermissionID());
            assertEquals("mdx1", ref.mdxScriptID());
            assertEquals("part1", ref.partitionID());
            assertEquals("agg1", ref.aggregationDesignID());
            assertEquals("model1", ref.miningModelID());
            assertEquals("modelPerm1", ref.miningModelPermissionID());
            assertEquals("structPerm1", ref.miningStructurePermissionID());
        }

        @Test
        void getObjectReference_minimalFields() throws Exception {
            String xml = """
                    <Object>
                        <DatabaseID>TestDatabase</DatabaseID>
                    </Object>
                    """;
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            ObjectReference ref = CommandParser.getObjectReference(nl);

            assertNotNull(ref);
            assertEquals("TestDatabase", ref.databaseID());
            assertNull(ref.serverID());
            assertNull(ref.cubeID());
        }
    }

    @Nested
    class EdgeCases {

        @Test
        void getCommand_unknownCommandReturnsNull() throws Exception {
            Document doc = parseXml("<root><UnknownCommand>data</UnknownCommand></root>");
            org.w3c.dom.Node node = doc.getDocumentElement().getFirstChild();

            Command command = CommandParser.getCommand(node, null);

            assertNull(command);
        }

        @Test
        void getCommand_emptyNodeListThrowsException() throws Exception {
            Document doc = parseXml("<root></root>");
            NodeList nl = doc.getDocumentElement().getChildNodes();

            assertThrows(XmlaParseException.class, () -> {
                CommandParser.getCommand(nl, null);
            });
        }
    }
}
