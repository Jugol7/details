package com.details.leetcode;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 8
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * 示例 1:
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * 示例 5:
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−231) 。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 1.第一个非空字符为数字或者正负符号
 * 2.如果超出范围 Integer.MAX/MIN 的返回则返回 MAX/MIN
 *
 * @author zlp
 * @date 17:44  2019/12/3
 */
public class MyAtoi {
    public static void main(String[] args) {

        String s1 = "-1234";
        String s2 = "+1234";
        String s3 = "12345678765432456";
        String s4 = "dwqdqw123456";
        String s5 = "    23456";
        String s6 = "-1234567876543456765123456";
        String s7 = "-";
        int method = lastestMethod(s4);
        System.out.println(method);

    }


    public static int method(String str) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        str.trim();
        if (!(45 == (str.charAt(0)) || '+' == str.charAt(0))) {
            return 0;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str.charAt(0));
        String regex = "[0-9]{1,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            stringBuffer.append(group);
        }
        //如果所得字符串的长度超过整型的最大最小值，则会报错
        try {
            return Integer.valueOf(stringBuffer.toString());
        } catch (Exception e) {
            return 0;
        }
    }


    public static int lastestMethod(String str) {
        //1 判断非空
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        //去空格
        str = str.trim();
        //如果只是一个正负号
        if ("-".equals(str) || "+".equals(str)) {
            return 0;
        }
//        //如果是以
        String sss = "[0-9-+]";
//        if (!String.valueOf(str.charAt(0)).matches(sss)) {
//            return 0;
//        }
        String regex = "[0-9]{1,}";
//        if (!regex.matches(str)) {
        if (!str.matches(regex)) {
            StringBuffer stringBuffer = new StringBuffer();
            //含有非数字字符处理
            for (int i = 0; i < str.length(); i++) {
                String c = String.valueOf(str.charAt(i));
                if (!c.matches(sss)) {
                    break;
                }
                stringBuffer.append(c);
            }
            str = stringBuffer.toString();
        }
        int result = 0;
        try {
            if (!StringUtils.isBlank(str)) {
                result = Integer.valueOf(str);
            }
        } catch (Exception e) {
            if ('-' == str.charAt(0)) {
                return -2147483648;
            }
            return 2147483647;
        }
        return result;
    }


    public static int myAtoi(String str) {
        //1 判断非空
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        //判断符号 + -
        char head = '+';
//        if ("-".equals(String.valueOf(str.charAt(0)))){
        if ('-' == str.charAt(0)) {
            head = str.charAt(0);
//            str = str.substring(1);
        }
        //判断溢出 处理异常
        if (str.length() > 11) {
            //需要在这里判断吗，还是捕捉异常
        }
        String regex = "[-+][0-9]";
        if (!regex.matches(str)) {
            //含有非数字字符处理
            return 0;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            //
            if ("-".equals(str.charAt(0)) || "+".equals(str.charAt(0)) || regex.matches(String.valueOf(str.charAt(0)))) {
                //开始取值
                stringBuffer.append(str.charAt(0));
                str.substring(1);
                for (int i = 0; i < str.length(); i++) {
                    if (regex.matches(String.valueOf(str.charAt(i)))) {
                        stringBuffer.append(str.charAt(i));
                    }
                }
                //如果超过范围  则取最大小值
                String string = stringBuffer.toString();
                if ("-".equals(string.charAt(0))) {
                    //
                    System.out.println();
                }
                if ('-' == string.charAt(0)) {
                    //
                    System.out.println();
                }
                return Integer.valueOf(stringBuffer.toString());
            }
        }
    }

    /**
     * 将字符串中前面的空格删除
     *
     * @param s
     * @return
     */
    private static int getFirstNotNull(String s) {
        if (Character.isWhitespace(s.charAt(0))) {
            getFirstNotNull(s.substring(1));
        }
        return 0;
    }

}
