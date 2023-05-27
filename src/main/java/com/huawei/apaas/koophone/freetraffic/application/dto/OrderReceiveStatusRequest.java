package com.huawei.apaas.koophone.freetraffic.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 请求获取当前用户领取状态request
 * @author zhangjihong
 * @since 2023-05-27
 */
@Data
@ApiModel(value = "OrderReceiveStatusRequest", description = "请求获取当前用户领取状态request")
public class OrderReceiveStatusRequest {
    /**
     * 手机号
     */
    @NotBlank
    @ApiModelProperty(value = "手机号", required = true)
    private String telephone;
}
