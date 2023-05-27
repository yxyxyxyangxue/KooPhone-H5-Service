package com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception;

/**
 * 错误码
 * @author zhangjihong
 * @since 2023-05-23
 */
public enum ErrorCode {
    /**
     * 系统异常
     */
    SYSTEM_EXCEPTION("kp.freetraffic.0000", "System Exception"),
    /**
     * 无效的token
     */
    VALIDATE_TOKEN_FAILURE("kp.freetraffic.0001", "Invalid Token"),
    /**
     * 发送短信正码失败
     */
    SEND_SMS_CODE_FAILURE("kp.freetraffic.0002", "Sending SMS Failure"),
    /**
     * 登录失败
     */
    LOGIN_FAILURE("kp.freetraffic.0003", "Login Failure"),
    /**
     * 下单失败
     */
    ADD_ORDER_FAILURE("kp.freetraffic.0004", "Add Order Failure"),
    /**
     * 下单结果未返回
     */
    ORDER_RESULT_PENDING("kp.freetraffic.0005", "Order Result Pending"),
    /**
     * 未知异常
     */
    UNKNOWN_ERROR("kp.freetraffic.9999", "Unknown Error");

    private final String errCode;
    private final String errMsg;

    private ErrorCode(String errCode, String errDesc) {
        this.errCode = errCode;
        this.errMsg = errDesc;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrDesc() {
        return errMsg;
    }

    public static ErrorCode statOf(String ecode) {
        for (ErrorCode errorCode : values()){
            if (errorCode.getErrCode().equals(ecode))
                return errorCode;
        }
        return null;
    }
}
