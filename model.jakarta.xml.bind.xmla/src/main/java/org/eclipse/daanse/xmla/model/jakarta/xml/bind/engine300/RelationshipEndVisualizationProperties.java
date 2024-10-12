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

import java.io.Serializable;
import java.math.BigInteger;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelationshipEndVisualizationProperties", propOrder = { "folderPosition", "contextualNameRule",
        "defaultDetailsPosition", "displayKeyPosition", "commonIdentifierPosition", "isDefaultMeasure",
        "isDefaultImage", "sortPropertiesPosition" })
public class RelationshipEndVisualizationProperties implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "FolderPosition", defaultValue = "-1")
    protected BigInteger folderPosition;
    @XmlElement(name = "ContextualNameRule", defaultValue = "None")
    protected String contextualNameRule;
    @XmlElement(name = "DefaultDetailsPosition", defaultValue = "-1")
    protected BigInteger defaultDetailsPosition;
    @XmlElement(name = "DisplayKeyPosition", defaultValue = "-1")
    protected BigInteger displayKeyPosition;
    @XmlElement(name = "CommonIdentifierPosition", defaultValue = "-1")
    protected BigInteger commonIdentifierPosition;
    @XmlElement(name = "IsDefaultMeasure", defaultValue = "false")
    protected Boolean isDefaultMeasure;
    @XmlElement(name = "IsDefaultImage", defaultValue = "false")
    protected Boolean isDefaultImage;
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

    public BigInteger getDefaultDetailsPosition() {
        return defaultDetailsPosition;
    }

    public void setDefaultDetailsPosition(BigInteger defaultDetailsPosition) {
        this.defaultDetailsPosition = defaultDetailsPosition;
    }

    public BigInteger getDisplayKeyPosition() {
        return displayKeyPosition;
    }

    public void setDisplayKeyPosition(BigInteger displayKeyPosition) {
        this.displayKeyPosition = displayKeyPosition;
    }

    public BigInteger getCommonIdentifierPosition() {
        return commonIdentifierPosition;
    }

    public void setCommonIdentifierPosition(BigInteger commonIdentifierPosition) {
        this.commonIdentifierPosition = commonIdentifierPosition;
    }

    public Boolean getDefaultMeasure() {
        return isDefaultMeasure;
    }

    public void setDefaultMeasure(Boolean defaultMeasure) {
        isDefaultMeasure = defaultMeasure;
    }

    public Boolean getDefaultImage() {
        return isDefaultImage;
    }

    public void setDefaultImage(Boolean defaultImage) {
        isDefaultImage = defaultImage;
    }

    public BigInteger getSortPropertiesPosition() {
        return sortPropertiesPosition;
    }

    public void setSortPropertiesPosition(BigInteger sortPropertiesPosition) {
        this.sortPropertiesPosition = sortPropertiesPosition;
    }
}
