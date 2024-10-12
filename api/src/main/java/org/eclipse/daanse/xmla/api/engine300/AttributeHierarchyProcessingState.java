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
package org.eclipse.daanse.xmla.api.engine300;

public enum AttributeHierarchyProcessingState {

    PROCESSED("Processed"), UNPROCESSED("Unprocessed"), DEPENDENCY_ERROR("DependencyError");

    private final String value;

    AttributeHierarchyProcessingState(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AttributeHierarchyProcessingState fromValue(String v) {
        for (AttributeHierarchyProcessingState c : AttributeHierarchyProcessingState.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
