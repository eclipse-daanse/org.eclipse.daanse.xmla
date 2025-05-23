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

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

import org.eclipse.daanse.xmla.api.engine300_300.Relationships;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.Binding;
import org.eclipse.daanse.xmla.api.xmla.Dimension;
import org.eclipse.daanse.xmla.api.xmla.DimensionAttribute;
import org.eclipse.daanse.xmla.api.xmla.DimensionCurrentStorageModeEnumType;
import org.eclipse.daanse.xmla.api.xmla.DimensionPermission;
import org.eclipse.daanse.xmla.api.xmla.ErrorConfiguration;
import org.eclipse.daanse.xmla.api.xmla.Hierarchy;
import org.eclipse.daanse.xmla.api.xmla.ProactiveCaching;
import org.eclipse.daanse.xmla.api.xmla.Translation;
import org.eclipse.daanse.xmla.api.xmla.UnknownMemberEnumType;

public record DimensionR(String name, String id, Instant createdTimestamp, Instant lastSchemaUpdate, String description,
        List<Annotation> annotations, Binding source, String miningModelID, String type,
        Dimension.UnknownMember unknownMember, String mdxMissingMemberMode, ErrorConfiguration errorConfiguration,
        String storageMode, Boolean writeEnabled, BigInteger processingPriority, Instant lastProcessed,
        List<DimensionPermission> dimensionPermissions, String dependsOnDimensionID, BigInteger language,
        String collation, String unknownMemberName, List<Translation> unknownMemberTranslations, String state,
        ProactiveCaching proactiveCaching, String processingMode, String processingGroup,
        Dimension.CurrentStorageMode currentStorageMode, List<Translation> translations,
        List<DimensionAttribute> attributes, String attributeAllMemberName,
        List<Translation> attributeAllMemberTranslations, List<Hierarchy> hierarchies, String processingRecommendation,
        Relationships relationships, Integer stringStoresCompatibilityLevel,
        Integer currentStringStoresCompatibilityLevel) implements Dimension {

    public record CurrentStorageMode(DimensionCurrentStorageModeEnumType value,

            String valuens) implements Dimension.CurrentStorageMode {

    }

    public record UnknownMember(UnknownMemberEnumType value, String valuens) implements Dimension.UnknownMember {

    }

}
