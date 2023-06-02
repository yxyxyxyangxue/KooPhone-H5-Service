package com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 用户信息获取response dto
 * @author zhangjihong
 * @since 2023-05-29
 */
@Data
public class GetUserinfoResponseDO {
    /**
     * 请求唯一标识
     */
    private String msgId;
    /**
     * 请求系统时间
     */
    private String systemTime;
    /**
     * 介入放预留参数
     */
    private String message;
    /**
     * 扩展参数
     */
    private String expandParams;
    /**
     * 处理状态编码
     * <li>1-成功</li>
     * <li>121-获取移动手机失败</li>
     * <li>122-超过当前取号阈值</li>
     * <li>99-系统异常</li>
     * <li>120-加密失败</li>
     * <li>303-参数解析错误</li>
     * <li>301-参数为空</li>
     * <li>118-校验签名失败</li>
     */
    private String resultcode;
    /**
     * 返回结果描述
     */
    private String desc;
    /**
     * 业务平台能订购的订购结果列表
     * <p>key: 套餐id</p>
     * <p>value: 0-未订购，1-订购</p>
     */
    private List<Map<String, String>> orderStatus;
    /**
     * 用户伪码
     */
    private String pcId;
}
