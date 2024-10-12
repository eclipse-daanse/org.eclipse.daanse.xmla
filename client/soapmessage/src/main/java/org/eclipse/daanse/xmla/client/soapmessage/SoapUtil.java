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
package org.eclipse.daanse.xmla.client.soapmessage;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import org.eclipse.daanse.xmla.api.discover.Properties;
import org.eclipse.daanse.xmla.api.engine.ImpersonationInfo;
import org.eclipse.daanse.xmla.api.engine200_200.ExpressionBinding;
import org.eclipse.daanse.xmla.api.engine200_200.RowNumberBinding;
import org.eclipse.daanse.xmla.api.engine300.CalculationPropertiesVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300.DimensionAttributeVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300.HierarchyVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300.RelationshipEndVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300_300.Relationship;
import org.eclipse.daanse.xmla.api.engine300_300.RelationshipEnd;
import org.eclipse.daanse.xmla.api.engine300_300.RelationshipEndTranslation;
import org.eclipse.daanse.xmla.api.engine300_300.Relationships;
import org.eclipse.daanse.xmla.api.engine300_300.XEvent;
import org.eclipse.daanse.xmla.api.execute.ExecuteParameter;
import org.eclipse.daanse.xmla.api.xmla.Account;
import org.eclipse.daanse.xmla.api.xmla.Action;
import org.eclipse.daanse.xmla.api.xmla.Aggregation;
import org.eclipse.daanse.xmla.api.xmla.AggregationAttribute;
import org.eclipse.daanse.xmla.api.xmla.AggregationDesign;
import org.eclipse.daanse.xmla.api.xmla.AggregationDesignAttribute;
import org.eclipse.daanse.xmla.api.xmla.AggregationDesignDimension;
import org.eclipse.daanse.xmla.api.xmla.AggregationDimension;
import org.eclipse.daanse.xmla.api.xmla.AggregationInstance;
import org.eclipse.daanse.xmla.api.xmla.AggregationInstanceAttribute;
import org.eclipse.daanse.xmla.api.xmla.AggregationInstanceDimension;
import org.eclipse.daanse.xmla.api.xmla.AggregationInstanceMeasure;
import org.eclipse.daanse.xmla.api.xmla.AlgorithmParameter;
import org.eclipse.daanse.xmla.api.xmla.Alter;
import org.eclipse.daanse.xmla.api.xmla.AndOrType;
import org.eclipse.daanse.xmla.api.xmla.AndOrTypeEnum;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.Assembly;
import org.eclipse.daanse.xmla.api.xmla.Attach;
import org.eclipse.daanse.xmla.api.xmla.AttributeBinding;
import org.eclipse.daanse.xmla.api.xmla.AttributePermission;
import org.eclipse.daanse.xmla.api.xmla.AttributeRelationship;
import org.eclipse.daanse.xmla.api.xmla.AttributeTranslation;
import org.eclipse.daanse.xmla.api.xmla.Backup;
import org.eclipse.daanse.xmla.api.xmla.Batch;
import org.eclipse.daanse.xmla.api.xmla.BeginTransaction;
import org.eclipse.daanse.xmla.api.xmla.Binding;
import org.eclipse.daanse.xmla.api.xmla.BoolBinop;
import org.eclipse.daanse.xmla.api.xmla.CalculatedMeasureBinding;
import org.eclipse.daanse.xmla.api.xmla.CalculationProperty;
import org.eclipse.daanse.xmla.api.xmla.Cancel;
import org.eclipse.daanse.xmla.api.xmla.CellPermission;
import org.eclipse.daanse.xmla.api.xmla.ClearCache;
import org.eclipse.daanse.xmla.api.xmla.CloneDatabase;
import org.eclipse.daanse.xmla.api.xmla.ColumnBinding;
import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.api.xmla.CommitTransaction;
import org.eclipse.daanse.xmla.api.xmla.Create;
import org.eclipse.daanse.xmla.api.xmla.Cube;
import org.eclipse.daanse.xmla.api.xmla.CubeAttribute;
import org.eclipse.daanse.xmla.api.xmla.CubeAttributeBinding;
import org.eclipse.daanse.xmla.api.xmla.CubeDimension;
import org.eclipse.daanse.xmla.api.xmla.CubeDimensionBinding;
import org.eclipse.daanse.xmla.api.xmla.CubeDimensionPermission;
import org.eclipse.daanse.xmla.api.xmla.CubeHierarchy;
import org.eclipse.daanse.xmla.api.xmla.CubePermission;
import org.eclipse.daanse.xmla.api.xmla.DBCC;
import org.eclipse.daanse.xmla.api.xmla.DSVTableBinding;
import org.eclipse.daanse.xmla.api.xmla.DataItem;
import org.eclipse.daanse.xmla.api.xmla.DataMiningMeasureGroupDimension;
import org.eclipse.daanse.xmla.api.xmla.DataSource;
import org.eclipse.daanse.xmla.api.xmla.DataSourcePermission;
import org.eclipse.daanse.xmla.api.xmla.DataSourceView;
import org.eclipse.daanse.xmla.api.xmla.DataSourceViewBinding;
import org.eclipse.daanse.xmla.api.xmla.Database;
import org.eclipse.daanse.xmla.api.xmla.DatabasePermission;
import org.eclipse.daanse.xmla.api.xmla.DegenerateMeasureGroupDimension;
import org.eclipse.daanse.xmla.api.xmla.Delete;
import org.eclipse.daanse.xmla.api.xmla.DesignAggregations;
import org.eclipse.daanse.xmla.api.xmla.Detach;
import org.eclipse.daanse.xmla.api.xmla.Dimension;
import org.eclipse.daanse.xmla.api.xmla.DimensionAttribute;
import org.eclipse.daanse.xmla.api.xmla.DimensionBinding;
import org.eclipse.daanse.xmla.api.xmla.DimensionPermission;
import org.eclipse.daanse.xmla.api.xmla.DrillThroughAction;
import org.eclipse.daanse.xmla.api.xmla.Drop;
import org.eclipse.daanse.xmla.api.xmla.ErrorConfiguration;
import org.eclipse.daanse.xmla.api.xmla.Event;
import org.eclipse.daanse.xmla.api.xmla.EventColumnID;
import org.eclipse.daanse.xmla.api.xmla.EventSession;
import org.eclipse.daanse.xmla.api.xmla.EventType;
import org.eclipse.daanse.xmla.api.xmla.FoldingParameters;
import org.eclipse.daanse.xmla.api.xmla.Group;
import org.eclipse.daanse.xmla.api.xmla.Hierarchy;
import org.eclipse.daanse.xmla.api.xmla.ImageLoad;
import org.eclipse.daanse.xmla.api.xmla.ImageSave;
import org.eclipse.daanse.xmla.api.xmla.IncrementalProcessingNotification;
import org.eclipse.daanse.xmla.api.xmla.InheritedBinding;
import org.eclipse.daanse.xmla.api.xmla.Insert;
import org.eclipse.daanse.xmla.api.xmla.Kpi;
import org.eclipse.daanse.xmla.api.xmla.Level;
import org.eclipse.daanse.xmla.api.xmla.Lock;
import org.eclipse.daanse.xmla.api.xmla.MajorObject;
import org.eclipse.daanse.xmla.api.xmla.ManyToManyMeasureGroupDimension;
import org.eclipse.daanse.xmla.api.xmla.MdxScript;
import org.eclipse.daanse.xmla.api.xmla.Measure;
import org.eclipse.daanse.xmla.api.xmla.MeasureBinding;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroup;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroupAttribute;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroupBinding;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimensionBinding;
import org.eclipse.daanse.xmla.api.xmla.Member;
import org.eclipse.daanse.xmla.api.xmla.MergePartitions;
import org.eclipse.daanse.xmla.api.xmla.MiningModel;
import org.eclipse.daanse.xmla.api.xmla.MiningModelColumn;
import org.eclipse.daanse.xmla.api.xmla.MiningModelPermission;
import org.eclipse.daanse.xmla.api.xmla.MiningModelingFlag;
import org.eclipse.daanse.xmla.api.xmla.MiningStructure;
import org.eclipse.daanse.xmla.api.xmla.MiningStructureColumn;
import org.eclipse.daanse.xmla.api.xmla.MiningStructurePermission;
import org.eclipse.daanse.xmla.api.xmla.NotType;
import org.eclipse.daanse.xmla.api.xmla.NotifyTableChange;
import org.eclipse.daanse.xmla.api.xmla.ObjectExpansion;
import org.eclipse.daanse.xmla.api.xmla.ObjectReference;
import org.eclipse.daanse.xmla.api.xmla.Partition;
import org.eclipse.daanse.xmla.api.xmla.Permission;
import org.eclipse.daanse.xmla.api.xmla.Perspective;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveAction;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveAttribute;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveCalculation;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveDimension;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveHierarchy;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveKpi;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveMeasure;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveMeasureGroup;
import org.eclipse.daanse.xmla.api.xmla.ProactiveCaching;
import org.eclipse.daanse.xmla.api.xmla.ProactiveCachingBinding;
import org.eclipse.daanse.xmla.api.xmla.ProactiveCachingIncrementalProcessingBinding;
import org.eclipse.daanse.xmla.api.xmla.ProactiveCachingQueryBinding;
import org.eclipse.daanse.xmla.api.xmla.QueryBinding;
import org.eclipse.daanse.xmla.api.xmla.QueryNotification;
import org.eclipse.daanse.xmla.api.xmla.ReferenceMeasureGroupDimension;
import org.eclipse.daanse.xmla.api.xmla.RegularMeasureGroupDimension;
import org.eclipse.daanse.xmla.api.xmla.ReportAction;
import org.eclipse.daanse.xmla.api.xmla.ReportFormatParameter;
import org.eclipse.daanse.xmla.api.xmla.ReportParameter;
import org.eclipse.daanse.xmla.api.xmla.Restore;
import org.eclipse.daanse.xmla.api.xmla.Role;
import org.eclipse.daanse.xmla.api.xmla.RollbackTransaction;
import org.eclipse.daanse.xmla.api.xmla.RowBinding;
import org.eclipse.daanse.xmla.api.xmla.ScalarMiningStructureColumn;
import org.eclipse.daanse.xmla.api.xmla.Scope;
import org.eclipse.daanse.xmla.api.xmla.Server;
import org.eclipse.daanse.xmla.api.xmla.ServerProperty;
import org.eclipse.daanse.xmla.api.xmla.SetAuthContext;
import org.eclipse.daanse.xmla.api.xmla.StandardAction;
import org.eclipse.daanse.xmla.api.xmla.Statement;
import org.eclipse.daanse.xmla.api.xmla.Subscribe;
import org.eclipse.daanse.xmla.api.xmla.Synchronize;
import org.eclipse.daanse.xmla.api.xmla.TableBinding;
import org.eclipse.daanse.xmla.api.xmla.TableMiningStructureColumn;
import org.eclipse.daanse.xmla.api.xmla.TabularBinding;
import org.eclipse.daanse.xmla.api.xmla.TimeAttributeBinding;
import org.eclipse.daanse.xmla.api.xmla.TimeBinding;
import org.eclipse.daanse.xmla.api.xmla.Trace;
import org.eclipse.daanse.xmla.api.xmla.TraceFilter;
import org.eclipse.daanse.xmla.api.xmla.Translation;
import org.eclipse.daanse.xmla.api.xmla.Unlock;
import org.eclipse.daanse.xmla.api.xmla.Unsubscribe;
import org.eclipse.daanse.xmla.api.xmla.Update;
import org.eclipse.daanse.xmla.api.xmla.UpdateCells;
import org.eclipse.daanse.xmla.api.xmla.UserDefinedGroupBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

import static org.eclipse.daanse.xmla.client.soapmessage.Constants.ACCOUNT;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.AGGREGATION_DESIGN_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.AGGREGATION_PREFIX;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.ALLOW_DRILL_THROUGH;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.ATTRIBUTE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.ATTRIBUTES;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.ATTRIBUTE_HIERARCHY_VISIBLE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.ATTRIBUTE_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.AXIS_FORMAT;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.CAPTION;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.CATALOG;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.COLLATION;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.COLUMNS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.COLUMN_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.COMMAND;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.CONTENT;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.CONTEXTUAL_NAME_RULE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.CREATED_TIMESTAMP;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.CUBE_DIMENSION_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.CUBE_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DATA_SOURCE_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DATA_SOURCE_INFO;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DATA_SOURCE_VIEW_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DEFAULT_DETAILS_POSITION;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DEFAULT_MEMBER;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DIMENSION;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DIMENSIONS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DIMENSION_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DISPLAY_FOLDER;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.ESTIMATED_ROWS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.ESTIMATED_SIZE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.EXPRESSION;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.FILTER;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.FOLDER_POSITION;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.FORMAT;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.HIERARCHIES;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.HIERARCHY;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.KEY_COLUMN;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.KEY_COLUMNS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.LANGUAGE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.LAST_PROCESSED;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.LAST_SCHEMA_UPDATE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.LOCALE_IDENTIFIER;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MEASURE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MEASURES;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MEASURE_GROUP_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MEASURE_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.MINING_MODEL_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.NAME_LC;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.ORDINAL;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.PERSISTENCE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.PROCESS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.PROCESSING_MODE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.PROCESSING_PRIORITY;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.PROCESSING_STATE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.READ_DEFINITION;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.REFRESH_INTERVAL;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.REFRESH_POLICY;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.ROLE_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.SERVER_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.SORT_PROPERTIES_POSITION;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.SOURCE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.STATE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.STORAGE_LOCATION;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.STORAGE_MODE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.TABLE_ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.TRANSLATION;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.TRANSLATIONS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.VALUE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.VALUE2;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.VALUENS;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.VISIBLE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.VISUALIZATION_PROPERTIES;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.WRITE;

class SoapUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoapUtil.class);

    private SoapUtil() {
        // constructor
    }

    static void addChildElementCancel(SOAPElement element, Cancel cancel) {
        SOAPElement cancelElement = addChildElement(element, "Cancel");
        addChildElement(cancelElement, "ConnectionID", String.valueOf(cancel.connectionID()));
        addChildElement(cancelElement, "SessionID", cancel.sessionID());
        addChildElement(cancelElement, "SPID", String.valueOf(cancel.spid()));
        addChildElement(cancelElement, "CancelAssociated", String.valueOf(cancel.cancelAssociated()));

    }

    static void addChildElementParameterList(SOAPElement element, List<ExecuteParameter> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Parameters");
            list.forEach(it -> addChildElementExecuteParameter(chElement, it));
        }
    }

    static void addChildElementExecuteParameter(SOAPElement element, ExecuteParameter it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Parameter");
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, VALUE2, it.value() == null ? null : it.value().toString());
        }
    }

    static void addChildElementTrace(SOAPElement element, Trace it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Trace");
            addChildElement(chElement, NAME, it.name());
            addChildElement(chElement, ID, it.id());
            addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(it.createdTimestamp()));
            addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(it.lastSchemaUpdate()));
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElementAnnotationList(chElement, it.annotations());
            addChildElement(chElement, "LogFileName", it.logFileName());
            addChildElement(chElement, "LogFileAppend", String.valueOf(it.logFileAppend()));
            addChildElement(chElement, "LogFileSize", String.valueOf(it.logFileSize()));
            addChildElement(chElement, "Audit", String.valueOf(it.audit()));
            addChildElement(chElement, "LogFileRollover", String.valueOf(it.logFileRollover()));
            addChildElement(chElement, "AutoRestart", String.valueOf(it.autoRestart()));
            addChildElement(chElement, "StopTime", convertInstant(it.stopTime()));
            addChildElementTraceFilter(chElement, it.filter());
            addChildElementEventType(chElement, it.eventType());
        }
    }

    static void addChildElementEventType(SOAPElement element, EventType it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "EventType");
            addChildElementEventList(chElement, it.events());
            addChildElementXEvent(chElement, it.xEvent());
        }
    }

    static void addChildElementXEvent(SOAPElement element, XEvent it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "XEvent");
            addChildElementEventSession(chElement, it.eventSession());
        }
    }

    static void addChildElementEventSession(SOAPElement element, EventSession it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "event_session");
            addChildElement(chElement, "templateCategory", it.templateCategory());
            addChildElement(chElement, "templateName", it.templateName());
            addChildElement(chElement, "templateDescription", it.templateDescription());
            addChildElementObjectList(chElement, "event", it.event());
            addChildElementObjectList(chElement, "target", it.target());
            addChildElement(chElement, "name", it.name());
            addChildElement(chElement, "maxMemory", String.valueOf(it.maxMemory()));
            addChildElement(chElement, "eventRetentionMode",
                    it.eventRetentionMode() == null ? null : it.eventRetentionMode().value());
            addChildElement(chElement, "dispatchLatency", String.valueOf(it.dispatchLatency()));
            addChildElement(chElement, "maxEventSize", String.valueOf(it.maxEventSize()));
            addChildElement(chElement, "memoryPartitionMode",
                    it.eventRetentionMode() == null ? null : it.eventRetentionMode().value());
            addChildElement(chElement, "trackCausality", String.valueOf(it.trackCausality()));
        }
    }

    private static void addChildElementObjectList(SOAPElement element, String tagName, List<Object> list) {
        if (list != null) {
            list.forEach(it -> addChildElement(element, tagName).setTextContent(it.toString()));
        }
    }

    static void addChildElementEventList(SOAPElement element, List<Event> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Events");
            list.forEach(it -> addChildElementEvent(chElement, it));
        }
    }

    static void addChildElementEvent(SOAPElement element, Event it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Event");
            addChildElement(chElement, "EventID", it.eventID());
            addChildElementEventColumnID(chElement, it.columns());
        }
    }

    static void addChildElementEventColumnID(SOAPElement element, EventColumnID it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, COLUMNS);
            addChildElementColumnID(chElement, it.columnID());
        }

    }

    static void addChildElementColumnID(SOAPElement element, List<String> list) {
        if (list != null) {
            list.forEach(it -> addChildElement(element, COLUMN_ID).setTextContent(it));
        }
    }

    static void addChildElementTraceFilter(SOAPElement element, TraceFilter it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, FILTER);
            addChildElementNotType(chElement, "Not", it.not());
            addChildElementAndOrType(chElement, "Or", it.or());
            addChildElementAndOrType(chElement, "And", it.and());
            addChildElementBoolBinop(chElement, "Equal", it.isEqual());
            addChildElementBoolBinop(chElement, "NotEqual", it.notEqual());
            addChildElementBoolBinop(chElement, "Less", it.less());
            addChildElementBoolBinop(chElement, "LessOrEqual", it.lessOrEqual());
            addChildElementBoolBinop(chElement, "Greater", it.greater());
            addChildElementBoolBinop(chElement, "GreaterOrEqual", it.greaterOrEqual());
            addChildElementBoolBinop(chElement, "Like", it.like());
            addChildElementBoolBinop(chElement, "NotLike", it.notLike());
        }
    }

    static void addChildElementBoolBinop(SOAPElement element, String nodeName, BoolBinop it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, nodeName);
            addChildElement(chElement, COLUMN_ID, it.columnID());
            addChildElement(chElement, VALUE2, it.value());
        }
    }

    static void addChildElementAndOrType(SOAPElement element, String nodeName, AndOrType it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, nodeName);
            addChildElementAndOrTypeEnumList(chElement, it.notOrOrOrAnd());
        }
    }

    static void addChildElementAndOrTypeEnumList(SOAPElement element, List<AndOrTypeEnum> list) {
        if (list != null) {
            list.forEach(it -> addChildElement(element, it.name()));
        }
    }

    static void addChildElementNotType(SOAPElement element, String nodeName, NotType it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, nodeName);
            addChildElementNotType(chElement, "Not", it.not());
            addChildElementAndOrType(chElement, "Or", it.or());
            addChildElementAndOrType(chElement, "And", it.and());
            addChildElementBoolBinop(chElement, "Equal", it.isEqual());
            addChildElementBoolBinop(chElement, "NotEqual", it.notEqual());
            addChildElementBoolBinop(chElement, "Less", it.less());
            addChildElementBoolBinop(chElement, "LessOrEqual", it.lessOrEqual());
            addChildElementBoolBinop(chElement, "Greater", it.greater());
            addChildElementBoolBinop(chElement, "GreaterOrEqual", it.greaterOrEqual());
            addChildElementBoolBinop(chElement, "Like", it.like());
            addChildElementBoolBinop(chElement, "NotLike", it.notLike());
        }
    }

    static void addChildElementServer(SOAPElement element, Server it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Server");
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, ID, it.id());
            addChildElement(chElement, CREATED_TIMESTAMP, String.valueOf(it.createdTimestamp()));
            addChildElement(chElement, LAST_SCHEMA_UPDATE, String.valueOf(it.lastSchemaUpdate()));
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElementAnnotationList(chElement, it.annotations());
            addChildElement(chElement, "ProductName", it.productName());
            addChildElement(chElement, "Edition", it.edition());
            addChildElement(chElement, "EditionID", String.valueOf(it.editionID()));
            addChildElement(chElement, "Version", it.version());
            addChildElement(chElement, "ServerMode", it.serverMode());
            addChildElement(chElement, "ProductLevel", it.productLevel());
            addChildElement(chElement, "DefaultCompatibilityLevel", String.valueOf(it.defaultCompatibilityLevel()));
            addChildElement(chElement, "SupportedCompatibilityLevels", it.supportedCompatibilityLevels());
            addChildElementDatabaseList(chElement, it.databases());
            addChildElementAssemblyList(chElement, it.assemblies());
            addChildElementTraceList(chElement, it.traces());
            addChildElementRoleList(chElement, it.roles());
            addChildElementServerPropertyList(chElement, it.serverProperties());
        }
    }

    static void addChildElementServerPropertyList(SOAPElement element, List<ServerProperty> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "ServerProperties");
            list.forEach(it -> addChildElementServerProperty(chElement, it));
        }
    }

    static void addChildElementServerProperty(SOAPElement element, ServerProperty it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "ServerProperty");
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, VALUE2, it.value());
            addChildElement(chElement, "RequiresRestart", String.valueOf(it.requiresRestart()));
            addChildElement(chElement, "PendingValue", it.pendingValue().toString());
            addChildElement(chElement, "DefaultValue", it.defaultValue().toString());
            addChildElement(chElement, "DisplayFlag", String.valueOf(it.displayFlag()));
            addChildElement(chElement, "Type", it.type());
        }
    }

    static void addChildElementRoleList(SOAPElement element, List<Role> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Roles");
            list.forEach(it -> addChildElementRole(chElement, it));
        }
    }

    static void addChildElementTraceList(SOAPElement element, List<Trace> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Traces");
            list.forEach(it -> addChildElementTrace(chElement, it));
        }
    }

    static void addChildElementAssemblyList(SOAPElement element, List<Assembly> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Assemblies");
            list.forEach(it -> addChildElementAssembly(chElement, it));
        }
    }

    static void addChildElementDatabaseList(SOAPElement element, List<Database> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Databases");
            list.forEach(it -> addChildElementDatabase(chElement, it));
        }
    }

    static void addChildElementRole(SOAPElement element, Role it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Role");
            addChildElement(chElement, NAME_LC, it.name());
            it.id().ifPresent(v -> addChildElement(chElement, ID, v));
            it.createdTimestamp().ifPresent(v -> addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(v)));
            it.lastSchemaUpdate().ifPresent(v -> addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(v)));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
            it.members().ifPresent(v -> addChildElementMemberList(chElement, v));
        }
    }

    static void addChildElementMemberList(SOAPElement element, List<Member> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Members");
            list.forEach(it -> addChildElementMember(chElement, it));
        }
    }

    static void addChildElementMember(SOAPElement element, Member it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Member");
            it.name().ifPresent(v -> addChildElement(chElement, NAME_LC, v));
            it.sid().ifPresent(v -> addChildElement(chElement, "Sid", v));
        }
    }

    static void addChildElementPerspective(SOAPElement element, Perspective it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Perspective");
            addChildElement(chElement, NAME, it.name());
            addChildElement(chElement, ID, it.id());
            addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(it.createdTimestamp()));
            addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(it.lastSchemaUpdate()));
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElementAnnotationList(chElement, it.annotations());
            addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, it.translations());
            addChildElement(chElement, "DefaultMeasure", it.defaultMeasure());
            addChildElementPerspectiveDimensionList(chElement, it.dimensions());
            addChildElementPerspectiveMeasureGroupList(chElement, it.measureGroups());
            addChildElementPerspectiveCalculationList(chElement, it.calculations());
            addChildElementPerspectiveKpiList(chElement, it.kpis());
            addChildElementPerspectiveActionList(chElement, it.actions());
        }
    }

    static void addChildElementPerspectiveActionList(SOAPElement element, List<PerspectiveAction> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Actions");
            list.forEach(it -> addChildElementPerspectiveAction(chElement, it));
        }
    }

    static void addChildElementPerspectiveAction(SOAPElement element, PerspectiveAction it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Action");
            addChildElement(chElement, "ActionID", it.actionID());
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
        }
    }

    static void addChildElementPerspectiveKpiList(SOAPElement element, List<PerspectiveKpi> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Kpis");
            list.forEach(it -> addChildElementPerspectiveKpi(chElement, it));
        }
    }

    static void addChildElementPerspectiveKpi(SOAPElement element, PerspectiveKpi it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Kpi");
            addChildElement(chElement, "KpiID", it.kpiID());
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
        }
    }

    static void addChildElementPerspectiveCalculationList(SOAPElement element, List<PerspectiveCalculation> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Calculations");
            list.forEach(it -> addChildElementPerspectiveCalculation(chElement, it));
        }
    }

    static void addChildElementPerspectiveCalculation(SOAPElement element, PerspectiveCalculation it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Calculation");
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, "Type", it.type());
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
        }
    }

    static void addChildElementPerspectiveMeasureGroupList(SOAPElement element, List<PerspectiveMeasureGroup> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "MeasureGroups");
            list.forEach(it -> addChildElementPerspectiveMeasureGroup(chElement, it));
        }

    }

    static void addChildElementPerspectiveMeasureGroup(SOAPElement element, PerspectiveMeasureGroup it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "MeasureGroup");
            addChildElement(chElement, MEASURE_GROUP_ID, it.measureGroupID());
            it.measures().ifPresent(v -> addChildElementPerspectiveMeasureList(chElement, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
        }
    }

    static void addChildElementPerspectiveMeasureList(SOAPElement element, List<PerspectiveMeasure> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, MEASURES);
            list.forEach(it -> addChildElementPerspectiveMeasure(chElement, it));
        }

    }

    static void addChildElementPerspectiveMeasure(SOAPElement element, PerspectiveMeasure it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, MEASURE);
            addChildElement(chElement, MEASURE_ID, it.measureID());
            addChildElementAnnotationList(chElement, it.annotations());
        }
    }

    static void addChildElementPerspectiveDimensionList(SOAPElement element, List<PerspectiveDimension> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, DIMENSIONS);
            list.forEach(it -> addChildElementPerspectiveDimension(chElement, it));
        }
    }

    static void addChildElementPerspectiveDimension(SOAPElement element, PerspectiveDimension it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, DIMENSION);
            addChildElement(chElement, CUBE_DIMENSION_ID, it.cubeDimensionID());
            it.attributes().ifPresent(v -> addChildElementPerspectiveAttributeList(chElement, v));
            it.hierarchies().ifPresent(v -> addChildElementPerspectiveHierarchyList(chElement, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
        }
    }

    static void addChildElementPerspectiveHierarchyList(SOAPElement element, List<PerspectiveHierarchy> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, HIERARCHIES);
            list.forEach(it -> addChildElementPerspectiveHierarchy(chElement, it));
        }
    }

    static void addChildElementPerspectiveHierarchy(SOAPElement element, PerspectiveHierarchy it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, HIERARCHY);
            addChildElement(chElement, "HierarchyID", it.hierarchyID());
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
        }
    }

    static void addChildElementPerspectiveAttributeList(SOAPElement element, List<PerspectiveAttribute> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, ATTRIBUTES);
            list.forEach(it -> addChildElementPerspectiveAttribute(chElement, it));
        }
    }

    static void addChildElementPerspectiveAttribute(SOAPElement element, PerspectiveAttribute it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, ATTRIBUTE);
            addChildElement(chElement, ATTRIBUTE_ID, it.attributeID());
            it.attributeHierarchyVisible()
                    .ifPresent(v -> addChildElement(chElement, ATTRIBUTE_HIERARCHY_VISIBLE, String.valueOf(v)));
            it.defaultMember().ifPresent(v -> addChildElement(chElement, DEFAULT_MEMBER, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
        }
    }

    static void addChildElementPermission(SOAPElement element, String tagName, Permission it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, tagName);
            addChildElement(chElement, NAME_LC, it.name());
            it.id().ifPresent(v -> addChildElement(chElement, ID, v));
            it.createdTimestamp().ifPresent(v -> addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(v)));
            it.lastSchemaUpdate().ifPresent(v -> addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(v)));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
            addChildElement(chElement, ROLE_ID, it.roleID());
            it.process().ifPresent(v -> addChildElement(chElement, PROCESS, String.valueOf(v)));
            it.readDefinition().ifPresent(v -> addChildElement(chElement, READ_DEFINITION, v.getValue()));
            it.read().ifPresent(v -> addChildElement(chElement, "Read", v.getValue()));
            it.write().ifPresent(v -> addChildElement(chElement, WRITE, v.getValue()));
            if (it instanceof DatabasePermission dp) {
                dp.administer().ifPresent(v -> addChildElement(chElement, "Administer", String.valueOf(v)));
            }
            if (it instanceof DataSourcePermission) {

            }
            if (it instanceof DimensionPermission dp) {
                dp.attributePermissions().ifPresent(v -> addChildElementAttributePermissionList(chElement, v));
                dp.allowedRowsExpression().ifPresent(v -> addChildElement(chElement, "AllowedRowsExpression", v));
            }
            if (it instanceof MiningStructurePermission mp) {
                mp.allowDrillThrough()
                        .ifPresent(v -> addChildElement(chElement, ALLOW_DRILL_THROUGH, String.valueOf(v)));
            }
            if (it instanceof MiningModelPermission mmp) {
                mmp.allowDrillThrough()
                        .ifPresent(v -> addChildElement(chElement, ALLOW_DRILL_THROUGH, String.valueOf(v)));
                mmp.allowBrowsing().ifPresent(v -> addChildElement(chElement, "AllowBrowsing", String.valueOf(v)));

            }
            if (it instanceof CubePermission cp) {
                cp.readSourceData().ifPresent(v -> addChildElement(chElement, "ReadSourceData", v));
                cp.dimensionPermissions().ifPresent(v -> addChildElementCubeDimensionPermissionList(chElement, v));
                cp.cellPermissions().ifPresent(v -> addChildElementCellPermissionList(chElement, v));
            }
        }
    }

    static void addChildElementPartition(SOAPElement element, Partition it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Partition");
            addChildElement(chElement, NAME, it.name());
            addChildElement(chElement, ID, it.id());
            addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(it.createdTimestamp()));
            addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(it.lastSchemaUpdate()));
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElementAnnotationList(chElement, it.annotations());
            addChildElementTabularBinding(chElement, it.source());
            addChildElement(chElement, PROCESSING_PRIORITY, String.valueOf(it.processingPriority()));
            addChildElement(chElement, AGGREGATION_PREFIX, it.aggregationPrefix());
            addChildElementPartitionStorageMode(chElement, it.storageMode());
            addChildElement(chElement, PROCESSING_MODE, it.processingMode());
            addChildElementErrorConfiguration(chElement, it.errorConfiguration());
            addChildElement(chElement, STORAGE_LOCATION, it.storageLocation());
            addChildElement(chElement, "RemoteDatasourceID", it.remoteDatasourceID());
            addChildElement(chElement, "Slice", it.slice());
            addChildElementProactiveCaching(chElement, it.proactiveCaching());
            addChildElement(chElement, "Type", it.type());
            addChildElement(chElement, ESTIMATED_SIZE, String.valueOf(it.estimatedSize()));
            addChildElement(chElement, ESTIMATED_ROWS, String.valueOf(it.estimatedRows()));
            addChildElementPartitionCurrentStorageMode(chElement, it.currentStorageMode());
            addChildElement(chElement, AGGREGATION_DESIGN_ID, it.aggregationDesignID());
            addChildElementAggregationInstanceList(chElement, it.aggregationInstances());
            addChildElementDataSourceViewBinding(chElement, "AggregationInstanceSource",
                    it.aggregationInstanceSource());
            addChildElement(chElement, LAST_PROCESSED, convertInstant(it.lastProcessed()));
            addChildElement(chElement, STATE, it.state());
            addChildElement(chElement, "StringStoresCompatibilityLevel",
                    String.valueOf(it.stringStoresCompatibilityLevel()));
            addChildElement(chElement, "CurrentStringStoresCompatibilityLevel",
                    String.valueOf(it.currentStringStoresCompatibilityLevel()));
            addChildElement(chElement, "DirectQueryUsage", it.directQueryUsage());
        }
    }

    static void addChildElementAggregationInstanceList(SOAPElement element, List<AggregationInstance> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "AggregationInstances");
            list.forEach(it -> addChildElementAggregationInstance(chElement, it));
        }
    }

    static void addChildElementAggregationInstance(SOAPElement element, AggregationInstance it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "AggregationInstance");
            addChildElement(chElement, "AggregationType", it.aggregationType());
            addChildElementTabularBinding(chElement, it.source());
            addChildElementAggregationInstanceDimensionList(chElement, it.dimensions());
            addChildElementAggregationInstanceMeasureList(chElement, it.measures());
        }
    }

    static void addChildElementAggregationInstanceMeasureList(SOAPElement element,
            List<AggregationInstanceMeasure> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, MEASURES);
            list.forEach(it -> addChildElementAggregationInstanceMeasure(chElement, it));
        }
    }

    static void addChildElementAggregationInstanceMeasure(SOAPElement element, AggregationInstanceMeasure it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, MEASURE);
            addChildElement(chElement, MEASURE_ID, it.measureID());
            addChildElementColumnBinding(chElement, it.source());
        }
    }

    static void addChildElementColumnBinding(SOAPElement element, ColumnBinding it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, SOURCE);
            addChildElement(chElement, TABLE_ID, it.tableID());
            addChildElement(chElement, COLUMN_ID, it.columnID());
        }
    }

    static void addChildElementAggregationInstanceDimensionList(SOAPElement element,
            List<AggregationInstanceDimension> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, DIMENSIONS);
            list.forEach(it -> addChildElementAggregationInstanceDimension(chElement, it));
        }
    }

    static void addChildElementAggregationInstanceDimension(SOAPElement element, AggregationInstanceDimension it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, DIMENSION);
            addChildElement(chElement, CUBE_DIMENSION_ID, it.cubeDimensionID());
            it.attributes().ifPresent(v -> addChildElementAggregationInstanceAttributeList(chElement, v));
        }
    }

    static void addChildElementAggregationInstanceAttributeList(SOAPElement element,
            List<AggregationInstanceAttribute> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, ATTRIBUTES);
            list.forEach(it -> addChildElementAggregationInstanceAttribute(chElement, it));
        }
    }

    static void addChildElementAggregationInstanceAttribute(SOAPElement element, AggregationInstanceAttribute it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, ATTRIBUTE);
            addChildElement(chElement, ATTRIBUTE_ID, it.attributeID());
            it.keyColumns().ifPresent(v -> addChildElementDataItemList(chElement, KEY_COLUMNS, KEY_COLUMN, v));
        }
    }

    static void addChildElementDataItemList(SOAPElement element, String wrapperName, String tagName,
            List<DataItem> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, wrapperName);
            list.forEach(it -> addChildElementDataItem(chElement, tagName, it));
        }
    }

    static void addChildElementDataItem(SOAPElement element, String tagName, DataItem it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, tagName);
            addChildElement(chElement, "DataType", it.dataType());
            it.dataSize().ifPresent(v -> addChildElement(chElement, "DataSize", String.valueOf(v)));
            it.mimeType().ifPresent(v -> addChildElement(chElement, "MimeType", v));
            it.nullProcessing().ifPresent(v -> addChildElement(chElement, "NullProcessing", v.getValue()));
            it.trimming().ifPresent(v -> addChildElement(chElement, "Trimming", v));
            it.invalidXmlCharacters().ifPresent(v -> addChildElement(chElement, "InvalidXmlCharacters", v.getValue()));
            it.collation().ifPresent(v -> addChildElement(chElement, COLLATION, v));
            it.format().ifPresent(v -> addChildElement(chElement, "Format", v.getValue()));
            it.source().ifPresent(v -> addChildElementBinding(chElement, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
        }
    }

    static void addChildElementPartitionCurrentStorageMode(SOAPElement element, Partition.CurrentStorageMode it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "CurrentStorageMode");
            chElement.setTextContent(it.value().value());
            chElement.setAttribute(VALUENS, it.valuens());
        }
    }

    static void addChildElementPartitionStorageMode(SOAPElement element, Partition.StorageMode it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, STORAGE_MODE);
            chElement.setTextContent(it.value().value());
            chElement.setAttribute(VALUENS, it.valuens());
        }
    }

    static void addChildElementTabularBinding(SOAPElement element, TabularBinding it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, SOURCE);
            if (it instanceof TableBinding tb) {
                tb.dataSourceID().ifPresent(v -> addChildElement(chElement, DATA_SOURCE_ID, v));
                addChildElement(chElement, "DbTableName", tb.dbTableName());
                tb.dbSchemaName().ifPresent(v -> addChildElement(chElement, "DbSchemaName", v));
            }
            if (it instanceof QueryBinding qb) {
                qb.dataSourceID().ifPresent(v -> addChildElement(chElement, DATA_SOURCE_ID, v));
                addChildElement(chElement, "QueryDefinition", qb.queryDefinition());
            }
            if (it instanceof DSVTableBinding dtb) {
                dtb.dataSourceViewID().ifPresent(v -> addChildElement(chElement, DATA_SOURCE_VIEW_ID, v));
                addChildElement(chElement, TABLE_ID, dtb.tableID());
                dtb.dataEmbeddingStyle().ifPresent(v -> addChildElement(chElement, "DataEmbeddingStyle", v));
            }
        }
    }

    static void addChildElementMiningStructure(SOAPElement element, MiningStructure it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "MiningStructure");
            addChildElement(chElement, NAME, it.name());
            it.id().ifPresent(v -> addChildElement(chElement, ID, v));
            it.createdTimestamp().ifPresent(v -> addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(v)));
            it.lastSchemaUpdate().ifPresent(v -> addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(v)));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
            it.source().ifPresent(v -> addChildElementBinding(chElement, v));
            it.lastProcessed().ifPresent(v -> addChildElement(chElement, LAST_PROCESSED, convertInstant(v)));
            it.translations().ifPresent(v -> addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, v));
            it.language().ifPresent(v -> addChildElement(chElement, LANGUAGE, String.valueOf(v)));
            it.collation().ifPresent(v -> addChildElement(chElement, COLLATION, v));
            it.errorConfiguration().ifPresent(v -> addChildElementErrorConfiguration(chElement, v));
            it.cacheMode().ifPresent(v -> addChildElement(chElement, "CacheMode", v));
            it.holdoutMaxPercent().ifPresent(v -> addChildElement(chElement, "HoldoutMaxPercent", String.valueOf(v)));
            it.holdoutMaxCases().ifPresent(v -> addChildElement(chElement, "HoldoutMaxCases", String.valueOf(v)));
            it.holdoutSeed().ifPresent(v -> addChildElement(chElement, "HoldoutSeed", String.valueOf(v)));
            it.holdoutActualSize().ifPresent(v -> addChildElement(chElement, "HoldoutActualSize", String.valueOf(v)));
            addChildElementMiningStructureColumnList(chElement, it.columns());
            it.state().ifPresent(v -> addChildElement(chElement, STATE, v));
            it.miningStructurePermissions().ifPresent(v -> addChildElementMiningStructurePermissionList(chElement, v));
            it.miningModels().ifPresent(v -> addChildElementMiningModelList(chElement, v));
        }
    }

    static void addChildElementMiningModelList(SOAPElement element, List<MiningModel> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "MiningModels");
            list.forEach(it -> addChildElementMiningModel(chElement, it));
        }
    }

    static void addChildElementMiningStructurePermissionList(SOAPElement element,
            List<MiningStructurePermission> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "MiningStructurePermissions");
            list.forEach(it -> addChildElementMiningStructurePermission(chElement, it));

        }

    }

    static void addChildElementMiningStructurePermission(SOAPElement element, MiningStructurePermission it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "MiningStructurePermission");
            addChildElement(chElement, NAME_LC, it.name());
            it.id().ifPresent(v -> addChildElement(chElement, ID, v));
            it.createdTimestamp().ifPresent(v -> addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(v)));
            it.lastSchemaUpdate().ifPresent(v -> addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(v)));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
            addChildElement(chElement, ROLE_ID, it.roleID());
            it.process().ifPresent(v -> addChildElement(chElement, PROCESS, String.valueOf(v)));
            it.readDefinition().ifPresent(v -> addChildElement(chElement, READ_DEFINITION, v.getValue()));
            it.read().ifPresent(v -> addChildElement(chElement, "Read", v.getValue()));
            it.write().ifPresent(v -> addChildElement(chElement, WRITE, v.getValue()));
            it.allowDrillThrough().ifPresent(v -> addChildElement(chElement, "AllowDrillThrough", String.valueOf(v)));
        }
    }

    static void addChildElementMiningStructureColumnList(SOAPElement element, List<MiningStructureColumn> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, COLUMNS);
            list.forEach(it -> addChildElementMiningStructureColumn(chElement, it));
        }
    }

    static void addChildElementMiningStructureColumn(SOAPElement element, MiningStructureColumn it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Column");
            if (it instanceof ScalarMiningStructureColumn smsc) {
                addChildElement(chElement, NAME_LC, smsc.name());
                smsc.id().ifPresent(v -> addChildElement(chElement, ID, v));
                smsc.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
                smsc.type().ifPresent(v -> addChildElement(chElement, "Type", v));
                smsc.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
                smsc.isKey().ifPresent(v -> addChildElement(chElement, "IsKey", String.valueOf(v)));
                smsc.source().ifPresent(v -> addChildElementBinding(chElement, v));
                smsc.distribution().ifPresent(v -> addChildElement(chElement, "Distribution", v));
                smsc.modelingFlags().ifPresent(v -> addChildElementMiningModelingFlagList(chElement, v));
                addChildElement(chElement, "Content", smsc.content());
                smsc.classifiedColumns().ifPresent(v -> addChildElementClassifiedColumnList(chElement, v));
                smsc.discretizationMethod().ifPresent(v -> addChildElement(chElement, "DiscretizationMethod", v));
                smsc.discretizationBucketCount()
                        .ifPresent(v -> addChildElement(chElement, "DiscretizationBucketCount", String.valueOf(v)));
                smsc.keyColumns().ifPresent(v -> addChildElementDataItemList(chElement, KEY_COLUMNS, KEY_COLUMN, v));
                smsc.nameColumn().ifPresent(v -> addChildElementDataItem(chElement, "NameColumn", v));
                smsc.translations()
                        .ifPresent(v -> addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, v));
            }
            if (it instanceof TableMiningStructureColumn tmsc) {
                tmsc.foreignKeyColumns().ifPresent(
                        v -> addChildElementDataItemList(chElement, "ForeignKeyColumns", "ForeignKeyColumn", v));
                tmsc.sourceMeasureGroup()
                        .ifPresent(v -> addChildElementMeasureGroupBinding(chElement, "SourceMeasureGroup", v));
                tmsc.columns().ifPresent(v -> addChildElementMiningStructureColumnList(chElement, v));
                tmsc.translations()
                        .ifPresent(v -> addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, v));
            }
        }
    }

    static void addChildElementMeasureGroupBinding(SOAPElement element, String tagName, MeasureGroupBinding it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, tagName);
            addChildElement(chElement, DATA_SOURCE_ID, it.dataSourceID());
            addChildElement(chElement, CUBE_ID, it.cubeID());
            addChildElement(chElement, MEASURE_GROUP_ID, it.measureGroupID());
            it.persistence().ifPresent(v -> addChildElement(chElement, PERSISTENCE, v.getValue()));
            it.refreshPolicy().ifPresent(v -> addChildElement(chElement, REFRESH_POLICY, v.getValue()));
            it.refreshInterval().ifPresent(v -> addChildElement(chElement, REFRESH_INTERVAL, convertDuration(v)));
            it.filter().ifPresent(v -> addChildElement(chElement, FILTER, v));
        }
    }

    static void addChildElementClassifiedColumnList(SOAPElement element, List<String> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "ClassifiedColumns");
            list.forEach(it -> addChildElement(chElement, "ClassifiedColumn").setTextContent(it));
        }
    }

    static void addChildElementMiningModelingFlagList(SOAPElement element, List<MiningModelingFlag> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "ModelingFlags");
            list.forEach(it -> addChildElementMiningModelingFlag(chElement, it));
        }
    }

    static void addChildElementMiningModelingFlag(SOAPElement element, MiningModelingFlag it) {
        if (it != null) {
            it.modelingFlag().ifPresent(v -> addChildElement(element, "ModelingFlag", v));
        }
    }

    static void addChildElementMiningModel(SOAPElement element, MiningModel it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "MiningModel");
            addChildElement(chElement, NAME, it.name());
            it.id().ifPresent(v -> addChildElement(chElement, ID, v));
            it.createdTimestamp().ifPresent(v -> addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(v)));
            it.lastSchemaUpdate().ifPresent(v -> addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(v)));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
            addChildElement(chElement, "Algorithm", it.algorithm());
            it.lastProcessed().ifPresent(v -> addChildElement(chElement, LAST_PROCESSED, convertInstant(v)));
            it.algorithmParameters().ifPresent(v -> addChildElementAlgorithmParameterList(chElement, v));
            it.allowDrillThrough().ifPresent(v -> addChildElement(chElement, ALLOW_DRILL_THROUGH, String.valueOf(v)));
            it.translations().ifPresent(v -> addChildElementAttributeTranslationList(chElement, v));
            it.columns().ifPresent(v -> addChildElementMiningModelColumnList(chElement, v));
            it.state().ifPresent(v -> addChildElement(chElement, STATE, v));
            it.foldingParameters().ifPresent(v -> addChildElementFoldingParameters(chElement, v));
            it.filter().ifPresent(v -> addChildElement(chElement, FILTER, v));
            it.miningModelPermissions().ifPresent(v -> addChildElementMiningModelPermissionList(chElement, v));
            it.language().ifPresent(v -> addChildElement(chElement, LANGUAGE, v));
            it.collation().ifPresent(v -> addChildElement(chElement, COLLATION, v));
        }
    }

    static void addChildElementMiningModelPermissionList(SOAPElement element, List<MiningModelPermission> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "MiningModelPermissions");
            list.forEach(it -> addChildElementMiningModelPermission(chElement, it));
        }
    }

    static void addChildElementMiningModelPermission(SOAPElement element, MiningModelPermission it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "MiningModelPermission");
            addChildElement(chElement, NAME_LC, it.name());
            it.id().ifPresent(v -> addChildElement(chElement, ID, v));
            it.createdTimestamp().ifPresent(v -> addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(v)));
            it.lastSchemaUpdate().ifPresent(v -> addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(v)));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
            addChildElement(chElement, ROLE_ID, it.roleID());
            it.process().ifPresent(v -> addChildElement(chElement, PROCESS, String.valueOf(v)));
            it.readDefinition().ifPresent(v -> addChildElement(chElement, READ_DEFINITION, v.getValue()));
            it.read().ifPresent(v -> addChildElement(chElement, "Read", v.getValue()));
            it.write().ifPresent(v -> addChildElement(chElement, WRITE, v.getValue()));
            it.allowDrillThrough().ifPresent(v -> addChildElement(chElement, ALLOW_DRILL_THROUGH, String.valueOf(v)));
            it.allowBrowsing().ifPresent(v -> addChildElement(chElement, "AllowBrowsing", String.valueOf(v)));
        }
    }

    static void addChildElementFoldingParameters(SOAPElement element, FoldingParameters it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "FoldingParameters");
            addChildElement(chElement, "FoldIndex", String.valueOf(it.foldIndex()));
            addChildElement(chElement, "FoldCount", String.valueOf(it.foldCount()));
            it.foldMaxCases().ifPresent(v -> addChildElement(chElement, "FoldMaxCases", String.valueOf(v)));
            it.foldTargetAttribute().ifPresent(v -> addChildElement(chElement, "FoldTargetAttribute", v));
        }
    }

    static void addChildElementMiningModelColumnList(SOAPElement element, List<MiningModelColumn> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, COLUMNS);
            list.forEach(it -> addChildElementMiningModelColumn(chElement, it));
        }
    }

    static void addChildElementMiningModelColumn(SOAPElement element, MiningModelColumn it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Column");
            addChildElement(chElement, NAME_LC, it.name());
            it.id().ifPresent(v -> addChildElement(chElement, ID, v));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.sourceColumnID().ifPresent(v -> addChildElement(chElement, "SourceColumnID", v));
            it.usage().ifPresent(v -> addChildElement(chElement, "Usage", v));
            it.filter().ifPresent(v -> addChildElement(chElement, FILTER, v));
            it.translations().ifPresent(v -> addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, v));
            it.columns().ifPresent(v -> addChildElementMiningModelColumnList(chElement, v));
            it.modelingFlags().ifPresent(v -> addChildElementMiningModelingFlagList(chElement, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
        }
    }

    static void addChildElementAttributeTranslationList(SOAPElement element, List<AttributeTranslation> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, TRANSLATIONS);
            list.forEach(it -> addChildElementAttributeTranslation(chElement, it));
        }
    }

    static void addChildElementAttributeTranslation(SOAPElement element, AttributeTranslation it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, TRANSLATION);
            addChildElement(chElement, LANGUAGE, String.valueOf(it.language()));
            it.caption().ifPresent(v -> addChildElement(chElement, CAPTION, v));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.displayFolder().ifPresent(v -> addChildElement(chElement, DISPLAY_FOLDER, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
            it.captionColumn().ifPresent(v -> addChildElementDataItem(chElement, "CaptionColumn", v));
            it.membersWithDataCaption().ifPresent(v -> addChildElement(chElement, "MembersWithDataCaption", v));
        }
    }

    static void addChildElementAlgorithmParameterList(SOAPElement element, List<AlgorithmParameter> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "AlgorithmParameters");
            list.forEach(it -> addChildElementAlgorithmParameter(chElement, it));
        }
    }

    static void addChildElementAlgorithmParameter(SOAPElement element, AlgorithmParameter it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "AlgorithmParameter");
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, VALUE2, it.value().toString());
        }
    }

    static void addChildElementMeasureGroup(SOAPElement element, MeasureGroup it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "MeasureGroup");
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, ID, it.id());
            addChildElement(chElement, CREATED_TIMESTAMP, String.valueOf(it.createdTimestamp()));
            addChildElement(chElement, LAST_SCHEMA_UPDATE, String.valueOf(it.lastSchemaUpdate()));
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElementAnnotationList(chElement, it.annotations());
            addChildElement(chElement, LAST_PROCESSED, String.valueOf(it.lastProcessed()));
            addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, it.translations());
            addChildElement(chElement, "Type", it.type());
            addChildElement(chElement, STATE, it.state());
            addChildElementMeasureList(chElement, it.measures());
            addChildElement(chElement, "DataAggregation", it.dataAggregation());
            addChildElementMeasureGroupBinding(chElement, "Source", it.source());
            addChildElementMeasureGroupStorageMode(chElement, it.storageMode());
            addChildElement(chElement, STORAGE_LOCATION, it.storageLocation());
            addChildElement(chElement, "IgnoreUnrelatedDimensions", String.valueOf(it.ignoreUnrelatedDimensions()));
            addChildElementProactiveCaching(chElement, it.proactiveCaching());
            addChildElement(chElement, ESTIMATED_ROWS, String.valueOf(it.estimatedRows()));
            addChildElementErrorConfiguration(chElement, it.errorConfiguration());
            addChildElement(chElement, ESTIMATED_SIZE, String.valueOf(it.estimatedSize()));
            addChildElement(chElement, PROCESSING_MODE, it.processingMode());
            addChildElementMeasureGroupDimensionList(chElement, it.dimensions());
            addChildElementPartitionList(chElement, it.partitions());
            addChildElement(chElement, AGGREGATION_PREFIX, it.aggregationPrefix());
            addChildElement(chElement, PROCESSING_PRIORITY, String.valueOf(it.processingPriority()));
            addChildElementAggregationDesignList(chElement, it.aggregationDesigns());
        }
    }

    static void addChildElementAggregationDesignList(SOAPElement element, List<AggregationDesign> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "AggregationDesigns");
            list.forEach(it -> addChildElementAggregationDesign(chElement, it));
        }
    }

    static void addChildElementPartitionList(SOAPElement element, List<Partition> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Partitions");
            list.forEach(it -> addChildElementPartition(chElement, it));
        }
    }

    static void addChildElementMeasureGroupDimensionList(SOAPElement element, List<MeasureGroupDimension> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, DIMENSIONS);
            list.forEach(it -> addChildElementMeasureGroupDimension(chElement, it));
        }
    }

    static void addChildElementMeasureGroupDimension(SOAPElement element, MeasureGroupDimension it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, DIMENSION);
            if (it instanceof ManyToManyMeasureGroupDimension d) {
                addChildElement(chElement, MEASURE_GROUP_ID, String.valueOf(d.measureGroupID()));
                addChildElement(chElement, "DirectSlice", String.valueOf(d.directSlice()));
                addChildElement(chElement, CUBE_DIMENSION_ID, String.valueOf(d.cubeDimensionID()));
                addChildElementAnnotationList(chElement, d.annotations());
                addChildElementMeasureGroupDimensionBinding(chElement, d.source());
            }
            if (it instanceof RegularMeasureGroupDimension d) {
                addChildElement(chElement, "Cardinality", d.cardinality());
                addChildElementMeasureGroupAttributeList(chElement, d.attributes());
                addChildElement(chElement, CUBE_DIMENSION_ID, String.valueOf(d.cubeDimensionID()));
                addChildElementAnnotationList(chElement, d.annotations());
                addChildElementMeasureGroupDimensionBinding(chElement, d.source());

            }
            if (it instanceof ReferenceMeasureGroupDimension d) {
                addChildElement(chElement, "IntermediateCubeDimensionID", d.intermediateCubeDimensionID());
                addChildElement(chElement, "IntermediateGranularityAttributeID",
                        d.intermediateGranularityAttributeID());
                addChildElement(chElement, "Materialization", d.materialization());
                addChildElement(chElement, PROCESSING_STATE, d.processingState());
                addChildElement(chElement, CUBE_DIMENSION_ID, String.valueOf(d.cubeDimensionID()));
                addChildElementAnnotationList(chElement, d.annotations());
                addChildElementMeasureGroupDimensionBinding(chElement, d.source());

            }
            if (it instanceof DegenerateMeasureGroupDimension d) {
                addChildElement(chElement, "ShareDimensionStorage", d.shareDimensionStorage());
                addChildElement(chElement, CUBE_DIMENSION_ID, String.valueOf(d.cubeDimensionID()));
                addChildElementAnnotationList(chElement, d.annotations());
                addChildElementMeasureGroupDimensionBinding(chElement, d.source());
            }
            if (it instanceof DataMiningMeasureGroupDimension d) {
                addChildElement(chElement, "CaseCubeDimensionID", d.caseCubeDimensionID());
                addChildElement(chElement, CUBE_DIMENSION_ID, String.valueOf(d.cubeDimensionID()));
                addChildElementAnnotationList(chElement, d.annotations());
                addChildElementMeasureGroupDimensionBinding(chElement, d.source());
            }
        }
    }

    static void addChildElementMeasureGroupAttributeList(SOAPElement element, List<MeasureGroupAttribute> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, ATTRIBUTES);
            list.forEach(it -> addChildElementMeasureGroupAttribute(chElement, it));
        }
    }

    static void addChildElementMeasureGroupAttribute(SOAPElement element, MeasureGroupAttribute it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, ATTRIBUTE);
            addChildElement(chElement, ATTRIBUTE_ID, it.attributeID());
            addChildElementDataItemList(chElement, KEY_COLUMNS, KEY_COLUMN, it.keyColumns());
            addChildElement(chElement, "Type", it.type());
            addChildElementAnnotationList(chElement, it.annotations());
        }
    }

    static void addChildElementMeasureGroupDimensionBinding(SOAPElement element, MeasureGroupDimensionBinding it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, SOURCE);
            addChildElement(chElement, CUBE_DIMENSION_ID, it.cubeDimensionID());
        }
    }

    static void addChildElementMeasureGroupStorageMode(SOAPElement element, MeasureGroup.StorageMode it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, STORAGE_MODE);
            chElement.setAttribute(VALUENS, it.valuens());
            addChildElement(chElement, VALUE, it.value() == null ? null : it.value().value());
        }

    }

    static void addChildElementMeasureList(SOAPElement element, List<Measure> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, MEASURES);
            list.forEach(it -> addChildElementMeasure(chElement, it));
        }

    }

    static void addChildElementMeasure(SOAPElement element, Measure it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, MEASURE);

            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, ID, it.id());
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElement(chElement, "AggregateFunction", it.aggregateFunction());
            addChildElement(chElement, "DataType", it.dataType());
            addChildElementDataItem(chElement, SOURCE, it.source());
            addChildElement(chElement, VISIBLE, String.valueOf(it.visible()));
            addChildElement(chElement, "MeasureExpression", it.measureExpression());
            addChildElement(chElement, DISPLAY_FOLDER, it.displayFolder());
            addChildElement(chElement, "FormatString", it.formatString());
            addChildElement(chElement, "BackColor", it.backColor());
            addChildElement(chElement, "ForeColor", it.foreColor());
            addChildElement(chElement, "FontName", it.fontName());
            addChildElement(chElement, "FontSize", it.fontSize());
            addChildElement(chElement, "FontFlags", it.fontFlags());
            addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, it.translations());
            addChildElementAnnotationList(chElement, it.annotations());
        }
    }

    static void addChildElementMdxScript(SOAPElement element, MdxScript it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "MdxScript");
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, ID, it.id());
            addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(it.createdTimestamp()));
            addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(it.lastSchemaUpdate()));
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElementAnnotationList(chElement, it.annotations());
            addChildElementCommandList(chElement, it.commands());
            addChildElement(chElement, "DefaultScript", String.valueOf(it.defaultScript()));
            addChildElementCalculationPropertyList(chElement, it.calculationProperties());
        }
    }

    static void addChildElementCalculationPropertyList(SOAPElement element, List<CalculationProperty> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "CalculationProperties");
            list.forEach(it -> addChildElementCalculationProperty(chElement, it));
        }
    }

    static void addChildElementCalculationProperty(SOAPElement element, CalculationProperty it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "CalculationProperty");
            addChildElement(chElement, "CalculationReference", it.calculationReference());
            addChildElement(chElement, "CalculationType", it.calculationType());
            addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, it.translations());
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElement(chElement, VISIBLE, String.valueOf(it.visible()));
            addChildElement(chElement, "SolveOrder", String.valueOf(it.solveOrder()));
            addChildElement(chElement, "FormatString", it.formatString());
            addChildElement(chElement, "ForeColor", it.foreColor());
            addChildElement(chElement, "BackColor", it.backColor());
            addChildElement(chElement, "FontName", it.fontName());
            addChildElement(chElement, "FontSize", it.fontSize());
            addChildElement(chElement, "FontFlags", it.fontFlags());
            addChildElement(chElement, "NonEmptyBehavior", it.nonEmptyBehavior());
            addChildElement(chElement, "AssociatedMeasureGroupID", it.associatedMeasureGroupID());
            addChildElement(chElement, DISPLAY_FOLDER, it.displayFolder());
            addChildElement(chElement, LANGUAGE, String.valueOf(it.language()));
            addChildElementCalculationPropertiesVisualizationProperties(chElement, it.visualizationProperties());
        }
    }

    static void addChildElementCalculationPropertiesVisualizationProperties(SOAPElement element,
            CalculationPropertiesVisualizationProperties it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, VISUALIZATION_PROPERTIES);
            addChildElement(chElement, "IsDefaultMeasure", String.valueOf(it.isDefaultMeasure()));
            addChildElement(chElement, "IsSimpleMeasure", String.valueOf(it.isSimpleMeasure()));
            addChildElement(chElement, FOLDER_POSITION, String.valueOf(it.folderPosition()));
            addChildElement(chElement, CONTEXTUAL_NAME_RULE, it.contextualNameRule());
            addChildElement(chElement, "Alignment", it.alignment());
            addChildElement(chElement, "IsFolderDefault", String.valueOf(it.isFolderDefault()));
            addChildElement(chElement, "IsRightToLeft", String.valueOf(it.isRightToLeft()));
            addChildElement(chElement, "SortDirection", it.sortDirection());
            addChildElement(chElement, "Units", it.units());
            addChildElement(chElement, "Width", String.valueOf(it.width()));
            addChildElement(chElement, SORT_PROPERTIES_POSITION, String.valueOf(it.sortPropertiesPosition()));
            addChildElement(chElement, DEFAULT_DETAILS_POSITION, String.valueOf(it.defaultDetailsPosition()));
        }
    }

    static void addChildElementCommandList(SOAPElement element, List<Command> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Commands");
            list.forEach(it -> addChildElementCommand(chElement, it));
        }
    }

    static void addChildElementCommand(SOAPElement element, Command it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, COMMAND);
            if (it instanceof Alter a) {
                addChildElementAlter(chElement, a);
            }
            if (it instanceof Attach) {
                throw new UnsupportedOperationException("Attach operation doesn't supported");
            }
            if (it instanceof Backup) {
                throw new UnsupportedOperationException("Backup operation doesn't supported");
            }
            if (it instanceof Batch) {
                throw new UnsupportedOperationException("Batch operation doesn't supported");
            }
            if (it instanceof BeginTransaction) {
                throw new UnsupportedOperationException("BeginTransaction operation doesn't supported");
            }
            if (it instanceof Cancel c) {
                addChildElementCancel(chElement, c);
            }
            if (it instanceof ClearCache c) {
                addChildElementClearCache(chElement, c);
            }
            if (it instanceof CloneDatabase) {
                throw new UnsupportedOperationException("CloneDatabase operation doesn't supported");
            }
            if (it instanceof CommitTransaction) {
                throw new UnsupportedOperationException("CommitTransaction operation doesn't supported");
            }
            if (it instanceof Create) {
                throw new UnsupportedOperationException("Create operation doesn't supported");
            }
            if (it instanceof DBCC) {
                throw new UnsupportedOperationException("DBCC operation doesn't supported");
            }
            if (it instanceof Delete) {
                throw new UnsupportedOperationException("Delete operation doesn't supported");
            }
            if (it instanceof DesignAggregations) {
                throw new UnsupportedOperationException("DesignAggregations operation doesn't supported");
            }
            if (it instanceof Detach) {
                throw new UnsupportedOperationException("Detach operation doesn't supported");
            }
            if (it instanceof Drop) {
                throw new UnsupportedOperationException("Drop operation doesn't supported");
            }
            if (it instanceof ImageLoad) {
                throw new UnsupportedOperationException("ImageLoad operation doesn't supported");
            }
            if (it instanceof ImageSave) {
                throw new UnsupportedOperationException("ImageSave operation doesn't supported");
            }
            if (it instanceof Insert) {
                throw new UnsupportedOperationException("Insert operation doesn't supported");
            }
            if (it instanceof Lock) {
                throw new UnsupportedOperationException("Lock operation doesn't supported");
            }
            if (it instanceof MergePartitions) {
                throw new UnsupportedOperationException("MergePartitions operation doesn't supported");
            }
            if (it instanceof NotifyTableChange) {
                throw new UnsupportedOperationException("NotifyTableChange operation doesn't supported");
            }
            if (it instanceof Process) {
                throw new UnsupportedOperationException("Process operation doesn't supported");
            }
            if (it instanceof Restore) {
                throw new UnsupportedOperationException("Restore operation doesn't supported");
            }
            if (it instanceof RollbackTransaction) {
                throw new UnsupportedOperationException("RollbackTransaction operation doesn't supported");
            }
            if (it instanceof SetAuthContext) {
                throw new UnsupportedOperationException("SetAuthContext operation doesn't supported");
            }
            if (it instanceof Statement) {
                throw new UnsupportedOperationException("Statement operation doesn't supported");
            }
            if (it instanceof Subscribe) {
                throw new UnsupportedOperationException("Subscribe operation doesn't supported");
            }
            if (it instanceof Synchronize) {
                throw new UnsupportedOperationException("Synchronize operation doesn't supported");
            }
            if (it instanceof Unlock) {
                throw new UnsupportedOperationException("Unlock operation doesn't supported");
            }
            if (it instanceof Unsubscribe) {
                throw new UnsupportedOperationException("Unsubscribe operation doesn't supported");
            }
            if (it instanceof Update) {
                throw new UnsupportedOperationException("Update operation doesn't supported");
            }
            if (it instanceof UpdateCells) {
                throw new UnsupportedOperationException("UpdateCells operation doesn't supported");
            }
        }
    }

    static void addChildElementAlter(SOAPElement element, Alter a) {
        if (a != null) {
            SOAPElement chElement = addChildElement(element, "Alter");
            addChildElementObjectReference(chElement, a.object());
            addChildElementMajorObject(chElement, a.objectDefinition());
            addChildElementScope(chElement, a.scope());
            addChildElement(chElement, "AllowCreate", String.valueOf(a.allowCreate()));
            addChildElementObjectExpansion(chElement, a.objectExpansion());
        }
    }

    static void addChildElementClearCache(SOAPElement element, ClearCache it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "ClearCache");
            addChildElementObject(chElement, it.object());
        }
    }

    static void addChildElementObjectExpansion(SOAPElement element, ObjectExpansion it) {
        if (it != null) {
            addChildElement(element, "ObjectExpansion", it.value());
        }
    }

    static void addChildElementScope(SOAPElement element, Scope it) {
        if (it != null) {
            addChildElement(element, "Scope", it.value());
        }
    }

    static void addChildElementMajorObject(SOAPElement element, MajorObject it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "ObjectDefinition");
            addChildElementAggregationDesign(chElement, it.aggregationDesign());
            addChildElementAssembly(chElement, it.assembly());
            addChildElementCube(chElement, it.cube());
            addChildElementDatabase(chElement, it.database());
            addChildElementDataSource(chElement, it.dataSource());
            addChildElementDataSourceView(chElement, it.dataSourceView());
            addChildElementDimension(chElement, DIMENSION, it.dimension());
            addChildElementMdxScript(chElement, it.mdxScript());
            addChildElementMeasureGroup(chElement, it.measureGroup());
            addChildElementMiningModel(chElement, it.miningModel());
            addChildElementMiningStructure(chElement, it.miningStructure());
            addChildElementPartition(chElement, it.partition());
            addChildElementPermission(chElement, "Permission", it.permission());
            addChildElementPerspective(chElement, it.perspective());
            addChildElementRole(chElement, it.role());
            addChildElementServer(chElement, it.server());
            addChildElementTrace(chElement, it.trace());
        }
    }

    static void addChildElementObjectReference(SOAPElement element, ObjectReference it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Object");
            addChildElement(chElement, SERVER_ID, it.serverID());
            addChildElement(chElement, "DatabaseID", it.databaseID());
            addChildElement(chElement, ROLE_ID, it.roleID());
            addChildElement(chElement, "TraceID", it.traceID());
            addChildElement(chElement, "AssemblyID", it.assemblyID());
            addChildElement(chElement, DIMENSION_ID, it.dimensionID());
            addChildElement(chElement, "DimensionPermissionID", it.dimensionPermissionID());
            addChildElement(chElement, DATA_SOURCE_ID, it.dataSourceID());
            addChildElement(chElement, "DataSourcePermissionID", it.dataSourcePermissionID());
            addChildElement(chElement, "DatabasePermissionID", it.databasePermissionID());
            addChildElement(chElement, DATA_SOURCE_VIEW_ID, it.dataSourceViewID());
            addChildElement(chElement, CUBE_ID, it.cubeID());
            addChildElement(chElement, "MiningStructureID", it.miningStructureID());
            addChildElement(chElement, MEASURE_GROUP_ID, it.measureGroupID());
            addChildElement(chElement, "PerspectiveID", it.perspectiveID());
            addChildElement(chElement, "CubePermissionID", it.cubePermissionID());
            addChildElement(chElement, "MdxScriptID", it.mdxScriptID());
            addChildElement(chElement, "PartitionID", it.partitionID());
            addChildElement(chElement, AGGREGATION_DESIGN_ID, it.aggregationDesignID());
            addChildElement(chElement, MINING_MODEL_ID, it.miningModelID());
            addChildElement(chElement, "MiningModelPermissionID", it.miningModelPermissionID());
            addChildElement(chElement, "MiningStructurePermissionID", it.miningStructurePermissionID());
        }
    }

    static void addChildElementDimension(SOAPElement element, String tagName, Dimension it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, tagName);
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, ID, it.id());
            addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(it.createdTimestamp()));
            addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(it.lastSchemaUpdate()));
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElementAnnotationList(chElement, it.annotations());
            addChildElementBinding(chElement, it.source());
            addChildElement(chElement, MINING_MODEL_ID, it.miningModelID());
            addChildElement(chElement, "Type", it.type());
            addChildElementDimensionUnknownMember(chElement, it.unknownMember());
            addChildElement(chElement, "MdxMissingMemberMode", it.mdxMissingMemberMode());
            addChildElementErrorConfiguration(chElement, it.errorConfiguration());
            addChildElement(chElement, STORAGE_MODE, it.storageMode());
            addChildElement(chElement, "WriteEnabled", String.valueOf(it.writeEnabled()));
            addChildElement(chElement, PROCESSING_PRIORITY, String.valueOf(it.processingPriority()));
            addChildElement(chElement, LAST_PROCESSED, convertInstant(it.lastProcessed()));
            addChildElementDimensionPermissionList(chElement, it.dimensionPermissions());
            addChildElement(chElement, "DependsOnDimensionID", it.dependsOnDimensionID());
            addChildElement(chElement, LANGUAGE, String.valueOf(it.language()));
            addChildElement(chElement, COLLATION, it.collation());
            addChildElement(chElement, "UnknownMemberName", it.unknownMemberName());
            addChildElementTranslationList(chElement, "UnknownMemberTranslations", "UnknownMemberTranslation",
                    it.translations());
            addChildElement(chElement, STATE, it.state());
            addChildElementProactiveCaching(chElement, it.proactiveCaching());
            addChildElement(chElement, PROCESSING_MODE, it.processingMode());
            addChildElement(chElement, "ProcessingGroup", it.processingGroup());
            addChildElementDimensionCurrentStorageMode(chElement, it.currentStorageMode());
            addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, it.translations());
            addChildElementDimensionAttributeList(chElement, it.attributes());
            addChildElement(chElement, "AttributeAllMemberName", it.attributeAllMemberName());
            addChildElementTranslationList(chElement, "AttributeAllMemberTranslations", "AttributeAllMemberTranslation",
                    it.translations());
            addChildElementHierarchyList(chElement, it.hierarchies());
            addChildElement(chElement, "ProcessingRecommendation", it.processingRecommendation());
            addChildElementRelationships(chElement, it.relationships());
            addChildElement(chElement, "StringStoresCompatibilityLevel",
                    String.valueOf(it.stringStoresCompatibilityLevel()));
            addChildElement(chElement, "CurrentStringStoresCompatibilityLevel",
                    String.valueOf(it.currentStringStoresCompatibilityLevel()));
        }
    }

    static void addChildElementRelationships(SOAPElement element, Relationships it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Relationships");
            addChildElementRelationshipList(chElement, it.relationship());
        }
    }

    static void addChildElementRelationshipList(SOAPElement element, List<Relationship> list) {
        if (list != null) {
            list.forEach(it -> addChildElementRelationship(element, it));
        }
    }

    static void addChildElementRelationship(SOAPElement element, Relationship it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Relationship");
            addChildElement(chElement, ID, it.id());
            addChildElement(chElement, VISIBLE, String.valueOf(it.visible()));
            addChildElementRelationshipEnd(chElement, "FromRelationshipEnd", it.fromRelationshipEnd());
            addChildElementRelationshipEnd(chElement, "ToRelationshipEnd", it.toRelationshipEnd());
        }
    }

    static void addChildElementRelationshipEnd(SOAPElement element, String nodeName, RelationshipEnd it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, nodeName);
            addChildElement(chElement, "Role", it.role());
            addChildElement(chElement, "Multiplicity", it.multiplicity());
            addChildElement(chElement, DIMENSION_ID, it.dimensionID());
            addChildElementRelationshipEndAttributes(chElement, it.attributes());
            addChildElementRelationshipEndTranslations(chElement, it.translations());
            addChildElementRelationshipEndVisualizationProperties(chElement, it.visualizationProperties());
        }
    }

    static void addChildElementRelationshipEndVisualizationProperties(SOAPElement element,
            RelationshipEndVisualizationProperties it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, VISUALIZATION_PROPERTIES);
            addChildElement(chElement, FOLDER_POSITION, String.valueOf(it.folderPosition()));
            addChildElement(chElement, CONTEXTUAL_NAME_RULE, it.contextualNameRule());
            addChildElement(chElement, DEFAULT_DETAILS_POSITION, String.valueOf(it.defaultDetailsPosition()));
            addChildElement(chElement, "DisplayKeyPosition", String.valueOf(it.displayKeyPosition()));
            addChildElement(chElement, "CommonIdentifierPosition", String.valueOf(it.commonIdentifierPosition()));
            addChildElement(chElement, "IsDefaultMeasure", String.valueOf(it.isDefaultMeasure()));
            addChildElement(chElement, "IsDefaultImage", String.valueOf(it.isDefaultImage()));
            addChildElement(chElement, SORT_PROPERTIES_POSITION, String.valueOf(it.sortPropertiesPosition()));
        }
    }

    static void addChildElementRelationshipEndTranslations(SOAPElement element, List<RelationshipEndTranslation> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, TRANSLATIONS);
            list.forEach(it -> addChildElementRelationshipEndTranslation(chElement, it));
        }
    }

    static void addChildElementRelationshipEndTranslation(SOAPElement element, RelationshipEndTranslation it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, TRANSLATION);
            addChildElement(chElement, LANGUAGE, String.valueOf(it.language()));
            addChildElement(chElement, CAPTION, it.caption());
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElement(chElement, DISPLAY_FOLDER, it.displayFolder());
            addChildElementAnnotationList(chElement, it.annotations());
            addChildElement(chElement, "CollectionCaption", it.collectionCaption());
        }
    }

    static void addChildElementRelationshipEndAttributes(SOAPElement element, List<String> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, ATTRIBUTES);
            list.forEach(it -> addChildElementRelationshipEndAttribute(chElement, it));
        }
    }

    static void addChildElementRelationshipEndAttribute(SOAPElement element, String it) {
        if (it != null) {
            addChildElement(addChildElement(element, ATTRIBUTE), ATTRIBUTE_ID, it);
        }
    }

    static void addChildElementHierarchyList(SOAPElement element, List<Hierarchy> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, HIERARCHIES);
            list.forEach(it -> addChildElementHierarchy(chElement, it));
        }
    }

    static void addChildElementHierarchy(SOAPElement element, Hierarchy it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, HIERARCHY);
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, ID, it.id());
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElement(chElement, PROCESSING_STATE, it.processingState());
            addChildElement(chElement, "StructureType", it.structureType());
            addChildElement(chElement, DISPLAY_FOLDER, it.displayFolder());
            addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, it.translations());
            addChildElement(chElement, "AllMemberName", it.allMemberName());
            addChildElementAnnotationList(chElement, it.annotations());
            addChildElementTranslationList(chElement, "AllMemberTranslations", "AllMemberTranslation",
                    it.allMemberTranslations());
            addChildElement(chElement, "MemberNamesUnique", String.valueOf(it.memberNamesUnique()));
            addChildElement(chElement, "MemberKeysUnique", String.valueOf(it.memberKeysUnique()));
            addChildElement(chElement, "AllowDuplicateNames", String.valueOf(it.allowDuplicateNames()));
            addChildElementLevelList(chElement, it.levels());
            addChildElementAnnotationList(chElement, it.annotations());
            addChildElementHierarchyVisualizationProperties(chElement, it.visualizationProperties());
        }
    }

    static void addChildElementHierarchyVisualizationProperties(SOAPElement element,
            HierarchyVisualizationProperties it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, VISUALIZATION_PROPERTIES);
            addChildElement(chElement, CONTEXTUAL_NAME_RULE, it.contextualNameRule());
            addChildElement(chElement, FOLDER_POSITION, String.valueOf(it.folderPosition()));
        }
    }

    static void addChildElementLevelList(SOAPElement element, List<Level> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Levels");
            list.forEach(it -> addChildElementLevel(chElement, it));
        }
    }

    static void addChildElementLevel(SOAPElement element, Level it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Level");
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, ID, it.id());
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElement(chElement, "SourceAttributeID", it.sourceAttributeID());
            addChildElement(chElement, "HideMemberIf", it.hideMemberIf());
            addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, it.translations());
            addChildElementAnnotationList(chElement, it.annotations());
        }
    }

    static void addChildElementDimensionAttributeList(SOAPElement element, List<DimensionAttribute> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, ATTRIBUTES);
            list.forEach(it -> addChildElementDimensionAttribute(chElement, it));
        }
    }

    static void addChildElementDimensionAttribute(SOAPElement element, DimensionAttribute it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, ATTRIBUTE);
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, ID, it.id());
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElementDimensionAttributeType(chElement, it.type());
            addChildElement(chElement, "Usage", it.usage());
            addChildElementBinding(chElement, it.source());
            addChildElement(chElement, "EstimatedCount", String.valueOf(it.estimatedCount()));
            addChildElementDataItemList(chElement, KEY_COLUMNS, KEY_COLUMN, it.keyColumns());
            addChildElementDataItem(chElement, "NameColumn", it.nameColumn());
            addChildElementDataItem(chElement, "ValueColumn", it.valueColumn());
            addChildElementAttributeTranslationList(chElement, it.translations());
            addChildElementAttributeRelationshipList(chElement, it.attributeRelationships());
            addChildElement(chElement, "DiscretizationMethod", it.discretizationMethod());
            addChildElement(chElement, "DiscretizationBucketCount", String.valueOf(it.discretizationBucketCount()));
            addChildElement(chElement, "RootMemberIf", it.rootMemberIf());
            addChildElement(chElement, "OrderBy", it.orderBy());
            addChildElement(chElement, DEFAULT_MEMBER, it.defaultMember());
            addChildElement(chElement, "OrderByAttributeID", it.orderByAttributeID());
            addChildElementDataItem(chElement, "SkippedLevelsColumn", it.skippedLevelsColumn());
            addChildElement(chElement, "NamingTemplate", it.namingTemplate());
            addChildElement(chElement, "MembersWithData", it.membersWithData());
            addChildElement(chElement, "MembersWithDataCaption", it.membersWithDataCaption());
            addChildElementTranslationList(chElement, "NamingTemplateTranslations", "NamingTemplateTranslation",
                    it.namingTemplateTranslations());
            addChildElementDataItem(chElement, "CustomRollupColumn", it.customRollupColumn());
            addChildElementDataItem(chElement, "CustomRollupPropertiesColumn", it.customRollupPropertiesColumn());
            addChildElementDataItem(chElement, "UnaryOperatorColumn", it.unaryOperatorColumn());
            addChildElement(chElement, "AttributeHierarchyOrdered", String.valueOf(it.attributeHierarchyOrdered()));
            addChildElement(chElement, "MemberNamesUnique", String.valueOf(it.memberNamesUnique()));
            addChildElement(chElement, "IsAggregatable", String.valueOf(it.isAggregatable()));
            addChildElement(chElement, "AttributeHierarchyEnabled", String.valueOf(it.attributeHierarchyEnabled()));
            addChildElement(chElement, "AttributeHierarchyOptimizedState", it.attributeHierarchyOptimizedState());
            addChildElement(chElement, ATTRIBUTE_HIERARCHY_VISIBLE, String.valueOf(it.attributeHierarchyVisible()));
            addChildElement(chElement, "AttributeHierarchyDisplayFolder", it.attributeHierarchyDisplayFolder());
            addChildElement(chElement, "KeyUniquenessGuarantee", String.valueOf(it.keyUniquenessGuarantee()));
            addChildElement(chElement, "GroupingBehavior", it.groupingBehavior());
            addChildElement(chElement, "InstanceSelection", it.instanceSelection());
            addChildElementAnnotationList(chElement, it.annotations());
            addChildElement(chElement, PROCESSING_STATE, it.processingState());
            addChildElement(chElement, "AttributeHierarchyProcessingState",
                    it.attributeHierarchyProcessingState() == null ? null
                            : it.attributeHierarchyProcessingState().value());
            addChildElementDimensionAttributeVisualizationProperties(chElement, it.visualizationProperties());
            addChildElement(chElement, "ExtendedType", it.extendedType());
        }
    }

    private static void addChildElementDimensionAttributeVisualizationProperties(SOAPElement element,
            DimensionAttributeVisualizationProperties it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "VisualizationProperties");

            addChildElement(chElement, FOLDER_POSITION, String.valueOf(it.folderPosition()));
            addChildElement(chElement, CONTEXTUAL_NAME_RULE, it.contextualNameRule());
            addChildElement(chElement, "Alignment", it.alignment());
            addChildElement(chElement, "IsFolderDefault", String.valueOf(it.isFolderDefault()));
            addChildElement(chElement, "IsRightToLeft", String.valueOf(it.isRightToLeft()));
            addChildElement(chElement, "SortDirection", it.sortDirection());
            addChildElement(chElement, "Units", it.units());
            addChildElement(chElement, "Width", String.valueOf(it.width()));
            addChildElement(chElement, DEFAULT_DETAILS_POSITION, String.valueOf(it.defaultDetailsPosition()));
            addChildElement(chElement, SORT_PROPERTIES_POSITION, String.valueOf(it.sortPropertiesPosition()));
            addChildElement(chElement, "CommonIdentifierPosition", String.valueOf(it.commonIdentifierPosition()));
            addChildElement(chElement, "DisplayKeyPosition", String.valueOf(it.displayKeyPosition()));
            addChildElement(chElement, "IsDefaultImage", String.valueOf(it.isDefaultImage()));
            addChildElement(chElement, "DefaultAggregateFunction", it.defaultAggregateFunction());
        }
    }

    static void addChildElementAttributeRelationshipList(SOAPElement element, List<AttributeRelationship> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "AttributeRelationships");
            list.forEach(it -> addChildElementAttributeRelationship(chElement, it));
        }

    }

    static void addChildElementAttributeRelationship(SOAPElement element, AttributeRelationship it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "AttributeRelationship");
            addChildElement(chElement, ATTRIBUTE_ID, it.attributeID());
            addChildElement(chElement, "RelationshipType", it.relationshipType());
            addChildElement(chElement, "Cardinality", it.cardinality());
            addChildElement(chElement, "Optionality", it.optionality());
            addChildElement(chElement, "OverrideBehavior", it.overrideBehavior());
            addChildElementAnnotationList(chElement, it.annotations());
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, VISIBLE, String.valueOf(it.visible()));
            addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, it.translations());
        }
    }

    static void addChildElementDimensionAttributeType(SOAPElement element, DimensionAttribute.Type it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Type");
            chElement.setAttribute(VALUENS, it.valuens());
            addChildElement(chElement, VALUE, it.value() == null ? null : it.value().value());
        }
    }

    static void addChildElementDimensionCurrentStorageMode(SOAPElement element, Dimension.CurrentStorageMode it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "CurrentStorageMode");
            chElement.setAttribute(VALUENS, it.valuens());
            addChildElement(chElement, VALUE, it.value() == null ? null : it.value().value());
        }
    }

    static void addChildElementDimensionPermissionList(SOAPElement element, List<DimensionPermission> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "DimensionPermissions");
            list.forEach(it -> addChildElementDimensionPermission(chElement, it));
        }

    }

    static void addChildElementDimensionPermission(SOAPElement element, DimensionPermission it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "DimensionPermission");
            addChildElement(chElement, ROLE_ID, it.roleID());
            it.process().ifPresent(v -> addChildElement(chElement, PROCESS, String.valueOf(v)));
            it.readDefinition().ifPresent(v -> addChildElement(chElement, READ_DEFINITION, v.getValue()));
            it.read().ifPresent(v -> addChildElement(chElement, "Read", v.getValue()));
            it.write().ifPresent(v -> addChildElement(chElement, WRITE, v.getValue()));

            addChildElement(chElement, NAME_LC, it.name());
            it.id().ifPresent(v -> addChildElement(chElement, ID, v));
            it.createdTimestamp().ifPresent(v -> addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(v)));
            it.lastSchemaUpdate().ifPresent(v -> addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(v)));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
            it.attributePermissions().ifPresent(v -> addChildElementAttributePermissionList(chElement, v));

            it.allowedRowsExpression().ifPresent(v -> addChildElement(chElement, "AllowedRowsExpression", v));
        }
    }

    static void addChildElementDimensionUnknownMember(SOAPElement element, Dimension.UnknownMember it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "UnknownMember");
            chElement.setAttribute(VALUENS, it.valuens());
            addChildElement(chElement, VALUE, it.value() == null ? null : it.value().value());
        }
    }

    static void addChildElementDataSourceView(SOAPElement element, DataSourceView it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "DataSourceView");
            addChildElement(chElement, DATA_SOURCE_ID, it.dataSourceID());

            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, ID, it.id());
            addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(it.createdTimestamp()));
            addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(it.lastSchemaUpdate()));
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElementAnnotationList(chElement, it.annotations());
        }
    }

    static void addChildElementDataSource(SOAPElement element, DataSource it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "DataSource");
            addChildElement(chElement, "ManagedProvider", it.managedProvider());
            addChildElement(chElement, "ConnectionString", it.connectionString());
            addChildElement(chElement, "ConnectionStringSecurity", it.connectionStringSecurity());
            addChildElementImpersonationInfo(chElement, "ImpersonationInfo", it.impersonationInfo());
            addChildElement(chElement, "Isolation", it.isolation());
            addChildElement(chElement, "MaxActiveConnections", String.valueOf(it.maxActiveConnections()));
            addChildElement(chElement, "Timeout", convertDuration(it.timeout()));
            addChildElementDataSourcePermissionList(chElement, it.dataSourcePermissions());
            addChildElementImpersonationInfo(chElement, "QueryImpersonationInfo", it.queryImpersonationInfo());
            addChildElement(chElement, "QueryHints", it.queryHints());
        }
    }

    static void addChildElementDataSourcePermissionList(SOAPElement element, List<DataSourcePermission> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "DataSourcePermissions");
            list.forEach(it -> addChildElementDataSourcePermission(chElement, it));
        }
    }

    static void addChildElementDataSourcePermission(SOAPElement element, DataSourcePermission it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "DataSourcePermission");
            it.id().ifPresent(v -> addChildElement(chElement, ID, v));
            addChildElement(chElement, NAME_LC, it.name());
            it.createdTimestamp().ifPresent(v -> addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(v)));
            it.lastSchemaUpdate().ifPresent(v -> addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(v)));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
            addChildElement(chElement, ROLE_ID, it.roleID());
            it.process().ifPresent(v -> addChildElement(chElement, PROCESS, String.valueOf(v)));
            it.readDefinition().ifPresent(v -> addChildElement(chElement, READ_DEFINITION, v.getValue()));
            it.read().ifPresent(v -> addChildElement(chElement, "Read", v.getValue()));
            it.write().ifPresent(v -> addChildElement(chElement, WRITE, v.getValue()));
        }
    }

    static void addChildElementImpersonationInfo(SOAPElement element, String tagName, ImpersonationInfo it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, tagName);
            addChildElement(chElement, "ImpersonationMode", it.impersonationMode());
            it.account().ifPresent(v -> addChildElement(chElement, ACCOUNT, v));
            addChildElement(chElement, "ImpersonationMode", it.impersonationMode());
            it.account().ifPresent(v -> addChildElement(chElement, ACCOUNT, v));
            it.password().ifPresent(v -> addChildElement(chElement, "Password", v));
            it.impersonationInfoSecurity().ifPresent(v -> addChildElement(chElement, "ImpersonationInfoSecurity", v));
        }
    }

    static void addChildElementDatabase(SOAPElement element, Database it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Database");
            addChildElement(chElement, NAME, it.name());
            addChildElement(chElement, ID, it.id());
            addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(it.createdTimestamp()));
            addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(it.lastSchemaUpdate()));
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElementAnnotationList(chElement, it.annotations());
            addChildElement(chElement, "LastUpdate", convertInstant(it.lastUpdate()));
            addChildElement(chElement, STATE, it.state());
            addChildElement(chElement, "ReadWriteMode", it.readWriteMode());
            addChildElement(chElement, "DbStorageLocation", it.dbStorageLocation());
            addChildElement(chElement, AGGREGATION_PREFIX, it.aggregationPrefix());
            addChildElement(chElement, PROCESSING_PRIORITY, String.valueOf(it.processingPriority()));
            addChildElement(chElement, ESTIMATED_SIZE, String.valueOf(it.estimatedSize()));
            addChildElement(chElement, LAST_PROCESSED, convertInstant(it.lastProcessed()));
            addChildElement(chElement, LANGUAGE, String.valueOf(it.language()));
            addChildElement(chElement, COLLATION, String.valueOf(it.collation()));
            addChildElement(chElement, VISIBLE, String.valueOf(it.visible()));
            addChildElement(chElement, "MasterDataSourceID", it.masterDataSourceID());
            addChildElementImpersonationInfo(chElement, "DataSourceImpersonationInfo",
                    it.dataSourceImpersonationInfo());
            addChildElementAccountList(chElement, it.accounts());
            addChildElementDataSourceList(chElement, it.dataSources());
            addChildElementDataSourceViewList(chElement, it.dataSourceViews());
            addChildElementDimensionList(chElement, it.dimensions());
            addChildElementCubeList(chElement, it.cubes());
            addChildElementMiningStructureList(chElement, it.miningStructures());
            addChildElementRoleList(chElement, it.roles());
            addChildElementAssemblyList(chElement, it.assemblies());
            addChildElementDatabasePermissionList(chElement, it.databasePermissions());
            addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, it.translations());
            addChildElement(chElement, "StorageEngineUsed", it.storageEngineUsed());
            addChildElement(chElement, "ImagePath", it.imagePath());
            addChildElement(chElement, "ImageUrl", it.imageUrl());
            addChildElement(chElement, "ImageUniqueID", it.imageUniqueID());
            addChildElement(chElement, "ImageVersion", it.imageVersion());
            addChildElement(chElement, "Token", it.token());
            addChildElement(chElement, "CompatibilityLevel", String.valueOf(it.compatibilityLevel()));
            addChildElement(chElement, "DirectQueryMode", it.directQueryMode());
        }
    }

    static void addChildElementDatabasePermissionList(SOAPElement element, List<DatabasePermission> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "DatabasePermissions");
            list.forEach(it -> addChildElementPermission(chElement, "DatabasePermission", it));
        }
    }

    static void addChildElementMiningStructureList(SOAPElement element, List<MiningStructure> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "MiningStructures");
            list.forEach(it -> addChildElementMiningStructure(chElement, it));
        }
    }

    static void addChildElementCubeList(SOAPElement element, List<Cube> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Cubes");
            list.forEach(it -> addChildElementCube(chElement, it));
        }
    }

    static void addChildElementDimensionList(SOAPElement element, List<Dimension> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, DIMENSIONS);
            list.forEach(it -> addChildElementDimension(chElement, DIMENSION, it));
        }
    }

    static void addChildElementDataSourceViewList(SOAPElement element, List<DataSourceView> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "DataSourceViews");
            list.forEach(it -> addChildElementDataSourceView(chElement, it));
        }
    }

    static void addChildElementDataSourceList(SOAPElement element, List<DataSource> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "DataSources");
            list.forEach(it -> addChildElementDataSource(chElement, it));
        }
    }

    static void addChildElementAccountList(SOAPElement element, List<Account> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Accounts");
            list.forEach(it -> addChildElementAccount(chElement, it));
        }
    }

    static void addChildElementAccount(SOAPElement element, Account it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, ACCOUNT);
            addChildElement(chElement, "AccountType", it.accountType());
            addChildElement(chElement, "AggregationFunction", it.aggregationFunction());
            addChildElementAliasList(chElement, it.aliases());
            addChildElementAnnotationList(chElement, it.annotations());
        }
    }

    static void addChildElementAliasList(SOAPElement element, List<String> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Aliases");
            list.forEach(it -> addChildElement(chElement, "Alias", it));
        }
    }

    static void addChildElementCube(SOAPElement element, Cube cube) {
        if (cube != null) {
            SOAPElement chElement = addChildElement(element, "Cube");
            addChildElement(chElement, NAME_LC, cube.name());
            addChildElement(chElement, ID, cube.id());
            addChildElement(chElement, CREATED_TIMESTAMP, convertInstant(cube.createdTimestamp()));
            addChildElement(chElement, LAST_SCHEMA_UPDATE, convertInstant(cube.lastSchemaUpdate()));
            addChildElement(chElement, DESCRIPTION, cube.description());
            addChildElement(chElement, DESCRIPTION, cube.description());
            addChildElementAnnotationList(chElement, cube.annotations());
            addChildElement(chElement, LANGUAGE, String.valueOf(cube.language()));
            addChildElement(chElement, COLLATION, String.valueOf(cube.collation()));
            addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, cube.translations());
            addChildElementCubeDimensionsList(chElement, cube.dimensions());
            addChildElementCubePermissionList(chElement, cube.cubePermissions());
            addChildElementMdxScriptList(chElement, cube.mdxScripts());
            addChildElementPerspectiveList(chElement, cube.perspectives());
            addChildElement(chElement, STATE, cube.state());
            addChildElement(chElement, "DefaultMeasure", cube.defaultMeasure());
            addChildElement(chElement, VISIBLE, String.valueOf(cube.visible()));
            addChildElementMeasureGroupList(chElement, cube.measureGroups());
            addChildElementDataSourceViewBinding(chElement, SOURCE, cube.source());
            addChildElement(chElement, AGGREGATION_PREFIX, cube.aggregationPrefix());
            addChildElement(chElement, PROCESSING_PRIORITY, String.valueOf(cube.processingPriority()));
            addChildElementCubeStorageMode(chElement, cube.storageMode());
            addChildElement(chElement, PROCESSING_MODE, cube.processingMode());
            addChildElement(chElement, "ScriptCacheProcessingMode", cube.scriptCacheProcessingMode());
            addChildElement(chElement, "ScriptErrorHandlingMode", cube.scriptErrorHandlingMode());
            addChildElement(chElement, "DaxOptimizationMode", cube.daxOptimizationMode());
            addChildElementProactiveCaching(chElement, cube.proactiveCaching());
            addChildElementKpiList(chElement, cube.kpis());
            addChildElementErrorConfiguration(chElement, cube.errorConfiguration());
            addChildElementActionList(chElement, cube.actions());
            addChildElement(chElement, STORAGE_LOCATION, cube.storageLocation());
            addChildElement(chElement, ESTIMATED_ROWS, String.valueOf(cube.estimatedRows()));
            addChildElement(chElement, LAST_PROCESSED, String.valueOf(cube.lastProcessed()));
        }
    }

    static void addChildElementActionList(SOAPElement element, List<Action> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Actions");
            list.forEach(it -> addChildElementAction(chElement, it));
        }
    }

    static void addChildElementAction(SOAPElement element, Action it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Action");
            addChildElement(chElement, NAME_LC, it.name());
            it.id().ifPresent(v -> addChildElement(chElement, ID, v));
            it.caption().ifPresent(v -> addChildElement(chElement, CAPTION, v));
            it.captionIsMdx().ifPresent(v -> addChildElement(chElement, "CaptionIsMdx", String.valueOf(v)));
            it.translations().ifPresent(v -> addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, v));
            addChildElement(chElement, "TargetType", it.targetType().getValue());
            it.target().ifPresent(v -> addChildElement(chElement, "Target", v));
            it.condition().ifPresent(v -> addChildElement(chElement, "Condition", v));
            addChildElement(chElement, "Type", it.type().getValue());
            it.invocation().ifPresent(v -> addChildElement(chElement, "Invocation", v));
            it.application().ifPresent(v -> addChildElement(chElement, "Application", v));
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));

            if (it instanceof DrillThroughAction dta) {
                dta.defaultAction().ifPresent(v -> addChildElement(chElement, "Default", String.valueOf(v)));
                dta.columns().ifPresent(v -> addChildElementBindingList(chElement, v));
                dta.maximumRows().ifPresent(v -> addChildElement(chElement, "MaximumRows", String.valueOf(v)));
            }
            if (it instanceof ReportAction ra) {
                addChildElement(chElement, "ReportServer", ra.reportServer());
                ra.path().ifPresent(v -> addChildElement(chElement, "Path", v));
                ra.reportParameters().ifPresent(v -> addChildElementReportParameterList(chElement, v));
                ra.reportFormatParameters().ifPresent(v -> addChildElementReportFormatParameterList(chElement, v));
            }
            if (it instanceof StandardAction sa) {
                addChildElement(chElement, EXPRESSION, sa.expression());
            }
        }
    }

    static void addChildElementReportFormatParameterList(SOAPElement element, List<ReportFormatParameter> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "ReportFormatParameters");
            list.forEach(it -> addChildElementReportFormatParameter(chElement, it));
        }
    }

    static void addChildElementReportFormatParameter(SOAPElement element, ReportFormatParameter it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "ReportFormatParameter");
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, VALUE2, it.value());
        }
    }

    static void addChildElementReportParameterList(SOAPElement element, List<ReportParameter> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "ReportParameters");
            list.forEach(it -> addChildElementReportParameter(chElement, it));
        }
    }

    static void addChildElementReportParameter(SOAPElement element, ReportParameter it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "ReportParameter");
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, VALUE2, it.value());
        }
    }

    static void addChildElementBindingList(SOAPElement element, List<Binding> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, COLUMNS);
            list.forEach(it -> addChildElementBinding(chElement, it));
        }
    }

    static void addChildElementBinding(SOAPElement element, Binding it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, SOURCE);
            if (it instanceof ColumnBinding cb) {
                addChildElement(chElement, TABLE_ID, cb.tableID());
                addChildElement(chElement, COLUMN_ID, cb.columnID());
            }
            if (it instanceof RowBinding rb) {
                addChildElement(chElement, TABLE_ID, rb.tableID());
            }
            if (it instanceof DataSourceViewBinding dsvb) {
                addChildElement(chElement, DATA_SOURCE_VIEW_ID, dsvb.dataSourceViewID());
            }
            if (it instanceof AttributeBinding ab) {
                addChildElement(chElement, ATTRIBUTE_ID, ab.attributeID());
                addChildElement(chElement, "Type", ab.type() == null ? null : ab.type().getValue());
                addChildElement(chElement, ORDINAL, String.valueOf(ab.ordinal()));
            }
            if (it instanceof UserDefinedGroupBinding udgb) {
                addChildElement(chElement, ATTRIBUTE_ID, udgb.attributeID());
                udgb.groups().ifPresent(v -> addChildElementGroupList(chElement, v));
            }
            if (it instanceof MeasureBinding mb) {
                addChildElement(chElement, MEASURE_ID, mb.measureID());
            }
            if (it instanceof CubeAttributeBinding cab) {
                addChildElement(chElement, CUBE_ID, cab.cubeID());
                addChildElement(chElement, CUBE_DIMENSION_ID, cab.cubeDimensionID());
                addChildElement(chElement, ATTRIBUTE_ID, cab.attributeID());
                addChildElement(chElement, "Type", cab.type() == null ? null : cab.type().getValue());
                cab.ordinal().ifPresent(v -> addChildElementCubeAttributeBindingOrdinalList(chElement, v));
            }
            if (it instanceof DimensionBinding db) {
                addChildElement(chElement, DATA_SOURCE_ID, db.dataSourceID());
                addChildElement(chElement, DIMENSION_ID, db.dimensionID());
                db.persistence().ifPresent(v -> addChildElement(chElement, PERSISTENCE, v.getValue()));
                db.refreshPolicy().ifPresent(v -> addChildElement(chElement, REFRESH_POLICY, v.getValue()));
                db.refreshInterval().ifPresent(v -> addChildElement(chElement, REFRESH_INTERVAL, String.valueOf(v)));
            }
            if (it instanceof CubeDimensionBinding cdb) {
                addChildElement(chElement, DATA_SOURCE_ID, cdb.dataSourceID());
                addChildElement(chElement, CUBE_ID, cdb.cubeID());
                addChildElement(chElement, CUBE_DIMENSION_ID, cdb.cubeDimensionID());
                cdb.filter().ifPresent(v -> addChildElement(chElement, FILTER, v));
            }
            if (it instanceof MeasureGroupBinding mgb) {
                addChildElement(chElement, DATA_SOURCE_ID, mgb.dataSourceID());
                addChildElement(chElement, CUBE_ID, mgb.cubeID());
                addChildElement(chElement, MEASURE_GROUP_ID, mgb.measureGroupID());
                mgb.persistence().ifPresent(v -> addChildElement(chElement, PERSISTENCE, v.getValue()));
                mgb.refreshPolicy().ifPresent(v -> addChildElement(chElement, REFRESH_POLICY, v.getValue()));
                mgb.refreshInterval().ifPresent(v -> addChildElement(chElement, REFRESH_INTERVAL, convertDuration(v)));
                mgb.filter().ifPresent(v -> addChildElement(chElement, FILTER, v));
            }
            if (it instanceof MeasureGroupDimensionBinding mgdb) {
                addChildElement(chElement, CUBE_DIMENSION_ID, mgdb.cubeDimensionID());
            }
            if (it instanceof TimeBinding tb) {
                addChildElement(chElement, "CalendarStartDate", convertInstant(tb.calendarStartDate()));
                addChildElement(chElement, "CalendarEndDate", convertInstant(tb.calendarEndDate()));
                tb.firstDayOfWeek().ifPresent(v -> addChildElement(chElement, "FirstDayOfWeek", String.valueOf(v)));
                tb.calendarLanguage().ifPresent(v -> addChildElement(chElement, "CalendarLanguage", String.valueOf(v)));
                tb.fiscalFirstMonth().ifPresent(v -> addChildElement(chElement, "FiscalFirstMonth", String.valueOf(v)));
                tb.fiscalFirstDayOfMonth()
                        .ifPresent(v -> addChildElement(chElement, "FiscalFirstDayOfMonth", String.valueOf(v)));
                tb.fiscalYearName().ifPresent(v -> addChildElement(chElement, "FiscalYearName", v.getValue()));
                tb.reportingFirstMonth()
                        .ifPresent(v -> addChildElement(chElement, "ReportingFirstMonth", String.valueOf(v)));
                tb.reportingFirstWeekOfMonth()
                        .ifPresent(v -> addChildElement(chElement, "ReportingFirstWeekOfMonth", v));
                tb.reportingWeekToMonthPattern()
                        .ifPresent(v -> addChildElement(chElement, "ReportingWeekToMonthPattern", v.getValue()));
                tb.manufacturingFirstMonth()
                        .ifPresent(v -> addChildElement(chElement, "ManufacturingFirstMonth", String.valueOf(v)));
                tb.manufacturingFirstWeekOfMonth()
                        .ifPresent(v -> addChildElement(chElement, "ManufacturingFirstWeekOfMonth", String.valueOf(v)));
                tb.manufacturingExtraMonthQuarter().ifPresent(
                        v -> addChildElement(chElement, "ManufacturingExtraMonthQuarter", String.valueOf(v)));
            }
            if (it instanceof TimeAttributeBinding) {
            }
            if (it instanceof InheritedBinding) {
            }
            if (it instanceof CalculatedMeasureBinding cmb) {
                addChildElement(chElement, "MeasureName", cmb.measureName());
            }
            if (it instanceof RowNumberBinding) {
            }
            if (it instanceof ExpressionBinding eb) {
                addChildElement(chElement, EXPRESSION, eb.expression());
            }
        }
    }

    private static String convertInstant(Instant instant) {
        if (instant != null) {
            return instant.toString();
        }
        return null;
    }

    static void addChildElementCubeAttributeBindingOrdinalList(SOAPElement element, List<BigInteger> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, ORDINAL);
            list.forEach(it -> addChildElementCubeAttributeBindingOrdinal(chElement, it));
        }
    }

    static void addChildElementCubeAttributeBindingOrdinal(SOAPElement element, BigInteger it) {
        if (it != null) {
            addChildElement(element, ORDINAL, String.valueOf(it));
        }
    }

    static void addChildElementGroupList(SOAPElement element, List<Group> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Groups");
            list.forEach(it -> addChildElementGroup(chElement, it));
        }
    }

    static void addChildElementGroup(SOAPElement element, Group it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Group");
            addChildElement(chElement, NAME_LC, it.name());
            SOAPElement membersElement = addChildElement(chElement, "Members");
            it.members().ifPresent(v -> v.forEach(m -> addChildElement(membersElement, "Member", m)));
        }
    }

    static void addChildElementErrorConfiguration(SOAPElement element, ErrorConfiguration it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "ErrorConfiguration");
            it.keyErrorLimit().ifPresent(v -> addChildElement(chElement, "KeyErrorLimit", String.valueOf(v)));
            it.keyErrorLogFile().ifPresent(v -> addChildElement(chElement, "KeyErrorLogFile", v));
            it.keyErrorAction().ifPresent(v -> addChildElement(chElement, "KeyErrorAction", v));
            it.keyErrorLimitAction().ifPresent(v -> addChildElement(chElement, "KeyErrorLimitAction", v));
            it.keyNotFound().ifPresent(v -> addChildElement(chElement, "KeyNotFound", v));
            it.keyDuplicate().ifPresent(v -> addChildElement(chElement, "KeyDuplicate", v));
            it.nullKeyConvertedToUnknown().ifPresent(v -> addChildElement(chElement, "NullKeyConvertedToUnknown", v));
            it.nullKeyNotAllowed().ifPresent(v -> addChildElement(chElement, "NullKeyNotAllowed", v));
            it.calculationError().ifPresent(v -> addChildElement(chElement, "CalculationError", v));
        }
    }

    static void addChildElementKpiList(SOAPElement element, List<Kpi> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Kpis");
            list.forEach(it -> addChildElementKpi(chElement, it));
        }
    }

    static void addChildElementKpi(SOAPElement element, Kpi it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Kpi");
            addChildElement(chElement, NAME_LC, it.name());
            addChildElement(chElement, ID, it.id());
            addChildElement(chElement, DESCRIPTION, it.description());
            addChildElementTranslationList(chElement, TRANSLATIONS, TRANSLATION, it.translations());
            addChildElement(chElement, DISPLAY_FOLDER, it.displayFolder());
            addChildElement(chElement, "AssociatedMeasureGroupID", it.associatedMeasureGroupID());
            addChildElement(chElement, VALUE2, it.value());
            addChildElement(chElement, "Goal", it.goal());
            addChildElement(chElement, "Status", it.status());
            addChildElement(chElement, "Trend", it.trend());
            addChildElement(chElement, "Weight", it.weight());
            addChildElement(chElement, "TrendGraphic", it.trendGraphic());
            addChildElement(chElement, "StatusGraphic", it.statusGraphic());
            addChildElement(chElement, "CurrentTimeMember", it.currentTimeMember());
            addChildElement(chElement, "ParentKpiID", it.parentKpiID());
            addChildElementAnnotationList(chElement, it.annotations());
        }
    }

    static void addChildElementProactiveCaching(SOAPElement element, ProactiveCaching proactiveCaching) {
        if (proactiveCaching != null) {
            SOAPElement chElement = addChildElement(element, "ProactiveCaching");
            proactiveCaching.onlineMode().ifPresent(v -> addChildElement(chElement, "OnlineMode", v));
            proactiveCaching.aggregationStorage().ifPresent(v -> addChildElement(chElement, "AggregationStorage", v));
            proactiveCaching.source().ifPresent(v -> addChildElementProactiveCachingBinding(chElement, v));
            proactiveCaching.silenceInterval()
                    .ifPresent(v -> addChildElement(chElement, "SilenceInterval", convertDuration(v)));
            proactiveCaching.latency().ifPresent(v -> addChildElement(chElement, "Latency", convertDuration(v)));
            proactiveCaching.silenceOverrideInterval()
                    .ifPresent(v -> addChildElement(chElement, "SilenceOverrideInterval", convertDuration(v)));
            proactiveCaching.forceRebuildInterval()
                    .ifPresent(v -> addChildElement(chElement, "ForceRebuildInterval", convertDuration(v)));
            proactiveCaching.enabled().ifPresent(v -> addChildElement(chElement, "Enabled", String.valueOf(v)));
        }
    }

    static void addChildElementProactiveCachingBinding(SOAPElement element, ProactiveCachingBinding source) {
        if (source != null) {
            SOAPElement chElement = addChildElement(element, SOURCE);
            if (source instanceof ProactiveCachingIncrementalProcessingBinding pcipb) {
                pcipb.refreshInterval()
                        .ifPresent(v -> addChildElement(chElement, REFRESH_INTERVAL, convertDuration(v)));
                addChildElementIncrementalProcessingNotificationList(chElement,
                        pcipb.incrementalProcessingNotifications());
            }
            if (source instanceof ProactiveCachingQueryBinding pcqb) {
                pcqb.refreshInterval().ifPresent(v -> addChildElement(chElement, REFRESH_INTERVAL, convertDuration(v)));
                addChildElementQueryNotificationList(chElement, pcqb.queryNotifications());
            }

        }
    }

    static void addChildElementQueryNotificationList(SOAPElement element, List<QueryNotification> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "QueryNotifications");
            list.forEach(it -> addChildElementQueryNotification(chElement, it));
        }
    }

    static void addChildElementQueryNotification(SOAPElement element, QueryNotification it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "QueryNotification");
            it.query().ifPresent(v -> addChildElement(chElement, "Query", v));
        }
    }

    static void addChildElementIncrementalProcessingNotificationList(SOAPElement element,
            List<IncrementalProcessingNotification> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "IncrementalProcessingNotifications");
            list.forEach(it -> addChildElementIncrementalProcessingNotification(chElement, it));
        }
    }

    static void addChildElementIncrementalProcessingNotification(SOAPElement element,
            IncrementalProcessingNotification it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "IncrementalProcessingNotification");
            addChildElement(chElement, TABLE_ID, it.tableID());
            addChildElement(chElement, "ProcessingQuery", it.processingQuery());
        }
    }

    private static String convertDuration(java.time.Duration duration) {
        if (duration != null) {
            return duration.toString();
        }
        return null;
    }

    static void addChildElementCubeStorageMode(SOAPElement element, Cube.StorageMode storageMode) {
        if (storageMode != null) {
            SOAPElement chElement = addChildElement(element, STORAGE_MODE);
            chElement.setAttribute(VALUENS, storageMode.valuens());
            addChildElement(chElement, VALUE, storageMode.value() == null ? null : storageMode.value().value());
        }

    }

    static void addChildElementDataSourceViewBinding(SOAPElement element, String tagName,
            DataSourceViewBinding source) {
        if (source != null) {
            SOAPElement chElement = addChildElement(element, tagName);
            addChildElement(chElement, DATA_SOURCE_VIEW_ID, source.dataSourceViewID());
        }
    }

    static void addChildElementPerspectiveList(SOAPElement element, List<Perspective> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Perspectives");
            list.forEach(it -> addChildElementPerspective(chElement, it));
        }
    }

    static void addChildElementMdxScriptList(SOAPElement element, List<MdxScript> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "MdxScripts");
            list.forEach(it -> addChildElementMdxScript(chElement, it));
        }
    }

    static void addChildElementMeasureGroupList(SOAPElement element, List<MeasureGroup> measureGroups) {
        if (measureGroups != null) {
            SOAPElement chElement = addChildElement(element, "MeasureGroups");
            measureGroups.forEach(it -> addChildElementMeasureGroup(chElement, it));
        }
    }

    static void addChildElementCubePermissionList(SOAPElement element, List<CubePermission> cubePermissions) {
        if (cubePermissions != null) {
            SOAPElement chElement = addChildElement(element, "CubePermissions");
            cubePermissions.forEach(it -> addChildElementCubePermission(chElement, it));
        }

    }

    static void addChildElementCubePermission(SOAPElement element, CubePermission cubePermission) {
        SOAPElement dimensionElement = addChildElement(element, "CubePermission");
        addChildElement(dimensionElement, NAME_LC, cubePermission.name());
        cubePermission.id().ifPresent(v -> addChildElement(dimensionElement, ID, v));
        cubePermission.createdTimestamp()
                .ifPresent(v -> addChildElement(dimensionElement, CREATED_TIMESTAMP, convertInstant(v)));
        cubePermission.lastSchemaUpdate()
                .ifPresent(v -> addChildElement(dimensionElement, LAST_SCHEMA_UPDATE, convertInstant(v)));
        cubePermission.description().ifPresent(v -> addChildElement(dimensionElement, DESCRIPTION, v));
        cubePermission.annotations().ifPresent(v -> addChildElementAnnotationList(dimensionElement, v));
        cubePermission.readSourceData().ifPresent(v -> addChildElement(dimensionElement, "ReadSourceData", v));
        cubePermission.dimensionPermissions()
                .ifPresent(v -> addChildElementCubeDimensionPermissionList(dimensionElement, v));
        cubePermission.cellPermissions().ifPresent(v -> addChildElementCellPermissionList(dimensionElement, v));
    }

    static void addChildElementCellPermissionList(SOAPElement element, List<CellPermission> v) {
        if (v != null) {
            SOAPElement chElement = addChildElement(element, "CellPermissions");
            v.forEach(it -> addChildElementCellPermission(chElement, it));
        }
    }

    static void addChildElementCellPermission(SOAPElement element, CellPermission it) {
        SOAPElement chElement = addChildElement(element, "CellPermission");
        it.access().ifPresent(v -> addChildElement(chElement, "Access", v.getValue()));
        it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
        it.expression().ifPresent(v -> addChildElement(chElement, EXPRESSION, v));
        it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
    }

    static void addChildElementCubeDimensionPermissionList(SOAPElement element, List<CubeDimensionPermission> v) {
        if (v != null) {
            SOAPElement chElement = addChildElement(element, "DimensionPermissions");
            v.forEach(it -> addChildElementCubeDimensionPermission(chElement, it));
        }
    }

    static void addChildElementCubeDimensionPermission(SOAPElement element, CubeDimensionPermission it) {
        SOAPElement chElement = addChildElement(element, "DimensionPermission");
        addChildElement(chElement, CUBE_DIMENSION_ID, it.cubeDimensionID());
        it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
        it.read().ifPresent(v -> addChildElement(chElement, "Read", v.getValue()));
        it.write().ifPresent(v -> addChildElement(chElement, WRITE, v.getValue()));
        it.attributePermissions().ifPresent(v -> addChildElementAttributePermissionList(chElement, v));
        it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
    }

    static void addChildElementAttributePermissionList(SOAPElement element, List<AttributePermission> v) {
        if (v != null) {
            SOAPElement chElement = addChildElement(element, "AttributePermissions");
            v.forEach(it -> addChildElementAttributePermission(chElement, it));
        }
    }

    static void addChildElementAttributePermission(SOAPElement element, AttributePermission it) {
        SOAPElement chElement = addChildElement(element, "AttributePermission");
        addChildElement(chElement, ATTRIBUTE_ID, it.attributeID());
        it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
        it.defaultMember().ifPresent(v -> addChildElement(chElement, DEFAULT_MEMBER, v));
        it.visualTotals().ifPresent(v -> addChildElement(chElement, "VisualTotals", v));
        it.allowedSet().ifPresent(v -> addChildElement(chElement, "AllowedSet", v));
        it.deniedSet().ifPresent(v -> addChildElement(chElement, "DeniedSet", v));
        it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
    }

    static void addChildElementCubeDimensionsList(SOAPElement cubeElement, List<CubeDimension> dimensions) {
        if (dimensions != null) {
            SOAPElement dimensionsElement = addChildElement(cubeElement, DIMENSIONS);
            dimensions.forEach(it -> addChildElementCubeDimension(dimensionsElement, it));
        }
    }

    static void addChildElementCubeDimension(SOAPElement dimensionsElement, CubeDimension cubeDimension) {
        SOAPElement dimensionElement = addChildElement(dimensionsElement, DIMENSION);
        addChildElement(dimensionElement, ID, cubeDimension.id());
        addChildElement(dimensionElement, NAME_LC, cubeDimension.name());
        addChildElement(dimensionElement, DESCRIPTION, cubeDimension.description());
        addChildElementTranslationList(dimensionElement, TRANSLATIONS, TRANSLATION, cubeDimension.translations());
        addChildElement(dimensionElement, DIMENSION_ID, cubeDimension.dimensionID());
        addChildElement(dimensionElement, VISIBLE, String.valueOf(cubeDimension.visible()));
        addChildElement(dimensionElement, "AllMemberAggregationUsage", cubeDimension.allMemberAggregationUsage());
        addChildElement(dimensionElement, "HierarchyUniqueNameStyle", cubeDimension.hierarchyUniqueNameStyle());
        addChildElement(dimensionElement, "MemberUniqueNameStyle", cubeDimension.memberUniqueNameStyle());
        addChildElementCubeAttributeList(dimensionElement, cubeDimension.attributes());
        addChildElementCubeHierarchyList(dimensionElement, cubeDimension.hierarchies());
        addChildElementAnnotationList(dimensionElement, cubeDimension.annotations());
    }

    static void addChildElementCubeHierarchyList(SOAPElement element, List<CubeHierarchy> hierarchies) {
        if (hierarchies != null) {
            SOAPElement hierarchiesElement = addChildElement(element, HIERARCHIES);
            hierarchies.forEach(v -> addChildElementCubeHierarchy(hierarchiesElement, v));
        }
    }

    static void addChildElementCubeHierarchy(SOAPElement element, CubeHierarchy v) {
        SOAPElement hierarchyElement = addChildElement(element, HIERARCHY);
        addChildElement(hierarchyElement, "HierarchyID", v.hierarchyID());
        addChildElement(hierarchyElement, "OptimizedState", v.optimizedState());
        addChildElement(hierarchyElement, VISIBLE, String.valueOf(v.visible()));
        addChildElement(hierarchyElement, "Enabled", String.valueOf(v.enabled()));
        addChildElementAnnotationList(hierarchyElement, v.annotations());
    }

    static void addChildElementCubeAttributeList(SOAPElement element, List<CubeAttribute> attributes) {
        if (attributes != null) {
            SOAPElement attributesElement = addChildElement(element, ATTRIBUTES);
            attributes.forEach(v -> addChildElementCubeAttribute(attributesElement, v));
        }
    }

    static void addChildElementCubeAttribute(SOAPElement element, CubeAttribute v) {
        SOAPElement attributeElement = addChildElement(element, ATTRIBUTE);
        addChildElement(attributeElement, ATTRIBUTE_ID, v.attributeID());
        addChildElement(attributeElement, "AggregationUsage", v.aggregationUsage());
        addChildElement(attributeElement, "AttributeHierarchyOptimizedState", v.attributeHierarchyOptimizedState());
        addChildElement(attributeElement, "AttributeHierarchyEnabled", String.valueOf(v.attributeHierarchyEnabled()));
        addChildElement(attributeElement, ATTRIBUTE_HIERARCHY_VISIBLE, String.valueOf(v.attributeHierarchyVisible()));
        addChildElementAnnotationList(attributeElement, v.annotations());
    }

    static void addChildElementTranslationList(SOAPElement cubeElement, String wrapperName, String tagName,
            List<Translation> translations) {
        if (translations != null) {
            SOAPElement translationsElement = addChildElement(cubeElement, wrapperName);
            translations.forEach(it -> addChildElementTranslation(translationsElement, tagName, it));
        }
    }

    static void addChildElementTranslation(SOAPElement translationsElement, String tagName, Translation it) {
        if (it != null) {
            SOAPElement translationElement = addChildElement(translationsElement, tagName);
            addChildElement(translationElement, LANGUAGE, String.valueOf(it.language()));
            addChildElement(translationElement, CAPTION, String.valueOf(it.caption()));
            addChildElement(translationElement, DESCRIPTION, String.valueOf(it.description()));
            addChildElement(translationElement, DISPLAY_FOLDER, String.valueOf(it.displayFolder()));
            addChildElementAnnotationList(translationElement, it.annotations());
        }
    }

    static void addChildElementAssembly(SOAPElement objectDefinitionElement, Assembly assembly) {
        if (assembly != null) {
            SOAPElement assemblyElement = addChildElement(objectDefinitionElement, "Assembly");
            addChildElement(assemblyElement, NAME_LC, assembly.name());
            addChildElement(assemblyElement, ID, assembly.id());
            addChildElement(assemblyElement, CREATED_TIMESTAMP, assembly.createdTimestamp().toString());
            addChildElement(assemblyElement, LAST_SCHEMA_UPDATE, assembly.lastSchemaUpdate().toString());
            addChildElement(assemblyElement, DESCRIPTION, assembly.description());
            addChildElementAnnotationList(assemblyElement, assembly.annotations());
            addChildElementImpersonationInfo(assemblyElement, "ImpersonationInfo", assembly.impersonationInfo());
        }
    }

    static void addChildElementAnnotationList(SOAPElement element, List<Annotation> annotations) {
        if (annotations != null) {
            SOAPElement annotationsElement = addChildElement(element, "Annotations");
            annotations.forEach(it -> addChildElementAnnotation(annotationsElement, it));
        }
    }

    static void addChildElementAnnotation(SOAPElement annotationsElement, Annotation annotation) {
        if (annotation != null) {
            SOAPElement annotationElement = addChildElement(annotationsElement, "Annotation");
            addChildElement(annotationElement, NAME_LC, annotation.name());
            annotation.visibility().ifPresent(v -> addChildElement(annotationElement, "Visibility", v));
            annotation.value().ifPresent(v -> addChildElement(annotationElement, VALUE2, v.toString()));
        }
    }

    static void addChildElementAggregationDesign(SOAPElement objectDefinitionElement,
            AggregationDesign aggregationDesign) {
        if (aggregationDesign != null) {
            SOAPElement aggregationDesignEl = addChildElement(objectDefinitionElement, "AggregationDesign");
            addChildElement(aggregationDesignEl, NAME, aggregationDesign.name());
            aggregationDesign.id().ifPresent(v -> addChildElement(aggregationDesignEl, ID, v));
            aggregationDesign.createdTimestamp()
                    .ifPresent(v -> addChildElement(aggregationDesignEl, CREATED_TIMESTAMP, convertInstant(v)));
            aggregationDesign.lastSchemaUpdate()
                    .ifPresent(v -> addChildElement(aggregationDesignEl, LAST_SCHEMA_UPDATE, convertInstant(v)));
            aggregationDesign.description().ifPresent(v -> addChildElement(aggregationDesignEl, DESCRIPTION, v));
            aggregationDesign.description().ifPresent(v -> addChildElement(aggregationDesignEl, DESCRIPTION, v));
            aggregationDesign.annotations().ifPresent(v -> addChildElementAnnotationList(aggregationDesignEl, v));
            aggregationDesign.estimatedRows()
                    .ifPresent(v -> addChildElement(aggregationDesignEl, ESTIMATED_ROWS, String.valueOf(v)));
            aggregationDesign.dimensions()
                    .ifPresent(v -> addChildElementAggregationDesignDimensionList(aggregationDesignEl, v));
            aggregationDesign.aggregations().ifPresent(v -> addChildElementAggregationList(aggregationDesignEl, v));
            aggregationDesign.estimatedPerformanceGain().ifPresent(
                    v -> addChildElement(aggregationDesignEl, "EstimatedPerformanceGain", String.valueOf(v)));
        }
    }

    static void addChildElementAggregationDesignDimensionList(SOAPElement element,
            List<AggregationDesignDimension> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, DIMENSIONS);
            list.forEach(it -> addChildElementAggregationDesignDimension(chElement, it));
        }

    }

    static void addChildElementAggregationDesignDimension(SOAPElement element, AggregationDesignDimension it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, DIMENSION);
            addChildElement(chElement, CUBE_DIMENSION_ID, it.cubeDimensionID());
            it.attributes().ifPresent(v -> addChildElementAggregationDesignAttributeList(chElement, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
        }
    }

    static void addChildElementAggregationDesignAttributeList(SOAPElement element,
            List<AggregationDesignAttribute> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, ATTRIBUTES);
            list.forEach(it -> addChildElementAggregationDesignAttribute(chElement, it));
        }

    }

    static void addChildElementAggregationDesignAttribute(SOAPElement element, AggregationDesignAttribute it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, ATTRIBUTE);
            addChildElement(chElement, ATTRIBUTE_ID, it.attributeID());
            it.estimatedCount().ifPresent(v -> addChildElement(chElement, "EstimatedCount", String.valueOf(v)));
        }
    }

    static void addChildElementAggregationList(SOAPElement element, List<Aggregation> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, "Aggregations");
            list.forEach(it -> addChildElementAggregation(chElement, it));
        }
    }

    static void addChildElementAggregation(SOAPElement element, Aggregation it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, "Aggregation");
            it.id().ifPresent(v -> addChildElement(chElement, ID, v));
            addChildElement(chElement, NAME_LC, it.name());
            it.description().ifPresent(v -> addChildElement(chElement, DESCRIPTION, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
            it.dimensions().ifPresent(v -> addChildElementAggregationDimensionList(chElement, v));
        }

    }

    static void addChildElementAggregationDimensionList(SOAPElement element, List<AggregationDimension> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, DIMENSIONS);
            list.forEach(it -> addChildElementAggregationDimension(chElement, it));
        }
    }

    static void addChildElementAggregationDimension(SOAPElement element, AggregationDimension it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, DIMENSION);
            addChildElement(chElement, CUBE_DIMENSION_ID, it.cubeDimensionID());
            it.attributes().ifPresent(v -> addChildElementAggregationAttributeList(chElement, v));
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
        }
    }

    static void addChildElementAggregationAttributeList(SOAPElement element, List<AggregationAttribute> list) {
        if (list != null) {
            SOAPElement chElement = addChildElement(element, ATTRIBUTES);
            list.forEach(it -> addChildElementAggregationAttribute(chElement, it));
        }
    }

    static void addChildElementAggregationAttribute(SOAPElement element, AggregationAttribute it) {
        if (it != null) {
            SOAPElement chElement = addChildElement(element, ATTRIBUTE);
            addChildElement(chElement, ATTRIBUTE_ID, it.attributeID());
            it.annotations().ifPresent(v -> addChildElementAnnotationList(chElement, v));
        }
    }

    static void addChildElementObject(SOAPElement element, ObjectReference reference) {
        if (reference != null) {
            SOAPElement chElement = addChildElement(element, "Object");
            addChildElement(chElement, SERVER_ID, reference.serverID());
            addChildElement(chElement, "DatabaseID", reference.databaseID());
            addChildElement(chElement, ROLE_ID, reference.roleID());
            addChildElement(chElement, "TraceID", reference.traceID());
            addChildElement(chElement, "AssemblyID", reference.assemblyID());
            addChildElement(chElement, DIMENSION_ID, reference.dimensionID());
            addChildElement(chElement, "DimensionPermissionID", reference.dimensionPermissionID());
            addChildElement(chElement, DATA_SOURCE_ID, reference.dataSourceID());
            addChildElement(chElement, "DataSourcePermissionID", reference.dataSourcePermissionID());
            addChildElement(chElement, "DatabasePermissionID", reference.databasePermissionID());
            addChildElement(chElement, DATA_SOURCE_VIEW_ID, reference.dataSourceViewID());
            addChildElement(chElement, CUBE_ID, reference.cubeID());
            addChildElement(chElement, "MiningStructureID", reference.miningStructureID());
            addChildElement(chElement, MEASURE_GROUP_ID, reference.measureGroupID());
            addChildElement(chElement, "PerspectiveID", reference.perspectiveID());
            addChildElement(chElement, "CubePermissionID", reference.cubePermissionID());
            addChildElement(chElement, "MdxScriptID", reference.mdxScriptID());
            addChildElement(chElement, "PartitionID", reference.partitionID());
            addChildElement(chElement, AGGREGATION_DESIGN_ID, reference.aggregationDesignID());
            addChildElement(chElement, MINING_MODEL_ID, reference.miningModelID());
            addChildElement(chElement, "MiningModelPermissionID", reference.miningModelPermissionID());
            addChildElement(chElement, "MiningStructurePermissionID", reference.miningStructurePermissionID());
        }
    }

    static void addChildElement(SOAPElement element, String childElementName, String value) {
        try {
            if (value != null) {
                element.addChildElement(childElementName).setTextContent(value);
            }
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", childElementName);
            throw new SoapClientException("addChildElement error", e);
        }
    }

    private static SOAPElement addChildElement(SOAPElement element, String childElementName) {
        try {
            return element.addChildElement(childElementName);
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", childElementName);
            throw new SoapClientException("addChildElement error", e);
        }
    }

    static void addChildElementPropertyList(SOAPElement propertyList, Properties properties) {
        properties.localeIdentifier().ifPresent(v -> addChildElement(propertyList, LOCALE_IDENTIFIER, v.toString()));
        properties.dataSourceInfo().ifPresent(v -> addChildElement(propertyList, DATA_SOURCE_INFO, v));
        properties.content().ifPresent(v -> addChildElement(propertyList, CONTENT, v.getValue()));
        properties.format().ifPresent(v -> addChildElement(propertyList, FORMAT, v.getValue()));
        properties.catalog().ifPresent(v -> addChildElement(propertyList, CATALOG, v));
        properties.axisFormat().ifPresent(v -> addChildElement(propertyList, AXIS_FORMAT, v.getValue()));
    }

}
