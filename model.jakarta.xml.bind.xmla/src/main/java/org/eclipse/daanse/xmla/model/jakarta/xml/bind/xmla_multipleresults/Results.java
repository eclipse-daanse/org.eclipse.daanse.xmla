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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_multipleresults;

import java.io.Serializable;
import java.util.List;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_empty.Emptyresult;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "results", propOrder = { "root" })
@XmlRootElement(name = "results")
public class Results implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(namespace = "urn:schemas-microsoft-com:xml-analysis:empty")
    private List<Emptyresult> root;

    public List<Emptyresult> getRoot() {
        return this.root;
    }

    public void setRoot(List<Emptyresult> root) {
        this.root = root;
    }

}
