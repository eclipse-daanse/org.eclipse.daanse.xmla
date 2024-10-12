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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cell", propOrder = {

})
public class Cell {

    @XmlElement(name = "Value", required = true)
    protected java.lang.Object value;
    @XmlAttribute(name = "CellOrdinal")
    protected Long cellOrdinal;

    public java.lang.Object getValue() {
        return value;
    }

    public void setValue(java.lang.Object value) {
        this.value = value;
    }

    public long getCellOrdinal() {
        return cellOrdinal;
    }

    public void setCellOrdinal(Long value) {
        this.cellOrdinal = value;
    }

}
