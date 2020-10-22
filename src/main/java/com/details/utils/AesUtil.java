package com.details.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Aes加解密工具类
 */
@Slf4j
public class AesUtil {

    private static final String AES_GCM_NOPADDING = "AES/GCM/NoPadding";
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static void main(String[] args) {
        String name = "zlp";
        Object encryptAES = encryptAES(name, "zlp", 1);
        if (encryptAES != null) {
            log.info(encryptAES.toString());
        }
    }
    /**
     * AES加密
     *     type   1为utf-8 ，2为gbk
     * @param sSrc 待加密字符串
     * @param password 16位key
     * @param type 加密类型
     * @return Object
     */
    public static Object encryptAES(String sSrc, String password,Integer type) {
        try {
            if (password == null) {
                throw new NullPointerException();
            }
            // 判断Key是否为16位
            int len = 16;
            if (password.length() != len) {
                log.error("password长度不足16"+password);
                throw new Exception();
            }
            if(type == 1){
                byte[] raw = password.getBytes(UTF_8);
                // 根据key(秘钥？)的字节字符串，指定AES的加密方式
                SecretKeySpec skySpec = new SecretKeySpec(raw, "AES");
                // 根据加密模式获取Cipher对象
                Cipher cipher = Cipher.getInstance(AES_GCM_NOPADDING);
                // 初始化
                cipher.init(Cipher.ENCRYPT_MODE, skySpec);
                // 加密字符转成字节数组
                byte[] encrypted = cipher.doFinal(sSrc.getBytes(UTF_8));
                return getBase64(encrypted);
            }else{
                byte[] raw = password.getBytes("gbk");
                SecretKeySpec skySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance(AES_GCM_NOPADDING);
                cipher.init(Cipher.ENCRYPT_MODE, skySpec);
                byte[] encrypted = cipher.doFinal(sSrc.getBytes("gbk"));
                return getBase64(encrypted);
            }
        } catch (Exception e) {
            log.error("加密异常"+e);
        }
        return null;
    }

    /**
     * AES 解密
     *    type   1为utf-8 ，2为gbk
     * @param sSrc s
     * @return Object
     */
    public static Object decryptAES(String sSrc, String password,Integer type) {
        try{
            if (password == null) {
                return null;
            }
            // 判断Key是否为16位
            int len = 16;
            if (password.length() != len) {
                return null;
            }
            if(type == 1){
                byte[] raw = password.getBytes(UTF_8);
                SecretKeySpec skySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance(AES_GCM_NOPADDING);
                cipher.init(Cipher.DECRYPT_MODE, skySpec);
                //先用base64解密
                byte[] encrypted1 = getFromBase64(sSrc);
                byte[] original = cipher.doFinal(encrypted1);
                return new String(original, UTF_8);
            }else{
                byte[] raw = password.getBytes("gbk");
                SecretKeySpec skySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance(AES_GCM_NOPADDING);
                cipher.init(Cipher.DECRYPT_MODE, skySpec);
                //先用base64解密
                byte[] encrypted1 = getFromBase64(sSrc);
                byte[] original = cipher.doFinal(encrypted1);
                return new String(original, "gbk");
            }
        }catch (Exception e){
            log.error("解密异常decryptAES"+e);
        }
        return null;
    }

    public static String getBase64(byte[] str) {
        String s = null;
        byte[] b = str;
        if (b != null) {
            // 将字节数组用base64加密
            s = new BASE64Encoder().encode(b);
            s = s.replaceAll("\r\n", "");
        }
        return s;
    }


    /**
     * 解密
     *
     * @param s
     * @return
     */
    public static byte[] getFromBase64(String s) {
        byte[] b = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
            } catch (Exception e) {
                log.error("解密异常getFromBase64"+e);
            }
        }
        return b;
    }

    public static String base64AndAESDecrypt128(String base64EncryptConent, String key, String keyIV, String encode) throws Exception {
        if (base64EncryptConent.length() % 8 != 0) {
            base64EncryptConent = base64EncryptConent.substring(0, base64EncryptConent.length()
                    - (base64EncryptConent.length() % 8));
        }
        // 注意，Iv只在CBC下使用，，在EBC下不使用
        byte[] byEncrypt = new Base64().decode(base64EncryptConent);
        byte[] keyArray = new byte[16];
        byte[] tempKey = key.getBytes(encode);
        int minLenKey = Math.min(keyArray.length, tempKey.length);
        for (int i = 0; i < minLenKey; ++i)
            keyArray[i] = tempKey[i];
        //Security.addProvider(new BouncyCastleProvider());
        SecretKeySpec sKey = new SecretKeySpec(keyArray, "AES");

        Cipher eCipher = Cipher.getInstance(AES_GCM_NOPADDING);
        eCipher.init(Cipher.DECRYPT_MODE, sKey);
        byte[] result = eCipher.doFinal(byEncrypt);
        int copyIndex = 0;
        for (int i = result.length - 1; i > -1; i--) {
            if (result[i] == 0x00) {
                copyIndex++;
            }

        }
        byte[] tmpResult = Arrays.copyOf(result,result.length-copyIndex);
        return new String(tmpResult, encode);

    }


}
