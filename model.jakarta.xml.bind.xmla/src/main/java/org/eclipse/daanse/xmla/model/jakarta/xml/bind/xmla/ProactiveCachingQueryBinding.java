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
@XmlType(name = "ProactiveCachingQueryBinding", propOrder = { "refreshInterval", "queryNotifications" })
public class ProactiveCachingQueryBinding extends ProactiveCachingBinding {

    @XmlElement(name = "RefreshInterval", required = true)
    protected Duration refreshInterval;
    @XmlElement(name = "QueryNotification", required = true)
    @XmlElementWrapper(name = "QueryNotifications", required = true)
    protected List<QueryNotification> queryNotifications;

    public Duration getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(Duration value) {
        this.refreshInterval = value;
    }

    public List<QueryNotification> getQueryNotifications() {
        return queryNotifications;
    }

    public void setQueryNotifications(List<QueryNotification> value) {
        this.queryNotifications = value;
    }

}
