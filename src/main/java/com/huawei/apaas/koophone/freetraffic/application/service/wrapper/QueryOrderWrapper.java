package com.huawei.apaas.koophone.freetraffic.application.service.wrapper;

import com.huawei.apaas.koophone.freetraffic.application.dto.QueryOrderResponseDTO;
import com.huawei.apaas.koophone.freetraffic.application.dto.QueryOrderResultRequest;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.SystemConstant;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.QueryOrderResponseDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.QueryOrderResultDO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhangjihong
 * @since 2023-05-23
 */
public class QueryOrderWrapper {
    private QueryOrderWrapper() { }

    public static QueryOrderResultDO request2Do(QueryOrderResultRequest queryOrderResultRequest) {
        QueryOrderResultDO queryOrderResultDO = new QueryOrderResultDO();
        queryOrderResultDO.setTelephone(queryOrderResultDO.getTelephone());
        queryOrderResultDO.setSourceOrderNo(queryOrderResultDO.getSourceOrderNo());
        queryOrderResultDO.setSourceGoodsIds(queryOrderResultDO.getSourceGoodsIds());
        return queryOrderResultDO;
    }

    public static QueryOrderResponseDTO do2Dto(QueryOrderResponseDO queryOrderResponseDO) {
        QueryOrderResponseDTO dto = new QueryOrderResponseDTO();
        QueryOrderResponseDO.RpcFullfillmentResultV2Resp data = queryOrderResponseDO.getData();
        dto.setSourceOrderNo(data.getSourceOrderNo());
        dto.setTelephone(data.getTelephone());
        List<QueryOrderResponseDO.OrderRespDO> orderList = data.getOrderList();
        if (!CollectionUtils.isEmpty(orderList)) {
            QueryOrderResponseDO.OrderRespDO orderRespDO = orderList.get(0);
            dto.setOrderNo(orderRespDO.getOrderNo());
            dto.setBizCode(orderRespDO.getBizCode());
            dto.setBizDesc(orderRespDO.getBizDesc());
            dto.setStatus(orderRespDO.getStatus());
            dto.setSkuCode(orderRespDO.getSkuCode());
            dto.setSkuName(orderRespDO.getSkuName());
        }
        return dto;
    }

    public static OrderDO responseDO2OrderDO(QueryOrderResponseDO queryOrderResponseDO) {
        OrderDO orderDO = new OrderDO();
        QueryOrderResponseDO.RpcFullfillmentResultV2Resp resp = queryOrderResponseDO.getData();
        orderDO.setTelephone(resp.getTelephone());
        orderDO.setSourceOrderNo(resp.getSourceOrderNo());
        List<QueryOrderResponseDO.OrderRespDO> respDOS = resp.getOrderList();
        if (!CollectionUtils.isEmpty(respDOS)) {
            QueryOrderResponseDO.OrderRespDO orderRespDO = respDOS.get(0);

            orderDO.setOrderNo(orderRespDO.getOrderNo());
            orderDO.setStatus(orderRespDO.getStatus());
            orderDO.setIndex(orderRespDO.getIndex());
            orderDO.setSkuCode(orderRespDO.getSkuCode());
            orderDO.setSkuName(orderRespDO.getSkuName());
            orderDO.setBizCode(orderRespDO.getBizCode());
            orderDO.setBizDesc(orderRespDO.getBizDesc());
            orderDO.setActualExecuteTime(orderRespDO.getActualExecuteTime());
            orderDO.setExceptedExecuteTime(orderRespDO.getExceptedExecuteTime());
        }
        return orderDO;
    }
}
