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
package org.eclipse.daanse.xmla.api.discover.mdschema.levels;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.CustomRollupSettingEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelOriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelUniqueSettingsEnum;

/**
 * This schema rowset describes each level within a particular hierarchy.
 */
public interface MdSchemaLevelsResponseRow {

    /**
     * @return The name of the database.
     */
    Optional<String> catalogName();

    /**
     * @return The name of the schema.
     */
    Optional<String> schemaName();

    /**
     * @return The name of the cube.
     */
    Optional<String> cubeName();

    /**
     * @return The unique name of the dimension.
     */
    Optional<String> dimensionUniqueName();

    /**
     * @return The unique name of the hierarchy.
     */
    Optional<String> hierarchyUniqueName();

    /**
     * The name of the level.
     */
    Optional<String> levelName();

    /**
     * The unique name of the level.
     */
    Optional<String> levelUniqueName();

    /**
     * @return The GUID of the level.
     */
    Optional<Integer> levelGuid();

    /**
     * @return The caption of the hierarchy.
     */
    Optional<String> levelCaption();

    /**
     * @return The level number in the hierarchy. Top level is zero (0)
     */
    Optional<Integer> levelNumber();

    /**
     * @return The number of members in the level.
     */
    Optional<Integer> levelCardinality();

    /**
     * @return The type of the level from a list of possible values. Account 0x1014 All 0x0001 Bill of
     *         Material Resource 0x1012 Calculated 0x0002 Channel 0x1061 Company 0x1042 Currency
     *         Destination 0x1052 Currency Source 0x1051 Customer 0x1021 Customer Group 0x1022 Customer
     *         Household 0x1023 Geography City 0x2006 Geography Continent 0x2001 Geography country
     *         0x2003 Geography County 0x2005 Geography Point 0x2008 Postal Code 0x2007 Geography Region
     *         0x2002 Geography StateOrProvince 0x2004 Organization Unit 0x1011 Person 0x1041 Product
     *         0x1031 Product Group 0x1032 Promotion 0x1071 Quantitative 0x1013 Regular 0x0000
     *         Representative 0x1062 Reserved1 0x0008 Scenario 0x1015 Time 0x0004 Time Days 0x0204 Time
     *         Half Years 0x0024 Time Quarters 0x0044 Time Seconds 0x0804 Time Undefined 0x1004 Time
     *         Weeks 0x0104 Time Years 0x0014 Utility 0x1016
     */
    Optional<LevelTypeEnum> levelType();

    /**
     * @return A description of the level.
     */
    Optional<String> description();

    /**
     * @return A bitmask that specifies the custom rollup options: 0x01 - Indicates that a custom rollup
     *         expression exists for this level. 0x02 - Indicates that members of this level have custom
     *         rollup expressions. 0x04 - Indicates that there is a skipped level associated with
     *         members of this level. 0x08 - Indicates that members of this level have custom member
     *         properties. 0x10 - Indicates that members on this level have unary operators.
     */
    Optional<CustomRollupSettingEnum> customRollupSetting();

    /**
     * @return A bitmask that specifies which columns contain unique values, if the level only has
     *         members with unique names or keys: 0x00000001 - Member key columns establish uniqueness.
     *         0x00000002 - Member name columns establish uniqueness.
     */
    Optional<LevelUniqueSettingsEnum> levelUniqueSettings();

    /**
     * @return When true, indicates that the level is visible; otherwise false.
     */
    Optional<Boolean> levelIsVisible();

    /**
     * @return The name of the attribute on which the level is sorted.
     */
    Optional<String> levelOrderingProperty();

