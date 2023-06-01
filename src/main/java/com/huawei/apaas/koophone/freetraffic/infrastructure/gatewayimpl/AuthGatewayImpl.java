package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl;

import com.huawei.apaas.koophone.freetraffic.domain.gateway.AuthGateway;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.CMCCAuthMapper;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 认证服务
 * @author zhangjihong
 * @since 2023-05-23
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthGatewayImpl implements AuthGateway {
    private final CMCCAuthMapper cmccAuthMapper;

    @Override
    public LoginResponseDO login(LoginDO loginDO) {
        return cmccAuthMapper.login(loginDO);
    }

    @Override
    public ValidateTokenResponseDO validToken(ValidateTokenDO validateTokenDO) {
        return cmccAuthMapper.validToken(validateTokenDO);
    }

    @Override
    public SendSmsCodeResponseDO sendSmsCode(SendSmsCodeDO sendSmsCodeDO) {
        return cmccAuthMapper.sendSmsCode(sendSmsCodeDO);
    }

    @Override
    public GetUserinfoResponseDO getUserinfo(GetUserinfoDO getUserinfoDO) {
        return cmccAuthMapper.getUserinfo(getUserinfoDO);
    }
}
