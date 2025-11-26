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
*   Stefan Bischof (bipolis.org)
* Licensed to Julian Hyde under one or more contributor license
* agreements. See the NOTICE file distributed with this work for
* additional information regarding copyright ownership.
*
* Julian Hyde licenses this file to you under the Apache License,
* Version 2.0 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at:
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.eclipse.daanse.xmla.api;

/**
 * Collection of various enumerations and constants defined by the XML for
 * Analysis (XMLA) and OLE DB for OLAP specifications.
 *
 * @author jhyde
 */
public class XmlaConstants
{
    // Suppresses default constructor, ensuring non-instantiability.
    private XmlaConstants() {
    }

    /* SOAP 1.1 */
    public static final String NS_SOAP_ENV_1_1 =
        "http://schemas.xmlsoap.org/soap/envelope/";
    public static final String NS_SOAP_ENC_1_1 =
        "http://schemas.xmlsoap.org/soap/encoding/";

    /* SOAP 1.2 - currently not supported */
    public static final String NS_SOAP_ENV_1_2 =
        "http://www.w3.org/2003/05/soap-envelope";
    public static final String NS_SOAP_ENC_1_2 =
        "http://www.w3.org/2003/05/soap-encoding";

    /* Namespaces for XML */
    public static final String NS_XSD = "http://www.w3.org/2001/XMLSchema";
    public static final String NS_XSI = "http://www.w3.org/2001/XMLSchema-instance";

    /* Namespaces for XML/A */
    public static final String NS_XMLA =
        "urn:schemas-microsoft-com:xml-analysis";
    public static final String NS_XMLA_MDDATASET =
        "urn:schemas-microsoft-com:xml-analysis:mddataset";
    public static final String NS_XMLA_EMPTY =
        "urn:schemas-microsoft-com:xml-analysis:empty";
    public static final String NS_XMLA_ROWSET =
        "urn:schemas-microsoft-com:xml-analysis:rowset";
    public static final String NS_SQL = "urn:schemas-microsoft-com:xml-sql";
    public static final String NS_XMLA_EX =
        "urn:schemas-microsoft-com:xml-analysis:exception";

    public static final String NS_SOAP_SECEXT =
        "http://schemas.xmlsoap.org/ws/2002/04/secext";

    public static final String SOAP_PREFIX = "SOAP-ENV";

    public static final String NS_AS_ENGINE =
        "http://schemas.microsoft.com/analysisservices/2003/engine";

    /**
     * Soap Header mustUnderstand attribute name.
     */
    public static final String SOAP_MUST_UNDERSTAND_ATTR = "mustUnderstand";

    /**
     * Soap XMLA Header elements and attribute names.
     */
    public static final String XMLA_BEGIN_SESSION      = "BeginSession";
    public static final String XMLA_SESSION            = "Session";
    public static final String XMLA_END_SESSION        = "EndSession";
    public static final String XMLA_SESSION_ID         = "SessionId";
    public static final String XMLA_SECURITY           = "Security";

    /**
     * Names of context keys known by both callbacks and Mondrian code.
     */
    // context key for role name storage
    public static final String CONTEXT_ROLE_NAME   = "role_name";
    // context key for language (SOAP or JSON)
    public static final String CONTEXT_MIME_TYPE = "language";
    // context key for session id storage
    public static final String CONTEXT_XMLA_SESSION_ID   = "session_id";

    // Username and password tokens
    public static final String CONTEXT_XMLA_USERNAME = "username";
    public static final String CONTEXT_XMLA_PASSWORD = "password";

    // context key for session state storage
    public static final String CONTEXT_XMLA_SESSION_STATE = "SessionState";
    public static final String CONTEXT_XMLA_SESSION_STATE_BEGIN =
        "SessionStateBegin";
    public static final String CONTEXT_XMLA_SESSION_STATE_WITHIN =
        "SessionStateWithin";
    public static final String CONTEXT_XMLA_SESSION_STATE_END =
        "SessionStateEnd";

