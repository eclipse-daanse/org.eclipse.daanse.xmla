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

import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ACTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ANNOTATIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTE_HIERARCHY_VISIBLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CREATED_TIMESTAMP;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CUBE_DIMENSION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DEFAULT_MEMBER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.HIERARCHIES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.HIERARCHY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LAST_SCHEMA_UPDATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASURE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASURES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASURE_GROUP;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASURE_GROUP_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASURE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TRANSLATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TRANSLATIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.matchesLocalName;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBoolean;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInstant;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.Perspective;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveAction;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveAttribute;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveCalculation;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveDimension;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveHierarchy;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveKpi;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveMeasure;
import org.eclipse.daanse.xmla.api.xmla.PerspectiveMeasureGroup;
import org.eclipse.daanse.xmla.api.xmla.Translation;
import org.eclipse.daanse.xmla.model.record.xmla.PerspectiveActionR;
import org.eclipse.daanse.xmla.model.record.xmla.PerspectiveAttributeR;
import org.eclipse.daanse.xmla.model.record.xmla.PerspectiveCalculationR;
import org.eclipse.daanse.xmla.model.record.xmla.PerspectiveDimensionR;
import org.eclipse.daanse.xmla.model.record.xmla.PerspectiveHierarchyR;
import org.eclipse.daanse.xmla.model.record.xmla.PerspectiveKpiR;
import org.eclipse.daanse.xmla.model.record.xmla.PerspectiveMeasureGroupR;
import org.eclipse.daanse.xmla.model.record.xmla.PerspectiveMeasureR;
import org.eclipse.daanse.xmla.model.record.xmla.PerspectiveR;
import org.w3c.dom.NodeList;

/**
 * Converter for Perspective and related types from XML NodeList.
 */
public class PerspectiveConverter {

    private PerspectiveConverter() {
        // utility class
    }

