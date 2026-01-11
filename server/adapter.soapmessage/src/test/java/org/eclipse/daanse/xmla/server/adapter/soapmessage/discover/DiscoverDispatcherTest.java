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
package org.eclipse.daanse.xmla.server.adapter.soapmessage.discover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import javax.xml.namespace.QName;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;

class DiscoverDispatcherTest {

    private static final String XMLA_NS = "urn:schemas-microsoft-com:xml-analysis";
    private static final QName QN_RESTRICTIONS = new QName(XMLA_NS, "Restrictions");
    private static final QName QN_RESTRICTION_LIST = new QName(XMLA_NS, "RestrictionList");

    private SOAPMessage soapMessage;
    private SOAPElement bodyElement;

    @BeforeEach
    void setUp() throws Exception {
        MessageFactory factory = MessageFactory.newInstance();
        soapMessage = factory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        bodyElement = envelope.getBody().addChildElement("TestElement");
    }

    @Nested
    class GetRestrictionMapTests {

        @Test
        void getRestrictionMap_withRestrictions() throws Exception {
            // getRestrictionMap expects the Restrictions element directly
            SOAPElement restrictions = bodyElement.addChildElement(QN_RESTRICTIONS);
            SOAPElement restrictionList = restrictions.addChildElement(QN_RESTRICTION_LIST);
            restrictionList.addChildElement("CATALOG_NAME").setTextContent("TestCatalog");
            restrictionList.addChildElement("SCHEMA_NAME").setTextContent("TestSchema");

            Map<String, String> result = DiscoverDispatcher.getRestrictionMap(restrictions);

            assertEquals(2, result.size());
            assertEquals("TestCatalog", result.get("CATALOG_NAME"));
            assertEquals("TestSchema", result.get("SCHEMA_NAME"));
        }

        @Test
        void getRestrictionMap_emptyRestrictionList() throws Exception {
            SOAPElement restrictions = bodyElement.addChildElement(QN_RESTRICTIONS);
            restrictions.addChildElement(QN_RESTRICTION_LIST);

            Map<String, String> result = DiscoverDispatcher.getRestrictionMap(restrictions);

            assertTrue(result.isEmpty());
        }

        @Test
        void getRestrictionMap_noRestrictionList() throws Exception {
            SOAPElement restrictions = bodyElement.addChildElement(QN_RESTRICTIONS);

            Map<String, String> result = DiscoverDispatcher.getRestrictionMap(restrictions);

            assertTrue(result.isEmpty());
        }

        @Test
        void getRestrictionMap_singleRestriction() throws Exception {
            SOAPElement restrictions = bodyElement.addChildElement(QN_RESTRICTIONS);
            SOAPElement restrictionList = restrictions.addChildElement(QN_RESTRICTION_LIST);
            restrictionList.addChildElement("CUBE_NAME").setTextContent("SalesCube");

            Map<String, String> result = DiscoverDispatcher.getRestrictionMap(restrictions);

            assertEquals(1, result.size());
            assertEquals("SalesCube", result.get("CUBE_NAME"));
        }

        @Test
        void getRestrictionMap_withEmptyValue() throws Exception {
            SOAPElement restrictions = bodyElement.addChildElement(QN_RESTRICTIONS);
            SOAPElement restrictionList = restrictions.addChildElement(QN_RESTRICTION_LIST);
            restrictionList.addChildElement("EMPTY_RESTRICTION").setTextContent("");

            Map<String, String> result = DiscoverDispatcher.getRestrictionMap(restrictions);

            assertEquals(1, result.size());
            assertEquals("", result.get("EMPTY_RESTRICTION"));
        }
    }
}
