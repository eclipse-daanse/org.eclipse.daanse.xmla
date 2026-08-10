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

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;
import org.eclipse.daanse.xmla.api.auth.Claims;
import org.eclipse.daanse.xmla.api.auth.NamedPrincipal;
import org.eclipse.daanse.xmla.api.auth.RoleProvider;
import org.eclipse.daanse.xmla.api.auth.XmlaCredentials;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Users, passwords and roles from the deployment's own configuration.
 * <p>
 * For a server with no directory to ask. Passwords are stored as PBKDF2 hashes,
 * never in the clear - see {@link PasswordHash} for the encoded form and for
 * how to produce it.
 * <p>
 * It answers both questions, but they stay separable: a deployment that keeps
 * its passwords here and its roles in a directory registers the directory's
 * provider as well, and the roles are the union.
 */
@Component(service = { XmlaCredentials.class, RoleProvider.class }, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = InternalUserStore.Config.class)
public class InternalUserStore implements XmlaCredentials, RoleProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(InternalUserStore.class);

    private volatile Map<String, String> passwords = Map.of();
    private volatile Map<String, Set<String>> roles = Map.of();

    /**
     * Checked when no user of that name exists, so that answer costs what a real
     * one costs. Otherwise an unknown name comes back in microseconds while a known
     * one runs the whole key derivation, and anyone who can time a request can read
     * off which accounts exist.
     */
    private volatile String decoy = PasswordHash.encode("there is no such user".toCharArray());

    @ObjectClassDefinition
    @interface Config {

        /**
         * One entry per user, {@code name=<encoded password>}. The encoded form is what
         * {@link PasswordHash} produces; an entry in any other shape is refused rather
         * than read as a plain password.
         */
        String[] credentials() default {};

        /** One entry per user, {@code name=Role,Role}. A user may have none. */
        String[] roles() default {};
    }

    @Activate
    void activate(Config config) {
        Map<String, String> encoded = entries(config.credentials());
        for (Map.Entry<String, String> entry : encoded.entrySet()) {
            if (!PasswordHash.isEncoded(entry.getValue())) {
                // Otherwise a typo is discovered at the first login, as an indistinguishable
                // "wrong password", possibly months later.
                throw new IllegalStateException("the password of '" + entry.getKey() + "' is not in the form "
                        + "PasswordHash produces; a plain password is refused rather than read");
            }
        }
        passwords = encoded;
        Map<String, Set<String>> granted = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : entries(config.roles()).entrySet()) {
            granted.put(entry.getKey(), split(entry.getValue()));
        }
        roles = Map.copyOf(granted);
        LOGGER.debug("internal user store holds {} users", passwords.size());
    }

    @Override
    public Optional<AuthenticatedIdentity> verify(String userName, char[] password) {
        if (userName == null || password == null || password.length == 0) {
            return Optional.empty();
        }
        String encoded = passwords.get(userName);
        // An unknown user and a wrong password answer alike, and take alike as long:
        // the decoy is checked so the two cannot be told apart by timing either.
        boolean accepted = PasswordHash.matches(password, encoded == null ? decoy : encoded);
        if (encoded == null || !accepted) {
            return Optional.empty();
        }
        Principal principal = new NamedPrincipal(userName);
        return Optional.of(new AuthenticatedIdentity(principal, rolesOf(userName), Claims.none()));
    }

    @Override
    public Set<String> rolesOf(Principal principal, Claims claims) {
        return principal == null ? Set.of() : rolesOf(principal.getName());
    }

    private Set<String> rolesOf(String userName) {
        return roles.getOrDefault(userName, Set.of());
    }

    /** {@code name=value} entries, split at the first {@code =} only. */
    private static Map<String, String> entries(String[] configured) {
        Map<String, String> parsed = new LinkedHashMap<>();
        for (String entry : configured) {
            if (entry == null) {
                continue;
            }
            int separator = entry.indexOf('=');
            if (separator <= 0) {
                LOGGER.warn("ignoring the configuration entry '{}': it names no user", entry);
                continue;
            }
            String name = entry.substring(0, separator).trim();
            if (name.isEmpty()) {
                // " =hash" would otherwise become a usable account with an invisible name.
                throw new IllegalStateException("a configuration entry names a user whose name is blank");
            }
            if (parsed.put(name, entry.substring(separator + 1).trim()) != null) {
                // Last one wins is a silent way to lose an account.
                throw new IllegalStateException("the user '" + name + "' is configured more than once");
            }
        }
        return Map.copyOf(parsed);
    }

    private static Set<String> split(String value) {
        Set<String> parts = new LinkedHashSet<>();
        for (String part : List.of(value.split(","))) {
            String trimmed = part.trim();
            if (!trimmed.isEmpty()) {
                parts.add(trimmed);
            }
        }
        return Set.copyOf(parts);
    }

}
