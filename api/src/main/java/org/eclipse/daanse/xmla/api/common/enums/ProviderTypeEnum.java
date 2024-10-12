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

import org.eclipse.daanse.xmla.api.annotation.Enumerator;

import java.util.stream.Stream;

@Enumerator(name = "ProviderType")
public enum ProviderTypeEnum {

    /**
     * multidimensional data provider.
     */
    MDP,

    /**
     * tabular data provider.
     */
    TDP,

    /**
     * data mining provider (implements the OLE for DB for Data Mining specification)
     */
    DMP;

    public static ProviderTypeEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        return Stream.of(ProviderTypeEnum.values()).filter(e -> (e.name().equals(v))).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("ProviderTypeEnum Illegal argument ").append(v).toString()));
    }
}
