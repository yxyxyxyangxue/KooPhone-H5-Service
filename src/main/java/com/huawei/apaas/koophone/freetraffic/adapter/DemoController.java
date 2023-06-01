package com.huawei.apaas.koophone.freetraffic.adapter;

import com.huawei.apaas.koophone.freetraffic.application.dto.*;
import com.huawei.apaas.koophone.freetraffic.application.service.IOrderService;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.SystemConstant;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.ErrorCode;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.KooPhoneException;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.BeanUtils;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.JAXBUtils;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.JsonUtils;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;
import lombok.Data;
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
import javax.validation.constraints.NotBlank;
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
    public SingleResponse<UserVO> connectionRefused2() {
        String url = "http://127.0.0.1:8080/user/info?name={name}&phone={phone}";
        Map<String, String> params = new HashMap<>();
        params.put("name", "tom");
        params.put("phone", "14278951236");
        try {
            Object vo = restTemplate.getForObject(url, Object.class, params);
            System.out.println(vo);
            ResponseEntity<UserVO> entity = restTemplate.getForEntity(url, UserVO.class, params);
            System.out.println(entity);
            return SingleResponse.of(entity.getBody());
        } catch (Exception e) {
            throw new KooPhoneException(ErrorCode.UNKNOWN_ERROR, e);
        }
    }

    @PostMapping("/getUserVO")
    public SingleResponse<UserVO> getUserVO() {
        String url = "http://127.0.0.1:8080/user/info?name={name}&phone={phone}";
        UserVO userVO = restTemplate.getForObject(url, UserVO.class, "tom", "15423236543");
        System.out.println(userVO);
        return SingleResponse.of(userVO);
    }

    @PostMapping("/getUserVO2")
    public SingleResponse<UserVO> getUserVO2() {
        String url = "http://127.0.0.1:8080/user/info?name={name}&phone={phone}";
        UserVO vo = new UserVO();
        vo.setName("tom");
        vo.setPhone("12345678798");

        UserVO userVO = restTemplate.getForObject(url, UserVO.class, BeanUtils.beanToMap(vo));
        System.out.println(userVO);
        return SingleResponse.of(userVO);
    }

    @PostMapping("/globalExceptionHandler")
    public Response globalExceptionHandler() {
        throw new KooPhoneException(ErrorCode.ILLEGAL_ARGUMENT);
    }

    @PostMapping("/globalExceptionHandler2")
    public Response globalExceptionHandler2() {
        throw new IllegalArgumentException("参数不合法~");
    }

    @PostMapping("/orderStatusCallback")
    public String orderStatusCallback(@RequestBody String req) {
        SyncFlowPkgOrderReq syncFlowPkgOrderReq = JAXBUtils.xml2ObjIgnoreNS(SyncFlowPkgOrderReq.class, req);
        log.warn("req = {}", syncFlowPkgOrderReq);
        SyncFlowPkgOrderResp resp = new SyncFlowPkgOrderResp();
        resp.setMsgType(SystemConstant.ORDER_STATUS_CALLBACK_RESP_TYPE);
        resp.setVersion(SystemConstant.ORDER_STATUS_CALLBACK_VERSION);
        resp.setHRet(SystemConstant.ORDER_STATUS_CALLBACK_OK);
        return JAXBUtils.obj2xmlStringWithNS(resp);
    }
}
