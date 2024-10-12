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

import javax.xml.datatype.Duration;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DesignAggregations", propOrder = {

})
public class DesignAggregations {

    @XmlElement(name = "Object", required = true)
    protected ObjectReference object;
    @XmlElement(name = "Time")
    protected Duration time;
    @XmlElement(name = "Steps")
    protected BigInteger steps;
    @XmlElement(name = "Optimization")
    protected Double optimization;
    @XmlElement(name = "Storage")
    protected Long storage;
    @XmlElement(name = "Materialize")
    protected Boolean materialize;
    @XmlElement(name = "Query")
    @XmlElementWrapper(name = "Queries")
    protected List<String> queries;

    public ObjectReference getObject() {
        return object;
    }

    public void setObject(ObjectReference value) {
        this.object = value;
    }

    public Duration getTime() {
        return time;
    }

    public void setTime(Duration value) {
        this.time = value;
    }

    public BigInteger getSteps() {
        return steps;
    }

    public void setSteps(BigInteger value) {
        this.steps = value;
    }

    public Double getOptimization() {
        return optimization;
    }

    public void setOptimization(Double value) {
        this.optimization = value;
    }

    public Long getStorage() {
        return storage;
    }

    public void setStorage(Long value) {
        this.storage = value;
    }

    public Boolean isMaterialize() {
        return materialize;
    }

    public void setMaterialize(Boolean value) {
        this.materialize = value;
    }

    public List<String> getQueries() {
        return queries;
    }

    public void setQueries(List<String> value) {
        this.queries = value;
    }

}
