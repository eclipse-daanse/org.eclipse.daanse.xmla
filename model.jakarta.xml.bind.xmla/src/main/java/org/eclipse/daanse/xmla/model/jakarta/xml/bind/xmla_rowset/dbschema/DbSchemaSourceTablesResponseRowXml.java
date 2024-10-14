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

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.TableTypeEnum;
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
@XmlType(name = "DbSchemaSourceTablesResponseRowXml")
public class DbSchemaSourceTablesResponseRowXml extends Row implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 4980356365829213910L;

    /**
     * Catalog name. NULL if the provider does not support catalogs.
     */
    @XmlElement(name = "TABLE_CATALOG", required = false)
    private String catalogName;

    /**
     * Unqualified schema name. NULL if the provider does not support schemas.
     */
    @XmlElement(name = "TABLE_SCHEMA", required = false)
    private String schemaName;

    /**
     * Table name
     */
    @XmlElement(name = "TABLE_NAME", required = false)
    private String tableName;

    /**
     * Table type. One of the following or a provider-specific value: ALIAS, TABLE, SYNONYM,
     *         SYSTEM TABLE, VIEW, GLOBAL " TEMPORARY, LOCAL TEMPORARY, EXTERNAL TABLE, SYSTEM VIEW
     */
    @XmlElement(name = "TABLE_TYPE", required = false)
    private TableTypeEnum tableType;

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

    public TableTypeEnum getTableType() {
        return tableType;
    }

    public void setTableType(TableTypeEnum tableType) {
        this.tableType = tableType;
    }
}
