package com.huawei.apaas.koophone.freetraffic.domain.order;

import com.huawei.apaas.koophone.freetraffic.domain.gateway.OrderGateway;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.SystemConstant;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.DateUtils;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject.OrderDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * 履约领域对象
 * @author zhangjihong
 * @since 2023-05-23
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderDomainService {
    private final OrderGateway orderGateway;

    /**
     * 先查询当前用户伪码是否已存在订购记录，如果存在则进行更新操作，否则新增
     * @param orderDO
     * @return
     */
    public OrderDO saveOrder(OrderDO orderDO) {
        OrderDO req = new OrderDO();
        req.setUserPseudoCode(orderDO.getUserPseudoCode());
        Optional<OrderDO> orderOpt = orderGateway.queryOrder(req);
        if (orderOpt.isPresent()) {
            orderOpt.ifPresent(o -> {
                orderDO.setId(o.getId());
                orderDO.setTelephone(o.getTelephone());
            });
        }
        return orderGateway.save(orderDO);
    }

    /**
     * 为空则返回月底最后一天
     * @return
     */
    private String getExpireTime(String expireTime) {
        if (StringUtils.hasText(expireTime)) {
            return expireTime;
        } else {
            return DateUtils.getCurrentMonthLastDay(expireTime).format(SystemConstant.DATEFORMAT_DATE);
        }
    }

    /**
     * 根据order信息判断当前订购状态，true未已订购
     * @param orderDO
     * @return
     */
    public boolean judgeOrderStatus(OrderDO orderDO) {
        log.info("judgeOrderStatus(). {}", orderDO);
        // 订购状态成功
        if (Objects.equals(orderDO.getReturnStatus(), SystemConstant.ORDER_RETURN_STATUS)) {
            // payType为2-到期关闭，无论怎么样，都需要校验expireTime
            // actionId为退订，则当前时间要在失效时间之前
            if (Objects.equals(orderDO.getPayType(), SystemConstant.ORDER_PAYTYPE_EXPIRE_CLOSE)
                    || Objects.equals(orderDO.getActionId(), SystemConstant.ORDER_ACTION_CANCEL_ORDER)) {
                if (DateUtils.beforeNow(getExpireTime(orderDO.getExpireTime()))) {
                    return true;
                }
            }
            // actionId为订购，则当前时间要在生效时间之后
            if (Objects.equals(orderDO.getActionId(), SystemConstant.ORDER_ACTION_PLACE_ORDER)) {
                if (orderDO.getEffectiveTime() == null
                        || DateUtils.afterNow(orderDO.getEffectiveTime())) {
                    return true;
                }
            }
        }
        return false;
    }
}
