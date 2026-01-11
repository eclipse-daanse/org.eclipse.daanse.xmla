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

import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.COLUMNS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.EQUAL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.EVENT_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.FILTER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.GREATER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.GREATER_OR_EQUAL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LESS_OR_EQUAL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NOT_EQUAL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NOT_LIKE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.VALUE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getAttribute;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getList;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBigInteger;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBoolean;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInstant;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toLong;

import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.xmla.api.engine300_300.XEvent;
import org.eclipse.daanse.xmla.api.xmla.AndOrType;
import org.eclipse.daanse.xmla.api.xmla.AndOrTypeEnum;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.BoolBinop;
import org.eclipse.daanse.xmla.api.xmla.Event;
import org.eclipse.daanse.xmla.api.xmla.EventColumnID;
import org.eclipse.daanse.xmla.api.xmla.EventSession;
import org.eclipse.daanse.xmla.api.xmla.EventType;
import org.eclipse.daanse.xmla.api.xmla.NotType;
import org.eclipse.daanse.xmla.api.xmla.PartitionModes;
import org.eclipse.daanse.xmla.api.xmla.RetentionModes;
import org.eclipse.daanse.xmla.api.xmla.Trace;
import org.eclipse.daanse.xmla.api.xmla.TraceFilter;
import org.eclipse.daanse.xmla.model.record.engine300_300.XEventR;
import org.eclipse.daanse.xmla.model.record.xmla.AndOrTypeR;
import org.eclipse.daanse.xmla.model.record.xmla.BoolBinopR;
import org.eclipse.daanse.xmla.model.record.xmla.EventColumnIDR;
import org.eclipse.daanse.xmla.model.record.xmla.EventR;
import org.eclipse.daanse.xmla.model.record.xmla.EventSessionR;
import org.eclipse.daanse.xmla.model.record.xmla.EventTypeR;
import org.eclipse.daanse.xmla.model.record.xmla.NotTypeR;
import org.eclipse.daanse.xmla.model.record.xmla.TraceFilterR;
import org.eclipse.daanse.xmla.model.record.xmla.TraceR;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 * Converter for Trace and Event-related XMLA types. Handles Trace, TraceFilter,
 * Event, EventType, XEvent, and related filter types.
 */
public class TraceEventConverter {

    private TraceEventConverter() {
        // utility class
    }

    public static List<Trace> getTraceList(NodeList nl) {
        return getList(nl, "Trace", TraceEventConverter::getTrace);
    }

