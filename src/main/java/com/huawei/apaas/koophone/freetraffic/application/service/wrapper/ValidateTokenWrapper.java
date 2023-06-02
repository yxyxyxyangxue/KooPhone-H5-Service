package com.huawei.apaas.koophone.freetraffic.application.service.wrapper;

import com.huawei.apaas.koophone.freetraffic.application.dto.ValidateTokenRequest;
import com.huawei.apaas.koophone.freetraffic.application.dto.ValidateTokenResponseDTO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.SystemConstant;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.KooPhoneException;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.EncryptUtils;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.ValidateTokenDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.ValidateTokenResponseDO;

/**
 *
 * @author zhangjihong
 * @since 2023-05-23
 */
public class ValidateTokenWrapper {
    private ValidateTokenWrapper() { }

    public static ValidateTokenDO request2Do(ValidateTokenRequest validateTokenRequest) {
        ValidateTokenDO validateTokenDO = new ValidateTokenDO();

        ValidateTokenDO.ValidateTokenBody validateTokenBody = new ValidateTokenDO.ValidateTokenBody();
        validateTokenBody.setUserInformation(validateTokenRequest.getUserInformation());
        validateTokenBody.setToken(validateTokenRequest.getToken());
        validateTokenDO.setBody(validateTokenBody);

        ValidateTokenDO.ValidateTokenHeader validateTokenHeader = new ValidateTokenDO.ValidateTokenHeader();
        validateTokenHeader.setApptype(validateTokenRequest.getAppType());
        validateTokenDO.setHeader(validateTokenHeader);
        return validateTokenDO;
    }

    public static ValidateTokenResponseDTO do2Dto(ValidateTokenResponseDO validateTokenResponseDO) {
        ValidateTokenResponseDTO dto = new ValidateTokenResponseDTO();
        ValidateTokenResponseDO.ValidateTokenResponseBody body = validateTokenResponseDO.getBody();
        dto.setNickname(body.getNickname());
        dto.setUsessionid(body.getUsessionid());
        dto.setMsmsdnmask(body.getMsisdnmask());
        dto.setPassid(body.getPassid());
        dto.setEmail(body.getEmail());
        dto.setLoginidtype(body.getLoginidtype());
        dto.setProvince(body.getProvince());
        dto.setAuthtype(body.getAuthtype());
        dto.setAuthtime(body.getAuthtime());
        dto.setMsisdn(body.getMsisdn());
        return dto;
    }
}
