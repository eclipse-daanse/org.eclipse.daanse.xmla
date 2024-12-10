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
package org.eclipse.daanse.xmla.api;

import static org.eclipse.daanse.xmla.api.XmlaConstants.CLIENT_FAULT_FC;
import static org.eclipse.daanse.xmla.api.XmlaConstants.USM_DOM_PARSE_CODE;
import static org.eclipse.daanse.xmla.api.XmlaConstants.USM_DOM_PARSE_FAULT_FS;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
/**
 * Utility methods for XML/A implementation.
 *
 * @author Gang Chen
 */
public class XmlaUtil {

    /**
     * Invalid characters for XML element name.
     *
     * <p>XML element name:
     *
     * Char ::= #x9 | #xA | #xD | [#x20-#xD7FF]
     *        | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
     * S ::= (#x20 | #x9 | #xD | #xA)+
     * NameChar ::= Letter | Digit | '.' | '-' | '_' | ':' | CombiningChar
     *        | Extender
     * Name ::= (Letter | '_' | ':') (NameChar)*
     * Names ::= Name (#x20 Name)*
     * Nmtoken ::= (NameChar)+
     * Nmtokens ::= Nmtoken (#x20 Nmtoken)*
     *
     */
    private static final String VALID_CHARACTERS_EXP = "^[:A-Z_a-z\u00C0\u00D6\u00D8-\u00F6\u00F8-\u02ff\u0370-\u037d"
            + "\u037f-\u1fff\u200c\u200d\u2070-\u218f\u2c00-\u2fef\u3001-\ud7ff"
            + "\uf900-\ufdcf\ufdf0-\ufffd]"
            + "[:A-Z_a-z\u00C0\u00D6\u00D8-\u00F6"
            + "\u00F8-\u02ff\u0370-\u037d\u037f-\u1fff\u200c\u200d\u2070-\u218f"
            + "\u2c00-\u2fef\u3001-\udfff\uf900-\ufdcf\ufdf0-\ufffd\\-\\.0-9"
            + "\u00b7\u0300-\u036f\u203f-\u2040]*\\Z";
    private static Pattern validCharactersPatern = Pattern.compile(VALID_CHARACTERS_EXP);

    private XmlaUtil() {
        // constructor
    }

    private static String encodeChar(char c) {
        StringBuilder buf = new StringBuilder();
        buf.append("_x");
        String str = Integer.toHexString(c);
        for (int i = 4 - str.length(); i > 0; i--) {
            buf.append("0");
        }
        return buf.append(str).append("_").toString();
    }

    /**
     * Encodes an XML element name.
     *
     * <p>This function is mainly for encode element names in result of Drill
     * Through execute, because its element names come from database, we cannot
     * make sure they are valid XML contents.
     *
     * <p>Quoth the <a href="http://xmla.org">XML/A specification</a>, version
     * 1.1:
     * <blockquote>
     * XML does not allow certain characters as element and attribute names.
     * XML for Analysis supports encoding as defined by SQL Server 2000 to
     * address this XML constraint. For column names that contain invalid XML
     * name characters (according to the XML 1.0 specification), the nonvalid
     * Unicode characters are encoded using the corresponding hexadecimal
     * values. These are escaped as _x<i>HHHH_</i> where <i>HHHH</i> stands for
     * the four-digit hexadecimal UCS-2 code for the character in
     * most-significant bit first order. For example, the name "Order Details"
     * is encoded as Order_<i>x0020</i>_Details, where the space character is
     * replaced by the corresponding hexadecimal code.
     * </blockquote>
     *
     * @param name Name of XML element
     * @return encoded name
     */
    private static String encodeElementName(String name) {
        StringBuilder buf = new StringBuilder();
        char[] nameChars = name.toCharArray();
        for (char ch : nameChars) {
            boolean b = validCharactersPatern.matcher("" + ch).matches();
            if(b) {
                buf.append(ch);
            } else {
                buf.append(encodeChar(ch));
            }
        }
        return buf.toString();
    }


