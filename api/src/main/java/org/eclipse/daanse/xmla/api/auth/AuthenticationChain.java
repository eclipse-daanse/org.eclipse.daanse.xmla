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
package org.eclipse.daanse.xmla.api.auth;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.daanse.xmla.api.XmlaRequest;

/**
 * The registered mechanisms in a defined order, and the one correct way to walk
 * them.
 * <p>
 * Declarative Services gives no ordering guarantee for a collection reference:
 * services arrive in whatever order they happen to bind, and that differs
 * between restarts. Since the walk stops at the first mechanism that claims a
 * request, an undefined order means an undefined answer to "who authenticated
 * this caller". This class binds mechanisms together with their service
 * properties and sorts them by {@code service.ranking} - see
 * {@link AuthRanking} - so the order is a decision rather than an accident.
 * <p>
 * A transport should not walk the mechanisms itself. Both shipped ones did, in
 * near-identical code, and the rules below are easy to get subtly wrong.
 */
public final class AuthenticationChain {

    private static final String RANKING = "service.ranking";
    private static final String SERVICE_ID = "service.id";

    private final List<Entry> entries = new CopyOnWriteArrayList<>();

    private record Entry(XmlaAuthenticator mechanism, int ranking, long serviceId) {
    }

    /**
     * @param properties the OSGi service properties the mechanism was registered
     *                   with
     */
    public void add(XmlaAuthenticator mechanism, Map<String, ?> properties) {
        entries.add(
                new Entry(mechanism, intOf(properties, RANKING, 0), longOf(properties, SERVICE_ID, Long.MAX_VALUE)));
        entries.sort(Comparator.comparingInt(Entry::ranking).reversed().thenComparingLong(Entry::serviceId));
    }

    public void remove(XmlaAuthenticator mechanism) {
        entries.removeIf(entry -> entry.mechanism() == mechanism);
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    /** The mechanisms in the order they are asked. */
    public List<XmlaAuthenticator> ranked() {
        return entries.stream().map(Entry::mechanism).toList();
    }

    /**
     * Every challenge worth offering a client that has not authenticated, in chain
     * order.
     * <p>
     * A mechanism that cannot be asked for - a trusted proxy header is presented by
     * the proxy or not at all - contributes nothing.
     */
    public List<String> challenges() {
        List<String> offered = new ArrayList<>();
        for (Entry entry : entries) {
            String challenge = entry.mechanism().challenge();
            if (challenge != null && !challenge.isBlank()) {
                offered.add(challenge);
            }
        }
        return List.copyOf(offered);
    }

    /**
     * Asks each mechanism in turn and reports what the transport should do.
     * <p>
     * A {@link XmlaAuthenticator.Result.Fallback} is held aside until every
     * mechanism has answered {@code NotMine}, which is what makes a stand-in
     * identity incapable of displacing a mechanism that would have authenticated
     * the caller for real.
     */
    public Outcome run(XmlaRequest request) {
        AuthenticatedIdentity fallback = null;
        for (Entry entry : entries) {
            XmlaAuthenticator.Result result = entry.mechanism().authenticate(request);
            if (result instanceof XmlaAuthenticator.Result.Authenticated authenticated) {
                XmlaRequest identified = request.withIdentity(authenticated.identity(), IdentitySource.MECHANISM);
                String answer = authenticated.responseHeaderValue();
                return new Outcome.Proceed(identified,
                        answer == null || answer.isBlank() ? List.of() : List.of(answer));
            }
            if (result instanceof XmlaAuthenticator.Result.Challenge challenge) {
                return new Outcome.Reject(List.of(challenge.headerValue()), "the handshake continues");
            }
            if (result instanceof XmlaAuthenticator.Result.Refused refused) {
                return new Outcome.Reject(challenges(), refused.reason());
            }
            if (result instanceof XmlaAuthenticator.Result.Fallback stand && fallback == null) {
                fallback = stand.identity();
            }
        }
        if (fallback != null) {
            return new Outcome.Proceed(request.withIdentity(fallback, IdentitySource.FALLBACK), List.of());
        }
        return new Outcome.Proceed(request, List.of());
    }

    /** What the transport does with the answer. */
    public sealed interface Outcome {

        /**
         * Serve the request as this caller.
         *
         * @param responseHeaders {@code WWW-Authenticate} values to write on the
         *                        successful response - what SPNEGO's mutual
         *                        authentication needs in order to complete
         */
        record Proceed(XmlaRequest request, List<String> responseHeaders) implements Outcome {
        }

        /** Answer 401 with these challenges rather than serving the request. */
        record Reject(List<String> challenges, String reason) implements Outcome {
        }
    }

    private static int intOf(Map<String, ?> properties, String name, int fallback) {
        Object value = properties == null ? null : properties.get(name);
        if (value instanceof Number number) {
            return number.intValue();
        }
        try {
            return value == null ? fallback : Integer.parseInt(value.toString().trim());
        } catch (NumberFormatException notANumber) {
            return fallback;
        }
    }

    private static long longOf(Map<String, ?> properties, String name, long fallback) {
        Object value = properties == null ? null : properties.get(name);
        if (value instanceof Number number) {
            return number.longValue();
        }
        try {
            return value == null ? fallback : Long.parseLong(value.toString().trim());
        } catch (NumberFormatException notANumber) {
            return fallback;
        }
    }
}
