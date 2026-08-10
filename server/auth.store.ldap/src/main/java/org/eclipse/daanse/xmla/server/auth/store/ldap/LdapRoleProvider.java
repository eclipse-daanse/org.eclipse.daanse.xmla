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

import java.security.Principal;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;

import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.eclipse.daanse.xmla.api.auth.AuthClaims;
import org.eclipse.daanse.xmla.api.auth.Claims;
import org.eclipse.daanse.xmla.api.auth.RoleProvider;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reads a caller's groups from LDAP or Active Directory.
 * <p>
 * Registered on its own, so it answers for every mechanism: a caller who
 * arrived with a Kerberos ticket, a bearer token or Basic credentials all get
 * their groups from the same directory. The name it searches by is the
 * principal's, and when the mechanism already knows the entry's distinguished
 * name it is used instead - which is what {@link LdapCredentials} passes along.
 * <p>
 * The group names are the directory's own. Translating them into the roles a
 * catalog defines is a {@code RoleMapping}'s business, not this one's.
 */
@Component(service = RoleProvider.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = LdapRoleProvider.Config.class)
public class LdapRoleProvider implements RoleProvider {

    /**
     * The claim a directory bind reports the caller's entry under.
     *
     * @deprecated use {@link AuthClaims#LDAP_DN}, which is namespaced so that a
     *             token claim of the same name cannot steer this lookup
     */
    @Deprecated
    public static final String CLAIM_DN = AuthClaims.LDAP_DN;

    private static final Logger LOGGER = LoggerFactory.getLogger(LdapRoleProvider.class);

    private volatile Config config;
    private volatile Directory.Settings directory;

    @ObjectClassDefinition
    @interface Config {

        /** The directory, e.g. {@code ldaps://dc.example.org:636}. */
        String url();

        /** Where the groups are, e.g. {@code ou=groups,dc=example,dc=org}. */
        String groupSearchBase();

        /**
         * The filter that finds a caller's groups. {@code {0}} is the entry's
         * distinguished name, {@code {1}} the plain name.
         */
        String groupSearchFilter() default "(member={0})";

        /** The attribute holding the group's name. */
        String groupNameAttribute() default "cn";

        /**
         * The attribute on the user's own entry that already lists their groups, as
         * Active Directory does. Empty searches instead, which every directory
         * supports.
         */
        String memberOfAttribute() default "";

        /**
         * Where to look up the entry of a caller who arrived without one - anybody
         * authenticated by a token, a ticket or a proxy header. Empty means such a
         * caller gets no groups from here.
         */
        String userSearchBase() default "";

        /** The filter that finds that entry, {@code {0}} being the caller's name. */
        String userSearchFilter() default "(uid={0})";

        /** The account that searches. Empty searches anonymously. */
        String serviceBindDn() default "";

        String serviceBindPassword() default "";

        /** How the connection to the directory is protected. */
        TlsMode transportSecurity() default TlsMode.LDAPS;

        /** Confirms that {@link #transportSecurity()} of {@code NONE} is meant. */
        boolean allowUnencrypted() default false;

        int connectTimeoutMillis() default 5000;

        int readTimeoutMillis() default 10000;

        /**
         * What to do with a referral: {@code follow}, {@code ignore} or {@code throw}.
         */
        String referral() default "follow";
    }

    @Activate
    void activate(Config config) {
        if (config.url() == null || config.url().isBlank()) {
            throw new IllegalStateException("url names the directory to ask and is required");
        }
        if (config.groupSearchBase() == null || config.groupSearchBase().isBlank()) {
            throw new IllegalStateException("groupSearchBase names where the groups live and is required");
        }
        if (config.transportSecurity() == TlsMode.NONE && !config.allowUnencrypted()) {
            throw new IllegalStateException("the service bind would travel in the clear; set allowUnencrypted to "
                    + "confirm that is intended, or choose LDAPS or STARTTLS");
        }
        this.directory = new Directory.Settings(config.url(), config.transportSecurity(), config.connectTimeoutMillis(),
                config.readTimeoutMillis(), config.referral(), config.serviceBindDn(), config.serviceBindPassword());
        this.config = config;
    }

