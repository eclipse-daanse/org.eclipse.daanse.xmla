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
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DimensionAttributeVisualizationProperties", propOrder = { "commonIdentifierPosition",
        "displayKeyPosition", "isDefaultImage", "defaultAggregateFunction" })
public class DimensionAttributeVisualizationProperties extends AbstractProperties implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "CommonIdentifierPosition", defaultValue = "-1")
    protected BigInteger commonIdentifierPosition;
    @XmlElement(name = "DisplayKeyPosition", defaultValue = "-1")
    protected BigInteger displayKeyPosition;
    @XmlElement(name = "IsDefaultImage", defaultValue = "false")
    protected Boolean isDefaultImage;
    @XmlElement(name = "DefaultAggregateFunction", defaultValue = "Default")
    protected String defaultAggregateFunction;

    public BigInteger getCommonIdentifierPosition() {
        return commonIdentifierPosition;
    }

    public void setCommonIdentifierPosition(BigInteger commonIdentifierPosition) {
        this.commonIdentifierPosition = commonIdentifierPosition;
    }

    public BigInteger getDisplayKeyPosition() {
        return displayKeyPosition;
    }

    public void setDisplayKeyPosition(BigInteger displayKeyPosition) {
        this.displayKeyPosition = displayKeyPosition;
    }

    public Boolean getDefaultImage() {
        return isDefaultImage;
    }

    public void setDefaultImage(Boolean defaultImage) {
        isDefaultImage = defaultImage;
    }

    public String getDefaultAggregateFunction() {
        return defaultAggregateFunction;
    }

    public void setDefaultAggregateFunction(String defaultAggregateFunction) {
        this.defaultAggregateFunction = defaultAggregateFunction;
    }
}
