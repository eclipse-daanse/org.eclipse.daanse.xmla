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

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.eclipse.daanse.xmla.api.auth.RoleMapping;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The deployment's own table from identity-provider names to catalog roles.
 * <p>
 * The two vocabularies almost never match: a token says {@code bi-admin}, a
 * directory says {@code CN=BI Admins,OU=Groups,DC=example,DC=org}, and the
 * catalog knows a role called {@code Admin}. Without this the raw external
 * names travel on as catalog roles, which means a directory group name silently
 * becomes a role name - and a group that happens to share a name with a defined
 * role becomes an unintended grant.
 */
@Component(service = RoleMapping.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = ConfiguredRoleMapping.Config.class, factory = true)
public class ConfiguredRoleMapping implements RoleMapping {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguredRoleMapping.class);

    /** What becomes of a name no rule mentions. */
    public enum Unmapped {

        /**
         * It grants nothing. A catalog refuses a role it does not define, so letting an
         * unknown group through would turn it into a failed request rather than into no
         * extra access.
         */
        DROP,

        /** It is used as the role name. For a deployment whose names already agree. */
        PASS_THROUGH
    }

    private volatile List<Rule> rules = List.of();
    private volatile Unmapped unmapped = Unmapped.DROP;
    private volatile boolean caseSensitive;

    @ObjectClassDefinition
    @interface Config {

        /**
         * One rule per entry, {@code external=Role}.
         * <p>
         * Split at the <em>last</em> {@code =}, so a distinguished name on the left
         * needs no escaping. The left side may join several names with {@code +}, and
         * the rule then fires only for a caller holding all of them - which is why a
         * mapping is handed the whole set at once.
         *
         * <pre>
         * bi-admin=Admin
         * CN=BI Admins,OU=Groups,DC=example,DC=org=Admin
         * bi-analyst+bi-eu=EuropeAnalyst
         * </pre>
         */
        String[] rules() default {};

        /** What happens to a name no rule mentions. */
        Unmapped unmapped() default Unmapped.DROP;

        /**
         * Whether the external names are matched exactly. Directory group names and
         * distinguished names are conventionally case-insensitive, so they are not by
         * default.
         */
        boolean caseSensitiveExternalNames() default false;
    }

    /**
     * @param required every external name that must be present for {@link #role} to
     *                 be granted
     */
    private record Rule(Set<String> required, String role) {
    }

    @Activate
    void activate(Config config) {
        boolean exact = config.caseSensitiveExternalNames();
        List<Rule> parsed = new ArrayList<>();
        for (String entry : config.rules()) {
            Rule rule = parse(entry, exact);
            if (rule != null) {
                parsed.add(rule);
            }
        }
        this.caseSensitive = exact;
        this.unmapped = config.unmapped();
        this.rules = List.copyOf(parsed);
        LOGGER.debug("{} role mapping rules, unmapped names {}", parsed.size(), config.unmapped());
    }

    private static Rule parse(String entry, boolean caseSensitive) {
        if (entry == null || entry.isBlank()) {
            return null;
        }
        int separator = entry.lastIndexOf('=');
        if (separator <= 0 || separator == entry.length() - 1) {
            throw new IllegalArgumentException("the role mapping rule '" + entry + "' is not 'external=Role'");
        }
        Set<String> required = new LinkedHashSet<>();
        for (String name : entry.substring(0, separator).split("\\+")) {
            String trimmed = name.trim();
            if (!trimmed.isEmpty()) {
                required.add(normalise(trimmed, caseSensitive));
            }
        }
        if (required.isEmpty()) {
            throw new IllegalArgumentException("the role mapping rule '" + entry + "' names nothing to match");
        }
        return new Rule(Set.copyOf(required), entry.substring(separator + 1).trim());
    }

    private static String normalise(String name, boolean caseSensitive) {
        return caseSensitive ? name : name.toLowerCase(Locale.ROOT);
    }

    @Override
    public Set<String> map(Set<String> external) {
        if (external == null || external.isEmpty()) {
            return Set.of();
        }
        boolean exact = caseSensitive;
        Set<String> held = new LinkedHashSet<>();
        for (String name : external) {
            held.add(normalise(name, exact));
        }

        Set<String> granted = new LinkedHashSet<>();
        Set<String> recognised = new LinkedHashSet<>();
        for (Rule rule : rules) {
            if (held.containsAll(rule.required())) {
                granted.add(rule.role());
                recognised.addAll(rule.required());
            }
        }
        if (unmapped == Unmapped.PASS_THROUGH) {
            for (String name : external) {
                if (!recognised.contains(normalise(name, exact))) {
                    granted.add(name);
                }
            }
        }
        return Set.copyOf(granted);
    }
}
