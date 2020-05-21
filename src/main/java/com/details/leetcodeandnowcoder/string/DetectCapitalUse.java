package com.details.leetcodeandnowcoder.string;

/**
 * 给定一个单词，你需要判断单词的大写使用是否正确。
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 * 全部字母都是大写，比如"USA"。
 * 单词中所有字母都不是大写，比如"leetcode"。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
 * 否则，我们定义这个单词没有正确使用大写字母。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/detect-capital
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DetectCapitalUse {

    public boolean detectCapitalUse(String word) {

        String regex = "[a-zA-Z]{1,}";
        if(!word.matches(regex)){
            return false;
        }
        String regexAZ = "[A-Z]{1,}";
        String regexaz = "[a-z]{1,}";
        if(word.matches(regexAZ) || word.matches(regexaz)){
            return true;
        }
        String regexAaz = "[A-Z][a-z]{0,}";
        if(word.matches(regexAaz)){
            return true;
        }
        return false;
    }

}
