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
package org.eclipse.daanse.xmla.server.auth.dummy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.lang.annotation.Annotation;

import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.api.auth.XmlaAuthenticator;
import org.junit.jupiter.api.Test;

/**
 * The two things that keep a mechanism which verifies nothing from taking over
 * an endpoint.
 */
class FixedIdentityAuthenticatorTest {

    private static FixedIdentityAuthenticator authenticator(boolean acknowledged, String... roles) {
        FixedIdentityAuthenticator authenticator = new FixedIdentityAuthenticator();
        authenticator.activate(new FixedIdentityAuthenticator.Config() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return FixedIdentityAuthenticator.Config.class;
            }

            @Override
            public boolean acknowledgeUnauthenticated() {
                return acknowledged;
            }

            @Override
            public String userName() {
                return "daanse";
            }

            @Override
            public String[] roles() {
                return roles;
            }
        });
        return authenticator;
    }

    @Test
    void itDoesNotComeUpUntilSomebodySaysTheEndpointIsUnprotected() {
        // Installing the bundle used to be enough to name every caller.
        assertThatThrownBy(() -> authenticator(false)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void itAnswersAsAStandInAndNotAsAnAuthentication() {
        // Which is what stops it displacing a real mechanism, displacing a session
        // identity, and satisfying a rule that demands a login.
        XmlaAuthenticator.Result result = authenticator(true, "Admin").authenticate(XmlaRequest.anonymous());

        assertThat(result).isInstanceOf(XmlaAuthenticator.Result.Fallback.class);
        assertThat(((XmlaAuthenticator.Result.Fallback) result).identity().name()).isEqualTo("daanse");
    }

    @Test
    void itOffersNoChallengeBecauseThereIsNothingToAskFor() {
        assertThat(authenticator(true).challenge()).isEmpty();
    }

    @Test
    void aBlankOrMissingRoleInTheConfigurationIsSkipped() {
        XmlaAuthenticator.Result result = authenticator(true, "Admin", "  ", null, "Analyst")
                .authenticate(XmlaRequest.anonymous());

        assertThat(((XmlaAuthenticator.Result.Fallback) result).identity().roles()).containsExactlyInAnyOrder("Admin",
                "Analyst");
    }
}
