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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectReference", propOrder = {

})
public class ObjectReference {

    @XmlElement(name = "ServerID")
    protected String serverID;
    @XmlElement(name = "DatabaseID")
    protected String databaseID;
    @XmlElement(name = "RoleID")
    protected String roleID;
    @XmlElement(name = "TraceID")
    protected String traceID;
    @XmlElement(name = "AssemblyID")
    protected String assemblyID;
    @XmlElement(name = "DimensionID")
    protected String dimensionID;
    @XmlElement(name = "DimensionPermissionID")
    protected String dimensionPermissionID;
    @XmlElement(name = "DataSourceID")
    protected String dataSourceID;
    @XmlElement(name = "DataSourcePermissionID")
    protected String dataSourcePermissionID;
    @XmlElement(name = "DatabasePermissionID")
    protected String databasePermissionID;
    @XmlElement(name = "DataSourceViewID")
    protected String dataSourceViewID;
    @XmlElement(name = "CubeID")
    protected String cubeID;
    @XmlElement(name = "MiningStructureID")
    protected String miningStructureID;
    @XmlElement(name = "MeasureGroupID")
    protected String measureGroupID;
    @XmlElement(name = "PerspectiveID")
    protected String perspectiveID;
    @XmlElement(name = "CubePermissionID")
    protected String cubePermissionID;
    @XmlElement(name = "MdxScriptID")
    protected String mdxScriptID;
    @XmlElement(name = "PartitionID")
    protected String partitionID;
    @XmlElement(name = "AggregationDesignID")
    protected String aggregationDesignID;
    @XmlElement(name = "MiningModelID")
    protected String miningModelID;
    @XmlElement(name = "MiningModelPermissionID")
    protected String miningModelPermissionID;
    @XmlElement(name = "MiningStructurePermissionID")
    protected String miningStructurePermissionID;

    public String getServerID() {
        return serverID;
    }

    public void setServerID(String value) {
        this.serverID = value;
    }

    public String getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(String value) {
        this.databaseID = value;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String value) {
        this.roleID = value;
    }

    public String getTraceID() {
        return traceID;
    }

    public void setTraceID(String value) {
        this.traceID = value;
    }

    public String getAssemblyID() {
        return assemblyID;
    }

    public void setAssemblyID(String value) {
        this.assemblyID = value;
    }

    public String getDimensionID() {
        return dimensionID;
    }

    public void setDimensionID(String value) {
        this.dimensionID = value;
    }

    public String getDimensionPermissionID() {
        return dimensionPermissionID;
    }

    public void setDimensionPermissionID(String value) {
        this.dimensionPermissionID = value;
    }

    public String getDataSourceID() {
        return dataSourceID;
    }

    public void setDataSourceID(String value) {
        this.dataSourceID = value;
    }

    public String getDataSourcePermissionID() {
        return dataSourcePermissionID;
    }

    public void setDataSourcePermissionID(String value) {
        this.dataSourcePermissionID = value;
    }

    public String getDatabasePermissionID() {
        return databasePermissionID;
    }

    public void setDatabasePermissionID(String value) {
        this.databasePermissionID = value;
    }

    public String getDataSourceViewID() {
        return dataSourceViewID;
    }

    public void setDataSourceViewID(String value) {
        this.dataSourceViewID = value;
    }

    public String getCubeID() {
        return cubeID;
    }

    public void setCubeID(String value) {
        this.cubeID = value;
    }

    public String getMiningStructureID() {
        return miningStructureID;
    }

    public void setMiningStructureID(String value) {
        this.miningStructureID = value;
    }

    public String getMeasureGroupID() {
        return measureGroupID;
    }

    public void setMeasureGroupID(String value) {
        this.measureGroupID = value;
    }

    public String getPerspectiveID() {
        return perspectiveID;
    }

    public void setPerspectiveID(String value) {
        this.perspectiveID = value;
    }

    public String getCubePermissionID() {
        return cubePermissionID;
    }

    public void setCubePermissionID(String value) {
        this.cubePermissionID = value;
    }

    public String getMdxScriptID() {
        return mdxScriptID;
    }

    public void setMdxScriptID(String value) {
        this.mdxScriptID = value;
    }

    public String getPartitionID() {
        return partitionID;
    }

    public void setPartitionID(String value) {
        this.partitionID = value;
    }

    public String getAggregationDesignID() {
        return aggregationDesignID;
    }

    public void setAggregationDesignID(String value) {
        this.aggregationDesignID = value;
    }

    public String getMiningModelID() {
        return miningModelID;
    }

    public void setMiningModelID(String value) {
        this.miningModelID = value;
    }

    public String getMiningModelPermissionID() {
        return miningModelPermissionID;
    }

    public void setMiningModelPermissionID(String value) {
        this.miningModelPermissionID = value;
    }

    public String getMiningStructurePermissionID() {
        return miningStructurePermissionID;
    }

    public void setMiningStructurePermissionID(String value) {
        this.miningStructurePermissionID = value;
    }
}
