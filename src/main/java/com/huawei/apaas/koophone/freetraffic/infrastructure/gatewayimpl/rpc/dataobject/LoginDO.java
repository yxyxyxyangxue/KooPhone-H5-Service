package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject;

import lombok.Data;

/**
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
public class LoginDO {
    private LoginHeader header;
    private LoginBody body;
    @Data
    public static class LoginHeader {
        /**
         * 唯一请求标识id
         */
        private String msgid;
        /**
         * 请求发送系统时间，17位
         * <p>20121227180001165</p>
         */
        private String systemtime;
        /**
         * 版本，1.0
         */
        private String version;
        /**
         * 申请接入ID
         */
        private String sourceid;
        /**
         * 签名
         */
        private String mac;
        /**
         * <p>1 | BOSS</p>
         * <p>2 | web</p>
         * <p>3 | wap</p>
         * <p>4 | pc端</p>
         * <p>5 | 手机端</p>
         */
        private String apptype;
        /**
         * 用户公网IP
         */
        private String userip;
    }

    @Data
    public static class LoginBody {
        /**
         * 用户输入的id，手机号或者邮箱或者“和id”
         * <p>maxLength = 64</p>
         */
        private String loginid;
        /**
         * 登录名类型
         * <p>1: 手机</p>
         * <p>2: 邮箱</p>
         * <p>3: 和通行证</p>
         * <p>maxLength = 8</p>
         */
        private String loginidtype;
        /**
         * 认证方式
         * <p>DUP: 短信验证码</p>
         * <p>maxLength = 8</p>
         */
        private String authtype;
        /**
         * 密码（短信验证码），短信验证码时不需要加密
         * <p>maxLength = 16</p>
         */
        private String password;

        /**
         * 业务扩展参数，多个参数使用 | 分隔，每个参数长度未3字节
         * <p>例如：300|301</p>
         * <p>300: SDK签发的token和临时凭证校验成功后返回uid</p>
         * <p>601: 用于获取推动标识pushId</p>
         */
        private String expandparams;
    }
}
