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
package org.eclipse.daanse.xmla.model.record.xmla;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.CellPermission;
import org.eclipse.daanse.xmla.api.xmla.CubeDimensionPermission;
import org.eclipse.daanse.xmla.api.xmla.CubePermission;
import org.eclipse.daanse.xmla.api.xmla.ReadDefinitionEnum;
import org.eclipse.daanse.xmla.api.xmla.ReadWritePermissionEnum;

public record CubePermissionR(Optional<String> readSourceData,
        Optional<List<CubeDimensionPermission>> dimensionPermissions, Optional<List<CellPermission>> cellPermissions,
        String name, Optional<String> id, Optional<Instant> createdTimestamp, Optional<Instant> lastSchemaUpdate,
        Optional<String> description, Optional<List<Annotation>> annotations, String roleID, Optional<Boolean> process,
        Optional<ReadDefinitionEnum> readDefinition, Optional<ReadWritePermissionEnum> read,
        Optional<ReadWritePermissionEnum> write) implements CubePermission {

}
