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
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.ReportAction;
import org.eclipse.daanse.xmla.api.xmla.ReportFormatParameter;
import org.eclipse.daanse.xmla.api.xmla.ReportParameter;
import org.eclipse.daanse.xmla.api.xmla.TargetTypeEnum;
import org.eclipse.daanse.xmla.api.xmla.Translation;
import org.eclipse.daanse.xmla.api.xmla.TypeEnum;

public record ReportActionR(String name, Optional<String> id, Optional<String> caption, Optional<Boolean> captionIsMdx,
        Optional<List<Translation>> translations, TargetTypeEnum targetType, Optional<String> target,
        Optional<String> condition, TypeEnum type, Optional<String> invocation, Optional<String> application,
        Optional<String> description, Optional<List<Annotation>> annotations, String reportServer,
        Optional<String> path, Optional<List<ReportParameter>> reportParameters,
        Optional<List<ReportFormatParameter>> reportFormatParameters) implements ReportAction {

}
