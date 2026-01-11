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
package org.eclipse.daanse.xmla.server.adapter.soapmessage;

import java.util.Iterator;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.properties.PropertyListElementDefinition;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;

import jakarta.xml.soap.Node;
import jakarta.xml.soap.SOAPElement;

/**
 * Shared utility for parsing Properties from SOAP elements. Used by both
 * ExecuteDispatcher and DiscoverDispatcher.
 */
public class PropertyConverter {

    private PropertyConverter() {
        // utility class
    }

    /**
     * Parse Properties from a SOAP element.
     *
     * @param propertiesElement the SOAP element containing properties
     * @return parsed PropertiesR object
     */
    public static PropertiesR propertiestoProperties(SOAPElement propertiesElement) {
        Iterator<Node> nodeIterator = propertiesElement.getChildElements();
        while (nodeIterator.hasNext()) {
            Node node = nodeIterator.next();
            if (node instanceof SOAPElement propertyList
                    && Constants.MSXMLA.QN_PROPERTY_LIST.equals(propertyList.getElementQName())) {
                return propertyListToProperties(propertyList);
            }
        }
        return new PropertiesR();
    }

    private static PropertiesR propertyListToProperties(SOAPElement propertyList) {
        PropertiesR properties = new PropertiesR();

        Iterator<Node> nodeIteratorPropertyList = propertyList.getChildElements();
        while (nodeIteratorPropertyList.hasNext()) {
            Node n = nodeIteratorPropertyList.next();

            if (n instanceof SOAPElement propertyListElement) {
                String name = propertyListElement.getLocalName();
                Optional<PropertyListElementDefinition> opd = PropertyListElementDefinition.byNameValue(name);
                if (opd.isPresent()) {
                    opd.ifPresent(pd -> properties.addProperty(pd, propertyListElement.getTextContent()));
                } else {
                    properties.setByname(name, propertyListElement.getTextContent());
                }
            }
        }
        return properties;
    }
}
