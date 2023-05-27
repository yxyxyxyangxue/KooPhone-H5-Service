package com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.ErrorCode;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.KooPhoneException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * json工具类
 * @author zhangjihong
 * @since 2023-05-23
 */
@Slf4j
public class JsonUtils {
    private final static ObjectMapper objectMapper;
    private JsonUtils() { }
    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Bean to JSON String fail. {}", e.getMessage());
            throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION, e);
        }
    }

    public static <T> T toBean(String json, Class<T> clz) {
        if (!StringUtils.hasText(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clz);
        } catch (JsonProcessingException e) {
            log.error("to Bean failed. {}", e.getMessage());
            throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION, e);
        }
    }
}
