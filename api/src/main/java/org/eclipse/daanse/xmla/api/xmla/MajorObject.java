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

public interface MajorObject {

    AggregationDesign aggregationDesign();

    Assembly assembly();

    Cube cube();

    Database database();

    DataSource dataSource();

    DataSourceView dataSourceView();

    Dimension dimension();

    MdxScript mdxScript();

    MeasureGroup measureGroup();

    MiningModel miningModel();

    MiningStructure miningStructure();

    Partition partition();

    Permission permission();

    Perspective perspective();

    Role role();

    Server server();

    Trace trace();
}
