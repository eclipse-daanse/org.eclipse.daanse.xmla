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

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import javax.xml.namespace.QName;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Helper class for XML node parsing operations. Contains generic methods for
 * parsing NodeLists into typed collections and values.
 */
public class XmlNodeHelper {

    private XmlNodeHelper() {
        // utility class
    }

    /**
     * Generic method to parse a list of elements from a NodeList.
     *
     * @param nl       NodeList to parse
     * @param nodeName Name of the nodes to match
     * @param factory  Function to convert NodeList to the target type
     * @param <T>      The type of elements in the resulting list
     * @return List of parsed elements
     */
    public static <T> List<T> getList(NodeList nl, String nodeName, Function<NodeList, T> factory) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, nodeName)) {
                list.add(factory.apply(node.getChildNodes()));
            }
        }
        return list;
    }

    public static String getAttribute(NamedNodeMap attributes, String name) {
        if (attributes == null) {
            return null;
        }
        org.w3c.dom.Node node = attributes.getNamedItem(name);
        if (node == null) {
            return null;
        }
        return node.getNodeValue();
    }

    public static String getNodeType(org.w3c.dom.Node node) {
        if (node == null || node.getAttributes() == null) {
            return null;
        }
        return getAttribute(node.getAttributes(), "xsi:type");
    }

    public static Boolean toBoolean(String s) {
        if (s == null) {
            return null;
        }
        return Boolean.parseBoolean(s);
    }

    public static Integer toInteger(String s) {
        if (s == null) {
            return null;
        }
        return Integer.parseInt(s);
    }

    public static Long toLong(String s) {
        if (s == null) {
            return null;
        }
        return Long.parseLong(s);
    }

    public static BigInteger toBigInteger(String s) {
        if (s == null) {
            return null;
        }
        return new BigInteger(s);
    }

    public static Instant toInstant(String s) {
        if (s == null) {
            return null;
        }
        return Instant.parse(s);
    }

    public static Duration toDuration(String s) {
        if (s == null) {
            return null;
        }
        return Duration.parse(s);
    }

    /**
     * Get text content from a node with a default value if null.
     *
     * @param node         The node to get text content from
     * @param defaultValue The default value if node is null or has no text content
     * @return The text content or default value
     */
    public static String getTextContent(Node node, String defaultValue) {
        if (node == null) {
            return defaultValue;
        }
        String content = node.getTextContent();
        return content != null ? content : defaultValue;
    }

    /**
     * Get optional text content from a node.
     *
     * @param node The node to get text content from
     * @return Optional containing the text content, or empty if node is null
     */
    public static Optional<String> getOptionalText(Node node) {
        if (node == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(node.getTextContent());
    }

    /**
     * Convert a NodeList to a Map of node names to text content values. Useful for
     * parsing simple key-value style XML structures. Note: This method uses local
     * names (without prefix) for namespace-safe behavior.
     *
     * @param nl The NodeList to convert
     * @return Map of local node names to text content values
     */
    public static Map<String, String> nodeListToMap(NodeList nl) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node != null) {
                String localName = node.getLocalName();
                if (localName == null) {
                    localName = extractLocalName(node.getNodeName());
                }
                map.put(localName, node.getTextContent());
            }
        }
        return map;
    }

    /**
     * Generic method to parse a list of string values from a NodeList.
     *
     * @param nl       NodeList to parse
     * @param nodeName Name of the nodes to match
     * @return List of text content values
     */
    public static List<String> getStringList(NodeList nl, String nodeName) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (matchesLocalName(node, nodeName)) {
                list.add(node.getTextContent());
            }
        }
        return list;
    }

    /**
     * Find the first node with the given name and return its text content.
     *
     * @param nl       NodeList to search
     * @param nodeName Name of the node to find
     * @return Text content of the first matching node, or null if not found
     */
    public static String findNodeValue(NodeList nl, String nodeName) {
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (matchesLocalName(node, nodeName)) {
                return node.getTextContent();
            }
        }
        return null;
    }

    /**
     * Find the first node with the given name and return its text content as
     * Optional.
     *
     * @param nl       NodeList to search
     * @param nodeName Name of the node to find
     * @return Optional containing text content, or empty if not found
     */
    public static Optional<String> findOptionalNodeValue(NodeList nl, String nodeName) {
        return Optional.ofNullable(findNodeValue(nl, nodeName));
    }

    /**
     * Find a node by name and apply a converter function to its text content.
     *
     * @param nl        NodeList to search
     * @param nodeName  Name of the node to find
     * @param converter Function to convert the text content
     * @param <T>       The result type
     * @return Converted value, or null if node not found
     */
    public static <T> T findNodeValue(NodeList nl, String nodeName, java.util.function.Function<String, T> converter) {
        String value = findNodeValue(nl, nodeName);
        return value != null ? converter.apply(value) : null;
    }

    /**
     * Find a node by name and return its text content as Boolean.
     *
     * @param nl       NodeList to search
     * @param nodeName Name of the node to find
     * @return Boolean value, or null if not found
     */
    public static Boolean findBooleanValue(NodeList nl, String nodeName) {
        return findNodeValue(nl, nodeName, XmlNodeHelper::toBoolean);
    }

    /**
     * Find a node by name and return its text content as Long.
     *
     * @param nl       NodeList to search
     * @param nodeName Name of the node to find
     * @return Long value, or null if not found
     */
    public static Long findLongValue(NodeList nl, String nodeName) {
        return findNodeValue(nl, nodeName, XmlNodeHelper::toLong);
    }

    /**
     * Find a node by name and return its text content as Integer.
     *
     * @param nl       NodeList to search
     * @param nodeName Name of the node to find
     * @return Integer value, or null if not found
     */
    public static Integer findIntegerValue(NodeList nl, String nodeName) {
        return findNodeValue(nl, nodeName, XmlNodeHelper::toInteger);
    }

    /**
     * Find a node by name and return its text content as Instant.
     *
     * @param nl       NodeList to search
     * @param nodeName Name of the node to find
     * @return Instant value, or null if not found
     */
    public static Instant findInstantValue(NodeList nl, String nodeName) {
        return findNodeValue(nl, nodeName, XmlNodeHelper::toInstant);
    }

    /**
     * Find a node by name and return its text content as BigInteger.
     *
     * @param nl       NodeList to search
     * @param nodeName Name of the node to find
     * @return BigInteger value, or null if not found
     */
    public static BigInteger findBigIntegerValue(NodeList nl, String nodeName) {
        return findNodeValue(nl, nodeName, XmlNodeHelper::toBigInteger);
    }

    /**
     * Find a node by name and return its text content as Duration.
     *
     * @param nl       NodeList to search
     * @param nodeName Name of the node to find
     * @return Duration value, or null if not found
     */
    public static Duration findDurationValue(NodeList nl, String nodeName) {
        return findNodeValue(nl, nodeName, XmlNodeHelper::toDuration);
    }

    /**
     * Find the first node with the given name.
     *
     * @param nl       NodeList to search
     * @param nodeName Name of the node to find
     * @return The first matching node, or null if not found
     */
    public static Node findNode(NodeList nl, String nodeName) {
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (matchesLocalName(node, nodeName)) {
                return node;
            }
        }
        return null;
    }

    // ========== QName-based namespace-aware methods ==========

    /**
     * Check if a DOM node matches a QName (namespace URI + local name). This method
     * is namespace-aware and handles both prefixed and unprefixed elements.
     *
     * @param node  The node to check
     * @param qname The QName to match against
     * @return true if the node matches the QName
     */
    public static boolean matchesQName(Node node, QName qname) {
        if (node == null || qname == null) {
            return false;
        }
        String localName = node.getLocalName();
        // Fallback for non-namespace-aware parsing
        if (localName == null) {
            localName = extractLocalName(node.getNodeName());
        }
        return Objects.equals(qname.getLocalPart(), localName)
                && Objects.equals(qname.getNamespaceURI(), node.getNamespaceURI());
    }

    /**
     * Check if a DOM node matches a local name (for elements without namespace
     * validation). This method handles both prefixed and unprefixed element names.
     *
     * @param node      The node to check
     * @param localName The local name to match
     * @return true if the node's local name matches
     */
    public static boolean matchesLocalName(Node node, String localName) {
        if (node == null || localName == null) {
            return false;
        }
        String nodeLocalName = node.getLocalName();
        if (nodeLocalName == null) {
            nodeLocalName = extractLocalName(node.getNodeName());
        }
        return localName.equals(nodeLocalName);
    }

    /**
     * Extract the local name from a potentially prefixed node name.
     *
     * @param nodeName The node name (may include prefix like "ns:localName")
     * @return The local name without prefix
     */
    public static String extractLocalName(String nodeName) {
        if (nodeName == null) {
            return null;
        }
        int colonIndex = nodeName.indexOf(':');
        return colonIndex >= 0 ? nodeName.substring(colonIndex + 1) : nodeName;
    }

    /**
     * Generic method to parse a list of elements from a NodeList using QName
     * matching.
     *
     * @param nl      NodeList to parse
     * @param qname   QName of the nodes to match
     * @param factory Function to convert NodeList to the target type
     * @param <T>     The type of elements in the resulting list
     * @return List of parsed elements
     */
    public static <T> List<T> getList(NodeList nl, QName qname, Function<NodeList, T> factory) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (matchesQName(node, qname)) {
                list.add(factory.apply(node.getChildNodes()));
            }
        }
        return list;
    }

    /**
     * Find the first node matching the QName and return its text content.
     *
     * @param nl    NodeList to search
     * @param qname QName of the node to find
     * @return Text content of the first matching node, or null if not found
     */
    public static String findNodeValue(NodeList nl, QName qname) {
        Node node = findNode(nl, qname);
        return node != null ? node.getTextContent() : null;
    }

    /**
     * Find the first node matching the QName.
     *
     * @param nl    NodeList to search
     * @param qname QName of the node to find
     * @return The first matching node, or null if not found
     */
    public static Node findNode(NodeList nl, QName qname) {
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (matchesQName(node, qname)) {
                return node;
            }
        }
        return null;
    }

    /**
     * Generic method to parse a list of elements from a NodeList using local name
     * matching. This is useful when namespace validation is not required.
     *
     * @param nl        NodeList to parse
     * @param localName Local name of the nodes to match
     * @param factory   Function to convert NodeList to the target type
     * @param <T>       The type of elements in the resulting list
     * @return List of parsed elements
     */
    public static <T> List<T> getListByLocalName(NodeList nl, String localName, Function<NodeList, T> factory) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (matchesLocalName(node, localName)) {
                list.add(factory.apply(node.getChildNodes()));
            }
        }
        return list;
    }

    /**
     * Find the first node matching the local name and return its text content.
     *
     * @param nl        NodeList to search
     * @param localName Local name of the node to find
     * @return Text content of the first matching node, or null if not found
     */
    public static String findNodeValueByLocalName(NodeList nl, String localName) {
        Node node = findNodeByLocalName(nl, localName);
        return node != null ? node.getTextContent() : null;
    }

    /**
     * Find the first node matching the local name.
     *
     * @param nl        NodeList to search
     * @param localName Local name of the node to find
     * @return The first matching node, or null if not found
     */
    public static Node findNodeByLocalName(NodeList nl, String localName) {
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (matchesLocalName(node, localName)) {
                return node;
            }
        }
        return null;
    }

    /**
     * Generic method to parse a list of string values from a NodeList using local
     * name matching.
     *
     * @param nl        NodeList to parse
     * @param localName Local name of the nodes to match
     * @return List of text content values
     */
    public static List<String> getStringListByLocalName(NodeList nl, String localName) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (matchesLocalName(node, localName)) {
                list.add(node.getTextContent());
            }
        }
        return list;
    }
}
