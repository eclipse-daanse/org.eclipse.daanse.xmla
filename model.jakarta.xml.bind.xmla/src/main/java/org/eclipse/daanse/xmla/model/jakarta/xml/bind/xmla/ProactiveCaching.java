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

import javax.xml.datatype.Duration;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProactiveCaching", propOrder = {

})
public class ProactiveCaching {

    @XmlElement(name = "OnlineMode")
    protected String onlineMode;
    @XmlElement(name = "AggregationStorage")
    protected String aggregationStorage;
    @XmlElement(name = "Source")
    protected ProactiveCachingBinding source;
    @XmlElement(name = "SilenceInterval")
    protected Duration silenceInterval;
    @XmlElement(name = "Latency")
    protected Duration latency;
    @XmlElement(name = "SilenceOverrideInterval")
    protected Duration silenceOverrideInterval;
    @XmlElement(name = "ForceRebuildInterval")
    protected Duration forceRebuildInterval;
    @XmlElement(name = "Enabled")
    protected Boolean enabled;

    public String getOnlineMode() {
        return onlineMode;
    }

    public void setOnlineMode(String onlineMode) {
        this.onlineMode = onlineMode;
    }

    public String getAggregationStorage() {
        return aggregationStorage;
    }

    public void setAggregationStorage(String aggregationStorage) {
        this.aggregationStorage = aggregationStorage;
    }

    public ProactiveCachingBinding getSource() {
        return source;
    }

    public void setSource(ProactiveCachingBinding source) {
        this.source = source;
    }

    public Duration getSilenceInterval() {
        return silenceInterval;
    }

    public void setSilenceInterval(Duration silenceInterval) {
        this.silenceInterval = silenceInterval;
    }

    public Duration getLatency() {
        return latency;
    }

    public void setLatency(Duration latency) {
        this.latency = latency;
    }

    public Duration getSilenceOverrideInterval() {
        return silenceOverrideInterval;
    }

    public void setSilenceOverrideInterval(Duration silenceOverrideInterval) {
        this.silenceOverrideInterval = silenceOverrideInterval;
    }

    public Duration getForceRebuildInterval() {
        return forceRebuildInterval;
    }

    public void setForceRebuildInterval(Duration forceRebuildInterval) {
        this.forceRebuildInterval = forceRebuildInterval;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
