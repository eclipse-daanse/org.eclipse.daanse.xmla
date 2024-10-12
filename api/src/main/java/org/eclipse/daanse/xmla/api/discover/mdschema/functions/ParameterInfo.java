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
*/
package org.eclipse.daanse.xmla.api.discover.mdschema.functions;

public interface ParameterInfo {
    /**
     * @return The name of the parameter.
     */
    String name();

    /**
     * @return The description of the parameter.
     */
    String description();

    /**
     * @return A Boolean that, when true, indicates that the parameter is optional.
     */
    Boolean optional();

    /**
     * @return A Boolean that, when true, indicates that multiple values can be specified for this
     *         parameter.
     */
    Boolean repeatable();

    /**
     * @return The index of the repeat group of this parameter.
     */
    Integer repeatGroup();

}
