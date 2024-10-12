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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine300_300;

import java.io.Serializable;
import java.util.List;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine300.RelationshipEndVisualizationProperties;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelationshipEnd", propOrder = { "role", "multiplicity", "dimensionID", "attributes", "translations",
        "visualizationProperties" })
public class RelationshipEnd implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "Role", required = true)
    protected String role;
    @XmlElement(name = "Multiplicity", required = true)
    protected String multiplicity;
    @XmlElement(name = "DimensionID", required = true)
    protected String dimensionID;
    @XmlElement(name = "Attributes")
    protected RelationshipEnd.Attributes attributes;
    @XmlElement(name = "Translations")
    protected RelationshipEnd.Translations translations;
    @XmlElement(name = "VisualizationProperties")
    protected RelationshipEndVisualizationProperties visualizationProperties;

    public String getRole() {
        return role;
    }

    public void setRole(String value) {
        this.role = value;
    }

    public String getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(String value) {
        this.multiplicity = value;
    }

    public String getDimensionID() {
        return dimensionID;
    }

    public void setDimensionID(String value) {
        this.dimensionID = value;
    }

    public RelationshipEnd.Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(RelationshipEnd.Attributes value) {
        this.attributes = value;
    }

    public RelationshipEnd.Translations getTranslations() {
        return translations;
    }

    public void setTranslations(RelationshipEnd.Translations value) {
        this.translations = value;
    }

    public RelationshipEndVisualizationProperties getVisualizationProperties() {
        return visualizationProperties;
    }

    public void setVisualizationProperties(RelationshipEndVisualizationProperties value) {
        this.visualizationProperties = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "attribute" })
    public static class Attributes implements Serializable {

        private static final long serialVersionUID = 1L;
        @XmlElement(name = "Attribute")
        private List<RelationshipEnd.Attributes.Attribute> attribute;

        public List<RelationshipEnd.Attributes.Attribute> getAttribute() {
            return this.attribute;
        }

        public void setAttribute(List<Attribute> attribute) {
            this.attribute = attribute;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "attributeID" })
        public static class Attribute implements Serializable {

            private static final long serialVersionUID = 1L;
            @XmlElement(name = "AttributeID", required = true)
            protected String attributeID;

            public String getAttributeID() {
                return attributeID;
            }

            public void setAttributeID(String value) {
                this.attributeID = value;
            }

        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "translation" })
    public static class Translations implements Serializable {

        private static final long serialVersionUID = 1L;
        @XmlElement(name = "Translation")
        private List<RelationshipEndTranslation> translation;

        public List<RelationshipEndTranslation> getTranslation() {
            return this.translation;
        }

        public void setTranslation(List<RelationshipEndTranslation> translation) {
            this.translation = translation;
        }
    }

}
