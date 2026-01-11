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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.UserRolePrincipal;
import org.eclipse.daanse.xmla.api.execute.ExecuteParameter;
import org.eclipse.daanse.xmla.api.execute.ExecuteService;
import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.execute.ExecuteParameterR;
import org.eclipse.daanse.xmla.model.record.xmla.AlterR;
import org.eclipse.daanse.xmla.model.record.xmla.CancelR;
import org.eclipse.daanse.xmla.model.record.xmla.ClearCacheR;
import org.eclipse.daanse.xmla.model.record.xmla.StatementR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.PropertyConverter;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaParseException;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter.CancelHandler;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter.CommandConverter;

import jakarta.xml.soap.Node;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

/**
 * Dispatcher for XMLA Execute operations. Routes Execute requests to the
 * appropriate handler based on command type.
 */
public class ExecuteDispatcher {

    private final Map<Class<? extends Command>, ExecuteHandler> handlers;

    public ExecuteDispatcher(ExecuteService executeService) {
        this.handlers = initHandlers(executeService);
    }

    /**
     * Dispatch an Execute SOAP element to the appropriate handler.
     *
     * @param executeElement the Execute SOAP element
     * @param responseBody   the response body to write to
     * @param metaData       request metadata
     * @param userPrincipal  user principal information
     * @throws SOAPException if SOAP processing fails
     */
    public void dispatch(SOAPElement executeElement, SOAPBody responseBody, RequestMetaData metaData,
            UserRolePrincipal userPrincipal) throws SOAPException {

        Command command = null;
        PropertiesR properties = null;
        List<ExecuteParameter> parameters = null;

        Iterator<Node> nodeIterator = executeElement.getChildElements();
        while (nodeIterator.hasNext()) {
            Node node = nodeIterator.next();
            if (node instanceof SOAPElement element) {
                if (properties == null && Constants.MSXMLA.QN_PROPERTIES.equals(element.getElementQName())) {
                    properties = PropertyConverter.propertiestoProperties(element);
                }
                if (parameters == null && Constants.MSXMLA.QN_PARAMETERS.equals(element.getElementQName())) {
                    parameters = parametersToParameters(element);
                }
                if (command == null && Constants.MSXMLA.QN_COMMAND.equals(element.getElementQName())) {
                    command = CommandConverter.commandToCommand(element);
                }
            }
        }

        ExecuteHandler handler = handlers.get(command.getClass());
        if (handler == null) {
            throw new XmlaParseException("Unsupported command type: " + command.getClass());
        }
        handler.handle(command, properties, parameters, metaData, userPrincipal, responseBody);
    }

    private Map<Class<? extends Command>, ExecuteHandler> initHandlers(ExecuteService es) {
        Map<Class<? extends Command>, ExecuteHandler> map = new HashMap<>();
        map.put(StatementR.class, new StatementHandler(es));
        map.put(AlterR.class, new AlterHandler(es));
        map.put(ClearCacheR.class, new ClearCacheHandler(es));
        map.put(CancelR.class, new CancelHandler(es));
        return map;
    }

    private List<ExecuteParameter> parametersToParameters(SOAPElement parametersElement) {
        List<ExecuteParameter> parameters = new ArrayList<>();

        Iterator<Node> nodeIteratorParameterList = parametersElement.getChildElements();
        while (nodeIteratorParameterList.hasNext()) {
            Node n = nodeIteratorParameterList.next();

            if (n instanceof SOAPElement parameterElement) {
                Iterator<Node> parameterList = parameterElement.getChildElements();
                String name = null;
                String value = null;
                while (parameterList.hasNext()) {
                    Node n1 = parameterList.next();
                    if (n1 instanceof SOAPElement pElement) {
                        if ("name".equalsIgnoreCase(pElement.getLocalName())) {
                            name = pElement.getTextContent();
                        }
                        if ("value".equalsIgnoreCase(pElement.getLocalName())) {
                            value = pElement.getTextContent();
                        }
                    }
                }
                if (name != null && value != null) {
                    parameters.add(new ExecuteParameterR(name, value));
                }
            }
        }
        return parameters;
    }
}
