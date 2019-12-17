package com.details.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zlp
 * @date 18:03  2019/12/16
 */
@Slf4j
public class IsMatch10 {

    public static void main(String[] args) {

        Integer min = Integer.MIN_VALUE;
        Integer max = Integer.MAX_VALUE;
        System.out.println(max);
        System.out.println(min);
        System.out.println(Math.abs(min));

//        isMatch("qwer","qwert*uuu*uuu..");
    }

    public static void isMatch(String s, String p) {

        String replace = p.replace("*", "");
        String replace1 = replace.replace(".", "");
        System.out.println(replace1);
        System.out.println(s);

    }
}
