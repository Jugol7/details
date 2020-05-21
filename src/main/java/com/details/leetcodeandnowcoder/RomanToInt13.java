package com.details.leetcodeandnowcoder;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转换成整型
 *
 * @author zlp
 * @date 16:06  2019/12/19
 */
public class RomanToInt13 {

    private static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
    }

    private static int romanToInt(String s) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        int sum = 0;
        while (!"".equals(s) && s.length() > 1) {
            int sub = 1;
            if (map.get(String.valueOf(s.charAt(0))) < map.get(String.valueOf(s.charAt(1)))) {
                sub = 2;
            }

            if (map.containsKey(s.substring(0, sub))) {
                int num = map.get(s.substring(0, sub));
                sum += num;
                s = s.substring(sub);
            }
        }
        if (s.length() == 1) {
            sum += map.get(s);
        }
        return sum;
    }

}
