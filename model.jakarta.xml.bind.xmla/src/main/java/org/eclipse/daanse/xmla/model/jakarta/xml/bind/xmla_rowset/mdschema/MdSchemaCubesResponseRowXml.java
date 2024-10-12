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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.mdschema;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.adapters.LocalDateTimeAdapter;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.CubeSourceEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.CubeTypeEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.PreferredQueryPatternsEnum;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * This schema rowset describes the structure of cubes within a database. Perspectives are also
 * returned in this schema.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MdSchemaCubesResponseRowXml")
public class MdSchemaCubesResponseRowXml extends AbstractMdSchemaResponseRowXml implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 108353317479541549L;

    /**
     * The type of the cube. Valid values are: CUBE DIMENSION
     */
    @XmlElement(name = "CUBE_TYPE")
    private CubeTypeEnum cubeType;

    /**
     * The GUID of the cube.
     */
    @XmlElement(name = "CUBE_GUID")
    private Integer cubeGuid;

    /**
     * TimeThe date and time the cube was created.
     */
    @XmlElement(name = "CREATED_ON")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime createdOn;

    /**
     * The date and time that the cube schema was last updated.
     */
    @XmlElement(name = "LAST_SCHEMA_UPDATE")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime lastSchemaUpdate;

    /**
     * The name of the user who last updated the cube’s schema.
     */
    @XmlElement(name = "SCHEMA_UPDATED_BY")
    private String schemaUpdatedBy;

    /**
     * TimeThe date and time that the cube was last processed.
     */
    @XmlElement(name = "LAST_DATA_UPDATE")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime lastDataUpdate;

    /**
     * The name of the user who last updated the data of the cube.
     */
    @XmlElement(name = "DATA_UPDATED_BY")
    private String dataUpdateDBy;

    /**
     * A description of the cube.
     */
    @XmlElement(name = "DESCRIPTION")
    private String description;

    /**
     * When true, indicates that the cube has drillthrough enabled; otherwise, false.
     */
    @XmlElement(name = "IS_DRILLTHROUGH_ENABLED")
    private Boolean isDrillThroughEnabled;

    /**
     * When true, indicates that the cube can be used in a linked cube; otherwise false.
     */
    @XmlElement(name = "IS_LINKABLE")
    private Boolean isLinkable;

    /**
     * When true, indicates that the cube is write-enabled; otherwise false.
     */
    @XmlElement(name = "IS_WRITE_ENABLED")
    private Boolean isWriteEnabled;

    /**
     * When true, indicates that SQL can be used on the cube; otherwise false.
     */
    @XmlElement(name = "IS_SQL_ENABLED")
    private Boolean isSqlEnabled;

    /**
     * The caption of the cube. BASE_CUBE_NAMExsd:stringYesThe name of the source cube if this cube is a
     * perspective cube.
     */
    @XmlElement(name = "CUBE_CAPTION")
    private String cubeCaption;

    /**
     * The name of the source cube if this cube is a perspective cube.
     */
    @XmlElement(name = "BASE_CUBE_NAME")
    private String baseCubeName;

    /**
     * A bitmask with one of these valid values: 0x01-Cube 0x02-Dimension
     */
    @XmlElement(name = "CUBE_SOURCE")
    private CubeSourceEnum cubeSource;

    /**
     * A bitmask that describes query pattern client applications can utilize for higher performance.
     * Valid values are:
     *
     * 0x00 – Use CrossJoin function to create symmetric sets on an axis. This is the default value for
     * the 0th bit when Analysis Services is running in Traditional mode.
     *
     * 0x01 – Use DrillDownMember to create a more restrictive, asymmetric axis. This is the default
     * value for the 0th bit when a server that is running Analysis Services is running in VertiPaq
     * mode.
     */
    @XmlElement(name = "PREFERRED_QUERY_PATTERNS")
    private PreferredQueryPatternsEnum preferredQueryPatterns;

    public CubeTypeEnum getCubeType() {
        return cubeType;
    }

    public void setCubeType(CubeTypeEnum cubeType) {
        this.cubeType = cubeType;
    }

    public Integer getCubeGuid() {
        return cubeGuid;
    }

    public void setCubeGuid(Integer cubeGuid) {
        this.cubeGuid = cubeGuid;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getLastSchemaUpdate() {
        return lastSchemaUpdate;
    }

    public void setLastSchemaUpdate(LocalDateTime lastSchemaUpdate) {
        this.lastSchemaUpdate = lastSchemaUpdate;
    }

    public String getSchemaUpdatedBy() {
        return schemaUpdatedBy;
    }

    public void setSchemaUpdatedBy(String schemaUpdatedBy) {
        this.schemaUpdatedBy = schemaUpdatedBy;
    }

    public LocalDateTime getLastDataUpdate() {
        return lastDataUpdate;
    }

    public void setLastDataUpdate(LocalDateTime lastDataUpdate) {
        this.lastDataUpdate = lastDataUpdate;
    }

    public String getDataUpdateDBy() {
        return dataUpdateDBy;
    }

    public void setDataUpdateDBy(String dataUpdateDBy) {
        this.dataUpdateDBy = dataUpdateDBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDrillThroughEnabled() {
        return isDrillThroughEnabled;
    }

    public void setDrillThroughEnabled(Boolean drillThroughEnabled) {
        isDrillThroughEnabled = drillThroughEnabled;
    }

    public Boolean getLinkable() {
        return isLinkable;
    }

    public void setLinkable(Boolean linkable) {
        isLinkable = linkable;
    }

    public Boolean getWriteEnabled() {
        return isWriteEnabled;
    }

    public void setWriteEnabled(Boolean writeEnabled) {
        isWriteEnabled = writeEnabled;
    }

    public Boolean getSqlEnabled() {
        return isSqlEnabled;
    }

    public void setSqlEnabled(Boolean sqlEnabled) {
        isSqlEnabled = sqlEnabled;
    }

    public String getCubeCaption() {
        return cubeCaption;
    }

    public void setCubeCaption(String cubeCaption) {
        this.cubeCaption = cubeCaption;
    }

    public String getBaseCubeName() {
        return baseCubeName;
    }

    public void setBaseCubeName(String baseCubeName) {
        this.baseCubeName = baseCubeName;
    }

    public CubeSourceEnum getCubeSource() {
        return cubeSource;
    }

    public void setCubeSource(CubeSourceEnum cubeSource) {
        this.cubeSource = cubeSource;
    }

    public PreferredQueryPatternsEnum getPreferredQueryPatterns() {
        return preferredQueryPatterns;
    }

    public void setPreferredQueryPatterns(PreferredQueryPatternsEnum preferredQueryPatterns) {
        this.preferredQueryPatterns = preferredQueryPatterns;
    }
}
