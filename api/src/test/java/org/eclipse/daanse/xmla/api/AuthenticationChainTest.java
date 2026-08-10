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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.daanse.xmla.api.auth.AuthRanking;
import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;
import org.eclipse.daanse.xmla.api.auth.AuthenticationChain;
import org.eclipse.daanse.xmla.api.auth.Claims;
import org.eclipse.daanse.xmla.api.auth.IdentitySource;
import org.eclipse.daanse.xmla.api.auth.NamedPrincipal;
import org.eclipse.daanse.xmla.api.auth.XmlaAuthenticator;
import org.junit.jupiter.api.Test;

/**
 * The order mechanisms are asked in, and the rule that a stand-in identity
 * cannot displace one.
 * <p>
 * Both were undefined before: Declarative Services binds a collection reference
 * in whatever order services happen to arrive, so which mechanism authenticated
 * a caller could differ between restarts.
 */
class AuthenticationChainTest {

    private final List<String> asked = new ArrayList<>();

    private static AuthenticatedIdentity identity(String name) {
        return new AuthenticatedIdentity(new NamedPrincipal(name), Set.of(), Claims.none());
    }

    /** A mechanism that records that it was asked and then answers as told. */
    private XmlaAuthenticator mechanism(String name, Function<XmlaRequest, XmlaAuthenticator.Result> answer) {
        return new XmlaAuthenticator() {

            @Override
            public String scheme() {
                return name;
            }

            @Override
            public String challenge() {
                return name.equals("Header") ? "" : name;
            }

            @Override
            public Result authenticate(XmlaRequest request) {
                asked.add(name);
                return answer.apply(request);
            }
        };
    }

    private XmlaAuthenticator passes(String name) {
        return mechanism(name, request -> new XmlaAuthenticator.Result.NotMine());
    }

    private static Map<String, Object> ranked(String ranking, long serviceId) {
        return Map.of("service.ranking", Integer.valueOf(ranking), "service.id", serviceId);
    }

    /**
     * Every shipped mechanism, registered in the reverse of the order it must run
     * in.
     */
    private AuthenticationChain everyMechanismRegisteredBackwards() {
        AuthenticationChain chain = new AuthenticationChain();
        chain.add(passes("Fixed"), ranked(AuthRanking.FIXED, 1));
        chain.add(passes("Header"), ranked(AuthRanking.TRUSTED_HEADER, 2));
        chain.add(passes("Basic"), ranked(AuthRanking.BASIC, 3));
        chain.add(passes("Bearer"), ranked(AuthRanking.BEARER, 4));
        chain.add(passes("Negotiate"), ranked(AuthRanking.NEGOTIATE, 5));
        return chain;
    }

    @Test
    void theOrderIsTheRankingAndNotTheRegistrationOrder() {
        everyMechanismRegisteredBackwards().run(XmlaRequest.anonymous());

        assertThat(asked).containsExactly("Negotiate", "Bearer", "Basic", "Header", "Fixed");
    }

    @Test
    void anEqualRankingIsBrokenByRegistrationAge() {
        AuthenticationChain chain = new AuthenticationChain();
        chain.add(passes("second"), ranked("100", 20));
        chain.add(passes("first"), ranked("100", 10));

        chain.run(XmlaRequest.anonymous());

        assertThat(asked).containsExactly("first", "second");
    }

    @Test
    void theFirstMechanismToClaimTheRequestEndsTheWalk() {
        AuthenticationChain chain = new AuthenticationChain();
        chain.add(mechanism("Basic", request -> XmlaAuthenticator.Result.Authenticated.of(identity("alice"))),
                ranked(AuthRanking.BASIC, 1));
        chain.add(passes("Header"), ranked(AuthRanking.TRUSTED_HEADER, 2));

        AuthenticationChain.Outcome outcome = chain.run(XmlaRequest.anonymous());

        assertThat(asked).containsExactly("Basic");
        assertThat(outcome).isInstanceOf(AuthenticationChain.Outcome.Proceed.class);
        XmlaRequest served = ((AuthenticationChain.Outcome.Proceed) outcome).request();
        assertThat(served.userName()).isEqualTo("alice");
        assertThat(served.source()).isEqualTo(IdentitySource.MECHANISM);
        assertThat(served.isAuthenticated()).isTrue();
    }