    /*****
    *
    * The following are XMLA exception fault codes used as faultcode entries
    * in the SOAP Fault element.
    *
    * If Mondrian Exceptions actually used the "id" attributes found in the
    * MondrianResource.xml file, then those would be used as the SOAP Fault
    * detail error code values, but, alas they do not show up as part of
    * the generated Exception Java code so, here we simply duplicate
    * the fault code entry.
    *
    * Currently, SOAP 1.2 errors are not supported.
    *
    ****/

   /**
    * This is the namespace used to qualify the faultcode identifier.
    */
    public static final String DAANSE_NAMESPACE = "http://daanse.sourceforge.net";
    public static final String FAULT_NS_PREFIX = "XA";

    public static final String FAULT_ACTOR = "Daanse";

    // soap 1.1 default faultcodes
    public static final String VERSION_MISSMATCH_FAULT_FC = "VersionMismatch";
    public static final String MUST_UNDERSTAND_FAULT_FC = "MustUnderstand";
    public static final String CLIENT_FAULT_FC = "Client";
    public static final String SERVER_FAULT_FC = "Server";

      //<faultcode>XA:Mondrian.XML.88BA1202</faultcode>
    public static final String FAULT_FC_PREFIX = "Daanse";
    public static final String FAULT_FS_PREFIX = "The Daanse XML: ";

    /////////////////////////////////////////////////////////////////////////
    // Servlet Initialization Error : SIE
    /////////////////////////////////////////////////////////////////////////
    public static final String SIE_REQUEST_STATE_CODE = "00SIEA01";
    public static final String SIE_REQUEST_STATE_FAULT_FS =
            "Servlet initialization error";

    /////////////////////////////////////////////////////////////////////////
    // Unmarshall Soap Message : USM
    /////////////////////////////////////////////////////////////////////////
    public static final String USM_REQUEST_STATE_CODE = "00USMA01";
    public static final String USM_REQUEST_STATE_FAULT_FS =
            "Request input method invoked at illegal time";

    public static final String USM_REQUEST_INPUT_CODE = "00USMA02";
    public static final String USM_REQUEST_INPUT_FAULT_FS =
            "Request input Exception occurred";

    public static final String USM_DOM_FACTORY_CODE = "00USMB01";
    public static final String USM_DOM_FACTORY_FAULT_FS =
        "DocumentBuilder cannot be created which satisfies the configuration requested";

    public static final String USM_DOM_PARSE_IO_CODE = "00USMC01";
    public static final String USM_DOM_PARSE_IO_FAULT_FS =
        "DOM parse IO errors occur";

    public static final String USM_DOM_PARSE_CODE = "00USMC02";
    public static final String USM_DOM_PARSE_FAULT_FS =
        "DOM parse errors occur";

    // unknown error while unmarshalling soap message
    public static final String USM_UNKNOWN_CODE = "00USMU01";
    public static final String USM_UNKNOWN_FAULT_FS =
            "Unknown error unmarshalling soap message";

    /////////////////////////////////////////////////////////////////////////
    // Callback http header : CHH
    /////////////////////////////////////////////////////////////////////////
    public static final String CHH_CODE = "00CHHA01";
    public static final String CHH_FAULT_FS =
            "Error in Callback processHttpHeader";

    public static final String CHH_AUTHORIZATION_CODE = "00CHHA02";
    public static final String CHH_AUTHORIZATION_FAULT_FS =
            "Error in Callback processHttpHeader Authorization";

    /////////////////////////////////////////////////////////////////////////
    // Callback Pre-Action : CPREA
    /////////////////////////////////////////////////////////////////////////
    public static final String CPREA_CODE = "00CPREA01";
    public static final String CPREA_FAULT_FS =
            "Error in Callback PreAction";

/*
    public static final String CPREA_AUTHORIZATION_CODE = "00CPREA02";
    public static final String CPREA_AUTHORIZATION_FAULT_FS =
            "Error Callback PreAction Authorization";
*/

    /////////////////////////////////////////////////////////////////////////
    // Handle Soap Header : HSH
    /////////////////////////////////////////////////////////////////////////
    public static final String HSH_MUST_UNDERSTAND_CODE = "00HSHA01";
    public static final String HSH_MUST_UNDERSTAND_FAULT_FS =
            "SOAP Header must understand element not recognized";

