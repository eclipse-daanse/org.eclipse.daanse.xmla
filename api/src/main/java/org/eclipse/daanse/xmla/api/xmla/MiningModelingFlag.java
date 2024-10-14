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

import java.util.Optional;

/**
 * This complex type represents a flag for a MiningModel algorithm. The flags that can be accepted
 * depend upon the algorithm used in the MiningModel.
 */
public interface MiningModelingFlag {

    /**
     * @return A flag that is passed to a mining model algorithm. The form and content of flags is
     *         specific to each algorithm. Each ModelingFlag needs to be valid for the algorithm
     *         chosen. Server vendors can define flags that support their algorithms.
     */
    Optional<String> modelingFlag();

}
