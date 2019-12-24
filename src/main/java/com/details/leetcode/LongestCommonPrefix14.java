package com.details.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

/**
 * 14
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zlp
 * @date 16:20  2019/12/20
 */
@Slf4j
public class LongestCommonPrefix14 {

    private static final Logger logger = LoggerFactory.getLogger(LongestCommonPrefix14.class);

    public static void main(String[] args) {
//        String[] strs = {"flower","flow","flight"};
//        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {

        String regex = "^[a-zA-Z]+$";
        int end = Integer.MAX_VALUE;
        for (String str : strs) {
//            if (!regex.matches(str)) {
//                return "所输入的必须是字母";
//            }
            end = Math.min(end, str.length());
        }
        System.out.println(end);
        int start = 0;
        while (start < end) {
            int mid = 0;
            String str0 = strs[0].substring(0, end);
            for (int i = 1; i < strs.length; i++) {
                mid = start + end / 2;
                if (str0.charAt(mid) != strs[i].charAt(mid)) {
                    end = mid + 1;
                    continue;
                }
                start = mid - 1;
            }
        }
        return strs[0].substring(0,end);
    }
}
