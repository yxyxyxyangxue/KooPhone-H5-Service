package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl;

import com.huawei.apaas.koophone.freetraffic.application.service.wrapper.QueryOrderWrapper;
import com.huawei.apaas.koophone.freetraffic.domain.gateway.OrderGateway;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.SystemConstant;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.OrderRepository;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.CMCCOrderMapper;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.AddOrderDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.AddOrderResponseDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.QueryOrderResponseDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.QueryOrderResultDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * 履约服务
 * @author zhangjihong
 * @since 2023-05-23
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class OrderGatewayImpl implements OrderGateway {
    private final CMCCOrderMapper cmccOrderMapper;
    private final OrderRepository orderRepository;

    @Override
    public AddOrderResponseDO addOrder(AddOrderDO addOrderDO) {
        return cmccOrderMapper.addOrder(addOrderDO);
    }

    @Override
    public QueryOrderResponseDO queryOrderResult(QueryOrderResultDO queryOrderResultDO) {
        return cmccOrderMapper.queryOrderResult(queryOrderResultDO);
    }

    @Override
    public Optional<OrderDO> queryOrderByTelephone(String telephone) {
        OrderDO orderDO = new OrderDO();
        orderDO.setTelephone(telephone);
        return orderRepository.findOne(Example.of(orderDO));
    }
}
