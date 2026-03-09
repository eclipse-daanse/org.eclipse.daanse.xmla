# Rowset Schema Definitions

External XSD files defining the row schema for each XMLA Discover request type.
Based on MS-SSAS specification v20251031.

## Files

| XSD File | Request Type |
|----------|-------------|
| `rowset-discover-datasources.xsd` | DISCOVER_DATASOURCES |
| `rowset-discover-properties.xsd` | DISCOVER_PROPERTIES |
| `rowset-discover-schema-rowsets.xsd` | DISCOVER_SCHEMA_ROWSETS |
| `rowset-discover-enumerators.xsd` | DISCOVER_ENUMERATORS |
| `rowset-discover-keywords.xsd` | DISCOVER_KEYWORDS |
| `rowset-discover-literals.xsd` | DISCOVER_LITERALS |
| `rowset-discover-xml-metadata.xsd` | DISCOVER_XML_METADATA |
| `rowset-discover-csdl-metadata.xsd` | DISCOVER_CSDL_METADATA |
| `rowset-dbschema-catalogs.xsd` | DBSCHEMA_CATALOGS |
| `rowset-dbschema-columns.xsd` | DBSCHEMA_COLUMNS |
| `rowset-dbschema-provider-types.xsd` | DBSCHEMA_PROVIDER_TYPES |
| `rowset-dbschema-tables.xsd` | DBSCHEMA_TABLES |
| `rowset-mdschema-actions.xsd` | MDSCHEMA_ACTIONS |
| `rowset-mdschema-cubes.xsd` | MDSCHEMA_CUBES |
| `rowset-mdschema-dimensions.xsd` | MDSCHEMA_DIMENSIONS |
| `rowset-mdschema-functions.xsd` | MDSCHEMA_FUNCTIONS |
| `rowset-mdschema-hierarchies.xsd` | MDSCHEMA_HIERARCHIES |
| `rowset-mdschema-kpis.xsd` | MDSCHEMA_KPIS |
| `rowset-mdschema-levels.xsd` | MDSCHEMA_LEVELS |
| `rowset-mdschema-measuregroup-dimensions.xsd` | MDSCHEMA_MEASUREGROUP_DIMENSIONS |
| `rowset-mdschema-measuregroups.xsd` | MDSCHEMA_MEASUREGROUPS |
| `rowset-mdschema-measures.xsd` | MDSCHEMA_MEASURES |
| `rowset-mdschema-members.xsd` | MDSCHEMA_MEMBERS |
| `rowset-mdschema-properties.xsd` | MDSCHEMA_PROPERTIES |
| `rowset-mdschema-sets.xsd` | MDSCHEMA_SETS |
| `rowset-mdschema-input-datasources.xsd` | MDSCHEMA_INPUT_DATASOURCES |

## TODO: API Discrepancies vs MS-SSAS Spec

The following discrepancies were found comparing the XSD definitions (from the spec) with the Java API response row interfaces in `api/src/main/java/org/eclipse/daanse/xmla/api/discover/`.

### Missing Columns (spec has, API missing)

- [ ] **DBSCHEMA_CATALOGS** (`DbSchemaCatalogsResponseRow`): Add `ENCRYPTION_LEVEL` (xsd:string) and `CRYPTOKEY_UPDATED` (xsd:dateTime)
- [ ] **DBSCHEMA_TABLES** (`DbSchemaTablesResponseRow`): Add `TABLE_OLAP_TYPE` (xsd:string)
- [ ] **MDSCHEMA_FUNCTIONS** (`MdSchemaFunctionsResponseRow`): Add `VISUAL_CALCULATIONS_INFO` (xsd:int)
- [ ] **MDSCHEMA_FUNCTIONS** (`ParameterInfo`): Add `SKIPPABLE` (xsd:boolean) to nested rowset type
- [ ] **MDSCHEMA_INPUT_DATASOURCES**: No Java API interface exists - entire request type not implemented. Needs `MdSchemaInputDataSourcesResponseRow` with 10 columns: CATALOG_NAME, SCHEMA_NAME, DATASOURCE_NAME, DATASOURCE_TYPE, CREATED_ON, LAST_SCHEMA_UPDATE, DESCRIPTION, TIMEOUT, DBMS_NAME, DBMS_VERSION

