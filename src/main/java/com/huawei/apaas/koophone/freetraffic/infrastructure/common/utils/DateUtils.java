package com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * 时间util
 * @author zhangjihong
 * @since 2023-05-23
 */
public class DateUtils {
    private DateUtils() { }

    private final static DateTimeFormatter DATEFORMAT_DATETIME = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    private final static DateTimeFormatter DATEFORMAT_DATETIME_NOSEC = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private final static DateTimeFormatter DATEFORMAT_DATE = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String formatDateTime(LocalDateTime localDateTime) {
        return DATEFORMAT_DATETIME.format(localDateTime);
    }

    public static String formatDate(LocalDate localDate) {
        return DATEFORMAT_DATE.format(localDate);
    }

    public static String now() {
        return formatDateTime(LocalDateTime.now());
    }

    public static LocalDate parseDate(String origin) {
        return LocalDate.parse(origin, DATEFORMAT_DATE);
    }

    /**
     * origin是否在now()之后
     * @param origin
     * @return
     */
    public static boolean afterNow(String origin) {
        return parseDate(origin).atStartOfDay().isAfter(LocalDateTime.now());
    }

    /**
     * origin是否在now()之前
     * @param origin
     * @return
     */
    public static boolean beforeNow(String origin) {
        return parseDate(origin).atStartOfDay().isBefore(LocalDateTime.now());
    }

    public static LocalDate getCurrentMonthLastDay(String origin) {
        LocalDate dateTime = parseDate(origin);
        return dateTime.with(TemporalAdjusters.lastDayOfMonth());
    }
}
