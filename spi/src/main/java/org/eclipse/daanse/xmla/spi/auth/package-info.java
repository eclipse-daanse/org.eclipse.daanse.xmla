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
/**
 * Pluggable authentication, on both sides of the SOAP boundary.
 * <p>
 * {@link org.eclipse.daanse.xmla.spi.auth.XmlaAuthenticator} is the HTTP side —
 * Basic, SPNEGO, Bearer, or a trusted proxy header.
 * {@link org.eclipse.daanse.xmla.spi.auth.InbandAuthenticator} is the
 * specification's own {@code Authenticate} handshake inside the SOAP body.
 * Neither is required: with nothing registered the endpoint runs fully
 * anonymous, and whether anonymous is enough for a given rowset is the
 * connector's decision, not the transport's.
 */
@org.osgi.annotation.bundle.Export
@org.osgi.annotation.versioning.Version("1.0.0")
package org.eclipse.daanse.xmla.spi.auth;
