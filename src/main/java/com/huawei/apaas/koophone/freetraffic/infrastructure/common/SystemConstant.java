package com.huawei.apaas.koophone.freetraffic.infrastructure.common;

import java.time.format.DateTimeFormatter;

/**
 * 系统常量
 * @author zhangjihong
 * @since 2023-05-23
 */
public class SystemConstant {
    /**
     * CMCC认证平台版本
     */
    public static final String VERSION = "1.0";
    /**
     * token校验接口 id类型
     */
    public static final String ID_TYPE = "0";
    /**
     * 用户信息获取接口结果码：成功
     */
    public static final String GET_USERINFO_RESULT_CODE_OK = "0";
    /**
     * 校验token扩展参数
     */
    public static final String VALID_TOKEN_EXPANDPARAMS = "300";
    /**
     * restTemplate超时时间
     */
    public static final int HTTP_TIME_OUT = 3000;
    /**
     * restTemplate超时时间
     */
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
     * 请求CMCC http 请求错误码前缀
     */
    public static final String CMCC_HTTP_FAILURE = "CMCC_HTTP_FAILURE_";

    /**
     * 下单状态-成功
     */
    public static final int ORDER_STATUS_OK = 50;
    /**
     * 下单失败，code
     */
    public static final String ORDER_RESULT_FAILURE = "10";

    public static final String CMCC_XMLNS = "http://www.monternet.com/dsmp/schemas/";
    /**
     * 订单状态回调接口 resp 类型
     */
    public static final String ORDER_STATUS_CALLBACK_RESP_TYPE = "SyncFlowPkgOrderResp";
    /**
     * 订单状态回调接口 req 类型
     */
    public static final String ORDER_STATUS_CALLBACK_REQ_TYPE = "SyncFlowPkgOrderReq";
    /**
     * 回调接口处理成功
     */
    public static final int ORDER_STATUS_CALLBACK_OK = 0;
    /**
     * 回调接口版本
     */
    public static final String ORDER_STATUS_CALLBACK_VERSION = "1.1.0";
    /**
     * actionId 订购
     */
    public static final int ORDER_ACTION_PLACE_ORDER = 1;
    /**
     * actionId 退订
     */
    public static final int ORDER_ACTION_CANCEL_ORDER = 2;

    /**
     * 订单状态：成功
     */
    public static final int ORDER_RETURN_STATUS = 0;
    /**
     * 订单状态，到期自动关闭
     */
    public static final int ORDER_PAYTYPE_EXPIRE_CLOSE = 2;

    public final static DateTimeFormatter DATEFORMAT_DATE = DateTimeFormatter.ofPattern("yyyyMMdd");
    /**
     * 查询用户信息接口：1-移动
     */
    public static final String OPEN_TYPE = "1";
}
