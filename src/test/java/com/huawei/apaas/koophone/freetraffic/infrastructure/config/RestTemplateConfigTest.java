package com.huawei.apaas.koophone.freetraffic.infrastructure.config;

import com.huawei.apaas.koophone.freetraffic.FreeTrafficApplicationTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RestTemplateConfigTest extends FreeTrafficApplicationTests {
    @Autowired
    RestTemplate restTemplate;
    @BeforeEach
    void setUp() {
    }

    @Test
    void restTemplate() {
        Assertions.assertNotNull(restTemplate);
    }

    // @Test
    public void test_get() {
        String url = "http://127.0.0.1:8080/user/info?name={}";
        Map<String, String> params = new HashMap<>();
        params.put("name", "tom");
        Object obj = restTemplate.getForObject(url, Object.class, params);
        System.out.println(obj);
    }
}