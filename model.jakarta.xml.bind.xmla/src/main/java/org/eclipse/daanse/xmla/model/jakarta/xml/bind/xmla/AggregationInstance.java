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
@XmlType(name = "AggregationInstance", propOrder = {

})
public class AggregationInstance extends AbstractAggregation {

    @XmlElement(name = "AggregationType", required = true)
    protected String aggregationType;
    @XmlElement(name = "Source")
    protected TabularBinding source;
    @XmlElement(name = "Dimension")
    @XmlElementWrapper(name = "Dimensions")
    protected List<AggregationInstanceDimension> dimensions;
    @XmlElement(name = "Measure")
    @XmlElementWrapper(name = "Measures")
    protected List<AggregationInstanceMeasure> measures;

    public String getAggregationType() {
        return aggregationType;
    }

    public void setAggregationType(String value) {
        this.aggregationType = value;
    }

    public TabularBinding getSource() {
        return source;
    }

    public void setSource(TabularBinding value) {
        this.source = value;
    }

    public boolean isSetSource() {
        return (this.source != null);
    }

    public List<AggregationInstanceDimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<AggregationInstanceDimension> value) {
        this.dimensions = value;
    }

    public List<AggregationInstanceMeasure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<AggregationInstanceMeasure> value) {
        this.measures = value;
    }

}
