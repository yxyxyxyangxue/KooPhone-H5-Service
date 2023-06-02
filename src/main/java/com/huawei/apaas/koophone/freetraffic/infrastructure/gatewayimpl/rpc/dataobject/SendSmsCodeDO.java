package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject;

import lombok.Data;

/**
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
public class SendSmsCodeDO {
    private SendSmsCodeHeader header;
    private SendSmsCodeBody body;
    @Data
    public static class SendSmsCodeHeader {
        /**
         * 请求唯一ID
         */
        private String msgid;
        /**
         * 请求时间，精确到毫秒，共17位
         * <p>example: 20230525180001165</p>
         */
        private String systemtime;
        /**
         * 版本号，1.0
         */
        private String version;
        /**
         * 申请接入id
         */
        private String sourceid;
        /**
         * 签名
         */
        private String mac;
        /**
         * <p>1 - BOSS</p>
         * <p>2 - web</p>
         * <p>3 - wap</p>
         * <p>4 - pc端</p>
         * <p>5 - 手机端</p>
         */
        private String apptype;
        /**
         * 用户公网ip
         */
        private String userip;
    }

    @Data
    public static class SendSmsCodeBody {
        /**
         * 用户手机号
         * <p>maxLength = 16</p>
         */
        private String msisdn;
        /**
         * 短信模板，移动认证平台在使用省接口下发时会传递此字段值
         * <p>maxLength = 32</p>
         */
        private String template;
    }
}
