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
package org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter;

import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ANNOTATIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CREATED_TIMESTAMP;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CUBE_DIMENSION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ESTIMATED_ROWS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LAST_SCHEMA_UPDATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getListByLocalName;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.matchesLocalName;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInstant;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInteger;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toLong;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.Aggregation;
import org.eclipse.daanse.xmla.api.xmla.AggregationAttribute;
import org.eclipse.daanse.xmla.api.xmla.AggregationDesign;
import org.eclipse.daanse.xmla.api.xmla.AggregationDesignAttribute;
import org.eclipse.daanse.xmla.api.xmla.AggregationDesignDimension;
import org.eclipse.daanse.xmla.api.xmla.AggregationDimension;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.model.record.xmla.AggregationAttributeR;
import org.eclipse.daanse.xmla.model.record.xmla.AggregationDesignAttributeR;
import org.eclipse.daanse.xmla.model.record.xmla.AggregationDesignDimensionR;
import org.eclipse.daanse.xmla.model.record.xmla.AggregationDesignR;
import org.eclipse.daanse.xmla.model.record.xmla.AggregationDimensionR;
import org.eclipse.daanse.xmla.model.record.xmla.AggregationR;
import org.w3c.dom.NodeList;

/**
 * Converter for Aggregation-related XMLA types. Handles AggregationDesign,
 * Aggregation, and related nested types.
 */
public class AggregationConverter {

    private AggregationConverter() {
        // utility class
    }

    /**
     * Functional interface for annotation list parsing. Allows AggregationConverter
     * to use shared annotation parsing from Convert.
     */
    @FunctionalInterface
    public interface AnnotationListParser {
        List<Annotation> getAnnotationList(NodeList nl);
    }

