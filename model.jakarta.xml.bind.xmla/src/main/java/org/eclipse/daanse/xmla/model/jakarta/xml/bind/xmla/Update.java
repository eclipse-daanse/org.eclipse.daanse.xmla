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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Update", propOrder = {

})
public class Update {

    @XmlElement(name = "Object", required = true)
    protected XmlaObject object;
    @XmlElement(name = "Attribute", type = AttributeInsertUpdate.class)
    @XmlElementWrapper(name = "Attributes")
    protected List<AttributeInsertUpdate> attributes;
    @XmlElement(name = "MoveWithDescendants")
    protected Boolean moveWithDescendants;
    @XmlElement(name = "MoveToRoot")
    protected Boolean moveToRoot;
    @XmlElement(name = "Where", required = true)
    protected Where where;

    public XmlaObject getObject() {
        return object;
    }

    public void setObject(XmlaObject value) {
        this.object = value;
    }

    public List<AttributeInsertUpdate> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeInsertUpdate> value) {
        this.attributes = value;
    }

    public Boolean isMoveWithDescendants() {
        return moveWithDescendants;
    }

    public void setMoveWithDescendants(Boolean value) {
        this.moveWithDescendants = value;
    }

    public Boolean isMoveToRoot() {
        return moveToRoot;
    }

    public void setMoveToRoot(Boolean value) {
        this.moveToRoot = value;
    }

    public Where getWhere() {
        return where;
    }

    public void setWhere(Where value) {
        this.where = value;
    }

}
