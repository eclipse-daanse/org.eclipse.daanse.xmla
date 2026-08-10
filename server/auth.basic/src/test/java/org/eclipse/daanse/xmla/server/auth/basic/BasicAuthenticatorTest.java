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
package org.eclipse.daanse.xmla.server.auth.basic;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.Annotation;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;
import org.eclipse.daanse.xmla.api.auth.Claims;
import org.eclipse.daanse.xmla.api.auth.NamedPrincipal;
import org.eclipse.daanse.xmla.api.auth.XmlaAuthenticator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * What arrives in an {@code Authorization} header and what becomes of it.
 * <p>
 * The encoding cases are the ones that mattered in practice: a password with a
 * character outside ASCII used to fail permanently and silently against Windows
 * clients, which send their ANSI code page rather than UTF-8.
 */
class BasicAuthenticatorTest {

    private static final String PASSWORD = "gehe1mß";

    private BasicAuthenticator authenticator;
    private int resolutions;

    @BeforeEach
    void wire() {
        authenticator = new BasicAuthenticator();
        authenticator.credentials = (user, password) -> "alice".equals(user) && PASSWORD.equals(new String(password))
                ? Optional.of(AuthenticatedIdentity.of(new NamedPrincipal(user), Claims.none()))
                : Optional.empty();
        authenticator.roles = (principal, claims, external) -> {
            resolutions++;
            return Set.of("Admin");
        };
        authenticator.activate(config(4096));
    }

    private static BasicAuthenticator.Config config(int maxLength) {
        return new BasicAuthenticator.Config() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return BasicAuthenticator.Config.class;
            }

            @Override
            public String realm() {
                return "Daanse XMLA";
            }

            @Override
            public int maxCredentialsLength() {
                return maxLength;
            }
        };
    }

    private static XmlaRequest with(String authorization) {
        Map<String, List<String>> headers = authorization == null ? Map.of()
                : Map.of("Authorization", List.of(authorization));
        return new XmlaRequest(null, null, headers, null, null);
    }

    private static String credentials(String user, String password, Charset charset) {
        return "Basic " + Base64.getEncoder().encodeToString((user + ":" + password).getBytes(charset));
    }

    @Test
    void aRequestWithNoCredentialsIsNotThisMechanismsBusiness() {
        assertThat(authenticator.authenticate(with(null))).isInstanceOf(XmlaAuthenticator.Result.NotMine.class);
        assertThat(authenticator.authenticate(with("Bearer abc"))).isInstanceOf(XmlaAuthenticator.Result.NotMine.class);
    }

    @Test
    void theBareSchemeIsAnswerdWithTheChallenge() {
        // Some clients probe with it; falling through to anonymous tells them nothing.
        assertThat(authenticator.authenticate(with("Basic"))).isInstanceOf(XmlaAuthenticator.Result.Challenge.class);
    }

    @Test
    void theChallengeNamesTheEncoding() {
        // RFC 7617's only way to say which encoding the password is in.
        assertThat(authenticator.challenge()).contains("charset=\"UTF-8\"");
    }

    @Test
    void theRightPasswordIsAcceptedAndGetsItsRoles() {
        XmlaAuthenticator.Result result = authenticator
                .authenticate(with(credentials("alice", PASSWORD, StandardCharsets.UTF_8)));

        assertThat(result).isInstanceOf(XmlaAuthenticator.Result.Authenticated.class);
        AuthenticatedIdentity identity = ((XmlaAuthenticator.Result.Authenticated) result).identity();
        assertThat(identity.name()).isEqualTo("alice");
        assertThat(identity.roles()).containsExactly("Admin");
        assertThat(resolutions).as("the roles are resolved once").isEqualTo(1);
    }

    @Test
    void aWindowsClientsCodePageIsAcceptedToo() {
        // The bytes are not valid UTF-8, and before the fallback this was an
        // indistinguishable "wrong password" forever.
        XmlaAuthenticator.Result result = authenticator
                .authenticate(with(credentials("alice", PASSWORD, StandardCharsets.ISO_8859_1)));

        assertThat(result).isInstanceOf(XmlaAuthenticator.Result.Authenticated.class);
    }

    @Test
    void theWrongPasswordIsRefused() {
        assertThat(authenticator.authenticate(with(credentials("alice", "wrong", StandardCharsets.UTF_8))))
                .isInstanceOf(XmlaAuthenticator.Result.Refused.class);
    }

    @Test
    void credentialsThatAreNotBase64AreRefused() {
        assertThat(authenticator.authenticate(with("Basic ~~~not base64~~~")))
                .isInstanceOf(XmlaAuthenticator.Result.Refused.class);
    }

    @Test
    void credentialsWithNoSeparatorAreRefused() {
        String encoded = Base64.getEncoder().encodeToString("nocolonhere".getBytes(StandardCharsets.UTF_8));

        assertThat(authenticator.authenticate(with("Basic " + encoded)))
                .isInstanceOf(XmlaAuthenticator.Result.Refused.class);
    }

    @Test
    void anOversizedHeaderIsRefusedBeforeItIsDecoded() {
        authenticator.activate(config(16));

        assertThat(authenticator.authenticate(with(credentials("alice", PASSWORD, StandardCharsets.UTF_8))))
                .isInstanceOf(XmlaAuthenticator.Result.Refused.class);
    }

    @Test
    void theSchemeIsMatchedWithoutRegardToCase() {
        assertThat(authenticator
                .authenticate(with(credentials("alice", PASSWORD, StandardCharsets.UTF_8).replace("Basic", "basic"))))
                .isInstanceOf(XmlaAuthenticator.Result.Authenticated.class);
    }
}
