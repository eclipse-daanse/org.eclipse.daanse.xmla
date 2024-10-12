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

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.DimensionTypeEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.DimensionUniqueSettingEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.GroupingBehaviorEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.HierarchyOriginEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.InstanceSelectionEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.StructureEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.StructureTypeEnum;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

/**
 * This schema rowset describes each hierarchy within a particular dimension.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MdSchemaHierarchiesResponseRowXml")
public class MdSchemaHierarchiesResponseRowXml extends AbstractMdSchemaResponseRowXml implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 7338665487975144070L;

    /**
     * The unique name of the dimension.
     */
    @XmlElement(name = "DIMENSION_UNIQUE_NAME", required = false)
    private String dimensionUniqueName;

    /**
     * The name of the hierarchy. This column MAY be blank if there is only a single hierarchy in the
     * dimension.
     */
    @XmlElement(name = "HIERARCHY_NAME", required = false)
    private String hierarchyName;

    /**
     * The unique name of the hierarchy.
     */
    @XmlElement(name = "HIERARCHY_UNIQUE_NAME", required = false)
    private String hierarchyUniqueName;

    /**
     * The GUID of the hierarchy.
     */
    @XmlElement(name = "HIERARCHY_GUID", required = false)
    private Integer hierarchyGuid;

    /**
     * The caption of the hierarchy.
     */
    @XmlElement(name = "HIERARCHY_CAPTION", required = false)
    private String hierarchyCaption;

    /**
     * The type of the dimension. 0 - UNKNOWN 1 - TIME 2 - MEASURE 3 - OTHER 5 - QUANTITATIVE 6 -
     * ACCOUNTS 7 - CUSTOMERS 8 - PRODUCTS 9 - SCENARIO 10 - UTILITY 11 - CURRENCY 12 - RATES 13 -
     * CHANNEL 14 - PROMOTION 15 - ORGANIZATION 16 - BILL_OF_MATERIALS 17 - GEOGRAPHY
     */
    @XmlElement(name = "DIMENSION_TYPE", required = false)
    private DimensionTypeEnum dimensionType;

    /**
     * The number of members in the hierarchy.
     */
    @XmlElement(name = "HIERARCHY_CARDINALITY", required = false)
    private Integer hierarchyCardinality;

    /**
     * The default member for this hierarchy.
     */
    @XmlElement(name = "DEFAULT_MEMBER", required = false)
    private String defaultMember;

    /**
     * The member name at the highest level of the hierarchy.
     */
    @XmlElement(name = "ALL_MEMBER", required = false)
    private String allMember;

    /**
     * A description of the hierarchy.
     */
    @XmlElement(name = "DESCRIPTION", required = false)
    private String description;

    /**
     * The structure of the hierarchy. Valid values are defined in the following table. 0 - Hierarchy is
     * a fully balanced structure. 1 - Hierarchy is a ragged balanced structure. 2 - Hierarchy is an
     * unbalanced structure. 3 - Hierarchy is a network structure. For more information, see the
     * definitions for balanced hierarchy and unbalanced hierarchy in section 1.1.
     */
    @XmlElement(name = "STRUCTURE", required = false)
    private StructureEnum structure;

    /**
     * When true, indicates that the hierarchy is a virtual hierarchy; otherwise false.
     */
    @XmlElement(name = "IS_VIRTUAL", required = false)
    private Boolean isVirtual;

    /**
     * When true, indicates that write back to the hierarchy is enabled; otherwise false.
     */
    @XmlElement(name = "IS_READWRITE", required = false)
    private Boolean isReadWrite;

    /**
     * A list of values that specifies which columns contain unique values: 0x00000001 - Member key
     * columns establish uniqueness. 0x00000002 - Member name columns establish uniqueness.
     */
    @XmlElement(name = "DIMENSION_UNIQUE_SETTINGS", required = false)
    private DimensionUniqueSettingEnum dimensionUniqueSettings;

    /**
     * The unique name of the master dimension.
     */
    @XmlElement(name = "DIMENSION_MASTER_UNIQUE_NAME", required = false)
    private String dimensionMasterUniqueName;

    /**
     * When true, indicates that the dimension is visible; otherwise false.
     */
    @XmlElement(name = "DIMENSION_IS_VISIBLE", required = false)
    private Boolean dimensionIsVisible;

    /**
     * The ordinal number of the hierarchy across all hierarchies of the dimension.
     */
    @XmlElement(name = "HIERARCHY_ORDINAL", required = false)
    private Integer hierarchyOrdinal;

    /**
     * When true, indicates that the dimension is shared; otherwise false.
     */
    @XmlElement(name = "DIMENSION_IS_SHARED", required = false)
    private Boolean dimensionIsShared;

    /**
     * When true, indicates that the hierarchy is visible; otherwise false.
     */
    @XmlElement(name = "HIERARCHY_IS_VISIBLE", required = false)
    private Boolean hierarchyIsVisible;

    /**
     * A bitmask that determines the source of the hierarchy. 0x0001 - Identifies user-defined
     * hierarchies. 0x0002 - Identifies attribute hierarchies. 0x0004 - Identifies key attribute
     * hierarchies. 0x0008 - Identifies attributes with no attribute hierarchies. 0x0003 - The default
     * restriction value.
     */
    @XmlElement(name = "HIERARCHY_ORIGIN", required = false)
    private HierarchyOriginEnum hierarchyOrigin;

    /**
     * Display folder for the hierarchy.
     */
    @XmlElement(name = "HIERARCHY_DISPLAY_FOLDER", required = false)
    private String hierarchyDisplayFolder;

    /**
     * A list of values that provides a hint to the client application about how to display the
     * hierarchy values. Valid values include the following: 0 - NONE (No hint is suggested.) 1 -
     * DROPDOWN type of display is suggested. 2 - LIST type of display is suggested. 3 - FILTERED LIST
     * type of display is suggested. 4 - MANDATORY FILTER type of display is suggested
     */
    @XmlElement(name = "INSTANCE_SELECTION", required = false)
    private InstanceSelectionEnum instanceSelection;

    /**
     * Recommends to client applications how to build queries within the hierarchy. Valid values include
     * the following: 1 - Client applications are encouraged to group by each member of the hierarchy. 2
     * - Client applications are discouraged from grouping by each member of the hierarchy.
     */
    @XmlElement(name = "GROUPING_BEHAVIOR", required = false)
    private GroupingBehaviorEnum groupingBehavior;

    /**
     * Indicates the type of hierarchy. Valid values include the following: Natural Unnatural Unknown
     */
    @XmlElement(name = "STRUCTURE_TYPE", required = false)
    private StructureTypeEnum structureType;

    public String getDimensionUniqueName() {
        return dimensionUniqueName;
    }

    public void setDimensionUniqueName(String dimensionUniqueName) {
        this.dimensionUniqueName = dimensionUniqueName;
    }

    public String getHierarchyName() {
        return hierarchyName;
    }

    public void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }

    public String getHierarchyUniqueName() {
        return hierarchyUniqueName;
    }

    public void setHierarchyUniqueName(String hierarchyUniqueName) {
        this.hierarchyUniqueName = hierarchyUniqueName;
    }

    public Integer getHierarchyGuid() {
        return hierarchyGuid;
    }

    public void setHierarchyGuid(Integer hierarchyGuid) {
        this.hierarchyGuid = hierarchyGuid;
    }

    public String getHierarchyCaption() {
        return hierarchyCaption;
    }

    public void setHierarchyCaption(String hierarchyCaption) {
        this.hierarchyCaption = hierarchyCaption;
    }

    public DimensionTypeEnum getDimensionType() {
        return dimensionType;
    }

    public void setDimensionType(DimensionTypeEnum dimensionType) {
        this.dimensionType = dimensionType;
    }

    public Integer getHierarchyCardinality() {
        return hierarchyCardinality;
    }

    public void setHierarchyCardinality(Integer hierarchyCardinality) {
        this.hierarchyCardinality = hierarchyCardinality;
    }

    public String getDefaultMember() {
        return defaultMember;
    }

    public void setDefaultMember(String defaultMember) {
        this.defaultMember = defaultMember;
    }

    public String getAllMember() {
        return allMember;
    }

    public void setAllMember(String allMember) {
        this.allMember = allMember;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StructureEnum getStructure() {
        return structure;
    }

    public void setStructure(StructureEnum structure) {
        this.structure = structure;
    }

    public Boolean getVirtual() {
        return isVirtual;
    }

    public void setVirtual(Boolean virtual) {
        isVirtual = virtual;
    }

    public Boolean getReadWrite() {
        return isReadWrite;
    }

    public void setReadWrite(Boolean readWrite) {
        isReadWrite = readWrite;
    }

    public DimensionUniqueSettingEnum getDimensionUniqueSettings() {
        return dimensionUniqueSettings;
    }

    public void setDimensionUniqueSettings(DimensionUniqueSettingEnum dimensionUniqueSettings) {
        this.dimensionUniqueSettings = dimensionUniqueSettings;
    }

    public String getDimensionMasterUniqueName() {
        return dimensionMasterUniqueName;
    }

    public void setDimensionMasterUniqueName(String dimensionMasterUniqueName) {
        this.dimensionMasterUniqueName = dimensionMasterUniqueName;
    }

    public Boolean getDimensionIsVisible() {
        return dimensionIsVisible;
    }

    public void setDimensionIsVisible(Boolean dimensionIsVisible) {
        this.dimensionIsVisible = dimensionIsVisible;
    }

    public Integer getHierarchyOrdinal() {
        return hierarchyOrdinal;
    }

    public void setHierarchyOrdinal(Integer hierarchyOrdinal) {
        this.hierarchyOrdinal = hierarchyOrdinal;
    }

    public Boolean getDimensionIsShared() {
        return dimensionIsShared;
    }

    public void setDimensionIsShared(Boolean dimensionIsShared) {
        this.dimensionIsShared = dimensionIsShared;
    }

    public Boolean getHierarchyIsVisible() {
        return hierarchyIsVisible;
    }

    public void setHierarchyIsVisible(Boolean hierarchyIsVisible) {
        this.hierarchyIsVisible = hierarchyIsVisible;
    }

    public HierarchyOriginEnum getHierarchyOrigin() {
        return hierarchyOrigin;
    }

    public void setHierarchyOrigin(HierarchyOriginEnum hierarchyOrigin) {
        this.hierarchyOrigin = hierarchyOrigin;
    }

    public String getHierarchyDisplayFolder() {
        return hierarchyDisplayFolder;
    }

    public void setHierarchyDisplayFolder(String hierarchyDisplayFolder) {
        this.hierarchyDisplayFolder = hierarchyDisplayFolder;
    }

    public InstanceSelectionEnum getInstanceSelection() {
        return instanceSelection;
    }

    public void setInstanceSelection(InstanceSelectionEnum instanceSelection) {
        this.instanceSelection = instanceSelection;
    }

    public GroupingBehaviorEnum getGroupingBehavior() {
        return groupingBehavior;
    }

    public void setGroupingBehavior(GroupingBehaviorEnum groupingBehavior) {
        this.groupingBehavior = groupingBehavior;
    }

    public StructureTypeEnum getStructureType() {
        return structureType;
    }

    public void setStructureType(StructureTypeEnum structureType) {
        this.structureType = structureType;
    }
}
