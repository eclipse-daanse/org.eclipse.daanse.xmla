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
 * Passwords and groups from LDAP or Active Directory.
 * <p>
 * {@code LdapCredentials} checks a password by binding as the user, which is
 * the check itself; {@code LdapRoleProvider} reads that caller's groups. They
 * are separate services, so a deployment may use either alone.
 * {@code Directory} opens the connection, {@code TlsMode} decides how it is
 * protected, and {@code LdapNames} escapes every caller-supplied value that
 * reaches a filter or a distinguished name.
 * <p>
 * See {@link org.eclipse.daanse.xmla.api.auth} for how the groups become
 * catalog roles.
 */
package org.eclipse.daanse.xmla.server.auth.store.ldap;
