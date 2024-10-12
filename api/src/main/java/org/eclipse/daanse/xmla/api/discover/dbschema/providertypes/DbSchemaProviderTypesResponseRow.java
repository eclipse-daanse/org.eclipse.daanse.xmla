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
package org.eclipse.daanse.xmla.api.discover.dbschema.providertypes;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.SearchableEnum;

/**
 * This schema rowset identifies the (base) data types supported by the server.
 */
public interface DbSchemaProviderTypesResponseRow {

    /**
     * @return The server-specific data type name.
     */
    Optional<String> typeName();

    /**
     * @return This enumeration is the same as LEVEL_DBTYPE for MDSCHEMA_LEVELS. The type of the member
     *         key column that is used for the level attribute. It MUST be null if concatenated keys are
     *         used as the member key column. Type values are described in the following list: 0 –
     *         (DBTYPE_EMPTY) Indicates that no value was specified. 2 – (DBTYPE_I2) Indicates a
     *         two-byte signed integer. 3 – (DBTYPE_I4) Indicates a four-byte signed integer. 4 –
     *         (DBTYPE_R4) Indicates a single- precision floating-point value. 5 – (DBTYPE_R8) Indicates
     *         a double- precision floating-point value. 6 – (DBTYPE_CY) Indicates a currency value.
     *         Currency is a fixed-point number with four digits to the right of the decimal point and
     *         is stored in an eight-byte signed integer scaled by 10,000. 7 – (DBTYPE_DATE) Indicates a
     *         date value. Date values are stored as Double, the whole part of which is the number of
     *         days since December 30, 1899, and the fractional part of which is the fraction of a day.
     *         8 – (DBTYPE_BSTR) A pointer to a BSTR, which is a null-terminated character string in
     *         which the string length is stored with the string. 9 – (DBTYPE_IDISPATCH) Indicates a
     *         pointer to an IDispatch interface on an OLE object. 10 – (DBTYPE_ERROR) Indicates a
     *         32-bit error code. 11 – (DBTYPE_BOOL) Indicates a Boolean value. 12 – (DBTYPE_VARIANT)
     *         Indicates an Automation variant. 13 – (DBTYPE_IUNKNOWN) Indicates a pointer to an
     *         IUnknown interface on an OLE object. 14 – (DBTYPE_DECIMAL) Indicates an exact numeric
     *         value with a fixed precision and scale. The scale is between 0 and 28. 16 – (DBTYPE_I1)
     *         Indicates a one-byte signed integer. 17 – (DBTYPE_UI1) Indicates a one-byte unsigned
     *         integer. 18 – (DBTYPE_UI2) Indicates a two-byte unsigned integer. 19 – (DBTYPE_UI4)
     *         Indicates a four-byte unsigned integer. 20 – (DBTYPE_I8) Indicates an eight-byte signed
     *         integer. 21 – (DBTYPE_UI8) Indicates an eight-byte unsigned integer. 72 – (DBTYPE_GUID)
     *         Indicates a GUID. 128 – (DBTYPE_BYTES) Indicates a binary value. 129 – (DBTYPE_STR)
     *         Indicates a string value. 130 – (DBTYPE_WSTR) Indicates a null- terminated Unicode
     *         character string. 131 – (DBTYPE_NUMERIC) Indicates an exact numeric value with a fixed
     *         precision and scale. The scale is between 0 and 38. 132 – (DBTYPE_UDT) Indicates a
     *         user-defined variable. 133 – (DBTYPE_DBDATE) Indicates a date value (yyyymmdd). 134 –
     *         (DBTYPE_DBTIME) Indicates a time value (hhmmss). 135 – (DBTYPE_DBTIMESTAMP) Indicates a
     *         date-time stamp (yyyymmddhhmmss plus a fraction in billionths). 136 - (DBTYPE_HCHAPTER)
     *         Indicates a four-byte chapter value used to identify rows in a child rowset.
     */
    Optional<LevelDbTypeEnum> dataType();

    /**
     * @return The length of a non-numeric column or parameter that refers to either the maximum or the
     *         length defined for this type by the server. For character data, this is the maximum or
     *         defined length in characters. For DateTime data types, this is the length of the string
     *         representation (assuming the maximum allowed precision of the fractional second
     *         component). If the data type is numeric, this is the upper bound on the maximum precision
     *         of the data type.
     */
    Optional<Integer> columnSize();

    /**
     * @return The character or characters used to prefix a literal of this type in a text command.
     */
    Optional<String> literalPrefix();

    /**
     * @return The character or characters used to suffix a literal of this type in a text command.
     */
    Optional<String> literalSuffix();

    /**
     * @return The creation parameters specified by the consumer when creating a column of this data
     *         type. For example, the SQL DECIMAL data type needs a precision and a scale. In this case,
     *         the creation parameters might be the string "precision,scale". In a text command to
     *         create a DECIMAL column with a precision of 10 and a scale of 2, the value of the
     *         TYPE_NAME column might be DECIMAL(), and the complete type specification would be
     *         DECIMAL(10,2). The creation parameters appear as a comma- separated list of values, in
     *         the order they are to be supplied and with no surrounding parentheses. If a creation
     *         parameter is length, maximum length, precision, scale, seed, or increment, use "length",
     *         "max length", "precision", "scale", "seed", and "increment", respectively. If the
     *         creation parameter is some other value, the server determines what text is to be used to
     *         describe the creation parameter. If the data type requires creation parameters, "()"
     *         usually appears in the type name. This indicates the position at which to insert the
     *         creation parameters. If the type name does not include "()", the creation parameters are
     *         enclosed in parentheses and appended to the data type name.
     */
    Optional<String> createParams();

