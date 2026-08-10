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
package org.eclipse.daanse.xmla.api.auth;

/**
 * The claim vocabulary the shipped mechanisms and providers share. In the API rather
 * than in the bundles so a deployment's own {@link RoleMapping} or {@link RoleProvider}
 * can speak it without depending on an implementation.
 * <p>
 * Every claim is written under the namespace of the mechanism that learned it, and a
 * provider reads only its own. That is a security rule, not tidiness: claim values are
 * attacker-influenceable, and without it a token claim named {@code dn} would be
 * indistinguishable from a distinguished name a directory bind established, and could
 * steer a group lookup at any entry the caller names.
 */
public final class AuthClaims {

    /** Claims copied verbatim out of a validated JWT. */
    public static final String NS_JWT = "jwt";

    /** Claims a trusted front asserted in HTTP headers. */
    public static final String NS_HEADER = "header";

    /** What a directory bind or search established. */
    public static final String NS_LDAP = "ldap";

    /** What the GSS-API context established. */
    public static final String NS_GSS = "gss";

    /** What the deployment's own user store holds. */
    public static final String NS_STORE = "store";

    /** Group names as the identity provider spells them. */
    public static final String GROUPS = "groups";

    /** Role names as the identity provider spells them. */
    public static final String ROLES = "roles";

    /** A distinguished name. */
    public static final String DN = "dn";

    /** The stable subject identifier. */
    public static final String SUBJECT = "sub";

    /**
     * Where the directory found this caller; the one key {@code LdapRoleProvider}
     * steers on.
     */
    public static final String LDAP_DN = NS_LDAP + ":" + DN;

    private AuthClaims() {
        // static access only
    }
}
