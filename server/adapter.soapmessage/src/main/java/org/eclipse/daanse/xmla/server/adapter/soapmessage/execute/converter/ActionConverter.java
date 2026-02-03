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
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.APPLICATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CAPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CAPTION_IS_MDX;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.COLUMNS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CONDITION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.EXPRESSION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.INVOCATION_LOW;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TARGET;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TARGET_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TRANSLATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TRANSLATIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.VALUE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getNodeType;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.matchesLocalName;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBoolean;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.Action;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.Binding;
import org.eclipse.daanse.xmla.api.xmla.ReportFormatParameter;
import org.eclipse.daanse.xmla.api.xmla.ReportParameter;
import org.eclipse.daanse.xmla.api.xmla.TargetTypeEnum;
import org.eclipse.daanse.xmla.api.xmla.Translation;
import org.eclipse.daanse.xmla.api.xmla.TypeEnum;
import org.eclipse.daanse.xmla.model.record.xmla.DrillThroughActionR;
import org.eclipse.daanse.xmla.model.record.xmla.ReportActionR;
import org.eclipse.daanse.xmla.model.record.xmla.ReportFormatParameterR;
import org.eclipse.daanse.xmla.model.record.xmla.ReportParameterR;
import org.eclipse.daanse.xmla.model.record.xmla.StandardActionR;
import org.w3c.dom.NodeList;

/**
 * Converter for Action types (StandardAction, ReportAction, DrillThroughAction)
 * from XML NodeList.
 */
public class ActionConverter {

    private ActionConverter() {
        // utility class
    }

