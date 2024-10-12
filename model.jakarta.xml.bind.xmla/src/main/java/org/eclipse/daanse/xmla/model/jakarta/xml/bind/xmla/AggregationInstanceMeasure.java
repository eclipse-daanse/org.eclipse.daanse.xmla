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
@XmlType(name = "AggregationInstanceMeasure", propOrder = {

})
public class AggregationInstanceMeasure {

    @XmlElement(name = "MeasureID", required = true)
    protected String measureID;
    @XmlElement(name = "Source", required = true)
    protected ColumnBinding source;

    public String getMeasureID() {
        return measureID;
    }

    public void setMeasureID(String value) {
        this.measureID = value;
    }

    public ColumnBinding getSource() {
        return source;
    }

    public void setSource(ColumnBinding value) {
        this.source = value;
    }

}
