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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trace_Columns", propOrder = { "data" })
public class TraceColumns {

    @XmlElement(name = "Data", required = true)
    protected TraceColumns.Data data;

    public TraceColumns.Data getData() {
        return data;
    }

    public void setData(TraceColumns.Data value) {
        this.data = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "column" })
    public static class Data {

        @XmlElement(name = "Column", required = true)
        protected TraceColumns.Data.Column column;

        public TraceColumns.Data.Column getColumn() {
            return column;
        }

        public void setColumn(TraceColumns.Data.Column value) {
            this.column = value;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {

        })
        public static class Column {

            @XmlElement(name = "ID", required = true)
            protected BigInteger id;
            @XmlElement(name = "Type", required = true)
            protected BigInteger type;
            @XmlElement(name = "Name", required = true)
            protected String name;
            @XmlElement(name = "Description")
            protected String description;
            @XmlElement(name = "Filterable")
            protected boolean filterable;
            @XmlElement(name = "Repeatable")
            protected boolean repeatable;
            @XmlElement(name = "RepeatedBase")
            protected boolean repeatedBase;

            public BigInteger getID() {
                return id;
            }

            public void setID(BigInteger value) {
                this.id = value;
            }

            public BigInteger getType() {
                return type;
            }

            public void setType(BigInteger value) {
                this.type = value;
            }

            public String getName() {
                return name;
            }

            public void setName(String value) {
                this.name = value;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String value) {
                this.description = value;
            }

            public boolean isFilterable() {
                return filterable;
            }

            public void setFilterable(boolean value) {
                this.filterable = value;
            }

            public boolean isRepeatable() {
                return repeatable;
            }

            public void setRepeatable(boolean value) {
                this.repeatable = value;
            }

            public boolean isRepeatedBase() {
                return repeatedBase;
            }

            public void setRepeatedBase(boolean value) {
                this.repeatedBase = value;
            }
        }

    }

}
