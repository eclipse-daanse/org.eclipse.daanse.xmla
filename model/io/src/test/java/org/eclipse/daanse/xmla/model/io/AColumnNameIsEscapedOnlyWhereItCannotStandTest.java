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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * A column of a tabular answer is an XML element, and its name is escaped where a character cannot stand: the first
 * character must start a name, the others may also be digits, dots and hyphens. The first two rows are names of the
 * kind an answer carries, a measure and a column of a table whose name starts with a dollar sign; escapes are written
 * with upper-case digits, the colon is escaped because an element name is a local name, an underscore only when an
 * escape follows it.
 */
class AColumnNameIsEscapedOnlyWhereItCannotStandTest {

    @ParameterizedTest(name = "{0}")
    @CsvSource(delimiter = '|', value = {
            "[Measures].[N]|_x005B_Measures_x005D_._x005B_N_x005D_",
            "[$Shipment Details].[Delivery Number]"
                    + "|_x005B__x0024_Shipment_x0020_Details_x005D_._x005B_Delivery_x0020_Number_x005D_",
            "Measure1|Measure1",
            "store_id|store_id",
            "store-name.v2|store-name.v2",
            "1st|_x0031_st",
            "-x|_x002D_x",
            ".x|_x002E_x",
            "a:b|a_x003A_b",
            "_x0041_|_x005F_x0041_",
            "my_x|my_x",
            "Größe|Größe",
            "￾|_xFFFE_" })
    void aCharacterIsEscapedOnlyWhereItCannotStand(String name, String onTheWire) {
        assertThat(ElementNames.encode(name)).isEqualTo(onTheWire);
    }

    @org.junit.jupiter.api.Test
    void aCharacterBeyondTheBasicPlaneIsKeptOrEscapedWhole() {
        String doubleStruck = new String(Character.toChars(0x1D538));
        assertThat(ElementNames.encode(doubleStruck + "1")).as("a letter outside the basic plane starts a name")
                .isEqualTo(doubleStruck + "1");
        assertThat(ElementNames.encode(new String(Character.toChars(0xF0000)))).isEqualTo("_x000F0000_");
    }
}
