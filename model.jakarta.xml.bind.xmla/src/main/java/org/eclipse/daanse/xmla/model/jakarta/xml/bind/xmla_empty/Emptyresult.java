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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_empty;

import java.io.Serializable;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_exception.Exception;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_exception.Messages;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "emptyresult", propOrder = { "exception", "messages" })
@XmlRootElement(name = "root")
public class Emptyresult implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "Exception", namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset")
    protected Exception exception;
    @XmlElement(name = "Messages", namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset")
    protected Messages messages;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception value) {
        this.exception = value;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages value) {
        this.messages = value;
    }

}
