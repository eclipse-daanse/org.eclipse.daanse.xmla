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
package org.eclipse.daanse.xmla.client.soapmessage;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import org.eclipse.daanse.xmla.api.discover.Properties;
import org.eclipse.daanse.xmla.api.execute.ExecuteParameter;
import org.eclipse.daanse.xmla.api.execute.alter.AlterRequest;
import org.eclipse.daanse.xmla.api.execute.cancel.CancelRequest;
import org.eclipse.daanse.xmla.api.execute.clearcache.ClearCacheRequest;
import org.eclipse.daanse.xmla.api.execute.statement.StatementRequest;
import org.eclipse.daanse.xmla.api.xmla.Alter;
import org.eclipse.daanse.xmla.api.xmla.Cancel;
import org.eclipse.daanse.xmla.api.xmla.ClearCache;
import org.eclipse.daanse.xmla.api.xmla.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Consumer;

import static org.eclipse.daanse.xmla.client.soapmessage.Constants.COMMAND;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.EXECUTE;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.PROPERTIES;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.PROPERTY_LIST;
import static org.eclipse.daanse.xmla.client.soapmessage.SoapUtil.addChildElementAlter;
import static org.eclipse.daanse.xmla.client.soapmessage.SoapUtil.addChildElementCancel;
import static org.eclipse.daanse.xmla.client.soapmessage.SoapUtil.addChildElementClearCache;
import static org.eclipse.daanse.xmla.client.soapmessage.SoapUtil.addChildElementParameterList;
import static org.eclipse.daanse.xmla.client.soapmessage.SoapUtil.addChildElementPropertyList;

public class ExecuteConsumers {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteConsumers.class);

    private ExecuteConsumers() {
        // constructor
    }

    static Consumer<SOAPMessage> createStatementRequestConsumer(StatementRequest requestApi) {
        return message -> {
            try {
                Statement statement = requestApi.command();
                Properties properties = requestApi.properties();

                SOAPElement execute = message.getSOAPBody().addChildElement(EXECUTE);
                execute.addChildElement(COMMAND).addChildElement("Statement").setTextContent(statement.statement());

                SOAPElement propertyList = execute.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);

            } catch (SOAPException e) {
                LOGGER.error("ExecuteConsumers StatementRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createCancelRequestConsumer(CancelRequest requestApi) {
        return message -> {
            try {
                Cancel cancel = requestApi.command();
                Properties properties = requestApi.properties();
                List<ExecuteParameter> executeParameterList = requestApi.parameters();

                SOAPElement execute = message.getSOAPBody().addChildElement(EXECUTE);
                SOAPElement commandElement = execute.addChildElement(COMMAND);

                addChildElementCancel(commandElement, cancel);

                SOAPElement propertyList = execute.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);
                addChildElementParameterList(execute, executeParameterList);

            } catch (SOAPException e) {
                LOGGER.error("ExecuteConsumers ClearCacheRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createClearCacheRequestConsumer(ClearCacheRequest requestApi) {
        return message -> {
            try {
                ClearCache clearCache = requestApi.command();
                Properties properties = requestApi.properties();
                List<ExecuteParameter> executeParameterList = requestApi.parameters();

                SOAPElement execute = message.getSOAPBody().addChildElement(EXECUTE);
                SOAPElement commandElement = execute.addChildElement(COMMAND);
                addChildElementClearCache(commandElement, clearCache);

                SOAPElement propertyList = execute.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);
                addChildElementParameterList(execute, executeParameterList);

            } catch (SOAPException e) {
                LOGGER.error("ExecuteConsumers ClearCacheRequest accept error", e);
            }
        };
    }

    static Consumer<SOAPMessage> createAlterRequestConsumer(AlterRequest requestApi) {
        return message -> {
            try {
                Alter alter = requestApi.command();
                Properties properties = requestApi.properties();
                List<ExecuteParameter> executeParameterList = requestApi.parameters();

                SOAPElement execute = message.getSOAPBody().addChildElement(EXECUTE);
                SOAPElement commandElement = execute.addChildElement(COMMAND);
                addChildElementAlter(commandElement, alter);

                SOAPElement propertyList = execute.addChildElement(PROPERTIES).addChildElement(PROPERTY_LIST);
                addChildElementPropertyList(propertyList, properties);
                addChildElementParameterList(execute, executeParameterList);

            } catch (SOAPException e) {
                LOGGER.error("ExecuteConsumers AlterRequest accept error", e);
            }
        };
    }
}
