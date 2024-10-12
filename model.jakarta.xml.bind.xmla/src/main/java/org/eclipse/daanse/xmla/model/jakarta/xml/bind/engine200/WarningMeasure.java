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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine200;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "WarningMeasure")
public class WarningMeasure implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "Cube", required = true)
    protected String cube;
    @XmlElement(name = "MeasureGroup", required = true)
    protected String measureGroup;
    @XmlElement(name = "MeasureName", required = true)
    protected String measureName;

    public String getCube() {
        return cube;
    }

    public void setCube(String value) {
        this.cube = value;
    }

    public String getMeasureGroup() {
        return measureGroup;
    }

    public void setMeasureGroup(String value) {
        this.measureGroup = value;
    }

    public String getMeasureName() {
        return measureName;
    }

    public void setMeasureName(String value) {
        this.measureName = value;
    }

}
