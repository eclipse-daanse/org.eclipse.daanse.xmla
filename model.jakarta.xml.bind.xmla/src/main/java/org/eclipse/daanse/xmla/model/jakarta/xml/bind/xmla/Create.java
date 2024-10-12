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
@XmlType(name = "Create", propOrder = {

})
public class Create {

    @XmlElement(name = "ParentObject")
    protected ObjectReference parentObject;
    @XmlElement(name = "ObjectDefinition", required = true)
    protected MajorObject objectDefinition;
    @XmlAttribute(name = "Scope")
    protected Scope scope;
    @XmlAttribute(name = "AllowOverwrite")
    protected Boolean allowOverwrite;

    public ObjectReference getParentObject() {
        return parentObject;
    }

    public void setParentObject(ObjectReference value) {
        this.parentObject = value;
    }

    public MajorObject getObjectDefinition() {
        return objectDefinition;
    }

    public void setObjectDefinition(MajorObject value) {
        this.objectDefinition = value;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope value) {
        this.scope = value;
    }

    public boolean isAllowOverwrite() {
        return allowOverwrite;
    }

    public void setAllowOverwrite(boolean value) {
        this.allowOverwrite = value;
    }

}
