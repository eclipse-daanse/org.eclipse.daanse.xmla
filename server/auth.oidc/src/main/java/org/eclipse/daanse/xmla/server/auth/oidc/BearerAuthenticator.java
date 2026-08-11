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

import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.api.auth.AuthClaims;
import org.eclipse.daanse.xmla.api.auth.AuthRanking;
import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;
import org.eclipse.daanse.xmla.api.auth.Claims;
import org.eclipse.daanse.xmla.api.auth.NamedPrincipal;
import org.eclipse.daanse.xmla.api.auth.RoleResolution;
import org.eclipse.daanse.xmla.api.auth.XmlaAuthenticator;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.JWKSourceBuilder;
import com.nimbusds.jose.proc.DefaultJOSEObjectTypeVerifier;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.DefaultJWTClaimsVerifier;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

/**
 * OAuth/OIDC bearer tokens, validated here rather than taken on trust.
 * <p>
 * The token's signature is checked against the keys the issuer publishes, which
 * are fetched from the configured JWKS endpoint and cached, and its
 * {@code iss}, {@code aud} and expiry are checked before anything else happens.
 * A deployment that would rather have a reverse proxy do this can use the
 * trusted-header mechanism instead; both are the same shape to the rest of the
 * server.
 * <p>
 * The token's own claims become the caller's {@link Claims}, so the groups an
 * identity provider ships are available to a
 * {@link org.eclipse.daanse.xmla.api.auth.RoleMapping} without a second lookup. A
 * deployment that keeps its roles elsewhere registers a
 * {@link org.eclipse.daanse.xmla.api.auth.RoleProvider}, and both sources are used.
 */
@Component(service = XmlaAuthenticator.class, configurationPolicy = ConfigurationPolicy.REQUIRE, property = "service.ranking:Integer="
        + AuthRanking.BEARER)
@Designate(ocd = BearerAuthenticator.Config.class)
public class BearerAuthenticator implements XmlaAuthenticator {

    private static final String SCHEME = "Bearer";
    private static final String PREFIX = SCHEME + " ";

    /**
     * Enough for any real key set, small enough that a wrong URL cannot fill the
     * heap.
     */
    private static final int DEFAULT_JWKS_SIZE_LIMIT = 51_200;

    private static final Logger LOGGER = LoggerFactory.getLogger(BearerAuthenticator.class);

    @Reference(cardinality = ReferenceCardinality.MANDATORY)
    RoleResolution roles;

    private volatile DefaultJWTProcessor<SecurityContext> processor;
    private volatile String principalClaim;
    private volatile List<String> groupsClaims;

    @ObjectClassDefinition
    @interface Config {

        /** The issuer a token must name in {@code iss}. */
        String issuer();

        /** Where the issuer publishes its signing keys. */
        String jwksUri();

        /**
         * The audiences a token may name in {@code aud}; naming one of them is enough.
         * Empty accepts any, which is only right when the issuer serves this endpoint
         * alone.
         */
        String[] audiences() default {};

        /** The signature algorithms accepted; a token signed otherwise is refused. */
        String[] algorithms() default { "RS256" };

        /**
         * The {@code typ} values accepted, so an ID token cannot be presented where an
         * access token was meant.
         */
        String[] tokenTypes() default { "at+jwt", "JWT" };

        /**
         * The claim the caller's name is taken from.
         * <p>
         * {@code sub} by default. OIDC guarantees stability and uniqueness only for
         * that one; {@code preferred_username} is documented as changeable, and it is
         * also the key a role provider looks the caller up by - so allowing it to
         * change is allowing roles to be inherited.
         */
        String principalClaim() default "sub";

        /**
         * The claims whose values are the caller's groups. Their union goes through the
         * role mapping.
         */
        String[] groupsClaims() default { "groups", "roles" };

        /** How much clock difference between issuer and this server is tolerated. */
        int clockSkewSeconds() default 60;

        /** How long to wait for the key set, in milliseconds. */
        int jwksTimeoutMillis() default 2000;

        /**
         * Whether a plaintext {@code jwksUri} is allowed. It is not by default: anyone
         * on the path could substitute the signing keys and mint accepted tokens.
         */
        boolean allowInsecureJwks() default false;
    }

    @Activate
    void activate(Config config) throws Exception {
        URI jwks = validated(config);

        DefaultJWTProcessor<SecurityContext> built = new DefaultJWTProcessor<>();
        built.setJWSKeySelector(new JWSVerificationKeySelector<>(algorithmsOf(config), keySource(jwks, config)));
        built.setJWSTypeVerifier(new DefaultJOSEObjectTypeVerifier<>(typesOf(config)));

        Set<String> required = new LinkedHashSet<>(List.of("sub", "iat", "exp"));
        JWTClaimsSet expected = new JWTClaimsSet.Builder().issuer(config.issuer()).build();
        // The four-argument form is the one that checks aud by containment. The shorter
        // one compares the whole list, so a token naming this endpoint among others -
        // which is what Keycloak issues by default - would be refused.
        Set<String> audiences = new LinkedHashSet<>(List.of(config.audiences()));
        DefaultJWTClaimsVerifier<SecurityContext> verifier = audiences.isEmpty()
                ? new DefaultJWTClaimsVerifier<>(expected, required)
                : new DefaultJWTClaimsVerifier<>(audiences, expected, required, null);
        verifier.setMaxClockSkew(config.clockSkewSeconds());
        built.setJWTClaimsSetVerifier(verifier);

        principalClaim = config.principalClaim();
        groupsClaims = List.of(config.groupsClaims());
        // Published only once it is fully built: a reader that saw it half-configured
        // would refuse every token and say nothing about why.
        processor = built;

        if (audiences.isEmpty()) {
            LOGGER.warn("no audience configured: a token minted for any other service by the same issuer is "
                    + "accepted here");
        }
    }

