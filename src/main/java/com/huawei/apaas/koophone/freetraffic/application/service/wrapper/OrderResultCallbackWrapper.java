package com.huawei.apaas.koophone.freetraffic.application.service.wrapper;

import com.huawei.apaas.koophone.freetraffic.application.dto.OrderResultCallbackRequest;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author zhangjihong
 * @since 2023-05-23
 */
public class OrderResultCallbackWrapper {
    private OrderResultCallbackWrapper() { }

    public static OrderDO requestDo(OrderResultCallbackRequest orderResultCallbackRequest) {
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderResultCallbackRequest, orderDO);
        return orderDO;
    }
}
