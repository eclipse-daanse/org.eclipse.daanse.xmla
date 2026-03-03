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
package org.eclipse.daanse.xmla.model.record.discover.discover.literals;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.LiteralNameEnumValueEnum;
import org.eclipse.daanse.xmla.api.discover.discover.literals.DiscoverLiteralsResponseRow;

public record DiscoverLiteralsResponseRowR(String literalName, String literalValue, Optional<String> literalInvalidChars,
        Optional<String> literalInvalidStartingChars, Optional<Integer> literalMaxLength,
        Optional<LiteralNameEnumValueEnum> literalNameEnumValue)
        implements DiscoverLiteralsResponseRow {

}
