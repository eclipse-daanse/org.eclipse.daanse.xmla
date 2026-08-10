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
package org.eclipse.daanse.xmla.server.auth.store.ldap;

import javax.naming.ldap.Rdn;

/**
 * Escaping for the two places a caller-supplied name reaches the directory.
 * <p>
 * Both are template substitutions, so an unescaped name does not merely fail -
 * it changes what is being asked. A name of the shape {@code x)(|(cn=*} turns a
 * membership filter into one that matches every group in the directory, and
 * every one of those names then travels on as a role. The name can come from a
 * JWT claim or a proxy header, which an attacker may be able to choose.
 */
final class LdapNames {

    private LdapNames() {
        // static access only
    }

    /** A value inside a search filter, per RFC 4515 §3. */
    static String escapeFilter(String value) {
        if (value == null) {
            return "";
        }
        StringBuilder escaped = new StringBuilder(value.length() + 8);
        for (int index = 0; index < value.length(); index++) {
            char character = value.charAt(index);
            switch (character) {
            case '\\' -> escaped.append("\\5c");
            case '*' -> escaped.append("\\2a");
            case '(' -> escaped.append("\\28");
            case ')' -> escaped.append("\\29");
            case '\0' -> escaped.append("\\00");
            default -> escaped.append(character);
            }
        }
        return escaped.toString();
    }

    /** A value inside a distinguished name, per RFC 4514. */
    static String escapeDn(String value) {
        return value == null ? "" : Rdn.escapeValue(value);
    }
}
