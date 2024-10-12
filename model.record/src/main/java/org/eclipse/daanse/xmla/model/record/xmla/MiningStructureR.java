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
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.Binding;
import org.eclipse.daanse.xmla.api.xmla.ErrorConfiguration;
import org.eclipse.daanse.xmla.api.xmla.MiningModel;
import org.eclipse.daanse.xmla.api.xmla.MiningStructure;
import org.eclipse.daanse.xmla.api.xmla.MiningStructureColumn;
import org.eclipse.daanse.xmla.api.xmla.MiningStructurePermission;
import org.eclipse.daanse.xmla.api.xmla.Translation;

public record MiningStructureR(String name, Optional<String> id, Optional<Instant> createdTimestamp,
        Optional<Instant> lastSchemaUpdate, Optional<String> description, Optional<List<Annotation>> annotations,
        Optional<Binding> source, Optional<Instant> lastProcessed, Optional<List<Translation>> translations,
        Optional<BigInteger> language, Optional<String> collation, Optional<ErrorConfiguration> errorConfiguration,
        Optional<String> cacheMode, Optional<Integer> holdoutMaxPercent, Optional<Integer> holdoutMaxCases,
        Optional<Integer> holdoutSeed, Optional<Integer> holdoutActualSize, List<MiningStructureColumn> columns,
        Optional<String> state, Optional<List<MiningStructurePermission>> miningStructurePermissions,
        Optional<List<MiningModel>> miningModels) implements MiningStructure {

}
