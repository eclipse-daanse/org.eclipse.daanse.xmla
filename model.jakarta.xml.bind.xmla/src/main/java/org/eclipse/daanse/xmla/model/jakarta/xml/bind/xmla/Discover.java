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
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "requestType", "restrictions", "properties" })
@XmlRootElement(name = "Discover")
public class Discover {

    @XmlElement(name = "RequestType", required = true)
    protected String requestType;

    @XmlElement(name = "Restrictions", required = true)
    protected Restrictions restrictions;
    @XmlElement(name = "Properties", required = true)
    protected Properties properties;

    public Discover() {
        // default constructor
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String value) {
        this.requestType = value;
    }

    public Restrictions getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(Restrictions value) {
        this.restrictions = value;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties value) {
        this.properties = value;
    }

}
