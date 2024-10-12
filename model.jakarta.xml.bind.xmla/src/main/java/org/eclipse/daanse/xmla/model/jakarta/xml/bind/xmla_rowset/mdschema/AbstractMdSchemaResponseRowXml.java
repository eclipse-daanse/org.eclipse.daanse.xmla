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

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.Row;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractMdSchemaResponseRowXml")
public class AbstractMdSchemaResponseRowXml extends Row {

    /**
     * The name of the database.
     */
    @XmlElement(name = "CATALOG_NAME", required = false)
    private String catalogName;

    /**
     * The name of the schema.
     */
    @XmlElement(name = "SCHEMA_NAME", required = false)
    private String schemaName;

    /**
     * The name of the cube.
     */
    @XmlElement(name = "CUBE_NAME", required = false)
    private String cubeName;

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getCubeName() {
        return cubeName;
    }

    public void setCubeName(String cubeName) {
        this.cubeName = cubeName;
    }
}
