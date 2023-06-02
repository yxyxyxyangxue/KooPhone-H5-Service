package com.huawei.apaas.koophone.freetraffic.application.service;

import com.huawei.apaas.koophone.freetraffic.application.dto.*;

/**
 * 履约服务
 * @author zhangjihong
 * @since 2023-05-23
 */
public interface IOrderService {

    /**
     * 下单
     * @param addOrderRequest
     * @return
     */
    AddOrderResponseDTO addOrder(AddOrderRequest addOrderRequest);

    /**
     * 订购状态查询
     * @param request
     * @return
     */
    OrderStatusResponseDTO orderStatus(OrderStatusRequest request);

    /**
     * 回调保存订购状态信息
     * @param syncFlowPkgOrderReq
     */
    void saveOrder(SyncFlowPkgOrderReq syncFlowPkgOrderReq);
}
