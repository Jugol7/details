package com.details.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * Aes加解密工具类
 */
@Slf4j
public class AesUtil {

    private static final String AES_GCM_NOPADDING = "AES/GCM/NoPadding";
    private static final String UTF_8 = "UTF-8";

    public static void main(String[] args) {
        String name = "zlp";
        Object encryptAES = encryptAES(name, "1234567890abcdef", 1);
        System.out.println(encryptAES);
    }
    /**
     * AES加密
     *     type   1为utf-8 ，2为gbk
     * @param sSrc 待加密字符串
     * @param password 16位key
     * @param type 加密类型
     * @return
     * @throws Exception
     */
    public static Object encryptAES(String sSrc, String password,Integer type) {
        try {
            if (password == null) {
                throw new Exception();
            }
            // 判断Key是否为16位
            int len = 16;
            if (password.length() != len) {
                throw new Exception();
            }
            if(type == 1){
                byte[] raw = password.getBytes("utf-8");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance(AES_GCM_NOPADDING);
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
                byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
                return getBase64(encrypted);
            }else{
                byte[] raw = password.getBytes("gbk");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance(AES_GCM_NOPADDING);
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
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
     * @param sSrc
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
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
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance(AES_GCM_NOPADDING);
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
                //先用base64解密
                byte[] encrypted1 = getFromBase64(sSrc);
                byte[] original = cipher.doFinal(encrypted1);
                return new String(original, UTF_8);
            }else{
                byte[] raw = password.getBytes("gbk");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance(AES_GCM_NOPADDING);
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
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
        byte[] byEncry = new Base64().decode(base64EncryptConent);
        byte[] keyArray = new byte[16];
        byte[] tempKey = key.getBytes(encode);
        int minLenKey = Math.min(keyArray.length, tempKey.length);
        for (int i = 0; i < minLenKey; ++i)
            keyArray[i] = tempKey[i];
        Security.addProvider(new BouncyCastleProvider());
        SecretKeySpec sKey = new SecretKeySpec(keyArray, "AES");

        Cipher eCipher = Cipher.getInstance(AES_GCM_NOPADDING);
        eCipher.init(Cipher.DECRYPT_MODE, sKey);
        byte[] result = eCipher.doFinal(byEncry);
        int copyIndex = 0;
        for (int i = result.length - 1; i > -1; i--) {
            if (result[i] == 0x00) {
                copyIndex++;
            }

        }
        byte[] tmpresult = new byte[result.length - copyIndex];

        for (int i = 0; i < tmpresult.length; i++) {
            tmpresult[i] = result[i];
        }
        return new String(tmpresult, encode);

    }


}
