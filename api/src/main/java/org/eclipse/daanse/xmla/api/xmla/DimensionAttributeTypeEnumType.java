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

public enum DimensionAttributeTypeEnumType {

    ACCOUNT("Account"), ACCOUNT_NAME("AccountName"), ACCOUNT_NUMBER("AccountNumber"), ACCOUNT_TYPE("AccountType"),
    ADDRESS("Address"), ADDRESS_BUILDING("AddressBuilding"), ADDRESS_CITY("AddressCity"),
    ADDRESS_COUNTRY("AddressCountry"), ADDRESS_FAX("AddressFax"), ADDRESS_FLOOR("AddressFloor"),
    ADDRESS_HOUSE("AddressHouse"), ADDRESS_PHONE("AddressPhone"), ADDRESS_QUARTER("AddressQuarter"),
    ADDRESS_ROOM("AddressRoom"), ADDRESS_STATE_OR_PROVINCE("AddressStateOrProvince"), ADDRESS_STREET("AddressStreet"),
    ADDRESS_ZIP("AddressZip"), BOM_RESOURCE("BomResource"), CAPTION("Caption"),
    CAPTION_ABBREVIATION("CaptionAbbreviation"), CAPTION_DESCRIPTION("CaptionDescription"), CHANNEL("Channel"),
    CITY("City"), COMPANY("Company"), CONTINENT("Continent"), COUNTRY("Country"), COUNTY("County"),
    CURRENCY_DESTINATION("CurrencyDestination"), CURRENCY_ISO_CODE("CurrencyIsoCode"), CURRENCY_NAME("CurrencyName"),
    CURRENCY_SOURCE("CurrencySource"), CUSTOMER_GROUP("CustomerGroup"), CUSTOMER_HOUSEHOLD("CustomerHousehold"),
    CUSTOMERS("Customers"), DATE("Date"), DATE_CANCELED("DateCanceled"), DATE_DURATION("DateDuration"),
    DATE_ENDED("DateEnded"), DATE_MODIFIED("DateModified"), DATE_START("DateStart"), DAY_OF_HALF_YEAR("DayOfHalfYear"),
    DAY_OF_MONTH("DayOfMonth"), DAY_OF_QUARTER("DayOfQuarter"), DAY_OF_TEN_DAYS("DayOfTenDays"),
    DAY_OF_TRIMESTER("DayOfTrimester"), DAY_OF_WEEK("DayOfWeek"), DAY_OF_YEAR("DayOfYear"), DAYS("Days"),
    DELETED_FLAG("DeletedFlag"), EXTENDED_TYPE("ExtendedType"), FISCAL_DATE("FiscalDate"),
    FISCAL_DAY_OF_HALF_YEAR("FiscalDayOfHalfYear"), FISCAL_DAY_OF_MONTH("FiscalDayOfMonth"),
    FISCAL_DAY_OF_QUARTER("FiscalDayOfQuarter"), FISCAL_DAY_OF_TRIMESTER("FiscalDayOfTrimester"),
    FISCAL_DAY_OF_WEEK("FiscalDayOfWeek"), FISCAL_DAY_OF_YEAR("FiscalDayOfYear"), FISCAL_HALF_YEARS("FiscalHalfYears"),
    FISCAL_HALF_YEAR_OF_YEAR("FiscalHalfYearOfYear"), FISCAL_MONTHS("FiscalMonths"),
    FISCAL_MONTH_OF_HALF_YEAR("FiscalMonthOfHalfYear"), FISCAL_MONTH_OF_QUARTER("FiscalMonthOfQuarter"),
    FISCAL_MONTH_OF_TRIMESTER("FiscalMonthOfTrimester"), FISCAL_MONTH_OF_YEAR("FiscalMonthOfYear"),
    FISCAL_QUARTERS("FiscalQuarters"), FISCAL_QUARTER_OF_HALF_YEAR("FiscalQuarterOfHalfYear"),
    FISCAL_QUARTER_OF_YEAR("FiscalQuarterOfYear"), FISCAL_TRIMESTERS("FiscalTrimesters"),
    FISCAL_TRIMESTER_OF_YEAR("FiscalTrimesterOfYear"), FISCAL_WEEKS("FiscalWeeks"),
    FISCAL_WEEK_OF_HALF_YEAR("FiscalWeekOfHalfYear"), FISCAL_WEEK_OF_MONTH("FiscalWeekOfMonth"),
    FISCAL_WEEK_OF_QUARTER("FiscalWeekOfQuarter"), FISCAL_WEEK_OF_TRIMESTER("FiscalWeekOfTrimester"),
    FISCAL_WEEK_OF_YEAR("FiscalWeekOfYear"), FISCAL_YEARS("FiscalYears"), FORMATTING_COLOR("FormattingColor"),
    FORMATTING_FONT("FormattingFont"), FORMATTING_FONT_EFFECTS("FormattingFontEffects"),
    FORMATTING_FONT_SIZE("FormattingFontSize"), FORMATTING_ORDER("FormattingOrder"),
    FORMATTING_SUBTOTAL("FormattingSubtotal"), GEO_BOUNDARY_BOTTOM("GeoBoundaryBottom"),
    GEO_BOUNDARY_FRONT("GeoBoundaryFront"), GEO_BOUNDARY_LEFT("GeoBoundaryLeft"),
    GEO_BOUNDARY_POLYGON("GeoBoundaryPolygon"), GEO_BOUNDARY_REAR("GeoBoundaryRear"),
    GEO_BOUNDARY_RIGHT("GeoBoundaryRight"), GEO_BOUNDARY_TOP("GeoBoundaryTop"), GEO_CENTROID_X("GeoCentroidX"),
    GEO_CENTROID_Y("GeoCentroidY"), GEO_CENTROID_Z("GeoCentroidZ"), HALF_YEARS("HalfYears"),
    HALF_YEAR_OF_YEAR("HalfYearOfYear"), HOURS("Hours"), ID("ID"), IMAGE("Image"), IMAGE_BMP("ImageBmp"),
    IMAGE_GIF("ImageGif"), IMAGE_JPG("ImageJpg"), IMAGE_PNG("ImagePng"), IMAGE_TIFF("ImageTiff"), IMAGE_URL("ImageUrl"),
    IS_HOLIDAY("IsHoliday"), ISO_8601_DATE("Iso8601Date"), ISO_8601_DAY_OF_WEEK("Iso8601DayOfWeek"),
    ISO_8601_DAY_OF_YEAR("Iso8601DayOfYear"), ISO_8601_WEEKS("Iso8601Weeks"),
    ISO_8601_WEEK_OF_YEAR("Iso8601WeekOfYear"), ISO_8601_YEARS("Iso8601Years"), IS_PEAK_DAY("IsPeakDay"),
    IS_WEEK_DAY("IsWeekDay"), IS_WORKING_DAY("IsWorkingDay"), MANUFACTURING_DATE("ManufacturingDate"),
    MANUFACTURING_DAY_OF_HALF_YEAR("ManufacturingDayOfHalfYear"), MANUFACTURING_DAY_OF_MONTH("ManufacturingDayOfMonth"),
    MANUFACTURING_DAY_OF_QUARTER("ManufacturingDayOfQuarter"), MANUFACTURING_DAY_OF_WEEK("ManufacturingDayOfWeek"),
    MANUFACTURING_DAY_OF_YEAR("ManufacturingDayOfYear"), MANUFACTURING_HALF_YEARS("ManufacturingHalfYears"),
    MANUFACTURING_HALF_YEAR_OF_YEAR("ManufacturingHalfYearOfYear"), MANUFACTURING_MONTHS("ManufacturingMonths"),
    MANUFACTURING_MONTH_OF_HALF_YEAR("ManufacturingMonthOfHalfYear"),
    MANUFACTURING_MONTH_OF_QUARTER("ManufacturingMonthOfQuarter"),
    MANUFACTURING_MONTH_OF_YEAR("ManufacturingMonthOfYear"), MANUFACTURING_QUARTERS("ManufacturingQuarters"),
    MANUFACTURING_QUARTER_OF_HALF_YEAR("ManufacturingQuarterOfHalfYear"),
    MANUFACTURING_QUARTER_OF_YEAR("ManufacturingQuarterOfYear"), MANUFACTURING_WEEKS("ManufacturingWeeks"),
    MANUFACTURING_WEEK_OF_HALF_YEAR("ManufacturingWeekOfHalfYear"),
    MANUFACTURING_WEEK_OF_MONTH("ManufacturingWeekOfMonth"),
    MANUFACTURING_WEEK_OF_QUARTER("ManufacturingWeekOfQuarter"), MANUFACTURING_WEEK_OF_YEAR("ManufacturingWeekOfYear"),
    MANUFACTURING_YEARS("ManufacturingYears"), MINUTES("Minutes"), MONTHS("Months"),
    MONTH_OF_HALF_YEAR("MonthOfHalfYear"), MONTH_OF_QUARTER("MonthOfQuarter"), MONTH_OF_TRIMESTER("MonthOfTrimester"),
    MONTH_OF_YEAR("MonthOfYear"), ORGANIZATIONAL_UNIT("OrganizationalUnit"), ORG_TITLE("OrgTitle"),
    PERCENT_OWNERSHIP("PercentOwnership"), PERCENT_VOTE_RIGHT("PercentVoteRight"), PERSON("Person"),
    PERSON_CONTACT("PersonContact"), PERSON_DEMOGRAPHIC("PersonDemographic"), PERSON_FIRST_NAME("PersonFirstName"),
    PERSON_FULL_NAME("PersonFullName"), PERSON_LAST_NAME("PersonLastName"), PERSON_MIDDLE_NAME("PersonMiddleName"),
    PHYSICAL_COLOR("PhysicalColor"), PHYSICAL_DENSITY("PhysicalDensity"), PHYSICAL_DEPTH("PhysicalDepth"),
    PHYSICAL_HEIGHT("PhysicalHeight"), PHYSICAL_SIZE("PhysicalSize"), PHYSICAL_VOLUME("PhysicalVolume"),
    PHYSICAL_WEIGHT("PhysicalWeight"), PHYSICAL_WIDTH("PhysicalWidth"), POINT("Point"), POSTAL_CODE("PostalCode"),
    PRODUCT("Product"), PRODUCT_BRAND("ProductBrand"), PRODUCT_CATEGORY("ProductCategory"),
    PRODUCT_GROUP("ProductGroup"), PRODUCT_SKU("ProductSKU"), PROJECT("Project"), PROJECT_CODE("ProjectCode"),
    PROJECT_COMPLETION("ProjectCompletion"), PROJECT_END_DATE("ProjectEndDate"), PROJECT_NAME("ProjectName"),
    PROJECT_START_DATE("ProjectStartDate"), PROMOTION("Promotion"), QTY_RANGE_HIGH("QtyRangeHigh"),
    QTY_RANGE_LOW("QtyRangeLow"), QUANTITATIVE("Quantitative"), QUARTERS("Quarters"),
    QUARTER_OF_HALF_YEAR("QuarterOfHalfYear"), QUARTER_OF_YEAR("QuarterOfYear"), RATE("Rate"), RATE_TYPE("RateType"),
    REGION("Region"), REGULAR("Regular"), RELATION_TO_PARENT("RelationToParent"), REPORTING_DATE("ReportingDate"),
    REPORTING_DAY_OF_HALF_YEAR("ReportingDayOfHalfYear"), REPORTING_DAY_OF_MONTH("ReportingDayOfMonth"),
    REPORTING_DAY_OF_QUARTER("ReportingDayOfQuarter"), REPORTING_DAY_OF_TRIMESTER("ReportingDayOfTrimester"),
    REPORTING_DAY_OF_WEEK("ReportingDayOfWeek"), REPORTING_DAY_OF_YEAR("ReportingDayOfYear"),
    REPORTING_HALF_YEARS("ReportingHalfYears"), REPORTING_HALF_YEAR_OF_YEAR("ReportingHalfYearOfYear"),
    REPORTING_MONTHS("ReportingMonths"), REPORTING_MONTH_OF_HALF_YEAR("ReportingMonthOfHalfYear"),
    REPORTING_MONTH_OF_QUARTER("ReportingMonthOfQuarter"), REPORTING_MONTH_OF_TRIMESTER("ReportingMonthOfTrimester"),
    REPORTING_MONTH_OF_YEAR("ReportingMonthOfYear"), REPORTING_QUARTERS("ReportingQuarters"),
    REPORTING_QUARTER_OF_HALF_YEAR("ReportingQuarterOfHalfYear"), REPORTING_QUARTER_OF_YEAR("ReportingQuarterOfYear"),
    REPORTING_TRIMESTERS("ReportingTrimesters"), REPORTING_TRIMESTER_OF_YEAR("ReportingTrimesterOfYear"),
    REPORTING_WEEKS("ReportingWeeks"), REPORTING_WEEK_OF_HALF_YEAR("ReportingWeekOfHalfYear"),
    REPORTING_WEEK_OF_MONTH("ReportingWeekOfMonth"), REPORTING_WEEK_OF_QUARTER("ReportingWeekOfQuarter"),
    REPORTING_WEEK_OF_TRIMESTER("ReportingWeekOfTrimester"), REPORTING_WEEK_OF_YEAR("ReportingWeekOfYear"),
    REPORTING_YEARS("ReportingYears"), REPRESENTATIVE("Representative"), ROW_NUMBER("RowNumber"),
    SCD_END_DATE("ScdEndDate"), SCD_ORIGINAL_ID("ScdOriginalID"), SCD_START_DATE("ScdStartDate"),
    SCD_STATUS("ScdStatus"), SCENARIO("Scenario"), SECONDS("Seconds"), SEQUENCE("Sequence"),
    SHORT_CAPTION("ShortCaption"), STATE_OR_PROVINCE("StateOrProvince"), TEN_DAY("TenDay"),
    TEN_DAY_OF_HALF_YEAR("TenDayOfHalfYear"), TEN_DAY_OF_MONTH("TenDayOfMonth"), TEN_DAY_OF_QUARTER("TenDayOfQuarter"),
    TEN_DAY_OF_TRIMESTER("TenDayOfTrimester"), TEN_DAY_OF_YEAR("TenDayOfYear"), TRIMESTERS("Trimesters"),
    TRIMESTER_OF_YEAR("TrimesterOfYear"), UNDEFINED_TIME("UndefinedTime"), UTILITY("Utility"), VERSION("Version"),
    WEB_HTML("WebHtml"), WEB_MAIL_ALIAS("WebMailAlias"), WEB_URL("WebUrl"), WEB_XML_OR_XSL("WebXmlOrXsl"),
    WEEK_OF_YEAR("WeekOfYear"), WEEKS("Weeks"), WINTER_SUMMER_SEASON("WinterSummerSeason"), YEARS("Years");

    private final String value;

    DimensionAttributeTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DimensionAttributeTypeEnumType fromValue(String v) {
        for (DimensionAttributeTypeEnumType c : DimensionAttributeTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
