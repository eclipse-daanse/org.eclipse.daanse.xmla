/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.model.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.eclipse.daanse.xmla.model.mddataset.CubeInfo;
import org.eclipse.daanse.xmla.model.mddataset.MdDataset;
import org.eclipse.daanse.xmla.model.mddataset.MdDatasetFactory;
import org.eclipse.daanse.xmla.model.mddataset.OlapInfo;
import org.eclipse.daanse.xmla.model.mddataset.OlapInfoCube;
import org.junit.jupiter.api.Test;

/**
 * The timestamps of a Cube in OlapInfo are in the engine namespace, as SSAS
 * writes them; the CubeName beside them stays in the mddataset namespace.
 */
class CubeInfoNamespaceTest {

    @Test
    void cubeTimestampsAreInTheEngineNamespace() throws Exception {
        MdDatasetFactory factory = MdDatasetFactory.eINSTANCE;
        OlapInfoCube cube = factory.createOlapInfoCube();
        cube.setCubeName("Sales");
        cube.setLastDataUpdate("2026-10-14T00:00:00");
        cube.setLastSchemaUpdate("2026-10-14T00:00:00");
        CubeInfo cubeInfo = factory.createCubeInfo();
        cubeInfo.getCube().add(cube);
        OlapInfo olapInfo = factory.createOlapInfo();
        olapInfo.setCubeInfo(cubeInfo);
        MdDataset dataset = factory.createMdDataset();
        dataset.setOlapInfo(olapInfo);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XmlaMessageCodec.writeExecuteResponse(out, List.of(), dataset);
        String xml = out.toString(StandardCharsets.UTF_8);

        assertThat(xml).contains("<Cube><CubeName>Sales</CubeName>"
                + "<LastDataUpdate xmlns=\"http://schemas.microsoft.com/analysisservices/2003/engine\">"
                + "2026-10-14T00:00:00</LastDataUpdate>"
                + "<LastSchemaUpdate xmlns=\"http://schemas.microsoft.com/analysisservices/2003/engine\">"
                + "2026-10-14T00:00:00</LastSchemaUpdate></Cube>");
    }
}
