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
@XmlType(name = "Perspective", propOrder = {

})
public class Perspective extends AbstractItem {

    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<Translation> translations;
    @XmlElement(name = "DefaultMeasure")
    protected String defaultMeasure;
    @XmlElement(name = "Dimension")
    @XmlElementWrapper(name = "Dimensions")
    protected List<PerspectiveDimension> dimensions;
    @XmlElement(name = "MeasureGroup")
    @XmlElementWrapper(name = "MeasureGroups")
    protected List<PerspectiveMeasureGroup> measureGroups;
    @XmlElement(name = "Calculation")
    @XmlElementWrapper(name = "Calculations")
    protected List<PerspectiveCalculation> calculations;
    @XmlElement(name = "Kpi")
    @XmlElementWrapper(name = "Kpis")
    protected List<PerspectiveKpi> kpis;
    @XmlElement(name = "Action")
    @XmlElementWrapper(name = "Actions")
    protected List<PerspectiveAction> actions;

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> value) {
        this.translations = value;
    }

    public String getDefaultMeasure() {
        return defaultMeasure;
    }

    public void setDefaultMeasure(String value) {
        this.defaultMeasure = value;
    }

    public List<PerspectiveDimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<PerspectiveDimension> value) {
        this.dimensions = value;
    }

    public List<PerspectiveMeasureGroup> getMeasureGroups() {
        return measureGroups;
    }

    public void setMeasureGroups(List<PerspectiveMeasureGroup> value) {
        this.measureGroups = value;
    }

    public List<PerspectiveCalculation> getCalculations() {
        return calculations;
    }

    public void setCalculations(List<PerspectiveCalculation> value) {
        this.calculations = value;
    }

    public List<PerspectiveKpi> getKpis() {
        return kpis;
    }

    public void setKpis(List<PerspectiveKpi> value) {
        this.kpis = value;
    }

    public List<PerspectiveAction> getActions() {
        return actions;
    }

    public void setActions(List<PerspectiveAction> value) {
        this.actions = value;
    }

}
