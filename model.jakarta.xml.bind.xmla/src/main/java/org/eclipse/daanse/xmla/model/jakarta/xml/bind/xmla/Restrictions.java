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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.util.ElementToEntryAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "restrictionList" })
public class Restrictions {

    @XmlElementWrapper(name = "RestrictionList")
    @XmlAnyElement(lax = true)
    @XmlJavaTypeAdapter(value = ElementToEntryAdapter.class)
    protected List<Entry<String, String>> restrictionList;

    public Restrictions() {
        // constructor
    }

    public List<Entry<String, String>> getRestrictionList() {
        return this.restrictionList;
    }

    public Map<String, String> getRestrictionMap() {

        Map<String, String> map = new HashMap<>();
        if (this.restrictionList != null) {

            for (Entry<String, String> entry : restrictionList) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    public void setRestrictionList(List<Entry<String, String>> restrictionList) {
        this.restrictionList = restrictionList;
    }

}
