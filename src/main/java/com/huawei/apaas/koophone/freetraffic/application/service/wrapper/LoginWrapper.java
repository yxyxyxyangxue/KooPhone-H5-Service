package com.huawei.apaas.koophone.freetraffic.application.service.wrapper;

import com.huawei.apaas.koophone.freetraffic.application.dto.LoginRequest;
import com.huawei.apaas.koophone.freetraffic.application.dto.LoginResponseDTO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.LoginDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.LoginResponseDO;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author zhangjihong
 * @since 2023-05-23
 */
public class LoginWrapper {
    private LoginWrapper() {}

    public static LoginDO request2do(LoginRequest loginRequest) {
        LoginDO loginDO = new LoginDO();
        LoginDO.LoginHeader header = new LoginDO.LoginHeader();
        loginDO.setHeader(header);

        LoginDO.LoginBody body = new LoginDO.LoginBody();
        body.setLoginid(loginRequest.getLoginid());
        body.setLoginidtype(loginRequest.getLoginidtype());
        body.setAuthtype(loginRequest.getAuthtype());
        body.setPassword(loginRequest.getPassword());
        body.setExpandparams(loginRequest.getExpandparams());
        loginDO.setBody(body);
        return loginDO;
    }

    public static LoginResponseDTO do2Dto(LoginResponseDO loginDO) {
        LoginResponseDO.LoginResponseBody body = loginDO.getBody();
        LoginResponseDTO dto = new LoginResponseDTO();
        BeanUtils.copyProperties(body, dto);
        return dto;
    }
}
