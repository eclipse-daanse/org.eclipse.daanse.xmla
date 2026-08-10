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
package org.eclipse.daanse.xmla.server.auth.header;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.api.auth.AuthClaims;
import org.eclipse.daanse.xmla.api.auth.XmlaAuthenticator;
import org.junit.jupiter.api.Test;

/**
 * What proves that a forwarded identity came from the front, and what becomes
 * of it once it is believed.
 * <p>
 * The two proofs exist for different deployments: the peer address where this
 * server accepts the front's connection itself, the shared secret where a
 * servlet container has already rewritten the peer to the original client and
 * the address can never match.
 */
class TrustedHeaderAuthenticatorTest {

    private final List<Collection<String>> handedIn = new ArrayList<>();

    /** Mutable so each test states only what it cares about. */
    private static final class Given {

        private String[] upstreams = new String[0];
        private String secretHeader = "";
        private String secret = "";
        private String[] claimHeaders = new String[0];

        private Given trusting(String... addresses) {
            upstreams = addresses;
            return this;
        }

        private Given withSecret(String header, String value) {
            secretHeader = header;
            secret = value;
            return this;
        }

        private Given carrying(String... headers) {
            claimHeaders = headers;
            return this;
        }
    }

    private TrustedHeaderAuthenticator authenticator(Given given) {
        TrustedHeaderAuthenticator authenticator = new TrustedHeaderAuthenticator();
        authenticator.roles = (principal, claims, external) -> {
            handedIn.add(external);
            return Set.copyOf(external);
        };
        authenticator.activate(new TrustedHeaderAuthenticator.Config() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return TrustedHeaderAuthenticator.Config.class;
            }

            @Override
            public String userHeader() {
                return "Remote-User";
            }

            @Override
            public String groupsHeader() {
                return "Remote-Groups";
            }

            @Override
            public String[] claimHeaders() {
                return given.claimHeaders;
            }

            @Override
            public String[] trustedUpstreams() {
                return given.upstreams;
            }

            @Override
            public String sharedSecretHeader() {
                return given.secretHeader;
            }

            @Override
            public String sharedSecret() {
                return given.secret;
            }
        });
        return authenticator;
    }

    /** A request carrying whatever the test names, as {@code Header, value} pairs. */
    private static XmlaRequest from(String peer, String... headers) {
        Map<String, List<String>> named = new LinkedHashMap<>();
        for (int index = 0; index + 1 < headers.length; index += 2) {
            if (headers[index + 1] != null) {
                named.put(headers[index], List.of(headers[index + 1]));
            }
        }
        return new XmlaRequest(null, null, named, null, peer);
    }

    // --- what has to be configured at all ---

    @Test
    void withNoProofAtAllItDoesNotComeUp() {
        // It would believe anybody who sends a header, which is the failure this
        // mechanism exists to prevent.
        assertThatThrownBy(() -> authenticator(new Given())).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void halfASecretIsRefused() {
        assertThatThrownBy(() -> authenticator(new Given().withSecret("X-Auth", "")))
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> authenticator(new Given().withSecret("", "s3cret")))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void anUpstreamThatCannotBeResolvedStopsTheComponentComingUp() {
        // Better than quietly trusting nobody because of a typo.
        assertThatThrownBy(() -> authenticator(new Given().trusting("no-such-host.invalid")))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> authenticator(new Given().trusting("10.0.0.0/99")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void aClaimHeaderThatCannotBeReadStopsTheComponentComingUp() {
        assertThatThrownBy(() -> authenticator(new Given().trusting("10.0.0.5").carrying("no-separator")))
                .isInstanceOf(IllegalStateException.class);
    }

    // --- the peer address as proof ---

    @Test
    void aHeaderFromATrustedFrontIsBelieved() {
        XmlaAuthenticator.Result result = authenticator(new Given().trusting("10.0.0.5"))
                .authenticate(from("10.0.0.5", "Remote-User", "alice", "Remote-Groups", "bi-admin, bi-eu"));

        assertThat(result).isInstanceOf(XmlaAuthenticator.Result.Authenticated.class);
        assertThat(((XmlaAuthenticator.Result.Authenticated) result).identity().name()).isEqualTo("alice");
    }

    @Test
    void theSameHeaderFromAnybodyElseIsNot() {
        assertThat(authenticator(new Given().trusting("10.0.0.5"))
                .authenticate(from("10.0.0.6", "Remote-User", "alice")))
                        .isInstanceOf(XmlaAuthenticator.Result.NotMine.class);
    }

    @Test
    void aFrontReportedInAnotherAddressFormStillMatches() {
        // A container reports loopback as 0:0:0:0:0:0:0:1 while an operator writes ::1.
        assertThat(authenticator(new Given().trusting("::1"))
                .authenticate(from("0:0:0:0:0:0:0:1", "Remote-User", "alice")))
                        .isInstanceOf(XmlaAuthenticator.Result.Authenticated.class);
        assertThat(authenticator(new Given().trusting("10.0.0.5"))
                .authenticate(from("::ffff:10.0.0.5", "Remote-User", "alice")))
                        .isInstanceOf(XmlaAuthenticator.Result.Authenticated.class);
    }

    @Test
    void aRangeMayBeNamedInsteadOfEveryAddress() {
        assertThat(authenticator(new Given().trusting("10.0.0.0/8"))
                .authenticate(from("10.11.12.13", "Remote-User", "alice")))
                        .isInstanceOf(XmlaAuthenticator.Result.Authenticated.class);
        assertThat(authenticator(new Given().trusting("10.0.0.0/8"))
                .authenticate(from("11.0.0.1", "Remote-User", "alice")))
                        .isInstanceOf(XmlaAuthenticator.Result.NotMine.class);
    }

    // --- the shared secret as proof ---

    @Test
    void behindARewritingContainerTheSecretIsWhatWorks() {
        // The reported peer is the original client and no configured address could
        // ever match it; the secret is unaffected by that.
        TrustedHeaderAuthenticator authenticator = authenticator(new Given().withSecret("X-Forwarded-Auth", "s3cret"));

        XmlaAuthenticator.Result result = authenticator
                .authenticate(from("203.0.113.9", "Remote-User", "alice", "X-Forwarded-Auth", "s3cret"));

        assertThat(result).isInstanceOf(XmlaAuthenticator.Result.Authenticated.class);
    }

    @Test
    void withoutTheSecretTheIdentityIsIgnored() {
        TrustedHeaderAuthenticator authenticator = authenticator(new Given().withSecret("X-Forwarded-Auth", "s3cret"));

        assertThat(authenticator.authenticate(from("203.0.113.9", "Remote-User", "alice")))
                .isInstanceOf(XmlaAuthenticator.Result.NotMine.class);
        assertThat(authenticator.authenticate(from("203.0.113.9", "Remote-User", "alice", "X-Forwarded-Auth", "wrong")))
                .isInstanceOf(XmlaAuthenticator.Result.NotMine.class);
    }

    @Test
    void everyConfiguredProofHasToHold() {
        // Configuring both is defence in depth, not a choice between them: the address
        // covers replay, the secret covers a front that fails to strip the headers.
        TrustedHeaderAuthenticator authenticator = authenticator(
                new Given().trusting("10.0.0.5").withSecret("X-Forwarded-Auth", "s3cret"));

        assertThat(authenticator
                .authenticate(from("10.0.0.5", "Remote-User", "alice", "X-Forwarded-Auth", "s3cret")))
                        .isInstanceOf(XmlaAuthenticator.Result.Authenticated.class);
        assertThat(authenticator.authenticate(from("10.0.0.5", "Remote-User", "alice")))
                .as("right address, no secret").isInstanceOf(XmlaAuthenticator.Result.NotMine.class);
        assertThat(authenticator
                .authenticate(from("10.0.0.6", "Remote-User", "alice", "X-Forwarded-Auth", "s3cret")))
                        .as("right secret, wrong address").isInstanceOf(XmlaAuthenticator.Result.NotMine.class);
    }

    @Test
    void aSecretOfTheWrongLengthIsRejectedLikeAnyOther() {
        TrustedHeaderAuthenticator authenticator = authenticator(new Given().withSecret("X-Forwarded-Auth", "s3cret"));

        assertThat(authenticator.authenticate(from("10.0.0.5", "Remote-User", "alice", "X-Forwarded-Auth", "s")))
                .isInstanceOf(XmlaAuthenticator.Result.NotMine.class);
        assertThat(authenticator
                .authenticate(from("10.0.0.5", "Remote-User", "alice", "X-Forwarded-Auth", "s3cretlonger")))
                        .isInstanceOf(XmlaAuthenticator.Result.NotMine.class);
    }

    // --- what the believed identity carries ---

    @Test
    void theGroupsAreHandedInOnceAndAsClaimsOfThisMechanism() {
        XmlaAuthenticator.Result result = authenticator(new Given().trusting("10.0.0.5"))
                .authenticate(from("10.0.0.5", "Remote-User", "alice", "Remote-Groups", "bi-admin, bi-eu"));

        assertThat(handedIn).hasSize(1);
        assertThat(handedIn.get(0)).containsExactly("bi-admin", "bi-eu");
        assertThat(((XmlaAuthenticator.Result.Authenticated) result).identity().claims()
                .all(AuthClaims.NS_HEADER, AuthClaims.GROUPS)).containsExactly("bi-admin", "bi-eu");
    }

    @Test
    void whateverElseTheFrontSendsCanBecomeAClaim() {
        XmlaAuthenticator.Result result = authenticator(
                new Given().trusting("10.0.0.5").carrying("Remote-Email=email", "Remote-Name=name"))
                        .authenticate(from("10.0.0.5", "Remote-User", "alice", "Remote-Email", "alice@example.org",
                                "Remote-Name", "Alice Example"));

        var claims = ((XmlaAuthenticator.Result.Authenticated) result).identity().claims();
        assertThat(claims.first(AuthClaims.NS_HEADER, "email")).isEqualTo("alice@example.org");
        assertThat(claims.first(AuthClaims.NS_HEADER, "name")).isEqualTo("Alice Example");
    }

    @Test
    void aConfiguredClaimHeaderTheFrontDidNotSendIsSimplyAbsent() {
        XmlaAuthenticator.Result result = authenticator(
                new Given().trusting("10.0.0.5").carrying("Remote-Email=email"))
                        .authenticate(from("10.0.0.5", "Remote-User", "alice"));

        assertThat(((XmlaAuthenticator.Result.Authenticated) result).identity().claims()
                .first(AuthClaims.NS_HEADER, "email")).isNull();
    }

    @Test
    void aNameCarryingALineBreakIsRefused() {
        assertThat(authenticator(new Given().trusting("10.0.0.5"))
                .authenticate(from("10.0.0.5", "Remote-User", "alice\r\nX-Admin: true")))
                        .isInstanceOf(XmlaAuthenticator.Result.Refused.class);
    }

    @Test
    void aRequestWithoutTheHeaderIsNotThisMechanismsBusiness() {
        assertThat(authenticator(new Given().trusting("10.0.0.5")).authenticate(from("10.0.0.5")))
                .isInstanceOf(XmlaAuthenticator.Result.NotMine.class);
    }
}
