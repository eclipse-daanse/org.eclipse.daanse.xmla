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
 * A configured identity for callers nothing identified.
 * <p>
 * {@code FixedIdentityAuthenticator} verifies nothing. It exists so a
 * development or demonstration endpoint can exercise everything that depends on
 * who is calling. It answers {@code Result.Fallback}, so it can never displace
 * a mechanism that would have authenticated the caller for real, and it
 * requires a configuration that says out loud that the endpoint is unprotected.
 * <p>
 * See {@link org.eclipse.daanse.xmla.api.auth} for the rules a fallback
 * identity is subject to.
 */
package org.eclipse.daanse.xmla.server.auth.dummy;