    public static AggregationDesign getAggregationDesign(NodeList nl, AnnotationListParser annotationParser) {
        String name = null;
        Optional<String> id = Optional.empty();
        Optional<Instant> createdTimestamp = Optional.empty();
        Optional<Instant> lastSchemaUpdate = Optional.empty();
        Optional<String> description = Optional.empty();
        Optional<List<Annotation>> annotations = Optional.empty();
        Optional<Long> estimatedRows = Optional.empty();
        Optional<List<AggregationDesignDimension>> dimensions = Optional.empty();
        Optional<List<Aggregation>> aggregations = Optional.empty();
        Optional<Integer> estimatedPerformanceGain = Optional.empty();

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, NAME)) {
                    name = node.getTextContent();
                }
                if (matchesLocalName(node, ID)) {
                    id = Optional.ofNullable(node.getTextContent());
                }
                if (matchesLocalName(node, CREATED_TIMESTAMP)) {
                    createdTimestamp = Optional.ofNullable(toInstant(node.getTextContent()));
                }
                if (matchesLocalName(node, LAST_SCHEMA_UPDATE)) {
                    lastSchemaUpdate = Optional.ofNullable(toInstant(node.getTextContent()));
                }
                if (matchesLocalName(node, DESCRIPTION)) {
                    description = Optional.ofNullable(node.getTextContent());
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = Optional.ofNullable(annotationParser.getAnnotationList(node.getChildNodes()));
                }
                if (matchesLocalName(node, ESTIMATED_ROWS)) {
                    estimatedRows = Optional.ofNullable(toLong(node.getTextContent()));
                }
                if (matchesLocalName(node, DIMENSIONS)) {
                    dimensions = Optional
                            .ofNullable(getAggregationDesignDimensionList(node.getChildNodes(), annotationParser));
                }
                if (matchesLocalName(node, "Aggregations")) {
                    aggregations = Optional.ofNullable(getAggregationList(node.getChildNodes(), annotationParser));
                }
                if (matchesLocalName(node, "EstimatedPerformanceGain")) {
                    estimatedPerformanceGain = Optional.ofNullable(toInteger(node.getTextContent()));
                }
            }
        }
        return new AggregationDesignR(name, id, createdTimestamp, lastSchemaUpdate, description, annotations,
                estimatedRows, dimensions, aggregations, estimatedPerformanceGain);
    }

    public static List<Aggregation> getAggregationList(NodeList nl, AnnotationListParser annotationParser) {
        return getListByLocalName(nl, "Aggregation", childNl -> getAggregation(childNl, annotationParser));
    }

    public static Aggregation getAggregation(NodeList nl, AnnotationListParser annotationParser) {
        Optional<String> id = Optional.empty();
        String name = null;
        Optional<List<AggregationDimension>> dimensions = Optional.empty();
        Optional<List<Annotation>> annotations = Optional.empty();
        Optional<String> description = Optional.empty();

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, ID)) {
                    id = Optional.ofNullable(node.getTextContent());
                }
                if (matchesLocalName(node, NAME)) {
                    name = node.getTextContent();
                }
                if (matchesLocalName(node, DESCRIPTION)) {
                    description = Optional.ofNullable(node.getTextContent());
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = Optional.ofNullable(annotationParser.getAnnotationList(node.getChildNodes()));
                }
                if (matchesLocalName(node, DIMENSIONS)) {
                    dimensions = Optional
                            .ofNullable(getAggregationDimensionList(node.getChildNodes(), annotationParser));
                }
            }
        }
        return new AggregationR(id, name, dimensions, annotations, description);
    }

    public static List<AggregationDimension> getAggregationDimensionList(NodeList nl,
            AnnotationListParser annotationParser) {
        return getListByLocalName(nl, DIMENSION, childNl -> getAggregationDimension(childNl, annotationParser));
    }

    public static AggregationDimension getAggregationDimension(NodeList nl, AnnotationListParser annotationParser) {
        String cubeDimensionID = null;
        Optional<List<AggregationAttribute>> attributes = Optional.empty();
        Optional<List<Annotation>> annotations = Optional.empty();

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, CUBE_DIMENSION_ID)) {
                    cubeDimensionID = node.getTextContent();
                }
                if (matchesLocalName(node, ATTRIBUTES)) {
                    attributes = Optional
                            .ofNullable(getAggregationAttributeList(node.getChildNodes(), annotationParser));
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = Optional.ofNullable(annotationParser.getAnnotationList(node.getChildNodes()));
                }
            }
        }
        return new AggregationDimensionR(cubeDimensionID, attributes, annotations);
    }

    public static List<AggregationAttribute> getAggregationAttributeList(NodeList nl,
            AnnotationListParser annotationParser) {
        return getListByLocalName(nl, ATTRIBUTE, childNl -> getAggregationAttribute(childNl, annotationParser));
    }

    public static AggregationAttribute getAggregationAttribute(NodeList nl, AnnotationListParser annotationParser) {
        String attributeID = null;
        List<Annotation> annotations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, ATTRIBUTE_ID)) {
                    attributeID = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = annotationParser.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new AggregationAttributeR(attributeID, Optional.ofNullable(annotations));
    }

    public static List<AggregationDesignDimension> getAggregationDesignDimensionList(NodeList nl,
            AnnotationListParser annotationParser) {
        return getListByLocalName(nl, DIMENSION, childNl -> getAggregationDesignDimension(childNl, annotationParser));
    }

    public static AggregationDesignDimension getAggregationDesignDimension(NodeList nl,
            AnnotationListParser annotationParser) {
        String cubeDimensionID = null;
        List<AggregationDesignAttribute> attributes = null;
        List<Annotation> annotations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, CUBE_DIMENSION_ID)) {
                    cubeDimensionID = node.getTextContent();
                }
                if (matchesLocalName(node, ATTRIBUTES)) {
                    attributes = getAggregationDesignAttributeList(node.getChildNodes());
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = annotationParser.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new AggregationDesignDimensionR(cubeDimensionID, Optional.ofNullable(attributes),
                Optional.ofNullable(annotations));
    }

    public static List<AggregationDesignAttribute> getAggregationDesignAttributeList(NodeList nl) {
        return getListByLocalName(nl, ATTRIBUTE, AggregationConverter::getAggregationDesignAttribute);
    }

    public static AggregationDesignAttribute getAggregationDesignAttribute(NodeList nl) {
        String attributeID = null;
        Long estimatedCount = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, ATTRIBUTE_ID)) {
                    attributeID = node.getTextContent();
                }
                if (matchesLocalName(node, "EstimatedCount")) {
                    estimatedCount = toLong(node.getTextContent());
                }
            }
        }
        return new AggregationDesignAttributeR(attributeID, Optional.ofNullable(estimatedCount));
    }
}
