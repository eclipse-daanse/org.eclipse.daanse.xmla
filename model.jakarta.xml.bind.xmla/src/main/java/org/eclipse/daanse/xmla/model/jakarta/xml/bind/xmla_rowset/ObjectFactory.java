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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.discover.DiscoverPropertiesResponseRowXml;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
        // constructor
    }

    public Rowset createRowset() {
        return new Rowset();
    }

    public DiscoverPropertiesResponseRowXml createDiscoverPropertiesResponseRowXml() {
        return new DiscoverPropertiesResponseRowXml();
    }

}
