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
@XmlType(name = "AttributePermission", propOrder = {

})
public class AttributePermission {

    @XmlElement(name = "AttributeID", required = true)
    protected String attributeID;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "DefaultMember")
    protected String defaultMember;
    @XmlElement(name = "VisualTotals")
    protected String visualTotals;
    @XmlElement(name = "AllowedSet")
    protected String allowedSet;
    @XmlElement(name = "DeniedSet")
    protected String deniedSet;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;

    public String getAttributeID() {
        return attributeID;
    }

    public void setAttributeID(String value) {
        this.attributeID = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDefaultMember() {
        return defaultMember;
    }

    public void setDefaultMember(String value) {
        this.defaultMember = value;
    }

    public String getVisualTotals() {
        return visualTotals;
    }

    public void setVisualTotals(String value) {
        this.visualTotals = value;
    }

    public String getAllowedSet() {
        return allowedSet;
    }

    public void setAllowedSet(String value) {
        this.allowedSet = value;
    }

    public String getDeniedSet() {
        return deniedSet;
    }

    public void setDeniedSet(String value) {
        this.deniedSet = value;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

}