    /**
     * @return The type of the member key column that is used for the level attribute. It MUST be null
     *         if concatenated keys are used as the member key column. Type values are described in the
     *         following list: 0 – (DBTYPE_EMPTY) Indicates that no value was specified. 2 – (DBTYPE_I2)
     *         Indicates a two-byte signed integer. 3 – (DBTYPE_I4) Indicates a four-byte signed
     *         integer. 4 – (DBTYPE_R4) Indicates a single- precision floating-point value. 5 –
     *         (DBTYPE_R8) Indicates a double- precision floating-point value. 6 – (DBTYPE_CY) Indicates
     *         a currency value. Currency is a fixed-point number with four digits to the right of the
     *         decimal point and is stored in an eight-byte signed integer scaled by 10,000. 7 –
     *         (DBTYPE_DATE) Indicates a date value. Date values are stored as Double, the whole part of
     *         which is the number of days since December 30, 1899, and the fractional part of which is
     *         the fraction of a day. 8 – (DBTYPE_BSTR) A pointer to a BSTR, which is a null-terminated
     *         character string in which the string length is stored with the string. 9 –
     *         (DBTYPE_IDISPATCH) Indicates a pointer to an IDispatch interface on an OLE object. 10 –
     *         (DBTYPE_ERROR) Indicates a 32-bit error code. 11 – (DBTYPE_BOOL) Indicates a Boolean
     *         value. 12 – (DBTYPE_VARIANT) Indicates an Automation variant. 13 – (DBTYPE_IUNKNOWN)
     *         Indicates a pointer to an IUnknown interface on an OLE object. 14 – (DBTYPE_DECIMAL)
     *         Indicates an exact numeric value with a fixed precision and scale. The scale is between 0
     *         and 28. 16 – (DBTYPE_I1) Indicates a one-byte signed integer. 17 – (DBTYPE_UI1) Indicates
     *         a one-byte unsigned integer. 18 – (DBTYPE_UI2) Indicates a two-byte unsigned integer. 19
     *         – (DBTYPE_UI4) Indicates a four-byte unsigned integer. 20 – (DBTYPE_I8) Indicates an
     *         eight-byte signed integer. 21 – (DBTYPE_UI8) Indicates an eight-byte unsigned integer. 72
     *         – (DBTYPE_GUID) Indicates a GUID. 128 – (DBTYPE_BYTES) Indicates a binary value. 129 –
     *         (DBTYPE_STR) Indicates a string value. 130 – (DBTYPE_WSTR) Indicates a null- terminated
     *         Unicode character string. 131 – (DBTYPE_NUMERIC) Indicates an exact numeric value with a
     *         fixed precision and scale. The scale is between 0 and 38. 132 – (DBTYPE_UDT) Indicates a
     *         user-defined variable. 133 – (DBTYPE_DBDATE) Indicates a date value (yyyymmdd). 134 –
     *         (DBTYPE_DBTIME) Indicates a time value (hhmmss). 135 – (DBTYPE_DBTIMESTAMP) Indicates a
     *         date-time stamp (yyyymmddhhmmss plus a fraction in billionths). 136 - (DBTYPE_HCHAPTER)
     *         Indicates a four-byte chapter value used to identify rows in a child rowset.
     */
    Optional<LevelDbTypeEnum> levelDbType();

    /**
     * @return The unique name of the master level.
     */
    Optional<String> levelMasterUniqueName();

    /**
     * @return The SQL column name for the level name.
     */
    Optional<String> levelNameSqlColumnName();

    /**
     * @return The SQL column name for the level key.
     */
    Optional<String> levelKeySqlColumnName();

    /**
     * @return The SQL column name for the level unique name.
     */
    Optional<String> levelUniqueNameSqlColumnName();

    /**
     * @return The name of the attribute hierarchy that provides the source of the level.
     */
    Optional<String> levelAttributeHierarchyName();

    /**
     * @return The number of columns in the level key.
     */
    Optional<Integer> levelKeyCardinality();

    /**
     * @return A bitmask that defines how the level was sourced: 0x0001 - Identifies levels in a user
     *         defined hierarchy. 0x0002 - Identifies levels in an attribute hierarchy. 0x0004 -
     *         Identifies levels in a key attribute hierarchy. 0x0008 - Identifies levels in attribute
     *         hierarchies that are not enabled.
     */
    Optional<LevelOriginEnum> levelOrigin();
}
