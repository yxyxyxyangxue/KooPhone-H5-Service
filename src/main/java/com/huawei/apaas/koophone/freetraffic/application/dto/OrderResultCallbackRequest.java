package com.huawei.apaas.koophone.freetraffic.application.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 领取回调回调request
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
@ApiModel(value = "OrderResultCallbackRequest", description = "领取结果回调request")
public class OrderResultCallbackRequest {
    /**
     * 订单号
     */
    @NotNull
    private Long orderNo;
    /**
     * 活动ID
     */
    @NotNull
    private Long activityId;
    /**
     * 手机号
     */
    @NotBlank
    private String telephone;
    /**
     * 来源订单ID
     */
    @NotBlank
    private String sourceOrderNo;
    /**
     * 履约次数
     */
    @NotNull
    private Integer index;
    /**
     * 商品编码
     */
    @NotBlank
    private String skuCode;
    /**
     *  商品名称
     */
    @NotBlank
    private String skuName;

    /**
     * 订单状态
     * <p>50-成功</p>
     * <p>60-失败</p>
     */
    @NotNull
    private Integer status;
    /**
     * 期望执行时间
     */
    @NotNull
    private LocalDateTime expectedExecuteTime;
    /**
     * 实际执行时间
     */
    @NotNull
    private LocalDateTime actualExecuteTime;
    /**
     * 业务编码
     * <p>0-成功，status为50时为0，不是50时非0</p>
     * <p>101-履约失败</p>
     * <p>……</p>
     */
    @NotBlank
    private String bizCode;
    /**
     * 业务码描述
     */
    @NotBlank
    private String bizDesc;
}
