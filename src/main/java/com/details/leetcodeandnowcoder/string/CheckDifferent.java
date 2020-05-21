package com.details.leetcodeandnowcoder.string;


/**
 * 请实现一个算法，确定一个字符串的所有字符是否全都不同。这里我们要求不允许使用额外的存储结构。
 * 给定一个string iniString，请返回一个bool值,True代表所有字符全都不同，False代表存在相同的字符。保证字符串中的字符为ASCII字符。字符串的长度小于等于3000。
 */
public class CheckDifferent {

    /**
     * 正则匹配
     * @param iniString
     * @return
     */
    public boolean checkDifferentRegex(String iniString) {
        return !iniString.matches(".*(.)(.*\\1).*");
    }

    /**
     * 思路，便利每一个字符，将每一个字符与此字符的后面的字符相比较是否相等，indexOf()
     * @param iniString
     * @return
     */
    public boolean checkDifferent(String iniString) {
        for (int i = 0; i < iniString.length(); i++) {
            char c = iniString.charAt(i);
            String str = iniString.substring(i+1);
            if(str.indexOf(c) != -1){
                return false;
            }
        }
        return true;
    }

}
