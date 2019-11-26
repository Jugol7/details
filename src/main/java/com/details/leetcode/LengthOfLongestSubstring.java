package com.details.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
//        HashSet<Integer> set = new HashSet<Integer>((Collection<? extends Integer>) hashMap);
        int max = 0;
        for (int j = 1; j < list.size(); j++) {
            Integer end = list.get(j);
            Integer start = list.get(j-1);
            int len = list.get(j) - list.get(j-1);
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

    /**
     * 最长子串,最初版
     * @param list
     * @param source
     * @return
     */
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

    /**
     * leetcode
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

}
