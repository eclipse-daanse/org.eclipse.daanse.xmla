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

import javax.xml.namespace.QName;

/**
 * Constants for XMLA Execute operations. Contains namespace and QName constants
 * for execute response formatting.
 */
public interface ExecuteConstants {

    /**
     * MDDATASET namespace constants for MDX result formatting.
     */
    interface MDDATASET {
        String PREFIX = "";
        String NS_URN = "urn:schemas-microsoft-com:xml-analysis:mddataset";

        QName QN_ROOT = new QName(NS_URN, "root", PREFIX);
        QName QN_OLAP_INFO = new QName(NS_URN, "OlapInfo", PREFIX);
        QName QN_CELL_INFO = new QName(NS_URN, "CellInfo", PREFIX);
        QName QN_CUBE_NAME = new QName(NS_URN, "CubeName", PREFIX);
        QName QN_SIZE = new QName(NS_URN, "Size", PREFIX);
        QName QN_MESSAGES = new QName(NS_URN, "Messages", PREFIX);
        QName QN_CELL_DATA = new QName(NS_URN, "CellData", PREFIX);
        QName QN_CELL = new QName(NS_URN, "Cell", PREFIX);
        QName QN_VALUE = new QName(NS_URN, "Value", PREFIX);
        QName QN_ERROR = new QName(NS_URN, "Error", PREFIX);
        QName QN_AXES = new QName(NS_URN, "Axes", PREFIX);
        QName QN_AXIS = new QName(NS_URN, "Axis", PREFIX);
        QName QN_UNION = new QName(NS_URN, "Union", PREFIX);
        QName QN_NORM_TUPLE_SET = new QName(NS_URN, "NormTupleSet", PREFIX);
        QName QN_CROSS_PRODUCT = new QName(NS_URN, "CrossProduct", PREFIX);
        QName QN_TUPLES = new QName(NS_URN, "Tuples", PREFIX);
        QName QN_MEMBER = new QName(NS_URN, "Member", PREFIX);
        QName QN_AXES_INFO = new QName(NS_URN, "AxesInfo", PREFIX);
        QName QN_AXIS_INFO = new QName(NS_URN, "AxisInfo", PREFIX);
        QName QN_HIERARCHY_INFO = new QName(NS_URN, "HierarchyInfo", PREFIX);
        QName QN_CUBE_INFO = new QName(NS_URN, "CubeInfo", PREFIX);
        QName QN_CUBE = new QName(NS_URN, "Cube", PREFIX);
        QName QN_MEMBERS = new QName(NS_URN, "Members", PREFIX);
        QName QN_TUPLE = new QName(NS_URN, "Tuple", PREFIX);
    }

    /**
     * ENGINE namespace constants for Analysis Services engine.
     */
    interface ENGINE {
        String PREFIX = "engine";
        String NS_URN = "http://schemas.microsoft.com/analysisservices/2003/engine";

        QName QN_LAST_DATA_UPDATE = new QName(MDDATASET.NS_URN, "LastDataUpdate", MDDATASET.PREFIX);
        QName QN_LAST_SCHEMA_UPDATE = new QName(MDDATASET.NS_URN, "LastSchemaUpdate", MDDATASET.PREFIX);
    }

    /**
     * ENGINE200 namespace constants for Analysis Services engine 2010.
     */
    interface ENGINE200 {
        String PREFIX = "engine200";
        String NS_URN = "http://schemas.microsoft.com/analysisservices/2010/engine/200";

        QName QN_WARNING_MEASURE = new QName(NS_URN, "WarningMeasure", PREFIX);
        QName QN_WARNING_COLUMN = new QName(NS_URN, "WarningColumn", PREFIX);
    }

    /**
     * EMPTY namespace constants for empty result formatting.
     */
    interface EMPTY {
        String PREFIX = "empty";
        String NS_URN = "urn:schemas-microsoft-com:xml-analysis:empty";

        QName QN_ROOT = new QName(NS_URN, "root", PREFIX);
    }
}
