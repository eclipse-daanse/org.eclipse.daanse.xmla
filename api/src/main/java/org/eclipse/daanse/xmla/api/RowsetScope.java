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

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.eclipse.daanse.xmla.model.xmla.Discover;

/**
 * Everything one rowset call carries, built fresh per call - providers are
 * stateless.
 * <p>
 * The request as the transport read it, its restrictions through the last-wins
 * reader, the caller (principal, roles, session), the set of request types
 * currently served - what {@code DISCOVER_SCHEMA_ROWSETS} announces - and the
 * hosting connector's backend handle, through which a provider reaches whatever
 * the backend offers (catalogs, connections).
 *
 * @param <B> the backend handle the hosting connector passes
 */
public record RowsetScope<B>(Discover request, RestrictionValues restrictions, XmlaRequest caller, Set<String> served,
        B backend) {

    public RowsetScope {
        Objects.requireNonNull(request, "request must not be null");
        Objects.requireNonNull(restrictions, "restrictions must not be null");
        Objects.requireNonNull(caller, "caller must not be null");
        served = Set.copyOf(served);
    }

    public static <B> RowsetScope<B> of(Discover request, XmlaRequest caller, B backend, Set<String> served) {
        return new RowsetScope<>(request, new RestrictionValues(request), caller, served, backend);
    }

    /** The caller's session id, when the transport carried one. */
    public Optional<String> sessionId() {
        return Optional.ofNullable(caller.sessionId());
    }
}
