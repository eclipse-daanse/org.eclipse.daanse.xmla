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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trace_Event_Categories", propOrder = { "data" })
public class TraceEventCategories {

    @XmlElement(name = "Data", required = true)
    protected TraceEventCategories.Data data;

    public TraceEventCategories.Data getData() {
        return data;
    }

    public void setData(TraceEventCategories.Data value) {
        this.data = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "eventCategory" })
    public static class Data {

        @XmlElement(name = "EventCategory", required = true)
        protected TraceEventCategories.Data.EventCategory eventCategory;

        public TraceEventCategories.Data.EventCategory getEventCategory() {
            return eventCategory;
        }

        public void setEventCategory(TraceEventCategories.Data.EventCategory value) {
            this.eventCategory = value;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {

        })
        public static class EventCategory {

            @XmlElement(name = "Name", required = true)
            protected String name;
            @XmlElement(name = "Type")
            protected String type;
            @XmlElement(name = "Description")
            protected String description;
            @XmlElement(name = "EventList", required = true)
            protected TraceEventCategories.Data.EventCategory.EventList eventList;

            public String getName() {
                return name;
            }

            public void setName(String value) {
                this.name = value;
            }

            public String getType() {
                return type;
            }

            public void setType(String value) {
                this.type = value;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String value) {
                this.description = value;
            }

            public TraceEventCategories.Data.EventCategory.EventList getEventList() {
                return eventList;
            }

            public void setEventList(TraceEventCategories.Data.EventCategory.EventList value) {
                this.eventList = value;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "event" })
            public static class EventList {

                @XmlElement(name = "Event")
                protected List<TraceEvent> event;

                public List<TraceEvent> getEvent() {
                    return this.event;
                }

                public void setEvent(List<TraceEvent> event) {
                    this.event = event;
                }
            }

        }

    }

}
