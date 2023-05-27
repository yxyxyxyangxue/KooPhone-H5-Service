package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject;

import lombok.Data;

/**
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
public class AddOrderResponseDO {
    /**
     * 0表示成功；
     * 其他表示失败
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
     * true 成功
     */
    private Boolean success;

    private RpcAddOrderResp data;

    @Data
    public static class RpcAddOrderResp {
        /**
         * 活动id
         */
        private Long activityId;
        /**
         * 来源顶点ID
         */
        private String sourceOrderNo;
        /**
         * 手机号
         */
        private String telephone;
    }
}
