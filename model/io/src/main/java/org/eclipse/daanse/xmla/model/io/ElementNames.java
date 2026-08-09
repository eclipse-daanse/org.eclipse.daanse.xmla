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
 * escapes it ({@code _x005b_Measures_x005d_...}); the model documents this
 * contract on {@code RowsetColumn}, and this is its one implementation. The
 * fixed schema-rowset column names never need it - encoding those is a no-op by
 * construction.
 * <p>
 * {@link #normalizeNumericString(String)} is the trailing-zero normalisation
 * SSAS applies before writing a numeric cell value.
 */
public final class ElementNames {

    /** XML 1.0 Name production, one character at a time. */
    private static final String VALID_CHARACTERS_EXP = "^[:A-Z_a-zÀÖØ-öø-˿Ͱ-ͽ" + "Ϳ-῿‌‍⁰-↏Ⰰ-⿯、-퟿" + "豈-﷏ﷰ-�]"
            + "[:A-Z_a-zÀÖØ-ö" + "ø-˿Ͱ-ͽͿ-῿‌‍⁰-↏" + "Ⰰ-⿯、-\udfff豈-﷏ﷰ-�\\-\\.0-9" + "·̀-ͯ‿-⁀]*\\Z";
    private static final Pattern VALID_CHARACTERS = Pattern.compile(VALID_CHARACTERS_EXP);

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
        StringBuilder buf = new StringBuilder();
        for (char ch : name.toCharArray()) {
            if (VALID_CHARACTERS.matcher(String.valueOf(ch)).matches()) {
                buf.append(ch);
            } else {
                buf.append(encodeChar(ch));
            }
        }
        return buf.toString();
    }

    private static String encodeChar(char c) {
        StringBuilder buf = new StringBuilder();
        buf.append("_x");
        String str = Integer.toHexString(c);
        for (int i = 4 - str.length(); i > 0; i--) {
            buf.append("0");
        }
        return buf.append(str).append("_").toString();
    }
}
