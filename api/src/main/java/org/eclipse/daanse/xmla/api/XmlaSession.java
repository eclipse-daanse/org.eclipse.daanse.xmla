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

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;

/**
 * One open session, as much of it as the protocol itself can observe.
 * <p>
 * These are the facts {@code DISCOVER_SESSIONS} ([MS-SSAS] 3.1.4.2.2.1.3.44)
 * asks a server for, and nothing beyond them. In particular there is no timeout
 * and no expiry rule here: how long a session may live is the handler's
 * decision, and keeping the comparison out of this class is what stops the
 * protocol side from acquiring an opinion about it.
 */
public final class XmlaSession {

    private final String id;
    private final Instant startedAt;
    private final AtomicLong commandCount = new AtomicLong();
    private volatile Instant lastUsedAt;
    private volatile Instant lastCommandStartedAt;
    private volatile Instant lastCommandEndedAt;
    private volatile AuthenticatedIdentity identity;

    XmlaSession(String id, Instant startedAt) {
        this.id = id;
        this.startedAt = startedAt;
        this.lastUsedAt = startedAt;
    }

    public String id() {
        return id;
    }

    public Instant startedAt() {
        return startedAt;
    }

    /** When a request last presented this id and was allowed to use it. */
    public Instant lastUsedAt() {
        return lastUsedAt;
    }

    public Optional<Instant> lastCommandStartedAt() {
        return Optional.ofNullable(lastCommandStartedAt);
    }

    public Optional<Instant> lastCommandEndedAt() {
        return Optional.ofNullable(lastCommandEndedAt);
    }

    /**
     * The identity the in-band handshake bound, or empty for a session that carries
     * none.
     */
    public Optional<AuthenticatedIdentity> identity() {
        return Optional.ofNullable(identity);
    }

    public long commandCount() {
        return commandCount.get();
    }

    public Duration elapsed(Instant now) {
        return Duration.between(startedAt, now);
    }

    public Duration idle(Instant now) {
        return Duration.between(lastUsedAt, now);
    }

    void touch(Instant now) {
        this.lastUsedAt = now;
    }

    void identity(AuthenticatedIdentity bound) {
        this.identity = bound;
    }

    public void commandStarted() {
        Instant now = Instant.now();
        lastCommandStartedAt = now;
        lastUsedAt = now;
        commandCount.incrementAndGet();
    }

    public void commandEnded() {
        Instant now = Instant.now();
        lastCommandEndedAt = now;
        lastUsedAt = now;
    }

    /**
     * Whether a command is running, which is what {@code SESSION_STATUS} reports.
     */
    public boolean busy() {
        Instant started = lastCommandStartedAt;
        Instant ended = lastCommandEndedAt;
        return started != null && (ended == null || ended.isBefore(started));
    }
}
