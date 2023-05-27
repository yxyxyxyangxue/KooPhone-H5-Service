package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject;

import lombok.Data;

/**
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
public class LoginResponseDO {
    private LoginResponseHeader header;
    private LoginResponseBody body;

    @Data
    public static class LoginResponseHeader {
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
    public static class LoginResponseBody {
        /**
         * 统一账号标识
         */
        private String passid;
        /**
         * 统一账号绑定手机号
         */
        private String msisdn;
        /**
         * 邮箱
         */
        private String email;
        /**
         * 单点登录标识 uid
         */
        private String usessionid;
        /**
         * 省份代码
         */
        private String province;
        /**
         * <p>01-全球通</p>
         * <p>02-神州行</p>
         * <p>03-动感地带</p>
         */
        private String brand;
        /**
         * 昵称
         */
        private String nickname;
    }
}
