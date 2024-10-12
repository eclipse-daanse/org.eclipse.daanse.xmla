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
package org.eclipse.daanse.xmla.client.soapmessage;

public class Responses {

    public static String DATA_SOURCES = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <DataSourceName>FoodMart</DataSourceName>
                            <DataSourceDescription>Foodmart 2000 on local machine</DataSourceDescription>
                            <URL>http://localhost/xmla/msxisapi.dll</URL>
                            <DataSourceInfo>Foodmart 2000</DataSourceInfo>
                            <ProviderName>Microsoft XML for Analysis</ProviderName>
                            <ProviderType>TDP</ProviderType>
                            <AuthenticationMode>Unauthenticated</AuthenticationMode>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;
    public static String ENUMERARORS = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <EnumName>EnumName</EnumName>
                            <EnumDescription>EnumDescription</EnumDescription>
                            <EnumType>EnumType</EnumType>
                            <ElementName>ElementName</ElementName>
                            <ElementDescription>ElementDescription</ElementDescription>
                            <ElementValue>ElementValue</ElementValue>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String KEYWORDS = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <Keyword>Keyword</Keyword>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String LITERALS = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <LiteralName>LiteralName</LiteralName>
                            <LiteralValue>LiteralValue</LiteralValue>
                            <LiteralInvalidChars>LiteralInvalidChars</LiteralInvalidChars>
                            <LiteralInvalidStartingChars>LiteralInvalidStartingChars</LiteralInvalidStartingChars>
                            <LiteralMaxLength>100</LiteralMaxLength>
                            <LiteralNameValue>1</LiteralNameValue>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String PROPERTIES = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <PropertyName>PropertyName</PropertyName>
                            <PropertyDescription>PropertyDescription</PropertyDescription>
                            <PropertyType>PropertyType</PropertyType>
                            <PropertyAccessType>Read</PropertyAccessType>
                            <IsRequired>true</IsRequired>
                            <Value>Value</Value>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String SCHEMAROWSETS = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <SchemaName>SchemaName</SchemaName>
                            <SchemaGuid>SchemaGuid</SchemaGuid>
                            <Restrictions>
                                    <Name>TABLE_SCHEMA</Name>
                                    <Type>xsd:string</Type>
                            </Restrictions>
                            <Description>Description</Description>
                            <RestrictionsMask>100</RestrictionsMask>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String XML_META_DATA = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <MetaData>MetaData</MetaData>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String CATALOGS = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <CATALOG_NAME>CatalogName</CATALOG_NAME>
                            <DESCRIPTION>Description</DESCRIPTION>
                            <ROLES>Roles</ROLES>
                            <DATE_MODIFIED>2023-01-10T10:45:00</DATE_MODIFIED>
                            <COMPATIBILITY_LEVEL>10</COMPATIBILITY_LEVEL>
                            <TYPE>0x00</TYPE>
                            <VERSION>10</VERSION>
                            <DATABASE_ID>DatabaseId</DATABASE_ID>
                            <DATE_QUERIED>2024-01-10T10:45:00</DATE_QUERIED>
                            <CURRENTLY_USED>true</CURRENTLY_USED>
                            <POPULARITY>2.25</POPULARITY>
                            <WEIGHTEDPOPULARITY>2.26</WEIGHTEDPOPULARITY>
                            <CLIENTCACHEREFRESHPOLICY>0</CLIENTCACHEREFRESHPOLICY>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String COLUMNS = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">

                            <TABLE_CATALOG>TableCatalog</TABLE_CATALOG>
                            <TABLE_SCHEMA>TableSchema</TABLE_SCHEMA>
                            <TABLE_NAME>TableName</TABLE_NAME>
                            <COLUMN_NAME>ColumnName</COLUMN_NAME>
                            <COLUMN_GUID>10</COLUMN_GUID>
                            <COLUMN_PROPID>11</COLUMN_PROPID>
                            <ORDINAL_POSITION>12</ORDINAL_POSITION>
                            <COLUMN_HAS_DEFAULT>true</COLUMN_HAS_DEFAULT>
                            <COLUMN_DEFAULT>ColumnDefault</COLUMN_DEFAULT>
                            <COLUMN_FLAG>0x1</COLUMN_FLAG>
                            <IS_NULLABLE>true</IS_NULLABLE>
                            <DATA_TYPE>10</DATA_TYPE>
                            <TYPE_GUID>11</TYPE_GUID>
                            <CHARACTER_MAXIMUM_LENGTH>12</CHARACTER_MAXIMUM_LENGTH>
                            <CHARACTER_OCTET_LENGTH>14</CHARACTER_OCTET_LENGTH>
                            <NUMERIC_PRECISION>15</NUMERIC_PRECISION>
                            <NUMERIC_SCALE>16</NUMERIC_SCALE>
                            <DATETIME_PRECISION>17</DATETIME_PRECISION>
                            <CHARACTER_SET_CATALOG>CharacterSetCatalog</CHARACTER_SET_CATALOG>
                            <CHARACTER_SET_SCHEMA>CharacterSetSchema</CHARACTER_SET_SCHEMA>
                            <CHARACTER_SET_NAME>CharacterSetName</CHARACTER_SET_NAME>
                            <COLLATION_CATALOG>CollationCatalog</COLLATION_CATALOG>
                            <COLLATION_SCHEMA>CollationSchema</COLLATION_SCHEMA>
                            <COLLATION_NAME>CollationName</COLLATION_NAME>
                            <DOMAIN_CATALOG>DomainCatalog</DOMAIN_CATALOG>
                            <DOMAIN_SCHEMA>DomainSchema</DOMAIN_SCHEMA>
                            <DOMAIN_NAME>DomainName</DOMAIN_NAME>
                            <DESCRIPTION>Description</DESCRIPTION>
                            <COLUMN_OLAP_TYPE>ATTRIBUTE</COLUMN_OLAP_TYPE>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String PROVIDER_TYPES = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">

