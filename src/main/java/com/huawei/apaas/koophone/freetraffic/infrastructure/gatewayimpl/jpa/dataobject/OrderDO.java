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
     * 来源订单ID
     */
    @Column(name = "source_order_no")
    private String sourceOrderNo;
    /**
     * 订单号
     */
    @Column(name = "order_no")
    private Long orderNo;

    /**
     * 活动ID
     */
    @Column(name = "activity_id")
    private Long activityId;

    /**
     * 履约次数
     */
    @Column(name = "idx")
    private Integer index;

    /**
     * 商品编码
     */
    @Column(name = "sku_code")
    private String skuCode;

    /**
     * 商品名称
     */
    @Column(name = "sku_name")
    private String skuName;

    /**
     * 订单状态
     * <li>50-成功</li>
     * <li>60-失败</li>
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 业务码
     */
    @Column(name = "biz_code")
    private String bizCode;

    /**
     * 业务码描述
     */
    @Column(name = "biz_desc")
    private String bizDesc;

    /**
     * 期望执行时间
     */
    @Column(name = "expected_execute_time")
    private LocalDateTime exceptedExecuteTime;
    /**
     * 实际执行时间
     */
    @Column(name = "actual_execute_time")
    private LocalDateTime actualExecuteTime;

    @CreatedDate
    @Column(name = "create_time", updatable = false, nullable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;
}
