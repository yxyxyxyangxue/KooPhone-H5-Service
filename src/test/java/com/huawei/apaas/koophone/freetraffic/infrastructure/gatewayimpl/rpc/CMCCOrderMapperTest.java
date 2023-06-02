package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.apaas.koophone.freetraffic.FreeTrafficApplicationTests;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.AddOrderDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.AddOrderResponseDO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class CMCCOrderMapperTest extends FreeTrafficApplicationTests {
    @Autowired
    CMCCOrderMapper cmccOrderMapper;
    @Autowired
    RestTemplate restTemplate;
    private MockRestServiceServer mockServer;
    @Value("${cmcc.url.add_order}")
    private String addOrderUrl;
    private ObjectMapper mapper = new ObjectMapper();
    @BeforeEach
    void setup() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }
    @Test
    void addOrder() throws Exception{
        AddOrderResponseDO responseDO = new AddOrderResponseDO();
        responseDO.setSuccess(true);
        responseDO.setCode("0");
        AddOrderResponseDO.RpcAddOrderResp resp = new AddOrderResponseDO.RpcAddOrderResp();
        resp.setSourceOrderNo("xxxxxx");
        resp.setTelephone("xxxxxxx");
        resp.setActivityId(232323344L);
        responseDO.setData(resp);
        mockServer.expect(ExpectedCount.once(), MockRestRequestMatchers.requestTo(new URI(addOrderUrl)))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(responseDO)));

        AddOrderDO addOrderDO = new AddOrderDO();
        AddOrderResponseDO addOrderResponseDO = cmccOrderMapper.addOrder(addOrderDO);
        assertTrue(addOrderResponseDO.getSuccess());
        assertNotNull(addOrderResponseDO.getData());
    }
}