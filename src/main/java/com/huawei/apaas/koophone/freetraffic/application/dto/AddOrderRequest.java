package com.huawei.apaas.koophone.freetraffic.application.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 领取request
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
@ApiModel(value = "AddOrderRequest", description = "领取request")
public class AddOrderRequest {
    @NotBlank
    @Size(min = 3, max = 32)
    private String telephone;
}
