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
package org.eclipse.daanse.xmla.api.discover.dbschema.catalogs;

import java.time.LocalDateTime;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.ClientCacheRefreshPolicyEnum;
import org.eclipse.daanse.xmla.api.common.enums.TypeEnum;

public interface DbSchemaCatalogsResponseRow {

    /**
     * @return The catalog name.
     */
    Optional<String> catalogName();

    /**
     * @return The catalog description.
     */
    Optional<String> description();

    /**
     * @return A comma-delimited list of roles to which the current user belongs.
     */
    Optional<String> roles();

    /**
     * @return The date that the catalog was last modified.
     */
    Optional<LocalDateTime> dateModified();

    /**
     * @return The compatibility level of the database.
     */
    Optional<Integer> compatibilityLevel();

    /**
     * @return A mask with the following flags: (0x0) Multidimensional. If the other bits are not set,
     *         the database is a Multidimensional database. (0x1) TabularMetadata. The Tabular model is
     *         built by using Tabular metadata. (0x2) TabularModel. This is a Tabular model, including
     *         those built using Tabular or Multidimensional metadata.
     */
    Optional<TypeEnum> type();

    /**
     * @return A database that uses Tabular Metadata will return the current version of the database.
     *         For more details, see [MS-SSAS-T]. Otherwise, the value will be 0.
     */
    Optional<Integer> version();

    /**
     * @return The ID of the database object.
     */
    Optional<String> databaseId();

    /**
     * @deprecated
     * @return Unused
     */
    @Deprecated(since = "deprecated in specification")
    Optional<LocalDateTime> dateQueried();

    /**
     * @deprecated
     * @return Unused
     */
    @Deprecated(since = "deprecated in specification")
    Optional<Boolean> currentlyUsed();

    /**
     * @return A measure of how frequently the database is used. The value is empty for the system
     *         tracker.
     */
    Optional<Double> popularity();

    /**
     * @return A measure of how frequently the database is used, expressed as a fraction with respect to
     *         the other databases. The value is empty for the system tracker.
     */
    Optional<Double> weightedPopularity();

    /**
     * @return A hint to the client applications about when their data caches, if any, SHOULD<180> be
     *         refreshed after a Refresh command changes the data on the server. The possible values are
     *         as follows: 0 – Client applications are notified to refresh their caches only if a user
     *         query/interaction needs newer data. 1 (default) – Client applications are notified to
     *         allow all background cache refreshes.
     */
    Optional<ClientCacheRefreshPolicyEnum> clientCacheRefreshPolicy();
}
