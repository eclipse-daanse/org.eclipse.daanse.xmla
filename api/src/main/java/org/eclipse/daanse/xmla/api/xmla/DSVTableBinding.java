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

import java.util.Optional;

/**
 * The DSVTableBinding complex type represents a binding to a table within a DataSourceView.
 */
public interface DSVTableBinding extends TabularBinding {

    /**
     * @return The ID of the DataSourceView.
     */
    Optional<String> dataSourceViewID();

    /**
     * @return The ID of the table.
     */
    String tableID();

    /**
     * @return Specifies whether the table contents are embedded within the DSV, or whether the DSV
     *         refers to their actual location. default NotEmbedded
     */
    Optional<String> dataEmbeddingStyle();
}
