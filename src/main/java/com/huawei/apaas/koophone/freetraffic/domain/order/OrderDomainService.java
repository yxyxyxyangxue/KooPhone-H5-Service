package com.huawei.apaas.koophone.freetraffic.domain.order;

import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.OrderRepository;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 履约领域对象
 * @author zhangjihong
 * @since 2023-05-23
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderDomainService {
    private final OrderRepository orderRepository;

    /**
     * 先查询当前手机号是否已存在领取记录，如果存在则进行更新操作
     * @param orderDO
     * @return
     */
    public OrderDO saveOrder(OrderDO orderDO) {
        OrderDO req = new OrderDO();
        req.setTelephone(orderDO.getTelephone());
        Optional<OrderDO> orderOpt = orderRepository.findOne(Example.of(req));
        if (orderOpt.isPresent()) {
            orderOpt.ifPresent(o -> orderDO.setId(o.getId()));
        }
        return orderRepository.save(orderDO);
    }
}
