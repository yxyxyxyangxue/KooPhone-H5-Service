package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject;

import lombok.Data;

/**
 * 用户信息获取do
 * @author zhangjihong
 * @since 2023-05-29
 */
@Data
public class GetUserinfoDO {
    /**
     * 渠道编码
     */
    private String channelId;
    /**
     * 请求唯一id，uuid 自己生成
     */
    private String msgId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * <li>1-移动</li>
     * <li>2-联通</li>
     * <li>3-电信</li>
     * <li>0-未知</li>
     */
    private String openType;
    /**
     * 接收方预留参数，需urlencode
     */
    private String message;
    /**
     * 签名
     */
    private String sign;
    /**
     * 填 phoneNum=xxx，需urlencode编码
     */
    private String expandParams;
}
