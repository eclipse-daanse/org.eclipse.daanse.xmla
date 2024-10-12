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

import java.math.BigInteger;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Attribute_InsertUpdate", propOrder = {

})
public class AttributeInsertUpdate {

    @XmlElement(name = "AttributeName", required = true)
    protected String attributeName;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Key")
    @XmlElementWrapper(name = "Keys")
    protected List<java.lang.Object> keys;
    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<TranslationInsertUpdate> translations;
    @XmlElement(name = "Value")
    protected String value;
    @XmlElement(name = "CUSTOM_ROLLUP")
    protected String customrollup;
    @XmlElement(name = "CUSTOM_ROLLUP_PROPERTIES")
    protected String customrollupproperties;
    @XmlElement(name = "UNARY_OPERATOR")
    protected String unaryoperator;
    @XmlElement(name = "SKIPPED_LEVELS")
    protected BigInteger skippedlevels;

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String value) {
        this.attributeName = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public List<java.lang.Object> getKeys() {
        return keys;
    }

    public void setKeys(List<java.lang.Object> value) {
        this.keys = value;
    }

    public List<TranslationInsertUpdate> getTranslations() {
        return translations;
    }

    public void setTranslations(List<TranslationInsertUpdate> value) {
        this.translations = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCustomrollup() {
        return customrollup;
    }

    public void setCustomrollup(String customrollup) {
        this.customrollup = customrollup;
    }

    public String getCustomrollupproperties() {
        return customrollupproperties;
    }

    public void setCustomrollupproperties(String customrollupproperties) {
        this.customrollupproperties = customrollupproperties;
    }

    public String getUnaryoperator() {
        return unaryoperator;
    }

    public void setUnaryoperator(String unaryoperator) {
        this.unaryoperator = unaryoperator;
    }

    public BigInteger getSkippedlevels() {
        return skippedlevels;
    }

    public void setSkippedlevels(BigInteger skippedlevels) {
        this.skippedlevels = skippedlevels;
    }

}
