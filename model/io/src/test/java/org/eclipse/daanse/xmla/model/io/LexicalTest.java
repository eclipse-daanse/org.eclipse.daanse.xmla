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

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;

/**
 * A timestamp is written the one way {@code xsd:dateTime} allows, whichever of
 * the three paths it takes to the wire.
 * <p>
 * The lexical form requires {@code hh:mm:ss}. A client handed anything else does
 * not complain: it stores the parse failure as the cell's value, and the error
 * surfaces much later as a cast that cannot succeed.
 */
class LexicalTest {

    /**
     * {@code LocalDateTime.toString()} drops seconds that are zero, and columns
     * such as MDSCHEMA_CUBES.LAST_DATA_UPDATE hold the start of a day.
     */
    @Test
    void midnightKeepsItsSeconds() {
        LocalDateTime midnight = LocalDateTime.of(2026, 8, 15, 0, 0);

        assertThat(midnight).hasToString("2026-08-15T00:00");
        assertThat(Lexical.of(midnight)).isEqualTo("2026-08-15T00:00:00");
    }

    /** The fraction is kept when there is one - it is optional, not truncated. */
    @Test
    void subSecondPrecisionSurvives() {
        assertThat(Lexical.of(LocalDateTime.of(2026, 8, 15, 12, 24, 33, 956_667_000)))
                .isEqualTo("2026-08-15T12:24:33.956667");
    }

    /** A JDBC driver hands back a Timestamp, whose toString uses a space. */
    @Test
    void aSqlTimestampIsNotWrittenWithASpace() {
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(LocalDateTime.of(2026, 8, 15, 0, 0));

        assertThat(timestamp.toString()).contains(" ");
        assertThat(Lexical.of((Object) timestamp)).isEqualTo("2026-08-15T00:00:00");
    }

    /** XMLA carries no offset, so one is normalised to UTC rather than dropped. */
    @Test
    void anOffsetIsNormalisedToUtc() {
        java.time.OffsetDateTime berlin = LocalDateTime.of(2026, 8, 15, 14, 35, 29).atOffset(ZoneOffset.ofHours(2));

        assertThat(Lexical.of((Object) berlin)).isEqualTo("2026-08-15T12:35:29");
    }

    @Test
    void nullStaysNull() {
        assertThat(Lexical.of((Object) null)).isNull();
    }

    /** Anything that is not a point in time is left to the value itself. */
    @Test
    void otherValuesAreUnchanged() {
        assertThat(Lexical.of((Object) 1100)).isEqualTo("1100");
        assertThat(Lexical.of((Object) "FoodMart")).isEqualTo("FoodMart");
    }
}
