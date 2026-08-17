/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.model.io;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.daanse.dmv.model.api.DmvPredicates;
import org.eclipse.daanse.dmv.model.api.DmvStatement;
import org.eclipse.daanse.dmv.model.api.OrderByItem;
import org.eclipse.daanse.xmla.model.xmla.Parameter;
import org.eclipse.daanse.xmla.model.xmla.RowsetCell;
import org.eclipse.daanse.xmla.model.xmla.RowsetColumn;
import org.eclipse.daanse.xmla.model.xmla.RowsetResult;
import org.eclipse.daanse.xmla.model.xmla.RowsetRow;
import org.eclipse.daanse.xmla.model.xmla.XmlaFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Tabular answers, rendered on the wire's side of the fence.
 * <p>
 * A DMV serves the typed rows of a discover implementation with the SELECT
 * applied - projection, the shared predicate evaluation, ORDER BY, DISTINCT,
 * TOP; a drill-through or SQL statement serves its JDBC result set. Neither
 * knows anything about the backend that produced the rows, which is why both
 * renderings live here, next to the schema writer and the element-name rules
 * they use.
 */
public final class RowsetResults {

    private static final XmlaFactory FACTORY = XmlaFactory.eINSTANCE;

    private RowsetResults() {
        // static access only
    }

    public static RowsetResult fromRows(EClass rowClass, List<EObject> rows, DmvStatement statement,
            List<Parameter> parameters, boolean schemaIncluded) {
        RowsetResult result = FACTORY.createRowsetResult();
        result.setSchemaIncluded(schemaIncluded);

        List<String> projection = statement.columns();
        List<EStructuralFeature> features = new ArrayList<>();
        for (EStructuralFeature feature : rowClass.getEAllStructuralFeatures()) {
            String wireName = RowsetSchemaWriter.wireNameOf(feature);
            if (!projection.isEmpty() && !projection.contains(wireName)) {
                continue;
            }
            features.add(feature);
            RowsetColumn column = FACTORY.createRowsetColumn();
            column.setField(wireName);
            column.setName(ElementNames.encode(wireName));
            column.setXsdType(RowsetSchemaWriter.xsdTypeOf(feature));
            result.getColumns().add(column);
        }

        List<EObject> matched = new ArrayList<>();
        for (EObject row : rows) {
            if (DmvPredicates.matches(statement.where().orElse(null), column -> columnValue(row, column),
                    name -> parameterValue(parameters, name))) {
                matched.add(row);
            }
        }
        if (!statement.orderBy().isEmpty()) {
            matched.sort(orderComparator(statement.orderBy()));
        }

        Set<String> seen = statement.distinct() ? new LinkedHashSet<>() : null;
        int limit = statement.top().orElse(Integer.MAX_VALUE);
        for (EObject row : matched) {
            if (result.getRows().size() >= limit) {
                break;
            }
            RowsetRow rendered = FACTORY.createRowsetRow();
            for (EStructuralFeature feature : features) {
                if (!row.eIsSet(feature)) {
                    continue;
                }
                String name = ElementNames.encode(RowsetSchemaWriter.wireNameOf(feature));
                if (feature.isMany()) {
                    for (Object value : (List<?>) row.eGet(feature)) {
                        rendered.getCells().add(cellOf(name, feature, value));
                    }
                } else {
                    rendered.getCells().add(cellOf(name, feature, row.eGet(feature)));
                }
            }
            if (seen != null && !seen.add(signatureOf(rendered))) {
                continue;
            }
            result.getRows().add(rendered);
        }
        return result;
    }

    /**
     * The rendered cells as one comparable string - what DISTINCT deduplicates on.
     */
    private static String signatureOf(RowsetRow row) {
        StringBuilder signature = new StringBuilder();
        for (RowsetCell cell : row.getCells()) {
            signature.append(cell.getName()).append('=').append(cell.getValue()).append('\u0000');
        }
        return signature.toString();
    }

    /**
     * ORDER BY, the way the WHERE comparison orders: numeric when both values are
     * numbers, lexicographic otherwise; an absent value sorts first; DESC inverts.
     */
    private static java.util.Comparator<EObject> orderComparator(List<? extends OrderByItem> orderBy) {
        return (left, right) -> {
            for (OrderByItem item : orderBy) {
                String a = columnValue(left, item.column());
                String b = columnValue(right, item.column());
                int outcome = compareValues(a, b);
                if (outcome != 0) {
                    return item.descending() ? -outcome : outcome;
                }
            }
            return 0;
        };
    }

    private static int compareValues(String left, String right) {
        if (left == null && right == null) {
            return 0;
        }
        if (left == null) {
            return -1;
        }
        if (right == null) {
            return 1;
        }
        try {
            return new java.math.BigDecimal(left).compareTo(new java.math.BigDecimal(right));
        } catch (NumberFormatException notNumeric) {
            return left.compareTo(right);
        }
    }

