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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScalarMiningStructureColumn", propOrder = {

})
public class ScalarMiningStructureColumn extends MiningStructureColumn {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;
    @XmlElement(name = "IsKey")
    protected Boolean isKey;
    @XmlElement(name = "Source")
    protected Binding source;
    @XmlElement(name = "Distribution")
    protected String distribution;
    @XmlElement(name = "ModelingFlag")
    @XmlElementWrapper(name = "ModelingFlags")
    protected List<MiningModelingFlag> modelingFlags;
    @XmlElement(name = "Content", required = true)
    protected String content;
    @XmlElement(name = "ClassifiedColumn")
    @XmlElementWrapper(name = "ClassifiedColumns")
    protected List<String> classifiedColumns;
    @XmlElement(name = "DiscretizationMethod")
    protected String discretizationMethod;
    @XmlElement(name = "DiscretizationBucketCount")
    protected BigInteger discretizationBucketCount;
    @XmlElement(name = "KeyColumn")
    @XmlElementWrapper(name = "KeyColumns")
    protected List<DataItem> keyColumns;
    @XmlElement(name = "NameColumn")
    protected DataItem nameColumn;
    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<Translation> translations;

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

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

    public Boolean isIsKey() {
        return isKey;
    }

    public void setIsKey(Boolean value) {
        this.isKey = value;
    }

    public Binding getSource() {
        return source;
    }

    public void setSource(Binding value) {
        this.source = value;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String value) {
        this.distribution = value;
    }

    public List<MiningModelingFlag> getModelingFlags() {
        return modelingFlags;
    }

    public void setModelingFlags(List<MiningModelingFlag> value) {
        this.modelingFlags = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String value) {
        this.content = value;
    }

    public List<String> getClassifiedColumns() {
        return classifiedColumns;
    }

    public void setClassifiedColumns(List<String> value) {
        this.classifiedColumns = value;
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

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> value) {
        this.translations = value;
    }

}
