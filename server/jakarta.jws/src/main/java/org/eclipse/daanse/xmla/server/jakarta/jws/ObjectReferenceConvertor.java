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
package org.eclipse.daanse.xmla.server.jakarta.jws;

import org.eclipse.daanse.xmla.api.xmla.ObjectReference;
import org.eclipse.daanse.xmla.model.record.xmla.ObjectReferenceR;

public class ObjectReferenceConvertor {

    private ObjectReferenceConvertor() {
    }

    public static ObjectReference convertObjectReference(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ObjectReference parentObject) {
        if (parentObject != null) {
            return new ObjectReferenceR(parentObject.getServerID(),
                parentObject.getDatabaseID(),
                parentObject.getRoleID(),
                parentObject.getTraceID(),
                parentObject.getAssemblyID(),
                parentObject.getDimensionID(),
                parentObject.getDimensionPermissionID(),
                parentObject.getDataSourceID(),
                parentObject.getDataSourcePermissionID(),
                parentObject.getDatabasePermissionID(),
                parentObject.getDataSourceViewID(),
                parentObject.getCubeID(),
                parentObject.getMiningStructureID(),
                parentObject.getMeasureGroupID(),
                parentObject.getPerspectiveID(),
                parentObject.getCubePermissionID(),
                parentObject.getMdxScriptID(),
                parentObject.getPartitionID(),
                parentObject.getAggregationDesignID(),
                parentObject.getMiningModelID(),
                parentObject.getMiningModelPermissionID(),
                parentObject.getMiningStructurePermissionID());
        }
        return null;
    }
}
