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
package org.eclipse.daanse.xmla.model.record.engine300_300;

import java.util.List;

import org.eclipse.daanse.xmla.api.engine300.RelationshipEndVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300_300.RelationshipEnd;
import org.eclipse.daanse.xmla.api.engine300_300.RelationshipEndTranslation;

public record RelationshipEndR(String role, String multiplicity, String dimensionID, List<String> attributes,
        List<RelationshipEndTranslation> translations, RelationshipEndVisualizationProperties visualizationProperties)
        implements RelationshipEnd {

}
