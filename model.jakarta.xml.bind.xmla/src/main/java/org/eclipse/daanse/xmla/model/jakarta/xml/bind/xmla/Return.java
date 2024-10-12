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

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_empty.Emptyresult;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.Mddataset;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_multipleresults.Results;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.Rowset;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "return", propOrder = { "value" }, namespace = "")
public class Return {

    @XmlElementRefs({ @XmlElementRef(name = "root", type = Mddataset.class, namespace = "", required = false),
            @XmlElementRef(name = "root", type = Rowset.class, namespace = "", required = false),
            @XmlElementRef(name = "root", type = Emptyresult.class, namespace = "", required = false),
            @XmlElementRef(name = "results", type = Results.class, namespace = "", required = false) })
    protected java.lang.Object value;

    public java.lang.Object getValue() {
        return value;
    }

    public void setValue(java.lang.Object value) {
        this.value = value;
    }

}