                            <TYPE_NAME>TypeName</TYPE_NAME>
                            <DATA_TYPE>0</DATA_TYPE>
                            <COLUMN_SIZE>10</COLUMN_SIZE>
                            <LITERAL_PREFIX>LiteralPrefix</LITERAL_PREFIX>
                            <LITERAL_SUFFIX>LiteralSuffix</LITERAL_SUFFIX>
                            <CREATE_PARAMS>CreateParams</CREATE_PARAMS>
                            <IS_NULLABLE>true</IS_NULLABLE>
                            <CASE_SENSITIVE>true</CASE_SENSITIVE>
                            <SEARCHABLE>0x01</SEARCHABLE>
                            <UNSIGNED_ATTRIBUTE>true</UNSIGNED_ATTRIBUTE>
                            <FIXED_PREC_SCALE>true</FIXED_PREC_SCALE>
                            <AUTO_UNIQUE_VALUE>true</AUTO_UNIQUE_VALUE>
                            <LOCAL_TYPE_NAME>LocalTypeName</LOCAL_TYPE_NAME>
                            <MINIMUM_SCALE>10</MINIMUM_SCALE>
                            <MAXIMUM_SCALE>11</MAXIMUM_SCALE>
                            <GUID>12</GUID>
                            <TYPE_LIB>TypeLib</TYPE_LIB>
                            <VERSION>Version</VERSION>
                            <IS_LONG>true</IS_LONG>
                            <BEST_MATCH>true</BEST_MATCH>
                            <IS_FIXEDLENGTH>true</IS_FIXEDLENGTH>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String SCHEMATA = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <CATALOG_NAME>CatalogName</CATALOG_NAME>
                            <SCHEMA_NAME>SchemaName</SCHEMA_NAME>
                            <SCHEMA_OWNER>SchemaOwner</SCHEMA_OWNER>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String SOURCE_TABLES = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <TABLE_CATALOG>CatalogName</TABLE_CATALOG>
                            <TABLE_SCHEMA>SchemaName</TABLE_SCHEMA>
                            <TABLE_NAME>TableName</TABLE_NAME>
                            <TABLE_TYPE>TABLE</TABLE_TYPE>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String TABLES = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <TABLE_CATALOG>TableCatalog</TABLE_CATALOG>
                            <TABLE_SCHEMA>TableSchema</TABLE_SCHEMA>
                            <TABLE_NAME>TableName</TABLE_NAME>
                            <TABLE_TYPE>TableType</TABLE_TYPE>
                            <TABLE_GUID>TableGuid</TABLE_GUID>
                            <DESCRIPTION>Description</DESCRIPTION>
                            <TABLE_PROP_ID>10</TABLE_PROP_ID>
                            <DATE_CREATED>2023-01-10T10:45:00</DATE_CREATED>
                            <DATE_MODIFIED>2024-01-10T10:45:00</DATE_MODIFIED>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String TABLES_INFO = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <TABLE_CATALOG>CatalogName</TABLE_CATALOG>
                            <TABLE_SCHEMA>SchemaName</TABLE_SCHEMA>
                            <TABLE_NAME>TableName</TABLE_NAME>
                            <TABLE_TYPE>TableType</TABLE_TYPE>
                            <TABLE_GUID>10</TABLE_GUID>
                            <BOOKMARKS>true</BOOKMARKS>
                            <BOOKMARK_TYPE>10</BOOKMARK_TYPE>
                            <BOOKMARK_DATA_TYPE>11</BOOKMARK_DATA_TYPE>
                            <BOOKMARK_MAXIMUM_LENGTH>12</BOOKMARK_MAXIMUM_LENGTH>
                            <BOOKMARK_INFORMATION>14</BOOKMARK_INFORMATION>
                            <TABLE_VERSION>15</TABLE_VERSION>
                            <CARDINALITY>16</CARDINALITY>
                            <DESCRIPTION>Description</DESCRIPTION>
                            <TABLE_PROP_ID>17</TABLE_PROP_ID>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String ACTIONS = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <CATALOG_NAME>CatalogName</CATALOG_NAME>
                            <SCHEMA_NAME>SchemaName</SCHEMA_NAME>
                            <CUBE_NAME>CubeName</CUBE_NAME>
                            <ACTION_NAME>ActionName</ACTION_NAME>
                            <ACTION_TYPE>0x01</ACTION_TYPE>
                            <COORDINATE>Coordinate</COORDINATE>
                            <COORDINATE_TYPE>1</COORDINATE_TYPE>
                            <ACTION_CAPTION>ActionCaption</ACTION_CAPTION>
                            <DESCRIPTION>Description</DESCRIPTION>
                            <CONTENT>Content</CONTENT>
                            <APPLICATION>Application</APPLICATION>
                            <INVOCATION>1</INVOCATION>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String CUBES = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <CATALOG_NAME>CatalogName</CATALOG_NAME>
                            <SCHEMA_NAME>SchemaName</SCHEMA_NAME>
                            <CUBE_NAME>CubeName</CUBE_NAME>
                            <CUBE_TYPE>CUBE</CUBE_TYPE>
                            <CUBE_GUID>10</CUBE_GUID>
                            <CREATED_ON>10</CREATED_ON>
                            <CREATED_ON>2023-01-10T10:45:00</CREATED_ON>
                            <LAST_SCHEMA_UPDATE>2024-01-10T10:45:00</LAST_SCHEMA_UPDATE>
                            <SCHEMA_UPDATED_BY>SchemaUpdatedBy</SCHEMA_UPDATED_BY>
                            <LAST_DATA_UPDATE>2025-01-10T10:45:00</LAST_DATA_UPDATE>
                            <DATA_UPDATED_BY>DataUpdatedBy</DATA_UPDATED_BY>
                            <DESCRIPTION>Description</DESCRIPTION>
                            <IS_DRILLTHROUGH_ENABLED>true</IS_DRILLTHROUGH_ENABLED>
                            <IS_LINKABLE>true</IS_LINKABLE>
                            <IS_WRITE_ENABLED>true</IS_WRITE_ENABLED>
                            <IS_SQL_ENABLED>true</IS_SQL_ENABLED>
                            <CUBE_CAPTION>CubeCaption</CUBE_CAPTION>
                            <BASE_CUBE_NAME>BaseCubeName</BASE_CUBE_NAME>
                            <CUBE_SOURCE>1</CUBE_SOURCE>
                            <PREFERRED_QUERY_PATTERNS>0</PREFERRED_QUERY_PATTERNS>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String DIMENSIONS = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <CATALOG_NAME>CatalogName</CATALOG_NAME>
                            <SCHEMA_NAME>SchemaName</SCHEMA_NAME>
                            <CUBE_NAME>CubeName</CUBE_NAME>
                            <DIMENSION_NAME>DimensionName</DIMENSION_NAME>
                            <DIMENSION_UNIQUE_NAME>DimensionUniqueName</DIMENSION_UNIQUE_NAME>
                            <DIMENSION_GUID>10</DIMENSION_GUID>
                            <DIMENSION_CAPTION>DimensionCaption</DIMENSION_CAPTION>
                            <DIMENSION_ORDINAL>11</DIMENSION_ORDINAL>
                            <DIMENSION_TYPE>0</DIMENSION_TYPE>
                            <DIMENSION_CARDINALITY>12</DIMENSION_CARDINALITY>
                            <DEFAULT_HIERARCHY>DefaultHierarchy</DEFAULT_HIERARCHY>
                            <DESCRIPTION>Description</DESCRIPTION>
                            <IS_VIRTUAL>true</IS_VIRTUAL>
                            <IS_READWRITE>true</IS_READWRITE>
                            <DIMENSION_UNIQUE_SETTINGS>1</DIMENSION_UNIQUE_SETTINGS>
                            <DIMENSION_MASTER_NAME>DimensionMasterName</DIMENSION_MASTER_NAME>
                            <DIMENSION_IS_VISIBLE>true</DIMENSION_IS_VISIBLE>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String FUNCTIONS = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <FUNCTION_NAME>FunctionalName</FUNCTION_NAME>
                            <DESCRIPTION>Description</DESCRIPTION>
                            <PARAMETER_LIST>ParameterList</PARAMETER_LIST>
                            <RETURN_TYPE>10</RETURN_TYPE>
                            <ORIGIN>1</ORIGIN>
                            <INTERFACE_NAME>FILTER</INTERFACE_NAME>
                            <LIBRARY_NAME>LibraryName</LIBRARY_NAME>
                            <DLL_NAME>DllName</DLL_NAME>
                            <HELP_FILE>HelpFile</HELP_FILE>
                            <HELP_CONTEXT>HelpContext</HELP_CONTEXT>
                            <OBJECT>Object</OBJECT>
                            <CAPTION>Caption</CAPTION>
                            <PARAMETERINFO>
                                    <NAME>name</NAME>
                                    <DESCRIPTION>description</DESCRIPTION>
                                    <OPTIONAL>true</OPTIONAL>
                                    <REPEATABLE>true</REPEATABLE>
                                    <REPEATGROUP>1</REPEATGROUP>
                            </PARAMETERINFO>
                            <PARAMETERINFO>
                                    <NAME>name1</NAME>
                                    <DESCRIPTION>description1</DESCRIPTION>
                                    <OPTIONAL>true</OPTIONAL>
                                    <REPEATABLE>true</REPEATABLE>
                                    <REPEATGROUP>1</REPEATGROUP>
                            </PARAMETERINFO>
                            <DIRECTQUERY_PUSHABLE>1</DIRECTQUERY_PUSHABLE>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String HIERARCHIES = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                    <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <CATALOG_NAME>CatalogName</CATALOG_NAME>
                            <SCHEMA_NAME>SchemaName</SCHEMA_NAME>
                            <CUBE_NAME>CubeName</CUBE_NAME>
                            <DIMENSION_UNIQUE_NAME>DimensionUniqueName</DIMENSION_UNIQUE_NAME>
                            <HIERARCHY_NAME>HierarchyName</HIERARCHY_NAME>
                            <HIERARCHY_UNIQUE_NAME>HierarchyUniqueName</HIERARCHY_UNIQUE_NAME>
                            <HIERARCHY_GUID>10</HIERARCHY_GUID>
                            <HIERARCHY_CAPTION>HierarchyCaption</HIERARCHY_CAPTION>
                            <DIMENSION_TYPE>0</DIMENSION_TYPE>
                            <HIERARCHY_CARDINALITY>11</HIERARCHY_CARDINALITY>
                            <DEFAULT_MEMBER>DefaultMember</DEFAULT_MEMBER>
                            <ALL_MEMBER>AllMember</ALL_MEMBER>
                            <DESCRIPTION>Description</DESCRIPTION>
                            <STRUCTURE>0</STRUCTURE>
                            <IS_VIRTUAL>true</IS_VIRTUAL>
                            <IS_READWRITE>true</IS_READWRITE>
                            <DIMENSION_UNIQUE_SETTINGS>1</DIMENSION_UNIQUE_SETTINGS>
                            <DIMENSION_MASTER_UNIQUE_NAME>dimensionMasterUniqueName</DIMENSION_MASTER_UNIQUE_NAME>
                            <DIMENSION_IS_VISIBLE>true</DIMENSION_IS_VISIBLE>
                            <HIERARCHY_ORDINAL>12</HIERARCHY_ORDINAL>
                            <DIMENSION_IS_SHARED>true</DIMENSION_IS_SHARED>
                            <HIERARCHY_IS_VISIBLE>true</HIERARCHY_IS_VISIBLE>
                            <HIERARCHY_ORIGIN>1</HIERARCHY_ORIGIN>
                            <HIERARCHY_DISPLAY_FOLDER>HierarchyDisplayFolder</HIERARCHY_DISPLAY_FOLDER>
                            <INSTANCE_SELECTION>1</INSTANCE_SELECTION>
                            <GROUPING_BEHAVIOR>1</GROUPING_BEHAVIOR>
                            <STRUCTURE_TYPE>Natural</STRUCTURE_TYPE>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String KPIS = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <CATALOG_NAME>CatalogName</CATALOG_NAME>
                            <SCHEMA_NAME>SchemaName</SCHEMA_NAME>
                            <CUBE_NAME>CubeName</CUBE_NAME>
                            <MEASUREGROUP_NAME>MeasureGroupName</MEASUREGROUP_NAME>
                            <KPI_NAME>KpiName</KPI_NAME>
                            <KPI_CAPTION>KpiCaption</KPI_CAPTION>
                            <KPI_DESCRIPTION>KpiDescription</KPI_DESCRIPTION>
                            <KPI_DISPLAY_FOLDER>KpiDisplayFolder</KPI_DISPLAY_FOLDER>
                            <KPI_VALUE>KpiValue</KPI_VALUE>
                            <KPI_GOAL>KpiGoal</KPI_GOAL>
                            <KPI_STATUS>KpiStatus</KPI_STATUS>
                            <KPI_TREND>KpiTrend</KPI_TREND>
                            <KPI_STATUS_GRAPHIC>KpiStatusGraphic</KPI_STATUS_GRAPHIC>
                            <KPI_TREND_GRAPHIC>KpiTrendGraphic</KPI_TREND_GRAPHIC>
                            <KPI_WEIGHT>KpiWight</KPI_WEIGHT>
                            <KPI_CURRENT_TIME_MEMBER>KpiCurrentTimeMember</KPI_CURRENT_TIME_MEMBER>
                            <KPI_PARENT_KPI_NAME>KpiParentKpiName</KPI_PARENT_KPI_NAME>
                            <ANNOTATIONS>Annotation</ANNOTATIONS>
                            <SCOPE>1</SCOPE>

                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String LEVELS = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <CATALOG_NAME>CatalogName</CATALOG_NAME>
                            <SCHEMA_NAME>SchemaName</SCHEMA_NAME>
                            <CUBE_NAME>CubeName</CUBE_NAME>
                            <DIMENSION_UNIQUE_NAME>DimensionUniqueName</DIMENSION_UNIQUE_NAME>
                            <HIERARCHY_UNIQUE_NAME>HierarchyUniqueName</HIERARCHY_UNIQUE_NAME>
                            <LEVEL_NAME>LevelName</LEVEL_NAME>
                            <LEVEL_UNIQUE_NAME>LevelUniqueName</LEVEL_UNIQUE_NAME>
                            <LEVEL_GUID>10</LEVEL_GUID>
                            <LEVEL_CAPTION>LevelCaption</LEVEL_CAPTION>
                            <LEVEL_NUMBER>11</LEVEL_NUMBER>
                            <LEVEL_CARDINALITY>12</LEVEL_CARDINALITY>
                            <LEVEL_TYPE>1</LEVEL_TYPE>
                            <DESCRIPTION>Description</DESCRIPTION>
                            <CUSTOM_ROLLUP_SETTINGS>1</CUSTOM_ROLLUP_SETTINGS>
                            <LEVEL_UNIQUE_SETTINGS>1</LEVEL_UNIQUE_SETTINGS>
                            <LEVEL_IS_VISIBLE>true</LEVEL_IS_VISIBLE>
                            <LEVEL_ORDERING_PROPERTY>LevelOrderingProperty</LEVEL_ORDERING_PROPERTY>
                            <LEVEL_DBTYPE>0</LEVEL_DBTYPE>
                            <LEVEL_MASTER_UNIQUE_NAME>LevelMasterUniqueName</LEVEL_MASTER_UNIQUE_NAME>
                            <LEVEL_NAME_SQL_COLUMN_NAME>LevelNameSqlColumnName</LEVEL_NAME_SQL_COLUMN_NAME>
                            <LEVEL_KEY_SQL_COLUMN_NAME>LevelKeySqlColumnName</LEVEL_KEY_SQL_COLUMN_NAME>
                            <LEVEL_UNIQUE_NAME_SQL_COLUMN_NAME>LevelUniqueNameSqlColumnName</LEVEL_UNIQUE_NAME_SQL_COLUMN_NAME>
                            <LEVEL_ATTRIBUTE_HIERARCHY_NAME>LevelAttributeHierarchyName</LEVEL_ATTRIBUTE_HIERARCHY_NAME>
                            <LEVEL_KEY_CARDINALITY>14</LEVEL_KEY_CARDINALITY>
                            <LEVEL_ORIGIN>1</LEVEL_ORIGIN>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;
    public static String MEASURE_GROUP_DIMENSIONS = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <CATALOG_NAME>CatalogName</CATALOG_NAME>
                            <SCHEMA_NAME>SchemaName</SCHEMA_NAME>
                            <CUBE_NAME>CubeName</CUBE_NAME>
                            <MEASUREGROUP_NAME>MeasureGroupName</MEASUREGROUP_NAME>
                            <MEASUREGROUP_CARDINALITY>MeasureGroupCardinality</MEASUREGROUP_CARDINALITY>
                            <DIMENSION_UNIQUE_NAME>DimensionUniqueName</DIMENSION_UNIQUE_NAME>
                            <DIMENSION_CARDINALITY>ONE</DIMENSION_CARDINALITY>
                            <DIMENSION_IS_VISIBLE>true</DIMENSION_IS_VISIBLE>
                            <DIMENSION_IS_FACT_DIMENSION>true</DIMENSION_IS_FACT_DIMENSION>
                            <DIMENSION_PATH>
                                    <MeasureGroupDimension>MeasureGroupDimension</MeasureGroupDimension>
                                    <MeasureGroupDimension>MeasureGroupDimension1</MeasureGroupDimension>
                            </DIMENSION_PATH>
                            <DIMENSION_GRANULARITY>DimensionGranularity</DIMENSION_GRANULARITY>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String MEASURE_GROUPS = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <CATALOG_NAME>CatalogName</CATALOG_NAME>
                            <SCHEMA_NAME>SchemaName</SCHEMA_NAME>
                            <CUBE_NAME>CubeName</CUBE_NAME>
                            <MEASUREGROUP_NAME>MeasureGroupName</MEASUREGROUP_NAME>
                            <DESCRIPTION>Description</DESCRIPTION>
                            <IS_WRITE_ENABLED>true</IS_WRITE_ENABLED>
                            <MEASUREGROUP_CAPTION>MeasureGroupCaption</MEASUREGROUP_CAPTION>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String MEASURES = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <CATALOG_NAME>CatalogName</CATALOG_NAME>
                            <SCHEMA_NAME>SchemaName</SCHEMA_NAME>
                            <CUBE_NAME>CubeName</CUBE_NAME>
                            <MEASURE_NAME>MeasureName</MEASURE_NAME>
                            <MEASURE_UNIQUE_NAME>MeasureUniqueName</MEASURE_UNIQUE_NAME>
                            <MEASURE_CAPTION>MeasureCaption</MEASURE_CAPTION>
                            <MEASURE_GUID>10</MEASURE_GUID>
                            <MEASURE_AGGREGATOR>1</MEASURE_AGGREGATOR>
                            <DATA_TYPE>0</DATA_TYPE>
                            <NUMERIC_PRECISION>11</NUMERIC_PRECISION>
                            <NUMERIC_SCALE>12</NUMERIC_SCALE>
                            <MEASURE_UNITS>MeasureUnits</MEASURE_UNITS>
                            <DESCRIPTION>Description</DESCRIPTION>
                            <EXPRESSION>Expression</EXPRESSION>
                            <MEASURE_IS_VISIBLE>true</MEASURE_IS_VISIBLE>
                            <LEVELS_LIST>LevelsList</LEVELS_LIST>
                            <MEASURE_NAME_SQL_COLUMN_NAME>MeasureNameSqlColumnName</MEASURE_NAME_SQL_COLUMN_NAME>
                            <MEASURE_UNQUALIFIED_CAPTION>MeasureUnqualifiedCaption</MEASURE_UNQUALIFIED_CAPTION>
                            <MEASUREGROUP_NAME>MeasureGroupName</MEASUREGROUP_NAME>
                            <DEFAULT_FORMAT_STRING>DefaultFormatString</DEFAULT_FORMAT_STRING>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String MEMBERS = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <CATALOG_NAME>CatalogName</CATALOG_NAME>
                            <SCHEMA_NAME>SchemaName</SCHEMA_NAME>
                            <CUBE_NAME>CubeName</CUBE_NAME>
                            <DIMENSION_UNIQUE_NAME>DimensionUniqueName</DIMENSION_UNIQUE_NAME>
                            <HIERARCHY_UNIQUE_NAME>HierarchyUniqueName</HIERARCHY_UNIQUE_NAME>
                            <LEVEL_UNIQUE_NAME>LevelUniqueName</LEVEL_UNIQUE_NAME>
                            <LEVEL_NUMBER>10</LEVEL_NUMBER>
                            <MEMBER_ORDINAL>11</MEMBER_ORDINAL>
                            <MEMBER_NAME>MemberName</MEMBER_NAME>
                            <MEMBER_UNIQUE_NAME>MemberUniqueName</MEMBER_UNIQUE_NAME>
                            <MEMBER_TYPE>1</MEMBER_TYPE>
                            <MEMBER_GUID>12</MEMBER_GUID>
                            <MEMBER_CAPTION>MemberCaption</MEMBER_CAPTION>
                            <CHILDREN_CARDINALITY>14</CHILDREN_CARDINALITY>
                            <PARENT_LEVEL>15</PARENT_LEVEL>
                            <PARENT_UNIQUE_NAME>ParentUniqueName</PARENT_UNIQUE_NAME>
                            <PARENT_COUNT>16</PARENT_COUNT>
                            <DESCRIPTION>Description</DESCRIPTION>
                            <EXPRESSION>Expression</EXPRESSION>
                            <MEMBER_KEY>MemberKey</MEMBER_KEY>
                            <IS_PLACEHOLDERMEMBER>true</IS_PLACEHOLDERMEMBER>
                            <IS_DATAMEMBER>true</IS_DATAMEMBER>
                            <SCOPE>1</SCOPE>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String MD_PROPERTIES = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <CATALOG_NAME>CatalogName</CATALOG_NAME>
                            <SCHEMA_NAME>SchemaName</SCHEMA_NAME>
                            <CUBE_NAME>CubeName</CUBE_NAME>
                            <DIMENSION_UNIQUE_NAME>DimensionUniqueName</DIMENSION_UNIQUE_NAME>
                            <HIERARCHY_UNIQUE_NAME>HierarchyUniqueName</HIERARCHY_UNIQUE_NAME>
                            <LEVEL_UNIQUE_NAME>LevelUniqueName</LEVEL_UNIQUE_NAME>
                            <MEMBER_UNIQUE_NAME>MemberUniqueName</MEMBER_UNIQUE_NAME>
                            <PROPERTY_TYPE>1</PROPERTY_TYPE>
                            <PROPERTY_NAME>PropertyName</PROPERTY_NAME>
                            <PROPERTY_CAPTION>PropertyCaption</PROPERTY_CAPTION>
                            <DATA_TYPE>0</DATA_TYPE>
                            <CHARACTER_MAXIMUM_LENGTH>10</CHARACTER_MAXIMUM_LENGTH>
                            <CHARACTER_OCTET_LENGTH>11</CHARACTER_OCTET_LENGTH>
                            <NUMERIC_PRECISION>12</NUMERIC_PRECISION>
                            <NUMERIC_SCALE>14</NUMERIC_SCALE>
                            <DESCRIPTION>Description</DESCRIPTION>
                            <PROPERTY_CONTENT_TYPE>0xB1</PROPERTY_CONTENT_TYPE>
                            <SQL_COLUMN_NAME>SqlColumnName</SQL_COLUMN_NAME>
                            <LANGUAGE>15</LANGUAGE>
                            <PROPERTY_ORIGIN>1</PROPERTY_ORIGIN>
                            <PROPERTY_ATTRIBUTE_HIERARCHY_NAME>PropertyAttributeHierarchyName</PROPERTY_ATTRIBUTE_HIERARCHY_NAME>
                            <PROPERTY_CARDINALITY>ONE</PROPERTY_CARDINALITY>
                            <MIME_TYPE>MimeType</MIME_TYPE>
                            <PROPERTY_IS_VISIBLE>true</PROPERTY_IS_VISIBLE>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String SETS = """
            <DiscoverResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <row
                            xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                            <CATALOG_NAME>CatalogName</CATALOG_NAME>
                            <SCHEMA_NAME>SchemaName</SCHEMA_NAME>
                            <CUBE_NAME>CubeName</CUBE_NAME>
                            <SET_NAME>SetName</SET_NAME>
                            <SCOPE>1</SCOPE>
                            <DESCRIPTION>Description</DESCRIPTION>
                            <EXPRESSION>Expression</EXPRESSION>
                            <DIMENSIONS>Dimension</DIMENSIONS>
                            <SET_CAPTION>SetCaption</SET_CAPTION>
                            <SET_DISPLAY_FOLDER>SetDisplayFolder</SET_DISPLAY_FOLDER>
                            <SET_EVALUATION_CONTEXT>1</SET_EVALUATION_CONTEXT>
                        </row>
                    </root>
                </return>
            </DiscoverResponse>
            """;

