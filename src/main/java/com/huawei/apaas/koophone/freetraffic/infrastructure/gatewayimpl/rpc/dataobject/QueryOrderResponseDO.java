package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject;

import com.huawei.apaas.koophone.freetraffic.application.dto.QueryOrderResponseDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
public class QueryOrderResponseDO {
    /**
     * 返回码
     * <p>0表示成功，其他表示失败</p>
     */
    private String code;
    /**
     * 返回码描述
     */
    private String message;
    /**
     * 内部错误描述
     */
    private String messageInternal;
    /**
     * 成功 true
     */
    private Boolean success;

    private RpcFullfillmentResultV2Resp data;

    @Data
    public static class RpcFullfillmentResultV2Resp {
        /**
         * 活动ID
         */
        private Long activityId;
        /**
         * 电话
         */
        private String telephone;
        /**
         * 来源订单ID
         */
        private String sourceOrderNo;
        /**
         * 履约详情
         */
        private List<OrderRespDO> orderList;
    }

    @Data
    public static class OrderRespDO {
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
         * <li>60-执行成功</li>
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
         * 业务描述
         */
        private String bizDesc;
    }
}
