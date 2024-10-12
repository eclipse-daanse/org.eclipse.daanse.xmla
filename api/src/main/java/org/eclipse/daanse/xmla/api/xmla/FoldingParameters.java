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
 * This complex type represents the folding parameters for a MiningModel.
 */
public interface FoldingParameters {

    /**
     * @return An integer that indicates the index of the partition to be used for validating this
     *         mining model in a multifold cross-validation procedure.
     */
    Integer foldIndex();

    /**
     * @return An integer that indicates the number of partitions in the multifold cross-validation
     *         procedure.
     */
    Integer foldCount();

    /**
     * @return An integer value that indicates the maximum number of training cases to be used for
     *         cross-validation in this model. This value MUST be a positive integer. A value of 0
     *         indicates that all cases are used.
     */
    Optional<Long> foldMaxCases();

    /**
     * @return A string that indicates the ID of the model column that contains the predictable
     *         attribute.
     */
    Optional<String> foldTargetAttribute();
}
