/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2003-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara
// Copyright (C) 2019 Topsoft
// All Rights Reserved.
*/

package org.eclipse.daanse.xmla.api;

import java.util.EnumSet;
import java.util.Enumeration;
import java.util.Set;

/**
 * Defines an XML for Analysis Property.
 *
 * @author jhyde
 * @since May 2, 2003
 */
public enum PropertyDefinition {
    AxisFormat(
        RowsetDefinitionType.ENUMERATION,
        EnumSet.allOf(XmlaConstants.AxisFormat.class),
        XmlaConstants.Access.Write,
        "",
        XmlaConstants.Method.EXECUTE,
        "Determines the format used within an MDDataSet result set to describe the axes of the multidimensional dataset. This property can have the values listed in the following table: TupleFormat (default), ClusterFormat, CustomFormat."),

    BeginRange(
        RowsetDefinitionType.INTEGER,
        null,
        XmlaConstants.Access.Write,
        "-1",
        XmlaConstants.Method.EXECUTE,
        """
        Contains a zero-based integer value corresponding to a CellOrdinal attribute value. (The CellOrdinal attribute is part of the Cell element in the CellData section of MDDataSet.)
        Used together with the EndRange property, the client application can use this property to restrict an OLAP dataset returned by a command to a specific range of cells. If -1 is specified, all cells up to the cell specified in the EndRange property are returned.
        The default value for this property is -1."""),

    Catalog(
        RowsetDefinitionType.STRING,
        null,
        XmlaConstants.Access.ReadWrite,
        "",
        XmlaConstants.Method.DISCOVER_AND_EXECUTE,
        """
        When establishing a session with an Analysis Services instance to send an XMLA command, this property is equivalent to the OLE DB property, DBPROP_INIT_CATALOG.
        When you set this property during a session to change the current database for the session, this property is equivalent to the OLE DB property, DBPROP_CURRENTCATALOG.
        The default value for this property is an empty string."""),

    Content(
        RowsetDefinitionType.ENUM_STRING,
        EnumSet.allOf(XmlaConstants.Content.class),
        XmlaConstants.Access.Write,
        XmlaConstants.Content.DEFAULT.name(),
        XmlaConstants.Method.DISCOVER_AND_EXECUTE,
        """
        An enumerator that specifies what type of data is returned in the result set.
        None: Allows the structure of the command to be verified, but not executed. Analogous to using Prepare to check syntax, and so on.
        Schema: Contains the XML schema (which indicates column information, and so on) that relates to the requested query.
        Data: Contains only the data that was requested.
        SchemaData: Returns both the schema information as well as the data."""),

    Cube(
        RowsetDefinitionType.STRING,
        null,
        XmlaConstants.Access.ReadWrite,
        "",
        XmlaConstants.Method.EXECUTE,
        "The cube context for the Command parameter. If the command contains a cube name (such as an MDX FROM clause) the setting of this property is ignored."),

    DataSourceInfo(
        RowsetDefinitionType.STRING,
        null,
        XmlaConstants.Access.ReadWrite,
        "",
        XmlaConstants.Method.DISCOVER_AND_EXECUTE,
        "A string containing provider specific information, required to access the data source."),

    // Mondrian-specific extension to XMLA.
    Deep(
        RowsetDefinitionType.BOOLEAN,
        null,
        XmlaConstants.Access.ReadWrite,
        "",
        XmlaConstants.Method.DISCOVER,
        "In an MDSCHEMA_CUBES request, whether to include sub-elements "
        + "(dimensions, hierarchies, levels, measures, named sets) of each "
        + "cube."),

    // Mondrian-specific extension to XMLA.
    EmitInvisibleMembers(
        RowsetDefinitionType.BOOLEAN,
        null,
        XmlaConstants.Access.ReadWrite,
        "",
        XmlaConstants.Method.DISCOVER,
        "Whether to include members whose VISIBLE property is false, or "
        + "measures whose MEASURE_IS_VISIBLE property is false."),

    EndRange(
        RowsetDefinitionType.INTEGER,
        null,
        XmlaConstants.Access.Write,
        "-1",
        XmlaConstants.Method.EXECUTE,
        "An integer value corresponding to a CellOrdinal used to restrict an MDDataSet returned by a command to a specific range of cells. Used in conjunction with the BeginRange property. If unspecified, all cells are returned in the rowset. The value -1 means unspecified."),

