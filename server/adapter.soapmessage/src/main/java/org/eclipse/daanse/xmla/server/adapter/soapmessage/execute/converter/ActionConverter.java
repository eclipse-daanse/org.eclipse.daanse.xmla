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
            if ((node != null) && (ACTION.equals(node.getNodeName()))) {
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
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CAPTION.equals(node.getNodeName())) {
                    caption = node.getTextContent();
                }
                if (CAPTION_IS_MDX.equals(node.getNodeName())) {
                    captionIsMdx = toBoolean(node.getTextContent());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (TARGET_TYPE.equals(node.getNodeName())) {
                    targetType = TargetTypeEnum.fromValue(node.getTextContent());
                }
                if (TARGET.equals(node.getNodeName())) {
                    target = node.getTextContent();
                }
                if (CONDITION.equals(node.getNodeName())) {
                    condition = node.getTextContent();
                }
                if ("Type".equals(node.getNodeName())) {
                    type = TypeEnum.fromValue(node.getTextContent());
                }
                if (INVOCATION_LOW.equals(node.getNodeName())) {
                    invocation = node.getTextContent();
                }
                if (APPLICATION.equals(node.getNodeName())) {
                    application = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (EXPRESSION.equals(node.getNodeName())) {
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
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CAPTION.equals(node.getNodeName())) {
                    caption = node.getTextContent();
                }
                if (CAPTION_IS_MDX.equals(node.getNodeName())) {
                    captionIsMdx = toBoolean(node.getTextContent());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (TARGET_TYPE.equals(node.getNodeName())) {
                    targetType = TargetTypeEnum.fromValue(node.getTextContent());
                }
                if (TARGET.equals(node.getNodeName())) {
                    target = node.getTextContent();
                }
                if (CONDITION.equals(node.getNodeName())) {
                    condition = node.getTextContent();
                }
                if ("Type".equals(node.getNodeName())) {
                    type = TypeEnum.fromValue(node.getTextContent());
                }
                if (INVOCATION_LOW.equals(node.getNodeName())) {
                    invocation = node.getTextContent();
                }
                if (APPLICATION.equals(node.getNodeName())) {
                    application = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if ("ReportServer".equals(node.getNodeName())) {
                    reportServer = node.getTextContent();
                }
                if ("Path".equals(node.getNodeName())) {
                    path = node.getTextContent();
                }
                if ("ReportParameters".equals(node.getNodeName())) {
                    reportParameters = getReportParameterList(node.getChildNodes());
                }
                if ("ReportFormatParameters".equals(node.getNodeName())) {
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
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CAPTION.equals(node.getNodeName())) {
                    caption = node.getTextContent();
                }
                if (CAPTION_IS_MDX.equals(node.getNodeName())) {
                    captionIsMdx = toBoolean(node.getTextContent());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (TARGET_TYPE.equals(node.getNodeName())) {
                    targetType = TargetTypeEnum.fromValue(node.getTextContent());
                }
                if (TARGET.equals(node.getNodeName())) {
                    target = node.getTextContent();
                }
                if (CONDITION.equals(node.getNodeName())) {
                    condition = node.getTextContent();
                }
                if ("Type".equals(node.getNodeName())) {
                    type = TypeEnum.fromValue(node.getTextContent());
                }
                if (INVOCATION_LOW.equals(node.getNodeName())) {
                    invocation = node.getTextContent();
                }
                if (APPLICATION.equals(node.getNodeName())) {
                    application = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if ("Default".equals(node.getNodeName())) {
                    defaultAction = toBoolean(node.getTextContent());
                }
                if (COLUMNS.equals(node.getNodeName())) {
                    columns = getBindingList(node.getChildNodes());
                }
                if ("MaximumRows".equals(node.getNodeName())) {
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
            if ((node != null) && ("Column".equals(node.getNodeName()))) {
                list.add(BindingConverter.getBinding(node.getChildNodes(), getNodeType(node)));
            }
        }
        return list;
    }

    private static List<ReportFormatParameter> getReportFormatParametersList(NodeList nl) {
        List<ReportFormatParameter> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && ("ReportFormatParameter".equals(node.getNodeName()))) {
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
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (VALUE.equals(node.getNodeName())) {
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
            if ((node != null) && ("ReportParameter".equals(node.getNodeName()))) {
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
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (VALUE.equals(node.getNodeName())) {
                    value = node.getTextContent();
                }
            }
        }
        return new ReportParameterR(name, value);
    }
}
