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
package org.eclipse.daanse.xmla.api.xmla;

import java.util.List;

public interface OutOfLineBinding {

    String databaseID();

    String dimensionID();

    String cubeID();

    String measureGroupID();

    String partitionID();

    String miningModelID();

    String miningStructureID();

    String attributeID();

    String cubeDimensionID();

    String measureID();

    String parentColumnID();

    String columnID();

    Binding source();

    Binding nameColumn();

    Binding skippedLevelsColumn();

    Binding customRollupColumn();

    Binding customRollupPropertiesColumn();

    Binding valueColumn();

    Binding unaryOperatorColumn();

    List<Binding> keyColumns();

    List<Binding> foreignKeyColumns();

    OutOfLineBinding.Translations translations();

    interface Translations {

        List<OutOfLineBinding.Translations.Translation> translation();

        public interface Translation {

            int language();

            Binding source();
        }
    }

}