    public static String CANCEL = """
            <ExecuteResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <Exception>
                        </Exception>
                        <Messages>
                            <Error Description="description" ErrorCode="1" HelpFile="helpFile" Source="source">
                                    <Location>
                                            <Start>
                                                    <Line>1</Line>
                                                    <Column>2</Column>
                                            </Start>
                                            <End>
                                                    <Line>3</Line>
                                                    <Column>4</Column>
                                            </End>
                                            <LineOffset>1</LineOffset>
                                            <TextLength>2</TextLength>
                                            <SourceObject>
                                                    <WarningColumn>
                                                            <Dimension>Dimension</Dimension>
                                                            <Attribute>Attribute</Attribute>
                                                    </WarningColumn>
                                                    <WarningMeasure>
                                                            <Cube>Cube</Cube>
                                                            <MeasureGroup>MeasureGroup</MeasureGroup>
                                                            <MeasureName>MeasureName</MeasureName>
                                                    </WarningMeasure>
                                            </SourceObject>
                                            <DependsOnObject>
                                                    <WarningColumn>
                                                            <Dimension>Dimension</Dimension>
                                                            <Attribute>Attribute</Attribute>
                                                    </WarningColumn>
                                                    <WarningMeasure>
                                                            <Cube>Cube</Cube>
                                                            <MeasureGroup>MeasureGroup</MeasureGroup>
                                                            <MeasureName>MeasureName</MeasureName>
                                                    </WarningMeasure>
                                            </DependsOnObject>
                                            <RowNumber>3</RowNumber>
                                    </Location>
                                    <Callstack>callstack</Callstack>
                            </Error>
                        </Messages>
                    </root>
                </return>
            </ExecuteResponse>
            """;

