/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.model.io;

import javax.xml.stream.XMLStreamReader;

/**
 * A message that could not be read or written.
 * <p>
 * Always carries where in the document the problem was: a codec that reports
 * only "cannot parse" is not much better than one that silently drops the
 * value.
 */
public class XmlaCodecException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public XmlaCodecException(String message, XMLStreamReader at) {
        super(withLocation(message, at));
    }

    public XmlaCodecException(String message, XMLStreamReader at, Throwable cause) {
        super(withLocation(message, at), cause);
    }

    public XmlaCodecException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlaCodecException(String message) {
        super(message);
    }

    private static String withLocation(String message, XMLStreamReader at) {
        if (at == null || at.getLocation() == null) {
            return message;
        }
        var location = at.getLocation();
        return message + " (line " + location.getLineNumber() + ", column " + location.getColumnNumber() + ")";
    }
}
