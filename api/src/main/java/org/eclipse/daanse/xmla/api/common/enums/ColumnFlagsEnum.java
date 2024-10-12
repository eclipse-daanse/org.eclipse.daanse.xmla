/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.api.common.enums;

import java.util.stream.Stream;

public enum ColumnFlagsEnum {

    /**
     * Set if the column is a bookmark.
     */
    DBCOLUMNFLAGS_ISBOOKMARK(0x1),

    /**
     * Set if the column is deferred.
     */
    DBCOLUMNFLAGS_MAYDEFER(0x2),

    /**
     * Set if the OLEDB interface IRowsetChange:SetData can be called.
     */
    DBCOLUMNFLAGS_WRITE(0x4),

    /**
     * – Set if the column can be updated through some means, but the means is unknown.
     */
    DBCOLUMNFLAGS_WRITEUNKNOWN(0x8),

    /**
     * Set if all data in the column has the same length.
     */
    DBCOLUMNFLAGS_ISFIXEDLENGTH(0x10),

    /**
     * Set if consumer can set the column to NULL or if the provider cannot determine if the column can
     * be set to NULL.
     */
    DBCOLUMNFLAGS_ISNULLABLE(0x20),

    /**
     * Set if the column can contain NULL values, or if the provider cannot guarantee that the column
     * cannot contain NULL values.
     */
    DBCOLUMNFLAGS_MAYBENULL(0x40),

    /**
     * Set if the column contains a BLOB that contains very long data.
     */
    DBCOLUMNFLAGS_ISLONG(0x80),

    /**
     * Set if the column contains a persistent row identifier that cannot be written to and has no
     * meaningful value except to identify the row.
     */
    DBCOLUMNFLAGS_ISROWID(0x100),

    /**
     * Set if the column contains a timestamp or other versioning mechanism that cannot be written to
     * directly and that is automatically updated to a new increasing value when the row is updated or
     * committed.
     */
    DBCOLUMNFLAGS_ISROWVER(0x200),

    /**
     * Set if when a deferred column is first read its value the column is cached by the provider.
     */
    DBCOLUMNFLAGS_CACHEDEFERRED(0x1000);

    private final int value;

    ColumnFlagsEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static ColumnFlagsEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        int vi = Integer.decode(v);
        return Stream.of(ColumnFlagsEnum.values()).filter(e -> (e.getValue() == vi)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("ColumnFlagsEnum Illegal argument ").append(v).toString()));
    }
}
