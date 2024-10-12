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
@XmlType(name = "ServerProperty", propOrder = {

})
public class ServerProperty {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Value", required = true)
    protected String value;
    @XmlElement(name = "RequiresRestart")
    protected Boolean requiresRestart;
    @XmlElement(name = "PendingValue")
    protected java.lang.Object pendingValue;
    @XmlElement(name = "DefaultValue")
    protected java.lang.Object defaultValue;
    @XmlElement(name = "DisplayFlag")
    protected Boolean displayFlag;
    @XmlElement(name = "Type")
    protected String type;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean isRequiresRestart() {
        return requiresRestart;
    }

    public void setRequiresRestart(Boolean value) {
        this.requiresRestart = value;
    }

    public java.lang.Object getPendingValue() {
        return pendingValue;
    }

    public void setPendingValue(java.lang.Object value) {
        this.pendingValue = value;
    }

    public java.lang.Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(java.lang.Object value) {
        this.defaultValue = value;
    }

    public Boolean isDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(Boolean value) {
        this.displayFlag = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

}