    public static String CLEAR_CACHE = """
            <ExecuteResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <Exception>
                        </Exception>
                        <Messages>
                            <Error Description="description" ErrorCode="1" HelpFile="helpFile" Source="source">
                                    <Location>
                                            <Start>
                                                    <Line>1</Line>
                                                    <Column>2</Column>
                                            </Start>
                                            <End>
                                                    <Line>3</Line>
                                                    <Column>4</Column>
                                            </End>
                                            <LineOffset>1</LineOffset>
                                            <TextLength>2</TextLength>
                                            <SourceObject>
                                                    <WarningColumn>
                                                            <Dimension>Dimension</Dimension>
                                                            <Attribute>Attribute</Attribute>
                                                    </WarningColumn>
                                                    <WarningMeasure>
                                                            <Cube>Cube</Cube>
                                                            <MeasureGroup>MeasureGroup</MeasureGroup>
                                                            <MeasureName>MeasureName</MeasureName>
                                                    </WarningMeasure>
                                            </SourceObject>
                                            <DependsOnObject>
                                                    <WarningColumn>
                                                            <Dimension>Dimension</Dimension>
                                                            <Attribute>Attribute</Attribute>
                                                    </WarningColumn>
                                                    <WarningMeasure>
                                                            <Cube>Cube</Cube>
                                                            <MeasureGroup>MeasureGroup</MeasureGroup>
                                                            <MeasureName>MeasureName</MeasureName>
                                                    </WarningMeasure>
                                            </DependsOnObject>
                                            <RowNumber>3</RowNumber>
                                    </Location>
                                    <Callstack>callstack</Callstack>
                            </Error>
                        </Messages>
                    </root>
                </return>
            </ExecuteResponse>
            """;

