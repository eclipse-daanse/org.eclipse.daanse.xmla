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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatementRowXml")
public class StatementRowXml extends Row {

    @XmlAnyElement
    private List<Element> any;

    public List<Element> getAny() {
        return any;
    }

    public void setAny(List<Element> any) {
        this.any = any;
    }
}
