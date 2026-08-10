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
 * OAuth and OIDC bearer tokens, validated in this process.
 * <p>
 * {@code BearerAuthenticator} checks a token's signature against the keys the
 * issuer publishes, and its issuer, audience, type and expiry, before anything
 * else happens. The token's own claims become the caller's claims, under this
 * mechanism's namespace.
 * <p>
 * See {@link org.eclipse.daanse.xmla.api.auth} for why that namespace matters
 * and how claims become roles.
 */
package org.eclipse.daanse.xmla.server.auth.oidc;
