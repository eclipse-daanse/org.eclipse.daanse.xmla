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

import java.util.List;

import org.eclipse.daanse.xmla.api.engine300.HierarchyVisualizationProperties;

public interface Hierarchy {

    String name();

    String id();

    String description();

    String processingState();

    String structureType();

    String displayFolder();

    List<Translation> translations();

    String allMemberName();

    List<Translation> allMemberTranslations();

    Boolean memberNamesUnique();

    String memberKeysUnique();

    Boolean allowDuplicateNames();

    List<Level> levels();

    List<Annotation> annotations();

    HierarchyVisualizationProperties visualizationProperties();
}
