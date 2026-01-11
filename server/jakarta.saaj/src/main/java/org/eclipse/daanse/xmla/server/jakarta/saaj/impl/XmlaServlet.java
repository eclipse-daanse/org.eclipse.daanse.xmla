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
*/
package org.eclipse.daanse.xmla.server.jakarta.saaj.impl;

import jakarta.servlet.Servlet;
import jakarta.xml.soap.MimeHeader;
import jakarta.xml.soap.SOAPMessage;
import org.eclipse.daanse.jakarta.servlet.soap.AbstractSoapServlet;
import org.eclipse.daanse.xmla.api.XmlaService;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaApiAdapter;
import org.eclipse.daanse.xmla.server.jakarta.saaj.api.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.servlet.whiteboard.propertytypes.HttpWhiteboardServletPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.security.Principal;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@HttpWhiteboardServletPattern("/xmla")
@Designate(ocd = XmlaServletOCD.class, factory = true)
@Component(service = Servlet.class, scope = ServiceScope.PROTOTYPE, configurationPid = Constants.PID_XMLA_SERVLET)
public class XmlaServlet extends AbstractSoapServlet {

    private static final long serialVersionUID = 1L;
    private static Logger LOGGER = LoggerFactory.getLogger(XmlaServlet.class);
    private XmlaApiAdapter xmlaAdapter;

    @Reference(name = Constants.REFERENCE_XMLA_SERVICE)
    private XmlaService xmlaService;

    @Activate
    public void activate() {
        xmlaAdapter = new XmlaApiAdapter(xmlaService);
    }

    @Override
    public SOAPMessage onMessage(SOAPMessage soapMessage,Principal principal, Function<String, Boolean> isUserInRoleFunction, String url) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("SoapMessage in:", prettyPrint(soapMessage).toString());
            }
            Iterable<MimeHeader> iterable = () -> soapMessage.getMimeHeaders().getAllHeaders();
            Map<String, Object> map = StreamSupport.stream(iterable.spliterator(), true).collect(
                    Collectors.toMap(MimeHeader::getName, MimeHeader::getValue, (oldValue, _) -> oldValue));

            SOAPMessage returnMessage = xmlaAdapter.handleRequest(soapMessage, map, principal,  isUserInRoleFunction, url);

            LOGGER.debug("SoapMessage out:", prettyPrint(returnMessage).toString());

            return returnMessage;

        } catch (Exception e) {
            LOGGER.error("Error processing SOAP message", e);
            return null;
        }
    }

    private static ByteArrayOutputStream prettyPrint(SOAPMessage msg) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(msg.getSOAPPart().getContent(), new StreamResult(baos));
        } catch (Exception e) {
            LOGGER.error("Exception while generate prettyPrint of SoapMessage.", e);
            try {

                msg.writeTo(baos);
            } catch (Exception e1) {
                LOGGER.error("Exception while generate prettyPrintfallback of SoapMessage.", e1);
                baos.writeBytes(msg.toString().getBytes());
            }
        }
        return baos;
    }
}
