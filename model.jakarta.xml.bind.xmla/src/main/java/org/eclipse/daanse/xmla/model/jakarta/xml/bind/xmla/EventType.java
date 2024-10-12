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

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine300_300.XEvent;

import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "events", "xEvent" })
@XmlRootElement(name = "EventType")
public class EventType {

    @XmlElement(name = "Event")
    @XmlElementWrapper(name = "Events")
    protected List<Event> events;
    @XmlElement(name = "XEvent", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300/300")
    protected XEvent xEvent;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> value) {
        this.events = value;
    }

    public XEvent getXEvent() {
        return xEvent;
    }

    public void setXEvent(XEvent value) {
        this.xEvent = value;
    }

}