    @Test
    void aFinalTokenTravelsBackOnTheSuccessfulResponse() {
        AuthenticationChain chain = new AuthenticationChain();
        chain.add(
                mechanism("Negotiate",
                        request -> new XmlaAuthenticator.Result.Authenticated(identity("alice"), "Negotiate deadbeef")),
                ranked(AuthRanking.NEGOTIATE, 1));

        AuthenticationChain.Outcome outcome = chain.run(XmlaRequest.anonymous());

        // Without this a client that asked for mutual authentication never completes.
        assertThat(((AuthenticationChain.Outcome.Proceed) outcome).responseHeaders())
                .containsExactly("Negotiate deadbeef");
    }

    @Test
    void wrongCredentialsStopTheWalkAndOfferEveryChallenge() {
        AuthenticationChain chain = new AuthenticationChain();
        chain.add(mechanism("Basic", request -> new XmlaAuthenticator.Result.Refused("wrong password")),
                ranked(AuthRanking.BASIC, 1));
        chain.add(passes("Header"), ranked(AuthRanking.TRUSTED_HEADER, 2));

        AuthenticationChain.Outcome outcome = chain.run(XmlaRequest.anonymous());

        assertThat(asked).containsExactly("Basic");
        assertThat(outcome).isInstanceOf(AuthenticationChain.Outcome.Reject.class);
        assertThat(((AuthenticationChain.Outcome.Reject) outcome).challenges()).containsExactly("Basic");
    }

    @Test
    void aMechanismThatCannotBeAskedForOffersNoChallenge() {
        AuthenticationChain chain = new AuthenticationChain();
        chain.add(passes("Header"), ranked(AuthRanking.TRUSTED_HEADER, 1));
        chain.add(passes("Basic"), ranked(AuthRanking.BASIC, 2));

        // A trusted proxy header is presented by the proxy or not at all.
        assertThat(chain.challenges()).containsExactly("Basic");
    }

    @Test
    void aStandInNeverDisplacesAMechanismThatWouldHaveAnswered() {
        AuthenticationChain chain = new AuthenticationChain();
        chain.add(mechanism("Fixed", request -> new XmlaAuthenticator.Result.Fallback(identity("nobody"))),
                ranked(AuthRanking.FIXED, 1));
        chain.add(mechanism("Basic", request -> XmlaAuthenticator.Result.Authenticated.of(identity("alice"))),
                ranked(AuthRanking.BASIC, 2));

        AuthenticationChain.Outcome outcome = chain.run(XmlaRequest.anonymous());

        assertThat(asked).containsExactly("Basic");
        assertThat(((AuthenticationChain.Outcome.Proceed) outcome).request().userName()).isEqualTo("alice");
    }

    @Test
    void aStandInAppliesOnlyWhenNobodyClaimedTheRequest() {
        AuthenticationChain chain = new AuthenticationChain();
        chain.add(mechanism("Fixed", request -> new XmlaAuthenticator.Result.Fallback(identity("nobody"))),
                ranked(AuthRanking.FIXED, 1));
        chain.add(passes("Basic"), ranked(AuthRanking.BASIC, 2));

        XmlaRequest served = ((AuthenticationChain.Outcome.Proceed) chain.run(XmlaRequest.anonymous())).request();

        assertThat(served.userName()).isEqualTo("nobody");
        // It names a caller without proving anything, so a rule demanding
        // authentication must still say no.
        assertThat(served.isAuthenticated()).isFalse();
        assertThat(served.source()).isEqualTo(IdentitySource.FALLBACK);
    }

    @Test
    void anEmptyChainLeavesTheRequestAnonymous() {
        AuthenticationChain chain = new AuthenticationChain();

        AuthenticationChain.Outcome outcome = chain.run(XmlaRequest.anonymous());

        assertThat(chain.isEmpty()).isTrue();
        assertThat(((AuthenticationChain.Outcome.Proceed) outcome).request().isAnonymous()).isTrue();
    }

    @Test
    void aRemovedMechanismIsNoLongerAsked() {
        XmlaAuthenticator basic = passes("Basic");
        AuthenticationChain chain = new AuthenticationChain();
        chain.add(basic, ranked(AuthRanking.BASIC, 1));
        chain.add(passes("Header"), ranked(AuthRanking.TRUSTED_HEADER, 2));

        chain.remove(basic);
        chain.run(XmlaRequest.anonymous());

        assertThat(asked).containsExactly("Header");
        assertThat(chain.ranked()).hasSize(1);
    }
}
