package com.huawei.apaas.koophone.freetraffic.application.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 请求获取当前用户订购状态request
 * @author zhangjihong
 * @since 2023-05-27
 */
@Data
@ApiModel(value = "OrderStatusResponseDTO", description = "当前用户领取状态response dto")
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusResponseDTO {
    /**
     * 订购状态
     */
    @ApiModelProperty(value = "订购状态", example = "TRUE")
    private OrderStatus status;

    /**
     * 失效时间
     */
    @ApiModelProperty(value = "失效时间 yyyyMMdd，退订情况下的expireTime为空，" +
            "设置为月底最后一天已处理，其它情况有可能是null的")
    private String expireTime;

    /**
     * 订购或下单状态
     */
    @Getter
    @RequiredArgsConstructor
    public enum OrderStatus {
        /**
         * 订购成功
         */
        TRUE(1, "已订购"),
        /**
         * 订购中
         */
        PROCESS(2, "订购中"),
        /**
         * 已退订
         */
        CANCELED(3, "已退订"),
        /**
         * 退订中
         */
        CANCELING(4, "退订中"),
        /**
         * 已到期自动关闭
         */
        CLOSED(5, "已到期自动关闭"),
        /**
         * 订购失败
         */
        FALSE(6, "失败"),
        /**
         * 当前还未收到订购回调
         */
        PENDING(7, "当前还未收到订购回调");

        private final int value;
        private final String message;
    }
}
