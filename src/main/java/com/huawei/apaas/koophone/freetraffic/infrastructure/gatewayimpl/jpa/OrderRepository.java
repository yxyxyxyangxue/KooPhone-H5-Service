package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa;

import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 订单 repository
 * @author zhangjihong
 * @since 2023-05-23
 */
public interface OrderRepository
        extends JpaRepository<OrderDO, Long>, JpaSpecificationExecutor<OrderDO> {
}
