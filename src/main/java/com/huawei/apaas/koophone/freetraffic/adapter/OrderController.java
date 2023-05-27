package com.huawei.apaas.koophone.freetraffic.adapter;

import com.huawei.apaas.koophone.freetraffic.application.dto.*;
import com.huawei.apaas.koophone.freetraffic.application.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 履约结果查询
     * @return
     */
    @PostMapping("/query")
    @ApiOperation(value = "查询履约结果", notes = "是否领取免流成功，仅支持查询最近一次履约结果？")
    public SingleResponse<QueryOrderResponseDTO> queryOrderResult(@RequestBody QueryOrderResultRequest queryOrderResultRequest) {
        return SingleResponse.of(orderService.queryOrderResult(queryOrderResultRequest));
    }

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
     * 结果回调推送
     * @return
     */
    @PostMapping("/add/resultCallback")
    @ApiOperation(value = "下单结果回调", notes = "供移动营销平台回调用的，H5前台不要调")
    public OrderResultCallbackResponse resultCallback(
            @RequestBody @Valid OrderResultCallbackRequest orderResultCallbackRequest) {
        orderService.addOrderCallback(orderResultCallbackRequest);
        return OrderResultCallbackResponse.ofOk();
    }

    /**
     * receiveStatus
     * @return
     */
    @PostMapping("/receiveStatus")
    @ApiOperation(value = "获取当前用户领取状态", notes = "获取当前用户领取状态")
    public SingleResponse<OrderReceiveStatusResponseDTO> receiveStatus(
            @RequestBody @Valid OrderReceiveStatusRequest orderReceiveStatusRequest) {
        return SingleResponse.of(orderService.receiveStatus(orderReceiveStatusRequest));
    }
}
