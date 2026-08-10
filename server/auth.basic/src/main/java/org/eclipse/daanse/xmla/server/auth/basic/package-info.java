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
 * HTTP BASIC.
 * <p>
 * {@code BasicAuthenticator} decodes the {@code Authorization} header and hands
 * the credentials to whatever credential store the deployment registered. It is
 * here because it is what Analysis Services clients actually send, and it is
 * only honest over TLS.
 * <p>
 * See {@link org.eclipse.daanse.xmla.api.auth} for how a mechanism fits
 * together with the stores, the role providers and the chain.
 */
package org.eclipse.daanse.xmla.server.auth.basic;
