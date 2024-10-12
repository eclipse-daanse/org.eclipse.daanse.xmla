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
*/
package org.eclipse.daanse.xmla.model.record.xmla;

import org.eclipse.daanse.xmla.api.xmla.ObjectReference;

public record ObjectReferenceR(String serverID, String databaseID, String roleID, String traceID, String assemblyID,
        String dimensionID, String dimensionPermissionID, String dataSourceID, String dataSourcePermissionID,
        String databasePermissionID, String dataSourceViewID, String cubeID, String miningStructureID,
        String measureGroupID, String perspectiveID, String cubePermissionID, String mdxScriptID, String partitionID,
        String aggregationDesignID, String miningModelID, String miningModelPermissionID,
        String miningStructurePermissionID) implements ObjectReference {

}
