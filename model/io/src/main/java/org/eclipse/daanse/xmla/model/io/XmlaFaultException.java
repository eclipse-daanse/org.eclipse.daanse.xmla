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

import java.util.List;

/**
 * The server refused, in a SOAP fault.
 * <p>
 * A refusal and a message this codec cannot read are different things: the
 * first is an answer - the server understood the question and said no - and the
 * second is a defect here.
 * <p>
 * Note that XMLA refuses in two shapes. A fault is one; the other is an
 * {@code <Exception/>} with a {@code <Messages>} block inside an otherwise
 * ordinary response, which arrives with HTTP 200 and is read into the model
 * rather than thrown. Both mean the same thing to a caller and neither means
 * the response was malformed.
 */
public class XmlaFaultException extends XmlaCodecException {

    private static final long serialVersionUID = 1L;

    private final transient List<String> details;

    public XmlaFaultException(String message, List<String> details) {
        super(message);
        this.details = List.copyOf(details);
    }

    /**
     * What the fault said: its {@code faultstring} and any {@code Description}
     * attribute, in the order they appeared. Empty when the fault carried neither.
     */
    public List<String> details() {
        return details;
    }
}
