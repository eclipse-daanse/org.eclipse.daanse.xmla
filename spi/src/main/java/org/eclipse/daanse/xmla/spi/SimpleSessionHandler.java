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
package org.eclipse.daanse.xmla.spi;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The protocol half of session handling: id minting and validity.
 * <p>
 * A session id is a UUID handed out on BeginSession and honoured until
 * EndSession - that much is the XMLA protocol and identical for every backend.
 * What a session <em>means</em> is the backend's business: a connector
 * overrides the two hooks to attach and release whatever it holds per session
 * (connections, caches), and never touches the bookkeeping.
 */
public abstract class SimpleSessionHandler implements XmlaSessionHandler {

    private final Set<String> sessions = ConcurrentHashMap.newKeySet();

    @Override
    public final Optional<String> beginSession(XmlaRequest request) {
        String sessionId = UUID.randomUUID().toString();
        onBeginSession(sessionId, request);
        sessions.add(sessionId);
        return Optional.of(sessionId);
    }

    @Override
    public final boolean checkSession(String sessionId, XmlaRequest request) {
        return sessions.contains(sessionId);
    }

    @Override
    public final void endSession(String sessionId, XmlaRequest request) {
        sessions.remove(sessionId);
        onEndSession(sessionId);
    }

    /**
     * The backend attaches what it holds per session; called before the id is
     * valid.
     */
    protected void onBeginSession(String sessionId, XmlaRequest request) {
        // nothing held per session by default
    }

    /**
     * The backend releases what it held; called after the id stopped being valid.
     */
    protected void onEndSession(String sessionId) {
        // nothing held per session by default
    }
}
