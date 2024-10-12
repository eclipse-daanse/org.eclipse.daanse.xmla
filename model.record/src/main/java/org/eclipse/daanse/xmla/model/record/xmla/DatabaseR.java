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

import org.eclipse.daanse.xmla.api.engine.ImpersonationInfo;
import org.eclipse.daanse.xmla.api.xmla.Account;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.Assembly;
import org.eclipse.daanse.xmla.api.xmla.Cube;
import org.eclipse.daanse.xmla.api.xmla.DataSource;
import org.eclipse.daanse.xmla.api.xmla.DataSourceView;
import org.eclipse.daanse.xmla.api.xmla.Database;
import org.eclipse.daanse.xmla.api.xmla.DatabasePermission;
import org.eclipse.daanse.xmla.api.xmla.Dimension;
import org.eclipse.daanse.xmla.api.xmla.MiningStructure;
import org.eclipse.daanse.xmla.api.xmla.Role;
import org.eclipse.daanse.xmla.api.xmla.Translation;

public record DatabaseR(String name, String id, Instant createdTimestamp, Instant lastSchemaUpdate, String description,
        List<Annotation> annotations, Instant lastUpdate, String state, String readWriteMode, String dbStorageLocation,
        String aggregationPrefix, BigInteger processingPriority, Long estimatedSize, Instant lastProcessed,
        BigInteger language, String collation, Boolean visible, String masterDataSourceID,
        ImpersonationInfo dataSourceImpersonationInfo, List<Account> accounts, List<DataSource> dataSources,
        List<DataSourceView> dataSourceViews, List<Dimension> dimensions, List<Cube> cubes,
        List<MiningStructure> miningStructures, List<Role> roles, List<Assembly> assemblies,
        List<DatabasePermission> databasePermissions, List<Translation> translations, String storageEngineUsed,
        String imagePath, String imageUrl, String imageUniqueID, String imageVersion, String token,
        BigInteger compatibilityLevel, String directQueryMode) implements Database {

}