### Type Mismatches (API uses wrong Java type for uuid columns)

All uuid columns below are typed as `Optional<Integer>` in the API but should be `Optional<String>` or a UUID type per spec:

- [ ] **DBSCHEMA_COLUMNS** (`DbSchemaColumnsResponseRow`): `columnGuid()` and `typeGuid()` — spec type is `uuid`
- [ ] **DBSCHEMA_PROVIDER_TYPES** (`DbSchemaProviderTypesResponseRow`): `guid()` — spec type is `uuid`
- [ ] **DBSCHEMA_TABLES_INFO** (`DbSchemaTablesInfoResponseRow`): `tableGuid()` — spec type is `uuid`
- [ ] **MDSCHEMA_CUBES** (`MdSchemaCubesResponseRow`): `cubeGuid()` — spec type is `uuid`
- [ ] **MDSCHEMA_DIMENSIONS** (`MdSchemaDimensionsResponseRow`): `dimensionGuid()` — spec type is `uuid`
- [ ] **MDSCHEMA_HIERARCHIES** (`MdSchemaHierarchiesResponseRow`): `hierarchyGuid()` — spec type is `uuid`
- [ ] **MDSCHEMA_LEVELS** (`MdSchemaLevelsResponseRow`): `levelGuid()` — spec type is `uuid`
- [ ] **MDSCHEMA_MEASURES** (`MdSchemaMeasuresResponseRow`): `measureGuid()` — spec type is `uuid`
- [ ] **MDSCHEMA_MEMBERS** (`MdSchemaMembersResponseRow`): `memberGuid()` — spec type is `uuid`

### Optionality Mismatches

- [ ] **DISCOVER_LITERALS** (`DiscoverLiteralsResponseRow`): `literalValue()`, `literalInvalidChars()`, `literalInvalidStartingChars()`, `literalMaxLength()`, `literalNameEnumValue()` are all Required (non-Optional) in API but should be `Optional` per spec. Only `literalName()` should be required.
- [ ] **DISCOVER_ENUMERATORS** (`DiscoverEnumeratorsResponseRow`): `enumType()` and `elementName()` are Required in API but not marked as required in spec — should be `Optional`

### Extra Columns (API has, spec does not have as response columns)

These are restriction-only parameters in the spec but appear as response row columns in the API:

- [ ] **MDSCHEMA_MEASURES** (`MdSchemaMeasuresResponseRow`): `cubeSource()` and `measureVisibility()` are restriction-only in spec
- [ ] **MDSCHEMA_PROPERTIES** (`MdSchemaPropertiesResponseRow`): `cubeSource()` and `propertyIsVisible()` — `CUBE_SOURCE` and `PROPERTY_VISIBILITY` are restriction-only in spec

### Naming Issues

- [ ] **Directory typo**: `api/.../mdschema/demensions/` should be `dimensions`
- [ ] **MdSchemaDimensionsResponseRow**: Method `dimensionOptional()` should be `dimensionOrdinal()` (maps to DIMENSION_ORDINAL column)
- [ ] **MdSchemaKpisResponseRow**: Method `kpiWight()` has typo — should be `kpiWeight()` (maps to KPI_WEIGHT column)
- [ ] **MdSchemaFunctionsResponseRow**: Method `functionalName()` should be `functionName()` (maps to FUNCTION_NAME column)

### API Has Interfaces Not in MS-SSAS Extracted Spec

These request types exist in the Java API but were not found in the extracted MS-SSAS spec. They may come from the OLE DB specification:

- [ ] **DBSCHEMA_SCHEMATA** — `DbSchemaSchemataResponseRow` (3 columns: catalogName, schemaName, schemaOwner)
- [ ] **DBSCHEMA_SOURCE_TABLES** — `DbSchemaSourceTablesResponseRow` (4 columns: catalogName, schemaName, tableName, tableType)
- [ ] **DBSCHEMA_TABLES_INFO** — `DbSchemaTablesInfoResponseRow` (14 columns)
