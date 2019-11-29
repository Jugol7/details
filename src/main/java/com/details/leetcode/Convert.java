package com.details.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 *
 * @author zlp
 * @date 11:23  2019/11/29
 */
@Slf4j
public class Convert {

    public static void main(String[] args) {
        log.debug("----------------start-------------------");
        char[][] qwertyus = convert("qwertyu", 3);
        log.debug("----------------end-------------------");
        System.out.println(Arrays.toString(qwertyus));
    }

    /**
     * 分析：
     * 1.rowNums代表几行，那么第一行每两个字符之间的空格就是rowNums-2，递减1，到1时就重复一次递增1
     * 2.二维数组
     *
     * @param str
     * @param rowNum
     */
    public static char[][] convert(String str, int rowNum) {
        log.debug("请求参数str为：" + str + "     参数rowNum为：" + rowNum);
        int len = str.length() / (rowNum + rowNum - 2);
        char[][] arr = new char[rowNum][len];
        boolean flag = true;
        String strBak = str;
        while (flag) {
            int index = 0;
            if (strBak == null) {
                return null;
            }
            for (int j = 0; j < len; j++) {
                log.debug("进入第" + j + "次循环");
                for (int i = 0; i < arr.length; i++) {
                    //是rowNums的
                    if (rowNum > j ? rowNum % (j + 1) == 0 : (j + 1) % rowNum == 0) {
                        log.debug("进入无空格填充：" + i + "行" + j + "列");
                        //无空格
                        log.debug("---------------" + String.valueOf(str.charAt(index)));
                        arr[i][j] = str.charAt(index);
                        log.debug("strBak" + strBak);
                        strBak = strBak.substring(index++);
                    } else {
                        log.debug("进入有空格填充：" + i + "行" + j + "列");
                        if (j % 2 == 0) {
                            if (i + j == rowNum - 1) {
                                arr[rowNum - i - 1][j] = str.charAt(index);
                                strBak = strBak.substring(index++);
                            } else {
                                arr[i][j] = '*';
                            }
                        } else {
                            //第奇数轮
                            if (i + j == rowNum - 1) {
                                arr[i][j] = str.charAt(index);
                                strBak = strBak.substring(index++);
                            } else {
                                arr[i][j] = '*';
                            }
                        }
                    }
                    if (strBak == null) {
                        return arr;
                    }
                }
            }
        }
        return null;
    }
}
