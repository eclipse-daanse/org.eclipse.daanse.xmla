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
@XmlType(name = "not_type", propOrder = { "not", "or", "and", "equal", "notEqual", "less", "lessOrEqual", "greater",
        "greaterOrEqual", "like", "notLike" })
public class NotType {

    @XmlElement(name = "Not")
    protected NotType not;
    @XmlElement(name = "Or")
    protected AndOrType or;
    @XmlElement(name = "And")
    protected AndOrType and;
    @XmlElement(name = "Equal")
    protected BoolBinop equal;
    @XmlElement(name = "NotEqual")
    protected BoolBinop notEqual;
    @XmlElement(name = "Less")
    protected BoolBinop less;
    @XmlElement(name = "LessOrEqual")
    protected BoolBinop lessOrEqual;
    @XmlElement(name = "Greater")
    protected BoolBinop greater;
    @XmlElement(name = "GreaterOrEqual")
    protected BoolBinop greaterOrEqual;
    @XmlElement(name = "Like")
    protected BoolBinop like;
    @XmlElement(name = "NotLike")
    protected BoolBinop notLike;

    public NotType getNot() {
        return not;
    }

    public void setNot(NotType value) {
        this.not = value;
    }

    public AndOrType getOr() {
        return or;
    }

    public void setOr(AndOrType value) {
        this.or = value;
    }

    public AndOrType getAnd() {
        return and;
    }

    public void setAnd(AndOrType value) {
        this.and = value;
    }

    public BoolBinop getEqual() {
        return equal;
    }

    public void setEqual(BoolBinop value) {
        this.equal = value;
    }

    public BoolBinop getNotEqual() {
        return notEqual;
    }

    public void setNotEqual(BoolBinop value) {
        this.notEqual = value;
    }

    public BoolBinop getLess() {
        return less;
    }

    public void setLess(BoolBinop value) {
        this.less = value;
    }

    public BoolBinop getLessOrEqual() {
        return lessOrEqual;
    }

    public void setLessOrEqual(BoolBinop value) {
        this.lessOrEqual = value;
    }

    public BoolBinop getGreater() {
        return greater;
    }

    public void setGreater(BoolBinop value) {
        this.greater = value;
    }

    public BoolBinop getGreaterOrEqual() {
        return greaterOrEqual;
    }

    public void setGreaterOrEqual(BoolBinop value) {
        this.greaterOrEqual = value;
    }

    public BoolBinop getLike() {
        return like;
    }

    public void setLike(BoolBinop value) {
        this.like = value;
    }

    public BoolBinop getNotLike() {
        return notLike;
    }

    public void setNotLike(BoolBinop value) {
        this.notLike = value;
    }
}
