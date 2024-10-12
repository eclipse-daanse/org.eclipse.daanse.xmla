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
@XmlType(name = "Annotation", propOrder = {

})
public class Annotation {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Visibility")
    protected String visibility;
    @XmlElement(name = "Value")
    protected java.lang.Object value;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String value) {
        this.visibility = value;
    }

    public java.lang.Object getValue() {
        return value;
    }

    public void setValue(java.lang.Object value) {
        this.value = value;
    }

}
