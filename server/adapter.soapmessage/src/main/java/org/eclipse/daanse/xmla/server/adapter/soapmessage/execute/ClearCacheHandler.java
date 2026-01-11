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

import java.util.List;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.UserRolePrincipal;
import org.eclipse.daanse.xmla.api.execute.ExecuteParameter;
import org.eclipse.daanse.xmla.api.execute.ExecuteService;
import org.eclipse.daanse.xmla.api.execute.clearcache.ClearCacheRequest;
import org.eclipse.daanse.xmla.api.execute.clearcache.ClearCacheResponse;
import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.execute.clearcache.ClearCacheRequestR;
import org.eclipse.daanse.xmla.model.record.xmla.ClearCacheR;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class ClearCacheHandler implements ExecuteHandler {

    private final ExecuteService executeService;

    public ClearCacheHandler(ExecuteService executeService) {
        this.executeService = executeService;
    }

    @Override
    public void handle(Command command, PropertiesR properties, List<ExecuteParameter> parameters,
            RequestMetaData metaData, UserRolePrincipal userPrincipal, SOAPBody responseBody) throws SOAPException {
        if (!(command instanceof ClearCacheR clearCache)) {
            return;
        }
        ClearCacheRequest request = new ClearCacheRequestR(properties, parameters, clearCache);
        ClearCacheResponse response = executeService.clearCache(request, metaData, userPrincipal);
        writeResponse(response, responseBody);
    }

    private void writeResponse(ClearCacheResponse response, SOAPBody body) throws SOAPException {
        SOAPElement root = ExecuteResponseUtil.addEmptyRoot(body);
        if (response != null) {
            ExecuteResponseUtil.addEmptyresult(root, response.emptyresult());
        }
    }
}
