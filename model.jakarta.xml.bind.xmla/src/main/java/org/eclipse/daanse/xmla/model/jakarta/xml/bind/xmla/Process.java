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
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Process", propOrder = {

})
public class Process {

    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "Object", required = true)
    protected ObjectReference object;
    @XmlElement(name = "Bindings")
    protected Bindings bindings;
    @XmlElement(name = "DataSource")
    protected DataSource dataSource;
    @XmlElement(name = "DataSourceView")
    protected DataSourceView dataSourceView;
    @XmlElement(name = "ErrorConfiguration")
    protected ErrorConfiguration errorConfiguration;
    @XmlElement(name = "WriteBackTableCreation")
    @XmlSchemaType(name = "string")
    protected WriteBackTableCreation writeBackTableCreation;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ObjectReference getObject() {
        return object;
    }

    public void setObject(ObjectReference object) {
        this.object = object;
    }

    public Bindings getBindings() {
        return bindings;
    }

    public void setBindings(Bindings bindings) {
        this.bindings = bindings;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSourceView getDataSourceView() {
        return dataSourceView;
    }

    public void setDataSourceView(DataSourceView dataSourceView) {
        this.dataSourceView = dataSourceView;
    }

    public ErrorConfiguration getErrorConfiguration() {
        return errorConfiguration;
    }

    public void setErrorConfiguration(ErrorConfiguration errorConfiguration) {
        this.errorConfiguration = errorConfiguration;
    }

    public WriteBackTableCreation getWriteBackTableCreation() {
        return writeBackTableCreation;
    }

    public void setWriteBackTableCreation(WriteBackTableCreation writeBackTableCreation) {
        this.writeBackTableCreation = writeBackTableCreation;
    }
}