    // This is used to signal XMLA clients supporting Soap header session ids
    // that the client's metadata may no longer be valid.
    public static final String HSH_BAD_SESSION_ID_CODE = "00HSHB01";
    public static final String HSH_BAD_SESSION_ID_FAULT_FS =
            "Bad Session Id, re-start session";

    // unknown error while handle soap header
    public static final String HSH_UNKNOWN_CODE = "00HSHU01";
    public static final String HSH_UNKNOWN_FAULT_FS =
            "Unknown error handle soap header";

    /////////////////////////////////////////////////////////////////////////
    // Handle Soap Body : HSB
    /////////////////////////////////////////////////////////////////////////
    public static final String HSB_BAD_SOAP_BODY_CODE = "00HSBA01";
    public static final String HSB_BAD_SOAP_BODY_FAULT_FS =
            "SOAP Body not correctly formed";

    public static final String HSB_PROCESS_CODE = "00HSBB01";
    public static final String HSB_PROCESS_FAULT_FS =
            "XMLA SOAP Body processing error";

    public static final String HSB_BAD_METHOD_CODE = "00HSBB02";
    public static final String HSB_BAD_METHOD_FAULT_FS =
            "XMLA SOAP bad method";

    public static final String HSB_BAD_METHOD_NS_CODE = "00HSBB03";
    public static final String HSB_BAD_METHOD_NS_FAULT_FS =
            "XMLA SOAP bad method namespace";

    public static final String HSB_BAD_REQUEST_TYPE_CODE = "00HSBB04";
    public static final String HSB_BAD_REQUEST_TYPE_FAULT_FS =
            "XMLA SOAP bad Discover RequestType element";

    public static final String HSB_BAD_RESTRICTIONS_CODE = "00HSBB05";
    public static final String HSB_BAD_RESTRICTIONS_FAULT_FS =
            "XMLA SOAP bad Discover Restrictions element";

    public static final String HSB_BAD_PROPERTIES_CODE = "00HSBB06";
    public static final String HSB_BAD_PROPERTIES_FAULT_FS =
            "XMLA SOAP bad Discover or Execute Properties element";

    public static final String HSB_BAD_COMMAND_CODE = "00HSBB07";
    public static final String HSB_BAD_COMMAND_FAULT_FS =
            "XMLA SOAP bad Execute Command element";

    public static final String HSB_BAD_RESTRICTION_LIST_CODE = "00HSBB08";
    public static final String HSB_BAD_RESTRICTION_LIST_FAULT_FS =
            "XMLA SOAP too many Discover RestrictionList element";

    public static final String HSB_BAD_PROPERTIES_LIST_CODE = "00HSBB09";
    public static final String HSB_BAD_PROPERTIES_LIST_FAULT_FS =
            "XMLA SOAP bad Discover or Execute PropertyList element";

    public static final String HSB_BAD_STATEMENT_CODE = "00HSBB10";
    public static final String HSB_BAD_STATEMENT_FAULT_FS =
            "XMLA SOAP bad Execute Statement element";

    public static final String HSB_BAD_NON_NULLABLE_COLUMN_CODE = "00HSBB16";
    public static final String HSB_BAD_NON_NULLABLE_COLUMN_FAULT_FS =
            "XMLA SOAP non-nullable column";


    public static final String HSB_CONNECTION_DATA_SOURCE_CODE = "00HSBC01";
    public static final String HSB_CONNECTION_DATA_SOURCE_FAULT_FS =
            "XMLA connection datasource not found";

    public static final String HSB_ACCESS_DENIED_CODE = "00HSBC02";
    public static final String HSB_ACCESS_DENIED_FAULT_FS =
            "XMLA connection with role must be authenticated";

    public static final String HSB_PARSE_QUERY_CODE = "00HSBD01";
    public static final String HSB_PARSE_QUERY_FAULT_FS =
        "XMLA MDX parse failed";

    public static final String HSB_EXECUTE_QUERY_CODE = "00HSBD02";
    public static final String HSB_EXECUTE_QUERY_FAULT_FS =
        "XMLA MDX execute failed";

    public static final String HSB_DISCOVER_FORMAT_CODE = "00HSBE01";
    public static final String HSB_DISCOVER_FORMAT_FAULT_FS =
            "XMLA Discover format error";

