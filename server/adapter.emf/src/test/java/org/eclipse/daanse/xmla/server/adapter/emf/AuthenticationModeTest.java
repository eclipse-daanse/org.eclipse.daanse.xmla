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
package org.eclipse.daanse.xmla.server.adapter.emf;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

import org.eclipse.daanse.xmla.api.XmlaConnector;
import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.model.rowset.core.RowsetCoreFactory;
import org.eclipse.daanse.xmla.model.rowset.core.DiscoverDatasourcesRow;
import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.Execute;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;

/**
 * DISCOVER_DATASOURCES says what the endpoint actually demands.
 * <p>
 * [MS-SSAS] on the AuthenticationMode column: {@code Unauthenticated} means "no
 * user ID or password has to be sent", {@code Authenticated} that they "MUST be
 * included". The connector cannot know which holds - it is handed a caller, not
 * a policy - so it states the open case and the transport corrects it.
 * <p>
 * This column is read <em>before</em> the first challenge: properties and data
 * sources are answered anonymously on purpose, so a client can ask before it
 * knows whom to introduce itself as. Announcing Unauthenticated on an endpoint
 * that then refuses leaves a client with nothing to offer, and it retries the
 * handshake instead of authenticating.
 */
class AuthenticationModeTest {

    private static final String DATASOURCES = """
            <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body>\
            <Discover xmlns="urn:schemas-microsoft-com:xml-analysis">\
            <RequestType>DISCOVER_DATASOURCES</RequestType>\
            <Restrictions><RestrictionList/></Restrictions>\
            <Properties><PropertyList/></Properties></Discover></soap:Body></soap:Envelope>""";

    /** A connector that states the open case, as one with no policy must. */
    private static XmlaConnector connectorSayingUnauthenticated() {
        return new XmlaConnector() {

            @Override
            public List<EObject> discover(Discover request, XmlaRequest context) {
                DiscoverDatasourcesRow row = RowsetCoreFactory.eINSTANCE.createDiscoverDatasourcesRow();
                row.setDataSourceName("Daanse");
                row.setAuthenticationMode("Unauthenticated");
                return List.of(row);
            }

            @Override
            public EObject execute(Execute request, XmlaRequest context) {
                return null;
            }
        };
    }

    private static String answerUnder(AccessPolicy policy) {
        ByteArrayOutputStream answer = new ByteArrayOutputStream();
        new EmfXmlaAdapter(connectorSayingUnauthenticated(), null, null, policy).handle(
                new ByteArrayInputStream(DATASOURCES.getBytes(StandardCharsets.UTF_8)), answer,
                XmlaRequest.anonymous());
        return answer.toString(StandardCharsets.UTF_8);
    }

    @Test
    void anEndpointThatDemandsALoginSaysSo() {
        String answer = answerUnder(new AccessPolicy(true, Set.of("DISCOVER_PROPERTIES", "DISCOVER_DATASOURCES")));

        assertThat(answer).contains("<AuthenticationMode>Authenticated</AuthenticationMode>")
                .doesNotContain("Unauthenticated");
    }

    @Test
    void anOpenEndpointIsLeftAsTheConnectorStatedIt() {
        String answer = answerUnder(AccessPolicy.OPEN);

        assertThat(answer).contains("<AuthenticationMode>Unauthenticated</AuthenticationMode>");
    }
}
