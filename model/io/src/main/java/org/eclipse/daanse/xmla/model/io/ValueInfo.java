/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation.
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

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Takes a DataType String (null, Integer, Numeric or non-null) and Value Object
 * (Integer, Double, String, other) and canonicalizes them to XSD data type and
 * corresponding object.
 * <p>
 * If the input DataType is Integer, then it attempts to return an XSD_INT with
 * value java.lang.Integer (and failing that an XSD_LONG (java.lang.Long) or
 * XSD_INTEGER (java.math.BigInteger)). Worst case is the value loses precision
 * with any integral representation and must be returned as a decimal type
 * (Double or java.math.BigDecimal).
 * <p>
 * If the input DataType is Decimal, then it attempts to return an XSD_DOUBLE
 * with value java.lang.Double (and failing that an XSD_DECIMAL
 * (java.math.BigDecimal)).
 */
public final class ValueInfo {

    // The XSD type tokens tabular answers carry.
    public static final String XSD_STRING = "xsd:string";
    public static final String XSD_BOOLEAN = "xsd:boolean";
    public static final String XSD_INTEGER = "xsd:int";
    public static final String XSD_INTEGER_LONG = "xsd:integer";
    public static final String XSD_DOUBLE = "xsd:double";
    public static final String XSD_SHORT = "xsd:short";
    public static final String XSD_LONG = "xsd:long";
    public static final String XSD_FLOAT = "xsd:float";
    public static final String XSD_DECIMAL = "xsd:decimal";
    public static final String XSD_BYTE = "xsd:byte";

    public static final int XSD_INT_MAX_INCLUSIVE = 2147483647;
    public static final int XSD_INT_MIN_INCLUSIVE = -2147483648;

    /**
     * Returns XSD_INT, XSD_DOUBLE, XSD_STRING or null.
     *
     * @param dataType null, Integer, Numeric or non-null.
     * @return Returns the suggested XSD type for a given datatype
     */
    public static String getValueTypeHint(final String dataType) {
        if (dataType != null) {
            return (dataType.equals("Integer")) ? XSD_INTEGER
                    : ((dataType.equals("Numeric")) ? XSD_DOUBLE : XSD_STRING);
        } else {
            return null;
        }
    }

    public String valueType;
    public Object value;
    public boolean isDecimal;

