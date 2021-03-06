package com.details.leetcodeandnowcoder;

/**
 * 12
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
 * 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 3
 * 输出: "III"
 * 示例 2:
 * <p>
 * 输入: 4
 * 输出: "IV"
 * 示例 3:
 * <p>
 * 输入: 9
 * 输出: "IX"
 * 示例 4:
 * <p>
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * 示例 5:
 * <p>
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-to-roman
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zlp
 * @date 17:26  2019/12/17
 */
public class IntToRoman12 {

    private static final int[] ROMAN_NUM = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    private static final String[] ROMAN_STR = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

    public static void main(String[] args) {
        String s = intToRoman(1000);
        System.out.println(s);
    }

    /**
     * 不必如此复杂的
     *  可以使用  符号  - 来改变num的值。
     * @param num
     * @return
     */
    private static String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        if (num < 1 || num > 3999) {
            return null;
        }
        int len = ROMAN_NUM.length;
        while (num > 0) {
            for (int i = 1; i < len; i++) {
                if (num > ROMAN_NUM[ROMAN_NUM.length - 1]) {
                    int count = num / ROMAN_NUM[ROMAN_NUM.length - 1];
                    num = num % ROMAN_NUM[ROMAN_NUM.length - 1];
                    for (int j = 0; j < count; j++) {
                        result.append(ROMAN_STR[ROMAN_NUM.length - 1]);
                    }
                } else if (num < ROMAN_NUM[i]) {
                    int count = num / ROMAN_NUM[i - 1];
                    num = num % ROMAN_NUM[i - 1];
                    for (int j = 0; j < count; j++) {
                        result.append(ROMAN_STR[i - 1]);
                    }
                    len = i;
                }else if(num == ROMAN_NUM[i]){
                    num = num % ROMAN_NUM[i];
                    result.append(ROMAN_STR[i]);
                    len = i;
                }
            }
        }
        return result.toString();
    }

}
