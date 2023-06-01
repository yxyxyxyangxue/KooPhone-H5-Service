package com.huawei.apaas.koophone.freetraffic.domain.auth;

import com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils.EncryptUtils;
import com.huawei.apaas.koophone.freetraffic.infrastructure.config.FreeTrafficProperties;
import com.huawei.apaas.koophone.freetraffic.infrastructure.gatewayimpl.rpc.dataobject.ValidateTokenDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

/**
 * 认证领域对象
 * @author zhangjihong
 * @since 2023-05-23
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthDomainService {
    private final FreeTrafficProperties freeTrafficProperties;

    /**
     * 构建token验证接口sign
     * @param validateTokenDO
     * @return
     */
    public String buildValidateTokenSign(ValidateTokenDO validateTokenDO) {
        Map<String, String> map = new TreeMap<>();
        map.put("version", validateTokenDO.getHeader().getVersion());
        map.put("id", freeTrafficProperties.getSourceId());
        map.put("idtype", validateTokenDO.getHeader().getIdtype());
        map.put("msgid", validateTokenDO.getHeader().getMsgid());
        map.put("key", freeTrafficProperties.getSourceKey());
        map.put("apptype", validateTokenDO.getHeader().getApptype());
        map.put("systemtime", validateTokenDO.getHeader().getSystemtime());
        map.put("token", validateTokenDO.getBody().getToken());
        return EncryptUtils.md5(map);
    }
}
