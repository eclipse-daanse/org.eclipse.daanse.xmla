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
package org.eclipse.daanse.xmla.server.auth.store.internal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.lang.annotation.Annotation;

import org.eclipse.daanse.xmla.api.auth.Claims;
import org.junit.jupiter.api.Test;

/** What the store must and must not accept. */
class InternalUserStoreTest {

    private static final String ALICE = PasswordHash.encode("alicepw".toCharArray());

    private static InternalUserStore storeOf(String[] credentials, String[] roles) {
        InternalUserStore store = new InternalUserStore();
        store.activate(new InternalUserStore.Config() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return InternalUserStore.Config.class;
            }

            @Override
            public String[] credentials() {
                return credentials;
            }

            @Override
            public String[] roles() {
                return roles;
            }
        });
        return store;
    }

    @Test
    void theRightPasswordIsAcceptedAndCarriesTheRoles() {
        InternalUserStore store = storeOf(new String[] { "alice=" + ALICE }, new String[] { "alice=Admin, Analyst" });

        var identity = store.verify("alice", "alicepw".toCharArray());

        assertThat(identity).isPresent();
        assertThat(identity.get().name()).isEqualTo("alice");
        assertThat(identity.get().roles()).containsExactlyInAnyOrder("Admin", "Analyst");
    }

    @Test
    void theWrongPasswordIsNot() {
        InternalUserStore store = storeOf(new String[] { "alice=" + ALICE }, new String[0]);
        assertThat(store.verify("alice", "wrong".toCharArray())).isEmpty();
    }

    @Test
    void anUnknownUserIsNot() {
        InternalUserStore store = storeOf(new String[] { "alice=" + ALICE }, new String[0]);
        assertThat(store.verify("mallory", "alicepw".toCharArray())).isEmpty();
    }

    @Test
    void anEmptyPasswordIsNot() {
        InternalUserStore store = storeOf(new String[] { "alice=" + ALICE }, new String[0]);
        assertThat(store.verify("alice", new char[0])).isEmpty();
    }

    @Test
    void aPlainPasswordInTheConfigurationStopsTheComponentComingUp() {
        // Otherwise a deployment could put the password itself there, and the mistake
        // would surface at the first login as an indistinguishable "wrong password".
        assertThatThrownBy(() -> storeOf(new String[] { "alice=alicepw" }, new String[0]))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void aUserConfiguredTwiceStopsTheComponentComingUp() {
        // Last one wins is a silent way to lose an account.
        assertThatThrownBy(() -> storeOf(new String[] { "alice=" + ALICE, "alice=" + ALICE }, new String[0]))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void aUserWhoseNameIsOnlyWhitespaceIsRefused() {
        assertThatThrownBy(() -> storeOf(new String[] { " =" + ALICE }, new String[0]))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void anUnknownUserCostsWhatAKnownOneCosts() {
        // The short-circuit this replaces let anyone with a stopwatch read off which
        // accounts exist.
        InternalUserStore store = storeOf(new String[] { "alice=" + ALICE }, new String[0]);

        long known = timeOf(() -> store.verify("alice", "wrong".toCharArray()));
        long unknown = timeOf(() -> store.verify("mallory", "wrong".toCharArray()));

        assertThat(unknown).isGreaterThan(known / 4);
    }

    private static long timeOf(Runnable attempt) {
        long started = System.nanoTime();
        attempt.run();
        return System.nanoTime() - started;
    }

    @Test
    void anEntryNamingNoUserIsIgnored() {
        InternalUserStore store = storeOf(new String[] { "nonsense", "alice=" + ALICE }, new String[0]);
        assertThat(store.verify("alice", "alicepw".toCharArray())).isPresent();
    }

    @Test
    void rolesAreAlsoAnsweredForACallerAnotherMechanismAuthenticated() {
        InternalUserStore store = storeOf(new String[0], new String[] { "alice=Admin" });

        assertThat(store.rolesOf(() -> "alice", Claims.none())).containsExactly("Admin");
        assertThat(store.rolesOf(() -> "bob", Claims.none())).isEmpty();
    }

    @Test
    void everyHashIsSaltedSoTwoUsersWithOnePasswordDifferOnDisk() {
        assertThat(PasswordHash.encode("same".toCharArray())).isNotEqualTo(PasswordHash.encode("same".toCharArray()));
    }

    @Test
    void anUnreadableHashDeniesRatherThanGrants() {
        assertThat(PasswordHash.matches("x".toCharArray(), "pbkdf2:sha256:notanumber:a:b")).isFalse();
        assertThat(PasswordHash.matches("x".toCharArray(), "")).isFalse();
        assertThat(PasswordHash.matches("x".toCharArray(), null)).isFalse();
    }
}
