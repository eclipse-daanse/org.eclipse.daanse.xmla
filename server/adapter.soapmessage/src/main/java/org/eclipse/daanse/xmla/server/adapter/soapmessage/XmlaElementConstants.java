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
package org.eclipse.daanse.xmla.server.adapter.soapmessage;

/**
 * XMLA element name constants used in XML parsing. Contains string constants
 * for element names in XMLA requests and responses.
 */
public interface XmlaElementConstants {

    String STATEMENT = "Statement";
    String ALTER = "Alter";
    String CANCEL = "Cancel";
    String CLEAR_CACHE = "ClearCache";
    String COMMAND = "Command";

    String SERVER_ID = "ServerID";
    String DATABASE_ID = "DatabaseID";
    String CUBE_ID = "CubeID";
    String DIMENSION_ID = "DimensionID";
    String MEASURE_GROUP_ID = "MeasureGroupID";
    String PARTITION_ID = "PartitionID";
    String MINING_STRUCTURE_ID = "MiningStructureID";
    String MINING_MODEL_ID = "MiningModelID";
    String ROLE_ID = "RoleID";
    String TRACE_ID = "TraceID";
    String ASSEMBLY_ID = "AssemblyID";
    String DATA_SOURCE_ID = "DataSourceID";
    String DATA_SOURCE_VIEW_ID = "DataSourceViewID";
    String DATA_SOURCE_PERMISSION_ID = "DataSourcePermissionID";
    String DATABASE_PERMISSION_ID = "DatabasePermissionID";
    String DIMENSION_PERMISSION_ID = "DimensionPermissionID";
    String CUBE_PERMISSION_ID = "CubePermissionID";
    String MINING_MODEL_PERMISSION_ID = "MiningModelPermissionID";
    String MINING_STRUCTURE_PERMISSION_ID = "MiningStructurePermissionID";
    String MDX_SCRIPT_ID = "MdxScriptID";
    String PERSPECTIVE_ID = "PerspectiveID";
    String AGGREGATION_DESIGN_ID = "AggregationDesignID";

    String CONNECTION_ID = "ConnectionID";
    String SESSION_ID = "SessionID";
    String SPID = "SPID";
    String CANCEL_ASSOCIATED = "CancelAssociated";

    String OBJECT_DEFINITION = "ObjectDefinition";
    String ALLOW_CREATE = "AllowCreate";
    String OBJECT_EXPANSION = "ObjectExpansion";

    String ID = "ID";
    String NAME = "Name";
    String DESCRIPTION = "Description";
    String CAPTION = "Caption";
    String LANGUAGE = "Language";
    String COLLATION = "Collation";
    String VALUE = "Value";
    String VISIBLE = "Visible";
    String STATE = "State";
    String SOURCE = "Source";
    String TARGET = "Target";
    String TARGET_TYPE = "TargetType";
    String EXPRESSION = "Expression";
    String ORDINAL = "Ordinal";
    String TYPE = "TYPE";
    String VERSION = "VERSION";
    String WRITE = "Write";

    String DIMENSION = "Dimension";
    String DIMENSIONS = "Dimensions";
    String CUBE_DIMENSION_ID = "CubeDimensionID";
    String HIERARCHY = "Hierarchy";
    String HIERARCHIES = "Hierarchies";
    String ATTRIBUTE = "Attribute";
    String ATTRIBUTES = "Attributes";
    String ATTRIBUTE_ID = "AttributeID";
    String MEASURE = "Measure";
    String MEASURES = "Measures";
    String MEASURE_ID = "MeasureID";
    String MEASURE_GROUP = "MeasureGroup";
    String ACTION = "Action";

    String PROCESS = "Process";
    String PROCESSING_MODE = "ProcessingMode";
    String PROCESSING_PRIORITY = "ProcessingPriority";
    String PROCESSING_STATE = "ProcessingState";

    String STORAGE_MODE = "StorageMode";
    String STORAGE_LOCATION = "StorageLocation";
    String PROACTIVE_CACHING = "ProactiveCaching";

    String CREATED_TIMESTAMP = "CreatedTimestamp";
    String LAST_SCHEMA_UPDATE = "LastSchemaUpdate";
    String LAST_PROCESSED = "LastProcessed";
    String ESTIMATED_ROWS = "EstimatedRows";
    String ESTIMATED_SIZE = "EstimatedSize";
    String ANNOTATIONS = "Annotations";
    String TRANSLATIONS = "Translations";
    String TRANSLATION = "Translation";

    String DISPLAY_FOLDER = "DisplayFolder";
    String FOLDER_POSITION = "FolderPosition";
    String DEFAULT_DETAILS_POSITION = "DefaultDetailsPosition";
    String SORT_PROPERTIES_POSITION = "SortPropertiesPosition";
    String VISUALIZATION_PROPERTIES = "VisualizationProperties";

    String KEY_COLUMNS = "KeyColumns";
    String KEY_COLUMN = "KeyColumn";
    String COLUMNS = "Columns";

    String TABLE_ID = "TableID";
    String TABLE_CATALOG = "TABLE_CATALOG";
    String TABLE_SCHEMA = "TABLE_SCHEMA";
    String TABLE_NAME = "TABLE_NAME";
    String TABLE_TYPE = "TABLE_TYPE";

