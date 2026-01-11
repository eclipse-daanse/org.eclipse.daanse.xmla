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
package org.eclipse.daanse.xmla.server.adapter.soapmessage.discover;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.common.enums.InterfaceNameEnum;
import org.eclipse.daanse.xmla.api.common.enums.OriginEnum;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.ParameterInfo;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.functions.MdSchemaFunctionsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.functions.MdSchemaFunctionsRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class MdSchemaFunctionsHandler implements DiscoverHandler {

    private final DiscoverService discoverService;

    public MdSchemaFunctionsHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        MdSchemaFunctionsRestrictionsR restrictions = parseRestrictions(restrictionElement);
        MdSchemaFunctionsRequest request = new MdSchemaFunctionsRequestR(properties, restrictions);
        List<MdSchemaFunctionsResponseRow> rows = discoverService.mdSchemaFunctions(request, metaData);
        writeResponse(rows, responseBody);
    }

    private MdSchemaFunctionsRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new MdSchemaFunctionsRestrictionsR(Optional.ofNullable(m.get(ROW.FUNCTION_NAME)),
                Optional.ofNullable(OriginEnum.fromValue(m.get(ROW.ORIGIN))),
                Optional.ofNullable(InterfaceNameEnum.fromValue(m.get(ROW.INTERFACE_NAME))),
                Optional.ofNullable(m.get(ROW.LIBRARY_NAME)));
    }

    private void writeResponse(List<MdSchemaFunctionsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (MdSchemaFunctionsResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);
        SOAPElement schema = SoapUtil.fillRoot(seRoot);

        SOAPElement s = SoapUtil.prepareSequenceElement(schema);
        SoapUtil.addElement(s, "FUNCTION_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "DESCRIPTION", "xsd:string", "0");
        SoapUtil.addElement(s, "PARAMETER_LIST", "xsd:string", "0");
        SoapUtil.addElement(s, "RETURN_TYPE", "xsd:int", null);
        SoapUtil.addElement(s, "ORIGIN", "xsd:int", null);
        SoapUtil.addElement(s, "INTERFACE_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "LIBRARY_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "CAPTION", "xsd:string", "0");
        return seRoot;
    }

    @SuppressWarnings("deprecation")
    private void addResponseRow(SOAPElement root, MdSchemaFunctionsResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        r.functionalName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_FUNCTION_NAME, v));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION, v));
        SoapUtil.addChildElement(row, ROW.QN_PARAMETER_LIST, r.parameterList());
        r.returnType().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_RETURN_TYPE, String.valueOf(v)));
        r.origin().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_ORIGIN, String.valueOf(v.getValue())));
        r.interfaceName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_INTERFACE_NAME, v.name()));
        r.libraryName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LIBRARY_NAME, v));
        r.dllName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DLL_NAME, v));
        r.helpFile().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_HELP_FILE, v));
        r.helpContext().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_HELP_CONTEXT, v));
        r.object().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_OBJECT, v));
        r.caption().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CAPTION, v));
        r.parameterInfo().ifPresent(v -> addParameterInfoList(row, v));
        r.directQueryPushable().ifPresent(
                v -> SoapUtil.addChildElement(row, ROW.QN_DIRECTQUERY_PUSHABLE, String.valueOf(v.getValue())));
    }

    private void addParameterInfoList(SOAPElement root, List<ParameterInfo> list) {
        if (list != null) {
            list.forEach(it -> addParameterInfo(root, it));
        }
    }

    private void addParameterInfo(SOAPElement root, ParameterInfo it) {
        SOAPElement el = SoapUtil.addChildElement(root, ROW.QN_PARAMETERINFO);
        SoapUtil.addChildElement(el, ROW.QN_NAME_LC, it.name());
        SoapUtil.addChildElement(el, ROW.QN_DESCRIPTION_LC, it.description());
        SoapUtil.addChildElement(el, ROW.QN_OPTIONAL, String.valueOf(it.optional()));
        SoapUtil.addChildElement(el, ROW.QN_REPEATABLE, String.valueOf(it.repeatable()));
        SoapUtil.addChildElement(el, ROW.QN_REPEATGROUP, String.valueOf(it.repeatGroup()));
    }
}