    /**
     * @return A Boolean that indicates whether the data type is nullable. True indicates that the data
     *         type is nullable. False indicates that the data type is not nullable. NULL indicates that
     *         it is not known whether the data type is nullable.
     */
    Optional<Boolean> isNullable();

    /**
     * @return A Boolean that indicates whether the data type is a character type and case-sensitive.
     *         True indicates that the data type is a character type and is case-sensitive. False
     *         indicates that the data type is not case- sensitive. NULL indicates that the data type is
     *         not a character type.
     */
    Optional<Boolean> caseSensitive();

    /**
     * @return An integer indicating how the data type can be used in searches if the server supports
     *         ICommandText; otherwise, NULL. If the server supports ICommandText, then this column can
     *         have the following values: DB_UNSEARCHABLE (0x01) - indicates that the data type cannot
     *         be used in a WHERE clause. DB_LIKE_ONLY (0x02) - indicates that the data type can be used
     *         in a WHERE clause only with the LIKE predicate. DB_ALL_EXCEPT_LIKE (0x03) - indicates
     *         that the data type can be used in a WHERE clause with all comparison operators except
     *         LIKE. DB_SEARCHABLE (0x04) - indicates that the data type can be used in a WHERE clause
     *         with any comparison operator.
     */
    Optional<SearchableEnum> searchable();

    /**
     * @return A Boolean that indicates whether the data type is unsigned. True indicates that the data
     *         type is unsigned. False indicates that the data type is signed. NULL indicates that this
     *         is not applicable to the data type.
     */
    Optional<Boolean> unsignedAttribute();

    /**
     * @return A Boolean that indicates whether the data type has a fixed precision and scale. True
     *         indicates that the data type has a fixed precision and scale. False indicates that the
     *         data type does not have a fixed precision and scale.
     */
    Optional<Boolean> fixedPrecScale();

    /**
     * @return A Boolean that indicates whether the data type can be autoincrementing. True indicates
     *         that values of this type can be autoincrementing. False indicates that values of this
     *         type cannot be autoincrementing. If this value is true, the server's
     *         DBPROP_COL_AUTOINCREMENT column property determines whether a column of this type is
     *         always autoincrementing. If the DBPROP_COL_AUTOINCREMENT property is read/write, the
     *         setting of the DBPROP_COL_AUTOINCREMENT property determines whether a column of this type
     *         is autoincrementing. If DBPROP_COL_AUTOINCREMENT is a read-only property, either all or
     *         none of the columns of this type are autoincrementing.
     */
    Optional<Boolean> autoUniqueValue();

    /**
     * @return The localized version of TYPE_NAME. NULL is returned if a localized name is not supported
     *         by the server.
     */
    Optional<String> localTypeName();

    /**
     * @return If the type indicator is DBTYPE_VARNUMERIC, DBTYPE_DECIMAL, or DBTYPE_NUMERIC, this
     *         column specifies the minimum number of digits allowed to the right of the decimal point.
     *         Otherwise, it is NULL.
     */
    Optional<Integer> minimumScale();

    /**
     * @return If the type indicator is DBTYPE_VARNUMERIC, DBTYPE_DECIMAL, or DBTYPE_NUMERIC, this
     *         column specifies the maximum number of digits allowed to the right of the decimal point.
     *         Otherwise, it is NULL.
     */
    Optional<Integer> maximumScale();

    /**
     * @return (Reserved for future use.) The GUID of the type, if the type is described in a type
     *         library. Otherwise, it is NULL.
     */
    Optional<Integer> guid();

    /**
     * @return The type library that contains the description of the type, if the type is described in a
     *         type library. Otherwise, it is NULL.
     */
    Optional<String> typeLib();

    /**
     * @return The version of the type definition. Servers might request to create different versions of
     *         type definitions. Different servers might use different versioning schemes, such as a
     *         timestamp or a number (Integer or Float). NULL if not supported.
     */
    Optional<String> version();

    /**
     * @return A Boolean that indicates whether the data type is a binary large object (BLOB) and has
     *         very long data. True indicates that the data type is a BLOB that contains very long
     *         data; the definition of very long data is server-specific.<193> False indicates that the
     *         data type is a BLOB that does not contain very long data or that is not a BLOB. This
     *         column value determines the setting of the DBCOLUMNFLAGS_ISLONG flag that is returned by
     *         GetColumnInfo in IColumnsInfo and the setting of the DBCOLUMNFLAGS_ISLONG flag that is
     *         returned by GetParameterInfo in ICommandWithParameters.
     */
    Optional<Boolean> isLong();

    /**
     * @return A Boolean that indicates whether the data type is the best match. A value of true
     *         indicates that the data type is the best match between all data types in the data store
     *         and the OLE DB data type that is indicated by the value in the DATA_TYPE column. For more
     *         information, see [MSDN-OLEDB]. A value of false indicates that the data type is not the
     *         best match. For each set of rows in which the value of the DATA_TYPE column is the same,
     *         the BEST_MATCH column is set to true in only one row.
     */
    Optional<Boolean> bestMatch();

    /**
     * @return A Boolean that indicates whether the column is fixed in length. A value of true indicates
     *         that columns of this type that are created by the DDL will be of fixed length. A value of
     *         false indicates that columns of this type that are created by the DDL will be of variable
     *         length. If the field is NULL, it is not known whether the server will map this field with
     *         a fixed-length or variable-length column.
     */
    Optional<Boolean> isFixedLength();
}
