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
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MeasureGroupDimension", propOrder = { "cubeDimensionID", "annotations", "source" })
@XmlSeeAlso({ ManyToManyMeasureGroupDimension.class, RegularMeasureGroupDimension.class,
        ReferenceMeasureGroupDimension.class, DegenerateMeasureGroupDimension.class,
        DataMiningMeasureGroupDimension.class })
public abstract class MeasureGroupDimension {

    @XmlElement(name = "CubeDimensionID", required = true)
    protected String cubeDimensionID;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;
    @XmlElement(name = "Source")
    protected MeasureGroupDimensionBinding source;

    public String getCubeDimensionID() {
        return cubeDimensionID;
    }

    public void setCubeDimensionID(String cubeDimensionID) {
        this.cubeDimensionID = cubeDimensionID;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    public MeasureGroupDimensionBinding getSource() {
        return source;
    }

    public void setSource(MeasureGroupDimensionBinding source) {
        this.source = source;
    }
}
