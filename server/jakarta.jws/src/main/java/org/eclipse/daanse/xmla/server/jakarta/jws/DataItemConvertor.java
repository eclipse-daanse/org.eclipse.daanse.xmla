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
package org.eclipse.daanse.xmla.server.jakarta.jws;

import static org.eclipse.daanse.xmla.server.jakarta.jws.AnnotationConvertor.convertAnnotationList;
import static org.eclipse.daanse.xmla.server.jakarta.jws.BindingConvertor.convertBinding;

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.DataItem;
import org.eclipse.daanse.xmla.api.xmla.DataItemFormatEnum;
import org.eclipse.daanse.xmla.api.xmla.InvalidXmlCharacterEnum;
import org.eclipse.daanse.xmla.api.xmla.NullProcessingEnum;
import org.eclipse.daanse.xmla.model.record.xmla.DataItemR;

public class DataItemConvertor {

    private DataItemConvertor() {
    }

    public static List<DataItem> convertDataItemList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DataItem> keyColumnList) {
        if (keyColumnList != null) {
            return keyColumnList.stream().map(DataItemConvertor::convertDataItem).toList();
        }
        return List.of();
    }

    public static DataItem convertDataItem(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DataItem source) {
        if (source != null) {
            return new DataItemR(source.getDataType(),
                Optional.ofNullable(source.getDataSize()),
                Optional.ofNullable(source.getMimeType()),
                Optional.ofNullable(NullProcessingEnum.fromValue(source.getNullProcessing())),
                Optional.ofNullable(source.getTrimming()),
                Optional.ofNullable(InvalidXmlCharacterEnum.fromValue(source.getInvalidXmlCharacters())),
                Optional.ofNullable(source.getCollation()),
                Optional.ofNullable(DataItemFormatEnum.fromValue(source.getFormat())),
                Optional.ofNullable(convertBinding(source.getSource())),
                Optional.ofNullable(convertAnnotationList(source.getAnnotations())));
        }
        return null;
    }

}
