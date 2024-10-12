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
 * The InheritedBinding complex type represents a binding that is inherited from another object.
 * InheritedBinding has no elements. It is used to indicate that a MeasureGroupAttribute inherits
 * its bindings from the corresponding DimensionAttribute.
 */
public interface InheritedBinding extends Binding {

}
