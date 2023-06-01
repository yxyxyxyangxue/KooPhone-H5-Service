package com.huawei.apaas.koophone.freetraffic.adapter;

import com.huawei.apaas.koophone.freetraffic.application.dto.*;
import com.huawei.apaas.koophone.freetraffic.application.service.IOrderService;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.SystemConstant;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.JAXBUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * 履约服务
 * @author zhangjihong
 * @since 2023-05-23
 */
@RestController
@Slf4j
@RequestMapping("/apaas/koophone/v1/order")
@RequiredArgsConstructor
@Api(tags = "履约服务")
public class OrderController {
    private final IOrderService orderService;

    /**
     * 下单
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "下单接口，领取免流优惠", notes = "下单接口，领取免流优惠")
    public SingleResponse<AddOrderResponseDTO> order(@RequestBody AddOrderRequest addOrderRequest) {
        return SingleResponse.of(orderService.addOrder(addOrderRequest));
    }

    /**
     * 获取当前用户订购状态
     * @return
     */
    @PostMapping("/status")
    @ApiOperation(value = "获取当前用户订购状态", notes = "获取当前用户订购状态")
    public SingleResponse<OrderStatusResponseDTO> status(
            @RequestBody @Valid OrderStatusRequest orderStatusRequest) {
        return SingleResponse.of(orderService.orderStatus(orderStatusRequest));
    }

    @PostMapping("/receiveStatus")
    // @ApiOperation(value = "获取当前用户领取状态", notes = "获取当前用户领取状态")
    @ApiIgnore
    public SingleResponse<OrderStatusResponseDTO> receiveStatus2(
            @RequestBody @Valid OrderStatusRequest orderReceiveStatusRequest) {
        return SingleResponse.of(orderService.receiveStatus(orderReceiveStatusRequest));
    }

    @PostMapping("/status/callback")
    @ApiOperation(value = "订购状态回调", notes = "供移动计费平台回调用的，H5前台不要调")
    public String orderStatusCallback(@RequestBody String reqXml) {
        // 1. 保存订购信息
        SyncFlowPkgOrderReq syncFlowPkgOrderReq = JAXBUtils.xml2ObjIgnoreNS(SyncFlowPkgOrderReq.class, reqXml);
        log.info("req = {}", syncFlowPkgOrderReq);
        orderService.saveOrder(syncFlowPkgOrderReq);
        // 2. 构造response
        SyncFlowPkgOrderResp resp = new SyncFlowPkgOrderResp();
        resp.setMsgType(SystemConstant.ORDER_STATUS_CALLBACK_RESP_TYPE);
        resp.setVersion(SystemConstant.ORDER_STATUS_CALLBACK_VERSION);
        resp.setHRet(SystemConstant.ORDER_STATUS_CALLBACK_OK);
        return JAXBUtils.obj2xmlStringWithNS(resp);
    }
}
