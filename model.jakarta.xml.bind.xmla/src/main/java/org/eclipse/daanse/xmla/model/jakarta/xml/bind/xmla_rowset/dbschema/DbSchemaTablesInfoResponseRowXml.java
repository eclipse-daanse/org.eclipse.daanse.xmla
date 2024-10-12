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

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.Row;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

/**
 * This schema rowset identifies the (base) data types supported by the server.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DbSchemaTablesInfoResponseRowXml")
public class DbSchemaTablesInfoResponseRowXml extends Row implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 3219953251608576475L;

    /**
     * @return Catalog name
     */
    @XmlElement(name = "TABLE_CATALOG", required = false)
    private String catalogName;

    /**
     * Schema name
     */
    @XmlElement(name = "TABLE_SCHEMA", required = false)
    private String schemaName;

    /**
     * @return Table name
     */
    @XmlElement(name = "TABLE_NAME", required = true)
    private String tableName;

    /**
     * @return Table type
     */
    @XmlElement(name = "TABLE_TYPE", required = true)
    private String tableType;

    /**
     * @return GUID that uniquely identifies the table. Providers that do not use GUIDs to identify
     *         tables should return NULL in this column.
     */
    @XmlElement(name = "TABLE_GUID", required = false)
    private Integer tableGuid;

    /**
     * @return Whether this table supports bookmarks. Allways is false.
     */
    @XmlElement(name = "BOOKMARKS", required = false)
    private Boolean bookmarks;

    /**
     * @return Default bookmark type supported on this table.
     */
    @XmlElement(name = "BOOKMARK_TYPE", required = false)
    private Integer bookmarkType;

    /**
     * @return The indicator of the bookmark's native data type.
     */
    @XmlElement(name = "BOOKMARK_DATA_TYPE", required = false)
    private Integer bookmarkDataType;

    /**
     * @return Maximum length of the bookmark in bytes.
     */
    @XmlElement(name = "BOOKMARK_MAXIMUM_LENGTH", required = false)
    private Integer bookmarkMaximumLength;

    /**
     * @return A bitmask specifying additional information about bookmarks over the rowset.
     */
    @XmlElement(name = "BOOKMARK_INFORMATION", required = false)
    private Integer bookmarkInformation;

    /**
     * @return Version number for this table or NULL if the provider does not support returning table
     *         version information.
     */
    @XmlElement(name = "TABLE_VERSION", required = false)
    private Long tableVersion;

    /**
     * @return Cardinality (number of rows) of the table.
     */
    @XmlElement(name = "CARDINALITY", required = false)
    private Long cardinality;

    /**
     * @return Human-readable description of the table.
     */
    @XmlElement(name = "DESCRIPTION", required = false)
    private String description;

    /**
     * @return Property ID of the table. Return null.
     */
    @XmlElement(name = "TABLE_PROP_ID", required = false)
    private Integer tablePropId;

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public Integer getTableGuid() {
        return tableGuid;
    }

    public void setTableGuid(Integer tableGuid) {
        this.tableGuid = tableGuid;
    }

    public Boolean getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(Boolean bookmarks) {
        this.bookmarks = bookmarks;
    }

    public Integer getBookmarkType() {
        return bookmarkType;
    }

    public void setBookmarkType(Integer bookmarkType) {
        this.bookmarkType = bookmarkType;
    }

    public Integer getBookmarkDataType() {
        return bookmarkDataType;
    }

    public void setBookmarkDataType(Integer bookmarkDataType) {
        this.bookmarkDataType = bookmarkDataType;
    }

    public Integer getBookmarkMaximumLength() {
        return bookmarkMaximumLength;
    }

    public void setBookmarkMaximumLength(Integer bookmarkMaximumLength) {
        this.bookmarkMaximumLength = bookmarkMaximumLength;
    }

    public Integer getBookmarkInformation() {
        return bookmarkInformation;
    }

    public void setBookmarkInformation(Integer bookmarkInformation) {
        this.bookmarkInformation = bookmarkInformation;
    }

    public Long getTableVersion() {
        return tableVersion;
    }

    public void setTableVersion(Long tableVersion) {
        this.tableVersion = tableVersion;
    }

    public Long getCardinality() {
        return cardinality;
    }

    public void setCardinality(Long cardinality) {
        this.cardinality = cardinality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTablePropId() {
        return tablePropId;
    }

    public void setTablePropId(Integer tablePropId) {
        this.tablePropId = tablePropId;
    }
}
