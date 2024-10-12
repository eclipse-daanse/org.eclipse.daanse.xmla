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

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * This complex type represents a mining structure.
 */
public interface MiningStructure {

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
     * @return The source for the MiningStructure data. Source is of type "Binding". One of the
     *         following derived classes MUST be used: DataSourceViewBinding, DimensionBinding, or
     *         CubeDimensionBinding. If DimensionBinding or CubeDimensionBinding is used, then the
     *         DataSourceID for DimensionBinding and CubeDimensionBinding MUST indicate an OLAP source.
     */
    Optional<Binding> source();

    /**
     * @return The date and time when the mining structure was last processed.
     */
    Optional<Instant> lastProcessed();

    /**
     * @return A collection of Translation objects.
     */
    Optional<List<Translation>> translations();

    /**
     * @return The LCID of the language to use by default. See [MS-LCID] for information about LCIDs. If
     *         empty, the server will determine the language to use.<80>
     */
    Optional<BigInteger> language();

    /**
     * @return The collation of this MiningStructure.
     */
    Optional<String> collation();

    /**
     * @return Error configuration settings to deal with issues in the source data.
     */
    Optional<ErrorConfiguration> errorConfiguration();

    /**
     * @return Determines caching mechanism for training data retrieved during mining structure
     *         processing.
     */
    Optional<String> cacheMode();

    /**
     * @return An integer value between 0 and 99 that specifies the maximum percentage of the cases that
     *         are to be held out as the test set. The remaining cases become the training data set.
     *         Zero indicates no limit.
     */
    Optional<Integer> holdoutMaxPercent();

    /**
     * @return An integer value equal to or greater than zero that specifies the maximum number of cases
     *         that are to be held out as the test set. The remaining cases become the training data
     *         set. Zero indicates no limit. If not zero, then the lowest of (HoldoutMaxCases,
     *         HoldoutMaxPercent) is used.
     */
    Optional<Integer> holdoutMaxCases();

    /**
     * @return Used as the seed for repeatable partitioning. If unspecified or set to zero, a hash of
     *         the mining structure name is used as the seed.
     */
    Optional<Integer> holdoutSeed();

    /**
     * @return If the mining structure is processed, this indicates the actual size of the test data
     *         set, expressed in number of cases. Zero indicates either no test partition or that the
     *         structure is not processed.
     */
    Optional<Integer> holdoutActualSize();

    /**
     * @return A collection of Column objects for MiningStructure.
     */
    List<MiningStructureColumn> columns();

    /**
     * @return The state of processing of the object.
     */
    Optional<String> state();

    /**
     * @return A collection of MiningStructurePermission objects. Each MiningStructurePermission defines
     *         the permissions a role has on this MiningStructure.
     */
    Optional<List<MiningStructurePermission>> miningStructurePermissions();

    /**
     * @return A collection of MiningModel objects.
     */
    Optional<List<MiningModel>> miningModels();
}
