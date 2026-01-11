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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalculationPropertiesVisualizationProperties", propOrder = { "isDefaultMeasure", "isSimpleMeasure" })
public class CalculationPropertiesVisualizationProperties extends AbstractProperties implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "IsDefaultMeasure", defaultValue = "false")
    protected Boolean isDefaultMeasure;
    @XmlElement(name = "IsSimpleMeasure", defaultValue = "false")
    protected Boolean isSimpleMeasure;

    public Boolean getDefaultMeasure() {
        return isDefaultMeasure;
    }

    public void setDefaultMeasure(Boolean defaultMeasure) {
        isDefaultMeasure = defaultMeasure;
    }

    public Boolean getSimpleMeasure() {
        return isSimpleMeasure;
    }

    public void setSimpleMeasure(Boolean simpleMeasure) {
        isSimpleMeasure = simpleMeasure;
    }
}
