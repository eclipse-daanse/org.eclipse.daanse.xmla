# Rowset answers outside the repository

Two test classes of this module compare the model with the answers Analysis Services 2017 gives to Discover
requests. The answers are not part of this repository. Without them the tests are skipped, and the build runs.

## Where the tests look

The system property `xmla.rowset.answers` names the folder; without it the tests read `../../../xmla-rowset-answers`,
a folder beside the repository's working tree.

```sh
mvn -pl model/io test -Dxmla.rowset.answers=/path/to/the/folder
```

## Files

| File | Holds | Read by |
| --- | --- | --- |
| `DISCOVER_SCHEMA_ROWSETS.rows.xml` | for each rowset its name, GUID, restrictions and restrictions mask | `EveryRowsetOfAnalysisServicesIsInTheModelTest` |
| `DISCOVER_PROPERTIES.rows.xml` | for each request property its name, type, access and whether it is required | `EveryPropertyOfAnalysisServicesIsInTheModelTest` |
| `<REQUEST_TYPE>.xsd` | the inline schema of the answer to that request type: columns and types, no rows | `EveryRowsetOfAnalysisServicesIsInTheModelTest` |

## How such files are made

- `<REQUEST_TYPE>.xsd` is the `xsd:schema` element of a Discover answer, written as a document of its own.
- `<REQUEST_TYPE>.rows.xml` is a `root` element in the namespace `urn:schemas-microsoft-com:xml-analysis:rowset`
  holding the answer's `row` elements with the columns the tests read.
