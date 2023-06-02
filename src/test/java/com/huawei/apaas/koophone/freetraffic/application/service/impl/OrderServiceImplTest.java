package com.huawei.apaas.koophone.freetraffic.application.service.impl;

import com.huawei.apaas.koophone.freetraffic.FreeTrafficApplicationTests;
import com.huawei.apaas.koophone.freetraffic.application.dto.OrderStatusRequest;
import com.huawei.apaas.koophone.freetraffic.application.dto.OrderStatusResponseDTO;
import com.huawei.apaas.koophone.freetraffic.application.service.IOrderService;
import com.huawei.apaas.koophone.freetraffic.domain.gateway.AuthGateway;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.CMCCAuthMapper;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.GetUserinfoResponseDO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest extends FreeTrafficApplicationTests {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private AuthGateway authGateway;
    @Autowired
    private CMCCAuthMapper cmccAuthMapper;
    @Mock
    private RestTemplate restTemplate;
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        GetUserinfoResponseDO responseDO = new GetUserinfoResponseDO();
        responseDO.setPcId("xxxxxxx");
        responseDO.setResultcode("0");
        ResponseEntity<GetUserinfoResponseDO> response = ResponseEntity.ok().body(responseDO);
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(),
                        Mockito.eq(GetUserinfoResponseDO.class), Mockito.anyMap()))
                .thenReturn(response);
        ReflectionTestUtils.setField(cmccAuthMapper, "restTemplate", restTemplate);
        ReflectionTestUtils.setField(authGateway, "cmccAuthMapper", cmccAuthMapper);
        ReflectionTestUtils.setField(orderService, "authGateway", authGateway);
    }

    // @Test
    void test_orderStatus() {
        OrderStatusRequest request = new OrderStatusRequest();
        request.setTelephone("xxxxxxxxx");
        OrderStatusResponseDTO orderStatusResponseDTO = orderService.orderStatus(request);
        assertEquals(orderStatusResponseDTO.getStatus(), OrderStatusResponseDTO.OrderStatus.PENDING);
    }
}