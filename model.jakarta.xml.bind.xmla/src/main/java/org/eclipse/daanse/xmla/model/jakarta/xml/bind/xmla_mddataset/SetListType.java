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

import java.util.List;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.msxmla.NormTupleSet;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetListType", propOrder = { "setType" })
public class SetListType {

    @XmlElements({ @XmlElement(name = "Members", type = MembersType.class),
            @XmlElement(name = "Tuples", type = TuplesType.class),
            @XmlElement(name = "CrossProduct", type = SetListType.class),
            @XmlElement(name = "NormTupleSet", namespace = "http://schemas.microsoft.com/analysisservices/2003/xmla", type = NormTupleSet.class),
            @XmlElement(name = "Union", type = Union.class) })
    protected List<Object> setType;
    @XmlAttribute(name = "Size")
    @XmlSchemaType(name = "unsignedInt")
    protected Long size;

    public List<Object> getSetType() {
        return this.setType;
    }

    public void setSetType(List<Object> setType) {
        this.setType = setType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(Long value) {
        this.size = value;
    }

}
