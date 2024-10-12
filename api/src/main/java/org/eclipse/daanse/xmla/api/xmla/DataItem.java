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
 * This complex type represents a scalar data item associated with an object, such as
 * DimensionAttribute and Measure.
 *
 * The Source element of the DataItem is of type Binding. However, in a specific instance of the
 * DataItem, there are often additional constraints as to what type of Binding will be permitted,
 * depending upon the parent of the DataItem. Within the tables throughout this document, it is
 * noted exactly which derived types of Binding are permitted in each context and MUST be used for
 * that particular context.
 */
public interface DataItem {

    /**
     * @return The data type of the column.
     */
    String dataType();

    /**
     * @return The data size in bytes. Zero means that the server will determine the DataSize.
     */
    Optional<Integer> dataSize();

    /**
     * @return The MIME type. Applicable only if the DataType is Binary.
     */
    Optional<String> mimeType();

    /**
     * @return Sets the processing of NULL values: "Automatic" – The server determines how null
     *         processing is handled.<97> "Automatic" uses the default processing that is appropriate
     *         for the element: "UnknownMember" – Generates an unknown member. This value MUST NOT be
     *         used if the column is associated with a measure. "ZeroOrBlank" – Converts the null value
     *         to zero (for numeric data items) or a blank string (for string data items). "Preserve" –
     *         Preserves the null value. "Error" – Raises an error. Value "Error" is not supported for
     *         measures. This value MUST NOT be used if the column is associated with a measure.
     */
    Optional<NullProcessingEnum> nullProcessing();

    /**
     * @return Specifies how data from the data source is trimmed. Applicable only to string data items.
     */
    Optional<String> trimming();

    /**
     * @return Specifies handling for invalid XML characters. The valid values are the following:
     *         "Preserve" - Specifies that invalid XML characters are preserved in the character stream.
     *         "Remove" – Specifies that invalid XML characters are removed. "Replace" – Specifies that
     *         invalid XML characters are replaced with a question mark (?) character.
     */
    Optional<InvalidXmlCharacterEnum> invalidXmlCharacters();

    /**
     * @return The collation of the data item. Applicable only to string data items.
     */
    Optional<String> collation();

    /**
     * @return The format of the data item. The valid values are the following: "TrimRight": The value
     *         is trimmed on the right. "TrimLeft": The value is trimmed on the left. "TrimAll": The
     *         value is trimmed on the left and the right. "TrimNone": The value is not trimmed.
     */
    Optional<DataItemFormatEnum> format();

    /**
     * @return The source of the data item. Which derived type of Binding is permitted is dependent upon
     *         the enclosing object, and is explained in the table for each enclosing object.
     */
    Optional<Binding> source();

    /**
     * @return A collection of Annotation objects.
     */
    Optional<List<Annotation>> annotations();
}
