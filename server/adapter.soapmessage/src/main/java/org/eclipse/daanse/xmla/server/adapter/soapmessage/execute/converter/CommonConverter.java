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
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CAPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.COLLATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DISPLAY_FOLDER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LANGUAGE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.SOURCE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TRANSLATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.VALUE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getList;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getNodeType;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBoolean;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toDuration;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInteger;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toLong;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.AttributeTranslation;
import org.eclipse.daanse.xmla.api.xmla.Binding;
import org.eclipse.daanse.xmla.api.xmla.DataItem;
import org.eclipse.daanse.xmla.api.xmla.DataItemFormatEnum;
import org.eclipse.daanse.xmla.api.xmla.ErrorConfiguration;
import org.eclipse.daanse.xmla.api.xmla.InvalidXmlCharacterEnum;
import org.eclipse.daanse.xmla.api.xmla.NullProcessingEnum;
import org.eclipse.daanse.xmla.api.xmla.ProactiveCaching;
import org.eclipse.daanse.xmla.api.xmla.ProactiveCachingBinding;
import org.eclipse.daanse.xmla.api.xmla.Translation;
import org.eclipse.daanse.xmla.model.record.xmla.AnnotationR;
import org.eclipse.daanse.xmla.model.record.xmla.AttributeTranslationR;
import org.eclipse.daanse.xmla.model.record.xmla.DataItemR;
import org.eclipse.daanse.xmla.model.record.xmla.ErrorConfigurationR;
import org.eclipse.daanse.xmla.model.record.xmla.ProactiveCachingR;
import org.eclipse.daanse.xmla.model.record.xmla.TranslationR;
import org.w3c.dom.NodeList;

/**
 * Common converter for shared parsing methods used by multiple converters.
 * Contains methods for Annotation, Translation, DataItem, ErrorConfiguration,
 * and ProactiveCaching.
 */
public class CommonConverter {

    private CommonConverter() {
        // utility class
    }

    public static List<Annotation> getAnnotationList(NodeList nl) {
        return getList(nl, "Annotation", CommonConverter::getAnnotation);
    }

