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
 * The Permission complex type represents a set of permissions assigned to a Role.
 */
public interface Permission {

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
     * @return The ID of the Role for which permissions are being defined.
     */
    String roleID();

    /**
     * @return When true, specifies that the role has permission to process the object; otherwise,
     *         false. default false
     */
    Optional<Boolean> process();

    /**
     * @return A string that specifies whether the role has permission to read the XML definition of the
     *         object or any of its contained objects using DISCOVER_XML_METADATA. "None" implies no
     *         access to object definition. "Basic" implies limited access to object definition.
     *         "Allowed" implies full access to object definition.
     */
    Optional<ReadDefinitionEnum> readDefinition();

    /**
     * @return A string that specifies whether the role has permission to read metadata or data from the
     *         object or any of its contained objects. "None" implies no read access to object metadata
     *         or data.<99> "Allowed" implies full read access to object metadata or data.
     */
    Optional<ReadWritePermissionEnum> read();

    /**
     * @return A string that specifies whether the role has permission to write to the object or any of
     *         its contained objects. DatabasePermission, DataSourcePermission, and
     *         MiningStructurePermission cannot have Write="Allowed". Write cannot be set to "Allowed"
     *         unless Read is also set to "Allowed". "None" implies no write access to object metadata
     *         or data. "Allowed" implies full write access to object metadata or data.
     */
    Optional<ReadWritePermissionEnum> write();
}
