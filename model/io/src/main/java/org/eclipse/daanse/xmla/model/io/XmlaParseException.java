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
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.model.io;

/**
 * A request that will not parse, refused with the number and the sentence Analysis Services refuses it with -- an
 * element that cannot appear where it stands is {@code 0xC10F0007}, "The EventType element at line 1, column 306
 * (namespace …) cannot appear under Envelope/Body/Execute/Command/Create/ObjectDefinition/Trace."
 */
public class XmlaParseException extends XmlaCodecException {

    private static final long serialVersionUID = 1L;

    /** An element that cannot appear under the element it stands in. */
    public static final long CANNOT_APPEAR = 0xC10F0007L;

    private final long errorCode;

    public XmlaParseException(long errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /** The unsigned 32-bit number Analysis Services answers this refusal with. */
    public long errorCode() {
        return errorCode;
    }
}
