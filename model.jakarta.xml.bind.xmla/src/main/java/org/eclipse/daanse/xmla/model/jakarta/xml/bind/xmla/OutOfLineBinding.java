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
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OutOfLineBinding", propOrder = {

})
public class OutOfLineBinding {

    @XmlElement(name = "DatabaseID")
    protected String databaseID;
    @XmlElement(name = "DimensionID")
    protected String dimensionID;
    @XmlElement(name = "CubeID")
    protected String cubeID;
    @XmlElement(name = "MeasureGroupID")
    protected String measureGroupID;
    @XmlElement(name = "PartitionID")
    protected String partitionID;
    @XmlElement(name = "MiningModelID")
    protected String miningModelID;
    @XmlElement(name = "MiningStructureID")
    protected String miningStructureID;
    @XmlElement(name = "AttributeID")
    protected String attributeID;
    @XmlElement(name = "CubeDimensionID")
    protected String cubeDimensionID;
    @XmlElement(name = "MeasureID")
    protected String measureID;
    @XmlElement(name = "ParentColumnID")
    protected String parentColumnID;
    @XmlElement(name = "ColumnID")
    protected String columnID;
    @XmlElement(name = "Source")
    protected Binding source;
    @XmlElement(name = "NameColumn")
    protected OutOfLineBinding.NameColumn nameColumn;
    @XmlElement(name = "SkippedLevelsColumn")
    protected OutOfLineBinding.SkippedLevelsColumn skippedLevelsColumn;
    @XmlElement(name = "CustomRollupColumn")
    protected OutOfLineBinding.CustomRollupColumn customRollupColumn;
    @XmlElement(name = "CustomRollupPropertiesColumn")
    protected OutOfLineBinding.CustomRollupPropertiesColumn customRollupPropertiesColumn;
    @XmlElement(name = "ValueColumn")
    protected OutOfLineBinding.ValueColumn valueColumn;
    @XmlElement(name = "UnaryOperatorColumn")
    protected OutOfLineBinding.UnaryOperatorColumn unaryOperatorColumn;
    @XmlElement(name = "KeyColumns")
    protected OutOfLineBinding.KeyColumns keyColumns;
    @XmlElement(name = "ForeignKeyColumns")
    protected OutOfLineBinding.ForeignKeyColumns foreignKeyColumns;
    @XmlElement(name = "Translations")
    protected OutOfLineBinding.Translations translations;

    public String getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(String value) {
        this.databaseID = value;
    }

    public String getDimensionID() {
        return dimensionID;
    }

    public void setDimensionID(String value) {
        this.dimensionID = value;
    }

    public String getCubeID() {
        return cubeID;
    }

    public void setCubeID(String value) {
        this.cubeID = value;
    }

    public String getMeasureGroupID() {
        return measureGroupID;
    }

    public void setMeasureGroupID(String value) {
        this.measureGroupID = value;
    }

    public String getPartitionID() {
        return partitionID;
    }

    public void setPartitionID(String value) {
        this.partitionID = value;
    }

    public String getMiningModelID() {
        return miningModelID;
    }

    public void setMiningModelID(String value) {
        this.miningModelID = value;
    }

    public String getMiningStructureID() {
        return miningStructureID;
    }

    public void setMiningStructureID(String value) {
        this.miningStructureID = value;
    }

    public String getAttributeID() {
        return attributeID;
    }

    public void setAttributeID(String value) {
        this.attributeID = value;
    }

    public String getCubeDimensionID() {
        return cubeDimensionID;
    }

    public void setCubeDimensionID(String value) {
        this.cubeDimensionID = value;
    }

    public String getMeasureID() {
        return measureID;
    }

    public void setMeasureID(String value) {
        this.measureID = value;
    }

    public String getParentColumnID() {
        return parentColumnID;
    }

    public void setParentColumnID(String value) {
        this.parentColumnID = value;
    }

    public String getColumnID() {
        return columnID;
    }

    public void setColumnID(String value) {
        this.columnID = value;
    }

    public Binding getSource() {
        return source;
    }

    public void setSource(Binding value) {
        this.source = value;
    }

    public OutOfLineBinding.NameColumn getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(OutOfLineBinding.NameColumn value) {
        this.nameColumn = value;
    }

    public OutOfLineBinding.SkippedLevelsColumn getSkippedLevelsColumn() {
        return skippedLevelsColumn;
    }

    public void setSkippedLevelsColumn(OutOfLineBinding.SkippedLevelsColumn value) {
        this.skippedLevelsColumn = value;
    }

    public OutOfLineBinding.CustomRollupColumn getCustomRollupColumn() {
        return customRollupColumn;
    }

    public void setCustomRollupColumn(OutOfLineBinding.CustomRollupColumn value) {
        this.customRollupColumn = value;
    }

    public OutOfLineBinding.CustomRollupPropertiesColumn getCustomRollupPropertiesColumn() {
        return customRollupPropertiesColumn;
    }

    public void setCustomRollupPropertiesColumn(OutOfLineBinding.CustomRollupPropertiesColumn value) {
        this.customRollupPropertiesColumn = value;
    }

