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
package org.eclipse.daanse.xmla.api.xmla;

import java.util.List;
import java.util.Optional;

/**
 * This complex type represents a single group within a UserDefinedGroupBinding.
 */
public interface Group {

    /**
     * @return Name of the grouping member.
     */
    String name();

    /**
     * @return A collection of strings that contain MDX expressions that identify the set of members to
     *         be grouped.
     */
    Optional<List<String>> members();
}
