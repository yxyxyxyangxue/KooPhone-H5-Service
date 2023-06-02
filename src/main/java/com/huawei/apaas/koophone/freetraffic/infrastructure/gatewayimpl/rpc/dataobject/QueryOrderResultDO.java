package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject;

import lombok.Data;

/**
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
public class QueryOrderResultDO {
    /**
     * 来源订单ID
     */
    private String sourceOrderNo;
    /**
     * 手机号
     */
    private String telephone;
    /**
     * 来源goodsId
     */
    private String sourceGoodsIds;
}
