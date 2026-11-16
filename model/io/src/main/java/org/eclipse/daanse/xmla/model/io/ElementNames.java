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
package org.eclipse.daanse.xmla.model.io;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * The wire's name and number rules for tabular answers.
 * <p>
 * A column whose name is not a valid XML element name - a JDBC label, a member
 * unique name like {@code [Measures].[Profit]} - is hex-escaped the way SSAS
 * escapes it ({@code _x005B_Measures_x005D_._x005B_Profit_x005D_}): a character
 * is escaped only where it cannot stand, so a digit, a dot or a hyphen is kept
 * after the first character and escaped as the first; the colon is escaped
 * everywhere, an element name being a local name; an underscore only when an
 * escape follows it, so that decoding is unambiguous (.NET
 * {@code XmlConvert.EncodeLocalName}, which clients decode with). The model
 * documents this contract on {@code RowsetColumn}, and this is its one
 * implementation. The fixed schema-rowset column names never need it - encoding
 * those is a no-op by construction.
 * <p>
 * {@link #normalizeNumericString(String)} is the trailing-zero normalisation
 * SSAS applies before writing a numeric cell value.
 */
public final class ElementNames {

    /** An escape as a decoder reads it, after an underscore: {@code xHHHH_} or {@code xHHHHHHHH_}. */
    private static final Pattern ESCAPE_AHEAD = Pattern.compile("[Xx](?:[0-9a-fA-F]{4}|[0-9a-fA-F]{8})_");

    /** Caches the encoding, because the same names recur in every row. */
    private static final Map<String, String> ENCODED = new ConcurrentHashMap<>();

    private ElementNames() {
        // static access only
    }

    public static String encode(String name) {
        return ENCODED.computeIfAbsent(name, ElementNames::encodeElementName);
    }

    /**
     * Strips a numeric string of trailing zeros right of the decimal point, and of
     * the point itself when nothing remains behind it. Exponential notation is left
     * alone: the zero of {@code 1.0E10} is not trailing.
     */
    public static String normalizeNumericString(String numericStr) {
        int index = numericStr.indexOf('.');
        if (index > 0) {
            if ((numericStr.indexOf('e') != -1) || (numericStr.indexOf('E') != -1)) {
                return numericStr;
            }
            boolean found = false;
            int p = numericStr.length();
            char c = numericStr.charAt(p - 1);
            while (c == '0') {
                found = true;
                p--;
                c = numericStr.charAt(p - 1);
            }
            if (c == '.') {
                p--;
            }
            if (found) {
                return numericStr.substring(0, p);
            }
        }
        return numericStr;
    }

    private static String encodeElementName(String name) {
        StringBuilder buf = new StringBuilder(name.length() + 16);
        int i = 0;
        while (i < name.length()) {
            int codePoint = name.codePointAt(i);
            int width = Character.charCount(codePoint);
            boolean first = i == 0;
            if (codePoint == '_' && ESCAPE_AHEAD.matcher(name).region(i + 1, name.length()).lookingAt()) {
                buf.append(escape(codePoint));
            } else if (codePoint != ':' && (first ? isNameStartChar(codePoint) : isNameChar(codePoint))) {
                buf.appendCodePoint(codePoint);
            } else {
                buf.append(escape(codePoint));
            }
            i += width;
        }
        return buf.toString();
    }

    /** {@code _xHHHH_} with upper-case digits, eight of them beyond the basic plane. */
    private static String escape(int codePoint) {
        return String.format(java.util.Locale.ROOT, codePoint > 0xFFFF ? "_x%08X_" : "_x%04X_", codePoint);
    }

    /** XML 1.0 (fifth edition) {@code NameStartChar}. */
    static boolean isNameStartChar(int c) {
        return c == ':' || c >= 'A' && c <= 'Z' || c == '_' || c >= 'a' && c <= 'z' || c >= 0xC0 && c <= 0xD6
                || c >= 0xD8 && c <= 0xF6 || c >= 0xF8 && c <= 0x2FF || c >= 0x370 && c <= 0x37D
                || c >= 0x37F && c <= 0x1FFF || c >= 0x200C && c <= 0x200D || c >= 0x2070 && c <= 0x218F
                || c >= 0x2C00 && c <= 0x2FEF || c >= 0x3001 && c <= 0xD7FF || c >= 0xF900 && c <= 0xFDCF
                || c >= 0xFDF0 && c <= 0xFFFD || c >= 0x10000 && c <= 0xEFFFF;
    }

    /** XML 1.0 (fifth edition) {@code NameChar}. */
    static boolean isNameChar(int c) {
        return isNameStartChar(c) || c == '-' || c == '.' || c >= '0' && c <= '9' || c == 0xB7
                || c >= 0x300 && c <= 0x36F || c >= 0x203F && c <= 0x2040;
    }
}
