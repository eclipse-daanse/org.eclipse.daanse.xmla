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
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CubeHierarchy", propOrder = {

})
public class CubeHierarchy {

    @XmlElement(name = "HierarchyID", required = true)
    protected String hierarchyID;
    @XmlElement(name = "OptimizedState")
    protected String optimizedState;
    @XmlElement(name = "Visible")
    protected Boolean visible;
    @XmlElement(name = "Enabled")
    protected Boolean enabled;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;

    public String getHierarchyID() {
        return hierarchyID;
    }

    public void setHierarchyID(String value) {
        this.hierarchyID = value;
    }

    public String getOptimizedState() {
        return optimizedState;
    }

    public void setOptimizedState(String value) {
        this.optimizedState = value;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean value) {
        this.visible = value;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean value) {
        this.enabled = value;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

}
