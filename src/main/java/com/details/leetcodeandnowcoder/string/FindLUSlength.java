package com.details.leetcodeandnowcoder.string;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 给你两个字符串，请你从这两个字符串中找出最长的特殊序列。
 * 「最长特殊序列」定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
 * 子序列 可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。空序列为所有字符串的子序列，任何字符串为其自身的子序列。
 * 输入为两个字符串，输出最长特殊序列的长度。如果不存在，则返回 -1。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-uncommon-subsequence-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class FindLUSlength {

    public static void main(String[] args) {
        FindLUSlength findLUSlength = new FindLUSlength();
//        String[] strs = {"aaa","aaa","aa"};
//        String[] strs = {"aba","cdc","eae"};
//        String[] strs = {"aaa","acb"};
        String[] strs = {"aabbcc", "aabbcc", "cb"};
        int lusLength = findLUSlength.findLUSLength(strs);
        System.out.println(lusLength);

    }

    /**
     * 简单来说 就是如果 两个string一样就返回 -1 否则就返回长的那个
     *
     * @param a
     * @param b
     * @return
     */
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        } else {
            return Math.max(a.length(), b.length());
        }
    }

    public int findLUSLength(String[] strs) {
        if (strs == null || strs.length == 0) {
            return -1;
        }
        ArrayList<Integer> len = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            len.add(strs[i].length());
        }
        //根据长度排序，
        ArrayList<String> lenRepeat = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        Iterator<Integer> iterator = len.iterator();
        int j = 0;
        while (iterator.hasNext()) {
            //利用set判断是否重复，
            boolean add = set.add(strs[j]);
            iterator.next();
            if (!add) {
                //顺便移除一一样的元素。  list 动态移除元素要借助迭代器
                //重复元素
                lenRepeat.add(strs[j]);
            }
            j++;
        }
//        Iterator<String> iterator1 = set.iterator();
        for (int k = 0; k < lenRepeat.size(); k++) {
            if (set.contains(lenRepeat.get(k))) {
                set.remove(lenRepeat.get(k));
            }
        }
        int max = -1;
        String[] objects = set.toArray(new String[set.size()]);
        if(objects.length == 1){
            return objects[0].length();
        }
        //是否为子串
        for (int i = 0; i < objects.length - 1; i++) {
            for (int k = i + 1; k < objects.length; k++) {
                if ((objects[i].indexOf(objects[k]) != -1 || (objects[k].indexOf(objects[i])) != -1)) {
                    break;
                }
                if (len.get(i) > max) {
                    max = len.get(i);
                }
            }
        }

        return max;
    }

    public boolean moreLong(String one, String two) {
        if (one.length() > two.length()) {
            return true;
        }
        return false;
    }

}