    Format(
        RowsetDefinitionType.ENUM_STRING,
        EnumSet.allOf(XmlaConstants.Format.class),
        XmlaConstants.Access.Write,
        "Native",
        XmlaConstants.Method.DISCOVER_AND_EXECUTE,
        """
        Enumerator that determines the format of the returned result set. Values include:
        Tabular: a flat or hierarchical rowset. Similar to the XML RAW format in SQL. The Format property should be set to Tabular for OLE DB for Data Mining commands.
        Multidimensional: Indicates that the result set will use the MDDataSet format (Execute method only).
        Native: The client does not request a specific format, so the provider may return the format  appropriate to the query. (The actual result type is identified by namespace of the result.)"""),

    LocaleIdentifier(
        RowsetDefinitionType.UNSIGNED_INTEGER,
        null,
        XmlaConstants.Access.ReadWrite,
        "None",
        XmlaConstants.Method.DISCOVER_AND_EXECUTE,
        """
        Use this to read or set the numeric locale identifier for this request. The default is provider-specific.
        For the complete hexadecimal list of language identifiers, search on "Language Identifiers" in the MSDN Library at http://www.msdn.microsoft.com.
        As an extension to the XMLA standard, Mondrian also allows locale codes as specified by ISO-639 and ISO-3166 and as used by Java; for example 'en-US'.
        """),

    MDXSupport(
        RowsetDefinitionType.ENUM_STRING,
        EnumSet.allOf(XmlaConstants.MdxSupport.class),
        XmlaConstants.Access.Read,
        "Core",
        XmlaConstants.Method.DISCOVER,
        "Enumeration that describes the degree of MDX support. At initial release Core is the only value in the enumeration. In future releases, other values will be defined for this enumeration."),

    Password(
        RowsetDefinitionType.STRING,
        null,
        XmlaConstants.Access.Read,
        "",
        XmlaConstants.Method.DISCOVER_AND_EXECUTE,
        "This property is deprecated in XMLA 1.1. To support legacy applications, the provider accepts but ignores the Password property setting when it is used with the Discover and Execute method"),

    ProviderName(
        RowsetDefinitionType.STRING,
        null,
        XmlaConstants.Access.Read,
        "Mondrian XML for Analysis Provider",
        XmlaConstants.Method.DISCOVER,
        "The XML for Analysis Provider name."),

    ProviderVersion(
        RowsetDefinitionType.STRING,
        null,
        XmlaConstants.Access.Read,
	"10.50.1600.1",
        //MondrianServer.forId(null).getVersion().getVersionString(),
        XmlaConstants.Method.DISCOVER,
        "The version of the Mondrian XMLA Provider"),

    // Mondrian-specific extension to XMLA.
    /**
     * @see Enumeration.ResponseMimeType
     */
    ResponseMimeType(
        RowsetDefinitionType.STRING,
        null,
        XmlaConstants.Access.ReadWrite,
        "None",
        XmlaConstants.Method.DISCOVER_AND_EXECUTE,
        "Accepted mime type for RPC response; accepted are 'text/xml' "
        + "(default), 'application/xml' (equivalent to 'text/xml'), or "
        + "'application/json'. If not specified, value in the 'Accept' header "
        + "of the HTTP request is used."),

    StateSupport(
        RowsetDefinitionType.ENUM_STRING,
        EnumSet.allOf(XmlaConstants.StateSupport.class),
        XmlaConstants.Access.Read,
        "None",
        XmlaConstants.Method.DISCOVER,
        """
        Property that specifies the degree of support in the provider for state. For information about state in XML for Analysis, see "Support for Statefulness in XML for Analysis." Minimum enumeration values are as follows:
        None - No support for sessions or stateful operations.
        Sessions - Provider supports sessions."""),

    Timeout(
        RowsetDefinitionType.UNSIGNED_INTEGER,
        null,
        XmlaConstants.Access.ReadWrite,
        "Undefined",
        XmlaConstants.Method.DISCOVER_AND_EXECUTE,
        "A numeric time-out specifying in seconds the amount of time to wait for a request to be successful."),

    UserName(
        RowsetDefinitionType.STRING,
        null,
        XmlaConstants.Access.Read,
        "",
        XmlaConstants.Method.DISCOVER_AND_EXECUTE,
        "Returns the UserName the server associates with the command.\n"
        + "This property is deprecated as writeable in XMLA 1.1. To support legacy applications, servers accept but ignore the password setting when it is used with the Execute method."),

    VisualMode(
        RowsetDefinitionType.ENUMERATION,
        EnumSet.allOf(XmlaConstants.VisualMode.class),
        XmlaConstants.Access.Write,
        Integer.toString(XmlaConstants.VisualMode.VISUAL.ordinal()),
        XmlaConstants.Method.DISCOVER_AND_EXECUTE,
        "This property is equivalent to the OLE DB property, MDPROP_VISUALMODE.\n"
        + "The default value for this property is zero (0), equivalent to DBPROPVAL_VISUAL_MODE_DEFAULT."),

