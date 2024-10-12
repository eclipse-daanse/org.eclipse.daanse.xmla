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
@XmlType(name = "DrillThroughAction", propOrder = { "defaultFlag", "columns", "maximumRows" })
public class DrillThroughAction extends Action {

    @XmlElement(name = "Default")
    protected Boolean defaultFlag;
    @XmlElement(name = "Column")
    @XmlElementWrapper(name = "Columns")
    protected List<Binding> columns;
    @XmlElement(name = "MaximumRows")
    protected Integer maximumRows;

    public Boolean isDefault() {
        return defaultFlag;
    }

    public void setDefault(Boolean value) {
        this.defaultFlag = value;
    }

    public List<Binding> getColumns() {
        return columns;
    }

    public void setColumns(List<Binding> value) {
        this.columns = value;
    }

    public Integer getMaximumRows() {
        return maximumRows;
    }

    public void setMaximumRows(Integer value) {
        this.maximumRows = value;
    }

}
