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
 * This complex type represents a standard action. The StandardAction type extends the Action type
 * and includes all elements of Action.
 */
public interface StandardAction extends Action {

    /**
     * @return An MDX expression that determines the content of the action. If the expression is blank,
     *         there is no action on the target.
     */
    String expression();
}
