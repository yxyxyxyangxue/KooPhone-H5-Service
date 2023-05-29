package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc;

import com.huawei.apaas.koophone.freetraffic.infrastructure.common.SystemConstant;
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
 * 调用移动认证平台接口
 * @author zhangjihong
 * @since 2023-05-23
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CMCCAuthMapper {
    private final RestTemplate restTemplate;

    @Value("${cmcc.url.valid_token}")
    private String validTokenUrl;
    @Value("${cmcc.url.login}")
    private String loginUrl;
    @Value("${cmcc.url.send_sms_code}")
    private String sendSmsCodeUrl;

    /**
     * 校验token，获取手机号
     * @return
     */
    public ValidateTokenResponseDO validToken(ValidateTokenDO validateTokenDO) {
        ResponseEntity<ValidateTokenResponseDO> response = restTemplate.postForEntity(
                validTokenUrl, validateTokenDO, ValidateTokenResponseDO.class);
        handlerFailure(response, validateTokenDO);
        return response.getBody();
    }

    /**
     * 短信验证码登录
     * @return
     */
    public LoginResponseDO login(LoginDO loginDO) {
        ResponseEntity<LoginResponseDO> response = restTemplate.postForEntity(
                loginUrl, loginDO, LoginResponseDO.class);
        handlerFailure(response, loginDO);
        return response.getBody();
    }

    /**
     * 发送验证消息
     * @return
     */
    public SendSmsCodeResponseDO sendSmsCode(SendSmsCodeDO sendSmsCodeDO) {
        ResponseEntity<SendSmsCodeResponseDO> response = restTemplate.postForEntity(
                sendSmsCodeUrl, sendSmsCodeDO, SendSmsCodeResponseDO.class);
        handlerFailure(response, sendSmsCodeDO);
        return response.getBody();
    }

    private void handlerFailure(ResponseEntity<?> response, Object request) {
        HttpStatus statusCode = response.getStatusCode();
        log.warn("Request CMCC auth platform. request: {}", request);
        log.warn("Request CMCC auth platform. response: {}", response);
        if (!HttpStatus.OK.equals(statusCode)) {
            throw new KooPhoneException(SystemConstant.CMCC_HTTP_FAILURE + statusCode.value(), statusCode.getReasonPhrase());
        }
    }
}
