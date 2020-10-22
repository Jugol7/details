package com.details.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试开元工具  HuTool
 * https://hutool.cn/
 *
 * @Author zlp
 * @Description:
 * @Date 17:19:51 2020/9/22/0022
 */
@Slf4j
public class TestHuTool {

    private final static Logger LOGGER_TEST_HU_TOOL = LoggerFactory.getLogger(TestHuTool.class);

    public static void main(String[] args) {

        methodAes();
        stringToDate();

    }

    public static void methodAes() {

        AES aes = new AES(Mode.CTS, Padding.NoPadding);

        String zlp = aes.encryptBase64("SymmetricCrypto.encrypt");

        LOGGER_TEST_HU_TOOL.info(zlp);

    }

    public static void stringToDate() {

        String strDate = "";

        DateTime parse = DateUtil.parse(strDate);

        LOGGER_TEST_HU_TOOL.info("转换之后的日期为：{}", parse);

    }

    /**
     * 加密
     *
     * @author zlp
     * @date 2020/10/22/0022 16:01:55
     */
    public static void encrypt() {

    }

    /**
     * 加密
     *
     * @author zlp
     * @date 2020/10/22/0022 16:01:55
     */
    public static void decrypt() {


    }


}