    public static List<Perspective> getPerspectiveList(NodeList nl) {
        List<Perspective> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "Perspective")) {
                list.add(getPerspective(node.getChildNodes()));
            }
        }
        return list;
    }

    public static Perspective getPerspective(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        List<Translation> translations = null;
        String defaultMeasure = null;
        List<PerspectiveDimension> dimensions = null;
        List<PerspectiveMeasureGroup> measureGroups = null;
        List<PerspectiveCalculation> calculations = null;
        List<PerspectiveKpi> kpis = null;
        List<PerspectiveAction> actions = null;

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
                if (matchesLocalName(node, TRANSLATIONS)) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (matchesLocalName(node, "DefaultMeasure")) {
                    defaultMeasure = node.getTextContent();
                }
                if (matchesLocalName(node, DIMENSIONS)) {
                    dimensions = getPerspectiveDimensionList(node.getChildNodes());
                }
                if (matchesLocalName(node, "MeasureGroups")) {
                    measureGroups = getPerspectiveMeasureGroupList(node.getChildNodes());
                }
                if (matchesLocalName(node, "Calculations")) {
                    calculations = getPerspectiveCalculationList(node.getChildNodes());
                }
                if (matchesLocalName(node, "Kpis")) {
                    kpis = getPerspectiveKpiList(node.getChildNodes());
                }
                if (matchesLocalName(node, "Actions")) {
                    actions = getPerspectiveActionList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveR(name, id, createdTimestamp, lastSchemaUpdate, description, annotations, translations,
                defaultMeasure, dimensions, measureGroups, calculations, kpis, actions);
    }

    public static List<PerspectiveAction> getPerspectiveActionList(NodeList nl) {
        List<PerspectiveAction> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, ACTION)) {
                list.add(getPerspectiveAction(node.getChildNodes()));
            }
        }
        return list;
    }

    public static PerspectiveAction getPerspectiveAction(NodeList nl) {
        String actionID = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, "ActionID")) {
                    actionID = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveActionR(actionID, Optional.ofNullable(annotations));
    }

    public static List<PerspectiveKpi> getPerspectiveKpiList(NodeList nl) {
        List<PerspectiveKpi> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "Kpi")) {
                list.add(getPerspectiveKpi(node.getChildNodes()));
            }
        }
        return list;
    }

    public static PerspectiveKpi getPerspectiveKpi(NodeList nl) {
        String kpiID = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, "KpiID")) {
                    kpiID = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveKpiR(kpiID, Optional.ofNullable(annotations));
    }

    public static List<PerspectiveCalculation> getPerspectiveCalculationList(NodeList nl) {
        List<PerspectiveCalculation> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "Calculation")) {
                list.add(getPerspectiveCalculation(node.getChildNodes()));
            }
        }
        return list;
    }

    public static PerspectiveCalculation getPerspectiveCalculation(NodeList nl) {
        String name = null;
        String type = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, NAME)) {
                    name = node.getTextContent();
                }
                if (matchesLocalName(node, "Type")) {
                    type = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveCalculationR(name, type, Optional.ofNullable(annotations));
    }

    public static List<PerspectiveMeasureGroup> getPerspectiveMeasureGroupList(NodeList nl) {
        List<PerspectiveMeasureGroup> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, MEASURE_GROUP)) {
                list.add(getPerspectiveMeasureGroup(node.getChildNodes()));
            }
        }
        return list;
    }

    public static PerspectiveMeasureGroup getPerspectiveMeasureGroup(NodeList nl) {
        String measureGroupID = null;
        List<PerspectiveMeasure> measures = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, MEASURE_GROUP_ID)) {
                    measureGroupID = node.getTextContent();
                }
                if (matchesLocalName(node, MEASURES)) {
                    measures = getPerspectiveMeasureList(node.getChildNodes());
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveMeasureGroupR(measureGroupID, Optional.ofNullable(measures),
                Optional.ofNullable(annotations));
    }

    public static List<PerspectiveMeasure> getPerspectiveMeasureList(NodeList nl) {
        List<PerspectiveMeasure> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, MEASURE)) {
                list.add(getPerspectiveMeasure(node.getChildNodes()));
            }
        }
        return list;
    }

    public static PerspectiveMeasure getPerspectiveMeasure(NodeList nl) {
        String measureID = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, MEASURE_ID)) {
                    measureID = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveMeasureR(measureID, annotations);
    }

    public static List<PerspectiveDimension> getPerspectiveDimensionList(NodeList nl) {
        List<PerspectiveDimension> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, DIMENSION)) {
                list.add(getPerspectiveDimension(node.getChildNodes()));
            }
        }
        return list;
    }

    public static PerspectiveDimension getPerspectiveDimension(NodeList nl) {
        String cubeDimensionID = null;
        List<PerspectiveAttribute> attributes = null;
        List<PerspectiveHierarchy> hierarchies = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, CUBE_DIMENSION_ID)) {
                    cubeDimensionID = node.getTextContent();
                }
                if (matchesLocalName(node, ATTRIBUTES)) {
                    attributes = getPerspectiveAttributeList(node.getChildNodes());
                }
                if (matchesLocalName(node, HIERARCHIES)) {
                    hierarchies = getPerspectiveHierarchyList(node.getChildNodes());
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveDimensionR(cubeDimensionID, Optional.ofNullable(attributes),
                Optional.ofNullable(hierarchies), Optional.ofNullable(annotations));
    }

    public static List<PerspectiveHierarchy> getPerspectiveHierarchyList(NodeList nl) {
        List<PerspectiveHierarchy> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, HIERARCHY)) {
                list.add(getPerspectiveHierarchy(node.getChildNodes()));
            }
        }
        return list;
    }

    public static PerspectiveHierarchy getPerspectiveHierarchy(NodeList nl) {
        String hierarchyID = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, "HierarchyID")) {
                    hierarchyID = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveHierarchyR(hierarchyID, Optional.ofNullable(annotations));
    }

    public static List<PerspectiveAttribute> getPerspectiveAttributeList(NodeList nl) {
        List<PerspectiveAttribute> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, ATTRIBUTE)) {
                list.add(getPerspectiveAttribute(node.getChildNodes()));
            }
        }
        return list;
    }

    public static PerspectiveAttribute getPerspectiveAttribute(NodeList nl) {
        String attributeID = null;
        Boolean attributeHierarchyVisible = null;
        String defaultMember = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, ATTRIBUTE_ID)) {
                    attributeID = node.getTextContent();
                }
                if (matchesLocalName(node, ATTRIBUTE_HIERARCHY_VISIBLE)) {
                    attributeHierarchyVisible = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, DEFAULT_MEMBER)) {
                    defaultMember = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveAttributeR(attributeID, Optional.ofNullable(attributeHierarchyVisible),
                Optional.ofNullable(defaultMember), Optional.ofNullable(annotations));
    }
}
