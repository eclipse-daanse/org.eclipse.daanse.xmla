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
 *     SmartCity Jena - initial
 *     Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.server.tck;

public class TestRequests {

    public static final String STATEMENT_REQUEST = """
                    <Execute xmlns="urn:schemas-microsoft-com:xml-analysis">
                    <Command>
                        <Statement>
                            select [Measures].[Sales Count] on 0, non empty [Store].[Store State].members on 1 from [Sales]
                        </Statement>
                    </Command>
                    <Properties>
                        <PropertyList>
                            <DataSourceInfo>FoodMart</DataSourceInfo>
                            <Catalog>FoodMart</Catalog>
                            <Format>Tabular</Format>
                            <AxisFormat>TupleFormat</AxisFormat>
                        </PropertyList>
                    </Properties>
                    </Execute>
            """;

    public static final String CLEAR_CACHE_REQUEST = """
            <Execute xmlns="urn:schemas-microsoft-com:xml-analysis">
            <Command>
                <ClearCache>
                    <Object>
                        <ServerID>serverID</ServerID>
                        <DatabaseID>databaseID</DatabaseID>
                        <RoleID>roleID</RoleID>
                        <TraceID>traceID</TraceID>
                        <AssemblyID>assemblyID</AssemblyID>
                        <DimensionID>dimensionID</DimensionID>
                        <DimensionPermissionID>dimensionPermissionID</DimensionPermissionID>
                        <DataSourceID>dataSourceID</DataSourceID>
                        <DataSourcePermissionID>dataSourcePermissionID</DataSourcePermissionID>
                        <DatabasePermissionID>databasePermissionID</DatabasePermissionID>
                        <DataSourceViewID>dataSourceViewID</DataSourceViewID>
                        <CubeID>cubeID</CubeID>
                        <MiningStructureID>miningStructureID</MiningStructureID>
                        <MeasureGroupID>measureGroupID</MeasureGroupID>
                        <PerspectiveID>perspectiveID</PerspectiveID>
                        <CubePermissionID>cubePermissionID</CubePermissionID>
                        <MdxScriptID>mdxScriptID</MdxScriptID>
                        <PartitionID>partitionID</PartitionID>
                        <AggregationDesignID>aggregationDesignID</AggregationDesignID>
                        <MiningModelID>miningModelID</MiningModelID>
                        <MiningModelPermissionID>miningModelPermissionID</MiningModelPermissionID>
                        <MiningStructurePermissionID>miningStructurePermissionID</MiningStructurePermissionID>
                    </Object>
                </ClearCache>
            </Command>
            <Properties>
                <PropertyList>
                    <DataSourceInfo>FoodMart</DataSourceInfo>
                    <Catalog>FoodMart</Catalog>
                    <Format>Tabular</Format>
                    <AxisFormat>TupleFormat</AxisFormat>
                </PropertyList>
            </Properties>
            </Execute>
            """;

    public static final String CANCEL_REQUEST = """
            <Execute xmlns="urn:schemas-microsoft-com:xml-analysis">
            <Command>
                <Cancel>
                    <ConnectionID>1</ConnectionID>
                    <SessionID>sessionID</SessionID>
                    <SPID>2</SPID>
                    <CancelAssociated>true</CancelAssociated>
                </Cancel>
            </Command>
            <Properties>
                <PropertyList>
                    <DataSourceInfo>FoodMart</DataSourceInfo>
                    <Catalog>FoodMart</Catalog>
                    <Format>Tabular</Format>
                    <AxisFormat>TupleFormat</AxisFormat>
                </PropertyList>
            </Properties>
            </Execute>
            """;

    public static final String ALTER_REQUEST = """
            <Execute xmlns="urn:schemas-microsoft-com:xml-analysis">
                <Command>
                    <Alter>
                        <Object>
                            <DatabaseID>AdventureWorks_SSAS_Alter</DatabaseID>
                            <DimensionID>Dim Customer</DimensionID>
                        </Object>
                        <ObjectDefinition>
                            <Dimension xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                                    xmlns:ddl2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
                                    xmlns:ddl2_2="http://schemas.microsoft.com/analysisservices/2003/engine/2/2"
                                    xmlns:ddl100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100">
                                <ID>Dim Customer</ID>
                                <Name>Customer</Name>
                                <Source xsi:type="DataSourceViewBinding">
                                    <DataSourceViewID>dsvAdventureWorksDW2008</DataSourceViewID>
                                </Source>
                                <ErrorConfiguration>
                                    <KeyNotFound>ReportAndStop</KeyNotFound>
                                    <KeyDuplicate>ReportAndStop</KeyDuplicate>
                                    <NullKeyNotAllowed>ReportAndStop</NullKeyNotAllowed>
                                </ErrorConfiguration>
                                <Language>1033</Language>
                                <Collation>Latin1_General_CI_AS</Collation>
                                <UnknownMemberName>Unknown</UnknownMemberName>
                                <Attributes>
                                    <Attribute>
                                        <ID>Customer Key</ID>
                                        <Name>Customer Key</Name>
                                        <Usage>Key</Usage>
                                        <EstimatedCount>18484</EstimatedCount>
                                        <KeyColumns>
                                            <KeyColumn>
                                                <DataType>Integer</DataType>
                                                <Source xsi:type="ColumnBinding">
                                                    <TableID>dbo_DimCustomer</TableID>
                                                    <ColumnID>CustomerKey</ColumnID>
                                                </Source>
                                            </KeyColumn>
                                        </KeyColumns>
                                        <OrderBy>Key</OrderBy>
                                    </Attribute>
                                </Attributes>
                                <ProactiveCaching>
                                    <SilenceInterval>-PT1S</SilenceInterval>
                                    <Latency>-PT1S</Latency>
                                    <SilenceOverrideInterval>-PT1S</SilenceOverrideInterval>
                                    <ForceRebuildInterval>-PT1S</ForceRebuildInterval>
                                </ProactiveCaching>
                            </Dimension>
                        </ObjectDefinition>
                    </Alter>
                </Command>
                <Properties>
                    <PropertyList>
                        <DataSourceInfo>FoodMart</DataSourceInfo>
                        <Catalog>FoodMart</Catalog>
                        <Format>Tabular</Format>
                        <AxisFormat>TupleFormat</AxisFormat>
                    </PropertyList>
                </Properties>
            </Execute>
            """;

    private TestRequests() {
        // constructor
    }
}
