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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CubePermission", propOrder = { "readSourceData", "dimensionPermissions", "cellPermissions" })
public class CubePermission extends Permission {

    @XmlElement(name = "ReadSourceData")
    protected String readSourceData;
    @XmlElement(name = "DimensionPermission")
    @XmlElementWrapper(name = "DimensionPermissions")
    protected List<CubeDimensionPermission> dimensionPermissions;
    @XmlElement(name = "CellPermission")
    @XmlElementWrapper(name = "CellPermissions")
    protected List<CellPermission> cellPermissions;

    public String getReadSourceData() {
        return readSourceData;
    }

    public void setReadSourceData(String value) {
        this.readSourceData = value;
    }

    public List<CubeDimensionPermission> getDimensionPermissions() {
        return dimensionPermissions;
    }

    public void setDimensionPermissions(List<CubeDimensionPermission> value) {
        this.dimensionPermissions = value;
    }

    public List<CellPermission> getCellPermissions() {
        return cellPermissions;
    }

    public void setCellPermissions(List<CellPermission> value) {
        this.cellPermissions = value;
    }

}
