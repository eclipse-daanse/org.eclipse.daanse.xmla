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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums;

import java.util.stream.Stream;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "ColumnFlags")
@XmlEnum
public enum ColumnFlagsEnum {

    /**
     * Set if the column is a bookmark.
     */
    @XmlEnumValue("0x1")
    DBCOLUMNFLAGS_ISBOOKMARK(0x1),

    /**
     * Set if the column is deferred.
     */
    @XmlEnumValue("0x2")
    DBCOLUMNFLAGS_MAYDEFER(0x2),

    /**
     * Set if the OLEDB interface IRowsetChange:SetData can be called.
     */
    @XmlEnumValue("0x4")
    DBCOLUMNFLAGS_WRITE(0x4),

    /**
     * Set if the column can be updated through some means, but the means is unknown.
     */
    @XmlEnumValue("0x8")
    DBCOLUMNFLAGS_WRITEUNKNOWN(0x8),

    /**
     * Set if all data in the column has the same length.
     */
    @XmlEnumValue("0x10")
    DBCOLUMNFLAGS_ISFIXEDLENGTH(0x10),

    /**
     * Set if consumer can set the column to NULL or if the provider cannot determine if the column can
     * be set to NULL.
     */
    @XmlEnumValue("0x20")
    DBCOLUMNFLAGS_ISNULLABLE(0x20),

    /**
     * Set if the column can contain NULL values, or if the provider cannot guarantee that the column
     * cannot contain NULL values.
     */
    @XmlEnumValue("0x40")
    DBCOLUMNFLAGS_MAYBENULL(0x40),

    /**
     * Set if the column contains a BLOB that contains very long data.
     */
    @XmlEnumValue("0x80")
    DBCOLUMNFLAGS_ISLONG(0x80),

    /**
     * Set if the column contains a persistent row identifier that cannot be written to and has no
     * meaningful value except to identify the row.
     */
    @XmlEnumValue("0x100")
    DBCOLUMNFLAGS_ISROWID(0x100),

    /**
     * Set if the column contains a timestamp or other versioning mechanism that cannot be written to
     * directly and that is automatically updated to a new increasing value when the row is updated or
     * committed.
     */
    @XmlEnumValue("0x200")
    DBCOLUMNFLAGS_ISROWVER(0x200),

    /**
     * Set if when a deferred column is first read its value the column is cached by the provider.
     */
    @XmlEnumValue("0x1000")
    DBCOLUMNFLAGS_CACHEDEFERRED(0x1000);

    private final int value;

    ColumnFlagsEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static ColumnFlagsEnum fromValue(int v) {
        return Stream.of(ColumnFlagsEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("ColumnFlagsEnum Illegal argument ").append(v).toString()));
    }
}
