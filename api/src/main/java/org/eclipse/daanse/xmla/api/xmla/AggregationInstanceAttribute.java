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

import java.util.List;
import java.util.Optional;

/**
 * This complex type represents the attribute in the AggregationInstanceDimension for which the fact
 * data is aggregated.
 */
public interface AggregationInstanceAttribute {

    /**
     * @return The ID of the attribute.
     */
    String attributeID();

    /**
     * @return A collection of KeyColumn elements of type DataItem. This can be used to override the
     *         binding that is specified on the attribute. The Source element within the DataItem MUST
     *         be of type ColumnBinding.
     *
     *         Required. However, if this element is specified as empty, the default is the binding that
     *         is specified on the attribute that is pointed to by AttributeID.
     */
    Optional<List<DataItem>> keyColumns();

}
