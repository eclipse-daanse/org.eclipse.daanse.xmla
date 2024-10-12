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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cancel", propOrder = {

})
public class Cancel {

    @XmlElement(name = "ConnectionID")
    protected BigInteger connectionID;
    @XmlElement(name = "SessionID")
    protected String sessionID;
    @XmlElement(name = "SPID")
    protected BigInteger spid;
    @XmlElement(name = "CancelAssociated")
    protected Boolean cancelAssociated;

    public BigInteger getConnectionID() {
        return connectionID;
    }

    public void setConnectionID(BigInteger value) {
        this.connectionID = value;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String value) {
        this.sessionID = value;
    }

    public BigInteger getSPID() {
        return spid;
    }

    public void setSPID(BigInteger value) {
        this.spid = value;
    }

    public Boolean isCancelAssociated() {
        return cancelAssociated;
    }

    public void setCancelAssociated(Boolean value) {
        this.cancelAssociated = value;
    }

}
