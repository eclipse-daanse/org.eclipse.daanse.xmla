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
package org.eclipse.daanse.xmla.server.auth.store.ldap;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.StartTlsRequest;
import javax.naming.ldap.StartTlsResponse;

/**
 * How this bundle opens a connection to the directory.
 * <p>
 * Two kinds of bind happen here and they must not be confused: the service
 * bind, which searches, and the user bind, which is the password check itself.
 * A search performed as the user would answer differently depending on who
 * asks; a password checked with the service account would not be checked at
 * all.
 */
final class Directory {

    private Directory() {
        // static access only
    }

    /** How the directory is reached, decided once when the component comes up. */
    record Settings(String url, TlsMode tls, int connectTimeoutMillis, int readTimeoutMillis, String referral,
            String bindDn, String bindPassword) {
    }

    /**
     * An open context, together with whatever has to be unwound before it closes.
     */
    record Connection(DirContext context, StartTlsResponse tls) implements AutoCloseable {

        @Override
        public void close() {
            if (tls != null) {
                try {
                    tls.close();
                } catch (IOException ignored) {
                    // the connection is being dropped anyway
                }
            }
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException ignored) {
                    // a context that will not close is already unusable
                }
            }
        }
    }

    /**
     * A context bound as the service account, or anonymously when none is
     * configured. Used for searching only.
     */
    static Connection asService(Settings settings) throws NamingException {
        boolean anonymous = settings.bindDn() == null || settings.bindDn().isBlank();
        // Pooling is safe here and only here: the JNDI pool keys on the bound principal
        // and its credentials, so pooling a per-user bind would keep every caller's
        // password in memory for the lifetime of the pool.
        return open(settings, anonymous ? null : settings.bindDn(),
                anonymous ? null : settings.bindPassword().toCharArray(), !anonymous);
    }

    /**
     * A context bound as the user. Opening it <em>is</em> the password check: the
     * directory refuses the bind when the password is wrong.
     *
     * @throws NamingException if the password is empty - LDAP would read that as a
     *                         request for an anonymous bind and report success,
     *                         which would be an authentication bypass
     */
    static Connection asUser(Settings settings, String userDn, char[] password) throws NamingException {
        if (password == null || password.length == 0) {
            throw new javax.naming.AuthenticationException("an empty password is not a password");
        }
        return open(settings, userDn, password, false);
    }

    private static Connection open(Settings settings, String principal, char[] credentials, boolean pooled)
            throws NamingException {
        Hashtable<String, Object> environment = base(settings);
        if (settings.tls() == TlsMode.STARTTLS) {
            // The upgrade has to happen before any credential travels, so the context is
            // opened unauthenticated and the bind follows on the protected connection.
            // Pooling is incompatible with that and is simply not used.
            return startTls(environment, principal, credentials);
        }
        if (pooled) {
            environment.put("com.sun.jndi.ldap.connect.pool", "true");
        }
        bind(environment, principal, credentials);
        return new Connection(new InitialLdapContext(environment, null), null);
    }

    private static Connection startTls(Hashtable<String, Object> environment, String principal, char[] credentials)
            throws NamingException {
        LdapContext context = new InitialLdapContext(environment, null);
        StartTlsResponse tls = null;
        try {
            tls = (StartTlsResponse) context.extendedOperation(new StartTlsRequest());
            tls.negotiate();
            if (principal == null) {
                context.addToEnvironment(Context.SECURITY_AUTHENTICATION, "none");
            } else {
                context.addToEnvironment(Context.SECURITY_AUTHENTICATION, "simple");
                context.addToEnvironment(Context.SECURITY_PRINCIPAL, principal);
                context.addToEnvironment(Context.SECURITY_CREDENTIALS, new String(credentials));
            }
            context.reconnect(null);
            return new Connection(context, tls);
        } catch (IOException | NamingException | RuntimeException failed) {
            new Connection(context, tls).close();
            if (failed instanceof NamingException naming) {
                throw naming;
            }
            if (failed instanceof RuntimeException runtime) {
                throw runtime;
            }
            throw new javax.naming.CommunicationException("StartTLS was refused: " + failed.getMessage());
        }
    }

    private static void bind(Hashtable<String, Object> environment, String principal, char[] credentials) {
        if (principal == null) {
            environment.put(Context.SECURITY_AUTHENTICATION, "none");
            return;
        }
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
        environment.put(Context.SECURITY_PRINCIPAL, principal);
        environment.put(Context.SECURITY_CREDENTIALS, new String(credentials));
    }

    private static Hashtable<String, Object> base(Settings settings) {
        Hashtable<String, Object> environment = new Hashtable<>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, settings.url());
        // Without these a directory that accepts the connection and then says nothing
        // holds the request thread for good, which an unauthenticated caller can
        // trigger.
        environment.put("com.sun.jndi.ldap.connect.timeout", Integer.toString(settings.connectTimeoutMillis()));
        environment.put("com.sun.jndi.ldap.read.timeout", Integer.toString(settings.readTimeoutMillis()));
        // A subtree search against Active Directory routinely returns referrals, and
        // JNDI's default is to throw at the end of the enumeration rather than say so.
        environment.put(Context.REFERRAL, settings.referral());
        if (settings.tls() == TlsMode.LDAPS) {
            environment.put(Context.SECURITY_PROTOCOL, "ssl");
        }
        return environment;
    }
}
