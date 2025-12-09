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
package org.eclipse.daanse.xmla.server.jakarta.xml.ws.provider.soapmessage.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.eclipse.daanse.xmla.api.XmlaService;
import org.eclipse.daanse.xmla.server.tck.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.RequireServiceComponentRuntime;
import org.osgi.service.component.runtime.ServiceComponentRuntime;
import org.osgi.service.component.runtime.dto.ComponentConfigurationDTO;
import org.osgi.service.component.runtime.dto.ComponentDescriptionDTO;
import org.osgi.service.component.runtime.dto.SatisfiedReferenceDTO;
import org.osgi.service.component.runtime.dto.UnsatisfiedReferenceDTO;
import org.osgi.service.servlet.whiteboard.annotations.RequireHttpWhiteboard;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.annotation.Property;
import org.osgi.test.common.annotation.config.WithFactoryConfiguration;
import org.osgi.test.common.dictionary.Dictionaries;
import org.osgi.test.junit5.cm.ConfigurationExtension;

import aQute.bnd.annotation.service.ServiceCapability;

@ServiceCapability(XmlaService.class)
@ExtendWith(ConfigurationExtension.class)

//JWS

@WithFactoryConfiguration(factoryPid = Constants.PID_MS_SOAP_JWS, name = "test-ms-config", location = "?", properties = {
        @Property(key = "xmlaService.target", value = "(" + Constants.XMLASERVICE_FILTER_KEY + "="
                + Constants.XMLASERVICE_FILTER_VALUE + ")"),
        @Property(key = "osgi.soap.endpoint.contextpath", value = Constants.WS_PATH) })

//SOAP-MESSAGE
@WithFactoryConfiguration(factoryPid = Constants.PID_MS_SOAP_SOAPMESSAGE, name = "test-ms-config", location = "?", properties = {
        @Property(key = "xmlaService.target", value = "(" + Constants.XMLASERVICE_FILTER_KEY + "="
                + Constants.XMLASERVICE_FILTER_VALUE + ")"),
        @Property(key = "osgi.soap.endpoint.contextpath", value = Constants.WS_PATH) })


@RequireHttpWhiteboard
@RequireServiceComponentRuntime
class InitialisationTest {

    @InjectBundleContext
    BundleContext bc;

    @InjectService
    ServiceComponentRuntime componentRuntime;

    @BeforeEach
    void beforaEach() throws InterruptedException {
        XmlaService xmlaService = mock(XmlaService.class);
        bc.registerService(XmlaService.class, xmlaService,
                Dictionaries.dictionaryOf(Constants.XMLASERVICE_FILTER_KEY, Constants.XMLASERVICE_FILTER_VALUE));
        TimeUnit.SECONDS.sleep(2);

    }

    @Test
    void testRequestwsdl(@InjectService XmlaService xmlaService) throws Exception {
        printScrInfo();
        String sUrl = "http://localhost:" + Constants.SERVER_PORT_WHITEBOARD + Constants.WS_PATH;
        assertThat(sUrl).isNotNull();
    }

    void printScrInfo() {

        System.out.println("============ Framework Components ==================");
        Collection<ComponentDescriptionDTO> descriptionDTOs = componentRuntime.getComponentDescriptionDTOs();
        Comparator<ComponentConfigurationDTO> byComponentName = Comparator.comparing(dto -> dto.description.name,
                String.CASE_INSENSITIVE_ORDER);
        Comparator<ComponentConfigurationDTO> byComponentState = Comparator.comparingInt(dto -> dto.state);
        descriptionDTOs.stream()
                .flatMap(dto -> componentRuntime.getComponentConfigurationDTOs(dto)
                        .stream())
                .sorted(byComponentState.thenComparing(byComponentName))
                .forEachOrdered(dto -> {
                    if (dto.state == ComponentConfigurationDTO.FAILED_ACTIVATION) {
                        System.out.println(
                                toComponentState(dto.state) + " | " + dto.description.name + " | " + dto.failure);
                    } else {
                        System.out.println(toComponentState(dto.state) + " | " + dto.description.name);
                    }
                    for (int i = 0; i < dto.unsatisfiedReferences.length; i++) {
                        UnsatisfiedReferenceDTO ref = dto.unsatisfiedReferences[i];
                        System.out.println("\t" + ref.name + " is missing");
                    }
                    for (int i = 0; i < dto.satisfiedReferences.length; i++) {
                        SatisfiedReferenceDTO sat = dto.satisfiedReferences[i];
                        System.out.println("\t" + sat.name + " (bound " + sat.boundServices.length + ")");
                    }
                    System.out.println("\tProperties:");
                    for (Entry<String, Object> entry : dto.properties.entrySet()) {
                        System.out.println("\t\t" + entry.getKey() + ": " + entry.getValue());
                    }
                });
    }

    private static String toComponentState(int state) {
        return switch (state) {
        case ComponentConfigurationDTO.ACTIVE -> "ACTIVE     ";
        case ComponentConfigurationDTO.FAILED_ACTIVATION -> "FAILED     ";
        case ComponentConfigurationDTO.SATISFIED -> "SATISFIED  ";
        case ComponentConfigurationDTO.UNSATISFIED_CONFIGURATION, ComponentConfigurationDTO.UNSATISFIED_REFERENCE ->
            "UNSATISFIED";
        default -> String.valueOf(state);
        };
    }
}
