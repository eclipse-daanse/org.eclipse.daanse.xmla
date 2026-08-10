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
package org.eclipse.daanse.xmla.server.auth.oidc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.api.auth.AuthClaims;
import org.eclipse.daanse.xmla.api.auth.XmlaAuthenticator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.sun.net.httpserver.HttpServer;

/**
 * Token validation against a real key set.
 * <p>
 * The audience case is the one that mattered: the shorter claims verifier
 * compares the whole {@code aud} list, so a token naming this endpoint
 * <em>among others</em> - which is what Keycloak issues by default - was
 * refused.
 */
class BearerAuthenticatorTest {

    private static final String ISSUER = "https://issuer.example.org";
    private static final String AUDIENCE = "daanse-xmla";

    private static RSAKey key;
    private static HttpServer jwks;
    private static String jwksUri;

    @BeforeAll
    static void publishKeys() throws Exception {
        key = new RSAKeyGenerator(2048).keyID("test").generate();
        byte[] body = new JWKSet(key.toPublicJWK()).toString().getBytes(StandardCharsets.UTF_8);

        jwks = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        jwks.createContext("/jwks", exchange -> {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, body.length);
            try (var out = exchange.getResponseBody()) {
                out.write(body);
            }
        });
        jwks.start();
        jwksUri = "http://127.0.0.1:" + jwks.getAddress().getPort() + "/jwks";
    }

    @AfterAll
    static void stop() {
        if (jwks != null) {
            jwks.stop(0);
        }
    }

    private static BearerAuthenticator.Config config(String issuer, String[] audiences, boolean allowInsecure) {
        return new BearerAuthenticator.Config() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return BearerAuthenticator.Config.class;
            }

            @Override
            public String issuer() {
                return issuer;
            }

            @Override
            public String jwksUri() {
                return jwksUri;
            }

            @Override
            public String[] audiences() {
                return audiences;
            }

            @Override
            public String[] algorithms() {
                return new String[] { "RS256" };
            }

            @Override
            public String[] tokenTypes() {
                return new String[] { "at+jwt", "JWT" };
            }

            @Override
            public String principalClaim() {
                return "sub";
            }

            @Override
            public String[] groupsClaims() {
                return new String[] { "groups" };
            }

            @Override
            public int clockSkewSeconds() {
                return 60;
            }

            @Override
            public int jwksTimeoutMillis() {
                return 2000;
            }

            @Override
            public boolean allowInsecureJwks() {
                return allowInsecure;
            }
        };
    }

    private static BearerAuthenticator authenticator() throws Exception {
        BearerAuthenticator authenticator = new BearerAuthenticator();
        authenticator.roles = (principal, claims, external) -> Set.copyOf(external);
        // The key set is served over plaintext loopback here, which the component
        // otherwise refuses for good reason.
        authenticator.activate(config(ISSUER, new String[] { AUDIENCE }, true));
        return authenticator;
    }

    private static String token(JWTClaimsSet claims, JOSEObjectType type) throws Exception {
        SignedJWT jwt = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(key.getKeyID()).type(type).build(), claims);
        jwt.sign(new RSASSASigner(key));
        return jwt.serialize();
    }

    private static JWTClaimsSet.Builder valid() {
        Instant now = Instant.now();
        return new JWTClaimsSet.Builder().issuer(ISSUER).subject("alice").issueTime(Date.from(now))
                .expirationTime(Date.from(now.plusSeconds(300)));
    }

    private static XmlaRequest bearing(String token) {
        Map<String, List<String>> headers = token == null ? Map.of()
                : Map.of("Authorization", List.of("Bearer " + token));
        return new XmlaRequest(null, null, headers, null, null);
    }

    @Test
    void aTokenNamingThisEndpointAmongOthersIsAccepted() throws Exception {
        // Keycloak's default shape. The two-argument claims verifier compared the whole
        // list and refused every one of these.
        String token = token(
                valid().audience(List.of(AUDIENCE, "account")).claim("groups", List.of("bi-admin")).build(),
                JOSEObjectType.JWT);

        XmlaAuthenticator.Result result = authenticator().authenticate(bearing(token));

        assertThat(result).isInstanceOf(XmlaAuthenticator.Result.Authenticated.class);
        var identity = ((XmlaAuthenticator.Result.Authenticated) result).identity();
        assertThat(identity.name()).isEqualTo("alice");
        assertThat(identity.roles()).containsExactly("bi-admin");
    }

    @Test
    void aTokenForAnotherServiceIsNot() throws Exception {
        String token = token(valid().audience(List.of("some-other-service")).build(), JOSEObjectType.JWT);

        assertThat(authenticator().authenticate(bearing(token))).isInstanceOf(XmlaAuthenticator.Result.Refused.class);
    }

    @Test
    void aTokenFromAnotherIssuerIsNot() throws Exception {
        String token = token(valid().issuer("https://elsewhere.example.org").audience(AUDIENCE).build(),
                JOSEObjectType.JWT);

        assertThat(authenticator().authenticate(bearing(token))).isInstanceOf(XmlaAuthenticator.Result.Refused.class);
    }

    @Test
    void anExpiredTokenIsNot() throws Exception {
        Instant past = Instant.now().minusSeconds(3600);
        String token = token(new JWTClaimsSet.Builder().issuer(ISSUER).subject("alice").audience(AUDIENCE)
                .issueTime(Date.from(past)).expirationTime(Date.from(past.plusSeconds(60))).build(),
                JOSEObjectType.JWT);

        assertThat(authenticator().authenticate(bearing(token))).isInstanceOf(XmlaAuthenticator.Result.Refused.class);
    }

    @Test
    void aTokenOfTheWrongTypeIsNot() throws Exception {
        // An ID token standing in for an access token.
        String token = token(valid().audience(AUDIENCE).build(), new JOSEObjectType("id_token+jwt"));

        assertThat(authenticator().authenticate(bearing(token))).isInstanceOf(XmlaAuthenticator.Result.Refused.class);
    }

    @Test
    void aTokenSignedByAnotherKeyIsNot() throws Exception {
        RSAKey other = new RSAKeyGenerator(2048).keyID("test").generate();
        SignedJWT jwt = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID("test").type(JOSEObjectType.JWT).build(),
                valid().audience(AUDIENCE).build());
        jwt.sign(new RSASSASigner(other));

        assertThat(authenticator().authenticate(bearing(jwt.serialize())))
                .isInstanceOf(XmlaAuthenticator.Result.Refused.class);
    }

    @Test
    void theTokensClaimsCannotReachAnotherMechanismsNamespace() throws Exception {
        // A dn claim here must not be readable where a directory bind's result is read,
        // or the caller would choose which entry a group lookup asks about.
        String token = token(valid().audience(AUDIENCE).claim("dn", "cn=somebody-else").build(), JOSEObjectType.JWT);

        XmlaAuthenticator.Result result = authenticator().authenticate(bearing(token));

        var claims = ((XmlaAuthenticator.Result.Authenticated) result).identity().claims();
        assertThat(claims.first(AuthClaims.LDAP_DN)).isNull();
        assertThat(claims.first(AuthClaims.NS_JWT, "dn")).isEqualTo("cn=somebody-else");
    }

    @Test
    void timesAreReportedAsNumbersRatherThanLocalisedText() throws Exception {
        String token = token(valid().audience(AUDIENCE).build(), JOSEObjectType.JWT);

        var claims = ((XmlaAuthenticator.Result.Authenticated) authenticator().authenticate(bearing(token))).identity()
                .claims();

        assertThat(claims.first(AuthClaims.NS_JWT, "exp")).containsOnlyDigits();
    }

    @Test
    void aRequestWithNoTokenIsNotThisMechanismsBusiness() throws Exception {
        assertThat(authenticator().authenticate(bearing(null))).isInstanceOf(XmlaAuthenticator.Result.NotMine.class);
    }

    @Test
    void aPlaintextKeySetIsRefusedUnlessSaidOtherwise() {
        BearerAuthenticator authenticator = new BearerAuthenticator();

        // Anyone on the path could otherwise substitute the keys and mint tokens this
        // server accepts.
        assertThatThrownBy(() -> authenticator.activate(config(ISSUER, new String[] { AUDIENCE }, false)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void anIssuerlessConfigurationIsRefused() {
        assertThatThrownBy(() -> new BearerAuthenticator().activate(config("", new String[] { AUDIENCE }, true)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void anUnverifiableSignatureAlgorithmIsRefused() throws IOException {
        BearerAuthenticator.Config unsigned = new BearerAuthenticator.Config() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return BearerAuthenticator.Config.class;
            }

            @Override
            public String issuer() {
                return ISSUER;
            }

            @Override
            public String jwksUri() {
                return jwksUri;
            }

            @Override
            public String[] audiences() {
                return new String[] { AUDIENCE };
            }

            @Override
            public String[] algorithms() {
                return new String[] { "none" };
            }

            @Override
            public String[] tokenTypes() {
                return new String[] { "JWT" };
            }

            @Override
            public String principalClaim() {
                return "sub";
            }

            @Override
            public String[] groupsClaims() {
                return new String[] { "groups" };
            }

            @Override
            public int clockSkewSeconds() {
                return 60;
            }

            @Override
            public int jwksTimeoutMillis() {
                return 2000;
            }

            @Override
            public boolean allowInsecureJwks() {
                return true;
            }
        };

        assertThatThrownBy(() -> new BearerAuthenticator().activate(unsigned))
                .isInstanceOf(IllegalStateException.class);
    }
}
