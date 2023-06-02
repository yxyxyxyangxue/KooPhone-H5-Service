package com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void afterNow() {
        assertFalse(DateUtils.afterNow("20210530"));
    }

    @Test
    void beforeNow() {
        assertTrue(DateUtils.beforeNow("20210530"));
    }

    @Test
    public void test() throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        Thread.sleep(1000);
        LocalDateTime now1 = LocalDateTime.now();
        assertFalse(now.isAfter(now1));
        assertTrue(now.isBefore(now1));
    }

    @Test
    public void test_getCurrentMonthLastDay() {
        LocalDate dateTime = DateUtils.getCurrentMonthLastDay("20230201");
        String format = DateUtils.formatDate(dateTime);
        assertEquals("20230228", format);
    }
}