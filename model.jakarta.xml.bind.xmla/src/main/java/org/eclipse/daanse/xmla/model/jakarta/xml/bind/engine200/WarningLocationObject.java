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
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WarningLocationObject", propOrder = { "warningColumn", "warningMeasure" })
public class WarningLocationObject implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "WarningColumn", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200")
    protected WarningColumn warningColumn;
    @XmlElement(name = "WarningMeasure", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200")
    protected WarningMeasure warningMeasure;

    public WarningColumn getWarningColumn() {
        return warningColumn;
    }

    public void setWarningColumn(WarningColumn value) {
        this.warningColumn = value;
    }

    public WarningMeasure getWarningMeasure() {
        return warningMeasure;
    }

    public void setWarningMeasure(WarningMeasure value) {
        this.warningMeasure = value;
    }

}
