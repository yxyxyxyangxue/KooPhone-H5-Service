package com.huawei.apaas.koophone.freetraffic.adapter;

import com.huawei.apaas.koophone.freetraffic.application.dto.AddOrderRequest;
import com.huawei.apaas.koophone.freetraffic.application.dto.AddOrderResponseDTO;
import com.huawei.apaas.koophone.freetraffic.application.dto.SingleResponse;
import com.huawei.apaas.koophone.freetraffic.application.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * demo
 * @author zhangjihong
 * @since 2023-05-23
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/demo")
@ApiIgnore
public class DemoController {
    private final IOrderService orderService;
    @PostMapping("/order/add")
    public SingleResponse<AddOrderResponseDTO> addOrder(
            @RequestBody @Valid AddOrderRequest addOrderRequest) {
        log.debug("request = {}", addOrderRequest);
        AddOrderResponseDTO addOrderResponseDTO = new AddOrderResponseDTO();
        addOrderResponseDTO.setTelephone("12478951236");
        return SingleResponse.of(addOrderResponseDTO);
    }
}
