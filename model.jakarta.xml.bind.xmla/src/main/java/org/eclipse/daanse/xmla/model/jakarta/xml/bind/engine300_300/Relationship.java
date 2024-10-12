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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine300_300;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Relationship", propOrder = { "id", "visible", "fromRelationshipEnd", "toRelationshipEnd" })
public class Relationship implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "ID", required = true)
    protected String id;
    @XmlElement(name = "Visible")
    protected boolean visible;
    @XmlElement(name = "FromRelationshipEnd", required = true)
    protected RelationshipEnd fromRelationshipEnd;
    @XmlElement(name = "ToRelationshipEnd", required = true)
    protected RelationshipEnd toRelationshipEnd;

    public String getID() {
        return id;
    }

    public void setID(String value) {
        this.id = value;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean value) {
        this.visible = value;
    }

    public RelationshipEnd getFromRelationshipEnd() {
        return fromRelationshipEnd;
    }

    public void setFromRelationshipEnd(RelationshipEnd value) {
        this.fromRelationshipEnd = value;
    }

    public RelationshipEnd getToRelationshipEnd() {
        return toRelationshipEnd;
    }

    public void setToRelationshipEnd(RelationshipEnd value) {
        this.toRelationshipEnd = value;
    }
}
