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

/**
 * A {@code <RequestType>} that names no rowset this model knows.
 * <p>
 * Told apart from a message that cannot be read at all, because the two deserve
 * different answers: Analysis Services returns a specific error code for an
 * unrecognised request type, and a client that gets it stops asking. A generic
 * parse failure tells it nothing it can act on.
 */
public class UnknownRequestTypeException extends XmlaCodecException {

    private static final long serialVersionUID = 1L;

    private final transient String requestType;

    public UnknownRequestTypeException(String requestType) {
        super("the request type " + requestType + " is not supported");
        this.requestType = requestType;
    }

    /** What the client asked for, verbatim, so the answer can name it back. */
    public String requestType() {
        return requestType;
    }
}