    public static String STATEMENT = """
            <ExecuteResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root
                        xmlns="urn:schemas-microsoft-com:xml-analysis:rowset"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                        xmlns:EX="urn:schemas-microsoft-com:xml-analysis:exception">
                        <OlapInfo>
                            <CubeInfo>
                                    <Cube>
                                            <CubeName>cubeName</CubeName>
                                            <LastDataUpdate>2024-01-10T10:45:00.00Z</LastDataUpdate>
                                            <LastSchemaUpdate>2024-01-10T10:45:00.00Z</LastSchemaUpdate>
                                    </Cube>
                            </CubeInfo>
                            <AxesInfo>
                                    <AxisInfo name="name">
                                            <HierarchyInfo name="name">
                                                    <tagName name="name" type="value">
                                                    </tagName>
                                            </HierarchyInfo>
                                    </AxisInfo>
                            </AxesInfo>
                            <CellInfo>
                                    <tagName name="name" type="value">
                                    </tagName>
                            </CellInfo>
                        </OlapInfo>
                        <Axes>
                            <Axis name="name">
                                    <Members Hierarchy="hierarchy">
                                            <Member Hierarchy="hierarchy">
                                            <tagName name="name" type="value">
                                            </tagName>
                                            </Member>
                                    </Members>
                            </Axis>
                        </Axes>
                        <CellData>
                            <Cell CellOrdinal="1">
                                    <Value>
                                            <Error Description="description" ErrorCode="1" />
                                    </Value>
                                    <tagName name="name" type="value">
                                    </tagName>
                            </Cell>
                        </CellData>
                        <Exception>
                        </Exception>
                        <Messages>
                        <Error Description="description" ErrorCode="1" HelpFile="helpFile" Source="source">
                            <Location>
                                    <Start>
                                            <Line>1</Line>
                                            <Column>2</Column>
                                    </Start>
                                    <End>
                                            <Line>3</Line>
                                            <Column>4</Column>
                                    </End>
                                    <LineOffset>1</LineOffset>
                                    <TextLength>2</TextLength>
                                    <SourceObject>
                                            <WarningColumn>
                                                    <Dimension>dimension</Dimension>
                                                    <Attribute>attribute</Attribute>
                                            </WarningColumn>
                                            <WarningMeasure>
                                                    <Cube>cube</Cube>
                                                    <MeasureGroup>measureGroup</MeasureGroup>
                                                    <MeasureName>measureName</MeasureName>
                                            </WarningMeasure>
                                    </SourceObject>
                                    <DependsOnObject>
                                            <WarningColumn>
                                                    <Dimension>dimension</Dimension>
                                                    <Attribute>attribute</Attribute>
                                            </WarningColumn>
                                            <WarningMeasure>
                                                    <Cube>cube</Cube>
                                                    <MeasureGroup>measureGroup</MeasureGroup>
                                                    <MeasureName>measureName</MeasureName>
                                            </WarningMeasure>
                                    </DependsOnObject>
                                    <RowNumber>3</RowNumber>
                            </Location>
                            <Callstack>callstack</Callstack>
                        </Error>
                        </Messages>
                    </root>
                </return>
            </ExecuteResponse>
            """;

