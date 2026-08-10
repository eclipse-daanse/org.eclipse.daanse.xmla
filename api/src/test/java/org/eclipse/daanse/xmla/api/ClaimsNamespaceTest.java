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

import java.util.List;

import org.eclipse.daanse.xmla.api.auth.AuthClaims;
import org.eclipse.daanse.xmla.api.auth.Claims;
import org.junit.jupiter.api.Test;

/**
 * That one mechanism's claims cannot be mistaken for another's.
 * <p>
 * The values come from tokens and proxy headers, so a caller may be able to
 * choose them. If a token claim could land where a directory bind's result is
 * read, the caller would be choosing which directory entry a group lookup asks
 * about.
 */
class ClaimsNamespaceTest {

    @Test
    void aTokenClaimAndADirectoryFactAreDifferentThings() {
        Claims fromToken = Claims.in(AuthClaims.NS_JWT).put(AuthClaims.DN, "cn=mallory,ou=chosen").build();
        Claims fromDirectory = Claims.in(AuthClaims.NS_LDAP).put(AuthClaims.DN, "cn=alice,ou=users").build();

        assertThat(fromToken.first(AuthClaims.LDAP_DN)).isNull();
        assertThat(fromDirectory.first(AuthClaims.LDAP_DN)).isEqualTo("cn=alice,ou=users");
    }

    @Test
    void aClaimCannotSpellItsWayIntoAnotherNamespace() {
        // A token whose claim is literally named "ldap:dn".
        Claims forged = Claims.in(AuthClaims.NS_JWT).put("ldap:dn", "cn=mallory").build();

        assertThat(forged.first(AuthClaims.LDAP_DN)).isNull();
        assertThat(forged.names()).containsExactly("jwt:ldap%3Adn");
    }

    @Test
    void aPercentInANameCannotForgeAnEscapeEither() {
        Claims one = Claims.in(AuthClaims.NS_JWT).put("ldap%3Adn", "a").build();
        Claims other = Claims.in(AuthClaims.NS_JWT).put("ldap:dn", "b").build();

        assertThat(one.names()).isNotEqualTo(other.names());
    }

    @Test
    void aUriShapedClaimNameSurvives() {
        String schemas = "http://schemas.microsoft.com/identity/claims/objectidentifier";
        Claims claims = Claims.in(AuthClaims.NS_JWT).put(schemas, "abc").build();

        assertThat(claims.first(AuthClaims.NS_JWT, schemas)).isEqualTo("abc");
    }

    @Test
    void aMultiValuedClaimKeepsEveryValue() {
        Claims claims = Claims.in(AuthClaims.NS_HEADER).put(AuthClaims.GROUPS, List.of("a", "b")).build();

        assertThat(claims.all(AuthClaims.NS_HEADER, AuthClaims.GROUPS)).containsExactly("a", "b");
        assertThat(claims.all(AuthClaims.NS_JWT, AuthClaims.GROUPS)).isEmpty();
    }

    @Test
    void nothingCollectedIsNoClaims() {
        assertThat(Claims.in(AuthClaims.NS_JWT).put("absent", (String) null).build().isEmpty()).isTrue();
    }
}
