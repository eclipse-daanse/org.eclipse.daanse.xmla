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

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

import java.util.stream.Stream;

/**
 * A hint to the client applications about when their data caches, if any, SHOULD<180> be refreshed
 * after a Refresh command changes the data on the server.
 */
@XmlType(name = "ClientCacheRefreshPolicy")
@XmlEnum
public enum ClientCacheRefreshPolicyEnum {

    /**
     * Client applications are notified to refresh their caches only if a user query/interaction needs
     * newer data.
     */
    @XmlEnumValue("0")
    REFRESH_NEWER_DATA(0),

    /**
     * (default) – Client applications are notified to allow all background cache refreshes.
     */
    @XmlEnumValue("1")
    ALL_CACHE_REFRESH(1);

    private final int value;

    ClientCacheRefreshPolicyEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static ClientCacheRefreshPolicyEnum fromValue(int v) {
        return Stream.of(ClientCacheRefreshPolicyEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("ClientCacheRefreshPolicyEnum Illegal argument ").append(v).toString()));
    }
}
