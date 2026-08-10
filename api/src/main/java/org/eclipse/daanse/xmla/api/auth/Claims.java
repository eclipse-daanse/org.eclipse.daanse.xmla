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
package org.eclipse.daanse.xmla.api.auth;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * What a mechanism learned about the caller beyond the name.
 * <p>
 * A JWT carries its claim set, a directory-backed mechanism carries the groups
 * it read, a trusted proxy carries whatever the front put in its headers. The
 * shape is deliberately untyped - it is the mechanism's own vocabulary, not
 * this project's - and it is what a {@link RoleProvider} and a
 * {@link RoleMapping} work from when they decide which catalog roles a caller
 * holds.
 * <p>
 * A claim is multi-valued because the interesting ones are: {@code groups},
 * {@code roles}, {@code amr}.
 * <p>
 * Names are namespaced by the mechanism that learned them - {@code jwt:sub},
 * {@code ldap:dn} - and a provider reads only its own namespace; see
 * {@link AuthClaims}. Without that separation a claim in a token would be
 * indistinguishable from a fact a directory bind established, and a caller who
 * can influence their own token could steer a lookup meant for something else.
 * A {@code :} inside a claim name is escaped, so a URI-shaped name from ADFS or
 * Entra cannot forge a namespace.
 */
public record Claims(Map<String, List<String>> values) {

    private static final Claims NONE = new Claims(Map.of());

    public Claims {
        Map<String, List<String>> copy = new LinkedHashMap<>();
        if (values != null) {
            for (Map.Entry<String, List<String>> entry : values.entrySet()) {
                List<String> value = entry.getValue();
                copy.put(entry.getKey(), value == null ? List.of() : List.copyOf(value));
            }
        }
        values = Map.copyOf(copy);
    }

    /** No claims at all - what a mechanism that learns only a name reports. */
    public static Claims none() {
        return NONE;
    }

    /** One single-valued claim. */
    public static Claims of(String name, String value) {
        return new Claims(Map.of(name, List.of(value)));
    }

    /** One multi-valued claim. */
    public static Claims of(String name, List<String> values) {
        return new Claims(Map.of(name, values));
    }

    /** Every value of a claim, empty when it is absent. */
    public List<String> all(String name) {
        List<String> value = values.get(name);
        return value == null ? List.of() : value;
    }

    /** The first value of a claim, or {@code null} when it is absent. */
    public String first(String name) {
        List<String> value = all(name);
        return value.isEmpty() ? null : value.get(0);
    }

    /**
     * Every value of a claim in one mechanism's namespace, empty when it is absent.
     */
    public List<String> all(String namespace, String name) {
        return all(key(namespace, name));
    }

    /** The first value of a claim in one mechanism's namespace, or {@code null}. */
    public String first(String namespace, String name) {
        return first(key(namespace, name));
    }

    /** The claim names present. */
    public Set<String> names() {
        return values.keySet();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    /** Collects claims a mechanism learned, all of them under its own namespace. */
    public static Builder in(String namespace) {
        return new Builder(namespace);
    }

    /** The key a claim of this name takes inside this namespace. */
    public static String key(String namespace, String name) {
        return namespace + ":" + escape(name);
    }

    /**
     * Renders a name so that no name can spell a namespace boundary.
     * <p>
     * The percent sign goes first, otherwise the escape of a {@code :} would be
     * indistinguishable from a name that literally contained {@code %3A}.
     */
    private static String escape(String name) {
        return name.replace("%", "%25").replace(":", "%3A");
    }

    public static final class Builder {

        private final String namespace;
        private final Map<String, List<String>> collected = new LinkedHashMap<>();

        private Builder(String namespace) {
            if (namespace == null || namespace.isBlank()) {
                throw new IllegalArgumentException("a claim needs a namespace to live in");
            }
            this.namespace = namespace;
        }

        public Builder put(String name, String value) {
            return value == null ? this : put(name, List.of(value));
        }

        public Builder put(String name, List<String> values) {
            if (name != null && values != null && !values.isEmpty()) {
                collected.put(key(namespace, name), List.copyOf(values));
            }
            return this;
        }

        public Claims build() {
            return collected.isEmpty() ? none() : new Claims(collected);
        }
    }
}
