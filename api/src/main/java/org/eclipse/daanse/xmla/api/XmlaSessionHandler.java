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

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;

/**
 * XMLA sessions, as the specification describes them, from the server's side.
 * <p>
 * A session is the one piece of XMLA state that spans requests, and it lives
 * entirely in SOAP headers: the client sends
 * {@code <BeginSession mustUnderstand="1"/>}, the server creates a session and
 * answers with {@code <Session SessionId="…"/>}, every following request
 * carries that header, and {@code <EndSession/>} closes it.
 * <p>
 * <strong>This interface owns the lifetime; the protocol does not.</strong>
 * [MS-SSAS] 3.1.3.1 makes idle expiry a {@code MAY} with no value, and 3.1.2
 * Timers says the protocol has none, so nothing above this interface decides
 * how long a session lives. An implementation may stop honouring an id at any
 * moment and for any reason - an idle sweep, an administrative action, its own
 * restart, an external system ending the underlying resource - and the
 * transport simply believes it.
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
     * a valid answer - for a caller this handler will not hold state for, or when
     * it is already holding as many as it will.
     */
    Optional<String> beginSession(XmlaRequest request);

    /**
     * Whether this handler still honours this id, for this caller.
     * <p>
     * Asked once per request that bears a {@code Session} or {@code EndSession}
     * header, before anything is dispatched, and the answer is final - the
     * transport does not cache it and does not second-guess it. An implementation
     * that expires sessions on inactivity may treat this call as the session's last
     * use.
     * <p>
     * {@code false} means the specification's fault, and it covers both cases 3.1.3
     * names: an id that was never valid and one that has timed out. They are not
     * distinguished, because the wire has no field for the difference and telling a
     * caller which one it was says whether the id ever existed.
     * <p>
     * A session that carries a bound identity belongs to that identity: a caller
     * who authenticated as somebody else is refused. A caller who presents it
     * anonymously is not - that is the ordinary case after the in-band handshake,
     * where the session is the only thing carrying the identity.
     */
    boolean checkSession(String sessionId, XmlaRequest request);

    /**
     * Ends a session. Ending one this handler does not hold is not an error here.
     */
    void endSession(String sessionId, XmlaRequest request);

    /**
     * Binds the identity the in-band {@code Authenticate} handshake established to
     * this session.
     * <p>
     * Over HTTP there is no connection to hold what the handshake produced, so the
     * session holds it and every later request bearing the id runs as this
     * identity.
     *
     * @throws XmlaRefusedException if the session is gone, or already belongs to a
     *                              different caller
     */
    void bindIdentity(String sessionId, AuthenticatedIdentity identity);

    /** The identity bound to this session, or empty when it carries none. */
    Optional<AuthenticatedIdentity> identityOf(String sessionId);

    /** One open session by id, or empty when this handler does not hold it. */
    default Optional<XmlaSession> session(String sessionId) {
        return Optional.empty();
    }

    /**
     * Every session this handler currently holds, which is what
     * {@code DISCOVER_SESSIONS} reports.
     */
    default Collection<XmlaSession> sessions() {
        return List.of();
    }
}
