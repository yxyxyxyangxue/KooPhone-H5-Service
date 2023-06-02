package com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils;

import com.huawei.apaas.koophone.freetraffic.FreeTrafficApplicationTests;
import com.huawei.apaas.koophone.freetraffic.infrastructure.config.FreeTrafficProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class EncryptUtilsTest extends FreeTrafficApplicationTests {
    @Autowired
    private FreeTrafficProperties freeTrafficProperties;
    private final String dsaPublicKey = "MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C" +
            "2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI" +
            "1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6Ouq" +
            "C+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKT" +
            "xvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBAIRjNIu8e1AGV2cY65kO5ZCgkmwuQiMeOGyKMpnFP0BwL2kcCmr" +
            "Ea0saX9TygGPwgsroVWIk3BZD51yc68VR3I4UD8pcnmidZ+8XFQEezYuujtGISKDc0byk5Ho/epi/Jr3lrWLVgRwjPB/AcEb" +
            "quVbyFGiyLeg1JRvBo7v/OORh";
    private final String rsaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCUGNEGI+4Dl3J6sqvosWEJptNPonrFp" +
            "2I7IzuKFFh0s4TzCgMNFYaqduPbQL1iDUv9hSwEIwb67Wfi6bNyUV5gurPSa3vM0Zw+F3IQkyTnYZ1fr5h9VuMAxxJhfjCXq6P/l6" +
            "gyrpYLjsD36/GaBDQANvfLALYzH7wTZE5+OYESgwIDAQAB";
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    // @Test
    void test_encodeRSA() throws Exception {
        String originText = "Hello World!";
        String rsa2 = EncryptUtils.encodeRSA(originText.getBytes(), freeTrafficProperties.getRsaPrivateKeyUrl());

        byte[] encode = Base64Ext.decode(rsaPublicKey.getBytes(), Base64Ext.NO_WRAP);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encode);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        Signature rsa = Signature.getInstance("MD5withRSA");
        rsa.initVerify(publicKey);
        rsa.update(originText.getBytes());
        boolean verify = rsa.verify(Base64Ext.decode(rsa2.getBytes(), Base64Ext.NO_WRAP));
        assertTrue(verify);
    }

    // @Test
    void test_encryptRSA() throws Exception {
        String originText = "Hello World!";
        byte[] pwd = EncryptUtils.encryptRSA(originText.getBytes(), freeTrafficProperties.getRsaPrivateKeyUrl());
        log.info("encrypt message = {}", pwd);
        byte[] bytes = decryptByPublicKey(pwd, rsaPublicKey);
        log.info("decrypt message = {}", new String(bytes));
        assertEquals(originText, new String(bytes));
    }

    // @Test
    void encodeDSA() throws Exception {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        String originText = "Hello World!";
        treeMap.put("k", originText);
        String sign = EncryptUtils.encodeDSA(treeMap, freeTrafficProperties.getDsaPrivateKeyUrl());
        byte[] signBytes = Base64.decodeBase64(sign.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        treeMap.forEach((k, v) -> {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(k).append("=").append(v);
        });

        byte[] bytesToSign = sb.toString().getBytes(StandardCharsets.UTF_8);

        Signature sg = Signature.getInstance("DSA");
        // 初始化DSA私钥
        sg.initVerify(getDSAPublicKey(dsaPublicKey));
        sg.update(bytesToSign);
        // 验证签名
        boolean status = sg.verify(signBytes);
        assertTrue(status);
    }

    private static PublicKey getDSAPublicKey(String publicKeyStr) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr.getBytes("GBK")));
        return keyFactory.generatePublic(keySpec);
    }

    private static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
        byte[] keyBytes = Base64Ext.decode(publicKey.getBytes(), Base64Ext.NO_WRAP);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }
}