/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.server.adapter.soapmessage;

import org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.EX;

import jakarta.xml.soap.Detail;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPConstants;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.soap.SOAPMessage;

public class SoapFaultFactory {

    static SOAPMessage senderFault(String reason, String xmlaErrorCode, XmlaParseException ex) {
        try {
            SOAPMessage msg = MessageFactory.newInstance().createMessage();
            SOAPBody body = msg.getSOAPPart().getEnvelope().getBody();
            SOAPFault fault = body.addFault();
            fault.setFaultCode(SOAPConstants.SOAP_SENDER_FAULT);
            fault.setFaultString(reason);
            Detail detail = fault.addDetail();
            SOAPElement err = detail.addChildElement("error", "", EX.NS_URN);
            err.addChildElement("Code").setTextContent(xmlaErrorCode);
            err.addChildElement("Description").setTextContent(reason);
            return msg;
        }
        catch (SOAPException e) {
            throw new IllegalStateException(e);
        }
    }

    public static SOAPMessage receiverFault(String reason, Throwable ex) {
        try {
            SOAPMessage msg = MessageFactory.newInstance().createMessage();
            SOAPBody body = msg.getSOAPPart().getEnvelope().getBody();
            SOAPFault fault = body.addFault();
            fault.setFaultCode(SOAPConstants.SOAP_RECEIVER_FAULT);
            fault.setFaultString(reason);
            Detail detail = fault.addDetail();
            SOAPElement err = detail.addChildElement("error", "", EX.NS_URN);
            err.addChildElement("Description").setTextContent(ex.getMessage());
            return msg;
        }
        catch (SOAPException e) {
            throw new IllegalStateException(e);
        }
    }

}
