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
@XmlType(name = "PerspectiveMeasureGroup", propOrder = {

})
public class PerspectiveMeasureGroup {

    @XmlElement(name = "MeasureGroupID", required = true)
    protected String measureGroupID;
    @XmlElement(name = "Measure", type = PerspectiveMeasure.class)
    @XmlElementWrapper(name = "Measures")
    protected List<PerspectiveMeasure> measures;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;

    public String getMeasureGroupID() {
        return measureGroupID;
    }

    public void setMeasureGroupID(String value) {
        this.measureGroupID = value;
    }

    public List<PerspectiveMeasure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<PerspectiveMeasure> value) {
        this.measures = value;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

}
