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

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine300.AttributeHierarchyProcessingStateXmlEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine300.DimensionAttributeVisualizationProperties;

import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DimensionAttribute", propOrder = {

})
public class DimensionAttribute {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Type")
    protected DimensionAttribute.Type type;
    @XmlElement(name = "Usage")
    protected String usage;
    @XmlElement(name = "Source")
    protected Binding source;
    @XmlElement(name = "EstimatedCount")
    protected Long estimatedCount;
    @XmlElement(name = "KeyColumn", required = true)
    @XmlElementWrapper(name = "KeyColumns", required = true)
    protected List<DataItem> keyColumns;
    @XmlElement(name = "NameColumn")
    protected DataItem nameColumn;
    @XmlElement(name = "ValueColumn")
    protected DataItem valueColumn;
    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<AttributeTranslation> translations;
    @XmlElement(name = "AttributeRelationship")
    @XmlElementWrapper(name = "AttributeRelationships")
    protected List<AttributeRelationship> attributeRelationships;
    @XmlElement(name = "DiscretizationMethod")
    protected String discretizationMethod;
    @XmlElement(name = "DiscretizationBucketCount")
    protected BigInteger discretizationBucketCount;
    @XmlElement(name = "RootMemberIf")
    protected String rootMemberIf;
    @XmlElement(name = "OrderBy")
    protected String orderBy;
    @XmlElement(name = "DefaultMember")
    protected String defaultMember;
    @XmlElement(name = "OrderByAttributeID")
    protected String orderByAttributeID;
    @XmlElement(name = "SkippedLevelsColumn")
    protected DataItem skippedLevelsColumn;
    @XmlElement(name = "NamingTemplate")
    protected String namingTemplate;
    @XmlElement(name = "MembersWithData")
    protected String membersWithData;
    @XmlElement(name = "MembersWithDataCaption")
    protected String membersWithDataCaption;
    @XmlElement(name = "NamingTemplateTranslation")
    @XmlElementWrapper(name = "NamingTemplateTranslations")
    protected List<Translation> namingTemplateTranslations;
    @XmlElement(name = "CustomRollupColumn")
    protected DataItem customRollupColumn;
    @XmlElement(name = "CustomRollupPropertiesColumn")
    protected DataItem customRollupPropertiesColumn;
    @XmlElement(name = "UnaryOperatorColumn")
    protected DataItem unaryOperatorColumn;
    @XmlElement(name = "AttributeHierarchyOrdered")
    protected Boolean attributeHierarchyOrdered;
    @XmlElement(name = "MemberNamesUnique")
    protected Boolean memberNamesUnique;
    @XmlElement(name = "IsAggregatable")
    protected Boolean isAggregatable;
    @XmlElement(name = "AttributeHierarchyEnabled")
    protected Boolean attributeHierarchyEnabled;
    @XmlElement(name = "AttributeHierarchyOptimizedState")
    protected String attributeHierarchyOptimizedState;
    @XmlElement(name = "AttributeHierarchyVisible")
    protected Boolean attributeHierarchyVisible;
    @XmlElement(name = "AttributeHierarchyDisplayFolder")
    protected String attributeHierarchyDisplayFolder;
    @XmlElement(name = "KeyUniquenessGuarantee")
    protected Boolean keyUniquenessGuarantee;
    @XmlElement(name = "GroupingBehavior")
    protected String groupingBehavior;
    @XmlElement(name = "InstanceSelection")
    protected String instanceSelection;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;
    @XmlElement(name = "ProcessingState")
    protected String processingState;
    @XmlElement(name = "AttributeHierarchyProcessingState")
    @XmlSchemaType(name = "string")
    protected AttributeHierarchyProcessingStateXmlEnum attributeHierarchyProcessingState;
    @XmlElement(name = "VisualizationProperties")
    protected DimensionAttributeVisualizationProperties visualizationProperties;
    @XmlElement(name = "ExtendedType")
    protected String extendedType;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getID() {
        return id;
    }

    public void setID(String value) {
        this.id = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public DimensionAttribute.Type getType() {
        return type;
    }

    public void setType(DimensionAttribute.Type value) {
        this.type = value;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String value) {
        this.usage = value;
    }

    public Binding getSource() {
        return source;
    }

    public void setSource(Binding value) {
        this.source = value;
    }

    public Long getEstimatedCount() {
        return estimatedCount;
    }

    public void setEstimatedCount(Long value) {
        this.estimatedCount = value;
    }

    public List<DataItem> getKeyColumns() {
        return keyColumns;
    }

    public void setKeyColumns(List<DataItem> value) {
        this.keyColumns = value;
    }

    public DataItem getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(DataItem value) {
        this.nameColumn = value;
    }

    public DataItem getValueColumn() {
        return valueColumn;
    }

    public void setValueColumn(DataItem value) {
        this.valueColumn = value;
    }

    public List<AttributeTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<AttributeTranslation> value) {
        this.translations = value;
    }

    public List<AttributeRelationship> getAttributeRelationships() {
        return attributeRelationships;
    }

    public void setAttributeRelationships(List<AttributeRelationship> value) {
        this.attributeRelationships = value;
    }

    public String getDiscretizationMethod() {
        return discretizationMethod;
    }

    public void setDiscretizationMethod(String value) {
        this.discretizationMethod = value;
    }

    public BigInteger getDiscretizationBucketCount() {
        return discretizationBucketCount;
    }

