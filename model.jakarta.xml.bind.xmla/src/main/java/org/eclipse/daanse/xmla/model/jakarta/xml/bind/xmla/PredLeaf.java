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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pred_leaf", propOrder = { "comparator", "event", "global", "value" })
public class PredLeaf {

    @XmlElement(required = true)
    protected PredLeaf.Comparator comparator;
    protected PredLeaf.Event event;
    protected PredLeaf.Global global;
    @XmlElement(required = true)
    protected java.lang.Object value;

    public PredLeaf.Comparator getComparator() {
        return comparator;
    }

    public void setComparator(PredLeaf.Comparator value) {
        this.comparator = value;
    }

    public PredLeaf.Event getEvent() {
        return event;
    }

    public void setEvent(PredLeaf.Event value) {
        this.event = value;
    }

    public PredLeaf.Global getGlobal() {
        return global;
    }

    public void setGlobal(PredLeaf.Global value) {
        this.global = value;
    }

    public java.lang.Object getValue() {
        return value;
    }

    public void setValue(java.lang.Object value) {
        this.value = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Comparator {

        @XmlAttribute(name = "module")
        protected String module;
        @XmlAttribute(name = "package", required = true)
        protected String packageValue;
        @XmlAttribute(name = "name", required = true)
        protected String name;

        public String getModule() {
            return module;
        }

        public void setModule(String value) {
            this.module = value;
        }

        public String getPackage() {
            return packageValue;
        }

        public void setPackage(String value) {
            this.packageValue = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String value) {
            this.name = value;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Event {

        @XmlAttribute(name = "field")
        protected String field;
        @XmlAttribute(name = "module")
        protected String module;
        @XmlAttribute(name = "package", required = true)
        protected String packageValue;
        @XmlAttribute(name = "name", required = true)
        protected String name;

        public String getField() {
            return field;
        }

        public void setField(String value) {
            this.field = value;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String value) {
            this.module = value;
        }

        public String getPackage() {
            return packageValue;
        }

        public void setPackage(String value) {
            this.packageValue = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String value) {
            this.name = value;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Global {

        @XmlAttribute(name = "module")
        protected String module;
        @XmlAttribute(name = "package", required = true)
        protected String packageValue;
        @XmlAttribute(name = "name", required = true)
        protected String name;

        public String getModule() {
            return module;
        }

        public void setModule(String value) {
            this.module = value;
        }

        public String getPackage() {
            return packageValue;
        }

        public void setPackage(String value) {
            this.packageValue = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String value) {
            this.name = value;
        }
    }

}
