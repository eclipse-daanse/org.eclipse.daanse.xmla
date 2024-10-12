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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.mdschema;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

/**
 * This schema rowset describes the measure groups within a database.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MdSchemaMeasureGroupsResponseRowXml")
public class MdSchemaMeasureGroupsResponseRowXml extends AbstractMdSchemaResponseRowXml implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 6352460080685075397L;

    /**
     * The name of the measure group.
     */
    @XmlElement(name = "MEASUREGROUP_NAME", required = false)
    private String measureGroupName;

    /**
     * A description of the member.
     */
    @XmlElement(name = "DESCRIPTION", required = false)
    private String description;

    /**
     * When true, indicates that the measure group is write- enabled; otherwise false. Returns a value
     * of true if the measure group is write- enabled.
     */
    @XmlElement(name = "IS_WRITE_ENABLED", required = false)
    private Boolean isWriteEnabled;

    /**
     * The caption for the measure group.
     */
    @XmlElement(name = "MEASUREGROUP_CAPTION", required = false)
    private String measureGroupCaption;

    public String getMeasureGroupName() {
        return measureGroupName;
    }

    public void setMeasureGroupName(String measureGroupName) {
        this.measureGroupName = measureGroupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getWriteEnabled() {
        return isWriteEnabled;
    }

    public void setWriteEnabled(Boolean writeEnabled) {
        isWriteEnabled = writeEnabled;
    }

    public String getMeasureGroupCaption() {
        return measureGroupCaption;
    }

    public void setMeasureGroupCaption(String measureGroupCaption) {
        this.measureGroupCaption = measureGroupCaption;
    }
}
