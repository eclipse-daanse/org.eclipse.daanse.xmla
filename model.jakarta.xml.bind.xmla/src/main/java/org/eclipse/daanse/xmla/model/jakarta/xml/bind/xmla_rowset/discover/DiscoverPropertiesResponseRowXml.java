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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.discover;

import java.io.Serializable;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.AccessEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.Row;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscoverPropertiesResponseRowXml")
public class DiscoverPropertiesResponseRowXml extends Row implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "PropertyName", required = true)
    private String propertyName;

    @XmlElement(name = "PropertyDescription", required = false)
    private String propertyDescription;

    @XmlElement(name = "PropertyType", required = false)
    private String propertyType;

    @XmlElement(name = "PropertyAccessType", required = true)
    private AccessEnum propertyAccessType;

    @XmlElement(name = "IsRequired", required = false)
    private Boolean required;

    @XmlElement(name = "Value", required = false)
    private String value;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public AccessEnum getPropertyAccessType() {
        return propertyAccessType;
    }

    public void setPropertyAccessType(AccessEnum propertyAccessType) {
        this.propertyAccessType = propertyAccessType;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
