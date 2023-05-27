package com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils;

import com.huawei.apaas.koophone.freetraffic.infrastructure.common.SystemConstant;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.ErrorCode;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.KooPhoneException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.util.Map;

/**
 * 加签、解签 util
 * @author zhangjihong
 * @since 2023-05-23
 */
@Slf4j
public class EncryptUtils {
    private EncryptUtils() { }

    /**
     * 手机号解密
     * @param ciphertext 密文
     * @param key 密钥
     * @return
     */
    public static String deCodeAES(String ciphertext, String key) {
        byte[] keyBytes = DigestUtils.md5(key);
        byte[] ct64Bytes = Base64.decodeBase64(ciphertext.getBytes());
        return new String(decrypt(ct64Bytes, keyBytes));
    }

    private static byte[] decrypt(byte[] text, byte[] key) {
        SecretKeySpec aesKey = new SecretKeySpec(key, SystemConstant.AES_ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(SystemConstant.CIPHER_KEY);
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            return cipher.doFinal(text);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                 | IllegalBlockSizeException | BadPaddingException e) {
            log.error("decrypt failure.", e);
            throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION);
        }
    }

    public static String md5(String text) {
        return DigestUtils.md5Hex(text);
    }

    public static String md5(Map<String, String> map) {
        return md5(mapToString(map));
    }

    public static String mapToString(Map<String, String> map) {
        if (null == map || map.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getValue());
        }

        return sb.toString();
    }

    public static String rsaEncode(String originText, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(SystemConstant.RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey, new SecureRandom());;
            byte[] result = cipher.doFinal(originText.getBytes());
            return new String(result);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                 | IllegalBlockSizeException | BadPaddingException e) {
            log.error("rsaEncode failure. ", e);
            throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION);
        }
    }

    /**
     * 获取mac
     *
     * @param secret sourcekey
     * @param data   源数据
     * @return mac值
     */
    public static String hmacsha256(String secret, String data) {
        Mac mac = null;
        byte[] doFinal = null;
        try {
            mac = Mac.getInstance(SystemConstant.HMAC_ALGORITHM);
            // 先对源字符串进行MD5
            byte[] dataBytes = DigestUtils.md5(data);
            // 对sourcekey进行MD5,得到密钥
            SecretKey secretkey = new SecretKeySpec(DigestUtils.md5(secret), SystemConstant.HMAC_ALGORITHM);
            mac.init(secretkey);
            // HmacSHA256加密
            doFinal = mac.doFinal(dataBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("hmacsha256 failure.", e);
            throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION);
        }
        return Hex.encodeHexString(doFinal).toLowerCase();
    }
}
