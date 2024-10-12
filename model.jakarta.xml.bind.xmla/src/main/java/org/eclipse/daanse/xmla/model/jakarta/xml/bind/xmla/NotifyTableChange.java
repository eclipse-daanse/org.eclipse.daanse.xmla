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
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotifyTableChange", propOrder = {

})
public class NotifyTableChange {

    @XmlElement(name = "Object", required = true)
    protected ObjectReference object;
    @XmlElement(name = "TableNotification", required = true)
    @XmlElementWrapper(name = "TableNotifications", required = true)
    protected List<TableNotification> tableNotifications;

    public ObjectReference getObject() {
        return object;
    }

    public void setObject(ObjectReference value) {
        this.object = value;
    }

    public List<TableNotification> getTableNotifications() {
        return tableNotifications;
    }

    public void setTableNotifications(List<TableNotification> value) {
        this.tableNotifications = value;
    }

}
