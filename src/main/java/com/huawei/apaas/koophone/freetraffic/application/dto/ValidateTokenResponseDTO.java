package com.huawei.apaas.koophone.freetraffic.application.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * token校验 response dto
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
@ApiModel(value = "ValidateTokenResponseDTO", description = "token校验response dto")
public class ValidateTokenResponseDTO {
    /**
     * 基于uid的token校验后，为uid
     */
    private String usessionid;
    /**
     * 手机号mask 123****4567
     */
    private String msmsdnmask;
    /**
     * 用户统一账户系统标识
     */
    private String passid;
    /**
     * 手机号
     */
    private String msisdn;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 登录id类型：0-手机号，1-邮箱
     */
    private String loginidtype;
    /**
     * 用户所属省
     */
    private String province;
    /**
     * 认证方式
     */
    private String authtype;
    /**
     * 认证时间
     */
    private String authtime;
    /**
     * 昵称
     */
    private String nickname;
}
