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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine300;

import java.io.Serializable;
import java.math.BigInteger;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HierarchyVisualizationProperties", propOrder = { "contextualNameRule", "folderPosition" })
public class HierarchyVisualizationProperties implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "ContextualNameRule", defaultValue = "None")
    protected String contextualNameRule;
    @XmlElement(name = "FolderPosition", defaultValue = "-1")
    protected BigInteger folderPosition;

    public String getContextualNameRule() {
        return contextualNameRule;
    }

    public void setContextualNameRule(String value) {
        this.contextualNameRule = value;
    }

    public BigInteger getFolderPosition() {
        return folderPosition;
    }

    public void setFolderPosition(BigInteger value) {
        this.folderPosition = value;
    }

}
