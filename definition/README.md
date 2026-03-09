# Definition Files - Spec Comparison Report

Generated from `[MS-SSAS]-251031.docx` (v20251031, October 31, 2025)

## Summary

- Spec version: v20251031 (revision 36.0)
- Definition files compared: 18
- Missing engine namespace schema files: **0**
- Types/elements matching: 0
- Types/elements with differences: **223**
- Types/elements missing from definition: **263**

## Missing Engine Namespace Schema Files

The spec v20251031 defines the following engine namespaces that have no corresponding XSD file in `definition/`:

| Prefix | Namespace URI | Referenced Elements |
|--------|--------------|---------------------|

### Recommendation

Create the missing `.xsd` files and update `xmla.wsdl` to import them. Use `eng300_300.xsd` or `eng800.xsd` as a
template.

## Detailed Comparison by File

### eng100.xsd

#### Missing from definition (2)

- `complexType` **Database** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types >
  Complex Type Definitions for Server Object Hierarchy > Database)
- `complexType` **Attach** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Execute > Elements > Execute > Command Element > Attach)

#### Extra in definition (not in spec) (6)

- `element` **ReadWriteMode**
- `element` **HoldoutMaxPercent**
- `element` **HoldoutMaxCases**
- `element` **HoldoutSeed**
- `element` **HoldoutActualSize**
- `element` **DbStorageLocation**

*Matching: 0, Different: 0, Missing: 2, Extra: 6*

### eng100_100.xsd

#### Missing from definition (4)

- `complexType` **MiningStructure** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > MiningStructure)
- `complexType` **Restore** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Execute > Elements > Execute > Command Element > Restore)
- `complexType` **Synchronize** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Execute > Elements > Execute > Command Element > Synchronize)
- `complexType` **CloneDatabase** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Execute > Elements > Execute > Command Element > CloneDatabase)

#### Extra in definition (not in spec) (5)

- `element` **DbStorageLocation**
- `element` **HoldoutMaxPercent**
- `element` **HoldoutMaxCases**
- `element` **HoldoutSeed**
- `element` **HoldoutActualSize**

*Matching: 0, Different: 0, Missing: 4, Extra: 5*

### eng200.xsd

#### Missing from definition (1)

- `complexType` **ErrorConfiguration** (spec: Messages > Common Message Syntax > Complex Types > Object Definition
  Complex Types > Complex Type Definitions for Server Object Hierarchy > ErrorConfiguration)

#### Extra in definition (not in spec) (5)

- `complexType` **WarningLocationObject**
- `element` **WarningColumn**
- `element` **WarningMeasure**
- `element` **CalculationError**
- `element` **CompatibilityLevel**

*Matching: 0, Different: 0, Missing: 1, Extra: 5*

### eng200_200.xsd

#### Missing from definition (5)

- `complexType` **Dimension** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Dimension)
- `complexType` **ReferenceMeasureGroupDimension** (spec: Messages > Common Message Syntax > Complex Types > Object
  Definition Complex Types > Complex Type Definitions for Server Object Hierarchy > MeasureGroup >
  MeasureGroupDimension > ReferenceMeasureGroupDimension)
- `complexType` **DegenerateMeasureGroupDimension** (spec: Messages > Common Message Syntax > Complex Types > Object
  Definition Complex Types > Complex Type Definitions for Server Object Hierarchy > MeasureGroup >
  MeasureGroupDimension > DegenerateMeasureGroupDimension)
- `complexType` **ImageLoad** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Execute > Elements > Execute > Command Element > ImageLoad)
- `complexType` **SetAuthContext** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Execute > Elements > Execute > Command Element > SetAuthContext)

#### Types with differences (1)

##### `complexType` **ExpressionBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > eng200_200:ExpressionBinding

```diff
--- definition/+++ spec/@@ -1,8 +1,8 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:ana="urn:schemas-microsoft-com:xml-analysis" name="ExpressionBinding">
+<xsd:complexType name="ExpressionBinding">
     <xsd:complexContent>
-      <xsd:extension base="ana:Binding">
+      <xsd:extension base="Binding">
         <xsd:all>
-          <xsd:element name="Expression" type="xsd:string"/>
+          <xsd:element name="Expression" type="xsd:string" />
         </xsd:all>
       </xsd:extension>
     </xsd:complexContent>

```

#### Extra in definition (not in spec) (10)

- `complexType` **RowNumberBinding**
- `element` **StorageEngineUsed**
- `element` **ImagePath**
- `element` **ImageUrl**
- `element` **ImageUniqueID**
- `element` **ImageVersion**
- `element` **Token**
- `element` **ProcessingRecommendation**
- `element` **ProcessingState**
- `element` **ShareDimensionStorage**

*Matching: 0, Different: 1, Missing: 5, Extra: 10*

### eng300.xsd

#### Missing from definition (7)

- `complexType` **Server** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types >
  Complex Type Definitions for Server Object Hierarchy > Server)
- `complexType` **DataSource** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > DataSource)
- `complexType` **Hierarchy** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Dimension > Hierarchy)
- `complexType` **Partition** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Partition)
- `simpleType` **PartitionStorageModeEnumType** (spec: Messages > Common Message Syntax > Complex Types > Object
  Definition Complex Types > Complex Type Definitions for Server Object Hierarchy > Partition)
- `simpleType` **PartitionCurrentStorageModeEnumType** (spec: Messages > Common Message Syntax > Complex Types > Object
  Definition Complex Types > Complex Type Definitions for Server Object Hierarchy > Partition)
- `complexType` **DSVTableBinding** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Binding > DSVTableBinding)

#### Extra in definition (not in spec) (13)

- `complexType` **RelationshipEndVisualizationProperties**
- `complexType` **DimensionAttributeVisualizationProperties**
- `complexType` **HierarchyVisualizationProperties**
- `complexType` **CalculationPropertiesVisualizationProperties**
- `simpleType` **AttributeHierarchyProcessingState**
- `element` **DataEmbeddingStyle**
- `element` **QueryImpersonationInfo**
- `element` **QueryHints**
- `element` **StringStoresCompatibilityLevel**
- `element` **CurrentStringStoresCompatibilityLevel**
- `element` **ProcessingState**
- `element` **StructureType**
- `element` **ServerMode**

*Matching: 0, Different: 0, Missing: 7, Extra: 13*

### eng300_300.xsd

#### Missing from definition (2)

- `complexType` **Trace** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types >
  Complex Type Definitions for Server Object Hierarchy > Trace)
- `complexType` **DimensionPermission** (spec: Messages > Common Message Syntax > Complex Types > Object Definition
  Complex Types > Complex Type Definitions for Server Object Hierarchy > DimensionPermission)

#### Extra in definition (not in spec) (8)

- `complexType` **Relationship**
- `complexType` **RelationshipEnd**
- `complexType` **RelationshipEndTranslation**
- `complexType` **Relationships**
- `element` **DirectQueryMode**
- `element` **AllowedRowsExpression**
- `element` **XEvent**
- `element` **DirectQueryUsage**

*Matching: 0, Different: 0, Missing: 2, Extra: 8*

### eng800.xsd

#### Missing from definition (4)

- `complexType` **Cube** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types >
  Complex Type Definitions for Server Object Hierarchy > Cube)
- `simpleType` **CubeStorageModeEnumType** (spec: Messages > Common Message Syntax > Complex Types > Object Definition
  Complex Types > Complex Type Definitions for Server Object Hierarchy > Cube)
- `complexType` **Subscribe** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Execute > Elements > Execute > Command Element > Subscribe)
- `complexType` **Unsubscribe** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Execute > Elements > Execute > Command Element > Unsubscribe)

#### Extra in definition (not in spec) (2)

- `element` **SubscriptionId**
- `element` **DaxOptimizationMode**

*Matching: 0, Different: 0, Missing: 4, Extra: 2*

### eng920.xsd

#### Extra in definition (not in spec) (1)

- `element` **IgnoreIncompatibilities**

*Matching: 0, Different: 0, Missing: 0, Extra: 1*

### eng922.xsd

#### Types with differences (1)

##### `complexType` **RemoveDiscontinuedFeatures**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > RemoveDiscontinuedFeatures

```diff
--- definition/+++ spec/@@ -1,6 +1,7 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="RemoveDiscontinuedFeatures">
+<xsd:complexType name="RemoveDiscontinuedFeatures">
     <xsd:all>
-      <xsd:element name="DatabaseID" type="xsd:string" minOccurs="1"/>
-      <xsd:element name="RemoveDataMining" type="xsd:boolean" minOccurs="0"/>
+      <xsd:element name="DatabaseID" type="xsd:string" minOccurs="1" />
+      <xsd:element name="RemoveDataMining" type="xsd:boolean" minOccurs="0" />
+      <xsd:element ref="eng922:EnsureProperEncryption" minOccurs="0" />
     </xsd:all>
   </xsd:complexType>
```

#### Extra in definition (not in spec) (1)

- `element` **EnsureProperEncryption**

*Matching: 0, Different: 1, Missing: 0, Extra: 1*

### ext.xsd

#### Types with differences (2)

##### `element` **Authenticate**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Authenticate >
Elements > Authenticate

```diff
--- definition/+++ spec/@@ -1,7 +1,8 @@-<xsd:element xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://schemas.microsoft.com/analysisservices/2003/ext" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Authenticate">
+<xsd:element name="Authenticate">
     <xsd:complexType>
       <xsd:sequence>
-        <xsd:element name="SspiHandshake" minOccurs="1" maxOccurs="1" nillable="false" type="xsd:base64Binary"/>
+        <xsd:element name="SspiHandshake" minOccurs="1" maxOccurs="1"
+                     nillable="false" type="xsd:base64Binary" />
       </xsd:sequence>
     </xsd:complexType>
   </xsd:element>
```

##### `element` **AuthenticateResponse**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Authenticate >
Elements > AuthenticateResponse

```diff
--- definition/+++ spec/@@ -1,10 +1,11 @@-<xsd:element xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://schemas.microsoft.com/analysisservices/2003/ext" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AuthenticateResponse">
+<xsd:element name="AuthenticateResponse">
     <xsd:complexType>
       <xsd:sequence>
         <xsd:element name="return" minOccurs="1" maxOccurs="1">
           <xsd:complexType>
             <xsd:sequence>
-              <xsd:element name="SspiHandshake" minOccurs="1" maxOccurs="1" nillable="false" type="xsd:base64Binary"/>
+              <xsd:element name="SspiHandshake" minOccurs="1" maxOccurs="1"
+                           nillable="false" type="xsd:base64Binary" />
             </xsd:sequence>
           </xsd:complexType>
         </xsd:element>

```

*Matching: 0, Different: 2, Missing: 0, Extra: 0*

### msxmla.xsd

#### Types with differences (2)

##### `element` **NormTupleSet**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > SetType Model Group > msxmla:NormTupleSet Complex Type

```diff
--- definition/+++ spec/@@ -1,14 +1,36 @@-<xsd:element xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" name="NormTupleSet">
+<xsd:element name="NormTupleSet">
     <xsd:complexType>
       <xsd:sequence>
-        <xsd:element name="NormTuples" type="NormTuplesType" minOccurs="1" maxOccurs="1"/>
+        <xsd:element name="NormTuples" type="msxmla:NormTuplesType"
+                     minOccurs="1" maxOccurs="1"/>
         <xsd:element name="MembersLookup" minOccurs="1" maxOccurs="1">
           <xsd:complexType>
             <xsd:sequence>
-              <xsd:element ref="ana-ds:Members" minOccurs="1" maxOccurs="unbounded"/>
+              <xsd:element ref="xmla-ds:Members" minOccurs="1" maxOccurs="unbounded"/>
             </xsd:sequence>
           </xsd:complexType>
         </xsd:element>
       </xsd:sequence>
     </xsd:complexType>
-  </xsd:element>+  </xsd:element>
+
+<xsd:complexType name="NormTuplesType">
+    <xsd:sequence>
+      <xsd:element name="NormTuple" minOccurs="0" maxOccurs="unbounded">
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="MemberRef" minOccurs="0" maxOccurs="unbounded">
+              <xsd:complexType>
+                <xsd:sequence>
+                  <xsd:element name="MemberOrdinal" type="xsd:int"
+                               minOccurs="1" maxOccurs="1"/>
+                  <xsd:element name="MemberDispInfo" type="xsd:int"
+                               minOccurs="0" maxOccurs="1"/>
+                </xsd:sequence>
+              </xsd:complexType>
+            </xsd:element>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+    </xsd:sequence>
+  </xsd:complexType>
```

##### `complexType` **NormTuplesType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > SetType Model Group > msxmla:NormTupleSet Complex Type

```diff
--- definition/+++ spec/@@ -1,4 +1,20 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" name="NormTuplesType">
+<xsd:element name="NormTupleSet">
+    <xsd:complexType>
+      <xsd:sequence>
+        <xsd:element name="NormTuples" type="msxmla:NormTuplesType"
+                     minOccurs="1" maxOccurs="1"/>
+        <xsd:element name="MembersLookup" minOccurs="1" maxOccurs="1">
+          <xsd:complexType>
+            <xsd:sequence>
+              <xsd:element ref="xmla-ds:Members" minOccurs="1" maxOccurs="unbounded"/>
+            </xsd:sequence>
+          </xsd:complexType>
+        </xsd:element>
+      </xsd:sequence>
+    </xsd:complexType>
+  </xsd:element>
+
+<xsd:complexType name="NormTuplesType">
     <xsd:sequence>
       <xsd:element name="NormTuple" minOccurs="0" maxOccurs="unbounded">
         <xsd:complexType>
@@ -6,8 +22,10 @@             <xsd:element name="MemberRef" minOccurs="0" maxOccurs="unbounded">
               <xsd:complexType>
                 <xsd:sequence>
-                  <xsd:element name="MemberOrdinal" type="xsd:int" minOccurs="1" maxOccurs="1"/>
-                  <xsd:element name="MemberDispInfo" type="xsd:int" minOccurs="0" maxOccurs="1"/>
+                  <xsd:element name="MemberOrdinal" type="xsd:int"
+                               minOccurs="1" maxOccurs="1"/>
+                  <xsd:element name="MemberDispInfo" type="xsd:int"
+                               minOccurs="0" maxOccurs="1"/>
                 </xsd:sequence>
               </xsd:complexType>
             </xsd:element>

```

*Matching: 0, Different: 2, Missing: 0, Extra: 0*

### xmla-ds.xsd

#### Missing from definition (2)

- `element` **LastSchemaUpdate** (spec: Messages > Common Message Syntax > Complex Types > Return Value Complex Types >
  xmla-ds:mddataset Complex Type > xmla-ds:OlapInfo Complex Type > xmla-ds:CubeInfo > OlapInfoCube)
- `element` **LastDataUpdate** (spec: Messages > Common Message Syntax > Complex Types > Return Value Complex Types >
  xmla-ds:mddataset Complex Type > xmla-ds:OlapInfo Complex Type > xmla-ds:CubeInfo > OlapInfoCube)

#### Types with differences (27)

##### `complexType` **mddataset**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type

```diff
--- definition/+++ spec/@@ -1,10 +1,10 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla"
xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="mddataset">
+<xsd:complexType name="mddataset" >
     <xsd:sequence>
-      <xsd:element ref="xsd:schema" minOccurs="0"/>
-      <xsd:element name="OlapInfo" minOccurs="0" type="OlapInfo"/>
-      <xsd:element name="Axes" minOccurs="0" type="Axes"/>
-      <xsd:element name="CellData" minOccurs="0" type="CellData"/>
-      <xsd:element name="Exception" minOccurs="0" type="ana-x:Exception"/>
-      <xsd:element name="Messages" minOccurs="0" type="ana-x:Messages"/>
+      <xsd:element ref="xs:Schema" />
+      <xsd:element name="OlapInfo" minOccurs="0" type="OlapInfo" />
+      <xsd:element name="Axes" minOccurs="0" type="Axes" />
+      <xsd:element name="CellData" minOccurs="0" type="CellData" />
+      <xsd:element name="Exception" minOccurs="0" type="Exception" />
+      <xsd:element name="Messages" minOccurs="0" type="Messages" />
     </xsd:sequence>
   </xsd:complexType>
```

##### `complexType` **OlapInfo**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:OlapInfo Complex Type

```diff
--- definition/+++ spec/@@ -1,7 +1,7 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla"
xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="OlapInfo">
+<xsd:complexType name="OlapInfo">
     <xsd:sequence>
-      <xsd:element name="CubeInfo" type="CubeInfo"/>
-      <xsd:element name="AxesInfo" type="AxesInfo"/>
-      <xsd:element name="CellInfo" type="CellInfo"/>
+      <xsd:element name="CubeInfo" type="CubeInfo" />
+      <xsd:element name="AxesInfo" type="AxesInfo" />
+      <xsd:element name="CellInfo" type="CellInfo" />
     </xsd:sequence>
   </xsd:complexType>
```

##### `complexType` **CubeInfo**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:OlapInfo Complex Type > xmla-ds:CubeInfo

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla"
xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="CubeInfo">
-    <xsd:sequence>
-      <xsd:element name="Cube" maxOccurs="unbounded" type="OlapInfoCube"/>
-    </xsd:sequence>
-  </xsd:complexType>+<xsd:complexType name="CubeInfo">
+      <xsd:sequence>
+        <xsd:element name="Cube" maxOccurs="unbounded" type="OlapInfoCube" />
+      </xsd:sequence>
+    </xsd:complexType>
```

##### `complexType` **OlapInfoCube**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:OlapInfo Complex Type > xmla-ds:CubeInfo > OlapInfoCube

```diff
--- definition/+++ spec/@@ -1,7 +1,7 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla"
xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="OlapInfoCube">
+<xsd:complexType name="OlapInfoCube" >
     <xsd:sequence>
-      <xsd:element name="CubeName" type="xsd:string"/>
-      <xsd:element ref="eng:LastDataUpdate" minOccurs="0"/>
-      <xsd:element ref="eng:LastSchemaUpdate" minOccurs="0"/>
+      <xsd:element name="CubeName" type="xsd:string" />
+      <xsd:element ref="eng:LastDataUpdate" minOccurs="0" />
+      <xsd:element ref="eng:LastSchemaUpdate" minOccurs="0" />
     </xsd:sequence>
   </xsd:complexType>
```

##### `complexType` **AxesInfo**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:OlapInfo Complex Type > xmla-ds:AxesInfo

```diff
--- definition/+++ spec/@@ -1,5 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla"
xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="AxesInfo">
+<xsd:complexType name="AxesInfo">
     <xsd:sequence>
-      <xsd:element name="AxisInfo" maxOccurs="unbounded" type="AxisInfo"/>
+      <xsd:element name="AxisInfo" maxOccurs="unbounded" type="AxisInfo">
+      </xsd:element>
     </xsd:sequence>
   </xsd:complexType>
```

##### `complexType` **AxisInfo**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:OlapInfo Complex Type > xmla-ds:AxesInfo > xmla-ds:AxisInfo Complex Type

```diff
--- definition/+++ spec/@@ -1,6 +1,7 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla"
xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="AxisInfo">
+<xsd:complexType name="AxisInfo">
     <xsd:sequence>
-      <xsd:element name="HierarchyInfo" type="HierarchyInfo" minOccurs="0" maxOccurs="unbounded"/>
+      <xsd:element name="HierarchyInfo" type="HierarchyInfo"
+                   minOccurs="0" maxOccurs="unbounded" />
     </xsd:sequence>
-    <xsd:attribute name="name" type="xsd:string"/>
+    <xsd:attribute name="name" type="xsd:string" />
   </xsd:complexType>
```

##### `complexType` **HierarchyInfo**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:OlapInfo Complex Type > xmla-ds:AxesInfo > xmla-ds:AxisInfo Complex Type > xmla-ds:HierarchyInfo Complex
Type

```diff
--- definition/+++ spec/@@ -1,6 +1,7 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb"
xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="HierarchyInfo">
+<xsd:complexType name="HierarchyInfo">
     <xsd:sequence>
-      <xsd:any namespace="##targetNamespace" minOccurs="0" maxOccurs="unbounded" processContents="skip"/>
+      <xsd:any namespace="##targetNamespace" minOccurs="0"
+               maxOccurs="unbounded" processContents="skip" />
     </xsd:sequence>
-    <xsd:attribute name="name" type="xsd:string" use="required"/>
+    <xsd:attribute name="name" type="xsd:string" use="required" />
   </xsd:complexType>
```

##### `complexType` **CellInfo**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:OlapInfo Complex Type > xmla-ds:CellInfo Complex Type

```diff
--- definition/+++ spec/@@ -1,5 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb"
xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="CellInfo">
-    <xsd:sequence>
-      <xsd:any namespace="##targetNamespace" minOccurs="0" maxOccurs="unbounded" processContents="skip"/>
-    </xsd:sequence>
+<xsd:complexType name="CellInfo">
+      <xsd:sequence>
+        <xsd:any namespace="##targetNamespace" minOccurs="0"
+                 maxOccurs="unbounded" processContents="skip" />
+      </xsd:sequence>
   </xsd:complexType>
```

##### `complexType` **Axes**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb"
xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="Axes">
+<xsd:complexType name="Axes">
     <xsd:sequence>
-      <xsd:element name="Axis" type="Axis" maxOccurs="unbounded"/>
+      <xsd:element name="Axis" type="Axis" maxOccurs="unbounded" />
     </xsd:sequence>
   </xsd:complexType>
```

##### `group` **SetType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > SetType Model Group

```diff
--- definition/+++ spec/@@ -1,13 +1,13 @@-<xsd:group xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb"
xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="SetType">
-    <xsd:choice>
-      <xsd:element name="Members" type="MembersType"/>
-      <xsd:element name="Tuples" type="TuplesType"/>
-      <xsd:element name="CrossProduct" type="SetListType"/>
-      <xsd:element ref="msxmla:NormTupleSet"/>
-      <xsd:element name="Union">
-        <xsd:complexType>
-          <xsd:group ref="SetType" minOccurs="0" maxOccurs="unbounded"/>
-        </xsd:complexType>
-      </xsd:element>
-    </xsd:choice>
-  </xsd:group>+<xsd:group name="SetType">
+  <xsd:choice>
+    <xsd:element name="Members" type="MembersType" />
+    <xsd:element name="Tuples" type="TuplesType" />
+    <xsd:element name="CrossProduct" type="SetListType" />
+          <xsd:element ref="msxmla:NormTupleSet" />
+    <xsd:element name="Union">
+      <xsd:complexType>
+        <xsd:group ref="SetType" minOccurs="0" maxOccurs="unbounded" />
+      </xsd:complexType>
+    </xsd:element>
+  </xsd:choice>
+</xsd:group>
```

##### `complexType` **MembersType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > SetType Model Group > MembersType Complex Type

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb"
xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="MembersType">
-    <xsd:sequence>
-      <xsd:element name="Member" type="MemberType" minOccurs="0" maxOccurs="unbounded"/>
-    </xsd:sequence>
-    <xsd:attribute name="Hierarchy" type="xsd:string" use="required"/>
-  </xsd:complexType>+<xsd:complexType name="MembersType">
+  <xsd:sequence>
+    <xsd:element name="Member" type="MemberType" minOccurs="0" maxOccurs="unbounded" />
+  </xsd:sequence>
+  <xsd:attribute name="Hierarchy" type="xsd:string" use="required" />
+</xsd:complexType>
```

##### `complexType` **MemberType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > SetType Model Group > MemberType Complex Type

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb"
xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="MemberType">
-    <xsd:sequence>
-      <xsd:any namespace="##targetNamespace" minOccurs="0" maxOccurs="unbounded" processContents="skip"/>
-    </xsd:sequence>
-    <xsd:attribute name="Hierarchy" type="xsd:string"/>
-  </xsd:complexType>+<xsd:complexType name="MemberType">
+  <xsd:sequence>
+    <xsd:any namespace="##targetNamespace" minOccurs="0" maxOccurs="unbounded" processContents="skip" />
+  </xsd:sequence>
+  <xsd:attribute name="Hierarchy" type="xsd:string" />
+</xsd:complexType>
```

##### `complexType` **TuplesType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > SetType Model Group > TupleType Complex Type

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla"
xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="TuplesType">
-    <xsd:sequence>
-      <xsd:element name="Tuple" type="TupleType" minOccurs="0" maxOccurs="unbounded"/>
-    </xsd:sequence>
-  </xsd:complexType>+<xsd:complexType name="TuplesType">
+  <xsd:sequence>
+    <xsd:element name="Tuple" type="TupleType" minOccurs="0" maxOccurs="unbounded" />
+  </xsd:sequence>
+</xsd:complexType>
```

##### `complexType` **TupleType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > SetType Model Group > TupleType Complex Type

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb"
xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="TupleType">
-    <xsd:sequence>
-      <xsd:element name="Member" type="MemberType" maxOccurs="unbounded"/>
-    </xsd:sequence>
-  </xsd:complexType>+<xsd:complexType name="TupleType">
+  <xsd:sequence>
+    <xsd:element name="Member" type="MemberType" maxOccurs="unbounded" />
+  </xsd:sequence>
+</xsd:complexType>
```

##### `complexType` **SetListType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > SetType Model Group > SetListType Complex Type

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb"
xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="SetListType">
-    <xsd:group ref="SetType" minOccurs="0" maxOccurs="unbounded"/>
-    <xsd:attribute name="Size" type="xsd:unsignedInt"/>
-  </xsd:complexType>+<xsd:complexType name="SetListType">
+  <xsd:group ref="SetType" minOccurs="0" maxOccurs="unbounded" />
+  <xsd:attribute name="Size" type="xsd:unsignedInt" />
+</xsd:complexType>
```

##### `element` **Members**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > NormType Model Group

```diff
--- definition/+++ spec/@@ -1 +1 @@-<xsd:element xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb"
xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla"
xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine"
name="Members" type="TupleType"/>+<xsd:element name="Members" type="TupleType"/>
```

##### `group` **NormType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > NormType Model Group

```diff
--- definition/+++ spec/@@ -1,8 +1,9 @@-<xsd:group xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla"
xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="NormType">
-    <xsd:sequence>
-      <xsd:element name="MetadataSet" type="MetadatasType" minOccurs="1"/>
-      <xsd:element name="TupleSet" type="TuplesNormType" minOccurs="1"/>
-      <xsd:element name="KeySet" type="KeysNormType" minOccurs="1"/>
-      <xsd:element name="MeasureFormatStringSet" type="MeasureFormatStringsNormType" minOccurs="0"/>
-    </xsd:sequence>
-  </xsd:group>+<xsd:group name="NormType">
+  <xsd:sequence>
+    <xsd:element name="MetadataSet" type="MetadatasType" minOccurs="1" />
+    <xsd:element name="TupleSet" type="TuplesNormType" minOccurs="1" />
+    <xsd:element name="KeySet" type="KeysNormType" minOccurs="1" />
+    <xsd:element name="MeasureFormatStringSet" type="MeasureFormatStringsNormType"
+                 minOccurs="0" />
+  </xsd:sequence>
+</xsd:group>
```

##### `complexType` **MetadatasType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > NormType Model Group > MetadataType Complex Type

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
    xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="MetadatasType">
-    <xsd:sequence>
-      <xsd:element name="Metadata" type="MetadataType" minOccurs="0" maxOccurs="unbounded"/>
-    </xsd:sequence>
-  </xsd:complexType>+<xsd:complexType name="MetadatasType">
+  <xsd:sequence>
+    <xsd:element name="Metadata" type="MetadataType" minOccurs="0" maxOccurs="unbounded" />
+  </xsd:sequence>
+</xsd:complexType>
```

##### `complexType` **MetadataType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > NormType Model Group > MetadataType Complex Type

```diff
--- definition/+++ spec/@@ -1,10 +1,10 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
    xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="MetadataType">
-    <xsd:sequence>
-      <xsd:element name="HierUName" type="xsd:string" minOccurs="1" maxOccurs="1"/>
-      <xsd:element name="HierFlags" type="xsd:int" minOccurs="1" maxOccurs="1"/>
-      <xsd:element name="AllMemberName" type="xsd:string" minOccurs="1" maxOccurs="1"/>
-      <xsd:element name="L#UName" type="xsd:string" minOccurs="1" maxOccurs="2"/>
-      <xsd:element name="MemberFormatString" type="xsd:string" minOccurs="0" maxOccurs="1"/>
-      <xsd:element name="DLValType" type="xsd:int" minOccurs="1" maxOccurs="1"/>
-    </xsd:sequence>
-  </xsd:complexType>+<xsd:complexType name="MetadataType">
+  <xsd:sequence>
+    <xsd:element name="HierUName" type="xsd:string" minOccurs="1" maxOccurs="1" />
+    <xsd:element name="HierFlags" type="xsd:int" minOccurs="1" maxOccurs="1" />
+    <xsd:element name="AllMemberName" type="xsd:string" minOccurs="1" maxOccurs="1" />
+    <xsd:element name="L#UName" type="xsd:string" minOccurs="1" maxOccurs="2" />
+    <xsd:element name="MemberFormatString" type="xsd:string" minOccurs="0" maxOccurs="1" />
+    <xsd:element name="DLValType" type="xsd:int" minOccurs="1" maxOccurs="1" />
+  </xsd:sequence>
+</xsd:complexType>
```

##### `complexType` **TuplesNormType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > NormType Model Group > TuplesNormType Complex Type

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
    xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="TuplesNormType">
-    <xsd:sequence>
-      <xsd:element name="TupleCount" type="xsd:integer" minOccurs="1" maxOccurs="1"/>
-      <xsd:element name="Data" type="xsd:base64Binary" minOccurs="1" maxOccurs="1"/>
-    </xsd:sequence>
-  </xsd:complexType>+<xsd:complexType name="TuplesNormType">
+  <xsd:sequence>
+    <xsd:element name="TupleCount" type="xsd:integer" minOccurs="1" maxOccurs="1" />
+    <xsd:element name="Data" type="xsd:base64Binary" minOccurs="1" maxOccurs="1" />
+  </xsd:sequence>
+</xsd:complexType>
```

##### `complexType` **KeysNormType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > NormType Model Group > KeyNormType Complex Type

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
    xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="KeysNormType">
-    <xsd:sequence>
-      <xsd:element name="KeySet" type="KeyNormType" minOccurs="1" maxOccurs="unbounded"/>
-    </xsd:sequence>
-  </xsd:complexType>+<xsd:complexType name="KeysNormType">
+  <xsd:sequence>
+    <xsd:element name="KeySet" type="KeyNormType" minOccurs="1" maxOccurs="unbounded" />
+  </xsd:sequence>
+</xsd:complexType>
```

##### `complexType` **KeyNormType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > NormType Model Group > KeyNormType Complex Type

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
    xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="KeyNormType">
-    <xsd:sequence>
-      <xsd:element name="DistinctCountMember" type="xsd:string" minOccurs="1" maxOccurs="1"/>
-      <xsd:element name="Data" type="xsd:base64Binary" minOccurs="0" maxOccurs="unbounded"/>
-    </xsd:sequence>
-  </xsd:complexType>+<xsd:complexType name="KeyNormType">
+  <xsd:sequence>
+    <xsd:element name="DistinctCountMember" type="xsd:string" minOccurs="1" maxOccurs="1" />
+    <xsd:element name="Data" type="xsd:base64Binary" minOccurs="0" maxOccurs="unbounded" />
+  </xsd:sequence>
+</xsd:complexType>
```

##### `complexType` **MeasureFormatStringsNormType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > NormType Model Group > MeasureFormatStringNormType Complex Type

```diff
--- definition/+++ spec/@@ -1,5 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
    xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="MeasureFormatStringsNormType">
-    <xsd:sequence>
-      <xsd:element name="MeasureFormatStringSet" type="MeasureFormatStringNormType" minOccurs="0" maxOccurs="unbounded"/>
-    </xsd:sequence>
-  </xsd:complexType>+<xsd:complexType name="MeasureFormatStringsNormType">
+  <xsd:sequence>
+    <xsd:element name="MeasureFormatStringSet" type="MeasureFormatStringNormType"
+                 minOccurs="0" maxOccurs="unbounded" />
+  </xsd:sequence>
+</xsd:complexType>
```

##### `complexType` **MeasureFormatStringNormType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:Axes Complex Type > Axis > NormType Model Group > MeasureFormatStringNormType Complex Type

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
    xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="MeasureFormatStringNormType">
-    <xsd:sequence>
-      <xsd:element name="DistinctMemberCount" type="xsd:integer" minOccurs="1" maxOccurs="1"/>
-      <xsd:element name="Data" type="xsd:base64Binary" minOccurs="1" maxOccurs="unbounded"/>
-    </xsd:sequence>
-  </xsd:complexType>+<xsd:complexType name="MeasureFormatStringNormType">
+  <xsd:sequence>
+    <xsd:element name="DistinctMemberCount" type="xsd:integer" minOccurs="1" maxOccurs="1" />
+    <xsd:element name="Data" type="xsd:base64Binary" minOccurs="1" maxOccurs="unbounded" />
+  </xsd:sequence>
+</xsd:complexType>
```

##### `complexType` **CellData**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:CellData Complex Type

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
    xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="CellData">
-    <xsd:sequence>
-      <xsd:element name="Cell" type="CellType" minOccurs="0" maxOccurs="unbounded"/>
-      <xsd:element name="CellSet" type="CellSetType" minOccurs="0" maxOccurs="1"/>
-    </xsd:sequence>
-  </xsd:complexType>+<xsd:complexType name="CellData">
+  <xsd:sequence>
+     <xsd:element name="Cell" type="CellType" minOccurs="0" maxOccurs="unbounded" />
+     <xsd:element name="CellSet" type="CellSetType" minOccurs="0" maxOccurs="1" />
+  </xsd:sequence>
+</xsd:complexType>
```

##### `complexType` **CellType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:CellData Complex Type > xmla-ds:CellType ComplexType

```diff
--- definition/+++ spec/@@ -1,13 +1,18 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
    xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="CellType">
-    <xsd:sequence>
-      <xsd:element name="Value" minOccurs="0" maxOccurs="1">
-        <xsd:complexType>
-          <xsd:sequence>
-            <xsd:element name="Error" type="CellTypeError" minOccurs="0" maxOccurs="unbounded"/>
-          </xsd:sequence>
-        </xsd:complexType>
-      </xsd:element>
-      <xsd:any namespace="##targetNamespace" minOccurs="0" maxOccurs="unbounded" processContents="skip"/>
-    </xsd:sequence>
-    <xsd:attribute name="CellOrdinal" type="xsd:unsignedInt" use="required"/>
-  </xsd:complexType>+<xsd:complexType name="CellType">
+  <xsd:sequence>
+    <xsd:element name="Value" minOccurs="0" maxOccurs="1">
+      <xsd:complexType>
+        <xsd:sequence>
+          <xsd:element name="Error" minOccurs="0" maxOccurs="unbounded">
+            <xsd:element name="ErrorCode" minOccurs="0" maxOccurs="1" />
+            <xsd:element name="Description" minOccurs="0" maxOccurs="1" />
+            <xsd:any namespace="##targetNamespace" minOccurs="0" maxOccurs="unbounded" />
+          </xsd:element>
+        </xsd:sequence>
+      </xsd:complexType>
+    </xsd:element>
+    <xsd:any namespace="##targetNamespace" minOccurs="0"
+             maxOccurs="unbounded" processContents="skip" />
+  </xsd:sequence>
+  <xsd:attribute name="CellOrdinal" type="xsd:unsignedInt" use="required" />
+</xsd:complexType>
```

##### `complexType` **CellSetType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-ds:mddataset Complex
Type > xmla-ds:CellData Complex Type > CellSetType ComplexType

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception"
    xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" name="CellSetType">
-    <xsd:sequence>
-      <xsd:element name="Data" type="xsd:base64Binary" minOccurs="1" maxOccurs="unbounded"/>
-    </xsd:sequence>
-  </xsd:complexType>+<xsd:complexType name="CellSetType">
+  <xsd:sequence>
+    <xsd:element name="Data" type="xsd:base64Binary" minOccurs="1" maxOccurs="unbounded"/>
+  </xsd:sequence>
+</xsd:complexType>
```

#### Extra in definition (not in spec) (3)

- `complexType` **Axis**
- `complexType` **CellTypeError**
- `element` **root**

*Matching: 0, Different: 27, Missing: 2, Extra: 3*

### xmla-e.xsd

#### Types with differences (1)

##### `complexType` **emptyresult**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-e:emptyresult
Complex Type

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" name="emptyresult">
+<xsd:complexType name="emptyresult" >
     <xsd:sequence>
-      <xsd:element name="Exception" minOccurs="0" type="ana-x:Exception"/>
-      <xsd:element name="Messages" minOccurs="0" type="ana-x:Messages"/>
+      <xsd:element name="Exception" minOccurs="0" type="Exception" />
+      <xsd:element name="Messages" minOccurs="0" type="Messages" />
     </xsd:sequence>
   </xsd:complexType>
```

#### Extra in definition (not in spec) (1)

- `element` **root**

*Matching: 0, Different: 1, Missing: 0, Extra: 1*

### xmla-m.xsd

#### Missing from definition (1)

- `complexType` **results** (spec: Messages > Common Message Syntax > Complex Types > Return Value Complex Types >
  xmla-m:results Complex Type)

#### Extra in definition (not in spec) (1)

- `element` **results**

*Matching: 0, Different: 0, Missing: 1, Extra: 1*

### xmla-rs.xsd

#### Types with differences (2)

##### `complexType` **rowset**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-rs:rowset Complex
Type

```diff
--- definition/+++ spec/@@ -1,8 +1,8 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="rowset">
+<xsd:complexType name="rowset" >
     <xsd:sequence>
-      <xsd:element ref="xsd:schema" minOccurs="0"/>
-      <xsd:element name="row" minOccurs="0" maxOccurs="unbounded" type="row"/>
-      <xsd:element name="Exception" minOccurs="0" type="ana-x:Exception"/>
-      <xsd:element name="Messages" minOccurs="0" type="ana-x:Messages"/>
+      <xsd:element ref="xs:Schema" minOccurs="0" />
+      <xsd:element name="row" minOccurs="0" maxOccurs="unbounded" type="row" />
+      <xsd:element name="Exception" minOccurs="0" type="Exception" />
+      <xsd:element name="Messages" minOccurs="0" type="Messages" />
     </xsd:sequence>
   </xsd:complexType>
```

##### `complexType` **row**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > xmla-rs:rowset Complex
Type > xmla-rs:row Complex Type

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="row">
+<xsd:complexType name="row">
     <xsd:sequence>
-      <xsd:any namespace="##any" minOccurs="0" maxOccurs="unbounded"/>
+      <xsd:any namespace="##any" minOccurs="0" maxOccurs ="unbounded" />
     </xsd:sequence>
   </xsd:complexType>
```

#### Extra in definition (not in spec) (1)

- `element` **root**

*Matching: 0, Different: 2, Missing: 0, Extra: 1*

### xmla-x.xsd

#### Missing from definition (3)

- `element` **WarningColumn** (spec: Messages > Common Message Syntax > Complex Types > Return Value Complex Types >
  Error and Warning Complex Types > xmla-x:Messages > MessageLocation)
- `element` **WarningMeasure** (spec: Messages > Common Message Syntax > Complex Types > Return Value Complex Types >
  Error and Warning Complex Types > xmla-x:Messages > MessageLocation)
- `complexType` **WarningLocationObject** (spec: Messages > Common Message Syntax > Complex Types > Return Value Complex
  Types > Error and Warning Complex Types > xmla-x:Messages > MessageLocation)

#### Types with differences (5)

##### `complexType` **Exception**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > Error and Warning Complex
Types > xmla-x:Messages

```diff
--- definition/+++ spec/@@ -1,3 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" name="Exception">
-    <xsd:sequence/>
+<xsd:complexType name="Exception">
+    <xsd:sequence>
+    </xsd:sequence>
   </xsd:complexType>
```

##### `complexType` **Messages**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > Error and Warning Complex
Types > xmla-x:Messages

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" name="Messages">
+<xsd:complexType name="Messages">
     <xsd:choice minOccurs="1" maxOccurs="unbounded">
-      <xsd:element name="Warning" type="WarningType"/>
-      <xsd:element name="Error" type="ErrorType"/>
+      <xsd:element name="Warning" type="WarningType" />
+      <xsd:element name="Error" type="ErrorType" />
     </xsd:choice>
   </xsd:complexType>
```

##### `complexType` **WarningType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > Error and Warning Complex
Types > xmla-x:Messages > WarningType

```diff
--- definition/+++ spec/@@ -1,9 +1,9 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" name="WarningType">
+<xsd:complexType name="WarningType">
     <xsd:all>
-      <xsd:element name="Location" type="MessageLocation" minOccurs="0"/>
+      <xsd:element name="Location" type="MessageLocation" minOccurs="0" />
     </xsd:all>
-    <xsd:attribute name="WarningCode" type="xsd:int"/>
-    <xsd:attribute name="Description" type="xsd:string"/>
-    <xsd:attribute name="Source" type="xsd:string"/>
-    <xsd:attribute name="HelpFile" type="xsd:string"/>
+    <xsd:attribute name="WarningCode" type="xsd:int" />
+    <xsd:attribute name="Description" type="xsd:string" />
+    <xsd:attribute name="Source" type="xsd:string" />
+    <xsd:attribute name="HelpFile" type="xsd:string" />
   </xsd:complexType>
```

##### `complexType` **ErrorType**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > Error and Warning Complex
Types > xmla-x:Messages > ErrorType

```diff
--- definition/+++ spec/@@ -1,10 +1,10 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" name="ErrorType">
+<xsd:complexType name="ErrorType">
     <xsd:all>
-      <xsd:element name="Location" type="MessageLocation" minOccurs="0"/>
-      <xsd:element name="Callstack" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="Location" type="MessageLocation" minOccurs="0" />
+      <xsd:element name="Callstack" type="xsd:string" minOccurs="0" />
     </xsd:all>
-    <xsd:attribute name="ErrorCode" type="xsd:long"/>
-    <xsd:attribute name="Description" type="xsd:string"/>
-    <xsd:attribute name="Source" type="xsd:string"/>
-    <xsd:attribute name="HelpFile" type="xsd:string"/>
+    <xsd:attribute name="ErrorCode" type="xsd:long" />
+    <xsd:attribute name="Description" type="xsd:string" />
+    <xsd:attribute name="Source" type="xsd:string" />
+    <xsd:attribute name="HelpFile" type="xsd:string" />
   </xsd:complexType>
```

##### `complexType` **MessageLocation**

Spec location: Messages > Common Message Syntax > Complex Types > Return Value Complex Types > Error and Warning Complex
Types > xmla-x:Messages > MessageLocation

```diff
--- definition/+++ spec/@@ -1,25 +1,27 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis:exception" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" name="MessageLocation">
+<xsd:complexType name="MessageLocation">
     <xsd:all>
-      <xsd:element name="Start">
+      <xsd:element name="Start" >
         <xsd:complexType>
           <xsd:all>
-            <xsd:element name="Line" type="xsd:int"/>
-            <xsd:element name="Column" type="xsd:int"/>
+            <xsd:element name="Line" type="xsd:int" />
+            <xsd:element name="Column" type="xsd:int" />
           </xsd:all>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="End">
+      <xsd:element name="End" >
         <xsd:complexType>
           <xsd:all>
-            <xsd:element name="Line" type="xsd:int"/>
-            <xsd:element name="Column" type="xsd:int"/>
+            <xsd:element name="Line" type="xsd:int" />
+            <xsd:element name="Column" type="xsd:int" />
           </xsd:all>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="LineOffset" type="xsd:int" minOccurs="0" maxOccurs="1"/>
-      <xsd:element name="TextLength" type="xsd:int" minOccurs="0" maxOccurs="1"/>
-      <xsd:element name="SourceObject" type="eng200:WarningLocationObject" minOccurs="0" maxOccurs="1"/>
-      <xsd:element name="DependsOnObject" type="eng200:WarningLocationObject" minOccurs="0" maxOccurs="1"/>
-      <xsd:element name="RowNumber" type="xsd:int" minOccurs="0" maxOccurs="1"/>
+      <xsd:element name ="LineOffset" type="xsd:int" minOccurs="0" maxOccurs="1"/>
+      <xsd:element name ="TextLength" type="xsd:int" minOccurs="0" maxOccurs="1"/>
+      <xsd:element name ="SourceObject" type="eng200:WarningLocationObject" minOccurs="0"
+                   maxOccurs="1"/>
+      <xsd:element name ="DependsOnObject" type="eng200:WarningLocationObject" minOccurs="0"
+                   maxOccurs="1"/>
+      <xsd:element name ="RowNumber" type="xsd:int" minOccurs="0" maxOccurs="1"/>
     </xsd:all>
   </xsd:complexType>
```

*Matching: 0, Different: 5, Missing: 3, Extra: 0*

### xmla.wsdl

#### Types with differences (9)

##### `wsdl:operation` **Authenticate**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Authenticate >
Messages

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<wsdl:operation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns="urn:daanse-eclipse-org:xmla-ws" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ana="urn:schemas-microsoft-com:xml-analysis" xmlns:ext="http://schemas.microsoft.com/analysisservices/2003/ext"
    xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng2_2="http://schemas.microsoft.com/analysisservices/2003/engine/2/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng400_400="http://schemas.microsoft.com/analysisservices/2012/engine/400/400" xmlns:eng500="http://schemas.microsoft.com/analysisservices/2013/engine/500"
    xmlns:eng500_500="http://schemas.microsoft.com/analysisservices/2013/engine/500/500" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng600_600="http://schemas.microsoft.com/analysisservices/2013/engine/600/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800"
    xmlns:eng800_800="http://schemas.microsoft.com/analysisservices/2013/engine/800/800" xmlns:eng900="http://schemas.microsoft.com/analysisservices/2022/engine/900" xmlns:eng900_900="http://schemas.microsoft.com/analysisservices/2022/engine/900/900" xmlns:eng910="http://schemas.microsoft.com/analysisservices/2022/engine/910"
    xmlns:eng910_910="http://schemas.microsoft.com/analysisservices/2022/engine/910/910" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920" xmlns:eng920_920="http://schemas.microsoft.com/analysisservices/2022/engine/920/920" xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921"
    xmlns:eng921_921="http://schemas.microsoft.com/analysisservices/2022/engine/921/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng922_922="http://schemas.microsoft.com/analysisservices/2022/engine/922/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923"
    xmlns:eng923_923="http://schemas.microsoft.com/analysisservices/2025/engine/923/923" xmlns:eng924="http://schemas.microsoft.com/analysisservices/2025/engine/924" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:eng925_925="http://schemas.microsoft.com/analysisservices/2025/engine/925/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset"
    xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" name="Authenticate">
-      <wsdl:input message="AuthenticateSoapIn"/>
-      <wsdl:output message="AuthenticateSoapOut"/>
-    </wsdl:operation>+<wsdl:operation name="Authenticate">
+  <wsdl:input message="AuthenticateSoapIn" />
+  <wsdl:output message="AuthenticateSoapOut" />
+</wsdl:operation>
```

##### `wsdl:message` **AuthenticateSoapIn**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Authenticate >
Messages > AuthenticateSoapOut

```diff
--- definition/+++ spec/@@ -1,3 +1,3 @@-<wsdl:message xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns="urn:daanse-eclipse-org:xmla-ws" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ana="urn:schemas-microsoft-com:xml-analysis" xmlns:ext="http://schemas.microsoft.com/analysisservices/2003/ext"
    xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng2_2="http://schemas.microsoft.com/analysisservices/2003/engine/2/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng400_400="http://schemas.microsoft.com/analysisservices/2012/engine/400/400" xmlns:eng500="http://schemas.microsoft.com/analysisservices/2013/engine/500"
    xmlns:eng500_500="http://schemas.microsoft.com/analysisservices/2013/engine/500/500" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng600_600="http://schemas.microsoft.com/analysisservices/2013/engine/600/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800"
    xmlns:eng800_800="http://schemas.microsoft.com/analysisservices/2013/engine/800/800" xmlns:eng900="http://schemas.microsoft.com/analysisservices/2022/engine/900" xmlns:eng900_900="http://schemas.microsoft.com/analysisservices/2022/engine/900/900" xmlns:eng910="http://schemas.microsoft.com/analysisservices/2022/engine/910"
    xmlns:eng910_910="http://schemas.microsoft.com/analysisservices/2022/engine/910/910" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920" xmlns:eng920_920="http://schemas.microsoft.com/analysisservices/2022/engine/920/920" xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921"
    xmlns:eng921_921="http://schemas.microsoft.com/analysisservices/2022/engine/921/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng922_922="http://schemas.microsoft.com/analysisservices/2022/engine/922/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923"
    xmlns:eng923_923="http://schemas.microsoft.com/analysisservices/2025/engine/923/923" xmlns:eng924="http://schemas.microsoft.com/analysisservices/2025/engine/924" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:eng925_925="http://schemas.microsoft.com/analysisservices/2025/engine/925/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset"
    xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" name="AuthenticateSoapIn">
-    <wsdl:part name="parameters" element="ext:Authenticate"/>
-  </wsdl:message>+<message name="AuthenticateSoapIn">
+  <part name="parameters" element="xmla:Authenticate" />
+</message>
```

##### `wsdl:message` **AuthenticateSoapOut**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Authenticate >
Elements

```diff
--- definition/+++ spec/@@ -1,3 +1,3 @@-<wsdl:message xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns="urn:daanse-eclipse-org:xmla-ws" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ana="urn:schemas-microsoft-com:xml-analysis" xmlns:ext="http://schemas.microsoft.com/analysisservices/2003/ext"
    xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng2_2="http://schemas.microsoft.com/analysisservices/2003/engine/2/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng400_400="http://schemas.microsoft.com/analysisservices/2012/engine/400/400" xmlns:eng500="http://schemas.microsoft.com/analysisservices/2013/engine/500"
    xmlns:eng500_500="http://schemas.microsoft.com/analysisservices/2013/engine/500/500" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng600_600="http://schemas.microsoft.com/analysisservices/2013/engine/600/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800"
    xmlns:eng800_800="http://schemas.microsoft.com/analysisservices/2013/engine/800/800" xmlns:eng900="http://schemas.microsoft.com/analysisservices/2022/engine/900" xmlns:eng900_900="http://schemas.microsoft.com/analysisservices/2022/engine/900/900" xmlns:eng910="http://schemas.microsoft.com/analysisservices/2022/engine/910"
    xmlns:eng910_910="http://schemas.microsoft.com/analysisservices/2022/engine/910/910" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920" xmlns:eng920_920="http://schemas.microsoft.com/analysisservices/2022/engine/920/920" xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921"
    xmlns:eng921_921="http://schemas.microsoft.com/analysisservices/2022/engine/921/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng922_922="http://schemas.microsoft.com/analysisservices/2022/engine/922/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923"
    xmlns:eng923_923="http://schemas.microsoft.com/analysisservices/2025/engine/923/923" xmlns:eng924="http://schemas.microsoft.com/analysisservices/2025/engine/924" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:eng925_925="http://schemas.microsoft.com/analysisservices/2025/engine/925/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset"
    xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" name="AuthenticateSoapOut">
-    <wsdl:part name="parameters" element="ext:AuthenticateResponse"/>
-  </wsdl:message>+<message name="AuthenticateSoapOut">
+  <part name="parameters" element="xmla:AuthenticateResponse" />
+</message>
```

##### `wsdl:operation` **Discover**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Discover

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<wsdl:operation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns="urn:daanse-eclipse-org:xmla-ws" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ana="urn:schemas-microsoft-com:xml-analysis" xmlns:ext="http://schemas.microsoft.com/analysisservices/2003/ext"
    xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng2_2="http://schemas.microsoft.com/analysisservices/2003/engine/2/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng400_400="http://schemas.microsoft.com/analysisservices/2012/engine/400/400" xmlns:eng500="http://schemas.microsoft.com/analysisservices/2013/engine/500"
    xmlns:eng500_500="http://schemas.microsoft.com/analysisservices/2013/engine/500/500" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng600_600="http://schemas.microsoft.com/analysisservices/2013/engine/600/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800"
    xmlns:eng800_800="http://schemas.microsoft.com/analysisservices/2013/engine/800/800" xmlns:eng900="http://schemas.microsoft.com/analysisservices/2022/engine/900" xmlns:eng900_900="http://schemas.microsoft.com/analysisservices/2022/engine/900/900" xmlns:eng910="http://schemas.microsoft.com/analysisservices/2022/engine/910"
    xmlns:eng910_910="http://schemas.microsoft.com/analysisservices/2022/engine/910/910" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920" xmlns:eng920_920="http://schemas.microsoft.com/analysisservices/2022/engine/920/920" xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921"
    xmlns:eng921_921="http://schemas.microsoft.com/analysisservices/2022/engine/921/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng922_922="http://schemas.microsoft.com/analysisservices/2022/engine/922/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923"
    xmlns:eng923_923="http://schemas.microsoft.com/analysisservices/2025/engine/923/923" xmlns:eng924="http://schemas.microsoft.com/analysisservices/2025/engine/924" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:eng925_925="http://schemas.microsoft.com/analysisservices/2025/engine/925/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset"
    xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" name="Discover">
-      <wsdl:input message="DiscoverSoapIn" name="DiscoverSoapIn"/>
-      <wsdl:output message="DiscoverSoapOut"/>
-    </wsdl:operation>+<wsdl:operation name="Discover">
+  <wsdl:input message="DiscoverSoapIn" />
+  <wsdl:output message="DiscoverSoapOut" />
+</wsdl:operation>
```

##### `wsdl:message` **DiscoverSoapIn**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Discover >
Messages > DiscoverSoapOut

```diff
--- definition/+++ spec/@@ -1,6 +1,3 @@-<wsdl:message xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns="urn:daanse-eclipse-org:xmla-ws" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ana="urn:schemas-microsoft-com:xml-analysis" xmlns:ext="http://schemas.microsoft.com/analysisservices/2003/ext"
    xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng2_2="http://schemas.microsoft.com/analysisservices/2003/engine/2/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng400_400="http://schemas.microsoft.com/analysisservices/2012/engine/400/400" xmlns:eng500="http://schemas.microsoft.com/analysisservices/2013/engine/500"
    xmlns:eng500_500="http://schemas.microsoft.com/analysisservices/2013/engine/500/500" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng600_600="http://schemas.microsoft.com/analysisservices/2013/engine/600/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800"
    xmlns:eng800_800="http://schemas.microsoft.com/analysisservices/2013/engine/800/800" xmlns:eng900="http://schemas.microsoft.com/analysisservices/2022/engine/900" xmlns:eng900_900="http://schemas.microsoft.com/analysisservices/2022/engine/900/900" xmlns:eng910="http://schemas.microsoft.com/analysisservices/2022/engine/910"
    xmlns:eng910_910="http://schemas.microsoft.com/analysisservices/2022/engine/910/910" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920" xmlns:eng920_920="http://schemas.microsoft.com/analysisservices/2022/engine/920/920" xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921"
    xmlns:eng921_921="http://schemas.microsoft.com/analysisservices/2022/engine/921/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng922_922="http://schemas.microsoft.com/analysisservices/2022/engine/922/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923"
    xmlns:eng923_923="http://schemas.microsoft.com/analysisservices/2025/engine/923/923" xmlns:eng924="http://schemas.microsoft.com/analysisservices/2025/engine/924" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:eng925_925="http://schemas.microsoft.com/analysisservices/2025/engine/925/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset"
    xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" name="DiscoverSoapIn">
-    <wsdl:part name="parameters" element="ana:Discover"/>
-    <wsdl:part name="Session" element="ana:Session"/>
-    <wsdl:part name="BeginSession" element="ana:BeginSession"/>
-    <wsdl:part name="EndSession" element="ana:EndSession"/>
-  </wsdl:message>+<message name="DiscoverSoapIn">
+  <part name="parameters" element="xmla:Discover" />
+</message>
```

##### `wsdl:message` **DiscoverSoapOut**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Discover > Elements

```diff
--- definition/+++ spec/@@ -1,4 +1,3 @@-<wsdl:message xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns="urn:daanse-eclipse-org:xmla-ws" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ana="urn:schemas-microsoft-com:xml-analysis" xmlns:ext="http://schemas.microsoft.com/analysisservices/2003/ext"
    xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng2_2="http://schemas.microsoft.com/analysisservices/2003/engine/2/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng400_400="http://schemas.microsoft.com/analysisservices/2012/engine/400/400" xmlns:eng500="http://schemas.microsoft.com/analysisservices/2013/engine/500"
    xmlns:eng500_500="http://schemas.microsoft.com/analysisservices/2013/engine/500/500" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng600_600="http://schemas.microsoft.com/analysisservices/2013/engine/600/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800"
    xmlns:eng800_800="http://schemas.microsoft.com/analysisservices/2013/engine/800/800" xmlns:eng900="http://schemas.microsoft.com/analysisservices/2022/engine/900" xmlns:eng900_900="http://schemas.microsoft.com/analysisservices/2022/engine/900/900" xmlns:eng910="http://schemas.microsoft.com/analysisservices/2022/engine/910"
    xmlns:eng910_910="http://schemas.microsoft.com/analysisservices/2022/engine/910/910" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920" xmlns:eng920_920="http://schemas.microsoft.com/analysisservices/2022/engine/920/920" xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921"
    xmlns:eng921_921="http://schemas.microsoft.com/analysisservices/2022/engine/921/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng922_922="http://schemas.microsoft.com/analysisservices/2022/engine/922/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923"
    xmlns:eng923_923="http://schemas.microsoft.com/analysisservices/2025/engine/923/923" xmlns:eng924="http://schemas.microsoft.com/analysisservices/2025/engine/924" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:eng925_925="http://schemas.microsoft.com/analysisservices/2025/engine/925/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset"
    xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" name="DiscoverSoapOut">
-    <wsdl:part name="parameters" element="ana:DiscoverResponse"/>
-    <wsdl:part name="Session" element="ana:Session"/>
-  </wsdl:message>+<message name="DiscoverSoapOut">
+  <part name="parameters" element="xmla:DiscoverResponse" />
+</message>
```

##### `wsdl:operation` **Execute**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<wsdl:operation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns="urn:daanse-eclipse-org:xmla-ws" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ana="urn:schemas-microsoft-com:xml-analysis" xmlns:ext="http://schemas.microsoft.com/analysisservices/2003/ext"
    xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng2_2="http://schemas.microsoft.com/analysisservices/2003/engine/2/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng400_400="http://schemas.microsoft.com/analysisservices/2012/engine/400/400" xmlns:eng500="http://schemas.microsoft.com/analysisservices/2013/engine/500"
    xmlns:eng500_500="http://schemas.microsoft.com/analysisservices/2013/engine/500/500" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng600_600="http://schemas.microsoft.com/analysisservices/2013/engine/600/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800"
    xmlns:eng800_800="http://schemas.microsoft.com/analysisservices/2013/engine/800/800" xmlns:eng900="http://schemas.microsoft.com/analysisservices/2022/engine/900" xmlns:eng900_900="http://schemas.microsoft.com/analysisservices/2022/engine/900/900" xmlns:eng910="http://schemas.microsoft.com/analysisservices/2022/engine/910"
    xmlns:eng910_910="http://schemas.microsoft.com/analysisservices/2022/engine/910/910" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920" xmlns:eng920_920="http://schemas.microsoft.com/analysisservices/2022/engine/920/920" xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921"
    xmlns:eng921_921="http://schemas.microsoft.com/analysisservices/2022/engine/921/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng922_922="http://schemas.microsoft.com/analysisservices/2022/engine/922/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923"
    xmlns:eng923_923="http://schemas.microsoft.com/analysisservices/2025/engine/923/923" xmlns:eng924="http://schemas.microsoft.com/analysisservices/2025/engine/924" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:eng925_925="http://schemas.microsoft.com/analysisservices/2025/engine/925/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset"
    xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" name="Execute">
-      <wsdl:input message="ExecuteSoapIn"/>
-      <wsdl:output message="ExecuteSoapOut"/>
-    </wsdl:operation>+<wsdl:operation name="Execute">
+  <wsdl:input message=" ExecuteSoapIn" />
+  <wsdl:output message=" ExecuteSoapOut" />
+</wsdl:operation>
```

##### `wsdl:message` **ExecuteSoapIn**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Messages >
ExecuteSoapOut

```diff
--- definition/+++ spec/@@ -1,6 +1,3 @@-<wsdl:message xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns="urn:daanse-eclipse-org:xmla-ws" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ana="urn:schemas-microsoft-com:xml-analysis" xmlns:ext="http://schemas.microsoft.com/analysisservices/2003/ext"
    xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng2_2="http://schemas.microsoft.com/analysisservices/2003/engine/2/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng400_400="http://schemas.microsoft.com/analysisservices/2012/engine/400/400" xmlns:eng500="http://schemas.microsoft.com/analysisservices/2013/engine/500"
    xmlns:eng500_500="http://schemas.microsoft.com/analysisservices/2013/engine/500/500" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng600_600="http://schemas.microsoft.com/analysisservices/2013/engine/600/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800"
    xmlns:eng800_800="http://schemas.microsoft.com/analysisservices/2013/engine/800/800" xmlns:eng900="http://schemas.microsoft.com/analysisservices/2022/engine/900" xmlns:eng900_900="http://schemas.microsoft.com/analysisservices/2022/engine/900/900" xmlns:eng910="http://schemas.microsoft.com/analysisservices/2022/engine/910"
    xmlns:eng910_910="http://schemas.microsoft.com/analysisservices/2022/engine/910/910" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920" xmlns:eng920_920="http://schemas.microsoft.com/analysisservices/2022/engine/920/920" xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921"
    xmlns:eng921_921="http://schemas.microsoft.com/analysisservices/2022/engine/921/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng922_922="http://schemas.microsoft.com/analysisservices/2022/engine/922/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923"
    xmlns:eng923_923="http://schemas.microsoft.com/analysisservices/2025/engine/923/923" xmlns:eng924="http://schemas.microsoft.com/analysisservices/2025/engine/924" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:eng925_925="http://schemas.microsoft.com/analysisservices/2025/engine/925/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset"
    xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" name="ExecuteSoapIn">
-    <wsdl:part name="parameters" element="ana:Execute"/>
-    <wsdl:part name="Session" element="ana:Session"/>
-    <wsdl:part name="BeginSession" element="ana:BeginSession"/>
-    <wsdl:part name="EndSession" element="ana:EndSession"/>
-  </wsdl:message>+<message name="ExecuteSoapIn">
+  <part name="parameters" element="xmla:Execute" />
+</message>
```

##### `wsdl:message` **ExecuteSoapOut**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements

```diff
--- definition/+++ spec/@@ -1,4 +1,3 @@-<wsdl:message xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns="urn:daanse-eclipse-org:xmla-ws" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ana="urn:schemas-microsoft-com:xml-analysis" xmlns:ext="http://schemas.microsoft.com/analysisservices/2003/ext"
    xmlns:msxmla="http://schemas.microsoft.com/analysisservices/2003/xmla" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng2_2="http://schemas.microsoft.com/analysisservices/2003/engine/2/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng400_400="http://schemas.microsoft.com/analysisservices/2012/engine/400/400" xmlns:eng500="http://schemas.microsoft.com/analysisservices/2013/engine/500"
    xmlns:eng500_500="http://schemas.microsoft.com/analysisservices/2013/engine/500/500" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng600_600="http://schemas.microsoft.com/analysisservices/2013/engine/600/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800"
    xmlns:eng800_800="http://schemas.microsoft.com/analysisservices/2013/engine/800/800" xmlns:eng900="http://schemas.microsoft.com/analysisservices/2022/engine/900" xmlns:eng900_900="http://schemas.microsoft.com/analysisservices/2022/engine/900/900" xmlns:eng910="http://schemas.microsoft.com/analysisservices/2022/engine/910"
    xmlns:eng910_910="http://schemas.microsoft.com/analysisservices/2022/engine/910/910" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920" xmlns:eng920_920="http://schemas.microsoft.com/analysisservices/2022/engine/920/920" xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921"
    xmlns:eng921_921="http://schemas.microsoft.com/analysisservices/2022/engine/921/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng922_922="http://schemas.microsoft.com/analysisservices/2022/engine/922/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923"
    xmlns:eng923_923="http://schemas.microsoft.com/analysisservices/2025/engine/923/923" xmlns:eng924="http://schemas.microsoft.com/analysisservices/2025/engine/924" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:eng925_925="http://schemas.microsoft.com/analysisservices/2025/engine/925/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset"
    xmlns:ana-x="urn:schemas-microsoft-com:xml-analysis:exception" name="ExecuteSoapOut">
-    <wsdl:part name="parameters" element="ana:ExecuteResponse"/>
-    <wsdl:part name="Session" element="ana:Session"/>
-  </wsdl:message>+<message name="ExecuteSoapOut">
+  <part name="parameters" element="xmla:ExecuteResponse" />
+</message>
```

*Matching: 0, Different: 9, Missing: 0, Extra: 0*

### xmla.xsd

#### Missing from definition (232)

- `element` **DefaultCompatibilityLevel** (spec: Messages > Common Message Syntax > Complex Types > Object Definition
  Complex Types > Complex Type Definitions for Server Object Hierarchy > Server)
- `element` **SupportedCompatibilityLevels** (spec: Messages > Common Message Syntax > Complex Types > Object Definition
  Complex Types > Complex Type Definitions for Server Object Hierarchy > Server)
- `element` **SupportsNewMetadataVersioning** (spec: Messages > Common Message Syntax > Complex Types > Object
  Definition Complex Types > Complex Type Definitions for Server Object Hierarchy > Server)
- `element` **XEvent** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types >
  Complex Type Definitions for Server Object Hierarchy > Trace)
- `element` **ReadWriteMode** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Database)
- `element` **DbStorageLocation** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Database)
- `element` **CompatibilityLevel** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Database)
- `element` **StorageEngineUsed** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Database)
- `element` **ImagePath** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types >
  Complex Type Definitions for Server Object Hierarchy > Database)
- `element` **ImageUrl** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types >
  Complex Type Definitions for Server Object Hierarchy > Database)
- `element` **ImageUniqueID** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Database)
- `element` **ImageVersion** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types >
  Complex Type Definitions for Server Object Hierarchy > Database)
- `element` **Token** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types >
  Complex Type Definitions for Server Object Hierarchy > Database)
- `element` **DirectQueryMode** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Database)
- `element` **UseCryptoNextGen** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Database)
- `element` **CryptKeyCreationTS** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Database)
- `element` **UnicodeCharacterBehavior** (spec: Messages > Common Message Syntax > Complex Types > Object Definition
  Complex Types > Complex Type Definitions for Server Object Hierarchy > Database)
- `element` **QueryImpersonationInfo** (spec: Messages > Common Message Syntax > Complex Types > Object Definition
  Complex Types > Complex Type Definitions for Server Object Hierarchy > DataSource)
- `element` **QueryHints** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types >
  Complex Type Definitions for Server Object Hierarchy > DataSource)
- `element` **ProcessingRecommendation** (spec: Messages > Common Message Syntax > Complex Types > Object Definition
  Complex Types > Complex Type Definitions for Server Object Hierarchy > Dimension)
- `element` **StringStoresCompatibilityLevel** (spec: Messages > Common Message Syntax > Complex Types > Object
  Definition Complex Types > Complex Type Definitions for Server Object Hierarchy > Dimension)
- `element` **CurrentStringStoresCompatibilityLevel** (spec: Messages > Common Message Syntax > Complex Types > Object
  Definition Complex Types > Complex Type Definitions for Server Object Hierarchy > Dimension)
- `complexType` **Relationships** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Dimension)
- `complexType` **DimensionAttributeVisualizationProperties** (spec: Messages > Common Message Syntax > Complex Types >
  Object Definition Complex Types > Complex Type Definitions for Server Object Hierarchy > Dimension >
  DimensionAttribute > DimensionAttributeVisualizationProperties)
- `element` **MemberKeysUnique** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Dimension > Hierarchy)
- `complexType` **HierarchyVisualizationProperties** (spec: Messages > Common Message Syntax > Complex Types > Object
  Definition Complex Types > Complex Type Definitions for Server Object Hierarchy > Dimension > Hierarchy >
  HierarchyVisualizationProperties)
- `element` **ProcessingState** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Dimension > Hierarchy >
  HierarchyVisualizationProperties)
- `complexType` **Relationship** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Dimension > Relationship)
- `complexType` **RelationshipEnd** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Dimension > Relationship)
- `complexType` **RelationshipEndTranslation** (spec: Messages > Common Message Syntax > Complex Types > Object
  Definition Complex Types > Complex Type Definitions for Server Object Hierarchy > Dimension > Relationship)
- `complexType` **RelationshipEndVisualizationProperties** (spec: Messages > Common Message Syntax > Complex Types >
  Object Definition Complex Types > Complex Type Definitions for Server Object Hierarchy > Dimension > Relationship)
- `element` **DaxOptimizationMode** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Cube)
- `complexType` **CalculationPropertiesVisualizationProperties** (spec: Messages > Common Message Syntax > Complex
  Types > Object Definition Complex Types > Complex Type Definitions for Server Object Hierarchy > MdxScript >
  CalculationPropertiesVisualizationProperties)
- `element` **ProcessingState** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > MeasureGroup > MeasureGroupDimension >
  ReferenceMeasureGroupDimension)
- `element` **DirectQueryUsage** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Partition)
- `element` **HoldoutMaxPercent** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > MiningStructure)
- `element` **HoldoutMaxCases** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > MiningStructure)
- `element` **HoldoutSeed** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types >
  Complex Type Definitions for Server Object Hierarchy > MiningStructure)
- `element` **HoldoutActualSize** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > MiningStructure)
- `complexType` **RowNumberBinding** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > Binding > CalculatedMeasureBinding)
- `element` **CalculationError** (spec: Messages > Common Message Syntax > Complex Types > Object Definition Complex
  Types > Complex Type Definitions for Server Object Hierarchy > ErrorConfiguration)
- `complexType` **ImpersonationInfo** (spec: Messages > Common Message Syntax > Complex Types > Object Definition
  Complex Types > Complex Type Definitions for Server Object Hierarchy > ImpersonationInfo)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DBSCHEMA_TABLES)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DBSCHEMA_TABLES)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DBSCHEMA_COLUMNS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DBSCHEMA_PROVIDER_TYPES)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DBSCHEMA_PROVIDER_TYPES)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DBSCHEMA_PROVIDER_TYPES)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_CUBES)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_DIMENSIONS)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_DIMENSIONS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_DIMENSIONS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_DIMENSIONS > Additional Restrictions)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_DIMENSIONS > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_DIMENSIONS > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_HIERARCHIES > Additional Restrictions)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_HIERARCHIES > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_HIERARCHIES > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_LEVELS > Additional Restrictions)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_LEVELS > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_LEVELS > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_MEASURES > Additional Restrictions)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_MEASURES > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_MEASURES > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_PROPERTIES > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_PROPERTIES > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_MEMBERS > Additional Restrictions)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_MEMBERS > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_MEMBERS > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_ACTIONS > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_ACTIONS > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_SETS > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_SETS > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_KPIS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_KPIS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_KPIS > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_KPIS > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_MEASUREGROUP_DIMENSIONS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_MEASUREGROUP_DIMENSIONS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_MEASUREGROUP_DIMENSIONS > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > MDSCHEMA_MEASUREGROUP_DIMENSIONS > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_SERVICES)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_SERVICES)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_SERVICE_PARAMETERS)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_SERVICE_PARAMETERS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_SERVICE_PARAMETERS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_FUNCTIONS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_FUNCTIONS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_MODEL_CONTENT)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_MODEL_CONTENT)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_MODEL_CONTENT > Additional Restrictions)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_MODEL_CONTENT > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_MODEL_CONTENT > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_MODEL_CONTENT_PMML)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_MODEL_CONTENT_PMML)
- `complexType` **xmlDocument** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_MODEL_CONTENT_PMML)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_MODEL_CONTENT_PMML)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_MODELS)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_MODELS)
- `complexType` **xmlDocument** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_MODELS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_MODELS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_COLUMNS)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_COLUMNS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_COLUMNS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_STRUCTURES)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_STRUCTURES)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_STRUCTURES)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_STRUCTURE_COLUMNS)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_STRUCTURE_COLUMNS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DMSCHEMA_MINING_STRUCTURE_COLUMNS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_PROPERTIES)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_PROPERTIES)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_PROPERTIES)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_LITERALS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_LITERALS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_SCHEMA_ROWSETS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_SCHEMA_ROWSETS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_KEYWORDS)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_KEYWORDS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_KEYWORDS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_DATASOURCES)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_DATASOURCES)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_ENUMERATORS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_ENUMERATORS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XML_METADATA)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XML_METADATA)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XML_METADATA > Additional Restrictions)
- `complexType` **xmlDocument** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Discover > Elements > Discover > Discover Request Types > DISCOVER_XML_METADATA > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XML_METADATA > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_TRACE_DEFINITION_PROVIDERINFO)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_TRACE_DEFINITION_PROVIDERINFO)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_TRACE_COLUMNS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_TRACE_COLUMNS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_TRACE_EVENT_CATEGORIES)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_TRACE_EVENT_CATEGORIES)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_MEMORYUSAGE)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_MEMORYUSAGE)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_MEMORYGRANT)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_MEMORYGRANT)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_LOCKS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_LOCKS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_LOCKS > Additional Restrictions)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_LOCKS > Additional Restrictions)
- `complexType` **xmlDocument** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Discover > Elements > Discover > Discover Request Types > DISCOVER_LOCKS > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_LOCKS > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_SESSIONS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_SESSIONS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_JOBS)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_JOBS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_JOBS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_JOBS > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_JOBS > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_DB_CONNECTIONS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_DB_CONNECTIONS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_MASTER_KEY)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_MASTER_KEY)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_PERFORMANCE_COUNTERS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_PERFORMANCE_COUNTERS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_LOCATIONS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_LOCATIONS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_LOCATIONS > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_LOCATIONS > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_PARTITION_STAT)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_PARTITION_STAT)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_DIMENSION_STAT)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_DIMENSION_STAT)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_COMMANDS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_COMMANDS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_COMMAND_OBJECTS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_COMMAND_OBJECTS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_OBJECT_ACTIVITY)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_OBJECT_ACTIVITY)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_OBJECT_MEMORY_USAGE)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_OBJECT_MEMORY_USAGE)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_STORAGE_TABLES)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_STORAGE_TABLES)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_STORAGE_TABLE_COLUMNS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_STORAGE_TABLE_COLUMNS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_STORAGE_TABLE_COLUMN_SEGMENTS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_STORAGE_TABLE_COLUMN_SEGMENTS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_CSDL_METADATA)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_CSDL_METADATA)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_CSDL_METADATA > Additional Restrictions)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_CSDL_METADATA > Additional Restrictions)
- `complexType` **xmlDocument** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Discover > Elements > Discover > Discover Request Types > DISCOVER_CSDL_METADATA > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_CSDL_METADATA > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_CALC_DEPENDENCY > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_RING_BUFFERS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_RING_BUFFERS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_RING_BUFFERS > Additional Restrictions)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_RING_BUFFERS > Additional Restrictions)
- `complexType` **xmlDocument** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Discover > Elements > Discover > Discover Request Types > DISCOVER_RING_BUFFERS > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_RING_BUFFERS > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_PACKAGES)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_PACKAGES)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_OBJECTS)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_OBJECTS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_OBJECTS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_OBJECT_COLUMNS)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_OBJECT_COLUMNS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_OBJECT_COLUMNS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_SESSIONS)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_SESSIONS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_SESSIONS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_SESSIONS > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_SESSIONS > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_SESSION_TARGETS > Additional Restrictions)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_SESSION_TARGETS > Additional Restrictions)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_XEVENT_SESSION_TARGETS > Additional Restrictions)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_DB_MEM_STATS)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_DB_MEM_STATS)
- `complexType` **xmlDocument** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Discover > Elements > Discover > Discover Request Types > DISCOVER_DB_MEM_STATS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_DB_MEM_STATS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_OBJECT_COUNTERS)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_OBJECT_COUNTERS)
- `complexType` **xmlDocument** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Discover > Elements > Discover > Discover Request Types > DISCOVER_OBJECT_COUNTERS)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > Discover > Discover Request Types > DISCOVER_OBJECT_COUNTERS)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > DiscoverResponse)
- `simpleType` **uuid** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > DiscoverResponse)
- `complexType` **xmlDocument** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Discover > Elements > DiscoverResponse)
- `complexType` **row** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Discover > Elements > DiscoverResponse)
- `complexType` **Execute** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Execute > Elements > Execute)
- `element` **DbStorageLocation** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Execute > Elements > Execute > Command Element > Synchronize)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Execute > Elements > Execute > Command Element > CloneDatabase)
- `element` **DbStorageLocation** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Execute > Elements > Execute > Command Element > CloneDatabase)
- `complexType` **Export** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Execute > Elements > Execute > Command Element > Export)
- `element` **Parameters** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Execute > Elements > Execute > Parameters > ExecuteParameter)
- `complexType` **ExecuteResponse** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing
  Rules > Execute > Elements > ExecuteResponse)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Execute > Elements > ExecuteResponse > return Element)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Execute > Elements > ExecuteResponse > return Element)
- `element` **root** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Execute > Elements > ExecuteResponse > return Element)
- `element` **results** (spec: Protocol Details > Server Details > Message Processing Events and Sequencing Rules >
  Execute > Elements > ExecuteResponse > return Element)

#### Types with differences (173)

##### `complexType` **MajorObject**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MajorObject

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MajorObject">
+<xsd:complexType name="MajorObject">
     <xsd:choice>
       <xsd:element name="AggregationDesign" type="AggregationDesign"/>
       <xsd:element name="Assembly" type="Assembly"/>

```

##### `complexType` **ServerProperty**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Server > ServerProperty

```diff
--- definition/+++ spec/@@ -1,11 +1,11 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ServerProperty">
+<xsd:complexType name="ServerProperty" >
     <xsd:all>
-      <xsd:element name="Name" type="xsd:string"/>
-      <xsd:element name="Value" type="xsd:string"/>
-      <xsd:element name="RequiresRestart" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="PendingValue" type="xsd:anySimpleType" minOccurs="0"/>
-      <xsd:element name="DefaultValue" type="xsd:anySimpleType" minOccurs="0"/>
+      <xsd:element name="Name" type="xsd:string" />
+      <xsd:element name="Value" type="xsd:string" />
+      <xsd:element name="RequiresRestart" type="xsd:boolean" minOccurs="0" />
+      <xsd:element name="PendingValue" type="xsd:anySimpleType" minOccurs="0" />
+      <xsd:element name="DefaultValue" type="xsd:anySimpleType" minOccurs="0" />
       <xsd:element name="DisplayFlag" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="Type" minOccurs="0" type="xsd:string"/>
+      <xsd:element name="Type" minOccurs="0" type="xsd:string" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Assembly**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Assembly

```diff
--- definition/+++ spec/@@ -1,19 +1,21 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Assembly" abstract="true">
+<xsd:complexType name="Assembly" abstract="true" >
     <xsd:sequence>
-      <!--These elements are common to each MajorObject -->
-      <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Name" type="xsd:string"/>
-      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Annotations" minOccurs="0">
+      <!--These elements are common to each MajorObject-->
+      <xsd:element name="ID"  type="xsd:string" minOccurs="0" />
+      <xsd:element name="Name"  type="xsd:string" />
+      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="Description" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Annotations"  minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation" type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded" />
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <!--Extended elements for Assembly object -->
-      <xsd:element name="ImpersonationInfo" type="eng:ImpersonationInfo" minOccurs="0"/>
+      <!--Extended elements for Assembly object-->
+      <xsd:element name="ImpersonationInfo" type="ImpersonationInfo"
+                   minOccurs="0" />
     </xsd:sequence>
   </xsd:complexType>
```

##### `complexType` **ComAssembly**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Assembly > ComAssembly

```diff
--- definition/+++ spec/@@ -1,9 +1,9 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ComAssembly">
+<xsd:complexType name="ComAssembly">
     <xsd:complexContent>
       <xsd:extension base="Assembly">
         <xsd:sequence>
           <xsd:element name="Source" type="xsd:string" minOccurs="0"/>
         </xsd:sequence>
-      </xsd:extension>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **ClrAssembly**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Assembly > ClrAssembly

```diff
--- definition/+++ spec/@@ -1,11 +1,12 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ClrAssembly">
+<xsd:complexType name="ClrAssembly">
     <xsd:complexContent>
-      <xsd:extension base="Assembly">
+      <xsd:extension base="Assembly" >
         <xsd:sequence>
-          <xsd:element name="Files">
+          <xsd:element name="Files" >
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="File" type="ClrAssemblyFile" maxOccurs="unbounded"/>
+                <xsd:element name="File"  type="ClrAssemblyFile"
+                             maxOccurs="unbounded" />
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>

```

##### `complexType` **ClrAssemblyFile**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Assembly > ClrAssembly > ClrAssemblyFile

```diff
--- definition/+++ spec/@@ -1,15 +1,15 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ClrAssemblyFile">
+<xsd:complexType name="ClrAssemblyFile">
     <xsd:all>
       <xsd:element name="Name" type="xsd:string"/>
-      <xsd:element name="Type">
+      <xsd:element name="Type" >
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Main"/>
-            <xsd:enumeration value="Dependent"/>
-            <xsd:enumeration value="Debug"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Main" />
+            <xsd:enumeration value="Dependent" />
+            <xsd:enumeration value="Debug" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="Data" type="DataBlock"/>
+      <xsd:element name="Data" type="DataBlock" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **DataBlock**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Assembly > ClrAssembly > DataBlock

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="DataBlock">
+<xsd:complexType name="DataBlock">
     <xsd:sequence>
-      <xsd:element name="Block" type="xsd:base64Binary" minOccurs="0" maxOccurs="unbounded"/>
+      <xsd:element name="Block"  type="xsd:base64Binary"                    minOccurs="0" maxOccurs="unbounded"/>
     </xsd:sequence>
   </xsd:complexType>
```

##### `complexType` **Event**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Trace > Event

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Event">
+<xsd:complexType name="Event">
     <xsd:all>
       <xsd:element name="EventID" type="xsd:string"/>
       <xsd:element name="Columns" type="EventColumnID"/>

```

##### `complexType` **EventColumnID**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Trace > Event > EventColumnID

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="EventColumnID">
+<xsd:complexType name="EventColumnID">
     <xsd:sequence>
-      <xsd:element name="ColumnID" type="xsd:string" maxOccurs="unbounded"/>
+        <xsd:element name="ColumnID" type="xsd:string" maxOccurs="unbounded"/>
     </xsd:sequence>
   </xsd:complexType>
```

##### `complexType` **TraceFilter**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Trace > event_session

```diff
--- definition/+++ spec/@@ -1,15 +1,51 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="TraceFilter">
+<xsd:complexType name="TraceFilter" >
     <xsd:choice minOccurs="1" maxOccurs="1">
-      <xsd:element name="Not" type="not_type" minOccurs="0"/>
-      <xsd:element name="Or" type="and_or_type" minOccurs="0"/>
-      <xsd:element name="And" type="and_or_type" minOccurs="0"/>
-      <xsd:element name="Equal" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="NotEqual" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="Less" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="LessOrEqual" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="Greater" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="GreaterOrEqual" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="Like" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="NotLike" type="bool_binop" minOccurs="0"/>
+      <xsd:element name="Not" type="not_type" minOccurs="0" />
+      <xsd:element name="Or" type="and_or_type" minOccurs="0" />
+      <xsd:element name="And" type="and_or_type" minOccurs="0" />
+      <xsd:element name="Equal" type="bool_binop" minOccurs="0" />
+      <xsd:element name="NotEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Less" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="LessOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Greater" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="GreaterOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Like" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="NotLike" type="bool_binop"  minOccurs="0" />
     </xsd:choice>
+  </xsd:complexType>
+  <xsd:complexType name="not_type" >
+    <xsd:choice maxOccurs="1" minOccurs="1">
+      <xsd:element name="Not" type="not_type" minOccurs="0" />
+      <xsd:element name="Or" type="and_or_type" minOccurs="0" />
+      <xsd:element name="And" type="and_or_type" minOccurs="0" />
+      <xsd:element name="Equal" type="bool_binop" minOccurs="0" />
+      <xsd:element name="NotEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Less" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="LessOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Greater" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="GreaterOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Like" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="NotLike" type="bool_binop"  minOccurs="0" />
+    </xsd:choice>
+  </xsd:complexType>
+  <xsd:complexType name="and_or_type" >
+    <xsd:choice maxOccurs="2" minOccurs="2">
+      <xsd:element name="Not" type="not_type"  minOccurs="0"/>
+      <xsd:element name="Or" type="and_or_type" minOccurs="0" />
+      <xsd:element name="And" type="and_or_type" minOccurs="0" />
+      <xsd:element name="Equal" type="bool_binop" minOccurs="0" />
+      <xsd:element name="NotEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Less" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="LessOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Greater" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="GreaterOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Like" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="NotLike" type="bool_binop"  minOccurs="0" />
+    </xsd:choice>
+  </xsd:complexType>
+  <xsd:complexType name="bool_binop" >
+    <xsd:all>
+      <xsd:element name="ColumnID" type="xsd:string"/>
+      <xsd:element name="Value" type="xsd:string" />
+    </xsd:all>
   </xsd:complexType>
```

##### `complexType` **not_type**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Trace > event_session

```diff
--- definition/+++ spec/@@ -1,15 +1,51 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="not_type">
+<xsd:complexType name="TraceFilter" >
+    <xsd:choice minOccurs="1" maxOccurs="1">
+      <xsd:element name="Not" type="not_type" minOccurs="0" />
+      <xsd:element name="Or" type="and_or_type" minOccurs="0" />
+      <xsd:element name="And" type="and_or_type" minOccurs="0" />
+      <xsd:element name="Equal" type="bool_binop" minOccurs="0" />
+      <xsd:element name="NotEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Less" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="LessOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Greater" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="GreaterOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Like" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="NotLike" type="bool_binop"  minOccurs="0" />
+    </xsd:choice>
+  </xsd:complexType>
+  <xsd:complexType name="not_type" >
     <xsd:choice maxOccurs="1" minOccurs="1">
-      <xsd:element name="Not" type="not_type" minOccurs="0"/>
-      <xsd:element name="Or" type="and_or_type" minOccurs="0"/>
-      <xsd:element name="And" type="and_or_type" minOccurs="0"/>
-      <xsd:element name="Equal" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="NotEqual" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="Less" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="LessOrEqual" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="Greater" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="GreaterOrEqual" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="Like" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="NotLike" type="bool_binop" minOccurs="0"/>
+      <xsd:element name="Not" type="not_type" minOccurs="0" />
+      <xsd:element name="Or" type="and_or_type" minOccurs="0" />
+      <xsd:element name="And" type="and_or_type" minOccurs="0" />
+      <xsd:element name="Equal" type="bool_binop" minOccurs="0" />
+      <xsd:element name="NotEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Less" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="LessOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Greater" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="GreaterOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Like" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="NotLike" type="bool_binop"  minOccurs="0" />
     </xsd:choice>
+  </xsd:complexType>
+  <xsd:complexType name="and_or_type" >
+    <xsd:choice maxOccurs="2" minOccurs="2">
+      <xsd:element name="Not" type="not_type"  minOccurs="0"/>
+      <xsd:element name="Or" type="and_or_type" minOccurs="0" />
+      <xsd:element name="And" type="and_or_type" minOccurs="0" />
+      <xsd:element name="Equal" type="bool_binop" minOccurs="0" />
+      <xsd:element name="NotEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Less" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="LessOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Greater" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="GreaterOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Like" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="NotLike" type="bool_binop"  minOccurs="0" />
+    </xsd:choice>
+  </xsd:complexType>
+  <xsd:complexType name="bool_binop" >
+    <xsd:all>
+      <xsd:element name="ColumnID" type="xsd:string"/>
+      <xsd:element name="Value" type="xsd:string" />
+    </xsd:all>
   </xsd:complexType>
```

##### `complexType` **and_or_type**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Trace > event_session

```diff
--- definition/+++ spec/@@ -1,15 +1,51 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="and_or_type">
+<xsd:complexType name="TraceFilter" >
+    <xsd:choice minOccurs="1" maxOccurs="1">
+      <xsd:element name="Not" type="not_type" minOccurs="0" />
+      <xsd:element name="Or" type="and_or_type" minOccurs="0" />
+      <xsd:element name="And" type="and_or_type" minOccurs="0" />
+      <xsd:element name="Equal" type="bool_binop" minOccurs="0" />
+      <xsd:element name="NotEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Less" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="LessOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Greater" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="GreaterOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Like" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="NotLike" type="bool_binop"  minOccurs="0" />
+    </xsd:choice>
+  </xsd:complexType>
+  <xsd:complexType name="not_type" >
+    <xsd:choice maxOccurs="1" minOccurs="1">
+      <xsd:element name="Not" type="not_type" minOccurs="0" />
+      <xsd:element name="Or" type="and_or_type" minOccurs="0" />
+      <xsd:element name="And" type="and_or_type" minOccurs="0" />
+      <xsd:element name="Equal" type="bool_binop" minOccurs="0" />
+      <xsd:element name="NotEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Less" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="LessOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Greater" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="GreaterOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Like" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="NotLike" type="bool_binop"  minOccurs="0" />
+    </xsd:choice>
+  </xsd:complexType>
+  <xsd:complexType name="and_or_type" >
     <xsd:choice maxOccurs="2" minOccurs="2">
-      <xsd:element name="Not" type="not_type" minOccurs="0"/>
-      <xsd:element name="Or" type="and_or_type" minOccurs="0"/>
-      <xsd:element name="And" type="and_or_type" minOccurs="0"/>
-      <xsd:element name="Equal" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="NotEqual" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="Less" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="LessOrEqual" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="Greater" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="GreaterOrEqual" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="Like" type="bool_binop" minOccurs="0"/>
-      <xsd:element name="NotLike" type="bool_binop" minOccurs="0"/>
+      <xsd:element name="Not" type="not_type"  minOccurs="0"/>
+      <xsd:element name="Or" type="and_or_type" minOccurs="0" />
+      <xsd:element name="And" type="and_or_type" minOccurs="0" />
+      <xsd:element name="Equal" type="bool_binop" minOccurs="0" />
+      <xsd:element name="NotEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Less" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="LessOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Greater" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="GreaterOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Like" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="NotLike" type="bool_binop"  minOccurs="0" />
     </xsd:choice>
+  </xsd:complexType>
+  <xsd:complexType name="bool_binop" >
+    <xsd:all>
+      <xsd:element name="ColumnID" type="xsd:string"/>
+      <xsd:element name="Value" type="xsd:string" />
+    </xsd:all>
   </xsd:complexType>
```

##### `complexType` **bool_binop**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Trace > event_session

```diff
--- definition/+++ spec/@@ -1,6 +1,51 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="bool_binop">
+<xsd:complexType name="TraceFilter" >
+    <xsd:choice minOccurs="1" maxOccurs="1">
+      <xsd:element name="Not" type="not_type" minOccurs="0" />
+      <xsd:element name="Or" type="and_or_type" minOccurs="0" />
+      <xsd:element name="And" type="and_or_type" minOccurs="0" />
+      <xsd:element name="Equal" type="bool_binop" minOccurs="0" />
+      <xsd:element name="NotEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Less" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="LessOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Greater" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="GreaterOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Like" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="NotLike" type="bool_binop"  minOccurs="0" />
+    </xsd:choice>
+  </xsd:complexType>
+  <xsd:complexType name="not_type" >
+    <xsd:choice maxOccurs="1" minOccurs="1">
+      <xsd:element name="Not" type="not_type" minOccurs="0" />
+      <xsd:element name="Or" type="and_or_type" minOccurs="0" />
+      <xsd:element name="And" type="and_or_type" minOccurs="0" />
+      <xsd:element name="Equal" type="bool_binop" minOccurs="0" />
+      <xsd:element name="NotEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Less" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="LessOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Greater" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="GreaterOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Like" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="NotLike" type="bool_binop"  minOccurs="0" />
+    </xsd:choice>
+  </xsd:complexType>
+  <xsd:complexType name="and_or_type" >
+    <xsd:choice maxOccurs="2" minOccurs="2">
+      <xsd:element name="Not" type="not_type"  minOccurs="0"/>
+      <xsd:element name="Or" type="and_or_type" minOccurs="0" />
+      <xsd:element name="And" type="and_or_type" minOccurs="0" />
+      <xsd:element name="Equal" type="bool_binop" minOccurs="0" />
+      <xsd:element name="NotEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Less" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="LessOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Greater" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="GreaterOrEqual" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="Like" type="bool_binop"  minOccurs="0" />
+      <xsd:element name="NotLike" type="bool_binop"  minOccurs="0" />
+    </xsd:choice>
+  </xsd:complexType>
+  <xsd:complexType name="bool_binop" >
     <xsd:all>
       <xsd:element name="ColumnID" type="xsd:string"/>
-      <xsd:element name="Value" type="xsd:string"/>
+      <xsd:element name="Value" type="xsd:string" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Account**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Database > Account

```diff
--- definition/+++ spec/@@ -1,34 +1,36 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Account">
+<xsd:complexType name="Account">
     <xsd:all>
-      <xsd:element name="AccountType" type="xsd:string"/>
+      <xsd:element name="AccountType" type="xsd:string" />
       <xsd:element name="AggregationFunction" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Sum"/>
-            <xsd:enumeration value="Count"/>
-            <xsd:enumeration value="Min"/>
-            <xsd:enumeration value="Max"/>
-            <xsd:enumeration value="DistinctCount"/>
-            <xsd:enumeration value="None"/>
-            <xsd:enumeration value="AverageOfChildren"/>
-            <xsd:enumeration value="FirstChild"/>
-            <xsd:enumeration value="LastChild"/>
-            <xsd:enumeration value="FirstNonEmpty"/>
-            <xsd:enumeration value="LastNonEmpty"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Sum" />
+            <xsd:enumeration value="Count" />
+            <xsd:enumeration value="Min" />
+            <xsd:enumeration value="Max" />
+            <xsd:enumeration value="DistinctCount" />
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="AverageOfChildren" />
+            <xsd:enumeration value="FirstChild" />
+            <xsd:enumeration value="LastChild" />
+            <xsd:enumeration value="FirstNonEmpty" />
+            <xsd:enumeration value="LastNonEmpty" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="Aliases" minOccurs="0">
+      <xsd:element name="Aliases" minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Alias" type="xsd:string" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Alias"  type="xsd:string" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **RelationalDataSource**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > DataSource > OlapDataSource

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="RelationalDataSource">
+<xsd:complexType name="RelationalDataSource" >
     <xsd:complexContent>
-      <xsd:extension base="DataSource"/>
+      <xsd:extension base="DataSource" />
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **OlapDataSource**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > DataSource > PushedDataSource

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="OlapDataSource">
+<xsd:complexType name="OlapDataSource" >
     <xsd:complexContent>
-      <xsd:extension base="DataSource"/>
+      <xsd:extension base="DataSource" />
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **PushedDataSource**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > DataSource > PushedDataSource

```diff
--- definition/+++ spec/@@ -1,22 +1,22 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="PushedDataSource">
+<xsd:complexType name="PushedDataSource" >
     <xsd:all>
       <xsd:element name="root">
         <xsd:complexType>
           <xsd:attribute name="Parameter">
             <xsd:simpleType>
               <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="InputRowset"/>
+                <xsd:enumeration value="InputRowset" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:attribute>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="EndOfData">
+      <xsd:element name="EndOfData" >
         <xsd:complexType>
-          <xsd:attribute name="Parameter">
+          <xsd:attribute name="Parameter" >
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="EndOfInputRowset"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="EndOfInputRowset" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:attribute>

```

##### `complexType` **DataSourceView**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > DataSourceView

```diff
--- definition/+++ spec/@@ -1,19 +1,31 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="DataSourceView">
+<xsd:complexType name="DataSourceView">
+    <xsd:complexContent>
+    <xsd:extension base="MajorObject">
     <xsd:all>
-      <!--These elements are common to each MajorObject -->
-      <xsd:element name="Name" type="xsd:string"/>
-      <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Annotations" minOccurs="0">
+      <!--These elements are common to each MajorObject-->
+      <xsd:element name="Name"  type="xsd:string" />
+      <xsd:element name="ID"  type="xsd:string" minOccurs="0" />
+      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="Description" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Annotations"  minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation" type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded" />
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <!--Extended elements for DataSourceView object -->
+      <!--Extended elements for DataSourceView object-->
       <xsd:element name="DataSourceID" type="xsd:string"/>
+      <xsd:element name="Schema">
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element ref="xsd:schema" />
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
     </xsd:all>
-  </xsd:complexType>+    </xsd:extension>
+    </xsd:complexContent>
+ </xsd:complexType>
```

##### `complexType` **DimensionAttribute**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Dimension > DimensionAttribute

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="DimensionAttribute">
+<xsd:complexType name="DimensionAttribute">
     <xsd:all>
       <xsd:element name="Name" type="xsd:string"/>
       <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
@@ -7,10 +7,11 @@         <xsd:complexType>
           <xsd:simpleContent>
             <xsd:extension base="DimensionAttributeTypeEnumType">
-              <xsd:attribute name="valuens">
+              <xsd:attribute name="valuens" >
                 <xsd:simpleType>
                   <xsd:restriction base="xsd:string">
-                    <xsd:enumeration value="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"/>
+                    <xsd:enumeration value=
+             "http://schemas.microsoft.com/analysisservices/2010/engine/200/200" />
                   </xsd:restriction>
                 </xsd:simpleType>
               </xsd:attribute>
@@ -20,80 +21,83 @@       </xsd:element>
       <xsd:element name="Usage" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Regular"/>
-            <xsd:enumeration value="Key"/>
-            <xsd:enumeration value="Parent"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="Source" type="Binding" minOccurs="0"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Regular" />
+            <xsd:enumeration value="Key" />
+            <xsd:enumeration value="Parent" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="Source" type="Binding" minOccurs="0" />
       <xsd:element name="EstimatedCount" type="xsd:long" minOccurs="0"/>
-      <xsd:element name="KeyColumns">
+      <xsd:element name="KeyColumns" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="KeyColumn" type="DataItem" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="KeyColumn"  type="DataItem" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="NameColumn" type="DataItem" minOccurs="0"/>
-      <xsd:element name="ValueColumn" type="DataItem" minOccurs="0"/>
+      <xsd:element name="NameColumn" type="DataItem" minOccurs="0" />
+      <xsd:element name="ValueColumn" type="DataItem" minOccurs="0" />
       <xsd:element name="Translations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Translation" type="AttributeTranslation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Translation"  type="AttributeTranslation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="AttributeRelationships" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="AttributeRelationship" type="AttributeRelationship" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="AttributeRelationship"  type="AttributeRelationship"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="DiscretizationMethod" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="None"/>
-            <xsd:enumeration value="Automatic"/>
-            <xsd:enumeration value="EqualAreas"/>
-            <xsd:enumeration value="Clusters"/>
-            <xsd:enumeration value="Thresholds"/>
-            <xsd:enumeration value="UserDefined"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="Automatic" />
+            <xsd:enumeration value="EqualAreas" />
+            <xsd:enumeration value="Clusters" />
+            <xsd:enumeration value="Thresholds" />
+            <xsd:enumeration value="UserDefined" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="DiscretizationBucketCount" type="xsd:integer" minOccurs="0"/>
       <xsd:element name="RootMemberIf" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="ParentIsBlankSelfOrMissing"/>
-            <xsd:enumeration value="ParentIsBlank"/>
-            <xsd:enumeration value="ParentIsSelf"/>
-            <xsd:enumeration value="ParentIsMissing"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="ParentIsBlankSelfOrMissing" />
+            <xsd:enumeration value="ParentIsBlank" />
+            <xsd:enumeration value="ParentIsSelf" />
+            <xsd:enumeration value="ParentIsMissing" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="OrderBy" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Key"/>
-            <xsd:enumeration value="Name"/>
-            <xsd:enumeration value="AttributeKey"/>
-            <xsd:enumeration value="AttributeName"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Key" />
+            <xsd:enumeration value="Name" />
+            <xsd:enumeration value="AttributeKey" />
+            <xsd:enumeration value="AttributeName" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="DefaultMember" type="xsd:string" minOccurs="0"/>
       <xsd:element name="OrderByAttributeID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="SkippedLevelsColumn" type="DataItem" minOccurs="0"/>
+      <xsd:element name="SkippedLevelsColumn" type="DataItem" minOccurs="0" />
       <xsd:element name="NamingTemplate" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="MembersWithData" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="NonLeafDataHidden"/>
-            <xsd:enumeration value="NonLeafDataVisible"/>
+      <xsd:element name="MembersWithData"  minOccurs="0">
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="NonLeafDataHidden" />
+            <xsd:enumeration value="NonLeafDataVisible" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
@@ -101,67 +105,332 @@       <xsd:element name="NamingTemplateTranslations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="NamingTemplateTranslation" type="Translation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="NamingTemplateTranslation"  type="Translation"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="CustomRollupColumn" type="DataItem" minOccurs="0"/>
-      <xsd:element name="CustomRollupPropertiesColumn" type="DataItem" minOccurs="0"/>
-      <xsd:element name="UnaryOperatorColumn" type="DataItem" minOccurs="0"/>
-      <xsd:element name="AttributeHierarchyOrdered" type="xsd:boolean" minOccurs="0"/>
+      <xsd:element name="CustomRollupColumn" type="DataItem" minOccurs="0" />
+      <xsd:element name="CustomRollupPropertiesColumn" type="DataItem"
+                   minOccurs="0" />
+      <xsd:element name="UnaryOperatorColumn" type="DataItem" minOccurs="0" />
+      <xsd:element name="AttributeHierarchyOrdered" type="xsd:boolean"
+                   minOccurs="0"/>
       <xsd:element name="MemberNamesUnique" type="xsd:boolean" minOccurs="0"/>
       <xsd:element name="IsAggregatable" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="AttributeHierarchyEnabled" type="xsd:boolean" minOccurs="0"/>
+      <xsd:element name="AttributeHierarchyEnabled" type="xsd:boolean"
+                   minOccurs="0"/>
       <xsd:element name="AttributeHierarchyOptimizedState" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="FullyOptimized"/>
-            <xsd:enumeration value="NotOptimized"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="AttributeHierarchyVisible" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="AttributeHierarchyDisplayFolder" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="KeyUniquenessGuarantee" type="xsd:boolean" minOccurs="0"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="FullyOptimized" />
+            <xsd:enumeration value="NotOptimized" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="AttributeHierarchyVisible" type="xsd:boolean"
+                   minOccurs="0"/>
+      <xsd:element name="AttributeHierarchyDisplayFolder" type="xsd:string"
+                   minOccurs="0"/>
+      <xsd:element name="KeyUniquenessGuarantee" type="xsd:boolean"
+                   minOccurs="0"/>
       <xsd:element name="GroupingBehavior" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="EncourageGrouping"/>
-            <xsd:enumeration value="DiscourageGrouping"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="EncourageGrouping" />
+            <xsd:enumeration value="DiscourageGrouping" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="InstanceSelection" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="None"/>
-            <xsd:enumeration value="DropDown"/>
-            <xsd:enumeration value="List"/>
-            <xsd:enumeration value="FilteredList"/>
-            <xsd:enumeration value="MandatoryFilter"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="DropDown" />
+            <xsd:enumeration value="List" />
+            <xsd:enumeration value="FilteredList" />
+            <xsd:enumeration value="MandatoryFilter" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="ProcessingState" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Processed"/>
-            <xsd:enumeration value="Unprocessed"/>
-            <xsd:enumeration value="InvalidExpression"/>
-            <xsd:enumeration value="CalculationError"/>
-            <xsd:enumeration value="DependencyError"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="AttributeHierarchyProcessingState" type="eng300:AttributeHierarchyProcessingState" minOccurs="0"/>
-      <xsd:element name="VisualizationProperties" type="eng300:DimensionAttributeVisualizationProperties" minOccurs="0" maxOccurs="1"/>
-      <xsd:element name="ExtendedType" type="xsd:string" minOccurs="0" maxOccurs="1"/>
+      <xsd:element name="ProcessingState" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Processed" />
+            <xsd:enumeration value="Unprocessed" />
+            <xsd:enumeration value="InvalidExpression" />
+            <xsd:enumeration value="CalculationError" />
+            <xsd:enumeration value="DependencyError" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="AttributeHierarchyProcessingState"
+                   type="eng300:AttributeHierarchyProcessingState"
+                   minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Processed" />
+            <xsd:enumeration value="Unprocessed" />
+            <xsd:enumeration value="DependencyError" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name ="VisualizationProperties"
+                   type="eng300:DimensionAttributeVisualizationProperties"
+                   minOccurs="0" maxOccurs="1"/>
+      <xsd:element name ="ExtendedType" type="xsd:string" minOccurs="0" maxOccurs="1"/>
     </xsd:all>
-  </xsd:complexType>+  </xsd:complexType>
+
+  <xsd:simpleType name="DimensionAttributeTypeEnumType">
+    <xsd:restriction base="xsd:string" >
+      <xsd:enumeration value="Account" />
+      <xsd:enumeration value="AccountName" />
+      <xsd:enumeration value="AccountNumber" />
+      <xsd:enumeration value="AccountType" />
+      <xsd:enumeration value="Address" />
+      <xsd:enumeration value="AddressBuilding" />
+      <xsd:enumeration value="AddressCity" />
+      <xsd:enumeration value="AddressCountry" />
+      <xsd:enumeration value="AddressFax" />
+      <xsd:enumeration value="AddressFloor" />
+      <xsd:enumeration value="AddressHouse" />
+      <xsd:enumeration value="AddressPhone" />
+      <xsd:enumeration value="AddressQuarter" />
+      <xsd:enumeration value="AddressRoom" />
+      <xsd:enumeration value="AddressStateOrProvince" />
+      <xsd:enumeration value="AddressStreet" />
+      <xsd:enumeration value="AddressZip" />
+      <xsd:enumeration value="BomResource" />
+      <xsd:enumeration value="Caption" />
+      <xsd:enumeration value="CaptionAbbreviation" />
+      <xsd:enumeration value="CaptionDescription" />
+      <xsd:enumeration value="Channel" />
+      <xsd:enumeration value="City" />
+      <xsd:enumeration value="Company" />
+      <xsd:enumeration value="Continent" />
+      <xsd:enumeration value="Country" />
+      <xsd:enumeration value="County" />
+      <xsd:enumeration value="CurrencyDestination" />
+      <xsd:enumeration value="CurrencyIsoCode" />
+      <xsd:enumeration value="CurrencyName" />
+      <xsd:enumeration value="CurrencySource" />
+      <xsd:enumeration value="CustomerGroup" />
+      <xsd:enumeration value="CustomerHousehold" />
+      <xsd:enumeration value="Customers" />
+      <xsd:enumeration value="Date" />
+      <xsd:enumeration value="DateCanceled" />
+      <xsd:enumeration value="DateDuration" />
+      <xsd:enumeration value="DateEnded" />
+      <xsd:enumeration value="DateModified" />
+      <xsd:enumeration value="DateStart" />
+      <xsd:enumeration value="DayOfHalfYear" />
+      <xsd:enumeration value="DayOfMonth" />
+      <xsd:enumeration value="DayOfQuarter" />
+      <xsd:enumeration value="DayOfTenDays" />
+      <xsd:enumeration value="DayOfTrimester" />
+      <xsd:enumeration value="DayOfWeek" />
+      <xsd:enumeration value="DayOfYear" />
+      <xsd:enumeration value="Days" />
+      <xsd:enumeration value="DeletedFlag" />
+      <xsd:enumeration value="ExtendedType" />
+      <xsd:enumeration value="FiscalDate" />
+      <xsd:enumeration value="FiscalDayOfHalfYear" />
+      <xsd:enumeration value="FiscalDayOfMonth" />
+      <xsd:enumeration value="FiscalDayOfQuarter" />
+      <xsd:enumeration value="FiscalDayOfTrimester" />
+      <xsd:enumeration value="FiscalDayOfWeek" />
+      <xsd:enumeration value="FiscalDayOfYear" />
+      <xsd:enumeration value="FiscalHalfYears" />
+      <xsd:enumeration value="FiscalHalfYearOfYear" />
+      <xsd:enumeration value="FiscalMonths" />
+      <xsd:enumeration value="FiscalMonthOfHalfYear" />
+      <xsd:enumeration value="FiscalMonthOfQuarter" />
+      <xsd:enumeration value="FiscalMonthOfTrimester" />
+      <xsd:enumeration value="FiscalMonthOfYear" />
+      <xsd:enumeration value="FiscalQuarters" />
+      <xsd:enumeration value="FiscalQuarterOfHalfYear" />
+      <xsd:enumeration value="FiscalQuarterOfYear" />
+      <xsd:enumeration value="FiscalTrimesters" />
+      <xsd:enumeration value="FiscalTrimesterOfYear" />
+      <xsd:enumeration value="FiscalWeeks" />
+      <xsd:enumeration value="FiscalWeekOfHalfYear" />
+      <xsd:enumeration value="FiscalWeekOfMonth" />
+      <xsd:enumeration value="FiscalWeekOfQuarter" />
+      <xsd:enumeration value="FiscalWeekOfTrimester" />
+      <xsd:enumeration value="FiscalWeekOfYear" />
+      <xsd:enumeration value="FiscalYears" />
+      <xsd:enumeration value="FormattingColor" />
+      <xsd:enumeration value="FormattingFont" />
+      <xsd:enumeration value="FormattingFontEffects" />
+      <xsd:enumeration value="FormattingFontSize" />
+      <xsd:enumeration value="FormattingOrder" />
+      <xsd:enumeration value="FormattingSubtotal" />
+      <xsd:enumeration value="GeoBoundaryBottom" />
+      <xsd:enumeration value="GeoBoundaryFront" />
+      <xsd:enumeration value="GeoBoundaryLeft" />
+      <xsd:enumeration value="GeoBoundaryPolygon" />
+      <xsd:enumeration value="GeoBoundaryRear" />
+      <xsd:enumeration value="GeoBoundaryRight" />
+      <xsd:enumeration value="GeoBoundaryTop" />
+      <xsd:enumeration value="GeoCentroidX" />
+      <xsd:enumeration value="GeoCentroidY" />
+      <xsd:enumeration value="GeoCentroidZ" />
+      <xsd:enumeration value="HalfYears" />
+      <xsd:enumeration value="HalfYearOfYear" />
+      <xsd:enumeration value="Hours" />
+      <xsd:enumeration value="ID" />
+      <xsd:enumeration value="Image" />
+      <xsd:enumeration value="ImageBmp" />
+      <xsd:enumeration value="ImageGif" />
+      <xsd:enumeration value="ImageJpg" />
+      <xsd:enumeration value="ImagePng" />
+      <xsd:enumeration value="ImageTiff" />
+      <xsd:enumeration value="ImageUrl" />
+      <xsd:enumeration value="IsHoliday" />
+      <xsd:enumeration value="Iso8601Date" />
+      <xsd:enumeration value="Iso8601DayOfWeek" />
+      <xsd:enumeration value="Iso8601DayOfYear" />
+      <xsd:enumeration value="Iso8601Weeks" />
+      <xsd:enumeration value="Iso8601WeekOfYear" />
+      <xsd:enumeration value="Iso8601Years" />
+      <xsd:enumeration value="IsPeakDay" />
+      <xsd:enumeration value="IsWeekDay" />
+      <xsd:enumeration value="IsWorkingDay" />
+      <xsd:enumeration value="ManufacturingDate" />
+      <xsd:enumeration value="ManufacturingDayOfHalfYear" />
+      <xsd:enumeration value="ManufacturingDayOfMonth" />
+      <xsd:enumeration value="ManufacturingDayOfQuarter" />
+      <xsd:enumeration value="ManufacturingDayOfWeek" />
+      <xsd:enumeration value="ManufacturingDayOfYear" />
+      <xsd:enumeration value="ManufacturingHalfYears" />
+      <xsd:enumeration value="ManufacturingHalfYearOfYear" />
+      <xsd:enumeration value="ManufacturingMonths" />
+      <xsd:enumeration value="ManufacturingMonthOfHalfYear" />
+      <xsd:enumeration value="ManufacturingMonthOfQuarter" />
+      <xsd:enumeration value="ManufacturingMonthOfYear" />
+      <xsd:enumeration value="ManufacturingQuarters" />
+      <xsd:enumeration value="ManufacturingQuarterOfHalfYear" />
+      <xsd:enumeration value="ManufacturingQuarterOfYear" />
+      <xsd:enumeration value="ManufacturingWeeks" />
+      <xsd:enumeration value="ManufacturingWeekOfHalfYear" />
+      <xsd:enumeration value="ManufacturingWeekOfMonth" />
+      <xsd:enumeration value="ManufacturingWeekOfQuarter" />
+      <xsd:enumeration value="ManufacturingWeekOfYear" />
+      <xsd:enumeration value="ManufacturingYears" />
+      <xsd:enumeration value="Minutes" />
+      <xsd:enumeration value="Months" />
+      <xsd:enumeration value="MonthOfHalfYear" />
+      <xsd:enumeration value="MonthOfQuarter" />
+      <xsd:enumeration value="MonthOfTrimester" />
+      <xsd:enumeration value="MonthOfYear" />
+      <xsd:enumeration value="OrganizationalUnit" />
+      <xsd:enumeration value="OrgTitle" />
+      <xsd:enumeration value="PercentOwnership" />
+      <xsd:enumeration value="PercentVoteRight" />
+      <xsd:enumeration value="Person" />
+      <xsd:enumeration value="PersonContact" />
+      <xsd:enumeration value="PersonDemographic" />
+      <xsd:enumeration value="PersonFirstName" />
+      <xsd:enumeration value="PersonFullName" />
+      <xsd:enumeration value="PersonLastName" />
+      <xsd:enumeration value="PersonMiddleName" />
+      <xsd:enumeration value="PhysicalColor" />
+      <xsd:enumeration value="PhysicalDensity" />
+      <xsd:enumeration value="PhysicalDepth" />
+      <xsd:enumeration value="PhysicalHeight" />
+      <xsd:enumeration value="PhysicalSize" />
+      <xsd:enumeration value="PhysicalVolume" />
+      <xsd:enumeration value="PhysicalWeight" />
+      <xsd:enumeration value="PhysicalWidth" />
+      <xsd:enumeration value="Point" />
+      <xsd:enumeration value="PostalCode" />
+      <xsd:enumeration value="Product" />
+      <xsd:enumeration value="ProductBrand" />
+      <xsd:enumeration value="ProductCategory" />
+      <xsd:enumeration value="ProductGroup" />
+      <xsd:enumeration value="ProductSKU" />
+      <xsd:enumeration value="Project" />
+      <xsd:enumeration value="ProjectCode" />
+      <xsd:enumeration value="ProjectCompletion" />
+      <xsd:enumeration value="ProjectEndDate" />
+      <xsd:enumeration value="ProjectName" />
+      <xsd:enumeration value="ProjectStartDate" />
+      <xsd:enumeration value="Promotion" />
+      <xsd:enumeration value="QtyRangeHigh" />
+      <xsd:enumeration value="QtyRangeLow" />
+      <xsd:enumeration value="Quantitative" />
+      <xsd:enumeration value="Quarters" />
+      <xsd:enumeration value="QuarterOfHalfYear" />
+      <xsd:enumeration value="QuarterOfYear" />
+      <xsd:enumeration value="Rate" />
+      <xsd:enumeration value="RateType" />
+      <xsd:enumeration value="Region" />
+      <xsd:enumeration value="Regular" />
+      <xsd:enumeration value="RelationToParent" />
+      <xsd:enumeration value="ReportingDate" />
+      <xsd:enumeration value="ReportingDayOfHalfYear" />
+      <xsd:enumeration value="ReportingDayOfMonth" />
+      <xsd:enumeration value="ReportingDayOfQuarter" />
+      <xsd:enumeration value="ReportingDayOfTrimester" />
+      <xsd:enumeration value="ReportingDayOfWeek" />
+      <xsd:enumeration value="ReportingDayOfYear" />
+      <xsd:enumeration value="ReportingHalfYears" />
+      <xsd:enumeration value="ReportingHalfYearOfYear" />
+      <xsd:enumeration value="ReportingMonths" />
+      <xsd:enumeration value="ReportingMonthOfHalfYear" />
+      <xsd:enumeration value="ReportingMonthOfQuarter" />
+      <xsd:enumeration value="ReportingMonthOfTrimester" />
+      <xsd:enumeration value="ReportingMonthOfYear" />
+      <xsd:enumeration value="ReportingQuarters" />
+      <xsd:enumeration value="ReportingQuarterOfHalfYear" />
+      <xsd:enumeration value="ReportingQuarterOfYear" />
+      <xsd:enumeration value="ReportingTrimesters" />
+      <xsd:enumeration value="ReportingTrimesterOfYear" />
+      <xsd:enumeration value="ReportingWeeks" />
+      <xsd:enumeration value="ReportingWeekOfHalfYear" />
+      <xsd:enumeration value="ReportingWeekOfMonth" />
+      <xsd:enumeration value="ReportingWeekOfQuarter" />
+      <xsd:enumeration value="ReportingWeekOfTrimester" />
+      <xsd:enumeration value="ReportingWeekOfYear" />
+      <xsd:enumeration value="ReportingYears" />
+      <xsd:enumeration value="Representative" />
+      <xsd:enumeration value="RowNumber" />
+      <xsd:enumeration value="ScdEndDate" />
+      <xsd:enumeration value="ScdOriginalID" />
+      <xsd:enumeration value="ScdStartDate" />
+      <xsd:enumeration value="ScdStatus" />
+      <xsd:enumeration value="Scenario" />
+      <xsd:enumeration value="Seconds" />
+      <xsd:enumeration value="Sequence" />
+      <xsd:enumeration value="ShortCaption" />
+      <xsd:enumeration value="StateOrProvince" />
+      <xsd:enumeration value="TenDay" />
+      <xsd:enumeration value="TenDayOfHalfYear" />
+      <xsd:enumeration value="TenDayOfMonth" />
+      <xsd:enumeration value="TenDayOfQuarter" />
+      <xsd:enumeration value="TenDayOfTrimester" />
+      <xsd:enumeration value="TenDayOfYear" />
+      <xsd:enumeration value="Trimesters" />
+      <xsd:enumeration value="TrimesterOfYear" />
+      <xsd:enumeration value="UndefinedTime" />
+      <xsd:enumeration value="Utility" />
+      <xsd:enumeration value="Version" />
+      <xsd:enumeration value="WebHtml" />
+      <xsd:enumeration value="WebMailAlias" />
+      <xsd:enumeration value="WebUrl" />
+      <xsd:enumeration value="WebXmlOrXsl" />
+      <xsd:enumeration value="WeekOfYear" />
+      <xsd:enumeration value="Weeks" />
+      <xsd:enumeration value="WinterSummerSeason" />
+      <xsd:enumeration value="Years" />
+    </xsd:restriction>
+  </xsd:simpleType>
```

##### `simpleType` **DimensionAttributeTypeEnumType**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Dimension > DimensionAttribute

```diff
--- definition/+++ spec/@@ -1,244 +1,436 @@-<xsd:simpleType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="DimensionAttributeTypeEnumType">
-    <xsd:restriction base="xsd:string">
-      <xsd:enumeration value="Account"/>
-      <xsd:enumeration value="AccountName"/>
-      <xsd:enumeration value="AccountNumber"/>
-      <xsd:enumeration value="AccountType"/>
-      <xsd:enumeration value="Address"/>
-      <xsd:enumeration value="AddressBuilding"/>
-      <xsd:enumeration value="AddressCity"/>
-      <xsd:enumeration value="AddressCountry"/>
-      <xsd:enumeration value="AddressFax"/>
-      <xsd:enumeration value="AddressFloor"/>
-      <xsd:enumeration value="AddressHouse"/>
-      <xsd:enumeration value="AddressPhone"/>
-      <xsd:enumeration value="AddressQuarter"/>
-      <xsd:enumeration value="AddressRoom"/>
-      <xsd:enumeration value="AddressStateOrProvince"/>
-      <xsd:enumeration value="AddressStreet"/>
-      <xsd:enumeration value="AddressZip"/>
-      <xsd:enumeration value="BomResource"/>
-      <xsd:enumeration value="Caption"/>
-      <xsd:enumeration value="CaptionAbbreviation"/>
-      <xsd:enumeration value="CaptionDescription"/>
-      <xsd:enumeration value="Channel"/>
-      <xsd:enumeration value="City"/>
-      <xsd:enumeration value="Company"/>
-      <xsd:enumeration value="Continent"/>
-      <xsd:enumeration value="Country"/>
-      <xsd:enumeration value="County"/>
-      <xsd:enumeration value="CurrencyDestination"/>
-      <xsd:enumeration value="CurrencyIsoCode"/>
-      <xsd:enumeration value="CurrencyName"/>
-      <xsd:enumeration value="CurrencySource"/>
-      <xsd:enumeration value="CustomerGroup"/>
-      <xsd:enumeration value="CustomerHousehold"/>
-      <xsd:enumeration value="Customers"/>
-      <xsd:enumeration value="Date"/>
-      <xsd:enumeration value="DateCanceled"/>
-      <xsd:enumeration value="DateDuration"/>
-      <xsd:enumeration value="DateEnded"/>
-      <xsd:enumeration value="DateModified"/>
-      <xsd:enumeration value="DateStart"/>
-      <xsd:enumeration value="DayOfHalfYear"/>
-      <xsd:enumeration value="DayOfMonth"/>
-      <xsd:enumeration value="DayOfQuarter"/>
-      <xsd:enumeration value="DayOfTenDays"/>
-      <xsd:enumeration value="DayOfTrimester"/>
-      <xsd:enumeration value="DayOfWeek"/>
-      <xsd:enumeration value="DayOfYear"/>
-      <xsd:enumeration value="Days"/>
-      <xsd:enumeration value="DeletedFlag"/>
-      <xsd:enumeration value="ExtendedType"/>
-      <xsd:enumeration value="FiscalDate"/>
-      <xsd:enumeration value="FiscalDayOfHalfYear"/>
-      <xsd:enumeration value="FiscalDayOfMonth"/>
-      <xsd:enumeration value="FiscalDayOfQuarter"/>
-      <xsd:enumeration value="FiscalDayOfTrimester"/>
-      <xsd:enumeration value="FiscalDayOfWeek"/>
-      <xsd:enumeration value="FiscalDayOfYear"/>
-      <xsd:enumeration value="FiscalHalfYears"/>
-      <xsd:enumeration value="FiscalHalfYearOfYear"/>
-      <xsd:enumeration value="FiscalMonths"/>
-      <xsd:enumeration value="FiscalMonthOfHalfYear"/>
-      <xsd:enumeration value="FiscalMonthOfQuarter"/>
-      <xsd:enumeration value="FiscalMonthOfTrimester"/>
-      <xsd:enumeration value="FiscalMonthOfYear"/>
-      <xsd:enumeration value="FiscalQuarters"/>
-      <xsd:enumeration value="FiscalQuarterOfHalfYear"/>
-      <xsd:enumeration value="FiscalQuarterOfYear"/>
-      <xsd:enumeration value="FiscalTrimesters"/>
-      <xsd:enumeration value="FiscalTrimesterOfYear"/>
-      <xsd:enumeration value="FiscalWeeks"/>
-      <xsd:enumeration value="FiscalWeekOfHalfYear"/>
-      <xsd:enumeration value="FiscalWeekOfMonth"/>
-      <xsd:enumeration value="FiscalWeekOfQuarter"/>
-      <xsd:enumeration value="FiscalWeekOfTrimester"/>
-      <xsd:enumeration value="FiscalWeekOfYear"/>
-      <xsd:enumeration value="FiscalYears"/>
-      <xsd:enumeration value="FormattingColor"/>
-      <xsd:enumeration value="FormattingFont"/>
-      <xsd:enumeration value="FormattingFontEffects"/>
-      <xsd:enumeration value="FormattingFontSize"/>
-      <xsd:enumeration value="FormattingOrder"/>
-      <xsd:enumeration value="FormattingSubtotal"/>
-      <xsd:enumeration value="GeoBoundaryBottom"/>
-      <xsd:enumeration value="GeoBoundaryFront"/>
-      <xsd:enumeration value="GeoBoundaryLeft"/>
-      <xsd:enumeration value="GeoBoundaryPolygon"/>
-      <xsd:enumeration value="GeoBoundaryRear"/>
-      <xsd:enumeration value="GeoBoundaryRight"/>
-      <xsd:enumeration value="GeoBoundaryTop"/>
-      <xsd:enumeration value="GeoCentroidX"/>
-      <xsd:enumeration value="GeoCentroidY"/>
-      <xsd:enumeration value="GeoCentroidZ"/>
-      <xsd:enumeration value="HalfYears"/>
-      <xsd:enumeration value="HalfYearOfYear"/>
-      <xsd:enumeration value="Hours"/>
-      <xsd:enumeration value="ID"/>
-      <xsd:enumeration value="Image"/>
-      <xsd:enumeration value="ImageBmp"/>
-      <xsd:enumeration value="ImageGif"/>
-      <xsd:enumeration value="ImageJpg"/>
-      <xsd:enumeration value="ImagePng"/>
-      <xsd:enumeration value="ImageTiff"/>
-      <xsd:enumeration value="ImageUrl"/>
-      <xsd:enumeration value="IsHoliday"/>
-      <xsd:enumeration value="Iso8601Date"/>
-      <xsd:enumeration value="Iso8601DayOfWeek"/>
-      <xsd:enumeration value="Iso8601DayOfYear"/>
-      <xsd:enumeration value="Iso8601Weeks"/>
-      <xsd:enumeration value="Iso8601WeekOfYear"/>
-      <xsd:enumeration value="Iso8601Years"/>
-      <xsd:enumeration value="IsPeakDay"/>
-      <xsd:enumeration value="IsWeekDay"/>
-      <xsd:enumeration value="IsWorkingDay"/>
-      <xsd:enumeration value="ManufacturingDate"/>
-      <xsd:enumeration value="ManufacturingDayOfHalfYear"/>
-      <xsd:enumeration value="ManufacturingDayOfMonth"/>
-      <xsd:enumeration value="ManufacturingDayOfQuarter"/>
-      <xsd:enumeration value="ManufacturingDayOfWeek"/>
-      <xsd:enumeration value="ManufacturingDayOfYear"/>
-      <xsd:enumeration value="ManufacturingHalfYears"/>
-      <xsd:enumeration value="ManufacturingHalfYearOfYear"/>
-      <xsd:enumeration value="ManufacturingMonths"/>
-      <xsd:enumeration value="ManufacturingMonthOfHalfYear"/>
-      <xsd:enumeration value="ManufacturingMonthOfQuarter"/>
-      <xsd:enumeration value="ManufacturingMonthOfYear"/>
-      <xsd:enumeration value="ManufacturingQuarters"/>
-      <xsd:enumeration value="ManufacturingQuarterOfHalfYear"/>
-      <xsd:enumeration value="ManufacturingQuarterOfYear"/>
-      <xsd:enumeration value="ManufacturingWeeks"/>
-      <xsd:enumeration value="ManufacturingWeekOfHalfYear"/>
-      <xsd:enumeration value="ManufacturingWeekOfMonth"/>
-      <xsd:enumeration value="ManufacturingWeekOfQuarter"/>
-      <xsd:enumeration value="ManufacturingWeekOfYear"/>
-      <xsd:enumeration value="ManufacturingYears"/>
-      <xsd:enumeration value="Minutes"/>
-      <xsd:enumeration value="Months"/>
-      <xsd:enumeration value="MonthOfHalfYear"/>
-      <xsd:enumeration value="MonthOfQuarter"/>
-      <xsd:enumeration value="MonthOfTrimester"/>
-      <xsd:enumeration value="MonthOfYear"/>
-      <xsd:enumeration value="OrganizationalUnit"/>
-      <xsd:enumeration value="OrgTitle"/>
-      <xsd:enumeration value="PercentOwnership"/>
-      <xsd:enumeration value="PercentVoteRight"/>
-      <xsd:enumeration value="Person"/>
-      <xsd:enumeration value="PersonContact"/>
-      <xsd:enumeration value="PersonDemographic"/>
-      <xsd:enumeration value="PersonFirstName"/>
-      <xsd:enumeration value="PersonFullName"/>
-      <xsd:enumeration value="PersonLastName"/>
-      <xsd:enumeration value="PersonMiddleName"/>
-      <xsd:enumeration value="PhysicalColor"/>
-      <xsd:enumeration value="PhysicalDensity"/>
-      <xsd:enumeration value="PhysicalDepth"/>
-      <xsd:enumeration value="PhysicalHeight"/>
-      <xsd:enumeration value="PhysicalSize"/>
-      <xsd:enumeration value="PhysicalVolume"/>
-      <xsd:enumeration value="PhysicalWeight"/>
-      <xsd:enumeration value="PhysicalWidth"/>
-      <xsd:enumeration value="Point"/>
-      <xsd:enumeration value="PostalCode"/>
-      <xsd:enumeration value="Product"/>
-      <xsd:enumeration value="ProductBrand"/>
-      <xsd:enumeration value="ProductCategory"/>
-      <xsd:enumeration value="ProductGroup"/>
-      <xsd:enumeration value="ProductSKU"/>
-      <xsd:enumeration value="Project"/>
-      <xsd:enumeration value="ProjectCode"/>
-      <xsd:enumeration value="ProjectCompletion"/>
-      <xsd:enumeration value="ProjectEndDate"/>
-      <xsd:enumeration value="ProjectName"/>
-      <xsd:enumeration value="ProjectStartDate"/>
-      <xsd:enumeration value="Promotion"/>
-      <xsd:enumeration value="QtyRangeHigh"/>
-      <xsd:enumeration value="QtyRangeLow"/>
-      <xsd:enumeration value="Quantitative"/>
-      <xsd:enumeration value="Quarters"/>
-      <xsd:enumeration value="QuarterOfHalfYear"/>
-      <xsd:enumeration value="QuarterOfYear"/>
-      <xsd:enumeration value="Rate"/>
-      <xsd:enumeration value="RateType"/>
-      <xsd:enumeration value="Region"/>
-      <xsd:enumeration value="Regular"/>
-      <xsd:enumeration value="RelationToParent"/>
-      <xsd:enumeration value="ReportingDate"/>
-      <xsd:enumeration value="ReportingDayOfHalfYear"/>
-      <xsd:enumeration value="ReportingDayOfMonth"/>
-      <xsd:enumeration value="ReportingDayOfQuarter"/>
-      <xsd:enumeration value="ReportingDayOfTrimester"/>
-      <xsd:enumeration value="ReportingDayOfWeek"/>
-      <xsd:enumeration value="ReportingDayOfYear"/>
-      <xsd:enumeration value="ReportingHalfYears"/>
-      <xsd:enumeration value="ReportingHalfYearOfYear"/>
-      <xsd:enumeration value="ReportingMonths"/>
-      <xsd:enumeration value="ReportingMonthOfHalfYear"/>
-      <xsd:enumeration value="ReportingMonthOfQuarter"/>
-      <xsd:enumeration value="ReportingMonthOfTrimester"/>
-      <xsd:enumeration value="ReportingMonthOfYear"/>
-      <xsd:enumeration value="ReportingQuarters"/>
-      <xsd:enumeration value="ReportingQuarterOfHalfYear"/>
-      <xsd:enumeration value="ReportingQuarterOfYear"/>
-      <xsd:enumeration value="ReportingTrimesters"/>
-      <xsd:enumeration value="ReportingTrimesterOfYear"/>
-      <xsd:enumeration value="ReportingWeeks"/>
-      <xsd:enumeration value="ReportingWeekOfHalfYear"/>
-      <xsd:enumeration value="ReportingWeekOfMonth"/>
-      <xsd:enumeration value="ReportingWeekOfQuarter"/>
-      <xsd:enumeration value="ReportingWeekOfTrimester"/>
-      <xsd:enumeration value="ReportingWeekOfYear"/>
-      <xsd:enumeration value="ReportingYears"/>
-      <xsd:enumeration value="Representative"/>
-      <xsd:enumeration value="RowNumber"/>
-      <xsd:enumeration value="ScdEndDate"/>
-      <xsd:enumeration value="ScdOriginalID"/>
-      <xsd:enumeration value="ScdStartDate"/>
-      <xsd:enumeration value="ScdStatus"/>
-      <xsd:enumeration value="Scenario"/>
-      <xsd:enumeration value="Seconds"/>
-      <xsd:enumeration value="Sequence"/>
-      <xsd:enumeration value="ShortCaption"/>
-      <xsd:enumeration value="StateOrProvince"/>
-      <xsd:enumeration value="TenDay"/>
-      <xsd:enumeration value="TenDayOfHalfYear"/>
-      <xsd:enumeration value="TenDayOfMonth"/>
-      <xsd:enumeration value="TenDayOfQuarter"/>
-      <xsd:enumeration value="TenDayOfTrimester"/>
-      <xsd:enumeration value="TenDayOfYear"/>
-      <xsd:enumeration value="Trimesters"/>
-      <xsd:enumeration value="TrimesterOfYear"/>
-      <xsd:enumeration value="UndefinedTime"/>
-      <xsd:enumeration value="Utility"/>
-      <xsd:enumeration value="Version"/>
-      <xsd:enumeration value="WebHtml"/>
-      <xsd:enumeration value="WebMailAlias"/>
-      <xsd:enumeration value="WebUrl"/>
-      <xsd:enumeration value="WebXmlOrXsl"/>
-      <xsd:enumeration value="WeekOfYear"/>
-      <xsd:enumeration value="Weeks"/>
-      <xsd:enumeration value="WinterSummerSeason"/>
-      <xsd:enumeration value="Years"/>
+<xsd:complexType name="DimensionAttribute">
+    <xsd:all>
+      <xsd:element name="Name" type="xsd:string"/>
+      <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="Type" minOccurs="0">
+        <xsd:complexType>
+          <xsd:simpleContent>
+            <xsd:extension base="DimensionAttributeTypeEnumType">
+              <xsd:attribute name="valuens" >
+                <xsd:simpleType>
+                  <xsd:restriction base="xsd:string">
+                    <xsd:enumeration value=
+             "http://schemas.microsoft.com/analysisservices/2010/engine/200/200" />
+                  </xsd:restriction>
+                </xsd:simpleType>
+              </xsd:attribute>
+            </xsd:extension>
+          </xsd:simpleContent>
+        </xsd:complexType>
+      </xsd:element>
+      <xsd:element name="Usage" minOccurs="0">
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Regular" />
+            <xsd:enumeration value="Key" />
+            <xsd:enumeration value="Parent" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="Source" type="Binding" minOccurs="0" />
+      <xsd:element name="EstimatedCount" type="xsd:long" minOccurs="0"/>
+      <xsd:element name="KeyColumns" >
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="KeyColumn"  type="DataItem" minOccurs="0"
+                         maxOccurs="unbounded"/>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+      <xsd:element name="NameColumn" type="DataItem" minOccurs="0" />
+      <xsd:element name="ValueColumn" type="DataItem" minOccurs="0" />
+      <xsd:element name="Translations" minOccurs="0">
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="Translation"  type="AttributeTranslation" minOccurs="0"
+                         maxOccurs="unbounded"/>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+      <xsd:element name="AttributeRelationships" minOccurs="0">
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="AttributeRelationship"  type="AttributeRelationship"
+                         minOccurs="0" maxOccurs="unbounded"/>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+      <xsd:element name="DiscretizationMethod" minOccurs="0">
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="Automatic" />
+            <xsd:enumeration value="EqualAreas" />
+            <xsd:enumeration value="Clusters" />
+            <xsd:enumeration value="Thresholds" />
+            <xsd:enumeration value="UserDefined" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="DiscretizationBucketCount" type="xsd:integer" minOccurs="0"/>
+      <xsd:element name="RootMemberIf" minOccurs="0">
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="ParentIsBlankSelfOrMissing" />
+            <xsd:enumeration value="ParentIsBlank" />
+            <xsd:enumeration value="ParentIsSelf" />
+            <xsd:enumeration value="ParentIsMissing" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="OrderBy" minOccurs="0">
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Key" />
+            <xsd:enumeration value="Name" />
+            <xsd:enumeration value="AttributeKey" />
+            <xsd:enumeration value="AttributeName" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="DefaultMember" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="OrderByAttributeID" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="SkippedLevelsColumn" type="DataItem" minOccurs="0" />
+      <xsd:element name="NamingTemplate" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="MembersWithData"  minOccurs="0">
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="NonLeafDataHidden" />
+            <xsd:enumeration value="NonLeafDataVisible" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="MembersWithDataCaption" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="NamingTemplateTranslations" minOccurs="0">
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="NamingTemplateTranslation"  type="Translation"
+                         minOccurs="0" maxOccurs="unbounded"/>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+      <xsd:element name="CustomRollupColumn" type="DataItem" minOccurs="0" />
+      <xsd:element name="CustomRollupPropertiesColumn" type="DataItem"
+                   minOccurs="0" />
+      <xsd:element name="UnaryOperatorColumn" type="DataItem" minOccurs="0" />
+      <xsd:element name="AttributeHierarchyOrdered" type="xsd:boolean"
+                   minOccurs="0"/>
+      <xsd:element name="MemberNamesUnique" type="xsd:boolean" minOccurs="0"/>
+      <xsd:element name="IsAggregatable" type="xsd:boolean" minOccurs="0"/>
+      <xsd:element name="AttributeHierarchyEnabled" type="xsd:boolean"
+                   minOccurs="0"/>
+      <xsd:element name="AttributeHierarchyOptimizedState" minOccurs="0">
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="FullyOptimized" />
+            <xsd:enumeration value="NotOptimized" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="AttributeHierarchyVisible" type="xsd:boolean"
+                   minOccurs="0"/>
+      <xsd:element name="AttributeHierarchyDisplayFolder" type="xsd:string"
+                   minOccurs="0"/>
+      <xsd:element name="KeyUniquenessGuarantee" type="xsd:boolean"
+                   minOccurs="0"/>
+      <xsd:element name="GroupingBehavior" minOccurs="0">
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="EncourageGrouping" />
+            <xsd:enumeration value="DiscourageGrouping" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="InstanceSelection" minOccurs="0">
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="DropDown" />
+            <xsd:enumeration value="List" />
+            <xsd:enumeration value="FilteredList" />
+            <xsd:enumeration value="MandatoryFilter" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="Annotations" minOccurs="0">
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+      <xsd:element name="ProcessingState" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Processed" />
+            <xsd:enumeration value="Unprocessed" />
+            <xsd:enumeration value="InvalidExpression" />
+            <xsd:enumeration value="CalculationError" />
+            <xsd:enumeration value="DependencyError" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="AttributeHierarchyProcessingState"
+                   type="eng300:AttributeHierarchyProcessingState"
+                   minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Processed" />
+            <xsd:enumeration value="Unprocessed" />
+            <xsd:enumeration value="DependencyError" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name ="VisualizationProperties"
+                   type="eng300:DimensionAttributeVisualizationProperties"
+                   minOccurs="0" maxOccurs="1"/>
+      <xsd:element name ="ExtendedType" type="xsd:string" minOccurs="0" maxOccurs="1"/>
+    </xsd:all>
+  </xsd:complexType>
+
+  <xsd:simpleType name="DimensionAttributeTypeEnumType">
+    <xsd:restriction base="xsd:string" >
+      <xsd:enumeration value="Account" />
+      <xsd:enumeration value="AccountName" />
+      <xsd:enumeration value="AccountNumber" />
+      <xsd:enumeration value="AccountType" />
+      <xsd:enumeration value="Address" />
+      <xsd:enumeration value="AddressBuilding" />
+      <xsd:enumeration value="AddressCity" />
+      <xsd:enumeration value="AddressCountry" />
+      <xsd:enumeration value="AddressFax" />
+      <xsd:enumeration value="AddressFloor" />
+      <xsd:enumeration value="AddressHouse" />
+      <xsd:enumeration value="AddressPhone" />
+      <xsd:enumeration value="AddressQuarter" />
+      <xsd:enumeration value="AddressRoom" />
+      <xsd:enumeration value="AddressStateOrProvince" />
+      <xsd:enumeration value="AddressStreet" />
+      <xsd:enumeration value="AddressZip" />
+      <xsd:enumeration value="BomResource" />
+      <xsd:enumeration value="Caption" />
+      <xsd:enumeration value="CaptionAbbreviation" />
+      <xsd:enumeration value="CaptionDescription" />
+      <xsd:enumeration value="Channel" />
+      <xsd:enumeration value="City" />
+      <xsd:enumeration value="Company" />
+      <xsd:enumeration value="Continent" />
+      <xsd:enumeration value="Country" />
+      <xsd:enumeration value="County" />
+      <xsd:enumeration value="CurrencyDestination" />
+      <xsd:enumeration value="CurrencyIsoCode" />
+      <xsd:enumeration value="CurrencyName" />
+      <xsd:enumeration value="CurrencySource" />
+      <xsd:enumeration value="CustomerGroup" />
+      <xsd:enumeration value="CustomerHousehold" />
+      <xsd:enumeration value="Customers" />
+      <xsd:enumeration value="Date" />
+      <xsd:enumeration value="DateCanceled" />
+      <xsd:enumeration value="DateDuration" />
+      <xsd:enumeration value="DateEnded" />
+      <xsd:enumeration value="DateModified" />
+      <xsd:enumeration value="DateStart" />
+      <xsd:enumeration value="DayOfHalfYear" />
+      <xsd:enumeration value="DayOfMonth" />
+      <xsd:enumeration value="DayOfQuarter" />
+      <xsd:enumeration value="DayOfTenDays" />
+      <xsd:enumeration value="DayOfTrimester" />
+      <xsd:enumeration value="DayOfWeek" />
+      <xsd:enumeration value="DayOfYear" />
+      <xsd:enumeration value="Days" />
+      <xsd:enumeration value="DeletedFlag" />
+      <xsd:enumeration value="ExtendedType" />
+      <xsd:enumeration value="FiscalDate" />
+      <xsd:enumeration value="FiscalDayOfHalfYear" />
+      <xsd:enumeration value="FiscalDayOfMonth" />
+      <xsd:enumeration value="FiscalDayOfQuarter" />
+      <xsd:enumeration value="FiscalDayOfTrimester" />
+      <xsd:enumeration value="FiscalDayOfWeek" />
+      <xsd:enumeration value="FiscalDayOfYear" />
+      <xsd:enumeration value="FiscalHalfYears" />
+      <xsd:enumeration value="FiscalHalfYearOfYear" />
+      <xsd:enumeration value="FiscalMonths" />
+      <xsd:enumeration value="FiscalMonthOfHalfYear" />
+      <xsd:enumeration value="FiscalMonthOfQuarter" />
+      <xsd:enumeration value="FiscalMonthOfTrimester" />
+      <xsd:enumeration value="FiscalMonthOfYear" />
+      <xsd:enumeration value="FiscalQuarters" />
+      <xsd:enumeration value="FiscalQuarterOfHalfYear" />
+      <xsd:enumeration value="FiscalQuarterOfYear" />
+      <xsd:enumeration value="FiscalTrimesters" />
+      <xsd:enumeration value="FiscalTrimesterOfYear" />
+      <xsd:enumeration value="FiscalWeeks" />
+      <xsd:enumeration value="FiscalWeekOfHalfYear" />
+      <xsd:enumeration value="FiscalWeekOfMonth" />
+      <xsd:enumeration value="FiscalWeekOfQuarter" />
+      <xsd:enumeration value="FiscalWeekOfTrimester" />
+      <xsd:enumeration value="FiscalWeekOfYear" />
+      <xsd:enumeration value="FiscalYears" />
+      <xsd:enumeration value="FormattingColor" />
+      <xsd:enumeration value="FormattingFont" />
+      <xsd:enumeration value="FormattingFontEffects" />
+      <xsd:enumeration value="FormattingFontSize" />
+      <xsd:enumeration value="FormattingOrder" />
+      <xsd:enumeration value="FormattingSubtotal" />
+      <xsd:enumeration value="GeoBoundaryBottom" />
+      <xsd:enumeration value="GeoBoundaryFront" />
+      <xsd:enumeration value="GeoBoundaryLeft" />
+      <xsd:enumeration value="GeoBoundaryPolygon" />
+      <xsd:enumeration value="GeoBoundaryRear" />
+      <xsd:enumeration value="GeoBoundaryRight" />
+      <xsd:enumeration value="GeoBoundaryTop" />
+      <xsd:enumeration value="GeoCentroidX" />
+      <xsd:enumeration value="GeoCentroidY" />
+      <xsd:enumeration value="GeoCentroidZ" />
+      <xsd:enumeration value="HalfYears" />
+      <xsd:enumeration value="HalfYearOfYear" />
+      <xsd:enumeration value="Hours" />
+      <xsd:enumeration value="ID" />
+      <xsd:enumeration value="Image" />
+      <xsd:enumeration value="ImageBmp" />
+      <xsd:enumeration value="ImageGif" />
+      <xsd:enumeration value="ImageJpg" />
+      <xsd:enumeration value="ImagePng" />
+      <xsd:enumeration value="ImageTiff" />
+      <xsd:enumeration value="ImageUrl" />
+      <xsd:enumeration value="IsHoliday" />
+      <xsd:enumeration value="Iso8601Date" />
+      <xsd:enumeration value="Iso8601DayOfWeek" />
+      <xsd:enumeration value="Iso8601DayOfYear" />
+      <xsd:enumeration value="Iso8601Weeks" />
+      <xsd:enumeration value="Iso8601WeekOfYear" />
+      <xsd:enumeration value="Iso8601Years" />
+      <xsd:enumeration value="IsPeakDay" />
+      <xsd:enumeration value="IsWeekDay" />
+      <xsd:enumeration value="IsWorkingDay" />
+      <xsd:enumeration value="ManufacturingDate" />
+      <xsd:enumeration value="ManufacturingDayOfHalfYear" />
+      <xsd:enumeration value="ManufacturingDayOfMonth" />
+      <xsd:enumeration value="ManufacturingDayOfQuarter" />
+      <xsd:enumeration value="ManufacturingDayOfWeek" />
+      <xsd:enumeration value="ManufacturingDayOfYear" />
+      <xsd:enumeration value="ManufacturingHalfYears" />
+      <xsd:enumeration value="ManufacturingHalfYearOfYear" />
+      <xsd:enumeration value="ManufacturingMonths" />
+      <xsd:enumeration value="ManufacturingMonthOfHalfYear" />
+      <xsd:enumeration value="ManufacturingMonthOfQuarter" />
+      <xsd:enumeration value="ManufacturingMonthOfYear" />
+      <xsd:enumeration value="ManufacturingQuarters" />
+      <xsd:enumeration value="ManufacturingQuarterOfHalfYear" />
+      <xsd:enumeration value="ManufacturingQuarterOfYear" />
+      <xsd:enumeration value="ManufacturingWeeks" />
+      <xsd:enumeration value="ManufacturingWeekOfHalfYear" />
+      <xsd:enumeration value="ManufacturingWeekOfMonth" />
+      <xsd:enumeration value="ManufacturingWeekOfQuarter" />
+      <xsd:enumeration value="ManufacturingWeekOfYear" />
+      <xsd:enumeration value="ManufacturingYears" />
+      <xsd:enumeration value="Minutes" />
+      <xsd:enumeration value="Months" />
+      <xsd:enumeration value="MonthOfHalfYear" />
+      <xsd:enumeration value="MonthOfQuarter" />
+      <xsd:enumeration value="MonthOfTrimester" />
+      <xsd:enumeration value="MonthOfYear" />
+      <xsd:enumeration value="OrganizationalUnit" />
+      <xsd:enumeration value="OrgTitle" />
+      <xsd:enumeration value="PercentOwnership" />
+      <xsd:enumeration value="PercentVoteRight" />
+      <xsd:enumeration value="Person" />
+      <xsd:enumeration value="PersonContact" />
+      <xsd:enumeration value="PersonDemographic" />
+      <xsd:enumeration value="PersonFirstName" />
+      <xsd:enumeration value="PersonFullName" />
+      <xsd:enumeration value="PersonLastName" />
+      <xsd:enumeration value="PersonMiddleName" />
+      <xsd:enumeration value="PhysicalColor" />
+      <xsd:enumeration value="PhysicalDensity" />
+      <xsd:enumeration value="PhysicalDepth" />
+      <xsd:enumeration value="PhysicalHeight" />
+      <xsd:enumeration value="PhysicalSize" />
+      <xsd:enumeration value="PhysicalVolume" />
+      <xsd:enumeration value="PhysicalWeight" />
+      <xsd:enumeration value="PhysicalWidth" />
+      <xsd:enumeration value="Point" />
+      <xsd:enumeration value="PostalCode" />
+      <xsd:enumeration value="Product" />
+      <xsd:enumeration value="ProductBrand" />
+      <xsd:enumeration value="ProductCategory" />
+      <xsd:enumeration value="ProductGroup" />
+      <xsd:enumeration value="ProductSKU" />
+      <xsd:enumeration value="Project" />
+      <xsd:enumeration value="ProjectCode" />
+      <xsd:enumeration value="ProjectCompletion" />
+      <xsd:enumeration value="ProjectEndDate" />
+      <xsd:enumeration value="ProjectName" />
+      <xsd:enumeration value="ProjectStartDate" />
+      <xsd:enumeration value="Promotion" />
+      <xsd:enumeration value="QtyRangeHigh" />
+      <xsd:enumeration value="QtyRangeLow" />
+      <xsd:enumeration value="Quantitative" />
+      <xsd:enumeration value="Quarters" />
+      <xsd:enumeration value="QuarterOfHalfYear" />
+      <xsd:enumeration value="QuarterOfYear" />
+      <xsd:enumeration value="Rate" />
+      <xsd:enumeration value="RateType" />
+      <xsd:enumeration value="Region" />
+      <xsd:enumeration value="Regular" />
+      <xsd:enumeration value="RelationToParent" />
+      <xsd:enumeration value="ReportingDate" />
+      <xsd:enumeration value="ReportingDayOfHalfYear" />
+      <xsd:enumeration value="ReportingDayOfMonth" />
+      <xsd:enumeration value="ReportingDayOfQuarter" />
+      <xsd:enumeration value="ReportingDayOfTrimester" />
+      <xsd:enumeration value="ReportingDayOfWeek" />
+      <xsd:enumeration value="ReportingDayOfYear" />
+      <xsd:enumeration value="ReportingHalfYears" />
+      <xsd:enumeration value="ReportingHalfYearOfYear" />
+      <xsd:enumeration value="ReportingMonths" />
+      <xsd:enumeration value="ReportingMonthOfHalfYear" />
+      <xsd:enumeration value="ReportingMonthOfQuarter" />
+      <xsd:enumeration value="ReportingMonthOfTrimester" />
+      <xsd:enumeration value="ReportingMonthOfYear" />
+      <xsd:enumeration value="ReportingQuarters" />
+      <xsd:enumeration value="ReportingQuarterOfHalfYear" />
+      <xsd:enumeration value="ReportingQuarterOfYear" />
+      <xsd:enumeration value="ReportingTrimesters" />
+      <xsd:enumeration value="ReportingTrimesterOfYear" />
+      <xsd:enumeration value="ReportingWeeks" />
+      <xsd:enumeration value="ReportingWeekOfHalfYear" />
+      <xsd:enumeration value="ReportingWeekOfMonth" />
+      <xsd:enumeration value="ReportingWeekOfQuarter" />
+      <xsd:enumeration value="ReportingWeekOfTrimester" />
+      <xsd:enumeration value="ReportingWeekOfYear" />
+      <xsd:enumeration value="ReportingYears" />
+      <xsd:enumeration value="Representative" />
+      <xsd:enumeration value="RowNumber" />
+      <xsd:enumeration value="ScdEndDate" />
+      <xsd:enumeration value="ScdOriginalID" />
+      <xsd:enumeration value="ScdStartDate" />
+      <xsd:enumeration value="ScdStatus" />
+      <xsd:enumeration value="Scenario" />
+      <xsd:enumeration value="Seconds" />
+      <xsd:enumeration value="Sequence" />
+      <xsd:enumeration value="ShortCaption" />
+      <xsd:enumeration value="StateOrProvince" />
+      <xsd:enumeration value="TenDay" />
+      <xsd:enumeration value="TenDayOfHalfYear" />
+      <xsd:enumeration value="TenDayOfMonth" />
+      <xsd:enumeration value="TenDayOfQuarter" />
+      <xsd:enumeration value="TenDayOfTrimester" />
+      <xsd:enumeration value="TenDayOfYear" />
+      <xsd:enumeration value="Trimesters" />
+      <xsd:enumeration value="TrimesterOfYear" />
+      <xsd:enumeration value="UndefinedTime" />
+      <xsd:enumeration value="Utility" />
+      <xsd:enumeration value="Version" />
+      <xsd:enumeration value="WebHtml" />
+      <xsd:enumeration value="WebMailAlias" />
+      <xsd:enumeration value="WebUrl" />
+      <xsd:enumeration value="WebXmlOrXsl" />
+      <xsd:enumeration value="WeekOfYear" />
+      <xsd:enumeration value="Weeks" />
+      <xsd:enumeration value="WinterSummerSeason" />
+      <xsd:enumeration value="Years" />
     </xsd:restriction>
   </xsd:simpleType>
```

##### `complexType` **AttributeRelationship**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Dimension > DimensionAttribute > AttributeRelationship

```diff
--- definition/+++ spec/@@ -1,51 +1,53 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AttributeRelationship">
+<xsd:complexType name="AttributeRelationship">
     <xsd:all>
-      <xsd:element name="AttributeID" type="xsd:string"/>
+      <xsd:element name="AttributeID" type="xsd:string" />
       <xsd:element name="RelationshipType" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Rigid"/>
-            <xsd:enumeration value="Flexible"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Rigid" />
+            <xsd:enumeration value="Flexible" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="Cardinality" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Many"/>
-            <xsd:enumeration value="One"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Many" />
+            <xsd:enumeration value="One" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="Optionality" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Mandatory"/>
-            <xsd:enumeration value="Optional"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Mandatory" />
+            <xsd:enumeration value="Optional" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="OverrideBehavior" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="None"/>
-            <xsd:enumeration value="Strong"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="Strong" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="Name" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Visible" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="Translations" minOccurs="0">
+      <xsd:element name="Name" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Visible" type="xsd:boolean"  minOccurs="0" />
+      <xsd:element name="Translations"  minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Translation" type="Translation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Translation"  type="Translation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **Level**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Dimension > Hierarchy > Level

```diff
--- definition/+++ spec/@@ -1,31 +1,33 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Level">
+<xsd:complexType name="Level">
     <xsd:all>
-      <xsd:element name="Name" type="xsd:string"/>
+      <xsd:element name="Name" type="xsd:string" />
       <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
       <xsd:element name="SourceAttributeID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="HideMemberIf" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Never"/>
-            <xsd:enumeration value="OnlyChildWithNoName"/>
-            <xsd:enumeration value="OnlyChildWithParentName"/>
-            <xsd:enumeration value="NoName"/>
-            <xsd:enumeration value="ParentName"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Never" />
+            <xsd:enumeration value="OnlyChildWithNoName" />
+            <xsd:enumeration value="OnlyChildWithParentName" />
+            <xsd:enumeration value="NoName" />
+            <xsd:enumeration value="ParentName" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="Translations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Translation" type="Translation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Translation"  type="Translation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **Annotation**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Dimension > Relationship

```diff
--- definition/+++ spec/@@ -1,14 +1,80 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Annotation">
+<xsd:complexType name="Relationship">
+    <xsd:sequence>
+      <xsd:element name ="ID" type="xsd:string" />
+      <xsd:element name="Visible" type="xsd:boolean" />
+      <xsd:element name="FromRelationshipEnd" type="eng300_300:RelationshipEnd" />
+      <xsd:element name="ToRelationshipEnd" type="eng300_300:RelationshipEnd" />
+    </xsd:sequence>
+  </xsd:complexType>
+
+  <xsd:complexType name="RelationshipEnd">
+    <xsd:sequence>
+      <xsd:element name="Role" type="xsd:string"/>
+      <xsd:element name="Multiplicity" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string">
+            <xsd:enumeration value="One" />
+            <xsd:enumeration value="Many" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="DimensionID" type="xsd:string"/>
+      <xsd:element name="Attributes" minOccurs="0" >
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="Attribute" minOccurs="0" maxOccurs="unbounded">
+              <xsd:complexType>
+                <xsd:sequence>
+                  <xsd:element name="AttributeID" type="xsd:string"/>
+                </xsd:sequence>
+              </xsd:complexType>
+            </xsd:element>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+      <xsd:element name="Translations" minOccurs="0">
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="Translation"  type="eng300_300:RelationshipEndTranslation"
+                         minOccurs="0" maxOccurs="unbounded"/>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+      <xsd:element name="VisualizationProperties"
+                         type="eng300:RelationshipEndVisualizationProperties"
+                         minOccurs="0" maxOccurs="1" />
+    </xsd:sequence>
+  </xsd:complexType>
+
+  <xsd:complexType name="RelationshipEndTranslation">
+    <xsd:all>
+      <xsd:element name="Language" type="xsd:unsignedInt"/>
+      <xsd:element name="Caption" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="CollectionCaption" type="xsd:string"/>
+      <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="DisplayFolder" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="Annotations"   minOccurs="0">
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="Annotation" type="eng300_300:Annotation"
+                         minOccurs="0" maxOccurs="unbounded"/>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+    </xsd:all>
+  </xsd:complexType>
+
+  <xsd:complexType name="Annotation">
     <xsd:all>
       <xsd:element name="Name" type="xsd:string"/>
       <xsd:element name="Visibility" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="SchemaRowset"/>
-            <xsd:enumeration value="None"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="SchemaRowset" />
+            <xsd:enumeration value="None" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="Value" type="xsd:anyType" minOccurs="0"/>
+      <xsd:element name="Value" type="xsd:anyType" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **CubeDimension**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Cube > CubeDimension

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="CubeDimension">
+<xsd:complexType name="CubeDimension">
     <xsd:all>
       <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="Name" type="xsd:string" minOccurs="0"/>
@@ -6,7 +6,8 @@       <xsd:element name="Translations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Translation" type="Translation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Translation"  type="Translation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
@@ -15,47 +16,50 @@       <xsd:element name="AllMemberAggregationUsage" minOccurs="0">
         <xsd:simpleType>
           <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Full"/>
-            <xsd:enumeration value="None"/>
-            <xsd:enumeration value="Unrestricted"/>
-            <xsd:enumeration value="Default"/>
+            <xsd:enumeration value="Full" />
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="Unrestricted" />
+            <xsd:enumeration value="Default" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="HierarchyUniqueNameStyle" minOccurs="0">
+      <xsd:element name="HierarchyUniqueNameStyle"  minOccurs="0">
         <xsd:simpleType>
           <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="IncludeDimensionName"/>
-            <xsd:enumeration value="ExcludeDimensionName"/>
+            <xsd:enumeration value="IncludeDimensionName" />
+            <xsd:enumeration value="ExcludeDimensionName" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="MemberUniqueNameStyle" minOccurs="0">
         <xsd:simpleType>
           <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Native"/>
-            <xsd:enumeration value="NamePath"/>
+            <xsd:enumeration value="Native" />
+            <xsd:enumeration value="NamePath" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="Attributes" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Attribute" type="CubeAttribute" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Attribute"  type="CubeAttribute" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Hierarchies" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Hierarchy" type="CubeHierarchy" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Hierarchy"  type="CubeHierarchy" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **CubeAttribute**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Cube > CubeAttribute

```diff
--- definition/+++ spec/@@ -1,21 +1,21 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="CubeAttribute">
+<xsd:complexType name="CubeAttribute">
     <xsd:all>
       <xsd:element name="AttributeID" type="xsd:string"/>
-      <xsd:element name="AggregationUsage" minOccurs="0">
+      <xsd:element name="AggregationUsage"  minOccurs="0">
         <xsd:simpleType>
           <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Full"/>
-            <xsd:enumeration value="None"/>
-            <xsd:enumeration value="Unrestricted"/>
-            <xsd:enumeration value="Default"/>
+            <xsd:enumeration value="Full" />
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="Unrestricted" />
+            <xsd:enumeration value="Default" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="AttributeHierarchyOptimizedState" minOccurs="0">
         <xsd:simpleType>
           <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="FullyOptimized"/>
-            <xsd:enumeration value="NotOptimized"/>
+            <xsd:enumeration value="FullyOptimized" />
+            <xsd:enumeration value="NotOptimized" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
@@ -24,7 +24,8 @@       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **CubeHierarchy**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Cube > CubeHierarchy

```diff
--- definition/+++ spec/@@ -1,11 +1,11 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="CubeHierarchy">
+<xsd:complexType name="CubeHierarchy">
     <xsd:all>
       <xsd:element name="HierarchyID" type="xsd:string"/>
       <xsd:element name="OptimizedState" minOccurs="0">
         <xsd:simpleType>
           <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="FullyOptimized"/>
-            <xsd:enumeration value="NotOptimized"/>
+            <xsd:enumeration value="FullyOptimized" />
+            <xsd:enumeration value="NotOptimized" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
@@ -14,7 +14,8 @@       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **Kpi**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Cube > Kpi

```diff
--- definition/+++ spec/@@ -1,12 +1,13 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Kpi">
+<xsd:complexType name="Kpi">
     <xsd:all>
       <xsd:element name="Name" type="xsd:string"/>
       <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Translations" minOccurs="0">
+      <xsd:element name="Translations"  minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Translation" type="Translation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Translation"  type="Translation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
@@ -21,10 +22,11 @@       <xsd:element name="StatusGraphic" type="xsd:string" minOccurs="0"/>
       <xsd:element name="CurrentTimeMember" type="xsd:string" minOccurs="0"/>
       <xsd:element name="ParentKpiID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Annotations" minOccurs="0">
+      <xsd:element name="Annotations"  minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation" type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **Action**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Cube > Action

```diff
--- definition/+++ spec/@@ -1 +1 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Action" abstract="true"/>+<xsd:complexType name="Action" abstract="true" />
```

##### `complexType` **StandardAction**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Cube > Action > StandardAction

```diff
--- definition/+++ spec/@@ -1,8 +1,8 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="StandardAction">
+<xsd:complexType name="StandardAction">
     <xsd:complexContent>
       <xsd:extension base="Action">
         <xsd:all>
-          <!--These elements are inherited from Action -->
+          <!--These elements are inherited from Action-->
           <xsd:element name="Name" type="xsd:string"/>
           <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
           <xsd:element name="Caption" type="xsd:string" minOccurs="0"/>
@@ -10,63 +10,65 @@           <xsd:element name="Translations" minOccurs="0">
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="Translation" type="Translation" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="Translation"  type="Translation" minOccurs="0"
+                             maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>
-          <xsd:element name="TargetType">
+          <xsd:element name="TargetType" >
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="Cube"/>
-                <xsd:enumeration value="Cells"/>
-                <xsd:enumeration value="Set"/>
-                <xsd:enumeration value="Hierarchy"/>
-                <xsd:enumeration value="Level"/>
-                <xsd:enumeration value="DimensionMembers"/>
-                <xsd:enumeration value="HierarchyMembers"/>
-                <xsd:enumeration value="LevelMembers"/>
-                <xsd:enumeration value="AttributeMembers"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="Cube" />
+                <xsd:enumeration value="Cells" />
+                <xsd:enumeration value="Set" />
+                <xsd:enumeration value="Hierarchy" />
+                <xsd:enumeration value="Level" />
+                <xsd:enumeration value="DimensionMembers" />
+                <xsd:enumeration value="HierarchyMembers" />
+                <xsd:enumeration value="LevelMembers" />
+                <xsd:enumeration value="AttributeMembers" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="Target" type="xsd:string" minOccurs="0"/>
           <xsd:element name="Condition" type="xsd:string" minOccurs="0"/>
-          <xsd:element name="Type">
+          <xsd:element name="Type" >
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="Url"/>
-                <xsd:enumeration value="Html"/>
-                <xsd:enumeration value="Statement"/>
-                <xsd:enumeration value="DrillThrough"/>
-                <xsd:enumeration value="Dataset"/>
-                <xsd:enumeration value="Rowset"/>
-                <xsd:enumeration value="CommandLine"/>
-                <xsd:enumeration value="Proprietary"/>
-                <xsd:enumeration value="Report"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="Url" />
+                <xsd:enumeration value="Html" />
+                <xsd:enumeration value="Statement" />
+                <xsd:enumeration value="DrillThrough" />
+                <xsd:enumeration value="Dataset" />
+                <xsd:enumeration value="Rowset" />
+                <xsd:enumeration value="CommandLine" />
+                <xsd:enumeration value="Proprietary" />
+                <xsd:enumeration value="Report" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="Invocation" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="Interactive"/>
-                <xsd:enumeration value="OnOpen"/>
-                <xsd:enumeration value="Batch"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="Interactive" />
+                <xsd:enumeration value="OnOpen" />
+                <xsd:enumeration value="Batch" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="Application" type="xsd:string" minOccurs="0"/>
           <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-          <xsd:element name="Annotations" minOccurs="0">
+          <xsd:element name="Annotations" minOccurs="0" >
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                             maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>
-          <!--Extended elements for StandardAction object -->
+          <!--Extended elements for StandardAction object-->
           <xsd:element name="Expression" type="xsd:string"/>
         </xsd:all>
-      </xsd:extension>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **ReportAction**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Cube > Action > ReportAction

```diff
--- definition/+++ spec/@@ -1,8 +1,8 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ReportAction">
+<xsd:complexType name="ReportAction">
     <xsd:complexContent>
       <xsd:extension base="Action">
         <xsd:all>
-          <!--These elements are inherited from Action -->
+          <!--These elements are inherited from Action-->
           <xsd:element name="Name" type="xsd:string"/>
           <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
           <xsd:element name="Caption" type="xsd:string" minOccurs="0"/>
@@ -10,78 +10,83 @@           <xsd:element name="Translations" minOccurs="0">
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="Translation" type="Translation" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="Translation"  type="Translation" minOccurs="0"
+                             maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>
-          <xsd:element name="TargetType">
+          <xsd:element name="TargetType" >
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="Cube"/>
-                <xsd:enumeration value="Cells"/>
-                <xsd:enumeration value="Set"/>
-                <xsd:enumeration value="Hierarchy"/>
-                <xsd:enumeration value="Level"/>
-                <xsd:enumeration value="DimensionMembers"/>
-                <xsd:enumeration value="HierarchyMembers"/>
-                <xsd:enumeration value="LevelMembers"/>
-                <xsd:enumeration value="AttributeMembers"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="Cube" />
+                <xsd:enumeration value="Cells" />
+                <xsd:enumeration value="Set" />
+                <xsd:enumeration value="Hierarchy" />
+                <xsd:enumeration value="Level" />
+                <xsd:enumeration value="DimensionMembers" />
+                <xsd:enumeration value="HierarchyMembers" />
+                <xsd:enumeration value="LevelMembers" />
+                <xsd:enumeration value="AttributeMembers" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="Target" type="xsd:string" minOccurs="0"/>
           <xsd:element name="Condition" type="xsd:string" minOccurs="0"/>
-          <xsd:element name="Type">
+          <xsd:element name="Type" >
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="Url"/>
-                <xsd:enumeration value="Html"/>
-                <xsd:enumeration value="Statement"/>
-                <xsd:enumeration value="Drillthrough"/>
-                <xsd:enumeration value="Dataset"/>
-                <xsd:enumeration value="Rowset"/>
-                <xsd:enumeration value="CommandLine"/>
-                <xsd:enumeration value="Proprietary"/>
-                <xsd:enumeration value="Report"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="Url" />
+                <xsd:enumeration value="Html" />
+                <xsd:enumeration value="Statement" />
+                <xsd:enumeration value="Drillthrough" />
+                <xsd:enumeration value="Dataset" />
+                <xsd:enumeration value="Rowset" />
+                <xsd:enumeration value="CommandLine" />
+                <xsd:enumeration value="Proprietary" />
+                <xsd:enumeration value="Report" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="Invocation" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="Interactive"/>
-                <xsd:enumeration value="OnOpen"/>
-                <xsd:enumeration value="Batch"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="Interactive" />
+                <xsd:enumeration value="OnOpen" />
+                <xsd:enumeration value="Batch" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="Application" type="xsd:string" minOccurs="0"/>
           <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-          <xsd:element name="Annotations" minOccurs="0">
+          <xsd:element name="Annotations" minOccurs="0" >
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                             maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>
-          <!--Extended elements for ReportAction object -->
+          <!--Extended elements for ReportAction object-->
           <xsd:element name="ReportServer" type="xsd:string"/>
           <xsd:element name="Path" type="xsd:string" minOccurs="0"/>
           <xsd:element name="ReportParameters" minOccurs="0">
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="ReportParameter" type="ReportParameter" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="ReportParameter" type="ReportParameter"
+                             minOccurs="0" maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>
           <xsd:element name="ReportFormatParameters" minOccurs="0">
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="ReportFormatParameter" type="ReportFormatParameter" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="ReportFormatParameter"
+                             type="ReportFormatParameter"
+                             minOccurs="0" maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>
         </xsd:all>
-      </xsd:extension>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **ReportParameter**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Cube > Action > ReportAction > ReportParameter

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ReportParameter">
+<xsd:complexType name="ReportParameter">
     <xsd:all>
       <xsd:element name="Name" type="xsd:string"/>
       <xsd:element name="Value" type="xsd:string"/>

```

##### `complexType` **ReportFormatParameter**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Cube > Action > ReportAction > ReportFormatParameter

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ReportFormatParameter">
+<xsd:complexType name="ReportFormatParameter">
     <xsd:all>
       <xsd:element name="Name" type="xsd:string"/>
       <xsd:element name="Value" type="xsd:string"/>

```

##### `complexType` **DrillThroughAction**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Cube > Action > DrillThroughAction

```diff
--- definition/+++ spec/@@ -1,8 +1,8 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="DrillThroughAction">
+<xsd:complexType name="DrillThroughAction">
     <xsd:complexContent>
       <xsd:extension base="Action">
         <xsd:all>
-          <!--These elements are inherited from Action -->
+          <!--These elements are inherited from Action-->
           <xsd:element name="Name" type="xsd:string"/>
           <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
           <xsd:element name="Caption" type="xsd:string" minOccurs="0"/>
@@ -10,71 +10,74 @@           <xsd:element name="Translations" minOccurs="0">
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="Translation" type="Translation" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="Translation"  type="Translation"
+                             minOccurs="0" maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>
-          <xsd:element name="TargetType">
+          <xsd:element name="TargetType" >
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="Cube"/>
-                <xsd:enumeration value="Cells"/>
-                <xsd:enumeration value="Set"/>
-                <xsd:enumeration value="Hierarchy"/>
-                <xsd:enumeration value="Level"/>
-                <xsd:enumeration value="DimensionMembers"/>
-                <xsd:enumeration value="HierarchyMembers"/>
-                <xsd:enumeration value="LevelMembers"/>
-                <xsd:enumeration value="AttributeMembers"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="Cube" />
+                <xsd:enumeration value="Cells" />
+                <xsd:enumeration value="Set" />
+                <xsd:enumeration value="Hierarchy" />
+                <xsd:enumeration value="Level" />
+                <xsd:enumeration value="DimensionMembers" />
+                <xsd:enumeration value="HierarchyMembers" />
+                <xsd:enumeration value="LevelMembers" />
+                <xsd:enumeration value="AttributeMembers" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="Target" type="xsd:string" minOccurs="0"/>
           <xsd:element name="Condition" type="xsd:string" minOccurs="0"/>
-          <xsd:element name="Type">
+          <xsd:element name="Type" >
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="Url"/>
-                <xsd:enumeration value="Html"/>
-                <xsd:enumeration value="Statement"/>
-                <xsd:enumeration value="DrillThrough"/>
-                <xsd:enumeration value="Dataset"/>
-                <xsd:enumeration value="Rowset"/>
-                <xsd:enumeration value="CommandLine"/>
-                <xsd:enumeration value="Proprietary"/>
-                <xsd:enumeration value="Report"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="Url" />
+                <xsd:enumeration value="Html" />
+                <xsd:enumeration value="Statement" />
+                <xsd:enumeration value="DrillThrough" />
+                <xsd:enumeration value="Dataset" />
+                <xsd:enumeration value="Rowset" />
+                <xsd:enumeration value="CommandLine" />
+                <xsd:enumeration value="Proprietary" />
+                <xsd:enumeration value="Report" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="Invocation" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="Interactive"/>
-                <xsd:enumeration value="OnOpen"/>
-                <xsd:enumeration value="Batch"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="Interactive" />
+                <xsd:enumeration value="OnOpen" />
+                <xsd:enumeration value="Batch" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="Application" type="xsd:string" minOccurs="0"/>
           <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-          <xsd:element name="Annotations" minOccurs="0">
+          <xsd:element name="Annotations" minOccurs="0" >
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                             maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>
-          <!--Extended elements for DrillThroughAction object -->
+          <!--Extended elements for DrillThroughAction object-->
           <xsd:element name="Default" type="xsd:boolean" minOccurs="0"/>
           <xsd:element name="Columns" minOccurs="0">
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="Column" type="Binding" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="Column"  type="Binding" minOccurs="0"
+                             maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>
           <xsd:element name="MaximumRows" type="xsd:integer" minOccurs="0"/>
         </xsd:all>
-      </xsd:extension>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **MdxScript**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MdxScript

```diff
--- definition/+++ spec/@@ -1,23 +1,25 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MdxScript">
+<xsd:complexType name="MdxScript">
     <xsd:all>
-      <!--These elements are common to each MajorObject -->
-      <xsd:element name="Name" type="xsd:string"/>
-      <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Annotations" minOccurs="0">
+      <!--These elements are common to each MajorObject-->
+      <xsd:element name="Name"  type="xsd:string" />
+      <xsd:element name="ID"  type="xsd:string" minOccurs="0" />
+      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="Description" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Annotations"  minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation" type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded" />
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <!--Extended elements for MdxScript object -->
+      <!--Extended elements for MdxScript object-->
       <xsd:element name="Commands" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Command" type="Command" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Command"  type="Command" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
@@ -25,7 +27,8 @@       <xsd:element name="CalculationProperties" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="CalculationProperty" type="CalculationProperty" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="CalculationProperty"  type="CalculationProperty"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **CalculationProperty**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MdxScript > CalculationProperty

```diff
--- definition/+++ spec/@@ -1,19 +1,20 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="CalculationProperty">
+<xsd:complexType name="CalculationProperty">
     <xsd:all>
       <xsd:element name="CalculationReference" type="xsd:string"/>
       <xsd:element name="CalculationType">
         <xsd:simpleType>
           <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Member"/>
-            <xsd:enumeration value="Set"/>
-            <xsd:enumeration value="Cells"/>
+            <xsd:enumeration value="Member" />
+            <xsd:enumeration value="Set" />
+            <xsd:enumeration value="Cells" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="Translations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Translation" type="Translation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Translation"  type="Translation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
@@ -30,6 +31,8 @@       <xsd:element name="AssociatedMeasureGroupID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="DisplayFolder" type="xsd:string" minOccurs="0"/>
       <xsd:element name="Language" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="VisualizationProperties" type="eng300:CalculationPropertiesVisualizationProperties" minOccurs="0" maxOccurs="1"/>
+      <xsd:element name ="VisualizationProperties"
+                         type="eng300:CalculationPropertiesVisualizationProperties"
+                         minOccurs="0" maxOccurs="1"/>
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Command**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MdxScript > Command

```diff
--- definition/+++ spec/@@ -1,37 +1,13 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Command">
-    <xsd:choice>
-      <xsd:element name="Statement" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Create" type="Create" minOccurs="0"/>
-      <xsd:element name="Alter" type="Alter" minOccurs="0"/>
-      <xsd:element name="Delete" type="Delete" minOccurs="0"/>
-      <xsd:element name="Process" type="Process" minOccurs="0"/>
-      <xsd:element name="MergePartitions" type="MergePartitions" minOccurs="0"/>
-      <xsd:element name="DesignAggregations" type="DesignAggregations" minOccurs="0"/>
-      <xsd:element name="ClearCache" type="ClearCache" minOccurs="0"/>
-      <xsd:element name="Subscribe" type="Subscribe" minOccurs="0"/>
-      <xsd:element name="Unsubscribe" type="Unsubscribe" minOccurs="0"/>
-      <xsd:element name="Cancel" type="Cancel" minOccurs="0"/>
-      <xsd:element name="BeginTransaction" type="BeginTransaction" minOccurs="0"/>
-      <xsd:element name="CommitTransaction" type="CommitTransaction" minOccurs="0"/>
-      <xsd:element name="RollbackTransaction" type="RollbackTransaction" minOccurs="0"/>
-      <xsd:element name="Lock" type="Lock" minOccurs="0"/>
-      <xsd:element name="Unlock" type="Unlock" minOccurs="0"/>
-      <xsd:element name="Backup" type="Backup" minOccurs="0"/>
-      <xsd:element name="Restore" type="Restore" minOccurs="0"/>
-      <xsd:element name="Synchronize" type="Synchronize" minOccurs="0"/>
-      <xsd:element name="Attach" type="Attach" minOccurs="0"/>
-      <xsd:element name="Detach" type="Detach" minOccurs="0"/>
-      <xsd:element name="Insert" type="Insert" minOccurs="0"/>
-      <xsd:element name="Update" type="Update" minOccurs="0"/>
-      <xsd:element name="Drop" type="Drop" minOccurs="0"/>
-      <xsd:element name="UpdateCells" type="UpdateCells" minOccurs="0"/>
-      <xsd:element name="NotifyTableChange" type="NotifyTableChange" minOccurs="0"/>
-      <xsd:element name="Batch" type="Batch" minOccurs="0"/>
-      <xsd:element name="ImageLoad" type="ImageLoad" minOccurs="0"/>
-      <xsd:element name="ImageSave" type="ImageSave" minOccurs="0"/>
-      <xsd:element name="CloneDatabase" type="CloneDatabase" minOccurs="0"/>
-      <xsd:element name="SetAuthContext" type="SetAuthContext" minOccurs="0"/>
-      <xsd:element name="DBCC" type="DBCC" minOccurs="0"/>
-      <xsd:element name="RemoveDiscontinuedFeatures" type="eng922:RemoveDiscontinuedFeatures" minOccurs="0"/>
-    </xsd:choice>
+<xsd:complexType name="Command">
+    <xsd:all>
+      <xsd:element name="Text" type="xsd:string"/>
+      <xsd:element name="Annotations" minOccurs="0">
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+    </xsd:all>
   </xsd:complexType>
```

##### `complexType` **MeasureGroup**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MeasureGroup

```diff
--- definition/+++ spec/@@ -1,75 +1,79 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MeasureGroup">
+<xsd:complexType name="MeasureGroup">
     <xsd:all>
-      <!--These elements are common to each MajorObject -->
-      <xsd:element name="Name" type="xsd:string"/>
-      <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Annotations" minOccurs="0">
+      <!--These elements are common to each MajorObject-->
+      <xsd:element name="Name"  type="xsd:string" />
+      <xsd:element name="ID"  type="xsd:string" minOccurs="0" />
+      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="Description" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Annotations"  minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation" type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded" />
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <!--Extended elements for MeasureGroup object -->
+      <!--Extended elements for MeasureGroup object-->
       <xsd:element name="LastProcessed" type="xsd:dateTime" minOccurs="0"/>
       <xsd:element name="Translations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Translation" type="Translation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Translation"  type="Translation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Type" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Regular"/>
-            <xsd:enumeration value="ExchangeRate"/>
-            <xsd:enumeration value="Sales"/>
-            <xsd:enumeration value="Budget"/>
-            <xsd:enumeration value="FinancialReporting"/>
-            <xsd:enumeration value="Marketing"/>
-            <xsd:enumeration value="Inventory"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Regular" />
+            <xsd:enumeration value="ExchangeRate" />
+            <xsd:enumeration value="Sales" />
+            <xsd:enumeration value="Budget" />
+            <xsd:enumeration value="FinancialReporting" />
+            <xsd:enumeration value="Marketing" />
+            <xsd:enumeration value="Inventory" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="State" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Processed"/>
-            <xsd:enumeration value="PartiallyProcessed"/>
-            <xsd:enumeration value="Unprocessed"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Processed" />
+            <xsd:enumeration value="PartiallyProcessed" />
+            <xsd:enumeration value="Unprocessed" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="Measures">
+      <xsd:element name="Measures" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Measure" type="Measure" maxOccurs="unbounded"/>
+            <xsd:element name="Measure"  type="Measure"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="DataAggregation" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="None"/>
-            <xsd:enumeration value="DataAggregatable"/>
-            <xsd:enumeration value="CacheAggregatable"/>
-            <xsd:enumeration value="DataAndCacheAggregatable"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="DataAggregatable" />
+            <xsd:enumeration value="CacheAggregatable" />
+            <xsd:enumeration value="DataAndCacheAggregatable" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="Source" type="MeasureGroupBinding" minOccurs="0"/>
+      <xsd:element name="Source" type="MeasureGroupBinding" minOccurs="0" />
       <xsd:element name="StorageMode" minOccurs="0">
         <xsd:complexType>
           <xsd:simpleContent>
             <xsd:extension base="MeasureGroupStorageModeEnumType">
-              <xsd:attribute name="valuens">
+              <xsd:attribute name="valuens" >
                 <xsd:simpleType>
                   <xsd:restriction base="xsd:string">
-                    <xsd:enumeration value="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"/>
+                    <xsd:enumeration value=
+            "http://schemas.microsoft.com/analysisservices/2010/engine/200/200" />
                   </xsd:restriction>
                 </xsd:simpleType>
               </xsd:attribute>
@@ -77,31 +81,36 @@           </xsd:simpleContent>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="StorageLocation" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="IgnoreUnrelatedDimensions" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="ProactiveCaching" type="ProactiveCaching" minOccurs="0"/>
+      <xsd:element name="StorageLocation" type="xsd:string" minOccurs="0" />
+      <xsd:element name="IgnoreUnrelatedDimensions" type="xsd:boolean"
+                   minOccurs="0"/>
+      <xsd:element name="ProactiveCaching" type="ProactiveCaching"
+                   minOccurs="0" />
       <xsd:element name="EstimatedRows" type="xsd:long" minOccurs="0"/>
-      <xsd:element name="ErrorConfiguration" type="ErrorConfiguration" minOccurs="0"/>
+      <xsd:element name="ErrorConfiguration" type="ErrorConfiguration"
+                   minOccurs="0" />
       <xsd:element name="EstimatedSize" type="xsd:long" minOccurs="0"/>
       <xsd:element name="ProcessingMode" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
+          <xsd:restriction base="xsd:string" >
             <xsd:enumeration value="Regular"/>
             <xsd:enumeration value="LazyAggregations"/>
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="Dimensions">
+      <xsd:element name="Dimensions" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Dimension" type="MeasureGroupDimension" maxOccurs="unbounded"/>
+            <xsd:element name="Dimension"  type="MeasureGroupDimension"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Partitions" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Partition" type="Partition" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Partition"  type="Partition" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
@@ -110,9 +119,20 @@       <xsd:element name="AggregationDesigns" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="AggregationDesign" type="AggregationDesign" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="AggregationDesign"  type="AggregationDesign"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
     </xsd:all>
-  </xsd:complexType>+  </xsd:complexType>
+
+
+  <xsd:simpleType name="MeasureGroupStorageModeEnumType">
+    <xsd:restriction base="xsd:string" >
+      <xsd:enumeration value="Molap" />
+      <xsd:enumeration value="Rolap" />
+      <xsd:enumeration value="Holap" />
+      <xsd:enumeration value="InMemory" />
+    </xsd:restriction>
+  </xsd:simpleType>
```

##### `simpleType` **MeasureGroupStorageModeEnumType**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MeasureGroup

```diff
--- definition/+++ spec/@@ -1,8 +1,138 @@-<xsd:simpleType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MeasureGroupStorageModeEnumType">
-    <xsd:restriction base="xsd:string">
-      <xsd:enumeration value="Molap"/>
-      <xsd:enumeration value="Rolap"/>
-      <xsd:enumeration value="Holap"/>
-      <xsd:enumeration value="InMemory"/>
+<xsd:complexType name="MeasureGroup">
+    <xsd:all>
+      <!--These elements are common to each MajorObject-->
+      <xsd:element name="Name"  type="xsd:string" />
+      <xsd:element name="ID"  type="xsd:string" minOccurs="0" />
+      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="Description" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Annotations"  minOccurs="0" >
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="Annotation" type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded" />
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+      <!--Extended elements for MeasureGroup object-->
+      <xsd:element name="LastProcessed" type="xsd:dateTime" minOccurs="0"/>
+      <xsd:element name="Translations" minOccurs="0">
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="Translation"  type="Translation" minOccurs="0"
+                         maxOccurs="unbounded"/>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+      <xsd:element name="Type" minOccurs="0">
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Regular" />
+            <xsd:enumeration value="ExchangeRate" />
+            <xsd:enumeration value="Sales" />
+            <xsd:enumeration value="Budget" />
+            <xsd:enumeration value="FinancialReporting" />
+            <xsd:enumeration value="Marketing" />
+            <xsd:enumeration value="Inventory" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="State" minOccurs="0">
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Processed" />
+            <xsd:enumeration value="PartiallyProcessed" />
+            <xsd:enumeration value="Unprocessed" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="Measures" >
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="Measure"  type="Measure"
+                         maxOccurs="unbounded"/>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+      <xsd:element name="DataAggregation" minOccurs="0">
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="DataAggregatable" />
+            <xsd:enumeration value="CacheAggregatable" />
+            <xsd:enumeration value="DataAndCacheAggregatable" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="Source" type="MeasureGroupBinding" minOccurs="0" />
+      <xsd:element name="StorageMode" minOccurs="0">
+        <xsd:complexType>
+          <xsd:simpleContent>
+            <xsd:extension base="MeasureGroupStorageModeEnumType">
+              <xsd:attribute name="valuens" >
+                <xsd:simpleType>
+                  <xsd:restriction base="xsd:string">
+                    <xsd:enumeration value=
+            "http://schemas.microsoft.com/analysisservices/2010/engine/200/200" />
+                  </xsd:restriction>
+                </xsd:simpleType>
+              </xsd:attribute>
+            </xsd:extension>
+          </xsd:simpleContent>
+        </xsd:complexType>
+      </xsd:element>
+      <xsd:element name="StorageLocation" type="xsd:string" minOccurs="0" />
+      <xsd:element name="IgnoreUnrelatedDimensions" type="xsd:boolean"
+                   minOccurs="0"/>
+      <xsd:element name="ProactiveCaching" type="ProactiveCaching"
+                   minOccurs="0" />
+      <xsd:element name="EstimatedRows" type="xsd:long" minOccurs="0"/>
+      <xsd:element name="ErrorConfiguration" type="ErrorConfiguration"
+                   minOccurs="0" />
+      <xsd:element name="EstimatedSize" type="xsd:long" minOccurs="0"/>
+      <xsd:element name="ProcessingMode" minOccurs="0">
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Regular"/>
+            <xsd:enumeration value="LazyAggregations"/>
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="Dimensions" >
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="Dimension"  type="MeasureGroupDimension"
+                         maxOccurs="unbounded"/>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+      <xsd:element name="Partitions" minOccurs="0">
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="Partition"  type="Partition" minOccurs="0"
+                         maxOccurs="unbounded"/>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+      <xsd:element name="AggregationPrefix" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="ProcessingPriority" type="xsd:integer" minOccurs="0"/>
+      <xsd:element name="AggregationDesigns" minOccurs="0">
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="AggregationDesign"  type="AggregationDesign"
+                         minOccurs="0" maxOccurs="unbounded"/>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+    </xsd:all>
+  </xsd:complexType>
+
+
+  <xsd:simpleType name="MeasureGroupStorageModeEnumType">
+    <xsd:restriction base="xsd:string" >
+      <xsd:enumeration value="Molap" />
+      <xsd:enumeration value="Rolap" />
+      <xsd:enumeration value="Holap" />
+      <xsd:enumeration value="InMemory" />
     </xsd:restriction>
   </xsd:simpleType>
```

##### `complexType` **MeasureGroupDimension**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MeasureGroup > MeasureGroupDimension

```diff
--- definition/+++ spec/@@ -1 +1 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults"
    xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MeasureGroupDimension" abstract="true"/>+<xsd:complexType name="MeasureGroupDimension" abstract="true" />
```

##### `complexType` **ManyToManyMeasureGroupDimension**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MeasureGroup > MeasureGroupDimension > ManyToManyMeasureGroupDimension

```diff
--- definition/+++ spec/@@ -1,21 +1,23 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ManyToManyMeasureGroupDimension">
+<xsd:complexType name="ManyToManyMeasureGroupDimension">
     <xsd:complexContent>
       <xsd:extension base="MeasureGroupDimension">
         <xsd:all>
-          <!--These elements are inherited from MeasureGroupDimension -->
+          <!--These elements are inherited from MeasureGroupDimension-->
           <xsd:element name="CubeDimensionID" type="xsd:string"/>
           <xsd:element name="Annotations" minOccurs="0">
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                             maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>
-          <xsd:element name="Source" type="MeasureGroupDimensionBinding" minOccurs="0"/>
-          <!--Extended elements for ManyToManyMeasureGroupDimension object -->
+          <xsd:element name="Source" type="MeasureGroupDimensionBinding"
+                       minOccurs="0" />
+          <!--Extended elements for ManyToManyMeasureGroupDimension object-->
           <xsd:element name="MeasureGroupID" type="xsd:string" minOccurs="0"/>
           <xsd:element name="DirectSlice" type="xsd:string" minOccurs="0"/>
         </xsd:all>
-      </xsd:extension>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **RegularMeasureGroupDimension**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MeasureGroup > MeasureGroupDimension > RegularMeasureGroupDimension

```diff
--- definition/+++ spec/@@ -1,34 +1,36 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="RegularMeasureGroupDimension">
+<xsd:complexType name="RegularMeasureGroupDimension">
     <xsd:complexContent>
       <xsd:extension base="MeasureGroupDimension">
         <xsd:all>
-          <!--These elements are inherited from MeasureGroupDimension -->
+          <!--These elements are inherited from MeasureGroupDimension-->
           <xsd:element name="CubeDimensionID" type="xsd:string"/>
           <xsd:element name="Annotations" minOccurs="0">
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                             maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>
-          <xsd:element name="Source" type="MeasureGroupDimensionBinding" minOccurs="0"/>
-          <!--Extended elements for RegularMeasureGroupDimension object -->
+          <xsd:element name="Source" type="MeasureGroupDimensionBinding" minOccurs="0" />
+          <!--Extended elements for RegularMeasureGroupDimension object-->
           <xsd:element name="Cardinality" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="Many"/>
-                <xsd:enumeration value="One"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="Many" />
+                <xsd:enumeration value="One" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
-          <xsd:element name="Attributes">
+          <xsd:element name="Attributes" >
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="Attribute" type="MeasureGroupAttribute" maxOccurs="unbounded"/>
+                <xsd:element name="Attribute"  type="MeasureGroupAttribute"
+                             maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>
         </xsd:all>
-      </xsd:extension>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `element` **ShareDimensionStorage**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MeasureGroup > MeasureGroupDimension > DegenerateMeasureGroupDimension

```diff
--- definition/+++ spec/@@ -1,8 +1,8 @@-<xsd:element xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ShareDimensionStorage">
+<xsd:element name="ShareDimensionStorage" >
     <xsd:simpleType>
-      <xsd:restriction base="xsd:string">
-        <xsd:enumeration value="Distinct"/>
-        <xsd:enumeration value="Shared"/>
+      <xsd:restriction base="xsd:string" >
+        <xsd:enumeration value="Distinct" />
+        <xsd:enumeration value="Shared" />
       </xsd:restriction>
     </xsd:simpleType>
   </xsd:element>
```

##### `complexType` **DataMiningMeasureGroupDimension**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MeasureGroup > MeasureGroupDimension > DataMiningMeasureGroupDimension

```diff
--- definition/+++ spec/@@ -1,20 +1,22 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="DataMiningMeasureGroupDimension">
+<xsd:complexType name="DataMiningMeasureGroupDimension">
     <xsd:complexContent>
       <xsd:extension base="MeasureGroupDimension">
         <xsd:all>
-          <!--These elements are inherited from MeasureGroupDimension -->
+          <!--These elements are inherited from MeasureGroupDimension-->
           <xsd:element name="CubeDimensionID" type="xsd:string"/>
           <xsd:element name="Annotations" minOccurs="0">
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                             maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>
-          <xsd:element name="Source" type="MeasureGroupDimensionBinding" minOccurs="0"/>
-          <!--Extended elements for DataMiningMeasureGroupDimension object -->
-          <xsd:element name="CaseCubeDimensionID" type="xsd:string"/>
+          <xsd:element name="Source" type="MeasureGroupDimensionBinding"
+                       minOccurs="0" />
+          <!--Extended elements for DataMiningMeasureGroupDimension object-->
+          <xsd:element name="CaseCubeDimensionID" type="xsd:string" />
         </xsd:all>
-      </xsd:extension>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **MeasureGroupAttribute**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MeasureGroup > MeasureGroupAttribute

```diff
--- definition/+++ spec/@@ -1,25 +1,27 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100" xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100"
xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"
xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300" xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300"
xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920" xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921"
    xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset"
    xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MeasureGroupAttribute">
+<xsd:complexType name="MeasureGroupAttribute">
     <xsd:all>
       <xsd:element name="AttributeID" type="xsd:string"/>
       <xsd:element name="KeyColumns" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="KeyColumn" type="DataItem" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="KeyColumn"  type="DataItem" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Type" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Regular"/>
-            <xsd:enumeration value="Granularity"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Regular" />
+            <xsd:enumeration value="Granularity" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **Measure**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MeasureGroup > Measure

```diff
--- definition/+++ spec/@@ -1,48 +1,48 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100" xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100"
xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"
xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300" xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300"
xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600"
xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922"
xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924"
xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty"
xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Measure">
+<xsd:complexType name="Measure">
     <xsd:all>
       <xsd:element name="Name" type="xsd:string"/>
       <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
       <xsd:element name="AggregateFunction" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Sum"/>
-            <xsd:enumeration value="Count"/>
-            <xsd:enumeration value="Min"/>
-            <xsd:enumeration value="Max"/>
-            <xsd:enumeration value="DistinctCount"/>
-            <xsd:enumeration value="None"/>
-            <xsd:enumeration value="ByAccount"/>
-            <xsd:enumeration value="AverageOfChildren"/>
-            <xsd:enumeration value="FirstChild"/>
-            <xsd:enumeration value="LastChild"/>
-            <xsd:enumeration value="FirstNonEmpty"/>
-            <xsd:enumeration value="LastNonEmpty"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Sum" />
+            <xsd:enumeration value="Count" />
+            <xsd:enumeration value="Min" />
+            <xsd:enumeration value="Max" />
+            <xsd:enumeration value="DistinctCount" />
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="ByAccount" />
+            <xsd:enumeration value="AverageOfChildren" />
+            <xsd:enumeration value="FirstChild" />
+            <xsd:enumeration value="LastChild" />
+            <xsd:enumeration value="FirstNonEmpty" />
+            <xsd:enumeration value="LastNonEmpty" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="DataType" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="WChar"/>
-            <xsd:enumeration value="Integer"/>
-            <xsd:enumeration value="BigInt"/>
-            <xsd:enumeration value="Single"/>
-            <xsd:enumeration value="Double"/>
-            <xsd:enumeration value="Date"/>
-            <xsd:enumeration value="Currency"/>
-            <xsd:enumeration value="UnsignedTinyInt"/>
-            <xsd:enumeration value="UnsignedSmallInt"/>
-            <xsd:enumeration value="UnsignedInt"/>
-            <xsd:enumeration value="UnsignedBigInt"/>
-            <xsd:enumeration value="Bool"/>
-            <xsd:enumeration value="Smallint"/>
-            <xsd:enumeration value="Tinyint"/>
-            <xsd:enumeration value="Variant"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="WChar" />
+            <xsd:enumeration value="Integer" />
+            <xsd:enumeration value="BigInt" />
+            <xsd:enumeration value="Single" />
+            <xsd:enumeration value="Double" />
+            <xsd:enumeration value="Date" />
+            <xsd:enumeration value="Currency" />
+            <xsd:enumeration value="UnsignedTinyInt" />
+            <xsd:enumeration value="UnsignedSmallInt" />
+            <xsd:enumeration value="UnsignedInt" />
+            <xsd:enumeration value="UnsignedBigInt" />
+            <xsd:enumeration value="Bool" />
+            <xsd:enumeration value="Smallint" />
+            <xsd:enumeration value="Tinyint" />
+            <xsd:enumeration value="Variant" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="Source" type="DataItem"/>
+      <xsd:element name="Source" type="DataItem" />
       <xsd:element name="Visible" type="xsd:boolean" minOccurs="0"/>
       <xsd:element name="MeasureExpression" type="xsd:string" minOccurs="0"/>
       <xsd:element name="DisplayFolder" type="xsd:string" minOccurs="0"/>
@@ -55,14 +55,16 @@       <xsd:element name="Translations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Translation" type="Translation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Translation"  type="Translation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **AggregationDesign**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > AggregationDesign

```diff
--- definition/+++ spec/@@ -1,34 +1,38 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AggregationDesign">
+<xsd:complexType name="AggregationDesign">
     <xsd:all>
-      <!--These elements are common to each MajorObject -->
-      <xsd:element name="Name" type="xsd:string"/>
-      <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Annotations" minOccurs="0">
+      <!--These elements are common to each MajorObject-->
+      <xsd:element name="Name"  type="xsd:string" />
+      <xsd:element name="ID"  type="xsd:string" minOccurs="0" />
+      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="Description" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Annotations"  minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation" type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded" />
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <!--Extended elements for AggregationDesign object -->
+      <!--Extended elements for AggregationDesign object-->
       <xsd:element name="EstimatedRows" type="xsd:long" minOccurs="0"/>
       <xsd:element name="Dimensions" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Dimension" type="AggregationDesignDimension" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Dimension"  type="AggregationDesignDimension"
+                 minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Aggregations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Aggregation" type="Aggregation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Aggregation"  type="Aggregation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="EstimatedPerformanceGain" type="xsd:integer" minOccurs="0"/>
+      <xsd:element name="EstimatedPerformanceGain" type="xsd:integer"
+                   minOccurs="0"/>
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **AggregationDesignDimension**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > AggregationDesign > AggregationDesignDimension

```diff
--- definition/+++ spec/@@ -1,17 +1,19 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AggregationDesignDimension">
+<xsd:complexType name="AggregationDesignDimension">
     <xsd:all>
       <xsd:element name="CubeDimensionID" type="xsd:string"/>
       <xsd:element name="Attributes" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Attribute" type="AggregationDesignAttribute" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Attribute"  type="AggregationDesignAttribute"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **AggregationDesignAttribute**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > AggregationDesign > AggregationDesignDimension > AggregationDesignAttribute

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AggregationDesignAttribute">
+<xsd:complexType name="AggregationDesignAttribute">
     <xsd:all>
       <xsd:element name="AttributeID" type="xsd:string"/>
       <xsd:element name="EstimatedCount" type="xsd:long" minOccurs="0"/>

```

##### `complexType` **Aggregation**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > AggregationDesign > Aggregation

```diff
--- definition/+++ spec/@@ -1,18 +1,20 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Aggregation">
+<xsd:complexType name="Aggregation">
     <xsd:all>
       <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="Name" type="xsd:string"/>
       <xsd:element name="Dimensions" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Dimension" type="AggregationDimension" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Dimension"  type="AggregationDimension"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **AggregationDimension**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > AggregationDesign > Aggregation > AggregationDimension

```diff
--- definition/+++ spec/@@ -1,17 +1,19 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AggregationDimension">
+<xsd:complexType name="AggregationDimension">
     <xsd:all>
       <xsd:element name="CubeDimensionID" type="xsd:string"/>
       <xsd:element name="Attributes" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Attribute" type="AggregationAttribute" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Attribute" type="AggregationAttribute"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **AggregationAttribute**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > AggregationDesign > Aggregation > AggregationDimension > AggregationAttribute

```diff
--- definition/+++ spec/@@ -1,10 +1,11 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AggregationAttribute">
+<xsd:complexType name="AggregationAttribute">
     <xsd:all>
       <xsd:element name="AttributeID" type="xsd:string"/>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **AggregationInstance**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Partition > AggregationInstance

```diff
--- definition/+++ spec/@@ -1,35 +1,38 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AggregationInstance">
+<xsd:complexType name="AggregationInstance">
     <xsd:all>
       <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="Name" type="xsd:string"/>
-      <xsd:element name="AggregationType">
+      <xsd:element name="AggregationType" >
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="IndexedView"/>
-            <xsd:enumeration value="Table"/>
-            <xsd:enumeration value="UserDefined"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="IndexedView" />
+            <xsd:enumeration value="Table" />
+            <xsd:enumeration value="UserDefined" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="Source" type="TabularBinding" minOccurs="0"/>
+      <xsd:element name="Source" type="TabularBinding" minOccurs="0" />
       <xsd:element name="Dimensions" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Dimension" type="AggregationInstanceDimension" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Dimension"  type="AggregationInstanceDimension"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Measures" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Measure" type="AggregationInstanceMeasure" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Measure"  type="AggregationInstanceMeasure"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **AggregationInstanceDimension**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Partition > AggregationInstance > AggregationInstanceDimension

```diff
--- definition/+++ spec/@@ -1,10 +1,11 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AggregationInstanceDimension">
+<xsd:complexType name="AggregationInstanceDimension">
     <xsd:all>
       <xsd:element name="CubeDimensionID" type="xsd:string"/>
       <xsd:element name="Attributes" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Attribute" type="AggregationInstanceAttribute" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Attribute"  type="AggregationInstanceAttribute"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **AggregationInstanceAttribute**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Partition > AggregationInstance > AggregationInstanceAttribute

```diff
--- definition/+++ spec/@@ -1,10 +1,11 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AggregationInstanceAttribute">
+<xsd:complexType name="AggregationInstanceAttribute">
     <xsd:all>
       <xsd:element name="AttributeID" type="xsd:string"/>
-      <xsd:element name="KeyColumns">
+      <xsd:element name="KeyColumns" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="KeyColumn" type="DataItem" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="KeyColumn"  type="DataItem" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **AggregationInstanceMeasure**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Partition > AggregationInstance > AggregationInstanceMeasure

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AggregationInstanceMeasure">
+<xsd:complexType name="AggregationInstanceMeasure">
     <xsd:all>
       <xsd:element name="MeasureID" type="xsd:string"/>
-      <xsd:element name="Source" type="ColumnBinding"/>
+      <xsd:element name="Source" type="ColumnBinding" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Perspective**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Perspective

```diff
--- definition/+++ spec/@@ -1,23 +1,25 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Perspective">
+<xsd:complexType name="Perspective">
     <xsd:all>
-      <!--These elements are common to each MajorObject -->
-      <xsd:element name="Name" type="xsd:string"/>
-      <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Annotations" minOccurs="0">
+      <!--These elements are common to each MajorObject-->
+      <xsd:element name="Name"  type="xsd:string" />
+      <xsd:element name="ID"  type="xsd:string" minOccurs="0" />
+      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="Description" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Annotations"  minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation" type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded" />
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <!--Extended elements for Perspective object -->
+      <!--Extended elements for Perspective object-->
       <xsd:element name="Translations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Translation" type="Translation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Translation"  type="Translation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
@@ -25,35 +27,40 @@       <xsd:element name="Dimensions" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Dimension" type="PerspectiveDimension" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Dimension"  type="PerspectiveDimension"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="MeasureGroups" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="MeasureGroup" type="PerspectiveMeasureGroup" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="MeasureGroup"  type="PerspectiveMeasureGroup"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Calculations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Calculation" type="PerspectiveCalculation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Calculation"  type="PerspectiveCalculation"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Kpis" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Kpi" type="PerspectiveKpi" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Kpi"  type="PerspectiveKpi" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Actions" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Action" type="PerspectiveAction" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Action"  type="PerspectiveAction"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **PerspectiveDimension**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Perspective > PerspectiveDimension

```diff
--- definition/+++ spec/@@ -1,24 +1,27 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="PerspectiveDimension">
+<xsd:complexType name="PerspectiveDimension">
     <xsd:all>
       <xsd:element name="CubeDimensionID" type="xsd:string"/>
       <xsd:element name="Attributes" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Attribute" type="PerspectiveAttribute" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Attribute"  type="PerspectiveAttribute"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Hierarchies" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Hierarchy" type="PerspectiveHierarchy" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Hierarchy"  type="PerspectiveHierarchy"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **PerspectiveAttribute**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Perspective > PerspectiveDimension > PerspectiveAttribute

```diff
--- definition/+++ spec/@@ -1,12 +1,14 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="PerspectiveAttribute">
+<xsd:complexType name="PerspectiveAttribute">
     <xsd:all>
       <xsd:element name="AttributeID" type="xsd:string"/>
-      <xsd:element name="AttributeHierarchyVisible" type="xsd:boolean" minOccurs="0"/>
+      <xsd:element name="AttributeHierarchyVisible" type="xsd:boolean"
+                   minOccurs="0"/>
       <xsd:element name="DefaultMember" type="xsd:string" minOccurs="0"/>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **PerspectiveHierarchy**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Perspective > PerspectiveDimension > PerspectiveHierarchy

```diff
--- definition/+++ spec/@@ -1,10 +1,11 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="PerspectiveHierarchy">
+<xsd:complexType name="PerspectiveHierarchy">
     <xsd:all>
       <xsd:element name="HierarchyID" type="xsd:string"/>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **PerspectiveMeasureGroup**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Perspective > PerspectiveMeasureGroup

```diff
--- definition/+++ spec/@@ -1,17 +1,19 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="PerspectiveMeasureGroup">
+<xsd:complexType name="PerspectiveMeasureGroup">
     <xsd:all>
       <xsd:element name="MeasureGroupID" type="xsd:string"/>
       <xsd:element name="Measures" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Measure" type="PerspectiveMeasure" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Measure"  type="PerspectiveMeasure" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **PerspectiveMeasure**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Perspective > PerspectiveMeasureGroup > PerspectiveMeasure

```diff
--- definition/+++ spec/@@ -1,10 +1,11 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="PerspectiveMeasure">
+<xsd:complexType name="PerspectiveMeasure">
     <xsd:all>
       <xsd:element name="MeasureID" type="xsd:string"/>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **PerspectiveCalculation**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Perspective > PerspectiveCalculation

```diff
--- definition/+++ spec/@@ -1,18 +1,19 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="PerspectiveCalculation">
+<xsd:complexType name="PerspectiveCalculation">
     <xsd:all>
       <xsd:element name="Name" type="xsd:string"/>
-      <xsd:element name="Type">
+      <xsd:element name="Type" >
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Member"/>
-            <xsd:enumeration value="Set"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Member" />
+            <xsd:enumeration value="Set" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **PerspectiveKpi**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Perspective > PerspectiveKpi

```diff
--- definition/+++ spec/@@ -1,10 +1,11 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="PerspectiveKpi">
+<xsd:complexType name="PerspectiveKpi">
     <xsd:all>
       <xsd:element name="KpiID" type="xsd:string"/>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **PerspectiveAction**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Perspective > PerspectiveAction

```diff
--- definition/+++ spec/@@ -1,10 +1,11 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="PerspectiveAction">
+<xsd:complexType name="PerspectiveAction">
     <xsd:all>
       <xsd:element name="ActionID" type="xsd:string"/>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **MiningStructureColumn**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MiningStructure > MiningStructureColumn

```diff
--- definition/+++ spec/@@ -1 +1 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults"
    xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MiningStructureColumn" abstract="true"/>+<xsd:complexType name="MiningStructureColumn" abstract="true" />
```

##### `complexType` **ScalarMiningStructureColumn**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MiningStructure > MiningStructureColumn > ScalarMiningStructureColumn

```diff
--- definition/+++ spec/@@ -1,60 +1,63 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ScalarMiningStructureColumn">
+<xsd:complexType name="ScalarMiningStructureColumn">
     <xsd:all>
       <xsd:element name="Name" type="xsd:string"/>
       <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Type">
+      <xsd:element name="Type" >
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Long"/>
-            <xsd:enumeration value="Boolean"/>
-            <xsd:enumeration value="Text"/>
-            <xsd:enumeration value="Double"/>
-            <xsd:enumeration value="Date"/>
-            <xsd:enumeration value="Table"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Long" />
+            <xsd:enumeration value="Boolean" />
+            <xsd:enumeration value="Text" />
+            <xsd:enumeration value="Double" />
+            <xsd:enumeration value="Date" />
+            <xsd:enumeration value="Table" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="IsKey" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="Source" type="Binding" minOccurs="0"/>
+      <xsd:element name="Source" type="Binding"  minOccurs="0" />
       <xsd:element name="Distribution" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="ModelingFlags" minOccurs="0">
+      <xsd:element name="ModelingFlags"   minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="ModelingFlag" type="MiningModelingFlag" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="ModelingFlag"  type="MiningModelingFlag"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="Content">
+      <xsd:element name="Content" >
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <!-- This list is user-extensible -->
-            <xsd:enumeration value="Discrete"/>
-            <xsd:enumeration value="Continuous"/>
-            <xsd:enumeration value="Discretized"/>
-            <xsd:enumeration value="Ordered"/>
-            <xsd:enumeration value="Cyclical"/>
-            <xsd:enumeration value="Probability"/>
-            <xsd:enumeration value="Variance"/>
-            <xsd:enumeration value="StdDev"/>
-            <xsd:enumeration value="ProbabilityVariance"/>
-            <xsd:enumeration value="ProbabilityStdDev"/>
-            <xsd:enumeration value="Support"/>
-            <xsd:enumeration value="Key"/>
+          <xsd:restriction base="xsd:string" >
+            <!-- This list is user-extensible  -->
+            <xsd:enumeration value="Discrete" />
+            <xsd:enumeration value="Continuous" />
+            <xsd:enumeration value="Discretized" />
+            <xsd:enumeration value="Ordered" />
+            <xsd:enumeration value="Cyclical" />
+            <xsd:enumeration value="Probability" />
+            <xsd:enumeration value="Variance" />
+            <xsd:enumeration value="StdDev" />
+            <xsd:enumeration value="ProbabilityVariance" />
+            <xsd:enumeration value="ProbabilityStdDev" />
+            <xsd:enumeration value="Support" />
+            <xsd:enumeration value="Key" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="ClassifiedColumns" minOccurs="0">
+      <xsd:element name="ClassifiedColumns" minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="ClassifiedColumnID" type="xsd:string" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="ClassifiedColumnID"  type="xsd:string"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
@@ -63,15 +66,17 @@       <xsd:element name="KeyColumns" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="KeyColumn" type="DataItem" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="KeyColumn"  type="DataItem" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="NameColumn" type="DataItem" minOccurs="0"/>
-      <xsd:element name="Translations" minOccurs="0">
+      <xsd:element name="NameColumn" type="DataItem"  minOccurs="0" />
+      <xsd:element name="Translations"  minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Translation" type="Translation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Translation"  type="Translation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **TableMiningStructureColumn**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MiningStructure > MiningStructureColumn > TableMiningStructureColumn

```diff
--- definition/+++ spec/@@ -1,24 +1,28 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="TableMiningStructureColumn">
+<xsd:complexType name="TableMiningStructureColumn">
     <xsd:all>
       <xsd:element name="ForeignKeyColumns" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="ForeignKeyColumn" type="DataItem" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="ForeignKeyColumn"  type="DataItem"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="SourceMeasureGroup" type="MeasureGroupBinding" minOccurs="0"/>
+      <xsd:element name="SourceMeasureGroup" type="MeasureGroupBinding"
+                   minOccurs="0" />
       <xsd:element name="Columns" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Column" type="MiningStructureColumn" minOccurs="1" maxOccurs="unbounded"/>
+            <xsd:element name="Column"  type="MiningStructureColumn"
+                         minOccurs="1" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Translations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Translation" type="Translation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Translation"  type="Translation"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **MiningModel**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MiningModel

```diff
--- definition/+++ spec/@@ -1,31 +1,32 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MiningModel">
+<xsd:complexType name="MiningModel">
     <xsd:all>
-      <!--These elements are common to each MajorObject -->
-      <xsd:element name="Name" type="xsd:string"/>
-      <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Annotations" minOccurs="0">
+      <!--These elements are common to each MajorObject-->
+      <xsd:element name="Name"  type="xsd:string" />
+      <xsd:element name="ID"  type="xsd:string" minOccurs="0" />
+      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="Description" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Annotations"  minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation" type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded" />
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <!--Extended elements for MiningModel object -->
+      <!--Extended elements for MiningModel object-->
       <xsd:element name="Algorithm" minOccurs="1">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Microsoft_Naive_Bayes"/>
-            <xsd:enumeration value="Microsoft_Decision_Trees"/>
-            <xsd:enumeration value="Microsoft_Clustering"/>
-            <xsd:enumeration value="Microsoft_Neural_Network"/>
-            <xsd:enumeration value="Microsoft_Logistic_Regression"/>
-            <xsd:enumeration value="Microsoft_Linear_Regression"/>
-            <xsd:enumeration value="Microsoft_Association_Rules"/>
-            <xsd:enumeration value="Microsoft_Time_Series"/>
-            <xsd:enumeration value="Microsoft_Sequence_Clustering"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Microsoft_Naive_Bayes" />
+            <xsd:enumeration value="Microsoft_Decision_Trees" />
+            <xsd:enumeration value="Microsoft_Clustering" />
+            <xsd:enumeration value="Microsoft_Neural_Network" />
+            <xsd:enumeration value="Microsoft_Logistic_Regression" />
+            <xsd:enumeration value="Microsoft_Linear_Regression" />
+            <xsd:enumeration value="Microsoft_Association_Rules" />
+            <xsd:enumeration value="Microsoft_Time_Series" />
+            <xsd:enumeration value="Microsoft_Sequence_Clustering" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
@@ -33,7 +34,8 @@       <xsd:element name="AlgorithmParameters" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="AlgorithmParameter" type="AlgorithmParameter" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="AlgorithmParameter"  type="AlgorithmParameter"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
@@ -41,31 +43,35 @@       <xsd:element name="Translations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Translation" type="AttributeTranslation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Translation"  type="AttributeTranslation"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="Columns">
+      <xsd:element name="Columns" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Column" type="MiningModelColumn" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Column"  type="MiningModelColumn" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="State" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Processed"/>
-            <xsd:enumeration value="Unprocessed"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Processed" />
+            <xsd:enumeration value="Unprocessed" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="FoldingParameters" type="FoldingParameters" minOccurs="0"/>
-      <xsd:element name="Filter" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="FoldingParameters" type="FoldingParameters"
+                   minOccurs="0" />
+      <xsd:element name="Filter"  type="xsd:string" minOccurs="0" />
       <xsd:element name="MiningModelPermissions" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="MiningModelPermission" type="MiningModelPermission" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="MiningModelPermission"  type="MiningModelPermission"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **MiningModelingFlag**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MiningModel > MiningModelingFlag

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MiningModelingFlag">
+<xsd:complexType name="MiningModelingFlag">
     <xsd:all>
-      <xsd:element name="ModelingFlag" type="xsd:string"/>
+      <xsd:element name="ModelingFlag"  type="xsd:string" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **MiningModelColumn**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MiningModel > MiningModelColumn

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MiningModelColumn">
+<xsd:complexType name="MiningModelColumn">
     <xsd:all>
       <xsd:element name="Name" type="xsd:string"/>
       <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
@@ -6,11 +6,11 @@       <xsd:element name="SourceColumnID" type="xsd:string"/>
       <xsd:element name="Usage" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Key"/>
-            <xsd:enumeration value="Input"/>
-            <xsd:enumeration value="Predict"/>
-            <xsd:enumeration value="PredictOnly"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Key" />
+            <xsd:enumeration value="Input" />
+            <xsd:enumeration value="Predict" />
+            <xsd:enumeration value="PredictOnly" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
@@ -18,28 +18,32 @@       <xsd:element name="Translations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Translation" type="Translation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Translation"  type="Translation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Columns" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Column" type="MiningModelColumn" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Column"  type="MiningModelColumn" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="ModelingFlags" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="ModelingFlag" type="MiningModelingFlag" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="ModelingFlag"  type="MiningModelingFlag" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **AlgorithmParameter**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MiningModel > AlgorithmParameter

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AlgorithmParameter">
+<xsd:complexType name="AlgorithmParameter">
     <xsd:all>
       <xsd:element name="Name" type="xsd:string"/>
-      <xsd:element name="Value" type="xsd:anySimpleType"/>
+      <xsd:element name="Value" type="xsd:anySimpleType" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **FoldingParameters**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MiningModel > FoldingParameters

```diff
--- definition/+++ spec/@@ -1,7 +1,7 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="FoldingParameters">
+<xsd:complexType name="FoldingParameters">
     <xsd:all>
-      <xsd:element name="FoldIndex" type="xsd:integer"/>
-      <xsd:element name="FoldCount" type="xsd:integer"/>
+      <xsd:element name="FoldIndex" type="xsd:integer" />
+      <xsd:element name="FoldCount" type="xsd:integer" />
       <xsd:element name="FoldMaxCases" type="xsd:long" minOccurs="0"/>
       <xsd:element name="FoldTargetAttribute" type="xsd:string" minOccurs="0"/>
     </xsd:all>

```

##### `complexType` **Annotation**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Annotation

```diff
--- definition/+++ spec/@@ -1,14 +1,14 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Annotation">
+<xsd:complexType name="Annotation">
     <xsd:all>
       <xsd:element name="Name" type="xsd:string"/>
       <xsd:element name="Visibility" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="SchemaRowset"/>
-            <xsd:enumeration value="None"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="SchemaRowset" />
+            <xsd:enumeration value="None" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="Value" type="xsd:anyType" minOccurs="0"/>
+      <xsd:element name="Value" type="xsd:anyType" minOccurs="0" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **LinguisticSchemas**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Annotation > LinguisticSchemas Annotation

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="LinguisticSchemas">
+<xsd:complexType name="LinguisticSchemas">
     <xsd:sequence>
-      <xsd:element name="LinguisticSchema" type="LinguisticSchema" minOccurs="1"/>
+      <xsd:element name="LinguisticSchema" type="LinguisticSchema" minOccurs="1" />
     </xsd:sequence>
   </xsd:complexType>
```

##### `complexType` **LinguisticSchema**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Annotation > LinguisticSchemas Annotation > LinguisticSchema

```diff
--- definition/+++ spec/@@ -1,6 +1,12 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="LinguisticSchema">
+<xsd:complexType name="LinguisticSchema">
     <xsd:sequence>
-      <xsd:element name="Entities" type="EntitiesType" minOccurs="0" maxOccurs="1"/>
+      <xsd:element name="Entities" type="EntitiesType" minOccurs="0" maxOccurs="1" />
     </xsd:sequence>
-    <xsd:attribute name="Language" type="xsd:language" use="required"/>
+    <xsd:attribute name="Language" type="xsd:language" use="required" />
+  </xsd:complexType>
+
+  <xsd:complexType name="EntitiesType">
+    <xsd:sequence>
+      <xsd:element name="Entity" type="EntityType" minOccurs="1" />
+    </xsd:sequence>
   </xsd:complexType>
```

##### `complexType` **EntitiesType**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Annotation > LinguisticSchemas Annotation > LinguisticSchema

```diff
--- definition/+++ spec/@@ -1,5 +1,12 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="EntitiesType">
+<xsd:complexType name="LinguisticSchema">
     <xsd:sequence>
-      <xsd:element name="Entity" type="EntityType" minOccurs="1"/>
+      <xsd:element name="Entities" type="EntitiesType" minOccurs="0" maxOccurs="1" />
+    </xsd:sequence>
+    <xsd:attribute name="Language" type="xsd:language" use="required" />
+  </xsd:complexType>
+
+  <xsd:complexType name="EntitiesType">
+    <xsd:sequence>
+      <xsd:element name="Entity" type="EntityType" minOccurs="1" />
     </xsd:sequence>
   </xsd:complexType>
```

##### `complexType` **EntityType**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Annotation > LinguisticSchemas Annotation > LinguisticSchema > Entity

```diff
--- definition/+++ spec/@@ -1,8 +1,14 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="EntityType">
+<xsd:complexType name="EntityType">
     <xsd:sequence>
-      <xsd:element name="Words" type="WordsType" minOccurs="0" maxOccurs="1"/>
+      <xsd:element name="Words" type="WordsType" minOccurs="0" maxOccurs="1" />
     </xsd:sequence>
-    <xsd:attribute name="Name" type="xsd:string" use="required"/>
-    <xsd:attribute name="EdmEntitySet" type="xsd:string" use="required"/>
-    <xsd:attribute name="EdmProperty" type="xsd:string" use="optional"/>
+    <xsd:attribute name="Name" type="Name" use="required" />
+    <xsd:attribute name="EdmEntitySet" type="EdmQualifiedName" use="required" />
+    <xsd:attribute name="EdmProperty" type="EdmSimpleName" use="optional" />
+  </xsd:complexType>
+
+  <xsd:complexType name="WordsType">
+    <xsd:sequence>
+      <xsd:element name="Word" type="xsd:token" minOccurs="1" />
+    </xsd:sequence>
   </xsd:complexType>
```

##### `complexType` **WordsType**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Annotation > LinguisticSchemas Annotation > LinguisticSchema > Entity

```diff
--- definition/+++ spec/@@ -1,5 +1,14 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="WordsType">
+<xsd:complexType name="EntityType">
     <xsd:sequence>
-      <xsd:element name="Word" type="xsd:token" minOccurs="1"/>
+      <xsd:element name="Words" type="WordsType" minOccurs="0" maxOccurs="1" />
+    </xsd:sequence>
+    <xsd:attribute name="Name" type="Name" use="required" />
+    <xsd:attribute name="EdmEntitySet" type="EdmQualifiedName" use="required" />
+    <xsd:attribute name="EdmProperty" type="EdmSimpleName" use="optional" />
+  </xsd:complexType>
+
+  <xsd:complexType name="WordsType">
+    <xsd:sequence>
+      <xsd:element name="Word" type="xsd:token" minOccurs="1" />
     </xsd:sequence>
   </xsd:complexType>
```

##### `complexType` **Translation**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Translation

```diff
--- definition/+++ spec/@@ -1,13 +1,14 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Translation">
+<xsd:complexType name="Translation">
     <xsd:all>
       <xsd:element name="Language" type="xsd:unsignedInt"/>
       <xsd:element name="Caption" type="xsd:string" minOccurs="0"/>
       <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
       <xsd:element name="DisplayFolder" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Annotations" minOccurs="0">
+      <xsd:element name="Annotations"  minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **AttributeTranslation**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Translation > AttributeTranslation

```diff
--- definition/+++ spec/@@ -1,17 +1,18 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AttributeTranslation">
+<xsd:complexType name="AttributeTranslation">
     <xsd:all>
       <xsd:element name="Language" type="xsd:unsignedInt"/>
       <xsd:element name="Caption" type="xsd:string" minOccurs="0"/>
       <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
       <xsd:element name="DisplayFolder" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Annotations" minOccurs="0">
+      <xsd:element name="Annotations"  minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="CaptionColumn" type="DataItem" minOccurs="0"/>
-      <xsd:element name="MembersWithDataCaption" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="CaptionColumn" type="DataItem"  minOccurs="0" />
+      <xsd:element name="MembersWithDataCaption" type="xsd:string"  minOccurs="0" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **DataItem**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > DataItem

```diff
--- definition/+++ spec/@@ -1,23 +1,23 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="DataItem">
+<xsd:complexType name="DataItem">
     <xsd:all>
       <xsd:element name="DataType">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="WChar"/>
-            <xsd:enumeration value="Integer"/>
-            <xsd:enumeration value="BigInt"/>
-            <xsd:enumeration value="Single"/>
-            <xsd:enumeration value="Double"/>
-            <xsd:enumeration value="Date"/>
-            <xsd:enumeration value="Currency"/>
-            <xsd:enumeration value="UnsignedTinyInt"/>
-            <xsd:enumeration value="UnsignedSmallInt"/>
-            <xsd:enumeration value="UnsignedInt"/>
-            <xsd:enumeration value="UnsignedBigInt"/>
-            <xsd:enumeration value="Bool"/>
-            <xsd:enumeration value="Smallint"/>
-            <xsd:enumeration value="Tinyint"/>
-            <xsd:enumeration value="Binary"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="WChar" />
+            <xsd:enumeration value="Integer" />
+            <xsd:enumeration value="BigInt" />
+            <xsd:enumeration value="Single" />
+            <xsd:enumeration value="Double" />
+            <xsd:enumeration value="Date" />
+            <xsd:enumeration value="Currency" />
+            <xsd:enumeration value="UnsignedTinyInt" />
+            <xsd:enumeration value="UnsignedSmallInt" />
+            <xsd:enumeration value="UnsignedInt" />
+            <xsd:enumeration value="UnsignedBigInt" />
+            <xsd:enumeration value="Bool" />
+            <xsd:enumeration value="Smallint" />
+            <xsd:enumeration value="Tinyint" />
+            <xsd:enumeration value="Binary" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
@@ -25,42 +25,42 @@       <xsd:element name="MimeType" type="xsd:string" minOccurs="0"/>
       <xsd:element name="NullProcessing" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Preserve"/>
-            <xsd:enumeration value="Error"/>
-            <xsd:enumeration value="UnknownMember"/>
-            <xsd:enumeration value="ZeroOrBlank"/>
-            <xsd:enumeration value="Automatic"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Preserve" />
+            <xsd:enumeration value="Error" />
+            <xsd:enumeration value="UnknownMember" />
+            <xsd:enumeration value="ZeroOrBlank" />
+            <xsd:enumeration value="Automatic" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="Trimming" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Left"/>
-            <xsd:enumeration value="Right"/>
-            <xsd:enumeration value="LeftRight"/>
-            <xsd:enumeration value="None"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Left" />
+            <xsd:enumeration value="Right" />
+            <xsd:enumeration value="LeftRight" />
+            <xsd:enumeration value="None" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="InvalidXmlCharacters" minOccurs="0">
+      <xsd:element name="InvalidXmlCharacters"  minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Preserve"/>
-            <xsd:enumeration value="Remove"/>
-            <xsd:enumeration value="Replace"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Preserve" />
+            <xsd:enumeration value="Remove" />
+            <xsd:enumeration value="Replace" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="Collation" type="xsd:string" minOccurs="0"/>
       <xsd:element name="Format" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="TrimRight"/>
-            <xsd:enumeration value="TrimLeft"/>
-            <xsd:enumeration value="TrimAll"/>
-            <xsd:enumeration value="TrimNone"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="TrimRight" />
+            <xsd:enumeration value="TrimLeft" />
+            <xsd:enumeration value="TrimAll" />
+            <xsd:enumeration value="TrimNone" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
@@ -68,7 +68,8 @@       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **Binding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > ColumnBinding

```diff
--- definition/+++ spec/@@ -1 +1 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults"
    xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Binding" abstract="true"/>+<xsd:complexType name="Binding" abstract="true" />
```

##### `complexType` **ColumnBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > ColumnBinding

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ColumnBinding">
+<xsd:complexType name="ColumnBinding" >
     <xsd:complexContent>
       <xsd:extension base="Binding">
         <xsd:all>

```

##### `complexType` **RowBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > RowBinding

```diff
--- definition/+++ spec/@@ -1,9 +1,9 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="RowBinding">
+<xsd:complexType name="RowBinding">
     <xsd:complexContent>
       <xsd:extension base="Binding">
         <xsd:all>
           <xsd:element name="TableID" type="xsd:string"/>
         </xsd:all>
-      </xsd:extension>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **DataSourceViewBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > DataSourceViewBinding

```diff
--- definition/+++ spec/@@ -1,8 +1,8 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="DataSourceViewBinding">
+<xsd:complexType name="DataSourceViewBinding" >
     <xsd:complexContent>
       <xsd:extension base="Binding">
         <xsd:all>
-          <xsd:element name="DataSourceViewID" type="xsd:string"/>
+          <xsd:element name="DataSourceViewID" type="xsd:string" />
         </xsd:all>
       </xsd:extension>
     </xsd:complexContent>

```

##### `complexType` **MeasureBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > MeasureBinding

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MeasureBinding">
+<xsd:complexType name="MeasureBinding" >
     <xsd:complexContent>
       <xsd:extension base="Binding">
         <xsd:all>

```

##### `complexType` **AttributeBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > AttributeBinding

```diff
--- definition/+++ spec/@@ -1,20 +1,20 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AttributeBinding">
+<xsd:complexType name="AttributeBinding" >
     <xsd:complexContent>
       <xsd:extension base="Binding">
         <xsd:all>
           <xsd:element name="AttributeID" type="xsd:string"/>
-          <xsd:element name="Type">
+          <xsd:element name="Type" >
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="All"/>
-                <xsd:enumeration value="Key"/>
-                <xsd:enumeration value="Name"/>
-                <xsd:enumeration value="Value"/>
-                <xsd:enumeration value="Translation"/>
-                <xsd:enumeration value="UnaryOperator"/>
-                <xsd:enumeration value="SkippedLevels"/>
-                <xsd:enumeration value="CustomRollup"/>
-                <xsd:enumeration value="CustomRollupProperties"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="All" />
+                <xsd:enumeration value="Key" />
+                <xsd:enumeration value="Name" />
+                <xsd:enumeration value="Value" />
+                <xsd:enumeration value="Translation" />
+                <xsd:enumeration value="UnaryOperator" />
+                <xsd:enumeration value="SkippedLevels" />
+                <xsd:enumeration value="CustomRollup" />
+                <xsd:enumeration value="CustomRollupProperties" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>

```

##### `complexType` **UserDefinedGroupBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > UserDefinedGroupBinding

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="UserDefinedGroupBinding">
+<xsd:complexType name="UserDefinedGroupBinding" >
     <xsd:complexContent>
       <xsd:extension base="Binding">
         <xsd:all>
@@ -6,7 +6,8 @@           <xsd:element name="Groups" minOccurs="0">
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="Group" type="Group" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="Group"  type="Group" minOccurs="0"
+                             maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>

```

##### `complexType` **Group**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > UserDefinedGroupBinding > Group

```diff
--- definition/+++ spec/@@ -1,10 +1,11 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Group">
+<xsd:complexType name="Group">
     <xsd:all>
       <xsd:element name="Name" type="xsd:string"/>
-      <xsd:element name="Members" minOccurs="0">
+      <xsd:element name="Members" minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Member" type="xsd:string" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Member"  type="xsd:string" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **CubeAttributeBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > CubeAttributeBinding

```diff
--- definition/+++ spec/@@ -1,29 +1,30 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="CubeAttributeBinding">
+<xsd:complexType name="CubeAttributeBinding" >
     <xsd:complexContent>
-      <xsd:extension base="Binding">
+      <xsd:extension base="Binding" >
         <xsd:all>
           <xsd:element name="CubeID" type="xsd:string"/>
           <xsd:element name="CubeDimensionID" type="xsd:string"/>
           <xsd:element name="AttributeID" type="xsd:string"/>
-          <xsd:element name="Type">
+          <xsd:element name="Type" >
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="All"/>
-                <xsd:enumeration value="Key"/>
-                <xsd:enumeration value="Name"/>
-                <xsd:enumeration value="Value"/>
-                <xsd:enumeration value="Translation"/>
-                <xsd:enumeration value="UnaryOperator"/>
-                <xsd:enumeration value="SkippedLevels"/>
-                <xsd:enumeration value="CustomRollup"/>
-                <xsd:enumeration value="CustomRollupProperties"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="All" />
+                <xsd:enumeration value="Key" />
+                <xsd:enumeration value="Name" />
+                <xsd:enumeration value="Value" />
+                <xsd:enumeration value="Translation" />
+                <xsd:enumeration value="UnaryOperator" />
+                <xsd:enumeration value="SkippedLevels" />
+                <xsd:enumeration value="CustomRollup" />
+                <xsd:enumeration value="CustomRollupProperties" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="Ordinal" minOccurs="0">
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="Ordinal" type="xsd:integer" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="Ordinal"  type="xsd:integer"
+                             minOccurs="0" maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>

```

##### `complexType` **DimensionBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > DimensionBinding

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="DimensionBinding">
+<xsd:complexType name="DimensionBinding" >
     <xsd:complexContent>
       <xsd:extension base="Binding">
         <xsd:all>
@@ -6,21 +6,21 @@           <xsd:element name="DimensionID" type="xsd:string"/>
           <xsd:element name="Persistence" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="NotPersisted"/>
-                <xsd:enumeration value="Metadata"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="NotPersisted" />
+                <xsd:enumeration value="Metadata" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="RefreshPolicy" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="ByQuery"/>
-                <xsd:enumeration value="ByInterval"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="ByQuery" />
+                <xsd:enumeration value="ByInterval" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
-          <xsd:element name="RefreshInterval" type="xsd:duration" minOccurs="0"/>
+          <xsd:element name="RefreshInterval" type="xsd:duration"  minOccurs="0" />
         </xsd:all>
       </xsd:extension>
     </xsd:complexContent>

```

##### `complexType` **CubeDimensionBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > CubeDimensionBinding

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="CubeDimensionBinding">
+<xsd:complexType name="CubeDimensionBinding" >
     <xsd:complexContent>
       <xsd:extension base="Binding">
         <xsd:all>

```

##### `complexType` **MeasureGroupBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > MeasureGroupBinding

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MeasureGroupBinding">
+<xsd:complexType name="MeasureGroupBinding">
     <xsd:complexContent>
       <xsd:extension base="Binding">
         <xsd:all>
@@ -7,24 +7,24 @@           <xsd:element name="MeasureGroupID" type="xsd:string"/>
           <xsd:element name="Persistence" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="NotPersisted"/>
-                <xsd:enumeration value="Metadata"/>
-                <xsd:enumeration value="All"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="NotPersisted" />
+                <xsd:enumeration value="Metadata" />
+                <xsd:enumeration value="All" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="RefreshPolicy" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="ByQuery"/>
-                <xsd:enumeration value="ByInterval"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="ByQuery" />
+                <xsd:enumeration value="ByInterval" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
-          <xsd:element name="RefreshInterval" type="xsd:duration" minOccurs="0"/>
+          <xsd:element name="RefreshInterval"  type="xsd:duration" minOccurs="0" />
           <xsd:element name="Filter" type="xsd:string" minOccurs="0"/>
         </xsd:all>
-      </xsd:extension>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **MeasureGroupDimensionBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > MeasureGroupDimensionBinding

```diff
--- definition/+++ spec/@@ -1,9 +1,9 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MeasureGroupDimensionBinding">
+<xsd:complexType name="MeasureGroupDimensionBinding">
     <xsd:complexContent>
       <xsd:extension base="Binding">
         <xsd:all>
           <xsd:element name="CubeDimensionID" type="xsd:string"/>
         </xsd:all>
-      </xsd:extension>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **TimeBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > TimeBinding

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="TimeBinding">
+<xsd:complexType name="TimeBinding" >
     <xsd:complexContent>
       <xsd:extension base="Binding">
         <xsd:all>
@@ -7,8 +7,8 @@           <xsd:element name="FirstDayOfWeek " minOccurs="0">
             <xsd:simpleType>
               <xsd:restriction base="xsd:integer">
-                <xsd:minInclusive value="1"/>
-                <xsd:maxInclusive value="7"/>
+                <xsd:minInclusive value="1" />
+                <xsd:maxInclusive value="7" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
@@ -16,75 +16,75 @@           <xsd:element name="FiscalFirstMonth" minOccurs="0">
             <xsd:simpleType>
               <xsd:restriction base="xsd:integer">
-                <xsd:minInclusive value="1"/>
-                <xsd:maxInclusive value="12"/>
+                <xsd:minInclusive value="1" />
+                <xsd:maxInclusive value="12" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="FiscalFirstDayOfMonth" minOccurs="0">
             <xsd:simpleType>
               <xsd:restriction base="xsd:integer">
-                <xsd:minInclusive value="1"/>
-                <xsd:maxInclusive value="31"/>
+                <xsd:minInclusive value="1" />
+                <xsd:maxInclusive value="31" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="FiscalYearName" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="CalendarYearName"/>
-                <xsd:enumeration value="NextCalendarYearName"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="CalendarYearName" />
+                <xsd:enumeration value="NextCalendarYearName" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="ReportingFirstMonth" minOccurs="0">
             <xsd:simpleType>
               <xsd:restriction base="xsd:integer">
-                <xsd:minInclusive value="1"/>
-                <xsd:maxInclusive value="12"/>
+                <xsd:minInclusive value="1" />
+                <xsd:maxInclusive value="12" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="ReportingFirstWeekOfMonth" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="1"/>
-                <xsd:enumeration value="2"/>
-                <xsd:enumeration value="3"/>
-                <xsd:enumeration value="4"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="1" />
+                <xsd:enumeration value="2" />
+                <xsd:enumeration value="3" />
+                <xsd:enumeration value="4" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="ReportingWeekToMonthPattern" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="Weeks445"/>
-                <xsd:enumeration value="Weeks454"/>
-                <xsd:enumeration value="Weeks544"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="Weeks445" />
+                <xsd:enumeration value="Weeks454" />
+                <xsd:enumeration value="Weeks544" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="ManufacturingFirstMonth" minOccurs="0">
             <xsd:simpleType>
               <xsd:restriction base="xsd:integer">
-                <xsd:minInclusive value="1"/>
-                <xsd:maxInclusive value="12"/>
+                <xsd:minInclusive value="1" />
+                <xsd:maxInclusive value="12" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name=" ManufacturingFirstWeekOfMonth" minOccurs="0">
             <xsd:simpleType>
               <xsd:restriction base="xsd:integer">
-                <xsd:minInclusive value="1"/>
-                <xsd:maxInclusive value="4"/>
+                <xsd:minInclusive value="1" />
+                <xsd:maxInclusive value="4" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="ManufacturingExtraMonthQuarter" minOccurs="0">
             <xsd:simpleType>
               <xsd:restriction base="xsd:integer">
-                <xsd:minInclusive value="1"/>
-                <xsd:maxInclusive value="4"/>
+                <xsd:minInclusive value="1" />
+                <xsd:maxInclusive value="4" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>

```

##### `complexType` **TimeAttributeBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > InheritedBinding

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="TimeAttributeBinding">
+<xsd:complexType name="TimeAttributeBinding" >
     <xsd:complexContent>
-      <xsd:extension base="Binding"/>
+      <xsd:extension base="Binding" />
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **InheritedBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > TabularBinding

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="InheritedBinding">
+<xsd:complexType name="InheritedBinding">
     <xsd:complexContent>
-      <xsd:extension base="Binding"/>
+      <xsd:extension base="Binding" />
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **TabularBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > TableBinding

```diff
--- definition/+++ spec/@@ -1 +1 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults"
    xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="TabularBinding" abstract="true"/>+<xsd:complexType name="TabularBinding" abstract="true" />
```

##### `complexType` **TableBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > TableBinding

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="TableBinding">
+<xsd:complexType name="TableBinding" >
     <xsd:complexContent>
       <xsd:extension base="TabularBinding">
         <xsd:all>

```

##### `complexType` **QueryBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > QueryBinding

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="QueryBinding">
+<xsd:complexType name="QueryBinding" >
     <xsd:complexContent>
       <xsd:extension base="TabularBinding">
         <xsd:all>

```

##### `complexType` **ProactiveCachingBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > ProactiveCachingObjectNotificationBinding

```diff
--- definition/+++ spec/@@ -1 +1 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults"
    xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ProactiveCachingBinding" abstract="true"/>+<xsd:complexType name="ProactiveCachingBinding" abstract="true" />
```

##### `complexType` **ProactiveCachingObjectNotificationBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > ProactiveCachingObjectNotificationBinding

```diff
--- definition/+++ spec/@@ -1 +1 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults"
    xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ProactiveCachingObjectNotificationBinding" abstract="true"/>+<xsd:complexType name="ProactiveCachingObjectNotificationBinding" abstract="true" />
```

##### `complexType` **ProactiveCachingInheritedBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > ProactiveCachingTablesBinding

```diff
--- definition/+++ spec/@@ -1,12 +1,12 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ProactiveCachingInheritedBinding">
+<xsd:complexType name="ProactiveCachingInheritedBinding" >
     <xsd:complexContent>
       <xsd:extension base="ProactiveCachingObjectNotificationBinding">
         <xsd:all>
           <xsd:element name="NotificationTechnique" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="Client"/>
-                <xsd:enumeration value="Server"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="Client" />
+                <xsd:enumeration value="Server" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>

```

##### `complexType` **ProactiveCachingTablesBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > ProactiveCachingTablesBinding

```diff
--- definition/+++ spec/@@ -1,19 +1,20 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ProactiveCachingTablesBinding">
+<xsd:complexType name="ProactiveCachingTablesBinding" >
     <xsd:complexContent>
       <xsd:extension base="ProactiveCachingObjectNotificationBinding">
         <xsd:all>
           <xsd:element name="NotificationTechnique" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="Client"/>
-                <xsd:enumeration value="Server"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="Client" />
+                <xsd:enumeration value="Server" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
-          <xsd:element name="TableNotifications">
+          <xsd:element name="TableNotifications" >
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="TableNotification" type="TableNotification" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="TableNotification"  type="TableNotification"
+                             minOccurs="0" maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>

```

##### `complexType` **TableNotification**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > ProactiveCachingTablesBinding > TableNotification

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="TableNotification">
+<xsd:complexType name="TableNotification">
     <xsd:all>
       <xsd:element name="DbTableName" type="xsd:string"/>
       <xsd:element name="DbSchemaName" type="xsd:string" minOccurs="0"/>

```

##### `complexType` **ProactiveCachingQueryBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > ProactiveCachingQueryBinding

```diff
--- definition/+++ spec/@@ -1,12 +1,13 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ProactiveCachingQueryBinding">
+<xsd:complexType name="ProactiveCachingQueryBinding" >
     <xsd:complexContent>
       <xsd:extension base="ProactiveCachingBinding">
         <xsd:all>
-          <xsd:element name="RefreshInterval" type="xsd:duration"/>
-          <xsd:element name="QueryNotifications">
+          <xsd:element name="RefreshInterval" type="xsd:duration" />
+          <xsd:element name="QueryNotifications" >
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="QueryNotification" type="QueryNotification" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="QueryNotification"  type="QueryNotification"
+                             minOccurs="0" maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>

```

##### `complexType` **QueryNotification**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > ProactiveCachingQueryBinding > QueryNotification

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="QueryNotification">
+<xsd:complexType name="QueryNotification">
     <xsd:all>
       <xsd:element name="Query" type="xsd:string"/>
     </xsd:all>

```

##### `complexType` **ProactiveCachingIncrementalProcessingBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > ProactiveCachingIncrementalProcessingBinding

```diff
--- definition/+++ spec/@@ -1,12 +1,14 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ProactiveCachingIncrementalProcessingBinding">
+<xsd:complexType name="ProactiveCachingIncrementalProcessingBinding" >
     <xsd:complexContent>
       <xsd:extension base="ProactiveCachingBinding">
         <xsd:all>
-          <xsd:element name="RefreshInterval" type="xsd:duration" minOccurs="0"/>
-          <xsd:element name="IncrementalProcessingNotifications">
+          <xsd:element name="RefreshInterval" type="xsd:duration" minOccurs="0" />
+          <xsd:element name="IncrementalProcessingNotifications" >
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="IncrementalProcessingNotification" type="IncrementalProcessingNotification" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="IncrementalProcessingNotification"
+                             type="IncrementalProcessingNotification"
+                             minOccurs="0" maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>

```

##### `complexType` **IncrementalProcessingNotification**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > ProactiveCachingIncrementalProcessingBinding >
IncrementalProcessingNotification

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="IncrementalProcessingNotification">
+<xsd:complexType name="IncrementalProcessingNotification">
     <xsd:all>
       <xsd:element name="TableID" type="xsd:string"/>
       <xsd:element name="ProcessingQuery" type="xsd:string"/>

```

##### `complexType` **CalculatedMeasureBinding**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Binding > CalculatedMeasureBinding

```diff
--- definition/+++ spec/@@ -1,8 +1,8 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="CalculatedMeasureBinding">
+<xsd:complexType name="CalculatedMeasureBinding" >
     <xsd:complexContent>
       <xsd:extension base="Binding">
         <xsd:all>
-          <xsd:element name="MeasureName" type="xsd:string"/>
+          <xsd:element name="MeasureName" type="xsd:string" />
         </xsd:all>
       </xsd:extension>
     </xsd:complexContent>

```

##### `complexType` **Permission**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Permission

```diff
--- definition/+++ spec/@@ -1,35 +1,44 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Permission">
+<xsd:complexType name="Permission">
     <xsd:sequence>
-      <!--These elements are common to each MajorObject -->
-      <xsd:element name="Name" type="xsd:string"/>
-      <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Annotations" minOccurs="0">
+      <!--These elements are common to each MajorObject-->
+      <xsd:element name="Name"  type="xsd:string" />
+      <xsd:element name="ID"  type="xsd:string" minOccurs="0" />
+      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="Description" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Annotations"  minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation" type="Annotation"
+                         minOccurs="0" maxOccurs="unbounded" />
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <!--Extended elements for Permission object -->
+      <!--Extended elements for Permission object-->
       <xsd:element name="RoleID" type="xsd:string"/>
       <xsd:element name="Process" type="xsd:boolean" minOccurs="0"/>
       <xsd:element name="ReadDefinition" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="None"/>
-            <xsd:enumeration value="Basic"/>
-            <xsd:enumeration value="Allowed"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="Basic" />
+            <xsd:enumeration value="Allowed" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="Read" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="None"/>
-            <xsd:enumeration value="Allowed"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="Allowed" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="Write" minOccurs="0">
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="Allowed" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>

```

##### `complexType` **CubeDimensionPermission**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Permission > CubeDimensionPermission

```diff
--- definition/+++ spec/@@ -1,34 +1,36 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="CubeDimensionPermission">
+<xsd:complexType name="CubeDimensionPermission">
     <xsd:all>
       <xsd:element name="CubeDimensionID" type="xsd:string"/>
       <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
       <xsd:element name="Read" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="None"/>
-            <xsd:enumeration value="Allowed"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="Allowed" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="Write" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="None"/>
-            <xsd:enumeration value="Allowed"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="Allowed" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="AttributePermissions" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="AttributePermission" type="AttributePermission" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="AttributePermission"  type="AttributePermission"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **AttributePermission**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Permission > AttributePermission

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AttributePermission">
+<xsd:complexType name="AttributePermission">
     <xsd:all>
       <xsd:element name="AttributeID" type="xsd:string"/>
       <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
@@ -9,7 +9,8 @@       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **CellPermission**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Permission > CellPermission

```diff
--- definition/+++ spec/@@ -1,11 +1,11 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="CellPermission">
+<xsd:complexType name="CellPermission">
     <xsd:all>
-      <xsd:element name="Access">
+      <xsd:element name="Access" >
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Read"/>
-            <xsd:enumeration value="ReadContingent"/>
-            <xsd:enumeration value="ReadWrite"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Read" />
+            <xsd:enumeration value="ReadContingent" />
+            <xsd:enumeration value="ReadWrite" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
@@ -14,7 +14,8 @@       <xsd:element name="Annotations" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation"  type="Annotation" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **DatabasePermission**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > DatabasePermission

```diff
--- definition/+++ spec/@@ -1,16 +1,16 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="DatabasePermission">
+<xsd:complexType name="DatabasePermission">
     <xsd:complexContent>
       <xsd:extension base="Permission">
         <xsd:sequence>
           <xsd:element name="Administer" type="xsd:boolean" minOccurs="0"/>
           <xsd:element name="Write" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="None"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="None" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
         </xsd:sequence>
-      </xsd:extension>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **DataSourcePermission**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > DataSourcePermission

```diff
--- definition/+++ spec/@@ -1,16 +1,16 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="DataSourcePermission">
+<xsd:complexType name="DataSourcePermission">
     <xsd:complexContent>
       <xsd:extension base="Permission">
         <xsd:sequence>
-          <!--Extended elements for DataSourcePermission object -->
+          <!--Extended elements for DataSourcePermission object-->
           <xsd:element name="Write" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="None"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="None" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
         </xsd:sequence>
-      </xsd:extension>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `element` **AllowedRowsExpression**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > DimensionPermission

```diff
--- definition/+++ spec/@@ -1 +1 @@-<xsd:element xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults"
    xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="AllowedRowsExpression" type="xsd:string"/>+<xsd:element name="AllowedRowsExpression" type="xsd:string" />
```

##### `complexType` **MiningStructurePermission**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MiningStructurePermission

```diff
--- definition/+++ spec/@@ -1,17 +1,17 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MiningStructurePermission">
+<xsd:complexType name="MiningStructurePermission">
     <xsd:complexContent>
       <xsd:extension base="Permission">
         <xsd:sequence>
-          <!--Extended elements for MiningStructurePermission object -->
+          <!--Extended elements for MiningStructurePermission object-->
           <xsd:element name="AllowDrillThrough" type="xsd:boolean" minOccurs="0"/>
           <xsd:element name="Write" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="None"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="None" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
         </xsd:sequence>
-      </xsd:extension>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **MiningModelPermission**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > MiningModelPermission

```diff
--- definition/+++ spec/@@ -1,19 +1,19 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MiningModelPermission">
+<xsd:complexType name="MiningModelPermission">
     <xsd:complexContent>
       <xsd:extension base="Permission">
         <xsd:sequence>
-          <!--Extended elements for MiningModelPermission object -->
+          <!--Extended elements for MiningModelPermission object-->
           <xsd:element name="AllowDrillThrough" type="xsd:boolean" minOccurs="0"/>
           <xsd:element name="AllowBrowsing" type="xsd:boolean" minOccurs="0"/>
           <xsd:element name="Write" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="None"/>
-                <xsd:enumeration value="Allowed"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="None" />
+                <xsd:enumeration value="Allowed" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
         </xsd:sequence>
-      </xsd:extension>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **CubePermission**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > CubePermission

```diff
--- definition/+++ spec/@@ -1,39 +1,42 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="CubePermission">
+<xsd:complexType name="CubePermission">
     <xsd:complexContent>
       <xsd:extension base="Permission">
         <xsd:sequence>
-          <!--Extended elements for CubePermission object -->
+          <!--Extended elements for CubePermission object-->
           <xsd:element name="ReadSourceData" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="None"/>
-                <xsd:enumeration value="Allowed"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="None" />
+                <xsd:enumeration value="Allowed" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
           <xsd:element name="DimensionPermissions" minOccurs="0">
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="DimensionPermission" type="CubeDimensionPermission" minOccurs="0" maxOccurs="unbounded"/>
+                <xsd:element name="DimensionPermission"
+                             type="CubeDimensionPermission"
+                             minOccurs="0" maxOccurs="unbounded"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>
-          <xsd:element name="CellPermissions" minOccurs="0">
+          <xsd:element name="CellPermissions" minOccurs="0" >
             <xsd:complexType>
               <xsd:sequence>
-                <xsd:element name="CellPermission" type="CellPermission" minOccurs="0" maxOccurs="3"/>
+                <xsd:element name="CellPermission"  type="CellPermission"
+                             minOccurs="0" maxOccurs="3"/>
               </xsd:sequence>
             </xsd:complexType>
           </xsd:element>
           <xsd:element name="Write" minOccurs="0">
             <xsd:simpleType>
-              <xsd:restriction base="xsd:string">
-                <xsd:enumeration value="None"/>
-                <xsd:enumeration value="Allowed"/>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="None" />
+                <xsd:enumeration value="Allowed" />
               </xsd:restriction>
             </xsd:simpleType>
           </xsd:element>
         </xsd:sequence>
-      </xsd:extension>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **Role**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Role

```diff
--- definition/+++ spec/@@ -1,23 +1,25 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Role">
+<xsd:complexType name="Role">
     <xsd:all>
-      <!--These elements are common to each MajorObject -->
-      <xsd:element name="Name" type="xsd:string"/>
-      <xsd:element name="ID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0"/>
-      <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Annotations" minOccurs="0">
+      <!--These elements are common to each MajorObject-->
+      <xsd:element name="Name"  type="xsd:string" />
+      <xsd:element name="ID"  type="xsd:string" minOccurs="0" />
+      <xsd:element name="CreatedTimestamp" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="LastSchemaUpdate" type="xsd:dateTime" minOccurs="0" />
+      <xsd:element name="Description" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Annotations"  minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Annotation" type="Annotation" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Annotation" type="Annotation"
+                         minOccurs="0" maxOccurs="unbounded" />
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <!--Extended elements for Role object -->
+      <!--Extended elements for Role object-->
       <xsd:element name="Members" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Member" type="Member" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Member"  type="Member" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **Member**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > Role > Member

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Member">
+<xsd:complexType name="Member">
     <xsd:all>
       <xsd:element name="Name" minOccurs="0" type="xsd:string"/>
       <xsd:element name="Sid" minOccurs="0" type="xsd:string"/>

```

##### `complexType` **ProactiveCaching**

Spec location: Messages > Common Message Syntax > Complex Types > Object Definition Complex Types > Complex Type
Definitions for Server Object Hierarchy > ProactiveCaching

```diff
--- definition/+++ spec/@@ -1,26 +1,27 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100" xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100"
xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"
xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300" xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300"
xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600"
xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922"
 xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset"
     xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ProactiveCaching">
+<xsd:complexType name="ProactiveCaching">
     <xsd:all>
       <xsd:element name="OnlineMode" minOccurs="0">
         <xsd:simpleType>
           <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Immediate"/>
-            <xsd:enumeration value="OnCacheComplete"/>
+            <xsd:enumeration value="Immediate" />
+            <xsd:enumeration value="OnCacheComplete" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="AggregationStorage" minOccurs="0">
         <xsd:simpleType>
           <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Regular"/>
-            <xsd:enumeration value="MolapOnly"/>
+            <xsd:enumeration value="Regular" />
+            <xsd:enumeration value="MolapOnly" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="Source" type="ProactiveCachingBinding" minOccurs="0"/>
-      <xsd:element name="SilenceInterval" type="xsd:duration" minOccurs="0"/>
-      <xsd:element name="Latency" type="xsd:duration" minOccurs="0"/>
-      <xsd:element name="SilenceOverrideInterval" type="xsd:duration" minOccurs="0"/>
-      <xsd:element name="ForceRebuildInterval" type="xsd:duration" minOccurs="0"/>
+      <xsd:element name="Source" type="ProactiveCachingBinding" minOccurs="0" />
+      <xsd:element name="SilenceInterval" type="xsd:duration" minOccurs="0" />
+      <xsd:element name="Latency" type="xsd:duration" minOccurs="0" />
+      <xsd:element name="SilenceOverrideInterval" type="xsd:duration"
+                   minOccurs="0" />
+      <xsd:element name="ForceRebuildInterval" type="xsd:duration" minOccurs="0" />
       <xsd:element name="Enabled" type="xsd:boolean" minOccurs="0"/>
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Trace_Definition_ProviderInfo**

Spec location: Messages > Common Message Syntax > Complex Types > TraceDefinition Complex Types >
Trace_Definition_ProviderInfo

```diff
--- definition/+++ spec/@@ -1,20 +1,20 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100" xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100"
xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"
xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300" xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300"
xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600"
xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922"
xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924"
xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty"
xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Trace_Definition_ProviderInfo">
+<xsd:complexType name="Trace_Definition_ProviderInfo" >
     <xsd:sequence>
-      <xsd:element name="Data">
+      <xsd:element name="Data" >
         <xsd:complexType>
           <xsd:all>
-            <xsd:element name="Name" type="xsd:string"/>
-            <xsd:element name="Version">
+            <xsd:element name="Name" type="xsd:string" />
+            <xsd:element name="Version" >
               <xsd:complexType>
                 <xsd:all>
-                  <xsd:element name="Major" type="xsd:string" minOccurs="0"/>
-                  <xsd:element name="Minor" type="xsd:string" minOccurs="0"/>
-                  <xsd:element name="BuildNumber" type="xsd:string" minOccurs="0"/>
+                  <xsd:element name="Major" type="xsd:string" minOccurs="0" />
+                  <xsd:element name="Minor" type="xsd:string" minOccurs="0" />
+                  <xsd:element name="BuildNumber" type="xsd:string" minOccurs="0" />
                 </xsd:all>
               </xsd:complexType>
             </xsd:element>
-            <xsd:element name="Type" type="xsd:string" minOccurs="0"/>
-            <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
+            <xsd:element name="Type" type="xsd:string" minOccurs="0" />
+            <xsd:element name="Description" type="xsd:string" minOccurs="0" />
           </xsd:all>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **Trace_Event_Categories**

Spec location: Messages > Common Message Syntax > Complex Types > TraceDefinition Complex Types > Trace_Event_Categories

```diff
--- definition/+++ spec/@@ -1,18 +1,19 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100" xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100"
xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"
xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300" xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300"
xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600"
xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922"
xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924"
xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty"
xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Trace_Event_Categories">
+<xsd:complexType name="Trace_Event_Categories" >
     <xsd:sequence>
-      <xsd:element name="Data">
+      <xsd:element name="Data" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="EventCategory">
+            <xsd:element name="EventCategory" >
               <xsd:complexType>
                 <xsd:all>
-                  <xsd:element name="Name" type="xsd:string"/>
-                  <xsd:element name="Type" type="xsd:string" minOccurs="0"/>
-                  <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-                  <xsd:element name="EventList">
+                  <xsd:element name="Name" type="xsd:string" />
+                  <xsd:element name="Type" type="xsd:string" minOccurs="0" />
+                  <xsd:element name="Description" type="xsd:string" minOccurs="0" />
+                  <xsd:element name="EventList" >
                     <xsd:complexType>
                       <xsd:sequence>
-                        <xsd:element name="Event" type="TraceEvent" minOccurs="0" maxOccurs="unbounded"/>
+                        <xsd:element name="Event" type="TraceEvent" minOccurs="0"
+                                     maxOccurs="unbounded" />
                       </xsd:sequence>
                     </xsd:complexType>
                   </xsd:element>

```

##### `complexType` **TraceEvent**

Spec location: Messages > Common Message Syntax > Complex Types > TraceDefinition Complex Types >
Trace_Event_Categories > TraceEvent

```diff
--- definition/+++ spec/@@ -1,12 +1,13 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100" xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100"
xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"
xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300" xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300"
xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600"
xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922"
xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924"
xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty"
xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="TraceEvent">
+<xsd:complexType name="TraceEvent" >
     <xsd:all>
-      <xsd:element name="ID" type="xsd:integer"/>
-      <xsd:element name="Name" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="EventColumnList">
+      <xsd:element name="ID" type="xsd:integer" />
+      <xsd:element name="Name" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Description" type="xsd:string" minOccurs="0" />
+      <xsd:element name="EventColumnList" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="EventColumn" type="EventColumn" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="EventColumn" type="EventColumn" minOccurs="0"
+                         maxOccurs="unbounded" />
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **EventColumn**

Spec location: Messages > Common Message Syntax > Complex Types > TraceDefinition Complex Types >
Trace_Event_Categories > TraceEvent > EventColumn

```diff
--- definition/+++ spec/@@ -1,14 +1,15 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100" xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100"
xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"
xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300" xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300"
xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600"
xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922"
xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924"
xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults"
xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="EventColumn">
+<xsd:complexType name="EventColumn" >
     <xsd:all>
-      <xsd:element name="ID" type="xsd:integer"/>
+      <xsd:element name="ID" type="xsd:integer" />
       <xsd:element name="EventColumnSubclassList" minOccurs="0">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="EventColumnSubclass" minOccurs="0" maxOccurs="unbounded">
+            <xsd:element name="EventColumnSubclass" minOccurs="0"
+                         maxOccurs="unbounded" >
               <xsd:complexType>
                 <xsd:all>
-                  <xsd:element name="ID" type="xsd:integer"/>
-                  <xsd:element name="name" type="xsd:string"/>
+                  <xsd:element name="ID" type="xsd:integer" />
+                  <xsd:element name="name" type="xsd:string" />
                 </xsd:all>
               </xsd:complexType>
             </xsd:element>

```

##### `complexType` **Trace_Columns**

Spec location: Messages > Common Message Syntax > Complex Types > TraceDefinition Complex Types > Trace_Columns

```diff
--- definition/+++ spec/@@ -1,18 +1,18 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100" xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100"
xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"
xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300" xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300"
xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600"
xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922"
xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924"
xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults"
xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Trace_Columns">
+<xsd:complexType name="Trace_Columns" >
     <xsd:sequence>
       <xsd:element name="Data">
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Column">
+            <xsd:element name="Column" >
               <xsd:complexType>
                 <xsd:all>
-                  <xsd:element name="ID" type="xsd:integer"/>
-                  <xsd:element name="Type" type="xsd:integer"/>
-                  <xsd:element name="Name" type="xsd:string"/>
+                  <xsd:element name="ID" type="xsd:integer" />
+                  <xsd:element name="Type" type="xsd:integer" />
+                  <xsd:element name="Name" type="xsd:string" />
                   <xsd:element name="Description" type="xsd:string" minOccurs="0"/>
-                  <xsd:element name="Filterable" type="xsd:boolean"/>
-                  <xsd:element name="Repeatable" type="xsd:boolean"/>
-                  <xsd:element name="RepeatedBase" type="xsd:boolean"/>
+                  <xsd:element name="Filterable" type="xsd:boolean" />
+                  <xsd:element name="Repeatable" type="xsd:boolean" />
+                  <xsd:element name="RepeatedBase" type="xsd:boolean" />
                 </xsd:all>
               </xsd:complexType>
             </xsd:element>

```

##### `element` **Discover**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Discover >
Elements > Discover

```diff
--- definition/+++ spec/@@ -1,99 +1,100 @@-<xsd:element xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100" xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100"
xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"
xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300" xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300"
xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600"
xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922"
xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924"
xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults"
xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Discover">
+<xsd:element name="Discover">
     <xsd:complexType>
       <xsd:sequence>
-        <xsd:element name="RequestType" minOccurs="1" maxOccurs="1">
+        <xsd:element name="RequestType" minOccurs="1" maxOccurs="1" >
           <xsd:simpleType>
-            <xsd:restriction base="xsd:string">
-              <xsd:enumeration value="DBSCHEMA_CATALOGS"/>
-              <xsd:enumeration value="MDSCHEMA_CUBES"/>
-              <xsd:enumeration value="MDSCHEMA_DIMENSIONS"/>
-              <xsd:enumeration value="MDSCHEMA_HIERARCHIES"/>
-              <xsd:enumeration value="MDSCHEMA_LEVELS"/>
-              <xsd:enumeration value="MDSCHEMA_MEASURES"/>
-              <xsd:enumeration value="MDSCHEMA_PROPERTIES"/>
-              <xsd:enumeration value="MDSCHEMA_MEMBERS"/>
-              <xsd:enumeration value="MDSCHEMA_ACTIONS"/>
-              <xsd:enumeration value="MDSCHEMA_SETS"/>
-              <xsd:enumeration value="DISCOVER_INSTANCES"/>
-              <xsd:enumeration value="MDSCHEMA_KPIS"/>
-              <xsd:enumeration value="MDSCHEMA_MEASUREGROUPS"/>
-              <xsd:enumeration value="MDSCHEMA_MEASUREGROUP_DIMENSIONS"/>
-              <xsd:enumeration value="DISCOVER_PROPERTIES"/>
-              <xsd:enumeration value="DISCOVER_LITERALS"/>
-              <xsd:enumeration value="DISCOVER_SCHEMA_ROWSETS"/>
-              <xsd:enumeration value="DISCOVER_KEYWORDS"/>
-              <xsd:enumeration value="DBSCHEMA_TABLES"/>
-              <xsd:enumeration value="DBSCHEMA_COLUMNS"/>
-              <xsd:enumeration value="DBSCHEMA_PROVIDER_TYPES"/>
-              <xsd:enumeration value="MDSCHEMA_INPUT_DATASOURCES"/>
-              <xsd:enumeration value="DMSCHEMA_MINING_SERVICES"/>
-              <xsd:enumeration value="DMSCHEMA_MINING_SERVICE_PARAMETERS"/>
-              <xsd:enumeration value="DMSCHEMA_MINING_FUNCTIONS"/>
-              <xsd:enumeration value="DMSCHEMA_MINING_MODEL_CONTENT"/>
-              <xsd:enumeration value="DMSCHEMA_MINING_MODEL_XML"/>
-              <xsd:enumeration value="DMSCHEMA_MINING_MODEL_CONTENT_PMML"/>
-              <xsd:enumeration value="DMSCHEMA_MINING_MODELS"/>
-              <xsd:enumeration value="DMSCHEMA_MINING_COLUMNS"/>
-              <xsd:enumeration value="DMSCHEMA_MINING_STRUCTURES"/>
-              <xsd:enumeration value="DMSCHEMA_MINING_STRUCTURE_COLUMNS"/>
-              <xsd:enumeration value="DISCOVER_DATASOURCES"/>
-              <xsd:enumeration value="DISCOVER_ENUMERATORS"/>
-              <xsd:enumeration value="DISCOVER_XML_METADATA"/>
-              <xsd:enumeration value="DISCOVER_TRACES"/>
-              <xsd:enumeration value="DISCOVER_TRACE_DEFINITION_PROVIDERINFO"/>
-              <xsd:enumeration value="DISCOVER_TRACE_COLUMNS"/>
-              <xsd:enumeration value="DISCOVER_TRACE_EVENT_CATEGORIES"/>
-              <xsd:enumeration value="DISCOVER_MEMORYUSAGE"/>
-              <xsd:enumeration value="DISCOVER_MEMORYGRANT"/>
-              <xsd:enumeration value="DISCOVER_LOCKS"/>
-              <xsd:enumeration value="DISCOVER_CONNECTIONS"/>
-              <xsd:enumeration value="DISCOVER_SESSIONS"/>
-              <xsd:enumeration value="DISCOVER_JOBS"/>
-              <xsd:enumeration value="DISCOVER_TRANSACTIONS"/>
-              <xsd:enumeration value="DISCOVER_DB_CONNECTIONS"/>
-              <xsd:enumeration value="DISCOVER_MASTER_KEY"/>
-              <xsd:enumeration value="DISCOVER_PERFORMANCE_COUNTERS"/>
-              <xsd:enumeration value="DISCOVER_LOCATIONS"/>
-              <xsd:enumeration value="DISCOVER_PARTITION_DIMENSION_STAT"/>
-              <xsd:enumeration value="DISCOVER_PARTITION_STAT"/>
-              <xsd:enumeration value="DISCOVER_DIMENSION_STAT"/>
-              <xsd:enumeration value="DISCOVER_COMMANDS"/>
-              <xsd:enumeration value="DISCOVER_COMMAND_OBJECTS"/>
-              <xsd:enumeration value="DISCOVER_OBJECT_ACTIVITY"/>
-              <xsd:enumeration value="DISCOVER_OBJECT_MEMORY_USAGE"/>
-              <xsd:enumeration value="DISCOVER_STORAGE_TABLES"/>
-              <xsd:enumeration value="DISCOVER_STORAGE_TABLE_COLUMNS"/>
-              <xsd:enumeration value="DISCOVER_STORAGE_TABLE_COLUMN_SEGMENTS"/>
-              <xsd:enumeration value="DISCOVER_CSDL_METADATA"/>
-              <xsd:enumeration value="DISCOVER_CALC_DEPENDENCY"/>
-              <xsd:enumeration value="MDSCHEMA_FUNCTIONS"/>
-              <xsd:enumeration value="DISCOVER_RING_BUFFERS"/>
-              <xsd:enumeration value="DISCOVER_XEVENT_TRACE_DEFINITION"/>
-              <xsd:enumeration value="DISCOVER_XEVENT_PACKAGES"/>
-              <xsd:enumeration value="DISCOVER_XEVENT_OBJECTS"/>
-              <xsd:enumeration value="DISCOVER_XEVENT_OBJECT_COLUMNS"/>
-              <xsd:enumeration value="DISCOVER_XEVENT_SESSIONS"/>
-              <xsd:enumeration value="DISCOVER_XEVENT_SESSION_TARGETS"/>
-              <xsd:enumeration value="DISCOVER_MEM_STATS"/>
-              <xsd:enumeration value="DISCOVER_DB_MEM_STATS"/>
-              <xsd:enumeration value="DISCOVER_OBJECT_COUNTERS"/>
+            <xsd:restriction base="xsd:string" >
+              <xsd:enumeration value="DBSCHEMA_CATALOGS" />
+              <xsd:enumeration value="MDSCHEMA_CUBES" />
+              <xsd:enumeration value="MDSCHEMA_DIMENSIONS" />
+              <xsd:enumeration value="MDSCHEMA_HIERARCHIES" />
+              <xsd:enumeration value="MDSCHEMA_LEVELS" />
+              <xsd:enumeration value="MDSCHEMA_MEASURES" />
+              <xsd:enumeration value="MDSCHEMA_PROPERTIES" />
+              <xsd:enumeration value="MDSCHEMA_MEMBERS" />
+              <xsd:enumeration value="MDSCHEMA_ACTIONS" />
+              <xsd:enumeration value="MDSCHEMA_SETS" />
+              <xsd:enumeration value="DISCOVER_INSTANCES" />
+              <xsd:enumeration value="MDSCHEMA_KPIS" />
+              <xsd:enumeration value="MDSCHEMA_MEASUREGROUPS" />
+              <xsd:enumeration value="MDSCHEMA_MEASUREGROUP_DIMENSIONS" />
+              <xsd:enumeration value="DISCOVER_PROPERTIES" />
+              <xsd:enumeration value="DISCOVER_LITERALS" />
+              <xsd:enumeration value="DISCOVER_SCHEMA_ROWSETS" />
+              <xsd:enumeration value="DISCOVER_KEYWORDS" />
+              <xsd:enumeration value="DBSCHEMA_TABLES" />
+              <xsd:enumeration value="DBSCHEMA_COLUMNS" />
+              <xsd:enumeration value="DBSCHEMA_PROVIDER_TYPES" />
+              <xsd:enumeration value="MDSCHEMA_INPUT_DATASOURCES" />
+              <xsd:enumeration value="DMSCHEMA_MINING_SERVICES" />
+              <xsd:enumeration value="DMSCHEMA_MINING_SERVICE_PARAMETERS" />
+              <xsd:enumeration value="DMSCHEMA_MINING_FUNCTIONS" />
+              <xsd:enumeration value="DMSCHEMA_MINING_MODEL_CONTENT" />
+              <xsd:enumeration value="DMSCHEMA_MINING_MODEL_XML" />
+              <xsd:enumeration value="DMSCHEMA_MINING_MODEL_CONTENT_PMML" />
+              <xsd:enumeration value="DMSCHEMA_MINING_MODELS" />
+              <xsd:enumeration value="DMSCHEMA_MINING_COLUMNS" />
+              <xsd:enumeration value="DMSCHEMA_MINING_STRUCTURES" />
+              <xsd:enumeration value="DMSCHEMA_MINING_STRUCTURE_COLUMNS" />
+              <xsd:enumeration value="DISCOVER_DATASOURCES" />
+              <xsd:enumeration value="DISCOVER_ENUMERATORS" />
+              <xsd:enumeration value="DISCOVER_XML_METADATA" />
+              <xsd:enumeration value="DISCOVER_TRACES" />
+              <xsd:enumeration value="DISCOVER_TRACE_DEFINITION_PROVIDERINFO" />
+              <xsd:enumeration value="DISCOVER_TRACE_COLUMNS" />
+              <xsd:enumeration value="DISCOVER_TRACE_EVENT_CATEGORIES" />
+              <xsd:enumeration value="DISCOVER_MEMORYUSAGE" />
+              <xsd:enumeration value="DISCOVER_MEMORYGRANT" />
+              <xsd:enumeration value="DISCOVER_LOCKS" />
+              <xsd:enumeration value="DISCOVER_CONNECTIONS" />
+              <xsd:enumeration value="DISCOVER_SESSIONS" />
+              <xsd:enumeration value="DISCOVER_JOBS" />
+              <xsd:enumeration value="DISCOVER_TRANSACTIONS" />
+              <xsd:enumeration value="DISCOVER_DB_CONNECTIONS" />
+              <xsd:enumeration value="DISCOVER_MASTER_KEY" />
+              <xsd:enumeration value="DISCOVER_PERFORMANCE_COUNTERS" />
+              <xsd:enumeration value="DISCOVER_LOCATIONS" />
+              <xsd:enumeration value="DISCOVER_PARTITION_DIMENSION_STAT" />
+              <xsd:enumeration value="DISCOVER_PARTITION_STAT" />
+              <xsd:enumeration value="DISCOVER_DIMENSION_STAT" />
+              <xsd:enumeration value="DISCOVER_COMMANDS" />
+              <xsd:enumeration value="DISCOVER_COMMAND_OBJECTS" />
+              <xsd:enumeration value="DISCOVER_OBJECT_ACTIVITY" />
+              <xsd:enumeration value="DISCOVER_OBJECT_MEMORY_USAGE" />
+              <xsd:enumeration value="DISCOVER_STORAGE_TABLES" />
+              <xsd:enumeration value="DISCOVER_STORAGE_TABLE_COLUMNS" />
+              <xsd:enumeration value="DISCOVER_STORAGE_TABLE_COLUMN_SEGMENTS" />
+              <xsd:enumeration value="DISCOVER_CSDL_METADATA" />
+              <xsd:enumeration value="DISCOVER_CALC_DEPENDENCY" />
+              <xsd:enumeration value="MDSCHEMA_FUNCTIONS" />
+              <xsd:enumeration value="DISCOVER_RING_BUFFERS" />
+              <xsd:enumeration value="DISCOVER_XEVENT_TRACE_DEFINITION" />
+              <xsd:enumeration value="DISCOVER_XEVENT_PACKAGES" />
+              <xsd:enumeration value="DISCOVER_XEVENT_OBJECTS" />
+              <xsd:enumeration value="DISCOVER_XEVENT_OBJECT_COLUMNS" />
+              <xsd:enumeration value="DISCOVER_XEVENT_SESSIONS" />
+              <xsd:enumeration value="DISCOVER_XEVENT_SESSION_TARGETS" />
+              <xsd:enumeration value="DISCOVER_MEM_STATS" />
+              <xsd:enumeration value="DISCOVER_DB_MEM_STATS" />
+              <xsd:enumeration value="DISCOVER_OBJECT_COUNTERS" />
             </xsd:restriction>
           </xsd:simpleType>
         </xsd:element>
         <xsd:element name="Restrictions" minOccurs="1" maxOccurs="1">
           <xsd:complexType>
             <xsd:sequence>
-              <xsd:element name="RestrictionList" minOccurs="0" maxOccurs="1" nillable="true">
+              <xsd:element name="RestrictionList" minOccurs="0" maxOccurs="1"
+                           nillable="true" >
                 <xsd:complexType>
                   <xsd:sequence>
-                    <xsd:any minOccurs="0" maxOccurs="unbounded"/>
+                    <xsd:any minOccurs="0" maxOccurs="unbounded" />
                   </xsd:sequence>
                 </xsd:complexType>
               </xsd:element>
             </xsd:sequence>
           </xsd:complexType>
         </xsd:element>
-        <xsd:element name="Properties" type="Properties"/>
+        <xsd:element name="Properties" type="Properties" />
       </xsd:sequence>
     </xsd:complexType>
   </xsd:element>
```

##### `complexType` **PropertyList**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Discover >
Elements > Discover > Properties Type > PropertyList

```diff
--- definition/+++ spec/@@ -1,395 +1,435 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100" xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100"
xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"
xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300" xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300"
xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600"
xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922"
xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924"
xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults"
xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="PropertyList">
+<xsd:complexType name="PropertyList" >
     <xsd:all>
-      <xsd:element name="DataSourceInfo" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Timeout" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="UserName" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Password" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="LocaleIdentifier" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="Catalog" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="DataSourceInfo" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Timeout" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="UserName" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Password" type="xsd:string" minOccurs="0" />
+      <xsd:element name="LocaleIdentifier" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="Catalog" type="xsd:string" minOccurs="0" />
       <xsd:element name="StateSupport" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="None"/>
-            <xsd:enumeration value="Sessions"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="Content" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="None"/>
-            <xsd:enumeration value="Schema"/>
-            <xsd:enumeration value="Data"/>
-            <xsd:enumeration value="SchemaData"/>
-            <xsd:enumeration value="Metadata"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="Format" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Tabular"/>
-            <xsd:enumeration value="Multidimensional"/>
-            <xsd:enumeration value="Native"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="AxisFormat" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="ClusterFormat"/>
-            <xsd:enumeration value="CustomFormat"/>
-            <xsd:enumeration value="TupleFormat"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="BeginRange" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="EndRange" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MDXSupport" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Core"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="ProviderName" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="ProviderVersion" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="DBMSVersion" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="ProviderType" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-            <xsd:enumeration value="3"/>
-            <xsd:enumeration value="4"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="ShowHiddenCubes" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="SQLSupport" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="TransactionDDL" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MaximumRows" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="Roles" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="VisualMode" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="0"/>
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="EffectiveRoles" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="EffectiveUserName" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="ServerName" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="CatalogLocation" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="DbpropCatalogTerm" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="DbpropCatalogUsage" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropColumnDefinition" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropConcatNullBehavior" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="DbpropDataSourceReadOnly" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="DbpropGroupBy" minOccurs="0" type="xsd:integer"/>
-      <xsd:element name="DbpropHeterogeneousTables" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropIdentifierCase" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropMaxIndexSize" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropMaxOpenChapters" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropMaxRowSize" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropMaxRowSizeIncludeBlob" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="DbpropMaxTablesInSelect" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropMultiTableUpdate" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="DbpropNullCollation" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-            <xsd:enumeration value="4"/>
-            <xsd:enumeration value="8"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="DbpropOrderByColumnsInSelect" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="DbpropOutputParameterAvailable" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-            <xsd:enumeration value="4"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="DbpropPersistentIdType" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-            <xsd:enumeration value="4"/>
-            <xsd:enumeration value="8"/>
-            <xsd:enumeration value="16"/>
-            <xsd:enumeration value="32"/>
-            <xsd:enumeration value="64"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="DbpropPrepareAbortBehavior" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="DbpropPrepareCommitBehavior" minOccurs="0">
-        <xsd:simpleType>
-
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="DbpropProcedureTerm" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="DbpropQuotedIdentifierCase" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-            <xsd:enumeration value="4"/>
-            <xsd:enumeration value="8"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="DbpropSchemaUsage" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropSqlSupport" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropSubqueries" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropSupportedTxnDdl" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MdpropMdxSubqueries" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropSupportedTxnIsoLevels" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropSupportedTxnIsoRetain" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropTableTerm" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="MdpropAggregateCellUpdate" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="0"/>
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="MdpropAxes" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MdpropFlatteningSupport" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-            <xsd:enumeration value="3"/>
-            <xsd:enumeration value="4"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="MdpropMdxCaseSupport" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MdpropMdxDescFlags" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MdpropMdxDrillFunctions" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MdpropMdxFormulas" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MdpropMdxJoinCubes" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MdpropMdxMemberFunctions" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MdpropMdxNonMeasureExpressions" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="0"/>
-            <xsd:enumeration value="1"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="MdpropMdxNumericFunctions" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MdpropMdxObjQualification" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MdpropMdxOuterReference" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MdpropMdxQueryByProperty" type="xsd:boolean" minOccurs="0"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="Sessions" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="Content" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="None" />
+            <xsd:enumeration value="Schema" />
+            <xsd:enumeration value="Data" />
+            <xsd:enumeration value="SchemaData" />
+            <xsd:enumeration value="Metadata" />
+
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="Format" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Tabular" />
+            <xsd:enumeration value="Multidimensional" />
+            <xsd:enumeration value="Native" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="AxisFormat" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="ClusterFormat" />
+            <xsd:enumeration value="CustomFormat" />
+            <xsd:enumeration value="TupleFormat" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="BeginRange" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="EndRange" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="MDXSupport" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Core" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="ProviderName" type="xsd:string" minOccurs="0" />
+      <xsd:element name="ProviderVersion" type="xsd:string" minOccurs="0" />
+      <xsd:element name="DBMSVersion" type="xsd:string" minOccurs="0" />
+      <xsd:element name="ProviderType" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer" >
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+            <xsd:enumeration value="3" />
+            <xsd:enumeration value="4" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="ShowHiddenCubes" type="xsd:boolean" minOccurs="0" />
+      <xsd:element name="SQLSupport" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="TransactionDDL" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="MaximumRows" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="Roles" type="xsd:string" minOccurs="0" />
+      <xsd:element name="VisualMode" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer" >
+            <xsd:enumeration value="0" />
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="EffectiveRoles" type="xsd:string" minOccurs="0" />
+      <xsd:element name="EffectiveUserName" type="xsd:string" minOccurs="0" />
+      <xsd:element name="ServerName" type="xsd:string" minOccurs="0" />
+      <xsd:element name="CatalogLocation" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer">
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="DbpropCatalogTerm" type="xsd:string" minOccurs="0" />
+      <xsd:element name="DbpropCatalogUsage" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="DbpropColumnDefinition" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="DbpropConcatNullBehavior" minOccurs="0 " >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer">
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="DbpropDataSourceReadOnly" type="xsd:boolean"
+                   minOccurs="0" />
+      <xsd:element name="DbpropGroupBy" minOccurs="0" type="xsd:integer" />
+      <xsd:element name="DbpropHeterogeneousTables" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="DbpropIdentifierCase" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="DbpropMaxIndexSize" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="DbpropMaxOpenChapters" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="DbpropMaxRowSize" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="DbpropMaxRowSizeIncludeBlob" type="xsd:boolean"
+                   minOccurs="0" />
+      <xsd:element name="DbpropMaxTablesInSelect" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="DbpropMultiTableUpdate" type="xsd:boolean"
+                   minOccurs="0" />
+      <xsd:element name="DbpropNullCollation" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer">
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+            <xsd:enumeration value="4" />
+            <xsd:enumeration value="8" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="DbpropOrderByColumnsInSelect" type="xsd:boolean"
+                   minOccurs="0" />
+      <xsd:element name="DbpropOutputParameterAvailable" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer">
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+            <xsd:enumeration value="4" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="DbpropPersistentIdType" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer">
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+            <xsd:enumeration value="4" />
+            <xsd:enumeration value="8" />
+            <xsd:enumeration value="16" />
+            <xsd:enumeration value="32" />
+            <xsd:enumeration value="64" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="DbpropPrepareAbortBehavior" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer">
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="DbpropPrepareCommitBehavior" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer">
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="DbpropProcedureTerm" type="xsd:string" minOccurs="0" />
+      <xsd:element name="DbpropQuotedIdentifierCase" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer">
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+            <xsd:enumeration value="4" />
+            <xsd:enumeration value="8" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="DbpropSchemaUsage" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="DbpropSqlSupport" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="DbpropSubqueries" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="DbpropSupportedTxnDdl" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="MdpropMdxSubqueries" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="DbpropSupportedTxnIsoLevels" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="DbpropSupportedTxnIsoRetain" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="DbpropTableTerm" type="xsd:string" minOccurs="0" />
+      <xsd:element name="MdpropAggregateCellUpdate" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer">
+            <xsd:enumeration value="0" />
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="MdpropAxes" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="MdpropFlatteningSupport"  minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer">
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+            <xsd:enumeration value="3" />
+            <xsd:enumeration value="4" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="MdpropMdxCaseSupport" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="MdpropMdxDescFlags" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="MdpropMdxDrillFunctions" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="MdpropMdxFormulas" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="MdpropMdxJoinCubes" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="MdpropMdxMemberFunctions" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="MdpropMdxNonMeasureExpressions" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer">
+            <xsd:enumeration value="0" />
+            <xsd:enumeration value="1" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="MdpropMdxNumericFunctions" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="MdpropMdxObjQualification" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="MdpropMdxOuterReference" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="MdpropMdxQueryByProperty" type="xsd:boolean"
+                   minOccurs="0" />
       <xsd:element name="MdpropMdxRangeRowset" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-            <xsd:enumeration value="4"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="MdpropMdxSetFunctions" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MdpropMdxSlicer" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="MdpropMdxStringCompop" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MdpropNamedLevels" type="xsd:integer" minOccurs="0"/>
+          <xsd:restriction base="xsd:integer" >
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+            <xsd:enumeration value="4" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="MdpropMdxSetFunctions" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="MdpropMdxSlicer" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer" >
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="MdpropMdxStringCompop" type="xsd:integer"
+                   minOccurs="0"/>
+      <xsd:element name="MdpropNamedLevels" type="xsd:integer" minOccurs="0" />
       <xsd:element name="DbpropMsmdMDXCompatibility" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="0"/>
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-            <xsd:enumeration value="3"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="DbpropMsmdSQLCompatibility" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropMsmdMDXUniqueNameStyle" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropMsmdCachePolicy" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropMsmdCacheRatio" type="xsd:integer" minOccurs="0"/>
+          <xsd:restriction base="xsd:integer" >
+            <xsd:enumeration value="0" />
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+            <xsd:enumeration value="3" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="DbpropMsmdSQLCompatibility" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="DbpropMsmdMDXUniqueNameStyle" type="xsd:integer"
+                 minOccurs="0"/>
+      <xsd:element name="DbpropMsmdCachePolicy" type="xsd:integer"
+                   minOccurs="0"/>
+      <xsd:element name="DbpropMsmdCacheRatio" type="xsd:integer"
+                   minOccurs="0"/>
       <xsd:element name="DbpropMsmdCacheMode" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropMsmdCompareCaseSensitiveStringFlags" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-            <xsd:enumeration value="16"/>
-            <xsd:enumeration value="256"/>
-            <xsd:enumeration value="4096"/>
-            <xsd:enumeration value="65536"/>
-            <xsd:enumeration value="1048576"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="DbpropMsmdCompareCaseNotSensitiveStringFlags" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropMsmdFlattened2" type="xsd:boolean" minOccurs="0"/>
+      <xsd:element name="DbpropMsmdCompareCaseSensitiveStringFlags"
+                   minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer" >
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+            <xsd:enumeration value="16" />
+            <xsd:enumeration value="256" />
+            <xsd:enumeration value="4096" />
+            <xsd:enumeration value="65536" />
+            <xsd:enumeration value="1048576" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="DbpropMsmdCompareCaseNotSensitiveStringFlags"
+                   Type="xsd:integer" minOccurs="0" />
+      <xsd:element name="DbpropMsmdFlattened2" type="xsd:boolean"
+                   minOccurs="0" />
       <xsd:element name="DbpropInitMode" type="xsd:integer" minOccurs="0"/>
       <xsd:element name="SspropInitAppName" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="SspropInitWsid" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="SspropInitPacketsize" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="ReadOnlySession" type="xsd:integer" minOccurs="0"/>
+      <xsd:element name="SspropInitWsid" type="xsd:string" minOccurs="0" />
+      <xsd:element name="SspropInitPacketsize" type="xsd:integer"
+                   minOccurs="0"/>
+      <xsd:element name="ReadOnlySession" type="xsd:integer" minOccurs="0" />
       <xsd:element name="SecuredCellValue" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="0"/>
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-            <xsd:enumeration value="3"/>
-            <xsd:enumeration value="4"/>
-            <xsd:enumeration value="5"/>
+          <xsd:restriction base="xsd:integer" >
+            <xsd:enumeration value="0" />
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+            <xsd:enumeration value="3" />
+            <xsd:enumeration value="4" />
+            <xsd:enumeration value="5" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="NonEmptyThreshold" type="xsd:integer" minOccurs="0"/>
       <xsd:element name="SafetyOptions" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="0"/>
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-            <xsd:enumeration value="3"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="DbpropMsmdCacheRatio2" type="xsd:double" minOccurs="0"/>
-      <xsd:element name="DbpropMsmdUseFormulaCache" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="DbpropMsmdDynamicDebugLimit" type="xsd:integer" minOccurs="0"/>
+          <xsd:restriction base="xsd:integer" >
+            <xsd:enumeration  value="0" />
+            <xsd:enumeration  value="1" />
+            <xsd:enumeration  value="2" />
+            <xsd:enumeration  value="3" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="DbpropMsmdCacheRatio2" type="xsd:double"
+                   minOccurs="0"/>
+      <xsd:element name="DbpropMsmdUseFormulaCache" type="xsd:string"
+                   minOccurs="0"/>
+      <xsd:element name="DbpropMsmdDynamicDebugLimit" type="xsd:integer"
+                   minOccurs="0"/>
       <xsd:element name="DbpropMsmdDebugMode" type="xsd:string" minOccurs="0"/>
       <xsd:element name="Dialect" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="MDX"/>
-            <xsd:enumeration value="DMX"/>
-            <xsd:enumeration value="SQL"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="ImpactAnalysis" type="xsd:boolean" minOccurs="0"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="MDX" />
+            <xsd:enumeration value="DMX" />
+            <xsd:enumeration value="SQL" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="ImpactAnalysis" type="xsd:boolean" minOccurs="0" />
       <xsd:element name="SQLQueryMode" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Data"/>
-            <xsd:enumeration value="Calculated"/>
-            <xsd:enumeration value="IncludeEmpty"/>
-            <xsd:enumeration value="DataKeys"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Data" />
+            <xsd:enumeration value="Calculated" />
+            <xsd:enumeration value="IncludeEmpty" />
+            <xsd:enumeration value="DataKeys" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="ClientProcessID" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="Cube" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="ReturnCellProperties" type="xsd:boolean" minOccurs="0"/>
+      <xsd:element name="Cube" type="xsd:string" minOccurs="0" />
+      <xsd:element name="ReturnCellProperties" type="xsd:boolean" minOccurs="0" />
       <xsd:element name="CommitTimeout" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="ForceCommitTimeout" type="xsd:integer" minOccurs="0"/>
+      <xsd:element name="ForceCommitTimeout" type="xsd:integer"
+                   minOccurs="0"/>
       <xsd:element name="ExecutionMode" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="RealTimeOlap" type="xsd:boolean" minOccurs="0"/>
+      <xsd:element name="RealTimeOlap" type="xsd:boolean" minOccurs="0" />
       <xsd:element name="MdxMissingMemberMode" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Default"/>
-            <xsd:enumeration value="Ignore"/>
-            <xsd:enumeration value="Error"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="MdpropMdxNamedSets" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropMsmdSubqueries" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="0"/>
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Default" />
+            <xsd:enumeration value="Ignore" />
+            <xsd:enumeration value="Error" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="MdpropMdxNamedSets" type="xsd:integer"
+                   minOccurs="0" />
+      <xsd:element name="DbpropMsmdSubqueries" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:integer" >
+            <xsd:enumeration value="0" />
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="DbpropMsmdAutoExists" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="0"/>
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-            <xsd:enumeration value="3"/>
+          <xsd:restriction base="xsd:integer" >
+            <xsd:enumeration value="0" />
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+            <xsd:enumeration value="3" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="CustomData" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="DisablePrefetchFacts" type="xsd:boolean" minOccurs="0"/>
+      <xsd:element name="DisablePrefetchFacts" type="xsd:boolean"
+                   minOccurs="0" />
       <xsd:element name="UpdateIsolationLevel" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="1"/>
-            <xsd:enumeration value="2"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-      <xsd:element name="DbpropMsmdErrorMessageMode" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="MdpropMdxDdlExtensions" type="xsd:integer" minOccurs="0"/>
+          <xsd:restriction base="xsd:integer" >
+            <xsd:enumeration value="1" />
+            <xsd:enumeration value="2" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+      <xsd:element name="DbpropMsmdErrorMessageMode" type="xsd:integer"
+                   minOccurs="0"/>
+      <xsd:element name="MdpropMdxDdlExtensions" type="xsd:integer"
+                   minOccurs="0" />
       <xsd:element name="ResponseEncoding" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="Default"/>
-            <xsd:enumeration value="UTF-8"/>
-            <xsd:enumeration value="UTF-16"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Default" />
+            <xsd:enumeration value="UTF-8" />
+            <xsd:enumeration value="UTF-16" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="MemoryLockingMode" minOccurs="0">
         <xsd:simpleType>
-          <xsd:restriction base="xsd:integer">
-            <xsd:enumeration value="0"/>
-            <xsd:enumeration value="1"/>
+          <xsd:restriction base="xsd:integer" >
+            <xsd:enumeration value="0" />
+            <xsd:enumeration value="1" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
       <xsd:element name="DbpropMsmdOptimizeResponse" type="xsd:integer" minOccurs="0"/>
       <xsd:element name="DbpropMsmdActivityID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="DbpropMsmdRequestID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="ReturnAffectedObjects" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="DbpropMsmdRequestMemoryLimit" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="ApplicationContext" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="ReturnAffectedObjects" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="DbpropMsmdRequestMemoryLimit" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="ApplicationContext" type="xsd:string" minOccurs="0" />
     </xsd:all>
   </xsd:complexType>
```

##### `element` **DiscoverResponse**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Discover >
Elements > DiscoverResponse > return Element

```diff
--- definition/+++ spec/@@ -1,10 +1,11 @@-<xsd:element xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100" xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100"
xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"
xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300" xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300"
xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600"
xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922"
xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924"
xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults"
xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="DiscoverResponse">
+<xsd:element name="DiscoverResponse">
     <xsd:complexType>
       <xsd:sequence>
         <xsd:element name="return" minOccurs="0" maxOccurs="1">
           <xsd:complexType>
             <xsd:sequence>
-              <xsd:element ref="ana-rs:root" minOccurs="0" maxOccurs="1">
+              <xsd:element name= "root" ref="xmla-rs:root"
+                        minOccurs="0" maxOccurs="1">
               </xsd:element>
             </xsd:sequence>
           </xsd:complexType>

```

##### `complexType` **Command**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element

```diff
--- definition/+++ spec/@@ -1,37 +1,37 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100" xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100"
xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"
xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300" xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300"
xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600"
xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922"
xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924"
xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset"
xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Command">
+<xsd:complexType name="Command">
     <xsd:choice>
-      <xsd:element name="Statement" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Create" type="Create" minOccurs="0"/>
-      <xsd:element name="Alter" type="Alter" minOccurs="0"/>
-      <xsd:element name="Delete" type="Delete" minOccurs="0"/>
-      <xsd:element name="Process" type="Process" minOccurs="0"/>
-      <xsd:element name="MergePartitions" type="MergePartitions" minOccurs="0"/>
-      <xsd:element name="DesignAggregations" type="DesignAggregations" minOccurs="0"/>
-      <xsd:element name="ClearCache" type="ClearCache" minOccurs="0"/>
-      <xsd:element name="Subscribe" type="Subscribe" minOccurs="0"/>
-      <xsd:element name="Unsubscribe" type="Unsubscribe" minOccurs="0"/>
-      <xsd:element name="Cancel" type="Cancel" minOccurs="0"/>
-      <xsd:element name="BeginTransaction" type="BeginTransaction" minOccurs="0"/>
-      <xsd:element name="CommitTransaction" type="CommitTransaction" minOccurs="0"/>
-      <xsd:element name="RollbackTransaction" type="RollbackTransaction" minOccurs="0"/>
-      <xsd:element name="Lock" type="Lock" minOccurs="0"/>
-      <xsd:element name="Unlock" type="Unlock" minOccurs="0"/>
-      <xsd:element name="Backup" type="Backup" minOccurs="0"/>
-      <xsd:element name="Restore" type="Restore" minOccurs="0"/>
-      <xsd:element name="Synchronize" type="Synchronize" minOccurs="0"/>
-      <xsd:element name="Attach" type="Attach" minOccurs="0"/>
-      <xsd:element name="Detach" type="Detach" minOccurs="0"/>
-      <xsd:element name="Insert" type="Insert" minOccurs="0"/>
-      <xsd:element name="Update" type="Update" minOccurs="0"/>
-      <xsd:element name="Drop" type="Drop" minOccurs="0"/>
-      <xsd:element name="UpdateCells" type="UpdateCells" minOccurs="0"/>
-      <xsd:element name="NotifyTableChange" type="NotifyTableChange" minOccurs="0"/>
-      <xsd:element name="Batch" type="Batch" minOccurs="0"/>
-      <xsd:element name="ImageLoad" type="ImageLoad" minOccurs="0"/>
-      <xsd:element name="ImageSave" type="ImageSave" minOccurs="0"/>
-      <xsd:element name="CloneDatabase" type="CloneDatabase" minOccurs="0"/>
-      <xsd:element name="SetAuthContext" type="SetAuthContext" minOccurs="0"/>
-      <xsd:element name="DBCC" type="DBCC" minOccurs="0"/>
-      <xsd:element name="RemoveDiscontinuedFeatures" type="eng922:RemoveDiscontinuedFeatures" minOccurs="0"/>
+      <xsd:element name="Statement" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Create" type="Create" minOccurs="0" />
+      <xsd:element name="Alter" type="Alter" minOccurs="0" />
+      <xsd:element name="Delete" type="Delete" minOccurs="0" />
+      <xsd:element name="Process" type="Process" minOccurs="0" />
+      <xsd:element name="MergePartitions" type="MergePartitions" minOccurs="0" />
+      <xsd:element name="DesignAggregations" type="DesignAggregations" minOccurs="0" />
+      <xsd:element name="ClearCache" type="ClearCache" minOccurs="0" />
+      <xsd:element name="Subscribe" type="Subscribe" minOccurs="0" />
+      <xsd:element name="Unsubscribe" type="Unsubscribe" minOccurs="0" />
+      <xsd:element name="Cancel" type="Cancel" minOccurs="0" />
+      <xsd:element name="BeginTransaction" type="BeginTransaction" minOccurs="0" />
+      <xsd:element name="CommitTransaction" type="CommitTransaction" minOccurs="0" />
+      <xsd:element name="RollbackTransaction" type="RollbackTransaction" minOccurs="0" />
+      <xsd:element name="Lock" type="Lock" minOccurs="0" />
+      <xsd:element name="Unlock" type="Unlock" minOccurs="0" />
+      <xsd:element name="Backup" type="Backup" minOccurs="0" />
+      <xsd:element name="Restore" type="Restore" minOccurs="0" />
+      <xsd:element name="Synchronize" type="Synchronize" minOccurs="0" />
+      <xsd:element name="Attach" type="Attach" minOccurs="0" />
+      <xsd:element name="Detach" type="Detach" minOccurs="0" />
+      <xsd:element name="Insert" type="Insert" minOccurs="0" />
+      <xsd:element name="Update" type="Update" minOccurs="0" />
+      <xsd:element name="Drop" type="Drop" minOccurs="0" />
+      <xsd:element name="UpdateCells" type="UpdateCells" minOccurs="0" />
+      <xsd:element name="NotifyTableChange" type="NotifyTableChange" minOccurs="0" />
+      <xsd:element name="Batch" type="Batch" minOccurs="0" />
+      <xsd:element name="ImageLoad" type="ImageLoad" minOccurs="0" />
+      <xsd:element name="ImageSave" type="ImageSave" minOccurs="0" />
+      <xsd:element name="CloneDatabase" type="CloneDatabase" minOccurs="0" />
+      <xsd:element name="SetAuthContext" type="SetAuthContext" minOccurs="0" />
+      <xsd:element name="DBCC" type="DBCC" minOccurs="0" />
+      <xsd:element name="RemoveDiscontinuedFeatures" type="xsd:boolean" minOccurs="0" />
     </xsd:choice>
   </xsd:complexType>
```

##### `complexType` **ObjectReference**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > ObjectReference Type

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ObjectReference">
+<xsd:complexType name="ObjectReference">
     <xsd:all>
       <xsd:element name="ServerID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="DatabaseID" type="xsd:string" minOccurs="0"/>
@@ -6,11 +6,13 @@       <xsd:element name="TraceID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="AssemblyID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="DimensionID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="DimensionPermissionID" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="DimensionPermissionID" type="xsd:string"
+                   minOccurs="0"/>
       <xsd:element name="DataSourceID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="DataSourcePermissionID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="DatabasePermissionID" type="xsd:string" minOccurs="0"/>
-
+      <xsd:element name="DataSourcePermissionID" type="xsd:string"
+                   minOccurs="0"/>
+      <xsd:element name="DatabasePermissionID" type="xsd:string"
+                   minOccurs="0"/>
       <xsd:element name="DataSourceViewID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="CubeID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="MiningStructureID" type="xsd:string" minOccurs="0"/>
@@ -21,7 +23,9 @@       <xsd:element name="PartitionID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="AggregationDesignID" type="xsd:string" minOccurs="0"/>
       <xsd:element name="MiningModelID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="MiningModelPermissionID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="MiningStructurePermissionID" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="MiningModelPermissionID" type="xsd:string"
+                   minOccurs="0"/>
+      <xsd:element name="MiningStructurePermissionID" type="xsd:string"
+                   minOccurs="0"/>
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Statement**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Statement

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Statement">
+<xsd:complexType name="Statement" >
     <xsd:all>
-      <xsd:element name="Statement" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="Statement" type="xsd:string" minOccurs="0" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Create**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Create

```diff
--- definition/+++ spec/@@ -1,8 +1,14 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Create">
+<xsd:complexType name="Create">
     <xsd:all>
-      <xsd:element name="ParentObject" type="ObjectReference" minOccurs="0"/>
-      <xsd:element name="ObjectDefinition" type="MajorObject"/>
+      <xsd:element name="ParentObject" type="ObjectReference" minOccurs="0" />
+      <xsd:element name="ObjectDefinition" type="MajorObject" />
     </xsd:all>
-    <xsd:attribute name="Scope" type="Scope"/>
-    <xsd:attribute name="AllowOverwrite" type="xsd:boolean"/>
+    <xsd:attribute name="Scope" >
+      <xsd:simpleType>
+        <xsd:restriction base="xsd:string" >
+          <xsd:enumeration value="Session" />
+        </xsd:restriction>
+      </xsd:simpleType>
+    </xsd:attribute>
+    <xsd:attribute name="AllowOverwrite" type="xsd:boolean" />
   </xsd:complexType>
```

##### `complexType` **Alter**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Alter

```diff
--- definition/+++ spec/@@ -1,9 +1,22 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Alter">
+<xsd:complexType name="Alter">
     <xsd:all>
-      <xsd:element name="Object" type="ObjectReference" minOccurs="0"/>
-      <xsd:element name="ObjectDefinition" type="MajorObject"/>
+      <xsd:element name="Object" type="ObjectReference" minOccurs="0" />
+      <xsd:element name="ObjectDefinition" type="MajorObject" />
     </xsd:all>
-    <xsd:attribute name="Scope" type="Scope"/>
-    <xsd:attribute name="AllowCreate" type="xsd:boolean"/>
-    <xsd:attribute name="ObjectExpansion" type="ObjectExpansion"/>
+    <xsd:attribute name="Scope" >
+      <xsd:simpleType>
+        <xsd:restriction base="xsd:string" >
+          <xsd:enumeration value="Session" />
+        </xsd:restriction>
+      </xsd:simpleType>
+    </xsd:attribute>
+    <xsd:attribute name="AllowCreate" type="xsd:boolean" />
+    <xsd:attribute name="ObjectExpansion" >
+      <xsd:simpleType>
+        <xsd:restriction base="xsd:string" >
+          <xsd:enumeration value="ObjectProperties" />
+          <xsd:enumeration value="ExpandFull" />
+        </xsd:restriction>
+      </xsd:simpleType>
+    </xsd:attribute>
   </xsd:complexType>
```

##### `complexType` **Delete**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Delete

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Delete">
+<xsd:complexType name="Delete">
     <xsd:all>
-      <xsd:element name="Object" type="ObjectReference"/>
+      <xsd:element name="Object" type="ObjectReference" />
     </xsd:all>
-    <xsd:attribute name="IgnoreFailures" type="xsd:boolean"/>
+    <xsd:attribute name="IgnoreFailures" type="xsd:boolean" />
   </xsd:complexType>
```

##### `complexType` **Process**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Process

```diff
--- definition/+++ spec/@@ -1,28 +1,44 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Process">
+<xsd:complexType name="Process">
     <xsd:all>
-      <xsd:element name="Type">
+      <xsd:element name="Type" >
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="ProcessFull"/>
-            <xsd:enumeration value="ProcessAdd"/>
-            <xsd:enumeration value="ProcessUpdate"/>
-            <xsd:enumeration value="ProcessIndexes"/>
-            <xsd:enumeration value="ProcessScriptCache"/>
-            <xsd:enumeration value="ProcessData"/>
-            <xsd:enumeration value="ProcessDefault"/>
-            <xsd:enumeration value="ProcessClear"/>
-            <xsd:enumeration value="ProcessStructure"/>
-            <xsd:enumeration value="ProcessClearStructureOnly"/>
-            <xsd:enumeration value="ProcessClearIndexes"/>
-            <xsd:enumeration value="ProcessDefrag"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="ProcessFull" />
+            <xsd:enumeration value="ProcessAdd" />
+            <xsd:enumeration value="ProcessUpdate" />
+            <xsd:enumeration value="ProcessIndexes" />
+            <xsd:enumeration value="ProcessScriptCache" />
+            <xsd:enumeration value="ProcessData" />
+            <xsd:enumeration value="ProcessDefault" />
+            <xsd:enumeration value="ProcessClear" />
+            <xsd:enumeration value="ProcessStructure" />
+            <xsd:enumeration value="ProcessClearStructureOnly" />
+            <xsd:enumeration value="ProcessClearIndexes" />
+            <xsd:enumeration value="ProcessDefrag" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="Object" type="ObjectReference"/>
-      <xsd:element name="Bindings" type="Bindings" minOccurs="0"/>
-      <xsd:element name="DataSource" type="DataSource" minOccurs="0"/>
-      <xsd:element name="DataSourceView" type="DataSourceView" minOccurs="0"/>
-      <xsd:element name="ErrorConfiguration" type="ErrorConfiguration" minOccurs="0"/>
-      <xsd:element name="WriteBackTableCreation" type="WriteBackTableCreation" minOccurs="0"/>
+      <xsd:element name="Object" type="ObjectReference" />
+      <xsd:element name="Bindings" minOccurs="0" >
+        <xsd:complexType>
+          <xsd:sequence>
+            <xsd:element name="Binding"  type="OutOfLineBinding" minOccurs="0"
+                         maxOccurs="unbounded"/>
+          </xsd:sequence>
+        </xsd:complexType>
+      </xsd:element>
+      <xsd:element name="DataSource" type="DataSource" minOccurs="0"  />
+      <xsd:element name="DataSourceView" type="DataSourceView" minOccurs="0" />
+      <xsd:element name="ErrorConfiguration" type="ErrorConfiguration"
+                   minOccurs="0" />
+      <xsd:element name="WriteBackTableCreation" minOccurs="0" >
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Create" />
+            <xsd:enumeration value="CreateAlways" />
+            <xsd:enumeration value="UseExisting" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **OutOfLineBinding**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Process > OutOfLineBinding

```diff
--- definition/+++ spec/@@ -1,94 +1,95 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="OutOfLineBinding">
+<xsd:complexType name="OutOfLineBinding" >
     <xsd:all>
-      <xsd:element name="DatabaseID" minOccurs="0" type="xsd:string"/>
-      <xsd:element name="DimensionID" minOccurs="0" type="xsd:string"/>
-      <xsd:element name="CubeID" minOccurs="0" type="xsd:string"/>
-      <xsd:element name="MeasureGroupID" minOccurs="0" type="xsd:string"/>
-      <xsd:element name="PartitionID" minOccurs="0" type="xsd:string"/>
-      <xsd:element name="MiningModelID" minOccurs="0" type="xsd:string"/>
-      <xsd:element name="MiningStructureID" minOccurs="0" type="xsd:string"/>
-      <xsd:element name="AttributeID" minOccurs="0" type="xsd:string"/>
-      <xsd:element name="CubeDimensionID" minOccurs="0" type="xsd:string"/>
-      <xsd:element name="MeasureID" minOccurs="0" type="xsd:string"/>
-      <xsd:element name="ParentColumnID" minOccurs="0" type="xsd:string"/>
-      <xsd:element name="ColumnID" minOccurs="0" type="xsd:string"/>
-      <xsd:element name="Source" minOccurs="0" type="Binding"/>
-      <xsd:element name="NameColumn" minOccurs="0">
+      <xsd:element name="DatabaseID" minOccurs="0"  type="xsd:string" />
+      <xsd:element name="DimensionID" minOccurs="0"  type="xsd:string" />
+      <xsd:element name="CubeID" minOccurs="0"  type="xsd:string" />
+      <xsd:element name="MeasureGroupID" minOccurs="0"  type="xsd:string" />
+      <xsd:element name="PartitionID" minOccurs="0"  type="xsd:string" />
+      <xsd:element name="MiningModelID" minOccurs="0"  type="xsd:string" />
+      <xsd:element name="MiningStructureID" minOccurs="0"  type="xsd:string" />
+      <xsd:element name="AttributeID" minOccurs="0"  type="xsd:string" />
+      <xsd:element name="CubeDimensionID" minOccurs="0"  type="xsd:string" />
+      <xsd:element name="MeasureID" minOccurs="0"  type="xsd:string" />
+      <xsd:element name="ParentColumnID" minOccurs="0"  type="xsd:string" />
+      <xsd:element name="ColumnID" minOccurs="0"  type="xsd:string" />
+      <xsd:element name="Source" minOccurs="0"  type="Binding" />
+      <xsd:element name="NameColumn" minOccurs="0" >
         <xsd:complexType>
           <xsd:all>
-            <xsd:element name="Source" minOccurs="0" type="Binding"/>
+            <xsd:element name="Source" minOccurs="0"  type="Binding" />
           </xsd:all>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="SkippedLevelsColumn" minOccurs="0">
+      <xsd:element name="SkippedLevelsColumn" minOccurs="0" >
         <xsd:complexType>
           <xsd:all>
-            <xsd:element name="Source" minOccurs="0" type="Binding"/>
+            <xsd:element name="Source" minOccurs="0"  type="Binding" />
           </xsd:all>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="CustomRollupColumn" minOccurs="0">
+      <xsd:element name="CustomRollupColumn" minOccurs="0" >
         <xsd:complexType>
           <xsd:all>
-            <xsd:element name="Source" minOccurs="0" type="Binding"/>
+            <xsd:element name="Source" minOccurs="0"  type="Binding" />
           </xsd:all>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="CustomRollupPropertiesColumn" minOccurs="0">
+      <xsd:element name="CustomRollupPropertiesColumn" minOccurs="0" >
         <xsd:complexType>
           <xsd:all>
-            <xsd:element name="Source" minOccurs="0" type="Binding"/>
+            <xsd:element name="Source" minOccurs="0"  type="Binding" />
           </xsd:all>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="ValueColumn" minOccurs="0">
+      <xsd:element name="ValueColumn" minOccurs="0" >
         <xsd:complexType>
           <xsd:all>
-            <xsd:element name="Source" minOccurs="0" type="Binding"/>
+            <xsd:element name="Source" minOccurs="0"  type="Binding" />
           </xsd:all>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="UnaryOperatorColumn" minOccurs="0">
+      <xsd:element name="UnaryOperatorColumn" minOccurs="0" >
         <xsd:complexType>
           <xsd:all>
-            <xsd:element name="Source" minOccurs="0" type="Binding"/>
+            <xsd:element name="Source" minOccurs="0"  type="Binding" />
           </xsd:all>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="KeyColumns" minOccurs="0">
+      <xsd:element name="KeyColumns" minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
             <xsd:element name="KeyColumn" minOccurs="0" maxOccurs="unbounded">
               <xsd:complexType>
                 <xsd:all>
-                  <xsd:element name="Source" minOccurs="0" type="Binding"/>
+                  <xsd:element name="Source" minOccurs="0"  type="Binding" />
                 </xsd:all>
               </xsd:complexType>
             </xsd:element>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="ForeignKeyColumns" minOccurs="0">
+      <xsd:element name="ForeignKeyColumns" minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="ForeignKeyColumn" minOccurs="0" maxOccurs="unbounded">
+            <xsd:element name="ForeignKeyColumn" minOccurs="0"
+                         maxOccurs="unbounded">
               <xsd:complexType>
                 <xsd:all>
-                  <xsd:element name="Source" minOccurs="0" type="Binding"/>
+                  <xsd:element name="Source" minOccurs="0"  type="Binding" />
                 </xsd:all>
               </xsd:complexType>
             </xsd:element>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="Translations" minOccurs="0">
+      <xsd:element name="Translations" minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
             <xsd:element name="Translation" minOccurs="0" maxOccurs="unbounded">
               <xsd:complexType>
                 <xsd:all>
-                  <xsd:element name="Language" type="xsd:int"/>
-                  <xsd:element name="Source" minOccurs="0" type="Binding"/>
+                  <xsd:element name="Language" type="xsd:int" />
+                  <xsd:element name="Source" minOccurs="0"  type="Binding" />
                 </xsd:all>
               </xsd:complexType>
             </xsd:element>

```

##### `complexType` **MergePartitions**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > MergePartitions

```diff
--- definition/+++ spec/@@ -1,12 +1,13 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="MergePartitions">
+<xsd:complexType name="MergePartitions">
     <xsd:all>
-      <xsd:element name="Sources">
+      <xsd:element name="Sources" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Source" type="ObjectReference" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Source"  type="ObjectReference" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="Target" type="ObjectReference"/>
+      <xsd:element name="Target" type="ObjectReference" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **DesignAggregations**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > DesignAggregations

```diff
--- definition/+++ spec/@@ -1,15 +1,16 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="DesignAggregations">
+<xsd:complexType name="DesignAggregations">
     <xsd:all>
-      <xsd:element name="Object" type="ObjectReference"/>
-      <xsd:element name="Time" type="xsd:duration" minOccurs="0"/>
-      <xsd:element name="Steps" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="Optimization" type="xsd:double" minOccurs="0"/>
-      <xsd:element name="Storage" type="xsd:long" minOccurs="0"/>
-      <xsd:element name="Materialize" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="Queries" minOccurs="0">
+      <xsd:element name="Object" type="ObjectReference" />
+      <xsd:element name="Time" type="xsd:duration" minOccurs="0" />
+      <xsd:element name="Steps" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="Optimization" type="xsd:double" minOccurs="0" />
+      <xsd:element name="Storage" type="xsd:long" minOccurs="0" />
+      <xsd:element name="Materialize" type="xsd:boolean" minOccurs="0" />
+      <xsd:element name="Queries" minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Query" type="xsd:string" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Query"  type="xsd:string" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **ClearCache**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > ClearCache

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ClearCache">
+<xsd:complexType name="ClearCache">
     <xsd:all>
-      <xsd:element name="Object" type="ObjectReference"/>
+      <xsd:element name="Object" type="ObjectReference" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Cancel**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Cancel

```diff
--- definition/+++ spec/@@ -1,8 +1,8 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Cancel">
+<xsd:complexType name="Cancel">
     <xsd:all>
-      <xsd:element name="ConnectionID" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="SessionID" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="SPID" type="xsd:integer" minOccurs="0"/>
-      <xsd:element name="CancelAssociated" type="xsd:boolean" minOccurs="0"/>
+      <xsd:element name="ConnectionID" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="SessionID" type="xsd:string" minOccurs="0" />
+      <xsd:element name="SPID" type="xsd:integer" minOccurs="0" />
+      <xsd:element name="CancelAssociated" type="xsd:boolean" minOccurs="0" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **BeginTransaction**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > BeginTransaction

```diff
--- definition/+++ spec/@@ -1 +1 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="BeginTransaction"/>+<xsd:complexType name="BeginTransaction" />
```

##### `complexType` **CommitTransaction**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > CommitTransaction

```diff
--- definition/+++ spec/@@ -1,12 +1,12 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="CommitTransaction">
-    <xsd:all>
-      <xsd:element name="DurabilityGuarantee" minOccurs="0">
-        <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="LocalDisk"/>
-            <xsd:enumeration value="Full"/>
-          </xsd:restriction>
-        </xsd:simpleType>
-      </xsd:element>
-    </xsd:all>
-  </xsd:complexType>+<xsd:complexType name="CommitTransaction">
+    <xsd:all>
+      <xsd:element name="DurabilityGuarantee" minOccurs="0">
+        <xsd:simpleType>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="LocalDisk" />
+            <xsd:enumeration value="Full" />
+          </xsd:restriction>
+        </xsd:simpleType>
+      </xsd:element>
+    </xsd:all>
+  </xsd:complexType>
```

##### `complexType` **RollbackTransaction**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > RollbackTransaction

```diff
--- definition/+++ spec/@@ -1 +1 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="RollbackTransaction"/>+<xsd:complexType name="RollbackTransaction" />
```

##### `complexType` **Lock**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Lock

```diff
--- definition/+++ spec/@@ -1,18 +1,21 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Lock">
+<xsd:complexType name="Lock">
     <xsd:all>
       <xsd:element name="ID">
         <xsd:simpleType>
           <xsd:restriction base="xsd:token">
-            <xsd:pattern value="[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z] {4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}"/>
+            <xsd:pattern value="[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]
+                {4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}"/>
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="Object" type="ObjectReference"/>
-      <xsd:element name="Mode">
+      <xsd:element name="Object" type="ObjectReference" />
+      <xsd:element name="Mode" >
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="CommitShared"/>
-            <xsd:enumeration value="CommitExclusive"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="Read" />
+            <xsd:enumeration value="Write" />
+            <xsd:enumeration value="CommitShared" />
+            <xsd:enumeration value="CommitExclusive" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>

```

##### `complexType` **Unlock**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Unlock

```diff
--- definition/+++ spec/@@ -1,9 +1,10 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Unlock">
+<xsd:complexType name="Unlock">
     <xsd:all>
       <xsd:element name="ID">
         <xsd:simpleType>
           <xsd:restriction base="xsd:token">
-            <xsd:pattern value="[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z] {4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}"/>
+            <xsd:pattern value="[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]
+                {4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}"/>
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>

```

##### `complexType` **Backup**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Backup

```diff
--- definition/+++ spec/@@ -1,24 +1,26 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Backup">
+<xsd:complexType name="Backup">
     <xsd:all>
-      <xsd:element name="Object" type="ObjectReference"/>
-      <xsd:element name="File" type="xsd:string"/>
-      <xsd:element name="Security" minOccurs="0">
+      <xsd:element name="Object" type="ObjectReference" />
+      <xsd:element name="File" type="xsd:string" />
+      <xsd:element name="Security" minOccurs="0" >
         <xsd:simpleType>
-          <xsd:restriction base="xsd:string">
-            <xsd:enumeration value="SkipMembership"/>
-            <xsd:enumeration value="CopyAll"/>
-            <xsd:enumeration value="IgnoreSecurity"/>
+          <xsd:restriction base="xsd:string" >
+            <xsd:enumeration value="SkipMembership" />
+            <xsd:enumeration value="CopyAll" />
+            <xsd:enumeration value="IgnoreSecurity" />
           </xsd:restriction>
         </xsd:simpleType>
       </xsd:element>
-      <xsd:element name="ApplyCompression" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="AllowOverwrite" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="Password" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="BackupRemotePartitions" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="Locations" minOccurs="0">
+      <xsd:element name="ApplyCompression" type="xsd:boolean" minOccurs="0" />
+      <xsd:element name="AllowOverwrite" type="xsd:boolean" minOccurs="0" />
+      <xsd:element name="Password" type="xsd:string" minOccurs="0" />
+      <xsd:element name="BackupRemotePartitions" type="xsd:boolean"
+      minOccurs="0" />
+      <xsd:element name="Locations" minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Location" type="Location_Backup" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Location"  type="Location_Backup" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **Location_Backup**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Backup > Location_Backup

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Location_Backup">
+<xsd:complexType name="Location_Backup">
     <xsd:all>
-      <xsd:element name="File" type="xsd:string"/>
-      <xsd:element name="DataSourceID" type="xsd:string"/>
+      <xsd:element name="File" type="xsd:string" />
+      <xsd:element name="DataSourceID" type="xsd:string" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Location**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Restore > Location

```diff
--- definition/+++ spec/@@ -1,6 +1,26 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Location">
+<xsd:complexType name="Location">
     <xsd:complexContent>
-      <xsd:extension base="Location_Backup">
-      </xsd:extension>
+      <xsd:extension base="Location_Backup"  >
+        <xsd:all>
+          <xsd:element name="DataSourceType" minOccurs="0" >
+            <xsd:simpleType>
+              <xsd:restriction base="xsd:string" >
+                <xsd:enumeration value="Remote" />
+                <xsd:enumeration value="Local" />
+              </xsd:restriction>
+            </xsd:simpleType>
+          </xsd:element>
+          <xsd:element name="ConnectionString" type="xsd:string"
+                       minOccurs="0" />
+          <xsd:element name="Folders" minOccurs="0" >
+            <xsd:complexType>
+              <xsd:sequence>
+                <xsd:element name="Folder"  type="Folder" minOccurs="0"
+                             maxOccurs="unbounded"/>
+              </xsd:sequence>
+            </xsd:complexType>
+          </xsd:element>
+        </xsd:all>
+      </xsd:extension >
     </xsd:complexContent>
   </xsd:complexType>
```

##### `complexType` **Folder**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Restore > Folder

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Folder">
+<xsd:complexType name="Folder">
     <xsd:all>
-      <xsd:element name="Original" type="xsd:string"/>
-      <xsd:element name="New" type="xsd:string"/>
+      <xsd:element name="Original" type="xsd:string" />
+      <xsd:element name="New" type="xsd:string" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Source**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Synchronize > Source

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Source">
+<xsd:complexType name="Source">
     <xsd:all>
-      <xsd:element name="Object" type="ObjectReference"/>
-      <xsd:element name="ConnectionString" type="xsd:string"/>
+      <xsd:element name="Object" type="ObjectReference" />
+      <xsd:element name="ConnectionString" type="xsd:string" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Detach**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Detach

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Detach">
+<xsd:complexType name="Detach">
     <xsd:all>
-      <xsd:element name="Object" type="ObjectReference"/>
-      <xsd:element name="Password" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="Object" type="ObjectReference" />
+      <xsd:element name="Password" type="xsd:string" minOccurs="0" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Insert**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Insert

```diff
--- definition/+++ spec/@@ -1,10 +1,11 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Insert">
+<xsd:complexType name="Insert">
     <xsd:all>
-      <xsd:element name="Object" type="Object"/>
-      <xsd:element name="Attributes" minOccurs="0">
+      <xsd:element name="Object" type="Object" />
+      <xsd:element name="Attributes" minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Attribute" type="Attribute_InsertUpdate" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Attribute"  type="Attribute_InsertUpdate"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **Object**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Insert > Object

```diff
--- definition/+++ spec/@@ -1,7 +1,7 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Object">
+<xsd:complexType name="Object">
     <xsd:all>
-      <xsd:element name="Database" type="xsd:string"/>
-      <xsd:element name="Cube" type="xsd:string"/>
-      <xsd:element name="Dimension" type="xsd:string"/>
+      <xsd:element name="Database" type="xsd:string" />
+      <xsd:element name="Cube" type="xsd:string" />
+      <xsd:element name="Dimension" type="xsd:string" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Attribute_InsertUpdate**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Insert > Attribute_InsertUpdate

```diff
--- definition/+++ spec/@@ -1,25 +1,27 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Attribute_InsertUpdate">
+<xsd:complexType name="Attribute_InsertUpdate">
     <xsd:all>
-      <xsd:element name="AttributeName" type="xsd:string"/>
-      <xsd:element name="Name" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="Keys" minOccurs="0">
+      <xsd:element name="AttributeName" type="xsd:string" />
+      <xsd:element name="Name" type="xsd:string" minOccurs="0" />
+      <xsd:element name="Keys" minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Key" type="xsd:anySimpleType" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Key" type="xsd:anySimpleType" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="Translations" minOccurs="0">
+      <xsd:element name="Translations" minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Translation" type="Translation_InsertUpdate" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Translation"  type="Translation_InsertUpdate"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="Value" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="CUSTOM_ROLLUP" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="CUSTOM_ROLLUP_PROPERTIES" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="UNARY_OPERATOR" type="xsd:string" minOccurs="0"/>
-      <xsd:element name="SKIPPED_LEVELS" type="xsd:integer" minOccurs="0"/>
+      <xsd:element name="Value" type="xsd:string" minOccurs="0" />
+      <xsd:element name="CUSTOM_ROLLUP" type="xsd:string" minOccurs="0" />
+      <xsd:element name="CUSTOM_ROLLUP_PROPERTIES" type="xsd:string" minOccurs="0" />
+      <xsd:element name="UNARY_OPERATOR" type="xsd:string" minOccurs="0" />
+      <xsd:element name="SKIPPED_LEVELS" type="xsd:integer" minOccurs="0" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Translation_InsertUpdate**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Insert > Translation_InsertUpdate

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Translation_InsertUpdate">
+<xsd:complexType name="Translation_InsertUpdate">
     <xsd:all>
-      <xsd:element name="Language" type="xsd:integer"/>
-      <xsd:element name="Name" type="xsd:string" minOccurs="0"/>
+      <xsd:element name="Language" type="xsd:integer" />
+      <xsd:element name="Name" type="xsd:string" minOccurs="0" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Update**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Update

```diff
--- definition/+++ spec/@@ -1,15 +1,18 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Update">
+<xsd:complexType name="Update">
     <xsd:all>
-      <xsd:element name="Object" type="Object"/>
-      <xsd:element name="Attributes" minOccurs="0">
+      <xsd:element name="Object" type="Object" />
+      <xsd:element name="Attributes" minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Attribute" type="Attribute_InsertUpdate" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Attribute"  type="Attribute_InsertUpdate"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="MoveWithDescendants" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="MoveToRoot" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="Where" type="Where"/>
+      <xsd:element name="MoveWithDescendants" type="xsd:boolean"
+                   minOccurs="0"/>
+      <xsd:element name="MoveToRoot" type="xsd:boolean"
+                   minOccurs="0"/>
+      <xsd:element name="Where"  type="Where" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Where**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Update > Where

```diff
--- definition/+++ spec/@@ -1,5 +1,5 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Where">
+<xsd:complexType name="Where">
     <xsd:all>
-      <xsd:element name="Attribute" type="Where_Attribute"/>
+      <xsd:element name="Attribute"  type="Where_Attribute" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **Where_Attribute**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Update > Where_Attribute

```diff
--- definition/+++ spec/@@ -1,10 +1,11 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Where_Attribute">
+<xsd:complexType name="Where_Attribute">
     <xsd:all>
-      <xsd:element name="AttributeName" type="xsd:string"/>
-      <xsd:element name="Keys" minOccurs="0">
+      <xsd:element name="AttributeName" type="xsd:string" />
+      <xsd:element name="Keys" minOccurs="0" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Key" type="xsd:anySimpleType" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Key" type="xsd:anySimpleType" minOccurs="0"
+                         maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **Drop**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Drop

```diff
--- definition/+++ spec/@@ -1,7 +1,8 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Drop">
+<xsd:complexType name="Drop">
     <xsd:all>
-      <xsd:element name="Object" type="Object"/>
-      <xsd:element name="DeleteWithDescendants" type="xsd:boolean" minOccurs="0"/>
-      <xsd:element name="Where" type="Where"/>
+      <xsd:element name="Object" type="Object" />
+      <xsd:element name="DeleteWithDescendants" type="xsd:boolean"
+                   minOccurs="0" />
+      <xsd:element name="Where" type="Where" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **UpdateCells**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > UpdateCells

```diff
--- definition/+++ spec/@@ -1,5 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="UpdateCells">
+<xsd:complexType name="UpdateCells">
     <xsd:sequence>
-      <xsd:element name="Cell" type="Cell" minOccurs="0" maxOccurs="unbounded"/>
+      <xsd:element name="Cell"  type="Cell" minOccurs="0"
+                   maxOccurs="unbounded"/>
     </xsd:sequence>
   </xsd:complexType>
```

##### `element` **KeepResult**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > UpdateCells

```diff
--- definition/+++ spec/@@ -1,7 +1,7 @@-<xsd:element xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="KeepResult">
-    <xsd:complexType>
+<xsd:element name="KeepResult" >
+    <xsd:complexType >
       <xsd:sequence>
-        <!--The KeepResult element MUST be empty -->
+        <!--The KeepResult element MUST be empty-->
       </xsd:sequence>
     </xsd:complexType>
   </xsd:element>
```

##### `element` **Result**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > UpdateCells

```diff
--- definition/+++ spec/@@ -1,7 +1,7 @@-<xsd:element xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Result">
+<xsd:element name="Result" >
     <xsd:complexType>
       <xsd:sequence>
-        <xsd:element name="ResultId" type="xsd:string"/>
+        <xsd:element name="ResultId" type="xsd:string" />
       </xsd:sequence>
     </xsd:complexType>
   </xsd:element>
```

##### `element` **ClearResult**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > UpdateCells

```diff
--- definition/+++ spec/@@ -1,7 +1,7 @@-<xsd:element xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ClearResult">
+<xsd:element name="ClearResult" >
     <xsd:complexType>
       <xsd:sequence>
-        <xsd:element name="ResultId" type="xsd:string"/>
+        <xsd:element name="ResultId" type="xsd:string" />
       </xsd:sequence>
     </xsd:complexType>
   </xsd:element>
```

##### `complexType` **Cell**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > UpdateCells > Cell

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Cell">
+<xsd:complexType name="Cell">
     <xsd:all>
-      <xsd:element name="Value" type="xsd:anySimpleType"/>
+      <xsd:element name="Value" type="xsd:anySimpleType" />
     </xsd:all>
-    <xsd:attribute name="CellOrdinal" type="xsd:long"/>
+    <xsd:attribute name="CellOrdinal" type="xsd:long" />
   </xsd:complexType>
```

##### `complexType` **NotifyTableChange**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > NotifyTableChange

```diff
--- definition/+++ spec/@@ -1,10 +1,11 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="NotifyTableChange">
+<xsd:complexType name="NotifyTableChange">
     <xsd:all>
-      <xsd:element name="Object" type="ObjectReference"/>
-      <xsd:element name="TableNotifications">
+      <xsd:element name="Object" type="ObjectReference" />
+      <xsd:element name="TableNotifications"  >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="TableNotification" type="TableNotification" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="TableNotification"  type="TableNotification"
+                         minOccurs="0" maxOccurs="unbounded"/>
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>

```

##### `complexType` **Batch**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > Batch

```diff
--- definition/+++ spec/@@ -1,49 +1,81 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="Batch">
+<xsd:complexType name="Batch">
     <xsd:sequence>
-      <xsd:element name="Parallel" minOccurs="0" maxOccurs="unbounded">
+      <xsd:element name="Parallel" minOccurs="0" maxOccurs="unbounded" >
         <xsd:complexType>
           <xsd:sequence>
-            <xsd:element name="Process" type="Process" minOccurs="0" maxOccurs="unbounded"/>
+            <xsd:element name="Process" type="Process" minOccurs="0"
+                         maxOccurs="unbounded" />
           </xsd:sequence>
-          <xsd:attribute name="MaxParallel" type="xsd:int" default="0"/>
+          <xsd:attribute name="MaxParallel" type="xsd:int"  default="0" />
         </xsd:complexType>
       </xsd:element>
-      <xsd:element name="Bindings" minOccurs="0" type="OutOfLineBinding"/>
-      <xsd:element name="DataSource" minOccurs="0" type="DataSource"/>
-      <xsd:element name="DataSourceView" type="DataSourceView" minOccurs="0"/>
-      <xsd:element name="ErrorConfiguration" type="ErrorConfiguration" minOccurs="0"/>
-      <xsd:element name="Create" type="Create" minOccurs="0" maxOccurs="unbounded"/>
-      <xsd:element name="Alter" type="Alter" minOccurs="0" maxOccurs="unbounded"/>
-      <xsd:element name="Delete" type="Delete" minOccurs="0" maxOccurs="unbounded"/>
-      <xsd:element name="Process" type="Process" minOccurs="0" maxOccurs="unbounded"/>
-      <xsd:element name="MergePartitions" type="MergePartitions" minOccurs="0" maxOccurs="unbounded"/>
-      <xsd:element name="DesignAggregations" type="DesignAggregations" minOccurs="0" maxOccurs="unbounded"/>
-      <xsd:element name="NotifyTableChange" type="NotifyTableChange" minOccurs="0" maxOccurs="unbounded"/>
-      <xsd:element name="Insert" minOccurs="0" type="Insert" maxOccurs="unbounded"/>
-      <xsd:element name="Update" minOccurs="0" type="Update" maxOccurs="unbounded"/>
-      <xsd:element name="Drop" minOccurs="0" type="Drop" maxOccurs="unbounded"/>
-      <xsd:element name="UpdateCells" minOccurs="0" type="UpdateCells" maxOccurs="unbounded"/>
-      <xsd:element name="Backup" minOccurs="0" type="Backup" maxOccurs="unbounded"/>
-      <xsd:element name="Restore" minOccurs="0" type="Restore" maxOccurs="unbounded"/>
-      <xsd:element name="Synchronize" minOccurs="0" type="Synchronize" maxOccurs="unbounded"/>
-      <xsd:element name="Cancel" minOccurs="0" type="Cancel" maxOccurs="unbounded"/>
-      <xsd:element name="BeginTransaction" minOccurs="0" type="BeginTransaction" maxOccurs="unbounded"/>
-      <xsd:element name="CommitTransaction" minOccurs="0" type="CommitTransaction" maxOccurs="unbounded"/>
-      <xsd:element name="RollbackTransaction" minOccurs="0" type="RollbackTransaction" maxOccurs="unbounded"/>
-      <xsd:element name="ClearCache" minOccurs="0" type="ClearCache" maxOccurs="unbounded"/>
-      <xsd:element name="Subscribe" minOccurs="0" type="Subscribe" maxOccurs="unbounded"/>
-      <xsd:element name="Unsubscribe" minOccurs="0" type="Unsubscribe" maxOccurs="unbounded"/>
-      <xsd:element name="Detach" minOccurs="0" type="Detach" maxOccurs="unbounded"/>
-      <xsd:element name="Attach" minOccurs="0" type="Attach" maxOccurs="unbounded"/>
-      <xsd:element name="Lock" minOccurs="0" type="Lock" maxOccurs="unbounded"/>
-      <xsd:element name="Unlock" minOccurs="0" type="Unlock" maxOccurs="unbounded"/>
-      <xsd:element name="ImageLoad" minOccurs="0" type="ImageLoad" maxOccurs="unbounded"/>
-      <xsd:element name="ImageSave" minOccurs="0" type="ImageSave" maxOccurs="unbounded"/>
-      <xsd:element name="CloneDatabase" minOccurs="0" type="CloneDatabase" maxOccurs="unbounded"/>
-      <xsd:element name="SetAuthContext" minOccurs="0" type="SetAuthContext" maxOccurs="unbounded"/>
-      <xsd:element name="DBCC" type="DBCC" minOccurs="0"/>
-      <xsd:element ref="Discover" minOccurs="0" maxOccurs="unbounded"/>
+      <xsd:element name="Bindings" minOccurs="0" type="OutOfLineBinding" />
+      <xsd:element name="DataSource" minOccurs="0" type="DataSource" />
+      <xsd:element name="DataSourceView" type="DataSourceView"
+                  minOccurs="0" />
+      <xsd:element name="ErrorConfiguration" type="ErrorConfiguration"
+                  minOccurs="0" />
+      <xsd:element name="Create" type="Create" minOccurs="0"
+                   maxOccurs="unbounded" />
+      <xsd:element name="Alter" type="Alter" minOccurs="0"
+                   maxOccurs="unbounded" />
+      <xsd:element name="Delete" type="Delete" minOccurs="0"
+                   maxOccurs="unbounded" />
+      <xsd:element name="Process" type="Process" minOccurs="0"
+                  maxOccurs="unbounded" />
+      <xsd:element name="MergePartitions" type="MergePartitions"
+                  minOccurs="0" maxOccurs="unbounded" />
+      <xsd:element name="DesignAggregations" type="DesignAggregations"
+                  minOccurs="0" maxOccurs="unbounded" />
+      <xsd:element name="NotifyTableChange" type="NotifyTableChange"
+                  minOccurs="0" maxOccurs="unbounded" />
+      <xsd:element name="Insert" minOccurs="0" type="Insert"
+                  maxOccurs="unbounded" />
+      <xsd:element name="Update" minOccurs="0" type="Update"
+                  maxOccurs="unbounded" />
+      <xsd:element name="Drop" minOccurs="0" type="Drop"
+                  maxOccurs="unbounded" />
+      <xsd:element name="UpdateCells" minOccurs="0" type="UpdateCells"
+                  maxOccurs="unbounded" />
+      <xsd:element name="Backup" minOccurs="0" type="Backup"
+                  maxOccurs="unbounded" />
+      <xsd:element name="Restore" minOccurs="0" type="Restore"
+                  maxOccurs="unbounded" />
+      <xsd:element name="Synchronize" minOccurs="0" type="Synchronize"
+                  maxOccurs="unbounded" />
+      <xsd:element name="Cancel" minOccurs="0" type="Cancel"
+                  maxOccurs="unbounded" />
+      <xsd:element name="BeginTransaction" minOccurs="0" type="BeginTransaction"
+                  maxOccurs="unbounded" />
+      <xsd:element name="CommitTransaction" minOccurs="0" type="CommitTransaction"
+                  maxOccurs="unbounded" />
+      <xsd:element name="RollbackTransaction" minOccurs="0" type="RollbackTransaction"
+                  maxOccurs="unbounded" />
+      <xsd:element name="ClearCache" minOccurs="0" type="ClearCache"
+                  maxOccurs="unbounded" />
+      <xsd:element name="Subscribe" minOccurs="0" type="Subscribe"
+                  maxOccurs="unbounded" />
+      <xsd:element name="Unsubscribe" minOccurs="0" type="Unsubscribe"
+                  maxOccurs="unbounded" />
+      <xsd:element name="Detach" minOccurs="0" type="Detach"
+                  maxOccurs="unbounded" />
+      <xsd:element name="Attach" minOccurs="0" type="Attach"
+                  maxOccurs="unbounded" />
+      <xsd:element name="Lock" minOccurs="0" type="Lock"
+                  maxOccurs="unbounded" />
+      <xsd:element name="Unlock" minOccurs="0" type="Unlock"
+                  maxOccurs="unbounded" />
+      <xsd:element name="ImageLoad" minOccurs="0" type="ImageLoad"
+                  maxOccurs="unbounded" />
+      <xsd:element name="ImageSave" minOccurs="0" type="ImageSave"
+                  maxOccurs="unbounded" />
+      <xsd:element name="CloneDatabase" minOccurs="0" type="CloneDatabase"
+                  maxOccurs="unbounded" />
+      <xsd:element name="SetAuthContext" minOccurs="0" type="SetAuthContext"
+                  maxOccurs="unbounded" />
+      <xsd:element name="Discover" minOccurs="0" type="Discover"
+                  maxOccurs="unbounded" />
     </xsd:sequence>
-    <xsd:attribute name="Transaction" type="xsd:boolean"/>
-    <xsd:attribute name="ProcessAffectedObjects" type="xsd:boolean"/>
+    <xsd:attribute name="Transaction" type="xsd:boolean" />
+    <xsd:attribute name="ProcessAffectedObjects" type="xsd:boolean" />
   </xsd:complexType>
```

##### `complexType` **ImageSave**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > ImageSave

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis" xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2" xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100"
    xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100" xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200" xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300"
    xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300" xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600" xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
    xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922" xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924" xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925"
    xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset" xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ImageSave">
+<xsd:complexType name="ImageSave">
     <xsd:all>
-      <xsd:element name="Object" type="ObjectReference"/>
-      <xsd:element name="Data" type="xsd:boolean" minOccurs="0"/>
+      <xsd:element name="Object" type="ObjectReference" />
+      <xsd:element name="Data" type="xsd:boolean" minOccurs="0" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **DBCC**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Command Element > DBCC

```diff
--- definition/+++ spec/@@ -1,4 +1,4 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100" xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100"
xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"
xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300" xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300"
xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600"
xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922"
xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924"
xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty"
xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults" xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="DBCC">
+<xsd:complexType name="DBCC">
     <xsd:all>
       <xsd:element name="Object" type="ObjectReference" minOccurs="0" maxOccurs="1"/>
     </xsd:all>

```

##### `complexType` **ExecuteParameter**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
Execute > Parameters > ExecuteParameter

```diff
--- definition/+++ spec/@@ -1,6 +1,6 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100" xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100"
xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"
xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300" xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300"
xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600"
xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922"
xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924"
xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults"
xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="ExecuteParameter">
+<xsd:complexType name="ExecuteParameter">
     <xsd:all>
-      <xsd:element name="Name" minOccurs="1" type="xsd:string"/>
-      <xsd:element name="Value" minOccurs="1" type="xsd:anyType"/>
+      <xsd:element name="Name" minOccurs="1" type="xsd:string" />
+      <xsd:element name="Value" minOccurs="1" type="xsd:anyType" />
     </xsd:all>
   </xsd:complexType>
```

##### `complexType` **return**

Spec location: Protocol Details > Server Details > Message Processing Events and Sequencing Rules > Execute > Elements >
ExecuteResponse > return Element

```diff
--- definition/+++ spec/@@ -1,8 +1,8 @@-<xsd:complexType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:schemas-microsoft-com:xml-analysis"
xmlns:eng="http://schemas.microsoft.com/analysisservices/2003/engine" xmlns:eng2="http://schemas.microsoft.com/analysisservices/2003/engine/2"
xmlns:eng100="http://schemas.microsoft.com/analysisservices/2008/engine/100" xmlns:eng100_100="http://schemas.microsoft.com/analysisservices/2008/engine/100/100"
xmlns:eng200="http://schemas.microsoft.com/analysisservices/2010/engine/200" xmlns:eng200_200="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"
xmlns:eng300="http://schemas.microsoft.com/analysisservices/2011/engine/300" xmlns:eng300_300="http://schemas.microsoft.com/analysisservices/2011/engine/300/300"
xmlns:eng400="http://schemas.microsoft.com/analysisservices/2012/engine/400" xmlns:eng600="http://schemas.microsoft.com/analysisservices/2013/engine/600"
xmlns:eng800="http://schemas.microsoft.com/analysisservices/2013/engine/800" xmlns:eng920="http://schemas.microsoft.com/analysisservices/2022/engine/920"
xmlns:eng921="http://schemas.microsoft.com/analysisservices/2022/engine/921" xmlns:eng922="http://schemas.microsoft.com/analysisservices/2022/engine/922"
xmlns:eng923="http://schemas.microsoft.com/analysisservices/2025/engine/923" xmlns:eng924_924="http://schemas.microsoft.com/analysisservices/2025/engine/924/924"
xmlns:eng925="http://schemas.microsoft.com/analysisservices/2025/engine/925" xmlns:ana-ds="urn:schemas-microsoft-com:xml-analysis:mddataset"
xmlns:ana-rs="urn:schemas-microsoft-com:xml-analysis:rowset" xmlns:ana-e="urn:schemas-microsoft-com:xml-analysis:empty" xmlns:ana-m="http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults"
xmlns:jaxb="https://jakarta.ee/xml/ns/jaxb" name="return">
+<xsd:complexType name="return">
     <xsd:choice>
-      <xsd:element ref="ana-ds:root"/>
-      <xsd:element ref="ana-rs:root"/>
-      <xsd:element ref="ana-e:root"/>
-      <xsd:element ref="ana-m:results"/>
+      <xsd:element ref="xmla-ds:root" />
+      <xsd:element ref="xmla-rs:root" />
+      <xsd:element ref="xmla-e:root" />
+      <xsd:element ref="xmla-m:results" />
     </xsd:choice>
   </xsd:complexType>
```

#### Extra in definition (not in spec) (50)

- `complexType` **Properties**
- `complexType` **Parameters**
- `complexType` **Bindings**
- `complexType` **Subscribe**
- `complexType` **Unsubscribe**
- `complexType` **Restore**
- `complexType` **Synchronize**
- `complexType` **Attach**
- `complexType` **ImageLoad**
- `complexType` **CloneDatabase**
- `complexType` **SetAuthContext**
- `complexType` **DSVTableBinding**
- `complexType` **DimensionPermission**
- `complexType` **ErrorConfiguration**
- `complexType` **DataSource**
- `complexType` **Dimension**
- `complexType` **Hierarchy**
- `complexType` **Server**
- `complexType` **Trace**
- `complexType` **pred_leaf**
- `complexType` **unary_expr**
- `complexType` **boolean_expr**
- `complexType` **Database**
- `complexType` **MiningStructure**
- `complexType` **Cube**
- `complexType` **ReferenceMeasureGroupDimension**
- `complexType` **DegenerateMeasureGroupDimension**
- `complexType` **Partition**
- `simpleType` **Scope**
- `simpleType` **ObjectExpansion**
- `simpleType` **WriteBackTableCreation**
- `simpleType` **DimensionCurrentStorageModeEnumType**
- `simpleType` **UnknownMemberEnumType**
- `simpleType` **retentionModes**
- `simpleType` **partitionModes**
- `simpleType` **CubeStorageModeEnumType**
- `simpleType` **PartitionStorageModeEnumType**
- `simpleType` **PartitionCurrentStorageModeEnumType**
- `element` **ExecuteResponse**
- `element` **EventType**
- `element` **event_session**
- `element` **event**
- `element` **action**
- `element` **target**
- `element` **parameter**
- `element` **Execute**
- `element` **BeginSession**
- `element` **EndSession**
- `element` **Session**
- `attributeGroup` **objectNames**

*Matching: 0, Different: 173, Missing: 232, Extra: 50*

