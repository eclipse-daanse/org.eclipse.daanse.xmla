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

import org.eclipse.daanse.xmla.api.engine200.WarningLocationObject;

/**
 * The MessageLocation type is used to identify the line and column location of a warning or an
 * error within a Statement element.
 */
public interface MessageLocation {

    /**
     * @return The Start element contains a Line element (integer) and a Column element (integer) that
     *         indicates the starting point of the Warning or Error.
     */
    StartEnd start();

    /**
     * @return The End element contains a Line element (integer) and a Column element (integer) that
     *         indicates the ending point of the Warning or Error.
     */
    StartEnd end();

    /**
     * @return The number of characters from the beginning of the stream to the beginning of the Start
     *         line.
     */
    Integer lineOffset();

    /**
     * @return Number of characters in the message location, between Start and End.
     */
    Integer textLength();

    /**
     * @return The SourceObject is the object that has the error. The WarningLocation object represents
     *         either a column or a measure.
     */
    WarningLocationObject sourceObject();

    /**
     * @return The DependsOnObject is the object on which the SourceObject depends in the case of a
     *         dependency error. The WarningLocation object represents either a column or a measure.
     */
    WarningLocationObject dependsOnObject();

    /**
     * @return For calculation errors, the RowNumber in which the error occurred is provided.
     */
    Integer rowNumber();

}
