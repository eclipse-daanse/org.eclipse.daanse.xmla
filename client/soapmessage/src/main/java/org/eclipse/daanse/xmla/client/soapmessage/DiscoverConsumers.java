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
package org.eclipse.daanse.xmla.client.soapmessage;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import org.eclipse.daanse.xmla.api.discover.Properties;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsRestrictions;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsRestrictions;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesRestrictions;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataRestrictions;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesRestrictions;
import org.eclipse.daanse.xmla.api.discover.dbschema.tables.DbSchemaTablesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.tables.DbSchemaTablesRestrictions;
import org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo.DbSchemaTablesInfoRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo.DbSchemaTablesInfoRestrictions;
import org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesRequest;
import org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesRestrictions;
import org.eclipse.daanse.xmla.api.discover.discover.enumerators.DiscoverEnumeratorsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.enumerators.DiscoverEnumeratorsRestrictions;
import org.eclipse.daanse.xmla.api.discover.discover.keywords.DiscoverKeywordsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.keywords.DiscoverKeywordsRestrictions;
import org.eclipse.daanse.xmla.api.discover.discover.literals.DiscoverLiteralsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.literals.DiscoverLiteralsRestrictions;
import org.eclipse.daanse.xmla.api.discover.discover.properties.DiscoverPropertiesRequest;
import org.eclipse.daanse.xmla.api.discover.discover.properties.DiscoverPropertiesRestrictions;
import org.eclipse.daanse.xmla.api.discover.discover.schemarowsets.DiscoverSchemaRowsetsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.schemarowsets.DiscoverSchemaRowsetsRestrictions;
import org.eclipse.daanse.xmla.api.discover.discover.xmlmetadata.DiscoverXmlMetaDataRequest;
import org.eclipse.daanse.xmla.api.discover.discover.xmlmetadata.DiscoverXmlMetaDataRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsRestrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesRestrictions.RESTRICTIONS_AUTHENTICATION_MODE;
import static org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesRestrictions.RESTRICTIONS_DATA_SOURCE_DESCRIPTION;
import static org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesRestrictions.RESTRICTIONS_DATA_SOURCE_INFO;
import static org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesRestrictions.RESTRICTIONS_DATA_SOURCE_NAME;
import static org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesRestrictions.RESTRICTIONS_PROVIDER_NAME;
import static org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesRestrictions.RESTRICTIONS_PROVIDER_TYPE;
import static org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesRestrictions.RESTRICTIONS_URL;
import static org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisRestrictions.RESTRICTIONS_KPI_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DBSCHEMA_CATALOGS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DBSCHEMA_COLUMNS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DBSCHEMA_PROVIDER_TYPES;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DBSCHEMA_SCHEMATA;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DBSCHEMA_SOURCE_TABLES;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DBSCHEMA_TABLES;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DBSCHEMA_TABLES_INFO;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DISCOVER;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DISCOVER_DATASOURCES;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DISCOVER_ENUMERATORS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DISCOVER_KEYWORDS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DISCOVER_LITERALS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DISCOVER_PROPERTIES;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DISCOVER_SCHEMA_ROWSETS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DISCOVER_XML_METADATA;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MDSCHEMA_ACTIONS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MDSCHEMA_CUBES;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MDSCHEMA_DIMENSIONS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MDSCHEMA_FUNCTIONS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MDSCHEMA_HIERARCHIES;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MDSCHEMA_KPIS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MDSCHEMA_LEVELS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MDSCHEMA_MEASUREGROUPS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MDSCHEMA_MEASUREGROUP_DIMENSIONS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MDSCHEMA_MEASURES;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MDSCHEMA_MEMBERS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MDSCHEMA_PROPERTIES;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MDSCHEMA_SETS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.PROPERTIES;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.PROPERTY_LIST;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.PROPERTY_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.REQUEST_TYPE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_ACTION_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_ACTION_TYPE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_AGGREGATION_DESIGN_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_ASSEMBLY_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_BASE_CUBE_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_BEST_MATCH;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_CATALOG_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_COLUMN_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_COLUMN_OLAP_TYPE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_COORDINATE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_COORDINATE_TYPE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_CUBE_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_CUBE_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_CUBE_PERMISSION_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_CUBE_SOURCE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_DATABASE_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_DATABASE_PERMISSION_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_DATA_SOURCE_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_DATA_SOURCE_PERMISSION_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_DATA_SOURCE_VIEW_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_DATA_TYPE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_DIMENSION_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_DIMENSION_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_DIMENSION_UNIQUE_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_DIMENSION_VISIBILITY;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_ENUM_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_HIERARCHY_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_HIERARCHY_ORIGIN;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_HIERARCHY_UNIQUE_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_HIERARCHY_VISIBILITY;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_INTERFACE_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_INVOCATION;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_KEYWORD;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_LEVEL_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_LEVEL_NUMBER;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_LEVEL_UNIQUE_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_LEVEL_VISIBILITY;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_LIBRARY_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_LITERAL_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_MDX_SCRIPT_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_MEASUREGROUP_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_MEASURE_GROUP_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_MEASURE_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_MEASURE_UNIQUE_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_MEASURE_VISIBILITY;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_MEMBER_CAPTION;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_MEMBER_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_MEMBER_TYPE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_MEMBER_UNIQUE_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_MINING_MODEL_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_MINING_MODEL_PERMISSION_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_MINING_STRUCTURE_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_MINING_STRUCTURE_PERMISSION_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_OBJECT_EXPANSION;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_ORIGIN;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_PARTITION_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_PERMISSION_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_PERSPECTIVE_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_PROPERTY_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_PROPERTY_ORIGIN;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_PROPERTY_TYPE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_PROPERTY_VISIBILITY;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_ROLE_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_SCHEMA_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_SCHEMA_OWNER;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_SCOPE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_SET_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_TABLE_CATALOG;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_TABLE_NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_TABLE_SCHEMA;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_TABLE_TYPE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_TRACE_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTIONS_TREE_OP;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.RESTRICTION_LIST;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.SCHEMA_NAME1;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.TABLE_CATALOG;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.TABLE_SCHEMA;
import static org.eclipse.daanse.xmla.client.soapmessage.SoapUtil.addChildElement;
import static org.eclipse.daanse.xmla.client.soapmessage.SoapUtil.addChildElementPropertyList;

