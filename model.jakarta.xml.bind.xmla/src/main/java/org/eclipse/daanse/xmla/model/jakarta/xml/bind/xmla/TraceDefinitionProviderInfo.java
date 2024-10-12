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
@XmlType(name = "Trace_Definition_ProviderInfo", propOrder = { "data" })
public class TraceDefinitionProviderInfo {

    @XmlElement(name = "Data", required = true)
    protected TraceDefinitionProviderInfo.Data data;

    public TraceDefinitionProviderInfo.Data getData() {
        return data;
    }

    public void setData(TraceDefinitionProviderInfo.Data value) {
        this.data = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class Data {

        @XmlElement(name = "Name", required = true)
        protected String name;
        @XmlElement(name = "Version", required = true)
        protected TraceDefinitionProviderInfo.Data.Version version;
        @XmlElement(name = "Type")
        protected String type;
        @XmlElement(name = "Description")
        protected String description;

        public String getName() {
            return name;
        }

        public void setName(String value) {
            this.name = value;
        }

        public TraceDefinitionProviderInfo.Data.Version getVersion() {
            return version;
        }

        public void setVersion(TraceDefinitionProviderInfo.Data.Version value) {
            this.version = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String value) {
            this.type = value;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String value) {
            this.description = value;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {

        })
        public static class Version {

            @XmlElement(name = "Major")
            protected String major;
            @XmlElement(name = "Minor")
            protected String minor;
            @XmlElement(name = "BuildNumber")
            protected String buildNumber;

            public String getMajor() {
                return major;
            }

            public void setMajor(String value) {
                this.major = value;
            }

            public String getMinor() {
                return minor;
            }

            public void setMinor(String value) {
                this.minor = value;
            }

            public String getBuildNumber() {
                return buildNumber;
            }

            public void setBuildNumber(String value) {
                this.buildNumber = value;
            }
        }

    }

}