    public static List<Action> getActionList(NodeList nl) {
        List<Action> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, ACTION)) {
                list.add(getAction(node.getChildNodes(), getNodeType(node)));
            }
        }
        return list;
    }

    public static Action getAction(NodeList nl, String type) {
        if ("StandardAction".equals(type)) {
            return getStandardAction(nl);
        }
        if ("ReportAction".equals(type)) {
            return getReportAction(nl);
        }
        if ("DrillThroughAction".equals(type)) {
            return getDrillThroughAction(nl);
        }
        return null;
    }

    public static Action getStandardAction(NodeList nl) {
        String name = null;
        String id = null;
        String caption = null;
        Boolean captionIsMdx = null;
        List<Translation> translations = null;
        TargetTypeEnum targetType = null;
        String target = null;
        String condition = null;
        TypeEnum type = null;
        String invocation = null;
        String application = null;
        String description = null;
        List<Annotation> annotations = null;
        String expression = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, NAME)) {
                    name = node.getTextContent();
                }
                if (matchesLocalName(node, ID)) {
                    id = node.getTextContent();
                }
                if (matchesLocalName(node, CAPTION)) {
                    caption = node.getTextContent();
                }
                if (matchesLocalName(node, CAPTION_IS_MDX)) {
                    captionIsMdx = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, TRANSLATIONS)) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (matchesLocalName(node, TARGET_TYPE)) {
                    targetType = TargetTypeEnum.fromValue(node.getTextContent());
                }
                if (matchesLocalName(node, TARGET)) {
                    target = node.getTextContent();
                }
                if (matchesLocalName(node, CONDITION)) {
                    condition = node.getTextContent();
                }
                if (matchesLocalName(node, "Type")) {
                    type = TypeEnum.fromValue(node.getTextContent());
                }
                if (matchesLocalName(node, INVOCATION_LOW)) {
                    invocation = node.getTextContent();
                }
                if (matchesLocalName(node, APPLICATION)) {
                    application = node.getTextContent();
                }
                if (matchesLocalName(node, DESCRIPTION)) {
                    description = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (matchesLocalName(node, EXPRESSION)) {
                    expression = node.getTextContent();
                }
            }
        }
        return new StandardActionR(name, Optional.ofNullable(id), Optional.ofNullable(caption),
                Optional.ofNullable(captionIsMdx), Optional.ofNullable(translations), targetType,
                Optional.ofNullable(target), Optional.ofNullable(condition), type, Optional.ofNullable(invocation),
                Optional.ofNullable(application), Optional.ofNullable(description), Optional.ofNullable(annotations),
                expression);
    }

    public static Action getReportAction(NodeList nl) {
        String name = null;
        String id = null;
        String caption = null;
        Boolean captionIsMdx = null;
        List<Translation> translations = null;
        TargetTypeEnum targetType = null;
        String target = null;
        String condition = null;
        TypeEnum type = null;
        String invocation = null;
        String application = null;
        String description = null;
        List<Annotation> annotations = null;
        String reportServer = null;
        String path = null;
        List<ReportParameter> reportParameters = null;
        List<ReportFormatParameter> reportFormatParameters = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, NAME)) {
                    name = node.getTextContent();
                }
                if (matchesLocalName(node, ID)) {
                    id = node.getTextContent();
                }
                if (matchesLocalName(node, CAPTION)) {
                    caption = node.getTextContent();
                }
                if (matchesLocalName(node, CAPTION_IS_MDX)) {
                    captionIsMdx = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, TRANSLATIONS)) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (matchesLocalName(node, TARGET_TYPE)) {
                    targetType = TargetTypeEnum.fromValue(node.getTextContent());
                }
                if (matchesLocalName(node, TARGET)) {
                    target = node.getTextContent();
                }
                if (matchesLocalName(node, CONDITION)) {
                    condition = node.getTextContent();
                }
                if (matchesLocalName(node, "Type")) {
                    type = TypeEnum.fromValue(node.getTextContent());
                }
                if (matchesLocalName(node, INVOCATION_LOW)) {
                    invocation = node.getTextContent();
                }
                if (matchesLocalName(node, APPLICATION)) {
                    application = node.getTextContent();
                }
                if (matchesLocalName(node, DESCRIPTION)) {
                    description = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (matchesLocalName(node, "ReportServer")) {
                    reportServer = node.getTextContent();
                }
                if (matchesLocalName(node, "Path")) {
                    path = node.getTextContent();
                }
                if (matchesLocalName(node, "ReportParameters")) {
                    reportParameters = getReportParameterList(node.getChildNodes());
                }
                if (matchesLocalName(node, "ReportFormatParameters")) {
                    reportFormatParameters = getReportFormatParametersList(node.getChildNodes());
                }
            }
        }
        return new ReportActionR(name, Optional.ofNullable(id), Optional.ofNullable(caption),
                Optional.ofNullable(captionIsMdx), Optional.ofNullable(translations), targetType,
                Optional.ofNullable(target), Optional.ofNullable(condition), type, Optional.ofNullable(invocation),
                Optional.ofNullable(application), Optional.ofNullable(description), Optional.ofNullable(annotations),
                reportServer, Optional.ofNullable(path), Optional.ofNullable(reportParameters),
                Optional.ofNullable(reportFormatParameters));
    }

    public static Action getDrillThroughAction(NodeList nl) {
        String name = null;
        String id = null;
        String caption = null;
        Boolean captionIsMdx = null;
        List<Translation> translations = null;
        TargetTypeEnum targetType = null;
        String target = null;
        String condition = null;
        TypeEnum type = null;
        String invocation = null;
        String application = null;
        String description = null;
        List<Annotation> annotations = null;
        Boolean defaultAction = null;
        List<Binding> columns = null;
        Integer maximumRows = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, NAME)) {
                    name = node.getTextContent();
                }
                if (matchesLocalName(node, ID)) {
                    id = node.getTextContent();
                }
                if (matchesLocalName(node, CAPTION)) {
                    caption = node.getTextContent();
                }
                if (matchesLocalName(node, CAPTION_IS_MDX)) {
                    captionIsMdx = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, TRANSLATIONS)) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (matchesLocalName(node, TARGET_TYPE)) {
                    targetType = TargetTypeEnum.fromValue(node.getTextContent());
                }
                if (matchesLocalName(node, TARGET)) {
                    target = node.getTextContent();
                }
                if (matchesLocalName(node, CONDITION)) {
                    condition = node.getTextContent();
                }
                if (matchesLocalName(node, "Type")) {
                    type = TypeEnum.fromValue(node.getTextContent());
                }
                if (matchesLocalName(node, INVOCATION_LOW)) {
                    invocation = node.getTextContent();
                }
                if (matchesLocalName(node, APPLICATION)) {
                    application = node.getTextContent();
                }
                if (matchesLocalName(node, DESCRIPTION)) {
                    description = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (matchesLocalName(node, "Default")) {
                    defaultAction = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, COLUMNS)) {
                    columns = getBindingList(node.getChildNodes());
                }
                if (matchesLocalName(node, "MaximumRows")) {
                    maximumRows = toInteger(node.getTextContent());
                }
            }
        }
        return new DrillThroughActionR(name, Optional.ofNullable(id), Optional.ofNullable(caption),
                Optional.ofNullable(captionIsMdx), Optional.ofNullable(translations), targetType,
                Optional.ofNullable(target), Optional.ofNullable(condition), type, Optional.ofNullable(invocation),
                Optional.ofNullable(application), Optional.ofNullable(description), Optional.ofNullable(annotations),
                Optional.ofNullable(defaultAction), Optional.ofNullable(columns), Optional.ofNullable(maximumRows));
    }

    private static List<Binding> getBindingList(NodeList nl) {
        List<Binding> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "Column")) {
                list.add(BindingConverter.getBinding(node.getChildNodes(), getNodeType(node)));
            }
        }
        return list;
    }

    private static List<ReportFormatParameter> getReportFormatParametersList(NodeList nl) {
        List<ReportFormatParameter> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "ReportFormatParameter")) {
                list.add(getReportFormatParameter(node.getChildNodes()));
            }
        }
        return list;
    }

    private static ReportFormatParameter getReportFormatParameter(NodeList nl) {
        String name = null;
        String value = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, NAME)) {
                    name = node.getTextContent();
                }
                if (matchesLocalName(node, VALUE)) {
                    value = node.getTextContent();
                }
            }
        }
        return new ReportFormatParameterR(name, value);
    }

    private static List<ReportParameter> getReportParameterList(NodeList nl) {
        List<ReportParameter> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "ReportParameter")) {
                list.add(getReportParameter(node.getChildNodes()));
            }
        }
        return list;
    }

    private static ReportParameter getReportParameter(NodeList nl) {
        String name = null;
        String value = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, NAME)) {
                    name = node.getTextContent();
                }
                if (matchesLocalName(node, VALUE)) {
                    value = node.getTextContent();
                }
            }
        }
        return new ReportParameterR(name, value);
    }
}
