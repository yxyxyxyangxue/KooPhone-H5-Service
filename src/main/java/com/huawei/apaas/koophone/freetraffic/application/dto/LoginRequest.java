package com.huawei.apaas.koophone.freetraffic.application.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录request
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
@ApiModel(value = "LoginRequest", description = "登录request")
public class LoginRequest {
    /**
     * <p>1 | BOSS</p>
     * <p>2 | web</p>
     * <p>3 | wap</p>
     * <p>4 | pc端</p>
     * <p>5 | 手机端</p>
     */
    private String apptype;
    /**
     * 用户输入的id，手机号或者邮箱或者“和id”
     * <p>maxLength = 64</p>
     */
    @NotBlank
    private String loginid;
    /**
     * 登录名类型
     * <p>1: 手机</p>
     * <p>2: 邮箱</p>
     * <p>3: 和通行证</p>
     * <p>maxLength = 8</p>
     */
    @NotBlank
    private String loginidtype;
    /**
     * 认证方式
     * <p>DUP: 短信验证码</p>
     * <p>maxLength = 8</p>
     */
    @NotBlank
    private String authtype;
    /**
     * 密码（短信验证码），短信验证码时不需要加密
     * <p>maxLength = 16</p>
     */
    @NotBlank
    private String password;

    /**
     * 业务扩展参数，多个参数使用 | 分隔，每个参数长度未3字节
     * <p>例如：300|301</p>
     * <p>300: SDK签发的token和临时凭证校验成功后返回uid</p>
     * <p>601: 用于获取推动标识pushId</p>
     */
    private String expandparams;
}


