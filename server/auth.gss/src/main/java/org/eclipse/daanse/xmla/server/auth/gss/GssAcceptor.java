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
package org.eclipse.daanse.xmla.server.auth.gss;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;

/**
 * The GSS-API acceptor both channels share.
 * <p>
 * A handshake is a sequence of tokens rather than one exchange, so a
 * half-finished {@link GSSContext} has to survive between requests. It is kept
 * under a key the caller supplies and dropped as soon as it is finished or
 * stale, because an abandoned handshake would otherwise hold a context forever.
 * <p>
 * A {@link GSSContext} is not thread safe and holds the state of one handshake,
 * so every round on a key runs under that key's lock, and disposal takes the
 * same lock - otherwise a context could be disposed while another thread was
 * inside {@code acceptSecContext} on it. Which key is safe to use is the
 * caller's problem and a real one: see {@link GssAuthenticator}.
 */
final class GssAcceptor {

    /** SPNEGO, as [RFC4178] registers it. */
    static final String SPNEGO_OID = "1.3.6.1.5.5.2";

    /** Kerberos v5, for a client that names the mechanism directly. */
    static final String KERBEROS_OID = "1.2.840.113554.1.2.2";

    private final Map<String, Pending> pending = new ConcurrentHashMap<>();
    private final GSSManager manager = GSSManager.getInstance();
    private final Duration ttl;
    private final String servicePrincipal;
    private final int maxPending;
    private final AtomicLong lastEviction = new AtomicLong(System.nanoTime());

    private volatile GSSCredential credential;

    GssAcceptor(String servicePrincipal, Duration ttl, int maxPending) {
        this.servicePrincipal = servicePrincipal;
        this.ttl = ttl;
        this.maxPending = maxPending;
    }

    /**
     * One round of a handshake that is remembered between requests.
     *
     * @param key what identifies this handshake across requests
     */
    Round accept(String key, byte[] token) throws GSSException {
        evictStaleOccasionally();
        Pending handshake = pending.get(key);
        if (handshake == null) {
            if (pending.size() >= maxPending) {
                throw new GSSException(GSSException.UNAVAILABLE, 0,
                        "too many handshakes are already in flight on this server");
            }
            // Built outside computeIfAbsent: acquiring the acceptor credential does JAAS
            // and keytab work, which must not run while a map bin is locked.
            Pending created = new Pending(newContext());
            handshake = pending.putIfAbsent(key, created);
            if (handshake == null) {
                handshake = created;
            } else {
                dispose(created.context);
            }
        }

        handshake.lock.lock();
        try {
            if (handshake.disposed) {
                throw new GSSException(GSSException.CONTEXT_EXPIRED, 0, "this handshake was already given up on");
            }
            handshake.touch();
            GSSContext context = handshake.context;
            byte[] answer = context.acceptSecContext(token, 0, token.length);
            if (!context.isEstablished()) {
                return new Round(false, answer == null ? new byte[0] : answer, null);
            }
            String name = context.getSrcName().toString();
            pending.remove(key, handshake);
            handshake.disposed = true;
            dispose(context);
            return new Round(true, answer == null ? new byte[0] : answer, name);
        } finally {
            handshake.lock.unlock();
        }
    }

    /**
     * One round of a handshake that is not remembered.
     * <p>
     * For a channel with no key that identifies a caller. A mechanism that needs
     * more than one round cannot be served this way, which is the point: sharing a
     * context under a key that does not identify one caller is worse than refusing.
     */
    Round acceptOnce(byte[] token) throws GSSException {
        GSSContext context = newContext();
        try {
            byte[] answer = context.acceptSecContext(token, 0, token.length);
            if (!context.isEstablished()) {
                return new Round(false, answer == null ? new byte[0] : answer, null);
            }
            return new Round(true, answer == null ? new byte[0] : answer, context.getSrcName().toString());
        } finally {
            dispose(context);
        }
    }