    public static final String HSB_DRILL_THROUGH_FORMAT_CODE = "00HSBE02";
    public static final String HSB_DRILL_THROUGH_FORMAT_FAULT_FS =
            "XMLA Drill Through format error";

    public static final String HSB_DISCOVER_UNPARSE_CODE = "00HSBE02";
    public static final String HSB_DISCOVER_UNPARSE_FAULT_FS =
            "XMLA Discover unparse results error";

    public static final String HSB_EXECUTE_UNPARSE_CODE = "00HSBE03";
    public static final String HSB_EXECUTE_UNPARSE_FAULT_FS =
            "XMLA Execute unparse results error";

    public static final String HSB_DRILL_THROUGH_NOT_ALLOWED_CODE = "00HSBF01";
    public static final String HSB_DRILL_THROUGH_NOT_ALLOWED_FAULT_FS =
            "XMLA Drill Through not allowed";

    public static final String HSB_DRILL_THROUGH_SQL_CODE = "00HSBF02";
    public static final String HSB_DRILL_THROUGH_SQL_FAULT_FS =
            "XMLA Drill Through SQL error";

    // unknown error while handle soap body
    public static final String HSB_UNKNOWN_CODE = "00HSBU01";
    public static final String HSB_UNKNOWN_FAULT_FS =
            "Unknown error handle soap body";

    /////////////////////////////////////////////////////////////////////////
    // Callback Post-Action : CPOSTA
    /////////////////////////////////////////////////////////////////////////
    public static final String CPOSTA_CODE = "00CPOSTA01";
    public static final String CPOSTA_FAULT_FS =
            "Error in Callback PostAction";

    /////////////////////////////////////////////////////////////////////////
    // Marshall Soap Message : MSM
    /////////////////////////////////////////////////////////////////////////

    // unknown error while marshalling soap message
    public static final String MSM_UNKNOWN_CODE = "00MSMU01";
    public static final String MSM_UNKNOWN_FAULT_FS =
            "Unknown error marshalling soap message";

    /////////////////////////////////////////////////////////////////////////
    // Unknown error : UE
    /////////////////////////////////////////////////////////////////////////
    public static final String UNKNOWN_ERROR_CODE = "00UE001";
    // While this is actually "unknown", for users "internal"
    // is a better term
    public static final String UNKNOWN_ERROR_FAULT_FS = "Internal Error";

    private static final String NUMBERS = "0123456789";

    public enum Literal implements XmlaConstant {
        CATALOG_NAME(
                2, null, 24, ".", NUMBERS, 2,
                "A catalog name in a text command."),
        CATALOG_SEPARATOR(3, ".", 0, null, null, 3, null),
        COLUMN_ALIAS(5, null, -1, "'\"[]", NUMBERS, 5, null),
        COLUMN_NAME(6, null, -1, ".", NUMBERS, 6, null),
        CORRELATION_NAME(7, null, -1, "'\"[]", NUMBERS, 7, null),
        CUBE_NAME(21, null, -1, ".", NUMBERS, 21, null),
        DIMENSION_NAME(22, null, -1, ".", NUMBERS, 22, null),
        HIERARCHY_NAME(23, null, -1, ".", NUMBERS, 23, null),
        LEVEL_NAME(24, null, -1, ".", NUMBERS, 24, null),
        MEMBER_NAME(25, null, -1, ".", NUMBERS, 25, null),
        PROCEDURE_NAME(14, null, -1, ".", NUMBERS, 14, null),
        PROPERTY_NAME(26, null, -1, ".", NUMBERS, 26, null),
        QUOTE(
                15, "[", -1, null, null, 15,
                "The character used in a text command as the opening quote for "
                        + "quoting identifiers that contain special characters."),
        QUOTE_SUFFIX(
                28, "]", -1, null, null, 28,
                "The character used in a text command as the closing quote for "
                        + "quoting identifiers that contain special characters. 1.x "
                        + "providers that use the same character as the prefix and suffix "
                        + "may not return this literal value and can set the lt member of "
                        + "the DBLITERAL structure to DBLITERAL_INVALID if requested."),
        TABLE_NAME(17, null, -1, ".", NUMBERS, 17, null),
        TEXT_COMMAND(
                18, null, -1, null, null, 18,
                "A text command, such as an SQL statement."),
        USER_NAME(19, null, 0, null, null, 19, null);

