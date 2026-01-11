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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

class XmlNodeHelperTest {

    private DocumentBuilder documentBuilder;

    @BeforeEach
    void setUp() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        documentBuilder = factory.newDocumentBuilder();
    }

    private Document parseXml(String xml) throws Exception {
        return documentBuilder.parse(new InputSource(new StringReader(xml)));
    }

    @Nested
    class ConversionMethods {

        @Test
        void toBoolean_nullReturnsNull() {
            assertNull(XmlNodeHelper.toBoolean(null));
        }

        @Test
        void toBoolean_trueString() {
            assertTrue(XmlNodeHelper.toBoolean("true"));
        }

        @Test
        void toBoolean_falseString() {
            assertFalse(XmlNodeHelper.toBoolean("false"));
        }

        @Test
        void toInteger_nullReturnsNull() {
            assertNull(XmlNodeHelper.toInteger(null));
        }

        @Test
        void toInteger_validNumber() {
            assertEquals(Integer.valueOf(42), XmlNodeHelper.toInteger("42"));
        }

        @Test
        void toInteger_negativeNumber() {
            assertEquals(Integer.valueOf(-123), XmlNodeHelper.toInteger("-123"));
        }

        @Test
        void toLong_nullReturnsNull() {
            assertNull(XmlNodeHelper.toLong(null));
        }

        @Test
        void toLong_validNumber() {
            assertEquals(Long.valueOf(9876543210L), XmlNodeHelper.toLong("9876543210"));
        }

        @Test
        void toBigInteger_nullReturnsNull() {
            assertNull(XmlNodeHelper.toBigInteger(null));
        }

        @Test
        void toBigInteger_validNumber() {
            assertEquals(new BigInteger("12345678901234567890"), XmlNodeHelper.toBigInteger("12345678901234567890"));
        }

        @Test
        void toInstant_nullReturnsNull() {
            assertNull(XmlNodeHelper.toInstant(null));
        }

        @Test
        void toInstant_validIsoString() {
            Instant expected = Instant.parse("2023-06-15T10:30:00Z");
            assertEquals(expected, XmlNodeHelper.toInstant("2023-06-15T10:30:00Z"));
        }

        @Test
        void toDuration_nullReturnsNull() {
            assertNull(XmlNodeHelper.toDuration(null));
        }

        @Test
        void toDuration_validIsoString() {
            Duration expected = Duration.parse("PT2H30M");
            assertEquals(expected, XmlNodeHelper.toDuration("PT2H30M"));
        }
    }

    @Nested
    class AttributeMethods {

        @Test
        void getAttribute_nullAttributesReturnsNull() {
            assertNull(XmlNodeHelper.getAttribute(null, "test"));
        }

        @Test
        void getAttribute_existingAttribute() throws Exception {
            Document doc = parseXml("<root attr=\"value\"/>");
            NamedNodeMap attributes = doc.getDocumentElement().getAttributes();
            assertEquals("value", XmlNodeHelper.getAttribute(attributes, "attr"));
        }

        @Test
        void getAttribute_nonExistingAttribute() throws Exception {
            Document doc = parseXml("<root attr=\"value\"/>");
            NamedNodeMap attributes = doc.getDocumentElement().getAttributes();
            assertNull(XmlNodeHelper.getAttribute(attributes, "nonexistent"));
        }

        @Test
        void getNodeType_nullNodeReturnsNull() {
            assertNull(XmlNodeHelper.getNodeType(null));
        }

        @Test
        void getNodeType_nodeWithXsiType() throws Exception {
            Document doc = parseXml(
                    "<root xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"MyType\"/>");
            assertEquals("MyType", XmlNodeHelper.getNodeType(doc.getDocumentElement()));
        }

        @Test
        void getNodeType_nodeWithoutXsiType() throws Exception {
            Document doc = parseXml("<root attr=\"value\"/>");
            assertNull(XmlNodeHelper.getNodeType(doc.getDocumentElement()));
        }
    }

    @Nested
    class TextContentMethods {

        @Test
        void getTextContent_nullNodeReturnsDefault() {
            assertEquals("default", XmlNodeHelper.getTextContent(null, "default"));
        }

        @Test
        void getTextContent_nodeWithContent() throws Exception {
            Document doc = parseXml("<root>content</root>");
            assertEquals("content", XmlNodeHelper.getTextContent(doc.getDocumentElement(), "default"));
        }

        @Test
        void getOptionalText_nullNodeReturnsEmpty() {
            assertEquals(Optional.empty(), XmlNodeHelper.getOptionalText(null));
        }

        @Test
        void getOptionalText_nodeWithContent() throws Exception {
            Document doc = parseXml("<root>content</root>");
            assertEquals(Optional.of("content"), XmlNodeHelper.getOptionalText(doc.getDocumentElement()));
        }
    }

    @Nested
    class NodeListMethods {

        @Test
        void nodeListToMap_convertsCorrectly() throws Exception {
            Document doc = parseXml("<root><name>Test</name><value>123</value></root>");
            NodeList nl = doc.getDocumentElement().getChildNodes();
            Map<String, String> map = XmlNodeHelper.nodeListToMap(nl);
            assertEquals("Test", map.get("name"));
            assertEquals("123", map.get("value"));
        }

        @Test
        void getStringList_extractsMatchingNodes() throws Exception {
            Document doc = parseXml("<root><item>A</item><item>B</item><other>C</other></root>");
            NodeList nl = doc.getDocumentElement().getChildNodes();
            List<String> list = XmlNodeHelper.getStringList(nl, "item");
            assertEquals(2, list.size());
            assertEquals("A", list.get(0));
            assertEquals("B", list.get(1));
        }

        @Test
        void getStringList_noMatchesReturnsEmpty() throws Exception {
            Document doc = parseXml("<root><other>C</other></root>");
            NodeList nl = doc.getDocumentElement().getChildNodes();
            List<String> list = XmlNodeHelper.getStringList(nl, "item");
            assertTrue(list.isEmpty());
        }

        @Test
        void getList_withFactoryFunction() throws Exception {
            Document doc = parseXml("<root><item><sub>X</sub></item><item><sub>Y</sub></item></root>");
            NodeList nl = doc.getDocumentElement().getChildNodes();
            List<String> list = XmlNodeHelper.getList(nl, "item", childNodes -> childNodes.item(0).getTextContent());
            assertEquals(2, list.size());
            assertEquals("X", list.get(0));
            assertEquals("Y", list.get(1));
        }
    }

    @Nested
    class FindNodeValueMethods {

        @Test
        void findNodeValue_existingNode() throws Exception {
            Document doc = parseXml("<root><name>Test</name><value>123</value></root>");
            NodeList nl = doc.getDocumentElement().getChildNodes();
            assertEquals("Test", XmlNodeHelper.findNodeValue(nl, "name"));
            assertEquals("123", XmlNodeHelper.findNodeValue(nl, "value"));
        }

        @Test
        void findNodeValue_nonExistingNode() throws Exception {
            Document doc = parseXml("<root><name>Test</name></root>");
            NodeList nl = doc.getDocumentElement().getChildNodes();
            assertNull(XmlNodeHelper.findNodeValue(nl, "nonexistent"));
        }

        @Test
        void findOptionalNodeValue_existingNode() throws Exception {
            Document doc = parseXml("<root><name>Test</name></root>");
            NodeList nl = doc.getDocumentElement().getChildNodes();
            assertEquals(Optional.of("Test"), XmlNodeHelper.findOptionalNodeValue(nl, "name"));
        }

        @Test
        void findOptionalNodeValue_nonExistingNode() throws Exception {
            Document doc = parseXml("<root><name>Test</name></root>");
            NodeList nl = doc.getDocumentElement().getChildNodes();
            assertEquals(Optional.empty(), XmlNodeHelper.findOptionalNodeValue(nl, "nonexistent"));
        }

        @Test
        void findNodeValue_withConverter() throws Exception {
            Document doc = parseXml("<root><count>42</count></root>");
            NodeList nl = doc.getDocumentElement().getChildNodes();
            Integer result = XmlNodeHelper.findNodeValue(nl, "count", Integer::parseInt);
            assertEquals(Integer.valueOf(42), result);
        }

        @Test
        void findNodeValue_withConverter_nonExistingNode() throws Exception {
            Document doc = parseXml("<root><name>Test</name></root>");
            NodeList nl = doc.getDocumentElement().getChildNodes();
            Integer result = XmlNodeHelper.findNodeValue(nl, "count", Integer::parseInt);
            assertNull(result);
        }
    }

    @Nested
    class TypedFindValueTests {

        @Test
        void findBooleanValue_returnsBoolean() throws Exception {
            String xml = "<root><enabled>true</enabled></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Boolean result = XmlNodeHelper.findBooleanValue(nl, "enabled");
            assertTrue(result);
        }

        @Test
        void findBooleanValue_returnsFalse() throws Exception {
            String xml = "<root><enabled>false</enabled></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Boolean result = XmlNodeHelper.findBooleanValue(nl, "enabled");
            assertFalse(result);
        }

        @Test
        void findLongValue_returnsLong() throws Exception {
            String xml = "<root><size>123456789012</size></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Long result = XmlNodeHelper.findLongValue(nl, "size");
            assertEquals(123456789012L, result);
        }

        @Test
        void findIntegerValue_returnsInteger() throws Exception {
            String xml = "<root><count>42</count></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Integer result = XmlNodeHelper.findIntegerValue(nl, "count");
            assertEquals(42, result);
        }

        @Test
        void findInstantValue_returnsInstant() throws Exception {
            String xml = "<root><timestamp>2024-01-15T10:30:00Z</timestamp></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            java.time.Instant result = XmlNodeHelper.findInstantValue(nl, "timestamp");
            assertNotNull(result);
            assertEquals(java.time.Instant.parse("2024-01-15T10:30:00Z"), result);
        }

        @Test
        void findBigIntegerValue_returnsBigInteger() throws Exception {
            String xml = "<root><bignum>999999999999999999999</bignum></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            java.math.BigInteger result = XmlNodeHelper.findBigIntegerValue(nl, "bignum");
            assertEquals(new java.math.BigInteger("999999999999999999999"), result);
        }

        @Test
        void findDurationValue_returnsDuration() throws Exception {
            String xml = "<root><duration>PT1H30M</duration></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            java.time.Duration result = XmlNodeHelper.findDurationValue(nl, "duration");
            assertEquals(java.time.Duration.parse("PT1H30M"), result);
        }

        @Test
        void findNode_returnsNode() throws Exception {
            String xml = "<root><child>text</child><other>value</other></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            org.w3c.dom.Node result = XmlNodeHelper.findNode(nl, "child");
            assertNotNull(result);
            assertEquals("child", result.getNodeName());
            assertEquals("text", result.getTextContent());
        }

        @Test
        void findNode_notFound_returnsNull() throws Exception {
            String xml = "<root><child>text</child></root>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            org.w3c.dom.Node result = XmlNodeHelper.findNode(nl, "nonexistent");
            assertNull(result);
        }
    }
}
