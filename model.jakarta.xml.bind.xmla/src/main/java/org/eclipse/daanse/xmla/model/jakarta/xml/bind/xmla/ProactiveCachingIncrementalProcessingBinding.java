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

import javax.xml.datatype.Duration;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProactiveCachingIncrementalProcessingBinding", propOrder = { "refreshInterval",
        "incrementalProcessingNotifications" })
public class ProactiveCachingIncrementalProcessingBinding extends ProactiveCachingBinding {

    @XmlElement(name = "RefreshInterval")
    protected Duration refreshInterval;
    @XmlElement(name = "IncrementalProcessingNotification", required = true)
    @XmlElementWrapper(name = "IncrementalProcessingNotifications", required = true)
    protected List<IncrementalProcessingNotification> incrementalProcessingNotifications;

    public Duration getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(Duration value) {
        this.refreshInterval = value;
    }

    public List<IncrementalProcessingNotification> getIncrementalProcessingNotifications() {
        return incrementalProcessingNotifications;
    }

    public void setIncrementalProcessingNotifications(List<IncrementalProcessingNotification> value) {
        this.incrementalProcessingNotifications = value;
    }
}
