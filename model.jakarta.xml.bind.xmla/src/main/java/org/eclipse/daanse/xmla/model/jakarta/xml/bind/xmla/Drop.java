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
@XmlType(name = "Drop", propOrder = {

})
public class Drop {

    @XmlElement(name = "Object", required = true)
    protected XmlaObject object;
    @XmlElement(name = "DeleteWithDescendants")
    protected Boolean deleteWithDescendants;
    @XmlElement(name = "Where", required = true)
    protected Where where;

    public XmlaObject getObject() {
        return object;
    }

    public void setObject(XmlaObject value) {
        this.object = value;
    }

    public Boolean isDeleteWithDescendants() {
        return deleteWithDescendants;
    }

    public void setDeleteWithDescendants(Boolean value) {
        this.deleteWithDescendants = value;
    }

    public Where getWhere() {
        return where;
    }

    public void setWhere(Where value) {
        this.where = value;
    }
}
