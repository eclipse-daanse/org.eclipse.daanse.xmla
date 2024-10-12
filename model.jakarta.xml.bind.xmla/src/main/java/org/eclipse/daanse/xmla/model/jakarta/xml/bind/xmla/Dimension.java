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

import java.math.BigInteger;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine300_300.Relationships;

import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dimension", propOrder = {

})
public class Dimension extends AbstractItem {

    @XmlElement(name = "Source")
    protected Binding source;
    @XmlElement(name = "MiningModelID")
    protected String miningModelID;
    @XmlElement(name = "Type")
    protected String type;
    @XmlElement(name = "UnknownMember")
    protected Dimension.UnknownMember unknownMember;
    @XmlElement(name = "MdxMissingMemberMode")
    protected String mdxMissingMemberMode;
    @XmlElement(name = "ErrorConfiguration")
    protected ErrorConfiguration errorConfiguration;
    @XmlElement(name = "StorageMode", required = true)
    protected String storageMode;
    @XmlElement(name = "WriteEnabled")
    protected Boolean writeEnabled;
    @XmlElement(name = "ProcessingPriority")
    protected BigInteger processingPriority;
    @XmlElement(name = "LastProcessed")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastProcessed;
    @XmlElement(name = "DimensionPermission")
    @XmlElementWrapper(name = "DimensionPermissions")
    protected List<DimensionPermission> dimensionPermissions;
    @XmlElement(name = "DependsOnDimensionID")
    protected String dependsOnDimensionID;
    @XmlElement(name = "Language")
    protected BigInteger language;
    @XmlElement(name = "Collation")
    protected String collation;
    @XmlElement(name = "UnknownMemberName")
    protected String unknownMemberName;
    @XmlElement(name = "UnknownMemberTranslation")
    @XmlElementWrapper(name = "UnknownMemberTranslations")
    protected List<Translation> unknownMemberTranslations;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "ProactiveCaching")
    protected ProactiveCaching proactiveCaching;
    @XmlElement(name = "ProcessingMode")
    protected String processingMode;
    @XmlElement(name = "ProcessingGroup")
    protected String processingGroup;
    @XmlElement(name = "CurrentStorageMode")
    protected Dimension.CurrentStorageMode currentStorageMode;
    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<Translation> translations;
    @XmlElement(name = "Attribute", type = DimensionAttribute.class)
    @XmlElementWrapper(name = "Attributes")
    protected List<DimensionAttribute> attributes;
    @XmlElement(name = "AttributeAllMemberName")
    protected String attributeAllMemberName;
    @XmlElement(name = "AttributeAllMemberTranslation")
    @XmlElementWrapper(name = "AttributeAllMemberTranslations")
    protected List<Translation> attributeAllMemberTranslations;
    @XmlElement(name = "Hierarchy")
    @XmlElementWrapper(name = "Hierarchies")
    protected List<Hierarchy> hierarchies;
    @XmlElement(name = "ProcessingRecommendation", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2010/engine/200/200")
    protected String processingRecommendation;
    @XmlElement(name = "Relationships")
    protected Relationships relationships;
    @XmlElement(name = "StringStoresCompatibilityLevel", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2011/engine/300")
    protected Integer stringStoresCompatibilityLevel;
    @XmlElement(name = "CurrentStringStoresCompatibilityLevel", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2011/engine/300")
    protected Integer currentStringStoresCompatibilityLevel;

    public Binding getSource() {
        return source;
    }

    public void setSource(Binding value) {
        this.source = value;
    }

    public String getMiningModelID() {
        return miningModelID;
    }

    public void setMiningModelID(String value) {
        this.miningModelID = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public Dimension.UnknownMember getUnknownMember() {
        return unknownMember;
    }

    public void setUnknownMember(Dimension.UnknownMember value) {
        this.unknownMember = value;
    }

    public String getMdxMissingMemberMode() {
        return mdxMissingMemberMode;
    }

    public void setMdxMissingMemberMode(String value) {
        this.mdxMissingMemberMode = value;
    }

    public ErrorConfiguration getErrorConfiguration() {
        return errorConfiguration;
    }

    public void setErrorConfiguration(ErrorConfiguration value) {
        this.errorConfiguration = value;
    }

    public String getStorageMode() {
        return storageMode;
    }

    public void setStorageMode(String value) {
        this.storageMode = value;
    }

    public Boolean isWriteEnabled() {
        return writeEnabled;
    }

    public void setWriteEnabled(Boolean value) {
        this.writeEnabled = value;
    }

    public BigInteger getProcessingPriority() {
        return processingPriority;
    }

    public void setProcessingPriority(BigInteger value) {
        this.processingPriority = value;
    }

    public XMLGregorianCalendar getLastProcessed() {
        return lastProcessed;
    }

    public void setLastProcessed(XMLGregorianCalendar value) {
        this.lastProcessed = value;
    }

    public List<DimensionPermission> getDimensionPermissions() {
        return dimensionPermissions;
    }

    public void setDimensionPermissions(List<DimensionPermission> value) {
        this.dimensionPermissions = value;
    }

    public String getDependsOnDimensionID() {
        return dependsOnDimensionID;
    }

    public void setDependsOnDimensionID(String value) {
        this.dependsOnDimensionID = value;
    }

    public BigInteger getLanguage() {
        return language;
    }

    public void setLanguage(BigInteger value) {
        this.language = value;
    }

    public String getCollation() {
        return collation;
    }

    public void setCollation(String value) {
        this.collation = value;
    }

    public String getUnknownMemberName() {
        return unknownMemberName;
    }

    public void setUnknownMemberName(String value) {
        this.unknownMemberName = value;
    }

    public List<Translation> getUnknownMemberTranslations() {
        return unknownMemberTranslations;
    }

    public void setUnknownMemberTranslations(List<Translation> value) {
        this.unknownMemberTranslations = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String value) {
        this.state = value;
    }

    public ProactiveCaching getProactiveCaching() {
        return proactiveCaching;
    }

    public void setProactiveCaching(ProactiveCaching value) {
        this.proactiveCaching = value;
    }

    public String getProcessingMode() {
        return processingMode;
    }

    public void setProcessingMode(String value) {
        this.processingMode = value;
    }

    public String getProcessingGroup() {
        return processingGroup;
    }

    public void setProcessingGroup(String value) {
        this.processingGroup = value;
    }

    public Dimension.CurrentStorageMode getCurrentStorageMode() {
        return currentStorageMode;
    }

    public void setCurrentStorageMode(Dimension.CurrentStorageMode value) {
        this.currentStorageMode = value;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> value) {
        this.translations = value;
    }

    public List<DimensionAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<DimensionAttribute> value) {
        this.attributes = value;
    }

    public String getAttributeAllMemberName() {
        return attributeAllMemberName;
    }

    public void setAttributeAllMemberName(String value) {
        this.attributeAllMemberName = value;
    }

    public List<Translation> getAttributeAllMemberTranslations() {
        return attributeAllMemberTranslations;
    }

    public void setAttributeAllMemberTranslations(List<Translation> value) {
        this.attributeAllMemberTranslations = value;
    }

    public List<Hierarchy> getHierarchies() {
        return hierarchies;
    }

    public void setHierarchies(List<Hierarchy> value) {
        this.hierarchies = value;
    }

    public String getProcessingRecommendation() {
        return processingRecommendation;
    }

    public void setProcessingRecommendation(String value) {
        this.processingRecommendation = value;
    }

    public Relationships getRelationships() {
        return relationships;
    }

    public void setRelationships(Relationships value) {
        this.relationships = value;
    }

    public Integer getStringStoresCompatibilityLevel() {
        return stringStoresCompatibilityLevel;
    }

    public void setStringStoresCompatibilityLevel(Integer value) {
        this.stringStoresCompatibilityLevel = value;
    }

    public Integer getCurrentStringStoresCompatibilityLevel() {
        return currentStringStoresCompatibilityLevel;
    }

    public void setCurrentStringStoresCompatibilityLevel(Integer value) {
        this.currentStringStoresCompatibilityLevel = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "value" })
    public static class CurrentStorageMode {

        @XmlValue
        protected DimensionCurrentStorageModeEnumType value;
        @XmlAttribute(name = "valuens")
        protected String valuens;

        public DimensionCurrentStorageModeEnumType getValue() {
            return value;
        }

        public void setValue(DimensionCurrentStorageModeEnumType value) {
            this.value = value;
        }

        public String getValuens() {
            return valuens;
        }

        public void setValuens(String value) {
            this.valuens = value;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "value" })
    public static class UnknownMember {

        @XmlValue
        protected UnknownMemberEnumType value;
        @XmlAttribute(name = "valuens")
        protected String valuens;

        public UnknownMemberEnumType getValue() {
            return value;
        }

        public void setValue(UnknownMemberEnumType value) {
            this.value = value;
        }

        public String getValuens() {
            return valuens;
        }

        public void setValuens(String value) {
            this.valuens = value;
        }
    }

}
