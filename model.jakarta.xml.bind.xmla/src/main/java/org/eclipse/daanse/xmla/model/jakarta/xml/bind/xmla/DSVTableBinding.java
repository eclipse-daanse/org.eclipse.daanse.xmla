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
@XmlType(name = "DSVTableBinding", propOrder = { "dataSourceViewID", "tableID", "dataEmbeddingStyle" })
public class DSVTableBinding extends TabularBinding {

    @XmlElement(name = "DataSourceViewID")
    protected String dataSourceViewID;
    @XmlElement(name = "TableID", required = true)
    protected String tableID;
    @XmlElement(name = "DataEmbeddingStyle", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300")
    protected String dataEmbeddingStyle;

    public String getDataSourceViewID() {
        return dataSourceViewID;
    }

    public void setDataSourceViewID(String value) {
        this.dataSourceViewID = value;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String value) {
        this.tableID = value;
    }

    public String getDataEmbeddingStyle() {
        return dataEmbeddingStyle;
    }

    public void setDataEmbeddingStyle(String value) {
        this.dataEmbeddingStyle = value;
    }

}
