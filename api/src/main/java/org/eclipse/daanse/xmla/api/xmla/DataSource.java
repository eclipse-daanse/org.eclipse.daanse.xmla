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
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.eclipse.daanse.xmla.api.engine.ImpersonationInfo;

/**
 * The DataSource complex type represents a source of data for cubes, dimensions, and mining
 * structures. The following complex types extend this type: RelationalDataSource OlapDataSource
 */
public interface DataSource {

    /**
     * @return The object name.
     */
    String name();

    /**
     * @return The object ID string.
     */
    String id();

    /**
     * @return A timestamp that indicates the time that object was created.
     */
    Instant createdTimestamp();

    /**
     * @return A timestamp that indicates the time that the schema was last updated.
     */
    Instant lastSchemaUpdate();

    /**
     * @return The object description.
     */
    String description();

    /**
     * @return A collection of Annotation objects.
     */
    List<Annotation> annotations();

    /**
     * @return The managed provider name.
     */
    String managedProvider();

    /**
     * @return The connection string. The password can be stripped out, depending upon the setting of
     *         the ConnectionStringSecurity element.
     */
    String connectionString();

    /**
     * @return An enumeration value that specifies whether the user's password is stripped from the data
     *         source connection string for security purposes. The enumeration values are as follows:
     *         PasswordRemoved – The user’s password is stripped from the connection string. Unchanged -
     *         The connection string text is unchanged.
     */
    String connectionStringSecurity();

    /**
     * @return The user credentials that are used to connect to a data source.
     */
    ImpersonationInfo impersonationInfo();

    /**
     * @return An enumeration value that specifies the isolation level for reading data that was
     *         modified but not committed by another simultaneous transaction. The enumeration values
     *         are as follows: ReadCommitted – ReadCommitted isolation is used. Snapshot – Snapshot
     *         isolation is used.
     */
    String isolation();

    /**
     * @return The maximum number of concurrent connections to the data source. A negative number means
     *         that there is no limit. A value of zero means the default limit. default 10
     */
    BigInteger maxActiveConnections();

    /**
     * @return An integer that specifies the time, in seconds, after which an attempt to retrieve data
     *         reports a timeout.
     */
    Duration timeout();

    /**
     * @return A collection of objects of type DataSourcePermission.
     */
    List<DataSourcePermission> dataSourcePermissions();

    /**
     * @return The user credentials that are used to connect to a data source in DirectQuery mode. If
     *         not in DirectQuery mode, the value is ignored. If not provided, the credentials are
     *         obtained from the ImpersonationInfo element, also in this table.
     */
    ImpersonationInfo queryImpersonationInfo();

    /**
     * @return If provided, the query hint is appended to any query before the query is invoked in
     *         DirectQuery mode.
     */
    String queryHints();
}
