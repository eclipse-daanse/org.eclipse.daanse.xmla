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

import static org.eclipse.daanse.xmla.server.jakarta.jws.ConvertorUtil.convertException;
import static org.eclipse.daanse.xmla.server.jakarta.jws.ConvertorUtil.convertMessages;

import java.time.Instant;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.daanse.xmla.api.mddataset.CellInfoItem;
import org.eclipse.daanse.xmla.api.mddataset.Value;
import org.eclipse.daanse.xmla.api.msxmla.MemberRef;
import org.eclipse.daanse.xmla.api.msxmla.MembersLookup;
import org.eclipse.daanse.xmla.api.msxmla.NormTuple;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.CellTypeEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.msxmla.NormTupleSet;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.msxmla.NormTuplesType;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.Axes;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.AxesInfo;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.Axis;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.AxisInfo;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.CellData;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.CellInfo;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.CellSetType;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.CellType;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.CellTypeError;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.CubeInfo;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.HierarchyInfo;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.Mddataset;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.MemberType;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.MembersType;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.OlapInfo;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.OlapInfoCube;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.SetListType;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.TupleType;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.TuplesType;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset.Union;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MdDataSetConvertor {

    private MdDataSetConvertor() {
    }

    public static final String NAME = "name";
    public static final String TYPE = "type";

    public static Mddataset convertMdDataSet(org.eclipse.daanse.xmla.api.mddataset.Mddataset mdDataSet) {
        Mddataset mddatasetXml =
            new Mddataset();
        mddatasetXml.setAxes(convertAxes(mdDataSet.axes()));
        mddatasetXml.setCellData(convertCellData(mdDataSet.cellData()));
        mddatasetXml.setException(convertException(mdDataSet.exception()));
        mddatasetXml.setMessages(convertMessages(mdDataSet.messages()));
        mddatasetXml.setOlapInfo(convertOlapInfo(mdDataSet.olapInfo()));
        return mddatasetXml;
    }

    private static CellData convertCellData(org.eclipse.daanse.xmla.api.mddataset.CellData cellData) {
        if (cellData != null) {
            CellData res = new CellData();
            res.setCell(convertCellTypeList(cellData.cell()));
            res.setCellSet(convertCellSet(cellData.cellSet()));
            return res;
        }
        return null;
    }

    private static CellSetType convertCellSet(org.eclipse.daanse.xmla.api.mddataset.CellSetType cellSet) {
        if (cellSet != null) {
            CellSetType res = new CellSetType();
            res.setData(cellSet.data());
        }
        return null;
    }

    private static List<CellType> convertCellTypeList(List<org.eclipse.daanse.xmla.api.mddataset.CellType> cellTypeList) {
        if (cellTypeList != null) {
            return cellTypeList.stream().map(MdDataSetConvertor::convertCellType).toList();
        }
        return List.of();
    }

    private static CellType convertCellType(org.eclipse.daanse.xmla.api.mddataset.CellType cellType) {
        if (cellType != null) {
            CellType res = new CellType();
            res.setAny(convertElementList(cellType.any()));
            res.setValue(convertValue(cellType.value()));
            res.setCellOrdinal(cellType.cellOrdinal());
            return res;
        }
        return null;
    }

    private static CellType.Value convertValue(Value value) {
        if (value != null) {
            CellType.Value res = new CellType.Value();
            res.setError(convertCellTypeErrorList(value.error()));
            res.setType(CellTypeEnum.fromValue(value.type() != null ? value.type().getValue() : null ));
            res.setValue(value.value() != null ? List.of(value.value()) : null);
            return res;
        }
        return null;
    }

    private static List<CellTypeError> convertCellTypeErrorList(List<org.eclipse.daanse.xmla.api.mddataset.CellTypeError> errorList) {
        if (errorList != null) {
            return errorList.stream().map(MdDataSetConvertor::convertCellTypeError).toList();
        }
        return List.of();
    }

    private static CellTypeError convertCellTypeError(org.eclipse.daanse.xmla.api.mddataset.CellTypeError cellTypeError) {
        if (cellTypeError != null) {
            CellTypeError res = new CellTypeError();
            res.setErrorCode(cellTypeError.errorCode());
            res.setDescription(cellTypeError.description());
            return res;
        }
        return null;
    }

    private static List<Element> convertElementList(List<CellInfoItem> anyList) {
        if (anyList != null) {
            return anyList.stream().map(MdDataSetConvertor::convertElement).toList();
        }
        return List.of();
    }

    private static Element convertElement(CellInfoItem cellInfoItem) {
        if (cellInfoItem != null) {
            try {
                Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .newDocument();
                Element element = document.createElement(cellInfoItem.tagName());
                element.setAttribute(NAME, cellInfoItem.name());

                cellInfoItem.type().ifPresent(type -> element.setAttribute(TYPE, type));

                return element;
            } catch (ParserConfigurationException e) {
                throw new ConvertorException(e);
            }
        }

        return null;
    }

    private static OlapInfo convertOlapInfo(org.eclipse.daanse.xmla.api.mddataset.OlapInfo olapInfo) {
        if (olapInfo != null) {
            OlapInfo res = new OlapInfo();
            res.setAxesInfo(convertAxesInfo(olapInfo.axesInfo()));
            res.setCellInfo(convertCellInfo(olapInfo.cellInfo()));
            res.setCubeInfo(convertCubeInfo(olapInfo.cubeInfo()));
            return res;
        }
        return null;
    }

    private static CubeInfo convertCubeInfo(org.eclipse.daanse.xmla.api.mddataset.CubeInfo cubeInfo) {
        if (cubeInfo != null) {
            CubeInfo res = new CubeInfo();
            res.setCube(convertOlapInfoCubeList(cubeInfo.cube()));
            return res;
        }
        return null;
    }

    private static List<OlapInfoCube> convertOlapInfoCubeList(List<org.eclipse.daanse.xmla.api.mddataset.OlapInfoCube> cubeList) {
        if (cubeList != null) {
            return cubeList.stream().map(MdDataSetConvertor::convertOlapInfoCube).toList();
        }
        return List.of();
    }

    private static OlapInfoCube convertOlapInfoCube(org.eclipse.daanse.xmla.api.mddataset.OlapInfoCube olapInfoCube) {
        if (olapInfoCube != null) {
            OlapInfoCube res = new OlapInfoCube();
            res.setCubeName(olapInfoCube.cubeName());
            res.setLastDataUpdate(convertToXMLGregorianCalendar(olapInfoCube.lastDataUpdate()));
            res.setLastSchemaUpdate(convertToXMLGregorianCalendar(olapInfoCube.lastSchemaUpdate()));
            return res;
        }
        return null;
    }

    private static XMLGregorianCalendar convertToXMLGregorianCalendar(Instant instant) {
        GregorianCalendar gregorianCalendar = GregorianCalendar
            .from(instant.atZone(ZoneId.systemDefault()));
        try {
            return DatatypeFactory
                .newInstance()
                .newXMLGregorianCalendar(
                    gregorianCalendar
                );
        } catch (DatatypeConfigurationException e) {
            throw new ConvertorException(e);
        }
    }

    private static CellInfo convertCellInfo(org.eclipse.daanse.xmla.api.mddataset.CellInfo cellInfo) {
        if (cellInfo != null) {
            CellInfo res = new CellInfo();
            res.setAny(convertElementList(cellInfo.any()));
            return res;
        }
        return null;
    }

    private static AxesInfo convertAxesInfo(org.eclipse.daanse.xmla.api.mddataset.AxesInfo axesInfo) {
        if (axesInfo != null) {
            AxesInfo res = new AxesInfo();
            res.setAxisInfo(convertAxisInfoList(axesInfo.axisInfo()));
            return res;
        }
        return null;
    }

    private static List<AxisInfo> convertAxisInfoList(List<org.eclipse.daanse.xmla.api.mddataset.AxisInfo> axisInfoList) {
        if (axisInfoList != null) {
            return axisInfoList.stream().map(MdDataSetConvertor::convertAxisInfo).toList();
        }
        return List.of();
    }

    private static AxisInfo convertAxisInfo(org.eclipse.daanse.xmla.api.mddataset.AxisInfo axisInfo) {
        if (axisInfo != null) {
            AxisInfo res = new AxisInfo();
            res.setName(axisInfo.name());
            res.setHierarchyInfo(convertHierarchyInfoList(axisInfo.hierarchyInfo()));
            return res;
        }
        return null;
    }

    private static List<HierarchyInfo> convertHierarchyInfoList(List<org.eclipse.daanse.xmla.api.mddataset.HierarchyInfo> hierarchyInfoList) {
        if (hierarchyInfoList != null) {
            return hierarchyInfoList.stream().map(MdDataSetConvertor::convertHierarchyInfo).toList();
        }
        return List.of();
    }

    private static HierarchyInfo convertHierarchyInfo(org.eclipse.daanse.xmla.api.mddataset.HierarchyInfo hierarchyInfo) {
        if (hierarchyInfo != null) {
            HierarchyInfo res = new HierarchyInfo();
            res.setAny(convertElementList(hierarchyInfo.any()));
            res.setName(hierarchyInfo.name());
            return res;
        }
        return null;
    }

    private static Axes convertAxes(org.eclipse.daanse.xmla.api.mddataset.Axes axes) {
        if (axes != null) {
            Axes res = new Axes();
            res.setAxis(convertAxisList(axes.axis()));
            return res;
        }
        return null;
    }

    private static List<Axis> convertAxisList(List<org.eclipse.daanse.xmla.api.mddataset.Axis> axisList) {
        if (axisList != null) {
            return axisList.stream().map(MdDataSetConvertor::convertAxis).toList();
        }
        return List.of();
    }

    private static Axis convertAxis(org.eclipse.daanse.xmla.api.mddataset.Axis axis) {
        if (axis != null) {
            Axis res = new Axis();
            res.setName(axis.name());
            res.setSetType(convertSetTypeList(axis.setType()));
            return res;
        }
        return null;
    }

    private static List<Object> convertSetTypeList(List<org.eclipse.daanse.xmla.api.mddataset.Type> setTypeList) {
        if (setTypeList != null) {
            return setTypeList.stream().map(MdDataSetConvertor::convertSetType).toList();
        }
        return List.of();
    }

    private static Object convertSetType(org.eclipse.daanse.xmla.api.mddataset.Type type) {
        if (type != null) {
            if (type instanceof org.eclipse.daanse.xmla.api.mddataset.MembersType mt) {
                MembersType res = new MembersType();
                res.setHierarchy(mt.hierarchy());
                res.setMember(convertMemberTypeList(mt.member()));
                return res;
            }
            if (type instanceof org.eclipse.daanse.xmla.api.mddataset.TuplesType tt) {
                TuplesType res = new TuplesType();
                res.setTuple(convertTupleTypeList(tt.tuple()));
                return res;
            }
            if (type instanceof org.eclipse.daanse.xmla.api.mddataset.SetListType st) {
                SetListType res = new SetListType();
                res.setSize(st.size());
                res.setSetType(convertSetTypeList(st.setType()));
                return res;
            }
            if (type instanceof org.eclipse.daanse.xmla.api.mddataset.NormTupleSet nts) {
                NormTupleSet res = new NormTupleSet();
                res.setMembersLookup(convertMembersLookup(nts.membersLookup()));
                res.setNormTuples(convertNormTuplesType(nts.normTuples()));
                return res;
            }
            if (type instanceof org.eclipse.daanse.xmla.api.mddataset.Union u) {
                Union res = new Union();
                res.setSetType(convertSetTypeList(u.setType()));
                return res;
            }
        }
        return null;
    }

    private static NormTuplesType convertNormTuplesType(org.eclipse.daanse.xmla.api.msxmla.NormTuplesType normTuplesType) {
        if (normTuplesType != null) {
            NormTuplesType res = new NormTuplesType();
            res.setNormTuple(convertNormTupleList(normTuplesType.normTuple()));
            return res;
        }
        return null;
    }

    private static List<NormTuplesType.NormTuple> convertNormTupleList(List<NormTuple> normTupleList) {
        if (normTupleList != null) {
            return normTupleList.stream().map(MdDataSetConvertor::convertNormTuple).toList();
        }
        return List.of();
    }

    private static NormTuplesType.NormTuple convertNormTuple(NormTuple normTuple) {
        if (normTuple != null) {
            NormTuplesType.NormTuple res = new NormTuplesType.NormTuple();
            res.setMemberRef(convertMemberRefList(normTuple.memberRef()));
            return res;
        }
        return null;
    }

    private static List<NormTuplesType.NormTuple.MemberRef> convertMemberRefList(List<MemberRef> memberRefList) {
        if (memberRefList != null) {
            return memberRefList.stream().map(MdDataSetConvertor::convertMemberRef).toList();
        }
        return List.of();
    }

    private static NormTuplesType.NormTuple.MemberRef convertMemberRef(MemberRef memberRef) {
        if (memberRef != null) {
            NormTuplesType.NormTuple.MemberRef res = new NormTuplesType.NormTuple.MemberRef();
            res.setMemberDispInfo(memberRef.memberDispInfo());
            res.setMemberOrdinal(memberRef.memberOrdinal());
            return res;
        }
        return null;
    }

    private static List<TupleType> convertMembersLookup(MembersLookup membersLookup) {
        if (membersLookup != null) {
            return convertTupleTypeList(membersLookup.members());
        }
        return List.of();
    }

    private static List<TupleType> convertTupleTypeList(List<org.eclipse.daanse.xmla.api.mddataset.TupleType> tupleTypeList) {
        if (tupleTypeList != null) {
            return tupleTypeList.stream().map(MdDataSetConvertor::convertTupleType).toList();
        }
        return List.of();
    }

    private static TupleType convertTupleType(org.eclipse.daanse.xmla.api.mddataset.TupleType tupleType) {
        if (tupleType != null) {
            TupleType res = new TupleType();
            res.setMember(convertMemberTypeList(tupleType.member()));
            return res;
        }
        return null;
    }

    private static List<MemberType> convertMemberTypeList(List<org.eclipse.daanse.xmla.api.mddataset.MemberType> memberTypeList) {
        if (memberTypeList != null) {
            return memberTypeList.stream().map(MdDataSetConvertor::convertMemberType).toList();
        }
        return List.of();
    }

    private static MemberType convertMemberType(org.eclipse.daanse.xmla.api.mddataset.MemberType memberType) {
        if (memberType != null) {
            MemberType res = new MemberType();
            res.setHierarchy(memberType.hierarchy());
            res.setAny(convertElementList(memberType.any()));
            return res;
        }
        return null;
    }
}
