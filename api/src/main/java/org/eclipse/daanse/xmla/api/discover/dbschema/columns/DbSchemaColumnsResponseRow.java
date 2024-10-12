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
package org.eclipse.daanse.xmla.api.discover.dbschema.columns;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.ColumnFlagsEnum;
import org.eclipse.daanse.xmla.api.common.enums.ColumnOlapTypeEnum;

/**
 * This schema rowset returns a row for each measure, each cube dimension attribute, and each schema
 * rowset column, exposed as a column.
 */
public interface DbSchemaColumnsResponseRow {

    /**
     * @return The name of the database.
     */
    Optional<String> tableCatalog();

    /**
     * @return The name of the schema.
     */
    Optional<String> tableSchema();

    /**
     * @return The name of the table.
     */
    Optional<String> tableName();

    /**
     * The name of the attribute hierarchy or measure.
     */
    Optional<String> columnName();

    /**
     * @return The GUID of the column.
     */
    Optional<Integer> columnGuid();

    /**
     * @return The property ID of the column.
     */
    Optional<Integer> columnPropId();

    /**
     * @return The column order for each constraint.
     */
    Optional<Integer> ordinalPosition();

    /**
     * @return Indicates whether the column has a default. If true, the column has a default. If false,
     *         the column does not have a default.
     */
    Optional<Boolean> columnHasDefault();

    /**
     * @return The default value of the column.
     */
    Optional<String> columnDefault();

    /**
     * @return A bitmask that indicates column properties. 0x1 - DBCOLUMNFLAGS_ISBOOKMARK – Set if the
     *         column is a bookmark. 0x2 - DBCOLUMNFLAGS_MAYDEFER – Set if the column is deferred. 0x4 -
     *         DBCOLUMNFLAGS_WRITE – Set if the OLEDB interface IRowsetChange:SetData can be called. 0x8
     *         - DBCOLUMNFLAGS_WRITEUNKNOWN – Set if the column can be updated through some means, but
     *         the means is unknown. 0x10 - DBCOLUMNFLAGS_ISFIXEDLENGTH – Set if all data in the column
     *         has the same length. 0x20 - DBCOLUMNFLAGS_ISNULLABLE – Set if consumer can set the column
     *         to NULL or if the provider cannot determine if the column can be set to NULL. 0x40 -
     *         DBCOLUMNFLAGS_MAYBENULL – Set if the column can contain NULL values, or if the provider
     *         cannot guarantee that the column cannot contain NULL values. 0x80 - DBCOLUMNFLAGS_ISLONG
     *         – Set if the column contains a BLOB that contains very long data. 0x100 -
     *         DBCOLUMNFLAGS_ISROWID – Set if the column contains a persistent row identifier that
     *         cannot be written to and has no meaningful value except to identify the row. 0x200 -
     *         DBCOLUMNFLAGS_ISROWVER – Set if the column contains a timestamp or other versioning
     *         mechanism that cannot be written to directly and that is automatically updated to a new
     *         increasing value when the row is updated or committed. 0x1000 -
     *         DBCOLUMNFLAGS_CACHEDEFERRED – Set if when a deferred column is first read its value the
     *         column is cached by the provider.
     */
    Optional<ColumnFlagsEnum> columnFlags();

    /**
     * @return Indicates whether the column is nullable. If true, indicates that the column is nullable.
     *         Otherwise, false.
     */
    Optional<Boolean> isNullable();

    /**
     * @return The data type of the column. Returns a string for dimension columns and a variant for
     *         measures.
     */
    Optional<Integer> dataType(); // TODO maybe need enum here

    /**
     * @return The GUID of the column's data type.
     */
    Optional<Integer> typeGuid();

    /**
     * @return The maximum possible length of a value in the column, expressed as the number of wide
     *         characters.
     */
    Optional<Integer> characterMaximum();

    /**
     * @return The maximum length in octets (bytes) of the column, if the type of the column is
     *         character or binary. A value of zero means that the column has no maximum length. NULL
     *         for all other types of columns.
     */
    Optional<Integer> characterOctetLength();

    /**
     * @return The maximum precision of the column if the column's data type is of a numeric data type
     *         other than DBTYPE_VARNUMERIC.
     */
    Optional<Integer> numericPrecision();

    /**
     * @return The number of digits to the right of the decimal point if the column's type indicator is
     *         DBTYPE_DECIMAL, DBTYPE_NUMERIC, or DBTYPE_VARNUMERIC. Otherwise, this is NULL.
     */
    Optional<Integer> numericScale();

    /**
     * @return The date/time precision (number of digits in the fractional seconds portion) of the
     *         column if the column is a DateTime or Interval type.
     */
    Optional<Integer> dateTimePrecision();

    /**
     * @return The catalog name. NULL if the provider does not support catalogs.
     */
    Optional<String> characterSetCatalog();

    /**
     * @return The unqualified schema name. NULL if the provider does not support schemas.
     */
    Optional<String> characterSetSchema();

    /**
     * @return The character set name.
     */
    Optional<String> characterSetName();

    /**
     * @return The catalog name in which the collation is defined. NULL if the provider does not support
     *         catalogs or different collations.
     */
    Optional<String> collationCatalog();

    /**
     * @return The unqualified schema name in which the collation is defined. NULL if the provider does
     *         not support schemas or different collations.
     */
    Optional<String> collationSchema();

    /**
     * @return The collation name. NULL if the server does not support different collations.
     */
    Optional<String> collationName();

    /**
     * @return The catalog name in which the domain is defined. NULL if the server does not support
     *         catalogs or domains.
     */
    Optional<String> domainCatalog();

    /**
     * @return The unqualified schema name in which the domain is defined. NULL if the server does not
     *         support schemas or domains.
     */
    Optional<String> domainSchema();

    /**
     * @return The domain name. NULL if the server does not support domains
     */
    Optional<String> domainName();

    /**
     * @return The human-readable description of the column. For example, the description for a column
     *         that is named Name in the Employee table might be "Employee name." NULL if this column is
     *         not supported by the server, or if there is no description associated with the column.
     */
    Optional<String> description();

    /**
     * @return The OLAP type of the object: MEASURE indicates that the object is a measure. ATTRIBUTE
     *         indicates that the object is a dimension attribute. SCHEMA indicates that the object is a
     *         column in a schema rowset table.
     */
    Optional<ColumnOlapTypeEnum> columnOlapType();
}
