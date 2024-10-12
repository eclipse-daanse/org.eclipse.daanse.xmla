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
@XmlType(name = "FoldingParameters", propOrder = {

})
public class FoldingParameters {

    @XmlElement(name = "FoldIndex", required = true)
    protected Integer foldIndex;
    @XmlElement(name = "FoldCount", required = true)
    protected Integer foldCount;
    @XmlElement(name = "FoldMaxCases")
    protected Long foldMaxCases;
    @XmlElement(name = "FoldTargetAttribute")
    protected String foldTargetAttribute;

    public Integer getFoldIndex() {
        return foldIndex;
    }

    public void setFoldIndex(Integer value) {
        this.foldIndex = value;
    }

    public Integer getFoldCount() {
        return foldCount;
    }

    public void setFoldCount(Integer value) {
        this.foldCount = value;
    }

    public Long getFoldMaxCases() {
        return foldMaxCases;
    }

    public void setFoldMaxCases(Long value) {
        this.foldMaxCases = value;
    }

    public String getFoldTargetAttribute() {
        return foldTargetAttribute;
    }

    public void setFoldTargetAttribute(String value) {
        this.foldTargetAttribute = value;
    }
}
