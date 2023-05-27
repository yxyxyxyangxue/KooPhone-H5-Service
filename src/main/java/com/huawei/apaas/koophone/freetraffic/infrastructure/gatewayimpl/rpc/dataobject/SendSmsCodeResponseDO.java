package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject;

import lombok.Data;

/**
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
public class SendSmsCodeResponseDO {
    private SendSmsCodeResponseHeader header;
    private SendSmsCodeResponseBody body;
    @Data
    public static class SendSmsCodeResponseHeader {
        /**
         * 对应 msgid
         */
        private String inresponseto;
        /**
         * 结果码，成功104000
         */
        private String resultcode;
        /**
         * 结果描述，"成功"
         */
        private String resultdesc;
        /**
         * 响应系统时间 17位
         */
        private String systemtime;
        /**
         * 版本 1.0
         */
        private String version;
        /**
         * mac
         */
        private String mac;
    }

    @Data
    public static class SendSmsCodeResponseBody {

    }
}
