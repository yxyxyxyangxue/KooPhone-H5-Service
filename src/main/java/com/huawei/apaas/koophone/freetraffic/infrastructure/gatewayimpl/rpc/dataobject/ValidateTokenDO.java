package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject;

import lombok.Data;

/**
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
public class ValidateTokenDO {
    private ValidateTokenHeader header;
    private ValidateTokenBody body;

    @Data
    public static class ValidateTokenHeader {
        /**
         * 1.0
         */
        private String version;
        /**
         * 使用uuid标识请求的唯一性
         */
        private String msgid;
        /**
         * 请求消息发送的系统时间，精确到毫秒，共17位
         * <p>example: 20230525180001165</p>
         */
        private String systemtime;
        /**
         * 业务或应用集成移动认证的标识
         */
        private String id;
        /**
         * id类型：0
         */
        private String idtype;
        /**
         * <p>1 | BOSS</p>
         * <p>2 | web</p>
         * <p>3 | wap</p>
         * <p>4 | pc端</p>
         * <p>5 | 手机端</p>
         */
        private String apptype;
        /**
         * 客户端来源ip
         */
        private String userip;
        /**
         * 接入方预留参数，会透传给通知接口，需要urlencode编码
         */
        private String message;
        /**
         * 业务扩展参数，多个以 "|" 分割
         * <p>300: sdk签发的token和临时凭证校验成功后返回uid，没传也要留位置</p>
         * <p>601: 标识用于回去推送标识pushId</p>
         */
        private String expandparams;
        /**
         * 签名MD5(apptype+id+idtype+key+msgid+systemtime+token+version)
         * <p>输出32位</p>
         */
        private String sign;
    }
    @Data
    public static class ValidateTokenBody {
        /**
         * 需要解析的凭证值
         * <p>maxLength < 500</p>
         */
        private String token;
        /**
         * 加密的浏览器指纹，智能认证jssdk token校验时必填
         */
        private String userInformation;
    }
}