    public void setDiscretizationBucketCount(BigInteger value) {
        this.discretizationBucketCount = value;
    }

    public String getRootMemberIf() {
        return rootMemberIf;
    }

    public void setRootMemberIf(String value) {
        this.rootMemberIf = value;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String value) {
        this.orderBy = value;
    }

    public String getDefaultMember() {
        return defaultMember;
    }

    public void setDefaultMember(String value) {
        this.defaultMember = value;
    }

    public String getOrderByAttributeID() {
        return orderByAttributeID;
    }

    public void setOrderByAttributeID(String value) {
        this.orderByAttributeID = value;
    }

    public DataItem getSkippedLevelsColumn() {
        return skippedLevelsColumn;
    }

    public void setSkippedLevelsColumn(DataItem value) {
        this.skippedLevelsColumn = value;
    }

    public String getNamingTemplate() {
        return namingTemplate;
    }

    public void setNamingTemplate(String value) {
        this.namingTemplate = value;
    }

    public String getMembersWithData() {
        return membersWithData;
    }

    public void setMembersWithData(String value) {
        this.membersWithData = value;
    }

    public String getMembersWithDataCaption() {
        return membersWithDataCaption;
    }

    public void setMembersWithDataCaption(String value) {
        this.membersWithDataCaption = value;
    }

    public List<Translation> getNamingTemplateTranslations() {
        return namingTemplateTranslations;
    }

    public void setNamingTemplateTranslations(List<Translation> value) {
        this.namingTemplateTranslations = value;
    }

    public DataItem getCustomRollupColumn() {
        return customRollupColumn;
    }

    public void setCustomRollupColumn(DataItem value) {
        this.customRollupColumn = value;
    }

    public DataItem getCustomRollupPropertiesColumn() {
        return customRollupPropertiesColumn;
    }

    public void setCustomRollupPropertiesColumn(DataItem value) {
        this.customRollupPropertiesColumn = value;
    }

    public DataItem getUnaryOperatorColumn() {
        return unaryOperatorColumn;
    }

    public void setUnaryOperatorColumn(DataItem value) {
        this.unaryOperatorColumn = value;
    }

    public Boolean isAttributeHierarchyOrdered() {
        return attributeHierarchyOrdered;
    }

    public void setAttributeHierarchyOrdered(Boolean value) {
        this.attributeHierarchyOrdered = value;
    }

    public Boolean isMemberNamesUnique() {
        return memberNamesUnique;
    }

    public void setMemberNamesUnique(Boolean value) {
        this.memberNamesUnique = value;
    }

    public Boolean isIsAggregatable() {
        return isAggregatable;
    }

    public void setIsAggregatable(Boolean value) {
        this.isAggregatable = value;
    }

    public Boolean isAttributeHierarchyEnabled() {
        return attributeHierarchyEnabled;
    }

    public void setAttributeHierarchyEnabled(Boolean value) {
        this.attributeHierarchyEnabled = value;
    }

    public String getAttributeHierarchyOptimizedState() {
        return attributeHierarchyOptimizedState;
    }

    public void setAttributeHierarchyOptimizedState(String value) {
        this.attributeHierarchyOptimizedState = value;
    }

    public Boolean isAttributeHierarchyVisible() {
        return attributeHierarchyVisible;
    }

    public void setAttributeHierarchyVisible(Boolean value) {
        this.attributeHierarchyVisible = value;
    }

    public String getAttributeHierarchyDisplayFolder() {
        return attributeHierarchyDisplayFolder;
    }

    public void setAttributeHierarchyDisplayFolder(String value) {
        this.attributeHierarchyDisplayFolder = value;
    }

    public Boolean isKeyUniquenessGuarantee() {
        return keyUniquenessGuarantee;
    }

    public void setKeyUniquenessGuarantee(Boolean value) {
        this.keyUniquenessGuarantee = value;
    }

    public String getGroupingBehavior() {
        return groupingBehavior;
    }

    public void setGroupingBehavior(String value) {
        this.groupingBehavior = value;
    }

    public String getInstanceSelection() {
        return instanceSelection;
    }

    public void setInstanceSelection(String value) {
        this.instanceSelection = value;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

    public String getProcessingState() {
        return processingState;
    }

    public void setProcessingState(String value) {
        this.processingState = value;
    }

    public AttributeHierarchyProcessingStateXmlEnum getAttributeHierarchyProcessingState() {
        return attributeHierarchyProcessingState;
    }

    public void setAttributeHierarchyProcessingState(AttributeHierarchyProcessingStateXmlEnum value) {
        this.attributeHierarchyProcessingState = value;
    }

    public DimensionAttributeVisualizationProperties getVisualizationProperties() {
        return visualizationProperties;
    }

    public void setVisualizationProperties(DimensionAttributeVisualizationProperties value) {
        this.visualizationProperties = value;
    }

    public String getExtendedType() {
        return extendedType;
    }

    public void setExtendedType(String value) {
        this.extendedType = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "value" })
    public static class Type {

        @XmlValue
        protected DimensionAttributeTypeEnumTypeXmlEnum value;
        @XmlAttribute(name = "valuens")
        protected String valuens;

        public DimensionAttributeTypeEnumTypeXmlEnum getValue() {
            return value;
        }

        public void setValue(DimensionAttributeTypeEnumTypeXmlEnum value) {
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
