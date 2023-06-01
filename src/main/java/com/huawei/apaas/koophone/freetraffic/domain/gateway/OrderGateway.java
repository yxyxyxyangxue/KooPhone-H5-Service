package com.huawei.apaas.koophone.freetraffic.domain.gateway;

import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.*;

import java.util.Optional;

/**
 * 履约接口
 * @author zhangjihong
 * @since 2023-05-23
 */
public interface OrderGateway {
    /**
     * 下单
     * @param addOrderDO
     * @return
     */
    AddOrderResponseDO addOrder(AddOrderDO addOrderDO);

    /**
     * 根据手机号查询订单
     * @param orderDO
     * @return
     */
    Optional<OrderDO> queryOrder(OrderDO orderDO);

    /**
     * 保存orderDO
     * @param orderDO
     * @return
     */
    OrderDO save(OrderDO orderDO);
}
