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
package org.eclipse.daanse.xmla.model.record.mddataset;

import java.util.List;

import org.eclipse.daanse.xmla.api.mddataset.AxisInfo;
import org.eclipse.daanse.xmla.api.mddataset.HierarchyInfo;

public record AxisInfoR(List<HierarchyInfo> hierarchyInfo, String name) implements AxisInfo {
}
