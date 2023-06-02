package com.huawei.apaas.koophone.freetraffic.domain.order;

import com.huawei.apaas.koophone.freetraffic.FreeTrafficApplicationTests;
import com.huawei.apaas.koophone.freetraffic.application.dto.OrderStatusResponseDTO;
import com.huawei.apaas.koophone.freetraffic.application.dto.SyncFlowPkgOrderReq;
import com.huawei.apaas.koophone.freetraffic.application.service.wrapper.OrderStatusCallbackWrapper;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.JAXBUtils;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class OrderDomainServiceTest extends FreeTrafficApplicationTests {
    @Autowired
    OrderDomainService domainService;
    @Test
    void judgeOrderStatus() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<SyncFlowPkgOrderReq xmlns=\"http://www.monternet.com/dsmp/schemas/\">\n" +
                "  <MsgType>SyncFlowPkgOrderReq</MsgType>\n" +
                "  <Version>1.0.0</Version>\n" +
                "  <OrderId>123556565656</OrderId>\n" +
                "  <UserPseudoCode>454546565</UserPseudoCode>\n" +
                "  <ChannelSeqId>45654645656343fdg</ChannelSeqId>\n" +
                "  <Price>30.0</Price>\n" +
                "  <ActionTime>232305300953</ActionTime>\n" +
                "  <ActionID>1</ActionID>\n" +
                "  <EffectiveTime>23230530</EffectiveTime>\n" +
                "  <ExpireTime>23230530</ExpireTime>\n" +
                "  <EffectiveRealTime>232305300954</EffectiveRealTime>\n" +
                "  <ExpireRealTime>232305300954</ExpireRealTime>\n" +
                "  <PayType>1</PayType>\n" +
                "  <ChannelId>H5-Cloud</ChannelId>\n" +
                "  <ProductId>34354546456</ProductId>\n" +
                "  <OrderType>1</OrderType>\n" +
                "  <sign>SHA1(OrderID#UserPseudoCode#channelId#productId#signKey)</sign>\n" +
                "  <ReturnStatus>0</ReturnStatus>\n" +
                "  <ProvCode>210</ProvCode>\n" +
                "  <OrderTime>232305300954</OrderTime>\n" +
                "  <AppId>r54ye534</AppId>\n" +
                "</SyncFlowPkgOrderReq>";
        SyncFlowPkgOrderReq req = JAXBUtils.xml2ObjIgnoreNS(SyncFlowPkgOrderReq.class, xml);
        OrderDO orderDO = OrderStatusCallbackWrapper.request2DO(req);
        OrderStatusResponseDTO.OrderStatus orderStatus = domainService.judgeOrderStatus(orderDO);
        assertEquals(orderStatus, OrderStatusResponseDTO.OrderStatus.TRUE);
    }
}