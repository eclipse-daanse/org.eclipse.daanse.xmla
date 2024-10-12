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

/**
 * ErrorType
 */
public non-sealed interface ErrorType extends Type {

    /**
     * @return The location information for the Error.
     */
    MessageLocation location();

    /**
     * @return The callstack at which the Error occurred.
     */
    String callstack();

    /**
     * @return The error code for the Error.
     */
    Long errorCode();

    /**
     * @return A description of the Error.
     */
    String description();

    /**
     * @return The source of the Error, such as a product name.
     */
    String source();

    /**
     * @return A help file that contains information about the Error.
     */
    String helpFile();

}
