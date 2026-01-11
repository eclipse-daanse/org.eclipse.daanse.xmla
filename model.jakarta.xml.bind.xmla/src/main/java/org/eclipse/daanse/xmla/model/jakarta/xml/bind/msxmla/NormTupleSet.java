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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.msxmla;

import java.io.Serializable;
import java.util.List;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.TupleType;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "normTuples", "membersLookup" })
@XmlRootElement(name = "NormTupleSet")
public class NormTupleSet implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "NormTuples", required = true)
    protected NormTuplesType normTuples;
    @XmlElement(name = "Members", required = true, type = TupleType.class)
    @XmlElementWrapper(name = "MembersLookup", required = true)
    protected List<TupleType> membersLookup;

    public NormTuplesType getNormTuples() {
        return normTuples;
    }

    public void setNormTuples(NormTuplesType value) {
        this.normTuples = value;
    }

    public List<TupleType> getMembersLookup() {
        return membersLookup;
    }

    public void setMembersLookup(List<TupleType> value) {
        this.membersLookup = value;
    }

}
