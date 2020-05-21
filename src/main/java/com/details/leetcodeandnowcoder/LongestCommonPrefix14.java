package com.details.leetcodeandnowcoder;

import lombok.extern.slf4j.Slf4j;

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

    public static void main(String[] args) {

//        String[] strs = {"flower", "flow", "floight"};
//        System.out.println(longestCommonPrefix(strs));
        String[] s = {"a"};
        String s1 = longestCommonPrefixLeet(s);
        System.out.println(s1);

    }


    public static String longestCommonPrefixLeet(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs)
            minLen = Math.min(minLen, str.length());
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle))
                low = middle + 1;
            else
                high = middle - 1;
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private static boolean isCommonPrefix(String[] strs, int len){
        String str1 = strs[0].substring(0,len);
        for (int i = 1; i < strs.length; i++)
            if (!strs[i].startsWith(str1))
                return false;
        return true;
    }

    public static String longestCommonPrefix2(String[] strs) {
        int end = Integer.MAX_VALUE;
        for (String str : strs) {
            end = Math.min(end, str.length());
        }
        int start = 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            String str0 = strs[0].substring(0, end);
            for (int i = 1; i < strs.length; i++) {
//                if (str0.charAt(mid) != strs[i].charAt(mid)) {
                if (!strs[i].startsWith(str0)) {
                    end = mid - 1;
                    break;
                }
                start = mid + 1;
            }
        }
        return strs[0].substring(0, mid);
    }

    public static String longestCommonPrefix(String[] strs) {
        int end = Integer.MAX_VALUE;
        for (String str : strs) {
            end = Math.min(end, str.length());
        }
        int start = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if(!isCommonPrefix(strs,mid)){
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }
        return strs[0].substring(0, end);
    }

    private static boolean isSame(String[] strings, int mid){
        String str0 = strings[0].substring(0, mid+1);
        for (int i = 1; i < strings.length; i++) {
            if (str0.charAt(mid) != strings[i].charAt(mid)) {
                return false;
            }
        }
        return true;
    }
}
