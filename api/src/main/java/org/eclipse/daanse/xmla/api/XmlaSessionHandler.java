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

import java.util.Optional;

/**
 * XMLA sessions, as the specification describes them.
 * <p>
 * A session is the one piece of XMLA state that spans requests, and it lives
 * entirely in SOAP headers: the client sends
 * {@code <BeginSession mustUnderstand="1"/>}, the server creates a session and
 * answers with {@code <Session SessionId="…"/>}, every following request
 * carries that header, and {@code <EndSession/>} closes it. This interface is
 * that protocol from the server's side, with the transport doing the header
 * work.
 * <p>
 * It is a separate contract rather than methods on {@link XmlaConnector}
 * because sessions are optional in both directions: a stateless backend
 * registers none, and the transport then opens no sessions and echoes no ids.
 */
public interface XmlaSessionHandler {

    /**
     * Opens a session, or declines to.
     * <p>
     * The server owns the id: whatever is returned here is what the client is told
     * and what every later {@link #checkSession} will be asked about. Declining is
     * a valid answer for a caller this handler will not hold state for — the client
     * then simply gets no Session header, which the specification allows.
     */
    Optional<String> beginSession(XmlaRequest request);

    /**
     * Whether this session id is one this handler still honours.
     * <p>
     * A rejected id is answered as no session at all rather than echoed back —
     * echoing it would tell the client its expired session is still good.
     */
    boolean checkSession(String sessionId, XmlaRequest request);

    /** Ends a session. Ending one that was never opened is not an error. */
    void endSession(String sessionId, XmlaRequest request);
}
