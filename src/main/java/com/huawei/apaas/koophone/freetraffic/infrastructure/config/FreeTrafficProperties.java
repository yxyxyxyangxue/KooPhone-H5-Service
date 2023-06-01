package com.huawei.apaas.koophone.freetraffic.infrastructure.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 免流配置项
 * @author zhangjihong
 * @since 2023-05-23
 */
@Component
@Data
public class FreeTrafficProperties {
    // 以下都是需要和移动申请的
    @Value("${cmcc.auth.sourceId:null}")
    private String sourceId;
    @Value("${cmcc.auth.sourceKey:null}")
    private String sourceKey;
    @Value("${cmcc.auth.appId:null}")
    private String appId;
    @Value("${cmcc.auth.appKey:null}")
    private String appKey;
    @Value("${cmcc.auth.userip:null}")
    private String userIp;
    @Value("${cmcc.auth.channelId:null}")
    private String channelId;
    @Value("${cmcc.order.activityId:0}")
    private Long activityId;
    @Value("${cmcc.order.sourceApp:null}")
    private String sourceApp;
    @Value("${cmcc.order.sourceGoodsId:null}")
    private String sourceGoodsId;
    @Value("${cmcc.rsaPrivateKeyUrl:null}")
    private String rsaPrivateKeyUrl;
    @Value("${cmcc.dsaPrivateKeyUrl:null}")
    private String dsaPrivateKeyUrl;
}