    public static Annotation getAnnotation(NodeList nl) {
        String name = null;
        Optional<String> visibility = Optional.empty();
        Optional<java.lang.Object> value = Optional.empty();

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if ("Visibility".equals(node.getNodeName())) {
                    visibility = Optional.ofNullable(node.getTextContent());
                }
                if (VALUE.equals(node.getNodeName())) {
                    value = Optional.ofNullable(node.getTextContent());
                }
            }
        }

        return new AnnotationR(name, visibility, value);
    }

    public static List<Translation> getTranslationList(NodeList nl, String nodeName) {
        return getList(nl, nodeName, CommonConverter::getTranslation);
    }

    public static Translation getTranslation(NodeList nl) {
        long language = 0;
        String caption = null;
        String description = null;
        String displayFolder = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (LANGUAGE.equals(node.getNodeName())) {
                    language = toLong(node.getTextContent());
                }
                if (CAPTION.equals(node.getNodeName())) {
                    caption = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (DISPLAY_FOLDER.equals(node.getNodeName())) {
                    displayFolder = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new TranslationR(language, caption, description, displayFolder, annotations);
    }

    public static List<AttributeTranslation> getAttributeTranslationList(NodeList nl) {
        return getList(nl, TRANSLATION, CommonConverter::getAttributeTranslation);
    }

    public static AttributeTranslation getAttributeTranslation(NodeList nl) {
        long language = 0;
        String caption = null;
        String description = null;
        String displayFolder = null;
        List<Annotation> annotations = null;
        DataItem captionColumn = null;
        String membersWithDataCaption = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (LANGUAGE.equals(node.getNodeName())) {
                    language = toLong(node.getTextContent());
                }
                if (CAPTION.equals(node.getNodeName())) {
                    caption = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (DISPLAY_FOLDER.equals(node.getNodeName())) {
                    displayFolder = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if ("CaptionColumn".equals(node.getNodeName())) {
                    captionColumn = getDataItem(node.getChildNodes());
                }
                if ("MembersWithDataCaption".equals(node.getNodeName())) {
                    membersWithDataCaption = node.getTextContent();
                }
            }
        }
        return new AttributeTranslationR(language, Optional.ofNullable(caption), Optional.ofNullable(description),
                Optional.ofNullable(displayFolder), Optional.ofNullable(annotations),
                Optional.ofNullable(captionColumn), Optional.ofNullable(membersWithDataCaption));
    }

    public static List<DataItem> getDataItemList(NodeList nl, String nodeName) {
        return getList(nl, nodeName, CommonConverter::getDataItem);
    }

    public static DataItem getDataItem(NodeList nl) {
        String dataType = null;
        Integer dataSize = null;
        String mimeType = null;
        NullProcessingEnum nullProcessing = null;
        String trimming = null;
        InvalidXmlCharacterEnum invalidXmlCharacters = null;
        String collation = null;
        DataItemFormatEnum format = null;
        Binding source = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("DataType".equals(node.getNodeName())) {
                    dataType = node.getTextContent();
                }
                if ("DataSize".equals(node.getNodeName())) {
                    dataSize = toInteger(node.getTextContent());
                }
                if ("MimeType".equals(node.getNodeName())) {
                    mimeType = node.getTextContent();
                }
                if ("NullProcessing".equals(node.getNodeName())) {
                    nullProcessing = NullProcessingEnum.fromValue(node.getTextContent());
                }
                if ("Trimming".equals(node.getNodeName())) {
                    trimming = node.getTextContent();
                }
                if ("InvalidXmlCharacters".equals(node.getNodeName())) {
                    invalidXmlCharacters = InvalidXmlCharacterEnum.fromValue(node.getTextContent());
                }
                if (COLLATION.equals(node.getNodeName())) {
                    collation = node.getTextContent();
                }
                if ("Format".equals(node.getNodeName())) {
                    format = DataItemFormatEnum.fromValue(node.getTextContent());
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = BindingConverter.getBinding(node.getChildNodes(), getNodeType(node));
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new DataItemR(dataType, Optional.ofNullable(dataSize), Optional.ofNullable(mimeType),
                Optional.ofNullable(nullProcessing), Optional.ofNullable(trimming),
                Optional.ofNullable(invalidXmlCharacters), Optional.ofNullable(collation), Optional.ofNullable(format),
                Optional.ofNullable(source), Optional.ofNullable(annotations));
    }

    public static ErrorConfiguration getErrorConfiguration(NodeList nl) {
        Long keyErrorLimit = null;
        String keyErrorLogFile = null;
        String keyErrorAction = null;
        String keyErrorLimitAction = null;
        String keyNotFound = null;
        String keyDuplicate = null;
        String nullKeyConvertedToUnknown = null;
        String nullKeyNotAllowed = null;
        String calculationError = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("KeyErrorLimit".equals(node.getNodeName())) {
                    keyErrorLimit = toLong(node.getTextContent());
                }
                if ("KeyErrorLogFile".equals(node.getNodeName())) {
                    keyErrorLogFile = node.getTextContent();
                }
                if ("KeyErrorAction".equals(node.getNodeName())) {
                    keyErrorAction = node.getTextContent();
                }
                if ("KeyErrorLimitAction".equals(node.getNodeName())) {
                    keyErrorLimitAction = node.getTextContent();
                }
                if ("KeyNotFound".equals(node.getNodeName())) {
                    keyNotFound = node.getTextContent();
                }
                if ("KeyDuplicate".equals(node.getNodeName())) {
                    keyDuplicate = node.getTextContent();
                }
                if ("NullKeyConvertedToUnknown".equals(node.getNodeName())) {
                    nullKeyConvertedToUnknown = node.getTextContent();
                }
                if ("NullKeyNotAllowed".equals(node.getNodeName())) {
                    nullKeyNotAllowed = node.getTextContent();
                }
                if ("CalculationError".equals(node.getNodeName())) {
                    calculationError = node.getTextContent();
                }
            }
        }
        return new ErrorConfigurationR(Optional.ofNullable(keyErrorLimit), Optional.ofNullable(keyErrorLogFile),
                Optional.ofNullable(keyErrorAction), Optional.ofNullable(keyErrorLimitAction),
                Optional.ofNullable(keyNotFound), Optional.ofNullable(keyDuplicate),
                Optional.ofNullable(nullKeyConvertedToUnknown), Optional.ofNullable(nullKeyNotAllowed),
                Optional.ofNullable(calculationError));
    }

    public static ProactiveCaching getProactiveCaching(NodeList nl) {
        String onlineMode = null;
        String aggregationStorage = null;
        ProactiveCachingBinding source = null;
        Duration silenceInterval = null;
        Duration latency = null;
        Duration silenceOverrideInterval = null;
        Duration forceRebuildInterval = null;
        Boolean enabled = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("OnlineMode".equals(node.getNodeName())) {
                    onlineMode = node.getTextContent();
                }
                if ("AggregationStorage".equals(node.getNodeName())) {
                    aggregationStorage = node.getTextContent();
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = BindingConverter.getProactiveCachingBinding(node.getChildNodes(), getNodeType(node));
                }
                if ("SilenceInterval".equals(node.getNodeName())) {
                    silenceInterval = toDuration(node.getTextContent());
                }
                if ("Latency".equals(node.getNodeName())) {
                    latency = toDuration(node.getTextContent());
                }
                if ("SilenceOverrideInterval".equals(node.getNodeName())) {
                    silenceOverrideInterval = toDuration(node.getTextContent());
                }
                if ("ForceRebuildInterval".equals(node.getNodeName())) {
                    forceRebuildInterval = toDuration(node.getTextContent());
                }
                if ("Enabled".equals(node.getNodeName())) {
                    enabled = toBoolean(node.getTextContent());
                }
            }
        }
        return new ProactiveCachingR(Optional.ofNullable(onlineMode), Optional.ofNullable(aggregationStorage),
                Optional.ofNullable(source), Optional.ofNullable(silenceInterval), Optional.ofNullable(latency),
                Optional.ofNullable(silenceOverrideInterval), Optional.ofNullable(forceRebuildInterval),
                Optional.ofNullable(enabled));
    }
}
