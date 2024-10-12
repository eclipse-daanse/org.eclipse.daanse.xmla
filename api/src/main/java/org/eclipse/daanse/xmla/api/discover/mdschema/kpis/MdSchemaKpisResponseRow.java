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
package org.eclipse.daanse.xmla.api.discover.mdschema.kpis;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.ScopeEnum;

/**
 * This schema rowset describes the KPIs within a database.
 */
public interface MdSchemaKpisResponseRow {

    /**
     * @return The name of the database.
     */
    Optional<String> catalogName();

    /**
     * @return The name of the schema.
     */
    Optional<String> schemaName();

    /**
     * @return The name of the cube.
     */
    Optional<String> cubeName();

    /**
     * @return The associated measure group for the KPI.
     */
    Optional<String> measureGroupName();

    /**
     * The name of the KPI.
     */
    Optional<String> kpiName();

    /**
     * @return A label or caption associated with the KPI.
     */
    Optional<String> kpiCaption();

    /**
     * @return A description of the KPI.
     */
    Optional<String> kpiDescription();

    /**
     * @return The display folder.
     */
    Optional<String> kpiDisplayFolder();

    /**
     * @return The unique name of the member in the measures dimension for the KPI value.
     */
    Optional<String> kpiValue();

    /**
     * @return The unique name of the member in the measures dimension for the KPI goal.
     */
    Optional<String> kpiGoal();

    /**
     * @return The unique name of the member in the measures dimension for the KPI status.
     */
    Optional<String> kpiStatus();

    /**
     * @return The unique name of the member in the measures dimension for the KPI trend.
     */
    Optional<String> kpiTrend();

    /**
     * @return The default graphical representation of the KPI status.
     */
    Optional<String> kpiStatusGraphic();

    /**
     * @return The default graphical representation of the KPI trend.
     */
    Optional<String> kpiTrendGraphic();

    /**
     * @return The unique name of the member in the measures dimension for the KPI weight.
     */
    Optional<String> kpiWight();

    /**
     * @return The unique name of the member in the time dimension that defines the temporal context of
     *         the KPI.
     */
    Optional<String> kpiCurrentTimeMember();

    /**
     * @return The name of the parent KPI. ANNOTATIONSxsd:stringThe annotations on the KPI.
     */
    Optional<String> kpiParentKpiName();

    /**
     * @return The annotations on the KPI.
     */
    Optional<String> annotation();

    /**
     * The scope of the KPI. The KPI can be a session KPI or global KPI. This column can have one of the
     * following values: 1 - Global 2 – Session
     */
    Optional<ScopeEnum> scope();

}
