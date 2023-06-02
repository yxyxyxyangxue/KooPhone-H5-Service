package com.huawei.apaas.koophone.freetraffic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * 启动类
 * @author zhangjihong
 * @since 2023-05-23
 */
@SpringBootApplication
@EnableJpaRepositories("com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa")
@EnableJpaAuditing
@EntityScan("com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.jpa")
public class FreeTrafficApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreeTrafficApplication.class, args);
	}

}
