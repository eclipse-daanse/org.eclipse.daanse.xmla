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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.dbschema;

import java.io.Serializable;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.ColumnFlagsEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.ColumnOlapTypeEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.Row;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

/**
 * This schema rowset returns a row for each measure, each cube dimension attribute, and each schema
 * rowset column, exposed as a column.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DbSchemaColumnsResponseRowXml")
public class DbSchemaColumnsResponseRowXml extends Row implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 5692876791695703039L;

    /**
     * @return The name of the database.
     */
    @XmlElement(name = "TABLE_CATALOG", required = false)
    private String tableCatalog;

    /**
     * @return The name of the schema.
     */
    @XmlElement(name = "TABLE_SCHEMA", required = false)
    private String tableSchema;

    /**
     * @return The name of the table.
     */
    @XmlElement(name = "TABLE_NAME", required = false)
    private String tableName;

    /**
     * The name of the attribute hierarchy or measure.
     */
    @XmlElement(name = "COLUMN_NAME", required = false)
    private String columnName;

    /**
     * @return The GUID of the column.
     */
    @XmlElement(name = "COLUMN_GUID", required = false)
    private Integer columnGuid;

    /**
     * @return The property ID of the column.
     */
    @XmlElement(name = "COLUMN_PROPID", required = false)
    private Integer columnPropId;

    /**
     * @return The column order for each constraint.
     */
    @XmlElement(name = "ORDINAL_POSITION", required = false)
    private Integer ordinalPosition;

    /**
     * @return Indicates whether the column has a default. If true, the column has a default. If false,
     *         the column does not have a default.
     */
    @XmlElement(name = "COLUMN_HAS_DEFAULT", required = false)
    private Boolean columnHasDefault;

    /**
     * @return The default value of the column.
     */
    @XmlElement(name = "COLUMN_DEFAULT", required = false)
    private String columnDefault;

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
    @XmlElement(name = "COLUMN_FLAG", required = false)
    private ColumnFlagsEnum columnFlags;

    /**
     * @return Indicates whether the column is nullable. If true, indicates that the column is nullable.
     *         Otherwise, false.
     */
    @XmlElement(name = "IS_NULLABLE", required = false)
    private Boolean isNullable;

    /**
     * @return The data type of the column. Returns a string for dimension columns and a variant for
     *         measures.
     */
    @XmlElement(name = "DATA_TYPE", required = false)
    private Integer dataType; // TODO maybe need enum here

    /**
     * @return The GUID of the column's data type.
     */
    @XmlElement(name = "TYPE_GUID", required = false)
    private Integer typeGuid;

    /**
     * @return The maximum possible length of a value in the column, expressed as the number of wide
     *         characters.
     */
    @XmlElement(name = "CHARACTER_MAXIMUM_LENGTH", required = false)
    private Integer characterMaximum;

    /**
     * @return The maximum length in octets (bytes) of the column, if the type of the column is
     *         character or binary. A value of zero means that the column has no maximum length. NULL
     *         for all other types of columns.
     */
    @XmlElement(name = "CHARACTER_OCTET_LENGTH", required = false)
    private Integer characterOctetLength;

    /**
     * @return The maximum precision of the column if the column's data type is of a numeric data type
     *         other than DBTYPE_VARNUMERIC.
     */
    @XmlElement(name = "NUMERIC_PRECISION", required = false)
    private Integer numericPrecision;

    /**
     * @return The number of digits to the right of the decimal point if the column's type indicator is
     *         DBTYPE_DECIMAL, DBTYPE_NUMERIC, or DBTYPE_VARNUMERIC. Otherwise, this is NULL.
     */
    @XmlElement(name = "NUMERIC_SCALE", required = false)
    private Integer numericScale;

    /**
     * @return The date/time precision (number of digits in the fractional seconds portion) of the
     *         column if the column is a DateTime or Interval type.
     */
    @XmlElement(name = "DATETIME_PRECISION", required = false)
    private Integer dateTimePrecision;

    /**
     * @return The catalog name. NULL if the provider does not support catalogs.
     */
    @XmlElement(name = "CHARACTER_SET_CATALOG", required = false)
    private String characterSetCatalog;

    /**
     * @return The unqualified schema name. NULL if the provider does not support schemas.
     */
    @XmlElement(name = "CHARACTER_SET_SCHEMA", required = false)
    private String characterSetSchema;

    /**
     * @return The character set name.
     */
    @XmlElement(name = "CHARACTER_SET_NAME", required = false)
    private String characterSetName;

    /**
     * @return The catalog name in which the collation is defined. NULL if the provider does not support
     *         catalogs or different collations.
     */
    @XmlElement(name = "COLLATION_CATALOG", required = false)
    private String collationCatalog;

    /**
     * @return The unqualified schema name in which the collation is defined. NULL if the provider does
     *         not support schemas or different collations.
     */
    @XmlElement(name = "COLLATION_SCHEMA", required = false)
    private String collationSchema;

    /**
     * @return The collation name. NULL if the server does not support different collations.
     */
    @XmlElement(name = "COLLATION_NAME", required = false)
    private String collationName;

    /**
     * @return The catalog name in which the domain is defined. NULL if the server does not support
     *         catalogs or domains.
     */
    @XmlElement(name = "DOMAIN_CATALOG", required = false)
    private String domainCatalog;

    /**
     * @return The unqualified schema name in which the domain is defined. NULL if the server does not
     *         support schemas or domains.
     */
    @XmlElement(name = "DOMAIN_SCHEMA", required = false)
    private String domainSchema;

    /**
     * @return The domain name. NULL if the server does not support domains
     */
    @XmlElement(name = "DOMAIN_NAME", required = false)
    private String domainName;

    /**
     * The human-readable description of the column. For example, the description for a column that is
     * named Name in the Employee table might be "Employee name." NULL if this column is not supported
     * by the server, or if there is no description associated with the column.
     */
    @XmlElement(name = "DESCRIPTION", required = false)
    private String description;

    /**
     * @return The OLAP type of the object: MEASURE indicates that the object is a measure. ATTRIBUTE
     *         indicates that the object is a dimension attribute. SCHEMA indicates that the object is a
     *         column in a schema rowset table.
     */
    @XmlElement(name = "COLUMN_OLAP_TYPE", required = false)
    private ColumnOlapTypeEnum columnOlapType;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getColumnGuid() {
        return columnGuid;
    }

    public void setColumnGuid(Integer columnGuid) {
        this.columnGuid = columnGuid;
    }

    public Integer getColumnPropId() {
        return columnPropId;
    }

    public void setColumnPropId(Integer columnPropId) {
        this.columnPropId = columnPropId;
    }

    public Integer getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(Integer ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public Boolean getColumnHasDefault() {
        return columnHasDefault;
    }

    public void setColumnHasDefault(Boolean columnHasDefault) {
        this.columnHasDefault = columnHasDefault;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public ColumnFlagsEnum getColumnFlags() {
        return columnFlags;
    }

    public void setColumnFlags(ColumnFlagsEnum columnFlags) {
        this.columnFlags = columnFlags;
    }

    public Boolean getNullable() {
        return isNullable;
    }

    public void setNullable(Boolean nullable) {
        isNullable = nullable;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getTypeGuid() {
        return typeGuid;
    }

    public void setTypeGuid(Integer typeGuid) {
        this.typeGuid = typeGuid;
    }

    public Integer getCharacterMaximum() {
        return characterMaximum;
    }

    public void setCharacterMaximum(Integer characterMaximum) {
        this.characterMaximum = characterMaximum;
    }

    public Integer getCharacterOctetLength() {
        return characterOctetLength;
    }

    public void setCharacterOctetLength(Integer characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
    }

    public Integer getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(Integer numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public Integer getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(Integer numericScale) {
        this.numericScale = numericScale;
    }

    public Integer getDateTimePrecision() {
        return dateTimePrecision;
    }

    public void setDateTimePrecision(Integer dateTimePrecision) {
        this.dateTimePrecision = dateTimePrecision;
    }

    public String getCharacterSetCatalog() {
        return characterSetCatalog;
    }

    public void setCharacterSetCatalog(String characterSetCatalog) {
        this.characterSetCatalog = characterSetCatalog;
    }

    public String getCharacterSetSchema() {
        return characterSetSchema;
    }

    public void setCharacterSetSchema(String characterSetSchema) {
        this.characterSetSchema = characterSetSchema;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public String getCollationCatalog() {
        return collationCatalog;
    }

    public void setCollationCatalog(String collationCatalog) {
        this.collationCatalog = collationCatalog;
    }

    public String getCollationSchema() {
        return collationSchema;
    }

    public void setCollationSchema(String collationSchema) {
        this.collationSchema = collationSchema;
    }

    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    public String getDomainCatalog() {
        return domainCatalog;
    }

    public void setDomainCatalog(String domainCatalog) {
        this.domainCatalog = domainCatalog;
    }

    public String getDomainSchema() {
        return domainSchema;
    }

    public void setDomainSchema(String domainSchema) {
        this.domainSchema = domainSchema;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ColumnOlapTypeEnum getColumnOlapType() {
        return columnOlapType;
    }

    public void setColumnOlapType(ColumnOlapTypeEnum columnOlapType) {
        this.columnOlapType = columnOlapType;
    }
}
