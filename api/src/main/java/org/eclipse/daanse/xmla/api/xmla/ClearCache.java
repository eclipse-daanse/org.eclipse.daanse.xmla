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

public non-sealed interface ClearCache extends Command {

    /**
     * @return The object to clear from the cache. The object MUST be one of the following: Database,
     *         Dimension, Cube, or MeasureGroup. The ObjectReference type is defined in section
     *         3.1.4.3.2.1.1.1.
     */
    ObjectReference object();
}
