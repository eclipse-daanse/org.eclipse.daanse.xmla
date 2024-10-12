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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine300;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractProperties", propOrder = { "folderPosition", "contextualNameRule", "alignment",
        "isFolderDefault", "isRightToLeft", "sortDirection", "units", "width", "defaultDetailsPosition",
        "sortPropertiesPosition" })
@XmlSeeAlso({ CalculationPropertiesVisualizationProperties.class, DimensionAttributeVisualizationProperties.class })
public abstract class AbstractProperties {

    @XmlElement(name = "FolderPosition", defaultValue = "-1")
    protected BigInteger folderPosition;
    @XmlElement(name = "ContextualNameRule", defaultValue = "None")
    protected String contextualNameRule;
    @XmlElement(name = "Alignment", defaultValue = "Default")
    protected String alignment;
    @XmlElement(name = "IsFolderDefault", defaultValue = "false")
    protected Boolean isFolderDefault;
    @XmlElement(name = "IsRightToLeft", defaultValue = "false")
    protected Boolean isRightToLeft;
    @XmlElement(name = "SortDirection", defaultValue = "Default")
    protected String sortDirection;
    @XmlElement(name = "Units", defaultValue = "")
    protected String units;
    @XmlElement(name = "Width", defaultValue = "-1")
    protected BigInteger width;
    @XmlElement(name = "DefaultDetailsPosition", defaultValue = "-1")
    protected BigInteger defaultDetailsPosition;
    @XmlElement(name = "SortPropertiesPosition", defaultValue = "-1")
    protected BigInteger sortPropertiesPosition;

    public BigInteger getFolderPosition() {
        return folderPosition;
    }

    public void setFolderPosition(BigInteger folderPosition) {
        this.folderPosition = folderPosition;
    }

    public String getContextualNameRule() {
        return contextualNameRule;
    }

    public void setContextualNameRule(String contextualNameRule) {
        this.contextualNameRule = contextualNameRule;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public Boolean getFolderDefault() {
        return isFolderDefault;
    }

    public void setFolderDefault(Boolean folderDefault) {
        isFolderDefault = folderDefault;
    }

    public Boolean getRightToLeft() {
        return isRightToLeft;
    }

    public void setRightToLeft(Boolean rightToLeft) {
        isRightToLeft = rightToLeft;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public BigInteger getWidth() {
        return width;
    }

    public void setWidth(BigInteger width) {
        this.width = width;
    }

    public BigInteger getDefaultDetailsPosition() {
        return defaultDetailsPosition;
    }

    public void setDefaultDetailsPosition(BigInteger defaultDetailsPosition) {
        this.defaultDetailsPosition = defaultDetailsPosition;
    }

    public BigInteger getSortPropertiesPosition() {
        return sortPropertiesPosition;
    }

    public void setSortPropertiesPosition(BigInteger sortPropertiesPosition) {
        this.sortPropertiesPosition = sortPropertiesPosition;
    }
}
