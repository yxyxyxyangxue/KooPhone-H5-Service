package com.huawei.apaas.koophone.freetraffic.application.service.wrapper;

import com.huawei.apaas.koophone.freetraffic.application.dto.SyncFlowPkgOrderReq;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;

public class OrderStatusCallbackWrapper {
    private OrderStatusCallbackWrapper() { }

    public static OrderDO request2DO(SyncFlowPkgOrderReq req) {
        OrderDO orderDO = new OrderDO();
        orderDO.setUserPseudoCode(req.getUserPseudoCode());
        orderDO.setOrderId(req.getOrderId());
        orderDO.setChannelSeqId(req.getChannelSeqId());
        orderDO.setPrice(req.getPrice());
        orderDO.setActionTime(req.getActionTime());
        orderDO.setActionId(req.getActionID());
        orderDO.setEffectiveTime(req.getEffectiveTime());
        orderDO.setExpireTime(req.getExpireTime());
        orderDO.setEffectiveRealTime(req.getEffectiveRealTime());
        orderDO.setExpireRealTime(req.getExpireRealTime());
        orderDO.setPayType(req.getPayType());
        orderDO.setChannelId(req.getChannelId());
        orderDO.setProductId(req.getProductId());
        orderDO.setOrderType(req.getOrderType());
        orderDO.setReturnStatus(req.getReturnStatus());
        orderDO.setProvCode(req.getProvCode());
        orderDO.setOrderTime(req.getOrderTime());
        orderDO.setAppId(req.getAppId());
        return orderDO;
    }
}
