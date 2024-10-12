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
package org.eclipse.daanse.xmla.api.exception;

public non-sealed interface WarningType extends Type {

    /**
     * @return The location information for the Warning.
     */
    MessageLocation location();

    /**
     * @return The warning code for the Warning.
     */
    Integer warningCode();

    /**
     * @return A description of the Warning.
     */
    String description();

    /**
     * @return The source of the Warning, such as a product name.
     */
    String source();

    /**
     * @return A help file that contains information about the Warning.
     */
    String helpFile();
}
