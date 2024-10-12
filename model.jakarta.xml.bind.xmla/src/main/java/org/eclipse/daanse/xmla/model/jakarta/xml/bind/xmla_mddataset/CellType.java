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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.CellTypeEnum;
import org.w3c.dom.Element;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CellType", propOrder = { "value", "any" })
public class CellType {

    @XmlElement(name = "Value")
    protected CellType.Value value;
    @XmlAnyElement
    protected List<Element> any;
    @XmlAttribute(name = "CellOrdinal", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long cellOrdinal;

    public CellType.Value getValue() {
        return value;
    }

    public void setValue(CellType.Value value) {
        this.value = value;
    }

    public List<Element> getAny() {
        return this.any;
    }

    public void setAny(List<Element> any) {
        this.any = any;
    }

    public long getCellOrdinal() {
        return cellOrdinal;
    }

    public void setCellOrdinal(long value) {
        this.cellOrdinal = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "error", "value", "type" })
    public static class Value {

        @XmlElement(name = "Error")
        protected List<CellTypeError> error;

        @SuppressWarnings("java:S1700")
        @XmlMixed
        protected List<String> value;

        @XmlAttribute(name = "type", namespace = "xsi")
        protected CellTypeEnum type;

        public List<CellTypeError> getError() {
            return this.error;
        }

        public void setError(List<CellTypeError> error) {
            this.error = error;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }

        public CellTypeEnum getType() {
            return type;
        }

        public void setType(CellTypeEnum type) {
            this.type = type;
        }
    }

}