        // Enum DBLITERALENUM and DBLITERALENUM20, OLEDB.H.
        //
        // public static final int DBLITERAL_INVALID   = 0,
        //   DBLITERAL_BINARY_LITERAL    = 1,
        //   DBLITERAL_CATALOG_NAME  = 2,
        //   DBLITERAL_CATALOG_SEPARATOR = 3,
        //   DBLITERAL_CHAR_LITERAL  = 4,
        //   DBLITERAL_COLUMN_ALIAS  = 5,
        //   DBLITERAL_COLUMN_NAME   = 6,
        //   DBLITERAL_CORRELATION_NAME  = 7,
        //   DBLITERAL_CURSOR_NAME   = 8,
        //   DBLITERAL_ESCAPE_PERCENT    = 9,
        //   DBLITERAL_ESCAPE_UNDERSCORE = 10,
        //   DBLITERAL_INDEX_NAME    = 11,
        //   DBLITERAL_LIKE_PERCENT  = 12,
        //   DBLITERAL_LIKE_UNDERSCORE   = 13,
        //   DBLITERAL_PROCEDURE_NAME    = 14,
        //   DBLITERAL_QUOTE = 15,
        //   DBLITERAL_QUOTE_PREFIX = DBLITERAL_QUOTE,
        //   DBLITERAL_SCHEMA_NAME   = 16,
        //   DBLITERAL_TABLE_NAME    = 17,
        //   DBLITERAL_TEXT_COMMAND  = 18,
        //   DBLITERAL_USER_NAME = 19,
        //   DBLITERAL_VIEW_NAME = 20,
        //   DBLITERAL_CUBE_NAME = 21,
        //   DBLITERAL_DIMENSION_NAME    = 22,
        //   DBLITERAL_HIERARCHY_NAME    = 23,
        //   DBLITERAL_LEVEL_NAME    = 24,
        //   DBLITERAL_MEMBER_NAME   = 25,
        //   DBLITERAL_PROPERTY_NAME = 26,
        //   DBLITERAL_SCHEMA_SEPARATOR  = 27,
        //   DBLITERAL_QUOTE_SUFFIX  = 28;

        private int xmlaOrdinal;
        private final String literalValue;
        private final int literalMaxLength;
        private final String literalInvalidChars;
        private final String literalInvalidStartingChars;
        private final int literalNameEnumValue;
        private final String description;

        Literal(
                int xmlaOrdinal,
                String literalValue,
                int literalMaxLength,
                String literalInvalidChars,
                String literalInvalidStartingChars,
                int literalNameEnumValue,
                String description)
        {
            this.xmlaOrdinal = xmlaOrdinal;
            this.literalValue = literalValue;
            this.literalMaxLength = literalMaxLength;
            this.literalInvalidChars = literalInvalidChars;
            this.literalInvalidStartingChars = literalInvalidStartingChars;
            this.literalNameEnumValue = literalNameEnumValue;
            this.description = description;
        }

        public String getLiteralName() {
            return xmlaName();
        }

        public String getLiteralValue() {
            return literalValue;
        }

        public String getLiteralInvalidChars() {
            return literalInvalidChars;
        }

        public String getLiteralInvalidStartingChars() {
            return literalInvalidStartingChars;
        }

        public int getLiteralMaxLength() {
            return literalMaxLength;
        }

        public int getLiteralNameEnumValue() {
            return literalNameEnumValue;
        }

        @Override
		public String xmlaName() {
            return "DBLITERAL_" + name();
        }

        @Override
		public String getDescription() {
            return description;
        }

        @Override
		public int xmlaOrdinal() {
            return xmlaOrdinal;
        }
    }

    public enum VisualMode implements XmlaConstant {
        DEFAULT(
            0,
            "Provider-dependent. In Microsoft SQL Server 2000 Analysis "
                + "Services, this is equivalent to "
                + "DBPROPVAL_VISUAL_MODE_ORIGINAL."),
        VISUAL(
            1,
            "Visual totals are enabled."),
        ORIGINAL(
            2,
            "Visual totals are not enabled.");

        private final int xmlaOrdinal;
        private final String description;

