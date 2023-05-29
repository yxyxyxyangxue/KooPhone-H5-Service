package com.huawei.apaas.koophone.freetraffic.adapter;

import com.huawei.apaas.koophone.freetraffic.application.dto.AddOrderRequest;
import com.huawei.apaas.koophone.freetraffic.application.dto.AddOrderResponseDTO;
import com.huawei.apaas.koophone.freetraffic.application.dto.Response;
import com.huawei.apaas.koophone.freetraffic.application.dto.SingleResponse;
import com.huawei.apaas.koophone.freetraffic.application.service.IOrderService;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.ErrorCode;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.KooPhoneException;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    private final RestTemplate restTemplate;
    @PostMapping("/order/add")
    public SingleResponse<AddOrderResponseDTO> addOrder(
            @RequestBody @Valid AddOrderRequest addOrderRequest) {
        log.debug("request = {}", addOrderRequest);
        AddOrderResponseDTO addOrderResponseDTO = new AddOrderResponseDTO();
        addOrderResponseDTO.setTelephone("12478951236");
        return SingleResponse.of(addOrderResponseDTO);
    }

    @PostMapping("/connectionRefused")
    public SingleResponse<OrderDO> connectionRefused() {
        String url = "http://127.0.0.1:80/user/info?name={}";
        Map<String, String> params = new HashMap<>();
        params.put("name", "tom");
        ResponseEntity<OrderDO> entity = restTemplate.getForEntity(url, OrderDO.class, params);
        System.out.println(entity);
        return SingleResponse.of(entity.getBody());
    }

    @PostMapping("/connectionRefused2")
    public SingleResponse<OrderDO> connectionRefused2() {
        String url = "http://211.185.45.78:8080/user/info?name={}";
        Map<String, String> params = new HashMap<>();
        params.put("name", "tom");
        try {
            ResponseEntity<OrderDO> entity = restTemplate.getForEntity(url, OrderDO.class, params);
            System.out.println(entity);
            return SingleResponse.of(entity.getBody());
        } catch (Exception e) {
            throw new KooPhoneException(ErrorCode.UNKNOWN_ERROR, e);
        }
    }

    @PostMapping("/globalExceptionHandler")
    public Response globalExceptionHandler() {
        throw new KooPhoneException(ErrorCode.ILLEGAL_ARGUMENT);
    }

    @PostMapping("/globalExceptionHandler2")
    public Response globalExceptionHandler2() {
        throw new IllegalArgumentException("参数不合法~");
    }
}