    String DATA_SOURCE_NAME = "DataSourceName";
    String DATA_SOURCE_DESCRIPTION = "DataSourceDescription";
    String DATA_SOURCE_INFO = "DataSourceInfo";
    String URL = "URL";
    String PROVIDER_NAME = "ProviderName";
    String PROVIDER_TYPE = "ProviderType";
    String AUTHENTICATION_MODE = "AuthenticationMode";

    String FILTER = "Filter";
    String CONDITION = "Condition";
    String EQUAL = "Equal";
    String NOT_EQUAL = "NotEqual";
    String LESS_OR_EQUAL = "LessOrEqual";
    String GREATER_OR_EQUAL = "GreaterOrEqual";
    String GREATER = "Greater";
    String NOT_LIKE = "NotLike";

    String AGGREGATION_PREFIX = "AggregationPrefix";
    String ALLOW_DRILL_THROUGH = "AllowDrillThrough";

    String RESTRICTION_LIST = "RestrictionList";
    String REFRESH_INTERVAL = "RefreshInterval";
    String READ_DEFINITION = "ReadDefinition";
    String DEFAULT_MEMBER = "DefaultMember";
    String CONTEXTUAL_NAME_RULE = "ContextualNameRule";
    String CAPTION_IS_MDX = "CaptionIsMdx";
    String ATTRIBUTE_HIERARCHY_VISIBLE = "AttributeHierarchyVisible";
    String APPLICATION = "Application";
    String ERROR_CONFIGURATION = "ErrorConfiguration";
    String INVOCATION_LOW = "Invocation";
    String XSI_TYPE = "xsi:type";
    String VALUENS = "valuens";
    String EVENT_ID = "EventID";

    String CATALOG_NAME = "CATALOG_NAME";
    String SCHEMA_NAME_LOW = "SchemaName";
    String SCHEMA_OWNER = "SCHEMA_OWNER";
    String CUBE_SOURCE = "CUBE_SOURCE";
    String CUBE_TYPE = "CUBE_TYPE";
    String BASE_CUBE_NAME = "BASE_CUBE_NAME";
    String DIMENSION_NAME = "DIMENSION_NAME";
    String DIMENSION_UNIQUE_NAME = "DIMENSION_UNIQUE_NAME";
    String DIMENSION_VISIBILITY = "DIMENSION_VISIBILITY";
    String HIERARCHY_NAME = "HIERARCHY_NAME";
    String HIERARCHY_UNIQUE_NAME = "HIERARCHY_UNIQUE_NAME";
    String HIERARCHY_ORIGIN = "HIERARCHY_ORIGIN";
    String HIERARCHY_VISIBILITY = "HIERARCHY_VISIBILITY";
    String LEVEL_NAME = "LEVEL_NAME";
    String LEVEL_UNIQUE_NAME = "LEVEL_UNIQUE_NAME";
    String LEVEL_NUMBER = "LEVEL_NUMBER";
    String MEASURE_NAME = "MEASURE_NAME";
    String MEASURE_UNIQUE_NAME = "MEASURE_UNIQUE_NAME";
    String MEASURE_VISIBILITY = "MEASURE_VISIBILITY";
    String MEASUREGROUP_NAME = "MEASUREGROUP_NAME";
    String MEMBER_NAME = "MEMBER_NAME";
    String MEMBER_UNIQUE_NAME = "MEMBER_UNIQUE_NAME";
    String MEMBER_TYPE = "MEMBER_TYPE";
    String MEMBER_CAPTION = "MEMBER_CAPTION";
    String TREE_OP = "TREE_OP";
    String PROPERTY_NAME = "PropertyName";
    String PROPERTY_NAME2 = "PROPERTY_NAME";
    String PROPERTY_TYPE = "PROPERTY_TYPE";
    String PROPERTY_CONTENT_TYPE = "PROPERTY_CONTENT_TYPE";
    String PROPERTY_ORIGIN = "PROPERTY_ORIGIN";
    String PROPERTY_VISIBILITY = "PROPERTY_VISIBILITY";
    String SET_NAME = "SET_NAME";
    String SET_CAPTION = "SET_CAPTION";
    String SCOPE = "SCOPE";
    String KPI_NAME = "KPI_NAME";
    String ACTION_NAME = "ACTION_NAME";
    String ACTION_TYPE = "ACTION_TYPE";
    String COORDINATE = "COORDINATE";
    String COORDINATE_TYPE = "COORDINATE_TYPE";
    String INVOCATION = "INVOCATION";
    String FUNCTION_NAME = "FUNCTION_NAME";
    String ORIGIN = "ORIGIN";
    String INTERFACE_NAME = "INTERFACE_NAME";
    String LIBRARY_NAME = "LIBRARY_NAME";
    String ENUM_NAME = "EnumName";
    String KEYWORD = "Keyword";
    String LITERAL_NAME = "LiteralName";
    String PERSPECTIVE_NAME = "PERSPECTIVE_NAME";
    String DATA_TYPE = "DATA_TYPE";
    String BEST_MATCH = "BEST_MATCH";
    String COLUMN_NAME = "COLUMN_NAME";
    String COLUMN_OLAP_TYPE = "COLUMN_OLAP_TYPE";
    String OBJECT_TYPE = "ObjectType";

    String OBJECT2 = "Object";
    String SCOPE2 = "Scope";
    String ROLE2 = "Role";
    String ASSEMBLY2 = "Assembly";
    String CUBE2 = "Cube";
}
