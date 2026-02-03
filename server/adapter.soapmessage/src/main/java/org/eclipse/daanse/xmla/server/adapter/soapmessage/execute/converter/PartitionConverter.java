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

import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.AGGREGATION_DESIGN_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.AGGREGATION_PREFIX;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ANNOTATIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CREATED_TIMESTAMP;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CUBE_DIMENSION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ERROR_CONFIGURATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ESTIMATED_ROWS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ESTIMATED_SIZE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.KEY_COLUMN;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.KEY_COLUMNS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LAST_PROCESSED;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LAST_SCHEMA_UPDATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASURE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASURES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASURE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROACTIVE_CACHING;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROCESSING_MODE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROCESSING_PRIORITY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.SOURCE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.STATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.STORAGE_LOCATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.STORAGE_MODE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.VALUENS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getAttribute;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getNodeType;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.matchesLocalName;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBigInteger;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInstant;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInteger;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toLong;

import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.AggregationInstance;
import org.eclipse.daanse.xmla.api.xmla.AggregationInstanceAttribute;
import org.eclipse.daanse.xmla.api.xmla.AggregationInstanceDimension;
import org.eclipse.daanse.xmla.api.xmla.AggregationInstanceMeasure;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.ColumnBinding;
import org.eclipse.daanse.xmla.api.xmla.DataItem;
import org.eclipse.daanse.xmla.api.xmla.DataSourceViewBinding;
import org.eclipse.daanse.xmla.api.xmla.ErrorConfiguration;
import org.eclipse.daanse.xmla.api.xmla.Partition;
import org.eclipse.daanse.xmla.api.xmla.PartitionCurrentStorageModeEnumType;
import org.eclipse.daanse.xmla.api.xmla.PartitionStorageModeEnumType;
import org.eclipse.daanse.xmla.api.xmla.ProactiveCaching;
import org.eclipse.daanse.xmla.api.xmla.TabularBinding;
import org.eclipse.daanse.xmla.model.record.xmla.AggregationInstanceAttributeR;
import org.eclipse.daanse.xmla.model.record.xmla.AggregationInstanceDimensionR;
import org.eclipse.daanse.xmla.model.record.xmla.AggregationInstanceMeasureR;
import org.eclipse.daanse.xmla.model.record.xmla.AggregationInstanceR;
import org.eclipse.daanse.xmla.model.record.xmla.PartitionR;
import org.w3c.dom.NodeList;

/**
 * Converter for Partition types from XML NodeList.
 */
public class PartitionConverter {

    private PartitionConverter() {
        // utility class
    }

