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
package org.eclipse.daanse.xmla.api.exception;

/**
 * The End element contains a Line element (integer) and a Column element (integer) that indicates
 * the ending point of the Warning or Error.
 */
public interface StartEnd {

    int line();

    int column();
}
