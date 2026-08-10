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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.Set;

import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;
import org.eclipse.daanse.xmla.api.auth.AuthClaims;
import org.eclipse.daanse.xmla.api.auth.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.images.builder.Transferable;
import org.testcontainers.utility.DockerImageName;

/**
 * The directory bundle against a real directory.
 * <p>
 * A password check that is a bind, and a group search that is a search, are
 * exactly the two things a mock cannot tell the truth about: LDAP accepts an
 * empty password as an anonymous bind and would report it as success, and the
 * filter syntax is only wrong against a server that parses it.
 */
@Testcontainers(disabledWithoutDocker = true)
class LdapStoreTest {

    private static final String ROOT = "dc=example,dc=org";
    private static final String USERS = "ou=users," + ROOT;

    /** The entries the tests need, added once the directory is up. */
    private static final String LDIF = """
            dn: ou=users,dc=example,dc=org
            objectClass: organizationalUnit
            ou: users

            dn: cn=alice,ou=users,dc=example,dc=org
            objectClass: inetOrgPerson
            cn: alice
            sn: Example
            userPassword: alicepw

            dn: cn=bob,ou=users,dc=example,dc=org
            objectClass: inetOrgPerson
            cn: bob
            sn: Example
            userPassword: bobpw

            dn: cn=analysts,ou=users,dc=example,dc=org
            objectClass: groupOfNames
            cn: analysts
            member: cn=alice,ou=users,dc=example,dc=org
            """;

    @Container
    private final GenericContainer<?> directory = new GenericContainer<>(DockerImageName.parse("osixia/openldap:1.5.0"))
            .withEnv("LDAP_DOMAIN", "example.org").withEnv("LDAP_ADMIN_PASSWORD", "adminpw")
            .withCopyToContainer(Transferable.of(LDIF), "/tmp/daanse.ldif").withExposedPorts(389)
            .waitingFor(Wait.forListeningPort());

    private LdapCredentials credentials;
    private LdapRoleProvider roles;

    /**
     * Adds the entries, ignoring the complaint on the second call - the container
     * outlives a single test method and the entries are then already there.
     */
    private void seed() throws Exception {
        directory.execInContainer("ldapadd", "-x", "-H", "ldap://localhost", "-D", "cn=admin," + ROOT, "-w", "adminpw",
                "-f", "/tmp/daanse.ldif");
    }

    private String url() {
        return "ldap://" + directory.getHost() + ":" + directory.getMappedPort(389);
    }

    @BeforeEach
    void wire() throws Exception {
        seed();
        credentials = new LdapCredentials();
        credentials.activate(credentialsConfig());
        roles = new LdapRoleProvider();
        roles.activate(rolesConfig());
    }

    @Test
    void theRightPasswordIsAccepted() {
        Optional<AuthenticatedIdentity> alice = credentials.verify("alice", "alicepw".toCharArray());

        assertThat(alice).isPresent();
        assertThat(alice.get().name()).isEqualTo("alice");
        assertThat(alice.get().claims().first(AuthClaims.LDAP_DN)).isEqualTo("cn=alice," + USERS);
    }

    @Test
    void theWrongPasswordIsNot() {
        assertThat(credentials.verify("alice", "wrong".toCharArray())).isEmpty();
    }

    @Test
    void anEmptyPasswordIsRefusedBeforeItReachesTheDirectory() {
        // The directory would take this as an anonymous bind and answer success.
        assertThat(credentials.verify("alice", new char[0])).isEmpty();
    }

    @Test
    void anUnknownUserIsNot() {
        assertThat(credentials.verify("mallory", "whatever".toCharArray())).isEmpty();
    }

    @Test
    void theGroupsAreRead() {
        AuthenticatedIdentity alice = credentials.verify("alice", "alicepw".toCharArray()).orElseThrow();

        Set<String> granted = roles.rolesOf(alice.principal(), alice.claims());

        assertThat(granted).contains("analysts");
    }

    @Test
    void aNameThatBreaksTheFilterMatchesNothing() {
        // Unescaped, this turns (member={0}) into a filter matching every group in the
        // directory - and with no role mapping in front, every group name would become
        // a catalog role. The name can come from a token claim the caller chose.
        AuthenticatedIdentity mallory = AuthenticatedIdentity.of(() -> "x)(|(cn=*", Claims.none());

        assertThat(roles.rolesOf(mallory.principal(), mallory.claims())).isEmpty();
    }

    @Test
    void aNameThatBreaksADistinguishedNameDoesNotAuthenticate() {
        assertThat(credentials.verify("alice,ou=elsewhere", "alicepw".toCharArray())).isEmpty();
    }