    public static void element2Text(Element elem, final StringWriter writer)
        throws XmlaException
    {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            Transformer transformer = factory.newTransformer();
            transformer.transform(
                new DOMSource(elem),
                new StreamResult(writer));
        } catch (Exception e) {
            throw new XmlaException(
                CLIENT_FAULT_FC,
                USM_DOM_PARSE_CODE,
                USM_DOM_PARSE_FAULT_FS,
                e);
        }
    }


    public static Element[] filterChildElements(
        Element parent,
        String ns,
        String lname)
    {

        List<Element> elems = new ArrayList<>();
        NodeList nlst = parent.getChildNodes();
        for (int i = 0, nlen = nlst.getLength(); i < nlen; i++) {
            Node n = nlst.item(i);
            if (n instanceof Element e && (ns == null || ns.equals(e.getNamespaceURI()))
                    && (lname == null || lname.equals(e.getLocalName())))
            {
                elems.add(e);
            }
        }
        return elems.toArray(new Element[elems.size()]);
    }

    public static String textInElement(Element elem) {
        StringBuilder buf = new StringBuilder(100);
        elem.normalize();
        NodeList nlst = elem.getChildNodes();
        for (int i = 0, nlen = nlst.getLength(); i < nlen ; i++) {
            Node n = nlst.item(i);
            if (n instanceof Text text) {
                final String data = text.getData();
                buf.append(data);
            }
        }
        return buf.toString();
    }

    /**
     * Finds root MondrianException in exception chain if exists,
     * otherwise the input throwable.
     *
     * @param throwable Exception
     * @return Root exception
     */
    public static Throwable rootThrowable(Throwable throwable) {
        Throwable rootThrowable = throwable.getCause();
        if (rootThrowable instanceof XmlaException)
        {
            return rootThrowable(rootThrowable);
        }
        return throwable;
    }

    /**
     * Corrects for the differences between numeric strings arising because
     * JDBC drivers use different representations for numbers
     * ({@link Double} vs. {@link java.math.BigDecimal}) and
     * these have different toString() behavior.
     *
     * <p>If it contains a decimal point, then
     * strip off trailing '0's. After stripping off
     * the '0's, if there is nothing right of the
     * decimal point, then strip off decimal point.
     *
     * @param numericStr Numeric string
     * @return Normalized string
     */
    public static String normalizeNumericString(String numericStr) {
        int index = numericStr.indexOf('.');
        if (index > 0) {
            // If it uses exponential notation, 1.0E4, then it could
            // have a trailing '0' that should not be stripped of,
            // e.g., 1.0E10. This would be rather bad.
            if ((numericStr.indexOf('e') != -1) || (numericStr.indexOf('E') != -1)) {
                return numericStr;
            }

            boolean found = false;
            int p = numericStr.length();
            char c = numericStr.charAt(p - 1);
            while (c == '0') {
                found = true;
                p--;
                c = numericStr.charAt(p - 1);
            }
            if (c == '.') {
                p--;
            }
            if (found) {
                return numericStr.substring(0, p);
            }
        }
        return numericStr;
    }


    private static <T> String toString(List<T> list) {
        StringBuilder buf = new StringBuilder();
        int k = -1;
        for (T t : list) {
            if (++k > 0) {
                buf.append(", ");
            }
            buf.append(t);
        }
        return buf.toString();
    }



    /**
     * Result of a metadata query.
     */
    public static class MetadataRowset {
        public final List<String> headerList = new ArrayList<>();
        public final List<List<Object>> rowList = new ArrayList<>();
    }

    /**
     * Wrapper which indicates that a restriction is to be treated as a
     * SQL-style wildcard match.
     */
    public static class Wildcard {
        public final String pattern;

        public Wildcard(String pattern) {
            this.pattern = pattern;
        }
    }


    public static class ElementNameEncoder {
        private final Map<String, String> map =
            new ConcurrentHashMap<>();
        public static final ElementNameEncoder INSTANCE =
            new ElementNameEncoder();

        public String encode(String name) {
            return map.computeIfAbsent(name, XmlaUtil::encodeElementName);
        }
    }
}
