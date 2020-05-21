package com.details.leetcodeandnowcoder;

/**
 * 7
 * 整数反转
 * 123  ----> 321
 * -123 ----> -321
 * 120 -----> 21
 *
 * @author zlp
 * @date 17:48  2019/12/2
 */
public class IntegerReverse {

    public static void main(String[] args) {
        int integer = integerReverse(534236469);
        System.out.println(integer);

    }

    public static Integer reverse(int x) {
        System.out.println(x);
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            //以Integer的最大值与最小为边界点
            //1.最大值除10，得到前几位的数，如果所输入的值大，或者等于那么他就必须得小于7，否则返回0
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            //2.最小值除10，得到得前几位的数，如果所输入的值小，或者等于那么他就必须大于-8，否则返回0
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /**
     * Integer.valueOf(此值不能超过范围，否则报错)
     * @param x
     * @return
     */
    public static int integerReverse(Integer x) {
        //取模  取整
        if (x == 0) {
            return 0;
        }
        int num1 = x;
        StringBuffer stringBuffer = new StringBuffer();
        String sign = "";
        if (x < 0) {
            sign = "-";
            num1 = -num1;
        }
        while (num1 > 0) {
            int i = num1 % 10;
            stringBuffer.append(i);
            num1 /= 10;
        }
        String s = stringBuffer.toString();
        if (!cutZero(s)) {
            cutZero(s);
        }
        Integer result = Integer.valueOf(s);
        if (!"".equals(sign)) {
            result = -result;
        }
        return result;
    }

    public static boolean cutZero(String num) {
        if ("0".equals(num.charAt(0))) {
            num.substring(1);
            return false;
        }
        return true;
    }
}
