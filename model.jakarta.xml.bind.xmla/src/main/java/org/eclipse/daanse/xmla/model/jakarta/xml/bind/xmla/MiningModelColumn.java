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
@XmlType(name = "MiningModelColumn", propOrder = {

})
public class MiningModelColumn {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "SourceColumnID", required = true)
    protected String sourceColumnID;
    @XmlElement(name = "Usage")
    protected String usage;
    @XmlElement(name = "Filter")
    protected String filter;
    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<Translation> translations;
    @XmlElement(name = "Column")
    @XmlElementWrapper(name = "Columns")
    protected List<MiningModelColumn> columns;
    @XmlElement(name = "ModelingFlag")
    @XmlElementWrapper(name = "ModelingFlags")
    protected List<MiningModelingFlag> modelingFlags;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;

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

    public String getSourceColumnID() {
        return sourceColumnID;
    }

    public void setSourceColumnID(String value) {
        this.sourceColumnID = value;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String value) {
        this.usage = value;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String value) {
        this.filter = value;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> value) {
        this.translations = value;
    }

    public List<MiningModelColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<MiningModelColumn> value) {
        this.columns = value;
    }

    public List<MiningModelingFlag> getModelingFlags() {
        return modelingFlags;
    }

    public void setModelingFlags(List<MiningModelingFlag> value) {
        this.modelingFlags = value;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

}
