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

import java.security.Principal;
import java.time.Duration;
import java.util.Base64;

import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.api.auth.AuthClaims;
import org.eclipse.daanse.xmla.api.auth.AuthRanking;
import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;
import org.eclipse.daanse.xmla.api.auth.Claims;
import org.eclipse.daanse.xmla.api.auth.InbandAuthenticator;
import org.eclipse.daanse.xmla.api.auth.NamedPrincipal;
import org.eclipse.daanse.xmla.api.auth.RoleResolution;
import org.eclipse.daanse.xmla.api.auth.XmlaAuthenticator;
import org.ietf.jgss.GSSException;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Integrated authentication, on both channels, from one acceptor.
 * <p>
 * The specification's in-band {@code Authenticate} is GSS-API ([MS-SSAS] 3.2.2,
 * [RFC4178]): tokens are exchanged until GSS reports completion. HTTP
 * {@code Negotiate} is the same exchange with the tokens in headers instead of
 * in the SOAP body. Both arrive here, so a deployment configures its service
 * principal once and Excel, SSMS and a browser all get the same answer.
 * <p>
 * The two channels differ in what identifies a handshake across requests, and
 * that difference decides what each can do. The in-band one is keyed by the
 * session, which this server issued and which belongs to one caller - so a
 * handshake of any length is safe there, and that is where the specification
 * puts multi-round exchanges anyway.
 * <p>
 * HTTP Negotiate has no such key. The peer address is not one: everyone behind
 * a NAT, a reverse proxy or a terminal server shares it, and a remembered
 * context under a shared key can be completed by the wrong caller's rounds,
 * which ends with this server naming the wrong person. So HTTP Negotiate
 * remembers nothing and gets exactly one round. Kerberos completes in one
 * round, which is the case this serves. A deployment that needs several may
 * enable {@code allowMultiRoundNegotiate} and accept the peer address as the
 * key.
 * <p>
 * GSS establishes a name and nothing else. The roles come from
 * {@link RoleResolution}, keyed by that name.
 */
@Component(service = { InbandAuthenticator.class, XmlaAuthenticator.class }, property = "service.ranking:Integer="
        + AuthRanking.NEGOTIATE)
@Designate(ocd = GssAuthenticator.Config.class)
public class GssAuthenticator implements InbandAuthenticator, XmlaAuthenticator {

    private static final String SCHEME = "Negotiate";
    private static final String PREFIX = SCHEME + " ";

    private static final Logger LOGGER = LoggerFactory.getLogger(GssAuthenticator.class);

    @Reference(cardinality = ReferenceCardinality.MANDATORY)
    RoleResolution roles;

    volatile GssAcceptor acceptor;
    private volatile boolean multiRoundOverHttp;

    @ObjectClassDefinition
    @interface Config {

        /**
         * The service principal this server accepts for, host-based, e.g.
         * {@code HTTP@bi.example.org}. Empty takes whatever the JDK's login
         * configuration and keytab provide.
         */
        String servicePrincipal() default "";

        /**
         * How long a half-finished handshake is kept before it is dropped, in seconds.
         */
        int handshakeTimeoutSeconds() default 60;

        /** How many handshakes may be in flight before further ones are refused. */
        int maxPendingHandshakes() default 1000;

        /**
         * Whether an HTTP Negotiate handshake may span several requests, keyed by the
         * caller's address.
         * <p>
         * Off, because that key does not identify a caller: behind any shared address
         * two callers' rounds meet in one context, and the established name may be the
         * wrong one. Turning it on trades that for NTLM support over HTTP.
         */
        boolean allowMultiRoundNegotiate() default false;
    }

    @Activate
    void activate(Config config) throws GSSException {
        GssAcceptor created = new GssAcceptor(config.servicePrincipal(),
                Duration.ofSeconds(config.handshakeTimeoutSeconds()), config.maxPendingHandshakes());
        // Fail the component rather than every request: a missing keytab is a
        // deployment mistake, and it should be visible where deployment mistakes are.
        created.verifyCredential();
        multiRoundOverHttp = config.allowMultiRoundNegotiate();
        acceptor = created;
        if (multiRoundOverHttp) {
            LOGGER.warn("multi-round Negotiate over HTTP is keyed by the caller's address; behind a NAT or a "
                    + "reverse proxy two callers can share one handshake");
        }
    }

