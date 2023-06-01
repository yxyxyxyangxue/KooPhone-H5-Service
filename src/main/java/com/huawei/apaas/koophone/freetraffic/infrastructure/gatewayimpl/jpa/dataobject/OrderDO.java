package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 订单实体映射
 * @author zhangjihong
 * @since 2023-05-23
 */
@Entity
@Table(name = "kp_freetraffic_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class OrderDO {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telephone")
    private String telephone;
    /**
     * 用户伪码
     */
    @Column(name = "user_pseudo_code")
    private String userPseudoCode;
    /**
     * 订单号
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 外部交易ID
     */
    @Column(name = "channel_seq_id")
    private String channelSeqId;

    /**
     * 资费（单位-分）
     */
    @Column(name = "price")
    private Integer price;

    /**
     * 操作时间
     */
    @Column(name = "action_time")
    private String actionTime;

    /**
     * 1-订购，2-退订，3-暂停，4-激活
     */
    @Column(name = "action_id")
    private Integer actionId;

    /**
     * 生效时间
     */
    @Column(name = "effective_time")
    private String effectiveTime;

    /**
     * 失效时间
     */
    @Column(name = "expire_time")
    private String expireTime;

    /**
     * 生效时间
     */
    @Column(name = "effective_real_time")
    private String effectiveRealTime;

    /**
     * 失效时间
     */
    @Column(name = "expire_real_time")
    private String expireRealTime;

    /**
     * 计费类型
     * <li>1-自动续费</li>
     * <li>2-到期关闭</li>
     */
    @Column(name = "pay_type")
    private Integer payType;

    /**
     * 渠道合作方编码
     */
    @Column(name = "channel_id")
    private String channelId;

    /**
     * 产品编码
     */
    @Column(name = "product_id")
    private String productId;

    /**
     * 订购类型，0-测试，1-正式
     */
    @Column(name = "order_type")
    private Integer orderType;

    /**
     * 订购结果状态值，0-成功，1-失败
     */
    @Column(name = "return_status")
    private Integer returnStatus;

    /**
     * 省编码
     */
    @Column(name = "prov_code")
    private String provCode;

    /**
     * 用户发起的订购时间
     */
    @Column(name = "order_time")
    private String orderTime;

    /**
     * 应用id
     */
    @Column(name = "app_id")
    private String appId;

    @CreatedDate
    @Column(name = "create_time", updatable = false, nullable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;
}
