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

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.Row;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscoverEnumeratorsResponseRowXml")
public class DiscoverEnumeratorsResponseRowXml extends Row implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 200492007073962307L;

    @XmlElement(name = "EnumName", required = true)
    private String enumName;

    @XmlElement(name = "EnumDescription", required = true)
    private String enumDescription;

    @XmlElement(name = "EnumType", required = true)
    private String enumType;

    @XmlElement(name = "ElementName", required = true)
    private String elementName;

    @XmlElement(name = "ElementDescription", required = true)
    private String elementDescription;

    @XmlElement(name = "ElementValue", required = true)
    private String elementValue;

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public String getEnumDescription() {
        return enumDescription;
    }

    public void setEnumDescription(String enumDescription) {
        this.enumDescription = enumDescription;
    }

    public String getEnumType() {
        return enumType;
    }

    public void setEnumType(String enumType) {
        this.enumType = enumType;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementDescription() {
        return elementDescription;
    }

    public void setElementDescription(String elementDescription) {
        this.elementDescription = elementDescription;
    }

    public String getElementValue() {
        return elementValue;
    }

    public void setElementValue(String elementValue) {
        this.elementValue = elementValue;
    }

}
