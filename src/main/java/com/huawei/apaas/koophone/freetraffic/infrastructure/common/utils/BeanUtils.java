package com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils;

import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.ErrorCode;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.KooPhoneException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * bean和map互转util
 * @author zhangjihong
 * @since 2023-05-28
 */
@Slf4j
public class BeanUtils {
    private BeanUtils() { }

    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (IllegalAccessException e) {
            log.error("beanToMap illegalAccess. ", e);
            throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION);
        }
        return map;
    }

    public static void beanToTreeMap(TreeMap<String, Object> map, Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (IllegalAccessException e) {
            log.error("beanToTreeMap illegalAccess. ", e);
            throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION);
        }
    }
    public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass) {
        T object = null;
        try {
            object = beanClass.newInstance();
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                if (map.containsKey(field.getName())) {
                    field.set(object, map.get(field.getName()));
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("mapToBean illegalAccess. ", e);
            throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION);
        }
        return object;
    }
}