    private static String columnValue(EObject row, String columnName) {
        for (EStructuralFeature feature : row.eClass().getEAllStructuralFeatures()) {
            if (columnName.equals(RowsetSchemaWriter.wireNameOf(feature)) && row.eIsSet(feature) && !feature.isMany()
                    && feature.getEType() instanceof EDataType dataType) {
                return Lexical.of(dataType, row.eGet(feature));
            }
        }
        return null;
    }

    private static String parameterValue(List<Parameter> parameters, String name) {
        for (Parameter parameter : parameters) {
            if (name.equals(parameter.getName())) {
                return parameter.getValue();
            }
        }
        return null;
    }

    private static RowsetCell cellOf(String name, EStructuralFeature feature, Object value) {
        RowsetCell cell = FACTORY.createRowsetCell();
        cell.setName(name);
        if (feature instanceof EReference && value instanceof EObject nested) {
            // A nested rowset (DISCOVER_SCHEMA_ROWSETS' Restrictions): the nested row's
            // cells go directly inside the column element.
            for (EStructuralFeature child : nested.eClass().getEAllStructuralFeatures()) {
                if (!nested.eIsSet(child)) {
                    continue;
                }
                String childName = ElementNames.encode(RowsetSchemaWriter.wireNameOf(child));
                if (child.isMany()) {
                    for (Object childValue : (List<?>) nested.eGet(child)) {
                        cell.getCells().add(cellOf(childName, child, childValue));
                    }
                } else {
                    cell.getCells().add(cellOf(childName, child, nested.eGet(child)));
                }
            }
        } else if (value != null) {
            if (feature.getEType() instanceof EDataType dataType) {
                cell.setValue(Lexical.of(dataType, value));
            } else {
                cell.setValue(Lexical.of(value));
            }
        }
        return cell;
    }

    // --- JDBC: a drill-through or SQL result set
    // ----------------------------------------

    public static RowsetResult fromResultSet(ResultSet resultSet, int totalCount, boolean schemaIncluded)
            throws SQLException {
        RowsetResult result = FACTORY.createRowsetResult();
        result.setSchemaIncluded(schemaIncluded);

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String> encodedNames = new ArrayList<>();
        for (int index = 1; index <= columnCount; index++) {
            String label = metaData.getColumnLabel(index);
            String encoded = ElementNames.encode(label);
            encodedNames.add(encoded);
            RowsetColumn column = FACTORY.createRowsetColumn();
            column.setField(label);
            column.setName(encoded);
            column.setXsdType(sqlToXsdType(metaData.getColumnType(index), metaData.getPrecision(index),
                    metaData.getScale(index)));
            result.getColumns().add(column);
        }

        // The bridge's row-count convention: when the engine counted the total, the
        // first row repeats that count in every column.
        if (totalCount >= 0) {
            RowsetRow countRow = FACTORY.createRowsetRow();
            String count = Integer.toString(totalCount);
            for (String encoded : encodedNames) {
                RowsetCell cell = FACTORY.createRowsetCell();
                cell.setName(encoded);
                cell.setValue(count);
                countRow.getCells().add(cell);
            }
            result.getRows().add(countRow);
        }

        while (resultSet.next()) {
            RowsetRow row = FACTORY.createRowsetRow();
            for (int index = 1; index <= columnCount; index++) {
                Object value = resultSet.getObject(index);
                if (value == null) {
                    continue;
                }
                String text = Lexical.of(value);
                if (value instanceof Number) {
                    text = ElementNames.normalizeNumericString(text);
                }
                RowsetCell cell = FACTORY.createRowsetCell();
                cell.setName(encodedNames.get(index - 1));
                cell.setValue(text);
                row.getCells().add(cell);
            }
            result.getRows().add(row);
        }
        return result;
    }

    private static String sqlToXsdType(int sqlType, int precision, int scale) {
        switch (sqlType) {
        case Types.INTEGER, Types.SMALLINT, Types.TINYINT:
            return ValueInfo.XSD_INTEGER;
        case Types.NUMERIC, Types.DECIMAL:
            // scale 0 means integer only with a known precision: PostgreSQL reports an
            // unconstrained numeric as precision 0 / scale 0 though its values carry decimals.
            if (scale != 0 || precision == 0) {
                return ValueInfo.XSD_DECIMAL;
            }
            // xsd:int is 32-bit; wider integers get the unbounded type, as BIGINT does
            return precision <= 9 ? ValueInfo.XSD_INTEGER : ValueInfo.XSD_INTEGER_LONG;
        case Types.BIGINT:
            return ValueInfo.XSD_INTEGER_LONG;
        case Types.DOUBLE, Types.FLOAT, Types.REAL:
            return ValueInfo.XSD_DOUBLE;
        default:
            return ValueInfo.XSD_STRING;
        }
    }

}
