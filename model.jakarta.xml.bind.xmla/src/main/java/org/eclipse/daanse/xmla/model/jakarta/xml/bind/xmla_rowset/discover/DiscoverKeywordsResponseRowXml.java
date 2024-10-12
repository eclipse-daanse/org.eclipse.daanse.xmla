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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.discover;

import java.io.Serializable;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.Row;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

/**
 * This schema rowset returns information about keywords that are reserved by the XMLA server. If
 * you call the Discover method with the DISCOVER_KEYWORDS enumeration value in the RequestType
 * element, the Discover method returns the DISCOVER_KEYWORDS rowset.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscoverKeywordsResponseRowXml")
public class DiscoverKeywordsResponseRowXml extends Row implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 3242326701758575261L;

    @XmlElement(name = "Keyword", required = true)
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
