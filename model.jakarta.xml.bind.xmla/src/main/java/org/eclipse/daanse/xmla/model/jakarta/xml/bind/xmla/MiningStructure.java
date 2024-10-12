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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MiningStructure", propOrder = {

})
public class MiningStructure extends AbstractItem {

    @XmlElement(name = "Source")
    protected Binding source;
    @XmlElement(name = "LastProcessed")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastProcessed;
    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<Translation> translations;
    @XmlElement(name = "Language")
    protected BigInteger language;
    @XmlElement(name = "Collation")
    protected String collation;
    @XmlElement(name = "ErrorConfiguration")
    protected ErrorConfiguration errorConfiguration;
    @XmlElement(name = "CacheMode")
    protected String cacheMode;
    @XmlElement(name = "HoldoutMaxPercent", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2008/engine/100/100")
    protected Integer holdoutMaxPercent;
    @XmlElement(name = "HoldoutMaxCases", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2008/engine/100/100")
    protected Integer holdoutMaxCases;
    @XmlElement(name = "HoldoutSeed", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100")
    protected Integer holdoutSeed;
    @XmlElement(name = "HoldoutActualSize", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2008/engine/100/100")
    protected Integer holdoutActualSize;
    @XmlElement(name = "Column", required = true)
    @XmlElementWrapper(name = "Columns", required = true)
    protected List<MiningStructureColumn> columns;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "MiningStructurePermission")
    @XmlElementWrapper(name = "MiningStructurePermissions")
    protected List<MiningStructurePermission> miningStructurePermissions;
    @XmlElement(name = "MiningModel")
    @XmlElementWrapper(name = "MiningModels")
    protected List<MiningModel> miningModels;

    public Binding getSource() {
        return source;
    }

    public void setSource(Binding value) {
        this.source = value;
    }

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

    public boolean isSetTranslations() {
        return (this.translations != null);
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

    public ErrorConfiguration getErrorConfiguration() {
        return errorConfiguration;
    }

    public void setErrorConfiguration(ErrorConfiguration value) {
        this.errorConfiguration = value;
    }

    public String getCacheMode() {
        return cacheMode;
    }

    public void setCacheMode(String value) {
        this.cacheMode = value;
    }

    public Integer getHoldoutMaxPercent() {
        return holdoutMaxPercent;
    }

    public void setHoldoutMaxPercent(Integer value) {
        this.holdoutMaxPercent = value;
    }

    public Integer getHoldoutMaxCases() {
        return holdoutMaxCases;
    }

    public void setHoldoutMaxCases(Integer value) {
        this.holdoutMaxCases = value;
    }

    public Integer getHoldoutSeed() {
        return holdoutSeed;
    }

    public void setHoldoutSeed(Integer value) {
        this.holdoutSeed = value;
    }

    public Integer getHoldoutActualSize() {
        return holdoutActualSize;
    }

    public void setHoldoutActualSize(Integer value) {
        this.holdoutActualSize = value;
    }

    public List<MiningStructureColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<MiningStructureColumn> value) {
        this.columns = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String value) {
        this.state = value;
    }

    public List<MiningStructurePermission> getMiningStructurePermissions() {
        return miningStructurePermissions;
    }

    public void setMiningStructurePermissions(List<MiningStructurePermission> value) {
        this.miningStructurePermissions = value;
    }

    public List<MiningModel> getMiningModels() {
        return miningModels;
    }

    public void setMiningModels(List<MiningModel> value) {
        this.miningModels = value;
    }

    public boolean isSetMiningModels() {
        return (this.miningModels != null);
    }

}
