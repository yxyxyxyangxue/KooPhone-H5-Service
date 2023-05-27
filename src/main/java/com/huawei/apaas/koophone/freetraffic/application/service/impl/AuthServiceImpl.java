package com.huawei.apaas.koophone.freetraffic.application.service.impl;

import com.huawei.apaas.koophone.freetraffic.application.dto.*;
import com.huawei.apaas.koophone.freetraffic.application.service.IAuthService;
import com.huawei.apaas.koophone.freetraffic.application.service.wrapper.LoginWrapper;
import com.huawei.apaas.koophone.freetraffic.application.service.wrapper.SendSmsCodeWrapper;
import com.huawei.apaas.koophone.freetraffic.application.service.wrapper.ValidateTokenWrapper;
import com.huawei.apaas.koophone.freetraffic.domain.gateway.AuthGateway;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.SystemConstant;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.ErrorCode;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.KooPhoneException;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.DateUtils;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.EncryptUtils;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.JsonUtils;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.UuidUtils;
import com.huawei.apaas.koophone.freetraffic.infrastructure.config.FreeTrafficProperties;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 认证服务
 * @author zhangjihong
 * @since 2023-05-23
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final AuthGateway authGateway;
    private final FreeTrafficProperties freeTrafficProperties;

    @Override
    public LoginResponseDTO login(LoginRequest loginRequest) {
        // 1. 组装请求DO
        LoginDO loginDO = LoginWrapper.request2do(loginRequest);
        loginDO.getHeader().setMsgid(UuidUtils.genUuid());
        loginDO.getHeader().setSystemtime(DateUtils.now());
        loginDO.getHeader().setVersion(SystemConstant.VERSION);
        loginDO.getHeader().setSourceid(freeTrafficProperties.getUserIp());
        loginDO.getHeader().setApptype(loginRequest.getApptype());
        loginDO.getHeader().setUserip(freeTrafficProperties.getUserIp());
        loginDO.getHeader().setMac(EncryptUtils.hmacsha256(
                freeTrafficProperties.getSourceKey(), JsonUtils.toJson(loginDO)));
        // 2. 请求
        LoginResponseDO loginResponseDO = authGateway.login(loginDO);
        // 3. 组装response
        String resultcode = loginResponseDO.getHeader().getResultcode();
        if (SystemConstant.LOGIN_OK.equals(resultcode)) {
            return LoginWrapper.do2Dto(loginResponseDO);
        } else {
            log.info("Login Failure. {}. {}", loginRequest, loginResponseDO);
            throw new KooPhoneException(ErrorCode.LOGIN_FAILURE);
        }
    }

    @Override
    public ValidateTokenResponseDTO validToken(ValidateTokenRequest validateTokenRequest) {
        // 1. 组装请求DO
        ValidateTokenDO validateTokenDO = ValidateTokenWrapper.request2Do(validateTokenRequest);
        validateTokenDO.getHeader().setVersion(SystemConstant.VERSION);
        validateTokenDO.getHeader().setId(freeTrafficProperties.getSourceId());
        validateTokenDO.getHeader().setIdtype(SystemConstant.ID_TYPE);
        validateTokenDO.getHeader().setMsgid(UuidUtils.genUuid());
        validateTokenDO.getHeader().setAppType(UuidUtils.genUuid());
        validateTokenDO.getHeader().setUserip(freeTrafficProperties.getUserIp());
        validateTokenDO.getHeader().setSystemtime(DateUtils.now());
        validateTokenDO.getHeader().setExpandparams(SystemConstant.VALID_TOKEN_EXPANDPARAMS);
        validateTokenDO.getHeader().setSign(buildSign(validateTokenDO));
        // 2. 请求
        ValidateTokenResponseDO validateTokenResponseDO = authGateway.validToken(validateTokenDO);
        // 3. 组装响应DTO
        ValidateTokenResponseDO.ValidateTokenResponseHeader header = validateTokenResponseDO.getHeader();
        String resultcode = header.getResultcode();
        if (SystemConstant.VALIDATE_TOKEN_OK.equals(resultcode)) {
            ValidateTokenResponseDTO responseDTO = ValidateTokenWrapper.do2Dto(validateTokenResponseDO);
            responseDTO.setMsisdn(EncryptUtils.deCodeAES(responseDTO.getMsisdn(), freeTrafficProperties.getSourceKey()));
            return responseDTO;
        } else {
            log.info("Invalid Token. {}. {}", validateTokenRequest, validateTokenResponseDO);
            throw new KooPhoneException(ErrorCode.VALIDATE_TOKEN_FAILURE);
        }
    }

    private String buildSign(ValidateTokenDO validateTokenDO) {
        Map<String, String> map = new HashMap<>();
        map.put("version", validateTokenDO.getHeader().getVersion());
        map.put("id", freeTrafficProperties.getSourceId());
        map.put("idtype", validateTokenDO.getHeader().getIdtype());
        map.put("msgid", validateTokenDO.getHeader().getMsgid());
        map.put("key", freeTrafficProperties.getSourceKey());
        map.put("apptype", validateTokenDO.getHeader().getAppType());
        map.put("systemtime", validateTokenDO.getHeader().getSystemtime());
        map.put("token", validateTokenDO.getBody().getToken());
        return EncryptUtils.md5(map);
    }

    @Override
    public SendSmsCodeResponseDTO sendSmsCode(SendSmsCodeRequest sendSmsCodeRequest) {
        // 1. 组装请求DO
        SendSmsCodeDO sendSmsCodeDO = SendSmsCodeWrapper.request2Do(sendSmsCodeRequest);
        sendSmsCodeDO.getHeader().setMsgid(UuidUtils.genUuid());
        sendSmsCodeDO.getHeader().setSystemtime(DateUtils.now());
        sendSmsCodeDO.getHeader().setVersion(SystemConstant.VERSION);
        sendSmsCodeDO.getHeader().setSourceid(freeTrafficProperties.getSourceId());
        sendSmsCodeDO.getHeader().setUserip(freeTrafficProperties.getUserIp());
        sendSmsCodeDO.getHeader().setMac(EncryptUtils.hmacsha256(
                freeTrafficProperties.getSourceKey(), JsonUtils.toJson(sendSmsCodeDO)));
        // 2. 发送请求
        SendSmsCodeResponseDO sendSmsCodeResponseDO = authGateway.sendSmsCode(sendSmsCodeDO);
        // 3. 组装response
        SendSmsCodeResponseDO.SendSmsCodeResponseHeader header = sendSmsCodeResponseDO.getHeader();
        String resultcode = header.getResultcode();
        if (SystemConstant.SEND_SMS_CODE_OK.equals(resultcode)) {
            return SendSmsCodeWrapper.do2Dto(sendSmsCodeResponseDO);
        } else {
            log.info("Sending SMS failed. {}. {}", sendSmsCodeRequest, sendSmsCodeResponseDO);
            throw new KooPhoneException(ErrorCode.SEND_SMS_CODE_FAILURE);
        }
    }

    @Override
    public SignResponseDTO sign(SignRequest signRequest) {
        // TODO 私钥在哪里
        String signature = EncryptUtils.rsaEncode(signRequest.getOriginSignature(), null);
        return new SignResponseDTO(signature);
    }
}