    @Test
    void aCallerWhoArrivedWithoutAnEntryStillGetsGroups() {
        // Bearer, Negotiate and proxy-header callers carry no distinguished name, so
        // the entry has to be looked up or they silently hold no groups at all.
        AuthenticatedIdentity fromToken = AuthenticatedIdentity.of(() -> "alice", Claims.none());

        assertThat(roles.rolesOf(fromToken.principal(), fromToken.claims())).contains("analysts");
    }

    @Test
    void aTokenCannotChooseWhichEntryTheGroupsComeFrom() {
        // A "dn" claim from a token lands in the jwt namespace and is not the one this
        // provider steers by.
        Claims forged = Claims.in(AuthClaims.NS_JWT).put(AuthClaims.DN, "cn=bob," + USERS).build();
        AuthenticatedIdentity mallory = AuthenticatedIdentity.of(() -> "alice", forged);

        assertThat(roles.rolesOf(mallory.principal(), mallory.claims())).contains("analysts");
    }

    @Test
    void anUnprotectedConnectionHasToBeConfirmed() {
        assertThatThrownBy(() -> new LdapCredentials().activate(new LdapCredentials.Config() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return LdapCredentials.Config.class;
            }

            @Override
            public String url() {
                return LdapStoreTest.this.url();
            }

            @Override
            public String userDnPattern() {
                return "cn={0}," + USERS;
            }

            @Override
            public String userSearchBase() {
                return USERS;
            }

            @Override
            public String userSearchFilter() {
                return "(cn={0})";
            }

            @Override
            public String serviceBindDn() {
                return "";
            }

            @Override
            public String serviceBindPassword() {
                return "";
            }

            @Override
            public TlsMode transportSecurity() {
                return TlsMode.NONE;
            }

            @Override
            public boolean allowUnencrypted() {
                return false;
            }

            @Override
            public int connectTimeoutMillis() {
                return 5000;
            }

            @Override
            public int readTimeoutMillis() {
                return 10000;
            }

            @Override
            public String referral() {
                return "follow";
            }
        })).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void aCallerInNoGroupGetsNoRoles() {
        AuthenticatedIdentity nobody = AuthenticatedIdentity.of(() -> "nobody",
                Claims.in(AuthClaims.NS_LDAP).put(AuthClaims.DN, "cn=nobody," + USERS).build());

        assertThat(roles.rolesOf(nobody.principal(), nobody.claims())).isEmpty();
    }

    private LdapCredentials.Config credentialsConfig() {
        return new LdapCredentials.Config() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return LdapCredentials.Config.class;
            }

            @Override
            public String url() {
                return LdapStoreTest.this.url();
            }

            @Override
            public String userDnPattern() {
                return "cn={0}," + USERS;
            }

            @Override
            public String userSearchBase() {
                return USERS;
            }

            @Override
            public String userSearchFilter() {
                return "(cn={0})";
            }

            @Override
            public String serviceBindDn() {
                return "cn=admin," + ROOT;
            }

            @Override
            public String serviceBindPassword() {
                return "adminpw";
            }

            @Override
            public TlsMode transportSecurity() {
                return TlsMode.NONE;
            }

            @Override
            public boolean allowUnencrypted() {
                // A throwaway container on loopback; nothing here is a real password.
                return true;
            }

            @Override
            public int connectTimeoutMillis() {
                return 5000;
            }

            @Override
            public int readTimeoutMillis() {
                return 10000;
            }

            @Override
            public String referral() {
                return "follow";
            }
        };
    }

    private LdapRoleProvider.Config rolesConfig() {
        return new LdapRoleProvider.Config() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return LdapRoleProvider.Config.class;
            }

            @Override
            public String url() {
                return LdapStoreTest.this.url();
            }

            @Override
            public String groupSearchBase() {
                return USERS;
            }

            @Override
            public String groupSearchFilter() {
                return "(member={0})";
            }

            @Override
            public String groupNameAttribute() {
                return "cn";
            }

            @Override
            public String memberOfAttribute() {
                return "";
            }

            @Override
            public String userSearchBase() {
                return USERS;
            }

            @Override
            public String userSearchFilter() {
                return "(cn={0})";
            }

            @Override
            public String serviceBindDn() {
                return "cn=admin," + ROOT;
            }

            @Override
            public String serviceBindPassword() {
                return "adminpw";
            }

            @Override
            public TlsMode transportSecurity() {
                return TlsMode.NONE;
            }

            @Override
            public boolean allowUnencrypted() {
                // A throwaway container on loopback; nothing here is a real password.
                return true;
            }

            @Override
            public int connectTimeoutMillis() {
                return 5000;
            }

            @Override
            public int readTimeoutMillis() {
                return 10000;
            }

            @Override
            public String referral() {
                return "follow";
            }
        };
    }
}
