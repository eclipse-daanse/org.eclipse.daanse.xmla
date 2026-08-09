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
package org.eclipse.daanse.xmla.model.io;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

/**
 * One place for how this codec obtains its StAX factories, so the security
 * decisions are made once.
 * <p>
 * Both factories are pinned to Woodstox rather than discovered, because the
 * implementations disagree on something that matters here: the JDK's writer
 * emits {@code \r} and {@code \n} literally in attribute values and {@code \r}
 * literally in text, whereupon the next parser applies XML 1.0 line-end and
 * attribute-value normalisation and the characters are gone. Woodstox writes
 * them as character references, which is what a round-trip needs and what SSAS
 * itself does.
 */
public final class HardenedXml {

    /**
     * Entity limits the JDK applies by default. They cap how large a legitimate
     * response may be, because the parser counts the document itself as an entity -
     * an 85 MB {@code MDSCHEMA_MEMBERS} exceeds them.
     * <p>
     * Lifting them is safe here <strong>because DTD support is off</strong>: with
     * no DTD there are no entity declarations, so there is nothing for an entity
     * limit to protect against. The defence is {@code SUPPORT_DTD=false}, not the
     * size cap; lifting the cap without also turning off DTDs would be a much worse
     * decision.
     */
    private static final String[] SIZE_CAPS = { "jdk.xml.maxGeneralEntitySizeLimit", "jdk.xml.totalEntitySizeLimit", };

    private HardenedXml() {
        // static access only
    }

    /**
     * A writer that escapes control characters instead of losing them to
     * normalisation.
     */
    public static XMLOutputFactory output() {
        com.ctc.wstx.stax.WstxOutputFactory factory = new com.ctc.wstx.stax.WstxOutputFactory();
        // {@link XmlFragment} writes a column holding plain text as characters with no
        // element around them, which the one-root rule Woodstox polices by default
        // would reject.
        factory.setProperty(com.ctc.wstx.api.WstxOutputProperties.P_OUTPUT_VALIDATE_STRUCTURE, Boolean.FALSE);
        return factory;
    }

    /**
     * A reader factory that refuses DTDs and external entities and does not cap
     * document size.
     * <p>
     * A SOAP endpoint parses input from anywhere; entity expansion and external
     * references have no legitimate use in an XMLA message.
     */
    public static XMLInputFactory input() {
        // Pinned like the writer, so the parser does not depend on what else the
        // deployment carries.
        XMLInputFactory factory = new com.ctc.wstx.stax.WstxInputFactory();
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
        for (String cap : SIZE_CAPS) {
            try {
                factory.setProperty(cap, "0");
            } catch (IllegalArgumentException notThisImplementation) {
                // JDK properties. Woodstox does not know them, and never counts the document
                // itself as an entity.
            }
        }
        return factory;
    }
}
