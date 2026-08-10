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
 * Users, passwords and roles from the deployment's own configuration.
 * <p>
 * {@code InternalUserStore} answers both the password question and the role
 * question, registered separately so either can be combined with another
 * source. {@code PasswordHash} is the stored form - PBKDF2 with a per-user salt
 * - and produces the encoded value a configuration carries.
 * <p>
 * See {@link org.eclipse.daanse.xmla.api.auth} for how the two questions
 * compose.
 */
package org.eclipse.daanse.xmla.server.auth.store.internal;
