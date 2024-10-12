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

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.AttributePermission;
import org.eclipse.daanse.xmla.api.xmla.CubeDimensionPermission;
import org.eclipse.daanse.xmla.api.xmla.ReadWritePermissionEnum;

public record CubeDimensionPermissionR(String cubeDimensionID, Optional<String> description,
        Optional<ReadWritePermissionEnum> read, Optional<ReadWritePermissionEnum> write,
        Optional<List<AttributePermission>> attributePermissions, Optional<List<Annotation>> annotations)
        implements CubeDimensionPermission {

}
