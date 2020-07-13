package com.details.leetcodeandnowcoder.huawei;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Scanner;

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
//        testFindString();
        eightArray("");

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
            if (regex == (str.charAt(i))) {
                result++;
            }
        }
        return result;
    }

    /**
     * 连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
     * 长度不是8整数倍的字符串请在后面补数字0，空字符串不处理
     *
     * @param string
     */
    public static void eightArray(String string) {
        Scanner scanner = new Scanner(System.in);
        //这个输入的数字，长度大8，小8，等8 三种情况，空串另外考虑
        while (scanner.hasNext()) {
            String str = scanner.next();
            if (StringUtils.isBlank(str)) {
                return;
            }
            int len = str.length();
            if (len == 8) {
                String[] strings = new String[8];
                logger.info("长度为8的字符串：{}", str);
                logger.info("接下来转换成字符串数组：");
                for (int i = 0; i < len; i++) {
                    strings[i] = String.valueOf(str.charAt(i));
                }
                logger.info("字符串数组如下：{}", Arrays.toString(strings));
            } else if (len < 8) {
                logger.info("长度小于8的字符串：{}", str);
                String[] strings = new String[8];
                for (int i = 0; i < strings.length; i++) {
                    if (i + 1 < len) {
                        strings[i] = String.valueOf(str.charAt(i));
                    } else {
                        strings[i] = "0";
                    }
                }
                logger.info("字符串数组如下：{}", Arrays.toString(strings));
            } else {
                logger.info("长度大于8的字符串：{}", str);
                int count = len / 8;
                //需要count+1个数组
                for (int i = 0; i < count + 1; i++) {
                    String[] strings = new String[8];
                    if (i == count) {
                        for (int j = 0; j < 8; j++) {
                            int rest = len%8;
                            if(rest == 0){
                                break;
                            }
                            if(j < rest){
                                strings[j] = String.valueOf(str.charAt(i * 8 + j));
                            }else {
                                strings[j] = "0";
                            }
                        }
                        logger.info("第 {} 个数组，字符串数组如下：{}", i+1, Arrays.toString(strings));
                    }else {
                        for (int j = 0; j < 8; j++) {
                            strings[j] = String.valueOf(str.charAt(i * 8 + j));
                        }
                        logger.info("第 {} 个数组，字符串数组如下：{}", i+1, Arrays.toString(strings));
                    }
                }
            }
        }
    }

    /**
     * https://www.nowcoder.com/questionTerminal/d9162298cb5a437aad722fccccaae8a7
     * 牛客网大佬写的代码
     */
    public static void eightArrayNiuKe(){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            if(s.length()%8 != 0){
                //如果需要补0，直接加8个0
                s = s+"00000000";
            }
            while(s.length() >= 8){
                //\输出前八个字符
                System.out.println(s.substring(0,8));
                //将前八个字符干掉
                s = s.substring(8);
            }
        }

    }


}
