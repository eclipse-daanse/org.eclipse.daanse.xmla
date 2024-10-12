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
@XmlRootElement(name = "WarningColumn")
public class WarningColumn implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "Dimension", required = true)
    protected String dimension;
    @XmlElement(name = "Attribute", required = true)
    protected String attribute;

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String value) {
        this.dimension = value;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String value) {
        this.attribute = value;
    }

}
