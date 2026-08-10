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
 * Integrated authentication: Kerberos and SPNEGO, on both channels.
 * <p>
 * {@code GssAuthenticator} serves the specification's in-band
 * {@code Authenticate} handshake and HTTP {@code Negotiate} from one
 * {@code GssAcceptor}, so a deployment configures its service principal once.
 * The two channels differ in what may identify a handshake across requests, and
 * that is why only the in-band one may span several rounds.
 * <p>
 * See {@link org.eclipse.daanse.xmla.api.auth} for how the established name
 * becomes roles.
 */
package org.eclipse.daanse.xmla.server.auth.gss;
