package com.huawei.apaas.koophone.freetraffic.domain.gateway;

import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.AddOrderDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.AddOrderResponseDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.QueryOrderResponseDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.QueryOrderResultDO;

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
     * 下单结果查询
     * @param queryOrderResultDO
     * @return
     */
    QueryOrderResponseDO queryOrderResult(QueryOrderResultDO queryOrderResultDO);

    /**
     * 根据手机号查询订单
     * @param telephone
     * @return
     */
    Optional<OrderDO> queryOrderByTelephone(String telephone);
}
