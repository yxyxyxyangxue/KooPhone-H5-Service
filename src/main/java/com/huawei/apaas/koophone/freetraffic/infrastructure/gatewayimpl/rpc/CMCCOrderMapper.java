package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc;

import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.KooPhoneException;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 调用移动营销平台接口
 * @author zhangjihong
 * @since 2023-05-23
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CMCCOrderMapper {
    private final RestTemplate restTemplate;

    @Value("${cmcc.url.add_order}")
    private String addOrderUrl;

    /**
     * 请求移动营销平台，下单
     * @param addOrderDO
     * @return
     */
    public AddOrderResponseDO addOrder(AddOrderDO addOrderDO) {
        ResponseEntity<AddOrderResponseDO> response = restTemplate.postForEntity(
                addOrderUrl, addOrderDO, AddOrderResponseDO.class);
        handlerFailure(response, addOrderDO);
        return response.getBody();
    }

    private void handlerFailure(ResponseEntity<?> response, Object request) {
        HttpStatus statusCode = response.getStatusCode();
        log.debug("Request CMCC market platform. request: {}", request);
        log.debug("Request CMCC market platform. response: {}", response);
        if (!HttpStatus.OK.equals(statusCode)) {
            throw new KooPhoneException(String.valueOf(statusCode.value()), statusCode.getReasonPhrase());
        }
    }
}
