package com.huawei.apaas.koophone.freetraffic.infrastructure.common;

/**
 * 系统常量
 * @author zhangjihong
 * @since 2023-05-23
 */
public class SystemConstant {
    public static final String HMAC_ALGORITHM = "HmacSHA256";
    public static final String CIPHER_KEY = "AES/ECB/PKCS5Padding";
    public static final String AES_ALGORITHM = "AES";
    public static final String RSA_ALGORITHM = "RSA";
    /**
     * CMCC认证平台版本
     */
    public static final String VERSION = "1.0";
    /**
     *
     */
    public static final String ID_TYPE = "0";
    /**
     * 校验token扩展参数
     */
    public static final String VALID_TOKEN_EXPANDPARAMS = "300";

    public static final int HTTP_TIME_OUT = 3000;
    public static final int HTTP_READ_TIMEOUT_OUT = 6000;
    /**
     * token校验成功
     */

    public static final String VALIDATE_TOKEN_OK = "103000";
    /**
     * 发送验证码成功
     */
    public static final String SEND_SMS_CODE_OK = "104000";
    /**
     * 登录成功
     */
    public static final String LOGIN_OK = "104000";
    /**
     * 下单成功
     */
    public static final String ADD_ORDER_OK = "0";
    /**
     * 下单幂等校验失败
     */
    public static final String ADD_ORDER_IDEMPOTENT_INVALID = "1000";
    /**
     * 订单履约结果 完成
     */
    public static final String ORDER_RESULT_COMPLETE = "0";
    /**
     * 请求CMCC http 请求错误码前缀
     */
    public static final String CMCC_HTTP_FAILURE = "CMCC_HTTP_FAILURE_";

    public static final int ORDER_STATUS_OK = 50;
}
