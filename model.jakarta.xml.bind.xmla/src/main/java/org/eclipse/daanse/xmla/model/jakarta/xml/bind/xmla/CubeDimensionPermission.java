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
@XmlType(name = "CubeDimensionPermission", propOrder = {

})
public class CubeDimensionPermission {

    @XmlElement(name = "CubeDimensionID", required = true)
    protected String cubeDimensionID;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Read")
    protected String read;
    @XmlElement(name = "Write")
    protected String write;
    @XmlElement(name = "AttributePermission", type = AttributePermission.class)
    @XmlElementWrapper(name = "AttributePermissions")
    protected List<AttributePermission> attributePermissions;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;

    public String getCubeDimensionID() {
        return cubeDimensionID;
    }

    public void setCubeDimensionID(String value) {
        this.cubeDimensionID = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String value) {
        this.read = value;
    }

    public String getWrite() {
        return write;
    }

    public void setWrite(String value) {
        this.write = value;
    }

    public List<AttributePermission> getAttributePermissions() {
        return attributePermissions;
    }

    public void setAttributePermissions(List<AttributePermission> value) {
        this.attributePermissions = value;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

}
