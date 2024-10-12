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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MeasureGroup", propOrder = {

})
public class MeasureGroup extends AbstractItem {

    @XmlElement(name = "LastProcessed")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastProcessed;
    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<Translation> translations;
    @XmlElement(name = "Type")
    protected String type;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "Measure", required = true)
    @XmlElementWrapper(name = "Measures", required = true)
    protected List<Measure> measures;
    @XmlElement(name = "DataAggregation")
    protected String dataAggregation;
    @XmlElement(name = "Source")
    protected MeasureGroupBinding source;
    @XmlElement(name = "StorageMode")
    protected MeasureGroup.StorageMode storageMode;
    @XmlElement(name = "StorageLocation")
    protected String storageLocation;
    @XmlElement(name = "IgnoreUnrelatedDimensions")
    protected Boolean ignoreUnrelatedDimensions;
    @XmlElement(name = "ProactiveCaching")
    protected ProactiveCaching proactiveCaching;
    @XmlElement(name = "EstimatedRows")
    protected Long estimatedRows;
    @XmlElement(name = "ErrorConfiguration")
    protected ErrorConfiguration errorConfiguration;
    @XmlElement(name = "EstimatedSize")
    protected Long estimatedSize;
    @XmlElement(name = "ProcessingMode")
    protected String processingMode;
    @XmlElement(name = "Dimension", required = true)
    @XmlElementWrapper(name = "Dimensions", required = true)
    protected List<MeasureGroupDimension> dimensions;
    @XmlElement(name = "Partition")
    @XmlElementWrapper(name = "Partitions")
    protected List<Partition> partitions;
    @XmlElement(name = "AggregationPrefix")
    protected String aggregationPrefix;
    @XmlElement(name = "ProcessingPriority")
    protected BigInteger processingPriority;
    @XmlElement(name = "AggregationDesign")
    @XmlElementWrapper(name = "AggregationDesigns")
    protected List<AggregationDesign> aggregationDesigns;

    public XMLGregorianCalendar getLastProcessed() {
        return lastProcessed;
    }

    public void setLastProcessed(XMLGregorianCalendar value) {
        this.lastProcessed = value;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> value) {
        this.translations = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String value) {
        this.state = value;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> value) {
        this.measures = value;
    }

    public String getDataAggregation() {
        return dataAggregation;
    }

    public void setDataAggregation(String value) {
        this.dataAggregation = value;
    }

    public MeasureGroupBinding getSource() {
        return source;
    }

    public void setSource(MeasureGroupBinding value) {
        this.source = value;
    }

    public MeasureGroup.StorageMode getStorageMode() {
        return storageMode;
    }

    public void setStorageMode(MeasureGroup.StorageMode value) {
        this.storageMode = value;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String value) {
        this.storageLocation = value;
    }

    public Boolean isIgnoreUnrelatedDimensions() {
        return ignoreUnrelatedDimensions;
    }

    public void setIgnoreUnrelatedDimensions(Boolean value) {
        this.ignoreUnrelatedDimensions = value;
    }

    public ProactiveCaching getProactiveCaching() {
        return proactiveCaching;
    }

    public void setProactiveCaching(ProactiveCaching value) {
        this.proactiveCaching = value;
    }

    public Long getEstimatedRows() {
        return estimatedRows;
    }

    public void setEstimatedRows(Long value) {
        this.estimatedRows = value;
    }

    public ErrorConfiguration getErrorConfiguration() {
        return errorConfiguration;
    }

    public void setErrorConfiguration(ErrorConfiguration value) {
        this.errorConfiguration = value;
    }

    public Long getEstimatedSize() {
        return estimatedSize;
    }

    public void setEstimatedSize(Long value) {
        this.estimatedSize = value;
    }

    public String getProcessingMode() {
        return processingMode;
    }

    public void setProcessingMode(String value) {
        this.processingMode = value;
    }

    public List<MeasureGroupDimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<MeasureGroupDimension> value) {
        this.dimensions = value;
    }

    public List<Partition> getPartitions() {
        return partitions;
    }

    public void setPartitions(List<Partition> value) {
        this.partitions = value;
    }

    public String getAggregationPrefix() {
        return aggregationPrefix;
    }

    public void setAggregationPrefix(String value) {
        this.aggregationPrefix = value;
    }

    public BigInteger getProcessingPriority() {
        return processingPriority;
    }

    public void setProcessingPriority(BigInteger value) {
        this.processingPriority = value;
    }

    public List<AggregationDesign> getAggregationDesigns() {
        return aggregationDesigns;
    }

    public void setAggregationDesigns(List<AggregationDesign> value) {
        this.aggregationDesigns = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "value" })
    public static class StorageMode {

        @XmlValue
        protected MeasureGroupStorageModeEnumType value;
        @XmlAttribute(name = "valuens")
        protected String valuens;

        public MeasureGroupStorageModeEnumType getValue() {
            return value;
        }

        public void setValue(MeasureGroupStorageModeEnumType value) {
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
