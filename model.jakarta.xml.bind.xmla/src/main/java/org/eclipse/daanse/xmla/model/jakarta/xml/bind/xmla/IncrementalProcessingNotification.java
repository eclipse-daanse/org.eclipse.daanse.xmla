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
@XmlType(name = "IncrementalProcessingNotification", propOrder = {

})
public class IncrementalProcessingNotification {

    @XmlElement(name = "TableID", required = true)
    protected String tableID;
    @XmlElement(name = "ProcessingQuery", required = true)
    protected String processingQuery;

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String value) {
        this.tableID = value;
    }

    public String getProcessingQuery() {
        return processingQuery;
    }

    public void setProcessingQuery(String value) {
        this.processingQuery = value;
    }

}
