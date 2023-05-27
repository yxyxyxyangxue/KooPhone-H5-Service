package com.huawei.apaas.koophone.freetraffic.application.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 下单结果查询request
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
@ApiModel(value = "QueryOrderResultRequest", description = "查询履约结果request")
public class QueryOrderResultRequest {
    /**
     * 来源订单ID
     */
    private String sourceOrderNo;
    /**
     * 手机号
     */
    @NotBlank
    private String telephone;
    /**
     * 来源goodsId
     */
    private String sourceGoodsIds;
}
