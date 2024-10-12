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

/**
 * This complex type represents an algorithm parameter for a MiningModel. The parameters that are
 * allowed vary by which algorithm is chosen.
 */
public interface AlgorithmParameter {

    /**
     * @return The parameter name. Algorithm-specific.
     */
    String name();

    /**
     * @return The parameter value. Algorithm-specific.
     */
    java.lang.Object value();

}
