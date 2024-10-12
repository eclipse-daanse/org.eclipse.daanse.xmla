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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the org.eclipse.daanse.xmla.ws.jakarta.model.xmla.xmla package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName _AllowedRowsExpression_QNAME = new QName("urn:schemas-microsoft-com:xml-analysis",
            "AllowedRowsExpression");
    private static final QName _ShareDimensionStorage_QNAME = new QName("urn:schemas-microsoft-com:xml-analysis",
            "ShareDimensionStorage");
    private static final QName _BooleanExprAnd_QNAME = new QName("", "and");
    private static final QName _BooleanExprOr_QNAME = new QName("", "or");
    private static final QName _BooleanExprNot_QNAME = new QName("", "not");
    private static final QName _BooleanExprLeaf_QNAME = new QName("", "leaf");
    private static final QName _AndOrTypeNot_QNAME = new QName("", "Not");
    private static final QName _AndOrTypeOr_QNAME = new QName("", "Or");
    private static final QName _AndOrTypeAnd_QNAME = new QName("", "And");
    private static final QName _AndOrTypeEqual_QNAME = new QName("", "Equal");
    private static final QName _AndOrTypeNotEqual_QNAME = new QName("", "NotEqual");
    private static final QName _AndOrTypeLess_QNAME = new QName("", "Less");
    private static final QName _AndOrTypeLessOrEqual_QNAME = new QName("", "LessOrEqual");
    private static final QName _AndOrTypeGreater_QNAME = new QName("", "Greater");
    private static final QName _AndOrTypeGreaterOrEqual_QNAME = new QName("", "GreaterOrEqual");
    private static final QName _AndOrTypeLike_QNAME = new QName("", "Like");
    private static final QName _AndOrTypeNotLike_QNAME = new QName("", "NotLike");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for
     * package: org.eclipse.daanse.xmla.ws.jakarta.model.xmla.xmla
     *
     */
    public ObjectFactory() {
        // constructor
    }

    /**
     * Create an instance of {@link EventType }
     *
     */
    public EventType createEventType() {
        return new EventType();
    }

    /**
     * Create an instance of {@link Execute }
     *
     */
    public Execute createExecute() {
        return new Execute();
    }

    /**
     * Create an instance of {@link Discover }
     *
     */
    public Discover createDiscover() {
        return new Discover();
    }

    /**
     * Create an instance of {@link DiscoverResponse }
     *
     */
    public DiscoverResponse createDiscoverResponse() {
        return new DiscoverResponse();
    }

    /**
     * Create an instance of {@link Account }
     *
     */
    public Account createAccount() {
        return new Account();
    }

    /**
     * Create an instance of {@link AggregationInstanceAttribute }
     *
     */
    public AggregationInstanceAttribute createAggregationInstanceAttribute() {
        return new AggregationInstanceAttribute();
    }

    /**
     * Create an instance of {@link AggregationInstanceDimension }
     *
     */
    public AggregationInstanceDimension createAggregationInstanceDimension() {
        return new AggregationInstanceDimension();
    }

    /**
     * Create an instance of {@link AggregationInstance }
     *
     */
    public AggregationInstance createAggregationInstance() {
        return new AggregationInstance();
    }

    /**
     * Create an instance of {@link Partition }
     *
     */
    public Partition createPartition() {
        return new Partition();
    }

    /**
     * Create an instance of {@link AggregationAttribute }
     *
     */
    public AggregationAttribute createAggregationAttribute() {
        return new AggregationAttribute();
    }

    /**
     * Create an instance of {@link AggregationDimension }
     *
     */
    public AggregationDimension createAggregationDimension() {
        return new AggregationDimension();
    }

    /**
     * Create an instance of {@link Aggregation }
     *
     */
    public Aggregation createAggregation() {
        return new Aggregation();
    }

    /**
     * Create an instance of {@link AggregationDesignDimension }
     *
     */
    public AggregationDesignDimension createAggregationDesignDimension() {
        return new AggregationDesignDimension();
    }

    /**
     * Create an instance of {@link AggregationDesign }
     *
     */
    public AggregationDesign createAggregationDesign() {
        return new AggregationDesign();
    }

    /**
     * Create an instance of {@link Measure }
     *
     */
    public Measure createMeasure() {
        return new Measure();
    }

    /**
     * Create an instance of {@link MeasureGroupAttribute }
     *
     */
    public MeasureGroupAttribute createMeasureGroupAttribute() {
        return new MeasureGroupAttribute();
    }

    /**
     * Create an instance of {@link DataMiningMeasureGroupDimension }
     *
     */
    public DataMiningMeasureGroupDimension createDataMiningMeasureGroupDimension() {
        return new DataMiningMeasureGroupDimension();
    }

    /**
     * Create an instance of {@link DegenerateMeasureGroupDimension }
     *
     */
    public DegenerateMeasureGroupDimension createDegenerateMeasureGroupDimension() {
        return new DegenerateMeasureGroupDimension();
    }

    /**
     * Create an instance of {@link ReferenceMeasureGroupDimension }
     *
     */
    public ReferenceMeasureGroupDimension createReferenceMeasureGroupDimension() {
        return new ReferenceMeasureGroupDimension();
    }

    /**
     * Create an instance of {@link RegularMeasureGroupDimension }
     *
     */
    public RegularMeasureGroupDimension createRegularMeasureGroupDimension() {
        return new RegularMeasureGroupDimension();
    }

    /**
     * Create an instance of {@link ManyToManyMeasureGroupDimension }
     *
     */
    public ManyToManyMeasureGroupDimension createManyToManyMeasureGroupDimension() {
        return new ManyToManyMeasureGroupDimension();
    }

    /**
     * Create an instance of {@link MeasureGroup }
     *
     */
    public MeasureGroup createMeasureGroup() {
        return new MeasureGroup();
    }

    /**
     * Create an instance of {@link PerspectiveAction }
     *
     */
    public PerspectiveAction createPerspectiveAction() {
        return new PerspectiveAction();
    }

    /**
     * Create an instance of {@link PerspectiveKpi }
     *
     */
    public PerspectiveKpi createPerspectiveKpi() {
        return new PerspectiveKpi();
    }

    /**
     * Create an instance of {@link PerspectiveCalculation }
     *
     */
    public PerspectiveCalculation createPerspectiveCalculation() {
        return new PerspectiveCalculation();
    }

    /**
     * Create an instance of {@link PerspectiveMeasure }
     *
     */
    public PerspectiveMeasure createPerspectiveMeasure() {
        return new PerspectiveMeasure();
    }

    /**
     * Create an instance of {@link PerspectiveMeasureGroup }
     *
     */
    public PerspectiveMeasureGroup createPerspectiveMeasureGroup() {
        return new PerspectiveMeasureGroup();
    }

    /**
     * Create an instance of {@link PerspectiveHierarchy }
     *
     */
    public PerspectiveHierarchy createPerspectiveHierarchy() {
        return new PerspectiveHierarchy();
    }

    /**
     * Create an instance of {@link PerspectiveAttribute }
     *
     */
    public PerspectiveAttribute createPerspectiveAttribute() {
        return new PerspectiveAttribute();
    }

    /**
     * Create an instance of {@link PerspectiveDimension }
     *
     */
    public PerspectiveDimension createPerspectiveDimension() {
        return new PerspectiveDimension();
    }

    /**
     * Create an instance of {@link Perspective }
     *
     */
    public Perspective createPerspective() {
        return new Perspective();
    }

    /**
     * Create an instance of {@link CalculationProperty }
     *
     */
    public CalculationProperty createCalculationProperty() {
        return new CalculationProperty();
    }

    /**
     * Create an instance of {@link MdxScript }
     *
     */
    public MdxScript createMdxScript() {
        return new MdxScript();
    }

    /**
     * Create an instance of {@link DrillThroughAction }
     *
     */
    public DrillThroughAction createDrillThroughAction() {
        return new DrillThroughAction();
    }

    /**
     * Create an instance of {@link ReportAction }
     *
     */
    public ReportAction createReportAction() {
        return new ReportAction();
    }

    /**
     * Create an instance of {@link StandardAction }
     *
     */
    public StandardAction createStandardAction() {
        return new StandardAction();
    }

    /**
     * Create an instance of {@link Kpi }
     *
     */
    public Kpi createKpi() {
        return new Kpi();
    }

    /**
     * Create an instance of {@link CubeHierarchy }
     *
     */
    public CubeHierarchy createCubeHierarchy() {
        return new CubeHierarchy();
    }

    /**
     * Create an instance of {@link CubeAttribute }
     *
     */
    public CubeAttribute createCubeAttribute() {
        return new CubeAttribute();
    }

    /**
     * Create an instance of {@link CubeDimension }
     *
     */
    public CubeDimension createCubeDimension() {
        return new CubeDimension();
    }

    /**
     * Create an instance of {@link Cube }
     *
     */
    public Cube createCube() {
        return new Cube();
    }

    /**
     * Create an instance of {@link MiningModelColumn }
     *
     */
    public MiningModelColumn createMiningModelColumn() {
        return new MiningModelColumn();
    }

    /**
     * Create an instance of {@link MiningModel }
     *
     */
    public MiningModel createMiningModel() {
        return new MiningModel();
    }

    /**
     * Create an instance of {@link TableMiningStructureColumn }
     *
     */
    public TableMiningStructureColumn createTableMiningStructureColumn() {
        return new TableMiningStructureColumn();
    }

    /**
     * Create an instance of {@link ScalarMiningStructureColumn }
     *
     */
    public ScalarMiningStructureColumn createScalarMiningStructureColumn() {
        return new ScalarMiningStructureColumn();
    }

    /**
     * Create an instance of {@link MiningStructure }
     *
     */
    public MiningStructure createMiningStructure() {
        return new MiningStructure();
    }

    /**
     * Create an instance of {@link Database }
     *
     */
    public Database createDatabase() {
        return new Database();
    }

    /**
     * Create an instance of {@link PredLeaf }
     *
     */
    public PredLeaf createPredLeaf() {
        return new PredLeaf();
    }

    /**
     * Create an instance of {@link Trace }
     *
     */
    public Trace createTrace() {
        return new Trace();
    }

    /**
     * Create an instance of {@link ClrAssembly }
     *
     */
    public ClrAssembly createClrAssembly() {
        return new ClrAssembly();
    }

    /**
     * Create an instance of {@link Server }
     *
     */
    public Server createServer() {
        return new Server();
    }

    /**
     * Create an instance of {@link Level }
     *
     */
    public Level createLevel() {
        return new Level();
    }

    /**
     * Create an instance of {@link Hierarchy }
     *
     */
    public Hierarchy createHierarchy() {
        return new Hierarchy();
    }

    /**
     * Create an instance of {@link AttributeRelationship }
     *
     */
    public AttributeRelationship createAttributeRelationship() {
        return new AttributeRelationship();
    }

    /**
     * Create an instance of {@link AttributeTranslation }
     *
     */
    public AttributeTranslation createAttributeTranslation() {
        return new AttributeTranslation();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Translation }
     *
     */
    public org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Translation createTranslation() {
        return new org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Translation();
    }

    /**
     * Create an instance of {@link DataItem }
     *
     */
    public DataItem createDataItem() {
        return new DataItem();
    }

    /**
     * Create an instance of {@link DimensionAttribute }
     *
     */
    public DimensionAttribute createDimensionAttribute() {
        return new DimensionAttribute();
    }

    /**
     * Create an instance of {@link Dimension }
     *
     */
    public Dimension createDimension() {
        return new Dimension();
    }

    /**
     * Create an instance of {@link PushedDataSource }
     *
     */
    public PushedDataSource createPushedDataSource() {
        return new PushedDataSource();
    }

    /**
     * Create an instance of {@link TraceColumns }
     *
     */
    public TraceColumns createTraceColumns() {
        return new TraceColumns();
    }

    /**
     * Create an instance of {@link TraceColumns.Data }
     *
     */
    public TraceColumns.Data createTraceColumnsData() {
        return new TraceColumns.Data();
    }

    /**
     * Create an instance of {@link EventColumn }
     *
     */
    public EventColumn createEventColumn() {
        return new EventColumn();
    }

    /**
     * Create an instance of {@link EventColumn.EventColumnSubclassList }
     *
     */
    public EventColumn.EventColumnSubclassList createEventColumnEventColumnSubclassList() {
        return new EventColumn.EventColumnSubclassList();
    }

    /**
     * Create an instance of {@link TraceEvent }
     *
     */
    public TraceEvent createTraceEvent() {
        return new TraceEvent();
    }

    /**
     * Create an instance of {@link TraceEventCategories }
     *
     */
    public TraceEventCategories createTraceEventCategories() {
        return new TraceEventCategories();
    }

    /**
     * Create an instance of {@link TraceEventCategories.Data }
     *
     */
    public TraceEventCategories.Data createTraceEventCategoriesData() {
        return new TraceEventCategories.Data();
    }

    /**
     * Create an instance of {@link TraceEventCategories.Data.EventCategory }
     *
     */
    public TraceEventCategories.Data.EventCategory createTraceEventCategoriesDataEventCategory() {
        return new TraceEventCategories.Data.EventCategory();
    }

    /**
     * Create an instance of {@link TraceDefinitionProviderInfo }
     *
     */
    public TraceDefinitionProviderInfo createTraceDefinitionProviderInfo() {
        return new TraceDefinitionProviderInfo();
    }

    /**
     * Create an instance of {@link TraceDefinitionProviderInfo.Data }
     *
     */
    public TraceDefinitionProviderInfo.Data createTraceDefinitionProviderInfoData() {
        return new TraceDefinitionProviderInfo.Data();
    }

    /**
     * Create an instance of {@link Role }
     *
     */
    public Role createRole() {
        return new Role();
    }

    /**
     * Create an instance of {@link Permission }
     *
     */
    public Permission createPermission() {
        return new Permission();
    }

    /**
     * Create an instance of {@link CubePermission }
     *
     */
    public CubePermission createCubePermission() {
        return new CubePermission();
    }

    /**
     * Create an instance of {@link DimensionPermission }
     *
     */
    public DimensionPermission createDimensionPermission() {
        return new DimensionPermission();
    }

    /**
     * Create an instance of {@link CellPermission }
     *
     */
    public CellPermission createCellPermission() {
        return new CellPermission();
    }

    /**
     * Create an instance of {@link AttributePermission }
     *
     */
    public AttributePermission createAttributePermission() {
        return new AttributePermission();
    }

    /**
     * Create an instance of {@link CubeDimensionPermission }
     *
     */
    public CubeDimensionPermission createCubeDimensionPermission() {
        return new CubeDimensionPermission();
    }

    /**
     * Create an instance of {@link ProactiveCachingIncrementalProcessingBinding }
     *
     */
    public ProactiveCachingIncrementalProcessingBinding createProactiveCachingIncrementalProcessingBinding() {
        return new ProactiveCachingIncrementalProcessingBinding();
    }

    /**
     * Create an instance of {@link ProactiveCachingQueryBinding }
     *
     */
    public ProactiveCachingQueryBinding createProactiveCachingQueryBinding() {
        return new ProactiveCachingQueryBinding();
    }

    /**
     * Create an instance of {@link ProactiveCachingTablesBinding }
     *
     */
    public ProactiveCachingTablesBinding createProactiveCachingTablesBinding() {
        return new ProactiveCachingTablesBinding();
    }

    /**
     * Create an instance of {@link CubeAttributeBinding }
     *
     */
    public CubeAttributeBinding createCubeAttributeBinding() {
        return new CubeAttributeBinding();
    }

    /**
     * Create an instance of {@link Group }
     *
     */
    public Group createGroup() {
        return new Group();
    }

    /**
     * Create an instance of {@link UserDefinedGroupBinding }
     *
     */
    public UserDefinedGroupBinding createUserDefinedGroupBinding() {
        return new UserDefinedGroupBinding();
    }

    /**
     * Create an instance of {@link OutOfLineBinding }
     *
     */
    public OutOfLineBinding createOutOfLineBinding() {
        return new OutOfLineBinding();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.Translations }
     *
     */
    public OutOfLineBinding.Translations createOutOfLineBindingTranslations() {
        return new OutOfLineBinding.Translations();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.ForeignKeyColumns }
     *
     */
    public OutOfLineBinding.ForeignKeyColumns createOutOfLineBindingForeignKeyColumns() {
        return new OutOfLineBinding.ForeignKeyColumns();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.KeyColumns }
     *
     */
    public OutOfLineBinding.KeyColumns createOutOfLineBindingKeyColumns() {
        return new OutOfLineBinding.KeyColumns();
    }

    /**
     * Create an instance of {@link CloneDatabase }
     *
     */
    public CloneDatabase createCloneDatabase() {
        return new CloneDatabase();
    }

    /**
     * Create an instance of {@link ImageLoad }
     *
     */
    public ImageLoad createImageLoad() {
        return new ImageLoad();
    }

    /**
     * Create an instance of {@link Batch }
     *
     */
    public Batch createBatch() {
        return new Batch();
    }

    /**
     * Create an instance of {@link NotifyTableChange }
     *
     */
    public NotifyTableChange createNotifyTableChange() {
        return new NotifyTableChange();
    }

    /**
     * Create an instance of {@link WhereAttribute }
     *
     */
    public WhereAttribute createWhereAttribute() {
        return new WhereAttribute();
    }

    /**
     * Create an instance of {@link Update }
     *
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link AttributeInsertUpdate }
     *
     */
    public AttributeInsertUpdate createAttributeInsertUpdate() {
        return new AttributeInsertUpdate();
    }

    /**
     * Create an instance of {@link Insert }
     *
     */
    public Insert createInsert() {
        return new Insert();
    }

    /**
     * Create an instance of {@link Synchronize }
     *
     */
    public Synchronize createSynchronize() {
        return new Synchronize();
    }

    /**
     * Create an instance of {@link Location }
     *
     */
    public Location createLocation() {
        return new Location();
    }

    /**
     * Create an instance of {@link Restore }
     *
     */
    public Restore createRestore() {
        return new Restore();
    }

    /**
     * Create an instance of {@link Backup }
     *
     */
    public Backup createBackup() {
        return new Backup();
    }

    /**
     * Create an instance of {@link DesignAggregations }
     *
     */
    public DesignAggregations createDesignAggregations() {
        return new DesignAggregations();
    }

    /**
     * Create an instance of {@link MergePartitions }
     *
     */
    public MergePartitions createMergePartitions() {
        return new MergePartitions();
    }

    /**
     * Create an instance of {@link DataSourceView }
     *
     */
    public DataSourceView createDataSourceView() {
        return new DataSourceView();
    }

    /**
     * Create an instance of {@link Restrictions }
     *
     */
    public Restrictions createDiscoverRestrictions() {
        return new Restrictions();
    }

    /**
     * Create an instance of {@link KeepResult }
     *
     */
    public KeepResult createKeepResult() {
        return new KeepResult();
    }

    /**
     * Create an instance of {@link ClearResult }
     *
     */
    public ClearResult createClearResult() {
        return new ClearResult();
    }

    /**
     * Create an instance of {@link Result }
     *
     */
    public Result createResult() {
        return new Result();
    }

    /**
     * Create an instance of {@link ExecuteResponse }
     *
     */
    public ExecuteResponse createExecuteResponse() {
        return new ExecuteResponse();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Return }
     *
     */
    public Return createReturn() {
        return new Return();
    }

    /**
     * Create an instance of {@link EventSession }
     *
     */
    public EventSession createEventSession() {
        return new EventSession();
    }

    /**
     * Create an instance of {@link Event2 }
     *
     */
    public Event2 createEvent2() {
        return new Event2();
    }

    /**
     * Create an instance of {@link Action2 }
     *
     */
    public Action2 createAction2() {
        return new Action2();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Target }
     *
     */
    public org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Target createTarget() {
        return new org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Target();
    }

    /**
     * Create an instance of {@link Parameter }
     *
     */
    public Parameter createParameter() {
        return new Parameter();
    }

    /**
     * Create an instance of {@link Command }
     *
     */
    public Command createCommand() {
        return new Command();
    }

    /**
     * Create an instance of {@link Execute.Properties }
     *
     */
    public Execute.Properties createExecuteProperties() {
        return new Execute.Properties();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Properties }
     *
     */
    public org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Properties createProperties() {
        return new org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Properties();
    }

    /**
     * Create an instance of {@link BeginSession }
     *
     */
    public BeginSession createBeginSession() {
        return new BeginSession();
    }

    /**
     * Create an instance of {@link EndSession }
     *
     */
    public EndSession createEndSession() {
        return new EndSession();
    }

    /**
     * Create an instance of {@link Session }
     *
     */
    public Session createSession() {
        return new Session();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Parameters }
     *
     */
    public org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Parameters createParameters() {
        return new org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Parameters();
    }

    /**
     * Create an instance of {@link PropertyList }
     *
     */
    public PropertyList createPropertyList() {
        return new PropertyList();
    }

    /**
     * Create an instance of {@link ObjectReference }
     *
     */
    public ObjectReference createObjectReference() {
        return new ObjectReference();
    }

    /**
     * Create an instance of {@link Statement }
     *
     */
    public Statement createStatement() {
        return new Statement();
    }

    /**
     * Create an instance of {@link Create }
     *
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link Alter }
     *
     */
    public Alter createAlter() {
        return new Alter();
    }

    /**
     * Create an instance of {@link Delete }
     *
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link Process }
     *
     */
    public Process createProcess() {
        return new Process();
    }

    /**
     * Create an instance of {@link Bindings }
     *
     */
    public Bindings createBindings() {
        return new Bindings();
    }

    /**
     * Create an instance of {@link ClearCache }
     *
     */
    public ClearCache createClearCache() {
        return new ClearCache();
    }

    /**
     * Create an instance of {@link Subscribe }
     *
     */
    public Subscribe createSubscribe() {
        return new Subscribe();
    }

    /**
     * Create an instance of {@link Unsubscribe }
     *
     */
    public Unsubscribe createUnsubscribe() {
        return new Unsubscribe();
    }

    /**
     * Create an instance of {@link Cancel }
     *
     */
    public Cancel createCancel() {
        return new Cancel();
    }

    /**
     * Create an instance of {@link BeginTransaction }
     *
     */
    public BeginTransaction createBeginTransaction() {
        return new BeginTransaction();
    }

    /**
     * Create an instance of {@link CommitTransaction }
     *
     */
    public CommitTransaction createCommitTransaction() {
        return new CommitTransaction();
    }

    /**
     * Create an instance of {@link RollbackTransaction }
     *
     */
    public RollbackTransaction createRollbackTransaction() {
        return new RollbackTransaction();
    }

    /**
     * Create an instance of {@link Lock }
     *
     */
    public Lock createLock() {
        return new Lock();
    }

    /**
     * Create an instance of {@link Unlock }
     *
     */
    public Unlock createUnlock() {
        return new Unlock();
    }

    /**
     * Create an instance of {@link LocationBackup }
     *
     */
    public LocationBackup createLocationBackup() {
        return new LocationBackup();
    }

    /**
     * Create an instance of {@link MajorObject }
     *
     */
    public MajorObject createMajorObject() {
        return new MajorObject();
    }

    /**
     * Create an instance of {@link Folder }
     *
     */
    public Folder createFolder() {
        return new Folder();
    }

    /**
     * Create an instance of {@link Source }
     *
     */
    public Source createSource() {
        return new Source();
    }

    /**
     * Create an instance of {@link Attach }
     *
     */
    public Attach createAttach() {
        return new Attach();
    }

    /**
     * Create an instance of {@link Detach }
     *
     */
    public Detach createDetach() {
        return new Detach();
    }

    /**
     * Create an instance of {@link XmlaObject }
     *
     */
    public XmlaObject createObject() {
        return new XmlaObject();
    }

    /**
     * Create an instance of {@link TranslationInsertUpdate }
     *
     */
    public TranslationInsertUpdate createTranslationInsertUpdate() {
        return new TranslationInsertUpdate();
    }

    /**
     * Create an instance of {@link Where }
     *
     */
    public Where createWhere() {
        return new Where();
    }

    /**
     * Create an instance of {@link Drop }
     *
     */
    public Drop createDrop() {
        return new Drop();
    }

    /**
     * Create an instance of {@link UpdateCells }
     *
     */
    public UpdateCells createUpdateCells() {
        return new UpdateCells();
    }

    /**
     * Create an instance of {@link Cell }
     *
     */
    public Cell createCell() {
        return new Cell();
    }

    /**
     * Create an instance of {@link ImageSave }
     *
     */
    public ImageSave createImageSave() {
        return new ImageSave();
    }

    /**
     * Create an instance of {@link SetAuthContext }
     *
     */
    public SetAuthContext createSetAuthContext() {
        return new SetAuthContext();
    }

    /**
     * Create an instance of {@link DBCC }
     *
     */
    public DBCC createDBCC() {
        return new DBCC();
    }

    /**
     * Create an instance of {@link ExecuteParameter }
     *
     */
    public ExecuteParameter createExecuteParameter() {
        return new ExecuteParameter();
    }

    /**
     * Create an instance of {@link ColumnBinding }
     *
     */
    public ColumnBinding createColumnBinding() {
        return new ColumnBinding();
    }

    /**
     * Create an instance of {@link RowBinding }
     *
     */
    public RowBinding createRowBinding() {
        return new RowBinding();
    }

    /**
     * Create an instance of {@link DataSourceViewBinding }
     *
     */
    public DataSourceViewBinding createDataSourceViewBinding() {
        return new DataSourceViewBinding();
    }

    /**
     * Create an instance of {@link AttributeBinding }
     *
     */
    public AttributeBinding createAttributeBinding() {
        return new AttributeBinding();
    }

    /**
     * Create an instance of {@link MeasureBinding }
     *
     */
    public MeasureBinding createMeasureBinding() {
        return new MeasureBinding();
    }

    /**
     * Create an instance of {@link DimensionBinding }
     *
     */
    public DimensionBinding createDimensionBinding() {
        return new DimensionBinding();
    }

    /**
     * Create an instance of {@link CubeDimensionBinding }
     *
     */
    public CubeDimensionBinding createCubeDimensionBinding() {
        return new CubeDimensionBinding();
    }

    /**
     * Create an instance of {@link MeasureGroupBinding }
     *
     */
    public MeasureGroupBinding createMeasureGroupBinding() {
        return new MeasureGroupBinding();
    }

    /**
     * Create an instance of {@link MeasureGroupDimensionBinding }
     *
     */
    public MeasureGroupDimensionBinding createMeasureGroupDimensionBinding() {
        return new MeasureGroupDimensionBinding();
    }

    /**
     * Create an instance of {@link TimeBinding }
     *
     */
    public TimeBinding createTimeBinding() {
        return new TimeBinding();
    }

    /**
     * Create an instance of {@link TimeAttributeBinding }
     *
     */
    public TimeAttributeBinding createTimeAttributeBinding() {
        return new TimeAttributeBinding();
    }

    /**
     * Create an instance of {@link InheritedBinding }
     *
     */
    public InheritedBinding createInheritedBinding() {
        return new InheritedBinding();
    }

    /**
     * Create an instance of {@link TableBinding }
     *
     */
    public TableBinding createTableBinding() {
        return new TableBinding();
    }

    /**
     * Create an instance of {@link QueryBinding }
     *
     */
    public QueryBinding createQueryBinding() {
        return new QueryBinding();
    }

    /**
     * Create an instance of {@link DSVTableBinding }
     *
     */
    public DSVTableBinding createDSVTableBinding() {
        return new DSVTableBinding();
    }

    /**
     * Create an instance of {@link ProactiveCachingInheritedBinding }
     *
     */
    public ProactiveCachingInheritedBinding createProactiveCachingInheritedBinding() {
        return new ProactiveCachingInheritedBinding();
    }

    /**
     * Create an instance of {@link QueryNotification }
     *
     */
    public QueryNotification createQueryNotification() {
        return new QueryNotification();
    }

    /**
     * Create an instance of {@link IncrementalProcessingNotification }
     *
     */
    public IncrementalProcessingNotification createIncrementalProcessingNotification() {
        return new IncrementalProcessingNotification();
    }

    /**
     * Create an instance of {@link TableNotification }
     *
     */
    public TableNotification createTableNotification() {
        return new TableNotification();
    }

    /**
     * Create an instance of {@link CalculatedMeasureBinding }
     *
     */
    public CalculatedMeasureBinding createCalculatedMeasureBinding() {
        return new CalculatedMeasureBinding();
    }

    /**
     * Create an instance of {@link DatabasePermission }
     *
     */
    public DatabasePermission createDatabasePermission() {
        return new DatabasePermission();
    }

    /**
     * Create an instance of {@link DataSourcePermission }
     *
     */
    public DataSourcePermission createDataSourcePermission() {
        return new DataSourcePermission();
    }

    /**
     * Create an instance of {@link MiningStructurePermission }
     *
     */
    public MiningStructurePermission createMiningStructurePermission() {
        return new MiningStructurePermission();
    }

    /**
     * Create an instance of {@link MiningModelPermission }
     *
     */
    public MiningModelPermission createMiningModelPermission() {
        return new MiningModelPermission();
    }

    /**
     * Create an instance of {@link Member }
     *
     */
    public Member createMember() {
        return new Member();
    }

    /**
     * Create an instance of {@link ProactiveCaching }
     *
     */
    public ProactiveCaching createProactiveCaching() {
        return new ProactiveCaching();
    }

    /**
     * Create an instance of {@link ErrorConfiguration }
     *
     */
    public ErrorConfiguration createErrorConfiguration() {
        return new ErrorConfiguration();
    }

    /**
     * Create an instance of {@link Annotation }
     *
     */
    public Annotation createAnnotation() {
        return new Annotation();
    }

    /**
     * Create an instance of {@link RelationalDataSource }
     *
     */
    public RelationalDataSource createRelationalDataSource() {
        return new RelationalDataSource();
    }

    /**
     * Create an instance of {@link OlapDataSource }
     *
     */
    public OlapDataSource createOlapDataSource() {
        return new OlapDataSource();
    }

    /**
     * Create an instance of {@link ServerProperty }
     *
     */
    public ServerProperty createServerProperty() {
        return new ServerProperty();
    }

    /**
     * Create an instance of {@link ComAssembly }
     *
     */
    public ComAssembly createComAssembly() {
        return new ComAssembly();
    }

    /**
     * Create an instance of {@link ClrAssemblyFile }
     *
     */
    public ClrAssemblyFile createClrAssemblyFile() {
        return new ClrAssemblyFile();
    }

    /**
     * Create an instance of {@link DataBlock }
     *
     */
    public DataBlock createDataBlock() {
        return new DataBlock();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Event }
     *
     */
    public org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Event createEvent() {
        return new org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Event();
    }

    /**
     * Create an instance of {@link EventColumnID }
     *
     */
    public EventColumnID createEventColumnID() {
        return new EventColumnID();
    }

    /**
     * Create an instance of {@link TraceFilter }
     *
     */
    public TraceFilter createTraceFilter() {
        return new TraceFilter();
    }

    /**
     * Create an instance of {@link NotType }
     *
     */
    public NotType createNotType() {
        return new NotType();
    }

    /**
     * Create an instance of {@link AndOrType }
     *
     */
    public AndOrType createAndOrType() {
        return new AndOrType();
    }

    /**
     * Create an instance of {@link BoolBinop }
     *
     */
    public BoolBinop createBoolBinop() {
        return new BoolBinop();
    }

    /**
     * Create an instance of {@link UnaryExpr }
     *
     */
    public UnaryExpr createUnaryExpr() {
        return new UnaryExpr();
    }

    /**
     * Create an instance of {@link BooleanExpr }
     *
     */
    public BooleanExpr createBooleanExpr() {
        return new BooleanExpr();
    }

    /**
     * Create an instance of {@link MiningModelingFlag }
     *
     */
    public MiningModelingFlag createMiningModelingFlag() {
        return new MiningModelingFlag();
    }

    /**
     * Create an instance of {@link AlgorithmParameter }
     *
     */
    public AlgorithmParameter createAlgorithmParameter() {
        return new AlgorithmParameter();
    }

    /**
     * Create an instance of {@link FoldingParameters }
     *
     */
    public FoldingParameters createFoldingParameters() {
        return new FoldingParameters();
    }

    /**
     * Create an instance of {@link ReportParameter }
     *
     */
    public ReportParameter createReportParameter() {
        return new ReportParameter();
    }

    /**
     * Create an instance of {@link ReportFormatParameter }
     *
     */
    public ReportFormatParameter createReportFormatParameter() {
        return new ReportFormatParameter();
    }

    /**
     * Create an instance of {@link AggregationDesignAttribute }
     *
     */
    public AggregationDesignAttribute createAggregationDesignAttribute() {
        return new AggregationDesignAttribute();
    }

    /**
     * Create an instance of {@link AggregationInstanceMeasure }
     *
     */
    public AggregationInstanceMeasure createAggregationInstanceMeasure() {
        return new AggregationInstanceMeasure();
    }

    /**
     * Create an instance of {@link Partition.StorageMode }
     *
     */
    public Partition.StorageMode createPartitionStorageMode() {
        return new Partition.StorageMode();
    }

    /**
     * Create an instance of {@link Partition.CurrentStorageMode }
     *
     */
    public Partition.CurrentStorageMode createPartitionCurrentStorageMode() {
        return new Partition.CurrentStorageMode();
    }

    /**
     * Create an instance of {@link MeasureGroup.StorageMode }
     *
     */
    public MeasureGroup.StorageMode createMeasureGroupStorageMode() {
        return new MeasureGroup.StorageMode();
    }

    /**
     * Create an instance of {@link Cube.StorageMode }
     *
     */
    public Cube.StorageMode createCubeStorageMode() {
        return new Cube.StorageMode();
    }

    /**
     * Create an instance of {@link PredLeaf.Comparator }
     *
     */
    public PredLeaf.Comparator createPredLeafComparator() {
        return new PredLeaf.Comparator();
    }

    /**
     * Create an instance of {@link PredLeaf.Event }
     *
     */
    public PredLeaf.Event createPredLeafEvent() {
        return new PredLeaf.Event();
    }

    /**
     * Create an instance of {@link PredLeaf.Global }
     *
     */
    public PredLeaf.Global createPredLeafGlobal() {
        return new PredLeaf.Global();
    }

    /**
     * Create an instance of {@link DimensionAttribute.Type }
     *
     */
    public DimensionAttribute.Type createDimensionAttributeType() {
        return new DimensionAttribute.Type();
    }

    /**
     * Create an instance of {@link Dimension.UnknownMember }
     *
     */
    public Dimension.UnknownMember createDimensionUnknownMember() {
        return new Dimension.UnknownMember();
    }

    /**
     * Create an instance of {@link Dimension.CurrentStorageMode }
     *
     */
    public Dimension.CurrentStorageMode createDimensionCurrentStorageMode() {
        return new Dimension.CurrentStorageMode();
    }

    /**
     * Create an instance of {@link PushedDataSource.Root }
     *
     */
    public PushedDataSource.Root createPushedDataSourceRoot() {
        return new PushedDataSource.Root();
    }

    /**
     * Create an instance of {@link PushedDataSource.EndOfData }
     *
     */
    public PushedDataSource.EndOfData createPushedDataSourceEndOfData() {
        return new PushedDataSource.EndOfData();
    }

    /**
     * Create an instance of {@link TraceColumns.Data.Column }
     *
     */
    public TraceColumns.Data.Column createTraceColumnsDataColumn() {
        return new TraceColumns.Data.Column();
    }

    /**
     * Create an instance of {@link EventColumn.EventColumnSubclassList.EventColumnSubclass }
     *
     */
    public EventColumn.EventColumnSubclassList.EventColumnSubclass createEventColumnEventColumnSubclassListEventColumnSubclass() {
        return new EventColumn.EventColumnSubclassList.EventColumnSubclass();
    }

    /**
     * Create an instance of {@link TraceEventCategories.Data.EventCategory.EventList }
     *
     */
    public TraceEventCategories.Data.EventCategory.EventList createTraceEventCategoriesDataEventCategoryEventList() {
        return new TraceEventCategories.Data.EventCategory.EventList();
    }

    /**
     * Create an instance of {@link TraceDefinitionProviderInfo.Data.Version }
     *
     */
    public TraceDefinitionProviderInfo.Data.Version createTraceDefinitionProviderInfoDataVersion() {
        return new TraceDefinitionProviderInfo.Data.Version();
    }

    /**
     * Create an instance of {@link CubeAttributeBinding.Ordinal }
     *
     */
    public CubeAttributeBinding.Ordinal createCubeAttributeBindingOrdinal() {
        return new CubeAttributeBinding.Ordinal();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.NameColumn }
     *
     */
    public OutOfLineBinding.NameColumn createOutOfLineBindingNameColumn() {
        return new OutOfLineBinding.NameColumn();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.SkippedLevelsColumn }
     *
     */
    public OutOfLineBinding.SkippedLevelsColumn createOutOfLineBindingSkippedLevelsColumn() {
        return new OutOfLineBinding.SkippedLevelsColumn();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.CustomRollupColumn }
     *
     */
    public OutOfLineBinding.CustomRollupColumn createOutOfLineBindingCustomRollupColumn() {
        return new OutOfLineBinding.CustomRollupColumn();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.CustomRollupPropertiesColumn }
     *
     */
    public OutOfLineBinding.CustomRollupPropertiesColumn createOutOfLineBindingCustomRollupPropertiesColumn() {
        return new OutOfLineBinding.CustomRollupPropertiesColumn();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.ValueColumn }
     *
     */
    public OutOfLineBinding.ValueColumn createOutOfLineBindingValueColumn() {
        return new OutOfLineBinding.ValueColumn();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.UnaryOperatorColumn }
     *
     */
    public OutOfLineBinding.UnaryOperatorColumn createOutOfLineBindingUnaryOperatorColumn() {
        return new OutOfLineBinding.UnaryOperatorColumn();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.Translations.Translation }
     *
     */
    public OutOfLineBinding.Translations.Translation createOutOfLineBindingTranslationsTranslation() {
        return new OutOfLineBinding.Translations.Translation();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.ForeignKeyColumns.ForeignKeyColumn }
     *
     */
    public OutOfLineBinding.ForeignKeyColumns.ForeignKeyColumn createOutOfLineBindingForeignKeyColumnsForeignKeyColumn() {
        return new OutOfLineBinding.ForeignKeyColumns.ForeignKeyColumn();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.KeyColumns.KeyColumn }
     *
     */
    public OutOfLineBinding.KeyColumns.KeyColumn createOutOfLineBindingKeyColumnsKeyColumn() {
        return new OutOfLineBinding.KeyColumns.KeyColumn();
    }

    /**
     * Create an instance of {@link CloneDatabase.Object }
     *
     */
    public CloneDatabase.Object createCloneDatabaseObject() {
        return new CloneDatabase.Object();
    }

    /**
     * Create an instance of {@link CloneDatabase.Target }
     *
     */
    public CloneDatabase.Target createCloneDatabaseTarget() {
        return new CloneDatabase.Target();
    }

    /**
     * Create an instance of {@link Batch.Parallel }
     *
     */
    public Batch.Parallel createBatchParallel() {
        return new Batch.Parallel();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "urn:schemas-microsoft-com:xml-analysis", name = "AllowedRowsExpression")
    public JAXBElement<String> createAllowedRowsExpression(String value) {
        return new JAXBElement<>(_AllowedRowsExpression_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "urn:schemas-microsoft-com:xml-analysis", name = "ShareDimensionStorage")
    public JAXBElement<String> createShareDimensionStorage(String value) {
        return new JAXBElement<>(_ShareDimensionStorage_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BooleanExpr }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link BooleanExpr }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "and", scope = BooleanExpr.class)
    public JAXBElement<BooleanExpr> createBooleanExprAnd(BooleanExpr value) {
        return new JAXBElement<>(_BooleanExprAnd_QNAME, BooleanExpr.class, BooleanExpr.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BooleanExpr }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link BooleanExpr }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "or", scope = BooleanExpr.class)
    public JAXBElement<BooleanExpr> createBooleanExprOr(BooleanExpr value) {
        return new JAXBElement<>(_BooleanExprOr_QNAME, BooleanExpr.class, BooleanExpr.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnaryExpr }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link UnaryExpr }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "not", scope = BooleanExpr.class)
    public JAXBElement<UnaryExpr> createBooleanExprNot(UnaryExpr value) {
        return new JAXBElement<>(_BooleanExprNot_QNAME, UnaryExpr.class, BooleanExpr.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PredLeaf }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link PredLeaf }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "leaf", scope = BooleanExpr.class)
    public JAXBElement<PredLeaf> createBooleanExprLeaf(PredLeaf value) {
        return new JAXBElement<>(_BooleanExprLeaf_QNAME, PredLeaf.class, BooleanExpr.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link NotType }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Not", scope = AndOrType.class)
    public JAXBElement<NotType> createAndOrTypeNot(NotType value) {
        return new JAXBElement<>(_AndOrTypeNot_QNAME, NotType.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AndOrType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link AndOrType }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Or", scope = AndOrType.class)
    public JAXBElement<AndOrType> createAndOrTypeOr(AndOrType value) {
        return new JAXBElement<>(_AndOrTypeOr_QNAME, AndOrType.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AndOrType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link AndOrType }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "And", scope = AndOrType.class)
    public JAXBElement<AndOrType> createAndOrTypeAnd(AndOrType value) {
        return new JAXBElement<>(_AndOrTypeAnd_QNAME, AndOrType.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Equal", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeEqual(BoolBinop value) {
        return new JAXBElement<>(_AndOrTypeEqual_QNAME, BoolBinop.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "NotEqual", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeNotEqual(BoolBinop value) {
        return new JAXBElement<>(_AndOrTypeNotEqual_QNAME, BoolBinop.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Less", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeLess(BoolBinop value) {
        return new JAXBElement<>(_AndOrTypeLess_QNAME, BoolBinop.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "LessOrEqual", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeLessOrEqual(BoolBinop value) {
        return new JAXBElement<>(_AndOrTypeLessOrEqual_QNAME, BoolBinop.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Greater", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeGreater(BoolBinop value) {
        return new JAXBElement<>(_AndOrTypeGreater_QNAME, BoolBinop.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "GreaterOrEqual", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeGreaterOrEqual(BoolBinop value) {
        return new JAXBElement<>(_AndOrTypeGreaterOrEqual_QNAME, BoolBinop.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Like", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeLike(BoolBinop value) {
        return new JAXBElement<>(_AndOrTypeLike_QNAME, BoolBinop.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "NotLike", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeNotLike(BoolBinop value) {
        return new JAXBElement<>(_AndOrTypeNotLike_QNAME, BoolBinop.class, AndOrType.class, value);
    }

}
