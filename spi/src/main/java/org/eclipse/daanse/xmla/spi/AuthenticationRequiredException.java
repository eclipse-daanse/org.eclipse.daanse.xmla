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
package org.eclipse.daanse.xmla.spi;

/**
 * The backend will not answer this request anonymously.
 * <p>
 * This is how "does this need a login?" stays the backend's decision without
 * the transport having to understand the SOAP body: the transport passes an
 * anonymous request through, and if the connector refuses it, the transport
 * answers {@code 401} with a {@code WWW-Authenticate} challenge for every
 * registered mechanism — or {@code 403} when there is none to offer. A client
 * probing {@code DISCOVER_PROPERTIES} before logging in never sees this; a
 * client asking for {@code MDSCHEMA_CUBES} without credentials sees exactly the
 * challenge it needs.
 * <p>
 * Thrown before the first byte of the response by construction: the connector
 * is called before the transport starts writing, so the 401 is always still
 * possible.
 */
public class AuthenticationRequiredException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthenticationRequiredException(String message) {
        super(message);
    }
}
