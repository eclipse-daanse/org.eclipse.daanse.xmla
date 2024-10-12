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
@XmlType(name = "MergePartitions", propOrder = {

})
public class MergePartitions {

    @XmlElement(name = "Source", required = true)
    @XmlElementWrapper(name = "Sources", required = true)
    protected List<ObjectReference> sources;
    @XmlElement(name = "Target", required = true)
    protected ObjectReference target;

    public List<ObjectReference> getSources() {
        return sources;
    }

    public void setSources(List<ObjectReference> value) {
        this.sources = value;
    }

    public ObjectReference getTarget() {
        return target;
    }

    public void setTarget(ObjectReference value) {
        this.target = value;
    }

}
