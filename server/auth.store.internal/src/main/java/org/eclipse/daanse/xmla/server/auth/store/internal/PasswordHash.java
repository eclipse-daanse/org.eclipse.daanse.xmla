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
package org.eclipse.daanse.xmla.server.auth.store.internal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * How a password is stored and checked here.
 * <p>
 * PBKDF2 with a per-user salt, from the JDK, so nothing is added to the class
 * path for it. The encoded form carries everything needed to check against it
 * later, which is what lets the iteration count be raised without invalidating
 * what is already configured:
 *
 * <pre>
 * pbkdf2:sha256:&lt;iterations&gt;:&lt;salt-base64&gt;:&lt;hash-base64&gt;
 * </pre>
 * <p>
 * Comparison is time-constant. A comparison that returns early on the first
 * wrong byte tells anyone who can measure it how much of a guess was right.
 */
public final class PasswordHash {

    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String PREFIX = "pbkdf2:sha256:";
    private static final int DEFAULT_ITERATIONS = 210_000;
    private static final int SALT_BYTES = 16;
    private static final int KEY_BITS = 256;

    private PasswordHash() {
        // static access only
    }

    /** The encoded form to put in the configuration. */
    public static String encode(char[] password) {
        byte[] salt = new byte[SALT_BYTES];
        new SecureRandom().nextBytes(salt);
        byte[] hash = derive(password, salt, DEFAULT_ITERATIONS);
        Base64.Encoder encoder = Base64.getEncoder();
        return PREFIX + DEFAULT_ITERATIONS + ":" + encoder.encodeToString(salt) + ":" + encoder.encodeToString(hash);
    }

    /**
     * Whether this is the encoded form at all, without checking any password
     * against it.
     */
    public static boolean isEncoded(String encoded) {
        return encoded != null && encoded.startsWith(PREFIX)
                && encoded.substring(PREFIX.length()).split(":").length == 3;
    }

    /**
     * Whether the password produces the encoded hash.
     *
     * @return {@code false} for anything unreadable, so a malformed entry denies
     *         access rather than granting it
     */
    public static boolean matches(char[] password, String encoded) {
        if (password == null || encoded == null || !encoded.startsWith(PREFIX)) {
            return false;
        }
        String[] parts = encoded.substring(PREFIX.length()).split(":");
        if (parts.length != 3) {
            return false;
        }
        try {
            int iterations = Integer.parseInt(parts[0]);
            byte[] salt = Base64.getDecoder().decode(parts[1]);
            byte[] expected = Base64.getDecoder().decode(parts[2]);
            byte[] actual = derive(password, salt, iterations);
            return MessageDigest.isEqual(expected, actual);
        } catch (IllegalArgumentException unreadable) {
            return false;
        }
    }

    private static byte[] derive(char[] password, byte[] salt, int iterations) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, KEY_BITS);
        try {
            return SecretKeyFactory.getInstance(ALGORITHM).generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("the JDK does not provide " + ALGORITHM, e);
        } finally {
            spec.clearPassword();
        }
    }

    /**
     * Produces the encoded form for a password, so a deployment can fill its
     * configuration without writing code:
     *
     * <pre>
     * java -cp &lt;this bundle&gt; org.eclipse.daanse.xmla.server.auth.store.internal.PasswordHash secret
     * </pre>
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("usage: PasswordHash <password>");
            return;
        }
        System.out.println(encode(args[0].toCharArray()));
    }
}
