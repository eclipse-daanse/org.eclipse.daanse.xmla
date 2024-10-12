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

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.MeasureAggregatorEnum;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

/**
 * This schema rowset describes each measure.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MdSchemaMeasuresResponseRowXml")
public class MdSchemaMeasuresResponseRowXml extends AbstractMdSchemaResponseRowXml implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 5203857212623144228L;

    /**
     * The name of the measure.
     */
    @XmlElement(name = "MEASURE_NAME", required = false)
    private String measureName;

    /**
     * The unique name of the measure
     */
    @XmlElement(name = "MEASURE_UNIQUE_NAME", required = false)
    private String measureUniqueName;

    /**
     * A caption associated with the measure.
     */
    @XmlElement(name = "MEASURE_CAPTION", required = false)
    private String measureCaption;

    /**
     * The GUID of the measure.
     */
    @XmlElement(name = "MEASURE_GUID", required = false)
    private Integer measureGuid;

    /**
     * An enumeration that identifies how a measure was derived. This enumeration can be one of the
     * following values: 1 – (MDMEASURE_AGGR_SUM) MEASURE aggregates from SUM. 2 -
     * (MDMEASURE_AGGR_COUNT) MEASURE aggregates from COUNT. 3 - (MDMEASURE_AGGR_MIN) MEASURE aggregates
     * from MIN. 4 - (MDMEASURE_AGGR_MAX) MEASURE aggregates from MAX. 5 - (MDMEASURE_AGGR_AVG) MEASURE
     * aggregates from AVG. 6 - (MDMEASURE_AGGR_VAR) MEASURE aggregates from VAR. 7 -
     * (MDMEASURE_AGGR_STD) Identifies that the measure aggregates from STDEV. 8 - (MDMEASURE_AGGR_DST)
     * Distinct Count: The aggregation is a count of unique members. 9 - (MDMEASURE_AGGR_NONE) None: No
     * aggregation is applied. 10 - (MDMEASURE_AGGR_AVGCHILDREN) Average of Children: The aggregation of
     * a member is the average of its children. 11 - (MDMEASURE_AGGR_FIRSTCHILD) First Child: The member
     * value is evaluated as the value of its first child along the time dimension. 12 -
     * (MDMEASURE_AGGR_LASTCHILD) Last Child: The member value is evaluated as the value of its last
     * child along the time dimension. 13 - (MDMEASURE_AGGR_FIRSTNONEMPTY) First Non-Empty: The member
     * value is evaluated as the value of its first child along the time dimension that contains data.
     * 14 - (MDMEASURE_AGGR_LASTNONEMPTY) Last Non-Empty: The member value is evaluated as the value of
     * its last child along the time dimension that contains data. 15 - (MDMEASURE_AGGR_BYACCOUNT)
     * ByAccount: The system uses the semiadditive behavior specified for the account type. 127 -
     * (MDMEASURE_AGGR_CALCULATED) Identifies that the measure was derived from a formula that was not
     * any of the single functions listed in any of the preceding single functions. 0 -
     * (MDMEASURE_AGGR_UNKNOWN) Identifies that the measure was derived from an unknown aggregation
     * function or formula.
     */
    @XmlElement(name = "MEASURE_AGGREGATOR", required = false)
    private MeasureAggregatorEnum measureAggregator;

    @XmlElement(name = "DATA_TYPE", required = false)
    private LevelDbTypeEnum dataType;

    /**
     * The maximum precision of the measure if the measure object's data type is Numeric, Decimal, or
     * DateTime. NULL for all other property types.
     */
    @XmlElement(name = "NUMERIC_PRECISION", required = false)
    private Integer numericPrecision;

    /**
     * The number of digits to the right of the decimal point if the measure object's type indicator is
     * Numeric, Decimal or DateTime. Otherwise, this value is NULL.
     */
    @XmlElement(name = "NUMERIC_SCALE", required = false)
    private Integer numericScale;

    /**
     * The units for the measure.
     */
    @XmlElement(name = "MEASURE_UNITS", required = false)
    private String measureUnits;

    /**
     * A description of the measure.
     */
    @XmlElement(name = "DESCRIPTION", required = false)
    private String description;

    /**
     * An expression for the member.
     */
    @XmlElement(name = "EXPRESSION", required = false)
    private String expression;

    /**
     * When true, indicates that the measure is visible. Always returns a value of true. If the measure
     * is not visible, it will not be included in the schema rowset.
     */
    @XmlElement(name = "MEASURE_IS_VISIBLE", required = false)
    private Boolean measureIsVisible;

    /**
     * @deprecated Not currently in use.
     */
    @Deprecated(since = "deprecated in specification")
    @XmlElement(name = "LEVELS_LIST", required = false)
    private String levelsList;

    /**
     * The name of the column in the SQL query that corresponds to the measure's name.
     */
    @XmlElement(name = "MEASURE_NAME_SQL_COLUMN_NAME", required = false)
    private String measureNameSqlColumnName;

    /**
     * The caption of the measure, not qualified with the measure group caption.
     */
    @XmlElement(name = "MEASURE_UNQUALIFIED_CAPTION", required = false)
    private String measureUnqualifiedCaption;

    /**
     * The name of the measure group to which the measure belongs.
     */
    @XmlElement(name = "MEASUREGROUP_NAME", required = false)
    private String measureGroupName;

    @XmlElement(name = "MEASURE_DISPLAY_FOLDER", required = false)
    private String measureDisplayFolder;
    /**
     * The default format string for the measure.
     */
    @XmlElement(name = "DEFAULT_FORMAT_STRING", required = false)
    private String defaultFormatString;

    public String getMeasureName() {
        return measureName;
    }

    public void setMeasureName(String measureName) {
        this.measureName = measureName;
    }

    public String getMeasureUniqueName() {
        return measureUniqueName;
    }

    public void setMeasureUniqueName(String measureUniqueName) {
        this.measureUniqueName = measureUniqueName;
    }

    public String getMeasureCaption() {
        return measureCaption;
    }

    public void setMeasureCaption(String measureCaption) {
        this.measureCaption = measureCaption;
    }

    public Integer getMeasureGuid() {
        return measureGuid;
    }

    public void setMeasureGuid(Integer measureGuid) {
        this.measureGuid = measureGuid;
    }

    public MeasureAggregatorEnum getMeasureAggregator() {
        return measureAggregator;
    }

    public void setMeasureAggregator(MeasureAggregatorEnum measureAggregator) {
        this.measureAggregator = measureAggregator;
    }

    public LevelDbTypeEnum getDataType() {
        return dataType;
    }

    public void setDataType(LevelDbTypeEnum dataType) {
        this.dataType = dataType;
    }

    public Integer getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(Integer numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public Integer getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(Integer numericScale) {
        this.numericScale = numericScale;
    }

    public String getMeasureUnits() {
        return measureUnits;
    }

    public void setMeasureUnits(String measureUnits) {
        this.measureUnits = measureUnits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Boolean getMeasureIsVisible() {
        return measureIsVisible;
    }

    public void setMeasureIsVisible(Boolean measureIsVisible) {
        this.measureIsVisible = measureIsVisible;
    }

    /**
     * @deprecated
     * @return
     */
    @Deprecated(since = "deprecated in specification")
    public String getLevelsList() {
        return levelsList;
    }

    /**
     * @deprecated
     * @param levelsList
     */
    @Deprecated(since = "deprecated in specification")
    public void setLevelsList(String levelsList) {
        this.levelsList = levelsList;
    }

    public String getMeasureNameSqlColumnName() {
        return measureNameSqlColumnName;
    }

    public void setMeasureNameSqlColumnName(String measureNameSqlColumnName) {
        this.measureNameSqlColumnName = measureNameSqlColumnName;
    }

    public String getMeasureUnqualifiedCaption() {
        return measureUnqualifiedCaption;
    }

    public void setMeasureUnqualifiedCaption(String measureUnqualifiedCaption) {
        this.measureUnqualifiedCaption = measureUnqualifiedCaption;
    }

    public String getMeasureGroupName() {
        return measureGroupName;
    }

    public void setMeasureGroupName(String measureGroupName) {
        this.measureGroupName = measureGroupName;
    }

    public String getMeasureDisplayFolder() {
        return measureDisplayFolder;
    }

    public void setMeasureDisplayFolder(String measureDisplayFolder) {
        this.measureDisplayFolder = measureDisplayFolder;
    }

    public String getDefaultFormatString() {
        return defaultFormatString;
    }

    public void setDefaultFormatString(String defaultFormatString) {
        this.defaultFormatString = defaultFormatString;
    }
}
