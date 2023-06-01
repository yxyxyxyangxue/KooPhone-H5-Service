package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl;

import com.huawei.apaas.koophone.freetraffic.domain.gateway.OrderGateway;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.OrderRepository;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.CMCCOrderMapper;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

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
    public Optional<OrderDO> queryOrder(OrderDO orderDO) {
        return orderRepository.findOne(Example.of(orderDO));
    }

    @Override
    public OrderDO save(OrderDO orderDO) {
        return orderRepository.save(orderDO);
    }
}
