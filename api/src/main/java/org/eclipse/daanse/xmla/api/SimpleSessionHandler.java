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

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;

/**
 * The protocol half of session handling: id minting, validity, and the facts
 * {@code DISCOVER_SESSIONS} reports.
 * <p>
 * A session id is a UUID handed out on BeginSession and honoured until
 * something ends it - that much is the XMLA protocol and identical for every
 * backend. What a session <em>means</em> and <em>how long it lives</em> are the
 * backend's business: it overrides the hooks below to attach and release what
 * it holds, and {@link #stillHeld} is where it answers whether a session is
 * still its own.
 * <p>
 * Everything about one session lives in a single entry, so an identity cannot
 * outlive the id it belongs to, and {@link #expire} is the only way an entry
 * leaves - which is what makes "released exactly once" true under concurrency.
 */
public abstract class SimpleSessionHandler implements XmlaSessionHandler {

    private final Map<String, XmlaSession> sessions = new ConcurrentHashMap<>();

    @Override
    public final Optional<String> beginSession(XmlaRequest request) {
        if (!mayOpenSession(request)) {
            return Optional.empty();
        }
        String sessionId = UUID.randomUUID().toString();
        sessions.put(sessionId, new XmlaSession(sessionId, Instant.now()));
        onBeginSession(sessionId, request);
        return Optional.of(sessionId);
    }

    @Override
    public final boolean checkSession(String sessionId, XmlaRequest request) {
        XmlaSession session = sessionId == null ? null : sessions.get(sessionId);
        if (session == null) {
            return false;
        }
        if (!stillHeld(session)) {
            expire(sessionId);
            return false;
        }
        AuthenticatedIdentity bound = session.identity().orElse(null);
        if (bound != null && request.isAuthenticated() && !bound.name().equals(request.userName())) {
            // Not expired, and not this caller's. The rightful owner's idle clock keeps
            // running, so a stranger cannot keep somebody else's session alive either.
            // A caller carrying only a stand-in identity has claimed to be nobody, so
            // this is not that case - the session is what says who they are.
            return false;
        }
        session.touch(Instant.now());
        return true;
    }

    @Override
    public final void endSession(String sessionId, XmlaRequest request) {
        expire(sessionId);
    }

    @Override
    public final void bindIdentity(String sessionId, AuthenticatedIdentity identity) {
        XmlaSession session = sessionId == null ? null : sessions.get(sessionId);
        if (session == null) {
            throw new XmlaRefusedException(XmlaRefusedException.Side.SERVER,
                    "the session this handshake authenticated is no longer open");
        }
        AuthenticatedIdentity bound = session.identity().orElse(null);
        if (bound != null && !bound.name().equals(identity.name())) {
            // Otherwise anybody who learns a session id could re-run the handshake as
            // themselves and take the session over.
            throw new XmlaRefusedException(XmlaRefusedException.Side.CLIENT,
                    "this session already belongs to another caller");
        }
        if (bound == null) {
            session.identity(identity);
            onIdentityBound(sessionId, identity);
        }
    }

    @Override
    public final Optional<AuthenticatedIdentity> identityOf(String sessionId) {
        XmlaSession session = sessionId == null ? null : sessions.get(sessionId);
        return session == null ? Optional.empty() : session.identity();
    }

    @Override
    public final Optional<XmlaSession> session(String sessionId) {
        return sessionId == null ? Optional.empty() : Optional.ofNullable(sessions.get(sessionId));
    }

    @Override
    public final Collection<XmlaSession> sessions() {
        return List.copyOf(sessions.values());
    }

    /**
     * Ends a session on this handler's own initiative - an idle sweep, an
     * administrative action, an external system saying so.
     * <p>
     * The removal is the guard: whoever takes the entry out runs
     * {@link #onEndSession}, and a concurrent {@code EndSession} and sweep
     * therefore release what the session held exactly once.
     */
    protected final void expire(String sessionId) {
        if (sessionId != null && sessions.remove(sessionId) != null) {
            onEndSession(sessionId);
        }
    }

    /** Whether another session may be opened at all; {@code false} declines it. */
    protected boolean mayOpenSession(XmlaRequest request) {
        return true;
    }

    /**
     * Whether this handler still holds the session, asked once per request that
     * presents its id.
     * <p>
     * Answering {@code false} ends it. This is where an idle timeout, an external
     * expiry or an administrative decision takes effect; the protocol side has no
     * opinion and asks for no reason.
     */
    protected boolean stillHeld(XmlaSession session) {
        return true;
    }

    /** The backend attaches what it holds per session; the id is already valid. */
    protected void onBeginSession(String sessionId, XmlaRequest request) {
        // nothing held per session by default
    }

    /** The backend releases what it held; the id has stopped being valid. */
    protected void onEndSession(String sessionId) {
        // nothing held per session by default
    }

    /**
     * The session learned who is calling.
     * <p>
     * A backend that opened anything while the caller was still anonymous discards
     * it here: state built without an identity carries the wrong access.
     */
    protected void onIdentityBound(String sessionId, AuthenticatedIdentity identity) {
        // nothing to revisit by default
    }
}
