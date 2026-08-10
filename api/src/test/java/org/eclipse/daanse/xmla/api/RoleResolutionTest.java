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

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.daanse.xmla.api.auth.Claims;
import org.eclipse.daanse.xmla.api.auth.RoleMapping;
import org.eclipse.daanse.xmla.api.auth.RoleProvider;
import org.eclipse.daanse.xmla.api.auth.Roles;
import org.junit.jupiter.api.Test;

/**
 * How the two sources of roles compose: what a mechanism read itself, and what
 * a directory answers about the caller.
 */
class RoleResolutionTest {

    private static final Principal ALICE = () -> "alice";

    @Test
    void everyProvidersAnswerCounts() {
        RoleProvider directory = (principal, claims) -> Set.of("Admin");
        RoleProvider table = (principal, claims) -> Set.of("Analyst");

        Set<String> roles = Roles.resolve(ALICE, Claims.none(), List.of(directory, table), null);

        assertThat(roles).containsExactlyInAnyOrder("Admin", "Analyst");
    }

    @Test
    void aProviderThatKnowsNothingIsNotAFailure() {
        RoleProvider silent = (principal, claims) -> Set.of();
        assertThat(Roles.resolve(ALICE, Claims.none(), List.of(silent), null)).isEmpty();
    }

    @Test
    void theMappingTranslatesTheProvidersVocabulary() {
        RoleProvider directory = (principal, claims) -> Set.of("CN=BI Admins,OU=Groups");
        RoleMapping mapping = external -> external.contains("CN=BI Admins,OU=Groups") ? Set.of("Admin") : Set.of();

        assertThat(Roles.resolve(ALICE, Claims.none(), List.of(directory), mapping)).containsExactly("Admin");
    }

    @Test
    void anUnmappedNameIsDroppedRatherThanPassedOn() {
        // A catalog refuses a role it does not define, so an unknown group must not
        // travel on as if it were one.
        RoleMapping mapping = external -> Set.of();
        assertThat(Roles.map(Set.of("some-group"), mapping)).isEmpty();
    }

    @Test
    void withoutAMappingTheNamesPassThrough() {
        assertThat(Roles.map(Set.of("Admin"), null)).containsExactly("Admin");
        assertThat(RoleMapping.identity().map(Set.of("Admin"))).containsExactly("Admin");
    }

    @Test
    void aProviderThatFailsDoesNotDecideTheRequest() {
        // One unreachable directory must not turn into a failed request; the contract
        // says a provider that knows nothing answers empty, and so does a broken one.
        RoleProvider broken = (principal, claims) -> {
            throw new IllegalStateException("the directory is unreachable");
        };
        RoleProvider table = (principal, claims) -> Set.of("Analyst");

        assertThat(Roles.resolve(ALICE, Claims.none(), List.of(broken, table), null)).containsExactly("Analyst");
    }

    @Test
    void theMappingSeesOneUnionAndSeesItOnce() {
        RoleProvider directory = (principal, claims) -> Set.of("bi-power");
        List<Set<String>> asked = new ArrayList<>();
        RoleMapping mapping = external -> {
            asked.add(external);
            // A rule over a combination, which is only expressible if the whole set arrives
            // at once.
            return external.containsAll(Set.of("bi-admin", "bi-power")) ? Set.of("Admin") : Set.of();
        };

        Set<String> roles = Roles.resolve(ALICE, Claims.none(), List.of("bi-admin"), List.of(directory), mapping);

        assertThat(asked).hasSize(1);
        assertThat(asked.get(0)).containsExactlyInAnyOrder("bi-admin", "bi-power");
        assertThat(roles).containsExactly("Admin");
    }

    @Test
    void aMappingThatAnswersNothingAtAllIsTolerated() {
        RoleMapping broken = external -> null;
        assertThat(Roles.map(Set.of("bi-admin"), broken)).isEmpty();
    }

    @Test
    void aProviderMayDecideFromTheClaimsRatherThanTheName() {
        Claims claims = new Claims(Map.of("groups", List.of("bi-admin")));
        RoleProvider fromClaims = (principal, given) -> Set.copyOf(given.all("groups"));

        assertThat(Roles.resolve(ALICE, claims, List.of(fromClaims), null)).containsExactly("bi-admin");
    }

    @Test
    void claimsKeepEveryValueAndAnswerForAbsentOnes() {
        Claims claims = new Claims(Map.of("groups", List.of("a", "b")));

        assertThat(claims.all("groups")).containsExactly("a", "b");
        assertThat(claims.first("groups")).isEqualTo("a");
        assertThat(claims.all("missing")).isEmpty();
        assertThat(claims.first("missing")).isNull();
        assertThat(Claims.none().isEmpty()).isTrue();
    }
}