public class DiscoverConsumers {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscoverConsumers.class);
    private static final String NS_URI = "urn:schemas-microsoft-com:xml-analysis";

    private DiscoverConsumers() {
        // constructor
    }

    static Consumer<SOAPMessage> createDbSchemaTablesInfoRequestConsumer(DbSchemaTablesInfoRequest requestApi) {
        return message -> {
            try {
                DbSchemaTablesInfoRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(DBSCHEMA_TABLES_INFO);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.catalogName().ifPresent(v -> addChildElement(restrictionList, TABLE_CATALOG, v));
                dr.schemaName().ifPresent(v -> addChildElement(restrictionList, TABLE_SCHEMA, v));
                addChildElement(restrictionList, RESTRICTIONS_TABLE_NAME, dr.tableName());
                addChildElement(restrictionList, RESTRICTIONS_TABLE_TYPE, dr.tableType().getValue());

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers DbSchemaTablesInfoRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createDbSchemaSourceTablesRequestConsumer(DbSchemaSourceTablesRequest requestApi) {
        return message -> {
            try {
                DbSchemaSourceTablesRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(DBSCHEMA_SOURCE_TABLES);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.catalogName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_TABLE_CATALOG, v));
                dr.schemaName().ifPresent(v -> addChildElement(restrictionList, TABLE_SCHEMA, v));
                addChildElement(restrictionList, RESTRICTIONS_TABLE_NAME, dr.tableName());
                addChildElement(restrictionList, RESTRICTIONS_TABLE_TYPE, dr.tableType().getValue());

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers DbSchemaSourceTablesRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createMdSchemaMeasureGroupsRequestConsumer(MdSchemaMeasureGroupsRequest requestApi) {
        return message -> {
            try {
                MdSchemaMeasureGroupsRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(MDSCHEMA_MEASUREGROUPS);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.catalogName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CATALOG_NAME, v));
                dr.schemaName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_SCHEMA_NAME, v));
                dr.cubeName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_NAME, v));
                dr.measureGroupName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_MEASUREGROUP_NAME, v));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers MdSchemaMeasureGroupsRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createMdSchemaKpisRequestConsumer(MdSchemaKpisRequest requestApi) {
        return message -> {
            try {
                MdSchemaKpisRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(MDSCHEMA_KPIS);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.catalogName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CATALOG_NAME, v));
                dr.schemaName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_SCHEMA_NAME, v));
                dr.cubeName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_NAME, v));
                dr.kpiName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_KPI_NAME, v));
                dr.cubeSource().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_SOURCE, String.valueOf(v.getValue())));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers MdSchemaMeasureGroupsRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createMdSchemaSetsRequestConsumer(MdSchemaSetsRequest requestApi) {
        return message -> {
            try {
                MdSchemaSetsRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(MDSCHEMA_SETS);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.catalogName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CATALOG_NAME, v));
                dr.schemaName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_SCHEMA_NAME, v));
                dr.cubeName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_NAME, v));
                dr.setName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_SET_NAME, v));
                dr.scope().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_SCOPE, String.valueOf(v.getValue())));
                dr.cubeSource().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_SOURCE, String.valueOf(v.getValue())));
                dr.hierarchyUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_HIERARCHY_UNIQUE_NAME, v));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers MdSchemaPropertiesRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createMdSchemaPropertiesRequestConsumer(MdSchemaPropertiesRequest requestApi) {
        return message -> {
            try {
                MdSchemaPropertiesRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(MDSCHEMA_PROPERTIES);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.catalogName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CATALOG_NAME, v));
                dr.schemaName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_SCHEMA_NAME, v));
                dr.cubeName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_NAME, v));
                dr.dimensionUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DIMENSION_UNIQUE_NAME, v));
                dr.hierarchyUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_HIERARCHY_UNIQUE_NAME, v));
                dr.levelUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_LEVEL_UNIQUE_NAME, v));
                dr.memberUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_MEMBER_UNIQUE_NAME, v));
                dr.propertyType().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_PROPERTY_TYPE,
                        String.valueOf(v.getValue())));
                dr.propertyName().ifPresent(v -> addChildElement(restrictionList, PROPERTY_NAME, v));
                dr.propertyOrigin().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_PROPERTY_ORIGIN,
                        String.valueOf(v.getValue())));
                dr.cubeSource().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_SOURCE, String.valueOf(v.getValue())));
                dr.propertyVisibility().ifPresent(v -> addChildElement(restrictionList,
                        RESTRICTIONS_PROPERTY_VISIBILITY, String.valueOf(v.getValue())));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers MdSchemaPropertiesRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createMdSchemaMembersRequestConsumer(MdSchemaMembersRequest requestApi) {
        return message -> {
            try {
                MdSchemaMembersRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(MDSCHEMA_MEMBERS);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.catalogName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CATALOG_NAME, v));
                dr.schemaName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_SCHEMA_NAME, v));
                dr.cubeName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_NAME, v));
                dr.dimensionUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DIMENSION_UNIQUE_NAME, v));
                dr.hierarchyUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_HIERARCHY_UNIQUE_NAME, v));
                dr.levelUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_LEVEL_UNIQUE_NAME, v));
                dr.levelNumber()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_LEVEL_NUMBER, String.valueOf(v)));
                dr.memberName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_MEMBER_NAME, v));
                dr.memberUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_MEMBER_UNIQUE_NAME, v));
                dr.memberType().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_MEMBER_TYPE, String.valueOf(v.getValue())));
                dr.memberCaption().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_MEMBER_CAPTION, v));
                dr.cubeSource().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_SOURCE, String.valueOf(v.getValue())));
                dr.treeOp().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_TREE_OP, String.valueOf(v.getValue())));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers MdSchemaMeasuresRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createMdSchemaMeasuresRequestConsumer(MdSchemaMeasuresRequest requestApi) {
        return message -> {
            try {
                MdSchemaMeasuresRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(MDSCHEMA_MEASURES);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.catalogName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CATALOG_NAME, v));
                dr.schemaName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_SCHEMA_NAME, v));
                dr.cubeName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_NAME, v));
                dr.measureName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_MEASURE_NAME, v));
                dr.measureUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_MEASURE_UNIQUE_NAME, v));
                dr.measureGroupName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_MEASUREGROUP_NAME, v));
                dr.cubeSource().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_SOURCE, String.valueOf(v.getValue())));
                dr.cubeSource().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_MEASURE_VISIBILITY,
                        String.valueOf(v.getValue())));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers MdSchemaMeasuresRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createMdSchemaMeasureGroupDimensionsRequestConsumer(
            MdSchemaMeasureGroupDimensionsRequest requestApi) {
        return message -> {
            try {
                MdSchemaMeasureGroupDimensionsRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(MDSCHEMA_MEASUREGROUP_DIMENSIONS);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.catalogName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CATALOG_NAME, v));
                dr.schemaName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_SCHEMA_NAME, v));
                dr.cubeName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_NAME, v));
                dr.measureGroupName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_MEASUREGROUP_NAME, v));
                dr.dimensionUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DIMENSION_UNIQUE_NAME, v));
                dr.dimensionVisibility().ifPresent(v -> addChildElement(restrictionList,
                        RESTRICTIONS_DIMENSION_VISIBILITY, String.valueOf(v.getValue())));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers MdSchemaLevelsRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createMdSchemaLevelsRequestConsumer(MdSchemaLevelsRequest requestApi) {
        return message -> {
            try {
                MdSchemaLevelsRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(MDSCHEMA_LEVELS);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.catalogName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CATALOG_NAME, v));
                dr.schemaName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_SCHEMA_NAME, v));
                dr.cubeName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_NAME, v));
                dr.dimensionUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DIMENSION_UNIQUE_NAME, v));
                dr.hierarchyUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_HIERARCHY_UNIQUE_NAME, v));
                dr.levelName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_LEVEL_NAME, v));
                dr.levelUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_LEVEL_UNIQUE_NAME, v));
                dr.cubeSource().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_SOURCE, String.valueOf(v.getValue())));
                dr.levelVisibility().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_LEVEL_VISIBILITY,
                        String.valueOf(v.getValue())));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers MdSchemaLevelsRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createDbSchemaSchemataRequestConsumer(DbSchemaSchemataRequest requestApi) {
        return message -> {
            try {
                DbSchemaSchemataRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(DBSCHEMA_SCHEMATA);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                addChildElement(restrictionList, RESTRICTIONS_CATALOG_NAME, dr.catalogName());
                addChildElement(restrictionList, RESTRICTIONS_SCHEMA_NAME, dr.schemaName());
                addChildElement(restrictionList, RESTRICTIONS_SCHEMA_OWNER, dr.schemaOwner());

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers DbSchemaSchemataRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createDbSchemaProviderTypesRequestConsumer(DbSchemaProviderTypesRequest requestApi) {
        return message -> {
            try {
                DbSchemaProviderTypesRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(DBSCHEMA_PROVIDER_TYPES);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.dataType().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_DATA_TYPE, String.valueOf(v.getValue())));
                dr.bestMatch()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_BEST_MATCH, String.valueOf(v)));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers DbSchemaColumnsRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createDbSchemaColumnsRequestConsumer(DbSchemaColumnsRequest requestApi) {
        return message -> {
            try {
                DbSchemaColumnsRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(DBSCHEMA_COLUMNS);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.tableCatalog().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_TABLE_CATALOG, v));
                dr.tableSchema().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_TABLE_SCHEMA, v));
                dr.tableName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_TABLE_NAME, v));
                dr.columnName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_COLUMN_NAME, v));
                dr.columnOlapType()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_COLUMN_OLAP_TYPE, v.name()));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers DbSchemaColumnsRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createDiscoverXmlMetaDataRequestConsumer(DiscoverXmlMetaDataRequest requestApi) {
        return message -> {
            try {
                DiscoverXmlMetaDataRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(DISCOVER_XML_METADATA);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.databaseId().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DATABASE_ID, v));
                dr.dimensionId().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DIMENSION_ID, v));
                dr.cubeId().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_ID, v));
                dr.measureGroupId().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_MEASURE_GROUP_ID, v));
                dr.partitionId().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_PARTITION_ID, v));
                dr.perspectiveId().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_PERSPECTIVE_ID, v));
                dr.dimensionPermissionId()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_PERMISSION_ID, v));
                dr.roleId().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_ROLE_ID, v));
                dr.databasePermissionId()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DATABASE_PERMISSION_ID, v));
                dr.miningModelId().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_MINING_MODEL_ID, v));
                dr.miningModelPermissionId()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_MINING_MODEL_PERMISSION_ID, v));
                dr.dataSourceId().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DATA_SOURCE_ID, v));
                dr.miningStructureId()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_MINING_STRUCTURE_ID, v));
                dr.aggregationDesignId()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_AGGREGATION_DESIGN_ID, v));
                dr.traceId().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_TRACE_ID, v));
                dr.miningStructurePermissionId().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_MINING_STRUCTURE_PERMISSION_ID, v));
                dr.cubePermissionId()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_PERMISSION_ID, v));
                dr.assemblyId().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_ASSEMBLY_ID, v));
                dr.mdxScriptId().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_MDX_SCRIPT_ID, v));
                dr.dataSourceViewId()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DATA_SOURCE_VIEW_ID, v));
                dr.dataSourcePermissionId()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DATA_SOURCE_PERMISSION_ID, v));
                dr.objectExpansion()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_OBJECT_EXPANSION, v.getValue()));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers DiscoverSchemaRowsetsRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createDiscoverSchemaRowsetsRequestConsumer(DiscoverSchemaRowsetsRequest requestApi) {
        return message -> {
            try {
                DiscoverSchemaRowsetsRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(DISCOVER_SCHEMA_ROWSETS);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.schemaName().ifPresent(v -> addChildElement(restrictionList, SCHEMA_NAME1, v));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers DiscoverSchemaRowsetsRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createDiscoverLiteralsRequestConsumer(DiscoverLiteralsRequest requestApi) {
        return message -> {
            try {
                DiscoverLiteralsRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(DISCOVER_LITERALS);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.literalName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_LITERAL_NAME, v));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers DiscoverLiteralsRequest accept error", e);
            }

        };
    }

    static Consumer<SOAPMessage> createDiscoverPropertiesRequestConsumer(DiscoverPropertiesRequest requestApi) {
        return message -> {
            try {
                DiscoverPropertiesRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(DISCOVER_PROPERTIES);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                if (dr.propertyName().size() > 0) {
                    String joinedStr = dr.propertyName().stream().collect(Collectors.joining("\n", "", ""));
                    addChildElement(restrictionList, RESTRICTIONS_PROPERTY_NAME, joinedStr);
                }

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers DiscoverKeywordsRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createDiscoverKeywordsRequestConsumer(DiscoverKeywordsRequest requestApi) {
        return message -> {
            try {
                DiscoverKeywordsRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(DISCOVER_KEYWORDS);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.keyword().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_KEYWORD, v));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers DiscoverKeywordsRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createDiscoverEnumeratorsRequestConsumer(DiscoverEnumeratorsRequest requestApi) {
        return message -> {
            try {
                DiscoverEnumeratorsRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(DISCOVER_ENUMERATORS);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.enumName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_ENUM_NAME, v));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers DiscoverEnumeratorsRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createDbSchemaTablesRequestConsumer(DbSchemaTablesRequest requestApi) {
        return message -> {
            try {
                DbSchemaTablesRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(DBSCHEMA_TABLES);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.tableCatalog().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_TABLE_CATALOG, v));
                dr.tableSchema().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_TABLE_SCHEMA, v));
                dr.tableName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_TABLE_NAME, v));
                dr.tableType().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_TABLE_TYPE, v));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers DbSchemaTablesRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createDbSchemaCatalogsRequestConsumer(DbSchemaCatalogsRequest requestApi) {
        return message -> {
            try {
                DbSchemaCatalogsRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(DBSCHEMA_CATALOGS);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.catalogName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CATALOG_NAME, v));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers DbSchemaCatalogsRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createMdSchemaActionsRequestConsumer(MdSchemaActionsRequest requestApi) {
        return message -> {
            try {
                MdSchemaActionsRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(MDSCHEMA_ACTIONS);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.catalogName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CATALOG_NAME, v));
                dr.schemaName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_SCHEMA_NAME, v));
                if (dr.cubeName() != null) {
                    addChildElement(restrictionList, RESTRICTIONS_CUBE_NAME, dr.cubeName());
                }
                dr.actionName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_ACTION_NAME, v));
                dr.actionType().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_ACTION_TYPE, String.valueOf(v.getValue())));
                dr.coordinate().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_COORDINATE, v));
                if (dr.coordinateType() != null) {
                    addChildElement(restrictionList, RESTRICTIONS_COORDINATE_TYPE,
                            String.valueOf(dr.coordinateType().getValue()));
                }
                if (dr.invocation() != null) {
                    addChildElement(restrictionList, RESTRICTIONS_INVOCATION,
                            String.valueOf(dr.invocation().getValue()));
                }
                dr.cubeSource().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_SOURCE, String.valueOf(v.getValue())));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers MdSchemaActionsRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createMdSchemaCubesRequestConsumer(MdSchemaCubesRequest requestApi) {
        return message -> {
            try {
                MdSchemaCubesRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(MDSCHEMA_CUBES);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                if (dr.catalogName() != null) {
                    addChildElement(restrictionList, RESTRICTIONS_CATALOG_NAME, dr.catalogName());
                }
                dr.schemaName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_SCHEMA_NAME, v));
                dr.cubeName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_NAME, v));
                dr.baseCubeName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_BASE_CUBE_NAME, v));
                dr.cubeSource().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_SOURCE, String.valueOf(v.getValue())));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers MdSchemaCubesRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createMdSchemaDimensionsRequestConsumer(MdSchemaDimensionsRequest requestApi) {
        return message -> {
            try {
                MdSchemaDimensionsRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(MDSCHEMA_DIMENSIONS);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);
                dr.catalogName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CATALOG_NAME, v));
                dr.schemaName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_SCHEMA_NAME, v));
                dr.cubeName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_NAME, v));
                dr.dimensionName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DIMENSION_NAME, v));
                dr.dimensionUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DIMENSION_UNIQUE_NAME, v));
                dr.cubeSource().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_SOURCE, String.valueOf(v.getValue())));
                dr.dimensionVisibility().ifPresent(v -> addChildElement(restrictionList,
                        RESTRICTIONS_DIMENSION_VISIBILITY, String.valueOf(v.getValue())));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers MdSchemaDimensionsRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createMdSchemaFunctionsRequestConsumer(MdSchemaFunctionsRequest requestApi) {
        return message -> {
            try {
                MdSchemaFunctionsRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(MDSCHEMA_FUNCTIONS);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.origin().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_ORIGIN, String.valueOf(v.getValue())));
                dr.interfaceName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_INTERFACE_NAME, v.name()));
                dr.libraryName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_LIBRARY_NAME, v));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers MdSchemaFunctionsRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createMdSchemaHierarchiesRequestConsumer(MdSchemaHierarchiesRequest requestApi) {
        return message -> {
            try {
                MdSchemaHierarchiesRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(MDSCHEMA_HIERARCHIES);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);

                dr.catalogName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CATALOG_NAME, v));
                dr.schemaName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_SCHEMA_NAME, v));
                dr.cubeName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_NAME, v));
                dr.dimensionUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DIMENSION_UNIQUE_NAME, v));
                dr.hierarchyName().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_HIERARCHY_NAME, v));
                dr.hierarchyUniqueName()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_HIERARCHY_UNIQUE_NAME, v));
                dr.hierarchyOrigin().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_HIERARCHY_ORIGIN, String.valueOf(v)));
                dr.cubeSource().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_CUBE_SOURCE, String.valueOf(v.getValue())));
                dr.hierarchyVisibility().ifPresent(v -> addChildElement(restrictionList,
                        RESTRICTIONS_HIERARCHY_VISIBILITY, String.valueOf(v.getValue())));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers MdSchemaHierarchiesRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createDiscoverDataSourcesRequestConsumer(DiscoverDataSourcesRequest requestApi) {
        return message -> {
            try {
                DiscoverDataSourcesRestrictions dr = requestApi.restrictions();
                Properties properties = requestApi.properties();
                SOAPElement discover = message.getSOAPBody().addChildElement(DISCOVER, null, NS_URI);
                discover.addChildElement(REQUEST_TYPE).setTextContent(DISCOVER_DATASOURCES);
                SOAPElement restrictionList = discover.addChildElement(RESTRICTIONS).addChildElement(RESTRICTION_LIST);
                addChildElement(restrictionList, RESTRICTIONS_DATA_SOURCE_NAME, dr.dataSourceName());
                dr.dataSourceDescription()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DATA_SOURCE_DESCRIPTION, v));
                dr.dataSourceDescription()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DATA_SOURCE_DESCRIPTION, v));
                dr.url().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_URL, v));
                dr.dataSourceInfo().ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_DATA_SOURCE_INFO, v));
                if (dr.providerName() != null) {
                    addChildElement(restrictionList, RESTRICTIONS_PROVIDER_NAME, dr.providerName());
                }
                dr.providerType()
                        .ifPresent(v -> addChildElement(restrictionList, RESTRICTIONS_PROVIDER_TYPE, v.name()));
                dr.authenticationMode().ifPresent(
                        v -> addChildElement(restrictionList, RESTRICTIONS_AUTHENTICATION_MODE, v.getValue()));

                SOAPElement propertyList = discover.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("DiscoverConsumers DiscoverDataSourcesRequest accept error", e);
            }
        };
    }
}
