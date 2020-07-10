package com.details.leetcodeandnowcoder.huawei;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 华为笔试算法题--String篇
 *
 * @author zlp
 * @date 2020/7/10 21:28
 */
@Slf4j
public class AboutStrings {

    private static final Logger logger = LoggerFactory.getLogger(AboutStrings.class);

    public static void main(String[] args) {

//        testTheLengthOfLastWord();
        testFindString();

    }

    /**
     * 测试用例
     */
    public static void testTheLengthOfLastWord() {
        String str0 = "sdf sdfs sdfsd sdfsdf 9.";
        int i = theLengthOfLastWord(str0);
        if (i == -1) {
            logger.error("输入字符串 {} 有误", str0);
        } else {
            logger.info("字符串 {} 中，隔开后的最后一个字符的长度是：{}", str0, i);
        }
    }

    /**
     * 1、计算字符串最后一个单词的长度，单词以空格隔开
     *
     * @param str
     * @return
     */
    public static int theLengthOfLastWord(String str) {
        int result = -1;
        if (StringUtils.isBlank(str)) {
            return result;
        }
        String[] split = str.split(" ");
        if (split.length <= 0) {
            return result;
        }
        result = split[split.length - 1].length();
        return result;
    }

    /**
     * 测试用例
     */
    public static void testFindString() {
        String str = "asdasd,dasd,asdasd,asdasdasd,";
        char regex = ',';
        int count = findString(str, regex);
        logger.info("字符串 {} 中包含 {} 个 {}", str, count, regex);
    }

    /**
     * 2、写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写
     *
     * @param str   目标字符串
     * @param regex 匹配对象
     */
    public static int findString(String str, char regex) {
        int result = 0;
        if (StringUtils.isBlank(str)) {
            return result;
        }
        for (int i = 0; i < str.length(); i++) {
//            logger.info("{}",str.charAt(i));
            if(regex == (str.charAt(i))){
                result++;
            }
        }
        return result;
    }


}
