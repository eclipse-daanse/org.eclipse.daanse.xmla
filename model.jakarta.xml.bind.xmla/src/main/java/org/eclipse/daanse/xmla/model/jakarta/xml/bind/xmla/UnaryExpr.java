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
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "unary_expr", propOrder = { "not", "and", "or", "leaf" })
public class UnaryExpr {

    protected UnaryExpr not;
    protected BooleanExpr and;
    protected BooleanExpr or;
    protected PredLeaf leaf;

    public UnaryExpr getNot() {
        return not;
    }

    public void setNot(UnaryExpr value) {
        this.not = value;
    }

    public BooleanExpr getAnd() {
        return and;
    }

    public void setAnd(BooleanExpr value) {
        this.and = value;
    }

    public BooleanExpr getOr() {
        return or;
    }

    public void setOr(BooleanExpr value) {
        this.or = value;
    }

    public PredLeaf getLeaf() {
        return leaf;
    }

    public void setLeaf(PredLeaf value) {
        this.leaf = value;
    }

}
