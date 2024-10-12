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
package org.eclipse.daanse.xmla.model.record.discover.mdschema.levels;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.CustomRollupSettingEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelOriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelUniqueSettingsEnum;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsResponseRow;

public record MdSchemaLevelsResponseRowR(Optional<String> catalogName, Optional<String> schemaName,
        Optional<String> cubeName, Optional<String> dimensionUniqueName, Optional<String> hierarchyUniqueName,
        Optional<String> levelName, Optional<String> levelUniqueName, Optional<Integer> levelGuid,
        Optional<String> levelCaption, Optional<Integer> levelNumber, Optional<Integer> levelCardinality,
        Optional<LevelTypeEnum> levelType, Optional<String> description,
        Optional<CustomRollupSettingEnum> customRollupSetting, Optional<LevelUniqueSettingsEnum> levelUniqueSettings,
        Optional<Boolean> levelIsVisible, Optional<String> levelOrderingProperty, Optional<LevelDbTypeEnum> levelDbType,
        Optional<String> levelMasterUniqueName, Optional<String> levelNameSqlColumnName,
        Optional<String> levelKeySqlColumnName, Optional<String> levelUniqueNameSqlColumnName,
        Optional<String> levelAttributeHierarchyName, Optional<Integer> levelKeyCardinality,
        Optional<LevelOriginEnum> levelOrigin) implements MdSchemaLevelsResponseRow {

}