    public OutOfLineBinding.ValueColumn getValueColumn() {
        return valueColumn;
    }

    public void setValueColumn(OutOfLineBinding.ValueColumn value) {
        this.valueColumn = value;
    }

    public OutOfLineBinding.UnaryOperatorColumn getUnaryOperatorColumn() {
        return unaryOperatorColumn;
    }

    public void setUnaryOperatorColumn(OutOfLineBinding.UnaryOperatorColumn value) {
        this.unaryOperatorColumn = value;
    }

    public OutOfLineBinding.KeyColumns getKeyColumns() {
        return keyColumns;
    }

    public void setKeyColumns(OutOfLineBinding.KeyColumns value) {
        this.keyColumns = value;
    }

    public OutOfLineBinding.ForeignKeyColumns getForeignKeyColumns() {
        return foreignKeyColumns;
    }

    public void setForeignKeyColumns(OutOfLineBinding.ForeignKeyColumns value) {
        this.foreignKeyColumns = value;
    }

    public OutOfLineBinding.Translations getTranslations() {
        return translations;
    }

    public void setTranslations(OutOfLineBinding.Translations value) {
        this.translations = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class CustomRollupColumn {

        @XmlElement(name = "Source")
        protected Binding source;

        public Binding getSource() {
            return source;
        }

        public void setSource(Binding value) {
            this.source = value;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class CustomRollupPropertiesColumn {

        @XmlElement(name = "Source")
        protected Binding source;

        public Binding getSource() {
            return source;
        }

        public void setSource(Binding value) {
            this.source = value;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "foreignKeyColumn" })
    public static class ForeignKeyColumns {

        @XmlElement(name = "ForeignKeyColumn")
        protected List<OutOfLineBinding.ForeignKeyColumns.ForeignKeyColumn> foreignKeyColumn;

        public List<OutOfLineBinding.ForeignKeyColumns.ForeignKeyColumn> getForeignKeyColumn() {
            return this.foreignKeyColumn;
        }

        public void setForeignKeyColumn(List<ForeignKeyColumn> foreignKeyColumn) {
            this.foreignKeyColumn = foreignKeyColumn;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {

        })
        public static class ForeignKeyColumn {

            @XmlElement(name = "Source")
            protected Binding source;

            public Binding getSource() {
                return source;
            }

            public void setSource(Binding value) {
                this.source = value;
            }

        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "keyColumn" })
    public static class KeyColumns {

        @XmlElement(name = "KeyColumn")
        protected List<OutOfLineBinding.KeyColumns.KeyColumn> keyColumn;

        public List<OutOfLineBinding.KeyColumns.KeyColumn> getKeyColumn() {
            return this.keyColumn;
        }

        public void setKeyColumn(List<KeyColumn> keyColumn) {
            this.keyColumn = keyColumn;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {

        })
        public static class KeyColumn {

            @XmlElement(name = "Source")
            protected Binding source;

            public Binding getSource() {
                return source;
            }

            public void setSource(Binding value) {
                this.source = value;
            }
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class NameColumn {

        @XmlElement(name = "Source")
        protected Binding source;

        public Binding getSource() {
            return source;
        }

        public void setSource(Binding value) {
            this.source = value;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class SkippedLevelsColumn {

        @XmlElement(name = "Source")
        protected Binding source;

        public Binding getSource() {
            return source;
        }

        public void setSource(Binding value) {
            this.source = value;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "translation" })
    public static class Translations {

        @XmlElement(name = "Translation")
        protected List<OutOfLineBinding.Translations.Translation> translation;

        public List<OutOfLineBinding.Translations.Translation> getTranslation() {
            return this.translation;
        }

        public void setTranslation(List<Translation> translation) {
            this.translation = translation;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {

        })
        public static class Translation {

            @XmlElement(name = "Language")
            protected int language;
            @XmlElement(name = "Source")
            protected Binding source;

            public int getLanguage() {
                return language;
            }

            public void setLanguage(int value) {
                this.language = value;
            }

            public Binding getSource() {
                return source;
            }

            public void setSource(Binding value) {
                this.source = value;
            }

        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;all&gt;
     *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
     *       &lt;/all&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class UnaryOperatorColumn {

        @XmlElement(name = "Source")
        protected Binding source;

        /**
         * Gets the value of the source property.
         *
         * @return possible object is {@link Binding }
         */
        public Binding getSource() {
            return source;
        }

        /**
         * Sets the value of the source property.
         *
         * @param value allowed object is {@link Binding }
         */
        public void setSource(Binding value) {
            this.source = value;
        }

        public boolean isSetSource() {
            return (this.source != null);
        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;all&gt;
     *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
     *       &lt;/all&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class ValueColumn {

        @XmlElement(name = "Source")
        protected Binding source;

        /**
         * Gets the value of the source property.
         *
         * @return possible object is {@link Binding }
         */
        public Binding getSource() {
            return source;
        }

        /**
         * Sets the value of the source property.
         *
         * @param value allowed object is {@link Binding }
         */
        public void setSource(Binding value) {
            this.source = value;
        }

        public boolean isSetSource() {
            return (this.source != null);
        }

    }

}
