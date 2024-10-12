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
@XmlType(name = "PerspectiveDimension", propOrder = {

})
public class PerspectiveDimension {

    @XmlElement(name = "CubeDimensionID", required = true)
    protected String cubeDimensionID;
    @XmlElement(name = "Attribute", type = PerspectiveAttribute.class)
    @XmlElementWrapper(name = "Attributes")
    protected List<PerspectiveAttribute> attributes;
    @XmlElement(name = "Hierarchy")
    @XmlElementWrapper(name = "Hierarchies")
    protected List<PerspectiveHierarchy> hierarchies;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;

    public String getCubeDimensionID() {
        return cubeDimensionID;
    }

    public void setCubeDimensionID(String value) {
        this.cubeDimensionID = value;
    }

    public List<PerspectiveAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<PerspectiveAttribute> value) {
        this.attributes = value;
    }

    public List<PerspectiveHierarchy> getHierarchies() {
        return hierarchies;
    }

    public void setHierarchies(List<PerspectiveHierarchy> value) {
        this.hierarchies = value;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

}
