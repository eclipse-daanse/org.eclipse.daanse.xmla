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

    private final Long errorCode;

    /** A refusal this server has no Analysis Services number for: the fault then carries no {@code Error} element. */
    public XmlaRefusedException(Side side, String message) {
        this(side, null, message);
    }

    /**
     * A refusal with the number Analysis Services answers the same refusal with.
     * <p>
     * The number is what the client sees as {@code ErrorCode}, and the transport writes the fault code in the form
     * SSAS uses for it, {@code XMLAnalysisError.0x<hex>}. Only the number Analysis Services gives the same refusal
     * belongs here; a refusal it has no number for keeps the other constructor rather than an invented code.
     *
     * @param errorCode the unsigned 32-bit number, e.g. {@code 0xC1140002L}; {@code null} for none
     */
    public XmlaRefusedException(Side side, Long errorCode, String message) {
        super(message);
        this.side = side;
        this.errorCode = errorCode;
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

    /**
     * The refusal [MS-SSAS] 3.1.3.1 demands for a {@code Session} or
     * {@code EndSession} header the server does not honour.
     * <p>
     * The specification collapses "not valid" and "has timed out" into one answer
     * and names no error code for it, so neither does this.
     */
    public static XmlaRefusedException invalidSession(String sessionId) {
        return new XmlaRefusedException(Side.CLIENT,
                "XML for Analysis parser: The session ID '" + sessionId + "' is not valid or has timed out.");
    }

    /** A {@code BeginSession} this server will not answer with a session. */
    public static XmlaRefusedException sessionNotOpened() {
        return new XmlaRefusedException(Side.SERVER, "XML for Analysis parser: The server declined to open a session.");
    }

    public Side side() {
        return side;
    }

    /** The Analysis Services number of this refusal, or {@code null} when this server has none for it. */
    public Long errorCode() {
        return errorCode;
    }
}
