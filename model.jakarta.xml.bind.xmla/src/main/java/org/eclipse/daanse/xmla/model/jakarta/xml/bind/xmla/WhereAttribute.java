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
@XmlType(name = "Where_Attribute", propOrder = {

})
public class WhereAttribute {

    @XmlElement(name = "AttributeName", required = true)
    protected String attributeName;
    @XmlElement(name = "Key", type = java.lang.Object.class)
    @XmlElementWrapper(name = "Keys")
    protected List<java.lang.Object> keys;

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String value) {
        this.attributeName = value;
    }

    public List<java.lang.Object> getKeys() {
        return keys;
    }

    public void setKeys(List<java.lang.Object> value) {
        this.keys = value;
    }

}
