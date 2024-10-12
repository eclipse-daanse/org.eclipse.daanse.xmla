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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine200_200;

import java.io.Serializable;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Binding;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExpressionBinding", propOrder = { "expression" })
public class ExpressionBinding extends Binding implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "Expression", required = true)
    protected String expression;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String value) {
        this.expression = value;
    }

}
