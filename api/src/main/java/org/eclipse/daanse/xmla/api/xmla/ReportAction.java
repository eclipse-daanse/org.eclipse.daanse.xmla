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
 * This complex type represents an action that invokes a report. ReportAction extends Action, and
 * includes all the elements in Action.
 */
public interface ReportAction extends Action {

    /**
     * @return The name of the computer on which the report server is running.
     */
    String reportServer();

    /**
     * @return The path pointing to the report in the report server.
     */
    Optional<String> path();

    /**
     * @return A collection of type ReportParameter, which is passed to a specific report and handled
     *         according to the specification in that report for that parameter.
     */
    Optional<List<ReportParameter>> reportParameters();

    /**
     * @return A collection of type ReportFormatParameter, which is passed to a specific report to
     *         affect the formatting of the report. The parameter is handled according to the
     *         specification in that report for that parameter.
     */
    Optional<List<ReportFormatParameter>> reportFormatParameters();
}
