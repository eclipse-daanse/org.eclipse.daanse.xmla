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
            if ((node != null) && ("Partition".equals(node.getNodeName()))) {
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
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(node.getNodeName())) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(node.getNodeName())) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = BindingConverter.getTabularBinding(node.getChildNodes(), getNodeType(node));
                }
                if (PROCESSING_PRIORITY.equals(node.getNodeName())) {
                    processingPriority = toBigInteger(node.getTextContent());
                }
                if (AGGREGATION_PREFIX.equals(node.getNodeName())) {
                    aggregationPrefix = node.getTextContent();
                }
                if (STORAGE_MODE.equals(node.getNodeName())) {
                    storageMode = getPartitionStorageMode(node.getChildNodes());
                }
                if (PROCESSING_MODE.equals(node.getNodeName())) {
                    processingMode = node.getTextContent();
                }
                if (ERROR_CONFIGURATION.equals(node.getNodeName())) {
                    errorConfiguration = CommonConverter.getErrorConfiguration(node.getChildNodes());
                }
                if (STORAGE_LOCATION.equals(node.getNodeName())) {
                    storageLocation = node.getTextContent();
                }
                if ("RemoteDatasourceID".equals(node.getNodeName())) {
                    remoteDatasourceID = node.getTextContent();
                }
                if ("Slice".equals(node.getNodeName())) {
                    slice = node.getTextContent();
                }
                if (PROACTIVE_CACHING.equals(node.getNodeName())) {
                    proactiveCaching = CommonConverter.getProactiveCaching(node.getChildNodes());
                }
                if ("Type".equals(node.getNodeName())) {
                    type = node.getTextContent();
                }
                if (ESTIMATED_SIZE.equals(node.getNodeName())) {
                    estimatedSize = toLong(node.getTextContent());
                }
                if (ESTIMATED_ROWS.equals(node.getNodeName())) {
                    estimatedRows = toLong(node.getTextContent());
                }
                if ("CurrentStorageMode".equals(node.getNodeName())) {
                    currentStorageMode = getPartitionCurrentStorageMode(node.getChildNodes());
                }
                if (AGGREGATION_DESIGN_ID.equals(node.getNodeName())) {
                    aggregationDesignID = node.getTextContent();
                }
                if ("AggregationInstances".equals(node.getNodeName())) {
                    aggregationInstances = getAggregationInstanceList(node.getChildNodes());
                }
                if ("AggregationInstanceSource".equals(node.getNodeName())) {
                    aggregationInstanceSource = DataSourceConverter.getDataSourceViewBinding(node.getChildNodes());
                }
                if (LAST_PROCESSED.equals(node.getNodeName())) {
                    lastProcessed = toInstant(node.getTextContent());
                }
                if (STATE.equals(node.getNodeName())) {
                    state = node.getTextContent();
                }
                if ("StringStoresCompatibilityLevel".equals(node.getNodeName())) {
                    stringStoresCompatibilityLevel = toInteger(node.getTextContent());
                }
                if ("CurrentStringStoresCompatibilityLevel".equals(node.getNodeName())) {
                    currentStringStoresCompatibilityLevel = toInteger(node.getTextContent());
                }
                if ("DirectQueryUsage".equals(node.getNodeName())) {
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
            if ((node != null) && ("AggregationInstance".equals(node.getNodeName()))) {
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
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if ("AggregationType".equals(node.getNodeName())) {
                    aggregationType = node.getTextContent();
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = BindingConverter.getTabularBinding(node.getChildNodes(), getNodeType(node));
                }
                if (DIMENSIONS.equals(node.getNodeName())) {
                    dimensions = getAggregationInstanceDimensionList(node.getChildNodes());
                }
                if (MEASURES.equals(node.getNodeName())) {
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
            if ((node != null) && (MEASURE.equals(node.getNodeName()))) {
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
                if (MEASURE_ID.equals(node.getNodeName())) {
                    measureID = node.getTextContent();
                }
                if (SOURCE.equals(node.getNodeName())) {
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
            if ((node != null) && (DIMENSION.equals(node.getNodeName()))) {
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
                if (CUBE_DIMENSION_ID.equals(node.getNodeName())) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ATTRIBUTES.equals(node.getNodeName())) {
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
            if ((node != null) && (ATTRIBUTE.equals(node.getNodeName()))) {
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
                if (ATTRIBUTE_ID.equals(node.getNodeName())) {
                    attributeID = node.getTextContent();
                }
                if (KEY_COLUMNS.equals(node.getNodeName())) {
                    keyColumns = CommonConverter.getDataItemList(node.getChildNodes(), KEY_COLUMN);
                }
            }
        }
        return new AggregationInstanceAttributeR(attributeID, Optional.ofNullable(keyColumns));
    }
}
