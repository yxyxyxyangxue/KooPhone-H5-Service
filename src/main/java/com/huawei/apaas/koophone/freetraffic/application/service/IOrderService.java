package com.huawei.apaas.koophone.freetraffic.application.service;

import com.huawei.apaas.koophone.freetraffic.application.dto.*;

/**
 * 履约服务
 * @author zhangjihong
 * @since 2023-05-23
 */
public interface IOrderService {
    /**
     * 查询履约结果
     * <p>查询到结果会入库</p>
     * @param queryOrderResultRequest
     * @return
     */
    QueryOrderResponseDTO queryOrderResult(QueryOrderResultRequest queryOrderResultRequest);

    /**
     * 下单
     * @param addOrderRequest
     * @return
     */
    AddOrderResponseDTO addOrder(AddOrderRequest addOrderRequest);

    /**
     * 下单结果回调接口（入库）
     * @param orderResultCallbackRequest
     */
    void addOrderCallback(OrderResultCallbackRequest orderResultCallbackRequest);

    /**
     * 领取状态查询
     * @param request
     * @return
     */
    OrderReceiveStatusResponseDTO receiveStatus(OrderReceiveStatusRequest request);
}
