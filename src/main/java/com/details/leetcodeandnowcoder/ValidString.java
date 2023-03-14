package com.details.leetcodeandnowcoder;

import java.util.HashMap;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ：zlp
 * @date ：2023/3/7 23:32
 * @version: 1.0
 */
public class ValidString {

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
    }

    public static boolean isValid(String s) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("(", ")");
        hashMap.put("{", "}");
        hashMap.put("[", "]");
        int len = s.length();
        for (int i = 0; i < len; i = i + 2) {
            String c = String.valueOf(hashMap.get(String.valueOf(s.charAt(i))));
            if (!c.equals(String.valueOf(s.charAt(i + 1)))) {
                return false;
            }
        }
        return true;
    }
}
