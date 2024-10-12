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
import java.util.Optional;

/**
 * This complex type represents an action. Action is an abstract type, and types for derived Action
 * types are derived from it. Therefore, the XSD for action does not show the elements; instead they
 * are shown within the XSD of each of the derived types. However, all the elements in the base type
 * are explained in the table in this section.
 */
public interface Action {
    /**
     * @return The object name.
     */
    String name();

    /**
     * @return The object ID string.
     */
    Optional<String> id();

    /**
     * @return The caption displayed for the action. Can be an MDX expression.
     */
    Optional<String> caption();

    /**
     * @return When true, specifies that the caption is an MDX expression; otherwise, false.
     */
    Optional<Boolean> captionIsMdx();

    /**
     * @return A collection of Translation objects.
     */
    Optional<List<Translation>> translations();

    /**
     * @return The type of object to which this Action applies. Such objects are limited to those in the
     *         enumeration that is specified in the XSD. The following objects are allowed: "Cube": A
     *         Cube object. "Cells": A subcube. Subcubes are created by using MDX [MSDN- CREATESUBCUBE].
     *         "Set": A set. Sets are created by using MDX [MSDN-CREATESET]. "Hierarchy": A Hierarchy
     *         object. "Level": A Level object. "DimensionMembers": The members of a Dimension.
     *         "HierarchyMembers": The members of a Hierarchy.
     */
    TargetTypeEnum targetType();

    /**
     * @return Identifies the target for this Action. The specified Target MUST be an object of type
     *         TargetType.
     */
    Optional<String> target();

    /**
     * @return An MDX expression that determines if the action applies. If false, the action does not
     *         apply.
     */
    Optional<String> condition();

    /**
     * @return The type of action. The following values are allowed: "Url" – Opens a URL string in an
     *         Internet browser. "Html" – Renders an HTML script in an Internet browser. "Statement" –
     *         Executes a statement that is understood by the client application. "DrillThrough" - See
     *         DrillThroughAction. "Dataset" – Executes an MDX statement whose results are returned as a
     *         dataset. "Rowset" – Executes an MDX statement whose results are returned as a rowset.
     *         "CommandLine" – Executes a command. "Proprietary" – Executes an action whose structure is
     *         understood by a particular proprietary client application. "Report" – See ReportAction.
     *         The DrillThrough type can be defined only with actions where the target type is Cells.
     *         The DrillThrough type is referenced in the MDSCHEMA_ACTIONS schema rowset, Action_Type
     *         column, as a Rowset action (0x010). The report action is exposed in the MDSCHEMA_ACTIONS
     *         schema rowset, Action_Type column, as a URL action(0x01). Note that for the derived types
     *         ReportAction and DrillThroughAction, this value MUST be set to "Report" and
     *         "DrillThrough", respectively.
     */
    TypeEnum type();

    /**
     * @return An enumeration value that determines how the action is invoked.
     */
    Optional<String> invocation();

    /**
     * @return Identifies the application associated with an Action element.
     */
    Optional<String> application();

    /**
     * @return The object description.
     */
    Optional<String> description();

    /**
     * @return A collection of Annotation objects.
     */
    Optional<List<Annotation>> annotations();
}
