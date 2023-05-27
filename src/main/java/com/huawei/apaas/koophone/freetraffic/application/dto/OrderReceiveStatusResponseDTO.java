package com.huawei.apaas.koophone.freetraffic.application.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求获取当前用户领取状态request
 * @author zhangjihong
 * @since 2023-05-27
 */
@Data
@ApiModel(value = "OrderReceiveStatusRequest", description = "当前用户领取状态response dto")
@AllArgsConstructor
@NoArgsConstructor
public class OrderReceiveStatusResponseDTO {
    /**
     * 领取状态
     */
    @ApiModelProperty(value = "true-已领取")
    private Boolean status;
}
