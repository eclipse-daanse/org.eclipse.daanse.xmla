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
 * A command that ran and failed, where the session survives the failure.
 * <p>
 * The specification gives a response two ways to carry bad news, and they are
 * not interchangeable. A SOAP fault says the <em>message</em> could not be
 * processed - it has no result element at all, and a client is entitled to
 * treat it as the end of the conversation. This exception says the opposite:
 * the request was understood, the server answered it, and the answer is that
 * the command did not succeed. It becomes an {@code <Exception/>} and a
 * {@code <Messages><Error/></Messages>} inside the ordinary result root, under
 * HTTP 200, exactly as a Microsoft server answers a writeback commit it cannot
 * honour.
 * <p>
 * Which of the two a connector throws is therefore a statement about the
 * session, not about severity: {@link XmlaRefusedException} for a request that
 * should never have been sent, this one for a command whose failure leaves the
 * caller free to try something else on the same session.
 */
public class XmlaCommandFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final Long errorCode;
    private final String source;
    private final String helpFile;

    public XmlaCommandFailedException(String description) {
        this(null, description, null, null);
    }

    public XmlaCommandFailedException(String description, Throwable cause) {
        this(null, description, null, null, cause);
    }

    /**
     * @param errorCode  the number the client sees as {@code ErrorCode}, or
     *                   {@code null} for a failure this server has no number for -
     *                   the attribute is then left off rather than invented
     * @param source     what failed, as a client would name it; {@code null} leaves
     *                   it out
     * @param helpFile   conventionally empty, and {@code null} leaves it out
     */
    public XmlaCommandFailedException(Long errorCode, String description, String source, String helpFile) {
        super(description);
        this.errorCode = errorCode;
        this.source = source;
        this.helpFile = helpFile;
    }

    public XmlaCommandFailedException(Long errorCode, String description, String source, String helpFile,
            Throwable cause) {
        super(description, cause);
        this.errorCode = errorCode;
        this.source = source;
        this.helpFile = helpFile;
    }

    /** {@code null} when this server has no number for the failure. */
    public Long errorCode() {
        return errorCode;
    }

    public String description() {
        return getMessage();
    }

    public String source() {
        return source;
    }

    public String helpFile() {
        return helpFile;
    }
}
