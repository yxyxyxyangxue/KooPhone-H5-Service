package com.huawei.apaas.koophone.freetraffic.infrastructure.common.utils;

import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.ErrorCode;
import com.huawei.apaas.koophone.freetraffic.infrastructure.common.exception.KooPhoneException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Map;
import java.util.TreeMap;

/**
 * 加签、解签 util
 * @author zhangjihong
 * @since 2023-05-23
 */
@Slf4j
public class EncryptUtils {
    private static final String HMAC_ALGORITHM = "HmacSHA256";
    private static final String CIPHER_KEY = "AES/ECB/PKCS5Padding";
    private static final String AES_ALGORITHM = "AES";
    private static final String RSA_ALGORITHM = "RSA";
    private static final String RSA_SIGNATURE_ALGORITHM = "MD5withRSA";
    private static final String DSA_ALGORITHM = "DSA";
    private static final String CHARSET_GBK = "GBK";

    private static byte[] rsaPrivateKey = null;
    private static byte[] dsaPrivateKey = null;
    private EncryptUtils() { }

    /**
     * 手机号解密
     * @param ciphertext 密文
     * @param key 密钥
     * @return
     */
    public static String decodeAES(String ciphertext, String key) {
        byte[] keyBytes = DigestUtils.md5(key);
        byte[] ct64Bytes = Base64.decodeBase64(ciphertext.getBytes());
        return new String(decryptAES(ct64Bytes, keyBytes));
    }

    private static byte[] decryptAES(byte[] text, byte[] key) {
        SecretKeySpec aesKey = new SecretKeySpec(key, AES_ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_KEY);
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

    public static String encodeRSA(byte[] originText, String privateKeyUrl) {
        if (rsaPrivateKey == null) {
            rsaPrivateKey = getPrivateKey(privateKeyUrl);
        }
        try {
            byte[] keyBytes = Base64Ext.decode(rsaPrivateKey, Base64Ext.NO_WRAP);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature signature = Signature.getInstance(RSA_SIGNATURE_ALGORITHM);
            signature.initSign(privateK);
            signature.update(originText);
            return new String(Base64Ext.encode(signature.sign(), Base64Ext.NO_WRAP));
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | SignatureException e) {
            log.error("encodeRSA failure. ", e);
            throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION);
        }
    }

    public static String encodeDSA(TreeMap<String, Object> params, String privateKeyUrl) {
        if (dsaPrivateKey == null) {
            dsaPrivateKey = Base64.decodeBase64(getPrivateKey(privateKeyUrl));
        }
        StringBuilder sb = new StringBuilder();
        params.forEach((k, v) -> {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(k).append("=").append(v);
        });
        return encodeDSA(sb.toString());
    }

    public static String encodeDSA(String params) {
        try {
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(dsaPrivateKey);
            KeyFactory keyFactory = KeyFactory.getInstance(DSA_ALGORITHM);
            PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature dsa = Signature.getInstance(DSA_ALGORITHM);
            dsa.initSign(privateK);
            dsa.update(params.getBytes(CHARSET_GBK));
            return new String(Base64.decodeBase64(dsa.sign()));
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException
                 | SignatureException | UnsupportedEncodingException e) {
            log.info("encodeDSA failure. ", e);
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
            mac = Mac.getInstance(HMAC_ALGORITHM);
            // 先对源字符串进行MD5
            byte[] dataBytes = DigestUtils.md5(data);
            // 对sourcekey进行MD5,得到密钥
            SecretKey secretkey = new SecretKeySpec(DigestUtils.md5(secret), HMAC_ALGORITHM);
            mac.init(secretkey);
            // HmacSHA256加密
            doFinal = mac.doFinal(dataBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("hmacsha256 failure.", e);
            throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION);
        }
        return Hex.encodeHexString(doFinal).toLowerCase();
    }

    private static byte[] getPrivateKey(String privateKeyUrl) {
        try {
            return Files.readAllBytes(Paths.get(privateKeyUrl));
        } catch (IOException e) {
            log.error("load private key error. ", e);
            throw new KooPhoneException(ErrorCode.SYSTEM_EXCEPTION);
        }
    }
}
