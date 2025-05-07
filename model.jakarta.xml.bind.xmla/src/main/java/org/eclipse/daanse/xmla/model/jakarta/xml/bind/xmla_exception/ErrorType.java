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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_exception;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorType", propOrder = {

})
public class ErrorType extends AbstractLogType implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "Callstack", namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset")
    protected String callstack;
    @XmlAttribute(name = "ErrorCode", namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset")
    protected Long errorCode;

    public String getCallstack() {
        return callstack;
    }

    public void setCallstack(String value) {
        this.callstack = value;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long value) {
        this.errorCode = value;
    }

}