    private static URI validated(Config config) {
        if (config.issuer() == null || config.issuer().isBlank()) {
            throw new IllegalStateException("issuer names the only issuer whose tokens are accepted and is required");
        }
        if (config.jwksUri() == null || config.jwksUri().isBlank()) {
            throw new IllegalStateException("jwksUri points at the issuer's signing keys and is required");
        }
        URI jwks = URI.create(config.jwksUri());
        if (!"https".equalsIgnoreCase(jwks.getScheme()) && !config.allowInsecureJwks()) {
            throw new IllegalStateException("the signing keys would be fetched over plaintext from " + jwks
                    + "; anyone on the path could then mint tokens this server accepts");
        }
        return jwks;
    }

    private static Set<JWSAlgorithm> algorithmsOf(Config config) {
        Set<JWSAlgorithm> accepted = new LinkedHashSet<>();
        for (String name : config.algorithms()) {
            JWSAlgorithm algorithm = JWSAlgorithm.parse(name.trim());
            if (JWSAlgorithm.NONE.equals(algorithm) || JWSAlgorithm.Family.HMAC_SHA.contains(algorithm)) {
                // "none" is no signature at all, and a shared HMAC secret is not what a JWKS
                // publishes - accepting either turns the key set into decoration.
                throw new IllegalStateException(
                        "the signature algorithm " + name + " cannot be verified against a " + "published key set");
            }
            accepted.add(algorithm);
        }
        if (accepted.isEmpty()) {
            throw new IllegalStateException("at least one signature algorithm is required");
        }
        return accepted;
    }

    private static Set<JOSEObjectType> typesOf(Config config) {
        Set<JOSEObjectType> accepted = new LinkedHashSet<>();
        for (String name : config.tokenTypes()) {
            accepted.add(new JOSEObjectType(name.trim()));
        }
        return accepted;
    }

    /**
     * Fetched off the request thread, retried, and kept across a brief outage -
     * otherwise a key set that is briefly unreachable refuses every caller whose
     * key is not already cached.
     */
    private static JWKSource<SecurityContext> keySource(URI jwks, Config config) throws Exception {
        int timeout = config.jwksTimeoutMillis();
        return JWKSourceBuilder
                .create(jwks.toURL(), new DefaultResourceRetriever(timeout, timeout, DEFAULT_JWKS_SIZE_LIMIT))
                .retrying(true).refreshAheadCache(true).rateLimited(true).build();
    }

    @Deactivate
    void deactivate() {
        processor = null;
    }

    @Override
    public String scheme() {
        return SCHEME;
    }

    @Override
    public String challenge() {
        return SCHEME;
    }

    @Override
    public Result authenticate(XmlaRequest request) {
        String authorization = request.header("Authorization");
        if (authorization == null || !authorization.regionMatches(true, 0, SCHEME, 0, SCHEME.length())) {
            return new Result.NotMine();
        }
        String token = authorization.length() > PREFIX.length() ? authorization.substring(PREFIX.length()).trim() : "";
        if (token.isEmpty()) {
            return new Result.Challenge(SCHEME);
        }
        JWTClaimsSet verified;
        try {
            verified = processor.process(token, null);
        } catch (Exception refused) {
            // Signature, type, issuer, audience and expiry all end here. The client learns
            // that the token was not accepted and not which of the checks it failed.
            LOGGER.debug("the bearer token was not accepted", refused);
            return new Result.Refused("the bearer token was not accepted");
        }

        String name = nameOf(verified);
        if (name == null) {
            return new Result.Refused("the bearer token names no subject");
        }
        Claims claims = claimsOf(verified);
        Principal principal = new NamedPrincipal(name);

        return Result.Authenticated
                .of(new AuthenticatedIdentity(principal, roles.resolve(principal, claims, groups(claims)), claims));
    }

    private String nameOf(JWTClaimsSet verified) {
        Object named = verified.getClaim(principalClaim);
        if (named instanceof String name && !name.isBlank()) {
            return name;
        }
        return verified.getSubject();
    }

    /**
     * Every claim the token carries, under this mechanism's namespace.
     * <p>
     * The namespace is what stops a token deciding something it has no business
     * deciding: a claim called {@code dn} here becomes {@code jwt:dn} and cannot be
     * mistaken for the {@code ldap:dn} a directory bind established, which a role
     * provider steers its group lookup by.
     */
    private static Claims claimsOf(JWTClaimsSet verified) {
        Claims.Builder claims = Claims.in(AuthClaims.NS_JWT);
        for (Map.Entry<String, Object> claim : verified.getClaims().entrySet()) {
            claims.put(claim.getKey(), stringsOf(claim.getValue()));
        }
        return claims.build();
    }

    private static List<String> stringsOf(Object value) {
        List<String> strings = new ArrayList<>();
        if (value instanceof List<?> list) {
            for (Object each : list) {
                if (each != null) {
                    strings.add(asString(each));
                }
            }
        } else if (value != null) {
            strings.add(asString(value));
        }
        return strings;
    }

    /**
     * Times render as epoch seconds; {@code Date.toString} would carry a locale.
     */
    private static String asString(Object value) {
        return value instanceof Date date ? Long.toString(date.toInstant().getEpochSecond()) : value.toString();
    }

    private Set<String> groups(Claims claims) {
        Set<String> groups = new LinkedHashSet<>();
        for (String claim : groupsClaims) {
            groups.addAll(claims.all(AuthClaims.NS_JWT, claim));
        }
        return groups;
    }
}