    public ValueInfo(final String dataType, final Object inputValue) {
        final String valueTypeHint = getValueTypeHint(dataType);

        if (valueTypeHint != null) {

            if (valueTypeHint.equals(XSD_STRING)) {
                this.valueType = valueTypeHint;
                this.value = inputValue;
                this.isDecimal = false;

            } else if (valueTypeHint.equals(XSD_INTEGER)) {

                if (inputValue instanceof Integer) {
                    this.valueType = valueTypeHint;
                    this.value = inputValue;
                    this.isDecimal = false;

                } else if (inputValue instanceof Byte) {
                    this.valueType = XSD_BYTE;
                    this.value = inputValue;
                    this.isDecimal = false;

                } else if (inputValue instanceof Short) {
                    this.valueType = XSD_SHORT;
                    this.value = inputValue;
                    this.isDecimal = false;

                } else if (inputValue instanceof Long lval) {
                    setValueAndType(lval);

                } else if (inputValue instanceof BigInteger bi) {
                    long lval = bi.longValue();
                    if (bi.equals(BigInteger.valueOf(lval))) {
                        setValueAndType(lval);
                    } else {
                        this.valueType = XSD_INTEGER_LONG;
                        this.value = inputValue;
                        this.isDecimal = false;
                    }

                } else if (inputValue instanceof Float f) {
                    long lval = f.longValue();
                    if (f.equals(Float.valueOf(lval))) {
                        setValueAndType(lval);

                    } else {
                        this.valueType = XSD_FLOAT;
                        this.value = inputValue;
                        this.isDecimal = true;
                    }

                } else if (inputValue instanceof Double d) {
                    long lval = d.longValue();
                    if (d.equals(Double.valueOf(lval))) {
                        setValueAndType(lval);

                    } else {
                        this.valueType = XSD_DOUBLE;
                        this.value = inputValue;
                        this.isDecimal = true;
                    }

                } else if (inputValue instanceof BigDecimal bd) {
                    try {
                        long lval = bd.longValue();

                        setValueAndType(lval);
                    } catch (ArithmeticException ex) {

                        try {
                            BigInteger bi = bd.toBigIntegerExact();
                            this.valueType = XSD_INTEGER_LONG;
                            this.value = bi;
                            this.isDecimal = false;
                        } catch (ArithmeticException ex1) {
                            this.valueType = XSD_DECIMAL;
                            this.value = inputValue;
                            this.isDecimal = true;
                        }
                    }

                } else if (inputValue instanceof Number n) {
                    // An unknown Number subtype; narrowing it may lose precision.
                    this.value = n.longValue();
                    this.valueType = valueTypeHint;
                    this.isDecimal = false;

                } else {
                    this.valueType = valueTypeHint;
                    this.value = inputValue;
                    this.isDecimal = false;
                }

            } else if (valueTypeHint.equals(XSD_DOUBLE)) {

                if (inputValue instanceof Double) {
                    this.valueType = valueTypeHint;
                    this.value = inputValue;
                    this.isDecimal = true;

                } else if (inputValue instanceof Byte || inputValue instanceof Short || inputValue instanceof Integer
                        || inputValue instanceof Long) {
                    this.value = ((Number) inputValue).doubleValue();
                    this.valueType = valueTypeHint;
                    this.isDecimal = true;

                } else if (inputValue instanceof Float) {
                    this.value = inputValue;
                    this.valueType = XSD_FLOAT;
                    this.isDecimal = true;

                } else if (inputValue instanceof BigDecimal bd) {
                    double dval = bd.doubleValue();
                    // make with same scale as Double
                    try {
                        BigDecimal bd2 = BigDecimal.valueOf(dval);
                        // Must use compareTo - see BigDecimal.equals
                        if (bd.compareTo(bd2) == 0) {
                            this.valueType = XSD_DOUBLE;
                            this.value = dval;
                        } else {
                            this.valueType = XSD_DECIMAL;
                            this.value = inputValue;
                        }
                    } catch (NumberFormatException ex) {
                        this.valueType = XSD_DECIMAL;
                        this.value = inputValue;
                    }
                    this.isDecimal = true;

                } else if (inputValue instanceof BigInteger bi) {
                    long lval = bi.longValue();
                    if (bi.equals(BigInteger.valueOf(lval))) {
                        setValueAndType(lval);
                    } else {
                        this.valueType = XSD_INTEGER_LONG;
                        this.value = inputValue;
                        this.isDecimal = true;
                    }

                } else if (inputValue instanceof Number n) {
                    // An unknown Number subtype; narrowing it may lose precision.
                    this.value = n.doubleValue();
                    this.valueType = valueTypeHint;
                    this.isDecimal = true;

                } else {
                    this.valueType = valueTypeHint;
                    this.value = inputValue;
                    this.isDecimal = true;
                }
            }
        } else {
            // There is no valueType "hint", so just get it from the value.
            if (inputValue instanceof String) {
                this.valueType = XSD_STRING;
                this.value = inputValue;
                this.isDecimal = false;

            } else if (inputValue instanceof Integer) {
                this.valueType = XSD_INTEGER;
                this.value = inputValue;
                this.isDecimal = false;

            } else if (inputValue instanceof Byte b) {
                this.valueType = XSD_BYTE;
                this.value = b.intValue();
                this.isDecimal = false;

            } else if (inputValue instanceof Short s) {
                this.valueType = XSD_SHORT;
                this.value = s.intValue();
                this.isDecimal = false;

            } else if (inputValue instanceof Long lval) {
                setValueAndType(lval);

            } else if (inputValue instanceof BigInteger bi) {
                long lval = bi.longValue();
                if (bi.equals(BigInteger.valueOf(lval))) {
                    setValueAndType(lval);
                } else {
                    this.valueType = XSD_INTEGER_LONG;
                    this.value = inputValue;
                    this.isDecimal = false;
                }

            } else if (inputValue instanceof Float) {
                this.valueType = XSD_FLOAT;
                this.value = inputValue;
                this.isDecimal = true;

            } else if (inputValue instanceof Double) {
                this.valueType = XSD_DOUBLE;
                this.value = inputValue;
                this.isDecimal = true;

            } else if (inputValue instanceof BigDecimal bd) {
                double dval = bd.doubleValue();
                // make with same scale as Double
                try {
                    BigDecimal bd2 = BigDecimal.valueOf(dval);
                    // Must use compareTo - see BigDecimal.equals
                    if (bd.compareTo(bd2) == 0) {
                        this.valueType = XSD_DOUBLE;
                        this.value = dval;
                    } else {
                        this.valueType = XSD_DECIMAL;
                        this.value = inputValue;
                    }
                } catch (NumberFormatException ex) {
                    this.valueType = XSD_DECIMAL;
                    this.value = inputValue;
                }
                this.isDecimal = true;

            } else if (inputValue instanceof Number n) {
                // An unknown Number subtype; narrowing it may lose precision.
                this.value = n.longValue();
                this.valueType = XSD_LONG;
                this.isDecimal = false;

            } else if (inputValue instanceof Boolean) {
                this.value = inputValue;
                this.valueType = XSD_BOOLEAN;
                this.isDecimal = false;
            } else {
                this.valueType = XSD_STRING;
                this.value = inputValue;
                this.isDecimal = false;
            }
        }
    }

    private void setValueAndType(long lval) {
        if (!isValidXsdInt(lval)) {
            this.valueType = XSD_LONG;
            this.value = lval;
        } else {
            this.valueType = XSD_INTEGER;
            this.value = (int) lval;
        }
        this.isDecimal = false;
    }

    private boolean isValidXsdInt(long l) {
        return (l <= XSD_INT_MAX_INCLUSIVE) && (l >= XSD_INT_MIN_INCLUSIVE);
    }

}
