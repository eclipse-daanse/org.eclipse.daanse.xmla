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

import org.eclipse.daanse.xmla.api.xmla.Aggregation;
import org.eclipse.daanse.xmla.api.xmla.AggregationDesign;
import org.eclipse.daanse.xmla.api.xmla.AggregationDesignDimension;
import org.eclipse.daanse.xmla.api.xmla.Annotation;

public record AggregationDesignR(String name, Optional<String> id, Optional<Instant> createdTimestamp,
        Optional<Instant> lastSchemaUpdate, Optional<String> description, Optional<List<Annotation>> annotations,
        Optional<Long> estimatedRows, Optional<List<AggregationDesignDimension>> dimensions,
        Optional<List<Aggregation>> aggregations, Optional<Integer> estimatedPerformanceGain)
        implements AggregationDesign {

}
