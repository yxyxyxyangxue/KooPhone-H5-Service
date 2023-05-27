package com.huawei.apaas.koophone.freetraffic.application.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 履约结果查询response dto
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
@ApiModel(value = "QueryOrderResponseDTO", description = "查询履约结果response dto")
public class QueryOrderResponseDTO {
    /**
     * 收手机号
     */
    private String telephone;
    /**
     * 来源订单ID
     */
    private String sourceOrderNo;
    // 履约详情
    /**
     * 订单号
     */
    private Long orderNo;
    /**
     * 履约次数
     */
    private Integer index;
    /**
     * 商品编码
     */
    private String skuCode;
    /**
     * 商品名称
     */
    private String skuName;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 期望执行时间
     */
    private LocalDateTime exceptedExecuteTime;
    /**
     * 实际执行时间
     */
    private LocalDateTime actualExecuteTime;
    /**
     * 业务码
     */
    private String bizCode;
    /**
     * 业务码描述
     */
    private String bizDesc;
}
