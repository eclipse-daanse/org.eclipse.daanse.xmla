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

import java.util.List;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.UserRolePrincipal;
import org.eclipse.daanse.xmla.api.execute.ExecuteParameter;
import org.eclipse.daanse.xmla.api.execute.ExecuteService;
import org.eclipse.daanse.xmla.api.execute.cancel.CancelRequest;
import org.eclipse.daanse.xmla.api.execute.cancel.CancelResponse;
import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.execute.cancel.CancelRequestR;
import org.eclipse.daanse.xmla.model.record.xmla.CancelR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.ExecuteHandler;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.ExecuteResponseUtil;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class CancelHandler implements ExecuteHandler {

    private final ExecuteService executeService;

    public CancelHandler(ExecuteService executeService) {
        this.executeService = executeService;
    }

    @Override
    public void handle(Command command, PropertiesR properties, List<ExecuteParameter> parameters,
            RequestMetaData metaData, UserRolePrincipal userPrincipal, SOAPBody responseBody) throws SOAPException {
        if (!(command instanceof CancelR cancel)) {
            return;
        }
        CancelRequest request = new CancelRequestR(properties, parameters, cancel);
        CancelResponse response = executeService.cancel(request, metaData, userPrincipal);
        writeResponse(response, responseBody);
    }

    private void writeResponse(CancelResponse response, SOAPBody body) throws SOAPException {
        SOAPElement root = ExecuteResponseUtil.addEmptyRoot(body);
        if (response != null) {
            ExecuteResponseUtil.addEmptyresult(root, response.emptyresult());
        }
    }
}
