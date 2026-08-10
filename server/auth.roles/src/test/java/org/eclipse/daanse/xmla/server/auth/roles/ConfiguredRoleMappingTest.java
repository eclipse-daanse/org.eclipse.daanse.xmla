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
package org.eclipse.daanse.xmla.server.auth.roles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * What the table does with names it knows, names it does not, and rules it
 * cannot read.
 */
class ConfiguredRoleMappingTest {

    private static ConfiguredRoleMapping mapping(ConfiguredRoleMapping.Unmapped unmapped, boolean caseSensitive,
            String... rules) {
        ConfiguredRoleMapping mapping = new ConfiguredRoleMapping();
        mapping.activate(new ConfiguredRoleMapping.Config() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return ConfiguredRoleMapping.Config.class;
            }

            @Override
            public String[] rules() {
                return rules;
            }

            @Override
            public ConfiguredRoleMapping.Unmapped unmapped() {
                return unmapped;
            }

            @Override
            public boolean caseSensitiveExternalNames() {
                return caseSensitive;
            }
        });
        return mapping;
    }

    private static ConfiguredRoleMapping mapping(String... rules) {
        return mapping(ConfiguredRoleMapping.Unmapped.DROP, false, rules);
    }

    @Test
    void aNameIsTranslatedIntoTheRoleTheCatalogDefines() {
        assertThat(mapping("bi-admin=Admin").map(Set.of("bi-admin"))).containsExactly("Admin");
    }

    @Test
    void aDistinguishedNameNeedsNoEscaping() {
        // Splitting at the first '=' would make the left side "CN".
        String dn = "CN=BI Admins,OU=Groups,DC=example,DC=org";

        assertThat(mapping(dn + "=Admin").map(Set.of(dn))).containsExactly("Admin");
    }

    @Test
    void aRuleMayDependOnHoldingSeveralNames() {
        ConfiguredRoleMapping mapping = mapping("bi-analyst+bi-eu=EuropeAnalyst");

        assertThat(mapping.map(Set.of("bi-analyst", "bi-eu"))).containsExactly("EuropeAnalyst");
        assertThat(mapping.map(Set.of("bi-analyst"))).isEmpty();
    }

    @Test
    void anUnknownNameGrantsNothing() {
        // A catalog refuses a role it does not define, so passing it on would turn an
        // unknown group into a failed request rather than into no extra access.
        assertThat(mapping("bi-admin=Admin").map(Set.of("something-else"))).isEmpty();
    }

    @Test
    void aDeploymentWhoseNamesAlreadyAgreeCanSaySo() {
        ConfiguredRoleMapping mapping = mapping(ConfiguredRoleMapping.Unmapped.PASS_THROUGH, false, "bi-admin=Admin");

        assertThat(mapping.map(Set.of("bi-admin", "Analyst"))).containsExactlyInAnyOrder("Admin", "Analyst");
    }

    @Test
    void withNoRulesAtAllPassThroughIsTheIdentityMapping() {
        ConfiguredRoleMapping mapping = mapping(ConfiguredRoleMapping.Unmapped.PASS_THROUGH, false);

        assertThat(mapping.map(Set.of("Admin", "Analyst"))).containsExactlyInAnyOrder("Admin", "Analyst");
    }

    @Test
    void directoryNamesMatchRegardlessOfCaseUnlessAskedOtherwise() {
        assertThat(mapping("CN=BI Admins=Admin").map(Set.of("cn=bi admins"))).containsExactly("Admin");
        assertThat(mapping(ConfiguredRoleMapping.Unmapped.DROP, true, "CN=BI Admins=Admin").map(Set.of("cn=bi admins")))
                .isEmpty();
    }

    @Test
    void aRuleThatCannotBeReadStopsTheComponentComingUp() {
        // Better than a mapping that silently grants less than the operator wrote.
        assertThatThrownBy(() -> mapping("no-separator")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> mapping("=Admin")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> mapping("bi-admin=")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void nothingInIsNothingOut() {
        assertThat(mapping("bi-admin=Admin").map(Set.of())).isEmpty();
        assertThat(mapping("bi-admin=Admin").map(null)).isEmpty();
    }
}
