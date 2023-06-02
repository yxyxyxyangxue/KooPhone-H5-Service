package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl;

import com.huawei.apaas.koophone.freetraffic.FreeTrafficApplicationTests;
import com.huawei.apaas.koophone.freetraffic.domain.gateway.AuthGateway;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.CMCCAuthMapper;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.GetUserinfoDO;
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

class AuthGatewayImplTest extends FreeTrafficApplicationTests {
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
        responseDO.setPcId("454546565");
        responseDO.setResultcode("0");
        ResponseEntity<GetUserinfoResponseDO> response = ResponseEntity.ok().body(responseDO);
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(),
                        Mockito.eq(GetUserinfoResponseDO.class), Mockito.anyMap()))
                .thenReturn(response);
        ReflectionTestUtils.setField(cmccAuthMapper, "restTemplate", restTemplate);
        ReflectionTestUtils.setField(authGateway, "cmccAuthMapper", cmccAuthMapper);
    }
    // @Test
    void getUserinfo() {
        GetUserinfoDO request = new GetUserinfoDO();
        request.setUserId("1230000124");
        GetUserinfoResponseDO userinfo = authGateway.getUserinfo(request);
        assertEquals(userinfo.getPcId(), "454546565");
    }
}