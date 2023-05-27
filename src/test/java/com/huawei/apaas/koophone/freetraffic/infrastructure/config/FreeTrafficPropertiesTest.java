package com.huawei.apaas.koophone.freetraffic.infrastructure.config;

import com.huawei.apaas.koophone.freetraffic.FreeTrafficApplicationTests;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

class FreeTrafficPropertiesTest extends FreeTrafficApplicationTests {
    @Autowired
    private FreeTrafficProperties freeTrafficProperties;

    // @Test
    public void test_getUserip() {
        String userIp = freeTrafficProperties.getUserIp();
        Assertions.assertEquals(userIp, "139.196.180.61");
    }
}