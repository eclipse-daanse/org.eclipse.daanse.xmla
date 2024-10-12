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
import org.eclipse.daanse.xmla.api.xmla.MiningModelColumn;
import org.eclipse.daanse.xmla.api.xmla.MiningModelingFlag;
import org.eclipse.daanse.xmla.api.xmla.Translation;

public record MiningModelColumnR(String name, Optional<String> id, Optional<String> description,
        Optional<String> sourceColumnID, Optional<String> usage, Optional<String> filter,
        Optional<List<Translation>> translations, Optional<List<MiningModelColumn>> columns,
        Optional<List<MiningModelingFlag>> modelingFlags, Optional<List<Annotation>> annotations)
        implements MiningModelColumn {

}
