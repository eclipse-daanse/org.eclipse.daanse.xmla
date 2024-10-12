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
 * The ErrorConfiguration complex type represents error configuration settings to deal with issues
 * in the source data.
 */
public interface ErrorConfiguration {

    /**
     * @return The number of key errors after which processing will fail. default Zero
     */
    Optional<Long> keyErrorLimit();

    /**
     * @return The file path for logging key errors.
     */
    Optional<String> keyErrorLogFile();

    /**
     * @return The action to take upon encountering a key error. default "ConvertToUnknown"
     */
    Optional<String> keyErrorAction();

    /**
     * @return The action to take upon encountering a key error limit. default "StopProcessing"
     */
    Optional<String> keyErrorLimitAction();

    /**
     * @return The action to take upon encountering a "Key not found" error. default "ReportAndContinue"
     */
    Optional<String> keyNotFound();

    /**
     * @return The action to take upon encountering a key duplicate error. default "IgnoreError"
     */
    Optional<String> keyDuplicate();

    /**
     * @return The action to take if a null key is converted to Unknown. default "IgnoreError"
     */
    Optional<String> nullKeyConvertedToUnknown();

    /**
     * @return The action to take if a null key is encountered and not allowed. default
     *         "ReportAndContinue"
     */
    Optional<String> nullKeyNotAllowed();

    /**
     * @return The action to take upon encountering a calculation error. default "IgnoreError"
     */
    Optional<String> calculationError();
}
