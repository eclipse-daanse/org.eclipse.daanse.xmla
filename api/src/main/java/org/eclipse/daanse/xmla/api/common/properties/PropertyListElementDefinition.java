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
package org.eclipse.daanse.xmla.api.common.properties;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public enum PropertyListElementDefinition {

    CONTENT("Content", Type.ENUM_STRING, EnumSet.allOf(Content.class), Access.WRITE,
            org.eclipse.daanse.xmla.api.common.properties.Content.SCHEMA_DATA.getValue(), true, true),

    DATA_SOURCE_INFO("DataSourceInfo", Type.STRING, null, Access.READ_WRITE, "Empty", true, true),

    FORMAT("Format", Type.ENUM_STRING, EnumSet.allOf(Format.class), Access.WRITE,
            org.eclipse.daanse.xmla.api.common.properties.Format.NATIVE.getValue(), true, true),

    LOCALE_IDENTIFIER("LocaleIdentifier", Type.UNSIGNED_INTEGER, null, Access.READ_WRITE, null, true, true);

    final Type type;
    final Set<? extends Enum> enumSet;
    final Access access;
    final boolean discover;
    final boolean execute;
    final String value;
    final String nameValue;

    PropertyListElementDefinition(String nameValue, Type type, Set<? extends Enum> enumSet, Access access, String value,
            boolean discover, boolean execute) {

        assert (enumSet != null) == type.isEnum();
        this.type = type;
        this.enumSet = enumSet;
        this.access = access;
        this.value = value;
        this.discover = discover;
        this.execute = execute;
        this.nameValue = nameValue;
    }

    public static Optional<PropertyListElementDefinition> byNameValue(String name) {
        return Stream.of(PropertyListElementDefinition.values()).filter(pd -> pd.nameValue.equalsIgnoreCase(name))
                .findAny();
    }
}
