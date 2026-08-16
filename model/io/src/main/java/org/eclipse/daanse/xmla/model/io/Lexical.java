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

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * The lexical form a value takes on the wire.
 * <p>
 * Three paths lead there - the element writer, the cell list a DMV projects and
 * a JDBC result set - and they must agree, because a malformed
 * {@code xsd:dateTime} is not reported: a client stores the parse failure as the
 * cell's value and fails much later on a cast.
 * <p>
 * Two traps sit in the same spot. {@code LocalDateTime.toString()}
 * <em>omits seconds that are zero</em>, so midnight becomes
 * {@code 2026-08-15T00:00}, and the lexical form requires {@code hh:mm:ss};
 * {@code java.sql.Timestamp.toString()} separates date and time with a space
 * rather than {@code T}. EMF does not help: the generated
 * {@code convertDateTimeToString} hands the value to {@code XMLTypeFactory},
 * which expects a calendar and falls back to {@code toString()}.
 */
public final class Lexical {

    /**
     * XMLA writes timestamps without a zone offset and with whatever sub-second
     * precision the value carries, as in {@code 2024-05-13T12:24:33.956667}: the
     * fixed part always writes seconds, the fraction is optional.
     */
    private static final DateTimeFormatter XMLA_DATE_TIME = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss").appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true).toFormatter();

    private Lexical() {
        // static access only
    }

    /** The lexical form of a timestamp, seconds always written. */
    public static String of(LocalDateTime timestamp) {
        return timestamp.format(XMLA_DATE_TIME);
    }

    /**
     * The lexical form of a modelled value, preferring EMF's own conversion for
     * everything it handles correctly.
     *
     * @param dataType the datatype the model gives the value, never null
     * @param value    the value, may be null
     * @return the lexical form, or null when the value is null
     */
    public static String of(EDataType dataType, Object value) {
        if (value == null) {
            return null;
        }
        String timestamp = timestampOrNull(value);
        return timestamp != null ? timestamp : EcoreUtil.convertToString(dataType, value);
    }

    /**
     * The lexical form of a value whose model type is not known - a JDBC result set
     * column, say, where the driver decides what Java type arrives.
     */
    public static String of(Object value) {
        if (value == null) {
            return null;
        }
        String timestamp = timestampOrNull(value);
        return timestamp != null ? timestamp : value.toString();
    }

    /**
     * The lexical form of the timestamp kinds a driver or a model may hand us, or
     * null when the value is not a point in time at all.
     */
    private static String timestampOrNull(Object value) {
        if (value instanceof LocalDateTime timestamp) {
            return of(timestamp);
        }
        if (value instanceof java.sql.Timestamp timestamp) {
            return of(timestamp.toLocalDateTime());
        }
        if (value instanceof java.time.OffsetDateTime timestamp) {
            // XMLA carries no offset, and the column list a client converts to local time
            // is written in UTC, so that is what an offset is normalised to.
            return of(timestamp.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        }
        if (value instanceof java.time.Instant instant) {
            return of(LocalDateTime.ofInstant(instant, ZoneOffset.UTC));
        }
        return null;
    }
}
