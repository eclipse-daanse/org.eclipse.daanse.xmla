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
import java.util.Optional;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.eclipse.daanse.xmla.api.auth.AuthClaims;
import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;
import org.eclipse.daanse.xmla.api.auth.Claims;
import org.eclipse.daanse.xmla.api.auth.NamedPrincipal;
import org.eclipse.daanse.xmla.api.auth.XmlaCredentials;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Verifies a password against LDAP or Active Directory, by binding as the user.
 * <p>
 * Binding <em>is</em> the check: the directory refuses a wrong password, and no
 * password ever leaves this bundle. The user's entry is found either from a
 * pattern, when the directory tree says where users live, or by searching as a
 * service account, when it does not.
 * <p>
 * Registered on its own, separate from {@link LdapRoleProvider}, so a
 * deployment may verify credentials here and read roles from a token, or the
 * other way round.
 */
@Component(service = XmlaCredentials.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = LdapCredentials.Config.class)
public class LdapCredentials implements XmlaCredentials {

    private static final Logger LOGGER = LoggerFactory.getLogger(LdapCredentials.class);

    private volatile Config config;
    private volatile Directory.Settings directory;

    @ObjectClassDefinition
    @interface Config {

        /** The directory, e.g. {@code ldaps://dc.example.org:636}. */
        String url();

        /**
         * Where the user's entry is, with {@code {0}} for the name as the client sent
         * it, e.g. {@code uid={0},ou=people,dc=example,dc=org}. Empty searches instead.
         */
        String userDnPattern() default "";

        /** Where to search for the user's entry when there is no pattern. */
        String userSearchBase() default "";

        /** The filter to search with, {@code {0}} being the name. */
        String userSearchFilter() default "(uid={0})";

        /** The account that searches. Empty searches anonymously. */
        String serviceBindDn() default "";

        String serviceBindPassword() default "";

        /** How the connection to the directory is protected. */
        TlsMode transportSecurity() default TlsMode.LDAPS;

        /**
         * Confirms that {@link #transportSecurity()} of {@code NONE} is meant. Without
         * it the component does not come up, because a simple bind on an unprotected
         * connection sends the user's password in the clear.
         */
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
        this.directory = settingsOf(config);
        this.config = config;
    }

    /**
     * @throws IllegalStateException rather than letting a missing value reach the
     *                               directory layer, where a null URL becomes a
     *                               {@code NullPointerException} on a request
     *                               thread that no handler here would catch
     */
    static Directory.Settings settingsOf(Config config) {
        if (config.url() == null || config.url().isBlank()) {
            throw new IllegalStateException("url names the directory to ask and is required");
        }
        if (config.transportSecurity() == TlsMode.NONE && !config.allowUnencrypted()) {
            throw new IllegalStateException("every password would travel in the clear; set allowUnencrypted to "
                    + "confirm that is intended, or choose LDAPS or STARTTLS");
        }
        if (config.userDnPattern().isBlank() && config.userSearchBase().isBlank()) {
            throw new IllegalStateException("either userDnPattern or userSearchBase is needed to find a user");
        }
        return new Directory.Settings(config.url(), config.transportSecurity(), config.connectTimeoutMillis(),
                config.readTimeoutMillis(), config.referral(), config.serviceBindDn(), config.serviceBindPassword());
    }

    @Override
    public Optional<AuthenticatedIdentity> verify(String userName, char[] password) {
        if (userName == null || userName.isBlank() || password == null || password.length == 0) {
            return Optional.empty();
        }
        String userDn;
        try {
            userDn = dnOf(userName);
        } catch (NamingException e) {
            LOGGER.warn("could not look up {} in the directory", userName, e);
            return Optional.empty();
        }
        if (userDn == null) {
            return Optional.empty();
        }

        try (Directory.Connection asUser = Directory.asUser(directory, userDn, password)) {
            Principal principal = new NamedPrincipal(userName);
            Claims claims = Claims.in(AuthClaims.NS_LDAP).put(AuthClaims.DN, userDn).build();
            return Optional.of(AuthenticatedIdentity.of(principal, claims));
        } catch (NamingException refused) {
            LOGGER.debug("the directory refused the bind for {}", userDn, refused);
            return Optional.empty();
        }
    }

    /** The user's distinguished name, from the pattern or by searching. */
    private String dnOf(String userName) throws NamingException {
        Config current = config;
        if (!current.userDnPattern().isBlank()) {
            return current.userDnPattern().replace("{0}", LdapNames.escapeDn(userName));
        }
        try (Directory.Connection asService = Directory.asService(directory)) {
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            controls.setReturningAttributes(new String[0]);
            String filter = current.userSearchFilter().replace("{0}", LdapNames.escapeFilter(userName));
            NamingEnumeration<SearchResult> found = asService.context().search(current.userSearchBase(), filter,
                    controls);
            try {
                return found.hasMore() ? found.next().getNameInNamespace() : null;
            } finally {
                close(found);
            }
        }
    }

    /**
     * A search left open holds its slot in the connection pool, which is exactly
     * the case the pool makes worse rather than better.
     */
    private static void close(NamingEnumeration<?> enumeration) {
        try {
            enumeration.close();
        } catch (NamingException ignored) {
            // the search is over either way
        }
    }
}
