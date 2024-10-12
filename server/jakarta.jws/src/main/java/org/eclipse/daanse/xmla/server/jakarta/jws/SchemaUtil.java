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
package org.eclipse.daanse.xmla.server.jakarta.jws;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.transform.Result;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xsd.Schema;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.SchemaOutputResolver;
import jakarta.xml.bind.util.JAXBResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaUtil {

    private SchemaUtil() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaUtil.class);
    static Map<Class<?>[], Schema> map = new ConcurrentHashMap<>();

    //
    public static final Schema generateSchema(String ns, Class<?>... classes) throws JAXBException, IOException {

        Schema schema = map.get(classes);
        if (schema != null) {
            return schema;
        }
        JAXBContext applicationContext = JAXBContext.newInstance(classes);
        JAXBContext schemaContext = JAXBContext.newInstance(Schema.class);
        JaxBSchemaOutputResolver resolver = new JaxBSchemaOutputResolver(schemaContext);
        applicationContext.generateSchema(resolver);
        Marshaller marshaller = schemaContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        schema = (Schema) resolver.schemas.get(ns).getResult();
        StringWriter sw = new StringWriter();
        marshaller.marshal(schema, sw);
        String msg = sw.toString();
        LOGGER.debug(msg);
        map.put(classes, schema);
        return schema;
    }

    public static final class JaxBSchemaOutputResolver extends SchemaOutputResolver {

        private JAXBContext context;
        private Map<String, JAXBResult> schemas = new HashMap<>();

        public JaxBSchemaOutputResolver(JAXBContext context) {
            this.context = context;
        }

        @Override
        public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
            LOGGER.debug("Generate schema for {} with suggested name {}", namespaceURI, suggestedFileName);
            try {
                JAXBResult result = new JAXBResult(context);
                result.setSystemId(namespaceURI);
                schemas.put(namespaceURI, result);
                return result;
            } catch (JAXBException e) {
                throw new IOException(e);
            }
        }

    }

}
