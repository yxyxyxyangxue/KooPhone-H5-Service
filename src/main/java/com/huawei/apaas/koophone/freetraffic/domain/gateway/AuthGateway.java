package com.huawei.apaas.koophone.freetraffic.domain.gateway;

import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.*;

/**
 * 认证接口
 * @author zhangjihong
 * @since 2023-05-23
 */
public interface AuthGateway {
    /**
     * 请求移动认证平台 - 验证码登录
     * @param loginDO
     * @return
     */
    LoginResponseDO login(LoginDO loginDO);

    /**
     * 请求移动认证平台 - 校验token并返回手机号
     * @param validateTokenDO
     * @return
     */
    ValidateTokenResponseDO validToken(ValidateTokenDO validateTokenDO);

    /**
     * 请求移动认证平台 - 发送短信验证码
     * @param sendSmsCodeDO
     * @return
     */
    SendSmsCodeResponseDO sendSmsCode(SendSmsCodeDO sendSmsCodeDO);
}
