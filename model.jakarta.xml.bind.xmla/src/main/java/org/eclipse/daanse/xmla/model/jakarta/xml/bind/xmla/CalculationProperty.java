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

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine300.CalculationPropertiesVisualizationProperties;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalculationProperty", propOrder = {

})
public class CalculationProperty {

    @XmlElement(name = "CalculationReference", required = true)
    protected String calculationReference;
    @XmlElement(name = "CalculationType", required = true)
    protected String calculationType;
    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<Translation> translations;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Visible")
    protected Boolean visible;
    @XmlElement(name = "SolveOrder")
    protected BigInteger solveOrder;
    @XmlElement(name = "FormatString")
    protected String formatString;
    @XmlElement(name = "ForeColor")
    protected String foreColor;
    @XmlElement(name = "BackColor")
    protected String backColor;
    @XmlElement(name = "FontName")
    protected String fontName;
    @XmlElement(name = "FontSize")
    protected String fontSize;
    @XmlElement(name = "FontFlags")
    protected String fontFlags;
    @XmlElement(name = "NonEmptyBehavior")
    protected String nonEmptyBehavior;
    @XmlElement(name = "AssociatedMeasureGroupID")
    protected String associatedMeasureGroupID;
    @XmlElement(name = "DisplayFolder")
    protected String displayFolder;
    @XmlElement(name = "Language")
    protected BigInteger language;
    @XmlElement(name = "VisualizationProperties")
    protected CalculationPropertiesVisualizationProperties visualizationProperties;

    public String getCalculationReference() {
        return calculationReference;
    }

    public void setCalculationReference(String value) {
        this.calculationReference = value;
    }

    public String getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(String value) {
        this.calculationType = value;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> value) {
        this.translations = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean value) {
        this.visible = value;
    }

    public BigInteger getSolveOrder() {
        return solveOrder;
    }

    public void setSolveOrder(BigInteger value) {
        this.solveOrder = value;
    }

    public String getFormatString() {
        return formatString;
    }

    public void setFormatString(String value) {
        this.formatString = value;
    }

    public String getForeColor() {
        return foreColor;
    }

    public void setForeColor(String value) {
        this.foreColor = value;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String value) {
        this.backColor = value;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String value) {
        this.fontName = value;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String value) {
        this.fontSize = value;
    }

    public String getFontFlags() {
        return fontFlags;
    }

    public void setFontFlags(String value) {
        this.fontFlags = value;
    }

    public String getNonEmptyBehavior() {
        return nonEmptyBehavior;
    }

    public void setNonEmptyBehavior(String value) {
        this.nonEmptyBehavior = value;
    }

    public String getAssociatedMeasureGroupID() {
        return associatedMeasureGroupID;
    }

    public void setAssociatedMeasureGroupID(String value) {
        this.associatedMeasureGroupID = value;
    }

    public String getDisplayFolder() {
        return displayFolder;
    }

    public void setDisplayFolder(String value) {
        this.displayFolder = value;
    }

    public BigInteger getLanguage() {
        return language;
    }

    public void setLanguage(BigInteger value) {
        this.language = value;
    }

    public CalculationPropertiesVisualizationProperties getVisualizationProperties() {
        return visualizationProperties;
    }

    public void setVisualizationProperties(CalculationPropertiesVisualizationProperties value) {
        this.visualizationProperties = value;
    }

}
