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
@XmlType(name = "Measure", propOrder = {

})
public class Measure {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "AggregateFunction")
    protected String aggregateFunction;
    @XmlElement(name = "DataType")
    protected String dataType;
    @XmlElement(name = "Source", required = true)
    protected DataItem source;
    @XmlElement(name = "Visible")
    protected Boolean visible;
    @XmlElement(name = "MeasureExpression")
    protected String measureExpression;
    @XmlElement(name = "DisplayFolder")
    protected String displayFolder;
    @XmlElement(name = "FormatString")
    protected String formatString;
    @XmlElement(name = "BackColor")
    protected String backColor;
    @XmlElement(name = "ForeColor")
    protected String foreColor;
    @XmlElement(name = "FontName")
    protected String fontName;
    @XmlElement(name = "FontSize")
    protected String fontSize;
    @XmlElement(name = "FontFlags")
    protected String fontFlags;
    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<Translation> translations;
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

    public String getAggregateFunction() {
        return aggregateFunction;
    }

    public void setAggregateFunction(String value) {
        this.aggregateFunction = value;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String value) {
        this.dataType = value;
    }

    public DataItem getSource() {
        return source;
    }

    public void setSource(DataItem value) {
        this.source = value;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean value) {
        this.visible = value;
    }

    public String getMeasureExpression() {
        return measureExpression;
    }

    public void setMeasureExpression(String value) {
        this.measureExpression = value;
    }

    public String getDisplayFolder() {
        return displayFolder;
    }

    public void setDisplayFolder(String value) {
        this.displayFolder = value;
    }

    public String getFormatString() {
        return formatString;
    }

    public void setFormatString(String value) {
        this.formatString = value;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String value) {
        this.backColor = value;
    }

    public String getForeColor() {
        return foreColor;
    }

    public void setForeColor(String value) {
        this.foreColor = value;
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

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> value) {
        this.translations = value;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

}
