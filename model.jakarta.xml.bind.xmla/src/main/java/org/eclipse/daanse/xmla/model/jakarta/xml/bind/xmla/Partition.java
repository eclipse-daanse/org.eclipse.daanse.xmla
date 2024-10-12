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
@XmlType(name = "Partition", propOrder = {

})
public class Partition extends AbstractItem {

    @XmlElement(name = "Source")
    protected TabularBinding source;
    @XmlElement(name = "ProcessingPriority")
    protected BigInteger processingPriority;
    @XmlElement(name = "AggregationPrefix")
    protected String aggregationPrefix;
    @XmlElement(name = "StorageMode")
    protected Partition.StorageMode storageMode;
    @XmlElement(name = "ProcessingMode")
    protected String processingMode;
    @XmlElement(name = "ErrorConfiguration")
    protected ErrorConfiguration errorConfiguration;
    @XmlElement(name = "StorageLocation")
    protected String storageLocation;
    @XmlElement(name = "RemoteDatasourceID")
    protected String remoteDatasourceID;
    @XmlElement(name = "Slice")
    protected String slice;
    @XmlElement(name = "ProactiveCaching")
    protected ProactiveCaching proactiveCaching;
    @XmlElement(name = "Type")
    protected String type;
    @XmlElement(name = "EstimatedSize")
    protected Long estimatedSize;
    @XmlElement(name = "EstimatedRows")
    protected Long estimatedRows;
    @XmlElement(name = "CurrentStorageMode")
    protected Partition.CurrentStorageMode currentStorageMode;
    @XmlElement(name = "AggregationDesignID")
    protected String aggregationDesignID;
    @XmlElement(name = "AggregationInstance")
    @XmlElementWrapper(name = "AggregationInstances")
    protected List<AggregationInstance> aggregationInstances;
    @XmlElement(name = "AggregationInstanceSource")
    protected DataSourceViewBinding aggregationInstanceSource;
    @XmlElement(name = "LastProcessed")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastProcessed;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "StringStoresCompatibilityLevel", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2011/engine/300")
    protected Integer stringStoresCompatibilityLevel;
    @XmlElement(name = "CurrentStringStoresCompatibilityLevel", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2011/engine/300")
    protected Integer currentStringStoresCompatibilityLevel;
    @XmlElement(name = "DirectQueryUsage", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2011/engine/300/300")
    protected String directQueryUsage;

    public TabularBinding getSource() {
        return source;
    }

    public void setSource(TabularBinding value) {
        this.source = value;
    }

    public BigInteger getProcessingPriority() {
        return processingPriority;
    }

    public void setProcessingPriority(BigInteger value) {
        this.processingPriority = value;
    }

    public String getAggregationPrefix() {
        return aggregationPrefix;
    }

    public void setAggregationPrefix(String value) {
        this.aggregationPrefix = value;
    }

    public Partition.StorageMode getStorageMode() {
        return storageMode;
    }

    public void setStorageMode(Partition.StorageMode value) {
        this.storageMode = value;
    }

    public String getProcessingMode() {
        return processingMode;
    }

    public void setProcessingMode(String value) {
        this.processingMode = value;
    }

    public ErrorConfiguration getErrorConfiguration() {
        return errorConfiguration;
    }

    public void setErrorConfiguration(ErrorConfiguration value) {
        this.errorConfiguration = value;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String value) {
        this.storageLocation = value;
    }

    public String getRemoteDatasourceID() {
        return remoteDatasourceID;
    }

    public void setRemoteDatasourceID(String value) {
        this.remoteDatasourceID = value;
    }

    public String getSlice() {
        return slice;
    }

    public void setSlice(String value) {
        this.slice = value;
    }

    public ProactiveCaching getProactiveCaching() {
        return proactiveCaching;
    }

    public void setProactiveCaching(ProactiveCaching value) {
        this.proactiveCaching = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public Long getEstimatedSize() {
        return estimatedSize;
    }

    public void setEstimatedSize(Long value) {
        this.estimatedSize = value;
    }

    public Long getEstimatedRows() {
        return estimatedRows;
    }

    public void setEstimatedRows(Long value) {
        this.estimatedRows = value;
    }

    public Partition.CurrentStorageMode getCurrentStorageMode() {
        return currentStorageMode;
    }

    public void setCurrentStorageMode(Partition.CurrentStorageMode value) {
        this.currentStorageMode = value;
    }

    public String getAggregationDesignID() {
        return aggregationDesignID;
    }

    public void setAggregationDesignID(String value) {
        this.aggregationDesignID = value;
    }

    public List<AggregationInstance> getAggregationInstances() {
        return aggregationInstances;
    }

    public void setAggregationInstances(List<AggregationInstance> value) {
        this.aggregationInstances = value;
    }

    public DataSourceViewBinding getAggregationInstanceSource() {
        return aggregationInstanceSource;
    }

    public void setAggregationInstanceSource(DataSourceViewBinding value) {
        this.aggregationInstanceSource = value;
    }

    public XMLGregorianCalendar getLastProcessed() {
        return lastProcessed;
    }

    public void setLastProcessed(XMLGregorianCalendar value) {
        this.lastProcessed = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String value) {
        this.state = value;
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

    public String getDirectQueryUsage() {
        return directQueryUsage;
    }

    public void setDirectQueryUsage(String value) {
        this.directQueryUsage = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "value" })
    public static class CurrentStorageMode {

        @XmlValue
        protected PartitionCurrentStorageModeEnumType value;
        @XmlAttribute(name = "valuens")
        protected String valuens;

        public PartitionCurrentStorageModeEnumType getValue() {
            return value;
        }

        public void setValue(PartitionCurrentStorageModeEnumType value) {
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
    public static class StorageMode {

        @XmlValue
        protected PartitionStorageModeEnumType value;
        @XmlAttribute(name = "valuens")
        protected String valuens;

        public PartitionStorageModeEnumType getValue() {
            return value;
        }

        public void setValue(PartitionStorageModeEnumType value) {
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
