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

/**
 * Dimensions that are of the type Accounts might have an attribute marked as providing the Account
 * Type (such as Income, Expense, and Balance). Measures can then have an aggregate function of
 * ByAccount. The set of account types defined for a database map the valid account types to the
 * aggregate functions that apply for measures marked with ByAccount.
 */
public interface Account {

    /**
     * @return A string value that represents the name of the account type. The following list of known
     *         string values can be extended: "Income" - Represents an Income account type. "Expense" -
     *         Represents an Expense account type. "Flow" - Represents a Flow account type. "Balance" -
     *         Represents a Balance account type. "Asset" - Represents an Asset account type.
     *         "Liability" - Represents a Liability account type. "Statistical" - Represents a
     *         Statistical account type.
     */
    String accountType();

    /**
     * @return The aggregation function to use for the Account Type. Each enumeration value is the name
     *         of the aggregation function that would be set by choosing that value. The possible values
     *         for the enumeration are as follows: Sum - Calculates the sum of values for all child
     *         members. Count - Retrieves the count of all child members. Min - Retrieves the lowest
     *         value for all child members. Max - Retrieves the highest value for all child members.
     *         DistinctCount - Retrieves the count of all unique child members. None – No aggregation is
     *         performed. All values for leaf and nonleaf members in a dimension are supplied directly
     *         from the fact table for the measure group that contains the measure. If no value can be
     *         read from the fact table for a member, the value for that member is set to null.
     *         AverageOfChildren - Calculates the average of values for all nonempty child members.
     *         FirstChild - Retrieves the value of the first child member. LastChild - Retrieves the
     *         value of the last child member. FirstNonEmpty - Retrieves the value of the first nonempty
     *         child member. LastNonEmpty - Retrieves the value of the last nonempty child member.
     */
    String aggregationFunction();

    /**
     * @return A collection of strings, each of which will be treated as an alias for the given Account
     *         Type.
     */
    List<String> aliases();

    /**
     * @return A collection of Annotation objects.
     */
    List<Annotation> annotations();

}
