package com.huawei.apaas.koophone.freetraffic.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * token校验request
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
@ApiModel(value = "ValidateTokenRequest", description = "token校验request")
public class ValidateTokenRequest {
    /**
     * app类型
     */
    @ApiModelProperty(value = "app类型")
    private String appType;
    /**
     *
     */
    @NotBlank
    @ApiModelProperty(value = "token", required = true)
    private String token;
    /**
     * jssdk token校验时，必填
     */
    @ApiModelProperty(value = "userInformation", notes = "jssdk token校验时，必填")
    private String userInformation;
}
