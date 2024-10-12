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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.discover;

import java.io.Serializable;
import java.util.List;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Restriction;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.Row;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

/**
 * This schema rowset returns the names, restrictions, description, and other information for all
 * Discover requests.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscoverSchemaRowsetsResponseRowXml")
public class DiscoverSchemaRowsetsResponseRowXml extends Row implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 8000363696884603878L;

    @XmlElement(name = "SchemaName", required = true)
    private String schemaName;

    @XmlElement(name = "SchemaGuid")
    private String schemaGuid;

    @XmlElement(name = "Restrictions")
    private List<Restriction> restrictions;

    @XmlElement(name = "Description")
    private String description;

    @XmlElement(name = "RestrictionsMask")
    private Long restrictionsMask;

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getSchemaGuid() {
        return schemaGuid;
    }

    public void setSchemaGuid(String schemaGuid) {
        this.schemaGuid = schemaGuid;
    }

    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRestrictionsMask() {
        return restrictionsMask;
    }

    public void setRestrictionsMask(Long restrictionsMask) {
        this.restrictionsMask = restrictionsMask;
    }
}
