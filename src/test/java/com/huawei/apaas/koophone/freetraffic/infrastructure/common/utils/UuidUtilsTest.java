package com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils;

import com.huawei.apaas.koophone.freetraffic.FreeTrafficApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class UuidUtilsTest {

    @Test
    void genUuid() {
        String uuid = UuidUtils.genUuid();
        log.debug("uuid = {}", uuid);
        boolean contains = uuid.contains("-");
        Assertions.assertFalse(contains);
        Assertions.assertEquals(uuid.length(), 32);
    }
}