package com.details.utils;

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
public class AesUtil {

    private final static String PASSWORD = "hx+aisino+201711";


    /**
     * AES加密
     *     type   1为utf-8 ，2为gbk
     * @param sSrc 待加密字符串
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
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
                byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
                return getBase64(encrypted);
            }else{
                byte[] raw = password.getBytes("gbk");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
                byte[] encrypted = cipher.doFinal(sSrc.getBytes("gbk"));
                return getBase64(encrypted);
            }
        } catch (Exception e) {
            // TODO: handle exception
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
                byte[] raw = password.getBytes("UTF-8");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
                //先用base64解密
                byte[] encrypted1 = getFromBase64(sSrc);
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            }else{
                byte[] raw = password.getBytes("gbk");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
                //先用base64解密
                byte[] encrypted1 = getFromBase64(sSrc);
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "gbk");
                return originalString;
            }
        }catch (Exception e){
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
        return b;
    }

    public static String Base64AndAESDecrypt128(String base64EncryptConent, String key, String keyIV, String encode) throws Exception {
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

        Cipher eCipher = Cipher.getInstance("AES/ECB/NoPadding");
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

        String decryContent = new String(tmpresult, encode);
        return decryContent;

    }


}