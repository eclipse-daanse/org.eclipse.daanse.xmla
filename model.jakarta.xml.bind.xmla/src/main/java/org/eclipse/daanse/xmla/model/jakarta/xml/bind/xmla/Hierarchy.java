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

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine300.HierarchyVisualizationProperties;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hierarchy", propOrder = {

})
public class Hierarchy {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "ProcessingState", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300")
    protected String processingState;
    @XmlElement(name = "StructureType", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300")
    protected String structureType;
    @XmlElement(name = "DisplayFolder")
    protected String displayFolder;
    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<Translation> translations;
    @XmlElement(name = "AllMemberName")
    protected String allMemberName;
    @XmlElement(name = "AllMemberTranslation")
    @XmlElementWrapper(name = "AllMemberTranslations")
    protected List<Translation> allMemberTranslations;
    @XmlElement(name = "MemberNamesUnique")
    protected Boolean memberNamesUnique;
    @XmlElement(name = "MemberKeysUnique", namespace = "http://schemas.microsoft.com/analysisservices/2003/engine/2")
    protected String memberKeysUnique;
    @XmlElement(name = "AllowDuplicateNames")
    protected Boolean allowDuplicateNames;
    @XmlElement(name = "Level", required = true)
    @XmlElementWrapper(name = "Levels", required = true)
    protected List<Level> levels;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;
    @XmlElement(name = "VisualizationProperties")
    protected HierarchyVisualizationProperties visualizationProperties;

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

    public String getProcessingState() {
        return processingState;
    }

    public void setProcessingState(String value) {
        this.processingState = value;
    }

    public String getStructureType() {
        return structureType;
    }

    public void setStructureType(String value) {
        this.structureType = value;
    }

    public String getDisplayFolder() {
        return displayFolder;
    }

    public void setDisplayFolder(String value) {
        this.displayFolder = value;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> value) {
        this.translations = value;
    }

    public String getAllMemberName() {
        return allMemberName;
    }

    public void setAllMemberName(String value) {
        this.allMemberName = value;
    }

    public List<Translation> getAllMemberTranslations() {
        return allMemberTranslations;
    }

    public void setAllMemberTranslations(List<Translation> value) {
        this.allMemberTranslations = value;
    }

    public Boolean isMemberNamesUnique() {
        return memberNamesUnique;
    }

    public void setMemberNamesUnique(Boolean value) {
        this.memberNamesUnique = value;
    }

    public String getMemberKeysUnique() {
        return memberKeysUnique;
    }

    public void setMemberKeysUnique(String value) {
        this.memberKeysUnique = value;
    }

    public Boolean isAllowDuplicateNames() {
        return allowDuplicateNames;
    }

    public void setAllowDuplicateNames(Boolean value) {
        this.allowDuplicateNames = value;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> value) {
        this.levels = value;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

    public HierarchyVisualizationProperties getVisualizationProperties() {
        return visualizationProperties;
    }

    public void setVisualizationProperties(HierarchyVisualizationProperties value) {
        this.visualizationProperties = value;
    }

}
