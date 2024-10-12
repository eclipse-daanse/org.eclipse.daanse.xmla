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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OlapInfo", propOrder = { "cubeInfo", "axesInfo", "cellInfo" })
public class OlapInfo {

    @XmlElement(name = "CubeInfo", required = true)
    protected CubeInfo cubeInfo;
    @XmlElement(name = "AxesInfo", required = true)
    protected AxesInfo axesInfo;
    @XmlElement(name = "CellInfo", required = true)
    protected CellInfo cellInfo;

    public CubeInfo getCubeInfo() {
        return cubeInfo;
    }

    public void setCubeInfo(CubeInfo value) {
        this.cubeInfo = value;
    }

    public AxesInfo getAxesInfo() {
        return axesInfo;
    }

    public void setAxesInfo(AxesInfo value) {
        this.axesInfo = value;
    }

    public CellInfo getCellInfo() {
        return cellInfo;
    }

    public void setCellInfo(CellInfo value) {
        this.cellInfo = value;
    }

}
