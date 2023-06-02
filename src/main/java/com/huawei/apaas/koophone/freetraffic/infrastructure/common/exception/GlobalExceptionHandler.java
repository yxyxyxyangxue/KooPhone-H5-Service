package com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception;

import com.huawei.apaas.koophone.freetraffic.application.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

/**
 * 全局异常处理
 * @author zhangjihong
 * @since 2023-05-27
 */
@RestControllerAdvice
@ConditionalOnWebApplication
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = KooPhoneException.class)
    public Response handleBusinessException(KooPhoneException e) {
        log.error(e.getMessage(), e);
        return Response.buildFailure(e.getErrorCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response handleBusinessException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return Response.buildFailure(ErrorCode.ILLEGAL_ARGUMENT.getErrCode(), ErrorCode.ILLEGAL_ARGUMENT.getErrDesc());
    }

    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Response handleBusinessException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return Response.buildFailure(ErrorCode.ILLEGAL_ARGUMENT.getErrCode(), ErrorCode.ILLEGAL_ARGUMENT.getErrDesc());
    }



    @ResponseBody
    @ExceptionHandler(value = RestClientException.class)
    public Response handleBusinessException(RestClientException e) {
        log.error(e.getMessage(), e);
        return Response.buildFailure(ErrorCode.CMCC_REST_CLIENT_FAILURE.getErrCode(),
                ErrorCode.CMCC_REST_CLIENT_FAILURE.getErrDesc());
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public Response handleBusinessException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return Response.buildFailure(ErrorCode.SYSTEM_EXCEPTION.getErrCode(),
                ErrorCode.SYSTEM_EXCEPTION.getErrDesc());
    }

    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public Response handleBusinessException(Throwable e) {
        log.error(e.getMessage(), e);
        return Response.buildFailure(ErrorCode.UNKNOWN_ERROR.getErrCode(), ErrorCode.UNKNOWN_ERROR.getErrDesc());
    }
}
