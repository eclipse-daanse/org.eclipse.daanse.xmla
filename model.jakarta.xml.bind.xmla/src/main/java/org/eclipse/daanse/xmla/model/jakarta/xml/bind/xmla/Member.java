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
@XmlType(name = "Member", propOrder = {

})
public class Member {

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Sid")
    protected String sid;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String value) {
        this.sid = value;
    }

}
