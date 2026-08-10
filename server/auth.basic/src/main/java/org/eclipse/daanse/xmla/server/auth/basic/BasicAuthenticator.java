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

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.api.auth.AuthRanking;
import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;
import org.eclipse.daanse.xmla.api.auth.RoleResolution;
import org.eclipse.daanse.xmla.api.auth.XmlaAuthenticator;
import org.eclipse.daanse.xmla.api.auth.XmlaCredentials;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * HTTP BASIC over whatever {@link XmlaCredentials} a deployment registers.
 * <p>
 * BASIC sends the password in the clear on every request, so it is only honest
 * over TLS or on a loopback interface. It is here because it is what Analysis
 * Services clients - Excel, msolap.dll, every XMLA library - actually send.
 * <p>
 * The credential store is a mandatory reference: an endpoint configured for
 * BASIC without one would either refuse everyone or admit everyone, and this
 * component simply does not come up instead.
 */
@Component(service = XmlaAuthenticator.class, property = "service.ranking:Integer=" + AuthRanking.BASIC)
@Designate(ocd = BasicAuthenticator.Config.class)
public class BasicAuthenticator implements XmlaAuthenticator {

    private static final String SCHEME = "Basic";
    private static final String PREFIX = SCHEME + " ";

    @Reference(cardinality = ReferenceCardinality.MANDATORY)
    XmlaCredentials credentials;

    @Reference(cardinality = ReferenceCardinality.MANDATORY)
    RoleResolution roles;

    private volatile String realm;
    private volatile int maxLength;

    @ObjectClassDefinition
    @interface Config {

        /** The realm name a BASIC challenge shows the user. */
        String realm() default "Daanse XMLA";

        /**
         * The longest {@code Authorization} value that will be decoded at all, so an
         * unauthenticated caller cannot make this server allocate at will.
         */
        int maxCredentialsLength() default 4096;
    }

    @Activate
    void activate(Config config) {
        realm = config.realm();
        maxLength = config.maxCredentialsLength();
    }

    @Override
    public String scheme() {
        return SCHEME;
    }

    /**
     * The {@code charset} parameter is RFC 7617's only way to say which encoding
     * the password is in. Without it Windows clients send their ANSI code page, and
     * a correct password containing anything outside ASCII is rejected for good,
     * with nothing to see anywhere.
     */
    @Override
    public String challenge() {
        return PREFIX + "realm=\"" + realm + "\", charset=\"UTF-8\"";
    }

    @Override
    public Result authenticate(XmlaRequest request) {
        String authorization = request.header("Authorization");
        if (authorization == null || !authorization.regionMatches(true, 0, SCHEME, 0, SCHEME.length())) {
            return new Result.NotMine();
        }
        String encoded = authorization.length() > PREFIX.length() ? authorization.substring(PREFIX.length()).trim()
                : "";
        if (encoded.isEmpty()) {
            // Some clients probe with a bare scheme name; answering the challenge is more
            // useful than falling through to anonymous.
            return new Result.Challenge(challenge());
        }
        if (encoded.length() > maxLength) {
            return new Result.Refused("the Basic credentials are longer than this endpoint accepts");
        }

        byte[] decoded;
        try {
            decoded = Base64.getMimeDecoder().decode(encoded);
        } catch (IllegalArgumentException notBase64) {
            return new Result.Refused("the Basic credentials are not base64");
        }
        try {
            int colon = indexOfColon(decoded);
            if (colon < 0) {
                return new Result.Refused("the Basic credentials carry no ':' separator");
            }
            // The password never becomes a String: one would stay in the heap until the
            // garbage collector got to it, and could not be overwritten, which would make
            // the scrubbing below decorative.
            String user = decode(decoded, 0, colon).toString();
            char[] password = charsOf(decode(decoded, colon + 1, decoded.length - colon - 1));
            try {
                return verify(user, password);
            } finally {
                Arrays.fill(password, '\0');
            }
        } finally {
            Arrays.fill(decoded, (byte) 0);
        }
    }

    private Result verify(String user, char[] password) {
        Optional<AuthenticatedIdentity> verified = credentials.verify(user, password);
        if (verified.isEmpty()) {
            // "no such user" and "wrong password" answer identically on purpose - the
            // difference is only useful to someone enumerating accounts.
            return new Result.Refused("the credentials were not accepted");
        }
        AuthenticatedIdentity identity = verified.get();
        return Result.Authenticated.of(identity.withRoles(roles.resolve(identity.principal(), identity.claims())));
    }

    private static int indexOfColon(byte[] pair) {
        for (int index = 0; index < pair.length; index++) {
            if (pair[index] == ':') {
                return index;
            }
        }
        return -1;
    }

    /**
     * UTF-8 as RFC 7617 recommends, falling back to the code page Windows clients
     * send when the bytes are not valid UTF-8.
     */
    private static CharBuffer decode(byte[] bytes, int offset, int length) {
        ByteBuffer source = ByteBuffer.wrap(bytes, offset, length);
        CharsetDecoder strict = StandardCharsets.UTF_8.newDecoder().onMalformedInput(CodingErrorAction.REPORT)
                .onUnmappableCharacter(CodingErrorAction.REPORT);
        try {
            return strict.decode(source);
        } catch (CharacterCodingException notUtf8) {
            return StandardCharsets.ISO_8859_1.decode(ByteBuffer.wrap(bytes, offset, length));
        }
    }

    private static char[] charsOf(CharBuffer buffer) {
        char[] chars = new char[buffer.remaining()];
        buffer.get(chars);
        if (buffer.hasArray()) {
            Arrays.fill(buffer.array(), '\0');
        }
        return chars;
    }
}
