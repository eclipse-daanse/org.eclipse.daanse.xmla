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
 * Who is calling, what they may see, and how the pieces fit.
 * <p>
 * <strong>The path a request takes.</strong> An
 * {@link org.eclipse.daanse.xmla.api.auth.AuthenticationChain} asks each
 * registered {@link org.eclipse.daanse.xmla.api.auth.XmlaAuthenticator} in turn
 * until one claims the request. A mechanism that needs a password hands it to
 * an {@link org.eclipse.daanse.xmla.api.auth.XmlaCredentials} store; whatever
 * it learned beyond the name travels as
 * {@link org.eclipse.daanse.xmla.api.auth.Claims}. The roles then come from
 * {@link org.eclipse.daanse.xmla.api.auth.RoleResolution}: every registered
 * {@link org.eclipse.daanse.xmla.api.auth.RoleProvider} is asked, the answers
 * are joined with whatever the mechanism read itself, and a
 * {@link org.eclipse.daanse.xmla.api.auth.RoleMapping} translates that one set
 * into the names a catalog defines. The result is an
 * {@link org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity}, and the
 * endpoint's access rule decides whether it is enough.
 * <p>
 * <strong>Anonymous passes through.</strong> A request nobody claims is not
 * challenged here. XMLA clients probe {@code DISCOVER_PROPERTIES} and
 * {@code DISCOVER_DATASOURCES} before they log in, so the challenge comes
 * later, when the endpoint refuses the anonymous request - which is what
 * {@code AuthenticationRequiredException} carries, and it flies before the
 * first response byte so the status line is still free to choose.
 * <p>
 * <strong>Order is a decision, not an accident.</strong> Declarative Services
 * binds a collection reference in whatever order services arrive, and the walk
 * stops at the first mechanism that claims a request - so the chain sorts by
 * {@code service.ranking} and
 * {@link org.eclipse.daanse.xmla.api.auth.AuthRanking} holds the shipped
 * values. A mechanism that names a caller without verifying anything answers
 * {@code Result.Fallback}, which the chain applies only after every other
 * mechanism has passed, and which never satisfies a rule that demands
 * authentication - see {@link org.eclipse.daanse.xmla.api.auth.IdentitySource}.
 * <p>
 * <strong>Claims are namespaced.</strong> A mechanism writes only under its own
 * namespace and a provider reads only its own; see
 * {@link org.eclipse.daanse.xmla.api.auth.AuthClaims}. The values come from
 * tokens and proxy headers, so a caller may be able to choose them, and without
 * the separation a token claim would be indistinguishable from a fact a
 * directory bind established.
 * <p>
 * <strong>Sessions carry identity, deliberately.</strong> The specification's
 * in-band {@code Authenticate} handshake binds to the connection ([MS-SSAS]
 * 3.2.2). Over HTTP there is no connection, so what the handshake established
 * is bound to the XMLA session instead and restored on every later request that
 * bears its id - see
 * {@link org.eclipse.daanse.xmla.api.auth.InbandAuthenticator} and
 * {@code XmlaSessionHandler}. How long that session lives is the session
 * handler's decision and nothing here has an opinion about it.
 */
@org.osgi.annotation.bundle.Export
@org.osgi.annotation.versioning.Version("2.0.0")
package org.eclipse.daanse.xmla.api.auth;
