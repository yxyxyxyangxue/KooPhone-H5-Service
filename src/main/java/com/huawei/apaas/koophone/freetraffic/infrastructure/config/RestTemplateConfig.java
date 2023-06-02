package com.huawei.apaas.koophone.freetraffic.infrastructure.config;

import com.huawei.apaas.koophone.freetraffic.infrastructure.common.SystemConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * restTemplate配置类
 * @author zhangjihong
 * @since 2023-05-23
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(SystemConstant.HTTP_TIME_OUT);
        factory.setReadTimeout(SystemConstant.HTTP_READ_TIMEOUT_OUT);
        return factory;
    }
}
