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

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "templateCategory", "templateName", "templateDescription", "event", "target" })
@XmlRootElement(name = "event_session")
public class EventSession implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "templateCategory")
    protected String templateCategory;
    @XmlElement(name = "templateName")
    protected String templateName;
    @XmlElement(name = "templateDescription")
    protected String templateDescription;
    @XmlElement(name = "event")
    protected List<Event2> event;
    @XmlElement(name = "target")
    protected List<Event2> target;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "maxMemory")
    @XmlSchemaType(name = "unsignedLong")
    protected BigInteger maxMemory;
    @XmlAttribute(name = "eventRetentionMode")
    protected RetentionModes eventRetentionMode;
    @XmlAttribute(name = "dispatchLatency")
    @XmlSchemaType(name = "unsignedInt")
    protected Long dispatchLatency;
    @XmlAttribute(name = "maxEventSize")
    @XmlSchemaType(name = "unsignedInt")
    protected Long maxEventSize;
    @XmlAttribute(name = "memoryPartitionMode")
    protected PartitionModes memoryPartitionMode;
    @XmlAttribute(name = "trackCausality")
    protected Boolean trackCausality;

    public String getTemplateCategory() {
        return templateCategory;
    }

    public void setTemplateCategory(String value) {
        this.templateCategory = value;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String value) {
        this.templateName = value;
    }

    public String getTemplateDescription() {
        return templateDescription;
    }

    public void setTemplateDescription(String value) {
        this.templateDescription = value;
    }

    public List<Event2> getEvent() {
        return this.event;
    }

    public List<Event2> getTarget() {
        return this.target;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public BigInteger getMaxMemory() {
        if (maxMemory == null) {
            return BigInteger.valueOf(4);
        } else {
            return maxMemory;
        }
    }

    public RetentionModes getEventRetentionMode() {
        if (eventRetentionMode == null) {
            return RetentionModes.ALLOW_SINGLE_EVENT_LOSS;
        } else {
            return eventRetentionMode;
        }
    }

    public void setEventRetentionMode(RetentionModes value) {
        this.eventRetentionMode = value;
    }

    public long getDispatchLatency() {
        if (dispatchLatency == null) {
            return 30L;
        } else {
            return dispatchLatency;
        }
    }

    public void setDispatchLatency(long value) {
        this.dispatchLatency = value;
    }

    public long getMaxEventSize() {
        if (maxEventSize == null) {
            return 0L;
        } else {
            return maxEventSize;
        }
    }

    public void setMaxEventSize(long value) {
        this.maxEventSize = value;
    }

    public PartitionModes getMemoryPartitionMode() {
        if (memoryPartitionMode == null) {
            return PartitionModes.NONE;
        } else {
            return memoryPartitionMode;
        }
    }

    public void setMemoryPartitionMode(PartitionModes value) {
        this.memoryPartitionMode = value;
    }

    public boolean isTrackCausality() {
        if (trackCausality == null) {
            return false;
        } else {
            return trackCausality;
        }
    }

    public void setTrackCausality(boolean value) {
        this.trackCausality = value;
    }

    public void setEvent(List<Event2> event) {
        this.event = event;
    }

    public void setTarget(List<Event2> target) {
        this.target = target;
    }

    public void setMaxMemory(BigInteger maxMemory) {
        this.maxMemory = maxMemory;
    }

    public void setDispatchLatency(Long dispatchLatency) {
        this.dispatchLatency = dispatchLatency;
    }

    public void setMaxEventSize(Long maxEventSize) {
        this.maxEventSize = maxEventSize;
    }

    public Boolean getTrackCausality() {
        return trackCausality;
    }

    public void setTrackCausality(Boolean trackCausality) {
        this.trackCausality = trackCausality;
    }
}
