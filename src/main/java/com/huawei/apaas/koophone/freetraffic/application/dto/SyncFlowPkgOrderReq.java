package com.huawei.apaas.koophone.freetraffic.application.dto;

import com.huawei.apaas.koophone.freetraffic.infrastructure.common.SystemConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 订购状态变更通知回调接口 request
 * @author zhangjihong
 * @since 2023-05-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = SystemConstant.ORDER_STATUS_CALLBACK_REQ_TYPE)
public class SyncFlowPkgOrderReq {
    /**
     * 消息类型 SyncFlowPkgOrderReq
     */
    private String MsgType;
    /**
     * 接口消息版本，1.0.0
     */
    private String Version;
    /**
     * 订单编号，移动内部订购唯一标识
     */
    private String OrderId;
    /**
     * 用户伪码
     */
    private String UserPseudoCode;
    /**
     * 外部交易ID
     */
    private String ChannelSeqId;
    /**
     * 价格
     */
    private Integer Price;
    /**
     * 操作时间 YYYYMMDDHHMMSS
     */
    private String ActionTime;
    /**
     * <li>1-订购服务</li>
     * <li>2-退订服务</li>
     * <li>3-暂停服务（暂无）</li>
     * <li>4-激活服务（暂无）</li>
     */
    private Integer ActionID;
    /**
     * 生效时间 YYYYMMDD
     */
    private String EffectiveTime;
    /**
     * 失效时间 YYYYMMDD
     */
    private String ExpireTime;
    /**
     * 生效时间 YYYYMMDDHHMMSS
     */
    private String EffectiveRealTime;
    /**
     * 失效时间 YYYYMMDDHHMMSS
     */
    private String ExpireRealTime;
    /**
     * 计费类型
     * <li>1-自动续费</li>
     * <li>1-到期关闭（如果该字段为2，无论状态字段为订购还是退订，均需要通过ExpireTime来判断是否当前是否失效）</li>
     */
    private Integer PayType;
    /**
     * 合作方渠道编码（用户在什么渠道订购的）
     */
    private String ChannelId;
    /**
     * 产品编码
     */
    private String ProductId;
    /**
     * 订购类型 0-测试，1-正式
     */
    private Integer OrderType;
    /**
     * 签名
     */
    private String sign;
    /**
     * 订购结果状态值
     * <li>0-成功</li>
     * <li>1-失败</li>
     */
    private Integer ReturnStatus;
    /**
     * 省编码
     */
    private String ProvCode;
    /**
     * 订购时间，用户发起订购的时间
     * <p>YYYYMMDDHHMMSS</p>
     */
    private String OrderTime;
    /**
     * 应用ID
     */
    private String AppId;
}
