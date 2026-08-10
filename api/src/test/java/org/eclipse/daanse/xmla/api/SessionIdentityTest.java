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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;
import org.eclipse.daanse.xmla.api.auth.Claims;
import org.junit.jupiter.api.Test;

/**
 * What the in-band handshake needs from a session: that it remembers who was
 * authenticated, hands that back, and does not hand it to somebody else.
 */
class SessionIdentityTest {

    private final XmlaSessionHandler sessions = new SimpleSessionHandler() {
    };

    private static AuthenticatedIdentity identity(String name, String... roles) {
        Principal principal = () -> name;
        return new AuthenticatedIdentity(principal, Set.of(roles), Claims.none());
    }

    @Test
    void aSessionCarriesTheIdentityBoundToIt() {
        String session = sessions.beginSession(XmlaRequest.anonymous()).orElseThrow();
        sessions.bindIdentity(session, identity("alice", "Admin"));

        Optional<AuthenticatedIdentity> restored = sessions.identityOf(session);
        assertThat(restored).isPresent();
        assertThat(restored.get().name()).isEqualTo("alice");
        assertThat(restored.get().roles()).containsExactly("Admin");
    }

    @Test
    void aSessionWithoutAHandshakeCarriesNothing() {
        String session = sessions.beginSession(XmlaRequest.anonymous()).orElseThrow();
        assertThat(sessions.identityOf(session)).isEmpty();
    }

    @Test
    void anAnonymousCallerMayUseTheSessionTheHandshakeAuthenticated() {
        String session = sessions.beginSession(XmlaRequest.anonymous()).orElseThrow();
        sessions.bindIdentity(session, identity("alice"));

        // The ordinary case after the handshake: the request carries the id and no
        // credentials, and the session is what says who is calling.
        assertThat(sessions.checkSession(session, XmlaRequest.anonymous())).isTrue();
    }

    @Test
    void anotherAuthenticatedCallerDoesNotInheritTheSession() {
        String session = sessions.beginSession(XmlaRequest.anonymous()).orElseThrow();
        sessions.bindIdentity(session, identity("alice"));

        XmlaRequest bob = XmlaRequest.anonymous().withIdentity(identity("bob"));
        assertThat(sessions.checkSession(session, bob)).isFalse();

        XmlaRequest alice = XmlaRequest.anonymous().withIdentity(identity("alice"));
        assertThat(sessions.checkSession(session, alice)).isTrue();
    }

    @Test
    void anEndedSessionForgetsWhoItWas() {
        String session = sessions.beginSession(XmlaRequest.anonymous()).orElseThrow();
        sessions.bindIdentity(session, identity("alice"));

        sessions.endSession(session, XmlaRequest.anonymous());

        assertThat(sessions.identityOf(session)).isEmpty();
        assertThat(sessions.checkSession(session, XmlaRequest.anonymous())).isFalse();
    }

    @Test
    void anIdentityIsNotBoundToASessionThatWasNeverOpened() {
        assertThatThrownBy(() -> sessions.bindIdentity("never-opened", identity("alice")))
                .isInstanceOf(XmlaRefusedException.class);
        assertThat(sessions.identityOf("never-opened")).isEmpty();
    }

    @Test
    void aSecondHandshakeDoesNotTakeTheSessionOver() {
        String session = sessions.beginSession(XmlaRequest.anonymous()).orElseThrow();
        sessions.bindIdentity(session, identity("alice"));

        assertThatThrownBy(() -> sessions.bindIdentity(session, identity("mallory")))
                .isInstanceOf(XmlaRefusedException.class);
        assertThat(sessions.identityOf(session).orElseThrow().name()).isEqualTo("alice");

        // Re-running the handshake as the same caller is not a takeover.
        sessions.bindIdentity(session, identity("alice"));
    }

    @Test
    void theHolderMayStopHonouringASession() {
        List<String> released = new ArrayList<>();
        Set<String> held = new HashSet<>();
        XmlaSessionHandler expiring = new SimpleSessionHandler() {

            @Override
            protected void onBeginSession(String sessionId, XmlaRequest request) {
                held.add(sessionId);
            }

            @Override
            protected boolean stillHeld(XmlaSession session) {
                return held.contains(session.id());
            }

            @Override
            protected void onEndSession(String sessionId) {
                released.add(sessionId);
            }
        };

        String session = expiring.beginSession(XmlaRequest.anonymous()).orElseThrow();
        assertThat(expiring.checkSession(session, XmlaRequest.anonymous())).isTrue();

        held.remove(session);

        assertThat(expiring.checkSession(session, XmlaRequest.anonymous())).isFalse();
        assertThat(released).containsExactly(session);

        // Refusing it already ended it, so a later EndSession releases nothing twice.
        expiring.endSession(session, XmlaRequest.anonymous());
        assertThat(released).containsExactly(session);
    }

    @Test
    void aHolderMayDeclineToOpenOneAtAll() {
        XmlaSessionHandler stateless = new SimpleSessionHandler() {

            @Override
            protected boolean mayOpenSession(XmlaRequest request) {
                return false;
            }
        };

        assertThat(stateless.beginSession(XmlaRequest.anonymous())).isEmpty();
        assertThat(stateless.sessions()).isEmpty();
    }

    @Test
    void aSessionReportsWhatDiscoverSessionsAsksFor() {
        String id = sessions.beginSession(XmlaRequest.anonymous()).orElseThrow();
        XmlaSession session = sessions.session(id).orElseThrow();

        assertThat(session.id()).isEqualTo(id);
        assertThat(session.commandCount()).isZero();
        assertThat(session.busy()).isFalse();
        assertThat(session.idle(session.startedAt())).isZero();

        session.commandStarted();
        assertThat(session.busy()).isTrue();
        assertThat(session.commandCount()).isEqualTo(1);

        session.commandEnded();
        assertThat(session.busy()).isFalse();
        assertThat(sessions.sessions()).extracting(XmlaSession::id).containsExactly(id);
    }

    @Test
    void aRestoredIdentityAnswersTheRoleQuestion() {
        XmlaRequest request = XmlaRequest.anonymous().withIdentity(identity("alice", "Admin", "Analyst"));

        assertThat(request.isAnonymous()).isFalse();
        assertThat(request.userName()).isEqualTo("alice");
        assertThat(request.hasRole("Admin")).isTrue();
        // Exact, because the catalog that finally reads the name is exact too.
        assertThat(request.hasRole("admin")).isFalse();
        assertThat(request.hasRole("Nothing")).isFalse();
    }
}
