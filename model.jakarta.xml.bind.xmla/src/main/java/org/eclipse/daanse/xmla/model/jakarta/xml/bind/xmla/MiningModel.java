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

import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MiningModel", propOrder = {

})
public class MiningModel extends AbstractItem {

    @XmlElement(name = "Algorithm", required = true)
    protected String algorithm;
    @XmlElement(name = "LastProcessed")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastProcessed;
    @XmlElement(name = "AlgorithmParameter")
    @XmlElementWrapper(name = "AlgorithmParameters")
    protected List<AlgorithmParameter> algorithmParameters;
    @XmlElement(name = "AllowDrillThrough")
    protected Boolean allowDrillThrough;
    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<AttributeTranslation> translations;
    @XmlElement(name = "Column", required = true)
    @XmlElementWrapper(name = "Columns", required = true)
    protected List<MiningModelColumn> columns;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "FoldingParameters")
    protected FoldingParameters foldingParameters;
    @XmlElement(name = "Filter")
    protected String filter;
    @XmlElement(name = "MiningModelPermission")
    @XmlElementWrapper(name = "MiningModelPermissions")
    protected List<MiningModelPermission> miningModelPermissions;
    @XmlElement(name = "Language")
    protected String language;
    @XmlElement(name = "Collation")
    protected String collation;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String value) {
        this.algorithm = value;
    }

    public XMLGregorianCalendar getLastProcessed() {
        return lastProcessed;
    }

    public void setLastProcessed(XMLGregorianCalendar value) {
        this.lastProcessed = value;
    }

    public List<AlgorithmParameter> getAlgorithmParameters() {
        return algorithmParameters;
    }

    public void setAlgorithmParameters(List<AlgorithmParameter> value) {
        this.algorithmParameters = value;
    }

    public Boolean isAllowDrillThrough() {
        return allowDrillThrough;
    }

    public void setAllowDrillThrough(Boolean value) {
        this.allowDrillThrough = value;
    }

    public List<AttributeTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<AttributeTranslation> value) {
        this.translations = value;
    }

    public List<MiningModelColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<MiningModelColumn> value) {
        this.columns = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String value) {
        this.state = value;
    }

    public FoldingParameters getFoldingParameters() {
        return foldingParameters;
    }

    public void setFoldingParameters(FoldingParameters value) {
        this.foldingParameters = value;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String value) {
        this.filter = value;
    }

    public List<MiningModelPermission> getMiningModelPermissions() {
        return miningModelPermissions;
    }

    public void setMiningModelPermissions(List<MiningModelPermission> value) {
        this.miningModelPermissions = value;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String value) {
        this.language = value;
    }

    public String getCollation() {
        return collation;
    }

    public void setCollation(String value) {
        this.collation = value;
    }

}
