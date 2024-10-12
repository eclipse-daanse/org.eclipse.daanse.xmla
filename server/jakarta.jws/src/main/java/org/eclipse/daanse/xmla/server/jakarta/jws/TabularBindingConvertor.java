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
package org.eclipse.daanse.xmla.server.jakarta.jws;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.TabularBinding;
import org.eclipse.daanse.xmla.model.record.xmla.DSVTableBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.QueryBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.TableBindingR;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DSVTableBinding;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.QueryBinding;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.TableBinding;

public class TabularBindingConvertor {

    private TabularBindingConvertor() {
    }

    public static TabularBinding convertTabularBinding(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.TabularBinding source) {
        if (source != null) {
            if (source instanceof TableBinding tableBinding) {
                return new TableBindingR(Optional.ofNullable(tableBinding.getDataSourceID()),
                    tableBinding.getDbTableName(),
                    Optional.ofNullable(tableBinding.getDbSchemaName()));
            }
            if (source instanceof QueryBinding queryBinding) {
                return new QueryBindingR(Optional.ofNullable(queryBinding.getDataSourceID()),
                    queryBinding.getQueryDefinition());

            }
            if (source instanceof DSVTableBinding dsvTableBinding) {
                return new DSVTableBindingR(Optional.ofNullable(dsvTableBinding.getDataSourceViewID()),
                    dsvTableBinding.getTableID(),
                    Optional.ofNullable(dsvTableBinding.getDataEmbeddingStyle()));
            }
        }
        return null;
    }

}
