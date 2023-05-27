package com.huawei.apaas.koophone.freetraffic.application.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 签名接口response dto
 * @author zhangjihong
 * @since 2023-05-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SignResponseDTO", description = "签名接口response dto")
public class SignResponseDTO {
    private String signature;
}
