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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PushedDataSource", propOrder = {

})
public class PushedDataSource {

    @XmlElement(required = true)
    protected PushedDataSource.Root root;
    @XmlElement(name = "EndOfData", required = true)
    protected PushedDataSource.EndOfData endOfData;

    public PushedDataSource.Root getRoot() {
        return root;
    }

    public void setRoot(PushedDataSource.Root value) {
        this.root = value;
    }

    public PushedDataSource.EndOfData getEndOfData() {
        return endOfData;
    }

    public void setEndOfData(PushedDataSource.EndOfData value) {
        this.endOfData = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class EndOfData {

        @XmlAttribute(name = "Parameter")
        protected String parameter;

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String value) {
            this.parameter = value;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Root {

        @XmlAttribute(name = "Parameter")
        protected String parameter;

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String value) {
            this.parameter = value;
        }

    }

}
