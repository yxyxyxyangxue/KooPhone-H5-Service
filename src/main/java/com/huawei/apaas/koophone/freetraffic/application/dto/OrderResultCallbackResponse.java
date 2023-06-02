package com.huawei.apaas.koophone.freetraffic.application.dto;

import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.ErrorCode;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 领取回调response
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
@ApiModel(value = "OrderResultCallbackResponse", description = "领取结果回调response")
public class OrderResultCallbackResponse {
    private String code;
    private String message;
    private String messageInternal;
    private Boolean success;
    private OrderResultCallbackDTO data;

    @Data
    static class OrderResultCallbackDTO {

    }
    private OrderResultCallbackResponse() {
        this.success = true;
        this.code = "0";
        this.message = "成功";
        this.messageInternal = null;
        this.data = null;
    }

    private OrderResultCallbackResponse(ErrorCode errorCode) {
        this.success = false;
        this.code = errorCode.getErrCode();
        this.message = errorCode.getErrDesc();
        this.messageInternal = errorCode.getErrDesc();
        this.data = null;
    }

    public static OrderResultCallbackResponse ofOk() {
        return new OrderResultCallbackResponse();
    }
    public static OrderResultCallbackResponse ofFail(ErrorCode errorCode) {
        return new OrderResultCallbackResponse(errorCode);
    }
}
