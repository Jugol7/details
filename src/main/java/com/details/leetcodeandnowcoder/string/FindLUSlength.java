package com.details.leetcodeandnowcoder.string;

import com.sun.xml.internal.fastinfoset.util.StringIntMap;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 给你两个字符串，请你从这两个字符串中找出最长的特殊序列。
 * 「最长特殊序列」定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
 * 子序列 可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。空序列为所有字符串的子序列，任何字符串为其自身的子序列。
 * 输入为两个字符串，输出最长特殊序列的长度。如果不存在，则返回 -1。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-uncommon-subsequence-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindLUSlength {

    /**
     *  简单来说 就是如果 两个string一样就返回 -1 否则就返回长的那个
     * @param a
     * @param b
     * @return
     */
    public int findLUSlength(String a, String b) {
        if(a.equals(b)){
            return -1;
        }else {
            return Math.max(a.length(),b.length());
        }
    }

    public int findLUSLength(String[] strs){
        if(strs == null || strs.length == 0){
            return  -1;
        }
        ArrayList<Integer> len = new ArrayList<>();
        //根据长度排序，
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < strs.length; i++) {
            //set不存储重复的元素
            boolean add = set.add(strs[i]);
            if(!add){
                //顺便移除一一样的元素。  list 动态移除元素要借助迭代器
                len.remove(strs[i].length());
            }
            len.add(strs[i].length());
        }
        int max = 0;
        for (int i = 0; i < len.size(); i++) {
            if(len.get(i) > max){
                max = len.get(i);
            }
        }
        return max;
    }

    public boolean moreLong(String one, String two){
        if(one.length() > two.length()){
            return true;
        }
        return false;
    }

}