    @Deactivate
    void deactivate() {
        GssAcceptor held = acceptor;
        if (held != null) {
            held.dispose();
        }
    }

    // --- the in-band Authenticate handshake ---

    @Override
    public InbandAuthenticator.Result authenticate(byte[] token, XmlaRequest request) {
        String key = request.sessionId();
        try {
            GssAcceptor.Round round = acceptor.accept(key, token);
            if (!round.established()) {
                return new InbandAuthenticator.Result.Continue(round.token());
            }
            return new InbandAuthenticator.Result.Done(identityOf(round.name()), round.token());
        } catch (GSSException | RuntimeException e) {
            acceptor.forget(key);
            LOGGER.debug("the in-band handshake failed", e);
            return new InbandAuthenticator.Result.Refused("the security token was not accepted");
        }
    }

    // --- HTTP Negotiate ---

    @Override
    public String scheme() {
        return SCHEME;
    }

    @Override
    public String challenge() {
        return SCHEME;
    }

    @Override
    public XmlaAuthenticator.Result authenticate(XmlaRequest request) {
        String authorization = request.header("Authorization");
        if (authorization == null || !authorization.regionMatches(true, 0, SCHEME, 0, SCHEME.length())) {
            return new XmlaAuthenticator.Result.NotMine();
        }
        String encoded = authorization.length() > PREFIX.length() ? authorization.substring(PREFIX.length()).trim()
                : "";
        if (encoded.isEmpty()) {
            // The bare scheme is how some clients ask what this server supports.
            return new XmlaAuthenticator.Result.Challenge(SCHEME);
        }
        byte[] token;
        try {
            token = Base64.getMimeDecoder().decode(encoded);
        } catch (IllegalArgumentException notBase64) {
            return new XmlaAuthenticator.Result.Refused("the Negotiate token is not base64");
        }
        return multiRoundOverHttp ? remembered(request, token) : single(token);
    }

    /** One round and nothing kept, which is all Kerberos needs. */
    private XmlaAuthenticator.Result single(byte[] token) {
        try {
            GssAcceptor.Round round = acceptor.acceptOnce(token);
            if (!round.established()) {
                return new XmlaAuthenticator.Result.Refused("this endpoint completes Negotiate in one round; "
                        + "a handshake that needs more belongs in the in-band Authenticate exchange");
            }
            return established(round);
        } catch (GSSException | RuntimeException e) {
            LOGGER.debug("the Negotiate handshake failed", e);
            return new XmlaAuthenticator.Result.Refused("the security token was not accepted");
        }
    }

    private XmlaAuthenticator.Result remembered(XmlaRequest request, byte[] token) {
        String key = request.remoteAddress() == null ? "unknown-peer" : request.remoteAddress();
        try {
            GssAcceptor.Round round = acceptor.accept(key, token);
            if (!round.established()) {
                return new XmlaAuthenticator.Result.Challenge(
                        PREFIX + Base64.getEncoder().encodeToString(round.token()));
            }
            return established(round);
        } catch (GSSException | RuntimeException e) {
            acceptor.forget(key);
            LOGGER.debug("the Negotiate handshake failed", e);
            return new XmlaAuthenticator.Result.Refused("the security token was not accepted");
        }
    }

    /**
     * The final token travels back on the successful response: a client that asked
     * for mutual authentication verifies it, and without it that client never
     * finishes.
     */
    private XmlaAuthenticator.Result established(GssAcceptor.Round round) {
        byte[] token = round.token();
        String answer = token == null || token.length == 0 ? null : PREFIX + Base64.getEncoder().encodeToString(token);
        return new XmlaAuthenticator.Result.Authenticated(identityOf(round.name()), answer);
    }

    private AuthenticatedIdentity identityOf(String name) {
        Principal principal = new NamedPrincipal(name);
        Claims claims = Claims.in(AuthClaims.NS_GSS).put(AuthClaims.SUBJECT, name).build();
        return new AuthenticatedIdentity(principal, roles.resolve(principal, claims), claims);
    }
}
