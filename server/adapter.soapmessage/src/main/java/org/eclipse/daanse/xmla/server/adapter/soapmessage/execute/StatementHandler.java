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
package org.eclipse.daanse.xmla.server.adapter.soapmessage.execute;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.UserRolePrincipal;
import org.eclipse.daanse.xmla.api.common.enums.ItemTypeEnum;
import org.eclipse.daanse.xmla.api.execute.ExecuteParameter;
import org.eclipse.daanse.xmla.api.execute.ExecuteService;
import org.eclipse.daanse.xmla.api.execute.statement.StatementRequest;
import org.eclipse.daanse.xmla.api.execute.statement.StatementResponse;
import org.eclipse.daanse.xmla.api.mddataset.Axes;
import org.eclipse.daanse.xmla.api.mddataset.AxesInfo;
import org.eclipse.daanse.xmla.api.mddataset.Axis;
import org.eclipse.daanse.xmla.api.mddataset.AxisInfo;
import org.eclipse.daanse.xmla.api.mddataset.CellData;
import org.eclipse.daanse.xmla.api.mddataset.CellInfo;
import org.eclipse.daanse.xmla.api.mddataset.CellInfoItem;
import org.eclipse.daanse.xmla.api.mddataset.CellType;
import org.eclipse.daanse.xmla.api.mddataset.CellTypeError;
import org.eclipse.daanse.xmla.api.mddataset.CubeInfo;
import org.eclipse.daanse.xmla.api.mddataset.HierarchyInfo;
import org.eclipse.daanse.xmla.api.mddataset.Mddataset;
import org.eclipse.daanse.xmla.api.mddataset.MemberType;
import org.eclipse.daanse.xmla.api.mddataset.MembersType;
import org.eclipse.daanse.xmla.api.mddataset.NormTupleSet;
import org.eclipse.daanse.xmla.api.mddataset.OlapInfo;
import org.eclipse.daanse.xmla.api.mddataset.OlapInfoCube;
import org.eclipse.daanse.xmla.api.mddataset.RowSet;
import org.eclipse.daanse.xmla.api.mddataset.RowSetRow;
import org.eclipse.daanse.xmla.api.mddataset.RowSetRowItem;
import org.eclipse.daanse.xmla.api.mddataset.SetListType;
import org.eclipse.daanse.xmla.api.mddataset.TupleType;
import org.eclipse.daanse.xmla.api.mddataset.TuplesType;
import org.eclipse.daanse.xmla.api.mddataset.Type;
import org.eclipse.daanse.xmla.api.mddataset.Union;
import org.eclipse.daanse.xmla.api.mddataset.Value;
import org.eclipse.daanse.xmla.api.msxmla.MemberRef;
import org.eclipse.daanse.xmla.api.msxmla.MembersLookup;
import org.eclipse.daanse.xmla.api.msxmla.NormTuple;
import org.eclipse.daanse.xmla.api.msxmla.NormTuplesType;
import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.execute.statement.StatementRequestR;
import org.eclipse.daanse.xmla.model.record.xmla.StatementR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaSoapException;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.ExecuteConstants.MDDATASET;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class StatementHandler implements ExecuteHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatementHandler.class);
    private static final String UUID_VALUE = "[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}";
    private static final String QN_MEMBER_ORDINAL = "MemberOrdinal";
    private static final String QN_MEMBER_DISP_INFO = "MemberDispInfo";
    private static final String QN_NORM_TUPLES = "NormTuples";
    private static final String QN_NORM_TUPLE = "NormTuple";
    private static final String QN_MEMBER_REF = "MemberRef";
    private static final String QN_MEMBERS_LOOKUP = "MembersLookup";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    private final ExecuteService executeService;

    public StatementHandler(ExecuteService executeService) {
        this.executeService = executeService;
    }

    @Override
    public void handle(Command command, PropertiesR properties, List<ExecuteParameter> parameters,
            RequestMetaData metaData, UserRolePrincipal userPrincipal, SOAPBody responseBody) throws SOAPException {
        if (!(command instanceof StatementR statement)) {
            return;
        }
        String sessionId = metaData != null && metaData.sessionId() != null && metaData.sessionId().isPresent()
                ? metaData.sessionId().get()
                : null;
        StatementRequest request = new StatementRequestR(properties, parameters, statement, sessionId);
        StatementResponse response = executeService.statement(request, metaData, userPrincipal);
        writeResponse(response, responseBody);
    }

    private void writeResponse(StatementResponse response, SOAPBody body) throws SOAPException {
        if (response != null && response.mdDataSet() != null) {
            SOAPElement root = addMddatasetRoot(body);
            addMdDataSet(root, response.mdDataSet());
        }
        if (response.rowSet() != null) {
            SOAPElement root = addRowSetRoot(body, response.rowSet());
            List<RowSetRow> rowSetRows = response.rowSet().rowSetRows();
            if (rowSetRows != null) {
                for (RowSetRow rowSetRow : rowSetRows) {
                    addRowSetRow(root, rowSetRow);
                }
            }
        }
        if (response.mdDataSet() == null && response.rowSet() == null) {
            ExecuteResponseUtil.addEmptyRoot(body);
        }
    }

    private SOAPElement addMddatasetRoot(SOAPElement body) throws SOAPException {
        SOAPElement seExecuteResponse = body.addChildElement(Constants.MSXMLA.QN_EXECUTE_RESPONSE);
        SOAPElement seReturn = seExecuteResponse.addChildElement(Constants.MSXMLA.QN_RETURN);
        SOAPElement seRoot = seReturn.addChildElement(MDDATASET.QN_ROOT);
        seRoot.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        seRoot.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        seRoot.setAttribute("xmlns:EX", Constants.EX.NS_URN);
        addMddatasetSchema(seRoot);
        return seRoot;
    }

    private void addMddatasetSchema(SOAPElement root) {
        SOAPElement schema = addChildElement(root, Constants.XSD.QN_SCHEMA);
        schema.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        schema.setAttribute("targetNamespace", MDDATASET.NS_URN);
        schema.setAttribute("xmlns", MDDATASET.NS_URN);
        schema.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        schema.setAttribute("xmlns:sql", Constants.SQL.NS_URN);
        schema.setAttribute("elementFormDefault", "qualified");
        SOAPElement ct1 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct1.setAttribute("name", "MemberType");
        SOAPElement ct1Sequence = addChildElement(ct1, Constants.XSD.QN_SEQUENCE);
        SOAPElement e1 = addChildElement(ct1Sequence, Constants.XSD.QN_ELEMENT);
        e1.setAttribute("name", "UName");
        e1.setAttribute("type", "xsd:string");
        SOAPElement e2 = addChildElement(ct1Sequence, Constants.XSD.QN_ELEMENT);
        e2.setAttribute("name", "Caption");
        e2.setAttribute("type", "xsd:string");
        SOAPElement e3 = addChildElement(ct1Sequence, Constants.XSD.QN_ELEMENT);
        e3.setAttribute("name", "LName");
        e3.setAttribute("type", "xsd:string");
        SOAPElement e4 = addChildElement(ct1Sequence, Constants.XSD.QN_ELEMENT);
        e4.setAttribute("name", "LNum");
        e4.setAttribute("type", "xsd:unsignedInt");
        SOAPElement e5 = addChildElement(ct1Sequence, Constants.XSD.QN_ELEMENT);
        e5.setAttribute("name", "DisplayInfo");
        e5.setAttribute("type", "xsd:unsignedInt");
        SOAPElement s = addChildElement(ct1Sequence, Constants.XSD.QN_SEQUENCE);
        s.setAttribute("maxOccurs", "unbounded");
        s.setAttribute("minOccurs", "0");
        SOAPElement any = addChildElement(s, Constants.XSD.QN_ANY);
        any.setAttribute("processContents", "lax");
        any.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct1Attribute = addChildElement(ct1, Constants.XSD.QN_ATTRIBUTE);
        ct1Attribute.setAttribute("name", "Hierarchy");
        ct1Attribute.setAttribute("type", "xsd:string");

        SOAPElement ct2 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct2.setAttribute("name", "PropType");
        SOAPElement ct2Attribute = addChildElement(ct2, Constants.XSD.QN_ATTRIBUTE);
        ct2Attribute.setAttribute("name", "name");
        ct2Attribute.setAttribute("type", "xsd:string");

        SOAPElement ct3 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct3.setAttribute("name", "TupleType");
        SOAPElement ct3Sequence = addChildElement(ct3, Constants.XSD.QN_SEQUENCE);
        ct3Sequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct3SequenceElement = addChildElement(ct3Sequence, Constants.XSD.QN_ELEMENT);
        ct3SequenceElement.setAttribute("name", "Member");
        ct3SequenceElement.setAttribute("type", "MemberType");

        SOAPElement ct4 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct4.setAttribute("name", "MembersType");
        SOAPElement ct4Sequence = addChildElement(ct4, Constants.XSD.QN_SEQUENCE);
        ct4Sequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct4SequenceElement = addChildElement(ct4Sequence, Constants.XSD.QN_ELEMENT);
        ct4SequenceElement.setAttribute("name", "Member");
        ct4SequenceElement.setAttribute("type", "MemberType");
        SOAPElement ct4Attribute = addChildElement(ct4, Constants.XSD.QN_ATTRIBUTE);
        ct4Attribute.setAttribute("name", "Hierarchy");
        ct4Attribute.setAttribute("type", "xsd:string");

        SOAPElement ct5 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct5.setAttribute("name", "TuplesType");
        SOAPElement ct5Sequence = addChildElement(ct5, Constants.XSD.QN_SEQUENCE);
        ct5Sequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct5SequenceElement = addChildElement(ct5Sequence, Constants.XSD.QN_ELEMENT);
        ct5SequenceElement.setAttribute("name", "Tuple");
        ct5SequenceElement.setAttribute("type", "TupleType");

        SOAPElement ct6 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct6.setAttribute("name", "CrossProductType");
        SOAPElement ct6Sequence = addChildElement(ct6, Constants.XSD.QN_SEQUENCE);
        SOAPElement ct6SequenceChoice = addChildElement(ct6Sequence, Constants.XSD.QN_CHOICE);
        ct6SequenceChoice.setAttribute("minOccurs", "0");
        ct6SequenceChoice.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct6SequenceChoiceE1 = addChildElement(ct6SequenceChoice, Constants.XSD.QN_ELEMENT);
        ct6SequenceChoiceE1.setAttribute("name", "Members");
        ct6SequenceChoiceE1.setAttribute("type", "MembersType");
        SOAPElement ct6SequenceChoiceE2 = addChildElement(ct6SequenceChoice, Constants.XSD.QN_ELEMENT);
        ct6SequenceChoiceE2.setAttribute("name", "Tuples");
        ct6SequenceChoiceE2.setAttribute("type", "TuplesType");
        SOAPElement ct6Attribute = addChildElement(ct6, Constants.XSD.QN_ATTRIBUTE);
        ct6Attribute.setAttribute("name", "Size");
        ct6Attribute.setAttribute("type", "xsd:unsignedInt");

        SOAPElement ct7 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct7.setAttribute("name", "OlapInfo");
        SOAPElement ct7Sequence = addChildElement(ct7, Constants.XSD.QN_SEQUENCE);
        SOAPElement ct7SequenceElement1 = addChildElement(ct7Sequence, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement1.setAttribute("name", "CubeInfo");

        SOAPElement ct7SequenceElement1Ct = addChildElement(ct7SequenceElement1, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct7SequenceElement1CtSequence = addChildElement(ct7SequenceElement1Ct, Constants.XSD.QN_SEQUENCE);
        SOAPElement ct7SequenceElement1CtSequenceEl = addChildElement(ct7SequenceElement1CtSequence,
                Constants.XSD.QN_ELEMENT);
        ct7SequenceElement1CtSequenceEl.setAttribute("name", "Cube");
        ct7SequenceElement1CtSequenceEl.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement1CtSequenceElCt = addChildElement(ct7SequenceElement1CtSequenceEl,
                Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct7SequenceElement1CtSequenceElCtSequence = addChildElement(ct7SequenceElement1CtSequenceElCt,
                Constants.XSD.QN_SEQUENCE);
        SOAPElement ct7SequenceElement1CtSequenceElCtSequenceEl = addChildElement(
                ct7SequenceElement1CtSequenceElCtSequence, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement1CtSequenceElCtSequenceEl.setAttribute("name", "CubeName");
        ct7SequenceElement1CtSequenceElCtSequenceEl.setAttribute("type", "xsd:string");

        SOAPElement ct7SequenceElement2 = addChildElement(ct7Sequence, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2.setAttribute("name", "AxesInfo");
        SOAPElement ct7SequenceElement2Ct = addChildElement(ct7SequenceElement2, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct7SequenceElement2CtSequence = addChildElement(ct7SequenceElement2Ct, Constants.XSD.QN_SEQUENCE);
        SOAPElement ct7SequenceElement2CtSequenceEl = addChildElement(ct7SequenceElement2CtSequence,
                Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceEl.setAttribute("name", "AxisInfo");
        ct7SequenceElement2CtSequenceEl.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceElCt = addChildElement(ct7SequenceElement2CtSequenceEl,
                Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct7SequenceElement2CtSequenceElCtSequence = addChildElement(ct7SequenceElement2CtSequenceElCt,
                Constants.XSD.QN_SEQUENCE);

        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceElement = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequence, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceElCtSequenceElement.setAttribute("name", "HierarchyInfo");
        ct7SequenceElement2CtSequenceElCtSequenceElement.setAttribute("minOccurs", "0");
        ct7SequenceElement2CtSequenceElCtSequenceElement.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceElementCt = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceElement, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceElementCtSequence = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceElementCt, Constants.XSD.QN_SEQUENCE);

        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1 = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceElementCtSequence, Constants.XSD.QN_SEQUENCE);
        ct7SequenceElement2CtSequenceElCtSequenceSequence1.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1E1 = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceSequence1, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E1.setAttribute("name", "UName");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E1.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1E2 = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceSequence1, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E2.setAttribute("name", "Caption");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E2.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1E3 = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceSequence1, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E3.setAttribute("name", "LName");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E3.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1E4 = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceSequence1, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E4.setAttribute("name", "LNum");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E4.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1E5 = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceSequence1, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E5.setAttribute("name", "DisplayInfo");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E5.setAttribute("type", "PropType");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E5.setAttribute("minOccurs", "0");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E5.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence2 = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceElementCtSequence, Constants.XSD.QN_SEQUENCE);
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence2Any = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceSequence2, Constants.XSD.QN_ANY);
        ct7SequenceElement2CtSequenceElCtSequenceSequence2Any.setAttribute("processContents", "lax");
        ct7SequenceElement2CtSequenceElCtSequenceSequence2Any.setAttribute("minOccurs", "0");
        ct7SequenceElement2CtSequenceElCtSequenceSequence2Any.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceElementCtAttribute = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceElementCt, Constants.XSD.QN_ATTRIBUTE);
        ct7SequenceElement2CtSequenceElCtSequenceElementCtAttribute.setAttribute("name", "name");
        ct7SequenceElement2CtSequenceElCtSequenceElementCtAttribute.setAttribute("type", "xsd:string");
        ct7SequenceElement2CtSequenceElCtSequenceElementCtAttribute.setAttribute("use", "required");
        SOAPElement ct7SequenceElement2CtAttribute = addChildElement(ct7SequenceElement2CtSequenceElCt,
                Constants.XSD.QN_ATTRIBUTE);
        ct7SequenceElement2CtAttribute.setAttribute("name", "name");
        ct7SequenceElement2CtAttribute.setAttribute("type", "xsd:string");

        SOAPElement ct7SequenceElement3 = addChildElement(ct7Sequence, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement3.setAttribute("name", "CellInfo");
        SOAPElement ct7SequenceElement3Ct = addChildElement(ct7SequenceElement3, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct7SequenceElement3CtSequence = addChildElement(ct7SequenceElement3Ct, Constants.XSD.QN_SEQUENCE);
        SOAPElement ct7SequenceElement2CtSequenceSequence1 = addChildElement(ct7SequenceElement3CtSequence,
                Constants.XSD.QN_SEQUENCE);
        ct7SequenceElement2CtSequenceSequence1.setAttribute("minOccurs", "0");
        ct7SequenceElement2CtSequenceSequence1.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceSequence1Ch = addChildElement(ct7SequenceElement2CtSequenceSequence1,
                Constants.XSD.QN_CHOICE);
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE1 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE1.setAttribute("name", "Value");
        ct7SequenceElement2CtSequenceSequence1ChE1.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE2 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE2.setAttribute("name", "FmtValue");
        ct7SequenceElement2CtSequenceSequence1ChE2.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE3 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE3.setAttribute("name", "BackColor");
        ct7SequenceElement2CtSequenceSequence1ChE3.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE4 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE4.setAttribute("name", "ForeColor");
        ct7SequenceElement2CtSequenceSequence1ChE4.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE5 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE5.setAttribute("name", "FontName");
        ct7SequenceElement2CtSequenceSequence1ChE5.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE6 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE6.setAttribute("name", "FontSize");
        ct7SequenceElement2CtSequenceSequence1ChE6.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE7 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE7.setAttribute("name", "FontFlags");
        ct7SequenceElement2CtSequenceSequence1ChE7.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE8 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE8.setAttribute("name", "FormatString");
        ct7SequenceElement2CtSequenceSequence1ChE8.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE9 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE9.setAttribute("name", "NonEmptyBehavior");
        ct7SequenceElement2CtSequenceSequence1ChE9.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE10 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE10.setAttribute("name", "SolveOrder");
        ct7SequenceElement2CtSequenceSequence1ChE10.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE11 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE11.setAttribute("name", "Updateable");
        ct7SequenceElement2CtSequenceSequence1ChE11.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE12 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE12.setAttribute("name", "Visible");
        ct7SequenceElement2CtSequenceSequence1ChE12.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE13 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE13.setAttribute("name", "Expression");
        ct7SequenceElement2CtSequenceSequence1ChE13.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence2 = addChildElement(ct7SequenceElement3CtSequence,
                Constants.XSD.QN_SEQUENCE);
        ct7SequenceElement2CtSequenceSequence2.setAttribute("maxOccurs", "unbounded");
        ct7SequenceElement2CtSequenceSequence2.setAttribute("minOccurs", "0");
        SOAPElement ct7SequenceElement2CtSequenceSequence2Any = addChildElement(ct7SequenceElement2CtSequenceSequence2,
                Constants.XSD.QN_ANY);
        ct7SequenceElement2CtSequenceSequence2Any.setAttribute("processContents", "lax");
        ct7SequenceElement2CtSequenceSequence2Any.setAttribute("maxOccurs", "unbounded");

        SOAPElement ct8 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct8.setAttribute("name", "Axes");
        SOAPElement ct8Sequence = addChildElement(ct8, Constants.XSD.QN_SEQUENCE);
        ct8Sequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct8SequenceElement = addChildElement(ct8Sequence, Constants.XSD.QN_ELEMENT);
        ct8SequenceElement.setAttribute("name", "Axis");
        SOAPElement ct8SequenceElementComplexType = addChildElement(ct8SequenceElement, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct8SequenceElementComplexTypeChoice = addChildElement(ct8SequenceElementComplexType,
                Constants.XSD.QN_CHOICE);
        ct8SequenceElementComplexTypeChoice.setAttribute("minOccurs", "0");
        ct8SequenceElementComplexTypeChoice.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct8SequenceElementComplexTypeChoiceE1 = addChildElement(ct8SequenceElementComplexTypeChoice,
                Constants.XSD.QN_ELEMENT);
        ct8SequenceElementComplexTypeChoiceE1.setAttribute("name", "CrossProduct");
        ct8SequenceElementComplexTypeChoiceE1.setAttribute("type", "CrossProductType");
        SOAPElement ct8SequenceElementComplexTypeChoiceE2 = addChildElement(ct8SequenceElementComplexTypeChoice,
                Constants.XSD.QN_ELEMENT);
        ct8SequenceElementComplexTypeChoiceE2.setAttribute("name", "Tuples");
        ct8SequenceElementComplexTypeChoiceE2.setAttribute("type", "TuplesType");
        SOAPElement ct8SequenceElementComplexTypeChoiceE3 = addChildElement(ct8SequenceElementComplexTypeChoice,
                Constants.XSD.QN_ELEMENT);
        ct8SequenceElementComplexTypeChoiceE3.setAttribute("name", "Members");
        ct8SequenceElementComplexTypeChoiceE3.setAttribute("type", "MembersType");
        SOAPElement ct8SequenceElementComplexTypeAttribute = addChildElement(ct8SequenceElementComplexType,
                Constants.XSD.QN_ATTRIBUTE);
        ct8SequenceElementComplexTypeAttribute.setAttribute("name", "name");
        ct8SequenceElementComplexTypeAttribute.setAttribute("type", "xsd:string");

        SOAPElement ct9 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct9.setAttribute("name", "CellData");
        SOAPElement ct9Sequence = addChildElement(ct9, Constants.XSD.QN_SEQUENCE);
        SOAPElement ct9SequenceElement = addChildElement(ct9Sequence, Constants.XSD.QN_ELEMENT);
        ct9SequenceElement.setAttribute("name", "Cell");
        ct9SequenceElement.setAttribute("minOccurs", "0");
        ct9SequenceElement.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct9SequenceElementComplexType = addChildElement(ct9SequenceElement, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct9SequenceElementComplexTypeSequence = addChildElement(ct9SequenceElementComplexType,
                Constants.XSD.QN_SEQUENCE);
        ct9SequenceElementComplexTypeSequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoice = addChildElement(ct9SequenceElementComplexTypeSequence,
                Constants.XSD.QN_CHOICE);
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE1 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE1.setAttribute("name", "Value");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE2 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE2.setAttribute("name", "FmtValue");
        ct9SequenceElementComplexTypeSequenceChoiceE2.setAttribute("type", "xsd:string");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE3 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE3.setAttribute("name", "BackColor");
        ct9SequenceElementComplexTypeSequenceChoiceE3.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE4 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE4.setAttribute("name", "ForeColor");
        ct9SequenceElementComplexTypeSequenceChoiceE4.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE5 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE5.setAttribute("name", "FontName");
        ct9SequenceElementComplexTypeSequenceChoiceE5.setAttribute("type", "xsd:string");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE6 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE6.setAttribute("name", "FontSize");
        ct9SequenceElementComplexTypeSequenceChoiceE6.setAttribute("type", "xsd:unsignedShort");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE7 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE7.setAttribute("name", "FontFlags");
        ct9SequenceElementComplexTypeSequenceChoiceE7.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE8 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE8.setAttribute("name", "FormatString");
        ct9SequenceElementComplexTypeSequenceChoiceE8.setAttribute("type", "xsd:string");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE9 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE9.setAttribute("name", "NonEmptyBehavior");
        ct9SequenceElementComplexTypeSequenceChoiceE9.setAttribute("type", "xsd:unsignedShort");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE10 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE10.setAttribute("name", "SolveOrder");
        ct9SequenceElementComplexTypeSequenceChoiceE10.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE11 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE11.setAttribute("name", "Updateable");
        ct9SequenceElementComplexTypeSequenceChoiceE11.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE12 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE12.setAttribute("name", "Visible");
        ct9SequenceElementComplexTypeSequenceChoiceE12.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE13 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE13.setAttribute("name", "Expression");
        ct9SequenceElementComplexTypeSequenceChoiceE13.setAttribute("type", "xsd:string");
        SOAPElement ct9SequenceElementComplexTypeAttribute = addChildElement(ct9SequenceElementComplexType,
                Constants.XSD.QN_ATTRIBUTE);
        ct9SequenceElementComplexTypeAttribute.setAttribute("name", "CellOrdinal");
        ct9SequenceElementComplexTypeAttribute.setAttribute("type", "xsd:unsignedInt");
        ct9SequenceElementComplexTypeAttribute.setAttribute("use", "required");

        SOAPElement element = addChildElement(schema, Constants.XSD.QN_ELEMENT);
        element.setAttribute("name", "root");
        SOAPElement elementComplexType = addChildElement(element, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement elementComplexTypeSequence = addChildElement(elementComplexType, Constants.XSD.QN_SEQUENCE);
        elementComplexTypeSequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement elementComplexTypeSequenceE1 = addChildElement(elementComplexTypeSequence,
                Constants.XSD.QN_ELEMENT);
        elementComplexTypeSequenceE1.setAttribute("name", "OlapInfo");
        elementComplexTypeSequenceE1.setAttribute("type", "OlapInfo");
        SOAPElement elementComplexTypeSequenceE2 = addChildElement(elementComplexTypeSequence,
                Constants.XSD.QN_ELEMENT);
        elementComplexTypeSequenceE2.setAttribute("name", "Axes");
        elementComplexTypeSequenceE2.setAttribute("type", "Axes");
        SOAPElement elementComplexTypeSequenceE3 = addChildElement(elementComplexTypeSequence,
                Constants.XSD.QN_ELEMENT);
        elementComplexTypeSequenceE3.setAttribute("name", "CellData");
        elementComplexTypeSequenceE3.setAttribute("type", "CellData");
    }

    private void addMdDataSet(SOAPElement el, Mddataset it) throws SOAPException {
        if (it != null) {
            addOlapInfo(el, it.olapInfo());
            addAxes(el, it.axes());
            addCellData(el, it.cellData());
            ExecuteResponseUtil.addException(el, it.exception());
            ExecuteResponseUtil.addMessages(el, it.messages());
        }
    }

    private void addOlapInfo(SOAPElement e, OlapInfo it) throws SOAPException {
        if (it != null) {
            SOAPElement seOlapInfo = e.addChildElement(MDDATASET.QN_OLAP_INFO);
            addCubeInfo(seOlapInfo, it.cubeInfo());
            addAxesInfo(seOlapInfo, it.axesInfo());
            addCellInfo(seOlapInfo, it.cellInfo());
        }
    }

    private void addCubeInfo(SOAPElement e, CubeInfo it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_CUBE_INFO);
            addOlapInfoCubeList(el, it.cube());
        }
    }

    private void addOlapInfoCubeList(SOAPElement e, List<OlapInfoCube> list) {
        if (list != null) {
            list.forEach(it -> addOlapInfoCube(e, it));
        }
    }

    private void addOlapInfoCube(SOAPElement e, OlapInfoCube it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_CUBE);
            addChildElement(el, MDDATASET.QN_CUBE_NAME, it.cubeName());
            addChildElement(el, ExecuteConstants.ENGINE.QN_LAST_DATA_UPDATE, instantToString(it.lastDataUpdate()));
            addChildElement(el, ExecuteConstants.ENGINE.QN_LAST_SCHEMA_UPDATE, instantToString(it.lastSchemaUpdate()));
        }
    }

    private void addAxesInfo(SOAPElement e, AxesInfo it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_AXES_INFO);
            addAxisInfoList(el, it.axisInfo());
        }
    }

    private void addAxisInfoList(SOAPElement el, List<AxisInfo> list) {
        if (list != null) {
            list.forEach(it -> addAxisInfo(el, it));
        }
    }

    private void addAxisInfo(SOAPElement e, AxisInfo it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_AXIS_INFO);
            setAttribute(el, "name", it.name());
            addHierarchyInfoList(el, it.hierarchyInfo());
        }
    }

    private void addHierarchyInfoList(SOAPElement el, List<HierarchyInfo> list) {
        if (list != null) {
            list.forEach(it -> addHierarchyInfo(el, it));
        }
    }

    private void addHierarchyInfo(SOAPElement e, HierarchyInfo it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_HIERARCHY_INFO);
            addCellInfoItemListName(el, it.any());
            setAttribute(el, "name", it.name());
        }
    }

    private void addCellInfo(SOAPElement e, CellInfo it) throws SOAPException {
        if (it != null) {
            SOAPElement seCellInfo = e.addChildElement(MDDATASET.QN_CELL_INFO);
            addCellInfoItemListName(seCellInfo, it.any());
        }
    }

    private void addCellInfoItemListName(SOAPElement e, List<CellInfoItem> list) {
        if (list != null) {
            list.forEach(it -> addCellInfoItemName(e, it));
        }
    }

    private void addCellInfoItemName(SOAPElement e, CellInfoItem it) {
        if (it != null) {
            String prefix = MDDATASET.PREFIX;
            SOAPElement el = addChildElementWithPrefix(e, it.tagName(), prefix);
            setAttribute(el, "name", it.name());
            it.type().ifPresent(v -> setAttribute(el, "type", v));
        }
    }

    private void addCellInfoItemList(SOAPElement e, List<CellInfoItem> list) {
        if (list != null) {
            list.forEach(it -> addCellInfoItem(e, it));
        }
    }

    private void addCellInfoItem(SOAPElement e, CellInfoItem it) {
        if (it != null) {
            String prefix = MDDATASET.PREFIX;
            SOAPElement el = addChildElementWithPrefix(e, it.tagName(), prefix);
            el.setTextContent(it.name());
            it.type().ifPresent(v -> setAttribute(el, "type", v));
        }
    }

    private void addAxes(SOAPElement e, Axes it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_AXES);
            addAxisList(el, it.axis());
        }
    }

    private void addAxisList(SOAPElement e, List<Axis> list) {
        if (list != null) {
            list.forEach(it -> addAxis(e, it));
        }
    }

    private void addAxis(SOAPElement e, Axis it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_AXIS);
            addTypeList(el, it.setType());
            setAttribute(el, "name", it.name());
        }
    }

    private void addTypeList(SOAPElement e, List<Type> list) {
        if (list != null) {
            list.forEach(it -> addType(e, it));
        }
    }

    private void addType(SOAPElement soapElement, Type type) {
        if (type != null) {
            if (type instanceof MembersType membersType) {
                addMembersType(soapElement, membersType);
            }
            if (type instanceof TuplesType tuplesType) {
                addTuplesType(soapElement, tuplesType);
            }
            if (type instanceof SetListType setListType) {
                addSetListType(soapElement, setListType);
            }
            if (type instanceof NormTupleSet normTupleSet) {
                addNormTupleSet(soapElement, normTupleSet);
            }
            if (type instanceof Union union) {
                addUnion(soapElement, union);
            }
        }
    }

    private void addMembersType(SOAPElement e, MembersType it) {
        if (it != null && it.member() != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_MEMBERS);
            it.member().forEach(item -> addMemberType(el, item));
            setAttribute(e, "Hierarchy", it.hierarchy());
        }
    }

    private void addTuplesType(SOAPElement e, TuplesType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_TUPLES);
            addTuplesTypeList(el, it.tuple());
        }
    }

    private void addTuplesTypeList(SOAPElement e, List<TupleType> list) {
        if (list != null) {
            list.forEach(it -> addTupleTypeTuple(e, it));
        }
    }

    private void addTupleTypeTuple(SOAPElement e, TupleType it) {
        if (it != null && it.member() != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_TUPLE);
            it.member().forEach(item -> addMemberType(el, item));
        }
    }

    private void addTupleTypeMembers(SOAPElement e, TupleType it) {
        if (it != null && it.member() != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_MEMBERS);
            it.member().forEach(item -> addMemberType(el, item));
        }
    }

    private void addMemberType(SOAPElement e, MemberType it) {
        if (it != null) {
            SOAPElement seMember = addChildElement(e, MDDATASET.QN_MEMBER);
            addCellInfoItemList(seMember, it.any());
            setAttribute(seMember, "Hierarchy", it.hierarchy());
        }
    }

    private void addSetListType(SOAPElement e, SetListType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_CROSS_PRODUCT);
            addTypeList(el, it.setType());
            addChildElement(el, MDDATASET.QN_SIZE, String.valueOf(it.size()));
        }
    }

    private void addNormTupleSet(SOAPElement soapElement, NormTupleSet normTupleSet) {
        if (normTupleSet != null) {
            SOAPElement el = addChildElement(soapElement, MDDATASET.QN_NORM_TUPLE_SET);
            addNormTuplesType(el, normTupleSet.normTuples());
            addTupleTypeList(el, normTupleSet.membersLookup());
        }
    }

    private void addNormTuplesType(SOAPElement e, NormTuplesType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_NORM_TUPLES);
            addNormTupleList(el, it.normTuple());
        }
    }

    private void addNormTupleList(SOAPElement el, List<NormTuple> list) {
        if (list != null) {
            list.forEach(it -> addNormTuple(el, it));
        }
    }

    private void addNormTuple(SOAPElement e, NormTuple it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_NORM_TUPLE);
            addMemberRefList(el, it.memberRef());
        }
    }

    private void addMemberRefList(SOAPElement e, List<MemberRef> list) {
        if (list != null) {
            list.forEach(it -> addMemberRef(e, it));
        }
    }

    private void addMemberRef(SOAPElement e, MemberRef it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_MEMBER_REF);
            addChildElementWithValue(el, QN_MEMBER_ORDINAL, String.valueOf(it.memberOrdinal()));
            addChildElementWithValue(el, QN_MEMBER_DISP_INFO, String.valueOf(it.memberDispInfo()));
        }
    }

    private void addTupleTypeList(SOAPElement soapElement, MembersLookup membersLookup) {
        if (membersLookup != null) {
            SOAPElement el = addChildElement(soapElement, QN_MEMBERS_LOOKUP);
            if (membersLookup.members() != null) {
                membersLookup.members().forEach(it -> addTupleTypeMembers(el, it));
            }
        }
    }

    private void addUnion(SOAPElement soapElement, Union union) {
        if (union != null) {
            SOAPElement el = addChildElement(soapElement, MDDATASET.QN_UNION);
            addTypeList(el, union.setType());
        }
    }

    private void addCellData(SOAPElement e, CellData it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_CELL_DATA);
            addCellTypeList(el, it.cell());
        }
    }

    private void addCellTypeList(SOAPElement el, List<CellType> list) {
        if (list != null) {
            list.forEach(it -> addCellType(el, it));
        }
    }

    private void addCellType(SOAPElement e, CellType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_CELL);
            addCellTypeValue(el, it.value());
            addCellInfoItemList(el, it.any());
            setAttribute(el, "CellOrdinal", String.valueOf(it.cellOrdinal()));
        }
    }

    private void addCellTypeValue(SOAPElement e, Value it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_VALUE);
            el.setAttribute("xsi:type", it.type().getValue());
            el.setTextContent(it.value());
            addCellTypeErrorList(el, it.error());
        }
    }

    private void addCellTypeErrorList(SOAPElement el, List<CellTypeError> list) {
        if (list != null) {
            list.forEach(it -> addCellTypeError(el, it));
        }
    }

    private void addCellTypeError(SOAPElement e, CellTypeError it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_ERROR);
            setAttribute(el, "ErrorCode", String.valueOf(it.errorCode()));
            setAttribute(el, "Description", it.description());
        }
    }

    private SOAPElement addRowSetRoot(SOAPBody body, RowSet rowSet) throws SOAPException {
        SOAPElement seExecuteResponse = body.addChildElement(Constants.MSXMLA.QN_EXECUTE_RESPONSE);
        SOAPElement seReturn = seExecuteResponse.addChildElement(Constants.MSXMLA.QN_RETURN);
        SOAPElement seRoot = seReturn.addChildElement(DiscoverConstants.ROWSET.QN_ROOT);

        seRoot.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        seRoot.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        seRoot.setAttribute("xmlns:EX", Constants.EX.NS_URN);
        addRowsetSchema(seRoot, rowSet);
        return seRoot;
    }

    private void addRowsetSchema(SOAPElement root, RowSet rowSet) {
        SOAPElement schema = addChildElement(root, Constants.XSD.QN_SCHEMA);
        schema.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        schema.setAttribute("targetNamespace", DiscoverConstants.ROWSET.NS_URN);
        schema.setAttribute("xmlns", DiscoverConstants.ROWSET.NS_URN);
        schema.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        schema.setAttribute("xmlns:sql", Constants.SQL.NS_URN);
        schema.setAttribute("elementFormDefault", "qualified");

        SOAPElement el1 = addChildElement(schema, Constants.XSD.QN_ELEMENT);
        el1.setAttribute("name", "root");
        SOAPElement el1complexType = addChildElement(el1, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement el1complexTypeSequence = addChildElement(el1complexType, Constants.XSD.QN_SEQUENCE);
        SOAPElement el1complexTypeSequenceEl = addChildElement(el1complexTypeSequence, Constants.XSD.QN_ELEMENT);
        el1complexTypeSequenceEl.setAttribute("maxOccurs", "unbounded");
        el1complexTypeSequenceEl.setAttribute("minOccurs", "0");
        el1complexTypeSequenceEl.setAttribute("name", "row");
        el1complexTypeSequenceEl.setAttribute("type", "row");

        SOAPElement simpleType = addChildElement(schema, Constants.XSD.QN_SIMPLE_TYPE);
        simpleType.setAttribute("name", "uuid");
        SOAPElement restriction = addChildElement(simpleType, Constants.XSD.QN_RESTRICTION);
        restriction.setAttribute("base", "xsd:string");
        SOAPElement pattern = addChildElement(restriction, Constants.XSD.QN_PATTERN);
        pattern.setAttribute("value", UUID_VALUE);

        SOAPElement complexType = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        complexType.setAttribute("name", "xmlDocument");
        SOAPElement sequence = addChildElement(complexType, Constants.XSD.QN_SEQUENCE);
        addChildElement(sequence, Constants.XSD.QN_ANY);

        SOAPElement ct = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct.setAttribute("name", "row");
        SOAPElement ctSequence = addChildElement(ct, Constants.XSD.QN_SEQUENCE);
        if (rowSet.rowSetRows() != null && !rowSet.rowSetRows().isEmpty() && rowSet.rowSetRows().get(0) != null
                && rowSet.rowSetRows().get(0).rowSetRowItem() != null) {
            for (RowSetRowItem item : rowSet.rowSetRows().get(0).rowSetRowItem()) {
                SOAPElement ctSequenceEl1 = addChildElement(ctSequence, Constants.XSD.QN_ELEMENT);
                ItemTypeEnum type = item.type().orElse(ItemTypeEnum.STRING);
                ctSequenceEl1.setAttribute("minOccurs", "0");
                ctSequenceEl1.setAttribute("name", item.tagName());
                ctSequenceEl1.setAttribute("sql:field", item.fieldName());
                ctSequenceEl1.setAttribute("type", type.getValue());
            }
        }
    }

    private void addRowSetRow(SOAPElement e, RowSetRow it) throws SOAPException {
        SOAPElement seRow = e.addChildElement(DiscoverConstants.ROWSET.QN_ROW);
        if (it.rowSetRowItem() != null) {
            it.rowSetRowItem().forEach(i -> addRowSetRowItem(seRow, i));
        }
    }

    private void addRowSetRowItem(SOAPElement e, RowSetRowItem it) {
        if (it != null) {
            SOAPElement el = addChildElementWithPrefix(e, it.tagName(), DiscoverConstants.ROWSET.PREFIX);
            el.setTextContent(it.value());
            it.type().ifPresent(v -> setAttribute(el, "type", v.getValue()));
        }
    }

    private String instantToString(Instant instant) {
        return instant != null ? formatter.format(instant) : null;
    }

    private void setAttribute(SOAPElement el, String name, String value) {
        if (value != null) {
            el.setAttribute(name, value);
        }
    }

    private SOAPElement addChildElement(SOAPElement element, QName qNameOfChild, String valueOfChild) {
        try {
            SOAPElement createdChild = element.addChildElement(qNameOfChild);
            createdChild.setTextContent(valueOfChild);
            return createdChild;
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", qNameOfChild);
            throw new XmlaSoapException("addChildElement error", e);
        }
    }

    private SOAPElement addChildElement(SOAPElement element, QName qNameOfChild) {
        try {
            return element.addChildElement(qNameOfChild);
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", qNameOfChild);
            throw new XmlaSoapException("addChildElement error", e);
        }
    }

    private SOAPElement addChildElement(SOAPElement element, String childElementName) {
        try {
            return element.addChildElement(childElementName);
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", childElementName);
            throw new XmlaSoapException("addChildElement error", e);
        }
    }

    private void addChildElementWithValue(SOAPElement element, String childElementName, String value) {
        try {
            if (value != null) {
                element.addChildElement(childElementName).setTextContent(value);
            }
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", childElementName);
            throw new XmlaSoapException("addChildElement error", e);
        }
    }

    private SOAPElement addChildElementWithPrefix(SOAPElement element, String childElementName, String prefix) {
        try {
            if (prefix == null || prefix.isEmpty()) {
                return element.addChildElement(childElementName);
            } else {
                return element.addChildElement(childElementName, prefix);
            }
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", childElementName);
            throw new XmlaSoapException("addChildElement error", e);
        }
    }
}
