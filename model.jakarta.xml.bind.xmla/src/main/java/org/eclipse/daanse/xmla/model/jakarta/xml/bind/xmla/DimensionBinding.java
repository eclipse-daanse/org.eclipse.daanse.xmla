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

import javax.xml.datatype.Duration;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DimensionBinding", propOrder = { "dataSourceID", "dimensionID", "persistence", "refreshPolicy",
        "refreshInterval" })
public class DimensionBinding extends Binding {

    @XmlElement(name = "DataSourceID", required = true)
    protected String dataSourceID;
    @XmlElement(name = "DimensionID", required = true)
    protected String dimensionID;
    @XmlElement(name = "Persistence")
    protected String persistence;
    @XmlElement(name = "RefreshPolicy")
    protected String refreshPolicy;
    @XmlElement(name = "RefreshInterval")
    protected Duration refreshInterval;

    public String getDataSourceID() {
        return dataSourceID;
    }

    public void setDataSourceID(String value) {
        this.dataSourceID = value;
    }

    public String getDimensionID() {
        return dimensionID;
    }

    public void setDimensionID(String value) {
        this.dimensionID = value;
    }

    public String getPersistence() {
        return persistence;
    }

    public void setPersistence(String value) {
        this.persistence = value;
    }

    public String getRefreshPolicy() {
        return refreshPolicy;
    }

    public void setRefreshPolicy(String value) {
        this.refreshPolicy = value;
    }

    public Duration getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(Duration value) {
        this.refreshInterval = value;
    }
}
