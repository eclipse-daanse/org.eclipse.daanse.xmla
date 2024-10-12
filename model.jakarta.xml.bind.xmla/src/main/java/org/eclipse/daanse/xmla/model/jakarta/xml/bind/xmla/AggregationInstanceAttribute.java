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

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AggregationInstanceAttribute", propOrder = {

})
public class AggregationInstanceAttribute {

    @XmlElement(name = "AttributeID", required = true)
    protected String attributeID;
    @XmlElement(name = "KeyColumn", required = true)
    @XmlElementWrapper(name = "KeyColumns")
    protected List<DataItem> keyColumns;

    public String getAttributeID() {
        return attributeID;
    }

    public void setAttributeID(String value) {
        this.attributeID = value;
    }

    public List<DataItem> getKeyColumns() {
        return keyColumns;
    }

    public void setKeyColumns(List<DataItem> value) {
        this.keyColumns = value;
    }

}