    /** Drops a handshake that will not be continued. */
    void forget(String key) {
        Pending abandoned = pending.remove(key);
        if (abandoned != null) {
            abandoned.lock.lock();
            try {
                if (!abandoned.disposed) {
                    abandoned.disposed = true;
                    dispose(abandoned.context);
                }
            } finally {
                abandoned.lock.unlock();
            }
        }
    }

    void dispose() {
        for (String key : Map.copyOf(pending).keySet()) {
            forget(key);
        }
        GSSCredential held = credential;
        if (held != null) {
            credential = null;
            try {
                held.dispose();
            } catch (GSSException ignored) {
                // disposing a credential that is already gone is not a failure
            }
        }
    }

    /**
     * @throws GSSException rather than an unchecked exception, so that a JDK with
     *                      no keytab refuses the request instead of failing the
     *                      whole exchange with a server error
     */
    private GSSContext newContext() throws GSSException {
        return manager.createContext(acceptorCredential());
    }

    /**
     * The credential this server accepts with. Without a configured service
     * principal the default acceptor credential is used, which is what a JDK
     * configured through {@code java.security.auth.login.config} and a keytab
     * offers.
     * <p>
     * It is re-acquired once it has expired: the lifetime is the KDC's to decide,
     * not this server's to assume, and a keytab may be rotated under a running
     * process.
     */
    private GSSCredential acceptorCredential() throws GSSException {
        GSSCredential held = credential;
        if (held != null && isUsable(held)) {
            return held;
        }
        synchronized (this) {
            if (credential == null || !isUsable(credential)) {
                GSSName name = servicePrincipal == null || servicePrincipal.isBlank() ? null
                        : manager.createName(servicePrincipal, GSSName.NT_HOSTBASED_SERVICE);
                credential = manager.createCredential(name, GSSCredential.DEFAULT_LIFETIME,
                        new Oid[] { new Oid(SPNEGO_OID), new Oid(KERBEROS_OID) }, GSSCredential.ACCEPT_ONLY);
            }
            return credential;
        }
    }

    private static boolean isUsable(GSSCredential held) {
        try {
            return held.getRemainingLifetime() > 0;
        } catch (GSSException gone) {
            return false;
        }
    }

    /**
     * Acquires the credential now, so a misconfigured keytab fails the component.
     */
    void verifyCredential() throws GSSException {
        acceptorCredential();
    }

    /**
     * Sweeps at most twice per handshake lifetime rather than on every round - the
     * sweep is proportional to the number of handshakes in flight, and running it
     * per request turns a busy server quadratic.
     */
    private void evictStaleOccasionally() {
        long now = System.nanoTime();
        long previous = lastEviction.get();
        long interval = Math.max(ttl.toNanos() / 2, 1);
        if (now - previous < interval || !lastEviction.compareAndSet(previous, now)) {
            return;
        }
        Instant deadline = Instant.now().minus(ttl);
        for (Iterator<Map.Entry<String, Pending>> entries = pending.entrySet().iterator(); entries.hasNext();) {
            Map.Entry<String, Pending> entry = entries.next();
            if (entry.getValue().lastTouched.isBefore(deadline)) {
                forget(entry.getKey());
            }
        }
    }

    private static void dispose(GSSContext context) {
        try {
            context.dispose();
        } catch (GSSException ignored) {
            // a context that cannot be disposed is already unusable
        }
    }

    /** What one round produced. */
    record Round(boolean established, byte[] token, String name) {
    }

    private static final class Pending {

        private final GSSContext context;
        private final ReentrantLock lock = new ReentrantLock();
        private volatile Instant lastTouched = Instant.now();
        private volatile boolean disposed;

        private Pending(GSSContext context) {
            this.context = context;
        }

        /** Refreshed each round, so a slow handshake is not swept out mid-flight. */
        private void touch() {
            lastTouched = Instant.now();
        }
    }
}
