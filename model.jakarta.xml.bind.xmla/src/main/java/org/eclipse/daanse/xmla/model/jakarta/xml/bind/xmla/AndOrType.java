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

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "and_or_type", propOrder = { "notOrOrOrAnd" })
public class AndOrType {

    @XmlElementRefs({ @XmlElementRef(name = "Not", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "Or", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "And", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "Equal", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "NotEqual", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "Less", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "LessOrEqual", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "Greater", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "GreaterOrEqual", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "Like", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "NotLike", type = JAXBElement.class, required = false) })
    protected List<JAXBElement<?>> notOrOrOrAnd;

    public List<JAXBElement<?>> getNotOrOrOrAnd() {
        return this.notOrOrOrAnd;
    }

    public void setNotOrOrOrAnd(List<JAXBElement<?>> notOrOrOrAnd) {
        this.notOrOrOrAnd = notOrOrOrAnd;
    }
}
