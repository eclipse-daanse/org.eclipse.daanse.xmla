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
 * The QueryBinding complex type represents a binding to a query.
 */
public interface QueryBinding extends TabularBinding {

    /**
     * @return The ID of the DataSource.
     */
    Optional<String> dataSourceID();

    /**
     * @return The text of the query.
     */
    String queryDefinition();
}
