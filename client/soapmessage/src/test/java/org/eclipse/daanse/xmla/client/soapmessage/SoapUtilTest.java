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
package org.eclipse.daanse.xmla.client.soapmessage;

import static org.eclipse.daanse.xmla.client.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.ID;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.NAME;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.NAME_LC;
import static org.eclipse.daanse.xmla.client.soapmessage.Constants.VALUE2;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.engine.ImpersonationInfo;
import org.eclipse.daanse.xmla.api.engine300.AttributeHierarchyProcessingState;
import org.eclipse.daanse.xmla.api.engine300.CalculationPropertiesVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300.DimensionAttributeVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300.HierarchyVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300.RelationshipEndVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300_300.Relationship;
import org.eclipse.daanse.xmla.api.engine300_300.RelationshipEnd;
import org.eclipse.daanse.xmla.api.engine300_300.RelationshipEndTranslation;
import org.eclipse.daanse.xmla.api.engine300_300.Relationships;
import org.eclipse.daanse.xmla.api.engine300_300.XEvent;
import org.eclipse.daanse.xmla.api.execute.ExecuteParameter;
import org.eclipse.daanse.xmla.api.xmla.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xmlunit.assertj3.XmlAssert;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;

class SoapUtilTest {

    private MessageFactory mf;
    private SOAPMessage message;
    private SOAPElement soapElement;
    private List<Annotation> annotations;

    @BeforeEach
    void beforeEach() throws SOAPException {
        mf = MessageFactory.newInstance();
        message = mf.createMessage();
        soapElement = message.getSOAPBody();
        annotations = createAnnotationList();
    }

