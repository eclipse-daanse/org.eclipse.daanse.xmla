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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CubeAttributeBinding", propOrder = { "cubeID", "cubeDimensionID", "attributeID", "type", "ordinal" })
public class CubeAttributeBinding extends Binding {

    @XmlElement(name = "CubeID", required = true)
    protected String cubeID;
    @XmlElement(name = "CubeDimensionID", required = true)
    protected String cubeDimensionID;
    @XmlElement(name = "AttributeID", required = true)
    protected String attributeID;
    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "Ordinal")
    protected CubeAttributeBinding.Ordinal ordinal;

    public String getCubeID() {
        return cubeID;
    }

    public void setCubeID(String value) {
        this.cubeID = value;
    }

    public String getCubeDimensionID() {
        return cubeDimensionID;
    }

    public void setCubeDimensionID(String value) {
        this.cubeDimensionID = value;
    }

    public String getAttributeID() {
        return attributeID;
    }

    public void setAttributeID(String value) {
        this.attributeID = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public CubeAttributeBinding.Ordinal getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(CubeAttributeBinding.Ordinal value) {
        this.ordinal = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "ordinalField" })
    public static class Ordinal {

        @XmlElement(name = "Ordinal")
        protected List<BigInteger> ordinalField;

        public List<BigInteger> getOrdinal() {
            return this.ordinalField;
        }

        public void setOrdinal(List<BigInteger> ordinalF) {
            this.ordinalField = ordinalF;
        }
    }

}