    public static List<Partition> getPartitionList(NodeList nl) {
        List<Partition> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "Partition")) {
                list.add(getPartition(node.getChildNodes()));
            }
        }
        return list;
    }

    public static Partition getPartition(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        TabularBinding source = null;
        BigInteger processingPriority = null;
        String aggregationPrefix = null;
        Partition.StorageMode storageMode = null;
        String processingMode = null;
        ErrorConfiguration errorConfiguration = null;
        String storageLocation = null;
        String remoteDatasourceID = null;
        String slice = null;
        ProactiveCaching proactiveCaching = null;
        String type = null;
        Long estimatedSize = null;
        Long estimatedRows = null;
        Partition.CurrentStorageMode currentStorageMode = null;
        String aggregationDesignID = null;
        List<AggregationInstance> aggregationInstances = null;
        DataSourceViewBinding aggregationInstanceSource = null;
        Instant lastProcessed = null;
        String state = null;
        Integer stringStoresCompatibilityLevel = null;
        Integer currentStringStoresCompatibilityLevel = null;
        String directQueryUsage = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, NAME)) {
                    name = node.getTextContent();
                }
                if (matchesLocalName(node, ID)) {
                    id = node.getTextContent();
                }
                if (matchesLocalName(node, CREATED_TIMESTAMP)) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (matchesLocalName(node, LAST_SCHEMA_UPDATE)) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (matchesLocalName(node, DESCRIPTION)) {
                    description = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (matchesLocalName(node, SOURCE)) {
                    source = BindingConverter.getTabularBinding(node.getChildNodes(), getNodeType(node));
                }
                if (matchesLocalName(node, PROCESSING_PRIORITY)) {
                    processingPriority = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, AGGREGATION_PREFIX)) {
                    aggregationPrefix = node.getTextContent();
                }
                if (matchesLocalName(node, STORAGE_MODE)) {
                    storageMode = getPartitionStorageMode(node.getChildNodes());
                }
                if (matchesLocalName(node, PROCESSING_MODE)) {
                    processingMode = node.getTextContent();
                }
                if (matchesLocalName(node, ERROR_CONFIGURATION)) {
                    errorConfiguration = CommonConverter.getErrorConfiguration(node.getChildNodes());
                }
                if (matchesLocalName(node, STORAGE_LOCATION)) {
                    storageLocation = node.getTextContent();
                }
                if (matchesLocalName(node, "RemoteDatasourceID")) {
                    remoteDatasourceID = node.getTextContent();
                }
                if (matchesLocalName(node, "Slice")) {
                    slice = node.getTextContent();
                }
                if (matchesLocalName(node, PROACTIVE_CACHING)) {
                    proactiveCaching = CommonConverter.getProactiveCaching(node.getChildNodes());
                }
                if (matchesLocalName(node, "Type")) {
                    type = node.getTextContent();
                }
                if (matchesLocalName(node, ESTIMATED_SIZE)) {
                    estimatedSize = toLong(node.getTextContent());
                }
                if (matchesLocalName(node, ESTIMATED_ROWS)) {
                    estimatedRows = toLong(node.getTextContent());
                }
                if (matchesLocalName(node, "CurrentStorageMode")) {
                    currentStorageMode = getPartitionCurrentStorageMode(node.getChildNodes());
                }
                if (matchesLocalName(node, AGGREGATION_DESIGN_ID)) {
                    aggregationDesignID = node.getTextContent();
                }
                if (matchesLocalName(node, "AggregationInstances")) {
                    aggregationInstances = getAggregationInstanceList(node.getChildNodes());
                }
                if (matchesLocalName(node, "AggregationInstanceSource")) {
                    aggregationInstanceSource = DataSourceConverter.getDataSourceViewBinding(node.getChildNodes());
                }
                if (matchesLocalName(node, LAST_PROCESSED)) {
                    lastProcessed = toInstant(node.getTextContent());
                }
                if (matchesLocalName(node, STATE)) {
                    state = node.getTextContent();
                }
                if (matchesLocalName(node, "StringStoresCompatibilityLevel")) {
                    stringStoresCompatibilityLevel = toInteger(node.getTextContent());
                }
                if (matchesLocalName(node, "CurrentStringStoresCompatibilityLevel")) {
                    currentStringStoresCompatibilityLevel = toInteger(node.getTextContent());
                }
                if (matchesLocalName(node, "DirectQueryUsage")) {
                    directQueryUsage = node.getTextContent();
                }
            }
        }

        return new PartitionR(name, id, createdTimestamp, lastSchemaUpdate, description, annotations, source,
                processingPriority, aggregationPrefix, storageMode, processingMode, errorConfiguration, storageLocation,
                remoteDatasourceID, slice, proactiveCaching, type, estimatedSize, estimatedRows, currentStorageMode,
                aggregationDesignID, aggregationInstances, aggregationInstanceSource, lastProcessed, state,
                stringStoresCompatibilityLevel, currentStringStoresCompatibilityLevel, directQueryUsage);
    }

    public static Partition.StorageMode getPartitionStorageMode(NodeList nl) {
        PartitionStorageModeEnumType value = null;
        String valuens = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                value = PartitionStorageModeEnumType.fromValue(node.getTextContent());
                valuens = getAttribute(node.getAttributes(), VALUENS);
            }
        }
        return new PartitionR.StorageMode(value, valuens);
    }

    public static Partition.CurrentStorageMode getPartitionCurrentStorageMode(NodeList nl) {
        PartitionCurrentStorageModeEnumType value = null;
        String valuens = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                value = PartitionCurrentStorageModeEnumType.fromValue(node.getTextContent());
                valuens = getAttribute(node.getAttributes(), VALUENS);
            }
        }
        return new PartitionR.CurrentStorageMode(value, valuens);
    }

    static List<AggregationInstance> getAggregationInstanceList(NodeList nl) {
        List<AggregationInstance> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "AggregationInstance")) {
                list.add(getAggregationInstance(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AggregationInstance getAggregationInstance(NodeList nl) {
        String id = null;
        String name = null;
        String aggregationType = null;
        TabularBinding source = null;
        List<AggregationInstanceDimension> dimensions = null;
        List<AggregationInstanceMeasure> measures = null;
        List<Annotation> annotations = null;
        String description = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, ID)) {
                    id = node.getTextContent();
                }
                if (matchesLocalName(node, NAME)) {
                    name = node.getTextContent();
                }
                if (matchesLocalName(node, DESCRIPTION)) {
                    description = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (matchesLocalName(node, "AggregationType")) {
                    aggregationType = node.getTextContent();
                }
                if (matchesLocalName(node, SOURCE)) {
                    source = BindingConverter.getTabularBinding(node.getChildNodes(), getNodeType(node));
                }
                if (matchesLocalName(node, DIMENSIONS)) {
                    dimensions = getAggregationInstanceDimensionList(node.getChildNodes());
                }
                if (matchesLocalName(node, MEASURES)) {
                    measures = getAggregationInstanceMeasureList(node.getChildNodes());
                }
            }
        }
        return new AggregationInstanceR(id, name, aggregationType, source, dimensions, measures, annotations,
                description);
    }

    private static List<AggregationInstanceMeasure> getAggregationInstanceMeasureList(NodeList nl) {
        List<AggregationInstanceMeasure> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, MEASURE)) {
                list.add(getAggregationInstanceMeasure(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AggregationInstanceMeasure getAggregationInstanceMeasure(NodeList nl) {
        String measureID = null;
        ColumnBinding source = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, MEASURE_ID)) {
                    measureID = node.getTextContent();
                }
                if (matchesLocalName(node, SOURCE)) {
                    source = (ColumnBinding) BindingConverter.getColumnBinding(node.getChildNodes());
                }
            }
        }
        return new AggregationInstanceMeasureR(measureID, source);
    }

    private static List<AggregationInstanceDimension> getAggregationInstanceDimensionList(NodeList nl) {
        List<AggregationInstanceDimension> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, DIMENSION)) {
                list.add(getAggregationInstanceDimension(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AggregationInstanceDimension getAggregationInstanceDimension(NodeList nl) {
        String cubeDimensionID = null;
        List<AggregationInstanceAttribute> attributes = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, CUBE_DIMENSION_ID)) {
                    cubeDimensionID = node.getTextContent();
                }
                if (matchesLocalName(node, ATTRIBUTES)) {
                    attributes = getAggregationInstanceAttributeList(node.getChildNodes());
                }
            }
        }
        return new AggregationInstanceDimensionR(cubeDimensionID, Optional.ofNullable(attributes));
    }

    private static List<AggregationInstanceAttribute> getAggregationInstanceAttributeList(NodeList nl) {
        List<AggregationInstanceAttribute> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, ATTRIBUTE)) {
                list.add(getAggregationInstanceAttribute(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AggregationInstanceAttribute getAggregationInstanceAttribute(NodeList nl) {
        String attributeID = null;
        List<DataItem> keyColumns = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, ATTRIBUTE_ID)) {
                    attributeID = node.getTextContent();
                }
                if (matchesLocalName(node, KEY_COLUMNS)) {
                    keyColumns = CommonConverter.getDataItemList(node.getChildNodes(), KEY_COLUMN);
                }
            }
        }
        return new AggregationInstanceAttributeR(attributeID, Optional.ofNullable(keyColumns));
    }
}