    public static Trace getTrace(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String logFileName = null;
        Boolean logFileAppend = null;
        Long logFileSize = null;
        Boolean audit = null;
        Boolean logFileRollover = null;
        Boolean autoRestart = null;
        Instant stopTime = null;
        TraceFilter filter = null;
        EventType eventType = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("LogFileName".equals(node.getNodeName())) {
                    logFileName = node.getTextContent();
                }
                if ("LogFileAppend".equals(node.getNodeName())) {
                    logFileAppend = toBoolean(node.getTextContent());
                }
                if ("LogFileSize".equals(node.getNodeName())) {
                    logFileSize = toLong(node.getTextContent());
                }
                if ("Audit".equals(node.getNodeName())) {
                    audit = toBoolean(node.getTextContent());
                }
                if ("LogFileRollover".equals(node.getNodeName())) {
                    logFileRollover = toBoolean(node.getTextContent());
                }
                if ("AutoRestart".equals(node.getNodeName())) {
                    autoRestart = toBoolean(node.getTextContent());
                }
                if ("StopTime".equals(node.getNodeName())) {
                    stopTime = toInstant(node.getTextContent());
                }
                if (FILTER.equals(node.getNodeName())) {
                    filter = getTraceFilter(node.getChildNodes());
                }
                if ("EventType".equals(node.getNodeName())) {
                    eventType = getEventType(node.getChildNodes());
                }
            }
        }
        return new TraceR(name, id, createdTimestamp, lastSchemaUpdate, description, annotations, logFileName,
                logFileAppend, logFileSize, audit, logFileRollover, autoRestart, stopTime, filter, eventType);
    }

    public static EventType getEventType(NodeList nl) {
        List<Event> events = null;
        XEvent xEvent = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("Events".equals(node.getNodeName())) {
                    events = getEventList(node.getChildNodes());
                }
                if ("XEvent".equals(node.getNodeName())) {
                    xEvent = getXEvent(node.getChildNodes());
                }
            }
        }
        return new EventTypeR(events, xEvent);
    }

    public static XEvent getXEvent(NodeList nl) {
        EventSession eventSession = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && ("event_session".equals(node.getNodeName()))) {
                eventSession = getEventSession(node.getChildNodes());
            }
        }
        return new XEventR(eventSession);
    }

    public static EventSession getEventSession(NodeList nl) {
        String templateCategory = null;
        String templateName = null;
        String templateDescription = null;
        List<Object> event = new ArrayList<>();
        List<Object> target = new ArrayList<>();
        String name = null;
        BigInteger maxMemory = null;
        RetentionModes eventRetentionMode = null;
        Long dispatchLatency = null;
        Long maxEventSize = null;
        PartitionModes memoryPartitionMode = null;
        Boolean trackCausality = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("templateCategory".equals(node.getNodeName())) {
                    templateCategory = node.getTextContent();
                }
                if ("templateName".equals(node.getNodeName())) {
                    templateName = node.getTextContent();
                }
                if ("templateDescription".equals(node.getNodeName())) {
                    templateDescription = node.getTextContent();
                }
                if ("event".equals(node.getNodeName())) {
                    event.add(node.getTextContent());
                }
                if ("target".equals(node.getNodeName())) {
                    target.add(node.getTextContent());
                }
                NamedNodeMap nm = node.getAttributes();
                name = getAttribute(nm, "name");
                maxMemory = toBigInteger(getAttribute(nm, "maxMemory"));
                eventRetentionMode = RetentionModes.fromValue(getAttribute(nm, "eventRetentionMode"));
                dispatchLatency = toLong(getAttribute(nm, "dispatchLatency"));
                maxEventSize = toLong(getAttribute(nm, "maxEventSize"));
                memoryPartitionMode = PartitionModes.fromValue(getAttribute(nm, "memoryPartitionMode"));
                trackCausality = toBoolean(getAttribute(nm, "trackCausality"));
            }
        }
        return new EventSessionR(templateCategory, templateName, templateDescription, event, target, name, maxMemory,
                eventRetentionMode, dispatchLatency, maxEventSize, memoryPartitionMode, trackCausality);
    }

    public static List<Event> getEventList(NodeList nl) {
        return getList(nl, "Event", TraceEventConverter::getEvent);
    }

    public static Event getEvent(NodeList nl) {
        String eventID = null;
        EventColumnID columns = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (EVENT_ID.equals(node.getNodeName())) {
                    eventID = node.getTextContent();
                }
                if (COLUMNS.equals(node.getNodeName())) {
                    columns = getEventColumnID(node.getChildNodes());
                }
            }
        }
        return new EventR(eventID, columns);
    }

    public static EventColumnID getEventColumnID(NodeList nl) {
        List<String> columnID = new ArrayList<>();

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && (EVENT_ID.equals(node.getNodeName()))) {
                columnID.add(node.getTextContent());
            }
        }
        return new EventColumnIDR(columnID);
    }

    public static TraceFilter getTraceFilter(NodeList nl) {
        NotType not = null;
        AndOrType or = null;
        AndOrType and = null;
        BoolBinop isEqual = null;
        BoolBinop notEqual = null;
        BoolBinop less = null;
        BoolBinop lessOrEqual = null;
        BoolBinop greater = null;
        BoolBinop greaterOrEqual = null;
        BoolBinop like = null;
        BoolBinop notLike = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("Not".equals(node.getNodeName())) {
                    not = getNotType(node.getChildNodes());
                }
                if ("Or".equals(node.getNodeName())) {
                    or = getAndOrType(node.getChildNodes());
                }
                if ("And".equals(node.getNodeName())) {
                    and = getAndOrType(node.getChildNodes());
                }
                if (EQUAL.equals(node.getNodeName())) {
                    isEqual = getBoolBinop(node.getChildNodes());
                }
                if (NOT_EQUAL.equals(node.getNodeName())) {
                    notEqual = getBoolBinop(node.getChildNodes());
                }
                if ("Less".equals(node.getNodeName())) {
                    less = getBoolBinop(node.getChildNodes());
                }
                if (LESS_OR_EQUAL.equals(node.getNodeName())) {
                    lessOrEqual = getBoolBinop(node.getChildNodes());
                }
                if (GREATER.equals(node.getNodeName())) {
                    greater = getBoolBinop(node.getChildNodes());
                }
                if (GREATER_OR_EQUAL.equals(node.getNodeName())) {
                    greaterOrEqual = getBoolBinop(node.getChildNodes());
                }
                if ("Like".equals(node.getNodeName())) {
                    like = getBoolBinop(node.getChildNodes());
                }
                if (NOT_LIKE.equals(node.getNodeName())) {
                    notLike = getBoolBinop(node.getChildNodes());
                }
            }
        }
        return new TraceFilterR(not, or, and, isEqual, notEqual, less, lessOrEqual, greater, greaterOrEqual, like,
                notLike);
    }

    public static BoolBinop getBoolBinop(NodeList nl) {
        String columnID = null;
        String value = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("ColumnID".equals(node.getNodeName())) {
                    columnID = node.getTextContent();
                }
                if (VALUE.equals(node.getNodeName())) {
                    value = node.getTextContent();
                }
            }
        }
        return new BoolBinopR(columnID, value);
    }

    public static AndOrType getAndOrType(NodeList nl) {
        List<AndOrTypeEnum> notOrOrOrAnd = new ArrayList<>();

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("Not".equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.Not);
                }
                if ("Or".equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.Or);
                }
                if ("And".equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.And);
                }
                if (EQUAL.equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.Equal);
                }
                if (NOT_EQUAL.equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.NotEqual);
                }
                if ("Less".equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.Less);
                }
                if (LESS_OR_EQUAL.equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.LessOrEqual);
                }
                if (GREATER.equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.Greater);
                }
                if (GREATER_OR_EQUAL.equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.GreaterOrEqual);
                }
                if ("Like".equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.Like);
                }
                if (NOT_LIKE.equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.NotLike);
                }
            }
        }
        return new AndOrTypeR(notOrOrOrAnd);
    }

    public static NotType getNotType(NodeList nl) {
        NotType not = null;
        AndOrType or = null;
        AndOrType and = null;
        BoolBinop isEqual = null;
        BoolBinop notEqual = null;
        BoolBinop less = null;
        BoolBinop lessOrEqual = null;
        BoolBinop greater = null;
        BoolBinop greaterOrEqual = null;
        BoolBinop like = null;
        BoolBinop notLike = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("Not".equals(node.getNodeName())) {
                    not = getNotType(node.getChildNodes());
                }
                if ("Or".equals(node.getNodeName())) {
                    or = getAndOrType(node.getChildNodes());
                }
                if ("And".equals(node.getNodeName())) {
                    and = getAndOrType(node.getChildNodes());
                }
                if (EQUAL.equals(node.getNodeName())) {
                    isEqual = getBoolBinop(node.getChildNodes());
                }
                if (NOT_EQUAL.equals(node.getNodeName())) {
                    notEqual = getBoolBinop(node.getChildNodes());
                }
                if ("Less".equals(node.getNodeName())) {
                    less = getBoolBinop(node.getChildNodes());
                }
                if (LESS_OR_EQUAL.equals(node.getNodeName())) {
                    lessOrEqual = getBoolBinop(node.getChildNodes());
                }
                if (GREATER.equals(node.getNodeName())) {
                    greater = getBoolBinop(node.getChildNodes());
                }
                if (GREATER_OR_EQUAL.equals(node.getNodeName())) {
                    greaterOrEqual = getBoolBinop(node.getChildNodes());
                }
                if ("Like".equals(node.getNodeName())) {
                    like = getBoolBinop(node.getChildNodes());
                }
                if (NOT_LIKE.equals(node.getNodeName())) {
                    notLike = getBoolBinop(node.getChildNodes());
                }
            }
        }
        return new NotTypeR(not, or, and, isEqual, notEqual, less, lessOrEqual, greater, greaterOrEqual, like, notLike);
    }
}
