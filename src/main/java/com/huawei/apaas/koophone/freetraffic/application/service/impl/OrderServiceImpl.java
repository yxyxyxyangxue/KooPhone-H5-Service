package com.huawei.apaas.koophone.freetraffic.application.service.impl;

import com.huawei.apaas.koophone.freetraffic.application.dto.*;
import com.huawei.apaas.koophone.freetraffic.application.service.IOrderService;
import com.huawei.apaas.koophone.freetraffic.application.service.wrapper.AddOrderWrapper;
import com.huawei.apaas.koophone.freetraffic.application.service.wrapper.OrderStatusCallbackWrapper;
import com.huawei.apaas.koophone.freetraffic.domain.gateway.AuthGateway;
import com.huawei.apaas.koophone.freetraffic.domain.gateway.OrderGateway;
import com.huawei.apaas.koophone.freetraffic.domain.order.OrderDomainService;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.SystemConstant;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.ErrorCode;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.KooPhoneException;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.BeanUtils;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.EncryptUtils;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.UuidUtils;
import com.huawei.apaas.koophone.freetraffic.infrastructure.config.FreeTrafficProperties;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

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
    private final FreeTrafficProperties freeTrafficProperties;
    private final OrderGateway orderGateway;
    private final AuthGateway authGateway;

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
    public OrderStatusResponseDTO receiveStatus(OrderStatusRequest request) {
        log.info("receiveStatus(). {}", request.getTelephone());
        OrderDO req = new OrderDO();
        req.setTelephone(request.getTelephone());
        Optional<OrderDO> orderOpt = orderGateway.queryOrder(req);
        return orderOpt.map(orderDO -> new OrderStatusResponseDTO(
                    Objects.equals(orderDO.getReturnStatus(), SystemConstant.ORDER_STATUS_OK)))
                .orElseGet(() -> new OrderStatusResponseDTO(false));
    }

    @Override
    public OrderStatusResponseDTO orderStatus(OrderStatusRequest request) {
        log.info("orderStatus(). {}", request.getTelephone());
        // 1. 先根据手机号查询，如果找到orderDO，则构造response返回
        OrderDO req = new OrderDO();
        req.setTelephone(request.getTelephone());
        Optional<OrderDO> orderOpt = orderGateway.queryOrder(req);
        if (orderOpt.isPresent()) {
            return new OrderStatusResponseDTO(orderDomainService.judgeOrderStatus(orderOpt.get()));
        } else {
            // 2. 如果没找到，先查询用户信息，得到用户伪码，再查询
            // 2.1 构建用户信息查询req
            GetUserinfoDO userinfoDO = buildGetUserinfoDO(request);
            // 2.2 查询用户信息
            GetUserinfoResponseDO userinfo = authGateway.getUserinfo(userinfoDO);
            if (!Objects.equals(userinfo.getResultcode(), SystemConstant.GET_USERINFO_RESULT_CODE_OK)
                 || !StringUtils.hasText(userinfo.getPcId())) {
                log.error("get userinfo. {}", userinfo);
                throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION);
            }
            // 2.3 根据用户伪码查询
            OrderDO req2 = new OrderDO();
            req2.setUserPseudoCode(userinfo.getPcId());
            Optional<OrderDO> orderOpt2 = orderGateway.queryOrder(req2);
            // 2.4 保存用户手机号和伪码关系 & 构造response
            if (orderOpt2.isPresent()) {
                OrderDO orderDO = orderOpt2.get();
                orderDO.setTelephone(request.getTelephone());
                orderGateway.save(orderDO);
                return new OrderStatusResponseDTO(orderDomainService.judgeOrderStatus(orderOpt2.get()));
            } else {
                OrderDO orderDO = new OrderDO();
                orderDO.setTelephone(request.getTelephone());
                orderDO.setUserPseudoCode(userinfo.getPcId());
                orderGateway.save(orderDO);
                return new OrderStatusResponseDTO(false);
            }
        }
    }

    /**
     * 构建用户信息查询req
     * @param request
     * @return
     */
    private GetUserinfoDO buildGetUserinfoDO(OrderStatusRequest request) {
        GetUserinfoDO userinfoDO = new GetUserinfoDO();
        userinfoDO.setChannelId(freeTrafficProperties.getChannelId());
        userinfoDO.setMsgId(UuidUtils.genUuid());
        userinfoDO.setOpenType(SystemConstant.OPEN_TYPE);
        userinfoDO.setExpandParams("phoneNum=" + request.getTelephone());
        TreeMap<String, Object> paramMap = buildParams(userinfoDO);
        userinfoDO.setSign(EncryptUtils.encodeDSA(paramMap, freeTrafficProperties.getDsaPrivateKeyUrl()));
        try {
            userinfoDO.setExpandParams(URLEncoder.encode(
                    userinfoDO.getExpandParams(), StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException e) {
            log.error("get userinfo interface expandParams encode error. ", e);
            throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION);
        }
        return userinfoDO;
    }

    private TreeMap<String, Object> buildParams(GetUserinfoDO userinfoDO) {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        BeanUtils.beanToTreeMap(treeMap, userinfoDO);
        treeMap.remove("sgin");
        return treeMap;
    }

    @Override
    public void saveOrder(SyncFlowPkgOrderReq syncFlowPkgOrderReq) {
        log.info("Order Status Callback. {}", syncFlowPkgOrderReq);
        OrderDO orderDO = orderDomainService.saveOrder(
                OrderStatusCallbackWrapper.request2DO(syncFlowPkgOrderReq));
        if (orderDO == null || orderDO.getId() == null || orderDO.getId() == 0) {
            log.error("Failed to save the callback result. request: {}", syncFlowPkgOrderReq);
            throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION);
        }
    }
}
