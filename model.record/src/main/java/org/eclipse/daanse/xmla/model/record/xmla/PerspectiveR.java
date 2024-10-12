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

import java.time.Instant;
import java.util.List;

import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.Perspective;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveAction;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveCalculation;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveDimension;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveKpi;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveMeasureGroup;
import org.eclipse.daanse.xmla.api.xmla.Translation;

public record PerspectiveR(String name, String id, Instant createdTimestamp, Instant lastSchemaUpdate,
        String description, List<Annotation> annotations, List<Translation> translations, String defaultMeasure,
        List<PerspectiveDimension> dimensions, List<PerspectiveMeasureGroup> measureGroups,
        List<PerspectiveCalculation> calculations, List<PerspectiveKpi> kpis, List<PerspectiveAction> actions)
        implements Perspective {

}
