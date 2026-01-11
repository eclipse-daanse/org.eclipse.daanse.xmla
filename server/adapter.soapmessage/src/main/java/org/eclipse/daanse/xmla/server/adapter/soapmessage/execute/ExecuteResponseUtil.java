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
package org.eclipse.daanse.xmla.server.adapter.soapmessage.execute;

import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION;

import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.daanse.xmla.api.engine200.WarningColumn;
import org.eclipse.daanse.xmla.api.engine200.WarningLocationObject;
import org.eclipse.daanse.xmla.api.engine200.WarningMeasure;
import org.eclipse.daanse.xmla.api.exception.ErrorType;
import org.eclipse.daanse.xmla.api.exception.Exception;
import org.eclipse.daanse.xmla.api.exception.MessageLocation;
import org.eclipse.daanse.xmla.api.exception.Messages;
import org.eclipse.daanse.xmla.api.exception.StartEnd;
import org.eclipse.daanse.xmla.api.exception.WarningType;
import org.eclipse.daanse.xmla.api.xmla_empty.Emptyresult;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaSoapException;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.ExecuteConstants.ENGINE200;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.ExecuteConstants.MDDATASET;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class ExecuteResponseUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteResponseUtil.class);
    private static final String DESCRIPTION_LC = "Description";

    private ExecuteResponseUtil() {
        // utility class
    }

    public static SOAPElement addEmptyRoot(SOAPBody body) throws SOAPException {
        SOAPElement seExecuteResponse = body.addChildElement(Constants.MSXMLA.QN_EXECUTE_RESPONSE);
        SOAPElement seReturn = seExecuteResponse.addChildElement(Constants.MSXMLA.QN_RETURN);
        SOAPElement seRoot = seReturn.addChildElement(ExecuteConstants.EMPTY.QN_ROOT);

        seRoot.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        seRoot.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        seRoot.setAttribute("xmlns:EX", Constants.EX.NS_URN);
        return seRoot;
    }

    public static void addEmptyresult(SOAPElement root, Emptyresult emptyresult) {
        addException(root, emptyresult.exception());
        addMessages(root, emptyresult.messages());
    }

    public static void addException(SOAPElement e, Exception it) {
        if (it != null) {
            addChildElement(e, "Exception", null);
        }
    }

    public static void addMessages(SOAPElement e, Messages it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, MDDATASET.QN_MESSAGES);
            addExceptionTypeList(el, it.warningOrError());
        }
    }

    private static void addExceptionTypeList(SOAPElement e, List<org.eclipse.daanse.xmla.api.exception.Type> list) {
        if (list != null) {
            list.forEach(it -> addExceptionType(e, it));
        }
    }

    private static void addExceptionType(SOAPElement e, org.eclipse.daanse.xmla.api.exception.Type it) {
        if (it != null) {
            if (it instanceof WarningType warningType) {
                addWarningType(e, warningType);
            }
            if (it instanceof ErrorType errorType) {
                addErrorType(e, errorType);
            }
        }
    }

    private static void addErrorType(SOAPElement e, ErrorType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, "Error", null);

            addChildElement(el, "Callstack", null, it.callstack());
            addChildElement(el, "ErrorCode", null, String.valueOf(it.errorCode()));
            addMessageLocation(el, it.location());
            setAttribute(el, DESCRIPTION, it.description());
            setAttribute(el, "Source", it.source());
            setAttribute(el, "HelpFile", it.helpFile());
        }
    }

    private static void addWarningType(SOAPElement e, WarningType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, "Warning", null);
            addChildElement(el, "WarningCode", null, String.valueOf(it.warningCode()));
            addMessageLocation(el, it.location());
            addChildElement(el, DESCRIPTION_LC, null, it.description());
            addChildElement(el, "Source", null, it.source());
            addChildElement(el, "HelpFile", null, it.helpFile());
        }
    }

    private static void addMessageLocation(SOAPElement e, MessageLocation it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, "Location", null);
            addStartEnd(el, "Start", it.start());
            addStartEnd(el, "End", it.end());
            addChildElement(el, "LineOffset", null, String.valueOf(it.lineOffset()));
            addChildElement(el, "TextLength", null, String.valueOf(it.textLength()));
            addWarningLocationObject(el, "SourceObject", it.sourceObject());
            addWarningLocationObject(el, "DependsOnObject", it.dependsOnObject());
            addChildElement(el, "RowNumber", null, String.valueOf(it.rowNumber()));
        }
    }

    private static void addWarningLocationObject(SOAPElement e, String tagName, WarningLocationObject it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, tagName, null);
            addWarningColumn(el, it.warningColumn());
            addWarningMeasure(el, it.warningMeasure());
        }
    }

    private static void addWarningMeasure(SOAPElement e, WarningMeasure it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, ENGINE200.QN_WARNING_MEASURE);
            addChildElement(el, "Cube", null, it.cube());
            addChildElement(el, "MeasureGroup", null, it.measureGroup());
            addChildElement(el, "MeasureName", null, it.measureName());
        }
    }

    private static void addWarningColumn(SOAPElement e, WarningColumn it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, ENGINE200.QN_WARNING_COLUMN);
            addChildElement(el, "Dimension", null, it.dimension());
            addChildElement(el, "Attribute", null, it.attribute());
        }
    }

    private static void addStartEnd(SOAPElement e, String tagName, StartEnd it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, tagName, null);
            addChildElement(el, "Line", null, String.valueOf(it.line()));
            addChildElement(el, "Column", null, String.valueOf(it.column()));
        }
    }

    // Helper methods for SOAP element creation

    private static void setAttribute(SOAPElement el, String name, String value) {
        if (value != null) {
            el.setAttribute(name, value);
        }
    }

    private static SOAPElement addChildElement(SOAPElement element, QName qNameOfChild) {
        try {
            return element.addChildElement(qNameOfChild);
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", qNameOfChild);
            throw new XmlaSoapException("addChildElement error", e);
        }
    }

    private static void addChildElement(SOAPElement element, String childElementName, String prefix, String value) {
        try {
            if (value != null) {
                if (prefix != null) {
                    element.addChildElement(childElementName, prefix).setTextContent(value);
                } else {
                    element.addChildElement(childElementName).setTextContent(value);
                }
            }
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", childElementName);
            throw new XmlaSoapException("addChildElement error", e);
        }
    }

    private static SOAPElement addChildElement(SOAPElement element, String childElementName, String prefix) {
        try {
            if (prefix == null) {
                return element.addChildElement(childElementName);
            } else {
                return element.addChildElement(childElementName, prefix);
            }
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", childElementName);
            throw new XmlaSoapException("addChildElement error", e);
        }
    }
}
