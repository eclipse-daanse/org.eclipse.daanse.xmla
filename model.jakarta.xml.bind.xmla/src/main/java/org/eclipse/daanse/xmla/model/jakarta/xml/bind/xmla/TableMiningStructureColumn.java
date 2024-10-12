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
@XmlType(name = "TableMiningStructureColumn", propOrder = {

})
public class TableMiningStructureColumn extends MiningStructureColumn {

    @XmlElement(name = "ForeignKeyColumn", type = DataItem.class)
    @XmlElementWrapper(name = "ForeignKeyColumns")
    protected List<DataItem> foreignKeyColumns;
    @XmlElement(name = "SourceMeasureGroup")
    protected MeasureGroupBinding sourceMeasureGroup;
    @XmlElement(name = "Column", type = MiningStructureColumn.class)
    @XmlElementWrapper(name = "Columns")
    protected List<MiningStructureColumn> columns;
    @XmlElement(name = "Translation", type = Translation.class)
    @XmlElementWrapper(name = "Translations")
    protected List<Translation> translations;

    public List<DataItem> getForeignKeyColumns() {
        return foreignKeyColumns;
    }

    public void setForeignKeyColumns(List<DataItem> value) {
        this.foreignKeyColumns = value;
    }

    public MeasureGroupBinding getSourceMeasureGroup() {
        return sourceMeasureGroup;
    }

    public void setSourceMeasureGroup(MeasureGroupBinding value) {
        this.sourceMeasureGroup = value;
    }

    public List<MiningStructureColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<MiningStructureColumn> value) {
        this.columns = value;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> value) {
        this.translations = value;
    }

}