    // mondrian-specific property for advanced drill-through
    TableFields(
        RowsetDefinitionType.STRING,
        null,
        XmlaConstants.Access.Read,
        "",
        XmlaConstants.Method.DISCOVER_AND_EXECUTE,
        "List of fields to return for drill-through.\n"
        + "The default value of this property is the empty string,"
        + "in which case, all fields are returned."),

    // mondrian-specific property for advanced drill-through
    AdvancedFlag(
        RowsetDefinitionType.BOOLEAN,
        null,
        XmlaConstants.Access.Read,
        "false",
        XmlaConstants.Method.DISCOVER_AND_EXECUTE,
        ""),

    // Microsoft-specific XMLA definition.
    SafetyOptions(
        RowsetDefinitionType.INTEGER,
        null,
        XmlaConstants.Access.ReadWrite,
        "0",
        XmlaConstants.Method.DISCOVER_AND_EXECUTE,
        "Determines whether unsafe libraries can be registered and loaded by client applications."),

    // Microsoft-specific XMLA definition.
    MdxMissingMemberMode(
        RowsetDefinitionType.STRING,
        null,
        XmlaConstants.Access.Write,
        "",
        XmlaConstants.Method.DISCOVER_AND_EXECUTE,
        "Indicates whether missing members are ignored in MDX statements."),

    // Microsoft-specific XMLA definition.
    DbpropMsmdMDXCompatibility(
            RowsetDefinitionType.INTEGER,
            null,
            XmlaConstants.Access.ReadWrite,
            "0",
            XmlaConstants.Method.DISCOVER_AND_EXECUTE,
            "An enumeration value that determines how placeholder members in a ragged or\n" +
                    "unbalanced hierarchy are treated."),

    // Microsoft-specific XMLA definition.
    MdpropMdxSubqueries(
            RowsetDefinitionType.INTEGER,
            null,
            XmlaConstants.Access.Read,
            "",
            XmlaConstants.Method.DISCOVER,
            "A bitmask that indicates the level of support for subqueries in MDX."),

    // Microsoft-specific XMLA definition.
    ClientProcessID(
            RowsetDefinitionType.INTEGER,
            null,
            XmlaConstants.Access.ReadWrite,
            "0",
            XmlaConstants.Method.DISCOVER_AND_EXECUTE,
            "The ID of the client process."),

//    // Microsoft-specific XMLA definition.
//    MdpropMdxFormulas(
//            RowsetDefinitionType.Integer,
//            null,
//            XmlaConstants.Access.ReadWrite,
//            "63",
//            XmlaConstants.Method.DISCOVER_AND_EXECUTE,
//            ""),

    // Microsoft-specific XMLA definition.
    SspropInitAppName(
            RowsetDefinitionType.STRING,
            null,
            XmlaConstants.Access.ReadWrite,
            "",
            XmlaConstants.Method.DISCOVER_AND_EXECUTE,
            "The name of the client application."),

    // Microsoft-specific XMLA definition.
    DbpropMsmdSubqueries(
            RowsetDefinitionType.INTEGER,
            null,
            XmlaConstants.Access.ReadWrite,
            "1",
            XmlaConstants.Method.DISCOVER_AND_EXECUTE,
            "An enumeration value that determines the behavior of subqueries.")
            ;

    final RowsetDefinitionType type;
    final Set<? extends Enum> enumSet;
    final XmlaConstants.Access access;
    final XmlaConstants.Method usage;
    final String value;
    final String description;

    PropertyDefinition(
        RowsetDefinitionType type,
        Set<? extends Enum> enumSet,
        XmlaConstants.Access access,
        String value,
        XmlaConstants.Method usage,
        String description)
    {
        // Line endings must be UNIX style (LF) not Windows style (LF+CR).
        // Thus the client will receive the same XML, regardless
        // of the server O/S.
        assert description.indexOf('\r') == -1;
        assert value.indexOf('\r') == -1;
        assert (enumSet != null) == type.isEnum();
        this.type = type;
        this.enumSet = enumSet;
        this.access = access;
        this.usage = usage;
        this.value = value;
        this.description = description;
    }

    /**
     * Returns the description of this PropertyDefinition.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    public RowsetDefinitionType getType() {
        return type;
    }

    public XmlaConstants.Access getAccess() {
        return access;
    }

    public String getValue() {
        return value;
    }
}
