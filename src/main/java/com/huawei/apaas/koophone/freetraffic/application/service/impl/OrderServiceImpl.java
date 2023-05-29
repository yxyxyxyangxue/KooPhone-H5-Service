package com.huawei.apaas.koophone.freetraffic.application.service.impl;

import com.huawei.apaas.koophone.freetraffic.application.dto.*;
import com.huawei.apaas.koophone.freetraffic.application.service.IOrderService;
import com.huawei.apaas.koophone.freetraffic.application.service.wrapper.AddOrderWrapper;
import com.huawei.apaas.koophone.freetraffic.application.service.wrapper.OrderResultCallbackWrapper;
import com.huawei.apaas.koophone.freetraffic.application.service.wrapper.QueryOrderWrapper;
import com.huawei.apaas.koophone.freetraffic.domain.gateway.OrderGateway;
import com.huawei.apaas.koophone.freetraffic.domain.order.OrderDomainService;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.SystemConstant;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.ErrorCode;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.KooPhoneException;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.UuidUtils;
import com.huawei.apaas.koophone.freetraffic.infrastructure.config.FreeTrafficProperties;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.AddOrderDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.AddOrderResponseDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.QueryOrderResponseDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.QueryOrderResultDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * 履约服务
 * @author zhangjihong
 * @since 2023-05-23
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final OrderDomainService orderDomainService;
    private final OrderGateway orderGateway;
    private final FreeTrafficProperties freeTrafficProperties;

    // TODO
    // 当前按照code为0表示成功
    // 170248001是bizCode的取值
    @Override
    public QueryOrderResponseDTO queryOrderResult(QueryOrderResultRequest queryOrderResultRequest) {
        // 1. 构建请求DO
        QueryOrderResultDO queryOrderResultDO = QueryOrderWrapper.request2Do(queryOrderResultRequest);
        queryOrderResultDO.setSourceGoodsIds(freeTrafficProperties.getSourceGoodsId());
        // 2. 请求
        QueryOrderResponseDO queryOrderResponseDO = orderGateway.queryOrderResult(queryOrderResultDO);
        // 3. 构建response
        if (queryOrderResponseDO.getSuccess()) {
            if (queryOrderResponseDO.getData() != null
                    && !CollectionUtils.isEmpty(queryOrderResponseDO.getData().getOrderList())) {
                // 3.1 将结果保存
                log.info("queryOrderResult save OrderDO. {}", queryOrderResponseDO);
                orderDomainService.saveOrder(QueryOrderWrapper.responseDO2OrderDO(queryOrderResponseDO));

                // 3.2 正确返回
                if (Objects.equals(queryOrderResponseDO.getCode(), SystemConstant.ORDER_RESULT_COMPLETE)
                        && Objects.equals(queryOrderResponseDO.getData().getOrderList()
                            .get(0).getStatus(), SystemConstant.ORDER_STATUS_OK)) {
                    return QueryOrderWrapper.do2Dto(queryOrderResponseDO);
                }
                // 3.3 结果未返回
                if (SystemConstant.ADD_ORDER_RESULT_PENDING.containsKey(
                        queryOrderResponseDO.getData().getOrderList().get(0).getStatus())) {
                    throw new KooPhoneException(ErrorCode.ORDER_RESULT_PENDING);
                }
                // 3.4 错误处理
                failureResultHandler(queryOrderResponseDO);
                // 未知错误
                throw new KooPhoneException(ErrorCode.UNKNOWN_ERROR);
            } else {
                log.info("Query Order Result Failure. {}. {}", queryOrderResultRequest, queryOrderResultRequest);
                throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION);
            }
        } else {
            log.info("Query Order Result Failure. {}. {}", queryOrderResultRequest, queryOrderResultRequest);
            throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION);
        }
    }

    /**
     * 失败重试、无法领取、重复、已下线
     * @param queryOrderResponseDO
     */
    public void failureResultHandler(QueryOrderResponseDO queryOrderResponseDO) {
        String code = queryOrderResponseDO.getCode();
        if (Objects.equals(code, SystemConstant.ORDER_RESULT_FAILURE)) {
            Optional<String> orderRespDOOpt =
                    Optional.of(queryOrderResponseDO)
                            .map(QueryOrderResponseDO::getData)
                            .map(QueryOrderResponseDO.RpcFullfillmentResultV2Resp::getOrderList)
                            .map(e -> e.get(0))
                            .map(QueryOrderResponseDO.OrderRespDO::getBizCode);
            if (orderRespDOOpt.isPresent()) {
                String bizCode = orderRespDOOpt.get();
                if (SystemConstant.ADD_ORDER_RESULT_FAILURE.containsKey(bizCode)) {
                    throw new KooPhoneException(ErrorCode.ADD_ORDER_FAILURE);
                }
                if (SystemConstant.ADD_ORDER_RESULT_UNABLE.containsKey(bizCode)) {
                    throw new KooPhoneException(ErrorCode.ORDER_INVALID);
                }
                if (SystemConstant.ADD_ORDER_RESULT_DUPLICATE.containsKey(bizCode)) {
                    throw new KooPhoneException(ErrorCode.ORDER_DUPLICATE_INVALID);
                }
                if (SystemConstant.ADD_ORDER_RESULT_ENDED.containsKey(bizCode)) {
                    throw new KooPhoneException(ErrorCode.ACTIVITY_ENDED);
                }
            }
        }
    }

    @Override
    public AddOrderResponseDTO addOrder(AddOrderRequest addOrderRequest) {
        // 1. 构造请求DO
        AddOrderDO addOrderDO = AddOrderWrapper.request2Do(addOrderRequest);
        String sourceOrderNo = UuidUtils.genUuid();
        addOrderDO.setSourceOrderNo(sourceOrderNo);
        addOrderDO.setActivityId(freeTrafficProperties.getActivityId());
        addOrderDO.setSourceApp(freeTrafficProperties.getSourceApp());
        addOrderDO.setSourceGoodsId(freeTrafficProperties.getSourceGoodsId());
        // 2. 请求
        AddOrderResponseDO addOrderResponseDO = orderGateway.addOrder(addOrderDO);
        // 3. 构造response
        if (addOrderResponseDO.getSuccess()
                && Objects.equals(addOrderResponseDO.getCode(), SystemConstant.ADD_ORDER_OK)) {
            return AddOrderWrapper.do2Dto(addOrderResponseDO);
        } else {
            log.error("Add Order Failure. {}. {}", addOrderRequest, addOrderResponseDO);
            throw new KooPhoneException(ErrorCode.ADD_ORDER_FAILURE);
        }
    }

    @Override
    public void addOrderCallback(OrderResultCallbackRequest orderResultCallbackRequest) {
        log.info("addOrderCallback ok. {}", orderResultCallbackRequest);
        OrderDO orderDO = orderDomainService.saveOrder(OrderResultCallbackWrapper.requestDo(orderResultCallbackRequest));
        if (orderDO == null || orderDO.getId() == null || orderDO.getId() == 0) {
            log.error("Failed to save the callback result. request: {}", orderResultCallbackRequest);
            throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION);
        }
    }

    @Override
    public OrderReceiveStatusResponseDTO receiveStatus(OrderReceiveStatusRequest request) {
        log.info("receiveStatus(). {}", request.getTelephone());
        Optional<OrderDO> orderOpt = orderGateway.queryOrderByTelephone(request.getTelephone());
        return orderOpt.map(orderDO -> new OrderReceiveStatusResponseDTO(
                    Objects.equals(orderDO.getStatus(), SystemConstant.ORDER_STATUS_OK)))
                .orElseGet(() -> new OrderReceiveStatusResponseDTO(false));
    }
}
