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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NormTuplesType", propOrder = { "normTuple" })
public class NormTuplesType implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "NormTuple")
    private List<NormTuplesType.NormTuple> normTuple;

    public List<NormTuplesType.NormTuple> getNormTuple() {
        return this.normTuple;
    }

    public void setNormTuple(List<NormTuple> normTuple) {
        this.normTuple = normTuple;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "memberRef" })
    public static class NormTuple implements Serializable {

        private static final long serialVersionUID = 1L;
        @XmlElement(name = "MemberRef")
        private List<NormTuplesType.NormTuple.MemberRef> memberRef;

        public List<NormTuplesType.NormTuple.MemberRef> getMemberRef() {
            return this.memberRef;
        }

        public void setMemberRef(List<MemberRef> memberRef) {
            this.memberRef = memberRef;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "memberOrdinal", "memberDispInfo" })
        public static class MemberRef implements Serializable {

            private static final long serialVersionUID = 1L;
            @XmlElement(name = "MemberOrdinal")
            protected int memberOrdinal;
            @XmlElement(name = "MemberDispInfo")
            protected Integer memberDispInfo;

            public int getMemberOrdinal() {
                return memberOrdinal;
            }

            public void setMemberOrdinal(int value) {
                this.memberOrdinal = value;
            }

            public Integer getMemberDispInfo() {
                return memberDispInfo;
            }

            public void setMemberDispInfo(Integer value) {
                this.memberDispInfo = value;
            }

        }

    }

}
