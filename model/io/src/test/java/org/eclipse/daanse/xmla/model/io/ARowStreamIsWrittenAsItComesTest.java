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
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.model.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * A stream of rows is written as the rows come, in the form Analysis Services answers a trace's Subscribe with: the
 * envelope and the inline schema at once -- each column with its client name as {@code sql:field} and a short element
 * name -- then each row, each followed by 4095 spaces and a flush through to the client, and the closing elements when
 * the stream ends.
 */
class ARowStreamIsWrittenAsItComesTest {

    private static final List<RowsetSchemaWriter.Column> COLUMNS = List.of(
            new RowsetSchemaWriter.Column("EventClass", "a", "xsd:int"),
            new RowsetSchemaWriter.Column("Duration", "b", "xsd:long"),
            new RowsetSchemaWriter.Column("TextData", "c", "xsd:string"));

    /** What reached the target at each flush. */
    private static final class Flushes extends FilterOutputStream {

        final List<String> seen = new CopyOnWriteArrayList<>();
        private final ByteArrayOutputStream bytes;

        Flushes(ByteArrayOutputStream bytes) {
            super(bytes);
            this.bytes = bytes;
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            bytes.write(b, off, len);
        }

        @Override
        public void flush() throws IOException {
            super.flush();
            seen.add(bytes.toString(StandardCharsets.UTF_8));
        }
    }

    /** Rows handed over one at a time; an empty list ends the stream. */
    private static Iterator<List<Object>> from(SynchronousQueue<List<Object>> handover) {
        return new Iterator<>() {
            private List<Object> next;

            @Override
            public boolean hasNext() {
                if (next == null) {
                    try {
                        next = handover.take();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return false;
                    }
                }
                return !next.isEmpty();
            }

            @Override
            public List<Object> next() {
                List<Object> row = next;
                next = null;
                return row;
            }
        };
    }

    @Test
    void theSchemaGoesOutAtOnceAndEachRowWhenItComes() throws Exception {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        Flushes target = new Flushes(bytes);
        SynchronousQueue<List<Object>> handover = new SynchronousQueue<>();
        Thread writer = new Thread(() -> {
            try {
                XmlaMessageCodec.writeExecuteRowStream(target, List.of(), COLUMNS, from(handover));
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        });
        writer.start();

        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(10);
        while (target.seen.isEmpty() && System.nanoTime() < deadline) {
            Thread.onSpinWait();
        }
        Thread.sleep(200);
        assertThat(target.seen.getLast()).as("the schema is out before any row")
                .contains("sql:field=\"EventClass\" name=\"a\"").doesNotContain("<row>");

        handover.put(List.of(10, 6L, "SELECT {[Measures].DefaultMember} ON 0 FROM [Sales]"));
        while (!target.seen.getLast().contains("</row>") && System.nanoTime() < deadline) {
            Thread.onSpinWait();
        }
        assertThat(target.seen.getLast()).as("the row is out before the next one comes")
                .contains("<row><a>10</a><b>6</b><c>SELECT {[Measures].DefaultMember} ON 0 FROM [Sales]</c></row>");

        java.util.ArrayList<Object> withoutDuration = new java.util.ArrayList<>(java.util.Arrays.asList(10, null, "x"));
        handover.put(withoutDuration);
        handover.put(List.of());
        writer.join(TimeUnit.SECONDS.toMillis(10));

        String answer = bytes.toString(StandardCharsets.UTF_8);
        assertThat(answer).contains("</row>" + " ".repeat(4095) + "<row>");
        assertThat(answer).contains("<row><a>10</a><c>x</c></row>");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document document = factory.newDocumentBuilder()
                .parse(new ByteArrayInputStream(answer.getBytes(StandardCharsets.UTF_8)));
        assertThat(document.getElementsByTagNameNS(XmlaNamespaces.ROWSET, "row").getLength()).isEqualTo(2);
        Element column = (Element) document.getElementsByTagNameNS(XmlaNamespaces.XSD, "element").item(2);
        assertThat(column.getAttributeNS(XmlaNamespaces.SQL, "field")).isEqualTo("EventClass");
        assertThat(column.getAttribute("type")).isEqualTo("xsd:int");
    }
}
