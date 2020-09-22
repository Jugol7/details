package com.details.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

import java.util.Arrays;

/**
 *
 * 测试开元工具  HuTool
 * https://hutool.cn/
 *
 * @Author zlp
 * @Description:
 * @Date 17:19:51 2020/9/22/0022
 */
public class TestHuTool {


    public static void main(String[] args) {

        methodAes();
        stringToDate();

    }

    public static void methodAes(){

        AES aes = new AES(Mode.CTS, Padding.NoPadding);

        String zlp = aes.encryptBase64("at cn.hutool.crypto.symmetric.SymmetricCrypto.encrypt(SymmetricCrypto.java:209)\n" +
                "\tat cn.hutool.crypto.symmetric.SymmetricCrypto.encrypt(SymmetricCrypto.java:312)\n" +
                "\tat cn.hutool.crypto.symmetric.SymmetricCrypto.encryptBase64(SymmetricCrypto.java:332)\n" +
                "\tat com.details.utils.TestHuTool.methodAes(TestHuTool.java:31)\n" +
                "\tat com.details.utils.TestHuTool.main(TestHuTool.java:23)\n" +
                "Caused by: javax.crypto.IllegalBlockSizeException: need at least one block of");

        System.out.println(zlp);

    }

    public static void stringToDate(){

        String strDate = "2020.09.21";

        DateTime parse = DateUtil.parse(strDate);

        System.out.println(parse);

    }



}
