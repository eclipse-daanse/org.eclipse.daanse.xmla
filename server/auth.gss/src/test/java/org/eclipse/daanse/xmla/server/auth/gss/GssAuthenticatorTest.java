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

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.api.auth.InbandAuthenticator;
import org.eclipse.daanse.xmla.api.auth.XmlaAuthenticator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * What this mechanism decides before GSS-API is involved at all, and what it
 * does when GSS-API cannot help it.
 * <p>
 * A real handshake needs a KDC and a keytab, so the exchange itself is not
 * exercised here. What is exercised is everything around it - which is where
 * the defects were.
 */
class GssAuthenticatorTest {

    private GssAuthenticator authenticator;

    @BeforeEach
    void wire() {
        authenticator = new GssAuthenticator();
        authenticator.roles = (principal, claims, external) -> Set.of();
        // No keytab and a principal nothing can resolve: every attempt to acquire a
        // credential fails, which is the state a misconfigured deployment is in.
        authenticator.acceptor = new GssAcceptor("HTTP@nothing.invalid", Duration.ofSeconds(60), 1000);
    }

    private static XmlaRequest with(String authorization) {
        Map<String, List<String>> headers = authorization == null ? Map.of()
                : Map.of("Authorization", List.of(authorization));
        return new XmlaRequest(null, null, headers, null, "127.0.0.1");
    }

    @Test
    void aRequestWithoutTheSchemeIsNotThisMechanismsBusiness() {
        assertThat(authenticator.authenticate(with(null))).isInstanceOf(XmlaAuthenticator.Result.NotMine.class);
        assertThat(authenticator.authenticate(with("Basic abc"))).isInstanceOf(XmlaAuthenticator.Result.NotMine.class);
    }

    @Test
    void theBareSchemeIsAnsweredWithTheChallenge() {
        // Some clients send it to ask what the server supports; falling through to
        // anonymous left them nothing to go on.
        XmlaAuthenticator.Result result = authenticator.authenticate(with("Negotiate"));

        assertThat(result).isInstanceOf(XmlaAuthenticator.Result.Challenge.class);
        assertThat(((XmlaAuthenticator.Result.Challenge) result).headerValue()).isEqualTo("Negotiate");
    }

    @Test
    void aTokenThatIsNotBase64IsRefused() {
        assertThat(authenticator.authenticate(with("Negotiate ~~~not base64~~~")))
                .isInstanceOf(XmlaAuthenticator.Result.Refused.class);
    }

    @Test
    void withoutAUsableCredentialTheRequestIsRefusedRatherThanFailing() {
        // This used to leave an IllegalStateException that no handler caught, so a
        // deployment with no keytab answered every request with a server error.
        String token = Base64.getEncoder().encodeToString(new byte[] { 1, 2, 3, 4 });

        assertThat(authenticator.authenticate(with("Negotiate " + token)))
                .isInstanceOf(XmlaAuthenticator.Result.Refused.class);
    }

    @Test
    void theInBandHandshakeIsRefusedTheSameWay() {
        InbandAuthenticator.Result result = authenticator.authenticate(new byte[] { 1, 2, 3, 4 },
                XmlaRequest.anonymous().withSession("a-session"));

        assertThat(result).isInstanceOf(InbandAuthenticator.Result.Refused.class);
    }

    @Test
    void theSchemeIsMatchedWithoutRegardToCase() {
        assertThat(authenticator.authenticate(with("negotiate")))
                .isInstanceOf(XmlaAuthenticator.Result.Challenge.class);
    }

    @Test
    void theSchemeAndTheChallengeAreTheSameWord() {
        // They used to be three independent spellings of one protocol token.
        assertThat(authenticator.challenge()).isEqualTo(authenticator.scheme());
    }
}
