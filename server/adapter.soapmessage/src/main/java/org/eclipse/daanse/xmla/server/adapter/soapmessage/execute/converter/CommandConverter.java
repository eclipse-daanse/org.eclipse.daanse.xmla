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

import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ASSEMBLY2;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CUBE2;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASURE_GROUP;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROLE2;

import org.eclipse.daanse.xmla.api.xmla.AggregationDesign;
import org.eclipse.daanse.xmla.api.xmla.Assembly;
import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.api.xmla.Cube;
import org.eclipse.daanse.xmla.api.xmla.DataSource;
import org.eclipse.daanse.xmla.api.xmla.DataSourceView;
import org.eclipse.daanse.xmla.api.xmla.Database;
import org.eclipse.daanse.xmla.api.xmla.Dimension;
import org.eclipse.daanse.xmla.api.xmla.MajorObject;
import org.eclipse.daanse.xmla.api.xmla.MdxScript;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroup;
import org.eclipse.daanse.xmla.api.xmla.MiningModel;
import org.eclipse.daanse.xmla.api.xmla.MiningStructure;
import org.eclipse.daanse.xmla.api.xmla.Partition;
import org.eclipse.daanse.xmla.api.xmla.Permission;
import org.eclipse.daanse.xmla.api.xmla.Perspective;
import org.eclipse.daanse.xmla.api.xmla.Role;
import org.eclipse.daanse.xmla.api.xmla.Server;
import org.eclipse.daanse.xmla.api.xmla.Trace;
import org.eclipse.daanse.xmla.model.record.xmla.MajorObjectR;
import org.w3c.dom.NodeList;

import jakarta.xml.soap.SOAPElement;

/**
 * Converter for XMLA Command elements. Handles command parsing and MajorObject
 * conversion for Execute operations.
 */
public class CommandConverter {

    private CommandConverter() {
        // utility class
    }

    /**
     * Parse a Command from a SOAPElement. Entry point for ExecuteDispatcher.
     *
     * @param element the SOAP element containing the command
     * @return the parsed Command
     */
    public static Command commandToCommand(SOAPElement element) {
        return CommandParser.commandToCommand(element, CommandConverter::getMajorObject);
    }

    /**
     * Parse a MajorObject from a NodeList. Used for Alter command ObjectDefinition
     * parsing.
     *
     * @param nl the NodeList containing the MajorObject definition
     * @return the parsed MajorObject
     */
    static MajorObject getMajorObject(NodeList nl) {
        AggregationDesign aggregationDesign = null;
        Assembly assembly = null;
        Cube cube = null;
        Database database = null;
        DataSource dataSource = null;
        DataSourceView dataSourceView = null;
        Dimension dimension = null;
        MdxScript mdxScript = null;
        MeasureGroup measureGroup = null;
        MiningModel miningModel = null;
        MiningStructure miningStructure = null;
        Partition partition = null;
        Permission permission = null;
        Perspective perspective = null;
        Role role = null;
        Server server = null;
        Trace trace = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("AggregationDesign".equals(node.getNodeName())) {
                    aggregationDesign = AggregationConverter.getAggregationDesign(node.getChildNodes(),
                            CommonConverter::getAnnotationList);
                }
                if (ASSEMBLY2.equals(node.getNodeName())) {
                    assembly = AssemblyConverter.getAssembly(node.getChildNodes());
                }
                if (CUBE2.equals(node.getNodeName())) {
                    cube = CubeConverter.getCube(node.getChildNodes(), CommandConverter::getCommandFromNodeList);
                }
                if ("Database".equals(node.getNodeName())) {
                    database = DatabaseConverter.getDatabase(node.getChildNodes(),
                            CommandConverter::getCommandFromNodeList, AssemblyConverter::getAssemblyList);
                }
                if ("DataSource".equals(node.getNodeName())) {
                    dataSource = DataSourceConverter.getDataSource(node.getChildNodes());
                }
                if ("DataSourceView".equals(node.getNodeName())) {
                    dataSourceView = DataSourceConverter.getDataSourceView(node.getChildNodes());
                }
                if (DIMENSION.equals(node.getNodeName())) {
                    dimension = DimensionConverter.getDimension(node.getChildNodes());
                }
                if ("MdxScript".equals(node.getNodeName())) {
                    mdxScript = CubeConverter.getMdxScript(node.getChildNodes(),
                            CommandConverter::getCommandFromNodeList);
                }
                if (MEASURE_GROUP.equals(node.getNodeName())) {
                    measureGroup = CubeConverter.getMeasureGroup(node.getChildNodes());
                }
                if ("MiningModel".equals(node.getNodeName())) {
                    miningModel = MiningConverter.getMiningModel(node.getChildNodes());
                }
                if ("MiningStructure".equals(node.getNodeName())) {
                    miningStructure = MiningConverter.getMiningStructure(node.getChildNodes());
                }
                if ("Partition".equals(node.getNodeName())) {
                    partition = PartitionConverter.getPartition(node.getChildNodes());
                }
                if ("Permission".equals(node.getNodeName())) {
                    permission = PermissionConverter.getPermission(node.getChildNodes());
                }
                if ("Perspective".equals(node.getNodeName())) {
                    perspective = PerspectiveConverter.getPerspective(node.getChildNodes());
                }
                if (ROLE2.equals(node.getNodeName())) {
                    role = RoleConverter.getRole(node.getChildNodes());
                }
                if ("Server".equals(node.getNodeName())) {
                    server = ServerConverter.getServer(node.getChildNodes(), CommandConverter::getCommandFromNodeList,
                            AssemblyConverter::getAssemblyList);
                }
                if ("Trace".equals(node.getNodeName())) {
                    trace = TraceEventConverter.getTrace(node.getChildNodes());
                }
            }
        }
        return new MajorObjectR(aggregationDesign, assembly, cube, database, dataSource, dataSourceView, dimension,
                mdxScript, measureGroup, miningModel, miningStructure, partition, permission, perspective, role, server,
                trace);
    }

    /**
     * Parse a Command from a NodeList. Used by CubeConverter and DatabaseConverter
     * for nested commands.
     *
     * @param nl the NodeList containing the command
     * @return the parsed Command
     */
    public static Command getCommandFromNodeList(NodeList nl) {
        return CommandParser.getCommand(nl, CommandConverter::getMajorObject);
    }
}
