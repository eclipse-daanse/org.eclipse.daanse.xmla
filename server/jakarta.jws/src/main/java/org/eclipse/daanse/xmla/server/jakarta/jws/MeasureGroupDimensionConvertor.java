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
import static org.eclipse.daanse.xmla.server.jakarta.jws.DataItemConvertor.convertDataItemList;

import java.util.List;

import org.eclipse.daanse.xmla.api.xmla.MeasureGroupAttribute;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimensionBinding;
import org.eclipse.daanse.xmla.model.record.xmla.DataMiningMeasureGroupDimensionR;
import org.eclipse.daanse.xmla.model.record.xmla.DegenerateMeasureGroupDimensionR;
import org.eclipse.daanse.xmla.model.record.xmla.ManyToManyMeasureGroupDimensionR;
import org.eclipse.daanse.xmla.model.record.xmla.MeasureGroupAttributeR;
import org.eclipse.daanse.xmla.model.record.xmla.MeasureGroupDimensionBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.ReferenceMeasureGroupDimensionR;
import org.eclipse.daanse.xmla.model.record.xmla.RegularMeasureGroupDimensionR;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DataMiningMeasureGroupDimension;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DegenerateMeasureGroupDimension;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ManyToManyMeasureGroupDimension;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ReferenceMeasureGroupDimension;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.RegularMeasureGroupDimension;

public class MeasureGroupDimensionConvertor {

    private MeasureGroupDimensionConvertor() {
    }

    public static MeasureGroupDimension convertMeasureGroupDimension(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MeasureGroupDimension measureGroupDimension) {
        if (measureGroupDimension != null) {
            if (measureGroupDimension instanceof ManyToManyMeasureGroupDimension manyToManyMeasureGroupDimension) {
                return convertManyToManyMeasureGroupDimension(manyToManyMeasureGroupDimension);
            }
            if (measureGroupDimension instanceof RegularMeasureGroupDimension regularMeasureGroupDimension) {
                return convertRegularMeasureGroupDimension(regularMeasureGroupDimension);
            }
            if (measureGroupDimension instanceof org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ReferenceMeasureGroupDimension referenceMeasureGroupDimension) {
                return convertReferenceMeasureGroupDimension(referenceMeasureGroupDimension);
            }
            if (measureGroupDimension instanceof DegenerateMeasureGroupDimension degenerateMeasureGroupDimension) {
                return convertDegenerateMeasureGroupDimension(degenerateMeasureGroupDimension);
            }
            if (measureGroupDimension instanceof DataMiningMeasureGroupDimension dataMiningMeasureGroupDimension) {
                return convertDataMiningMeasureGroupDimension(dataMiningMeasureGroupDimension);
            }
        }
        return null;
    }

    private static MeasureGroupDimension convertDataMiningMeasureGroupDimension(DataMiningMeasureGroupDimension dataMiningMeasureGroupDimension) {
        return new DataMiningMeasureGroupDimensionR(dataMiningMeasureGroupDimension.getCubeDimensionID(),
            convertAnnotationList(dataMiningMeasureGroupDimension.getAnnotations()),
            convertMeasureGroupDimensionBinding(dataMiningMeasureGroupDimension.getSource()),
            dataMiningMeasureGroupDimension.getCaseCubeDimensionID());
    }

    private static MeasureGroupDimension convertDegenerateMeasureGroupDimension(
        DegenerateMeasureGroupDimension degenerateMeasureGroupDimension) {
        return new DegenerateMeasureGroupDimensionR(degenerateMeasureGroupDimension.getCubeDimensionID(),
            convertAnnotationList(degenerateMeasureGroupDimension.getAnnotations()),
            convertMeasureGroupDimensionBinding(degenerateMeasureGroupDimension.getSource()),
            degenerateMeasureGroupDimension.getShareDimensionStorage());
    }

    private static MeasureGroupDimension convertReferenceMeasureGroupDimension(
        ReferenceMeasureGroupDimension referenceMeasureGroupDimension) {
        return new ReferenceMeasureGroupDimensionR(referenceMeasureGroupDimension.getCubeDimensionID(),
            convertAnnotationList(referenceMeasureGroupDimension.getAnnotations()),
            convertMeasureGroupDimensionBinding(referenceMeasureGroupDimension.getSource()),
            referenceMeasureGroupDimension.getIntermediateCubeDimensionID(),
            referenceMeasureGroupDimension.getIntermediateGranularityAttributeID(),
            referenceMeasureGroupDimension.getMaterialization(),
            referenceMeasureGroupDimension.getProcessingState());
    }

    private static MeasureGroupDimension convertRegularMeasureGroupDimension(
        RegularMeasureGroupDimension regularMeasureGroupDimension) {
        return new RegularMeasureGroupDimensionR(regularMeasureGroupDimension.getCubeDimensionID(),
            convertAnnotationList(regularMeasureGroupDimension.getAnnotations()),
            convertMeasureGroupDimensionBinding(regularMeasureGroupDimension.getSource()),
            regularMeasureGroupDimension.getCardinality(),
            convertMeasureGroupAttributeList(regularMeasureGroupDimension.getAttributes()));
    }

    private static MeasureGroupDimension convertManyToManyMeasureGroupDimension(
        ManyToManyMeasureGroupDimension manyToManyMeasureGroupDimension) {
        return new ManyToManyMeasureGroupDimensionR(manyToManyMeasureGroupDimension.getCubeDimensionID(),
            convertAnnotationList(manyToManyMeasureGroupDimension.getAnnotations()),
            convertMeasureGroupDimensionBinding(manyToManyMeasureGroupDimension.getSource()),
            manyToManyMeasureGroupDimension.getMeasureGroupID(),
            manyToManyMeasureGroupDimension.getDirectSlice());
    }

    private static List<MeasureGroupAttribute> convertMeasureGroupAttributeList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MeasureGroupAttribute> attributeList) {
        if (attributeList != null) {
            return attributeList.stream().map(MeasureGroupDimensionConvertor::convertMeasureGroupAttribute).toList();
        }
        return List.of();
    }

    private static MeasureGroupAttribute convertMeasureGroupAttribute(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MeasureGroupAttribute measureGroupAttribute) {
        if (measureGroupAttribute != null) {
            return new MeasureGroupAttributeR(measureGroupAttribute.getAttributeID(),
                    convertDataItemList(measureGroupAttribute.getKeyColumns()),
                    measureGroupAttribute.getType(),
                    convertAnnotationList(measureGroupAttribute.getAnnotations()));
        }
        return null;
    }

    private static MeasureGroupDimensionBinding convertMeasureGroupDimensionBinding(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MeasureGroupDimensionBinding source) {
        if (source != null) {
            return new MeasureGroupDimensionBindingR(source.getCubeDimensionID());
        }
        return null;
    }
}
