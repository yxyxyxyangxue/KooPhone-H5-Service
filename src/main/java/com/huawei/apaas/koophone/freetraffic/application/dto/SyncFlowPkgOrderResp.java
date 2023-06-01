package com.huawei.apaas.koophone.freetraffic.application.dto;

import com.huawei.apaas.koophone.freetraffic.infrastructure.common.SystemConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 订购状态变更通知回调接口 response
 * @author zhangjihong
 * @since 2023-05-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = SystemConstant.ORDER_STATUS_CALLBACK_RESP_TYPE, namespace = SystemConstant.CMCC_XMLNS)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "MsgType",
        "Version",
        "hRet"
})
public class SyncFlowPkgOrderResp {
    /**
     * 消息类型 SyncFlowPkgOrderResp
     */
    private String MsgType;
    /**
     * 消息接口版本 1.0.0
     */
    private String Version;
    /**
     * 返回值，0-成功，其他失败
     */
    private Integer hRet;
}
