package com.huawei.apaas.koophone.freetraffic.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 签名接口request
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
@ApiModel(value = "SignRequest", description = "签名request")
public class SignRequest {
    /**
     * 原始签名
     */
    @NotBlank
    @ApiModelProperty(value = "原始签名", required = true)
    private String originSignature;
}
