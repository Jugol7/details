package com.details.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 * "abcabcbb"   3  abc
 * "bbbbb"      1  b
 * "pwwkew"     3  wke
 *
 * @author zlp
 * @date 16:00  2019/11/21
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        String s = "zlpqwertyuiop";
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> list = lengthOfLongestSubstring(arrayList, s);
        HashMap<String, Integer> hashMap = new HashMap<>();
        int max = 0;
        for (int j = 0; j < list.size(); j++) {
            Integer end = list.get(j + 1);
            Integer start = list.get(j);
            int len = list.get(j + 1) - list.get(j);
            if (len > max) {
                hashMap.put("start", start);
                hashMap.put("end", end);
                max = len;
            }
            j++;
        }
        String substring = s.substring(hashMap.get("start"), hashMap.get("end"));
        System.out.println(substring);
    }

    static ArrayList<Integer> lengthOfLongestSubstring(ArrayList<Integer> list, String source) {
        int length = source.length();
        for (int j = 0; j < length - 1; j++) {
            StringBuffer stringBuffer = new StringBuffer();
            String everyChar = source.substring(j, j + 1);
            stringBuffer.append(everyChar);
            list.add(j);
            for (int i = j + 1; i < length; i++) {
                String next = source.substring(i, i + 1);
                if (stringBuffer.toString().contains(next)) {
                    list.add(i);
                    break;
                }
                stringBuffer.append(next);
            }
        }
        return list;
    }
}
