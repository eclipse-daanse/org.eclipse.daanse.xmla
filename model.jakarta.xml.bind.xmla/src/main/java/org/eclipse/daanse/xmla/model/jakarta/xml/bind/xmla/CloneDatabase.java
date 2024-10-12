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
@XmlType(name = "CloneDatabase", propOrder = {

})
public class CloneDatabase {

    @XmlElement(name = "Object", required = true)
    protected CloneDatabase.Object object;
    @XmlElement(name = "Target", required = true)
    protected CloneDatabase.Target target;

    public CloneDatabase.Object getObject() {
        return object;
    }

    public void setObject(CloneDatabase.Object value) {
        this.object = value;
    }

    public CloneDatabase.Target getTarget() {
        return target;
    }

    public void setTarget(CloneDatabase.Target value) {
        this.target = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "databaseID" })
    public static class Object {

        @XmlElement(name = "DatabaseID", required = true)
        protected ObjectReference databaseID;

        public ObjectReference getDatabaseID() {
            return databaseID;
        }

        public void setDatabaseID(ObjectReference value) {
            this.databaseID = value;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class Target extends AbstractTarget {

    }

}