        VisualMode(
            int xmlaOrdinal, String description)
        {
            this.xmlaOrdinal = xmlaOrdinal;
            this.description = description;
        }

        public String xmlaName() {
            return "DBPROPVAL_VISUAL_MODE_";
        }

        public String getDescription() {
            return description;
        }

        public int xmlaOrdinal() {
            return xmlaOrdinal;
        }
    }

    public enum Method implements XmlaConstant {
        DISCOVER,
        EXECUTE,
        DISCOVER_AND_EXECUTE;

        public String xmlaName() {
            return name();
        }

        public String getDescription() {
            return null;
        }

        public int xmlaOrdinal() {
            return -1;
        }
    }

    public enum Access implements XmlaConstant {
        Read(1),
        Write(2),
        ReadWrite(3);

        private final int xmlaOrdinal;

        Access(int xmlaOrdinal) {
            this.xmlaOrdinal = xmlaOrdinal;
        }

        public String xmlaName() {
            return name();
        }

        public String getDescription() {
            return null;
        }

        public int xmlaOrdinal() {
            return xmlaOrdinal;
        }
    }

    public enum AuthenticationMode implements XmlaConstant {
        Unauthenticated("no user ID or password needs to be sent."),
        Authenticated(
            "User ID and Password must be included in the information required "
                + "for the connection."),
        Integrated(
            "the data source uses the underlying security to determine "
                + "authorization, such as Integrated Security provided by "
                + "Microsoft Internet Information Services (IIS).");

        private final String description;

        AuthenticationMode(String description) {
            this.description = description;
        }

        public String xmlaName() {
            return name();
        }

        public String getDescription() {
            return description;
        }

        public int xmlaOrdinal() {
            return -1;
        }
    }

    public enum ProviderType implements XmlaConstant {
        TDP("tabular data provider."),
        MDP("multidimensional data provider."),
        DMP(
            "data mining provider. A DMP provider implements the OLE DB for "
                + "Data Mining specification.");

        private final String description;

        private ProviderType(String description) {
            this.description = description;
        }

        public String xmlaName() {
            return name();
        }

        public String getDescription() {
            return description;
        }

        public int xmlaOrdinal() {
            return -1;
        }
    }

    public enum Updateable implements XmlaConstant {
        MD_MASK_ENABLED(
            0x00000000,
            "The cell can be updated."),

        MD_MASK_NOT_ENABLED(
            0x10000000,
            "The cell cannot be updated."),

        CELL_UPDATE_ENABLED(
            0x00000001,
            "Cell can be updated in the cellset."),

        CELL_UPDATE_ENABLED_WITH_UPDATE(
            0x00000002,
            "The cell can be updated with an update statement. The update may "
                + "fail if a leaf cell is updated that is not write-enabled."),

        CELL_UPDATE_NOT_ENABLED_FORMULA(
            0x10000001,
            "The cell cannot be updated because the cell has a calculated "
                + "member among its coordinates; the cell was retrieved with a set "
                + "in the where clause. A cell can be updated even though a "
                + "formula affects, or a calculated cell is on, the value of a "
                + "cell (is somewhere along the aggregation path). In this "
                + "scenario, the final value of the cell may not be the updated "
                + "value, because the calculation will affect the result."),

        CELL_UPDATE_NOT_ENABLED_NONSUM_MEASURE(
            0x10000002,
            "The cell cannot be updated because non-sum measures (count, min, "
                + "max, distinct count, semi-additive) can not be updated."),

        CELL_UPDATE_NOT_ENABLED_NACELL_VIRTUALCUBE(
            0x10000003,
            "The cell cannot be updated because the cell does not exist as it "
                + "is at the intersection of a measure and a dimension member "
                + "unrelated to the measure’s measure group."),

        CELL_UPDATE_NOT_ENABLED_SECURE(
            0x10000005,
            "The cell cannot be updated because the cell is secured."),

        CELL_UPDATE_NOT_ENABLED_CALCLEVEL(
            0x10000006,
            "Reserved for future use."),

        CELL_UPDATE_NOT_ENABLED_CANNOTUPDATE(
            0x10000007,
            "The cell cannot be updated because of internal reasons."),

