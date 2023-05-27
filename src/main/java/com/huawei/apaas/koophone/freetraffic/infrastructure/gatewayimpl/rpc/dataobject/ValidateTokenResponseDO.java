package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject;

import lombok.Data;

/**
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
public class ValidateTokenResponseDO {
    private ValidateTokenResponseHeader header;
    private ValidateTokenResponseBody body;
    @Data
    public static final class ValidateTokenResponseHeader {
        /**
         * 1.0
         */
        private String version;
        /**
         * 对应请求消息中的msgid
         */
        private String inresponseto;
        /**
         * 响应消息发送的系统时间
         */
        private String systemtime;
        /**
         * 返回码
         */
        private String resultcode;
        /**
         * 返回描述
         */
        private String resultdesc;
    }

    @Data
    public static final class ValidateTokenResponseBody {
        /**
         * 基于uid的token校验成功后返回uid
         */
        private String usessionid;
        /**
         * 手机号掩码
         * <p>example: 123****4567</p>
         */
        private String msisdnmask;
        /**
         * 用户统一账号的系统标识
         */
        private String passid;
        /**
         * 手机号码，经过AES加密
         */
        private String msisdn;
        /**
         * 邮箱地址
         */
        private String email;
        /**
         * 登录类型
         * <li>0-手机号码</li>
         * <li>1-邮箱</li>
         */
        private String loginidtype;
        /**
         * 省份代码
         */
        private String province;
        /**
         * 认证方式
         */
        private String authtype;
        /**
         * 认证时间
         */
        private String authtime;
        /**
         * 用户来源ip
         */
        private String userip;
        /**
         * 昵称
         */
        private String nickname;
        /**
         * 响应扩展参数的，多个参数以"|"分割
         * <p>example: key1=value1|key2=value2</p>
         */
        private String exresparams;
    }
}
