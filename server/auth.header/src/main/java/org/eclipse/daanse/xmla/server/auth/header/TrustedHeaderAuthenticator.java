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

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Identity forwarded by a front that already authenticated the caller.
 * <p>
 * Authelia, oauth2-proxy and their kind sit in front of an endpoint, do the
 * login - OIDC, LDAP, WebAuthn, their business - and forward who it was in
 * headers. This turns those headers into the identity, which is how a
 * deployment gets OIDC without this server speaking it.
 * <p>
 * A forwarded header is only worth believing if it cannot have come from the
 * client, so this asks for proof and refuses to come up with none configured.
 * Two proofs are available and each configured one must hold:
 * <ul>
 * <li><strong>the peer address</strong> is one of {@code trustedUpstreams}. The
 * strongest check where it is available, because it cannot be replayed - but it
 * is available only where this server sees the front directly.</li>
 * <li><strong>a shared secret</strong> arrives in a header only the front
 * knows. Independent of the transport and of any address rewriting, and it also
 * covers the case the address check cannot: a front that forgets to strip the
 * identity headers from what reaches it.</li>
 * </ul>
 * <p>
 * <strong>Which to use depends on what sits in front.</strong> When this server
 * accepts the front's connection itself, the address is enough. When a servlet
 * container processes {@code Forwarded}/{@code X-Forwarded-For} before the
 * request arrives - Tomcat's {@code RemoteIpValve}, Jetty's
 * {@code ForwardedRequestCustomizer} - the reported peer is the original client
 * and the socket peer is simply gone; there the shared secret is the only proof
 * that works, and configuring the address instead would refuse every request.
 * <p>
 * The groups and anything else the front forwards are claims, not roles: they
 * are the front's vocabulary, and the mapping behind {@link RoleResolution}
 * translates them.
 */
@Component(service = XmlaAuthenticator.class, configurationPolicy = ConfigurationPolicy.REQUIRE,
        property = "service.ranking:Integer=" + AuthRanking.TRUSTED_HEADER)
