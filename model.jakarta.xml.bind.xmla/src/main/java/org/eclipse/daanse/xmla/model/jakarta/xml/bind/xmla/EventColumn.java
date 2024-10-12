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
@XmlType(name = "EventColumn", propOrder = {

})
public class EventColumn {

    @XmlElement(name = "ID", required = true)
    protected BigInteger id;
    @XmlElement(name = "EventColumnSubclassList")
    protected EventColumn.EventColumnSubclassList eventColumnSubclassList;

    public BigInteger getID() {
        return id;
    }

    public void setID(BigInteger value) {
        this.id = value;
    }

    public EventColumn.EventColumnSubclassList getEventColumnSubclassList() {
        return eventColumnSubclassList;
    }

    public void setEventColumnSubclassList(EventColumn.EventColumnSubclassList value) {
        this.eventColumnSubclassList = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "eventColumnSubclass" })
    public static class EventColumnSubclassList {

        @XmlElement(name = "EventColumnSubclass")
        protected List<EventColumn.EventColumnSubclassList.EventColumnSubclass> eventColumnSubclass;

        public List<EventColumn.EventColumnSubclassList.EventColumnSubclass> getEventColumnSubclass() {
            return this.eventColumnSubclass;
        }

        public void setEventColumnSubclass(List<EventColumnSubclass> eventColumnSubclass) {
            this.eventColumnSubclass = eventColumnSubclass;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {

        })
        public static class EventColumnSubclass {

            @XmlElement(name = "ID", required = true)
            protected BigInteger id;
            @XmlElement(required = true)
            protected String name;

            public BigInteger getID() {
                return id;
            }

            public void setID(BigInteger value) {
                this.id = value;
            }

            public String getName() {
                return name;
            }

            public void setName(String value) {
                this.name = value;
            }

        }
    }

}
