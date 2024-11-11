/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.api;

public enum VarType {
            Empty("Uninitialized (default)"),
            Null("Contains no valid data"),
            Integer("Integer subtype"),
            Long("Long subtype"),
            Single("Single subtype"),
            Double("Double subtype"),
            Currency("Currency subtype"),
            Date("Date subtype"),
            String("String subtype"),
            Object("Object subtype"),
            Error("Error subtype"),
            Boolean("Boolean subtype"),
            Variant("Variant subtype"),
            DataObject("DataObject subtype"),
            Decimal("Decimal subtype"),
            Byte("Byte subtype"),
            Array("Array subtype");

            public static VarType forCategory(String category) {
                switch (category) {
                case "unknown":
                    // expression == unknown ???
                    // case Category.Expression:
                    return Empty;
                case "array":
                    return Array;
                case "dimension",
                "hierarchy",
                "level",
                "member",
                "set",
                "tuple",
                "cube",
                "value":
                    return Variant;
                case "logical":
                    return Boolean;
                case "numeric":
                    return Double;
                case "string", "symbol":
                    return String;
                case "datetime":
                    return Date;
                case "integer":
                    return Integer;
                default:
                    break;
                }
                // NOTE: this should never happen
                return Empty;
            }

            VarType(String description) {

            }
    }
