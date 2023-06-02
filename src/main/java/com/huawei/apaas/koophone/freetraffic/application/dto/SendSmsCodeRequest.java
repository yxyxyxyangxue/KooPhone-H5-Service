package com.huawei.apaas.koophone.freetraffic.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 验证码发送request
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
@ApiModel(value = "SendSmsCodeRequest", description = "请求发送短信验证码request")
public class SendSmsCodeRequest {
    /**
     * app类型
     */
    private String apptype;
    /**
     * 手机号
     */
    @NotBlank
    @ApiModelProperty(value = "手机号", required = true)
    private String msisdn;
}
