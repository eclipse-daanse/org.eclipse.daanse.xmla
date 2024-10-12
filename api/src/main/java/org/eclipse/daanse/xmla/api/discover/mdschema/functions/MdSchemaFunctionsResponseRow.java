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
package org.eclipse.daanse.xmla.api.discover.mdschema.functions;

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.DirectQueryPushableEnum;
import org.eclipse.daanse.xmla.api.common.enums.InterfaceNameEnum;
import org.eclipse.daanse.xmla.api.common.enums.OriginEnum;

/**
 * The MDSCHEMA_FUNCTIONS schema rowset returns information about the functions that are currently
 * available for use in the DAX and MDX languages.
 */
public interface MdSchemaFunctionsResponseRow {

    /**
     * @return The name of the function.
     */
    Optional<String> functionalName();

    /**
     * @return A description of the function.
     */
    Optional<String> description();

    /**
     * @return A description the parameters accepted by the function.
     */
    String parameterList();

    /**
     * @return The OLE DB data type that is returned by the function.
     */
    Optional<Integer> returnType();

    /**
     * @return The possible values are as follows: (0x1) MSOLAP (0x2) UDF (0x3) RELATIONAL (0x4) SCALAR
     */
    Optional<OriginEnum> origin();

    /**
     * @return A logical classification of the type of function. For example: DATETIME LOGICAL FILTER
     */
    Optional<InterfaceNameEnum> interfaceName();

    /**
     * @return The library that implements the function.
     */
    Optional<String> libraryName();

    /**
     * @deprecated
     * @return Unused
     */
    @Deprecated(since = "deprecated in specification")
    Optional<String> dllName();

    /**
     * @deprecated
     * @return Unused
     */
    @Deprecated(since = "deprecated in specification")
    Optional<String> helpFile();

    /**
     * @deprecated
     * @return Unused
     */
    @Deprecated(since = "deprecated in specification")
    Optional<String> helpContext();

    /**
     * @return The type of object on which this function can be called. For example, the Children MDX
     *         function can be called on a Member object.
     */
    Optional<String> object();

    /**
     * @return The caption of the function.
     */
    Optional<String> caption();

    /**
     * @return The parameters that can be provided to this function.
     */
    Optional<List<ParameterInfo>> parameterInfo();

    /**
     * @return A bitmask that indicates the scenarios in which this function can be used when the model
     *         is in DirectQuery mode. The possible values are as follows: (0x1) MEASURE: This function
     *         can be used in measure expressions. (0x2) CALCCOL: This function can be used in
     *         calculated column expressions.
     */
    Optional<DirectQueryPushableEnum> directQueryPushable();

}
