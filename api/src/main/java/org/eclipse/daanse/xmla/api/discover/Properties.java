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
package org.eclipse.daanse.xmla.api.discover;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.properties.AxisFormat;
import org.eclipse.daanse.xmla.api.common.properties.Content;
import org.eclipse.daanse.xmla.api.common.properties.Format;

public interface Properties {

    Optional<Integer> localeIdentifier();

    Optional<String> dataSourceInfo();

    Optional<Content> content();

    Optional<Format> format();

    Optional<String> catalog();

    Optional<AxisFormat> axisFormat();

    Optional<Boolean> deep();

    Optional<Boolean> emitInvisibleMembers();

    Optional<String> tableFields();

    Optional<Boolean> advancedFlag();
}