@Designate(ocd = TrustedHeaderAuthenticator.Config.class)
public class TrustedHeaderAuthenticator implements XmlaAuthenticator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrustedHeaderAuthenticator.class);

    /** How often the refusals are summarised, so a caller cannot flood the log. */
    private static final long REPORT_INTERVAL_NANOS = 60_000_000_000L;

    @Reference(cardinality = ReferenceCardinality.MANDATORY)
    RoleResolution roles;

    private volatile Settings settings;

    private final Refusals refusals = new Refusals();

    /** Read as one, so a reconfiguration cannot be seen half-applied. */
    private record Settings(String userHeader, String groupsHeader, Map<String, String> claimHeaders,
            PeerMatcher trusted, String secretHeader, byte[] secret) {

        private boolean checksPeer() {
            return !trusted.isEmpty();
        }

        private boolean checksSecret() {
            return secret.length > 0;
        }
    }

    @ObjectClassDefinition
    @interface Config {

        /** The header the front puts the user name in. */
        String userHeader() default "Remote-User";

        /** The header the front puts the comma-separated groups in. */
        String groupsHeader() default "Remote-Groups";

        /**
         * Anything else worth keeping, as {@code Header=claim}, e.g.
         * {@code Remote-Email=email}. The values become claims of this mechanism and are
         * available to a role mapping; they grant nothing by themselves.
         */
        String[] claimHeaders() default {};

        /**
         * The addresses whose forwarded headers are believed - an address, a host name,
         * or a range such as {@code 10.0.0.0/8}.
         * <p>
         * Leave empty where the peer address is not the front's: behind a servlet
         * container that processes forwarded headers it is the client's, and no value
         * configured here could ever match.
         */
        String[] trustedUpstreams() default {};

        /**
         * The header carrying the shared secret, e.g. {@code X-Forwarded-Auth}. Empty
         * switches this proof off.
         */
        String sharedSecretHeader() default "";

        /**
         * The value that header must carry. It is only as good as the front keeping it
         * to itself, so it belongs in the same place as any other credential.
         */
        String sharedSecret() default "";
    }

    @Activate
    void activate(Config config) {
        if (config.userHeader() == null || config.userHeader().isBlank()) {
            throw new IllegalStateException("userHeader names the header carrying the identity and cannot be empty");
        }
        PeerMatcher trusted = PeerMatcher.of(config.trustedUpstreams());
        byte[] secret = secretOf(config);
        if (trusted.isEmpty() && secret.length == 0) {
            throw new IllegalStateException("nothing would prove a forwarded identity came from the front: configure "
                    + "trustedUpstreams, or sharedSecretHeader and sharedSecret where the peer address is not the "
                    + "front's - this mechanism does not come up believing anybody who asks");
        }
        settings = new Settings(config.userHeader(), config.groupsHeader(), claimHeadersOf(config), trusted,
                config.sharedSecretHeader(), secret);
        if (!trusted.isEmpty() && secret.length == 0) {
            LOGGER.info("forwarded identity is believed from {} configured upstream(s) on the peer address alone; "
                    + "a shared secret would also cover a front that fails to strip the headers",
                    config.trustedUpstreams().length);
        }
    }

    private static byte[] secretOf(Config config) {
        boolean named = config.sharedSecretHeader() != null && !config.sharedSecretHeader().isBlank();
        boolean valued = config.sharedSecret() != null && !config.sharedSecret().isBlank();
        if (named != valued) {
            throw new IllegalStateException("sharedSecretHeader and sharedSecret go together; one without the other "
                    + "would either check nothing or check against nothing");
        }
        return valued ? config.sharedSecret().getBytes(StandardCharsets.UTF_8) : new byte[0];
    }

    private static Map<String, String> claimHeadersOf(Config config) {
        Map<String, String> named = new LinkedHashMap<>();
        for (String entry : config.claimHeaders()) {
            if (entry == null || entry.isBlank()) {
                continue;
            }
            int separator = entry.indexOf('=');
            if (separator <= 0 || separator == entry.length() - 1) {
                throw new IllegalStateException("the claim header '" + entry + "' is not 'Header=claim'");
            }
            named.put(entry.substring(0, separator).trim(), entry.substring(separator + 1).trim());
        }
        return Map.copyOf(named);
    }

    @Override
    public String scheme() {
        return "TrustedHeader";
    }

    @Override
    public String challenge() {
        // A proxy identity cannot be asked for; the challenge, if any, is the front's.
        return "";
    }

    @Override
    public Result authenticate(XmlaRequest request) {
        Settings current = settings;
        String name = request.header(current.userHeader());
        if (name == null || name.isBlank()) {
            return new Result.NotMine();
        }
        if (!proven(current, request)) {
            // Somebody sent a forwarded identity this endpoint will not believe. Worth
            // knowing about, but the request is the caller's to shape, so it is summarised
            // rather than logged per request.
            refusals.record(request.remoteAddress());
            return new Result.NotMine();
        }
        String user = name.trim();
        if (user.indexOf('\r') >= 0 || user.indexOf('\n') >= 0) {
            return new Result.Refused("the forwarded user name contains a line break");
        }

        List<String> groups = split(request.header(current.groupsHeader()));
        Claims claims = claimsOf(current, request, groups);
        Principal principal = new NamedPrincipal(user);

        Set<String> granted = roles.resolve(principal, claims, groups);
        return Result.Authenticated.of(new AuthenticatedIdentity(principal, granted, claims));
    }

    /** Every configured proof has to hold; at least one is configured by construction. */
    private static boolean proven(Settings current, XmlaRequest request) {
        if (current.checksPeer() && !current.trusted().matches(request.remoteAddress())) {
            return false;
        }
        return !current.checksSecret() || carriesSecret(current, request);
    }

    private static boolean carriesSecret(Settings current, XmlaRequest request) {
        String presented = request.header(current.secretHeader());
        if (presented == null) {
            return false;
        }
        // Time-constant: a comparison that stops at the first wrong byte tells whoever
        // can measure it how much of a guess was right.
        return MessageDigest.isEqual(presented.trim().getBytes(StandardCharsets.UTF_8), current.secret());
    }

    private static Claims claimsOf(Settings current, XmlaRequest request, List<String> groups) {
        Claims.Builder claims = Claims.in(AuthClaims.NS_HEADER).put(AuthClaims.GROUPS, groups);
        for (Map.Entry<String, String> named : current.claimHeaders().entrySet()) {
            String value = request.header(named.getKey());
            if (value != null && !value.isBlank()) {
                claims.put(named.getValue(), value.trim());
            }
        }
        return claims.build();
    }

    private static List<String> split(String value) {
        List<String> parts = new ArrayList<>();
        if (value == null) {
            return parts;
        }
        for (String part : value.split(",")) {
            String trimmed = part.trim();
            if (!trimmed.isEmpty()) {
                parts.add(trimmed);
            }
        }
        return parts;
    }

    /**
     * Counts refused forwarded identities and reports them at most once a minute.
     * <p>
     * Per request this would be a log flood anybody could trigger by sending one
     * header; never reporting it would hide a misconfigured front, which looks
     * exactly like "the login silently stopped working".
     */
    private static final class Refusals {

        private final java.util.concurrent.atomic.AtomicLong since =
                new java.util.concurrent.atomic.AtomicLong(System.nanoTime());
        private final java.util.concurrent.atomic.AtomicLong count =
                new java.util.concurrent.atomic.AtomicLong();
        private volatile String last;

        private void record(String peer) {
            last = peer;
            count.incrementAndGet();
            LOGGER.debug("ignoring a forwarded identity from {}: nothing proves it came from the front", peer);

            long now = System.nanoTime();
            long started = since.get();
            if (now - started < REPORT_INTERVAL_NANOS || !since.compareAndSet(started, now)) {
                return;
            }
            long ignored = count.getAndSet(0);
            if (ignored > 0) {
                LOGGER.warn("{} forwarded identities were ignored in the last minute because nothing proved they "
                        + "came from the front; the last was from {}", ignored, last);
            }
        }
    }
}
