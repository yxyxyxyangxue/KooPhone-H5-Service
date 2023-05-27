package com.huawei.apaas.koophone.freetraffic.application.service.wrapper;

import com.huawei.apaas.koophone.freetraffic.application.dto.AddOrderRequest;
import com.huawei.apaas.koophone.freetraffic.application.dto.AddOrderResponseDTO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.AddOrderDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.AddOrderResponseDO;

/**
 *
 * @author zhangjihong
 * @since 2023-05-23
 */
public class AddOrderWrapper {
    private AddOrderWrapper() { }

    public static AddOrderDO request2Do(AddOrderRequest addOrderRequest) {
        return new AddOrderDO();
    }

    public static AddOrderResponseDTO do2Dto(AddOrderResponseDO addOrderResponseDO) {
        AddOrderResponseDO.RpcAddOrderResp addOrderResp = addOrderResponseDO.getData();
        return new AddOrderResponseDTO(addOrderResp.getSourceOrderNo(), addOrderResp.getTelephone());
    }
}
