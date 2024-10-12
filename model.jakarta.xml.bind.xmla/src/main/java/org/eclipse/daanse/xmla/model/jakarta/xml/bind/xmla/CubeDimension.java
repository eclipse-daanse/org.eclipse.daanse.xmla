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
@XmlType(name = "CubeDimension", propOrder = {

})
public class CubeDimension {

    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<Translation> translations;
    @XmlElement(name = "DimensionID", required = true)
    protected String dimensionID;
    @XmlElement(name = "Visible")
    protected Boolean visible;
    @XmlElement(name = "AllMemberAggregationUsage")
    protected String allMemberAggregationUsage;
    @XmlElement(name = "HierarchyUniqueNameStyle")
    protected String hierarchyUniqueNameStyle;
    @XmlElement(name = "MemberUniqueNameStyle")
    protected String memberUniqueNameStyle;
    @XmlElement(name = "Attribute", type = CubeAttribute.class)
    @XmlElementWrapper(name = "Attributes")
    protected List<CubeAttribute> attributes;
    @XmlElement(name = "Hierarchy")
    @XmlElementWrapper(name = "Hierarchies")
    protected List<CubeHierarchy> hierarchies;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;

    public String getID() {
        return id;
    }

    public void setID(String value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> value) {
        this.translations = value;
    }

    public String getDimensionID() {
        return dimensionID;
    }

    public void setDimensionID(String value) {
        this.dimensionID = value;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean value) {
        this.visible = value;
    }

    public String getAllMemberAggregationUsage() {
        return allMemberAggregationUsage;
    }

    public void setAllMemberAggregationUsage(String value) {
        this.allMemberAggregationUsage = value;
    }

    public String getHierarchyUniqueNameStyle() {
        return hierarchyUniqueNameStyle;
    }

    public void setHierarchyUniqueNameStyle(String value) {
        this.hierarchyUniqueNameStyle = value;
    }

    public String getMemberUniqueNameStyle() {
        return memberUniqueNameStyle;
    }

    public void setMemberUniqueNameStyle(String value) {
        this.memberUniqueNameStyle = value;
    }

    public List<CubeAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<CubeAttribute> value) {
        this.attributes = value;
    }

    public List<CubeHierarchy> getHierarchies() {
        return hierarchies;
    }

    public void setHierarchies(List<CubeHierarchy> value) {
        this.hierarchies = value;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

}