        CELL_UPDATE_NOT_ENABLED_INVALIDDIMENSIONTYPE(
            0x10000009,
            "The cell cannot be updated because update is not supported in "
                + "mining model, indirect, or data mining dimensions.");

        private final int xmlaOrdinal;
        private final String description;

        Updateable(int xmlaOrdinal, String description) {
            this.xmlaOrdinal = xmlaOrdinal;
            this.description = description;
        }

        public String xmlaName() {
            return name();
        }

        public String getDescription() {
            return description;
        }

        public int xmlaOrdinal() {
            return xmlaOrdinal;
        }
    }

    public enum FontFlag implements XmlaConstant {
        BOLD(1),
        ITALIC(2),
        UNDERLINE(4),
        STRIKEOUT(8);

        private final int xmlaOrdinal;

        FontFlag(int xmlaOrdinal) {
            this.xmlaOrdinal = xmlaOrdinal;
        }

        public String xmlaName() {
            return "MDFF_" + name();
        }

        public String getDescription() {
            return name();
        }

        public int xmlaOrdinal() {
            return xmlaOrdinal;
        }
    }

    /**
     * Action type.
     *
     * <p>Fields correspond to XMLA constants MDACTION_TYPE_URL (0x01),
     * MDACTION_TYPE_HTML (0x02),
     * MDACTION_TYPE_STATEMENT (0x04),
     * MDACTION_TYPE_DATASET (0x08),
     * MDACTION_TYPE_ROWSET (0x10),
     * MDACTION_TYPE_COMMANDLINE (0x20),
     * MDACTION_TYPE_PROPRIETARY (0x40),
     * MDACTION_TYPE_REPORT (0x80),
     * MDACTION_TYPE_DRILLTHROUGH (0x100)</p>
     */
    public enum ActionType implements XmlaConstant {
        URL(0x01),
        HTML(0x02),
        STATEMENT(0x04),
        DATASET(0x08),
        ROWSET(0x10),
        COMMANDLINE(0x20),
        PROPRIETARY(0x40),
        REPORT(0x80),
        DRILLTHROUGH(0x100);

        private final int xmlaOrdinal;

        ActionType(int xmlaOrdinal) {
            this.xmlaOrdinal = xmlaOrdinal;
        }

        public String xmlaName() {
            return "MDACTION_TYPE_" + name();
        }

        public String getDescription() {
            return name();
        }

        public int xmlaOrdinal() {
            return xmlaOrdinal;
        }
    }

    /**
     * How the COORDINATE restriction column is interpreted.
     *
     * <p>Fields correspond to the XMLA values
     * MDACTION_COORDINATE_CUBE (1),
     * MDACTION_COORDINATE_DIMENSION (2)
     * MDACTION_COORDINATE_LEVEL (3),
     * MDACTION_COORDINATE_MEMBER (4),
     * MDACTION_COORDINATE_SET (5),
     * MDACTION_COORDINATE_CELL (6)</p>
     */
    public enum CoordinateType implements XmlaConstant {
        CUBE(1),
        DIMENSION(2),
        LEVEL(3),
        MEMBER(4),
        SET(5),
        CELL(6);

        private final int xmlaOrdinal;

        CoordinateType(int xmlaOrdinal) {
            this.xmlaOrdinal = xmlaOrdinal;
        }

        public String xmlaName() {
            return "MDACTION_COORDINATE_" + name();
        }

        public String getDescription() {
            return name();
        }

        public int xmlaOrdinal() {
            return xmlaOrdinal;
        }
    }

    /**
     * The only OLE DB Types Indicators returned by SQL Server are thoses coded
     * below.
     */
    public enum DBType implements XmlaConstant {
        // The following values exactly match VARENUM
        // in Automation and may be used in VARIANT.
        I4(
            "INTEGER", 3, "DBTYPE_I4", "A four-byte, signed integer: INTEGER"),

        R8(
            "DOUBLE", 5, "DBTYPE_R8",
            "A double-precision floating-point value: Double"),

        CY(
            "CURRENCY", 6, "DBTYPE_CY",
            "A currency value: LARGE_INTEGER, Currency is a fixed-point number "
                + "with four digits to the right of the decimal point. It is "
                + "stored in an eight-byte signed integer, scaled by 10,000."),

