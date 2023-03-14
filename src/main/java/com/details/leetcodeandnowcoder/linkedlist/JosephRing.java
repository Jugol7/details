package com.details.leetcodeandnowcoder.linkedlist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 *
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 *
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 *
 * 作者：bigsai
 * 链接：https://leetcode.cn/circle/article/BOoxAL/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @author ：zlp
 * @date ：2023/3/14 22:54
 * @version: 1.0
 */
public class JosephRing {

    private static List<Integer> result = new ArrayList<>(100);

    public static void main(String[] args) {
        int i = 0;
        Set<Integer> set = new HashSet<>(100);
        while (result.size() < 100) {
            i += 7;
            if (i > 100) {
                i = i % 100;
            }
            if (set.contains(i)) {
                while (set.contains(i)) {
                    i++;
                }
            }
            result.add(i);
            set.add(i);
        }
    }

    public static int find(int n, int m) {
        if (n == 1) {
            return 0;
        }
        return (find(n - 1, m) + m) % n;
    }

}
