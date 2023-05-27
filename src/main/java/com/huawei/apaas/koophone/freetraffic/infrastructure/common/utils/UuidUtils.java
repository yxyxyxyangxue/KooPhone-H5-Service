package com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils;

import java.util.UUID;

/**
 * uuid工具类
 * @author zhangjihong
 * @since 2023-05-23
 */
public class UuidUtils {
    private UuidUtils() { }
    public static String genUuid() {
        String result = UUID.randomUUID().toString();
        return result.replace("-", "");
    }
}
