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
@XmlType(name = "AttributeRelationship", propOrder = {

})
public class AttributeRelationship {

    @XmlElement(name = "AttributeID", required = true)
    protected String attributeID;
    @XmlElement(name = "RelationshipType")
    protected String relationshipType;
    @XmlElement(name = "Cardinality")
    protected String cardinality;
    @XmlElement(name = "Optionality")
    protected String optionality;
    @XmlElement(name = "OverrideBehavior")
    protected String overrideBehavior;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Visible")
    protected Boolean visible;
    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<Translation> translations;

    public String getAttributeID() {
        return attributeID;
    }

    public void setAttributeID(String value) {
        this.attributeID = value;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String value) {
        this.relationshipType = value;
    }

    public String getCardinality() {
        return cardinality;
    }

    public void setCardinality(String value) {
        this.cardinality = value;
    }

    public String getOptionality() {
        return optionality;
    }

    public void setOptionality(String value) {
        this.optionality = value;
    }

    public String getOverrideBehavior() {
        return overrideBehavior;
    }

    public void setOverrideBehavior(String value) {
        this.overrideBehavior = value;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean value) {
        this.visible = value;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> value) {
        this.translations = value;
    }

}