    public static String ALTER = """
            <ExecuteResponse xmlns="urn:schemas-microsoft-com:xml-analysis">
                <return>
                    <root>
                         <Exception />
                         <Messages>
                                <Error Description="description" ErrorCode="1" HelpFile="helpFile" Source="source">
                                     <Location>
                                            <Start>
                                                 <Line>1</Line>
                                                 <Column>2</Column>
                                            </Start>
                                            <End>
                                                 <Line>3</Line>
                                                 <Column>4</Column>
                                            </End>
                                            <LineOffset>1</LineOffset>
                                            <TextLength>2</TextLength>
                                            <SourceObject>
                                                 <WarningColumn>
                                                        <Dimension>dimension</Dimension>
                                                        <Attribute>attribute</Attribute>
                                                 </WarningColumn>
                                                 <WarningMeasure>
                                                        <Cube>cube</Cube>
                                                        <MeasureGroup>measureGroup</MeasureGroup>
                                                        <MeasureName>measureName</MeasureName>
                                                 </WarningMeasure>
                                            </SourceObject>
                                            <DependsOnObject>
                                                 <WarningColumn>
                                                        <Dimension>dimension</Dimension>
                                                        <Attribute>attribute</Attribute>
                                                 </WarningColumn>
                                                 <WarningMeasure>
                                                        <Cube>cube</Cube>
                                                        <MeasureGroup>measureGroup</MeasureGroup>
                                                        <MeasureName>measureName</MeasureName>
                                                 </WarningMeasure>
                                            </DependsOnObject>
                                            <RowNumber>3</RowNumber>
                                     </Location>
                                     <Callstack>callstack</Callstack>
                                </Error>
                         </Messages>
                    </root>
                </return>
            </ExecuteResponse>
            """;
}
