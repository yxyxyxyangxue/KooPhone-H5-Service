package com.huawei.apaas.koophone.freetraffic.infrastructure.common;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 下单状态-成功
     */
    public static final int ORDER_STATUS_OK = 50;
    /**
     * 下单失败，code
     */
    public static final String ORDER_RESULT_FAILURE = "10";

    // =========================== 下单结果查询 start ==============================
    /**
     * 成功
     */
    public static final Map<String, String> ADD_ORDER_RESULT_OK = new HashMap<String, String>() {{
        put("0", "下单成功");
    }};
    /**
     * 失败重试
     */
    public static final Map<String, String> ADD_ORDER_RESULT_FAILURE = new HashMap<String, String>() {{
        put("101", "履约失败");
        // 下单失败
        put("1703100000", "下单失败");
        // 订单有效性校验失败
        put("1702108000", "订单有效性校验失败");
        // 库存校验：库存扣减失败
        put("1702200000", "库存校验：库存扣减失败");
    }};
    /**
     * 无法领取
     */
    public static final Map<String, String> ADD_ORDER_RESULT_UNABLE = new HashMap<String, String>() {{
        // 下单失败，分省割接
        put("1703100001", "下单失败，分省割接");
        // 归属地校验失败
        put("1702100000", "归属地校验失败");
        // 入网渠道校验失败
        put("1702102000", "入网渠道校验失败");
        put("1702104000", "激活时间校验失败");
        put("1702105000", "充值金额校验失败");
        put("1702106000", "IOP在网使用状态校验失败");
        put("1702108000", "充值请求方渠道编码校验失败");
    }};
    /**
     * 重复下单
     */
    public static final Map<String, String> ADD_ORDER_RESULT_DUPLICATE = new HashMap<String, String>() {{
        // 订单数据重复，sourceOrderNo不唯一
        put("1702480001", "订单数据重复，sourceOrderNo不唯一");
    }};
    /**
     * 活动已结束
     */
    public static final Map<String, String> ADD_ORDER_RESULT_ENDED = new HashMap<String, String>() {{
        put("1702201000", "互斥校验：不支持的促销关系");
    }};

    /**
     * 结果pending
     */
    public static final Map<Integer, String> ADD_ORDER_RESULT_PENDING = new HashMap<Integer, String>() {{
        put(10, "草稿");
        put(30, "待执行");
        put(35, "审核中");
        put(40, "执行中");
        put(70, "待重新执行");
    }};
    // =========================== 下单结果查询 end ==============================
}