    @Test
    void addChildElementCancelTest() throws Exception {

        SoapUtil.addChildElementCancel(soapElement, createCancel());

        XmlAssert xmlAssert = XMLUtil.createAssert(message);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Cancel").exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Cancel/ConnectionID").isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Cancel/SessionID").isEqualTo("SessionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Cancel/SPID").isEqualTo("10");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Cancel/CancelAssociated").isEqualTo(true);
    }

    @Test
    void addChildElementExecuteParameterTest() throws Exception {
        SoapUtil.addChildElementExecuteParameter(soapElement, createExecuteParameter());

        XmlAssert xmlAssert = XMLUtil.createAssert(message);
        xmlAssert.hasXPath("/SOAP:Envelope");
        checkExecuteParameter(xmlAssert, "/SOAP:Envelope/SOAP:Body");
    }

    @Test
    void addChildElementTraceTest() throws Exception {
        SoapUtil.addChildElementTrace(soapElement, createTrace());
        XmlAssert xmlAssert = XMLUtil.createAssert(message);
        xmlAssert.hasXPath("/SOAP:Envelope");
        checkTrace(xmlAssert, "/SOAP:Envelope/SOAP:Body");
    }

    @Test
    void addChildElementClearCacheTest() throws Exception {
        SoapUtil.addChildElementClearCache(soapElement, createClearCache());
        XmlAssert xmlAssert = XMLUtil.createAssert(message);

        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/ClearCache").exist();
        checkObjectReference(xmlAssert, "/SOAP:Envelope/SOAP:Body/ClearCache");
    }

    @Test
    void addChildElementMajorObjectTest() throws Exception {
        SoapUtil.addChildElementMajorObject(soapElement, createMajorObject());
        XmlAssert xmlAssert = XMLUtil.createAssert(message);
        checkMajorObject(xmlAssert, "/SOAP:Envelope/SOAP:Body");

    }

    @Test
    void addChildElementAlterTest() throws Exception {
        SoapUtil.addChildElementAlter(soapElement, createAlter());
        XmlAssert xmlAssert = XMLUtil.createAssert(message);
        checkAlter(xmlAssert, "/SOAP:Envelope/SOAP:Body");

    }

    private void checkMajorObject(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/ObjectDefinition").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAggregationDesign(xmlAssert, p);
        checkAssembly(xmlAssert, p);
        checkCube(xmlAssert, p);
        checkDatabase(xmlAssert, p);
        checkDataSource(xmlAssert, p);
        checkDataSourceView(xmlAssert, p);
        checkDimension(xmlAssert, p);
        checkMdxScript(xmlAssert, p);
        checkMeasureGroup(xmlAssert, p);
        checkMiningModel(xmlAssert, p);
        checkMiningStructure(xmlAssert, p);
        checkPartition(xmlAssert, p);
        checkPermission(xmlAssert, p);
        checkPerspective(xmlAssert, p);
        checkRole(xmlAssert, p);
        checkServer(xmlAssert, p);
        checkTrace(xmlAssert, p);
    }

    private MajorObject createMajorObject() {
        MajorObject it = mock(MajorObject.class);
        AggregationDesign aggregationDesign = createAggregationDesign();
        Assembly assembly = createAssembly();
        Cube cube = createCube();
        Database database = createDatabase();
        DataSource dataSource = createDataSource();
        DataSourceView dataSourceView = createDataSourceView();
        Dimension dimension = createDimension();
        MdxScript mdxScript = createMdxScript();
        MeasureGroup measureGroup = createMeasureGroup();
        MiningModel miningModel = createMiningModel();
        MiningStructure miningStructure = createMiningStructure();
        Partition partition = createPartition();
        Permission permission = createPermission();
        Perspective perspective = createPerspective();
        Role role = createRole();
        Server server = createServer();
        Trace trace = createTrace();
        when(it.aggregationDesign()).thenReturn(aggregationDesign);
        when(it.assembly()).thenReturn(assembly);
        when(it.cube()).thenReturn(cube);
        when(it.database()).thenReturn(database);
        when(it.dataSource()).thenReturn(dataSource);
        when(it.dataSourceView()).thenReturn(dataSourceView);
        when(it.dimension()).thenReturn(dimension);
        when(it.mdxScript()).thenReturn(mdxScript);
        when(it.measureGroup()).thenReturn(measureGroup);
        when(it.miningModel()).thenReturn(miningModel);
        when(it.miningStructure()).thenReturn(miningStructure);
        when(it.partition()).thenReturn(partition);
        when(it.permission()).thenReturn(permission);
        when(it.perspective()).thenReturn(perspective);
        when(it.role()).thenReturn(role);
        when(it.server()).thenReturn(server);
        when(it.trace()).thenReturn(trace);
        return it;
    }

    private Server createServer() {
        Server server = mock(Server.class);

        when(server.name()).thenReturn("name");
        when(server.id()).thenReturn("id");
        when(server.createdTimestamp()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(server.lastSchemaUpdate()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(server.description()).thenReturn("description");
        when(server.annotations()).thenReturn(annotations);
        when(server.productName()).thenReturn("productName");
        when(server.edition()).thenReturn("edition");
        when(server.editionID()).thenReturn(10l);
        when(server.version()).thenReturn("version");
        when(server.serverMode()).thenReturn("serverMode");
        when(server.productLevel()).thenReturn("productLevel");
        when(server.defaultCompatibilityLevel()).thenReturn(11l);
        when(server.supportedCompatibilityLevels()).thenReturn("supportedCompatibilityLevels");
        List<Database> databases = List.of(createDatabase());
        when(server.databases()).thenReturn(databases);
        List<Assembly> assemblies = List.of(createAssembly());
        when(server.assemblies()).thenReturn(assemblies);
        List<Trace> traces = List.of(createTrace());
        when(server.traces()).thenReturn(traces);
        List<Role> roles = List.of(createRole());
        when(server.roles()).thenReturn(roles);
        List<ServerProperty> serverProperties = List.of(createServerProperty());
        when(server.serverProperties()).thenReturn(serverProperties);
        return server;
    }

    private AggregationDesign createAggregationDesign() {
        AggregationDesign it = mock(AggregationDesign.class);

        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn(Optional.of("id"));
        when(it.createdTimestamp()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.lastSchemaUpdate()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.description()).thenReturn(Optional.of("description"));
        Optional<List<Annotation>> annotations = Optional.of(this.annotations);
        when(it.annotations()).thenReturn(annotations);
        when(it.estimatedRows()).thenReturn(Optional.of(10l));
        Optional<List<AggregationDesignDimension>> dimensions = Optional
                .of(List.of(createAggregationDesignDimension()));
        when(it.dimensions()).thenReturn(dimensions);
        Optional<List<Aggregation>> aggregations = Optional.of(List.of(createAggregation()));
        when(it.aggregations()).thenReturn(aggregations);
        when(it.estimatedPerformanceGain()).thenReturn(Optional.of(11));
        return it;
    }

    private AggregationDesignDimension createAggregationDesignDimension() {
        AggregationDesignDimension it = mock(AggregationDesignDimension.class);
        when(it.cubeDimensionID()).thenReturn("cubeDimensionID");
        Optional<List<AggregationDesignAttribute>> attributes = Optional
                .of(List.of(createAggregationDesignAttribute()));
        when(it.attributes()).thenReturn(attributes);
        Optional<List<Annotation>> annotations = Optional.ofNullable(this.annotations);
        when(it.annotations()).thenReturn(annotations);
        return it;
    }

    private AggregationDesignAttribute createAggregationDesignAttribute() {
        AggregationDesignAttribute it = mock(AggregationDesignAttribute.class);
        when(it.attributeID()).thenReturn("attributeID");
        when(it.estimatedCount()).thenReturn(Optional.of(10l));
        return it;
    }

    private Aggregation createAggregation() {
        Aggregation it = mock(Aggregation.class);
        when(it.id()).thenReturn(Optional.of("id"));
        when(it.name()).thenReturn("name");
        Optional<List<AggregationDimension>> dimensions = Optional.of(List.of(createAggregationDimension()));
        when(it.dimensions()).thenReturn(dimensions);
        Optional<List<Annotation>> annotations = Optional.ofNullable(this.annotations);
        when(it.annotations()).thenReturn(annotations);
        when(it.description()).thenReturn(Optional.of("description"));
        return it;
    }

    private AggregationDimension createAggregationDimension() {
        AggregationDimension it = mock(AggregationDimension.class);
        when(it.cubeDimensionID()).thenReturn("cubeDimensionID");
        Optional<List<AggregationAttribute>> attributes = Optional.of(List.of(createAggregationAttribute()));
        when(it.attributes()).thenReturn(attributes);
        Optional<List<Annotation>> annotations = Optional.of(this.annotations);
        when(it.annotations()).thenReturn(annotations);
        return it;
    }

    private AggregationAttribute createAggregationAttribute() {
        AggregationAttribute it = mock(AggregationAttribute.class);
        when(it.attributeID()).thenReturn("attributeID");
        Optional<List<Annotation>> annotations = Optional.of(this.annotations);
        when(it.annotations()).thenReturn(annotations);
        return it;
    }

    private Cube createCube() {
        Cube it = mock(Cube.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn("id");
        when(it.createdTimestamp()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.lastSchemaUpdate()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.description()).thenReturn("description");
        List<Annotation> annotations = createAnnotationList();
        when(it.annotations()).thenReturn(annotations);
        when(it.language()).thenReturn(BigInteger.ONE);
        when(it.collation()).thenReturn("collation");
        List<Translation> translations = List.of(createTranslation());
        when(it.translations()).thenReturn(translations);
        List<CubeDimension> dimensions = List.of(createCubeDimension());
        when(it.dimensions()).thenReturn(dimensions);
        List<CubePermission> cubePermissions = List.of(createCubePermission());
        when(it.cubePermissions()).thenReturn(cubePermissions);
        List<MdxScript> mdxScripts = List.of(createMdxScript());
        when(it.mdxScripts()).thenReturn(mdxScripts);
        List<Perspective> perspectives = List.of(createPerspective());
        when(it.perspectives()).thenReturn(perspectives);
        when(it.state()).thenReturn("state");
        when(it.defaultMeasure()).thenReturn("defaultMeasure");
        when(it.visible()).thenReturn(true);
        List<MeasureGroup> measureGroups = List.of(createMeasureGroup());
        when(it.measureGroups()).thenReturn(measureGroups);
        DataSourceViewBinding source = createDataSourceViewBinding();
        when(it.source()).thenReturn(source);
        when(it.aggregationPrefix()).thenReturn("aggregationPrefix");
        when(it.processingPriority()).thenReturn(BigInteger.TEN);
        Cube.StorageMode storageMode = createCubeStorageMode();
        when(it.storageMode()).thenReturn(storageMode);
        when(it.processingMode()).thenReturn("processingMode");
        when(it.scriptCacheProcessingMode()).thenReturn("scriptCacheProcessingMode");
        when(it.scriptErrorHandlingMode()).thenReturn("scriptErrorHandlingMode");
        when(it.daxOptimizationMode()).thenReturn("daxOptimizationMode");
        ProactiveCaching proactiveCaching = createProactiveCaching();
        when(it.proactiveCaching()).thenReturn(proactiveCaching);
        List<Kpi> kpis = List.of(createKpi());
        when(it.kpis()).thenReturn(kpis);
        ErrorConfiguration errorConfiguration = createErrorConfiguration();
        when(it.errorConfiguration()).thenReturn(errorConfiguration);
        List<Action> actions = List.of(createAction());
        when(it.actions()).thenReturn(actions);
        when(it.storageLocation()).thenReturn("storageLocation");
        when(it.estimatedRows()).thenReturn(10l);
        when(it.lastProcessed()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        return it;
    }

    private Action createAction() {
        Action it = mock(Action.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn(Optional.of("id"));
        when(it.caption()).thenReturn(Optional.of("caption"));
        when(it.captionIsMdx()).thenReturn(Optional.of(true));
        Optional<List<Translation>> translations = Optional.of(List.of(createTranslation()));
        when(it.translations()).thenReturn(translations);
        when(it.targetType()).thenReturn(TargetTypeEnum.CUBE);
        when(it.target()).thenReturn(Optional.of("target"));
        when(it.condition()).thenReturn(Optional.of("condition"));
        when(it.type()).thenReturn(TypeEnum.REPORT);
        when(it.invocation()).thenReturn(Optional.of("invocation"));
        when(it.application()).thenReturn(Optional.of("application"));
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        return it;
    }

    private ErrorConfiguration createErrorConfiguration() {
        ErrorConfiguration it = mock(ErrorConfiguration.class);
        when(it.keyErrorLimit()).thenReturn(Optional.of(10l));
        when(it.keyErrorLogFile()).thenReturn(Optional.of("keyErrorLogFile"));
        when(it.keyErrorAction()).thenReturn(Optional.of("keyErrorAction"));
        when(it.keyErrorLimitAction()).thenReturn(Optional.of("keyErrorLimitAction"));
        when(it.keyNotFound()).thenReturn(Optional.of("keyNotFound"));
        when(it.keyDuplicate()).thenReturn(Optional.of("keyDuplicate"));
        when(it.nullKeyConvertedToUnknown()).thenReturn(Optional.of("nullKeyConvertedToUnknown"));
        when(it.nullKeyNotAllowed()).thenReturn(Optional.of("nullKeyNotAllowed"));
        when(it.calculationError()).thenReturn(Optional.of("calculationError"));
        return it;
    }

    private Kpi createKpi() {
        Kpi it = mock(Kpi.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn("id");
        when(it.description()).thenReturn("description");
        List<Translation> translations = List.of(createTranslation());
        when(it.translations()).thenReturn(translations);
        when(it.displayFolder()).thenReturn("displayFolder");
        when(it.associatedMeasureGroupID()).thenReturn("associatedMeasureGroupID");
        when(it.value()).thenReturn("value");
        when(it.goal()).thenReturn("goal");
        when(it.status()).thenReturn("status");
        when(it.trend()).thenReturn("trend");
        when(it.weight()).thenReturn("weight");
        when(it.trendGraphic()).thenReturn("trendGraphic");
        when(it.statusGraphic()).thenReturn("statusGraphic");
        when(it.currentTimeMember()).thenReturn("currentTimeMember");
        when(it.parentKpiID()).thenReturn("parentKpiID");
        when(it.annotations()).thenReturn(annotations);
        return it;
    }

    private ProactiveCaching createProactiveCaching() {
        ProactiveCaching it = mock(ProactiveCaching.class);
        when(it.onlineMode()).thenReturn(Optional.of("onlineMode"));
        when(it.aggregationStorage()).thenReturn(Optional.of("aggregationStorage"));
        Optional<ProactiveCachingBinding> source = Optional.of(createProactiveCachingIncrementalProcessingBinding());
        when(it.source()).thenReturn(source);
        when(it.silenceInterval()).thenReturn(Optional.of(Duration.ofDays(10l)));
        when(it.latency()).thenReturn(Optional.of(Duration.ofDays(11l)));
        when(it.silenceOverrideInterval()).thenReturn(Optional.of(Duration.ofDays(12l)));
        when(it.forceRebuildInterval()).thenReturn(Optional.of(Duration.ofDays(14l)));
        when(it.enabled()).thenReturn(Optional.of(true));
        return it;
    }

    private ProactiveCachingIncrementalProcessingBinding createProactiveCachingIncrementalProcessingBinding() {
        ProactiveCachingIncrementalProcessingBinding it = mock(ProactiveCachingIncrementalProcessingBinding.class);
        when(it.refreshInterval()).thenReturn(Optional.of(Duration.ofDays(10l)));
        List<IncrementalProcessingNotification> incrementalProcessingNotifications = List
                .of(createIncrementalProcessingNotification());
        when(it.incrementalProcessingNotifications()).thenReturn(incrementalProcessingNotifications);
        return it;
    }

    private IncrementalProcessingNotification createIncrementalProcessingNotification() {
        IncrementalProcessingNotification it = mock(IncrementalProcessingNotification.class);
        when(it.tableID()).thenReturn("tableID");
        when(it.processingQuery()).thenReturn("processingQuery");
        return it;
    }

    private Cube.StorageMode createCubeStorageMode() {
        Cube.StorageMode it = mock(Cube.StorageMode.class);
        when(it.value()).thenReturn(CubeStorageModeEnumType.ROLAP);
        when(it.valuens()).thenReturn("valuens");
        return it;
    }

    private DataSourceViewBinding createDataSourceViewBinding() {
        DataSourceViewBinding it = mock(DataSourceViewBinding.class);
        when(it.dataSourceViewID()).thenReturn("dataSourceViewID");
        return it;
    }

    private CubePermission createCubePermission() {
        CubePermission it = mock(CubePermission.class);
        when(it.readSourceData()).thenReturn(Optional.of("readSourceData"));
        Optional<List<CubeDimensionPermission>> dimensionPermissions = Optional
                .of(List.of(createCubeDimensionPermission()));
        when(it.dimensionPermissions()).thenReturn(dimensionPermissions);
        Optional<List<CellPermission>> cellPermissions = Optional.of(List.of(createCellPermission()));
        when(it.cellPermissions()).thenReturn(cellPermissions);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn(Optional.of("id"));
        when(it.createdTimestamp()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.lastSchemaUpdate()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        when(it.roleID()).thenReturn("roleID");
        when(it.process()).thenReturn(Optional.of(true));
        when(it.readDefinition()).thenReturn(Optional.of(ReadDefinitionEnum.BASIC));
        when(it.read()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        when(it.write()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        return it;
    }

    private CellPermission createCellPermission() {
        CellPermission it = mock(CellPermission.class);
        when(it.access()).thenReturn(Optional.of(AccessEnum.READ));
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.expression()).thenReturn(Optional.of("expression"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        return it;
    }

    private CubeDimensionPermission createCubeDimensionPermission() {
        CubeDimensionPermission it = mock(CubeDimensionPermission.class);
        when(it.cubeDimensionID()).thenReturn("cubeDimensionID");
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.read()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        when(it.write()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        Optional<List<AttributePermission>> attributePermissions = Optional.of(List.of(createAttributePermission()));
        when(it.attributePermissions()).thenReturn(attributePermissions);
        when(it.annotations()).thenReturn(Optional.of(annotations));
        return it;
    }

    private AttributePermission createAttributePermission() {
        AttributePermission it = mock(AttributePermission.class);
        when(it.attributeID()).thenReturn("attributeID");
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.defaultMember()).thenReturn(Optional.of("defaultMember"));
        when(it.visualTotals()).thenReturn(Optional.of("visualTotals"));
        when(it.allowedSet()).thenReturn(Optional.of("allowedSet"));
        when(it.deniedSet()).thenReturn(Optional.of("deniedSet"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        return it;
    }

    private CubeDimension createCubeDimension() {
        CubeDimension it = mock(CubeDimension.class);
        when(it.id()).thenReturn("id");
        when(it.name()).thenReturn("name");
        when(it.description()).thenReturn("description");
        List<Translation> translations = List.of(createTranslation());
        when(it.translations()).thenReturn(translations);
        when(it.dimensionID()).thenReturn("dimensionID");
        when(it.visible()).thenReturn(true);
        when(it.allMemberAggregationUsage()).thenReturn("allMemberAggregationUsage");
        when(it.hierarchyUniqueNameStyle()).thenReturn("hierarchyUniqueNameStyle");
        when(it.memberUniqueNameStyle()).thenReturn("memberUniqueNameStyle");
        List<CubeAttribute> attributes = List.of(createCubeAttribute());
        when(it.attributes()).thenReturn(attributes);
        List<CubeHierarchy> hierarchies = List.of(createCubeHierarchy());
        when(it.hierarchies()).thenReturn(hierarchies);
        List<Annotation> annotations = createAnnotationList();
        when(it.annotations()).thenReturn(annotations);
        return it;
    }

    private CubeHierarchy createCubeHierarchy() {
        CubeHierarchy it = mock(CubeHierarchy.class);
        when(it.hierarchyID()).thenReturn("hierarchyID");
        when(it.optimizedState()).thenReturn("optimizedState");
        when(it.visible()).thenReturn(true);
        when(it.enabled()).thenReturn(true);
        when(it.annotations()).thenReturn(annotations);
        return it;
    }

    private CubeAttribute createCubeAttribute() {
        CubeAttribute it = mock(CubeAttribute.class);
        when(it.attributeID()).thenReturn("attributeID");
        when(it.aggregationUsage()).thenReturn("aggregationUsage");
        when(it.attributeHierarchyOptimizedState()).thenReturn("attributeHierarchyOptimizedState");
        when(it.attributeHierarchyEnabled()).thenReturn(true);
        when(it.attributeHierarchyVisible()).thenReturn(true);
        when(it.annotations()).thenReturn(annotations);
        return it;
    }

    private Translation createTranslation() {
        Translation it = mock(Translation.class);
        when(it.language()).thenReturn(10l);
        when(it.caption()).thenReturn("caption");
        when(it.description()).thenReturn("description");
        when(it.displayFolder()).thenReturn("displayFolder");
        when(it.annotations()).thenReturn(annotations);
        return it;
    }

    private DataSource createDataSource() {
        DataSource it = mock(DataSource.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn("id");
        when(it.createdTimestamp()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.lastSchemaUpdate()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.description()).thenReturn("description");
        when(it.annotations()).thenReturn(annotations);
        when(it.managedProvider()).thenReturn("managedProvider");
        when(it.connectionString()).thenReturn("connectionString");
        when(it.connectionStringSecurity()).thenReturn("connectionStringSecurity");
        ImpersonationInfo impersonationInfo = createImpersonationInfo();
        when(it.impersonationInfo()).thenReturn(impersonationInfo);
        when(it.isolation()).thenReturn("isolation");
        when(it.maxActiveConnections()).thenReturn(BigInteger.ONE);
        when(it.timeout()).thenReturn(Duration.ofDays(2));
        List<DataSourcePermission> dataSourcePermissions = List.of(createDataSourcePermission());
        when(it.dataSourcePermissions()).thenReturn(dataSourcePermissions);
        ImpersonationInfo queryImpersonationInfo = createImpersonationInfo();
        when(it.queryImpersonationInfo()).thenReturn(queryImpersonationInfo);
        when(it.queryHints()).thenReturn("queryHints");
        return it;
    }

    private DataSourcePermission createDataSourcePermission() {
        DataSourcePermission it = mock(DataSourcePermission.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn(Optional.of("id"));
        when(it.createdTimestamp()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.lastSchemaUpdate()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        when(it.roleID()).thenReturn("roleID");
        when(it.process()).thenReturn(Optional.of(true));
        when(it.readDefinition()).thenReturn(Optional.of(ReadDefinitionEnum.BASIC));
        when(it.read()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        when(it.write()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        return it;
    }

    private DataSourceView createDataSourceView() {
        DataSourceView it = mock(DataSourceView.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn("id");
        when(it.createdTimestamp()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.lastSchemaUpdate()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.description()).thenReturn("description");
        when(it.annotations()).thenReturn(this.annotations);
        when(it.dataSourceID()).thenReturn("dataSourceID");
        return it;
    }

    private Dimension createDimension() {
        Dimension it = mock(Dimension.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn("id");
        when(it.createdTimestamp()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.lastSchemaUpdate()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.description()).thenReturn("description");
        when(it.annotations()).thenReturn(this.annotations);
        Binding source = createBinding();
        when(it.source()).thenReturn(source);
        when(it.miningModelID()).thenReturn("miningModelID");
        when(it.type()).thenReturn("type");
        when(it.miningModelID()).thenReturn("miningModelID");
        when(it.type()).thenReturn("type");
        Dimension.UnknownMember unknownMember = createDimensionUnknownMember();
        when(it.unknownMember()).thenReturn(unknownMember);
        when(it.mdxMissingMemberMode()).thenReturn("mdxMissingMemberMode");
        ErrorConfiguration errorConfiguration = createErrorConfiguration();
        when(it.errorConfiguration()).thenReturn(errorConfiguration);
        when(it.storageMode()).thenReturn("storageMode");
        when(it.writeEnabled()).thenReturn(true);
        when(it.processingPriority()).thenReturn(BigInteger.TEN);
        when(it.lastProcessed()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        List<DimensionPermission> dimensionPermissions = List.of(createDimensionPermission());
        when(it.dimensionPermissions()).thenReturn(dimensionPermissions);
        when(it.dependsOnDimensionID()).thenReturn("dependsOnDimensionID");
        when(it.language()).thenReturn(BigInteger.TWO);
        when(it.collation()).thenReturn("collation");
        when(it.unknownMemberName()).thenReturn("unknownMemberName");
        List<Translation> unknownMemberTranslations = List.of(createTranslation());
        when(it.unknownMemberTranslations()).thenReturn(unknownMemberTranslations);
        when(it.state()).thenReturn("state");
        ProactiveCaching proactiveCaching = createProactiveCaching();
        when(it.proactiveCaching()).thenReturn(proactiveCaching);
        when(it.processingMode()).thenReturn("processingMode");
        when(it.processingGroup()).thenReturn("processingGroup");
        Dimension.CurrentStorageMode currentStorageMode = createDimensionCurrentStorageMode();
        when(it.currentStorageMode()).thenReturn(currentStorageMode);
        List<Translation> translations = List.of(createTranslation());
        when(it.translations()).thenReturn(translations);
        List<DimensionAttribute> attributes = List.of(createDimensionAttribute());
        when(it.attributes()).thenReturn(attributes);
        when(it.attributeAllMemberName()).thenReturn("attributeAllMemberName");
        List<Translation> attributeAllMemberTranslations = List.of(createTranslation());
        when(it.attributeAllMemberTranslations()).thenReturn(attributeAllMemberTranslations);
        List<Hierarchy> hierarchies = List.of(createHierarchy());
        when(it.hierarchies()).thenReturn(hierarchies);
        when(it.processingRecommendation()).thenReturn("processingRecommendation");
        Relationships relationships = createRelationships();
        when(it.relationships()).thenReturn(relationships);
        when(it.stringStoresCompatibilityLevel()).thenReturn(10);
        when(it.currentStringStoresCompatibilityLevel()).thenReturn(11);
        return it;
    }

    private DimensionPermission createDimensionPermission() {
        DimensionPermission it = mock(DimensionPermission.class);

        Optional<List<AttributePermission>> attributePermissions = Optional.of(List.of(createAttributePermission()));
        when(it.attributePermissions()).thenReturn(attributePermissions);
        when(it.allowedRowsExpression()).thenReturn(Optional.of("allowedRowsExpression"));
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn(Optional.of("id"));
        when(it.createdTimestamp()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.lastSchemaUpdate()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        when(it.roleID()).thenReturn("roleID");
        when(it.process()).thenReturn(Optional.of(true));
        when(it.readDefinition()).thenReturn(Optional.of(ReadDefinitionEnum.BASIC));
        when(it.read()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        when(it.write()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        return it;

    }

    private Dimension.UnknownMember createDimensionUnknownMember() {
        Dimension.UnknownMember it = mock(Dimension.UnknownMember.class);
        when(it.value()).thenReturn(UnknownMemberEnumType.NONE);
        when(it.valuens()).thenReturn("valuens");
        return it;

    }

    private Relationships createRelationships() {
        Relationships it = mock(Relationships.class);
        List<Relationship> relationship = List.of(createRelationship());
        when(it.relationship()).thenReturn(relationship);
        return it;
    }

    private Relationship createRelationship() {
        Relationship it = mock(Relationship.class);
        when(it.id()).thenReturn("id");
        when(it.visible()).thenReturn(true);
        RelationshipEnd fromRelationshipEnd = createRelationshipEnd();
        when(it.fromRelationshipEnd()).thenReturn(fromRelationshipEnd);
        RelationshipEnd toRelationshipEnd = createRelationshipEnd();
        when(it.toRelationshipEnd()).thenReturn(toRelationshipEnd);
        return it;
    }

    private RelationshipEnd createRelationshipEnd() {
        RelationshipEnd it = mock(RelationshipEnd.class);
        when(it.role()).thenReturn("role");
        when(it.multiplicity()).thenReturn("multiplicity");
        when(it.dimensionID()).thenReturn("dimensionID");
        List<String> attributes = List.of("attribute");
        when(it.attributes()).thenReturn(attributes);
        List<RelationshipEndTranslation> translations = List.of(createRelationshipEndTranslation());
        when(it.translations()).thenReturn(translations);
        RelationshipEndVisualizationProperties visualizationProperties = createRelationshipEndVisualizationProperties();
        when(it.visualizationProperties()).thenReturn(visualizationProperties);
        return it;
    }

    private RelationshipEndVisualizationProperties createRelationshipEndVisualizationProperties() {
        RelationshipEndVisualizationProperties it = mock(RelationshipEndVisualizationProperties.class);
        when(it.folderPosition()).thenReturn(BigInteger.ONE);
        when(it.contextualNameRule()).thenReturn("contextualNameRule");
        when(it.defaultDetailsPosition()).thenReturn(BigInteger.TWO);
        when(it.displayKeyPosition()).thenReturn(BigInteger.TEN);
        when(it.commonIdentifierPosition()).thenReturn(BigInteger.ZERO);
        when(it.isDefaultMeasure()).thenReturn(true);
        when(it.isDefaultImage()).thenReturn(true);
        when(it.sortPropertiesPosition()).thenReturn(BigInteger.ZERO);
        return it;
    }

    private RelationshipEndTranslation createRelationshipEndTranslation() {
        RelationshipEndTranslation it = mock(RelationshipEndTranslation.class);
        when(it.language()).thenReturn(10l);
        when(it.caption()).thenReturn("caption");
        when(it.collectionCaption()).thenReturn("collectionCaption");
        when(it.description()).thenReturn("description");
        when(it.displayFolder()).thenReturn("displayFolder");
        when(it.annotations()).thenReturn(annotations);
        return it;
    }

    private Hierarchy createHierarchy() {
        Hierarchy it = mock(Hierarchy.class);

        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn("id");
        when(it.description()).thenReturn("description");
        when(it.processingState()).thenReturn("processingState");
        when(it.structureType()).thenReturn("structureType");
        when(it.displayFolder()).thenReturn("displayFolder");
        List<Translation> translations = List.of(createTranslation());
        when(it.translations()).thenReturn(translations);
        when(it.allMemberName()).thenReturn("allMemberName");
        List<Translation> allMemberTranslations = List.of(createTranslation());
        when(it.allMemberTranslations()).thenReturn(allMemberTranslations);
        when(it.allMemberTranslations()).thenReturn(translations);
        when(it.memberNamesUnique()).thenReturn(true);
        when(it.memberKeysUnique()).thenReturn("memberKeysUnique");
        when(it.allowDuplicateNames()).thenReturn(true);
        List<Level> levels = List.of(createLevel());
        when(it.levels()).thenReturn(levels);
        when(it.annotations()).thenReturn(annotations);
        HierarchyVisualizationProperties visualizationProperties = createHierarchyVisualizationProperties();
        when(it.visualizationProperties()).thenReturn(visualizationProperties);
        return it;
    }

    private Level createLevel() {
        Level it = mock(Level.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn("id");
        when(it.description()).thenReturn("description");
        when(it.sourceAttributeID()).thenReturn("sourceAttributeID");
        when(it.hideMemberIf()).thenReturn("hideMemberIf");
        List<Translation> translations = List.of(createTranslation());
        when(it.translations()).thenReturn(translations);
        when(it.annotations()).thenReturn(annotations);
        return it;
    }

    private HierarchyVisualizationProperties createHierarchyVisualizationProperties() {
        HierarchyVisualizationProperties it = mock(HierarchyVisualizationProperties.class);
        when(it.contextualNameRule()).thenReturn("contextualNameRule");
        when(it.folderPosition()).thenReturn(BigInteger.ONE);
        return it;

    }

    private DimensionAttribute createDimensionAttribute() {
        DimensionAttribute it = mock(DimensionAttribute.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn("id");
        when(it.description()).thenReturn("description");
        DimensionAttribute.Type type = createDimensionAttributeType();
        when(it.type()).thenReturn(type);
        when(it.usage()).thenReturn("usage");
        Binding source = createBinding();
        when(it.source()).thenReturn(source);
        when(it.estimatedCount()).thenReturn(10l);
        List<DataItem> keyColumns = List.of(createDataItem());
        when(it.keyColumns()).thenReturn(keyColumns);
        DataItem nameColumn = createDataItem();
        when(it.nameColumn()).thenReturn(nameColumn);
        DataItem valueColumn = createDataItem();
        when(it.valueColumn()).thenReturn(valueColumn);
        List<AttributeTranslation> translations = List.of(createAttributeTranslation());
        when(it.translations()).thenReturn(translations);
        List<AttributeRelationship> attributeRelationships = List.of(createAttributeRelationship());
        when(it.attributeRelationships()).thenReturn(attributeRelationships);
        when(it.discretizationMethod()).thenReturn("discretizationMethod");
        when(it.discretizationBucketCount()).thenReturn(BigInteger.ONE);
        when(it.rootMemberIf()).thenReturn("rootMemberIf");
        when(it.orderBy()).thenReturn("orderBy");
        when(it.defaultMember()).thenReturn("defaultMember");
        when(it.orderByAttributeID()).thenReturn("orderByAttributeID");
        DataItem skippedLevelsColumn = createDataItem();
        when(it.skippedLevelsColumn()).thenReturn(skippedLevelsColumn);
        when(it.namingTemplate()).thenReturn("namingTemplate");
        when(it.membersWithData()).thenReturn("membersWithData");
        when(it.membersWithDataCaption()).thenReturn("membersWithDataCaption");
        List<Translation> namingTemplateTranslations = List.of(createTranslation());
        when(it.namingTemplateTranslations()).thenReturn(namingTemplateTranslations);
        DataItem dataItem = createDataItem();
        when(it.customRollupColumn()).thenReturn(dataItem);
        when(it.customRollupPropertiesColumn()).thenReturn(dataItem);
        when(it.unaryOperatorColumn()).thenReturn(dataItem);
        when(it.attributeHierarchyOrdered()).thenReturn(true);
        when(it.memberNamesUnique()).thenReturn(true);
        when(it.isAggregatable()).thenReturn(true);
        when(it.attributeHierarchyEnabled()).thenReturn(true);
        when(it.attributeHierarchyOptimizedState()).thenReturn("attributeHierarchyOptimizedState");
        when(it.attributeHierarchyVisible()).thenReturn(true);
        when(it.attributeHierarchyDisplayFolder()).thenReturn("attributeHierarchyDisplayFolder");
        when(it.keyUniquenessGuarantee()).thenReturn(true);
        when(it.groupingBehavior()).thenReturn("groupingBehavior");
        when(it.instanceSelection()).thenReturn("instanceSelection");
        when(it.annotations()).thenReturn(annotations);
        when(it.processingState()).thenReturn("processingState");
        when(it.attributeHierarchyProcessingState()).thenReturn(AttributeHierarchyProcessingState.PROCESSED);
        DimensionAttributeVisualizationProperties visualizationProperties = createDimensionAttributeVisualizationProperties();
        when(it.visualizationProperties()).thenReturn(visualizationProperties);
        when(it.extendedType()).thenReturn("extendedType");
        return it;
    }

    private DimensionAttributeVisualizationProperties createDimensionAttributeVisualizationProperties() {
        DimensionAttributeVisualizationProperties it = mock(DimensionAttributeVisualizationProperties.class);
        when(it.folderPosition()).thenReturn(BigInteger.ONE);
        when(it.contextualNameRule()).thenReturn("contextualNameRule");
        when(it.alignment()).thenReturn("alignment");
        when(it.isFolderDefault()).thenReturn(true);
        when(it.isRightToLeft()).thenReturn(true);
        when(it.sortDirection()).thenReturn("sortDirection");
        when(it.units()).thenReturn("units");
        when(it.width()).thenReturn(BigInteger.TWO);
        when(it.defaultDetailsPosition()).thenReturn(BigInteger.TWO);
        when(it.commonIdentifierPosition()).thenReturn(BigInteger.TEN);
        when(it.sortPropertiesPosition()).thenReturn(BigInteger.ZERO);
        when(it.displayKeyPosition()).thenReturn(BigInteger.TWO);
        when(it.isDefaultImage()).thenReturn(true);
        when(it.defaultAggregateFunction()).thenReturn("defaultAggregateFunction");
        return it;

    }

    private AttributeRelationship createAttributeRelationship() {
        AttributeRelationship it = mock(AttributeRelationship.class);
        when(it.attributeID()).thenReturn("attributeID");
        when(it.relationshipType()).thenReturn("relationshipType");
        when(it.cardinality()).thenReturn("cardinality");
        when(it.optionality()).thenReturn("optionality");
        when(it.overrideBehavior()).thenReturn("overrideBehavior");
        when(it.annotations()).thenReturn(annotations);
        when(it.name()).thenReturn("name");
        when(it.visible()).thenReturn(true);
        List<Translation> translations = List.of(createTranslation());
        when(it.translations()).thenReturn(translations);
        return it;

    }

    private AttributeTranslation createAttributeTranslation() {
        AttributeTranslation it = mock(AttributeTranslation.class);
        when(it.language()).thenReturn(10l);
        when(it.caption()).thenReturn(Optional.of("caption"));
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.displayFolder()).thenReturn(Optional.of("displayFolder"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        Optional<DataItem> captionColumn = Optional.of(createDataItem());
        when(it.captionColumn()).thenReturn(captionColumn);
        when(it.membersWithDataCaption()).thenReturn(Optional.of("membersWithDataCaption"));
        return it;
    }

    private DataItem createDataItem() {
        DataItem it = mock(DataItem.class);
        when(it.dataType()).thenReturn("dataType");
        when(it.dataSize()).thenReturn(Optional.of(10));
        when(it.mimeType()).thenReturn(Optional.of("mimeType"));
        when(it.nullProcessing()).thenReturn(Optional.of(NullProcessingEnum.AUTOMATIC));
        when(it.trimming()).thenReturn(Optional.of("trimming"));
        when(it.invalidXmlCharacters()).thenReturn(Optional.of(InvalidXmlCharacterEnum.PRESERVE));
        when(it.collation()).thenReturn(Optional.of("collation"));
        when(it.format()).thenReturn(Optional.of(DataItemFormatEnum.TRIM_ALL));
        Optional<Binding> source = Optional.of(createBinding());
        when(it.source()).thenReturn(source);
        when(it.annotations()).thenReturn(Optional.of(annotations));
        return it;
    }

    private DimensionAttribute.Type createDimensionAttributeType() {
        DimensionAttribute.Type it = mock(DimensionAttribute.Type.class);
        when(it.value()).thenReturn(DimensionAttributeTypeEnumType.ACCOUNT);
        when(it.valuens()).thenReturn("valuens");
        return it;
    }

    private Dimension.CurrentStorageMode createDimensionCurrentStorageMode() {
        Dimension.CurrentStorageMode it = mock(Dimension.CurrentStorageMode.class);
        when(it.value()).thenReturn(DimensionCurrentStorageModeEnumType.ROLAP);
        when(it.valuens()).thenReturn("valuens");
        return it;
    }

    private Binding createBinding() {
        ColumnBinding it = mock(ColumnBinding.class);
        when(it.tableID()).thenReturn("tableID");
        when(it.columnID()).thenReturn("columnID");
        return it;
    }

    private MdxScript createMdxScript() {
        MdxScript it = mock(MdxScript.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn("id");
        when(it.createdTimestamp()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.lastSchemaUpdate()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.description()).thenReturn("description");
        when(it.annotations()).thenReturn(annotations);
        // List<Command> commands = List.of(createCommand()); //StackOverflow
        when(it.commands()).thenReturn(null);
        when(it.defaultScript()).thenReturn(true);
        List<CalculationProperty> calculationProperties = List.of(createCalculationProperty());
        when(it.calculationProperties()).thenReturn(calculationProperties);
        return it;
    }

    private CalculationProperty createCalculationProperty() {
        CalculationProperty it = mock(CalculationProperty.class);
        when(it.calculationReference()).thenReturn("calculationReference");
        when(it.calculationType()).thenReturn("calculationType");
        List<Translation> translations = List.of(createTranslation());
        when(it.translations()).thenReturn(translations);
        when(it.description()).thenReturn("description");
        when(it.visible()).thenReturn(true);
        when(it.solveOrder()).thenReturn(BigInteger.ONE);
        when(it.formatString()).thenReturn("formatString");
        when(it.foreColor()).thenReturn("foreColor");
        when(it.backColor()).thenReturn("backColor");
        when(it.fontName()).thenReturn("fontName");
        when(it.fontSize()).thenReturn("fontSize");
        when(it.fontFlags()).thenReturn("fontFlags");
        when(it.nonEmptyBehavior()).thenReturn("nonEmptyBehavior");
        when(it.associatedMeasureGroupID()).thenReturn("associatedMeasureGroupID");
        when(it.displayFolder()).thenReturn("displayFolder");
        when(it.language()).thenReturn(BigInteger.TEN);
        CalculationPropertiesVisualizationProperties visualizationProperties = createCalculationPropertiesVisualizationProperties();
        when(it.visualizationProperties()).thenReturn(visualizationProperties);
        return it;
    }

    private CalculationPropertiesVisualizationProperties createCalculationPropertiesVisualizationProperties() {
        CalculationPropertiesVisualizationProperties it = mock(CalculationPropertiesVisualizationProperties.class);
        when(it.folderPosition()).thenReturn(BigInteger.ONE);
        when(it.contextualNameRule()).thenReturn("contextualNameRule");
        when(it.alignment()).thenReturn("alignment");
        when(it.isFolderDefault()).thenReturn(true);
        when(it.isRightToLeft()).thenReturn(true);
        when(it.sortDirection()).thenReturn("sortDirection");
        when(it.units()).thenReturn("units");
        when(it.width()).thenReturn(BigInteger.TWO);
        when(it.isDefaultMeasure()).thenReturn(true);
        when(it.defaultDetailsPosition()).thenReturn(BigInteger.TWO);
        when(it.sortPropertiesPosition()).thenReturn(BigInteger.ZERO);
        when(it.isSimpleMeasure()).thenReturn(true);
        return it;
    }

    private Alter createAlter() {
        Alter it = mock(Alter.class);
        ObjectReference object = createObjectReference();
        when(it.object()).thenReturn(object);
        MajorObject objectDefinition = createMajorObject();
        when(it.objectDefinition()).thenReturn(objectDefinition);
        when(it.scope()).thenReturn(Scope.SESSION);
        when(it.allowCreate()).thenReturn(true);
        when(it.objectExpansion()).thenReturn(ObjectExpansion.EXPAND_FULL);
        return it;
    }

    private MeasureGroup createMeasureGroup() {
        MeasureGroup it = mock(MeasureGroup.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn("id");
        when(it.createdTimestamp()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.lastSchemaUpdate()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.description()).thenReturn("description");
        when(it.annotations()).thenReturn(annotations);
        when(it.lastProcessed()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        List<Translation> translations = List.of(createTranslation());
        when(it.translations()).thenReturn(translations);
        when(it.type()).thenReturn("type");
        when(it.state()).thenReturn("state");
        List<Measure> measures = List.of(createMeasure());
        when(it.measures()).thenReturn(measures);
        when(it.dataAggregation()).thenReturn("dataAggregation");
        MeasureGroupBinding source = createMeasureGroupBinding();
        when(it.source()).thenReturn(source);
        MeasureGroup.StorageMode storageMode = createMeasureGroupStorageMode();
        when(it.storageMode()).thenReturn(storageMode);
        when(it.storageLocation()).thenReturn("storageLocation");
        when(it.ignoreUnrelatedDimensions()).thenReturn(true);
        ProactiveCaching proactiveCaching = createProactiveCaching();
        when(it.proactiveCaching()).thenReturn(proactiveCaching);
        when(it.estimatedRows()).thenReturn(10l);
        ErrorConfiguration errorConfiguration = createErrorConfiguration();
        when(it.errorConfiguration()).thenReturn(errorConfiguration);
        when(it.estimatedSize()).thenReturn(11l);
        when(it.processingMode()).thenReturn("processingMode");
        List<MeasureGroupDimension> dimensions = List.of(createMeasureGroupDimension());
        when(it.dimensions()).thenReturn(dimensions);
        List<Partition> partitions = List.of(createPartition());
        when(it.partitions()).thenReturn(partitions);
        when(it.aggregationPrefix()).thenReturn("aggregationPrefix");
        when(it.processingPriority()).thenReturn(BigInteger.ONE);
        List<AggregationDesign> aggregationDesigns = List.of(createAggregationDesign());
        when(it.aggregationDesigns()).thenReturn(aggregationDesigns);
        return it;
    }

    private MeasureGroupDimension createMeasureGroupDimension() {
        ManyToManyMeasureGroupDimension it = mock(ManyToManyMeasureGroupDimension.class);
        when(it.cubeDimensionID()).thenReturn("cubeDimensionID");
        when(it.annotations()).thenReturn(annotations);
        MeasureGroupDimensionBinding source = createMeasureGroupDimensionBinding();
        when(it.source()).thenReturn(source);
        when(it.measureGroupID()).thenReturn("measureGroupID");
        when(it.directSlice()).thenReturn("directSlice");
        return it;
    }

    private MeasureGroupDimensionBinding createMeasureGroupDimensionBinding() {
        MeasureGroupDimensionBinding it = mock(MeasureGroupDimensionBinding.class);
        when(it.cubeDimensionID()).thenReturn("cubeDimensionID");
        return it;
    }

    private MeasureGroup.StorageMode createMeasureGroupStorageMode() {
        MeasureGroup.StorageMode it = mock(MeasureGroup.StorageMode.class);
        when(it.value()).thenReturn(MeasureGroupStorageModeEnumType.ROLAP);
        when(it.valuens()).thenReturn("valuens");
        return it;
    }

    private MeasureGroupBinding createMeasureGroupBinding() {
        MeasureGroupBinding it = mock(MeasureGroupBinding.class);
        when(it.dataSourceID()).thenReturn("dataSourceID");
        when(it.cubeID()).thenReturn("cubeID");
        when(it.measureGroupID()).thenReturn("measureGroupID");
        when(it.persistence()).thenReturn(Optional.of(PersistenceEnum.METADATA));
        when(it.refreshPolicy()).thenReturn(Optional.of(RefreshPolicyEnum.BY_QUERY));
        when(it.refreshInterval()).thenReturn(Optional.of(Duration.ofDays(2l)));
        when(it.filter()).thenReturn(Optional.of("filter"));
        return it;
    }

    private Measure createMeasure() {
        Measure it = mock(Measure.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn("id");
        when(it.description()).thenReturn("description");
        when(it.aggregateFunction()).thenReturn("aggregateFunction");
        when(it.dataType()).thenReturn("dataType");
        DataItem source = createDataItem();
        when(it.source()).thenReturn(source);
        when(it.visible()).thenReturn(true);
        when(it.measureExpression()).thenReturn("measureExpression");
        when(it.displayFolder()).thenReturn("displayFolder");
        when(it.formatString()).thenReturn("formatString");
        when(it.backColor()).thenReturn("backColor");
        when(it.foreColor()).thenReturn("foreColor");
        when(it.fontName()).thenReturn("fontName");
        when(it.fontSize()).thenReturn("fontSize");
        when(it.fontFlags()).thenReturn("fontFlags");
        List<Translation> translations = List.of(createTranslation());
        when(it.translations()).thenReturn(translations);
        when(it.annotations()).thenReturn(annotations);
        return it;
    }

    private MiningModel createMiningModel() {
        MiningModel it = mock(MiningModel.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn(Optional.of("id"));
        when(it.createdTimestamp()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.lastSchemaUpdate()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        when(it.algorithm()).thenReturn("algorithm");
        when(it.lastProcessed()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        Optional<List<AlgorithmParameter>> algorithmParameters = Optional.of(List.of(createAlgorithmParameter()));
        when(it.algorithmParameters()).thenReturn(algorithmParameters);
        when(it.allowDrillThrough()).thenReturn(Optional.of(true));
        Optional<List<AttributeTranslation>> translations = Optional.of(List.of(createAttributeTranslation()));
        when(it.translations()).thenReturn(translations);
        Optional<List<MiningModelColumn>> columns = Optional.of(List.of(createMiningModelColumn()));
        when(it.columns()).thenReturn(columns);
        when(it.state()).thenReturn(Optional.of("state"));
        Optional<FoldingParameters> foldingParameters = Optional.of(createFoldingParameters());
        when(it.foldingParameters()).thenReturn(foldingParameters);
        when(it.filter()).thenReturn(Optional.of("filter"));
        Optional<List<MiningModelPermission>> miningModelPermissions = Optional
                .of(List.of(createMiningModelPermission()));
        when(it.miningModelPermissions()).thenReturn(miningModelPermissions);
        when(it.language()).thenReturn(Optional.of("language"));
        when(it.collation()).thenReturn(Optional.of("collation"));
        return it;
    }

    private MiningModelPermission createMiningModelPermission() {
        MiningModelPermission it = mock(MiningModelPermission.class);
        when(it.allowDrillThrough()).thenReturn(Optional.of(true));
        when(it.allowBrowsing()).thenReturn(Optional.of(true));
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn(Optional.of("id"));
        when(it.createdTimestamp()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.lastSchemaUpdate()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        when(it.roleID()).thenReturn("roleID");
        when(it.process()).thenReturn(Optional.of(true));
        when(it.readDefinition()).thenReturn(Optional.of(ReadDefinitionEnum.ALLOWED));
        when(it.read()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        when(it.write()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        return it;
    }

    private FoldingParameters createFoldingParameters() {
        FoldingParameters it = mock(FoldingParameters.class);
        when(it.foldIndex()).thenReturn(10);
        when(it.foldCount()).thenReturn(11);
        when(it.foldMaxCases()).thenReturn(Optional.of(12l));
        when(it.foldTargetAttribute()).thenReturn(Optional.of("foldTargetAttribute"));
        return it;
    }

    private MiningModelColumn createMiningModelColumn() {
        MiningModelColumn it = mock(MiningModelColumn.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn(Optional.of("id"));
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.sourceColumnID()).thenReturn(Optional.of("sourceColumnID"));
        when(it.usage()).thenReturn(Optional.of("usage"));
        when(it.filter()).thenReturn(Optional.of("filter"));
        Optional<List<Translation>> translations = Optional.of(List.of(createTranslation()));
        when(it.translations()).thenReturn(translations);
        Optional<List<MiningModelColumn>> columns = Optional.empty();
        when(it.columns()).thenReturn(columns);
        Optional<List<MiningModelingFlag>> modelingFlags = Optional.of(List.of(createMiningModelingFlag()));
        when(it.modelingFlags()).thenReturn(modelingFlags);
        when(it.annotations()).thenReturn(Optional.of(annotations));
        return it;
    }

    private MiningModelingFlag createMiningModelingFlag() {
        MiningModelingFlag it = mock(MiningModelingFlag.class);
        when(it.modelingFlag()).thenReturn(Optional.of("modelingFlag"));
        return it;
    }

    private AlgorithmParameter createAlgorithmParameter() {
        AlgorithmParameter it = mock(AlgorithmParameter.class);
        when(it.name()).thenReturn("name");
        when(it.value()).thenReturn("value");
        return it;
    }

    private MiningStructure createMiningStructure() {
        MiningStructure it = mock(MiningStructure.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn(Optional.of("id"));
        when(it.createdTimestamp()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.lastSchemaUpdate()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        Optional<Binding> source = Optional.of(createBinding());
        when(it.source()).thenReturn(source);
        when(it.lastProcessed()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        Optional<List<Translation>> translations = Optional.of(List.of(createTranslation()));
        when(it.translations()).thenReturn(translations);
        when(it.language()).thenReturn(Optional.of(BigInteger.ONE));
        when(it.collation()).thenReturn(Optional.of("collation"));
        Optional<ErrorConfiguration> errorConfiguration = Optional.of(createErrorConfiguration());
        when(it.errorConfiguration()).thenReturn(errorConfiguration);
        when(it.cacheMode()).thenReturn(Optional.of("cacheMode"));
        when(it.holdoutMaxPercent()).thenReturn(Optional.of(10));
        when(it.holdoutMaxCases()).thenReturn(Optional.of(11));
        when(it.holdoutSeed()).thenReturn(Optional.of(12));
        when(it.holdoutActualSize()).thenReturn(Optional.of(14));
        List<MiningStructureColumn> columns = List.of(createMiningStructureColumn());
        when(it.columns()).thenReturn(columns);
        when(it.state()).thenReturn(Optional.of("state"));
        Optional<List<MiningStructurePermission>> miningStructurePermissions = Optional
                .of(List.of(createMiningStructurePermission()));
        when(it.miningStructurePermissions()).thenReturn(miningStructurePermissions);
        Optional<List<MiningModel>> miningModels = Optional.of(List.of(createMiningModel()));
        when(it.miningModels()).thenReturn(miningModels);
        return it;
    }

    private MiningStructurePermission createMiningStructurePermission() {
        MiningStructurePermission it = mock(MiningStructurePermission.class);
        when(it.allowDrillThrough()).thenReturn(Optional.of(true));
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn(Optional.of("id"));
        when(it.createdTimestamp()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.lastSchemaUpdate()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        when(it.roleID()).thenReturn("roleID");
        when(it.process()).thenReturn(Optional.of(true));
        when(it.readDefinition()).thenReturn(Optional.of(ReadDefinitionEnum.BASIC));
        when(it.read()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        when(it.write()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        return it;
    }

    private MiningStructureColumn createMiningStructureColumn() {
        ScalarMiningStructureColumn it = mock(ScalarMiningStructureColumn.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn(Optional.of("id"));
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.type()).thenReturn(Optional.of("type"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        when(it.isKey()).thenReturn(Optional.of(true));
        Optional<Binding> source = Optional.of(createBinding());
        when(it.source()).thenReturn(source);
        when(it.distribution()).thenReturn(Optional.of("distribution"));
        Optional<List<MiningModelingFlag>> modelingFlags = Optional.of(List.of(createMiningModelingFlag()));
        when(it.modelingFlags()).thenReturn(modelingFlags);
        when(it.content()).thenReturn("content");
        Optional<List<String>> classifiedColumns = Optional.of(List.of("classifiedColumn"));
        when(it.classifiedColumns()).thenReturn(classifiedColumns);
        when(it.discretizationMethod()).thenReturn(Optional.of("discretizationMethod"));
        when(it.discretizationBucketCount()).thenReturn(Optional.of(BigInteger.ONE));
        Optional<List<DataItem>> keyColumns = Optional.of(List.of(createDataItem()));
        when(it.keyColumns()).thenReturn(keyColumns);
        Optional<DataItem> nameColumn = Optional.of(createDataItem());
        when(it.nameColumn()).thenReturn(nameColumn);
        Optional<List<Translation>> translations = Optional.of(List.of(createTranslation()));
        when(it.translations()).thenReturn(translations);
        return it;
    }

    private Partition createPartition() {
        Partition it = mock(Partition.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn("id");
        when(it.createdTimestamp()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.lastSchemaUpdate()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.description()).thenReturn("description");
        when(it.annotations()).thenReturn(annotations);
        TabularBinding source = createTabularBinding();
        when(it.source()).thenReturn(source);
        when(it.processingPriority()).thenReturn(BigInteger.ONE);
        when(it.aggregationPrefix()).thenReturn("aggregationPrefix");
        Partition.StorageMode storageMode = createPartitionStorageMode();
        when(it.storageMode()).thenReturn(storageMode);
        when(it.processingMode()).thenReturn("processingMode");
        ErrorConfiguration errorConfiguration = createErrorConfiguration();
        when(it.errorConfiguration()).thenReturn(errorConfiguration);
        when(it.storageLocation()).thenReturn("storageLocation");
        when(it.remoteDatasourceID()).thenReturn("remoteDatasourceID");
        when(it.slice()).thenReturn("slice");
        ProactiveCaching proactiveCaching = createProactiveCaching();
        when(it.proactiveCaching()).thenReturn(proactiveCaching);
        when(it.type()).thenReturn("type");
        when(it.estimatedSize()).thenReturn(10l);
        when(it.estimatedRows()).thenReturn(11l);
        Partition.CurrentStorageMode currentStorageMode = createPartitionCurrentStorageMode();
        when(it.currentStorageMode()).thenReturn(currentStorageMode);
        when(it.aggregationDesignID()).thenReturn("aggregationDesignID");
        List<AggregationInstance> aggregationInstances = List.of(createAggregationInstance());
        when(it.aggregationInstances()).thenReturn(aggregationInstances);
        DataSourceViewBinding aggregationInstanceSource = createDataSourceViewBinding();
        when(it.aggregationInstanceSource()).thenReturn(aggregationInstanceSource);
        when(it.lastProcessed()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.state()).thenReturn("state");
        when(it.stringStoresCompatibilityLevel()).thenReturn(12);
        when(it.currentStringStoresCompatibilityLevel()).thenReturn(14);
        when(it.directQueryUsage()).thenReturn("directQueryUsage");
        return it;
    }

    private Partition.StorageMode createPartitionStorageMode() {
        Partition.StorageMode it = mock(Partition.StorageMode.class);
        when(it.value()).thenReturn(PartitionStorageModeEnumType.ROLAP);
        when(it.valuens()).thenReturn("valuens");
        return it;

    }

    private AggregationInstance createAggregationInstance() {
        AggregationInstance it = mock(AggregationInstance.class);
        when(it.id()).thenReturn("id");
        when(it.name()).thenReturn("name");
        when(it.aggregationType()).thenReturn("aggregationType");
        TabularBinding source = createTabularBinding();
        when(it.source()).thenReturn(source);
        List<AggregationInstanceDimension> dimensions = List.of(createAggregationInstanceDimension());
        when(it.dimensions()).thenReturn(dimensions);
        List<AggregationInstanceMeasure> measures = List.of(createAggregationInstanceMeasure());
        when(it.measures()).thenReturn(measures);
        when(it.annotations()).thenReturn(annotations);
        when(it.description()).thenReturn("description");
        return it;
    }

    private AggregationInstanceMeasure createAggregationInstanceMeasure() {
        AggregationInstanceMeasure it = mock(AggregationInstanceMeasure.class);
        when(it.measureID()).thenReturn("measureID");
        ColumnBinding source = createColumnBinding();
        when(it.source()).thenReturn(source);
        return it;
    }

    private ColumnBinding createColumnBinding() {
        ColumnBinding it = mock(ColumnBinding.class);
        when(it.tableID()).thenReturn("tableID");
        when(it.columnID()).thenReturn("columnID");
        return it;
    }

    private AggregationInstanceDimension createAggregationInstanceDimension() {
        AggregationInstanceDimension it = mock(AggregationInstanceDimension.class);
        when(it.cubeDimensionID()).thenReturn("cubeDimensionID");
        Optional<List<AggregationInstanceAttribute>> attributes = Optional
                .of(List.of(createAggregationInstanceAttribute()));
        when(it.attributes()).thenReturn(attributes);
        return it;
    }

    private AggregationInstanceAttribute createAggregationInstanceAttribute() {
        AggregationInstanceAttribute it = mock(AggregationInstanceAttribute.class);
        when(it.attributeID()).thenReturn("attributeID");
        Optional<List<DataItem>> keyColumns = Optional.of(List.of(createDataItem()));
        when(it.keyColumns()).thenReturn(keyColumns);
        return it;
    }

    private Partition.CurrentStorageMode createPartitionCurrentStorageMode() {
        Partition.CurrentStorageMode it = mock(Partition.CurrentStorageMode.class);
        when(it.value()).thenReturn(PartitionCurrentStorageModeEnumType.ROLAP);
        when(it.valuens()).thenReturn("valuens");
        return it;
    }

    private TabularBinding createTabularBinding() {
        QueryBinding it = mock(QueryBinding.class);
        when(it.dataSourceID()).thenReturn(Optional.of("dataSourceID"));
        when(it.queryDefinition()).thenReturn("queryDefinition");
        return it;
    }

    private Permission createPermission() {
        CubePermission it = mock(CubePermission.class);

        when(it.roleID()).thenReturn("roleID");
        when(it.process()).thenReturn(Optional.of(true));
        when(it.readSourceData()).thenReturn(Optional.of("readSourceData"));
        Optional<List<CubeDimensionPermission>> dimensionPermissions = Optional
                .of(List.of(createCubeDimensionPermission()));
        when(it.dimensionPermissions()).thenReturn(dimensionPermissions);
        Optional<List<CellPermission>> cellPermissions = Optional.of(List.of(createCellPermission()));
        when(it.cellPermissions()).thenReturn(cellPermissions);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn(Optional.of("id"));
        when(it.createdTimestamp()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.lastSchemaUpdate()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        when(it.process()).thenReturn(Optional.of(true));
        when(it.readDefinition()).thenReturn(Optional.of(ReadDefinitionEnum.BASIC));
        when(it.read()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        when(it.write()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        return it;
    }

    private Perspective createPerspective() {
        Perspective it = mock(Perspective.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn("id");
        when(it.createdTimestamp()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.lastSchemaUpdate()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.description()).thenReturn("description");
        when(it.annotations()).thenReturn(annotations);
        List<Translation> translations = List.of(createTranslation());
        when(it.translations()).thenReturn(translations);
        when(it.defaultMeasure()).thenReturn("defaultMeasure");
        List<PerspectiveDimension> dimensions = List.of(createPerspectiveDimension());
        when(it.dimensions()).thenReturn(dimensions);
        List<PerspectiveMeasureGroup> measureGroups = List.of(createPerspectiveMeasureGroup());
        when(it.measureGroups()).thenReturn(measureGroups);
        List<PerspectiveCalculation> calculations = List.of(createPerspectiveCalculation());
        when(it.calculations()).thenReturn(calculations);
        List<PerspectiveKpi> kpis = List.of(createPerspectiveKpi());
        when(it.kpis()).thenReturn(kpis);
        List<PerspectiveAction> actions = List.of(createPerspectiveAction());
        when(it.actions()).thenReturn(actions);
        return it;
    }

    private PerspectiveAction createPerspectiveAction() {
        PerspectiveAction it = mock(PerspectiveAction.class);
        when(it.actionID()).thenReturn("actionID");
        when(it.annotations()).thenReturn(Optional.of(annotations));
        return it;
    }

    private PerspectiveKpi createPerspectiveKpi() {
        PerspectiveKpi it = mock(PerspectiveKpi.class);
        when(it.kpiID()).thenReturn("kpiID");
        when(it.annotations()).thenReturn(Optional.of(annotations));
        return it;
    }

    private PerspectiveCalculation createPerspectiveCalculation() {
        PerspectiveCalculation it = mock(PerspectiveCalculation.class);
        when(it.name()).thenReturn("name");
        when(it.type()).thenReturn("type");
        when(it.annotations()).thenReturn(Optional.of(annotations));
        return it;
    }

    private PerspectiveMeasureGroup createPerspectiveMeasureGroup() {
        PerspectiveMeasureGroup it = mock(PerspectiveMeasureGroup.class);
        when(it.measureGroupID()).thenReturn("measureGroupID");
        Optional<List<PerspectiveMeasure>> measures = Optional.of(List.of(createPerspectiveMeasure()));
        when(it.measures()).thenReturn(measures);
        when(it.annotations()).thenReturn(Optional.of(annotations));
        return it;
    }

    private PerspectiveMeasure createPerspectiveMeasure() {
        PerspectiveMeasure it = mock(PerspectiveMeasure.class);
        when(it.measureID()).thenReturn("measureID");
        when(it.annotations()).thenReturn(annotations);
        return it;
    }

    private PerspectiveDimension createPerspectiveDimension() {
        PerspectiveDimension it = mock(PerspectiveDimension.class);
        when(it.cubeDimensionID()).thenReturn("cubeDimensionID");
        Optional<List<PerspectiveAttribute>> attributes = Optional.of(List.of(createPerspectiveAttribute()));
        when(it.attributes()).thenReturn(attributes);
        Optional<List<PerspectiveHierarchy>> hierarchies = Optional.of(List.of(createPerspectiveHierarchy()));
        when(it.hierarchies()).thenReturn(hierarchies);
        when(it.annotations()).thenReturn(Optional.of(annotations));
        return it;
    }

    private PerspectiveHierarchy createPerspectiveHierarchy() {
        PerspectiveHierarchy it = mock(PerspectiveHierarchy.class);
        when(it.hierarchyID()).thenReturn("hierarchyID");
        when(it.annotations()).thenReturn(Optional.of(annotations));
        return it;
    }

    private PerspectiveAttribute createPerspectiveAttribute() {
        PerspectiveAttribute it = mock(PerspectiveAttribute.class);
        when(it.attributeID()).thenReturn("attributeID");
        when(it.attributeHierarchyVisible()).thenReturn(Optional.of(true));
        when(it.defaultMember()).thenReturn(Optional.of("defaultMember"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        return it;
    }

    private ServerProperty createServerProperty() {
        ServerProperty it = mock(ServerProperty.class);
        when(it.name()).thenReturn("name");
        when(it.value()).thenReturn("value");
        when(it.requiresRestart()).thenReturn(true);
        when(it.pendingValue()).thenReturn("pendingValue");
        when(it.defaultValue()).thenReturn("defaultValue");
        when(it.displayFlag()).thenReturn(true);
        when(it.type()).thenReturn("type");
        return it;
    }

    private Role createRole() {
        Role it = mock(Role.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn(Optional.of("id"));
        when(it.createdTimestamp()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.lastSchemaUpdate()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        Optional<List<Member>> members = Optional.of(List.of(createMember()));
        when(it.members()).thenReturn(members);
        return it;
    }

    private Member createMember() {
        Member it = mock(Member.class);
        when(it.name()).thenReturn(Optional.of("name"));
        when(it.sid()).thenReturn(Optional.of("sid"));
        return it;
    }

    private Assembly createAssembly() {
        Assembly it = mock(Assembly.class);
        when(it.id()).thenReturn("id");
        when(it.name()).thenReturn("name");
        when(it.createdTimestamp()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.lastSchemaUpdate()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.description()).thenReturn("description");
        List<Annotation> annotations = this.annotations;
        when(it.annotations()).thenReturn(annotations);
        ImpersonationInfo impersonationInfo = createImpersonationInfo();
        when(it.impersonationInfo()).thenReturn(impersonationInfo);
        return it;
    }

    private ImpersonationInfo createImpersonationInfo() {
        ImpersonationInfo it = mock(ImpersonationInfo.class);
        when(it.impersonationMode()).thenReturn("impersonationMode");
        when(it.account()).thenReturn(Optional.of("account"));
        when(it.password()).thenReturn(Optional.of("password"));
        when(it.impersonationInfoSecurity()).thenReturn(Optional.of("impersonationInfoSecurity"));
        return it;
    }

    private Database createDatabase() {
        Database it = mock(Database.class);
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn("id");
        when(it.createdTimestamp()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.lastSchemaUpdate()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.description()).thenReturn("description");
        when(it.annotations()).thenReturn(annotations);
        when(it.lastUpdate()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.state()).thenReturn("state");
        when(it.readWriteMode()).thenReturn("readWriteMode");
        when(it.dbStorageLocation()).thenReturn("dbStorageLocation");
        when(it.aggregationPrefix()).thenReturn("aggregationPrefix");
        when(it.processingPriority()).thenReturn(BigInteger.ONE);
        when(it.estimatedSize()).thenReturn(10l);
        when(it.lastProcessed()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(it.language()).thenReturn(BigInteger.TEN);
        when(it.collation()).thenReturn("collation");
        when(it.visible()).thenReturn(true);
        when(it.masterDataSourceID()).thenReturn("masterDataSourceID");
        ImpersonationInfo dataSourceImpersonationInfo = createImpersonationInfo();
        when(it.dataSourceImpersonationInfo()).thenReturn(dataSourceImpersonationInfo);
        List<Account> accounts = List.of(createAccount());
        when(it.accounts()).thenReturn(accounts);
        List<DataSource> dataSources = List.of(createDataSource());
        when(it.dataSources()).thenReturn(dataSources);
        List<DataSourceView> dataSourceViews = List.of(createDataSourceView());
        when(it.dataSourceViews()).thenReturn(dataSourceViews);
        List<Dimension> dimensions = List.of(createDimension());
        when(it.dimensions()).thenReturn(dimensions);
        List<Cube> cubes = List.of(createCube());
        when(it.cubes()).thenReturn(cubes);
        List<MiningStructure> miningStructures = List.of(createMiningStructure());
        when(it.miningStructures()).thenReturn(miningStructures);
        List<Role> roles = List.of(createRole());
        when(it.roles()).thenReturn(roles);
        List<Assembly> assemblies = List.of(createAssembly());
        when(it.assemblies()).thenReturn(assemblies);
        List<DatabasePermission> databasePermissions = List.of(createDatabasePermission());
        when(it.databasePermissions()).thenReturn(databasePermissions);
        List<Translation> translations = List.of(createTranslation());
        when(it.translations()).thenReturn(translations);
        when(it.storageEngineUsed()).thenReturn("storageEngineUsed");
        when(it.imagePath()).thenReturn("imagePath");
        when(it.imageUrl()).thenReturn("imageUrl");
        when(it.imageUniqueID()).thenReturn("imageUniqueID");
        when(it.imageVersion()).thenReturn("imageVersion");
        when(it.token()).thenReturn("token");
        when(it.compatibilityLevel()).thenReturn(BigInteger.TWO);
        when(it.directQueryMode()).thenReturn("directQueryMode");
        return it;
    }

    private DatabasePermission createDatabasePermission() {
        DatabasePermission it = mock(DatabasePermission.class);
        when(it.administer()).thenReturn(Optional.of(true));
        when(it.name()).thenReturn("name");
        when(it.id()).thenReturn(Optional.of("id"));
        when(it.createdTimestamp()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.lastSchemaUpdate()).thenReturn(Optional.of(Instant.parse("2024-01-10T10:45:00Z")));
        when(it.description()).thenReturn(Optional.of("description"));
        when(it.annotations()).thenReturn(Optional.of(annotations));
        when(it.roleID()).thenReturn("roleID");
        when(it.process()).thenReturn(Optional.of(true));
        when(it.readDefinition()).thenReturn(Optional.of(ReadDefinitionEnum.BASIC));
        when(it.read()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        when(it.write()).thenReturn(Optional.of(ReadWritePermissionEnum.ALLOWED));
        return it;
    }

    private Account createAccount() {
        Account it = mock(Account.class);
        when(it.accountType()).thenReturn("accountType");
        when(it.aggregationFunction()).thenReturn("aggregationFunction");
        when(it.aliases()).thenReturn(List.of("alias"));
        when(it.annotations()).thenReturn(annotations);
        return it;
    }

    private Trace createTrace() {
        Trace trace = mock(Trace.class);
        when(trace.name()).thenReturn(NAME);
        when(trace.id()).thenReturn(ID);
        when(trace.createdTimestamp()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(trace.lastSchemaUpdate()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        when(trace.description()).thenReturn(DESCRIPTION);
        when(trace.annotations()).thenReturn(this.annotations);
        when(trace.logFileName()).thenReturn("LogFileName");
        when(trace.logFileAppend()).thenReturn(true);
        when(trace.logFileSize()).thenReturn(10L);
        when(trace.audit()).thenReturn(true);
        when(trace.logFileRollover()).thenReturn(true);
        when(trace.autoRestart()).thenReturn(true);
        when(trace.stopTime()).thenReturn(Instant.parse("2024-01-10T10:45:00Z"));
        TraceFilter traceFilter = createTraceFilter();
        when(trace.filter()).thenReturn(traceFilter);
        EventType eventType = createEventType();
        when(trace.eventType()).thenReturn(eventType);
        return trace;
    }

    private ObjectReference createObjectReference() {
        ObjectReference objectReference = mock(ObjectReference.class);
        when(objectReference.serverID()).thenReturn("serverID");
        when(objectReference.databaseID()).thenReturn("databaseID");
        when(objectReference.roleID()).thenReturn("roleID");
        when(objectReference.traceID()).thenReturn("traceID");
        when(objectReference.assemblyID()).thenReturn("assemblyID");
        when(objectReference.dimensionID()).thenReturn("dimensionID");
        when(objectReference.dimensionPermissionID()).thenReturn("dimensionPermissionID");
        when(objectReference.dataSourceID()).thenReturn("dataSourceID");
        when(objectReference.dataSourcePermissionID()).thenReturn("dataSourcePermissionID");
        when(objectReference.databasePermissionID()).thenReturn("databasePermissionID");
        when(objectReference.dataSourceViewID()).thenReturn("dataSourceViewID");
        when(objectReference.cubeID()).thenReturn("cubeID");
        when(objectReference.miningStructureID()).thenReturn("miningStructureID");
        when(objectReference.measureGroupID()).thenReturn("measureGroupID");
        when(objectReference.perspectiveID()).thenReturn("perspectiveID");
        when(objectReference.cubePermissionID()).thenReturn("cubePermissionID");
        when(objectReference.mdxScriptID()).thenReturn("mdxScriptID");
        when(objectReference.partitionID()).thenReturn("partitionID");
        when(objectReference.aggregationDesignID()).thenReturn("aggregationDesignID");
        when(objectReference.miningModelID()).thenReturn("miningModelID");
        when(objectReference.miningModelPermissionID()).thenReturn("miningModelPermissionID");
        when(objectReference.miningStructurePermissionID()).thenReturn("miningStructurePermissionID");

        return objectReference;
    }

    private ClearCache createClearCache() {
        ClearCache clearCache = mock(ClearCache.class);
        ObjectReference objectReference = createObjectReference();
        when(clearCache.object()).thenReturn(objectReference);
        return clearCache;
    }

    private void checkEventType(XmlAssert xmlAssert, String path) {
        xmlAssert.nodesByXPath(path + "/EventType").exist();
        String eventPath = new StringBuilder(path).append("/EventType/Events").toString();
        xmlAssert.nodesByXPath(eventPath).exist();
        String xEventPath = new StringBuilder(path).append("/EventType/XEvent").toString();
        xmlAssert.nodesByXPath(xEventPath).exist();
        checkEvent(xmlAssert, eventPath);
        checkEventSession(xmlAssert, xEventPath);

    }

    private void checkEvent(XmlAssert xmlAssert, String path) {
        xmlAssert.nodesByXPath(path + "/Event").exist();
        xmlAssert.valueByXPath(path + "/Event/EventID").isEqualTo("EventID");
        checkEventEventColumnID(xmlAssert, path + "/Event");
    }

    private void checkEventEventColumnID(XmlAssert xmlAssert, String path) {
        xmlAssert.nodesByXPath(path + "/Columns").exist();
        xmlAssert.valueByXPath(path + "/Columns/ColumnID").isEqualTo("columnID1");

    }

    private void checkEventSession(XmlAssert xmlAssert, String path) {
        xmlAssert.nodesByXPath(path + "/event_session").exist();
        xmlAssert.valueByXPath(path + "/event_session/templateCategory").isEqualTo("templateCategory");
        xmlAssert.valueByXPath(path + "/event_session/templateName").isEqualTo("templateName");
        xmlAssert.valueByXPath(path + "/event_session/templateDescription").isEqualTo("templateDescription");
        checkObjectList(xmlAssert, path + "/event_session/event");
        checkObjectList(xmlAssert, path + "/event_session/target");
        xmlAssert.valueByXPath(path + "/event_session/name").isEqualTo("name");
        xmlAssert.valueByXPath(path + "/event_session/maxMemory").isEqualTo("1");
        xmlAssert.valueByXPath(path + "/event_session/eventRetentionMode").isEqualTo("allowSingleEventLoss");
        xmlAssert.valueByXPath(path + "/event_session/dispatchLatency").isEqualTo("10");
        xmlAssert.valueByXPath(path + "/event_session/maxEventSize").isEqualTo("11");
        xmlAssert.valueByXPath(path + "/event_session/memoryPartitionMode").isEqualTo("allowSingleEventLoss");
        xmlAssert.valueByXPath(path + "/event_session/trackCausality").isEqualTo(true);
    }

    private void checkObjectList(XmlAssert xmlAssert, String path) {
        xmlAssert.valueByXPath(path).isEqualTo("Object1");
    }

    private void checkTraceFilter(XmlAssert xmlAssert, String path) {
        xmlAssert.nodesByXPath(path + "/Filter").exist();
        checkNotType(xmlAssert, path + "/Filter/Not");
        checkAndOrType(xmlAssert, path + "/Filter/Or");
        checkAndOrType(xmlAssert, path + "/Filter/And");
        checkBoolBinop(xmlAssert, path + "/Filter/Equal");
        checkBoolBinop(xmlAssert, path + "/Filter/NotEqual");
        checkBoolBinop(xmlAssert, path + "/Filter/Less");
        checkBoolBinop(xmlAssert, path + "/Filter/LessOrEqual");
        checkBoolBinop(xmlAssert, path + "/Filter/Greater");
        checkBoolBinop(xmlAssert, path + "/Filter/GreaterOrEqual");
        checkBoolBinop(xmlAssert, path + "/Filter/Like");
        checkBoolBinop(xmlAssert, path + "/Filter/NotLike");
    }

    private void checkNotType(XmlAssert xmlAssert, String path) {
        xmlAssert.nodesByXPath(path).exist();
        checkAndOrType(xmlAssert, path + "/Or");
        checkAndOrType(xmlAssert, path + "/And");
        checkBoolBinop(xmlAssert, path + "/Equal");
        checkBoolBinop(xmlAssert, path + "/NotEqual");
        checkBoolBinop(xmlAssert, path + "/Less");
        checkBoolBinop(xmlAssert, path + "/LessOrEqual");
        checkBoolBinop(xmlAssert, path + "/Greater");
        checkBoolBinop(xmlAssert, path + "/GreaterOrEqual");
        checkBoolBinop(xmlAssert, path + "/Like");
        checkBoolBinop(xmlAssert, path + "/NotLike");
    }

    private void checkAndOrType(XmlAssert xmlAssert, String path) {
        xmlAssert.nodesByXPath(path).exist();
        xmlAssert.nodesByXPath(path + "/And").exist();
    }

    private void checkBoolBinop(XmlAssert xmlAssert, String path) {
        xmlAssert.nodesByXPath(path).exist();
        xmlAssert.valueByXPath(path + "/ColumnID").isEqualTo("columnID");
        xmlAssert.valueByXPath(path + "/Value").isEqualTo("value");
    }

    private void checkAnnotationList(XmlAssert xmlAssert, String path) {
        xmlAssert.nodesByXPath(path + "/Annotations").exist();
        xmlAssert.valueByXPath(path + "/Annotations/Annotation/Name").isEqualTo(NAME);
        xmlAssert.valueByXPath(path + "/Annotations/Annotation/Visibility").isEqualTo("Visibility");
        xmlAssert.valueByXPath(path + "/Annotations/Annotation/Value").isEqualTo(VALUE2);
    }

    private List<Annotation> createAnnotationList() {
        Annotation annotation1 = mock(Annotation.class);
        when(annotation1.name()).thenReturn(NAME);
        when(annotation1.value()).thenReturn(Optional.of(VALUE2));
        when(annotation1.visibility()).thenReturn(Optional.of("Visibility"));
        return List.of(annotation1);
    }

    private Cancel createCancel() {
        Cancel cancel = mock(Cancel.class);
        when(cancel.connectionID()).thenReturn(BigInteger.ONE);
        when(cancel.sessionID()).thenReturn("SessionID");
        when(cancel.spid()).thenReturn(BigInteger.TEN);
        when(cancel.cancelAssociated()).thenReturn(true);
        return cancel;
    }

    private EventType createEventType() {
        EventType eventType = mock(EventType.class);
        List<Event> l = createEventList();
        when(eventType.events()).thenReturn(l);
        XEvent xEvent = createXEvent();
        when(eventType.xEvent()).thenReturn(xEvent);
        return eventType;
    }

    private XEvent createXEvent() {
        XEvent xEvent = mock(XEvent.class);
        EventSession eventSession = createEventSession();
        when(xEvent.eventSession()).thenReturn(eventSession);
        return xEvent;
    }

    private EventSession createEventSession() {
        EventSession eventSession = mock(EventSession.class);
        when(eventSession.templateCategory()).thenReturn("templateCategory");
        when(eventSession.templateName()).thenReturn("templateName");
        when(eventSession.templateDescription()).thenReturn("templateDescription");
        when(eventSession.event()).thenReturn(createListObject());
        when(eventSession.target()).thenReturn(createListObject());
        when(eventSession.name()).thenReturn("name");
        when(eventSession.maxMemory()).thenReturn(BigInteger.ONE);
        when(eventSession.eventRetentionMode()).thenReturn(RetentionModes.ALLOW_SINGLE_EVENT_LOSS);
        when(eventSession.dispatchLatency()).thenReturn(10l);
        when(eventSession.maxEventSize()).thenReturn(11l);
        when(eventSession.memoryPartitionMode()).thenReturn(PartitionModes.NONE);
        when(eventSession.trackCausality()).thenReturn(true);
        return eventSession;
    }

    private List<Object> createListObject() {
        return List.of("Object1", "Object2");
    }

    private List<Event> createEventList() {
        Event event = createEvent();
        return List.of(event);
    }

    private Event createEvent() {
        Event event = mock(Event.class);
        when(event.eventID()).thenReturn("EventID");
        EventColumnID ecid = createEventColumnID();
        when(event.columns()).thenReturn(ecid);
        return event;
    }

    private EventColumnID createEventColumnID() {
        EventColumnID eventColumnID = mock(EventColumnID.class);
        when(eventColumnID.columnID()).thenReturn(List.of("columnID1", "columnID2"));
        return eventColumnID;
    }

    TraceFilter createTraceFilter() {
        TraceFilter traceFilter = mock(TraceFilter.class);
        NotType not = createNotType();
        AndOrType or = createAndOrType();
        AndOrType and = or;
        BoolBinop isEqual = createBoolBinop();
        BoolBinop notEqual = isEqual;
        BoolBinop less = isEqual;
        BoolBinop lessOrEqual = isEqual;
        BoolBinop greater = isEqual;
        BoolBinop greaterOrEqual = isEqual;
        BoolBinop like = isEqual;
        BoolBinop notLike = isEqual;
        when(traceFilter.not()).thenReturn(not);
        when(traceFilter.or()).thenReturn(or);
        when(traceFilter.and()).thenReturn(and);
        when(traceFilter.isEqual()).thenReturn(isEqual);
        when(traceFilter.notEqual()).thenReturn(notEqual);
        when(traceFilter.less()).thenReturn(less);
        when(traceFilter.lessOrEqual()).thenReturn(lessOrEqual);
        when(traceFilter.greater()).thenReturn(greater);
        when(traceFilter.greaterOrEqual()).thenReturn(greaterOrEqual);
        when(traceFilter.like()).thenReturn(like);
        when(traceFilter.notLike()).thenReturn(notLike);
        return traceFilter;
    }

    private BoolBinop createBoolBinop() {
        BoolBinop boolBinop = mock(BoolBinop.class);
        when(boolBinop.columnID()).thenReturn("columnID");
        when(boolBinop.value()).thenReturn("value");
        return boolBinop;
    }

    private NotType createNotType() {
        NotType notType = mock(NotType.class);
        AndOrType or = createAndOrType();
        AndOrType and = or;
        BoolBinop isEqual = createBoolBinop();
        BoolBinop notEqual = isEqual;
        BoolBinop less = isEqual;
        BoolBinop lessOrEqual = isEqual;
        BoolBinop greater = isEqual;
        BoolBinop greaterOrEqual = isEqual;
        BoolBinop like = isEqual;
        BoolBinop notLike = isEqual;
        when(notType.or()).thenReturn(or);
        when(notType.and()).thenReturn(and);
        when(notType.isEqual()).thenReturn(isEqual);
        when(notType.notEqual()).thenReturn(notEqual);
        when(notType.less()).thenReturn(less);
        when(notType.lessOrEqual()).thenReturn(lessOrEqual);
        when(notType.greater()).thenReturn(greater);
        when(notType.greaterOrEqual()).thenReturn(greaterOrEqual);
        when(notType.like()).thenReturn(like);
        when(notType.notLike()).thenReturn(notLike);
        return notType;
    }

    private AndOrType createAndOrType() {
        AndOrType andOrType = mock(AndOrType.class);
        List<AndOrTypeEnum> l = List.of(AndOrTypeEnum.And);
        when(andOrType.notOrOrOrAnd()).thenReturn(l);
        return andOrType;
    }

    private ExecuteParameter createExecuteParameter() {
        ExecuteParameter executeParameter = mock(ExecuteParameter.class);
        when(executeParameter.value()).thenReturn(VALUE2);
        when(executeParameter.name()).thenReturn(NAME_LC);
        return executeParameter;
    }

    private void checkServer(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Server").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/ProductName").isEqualTo("productName");
        xmlAssert.valueByXPath(p + "/Edition").isEqualTo("edition");
        xmlAssert.valueByXPath(p + "/EditionID").isEqualTo("10");
        xmlAssert.valueByXPath(p + "/Version").isEqualTo("version");
        xmlAssert.valueByXPath(p + "/ServerMode").isEqualTo("serverMode");
        xmlAssert.valueByXPath(p + "/ProductLevel").isEqualTo("productLevel");
        xmlAssert.valueByXPath(p + "/DefaultCompatibilityLevel").isEqualTo("11");
        xmlAssert.valueByXPath(p + "/SupportedCompatibilityLevels").isEqualTo("supportedCompatibilityLevels");
        checkDatabaseList(xmlAssert, p);
        checkAssemblyList(xmlAssert, p);
        checkTraceList(xmlAssert, p);
        checkRoleList(xmlAssert, p);
        checkServerPropertyList(xmlAssert, p);
    }

    private void checkServerPropertyList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/ServerProperties").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkServerProperty(xmlAssert, p);
    }

    private void checkServerProperty(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/ServerProperty").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/Value").isEqualTo("value");
        xmlAssert.valueByXPath(p + "/RequiresRestart").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/PendingValue").isEqualTo("pendingValue");
        xmlAssert.valueByXPath(p + "/DefaultValue").isEqualTo("defaultValue");
        xmlAssert.valueByXPath(p + "/DisplayFlag").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/Type").isEqualTo("type");
    }

    private void checkRoleList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Roles").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkRole(xmlAssert, p);
    }

    private void checkTraceList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Traces").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkTrace(xmlAssert, p);
    }

    private void checkAssemblyList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Assemblies").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAssembly(xmlAssert, p);
    }

    private void checkDatabaseList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Databases").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkDatabase(xmlAssert, p);
    }

    private void checkRole(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Role").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAbstractItem(xmlAssert, p);
        checkMemberList(xmlAssert, p);
    }

    private void checkMemberList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Members").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkMember(xmlAssert, p);
    }

    private void checkMember(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Member").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/Sid").isEqualTo("sid");
    }

    private void checkPerspective(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Perspective").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
        xmlAssert.valueByXPath(p + "/DefaultMeasure").isEqualTo("defaultMeasure");
        checkPerspectiveDimensionList(xmlAssert, p);
        checkPerspectiveMeasureGroupList(xmlAssert, p);
        checkPerspectiveCalculationList(xmlAssert, p);
        checkPerspectiveKpiList(xmlAssert, p);
        checkPerspectiveActionList(xmlAssert, p);
    }

    private void checkPerspectiveActionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Actions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkPerspectiveAction(xmlAssert, p);
    }

    private void checkPerspectiveAction(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Action").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/ActionID").isEqualTo("actionID");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkPerspectiveKpiList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Kpis").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkPerspectiveKpi(xmlAssert, p);
    }

    private void checkPerspectiveKpi(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Kpi").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/KpiID").isEqualTo("kpiID");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkPerspectiveCalculationList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Calculations").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkPerspectiveCalculation(xmlAssert, p);
    }

    private void checkPerspectiveCalculation(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Calculation").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/Type").isEqualTo("type");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkPerspectiveMeasureGroupList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/MeasureGroups").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkPerspectiveMeasureGroup(xmlAssert, p);
    }

    private void checkPerspectiveMeasureGroup(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/MeasureGroup").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/MeasureGroupID").isEqualTo("measureGroupID");
        checkPerspectiveMeasureList(xmlAssert, p);
        checkAnnotationList(xmlAssert, p);
    }

    private void checkPerspectiveMeasureList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Measures").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkPerspectiveMeasure(xmlAssert, p);
    }

    private void checkPerspectiveMeasure(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Measure").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/MeasureID").isEqualTo("measureID");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkPerspectiveDimensionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Dimensions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkPerspectiveDimension(xmlAssert, p);
    }

    private void checkPerspectiveDimension(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Dimension").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/CubeDimensionID").isEqualTo("cubeDimensionID");
        checkPerspectiveAttributeList(xmlAssert, p);
        checkPerspectiveHierarchyList(xmlAssert, p);
        checkAnnotationList(xmlAssert, p);
    }

    private void checkPerspectiveHierarchyList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Hierarchies").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkPerspectiveHierarchy(xmlAssert, p);
    }

    private void checkPerspectiveHierarchy(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Hierarchy").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/HierarchyID").isEqualTo("hierarchyID");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkPerspectiveAttributeList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Attributes").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkPerspectiveAttribute(xmlAssert, p);
    }

    private void checkPerspectiveAttribute(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Attribute").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/AttributeID").isEqualTo("attributeID");
        xmlAssert.valueByXPath(p + "/AttributeHierarchyVisible").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/DefaultMember").isEqualTo("defaultMember");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkPermission(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Permission").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAbstractPermission(xmlAssert, p);
    }

    private void checkAbstractPermission(XmlAssert xmlAssert, String p) {
        checkAbstractItem(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/RoleID").isEqualTo("roleID");
        xmlAssert.valueByXPath(p + "/Process").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/ReadDefinition").isEqualTo("Basic");
        xmlAssert.valueByXPath(p + "/Read").isEqualTo("Allowed");
        xmlAssert.valueByXPath(p + "/Write").isEqualTo("Allowed");
    }

    private void checkPartition(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Partition").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkTabularBinding(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/ProcessingPriority").isEqualTo("1");
        xmlAssert.valueByXPath(p + "/AggregationPrefix").isEqualTo("aggregationPrefix");
        checkPartitionStorageMode(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/ProcessingMode").isEqualTo("processingMode");
        checkErrorConfiguration(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/StorageLocation").isEqualTo("storageLocation");
        xmlAssert.valueByXPath(p + "/RemoteDatasourceID").isEqualTo("remoteDatasourceID");
        xmlAssert.valueByXPath(p + "/Slice").isEqualTo("slice");
        checkProactiveCaching(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/Type").isEqualTo("type");
        xmlAssert.valueByXPath(p + "/EstimatedSize").isEqualTo("10");
        xmlAssert.valueByXPath(p + "/EstimatedRows").isEqualTo("11");
        checkPartitionCurrentStorageMode(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/AggregationDesignID").isEqualTo("aggregationDesignID");
        checkAggregationInstanceList(xmlAssert, p);
        checkDataSourceViewBinding(xmlAssert, p, "AggregationInstanceSource");
        xmlAssert.valueByXPath(p + "/LastProcessed").isEqualTo("2024-01-10T10:45:00Z");
        xmlAssert.valueByXPath(p + "/State").isEqualTo("state");
        xmlAssert.valueByXPath(p + "/StringStoresCompatibilityLevel").isEqualTo("12");
        xmlAssert.valueByXPath(p + "/CurrentStringStoresCompatibilityLevel").isEqualTo("14");
        xmlAssert.valueByXPath(p + "/DirectQueryUsage").isEqualTo("directQueryUsage");
    }

    private void checkAggregationInstanceList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/AggregationInstances").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAggregationInstance(xmlAssert, p);
    }

    private void checkAggregationInstance(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/AggregationInstance").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/AggregationType").isEqualTo("aggregationType");
        checkTabularBinding(xmlAssert, p);
        checkAggregationInstanceDimensionList(xmlAssert, p);
        checkAggregationInstanceMeasureList(xmlAssert, p);
    }

    private void checkAggregationInstanceMeasureList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Measures").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAggregationInstanceMeasure(xmlAssert, p);
    }

    private void checkAggregationInstanceDimensionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Dimensions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAggregationInstanceDimension(xmlAssert, p);
    }

    private void checkAggregationInstanceMeasure(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Measure").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/MeasureID").isEqualTo("measureID");
        checkColumnBinding(xmlAssert, p);
    }

    private void checkColumnBinding(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Source").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/TableID").isEqualTo("tableID");
        xmlAssert.valueByXPath(p + "/ColumnID").isEqualTo("columnID");
    }

    private void checkAggregationInstanceDimension(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Dimension").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/CubeDimensionID").isEqualTo("cubeDimensionID");
        checkAggregationInstanceAttributeList(xmlAssert, p);
    }

    private void checkAggregationInstanceAttributeList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Attributes").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAggregationInstanceAttribute(xmlAssert, p);
    }

    private void checkAggregationInstanceAttribute(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Attribute").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/AttributeID").isEqualTo("attributeID");
        checkDataItemList(xmlAssert, p, "KeyColumns", "KeyColumn");
    }

    private void checkDataItemList(XmlAssert xmlAssert, String path, String tagNameList, String tagName) {
        String p = new StringBuilder(path).append("/").append(tagNameList).toString();
        xmlAssert.nodesByXPath(p).exist();
        checkDataItem(xmlAssert, p, tagName);
    }

    private void checkDataItem(XmlAssert xmlAssert, String path, String tagName) {
        String p = new StringBuilder(path).append("/").append(tagName).toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/DataType").isEqualTo("dataType");
        xmlAssert.valueByXPath(p + "/DataSize").isEqualTo("10");
        xmlAssert.valueByXPath(p + "/MimeType").isEqualTo("mimeType");
        xmlAssert.valueByXPath(p + "/NullProcessing").isEqualTo("Automatic");
        xmlAssert.valueByXPath(p + "/Trimming").isEqualTo("trimming");
        xmlAssert.valueByXPath(p + "/InvalidXmlCharacters").isEqualTo("Preserve");
        xmlAssert.valueByXPath(p + "/Collation").isEqualTo("collation");
        xmlAssert.valueByXPath(p + "/Format").isEqualTo("TrimAll");
        checkBinding(xmlAssert, p);
        checkAnnotationList(xmlAssert, p);
    }

    private void checkBinding(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Source").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/TableID").isEqualTo("tableID");
        xmlAssert.valueByXPath(p + "/ColumnID").isEqualTo("columnID");
    }

    private void checkPartitionCurrentStorageMode(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/CurrentStorageMode").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p).isEqualTo("Rolap");
    }

    private void checkPartitionStorageMode(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/StorageMode").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p).isEqualTo("Rolap");
    }

    private void checkTabularBinding(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Source").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/DataSourceID").isEqualTo("dataSourceID");
        xmlAssert.valueByXPath(p + "/QueryDefinition").isEqualTo("queryDefinition");
    }

    private void checkMiningStructure(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/MiningStructure").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkBinding(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/LastProcessed").isEqualTo("2024-01-10T10:45:00Z");
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
        xmlAssert.valueByXPath(p + "/Language").isEqualTo("1");
        xmlAssert.valueByXPath(p + "/Collation").isEqualTo("collation");
        checkErrorConfiguration(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/CacheMode").isEqualTo("cacheMode");
        xmlAssert.valueByXPath(p + "/HoldoutMaxPercent").isEqualTo("10");
        xmlAssert.valueByXPath(p + "/HoldoutMaxCases").isEqualTo("11");
        xmlAssert.valueByXPath(p + "/HoldoutSeed").isEqualTo("12");
        xmlAssert.valueByXPath(p + "/HoldoutActualSize").isEqualTo("14");
        checkMiningStructureColumnList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/State").isEqualTo("state");
        checkMiningStructurePermissionList(xmlAssert, p);
        checkMiningModelList(xmlAssert, p);
    }

    private void checkMiningModelList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/MiningModels").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkMiningModel(xmlAssert, p);
    }

    private void checkMiningStructurePermissionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/MiningStructurePermissions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkMiningStructurePermission(xmlAssert, p);
    }

    private void checkMiningStructurePermission(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/MiningStructurePermission").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAbstractItem(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/RoleID").isEqualTo("roleID");
        xmlAssert.valueByXPath(p + "/Process").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/ReadDefinition").isEqualTo("Basic");
        xmlAssert.valueByXPath(p + "/Read").isEqualTo("Allowed");
        xmlAssert.valueByXPath(p + "/Write").isEqualTo("Allowed");
        xmlAssert.valueByXPath(p + "/AllowDrillThrough").isEqualTo("true");
    }

    private void checkMiningStructureColumnList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Columns").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkMiningStructureColumn(xmlAssert, p);
    }

    private void checkMiningStructureColumn(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Column").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/ID").isEqualTo("id");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        xmlAssert.valueByXPath(p + "/Type").isEqualTo("type");
        checkAnnotationList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/IsKey").isEqualTo("true");
        checkBinding(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/Distribution").isEqualTo("distribution");
        checkMiningModelingFlagList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/Content").isEqualTo("content");
        checkClassifiedColumnList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/DiscretizationMethod").isEqualTo("discretizationMethod");
        xmlAssert.valueByXPath(p + "/DiscretizationBucketCount").isEqualTo("1");
        checkDataItemList(xmlAssert, p, "KeyColumns", "KeyColumn");
        checkDataItem(xmlAssert, p, "NameColumn");
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
    }

    private void checkClassifiedColumnList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/ClassifiedColumns").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkClassifiedColumn(xmlAssert, p);
    }

    private void checkClassifiedColumn(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/ClassifiedColumn").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p).isEqualTo("classifiedColumn");
    }

    private void checkMiningModelingFlagList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/ModelingFlags").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkMiningModelingFlag(xmlAssert, p);
    }

    private void checkMiningModelingFlag(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/ModelingFlag").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p).isEqualTo("modelingFlag");
    }

    private void checkMiningModel(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/MiningModel").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Algorithm").isEqualTo("algorithm");
        xmlAssert.valueByXPath(p + "/LastProcessed").isEqualTo("2024-01-10T10:45:00Z");
        checkAlgorithmParameterList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/AllowDrillThrough").isEqualTo("true");
        checkAttributeTranslationList(xmlAssert, p);
        checkMiningModelColumnList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/State").isEqualTo("state");
        checkFoldingParameters(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/Filter").isEqualTo("filter");
        checkMiningModelPermissionList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/Language").isEqualTo("language");
        xmlAssert.valueByXPath(p + "/Collation").isEqualTo("collation");
    }

    private void checkMiningModelPermissionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/MiningModelPermissions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkMiningModelPermission(xmlAssert, p);
    }

    private void checkMiningModelPermission(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/MiningModelPermission").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAbstractItem(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/RoleID").isEqualTo("roleID");
        xmlAssert.valueByXPath(p + "/Process").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/ReadDefinition").isEqualTo("Allowed");
        xmlAssert.valueByXPath(p + "/Read").isEqualTo("Allowed");
        xmlAssert.valueByXPath(p + "/Write").isEqualTo("Allowed");
        xmlAssert.valueByXPath(p + "/AllowDrillThrough").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/AllowBrowsing").isEqualTo("true");
    }

    private void checkFoldingParameters(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/FoldingParameters").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/FoldIndex").isEqualTo("10");
        xmlAssert.valueByXPath(p + "/FoldCount").isEqualTo("11");
        xmlAssert.valueByXPath(p + "/FoldMaxCases").isEqualTo("12");
        xmlAssert.valueByXPath(p + "/FoldTargetAttribute").isEqualTo("foldTargetAttribute");
    }

    private void checkMiningModelColumnList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Columns").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkMiningModelColumn(xmlAssert, p);

    }

    private void checkMiningModelColumn(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Column").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/ID").isEqualTo("id");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        xmlAssert.valueByXPath(p + "/SourceColumnID").isEqualTo("sourceColumnID");
        xmlAssert.valueByXPath(p + "/Usage").isEqualTo("usage");
        xmlAssert.valueByXPath(p + "/Filter").isEqualTo("filter");
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
        checkMiningModelingFlagList(xmlAssert, p);
        checkAnnotationList(xmlAssert, p);
    }

    private void checkAttributeTranslationList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Translations").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAttributeTranslation(xmlAssert, p);

    }

    private void checkAttributeTranslation(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Translation").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAbstractTranslation(xmlAssert, p);
        checkDataItem(xmlAssert, p, "CaptionColumn");
        xmlAssert.valueByXPath(p + "/MembersWithDataCaption").isEqualTo("membersWithDataCaption");
    }

    private void checkAbstractTranslation(XmlAssert xmlAssert, String p) {
        xmlAssert.valueByXPath(p + "/Language").isEqualTo("10");
        xmlAssert.valueByXPath(p + "/Caption").isEqualTo("caption");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        xmlAssert.valueByXPath(p + "/DisplayFolder").isEqualTo("displayFolder");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkAlgorithmParameterList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/AlgorithmParameters").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAlgorithmParameter(xmlAssert, p);
    }

    private void checkAlgorithmParameter(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/AlgorithmParameter").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/Value").isEqualTo("value");
    }

    private void checkMeasureGroup(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/MeasureGroup").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/LastProcessed").isEqualTo("2024-01-10T10:45:00Z");
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
        xmlAssert.valueByXPath(p + "/Type").isEqualTo("type");
        xmlAssert.valueByXPath(p + "/State").isEqualTo("state");
        checkMeasureList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/DataAggregation").isEqualTo("dataAggregation");
        checkMeasureGroupBinding(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/StorageMode").isEqualTo("Rolap");
        xmlAssert.valueByXPath(p + "/StorageLocation").isEqualTo("storageLocation");
        xmlAssert.valueByXPath(p + "/IgnoreUnrelatedDimensions").isEqualTo("true");
        checkProactiveCaching(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/EstimatedRows").isEqualTo("10");
        checkErrorConfiguration(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/EstimatedSize").isEqualTo("11");
        xmlAssert.valueByXPath(p + "/ProcessingMode").isEqualTo("processingMode");
        checkMeasureGroupDimensionList(xmlAssert, p);
        checkPartitionList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/AggregationPrefix").isEqualTo("aggregationPrefix");
        xmlAssert.valueByXPath(p + "/ProcessingPriority").isEqualTo("1");
        checkAggregationDesignList(xmlAssert, p);
    }

    private void checkAggregationDesignList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/AggregationDesigns").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAggregationDesign(xmlAssert, p);
    }

    private void checkPartitionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Partitions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkPartition(xmlAssert, p);
    }

    private void checkMeasureGroupDimensionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Dimensions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkMeasureGroupDimension(xmlAssert, p);
    }

    private void checkMeasureGroupDimension(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Dimension").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/CubeDimensionID").isEqualTo("cubeDimensionID");
        checkAnnotationList(xmlAssert, p);
        checkMeasureGroupDimensionBinding(xmlAssert, p);

        xmlAssert.valueByXPath(p + "/MeasureGroupID").isEqualTo("measureGroupID");
        xmlAssert.valueByXPath(p + "/DirectSlice").isEqualTo("directSlice");
    }

    private void checkMeasureGroupDimensionBinding(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Source").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/CubeDimensionID").isEqualTo("cubeDimensionID");
    }

    private void checkMeasureGroupBinding(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Source").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/DataSourceID").isEqualTo("dataSourceID");
        xmlAssert.valueByXPath(p + "/CubeID").isEqualTo("cubeID");
        xmlAssert.valueByXPath(p + "/MeasureGroupID").isEqualTo("measureGroupID");
        xmlAssert.valueByXPath(p + "/Persistence").isEqualTo("Metadata");
        xmlAssert.valueByXPath(p + "/RefreshPolicy").isEqualTo("ByQuery");
        xmlAssert.valueByXPath(p + "/RefreshInterval").isEqualTo("PT48H");
        xmlAssert.valueByXPath(p + "/Filter").isEqualTo("filter");
    }

    private void checkMeasureList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Measures").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkMeasure(xmlAssert, p);
    }

    private void checkMeasure(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Measure").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/ID").isEqualTo("id");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        xmlAssert.valueByXPath(p + "/AggregateFunction").isEqualTo("aggregateFunction");
        xmlAssert.valueByXPath(p + "/DataType").isEqualTo("dataType");
        checkDataItem(xmlAssert, p, "Source");
        xmlAssert.valueByXPath(p + "/Visible").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/MeasureExpression").isEqualTo("measureExpression");
        xmlAssert.valueByXPath(p + "/DisplayFolder").isEqualTo("displayFolder");
        xmlAssert.valueByXPath(p + "/FormatString").isEqualTo("formatString");
        xmlAssert.valueByXPath(p + "/BackColor").isEqualTo("backColor");
        xmlAssert.valueByXPath(p + "/ForeColor").isEqualTo("foreColor");
        xmlAssert.valueByXPath(p + "/FontName").isEqualTo("fontName");
        xmlAssert.valueByXPath(p + "/FontSize").isEqualTo("fontSize");
        xmlAssert.valueByXPath(p + "/FontFlags").isEqualTo("fontFlags");
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkMdxScript(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/MdxScript").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAbstractItem(xmlAssert, p);
        // checkCommandList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/DefaultScript").isEqualTo("true");
        checkCalculationPropertyList(xmlAssert, p);
    }

    private void checkCalculationPropertyList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/CalculationProperties").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkCalculationProperty(xmlAssert, p);
    }

    private void checkCalculationProperty(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/CalculationProperty").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/CalculationReference").isEqualTo("calculationReference");
        xmlAssert.valueByXPath(p + "/CalculationType").isEqualTo("calculationType");
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        xmlAssert.valueByXPath(p + "/Visible").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/SolveOrder").isEqualTo("1");

        xmlAssert.valueByXPath(p + "/FormatString").isEqualTo("formatString");
        xmlAssert.valueByXPath(p + "/BackColor").isEqualTo("backColor");
        xmlAssert.valueByXPath(p + "/ForeColor").isEqualTo("foreColor");
        xmlAssert.valueByXPath(p + "/FontName").isEqualTo("fontName");
        xmlAssert.valueByXPath(p + "/FontSize").isEqualTo("fontSize");
        xmlAssert.valueByXPath(p + "/FontFlags").isEqualTo("fontFlags");

        xmlAssert.valueByXPath(p + "/NonEmptyBehavior").isEqualTo("nonEmptyBehavior");
        xmlAssert.valueByXPath(p + "/AssociatedMeasureGroupID").isEqualTo("associatedMeasureGroupID");
        xmlAssert.valueByXPath(p + "/DisplayFolder").isEqualTo("displayFolder");
        xmlAssert.valueByXPath(p + "/Language").isEqualTo("10");
        checkCalculationPropertiesVisualizationProperties(xmlAssert, p);
    }

    private void checkCalculationPropertiesVisualizationProperties(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/VisualizationProperties").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAbstractProperties(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/IsDefaultMeasure").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/IsSimpleMeasure").isEqualTo("true");
    }

    private void checkAbstractProperties(XmlAssert xmlAssert, String p) {

        xmlAssert.valueByXPath(p + "/FolderPosition").isEqualTo("1");
        xmlAssert.valueByXPath(p + "/ContextualNameRule").isEqualTo("contextualNameRule");
        xmlAssert.valueByXPath(p + "/Alignment").isEqualTo("alignment");
        xmlAssert.valueByXPath(p + "/IsFolderDefault").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/IsRightToLeft").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/SortDirection").isEqualTo("sortDirection");
        xmlAssert.valueByXPath(p + "/Units").isEqualTo("units");
        xmlAssert.valueByXPath(p + "/Width").isEqualTo("2");
        xmlAssert.valueByXPath(p + "/DefaultDetailsPosition").isEqualTo("2");
        xmlAssert.valueByXPath(p + "/SortPropertiesPosition").isEqualTo("0");
    }

    private void checkAlter(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Alter").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkObjectReference(xmlAssert, p);
        checkMajorObject(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/Scope").isEqualTo("Session");
        xmlAssert.valueByXPath(p + "/AllowCreate").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/ObjectExpansion").isEqualTo("ExpandFull");
    }

    private void checkObjectReference(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Object").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/ServerID").isEqualTo("serverID");
        xmlAssert.valueByXPath(p + "/DatabaseID").isEqualTo("databaseID");
        xmlAssert.valueByXPath(p + "/RoleID").isEqualTo("roleID");
        xmlAssert.valueByXPath(p + "/TraceID").isEqualTo("traceID");
        xmlAssert.valueByXPath(p + "/AssemblyID").isEqualTo("assemblyID");
        xmlAssert.valueByXPath(p + "/DimensionID").isEqualTo("dimensionID");
        xmlAssert.valueByXPath(p + "/DimensionPermissionID").isEqualTo("dimensionPermissionID");
        xmlAssert.valueByXPath(p + "/DataSourceID").isEqualTo("dataSourceID");
        xmlAssert.valueByXPath(p + "/DataSourcePermissionID").isEqualTo("dataSourcePermissionID");
        xmlAssert.valueByXPath(p + "/DatabasePermissionID").isEqualTo("databasePermissionID");
        xmlAssert.valueByXPath(p + "/DataSourceViewID").isEqualTo("dataSourceViewID");
        xmlAssert.valueByXPath(p + "/CubeID").isEqualTo("cubeID");
        xmlAssert.valueByXPath(p + "/MiningStructureID").isEqualTo("miningStructureID");
        xmlAssert.valueByXPath(p + "/MeasureGroupID").isEqualTo("measureGroupID");
        xmlAssert.valueByXPath(p + "/PerspectiveID").isEqualTo("perspectiveID");
        xmlAssert.valueByXPath(p + "/CubePermissionID").isEqualTo("cubePermissionID");
        xmlAssert.valueByXPath(p + "/MdxScriptID").isEqualTo("mdxScriptID");
        xmlAssert.valueByXPath(p + "/PartitionID").isEqualTo("partitionID");
        xmlAssert.valueByXPath(p + "/AggregationDesignID").isEqualTo("aggregationDesignID");
        xmlAssert.valueByXPath(p + "/MiningModelID").isEqualTo("miningModelID");
        xmlAssert.valueByXPath(p + "/MiningModelPermissionID").isEqualTo("miningModelPermissionID");
        xmlAssert.valueByXPath(p + "/MiningStructurePermissionID").isEqualTo("miningStructurePermissionID");
    }

    private void checkDimension(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Dimension").toString();
        xmlAssert.nodesByXPath(p).exist();

        checkBinding(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/MiningModelID").isEqualTo("miningModelID");
        xmlAssert.valueByXPath(p + "/Type").isEqualTo("type");
        xmlAssert.valueByXPath(p + "/Type").isEqualTo("type");
        checkDimensionUnknownMember(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/MdxMissingMemberMode").isEqualTo("mdxMissingMemberMode");
        xmlAssert.valueByXPath(p + "/MdxMissingMemberMode").isEqualTo("mdxMissingMemberMode");
        checkErrorConfiguration(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/StorageMode").isEqualTo("storageMode");
        xmlAssert.valueByXPath(p + "/WriteEnabled").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/ProcessingPriority").isEqualTo("10");
        xmlAssert.valueByXPath(p + "/LastProcessed").isEqualTo("2024-01-10T10:45:00Z");
        checkDimensionPermissionList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/DependsOnDimensionID").isEqualTo("dependsOnDimensionID");
        xmlAssert.valueByXPath(p + "/Language").isEqualTo("2");
        xmlAssert.valueByXPath(p + "/Collation").isEqualTo("collation");
        xmlAssert.valueByXPath(p + "/UnknownMemberName").isEqualTo("unknownMemberName");
        checkTranslationList(xmlAssert, p, "UnknownMemberTranslations", "UnknownMemberTranslation");
        xmlAssert.valueByXPath(p + "/State").isEqualTo("state");
        checkProactiveCaching(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/ProcessingMode").isEqualTo("processingMode");
        xmlAssert.valueByXPath(p + "/ProcessingGroup").isEqualTo("processingGroup");
        checkDimensionCurrentStorageMode(xmlAssert, p);
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
        checkDimensionAttributeList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/AttributeAllMemberName").isEqualTo("attributeAllMemberName");
        checkTranslationList(xmlAssert, p, "AttributeAllMemberTranslations", "AttributeAllMemberTranslation");
        checkHierarchyList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/ProcessingRecommendation").isEqualTo("processingRecommendation");
        checkRelationships(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/StringStoresCompatibilityLevel").isEqualTo("10");
        xmlAssert.valueByXPath(p + "/CurrentStringStoresCompatibilityLevel").isEqualTo("11");
    }

    private void checkRelationships(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Relationships").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkRelationshipList(xmlAssert, p);
    }

    private void checkRelationshipList(XmlAssert xmlAssert, String path) {
        xmlAssert.nodesByXPath(path).exist();
        checkRelationship(xmlAssert, path);
    }

    private void checkRelationship(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Relationship").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/ID").isEqualTo("id");
        xmlAssert.valueByXPath(p + "/Visible").isEqualTo("true");
        checkRelationshipEnd(xmlAssert, p, "FromRelationshipEnd");
        checkRelationshipEnd(xmlAssert, p, "ToRelationshipEnd");
    }

    private void checkRelationshipEnd(XmlAssert xmlAssert, String path, String tagName) {
        String p = new StringBuilder(path).append("/").append(tagName).toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Role").isEqualTo("role");
        xmlAssert.valueByXPath(p + "/Multiplicity").isEqualTo("multiplicity");
        xmlAssert.valueByXPath(p + "/DimensionID").isEqualTo("dimensionID");
        checkRelationshipEndAttributes(xmlAssert, p);
        checkRelationshipEndTranslations(xmlAssert, p);
        checkRelationshipEndVisualizationProperties(xmlAssert, p);
    }

    private void checkRelationshipEndVisualizationProperties(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/VisualizationProperties").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/FolderPosition").isEqualTo("1");
        xmlAssert.valueByXPath(p + "/ContextualNameRule").isEqualTo("contextualNameRule");
        xmlAssert.valueByXPath(p + "/DefaultDetailsPosition").isEqualTo("2");
        xmlAssert.valueByXPath(p + "/DisplayKeyPosition").isEqualTo("10");
        xmlAssert.valueByXPath(p + "/CommonIdentifierPosition").isEqualTo("0");
        xmlAssert.valueByXPath(p + "/IsDefaultMeasure").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/IsDefaultImage").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/SortPropertiesPosition").isEqualTo("0");
    }

    private void checkRelationshipEndTranslations(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Translations").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkRelationshipEndTranslation(xmlAssert, p);
    }

    private void checkRelationshipEndTranslation(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Translation").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAbstractTranslation(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/CollectionCaption").isEqualTo("collectionCaption");
    }

    private void checkRelationshipEndAttributes(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Attributes").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkRelationshipEndAttribute(xmlAssert, p);
    }

    private void checkRelationshipEndAttribute(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Attribute").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/AttributeID").isEqualTo("attribute");
    }

    private void checkHierarchyList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Hierarchies").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkHierarchy(xmlAssert, p);
    }

    private void checkHierarchy(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Hierarchy").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/ID").isEqualTo("id");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        xmlAssert.valueByXPath(p + "/ProcessingState").isEqualTo("processingState");
        xmlAssert.valueByXPath(p + "/StructureType").isEqualTo("structureType");
        xmlAssert.valueByXPath(p + "/DisplayFolder").isEqualTo("displayFolder");
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
        xmlAssert.valueByXPath(p + "/AllMemberName").isEqualTo("allMemberName");
        checkTranslationList(xmlAssert, p, "AllMemberTranslations", "AllMemberTranslation");
        xmlAssert.valueByXPath(p + "/MemberNamesUnique").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/MemberKeysUnique").isEqualTo("memberKeysUnique");
        xmlAssert.valueByXPath(p + "/AllowDuplicateNames").isEqualTo("true");
        checkLevelList(xmlAssert, p);
        checkAnnotationList(xmlAssert, p);
        checkHierarchyVisualizationProperties(xmlAssert, p);
    }

    private void checkHierarchyVisualizationProperties(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/VisualizationProperties").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/ContextualNameRule").isEqualTo("contextualNameRule");
        xmlAssert.valueByXPath(p + "/FolderPosition").isEqualTo("1");
    }

    private void checkLevelList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Levels").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkLevel(xmlAssert, p);
    }

    private void checkLevel(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Level").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/ID").isEqualTo("id");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        xmlAssert.valueByXPath(p + "/SourceAttributeID").isEqualTo("sourceAttributeID");
        xmlAssert.valueByXPath(p + "/HideMemberIf").isEqualTo("hideMemberIf");
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkDimensionAttributeList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Attributes").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkDimensionAttribute(xmlAssert, p);
    }

    private void checkDimensionAttribute(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Attribute").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/ID").isEqualTo("id");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        checkDimensionAttributeType(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/Usage").isEqualTo("usage");
        checkBinding(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/EstimatedCount").isEqualTo("10");
        checkDataItemList(xmlAssert, p, "KeyColumns", "KeyColumn");
        checkDataItem(xmlAssert, p, "NameColumn");
        checkDataItem(xmlAssert, p, "ValueColumn");
        checkAttributeTranslationList(xmlAssert, p);
        checkAttributeRelationshipList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/DiscretizationMethod").isEqualTo("discretizationMethod");
        xmlAssert.valueByXPath(p + "/DiscretizationBucketCount").isEqualTo("1");
        xmlAssert.valueByXPath(p + "/RootMemberIf").isEqualTo("rootMemberIf");
        xmlAssert.valueByXPath(p + "/OrderBy").isEqualTo("orderBy");
        xmlAssert.valueByXPath(p + "/DefaultMember").isEqualTo("defaultMember");
        xmlAssert.valueByXPath(p + "/OrderByAttributeID").isEqualTo("orderByAttributeID");
        checkDataItem(xmlAssert, p, "SkippedLevelsColumn");
        xmlAssert.valueByXPath(p + "/NamingTemplate").isEqualTo("namingTemplate");
        xmlAssert.valueByXPath(p + "/MembersWithData").isEqualTo("membersWithData");
        xmlAssert.valueByXPath(p + "/MembersWithDataCaption").isEqualTo("membersWithDataCaption");
        checkTranslationList(xmlAssert, p, "NamingTemplateTranslations", "NamingTemplateTranslation");
        checkDataItem(xmlAssert, p, "CustomRollupColumn");
        checkDataItem(xmlAssert, p, "CustomRollupPropertiesColumn");
        checkDataItem(xmlAssert, p, "UnaryOperatorColumn");
        xmlAssert.valueByXPath(p + "/AttributeHierarchyOrdered").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/MemberNamesUnique").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/IsAggregatable").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/AttributeHierarchyEnabled").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/AttributeHierarchyOptimizedState").isEqualTo("attributeHierarchyOptimizedState");
        xmlAssert.valueByXPath(p + "/AttributeHierarchyVisible").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/AttributeHierarchyDisplayFolder").isEqualTo("attributeHierarchyDisplayFolder");
        xmlAssert.valueByXPath(p + "/KeyUniquenessGuarantee").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/GroupingBehavior").isEqualTo("groupingBehavior");
        xmlAssert.valueByXPath(p + "/InstanceSelection").isEqualTo("instanceSelection");
        checkAnnotationList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/ProcessingState").isEqualTo("processingState");
        xmlAssert.valueByXPath(p + "/AttributeHierarchyProcessingState").isEqualTo("Processed");
        checkDimensionAttributeVisualizationProperties(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/ExtendedType").isEqualTo("extendedType");
    }

    private void checkDimensionAttributeVisualizationProperties(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/VisualizationProperties").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAbstractProperties(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/CommonIdentifierPosition").isEqualTo("10");
        xmlAssert.valueByXPath(p + "/DisplayKeyPosition").isEqualTo("2");
        xmlAssert.valueByXPath(p + "/IsDefaultImage").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/DefaultAggregateFunction").isEqualTo("defaultAggregateFunction");
    }

    private void checkAttributeRelationshipList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/AttributeRelationships").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAttributeRelationship(xmlAssert, p);
    }

    private void checkAttributeRelationship(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/AttributeRelationship").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/AttributeID").isEqualTo("attributeID");
        xmlAssert.valueByXPath(p + "/RelationshipType").isEqualTo("relationshipType");
        xmlAssert.valueByXPath(p + "/Cardinality").isEqualTo("cardinality");
        xmlAssert.valueByXPath(p + "/Optionality").isEqualTo("optionality");
        xmlAssert.valueByXPath(p + "/OverrideBehavior").isEqualTo("overrideBehavior");
        checkAnnotationList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/Visible").isEqualTo("true");
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
    }

    private void checkDimensionAttributeType(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Type").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p).isEqualTo("Account");
    }

    private void checkDimensionCurrentStorageMode(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/CurrentStorageMode").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p).isEqualTo("Rolap");
    }

    private void checkDimensionPermissionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/DimensionPermissions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkDimensionPermission(xmlAssert, p);

    }

    private void checkDimensionPermission(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/DimensionPermission").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAbstractPermission(xmlAssert, p);
        checkAttributePermissionList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/AllowedRowsExpression").isEqualTo("allowedRowsExpression");
    }

    private void checkDimensionUnknownMember(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/UnknownMember").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p).isEqualTo("None");
        xmlAssert.hasXPath(p).haveAttribute("valuens", "valuens");
    }

    private void checkDataSourceView(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/DataSourceView").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAbstractItem(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/DataSourceID").isEqualTo("dataSourceID");
    }

    private void checkDataSource(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/DataSource").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/ManagedProvider").isEqualTo("managedProvider");
        xmlAssert.valueByXPath(p + "/ConnectionString").isEqualTo("connectionString");
        xmlAssert.valueByXPath(p + "/ConnectionStringSecurity").isEqualTo("connectionStringSecurity");
        checkImpersonationInfo(xmlAssert, p, "ImpersonationInfo");
        xmlAssert.valueByXPath(p + "/Isolation").isEqualTo("isolation");
        xmlAssert.valueByXPath(p + "/MaxActiveConnections").isEqualTo("1");
        xmlAssert.valueByXPath(p + "/Timeout").isEqualTo("PT48H");
        checkDataSourcePermissionList(xmlAssert, p);
        checkImpersonationInfo(xmlAssert, p, "QueryImpersonationInfo");
        xmlAssert.valueByXPath(p + "/QueryHints").isEqualTo("queryHints");
    }

    private void checkDataSourcePermissionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/DataSourcePermissions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkDataSourcePermission(xmlAssert, p);
    }

    private void checkDataSourcePermission(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/DataSourcePermission").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAbstractPermission(xmlAssert, p);
    }

    private void checkDatabase(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Database").toString();
        xmlAssert.nodesByXPath(p).exist();

        xmlAssert.valueByXPath(p + "/LastUpdate").isEqualTo("2024-01-10T10:45:00Z");
        xmlAssert.valueByXPath(p + "/State").isEqualTo("state");
        xmlAssert.valueByXPath(p + "/ReadWriteMode").isEqualTo("readWriteMode");
        xmlAssert.valueByXPath(p + "/DbStorageLocation").isEqualTo("dbStorageLocation");
        xmlAssert.valueByXPath(p + "/AggregationPrefix").isEqualTo("aggregationPrefix");
        xmlAssert.valueByXPath(p + "/ProcessingPriority").isEqualTo("1");
        xmlAssert.valueByXPath(p + "/EstimatedSize").isEqualTo("10");
        xmlAssert.valueByXPath(p + "/LastProcessed").isEqualTo("2024-01-10T10:45:00Z");
        xmlAssert.valueByXPath(p + "/Language").isEqualTo("10");
        xmlAssert.valueByXPath(p + "/Collation").isEqualTo("collation");
        xmlAssert.valueByXPath(p + "/Visible").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/MasterDataSourceID").isEqualTo("masterDataSourceID");
        checkImpersonationInfo(xmlAssert, p, "DataSourceImpersonationInfo");
        checkAccountList(xmlAssert, p);
        checkDataSourceList(xmlAssert, p);
        checkDataSourceViewList(xmlAssert, p);
        checkDimensionList(xmlAssert, p);
        checkCubeList(xmlAssert, p);
        checkMiningStructureList(xmlAssert, p);
        checkRoleList(xmlAssert, p);
        checkAssemblyList(xmlAssert, p);
        checkDatabasePermissionList(xmlAssert, p);
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
        xmlAssert.valueByXPath(p + "/StorageEngineUsed").isEqualTo("storageEngineUsed");
        xmlAssert.valueByXPath(p + "/ImagePath").isEqualTo("imagePath");
        xmlAssert.valueByXPath(p + "/ImageUrl").isEqualTo("imageUrl");
        xmlAssert.valueByXPath(p + "/ImageUniqueID").isEqualTo("imageUniqueID");
        xmlAssert.valueByXPath(p + "/ImageVersion").isEqualTo("imageVersion");
        xmlAssert.valueByXPath(p + "/Token").isEqualTo("token");
        xmlAssert.valueByXPath(p + "/CompatibilityLevel").isEqualTo("2");
        xmlAssert.valueByXPath(p + "/DirectQueryMode").isEqualTo("directQueryMode");
    }

    private void checkDatabasePermissionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/DatabasePermissions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkDatabasePermission(xmlAssert, p);
    }

    private void checkDatabasePermission(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/DatabasePermission").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAbstractPermission(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/Administer").isEqualTo("true");
    }

    private void checkMiningStructureList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/MiningStructures").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkMiningStructure(xmlAssert, p);

    }

    private void checkCubeList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Cubes").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkCube(xmlAssert, p);

    }

    private void checkDimensionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Dimensions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkDimension(xmlAssert, p);

    }

    private void checkDataSourceViewList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/DataSourceViews").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkDataSourceView(xmlAssert, p);
    }

    private void checkDataSourceList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/DataSources").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkDataSource(xmlAssert, p);

    }

    private void checkAccountList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Accounts").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAccount(xmlAssert, p);
    }

    private void checkAccount(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Account").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/AccountType").isEqualTo("accountType");
        xmlAssert.valueByXPath(p + "/AggregationFunction").isEqualTo("aggregationFunction");
        xmlAssert.valueByXPath(p + "/AggregationFunction").isEqualTo("aggregationFunction");
        xmlAssert.valueByXPath(p + "/Aliases/Alias").isEqualTo("alias");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkCube(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Cube").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAbstractItem(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/Language").isEqualTo("1");
        xmlAssert.valueByXPath(p + "/Collation").isEqualTo("collation");
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
        checkCubeDimensionList(xmlAssert, p);
        checkCubePermissionList(xmlAssert, p);
        checkMdxScriptList(xmlAssert, p);
        checkPerspectiveList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/State").isEqualTo("state");
        xmlAssert.valueByXPath(p + "/DefaultMeasure").isEqualTo("defaultMeasure");
        xmlAssert.valueByXPath(p + "/Visible").isEqualTo("true");
        checkMeasureGroupList(xmlAssert, p);
        checkDataSourceViewBinding(xmlAssert, p, "Source");
        xmlAssert.valueByXPath(p + "/AggregationPrefix").isEqualTo("aggregationPrefix");
        xmlAssert.valueByXPath(p + "/ProcessingPriority").isEqualTo("10");
        checkCubeStorageMode(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/ProcessingMode").isEqualTo("processingMode");
        xmlAssert.valueByXPath(p + "/ScriptCacheProcessingMode").isEqualTo("scriptCacheProcessingMode");
        xmlAssert.valueByXPath(p + "/ScriptErrorHandlingMode").isEqualTo("scriptErrorHandlingMode");
        xmlAssert.valueByXPath(p + "/DaxOptimizationMode").isEqualTo("daxOptimizationMode");
        checkProactiveCaching(xmlAssert, p);
        checkKpiList(xmlAssert, p);
        checkErrorConfiguration(xmlAssert, p);
        checkActionList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/StorageLocation").isEqualTo("storageLocation");
        xmlAssert.valueByXPath(p + "/EstimatedRows").isEqualTo("10");
        xmlAssert.valueByXPath(p + "/LastProcessed").isEqualTo("2024-01-10T10:45:00Z");
    }

    private void checkActionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Actions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAction(xmlAssert, p);
    }

    private void checkAction(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Action").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/ID").isEqualTo("id");
        xmlAssert.valueByXPath(p + "/Caption").isEqualTo("caption");
        xmlAssert.valueByXPath(p + "/CaptionIsMdx").isEqualTo("true");
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
        xmlAssert.valueByXPath(p + "/TargetType").isEqualTo("Cube");
        xmlAssert.valueByXPath(p + "/Target").isEqualTo("target");
        xmlAssert.valueByXPath(p + "/Condition").isEqualTo("condition");
        xmlAssert.valueByXPath(p + "/Type").isEqualTo("Report");
        xmlAssert.valueByXPath(p + "/Invocation").isEqualTo("invocation");
        xmlAssert.valueByXPath(p + "/Application").isEqualTo("application");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkErrorConfiguration(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/ErrorConfiguration").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/KeyErrorLimit").isEqualTo("10");
        xmlAssert.valueByXPath(p + "/KeyErrorLogFile").isEqualTo("keyErrorLogFile");
        xmlAssert.valueByXPath(p + "/KeyErrorAction").isEqualTo("keyErrorAction");
        xmlAssert.valueByXPath(p + "/KeyErrorLimitAction").isEqualTo("keyErrorLimitAction");
        xmlAssert.valueByXPath(p + "/KeyNotFound").isEqualTo("keyNotFound");
        xmlAssert.valueByXPath(p + "/KeyDuplicate").isEqualTo("keyDuplicate");
        xmlAssert.valueByXPath(p + "/NullKeyConvertedToUnknown").isEqualTo("nullKeyConvertedToUnknown");
        xmlAssert.valueByXPath(p + "/NullKeyNotAllowed").isEqualTo("nullKeyNotAllowed");
        xmlAssert.valueByXPath(p + "/CalculationError").isEqualTo("calculationError");
    }

    private void checkKpiList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Kpis").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkKpi(xmlAssert, p);
    }

    private void checkKpi(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Kpi").toString();
        xmlAssert.nodesByXPath(p).exist();

        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/ID").isEqualTo("id");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
        xmlAssert.valueByXPath(p + "/DisplayFolder").isEqualTo("displayFolder");
        xmlAssert.valueByXPath(p + "/AssociatedMeasureGroupID").isEqualTo("associatedMeasureGroupID");
        xmlAssert.valueByXPath(p + "/Value").isEqualTo("value");
        xmlAssert.valueByXPath(p + "/Goal").isEqualTo("goal");
        xmlAssert.valueByXPath(p + "/Status").isEqualTo("status");
        xmlAssert.valueByXPath(p + "/Trend").isEqualTo("trend");
        xmlAssert.valueByXPath(p + "/Weight").isEqualTo("weight");
        xmlAssert.valueByXPath(p + "/TrendGraphic").isEqualTo("trendGraphic");
        xmlAssert.valueByXPath(p + "/StatusGraphic").isEqualTo("statusGraphic");
        xmlAssert.valueByXPath(p + "/CurrentTimeMember").isEqualTo("currentTimeMember");
        xmlAssert.valueByXPath(p + "/ParentKpiID").isEqualTo("parentKpiID");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkProactiveCaching(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/ProactiveCaching").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/OnlineMode").isEqualTo("onlineMode");
        xmlAssert.valueByXPath(p + "/AggregationStorage").isEqualTo("aggregationStorage");
        xmlAssert.valueByXPath(p + "/AggregationStorage").isEqualTo("aggregationStorage");
        checkProactiveCachingBinding(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/SilenceInterval").isEqualTo("PT240H");
        xmlAssert.valueByXPath(p + "/Latency").isEqualTo("PT264H");
        xmlAssert.valueByXPath(p + "/SilenceOverrideInterval").isEqualTo("PT288H");
        xmlAssert.valueByXPath(p + "/ForceRebuildInterval").isEqualTo("PT336H");
        xmlAssert.valueByXPath(p + "/Enabled").isEqualTo("true");
    }

    private void checkProactiveCachingBinding(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Source").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/RefreshInterval").isEqualTo("PT240H");
        checkIncrementalProcessingNotificationList(xmlAssert, p);
    }

    private void checkIncrementalProcessingNotificationList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/IncrementalProcessingNotifications").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkIncrementalProcessingNotification(xmlAssert, p);
    }

    private void checkIncrementalProcessingNotification(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/IncrementalProcessingNotification").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/TableID").isEqualTo("tableID");
        xmlAssert.valueByXPath(p + "/ProcessingQuery").isEqualTo("processingQuery");
    }

    private void checkCubeStorageMode(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/StorageMode").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p).isEqualTo("Rolap");
    }

    private void checkDataSourceViewBinding(XmlAssert xmlAssert, String path, String tagName) {
        String p = new StringBuilder(path).append("/").append(tagName).toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/DataSourceViewID").isEqualTo("dataSourceViewID");
    }

    private void checkMeasureGroupList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/MeasureGroups").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkMeasureGroup(xmlAssert, p);
    }

    private void checkPerspectiveList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Perspectives").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkPerspective(xmlAssert, p);
    }

    private void checkMdxScriptList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/MdxScripts").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkMdxScript(xmlAssert, p);
    }

    private void checkCubePermissionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/CubePermissions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkCubePermission(xmlAssert, p);
    }

    private void checkCubePermission(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/CubePermission").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/ReadSourceData").isEqualTo("readSourceData");
        checkCubeDimensionPermissionList(xmlAssert, p);
        checkCellPermissionList(xmlAssert, p);
    }

    private void checkCellPermissionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/CellPermissions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkCellPermission(xmlAssert, p);
    }

    private void checkCellPermission(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/CellPermission").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Access").isEqualTo("Read");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        xmlAssert.valueByXPath(p + "/Expression").isEqualTo("expression");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkCubeDimensionPermissionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/DimensionPermissions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkCubeDimensionPermission(xmlAssert, p);
    }

    private void checkCubeDimensionPermission(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/DimensionPermission").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/CubeDimensionID").isEqualTo("cubeDimensionID");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        xmlAssert.valueByXPath(p + "/Read").isEqualTo("Allowed");
        xmlAssert.valueByXPath(p + "/Write").isEqualTo("Allowed");
        checkAttributePermissionList(xmlAssert, p);
        checkAnnotationList(xmlAssert, p);
    }

    private void checkAttributePermissionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/AttributePermissions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAttributePermission(xmlAssert, p);
    }

    private void checkAttributePermission(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/AttributePermission").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/AttributeID").isEqualTo("attributeID");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        xmlAssert.valueByXPath(p + "/DefaultMember").isEqualTo("defaultMember");
        xmlAssert.valueByXPath(p + "/VisualTotals").isEqualTo("visualTotals");
        xmlAssert.valueByXPath(p + "/AllowedSet").isEqualTo("allowedSet");
        xmlAssert.valueByXPath(p + "/DeniedSet").isEqualTo("deniedSet");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkCubeDimensionList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Dimensions").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkCubeDimension(xmlAssert, p);
    }

    private void checkCubeDimension(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Dimension").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/ID").isEqualTo("id");
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        checkTranslationList(xmlAssert, p, "Translations", "Translation");
        xmlAssert.valueByXPath(p + "/DimensionID").isEqualTo("dimensionID");
        xmlAssert.valueByXPath(p + "/Visible").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/AllMemberAggregationUsage").isEqualTo("allMemberAggregationUsage");
        xmlAssert.valueByXPath(p + "/HierarchyUniqueNameStyle").isEqualTo("hierarchyUniqueNameStyle");
        xmlAssert.valueByXPath(p + "/MemberUniqueNameStyle").isEqualTo("memberUniqueNameStyle");
        checkCubeAttributeList(xmlAssert, p);
        checkCubeHierarchyList(xmlAssert, p);
        checkAnnotationList(xmlAssert, p);
    }

    private void checkCubeHierarchyList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Hierarchies").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkCubeHierarchy(xmlAssert, p);
    }

    private void checkCubeHierarchy(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Hierarchy").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/HierarchyID").isEqualTo("hierarchyID");
        xmlAssert.valueByXPath(p + "/OptimizedState").isEqualTo("optimizedState");
        xmlAssert.valueByXPath(p + "/Visible").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/Enabled").isEqualTo("true");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkCubeAttributeList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Attributes").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkCubeAttribute(xmlAssert, p);
    }

    private void checkCubeAttribute(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Attribute").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/AttributeID").isEqualTo("attributeID");
        xmlAssert.valueByXPath(p + "/AggregationUsage").isEqualTo("aggregationUsage");
        xmlAssert.valueByXPath(p + "/AttributeHierarchyOptimizedState").isEqualTo("attributeHierarchyOptimizedState");
        xmlAssert.valueByXPath(p + "/AttributeHierarchyEnabled").isEqualTo("true");
        xmlAssert.valueByXPath(p + "/AttributeHierarchyVisible").isEqualTo(true);
        checkAnnotationList(xmlAssert, p);
    }

    private void checkTranslationList(XmlAssert xmlAssert, String path, String tagNameList, String tagName) {
        String p = new StringBuilder(path).append("/").append(tagNameList).toString();
        xmlAssert.nodesByXPath(p).exist();
        checkTranslation(xmlAssert, p, tagName);
    }

    private void checkTranslation(XmlAssert xmlAssert, String path, String tagName) {
        String p = new StringBuilder(path).append("/").append(tagName).toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Language").isEqualTo("10");
        xmlAssert.valueByXPath(p + "/Caption").isEqualTo("caption");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        xmlAssert.valueByXPath(p + "/DisplayFolder").isEqualTo("displayFolder");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkAssembly(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Assembly").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAbstractItem(xmlAssert, p);
        checkImpersonationInfo(xmlAssert, p, "ImpersonationInfo");
    }

    private void checkImpersonationInfo(XmlAssert xmlAssert, String path, String tagName) {
        String p = new StringBuilder(path).append("/").append(tagName).toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/ImpersonationMode").isEqualTo("impersonationMode");
        xmlAssert.valueByXPath(p + "/Account").isEqualTo("account");
        xmlAssert.valueByXPath(p + "/Password").isEqualTo("password");
        xmlAssert.valueByXPath(p + "/ImpersonationInfoSecurity").isEqualTo("impersonationInfoSecurity");
    }

    private void checkAbstractItem(XmlAssert xmlAssert, String p) {
        xmlAssert.valueByXPath(p + "/ID").isEqualTo("id");
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/CreatedTimestamp").isEqualTo("2024-01-10T10:45:00Z");
        xmlAssert.valueByXPath(p + "/LastSchemaUpdate").isEqualTo("2024-01-10T10:45:00Z");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkAggregationDesign(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/AggregationDesign").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/EstimatedRows").isEqualTo("10");
        xmlAssert.nodesByXPath(p + "/Dimensions").exist();
        checkAggregationDesignDimensionList(xmlAssert, p);
        checkAggregationList(xmlAssert, p);
        xmlAssert.valueByXPath(p + "/EstimatedPerformanceGain").isEqualTo("11");
    }

    private void checkAggregationList(XmlAssert xmlAssert, String path) {
        xmlAssert.nodesByXPath(path + "/Aggregations").exist();
        checkAggregation(xmlAssert, path + "/Aggregations");
    }

    private void checkAggregation(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Aggregation").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/ID").isEqualTo("id");
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("name");
        xmlAssert.valueByXPath(p + "/Description").isEqualTo("description");
        checkAnnotationList(xmlAssert, p);
        checkAggregationDimensionList(xmlAssert, p);
    }

    private void checkAggregationDimensionList(XmlAssert xmlAssert, String path) {
        xmlAssert.nodesByXPath(path + "/Dimensions").exist();
        checkAggregationDimension(xmlAssert, path + "/Dimensions");
    }

    private void checkAggregationDimension(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Dimension").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/CubeDimensionID").isEqualTo("cubeDimensionID");
        xmlAssert.valueByXPath(p + "/CubeDimensionID").isEqualTo("cubeDimensionID");
        checkAggregationAttributeList(xmlAssert, p);
        checkAnnotationList(xmlAssert, p);
    }

    private void checkAggregationAttributeList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Attributes").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAggregationAttribute(xmlAssert, p);
    }

    private void checkAggregationAttribute(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Attribute").toString();
        xmlAssert.nodesByXPath(p);
        xmlAssert.valueByXPath(p + "/AttributeID").isEqualTo("attributeID");
        checkAnnotationList(xmlAssert, p);
    }

    private void checkAggregationDesignDimensionList(XmlAssert xmlAssert, String path) {
        xmlAssert.nodesByXPath(path + "/Dimensions").exist();
        checkAggregationDesignDimension(xmlAssert, path + "/Dimensions");
    }

    private void checkAggregationDesignDimension(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Dimension").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/CubeDimensionID").isEqualTo("cubeDimensionID");
        checkAggregationDesignAttributeList(xmlAssert, p);
        checkAnnotationList(xmlAssert, p);
    }

    private void checkAggregationDesignAttributeList(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Attributes").toString();
        xmlAssert.nodesByXPath(p).exist();
        checkAggregationDesignAttribute(xmlAssert, p);
    }

    private void checkAggregationDesignAttribute(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Attribute").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/AttributeID").isEqualTo("attributeID");
        xmlAssert.valueByXPath(p + "/EstimatedCount").isEqualTo("10");
    }

    private void checkExecuteParameter(XmlAssert xmlAssert, String path) {
        String p = new StringBuilder(path).append("/Parameter").toString();
        xmlAssert.nodesByXPath(p).exist();
        xmlAssert.valueByXPath(p + "/Name").isEqualTo("Name");
        xmlAssert.valueByXPath(p + "/Value").isEqualTo("Value");
    }

    private void checkTrace(XmlAssert xmlAssert, String path) {
        xmlAssert.nodesByXPath(path + "/Trace").exist();
        xmlAssert.valueByXPath(path + "/Trace/NAME").isEqualTo(NAME);
        xmlAssert.valueByXPath(path + "/Trace/ID").isEqualTo(ID);
        xmlAssert.valueByXPath(path + "/Trace/CreatedTimestamp").isEqualTo("2024-01-10T10:45:00Z");
        xmlAssert.valueByXPath(path + "/Trace/LastSchemaUpdate").isEqualTo("2024-01-10T10:45:00Z");
        xmlAssert.valueByXPath(path + "/Trace/Description").isEqualTo(DESCRIPTION);
        checkAnnotationList(xmlAssert, path + "/Trace");
        xmlAssert.valueByXPath(path + "/Trace/LogFileName").isEqualTo("LogFileName");
        xmlAssert.valueByXPath(path + "/Trace/LogFileAppend").isEqualTo("true");
        xmlAssert.valueByXPath(path + "/Trace/LogFileSize").isEqualTo("10");
        xmlAssert.valueByXPath(path + "/Trace/Audit").isEqualTo("true");
        xmlAssert.valueByXPath(path + "/Trace/LogFileRollover").isEqualTo("true");
        xmlAssert.valueByXPath(path + "/Trace/AutoRestart").isEqualTo("true");
        xmlAssert.valueByXPath(path + "/Trace/StopTime").isEqualTo("2024-01-10T10:45:00Z");
        checkTraceFilter(xmlAssert, path + "/Trace");
        checkEventType(xmlAssert, path + "/Trace");
    }

}
