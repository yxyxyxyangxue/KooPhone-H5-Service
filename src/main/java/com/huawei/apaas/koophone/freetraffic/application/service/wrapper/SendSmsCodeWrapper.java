package com.huawei.apaas.koophone.freetraffic.application.service.wrapper;

import com.huawei.apaas.koophone.freetraffic.application.dto.SendSmsCodeRequest;
import com.huawei.apaas.koophone.freetraffic.application.dto.SendSmsCodeResponseDTO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.SendSmsCodeDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.SendSmsCodeResponseDO;

/**
 *
 * @author zhangjihong
 * @since 2023-05-23
 */
public class SendSmsCodeWrapper {
    private SendSmsCodeWrapper() { }

    public static SendSmsCodeDO request2Do(SendSmsCodeRequest sendSmsCodeRequest) {
        SendSmsCodeDO sendSmsCodeDO = new SendSmsCodeDO();
        SendSmsCodeDO.SendSmsCodeHeader header = new SendSmsCodeDO.SendSmsCodeHeader();
        header.setApptype(sendSmsCodeRequest.getApptype());
        sendSmsCodeDO.setHeader(header);

        SendSmsCodeDO.SendSmsCodeBody body = new SendSmsCodeDO.SendSmsCodeBody();
        body.setMsisdn(sendSmsCodeRequest.getMsisdn());
        sendSmsCodeDO.setBody(body);
        return sendSmsCodeDO;
    }

    public static SendSmsCodeResponseDTO do2Dto(SendSmsCodeResponseDO sendSmsCodeResponseDO) {
        return new SendSmsCodeResponseDTO();
    }
}
