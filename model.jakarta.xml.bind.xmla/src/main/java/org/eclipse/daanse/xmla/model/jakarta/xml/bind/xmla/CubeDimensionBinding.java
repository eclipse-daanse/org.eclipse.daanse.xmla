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
@XmlType(name = "CubeDimensionBinding", propOrder = { "dataSourceID", "cubeID", "cubeDimensionID", "filter" })
public class CubeDimensionBinding extends Binding {

    @XmlElement(name = "DataSourceID", required = true)
    protected String dataSourceID;
    @XmlElement(name = "CubeID", required = true)
    protected String cubeID;
    @XmlElement(name = "CubeDimensionID", required = true)
    protected String cubeDimensionID;
    @XmlElement(name = "Filter")
    protected String filter;

    public String getDataSourceID() {
        return dataSourceID;
    }

    public void setDataSourceID(String value) {
        this.dataSourceID = value;
    }

    public String getCubeID() {
        return cubeID;
    }

    public void setCubeID(String value) {
        this.cubeID = value;
    }

    public String getCubeDimensionID() {
        return cubeDimensionID;
    }

    public void setCubeDimensionID(String value) {
        this.cubeDimensionID = value;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String value) {
        this.filter = value;
    }
}
