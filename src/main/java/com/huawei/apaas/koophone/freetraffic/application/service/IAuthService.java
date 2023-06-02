package com.huawei.apaas.koophone.freetraffic.application.service;

import com.huawei.apaas.koophone.freetraffic.application.dto.*;

/**
 * 认证服务
 * @author zhangjihong
 * @since 2023-05-23
 */
public interface IAuthService {
    /**
     * 验证码登录
     * @param loginRequest
     * @return
     */
    LoginResponseDTO login(LoginRequest loginRequest);

    /**
     * token校验+获取手机号
     * @param validateTokenRequest
     * @return
     */
    ValidateTokenResponseDTO validToken(ValidateTokenRequest validateTokenRequest);

    /**
     * 发送短信验证码
     * @param sendSmsCodeRequest
     * @return
     */
    SendSmsCodeResponseDTO sendSmsCode(SendSmsCodeRequest sendSmsCodeRequest);

    /**
     * 根据原始签名获取私钥签名
     * @param signRequest
     * @return
     */
    SignResponseDTO sign(SignRequest signRequest);
}
