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

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Account", propOrder = {

})
public class Account {

    @XmlElement(name = "AccountType", required = true)
    protected String accountType;
    @XmlElement(name = "AggregationFunction")
    protected String aggregationFunction;
    @XmlElement(name = "Alias", type = String.class)
    @XmlElementWrapper(name = "Aliases")
    protected List<String> aliases;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String value) {
        this.accountType = value;
    }

    public String getAggregationFunction() {
        return aggregationFunction;
    }

    public void setAggregationFunction(String value) {
        this.aggregationFunction = value;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> value) {
        this.aliases = value;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

}
