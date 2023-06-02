package com.huawei.apaas.koophone.freetraffic.adapter;

import com.huawei.apaas.koophone.freetraffic.application.dto.*;
import com.huawei.apaas.koophone.freetraffic.application.service.IAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 履约服务
 * @author zhangjihong
 * @since 2023-05-23
 */
@RestController
@RequestMapping("/apaas/koophone/v1/auth")
@Slf4j
@RequiredArgsConstructor
@Api(tags = "认证服务")
public class AuthController {
    private final IAuthService authService;
    /**
     * token校验，获取phone
     * @return
     */
    @ApiOperation(value = "token校验", notes = "校验token有效性并返回对应手机号")
    @PostMapping("/phone")
    public SingleResponse<ValidateTokenResponseDTO> uniTokenValidate(@RequestBody ValidateTokenRequest validateTokenRequest) {
        return SingleResponse.of(authService.validToken(validateTokenRequest));
    }

    /**
     * 签名
     * @param signRequest
     * @return
     */
    @PostMapping("/sign")
    @ApiOperation(value = "签名接口", notes = "传入原始签名，返回私钥签名")
    public SingleResponse<SignResponseDTO> sign(@RequestBody SignRequest signRequest) {
        return SingleResponse.of(authService.sign(signRequest));
    }

    /**
     * 发送短信验证码
     * @return
     */
    @PostMapping("/sendSmsCode")
    @ApiOperation(value = "请求短信登录验证码", notes = "请求移动平台发送短信验证码到指定手机上")
    public SingleResponse<SendSmsCodeResponseDTO> sendSmsCode(
            @RequestBody SendSmsCodeRequest sendSmsCodeRequest) {
        return SingleResponse.of(authService.sendSmsCode(sendSmsCodeRequest));
    }

    /**
     * 登录接口
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录认证", notes = "根据短信验证码进行登录")
    public SingleResponse<LoginResponseDTO> login(@RequestBody LoginRequest loginRequest) {
        return SingleResponse.of(authService.login(loginRequest));
    }
}
