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
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.Binding;
import org.eclipse.daanse.xmla.api.xmla.DataItem;
import org.eclipse.daanse.xmla.api.xmla.MiningModelingFlag;
import org.eclipse.daanse.xmla.api.xmla.ScalarMiningStructureColumn;
import org.eclipse.daanse.xmla.api.xmla.Translation;

public record ScalarMiningStructureColumnR(String name, Optional<String> id, Optional<String> description,
        Optional<String> type, Optional<List<Annotation>> annotations, Optional<Boolean> isKey,
        Optional<Binding> source, Optional<String> distribution, Optional<List<MiningModelingFlag>> modelingFlags,
        String content, Optional<List<String>> classifiedColumns, Optional<String> discretizationMethod,
        Optional<BigInteger> discretizationBucketCount, Optional<List<DataItem>> keyColumns,
        Optional<DataItem> nameColumn, Optional<List<Translation>> translations)
        implements ScalarMiningStructureColumn {
}
