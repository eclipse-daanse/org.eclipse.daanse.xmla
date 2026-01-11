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
@XmlType(name = "CubeAttribute", propOrder = {

})
public class CubeAttribute {

    @XmlElement(name = "AttributeID", required = true)
    protected String attributeID;
    @XmlElement(name = "AggregationUsage")
    protected String aggregationUsage;
    @XmlElement(name = "AttributeHierarchyOptimizedState")
    protected String attributeHierarchyOptimizedState;
    @XmlElement(name = "AttributeHierarchyEnabled")
    protected Boolean attributeHierarchyEnabled;
    @XmlElement(name = "AttributeHierarchyVisible")
    protected Boolean attributeHierarchyVisible;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;

    public String getAttributeID() {
        return attributeID;
    }

    public void setAttributeID(String value) {
        this.attributeID = value;
    }

    public String getAggregationUsage() {
        return aggregationUsage;
    }

    public void setAggregationUsage(String value) {
        this.aggregationUsage = value;
    }

    public String getAttributeHierarchyOptimizedState() {
        return attributeHierarchyOptimizedState;
    }

    public void setAttributeHierarchyOptimizedState(String value) {
        this.attributeHierarchyOptimizedState = value;
    }

    public Boolean isAttributeHierarchyEnabled() {
        return attributeHierarchyEnabled;
    }

    public void setAttributeHierarchyEnabled(Boolean value) {
        this.attributeHierarchyEnabled = value;
    }

    public Boolean isAttributeHierarchyVisible() {
        return attributeHierarchyVisible;
    }

    public void setAttributeHierarchyVisible(Boolean value) {
        this.attributeHierarchyVisible = value;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

}
