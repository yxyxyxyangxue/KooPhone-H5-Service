package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa;

import com.huawei.apaas.koophone.freetraffic.FreeTrafficApplicationTests;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class OrderRepositoryTest extends FreeTrafficApplicationTests {
    @Autowired
    OrderRepository orderRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    public void test_jpa() {
        List<OrderDO> list = orderRepository.findAll();
        Assertions.assertNotNull(list);
    }
}