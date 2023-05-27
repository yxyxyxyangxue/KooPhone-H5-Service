package com.huawei.apaas.koophone.freetraffic.application.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 领取response dto
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AddOrderResponseDTO", description = "领取response dto")
public class AddOrderResponseDTO {
    /**
     * 来源订单ID
     */
    private String sourceOrderNo;
    /**
     * 手机号
     */
    private String telephone;
}
