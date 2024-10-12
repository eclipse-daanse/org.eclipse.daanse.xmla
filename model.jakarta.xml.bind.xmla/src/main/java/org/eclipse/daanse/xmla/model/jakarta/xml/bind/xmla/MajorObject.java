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
@XmlType(name = "MajorObject", propOrder = { "aggregationDesign", "assembly", "cube", "database", "dataSource",
        "dataSourceView", "dimension", "mdxScript", "measureGroup", "miningModel", "miningStructure", "partition",
        "permission", "perspective", "role", "server", "trace" })
public class MajorObject {

    @XmlElement(name = "AggregationDesign")
    protected AggregationDesign aggregationDesign;
    @XmlElement(name = "Assembly")
    protected Assembly assembly;
    @XmlElement(name = "Cube")
    protected Cube cube;
    @XmlElement(name = "Database")
    protected Database database;
    @XmlElement(name = "DataSource")
    protected DataSource dataSource;
    @XmlElement(name = "DataSourceView")
    protected DataSourceView dataSourceView;
    @XmlElement(name = "Dimension")
    protected Dimension dimension;
    @XmlElement(name = "MdxScript")
    protected MdxScript mdxScript;
    @XmlElement(name = "MeasureGroup")
    protected MeasureGroup measureGroup;
    @XmlElement(name = "MiningModel")
    protected MiningModel miningModel;
    @XmlElement(name = "MiningStructure")
    protected MiningStructure miningStructure;
    @XmlElement(name = "Partition")
    protected Partition partition;
    @XmlElement(name = "Permission")
    protected Permission permission;
    @XmlElement(name = "Perspective")
    protected Perspective perspective;
    @XmlElement(name = "Role")
    protected Role role;
    @XmlElement(name = "Server")
    protected Server server;
    @XmlElement(name = "Trace")
    protected Trace trace;

    public AggregationDesign getAggregationDesign() {
        return aggregationDesign;
    }

    public void setAggregationDesign(AggregationDesign value) {
        this.aggregationDesign = value;
    }

    public Assembly getAssembly() {
        return assembly;
    }

    public void setAssembly(Assembly value) {
        this.assembly = value;
    }

    public Cube getCube() {
        return cube;
    }

    public void setCube(Cube value) {
        this.cube = value;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database value) {
        this.database = value;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource value) {
        this.dataSource = value;
    }

    public DataSourceView getDataSourceView() {
        return dataSourceView;
    }

    public void setDataSourceView(DataSourceView value) {
        this.dataSourceView = value;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension value) {
        this.dimension = value;
    }

    public MdxScript getMdxScript() {
        return mdxScript;
    }

    public void setMdxScript(MdxScript value) {
        this.mdxScript = value;
    }

    public MeasureGroup getMeasureGroup() {
        return measureGroup;
    }

    public void setMeasureGroup(MeasureGroup value) {
        this.measureGroup = value;
    }

    public MiningModel getMiningModel() {
        return miningModel;
    }

    public void setMiningModel(MiningModel value) {
        this.miningModel = value;
    }

    public MiningStructure getMiningStructure() {
        return miningStructure;
    }

    public void setMiningStructure(MiningStructure value) {
        this.miningStructure = value;
    }

    public Partition getPartition() {
        return partition;
    }

    public void setPartition(Partition value) {
        this.partition = value;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission value) {
        this.permission = value;
    }

    public Perspective getPerspective() {
        return perspective;
    }

    public void setPerspective(Perspective value) {
        this.perspective = value;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role value) {
        this.role = value;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server value) {
        this.server = value;
    }

    public Trace getTrace() {
        return trace;
    }

    public void setTrace(Trace value) {
        this.trace = value;
    }
}
