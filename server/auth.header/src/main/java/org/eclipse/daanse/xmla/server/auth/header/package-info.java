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
 * Identity forwarded by a front that already authenticated the caller.
 * <p>
 * {@code TrustedHeaderAuthenticator} turns the headers Authelia, oauth2-proxy
 * and their kind forward into the identity, and asks for proof that they came
 * from the front: the peer address, matched by {@code PeerMatcher}, or a shared
 * secret only the front knows, or both. The address is unavailable behind a
 * container that has already processed forwarded headers; the secret also covers
 * the case the address cannot, namely a front that fails to strip the identity
 * headers from what reaches it.
 * <p>
 * See {@link org.eclipse.daanse.xmla.api.auth} for how the forwarded groups become catalog roles.
 */
package org.eclipse.daanse.xmla.server.auth.header;
