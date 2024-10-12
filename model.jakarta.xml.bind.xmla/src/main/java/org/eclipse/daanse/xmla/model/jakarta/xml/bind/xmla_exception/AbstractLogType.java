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
*/
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_exception;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractLogType", propOrder = { "location" })
@XmlSeeAlso({ ErrorType.class, WarningType.class })
public abstract class AbstractLogType {

    @XmlElement(name = "Location")
    protected MessageLocation location;
    @XmlAttribute(name = "Description")
    protected String description;
    @XmlAttribute(name = "Source")
    protected String source;
    @XmlAttribute(name = "HelpFile")
    protected String helpFile;

    public MessageLocation getLocation() {
        return location;
    }

    public void setLocation(MessageLocation location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHelpFile() {
        return helpFile;
    }

    public void setHelpFile(String helpFile) {
        this.helpFile = helpFile;
    }
}
