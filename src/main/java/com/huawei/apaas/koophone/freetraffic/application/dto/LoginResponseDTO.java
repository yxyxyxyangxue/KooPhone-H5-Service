package com.huawei.apaas.koophone.freetraffic.application.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 登录response dto
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
@ApiModel(value = "LoginResponseDTO", description = "登录 response dto")
public class LoginResponseDTO {
    /**
     * 统一账号标识
     */
    private String passid;
    /**
     * 统一账号绑定的手机号
     */
    private String msisdn;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 单点登录标识
     */
    private String usessionid;
    /**
     * 省份代码
     */
    private String province;
    /**
     * <p>01-全球通</p>
     * <p>02-神州行</p>
     * <p>03-动感地带</p>
     */
    private String brand;
    /**
     * 昵称
     */
    private String nickname;
}
