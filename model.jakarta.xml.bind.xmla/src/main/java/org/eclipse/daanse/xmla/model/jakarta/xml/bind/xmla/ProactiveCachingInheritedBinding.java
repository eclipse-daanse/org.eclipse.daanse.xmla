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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProactiveCachingInheritedBinding", propOrder = { "notificationTechnique" })
public class ProactiveCachingInheritedBinding extends ProactiveCachingObjectNotificationBinding {

    @XmlElement(name = "NotificationTechnique")
    protected String notificationTechnique;

    public String getNotificationTechnique() {
        return notificationTechnique;
    }

    public void setNotificationTechnique(String value) {
        this.notificationTechnique = value;
    }
}
