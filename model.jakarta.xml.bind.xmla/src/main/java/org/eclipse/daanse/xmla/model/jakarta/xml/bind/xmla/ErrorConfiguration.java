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
@XmlType(name = "ErrorConfiguration", propOrder = {

})
public class ErrorConfiguration {

    @XmlElement(name = "KeyErrorLimit")
    protected Long keyErrorLimit;
    @XmlElement(name = "KeyErrorLogFile")
    protected String keyErrorLogFile;
    @XmlElement(name = "KeyErrorAction")
    protected String keyErrorAction;
    @XmlElement(name = "KeyErrorLimitAction")
    protected String keyErrorLimitAction;
    @XmlElement(name = "KeyNotFound")
    protected String keyNotFound;
    @XmlElement(name = "KeyDuplicate")
    protected String keyDuplicate;
    @XmlElement(name = "NullKeyConvertedToUnknown")
    protected String nullKeyConvertedToUnknown;
    @XmlElement(name = "NullKeyNotAllowed")
    protected String nullKeyNotAllowed;
    @XmlElement(name = "CalculationError", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200")
    protected String calculationError;

    public Long getKeyErrorLimit() {
        return keyErrorLimit;
    }

    public void setKeyErrorLimit(Long value) {
        this.keyErrorLimit = value;
    }

    public String getKeyErrorLogFile() {
        return keyErrorLogFile;
    }

    public void setKeyErrorLogFile(String value) {
        this.keyErrorLogFile = value;
    }

    public String getKeyErrorAction() {
        return keyErrorAction;
    }

    public void setKeyErrorAction(String value) {
        this.keyErrorAction = value;
    }

    public String getKeyErrorLimitAction() {
        return keyErrorLimitAction;
    }

    public void setKeyErrorLimitAction(String value) {
        this.keyErrorLimitAction = value;
    }

    public String getKeyNotFound() {
        return keyNotFound;
    }

    public void setKeyNotFound(String value) {
        this.keyNotFound = value;
    }

    public String getKeyDuplicate() {
        return keyDuplicate;
    }

    public void setKeyDuplicate(String value) {
        this.keyDuplicate = value;
    }

    public String getNullKeyConvertedToUnknown() {
        return nullKeyConvertedToUnknown;
    }

    public void setNullKeyConvertedToUnknown(String value) {
        this.nullKeyConvertedToUnknown = value;
    }

    public String getNullKeyNotAllowed() {
        return nullKeyNotAllowed;
    }

    public void setNullKeyNotAllowed(String value) {
        this.nullKeyNotAllowed = value;
    }

    public String getCalculationError() {
        return calculationError;
    }

    public void setCalculationError(String value) {
        this.calculationError = value;
    }

}
