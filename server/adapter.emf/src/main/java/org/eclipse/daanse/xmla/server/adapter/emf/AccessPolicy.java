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
package org.eclipse.daanse.xmla.server.adapter.emf;

import java.util.Set;

/**
 * Who may ask what without authenticating - an endpoint policy, enforced by the
 * transport.
 * <p>
 * The rowsets a client needs before it can authenticate meaningfully -
 * properties, data sources, the self-description - are conventionally open;
 * everything else is refused with the challenge when {@code requirePrincipal}
 * is on. Commands are never run anonymously under the policy.
 * <p>
 * It asks whether the caller is authenticated, not merely whether one is named:
 * a configured stand-in identity proves nothing and must not satisfy a policy
 * that exists to demand a login.
 */
public record AccessPolicy(boolean requirePrincipal, Set<String> anonymousRowsets) {

    /** Fully anonymous operation: nothing is refused. */
    public static final AccessPolicy OPEN = new AccessPolicy(false, Set.of());

    public AccessPolicy {
        anonymousRowsets = Set.copyOf(anonymousRowsets);
    }

    public boolean allowsDiscover(String requestType, boolean anonymous) {
        return !requirePrincipal || !anonymous || anonymousRowsets.contains(requestType);
    }

    public boolean allowsExecute(boolean anonymous) {
        return !requirePrincipal || !anonymous;
    }
}
