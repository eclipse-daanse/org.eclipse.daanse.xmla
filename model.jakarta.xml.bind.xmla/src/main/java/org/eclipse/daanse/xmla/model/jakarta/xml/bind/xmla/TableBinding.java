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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TableBinding", propOrder = { "dataSourceID", "dbTableName", "dbSchemaName" })
public class TableBinding extends TabularBinding {

    @XmlElement(name = "DataSourceID")
    protected String dataSourceID;
    @XmlElement(name = "DbTableName", required = true)
    protected String dbTableName;
    @XmlElement(name = "DbSchemaName")
    protected String dbSchemaName;

    public String getDataSourceID() {
        return dataSourceID;
    }

    public void setDataSourceID(String value) {
        this.dataSourceID = value;
    }

    public String getDbTableName() {
        return dbTableName;
    }

    public void setDbTableName(String value) {
        this.dbTableName = value;
    }

    public String getDbSchemaName() {
        return dbSchemaName;
    }

    public void setDbSchemaName(String value) {
        this.dbSchemaName = value;
    }

}
