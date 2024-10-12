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
import java.time.LocalDateTime;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.adapters.LocalDateTimeAdapter;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.Row;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * This schema rowset returns dimensions, measure groups, or schema rowsets exposed as tables.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DbSchemaTablesResponseRowXml")
public class DbSchemaTablesResponseRowXml extends Row implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 6603458218433022717L;

    /**
     * The name of the database.
     */
    @XmlElement(name = "TABLE_CATALOG", required = false)
    private String tableCatalog;

    /**
     * The name of the schema.
     */
    @XmlElement(name = "TABLE_SCHEMA", required = false)
    private String tableSchema;

    /**
     * The name of the table.
     */
    @XmlElement(name = "TABLE_NAME", required = false)
    private String tableName;

    /**
     * The type of table: TABLE for measure group. SYSTEM TABLE for dimension. SCHEMA for schema rowset
     * table.
     */
    @XmlElement(name = "TABLE_TYPE", required = false)
    private String tableType;

    /**
     * The GUID of the table.
     */
    @XmlElement(name = "TABLE_GUID", required = false)
    private String tableGuid;

    /**
     * A description of the object.
     */
    @XmlElement(name = "DESCRIPTION", required = false)
    private String description;

    /**
     * The ID of the table.
     */
    @XmlElement(name = "TABLE_PROP_ID", required = false)
    private Integer tablePropId;

    /**
     * The date the table was created.
     */
    @XmlElement(name = "DATE_CREATED", required = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime dateCreated;

    /**
     * The date the table was last modified.
     */
    @XmlElement(name = "DATE_MODIFIED", required = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime dateModified;

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

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getTableGuid() {
        return tableGuid;
    }

    public void setTableGuid(String tableGuid) {
        this.tableGuid = tableGuid;
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }
}
