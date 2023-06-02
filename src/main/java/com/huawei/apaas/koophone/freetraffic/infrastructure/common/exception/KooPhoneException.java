package com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception;

/**
 * 业务通用异常
 * @author zhangjihong
 * @since 2023-05-23
 */
public class KooPhoneException extends RuntimeException {

    private static final long serialVersionUID = -5504463600549055547L;
    private final String errorCode;
    public KooPhoneException(ErrorCode errorCode) {
        super(errorCode.getErrDesc());
        this.errorCode = errorCode.getErrCode();
    }
    public KooPhoneException(ErrorCode errorCode, Throwable e) {
        super(errorCode.getErrDesc(), e);
        this.errorCode = errorCode.getErrCode();
    }

    public KooPhoneException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
