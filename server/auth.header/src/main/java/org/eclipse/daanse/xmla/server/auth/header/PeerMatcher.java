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
package org.eclipse.daanse.xmla.server.auth.header;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Whether a request came from an address a deployment named.
 * <p>
 * Comparing the strings does not work. A container reports loopback as
 * {@code 0:0:0:0:0:0:0:1} while an operator writes {@code ::1}; a dual-stack
 * socket reports {@code ::ffff:10.0.0.5} for a peer the operator wrote as
 * {@code 10.0.0.5}; a link-local address carries a zone suffix. Each of those
 * silently makes a trusted front untrusted, and the failure is invisible - the
 * request just falls through to anonymous. So addresses are compared as
 * addresses, and a range may be written as one.
 */
final class PeerMatcher {

    private final List<Entry> entries;

    private record Entry(byte[] address, int prefixBits) {

        boolean matches(byte[] peer) {
            if (peer.length != address.length) {
                return false;
            }
            int whole = prefixBits / 8;
            for (int index = 0; index < whole; index++) {
                if (peer[index] != address[index]) {
                    return false;
                }
            }
            int remaining = prefixBits % 8;
            if (remaining == 0) {
                return true;
            }
            int mask = 0xFF << (8 - remaining);
            return (peer[whole] & mask) == (address[whole] & mask);
        }
    }

    private PeerMatcher(List<Entry> entries) {
        this.entries = entries;
    }

    /**
     * @param configured addresses, host names, or CIDR ranges such as
     *                   {@code 10.0.0.0/8}
     * @throws IllegalArgumentException if an entry cannot be resolved, so a typo
     *                                  fails the component rather than quietly
     *                                  trusting nobody
     */
    static PeerMatcher of(String[] configured) {
        List<Entry> entries = new ArrayList<>();
        for (String entry : configured) {
            if (entry == null || entry.isBlank()) {
                continue;
            }
            entries.add(parse(entry.trim()));
        }
        return new PeerMatcher(List.copyOf(entries));
    }

    private static Entry parse(String entry) {
        int slash = entry.lastIndexOf('/');
        String host = slash < 0 ? entry : entry.substring(0, slash);
        InetAddress address = resolve(host);
        byte[] bytes = address.getAddress();
        if (slash < 0) {
            return new Entry(bytes, bytes.length * 8);
        }
        int prefix;
        try {
            prefix = Integer.parseInt(entry.substring(slash + 1).trim());
        } catch (NumberFormatException notANumber) {
            throw new IllegalArgumentException("the trusted upstream '" + entry + "' has no readable prefix length");
        }
        if (prefix < 0 || prefix > bytes.length * 8) {
            throw new IllegalArgumentException("the trusted upstream '" + entry + "' has a prefix length outside "
                    + "what its address family allows");
        }
        return new Entry(bytes, prefix);
    }

    private static InetAddress resolve(String host) {
        try {
            return InetAddress.getByName(stripZone(host));
        } catch (UnknownHostException unresolvable) {
            throw new IllegalArgumentException("the trusted upstream '" + host + "' cannot be resolved", unresolvable);
        }
    }

    /** A zone suffix names an interface on this host and cannot identify a peer. */
    private static String stripZone(String host) {
        int zone = host.indexOf('%');
        return zone < 0 ? host : host.substring(0, zone);
    }

    boolean isEmpty() {
        return entries.isEmpty();
    }

    boolean matches(String peer) {
        if (peer == null || peer.isBlank() || entries.isEmpty()) {
            return false;
        }
        InetAddress address;
        try {
            address = InetAddress.getByName(stripZone(peer.trim()));
        } catch (UnknownHostException unreadable) {
            return false;
        }
        byte[] bytes = address.getAddress();
        for (Entry entry : entries) {
            if (entry.matches(bytes) || entry.matches(alternateFamily(bytes))) {
                return true;
            }
        }
        return false;
    }

    /**
     * The same address in the other family, so an IPv4-mapped peer on a dual-stack
     * socket matches an entry written as plain IPv4.
     *
     * @return an empty array when there is no equivalent, which matches nothing
     */
    private static byte[] alternateFamily(byte[] address) {
        if (address.length == 16 && isV4Mapped(address)) {
            return new byte[] { address[12], address[13], address[14], address[15] };
        }
        if (address.length == 4) {
            byte[] mapped = new byte[16];
            mapped[10] = (byte) 0xFF;
            mapped[11] = (byte) 0xFF;
            System.arraycopy(address, 0, mapped, 12, 4);
            return mapped;
        }
        return new byte[0];
    }

    private static boolean isV4Mapped(byte[] address) {
        for (int index = 0; index < 10; index++) {
            if (address[index] != 0) {
                return false;
            }
        }
        return address[10] == (byte) 0xFF && address[11] == (byte) 0xFF;
    }
}
