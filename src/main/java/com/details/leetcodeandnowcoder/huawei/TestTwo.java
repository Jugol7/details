package com.details.leetcodeandnowcoder.huawei;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 数据库 like = “%” 模糊匹配实现
 *
 * @author zlp
 * @date 2020/7/21 18:11
 */
public class TestTwo {
    public static String fun(int value) {
        char[] m = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int len = m.length;
        int d = value / len;
        int r = value % len;
        return (d == 0) ? String.valueOf(m[r]) : fun(d) + m[r];
    }

    public static void main(String[] args) {
        String s3 = Arrays.asList("Hello", "World", "How", "Are", "You")
                .stream()
                .map(s -> "_" + s + "_")
                .reduce((s1, s2) -> s1 + "," + s2)
                .get();
//        System.out.println(s3);

//        System.out.println(fuzzyQuery("abc","ab%"));
//        System.out.println(fuzzyQuery("qabc","ab%"));
//        System.out.println(fuzzyQuery("bc","%bc"));
//        System.out.println(fuzzyQuery("bc1111","%bc"));
//        System.out.println(fuzzyQuery("abc","a%c"));
//        System.out.println(fuzzyQuery("a11c","a%c"));
//        System.out.println(fuzzyQuery("ac","a%c"));
        System.out.println(fuzzyQuery("abci","a%c"));
        System.out.println(fuzzyQuery("abc","%b%"));

    }

    /**
     * 数据库模糊查询实现 like %
     * @param target
     * @param standard
     */
    public static boolean fuzzyQuery(String target, String standard) {
        //%的个数，有一个或者两个
//        int count = 0;
        //存储含有两个%的，%的位置
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < standard.length(); i++) {
            if ('%' == standard.charAt(i)) {
//                count++;
                list.add(i);
            }
        }
        String replace = standard.replace("%", "");
        if (list.size() == 1) {
            //只有一个%
            int index = list.get(0);
            if (index == 0) {
                //头
                return head(target, replace);

            } else if (index == standard.length() - 1) {
                //尾
                return end(target, replace);

            } else {
                //中
                String[] split = standard.split("%");
                return head(target, split[1]) && end(target, split[0]);
            }
        } else {
            //两个%
            if (list.get(0) == 0 && list.get(1) == standard.length() - 1) {
                //全匹配
                return target.contains(replace);
            } else {
                if (list.get(0) == 0) {
                    String replace1 = standard.replace("%", "");
                    String[] split = replace1.split("%");
                    return head(target, split[0]) && end(target, split[1]);
                } else if (list.get(1) == standard.length() - 1) {
                    String substring = standard.substring(0, standard.length() - 2);
                    String[] split = substring.split("%");
                    return head(target, split[0]) && end(target, split[1]);
                } else {
                    String[] split = standard.split("%");
                    //   "dwqd % wd % wdw"
                    return end(target.substring(0, list.get(0)), split[0]) && target.substring(list.get(0), list.get(1)).contains(split[1]) && head(target.substring(list.get(1)), split[2]);
                }
            }
        }

    }

    private static boolean head(String target, String replace) {
        for (int i = target.length() - 1; i > replace.length(); i--) {
            if (!replace.contains(target.substring(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean end(String target, String replace) {
        for (int i = 0; i < replace.length(); i++) {
            if (!replace.contains(target.substring(0, i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 面试时写的，没写出来，真菜
     *
     * @param string1
     * @param string2
     * @return
     */
    public static boolean method(String string1, String string2) {
        if (StringUtils.isBlank(string1) || StringUtils.isBlank(string2)) {
            return false;
        }
        //1 %的位置
        int index = -1;
        int count = 0;
        for (int j = 0; j < string2.length(); j++) {
            if ('%' == string2.charAt(j)) {
                index = j;
                count++;
            }
        }
        if (count > 1) {
            index = 2;
        }
        String replace = string2.replace("%", "");
        boolean result = false;
        if (index == string2.length() - 1) {
            for (int j = 1; j <= string1.length(); j++) {
                if (!replace.contains(string1.substring(0, j))) {
                    return result;
                }
                if (replace.equals(string1.substring(0, j))) {
                    result = true;
                }
            }
        } else if (index == 0) {
            for (int j = string1.length(); j > 0; j--) {
                if (!replace.contains(string1.substring(j))) {
                    return result;
                }
                if (replace.equals(string1.substring(j))) {
                    result = true;
                }
            }
        } else if (index == 2) {


        } else {
            //全匹配
            result = string1.contains(replace);
        }
        return result;
    }

}
