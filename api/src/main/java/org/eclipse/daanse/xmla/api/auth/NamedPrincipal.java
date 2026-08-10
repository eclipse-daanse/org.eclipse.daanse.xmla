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

import java.security.Principal;

/**
 * A caller that is nothing but a name.
 * <p>
 * Every mechanism here establishes exactly that and nothing more - what else it
 * learned travels in {@link Claims}. One shared type means a role provider or a
 * mapping can recognise the principal a mechanism produced instead of only
 * being able to ask it for a string.
 */
public record NamedPrincipal(String name) implements Principal {

    public NamedPrincipal {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("a principal needs a name");
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
