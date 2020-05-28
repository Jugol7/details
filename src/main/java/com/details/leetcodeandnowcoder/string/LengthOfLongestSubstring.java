package com.details.leetcodeandnowcoder.string;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 *
 *      "dvdf"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author zlp
 * @Date 2020/5/27 19:33
 **/
@Slf4j
public class LengthOfLongestSubstring {

    private static final Logger logger = LoggerFactory.getLogger(LengthOfLongestSubstring.class);

    public static void main(String[] args) {
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        String str = "dvdf";
        int length = lengthOfLongestSubstring.lengthOfLongestSubstring(str);
        logger.info("最长子串为的长度是：{}",length);
    }


    public int lengthOfLongestSubstring(String s) {
        if(StringUtils.isBlank(s)){
            return 0;
        }
        char[] chars = s.toCharArray();
        logger.info("接到的参数为：{}",s);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(chars[0]);
        int max = 0;
        for (int i = 1; i < chars.length; i++) {
            int len = stringBuilder.indexOf(String.valueOf(chars[i]));
            if(len != -1){
                //代表当前的字符与前面那个string有重复
                if(max < stringBuilder.length()){
                    max = stringBuilder.length();
                }
                //重复的那个字符的下一个作为下一个潜在目标
                stringBuilder = new StringBuilder(stringBuilder.substring(len+1));
            }
            stringBuilder.append(chars[i]);
        }
        if(max < stringBuilder.length()){
            max = stringBuilder.length();
        }
        return max;
    }


}
