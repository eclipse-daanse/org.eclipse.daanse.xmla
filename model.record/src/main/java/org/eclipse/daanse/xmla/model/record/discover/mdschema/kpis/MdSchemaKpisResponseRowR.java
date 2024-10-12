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
package org.eclipse.daanse.xmla.model.record.discover.mdschema.kpis;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.ScopeEnum;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisResponseRow;

public record MdSchemaKpisResponseRowR(Optional<String> catalogName, Optional<String> schemaName,
        Optional<String> cubeName, Optional<String> measureGroupName, Optional<String> kpiName,
        Optional<String> kpiCaption, Optional<String> kpiDescription, Optional<String> kpiDisplayFolder,
        Optional<String> kpiValue, Optional<String> kpiGoal, Optional<String> kpiStatus, Optional<String> kpiTrend,
        Optional<String> kpiStatusGraphic, Optional<String> kpiTrendGraphic, Optional<String> kpiWight,
        Optional<String> kpiCurrentTimeMember, Optional<String> kpiParentKpiName, Optional<String> annotation,
        Optional<ScopeEnum> scope) implements MdSchemaKpisResponseRow {

}
