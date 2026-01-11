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
package org.eclipse.daanse.xmla.server.adapter.soapmessage;

/**
 * Exception thrown when SOAP message operations fail. Typically wraps
 * {@link jakarta.xml.soap.SOAPException} to provide unchecked exception
 * handling for SOAP-related errors.
 */
public class XmlaSoapException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public XmlaSoapException(String message) {
        super(message);
    }

    public XmlaSoapException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlaSoapException(Throwable cause) {
        super(cause);
    }
}
