package com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils;

import com.huawei.apaas.koophone.freetraffic.application.dto.QueryOrderResponseDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BeanUtilsTest{
    @Test
    public void test_map2Bean() {
        QueryOrderResponseDTO dto = new QueryOrderResponseDTO();
        dto.setTelephone("23232323");
        dto.setStatus(2);
        LocalDateTime now = LocalDateTime.now();
        dto.setActualExecuteTime(now);
        Map<String, Object> map = BeanUtils.beanToMap(dto);
        System.out.println(map);
        assertEquals("23232323", map.get("telephone"));
        assertEquals(2, map.get("status"));
        assertEquals(now, map.get("actualExecuteTime"));

        QueryOrderResponseDTO dto1 = BeanUtils.mapToBean(map, QueryOrderResponseDTO.class);
        assertEquals(dto1, dto);
    }

}