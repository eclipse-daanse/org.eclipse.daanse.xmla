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

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * This complex type represents a mining model.
 */
public interface MiningModel {

    /**
     * @return The object name.
     */
    String name();

    /**
     * @return The object ID string.
     */
    Optional<String> id();

    /**
     * @return A timestamp for the time that the object was created.
     */
    Optional<Instant> createdTimestamp();

    /**
     * @return A timestamp for the time that the schema was last updated.
     */
    Optional<Instant> lastSchemaUpdate();

    /**
     * @return The object description.
     */
    Optional<String> description();

    /**
     * @return A collection of Annotation objects.
     */
    Optional<List<Annotation>> annotations();

    /**
     * @return The protocol does not require any particular algorithms to be supported, and each server
     *         developer can support whichever algorithms he or she chooses to support.
     */
    String algorithm();

    /**
     * @return The date and time when the mining model was last processed.
     */
    Optional<Instant> lastProcessed();

    /**
     * @return A collection of objects of type AlgorithmParameter. The allowed parameters are different
     *         depending on the algorithm.
     */
    Optional<List<AlgorithmParameter>> algorithmParameters();

    /**
     * @return When true, indicates that drillthrough is allowed; otherwise, false.
     */
    Optional<Boolean> allowDrillThrough();

    /**
     * @return A collection of Translation objects.
     */
    Optional<List<AttributeTranslation>> translations();

    /**
     * @return A collection of objects of type MiningModelColumn.
     */
    Optional<List<MiningModelColumn>> columns();

    /**
     * @return Represents the processing state of the partition. Values include:
     *
     *         Processed Unprocessed
     */
    Optional<String> state();

    /**
     * @return An object of type FoldingParameters. Describes a fold (a partition of the training data)
     *         to be used for training this mining model. Used only as part of the multifold cross-
     *         validation procedure.
     */
    Optional<FoldingParameters> foldingParameters();

    /**
     * @return The DMX filter statement to be applied to training data for models that are trained only
     *         on a part of a structure's data. An empty string or missing element implies no filter.
     */
    Optional<String> filter();

    /**
     * @return A collection of MiningModelPermission objects.
     */
    Optional<List<MiningModelPermission>> miningModelPermissions();

    /**
     * @return The language to use by default.
     */
    Optional<String> language();

    /**
     * @return The collation sequence to use.
     */
    Optional<String> collation();
}
