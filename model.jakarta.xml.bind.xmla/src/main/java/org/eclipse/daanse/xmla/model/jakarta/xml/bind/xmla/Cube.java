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
@XmlType(name = "Cube", propOrder = {

})
public class Cube extends AbstractItem {

    @XmlElement(name = "Language")
    protected BigInteger language;
    @XmlElement(name = "Collation")
    protected String collation;
    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<Translation> translations;
    @XmlElement(name = "Dimension", required = true)
    @XmlElementWrapper(name = "Dimensions", required = true)
    protected List<CubeDimension> dimensions;
    @XmlElement(name = "CubePermission")
    @XmlElementWrapper(name = "CubePermissions")
    protected List<CubePermission> cubePermissions;
    @XmlElement(name = "MdxScript")
    @XmlElementWrapper(name = "MdxScripts")
    protected List<MdxScript> mdxScripts;
    @XmlElement(name = "Perspective")
    @XmlElementWrapper(name = "Perspectives")
    protected List<Perspective> perspectives;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "DefaultMeasure")
    protected String defaultMeasure;
    @XmlElement(name = "Visible")
    protected Boolean visible;
    @XmlElement(name = "MeasureGroup", required = true)
    @XmlElementWrapper(name = "MeasureGroups", required = true)
    protected List<MeasureGroup> measureGroups;
    @XmlElement(name = "Source")
    protected DataSourceViewBinding source;
    @XmlElement(name = "AggregationPrefix")
    protected String aggregationPrefix;
    @XmlElement(name = "ProcessingPriority")
    protected BigInteger processingPriority;
    @XmlElement(name = "StorageMode")
    protected Cube.StorageMode storageMode;
    @XmlElement(name = "ProcessingMode")
    protected String processingMode;
    @XmlElement(name = "ScriptCacheProcessingMode")
    protected String scriptCacheProcessingMode;
    @XmlElement(name = "ScriptErrorHandlingMode")
    protected String scriptErrorHandlingMode;
    @XmlElement(name = "DaxOptimizationMode", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2013/engine/800")
    protected String daxOptimizationMode;
    @XmlElement(name = "ProactiveCaching")
    protected ProactiveCaching proactiveCaching;
    @XmlElement(name = "Kpi")
    @XmlElementWrapper(name = "Kpis")
    protected List<Kpi> kpis;
    @XmlElement(name = "ErrorConfiguration")
    protected ErrorConfiguration errorConfiguration;
    @XmlElement(name = "Action")
    @XmlElementWrapper(name = "Actions")
    protected List<Action> actions;
    @XmlElement(name = "StorageLocation")
    protected String storageLocation;
    @XmlElement(name = "EstimatedRows")
    protected Long estimatedRows;
    @XmlElement(name = "LastProcessed")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastProcessed;

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

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> value) {
        this.translations = value;
    }

    public List<CubeDimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<CubeDimension> value) {
        this.dimensions = value;
    }

    public List<CubePermission> getCubePermissions() {
        return cubePermissions;
    }

    public void setCubePermissions(List<CubePermission> value) {
        this.cubePermissions = value;
    }

    public List<MdxScript> getMdxScripts() {
        return mdxScripts;
    }

    public void setMdxScripts(List<MdxScript> value) {
        this.mdxScripts = value;
    }

    public List<Perspective> getPerspectives() {
        return perspectives;
    }

    public void setPerspectives(List<Perspective> value) {
        this.perspectives = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String value) {
        this.state = value;
    }

    public String getDefaultMeasure() {
        return defaultMeasure;
    }

    public void setDefaultMeasure(String value) {
        this.defaultMeasure = value;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean value) {
        this.visible = value;
    }

    public List<MeasureGroup> getMeasureGroups() {
        return measureGroups;
    }

    public void setMeasureGroups(List<MeasureGroup> value) {
        this.measureGroups = value;
    }

    public DataSourceViewBinding getSource() {
        return source;
    }

    public void setSource(DataSourceViewBinding value) {
        this.source = value;
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

    public Cube.StorageMode getStorageMode() {
        return storageMode;
    }

    public void setStorageMode(Cube.StorageMode value) {
        this.storageMode = value;
    }

    public String getProcessingMode() {
        return processingMode;
    }

    public void setProcessingMode(String value) {
        this.processingMode = value;
    }

    public String getScriptCacheProcessingMode() {
        return scriptCacheProcessingMode;
    }

    public void setScriptCacheProcessingMode(String value) {
        this.scriptCacheProcessingMode = value;
    }

    public String getScriptErrorHandlingMode() {
        return scriptErrorHandlingMode;
    }

    public void setScriptErrorHandlingMode(String value) {
        this.scriptErrorHandlingMode = value;
    }

    public String getDaxOptimizationMode() {
        return daxOptimizationMode;
    }

    public void setDaxOptimizationMode(String value) {
        this.daxOptimizationMode = value;
    }

    public ProactiveCaching getProactiveCaching() {
        return proactiveCaching;
    }

    public void setProactiveCaching(ProactiveCaching value) {
        this.proactiveCaching = value;
    }

    public List<Kpi> getKpis() {
        return kpis;
    }

    public void setKpis(List<Kpi> value) {
        this.kpis = value;
    }

    public ErrorConfiguration getErrorConfiguration() {
        return errorConfiguration;
    }

    public void setErrorConfiguration(ErrorConfiguration value) {
        this.errorConfiguration = value;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> value) {
        this.actions = value;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String value) {
        this.storageLocation = value;
    }

    public Long getEstimatedRows() {
        return estimatedRows;
    }

    public void setEstimatedRows(Long value) {
        this.estimatedRows = value;
    }

    public XMLGregorianCalendar getLastProcessed() {
        return lastProcessed;
    }

    public void setLastProcessed(XMLGregorianCalendar value) {
        this.lastProcessed = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "value" })
    public static class StorageMode {

        @XmlValue
        protected CubeStorageModeEnumTypeXmlEnum value;
        @XmlAttribute(name = "valuens")
        protected String valuens;

        public CubeStorageModeEnumTypeXmlEnum getValue() {
            return value;
        }

        public void setValue(CubeStorageModeEnumTypeXmlEnum value) {
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
