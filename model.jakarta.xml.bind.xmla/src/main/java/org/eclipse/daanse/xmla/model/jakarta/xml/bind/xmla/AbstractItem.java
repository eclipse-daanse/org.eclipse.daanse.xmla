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

import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractItem", propOrder = {})
@XmlSeeAlso({ AggregationDesign.class, Cube.class, DataSource.class, DataSourceView.class, Database.class,
        Dimension.class, MdxScript.class, MeasureGroup.class, MiningModel.class, MiningStructure.class, Partition.class,
        Permission.class, Perspective.class, Role.class, Server.class, Trace.class, Assembly.class })
public abstract class AbstractItem {
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "CreatedTimestamp")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTimestamp;
    @XmlElement(name = "LastSchemaUpdate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastSchemaUpdate;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public XMLGregorianCalendar getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(XMLGregorianCalendar createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public XMLGregorianCalendar getLastSchemaUpdate() {
        return lastSchemaUpdate;
    }

    public void setLastSchemaUpdate(XMLGregorianCalendar lastSchemaUpdate) {
        this.lastSchemaUpdate = lastSchemaUpdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

}
