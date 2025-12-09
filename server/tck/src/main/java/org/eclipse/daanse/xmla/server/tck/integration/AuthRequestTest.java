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
package org.eclipse.daanse.xmla.server.tck.integration;

import static org.mockito.Mockito.mock;

import java.util.Map;

import org.eclipse.daanse.xmla.api.XmlaService;
import org.eclipse.daanse.xmla.server.tck.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.annotation.Property;
import org.osgi.test.common.annotation.config.WithFactoryConfiguration;
import org.osgi.test.junit5.cm.ConfigurationExtension;

@ExtendWith(ConfigurationExtension.class)

// JWS
@WithFactoryConfiguration(factoryPid = Constants.PID_MS_SOAP_JWS, name = "test-ms-config", location = "?", properties = {
        @Property(key = "xmlaService.target", value = "(" + Constants.XMLASERVICE_FILTER_KEY + "="
                + Constants.XMLASERVICE_FILTER_VALUE + ")"),
        @Property(key = "osgi.soap.endpoint.contextpath", value = Constants.WS_PATH) })

// SOAP-MESSSAGE
@WithFactoryConfiguration(factoryPid = Constants.PID_MS_SOAP_SOAPMESSAGE, name = "test-ms-config", location = "?", properties = {
        @Property(key = "xmlaService.target", value = "(" + Constants.XMLASERVICE_FILTER_KEY + "="
                + Constants.XMLASERVICE_FILTER_VALUE + ")"),
        @Property(key = "osgi.soap.endpoint.contextpath", value = Constants.WS_PATH) })


class AuthRequestTest {

    public static final String REQUEST_AUTHENTICATE_1 = """
            <Authenticate xmlns="http://schemas.microsoft.com/analysisservices/2003/ext">
            <SspiHandshake>TlRMTVNTUAABAAAAB7IIogcABwAxAAAACQAJACgAAAAFAs4OAAAAD1ZBTEVSSUswM1JFRE1PTkQ=</SspiHandshake>
            </Authenticate>
            """;

    @InjectBundleContext
    BundleContext bc;



    @BeforeEach
    void beforaEach() {
        XmlaService xmlaService = mock(XmlaService.class);
        bc.registerService(XmlaService.class, xmlaService, FrameworkUtil
                .asDictionary(Map.of(Constants.XMLASERVICE_FILTER_KEY, Constants.XMLASERVICE_FILTER_VALUE)));
    }

    @Test()
    void testAUTH(@InjectService XmlaService xmlaService) {

//        AuthenticateResponse ar = new AuthenticateResponse();
//        ReturnValue rv = new ReturnValue();
//        rv.setSspiHandshake("22".getBytes());
//        ar.setReturn(rv);
//        when(xmlaService.authenticate(Mockito.any())).thenReturn(ar);
//
//        SOAPUtil.callSoapWebService(Constants.soapEndpointUrl, Optional.empty(),
//                SOAPUtil.envelop(REQUEST_AUTHENTICATE_1));

//        verify(xmlaService, (times(1))).authenticate(Mockito.any());
    }

}
