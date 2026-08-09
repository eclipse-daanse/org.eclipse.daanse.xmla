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
package org.eclipse.daanse.xmla.api;

/**
 * A connector refusing a request on purpose, with the words the client should
 * read.
 * <p>
 * The transport writes this as a SOAP fault before any response byte, which is
 * what distinguishes it from an ordinary exception: the refusal is the answer,
 * not an accident. A connector uses it where a live server refuses too - a
 * request type nothing serves, an operation a backend does not offer - so the
 * client sees the same shape of no it would see from the real thing.
 */
public class XmlaRefusedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** Whose fault the refusal is, in SOAP's two-sided sense. */
    public enum Side {
        CLIENT, SERVER
    }

    private final Side side;

    public XmlaRefusedException(Side side, String message) {
        super(message);
        this.side = side;
    }

    /**
     * The refusal for a request type nothing serves, in the words a live server
     * uses.
     * <p>
     * The wording lives here so a connector never has to author SOAP fault prose:
     * it states what it cannot do, and the sentence a client sees is the
     * transport's business, kept in one place with the rest of the fault
     * vocabulary.
     */
    public static XmlaRefusedException unknownRequestType(String requestType) {
        return new XmlaRefusedException(Side.CLIENT,
                "XML for Analysis parser: The '" + requestType + "' request type was not recognized by the server.");
    }

    public Side side() {
        return side;
    }
}
