package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject;

import lombok.Data;

/**
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
public class AddOrderDO {
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 来源订单ID
     */
    private String sourceOrderNo;
    /**
     * 来源系统，平台提供
     */
    private String sourceApp;
    /**
     * 手机号
     */
    private String telephone;
    /**
     * 来源goodsId，平台提供
     */
    private String sourceGoodsId;
    /**
     * 三方账号扩展信息
     */
    private ThirdpartyAccountExt thirdpartyAccountExt;

    @Data
    static class ThirdpartyAccountExt {
        /**
         * 三方账号类型（0: QQ号，1: 抖音号）
         */
        private String thirdpartyAccountType;
        /**
         * 三方账号ID
         */
        private String thirdpartyAccountId;
    }
}
