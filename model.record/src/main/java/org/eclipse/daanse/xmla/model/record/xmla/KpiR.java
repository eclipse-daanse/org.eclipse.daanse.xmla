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
package org.eclipse.daanse.xmla.model.record.xmla;

import java.util.List;

import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.Kpi;
import org.eclipse.daanse.xmla.api.xmla.Translation;

public record KpiR(String name, String id, String description, List<Translation> translations, String displayFolder,
        String associatedMeasureGroupID, String value, String goal, String status, String trend, String weight,
        String trendGraphic, String statusGraphic, String currentTimeMember, String parentKpiID,
        List<Annotation> annotations) implements Kpi {

}