    @Override
    public Set<String> rolesOf(Principal principal, Claims claims) {
        if (principal == null) {
            return Set.of();
        }
        Config current = config;
        // Only the namespace a directory bind writes. A claim of the same name from a
        // token would otherwise decide which entry's groups this caller receives.
        String dn = claims == null ? null : claims.first(AuthClaims.LDAP_DN);
        try (Directory.Connection asService = Directory.asService(directory)) {
            if (dn == null) {
                dn = lookUp(asService, current, principal.getName());
            }
            if (dn == null) {
                LOGGER.debug("no directory entry for {}; configure userSearchBase to look one up", principal.getName());
                return Set.of();
            }
            if (!current.memberOfAttribute().isBlank()) {
                return listed(asService, current, dn);
            }
            return searched(asService, current, dn, principal.getName());
        } catch (NamingException e) {
            // Not knowing is not refusing: the access policy decides what an empty set
            // means, and another provider may still answer.
            LOGGER.warn("could not read the groups of {} from the directory", principal.getName(), e);
            return Set.of();
        }
    }

    /**
     * The entry of a caller whose mechanism did not establish one.
     * <p>
     * Without this the plain name used to be substituted into a filter written for
     * a distinguished name, which matches nothing - so a token- or ticket-
     * authenticated caller silently held no groups at all.
     */
    private static String lookUp(Directory.Connection asService, Config config, String name) throws NamingException {
        if (config.userSearchBase().isBlank()) {
            return null;
        }
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(new String[0]);
        String filter = config.userSearchFilter().replace("{0}", LdapNames.escapeFilter(name));
        NamingEnumeration<SearchResult> found = asService.context().search(config.userSearchBase(), filter, controls);
        try {
            return found.hasMore() ? found.next().getNameInNamespace() : null;
        } finally {
            close(found);
        }
    }

    /** The groups the user's own entry lists, as Active Directory keeps them. */
    private static Set<String> listed(Directory.Connection asService, Config config, String dn) throws NamingException {
        Set<String> groups = new LinkedHashSet<>();
        Attribute memberOf = asService.context().getAttributes(dn, new String[] { config.memberOfAttribute() })
                .get(config.memberOfAttribute());
        if (memberOf == null) {
            return groups;
        }
        NamingEnumeration<?> values = memberOf.getAll();
        try {
            while (values.hasMore()) {
                Object value = values.next();
                if (value != null) {
                    groups.add(value.toString());
                }
            }
        } finally {
            close(values);
        }
        return groups;
    }

    /** The groups that name the caller as a member. */
    private static Set<String> searched(Directory.Connection asService, Config config, String dn, String name)
            throws NamingException {
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(new String[] { config.groupNameAttribute() });

        // Escaped, because both values can carry a name a caller chose: the plain one
        // comes from a token claim or a proxy header. Unescaped, a filter-breaking name
        // makes this match every group in the directory.
        String filter = config.groupSearchFilter().replace("{0}", LdapNames.escapeFilter(dn)).replace("{1}",
                LdapNames.escapeFilter(name));
        Set<String> groups = new LinkedHashSet<>();
        NamingEnumeration<SearchResult> found = asService.context().search(config.groupSearchBase(), filter, controls);
        try {
            while (found.hasMore()) {
                Attribute named = found.next().getAttributes().get(config.groupNameAttribute());
                if (named != null && named.get() != null) {
                    groups.add(named.get().toString());
                }
            }
        } finally {
            close(found);
        }
        return groups;
    }

    private static void close(NamingEnumeration<?> enumeration) {
        try {
            enumeration.close();
        } catch (NamingException ignored) {
            // the search is over either way
        }
    }
}
