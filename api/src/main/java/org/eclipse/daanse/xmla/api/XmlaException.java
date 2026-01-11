/*/*
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

/**
 * An exception thrown while processing an XMLA request. The faultcode
 * corresponds to the SOAP Fault faultcode and the faultstring
 * to the SOAP Fault faultstring.
 *
 * @author <a>Richard M. Emberson</a>
 */
@SuppressWarnings("serial")
public class XmlaException extends RuntimeException {

    public static String formatFaultCode(XmlaException xex) {
        return formatFaultCode(xex.getFaultCode(), xex.getCode());
    }
    public static String formatFaultCode(String faultCode, String code) {
        return formatFaultCode(
            XmlaConstants.SOAP_PREFIX,
            faultCode, code);
    }

    public static String formatFaultCode(
        String nsPrefix,
        String faultCode, String code)
    {
        return nsPrefix
            + ':'
            + faultCode
            + '.'
            + code;
    }
    public static String formatDetail(String msg) {
        return XmlaConstants.FAULT_FS_PREFIX + msg;
    }

    public static Throwable getRootCause(Throwable throwable) {
        Throwable t = throwable;
        while (t.getCause() != null) {
            t = t.getCause();
        }
        return t;
    }

    private final String faultCode;
    private final String code;
    private final String faultString;

    public XmlaException(
        String faultCode,
        String code,
        String faultString,
        Throwable cause)
    {
        super(faultString, cause);
        this.faultCode = faultCode;
        this.code = code;
        this.faultString = faultString;
    }

    public String getFaultCode() {
        return faultCode;
    }
    public String getCode() {
        return code;
    }
    public String getFaultString() {
        return faultString;
    }
    public String getDetail() {
        Throwable t = getCause();
        t = getRootCause(t);
        String detail = t.getMessage();
        return (detail != null)
            ? detail
            : t.getClass().getName();
    }
}

// End XmlaException.java