        BOOL(
            "BOOLEAN", 11, "DBTYPE_BOOL",
            "A Boolean value stored in the same way as in Automation: "
                + "VARIANT_BOOL; 0 means false and ~0 (bitwise, the value is not "
                + "0; that is, all bits are set to 1) means true."),

        /**
         * Used by SQL Server for value.
         */
        VARIANT(
            "VARIANT", 12, "DBTYPE_VARIANT", "An Automation VARIANT"),

        /**
         * Used by SQL Server for font size.
         */
        UI2("UNSIGNED_SHORT", 18, "DBTYPE_UI2", "A two-byte, unsigned integer"),

        /**
         * Used by SQL Server for colors, font flags and cell ordinal.
         */
        UI4(
            "UNSIGNED_INTEGER", 19, "DBTYPE_UI4",
            "A four-byte, unsigned integer"),

        // The following values exactly match VARENUM
        // in Automation but cannot be used in VARIANT.
        I8(
            "LARGE_INTEGER", 20, "DBTYPE_I8",
            "An eight-byte, signed integer: LARGE_INTEGER"),

        // The following values are not in VARENUM in OLE.
        WSTR(
            "STRING", 130, "DBTYPE_WSTR",
            "A null-terminated Unicode character string: wchar_t[length]; If "
                + "DBTYPE_WSTR is used by itself, the number of bytes allocated "
                + "for the string, including the null-termination character, is "
                + "specified by cbMaxLen in the DBBINDING structure. If "
                + "DBTYPE_WSTR is combined with DBTYPE_BYREF, the number of bytes "
                + "allocated for the string, including the null-termination "
                + "character, is at least the length of the string plus two. In "
                + "either case, the actual length of the string is determined from "
                + "the bound length value. The maximum length of the string is the "
                + "number of allocated bytes divided by sizeof(wchar_t) and "
                + "truncated to the nearest integer.");


        public final String userName;

        private final int xmlaOrdinal;

        private String description;

        DBType(
            String userName,
            int xmlaOrdinal,
            String dbTypeIndicator,
            String description)
        {
            this.userName = userName;
            this.xmlaOrdinal = xmlaOrdinal;
            this.description = description;
            assert xmlaName().equals(dbTypeIndicator);
        }

        public String xmlaName() {
            return "DBTYPE_" + name();
        }

        public String getDescription() {
            return description;
        }

        public int xmlaOrdinal() {
            return xmlaOrdinal;
        }
    }

    public enum Format implements XmlaConstant {
        Tabular(
            "a flat or hierarchical rowset. Similar to the XML RAW format in "
                + "SQL. The Format property should be set to Tabular for OLE DB "
                + "for Data Mining commands."),
        Multidimensional(
            "Indicates that the result set will use the MDDataSet format "
                + "(Execute method only)."),
        Native(
            "The client does not request a specific format, so the provider "
                + "may return the format  appropriate to the query. (The actual "
                + "result type is identified by namespace of the result.)");

        private final String description;

        Format(String description) {
            this.description = description;
        }

        public String xmlaName() {
            return name();
        }

        public String getDescription() {
            return description;
        }

        public int xmlaOrdinal() {
            return -1;
        }
    }

    public enum AxisFormat implements XmlaConstant {
        TupleFormat(
            "The MDDataSet axis is made up of one or more CrossProduct "
                + "elements."),
        ClusterFormat(
            "Analysis Services uses the TupleFormat format for this setting."),
        CustomFormat(
            "The MDDataSet axis contains one or more Tuple elements.");

        private final String description;

        AxisFormat(String description) {
            this.description = description;
        }

        public String xmlaName() {
            return name();
        }

        public String getDescription() {
            return description;
        }

        public int xmlaOrdinal() {
            return -1;
        }
    }

    public enum Content {
        None,
        Schema,
        Data,
        SchemaData,
        DataOmitDefaultSlicer,
        DataIncludeDefaultSlicer;

        /** The content type default value - shared across more than one file */
        public static final Content DEFAULT = SchemaData;
    }

    public enum MdxSupport {
        Core
    }

    public enum StateSupport {
        None,
        Sessions
    }

    public interface EnumWithDesc {
        String getDescription();
    }
}

// End XmlaConstants.java
