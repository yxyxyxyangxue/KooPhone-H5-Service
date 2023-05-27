package com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间util
 * @author zhangjihong
 * @since 2023-05-23
 */
public class DateUtils {
    private DateUtils() { }
    private final static DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public static String format(LocalDateTime localDateTime) {
        return dateformat.format(localDateTime);
    }

    public static String now() {
        return format(LocalDateTime.now());
    }
}
