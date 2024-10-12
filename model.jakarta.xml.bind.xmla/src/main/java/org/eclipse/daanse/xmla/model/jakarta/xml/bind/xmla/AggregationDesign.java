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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AggregationDesign", propOrder = {

})
public class AggregationDesign extends AbstractItem {

    @XmlElement(name = "EstimatedRows")
    protected Long estimatedRows;
    @XmlElement(name = "Dimension")
    @XmlElementWrapper(name = "Dimensions")
    protected List<AggregationDesignDimension> dimensions;
    @XmlElement(name = "Aggregation")
    @XmlElementWrapper(name = "Aggregations")
    protected List<Aggregation> aggregations;
    @XmlElement(name = "EstimatedPerformanceGain")
    protected Integer estimatedPerformanceGain;

    public Long getEstimatedRows() {
        return estimatedRows;
    }

    public void setEstimatedRows(Long value) {
        this.estimatedRows = value;
    }

    public List<AggregationDesignDimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<AggregationDesignDimension> value) {
        this.dimensions = value;
    }

    public List<Aggregation> getAggregations() {
        return aggregations;
    }

    public void setAggregations(List<Aggregation> value) {
        this.aggregations = value;
    }

    public Integer getEstimatedPerformanceGain() {
        return estimatedPerformanceGain;
    }

    public void setEstimatedPerformanceGain(Integer value) {
        this.estimatedPerformanceGain = value;
    }

}
