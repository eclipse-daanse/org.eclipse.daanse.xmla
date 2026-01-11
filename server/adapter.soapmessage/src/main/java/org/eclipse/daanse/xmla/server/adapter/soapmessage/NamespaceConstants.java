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
package org.eclipse.daanse.xmla.server.adapter.soapmessage;

import javax.xml.namespace.QName;

/**
 * XML namespace constants for XMLA SOAP messages. Contains namespace URIs,
 * prefixes, and QNames for various XMLA-related schemas.
 */
public interface NamespaceConstants {

    /**
     * Microsoft XMLA namespace constants.
     */
    interface MSXMLA {
        String PREFIX = "msxmla";
        String NS_URN = "urn:schemas-microsoft-com:xml-analysis";

        QName QN_COMMAND = new QName(NS_URN, "Command", PREFIX);
        QName QN_DISCOVER = new QName(NS_URN, "Discover", PREFIX);
        QName QN_DISCOVER_RESPONSE = new QName(NS_URN, "DiscoverResponse", "");
        QName QN_EXECUTE = new QName(NS_URN, "Execute", PREFIX);
        QName QN_EXECUTE_RESPONSE = new QName(NS_URN, "ExecuteResponse", "");
        QName QN_PROPERTIES = new QName(NS_URN, "Properties", PREFIX);
        QName QN_PARAMETERS = new QName(NS_URN, "Parameters", PREFIX);
        QName QN_PROPERTY_LIST = new QName(NS_URN, "PropertyList", PREFIX);
        QName QN_RESTRICTIONS = new QName(NS_URN, "Restrictions", PREFIX);
        QName QN_RESTRICTION_LIST = new QName(NS_URN, "RestrictionList", PREFIX);
        QName QN_RETURN = new QName(NS_URN, "return", "");
        QName QN_REQUEST_TYPE = new QName(NS_URN, "RequestType", PREFIX);
    }

    /**
     * Empty result namespace constants.
     */
    interface EMPTY {
        String PREFIX = "empty";
        String NS_URN = "urn:schemas-microsoft-com:xml-analysis:empty";
        QName QN_ROOT = new QName(NS_URN, "root", PREFIX);
    }

    /**
     * MD Dataset namespace constants.
     */
    interface MDDATASET {
        String PREFIX = "";
        String NS_URN = "urn:schemas-microsoft-com:xml-analysis:mddataset";

        QName QN_ROOT = new QName(NS_URN, "root", PREFIX);
        QName QN_OLAPINFO = new QName(NS_URN, "OlapInfo", PREFIX);
        QName QN_CUBE_INFO = new QName(NS_URN, "CubeInfo", PREFIX);
        QName QN_CUBE = new QName(NS_URN, "Cube", PREFIX);
        QName QN_CUBE_NAME = new QName(NS_URN, "CubeName", PREFIX);
        QName QN_AXES_INFO = new QName(NS_URN, "AxesInfo", PREFIX);
        QName QN_AXIS_INFO = new QName(NS_URN, "AxisInfo", PREFIX);
        QName QN_HIERARCHY_INFO = new QName(NS_URN, "HierarchyInfo", PREFIX);
        QName QN_CELL_INFO = new QName(NS_URN, "CellInfo", PREFIX);
        QName QN_AXES = new QName(NS_URN, "Axes", PREFIX);
        QName QN_AXIS = new QName(NS_URN, "Axis", PREFIX);
        QName QN_TUPLES = new QName(NS_URN, "Tuples", PREFIX);
        QName QN_TUPLE = new QName(NS_URN, "Tuple", PREFIX);
        QName QN_MEMBER = new QName(NS_URN, "Member", PREFIX);
        QName QN_MEMBERS = new QName(NS_URN, "Members", PREFIX);
        QName QN_CELL_DATA = new QName(NS_URN, "CellData", PREFIX);
        QName QN_CELL = new QName(NS_URN, "Cell", PREFIX);
        QName QN_UNION = new QName(NS_URN, "Union", PREFIX);
        QName QN_CROSS_PRODUCT = new QName(NS_URN, "CrossProduct", PREFIX);
        QName QN_NORM_TUPLE_SET = new QName(NS_URN, "NormTupleSet", PREFIX);
    }

    /**
     * Engine namespace constants.
     */
    interface ENGINE {
        String PREFIX = "";
        String NS_URN = "http://schemas.microsoft.com/analysisservices/2003/engine";
    }

    /**
     * Engine 200 namespace constants.
     */
    interface ENGINE200 {
        String PREFIX = "";
        String NS_URN = "http://schemas.microsoft.com/analysisservices/2010/engine/200";
        QName QN_WARNING_COLUMN = new QName(NS_URN, "WarningColumn", PREFIX);
        QName QN_WARNING_MEASURE = new QName(NS_URN, "WarningMeasure", PREFIX);
    }

    /**
     * XSI (XML Schema Instance) namespace constants.
     */
    interface XSI {
        String PREFIX = "xsi";
        String NS_URN = "http://www.w3.org/2001/XMLSchema-instance";
    }

    /**
     * XSD (XML Schema Definition) namespace constants.
     */
    interface XSD {
        String PREFIX = "xsd";
        String NS_URN = "http://www.w3.org/2001/XMLSchema";

        QName QN_SCHEMA = new QName(NS_URN, "schema", PREFIX);
        QName QN_COMPLEX_TYPE = new QName(NS_URN, "complexType", PREFIX);
        QName QN_SEQUENCE = new QName(NS_URN, "sequence", PREFIX);
        QName QN_ELEMENT = new QName(NS_URN, "element", PREFIX);
        QName QN_ANY = new QName(NS_URN, "any", PREFIX);
    }

    /**
     * SQL namespace constants.
     */
    interface SQL {
        String PREFIX = "sql";
        String NS_URN = "urn:schemas-microsoft-com:xml-sql";
    }

    /**
     * Exception namespace constants.
     */
    interface EX {
        String PREFIX = "EX";
        String NS_URN = "urn:schemas-microsoft-com:xml-analysis:exception";
    }
}
