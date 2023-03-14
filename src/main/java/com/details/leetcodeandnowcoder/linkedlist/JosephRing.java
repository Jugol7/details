package com.details.leetcodeandnowcoder.linkedlist;

/**
 * @author ：zlp
 * @date ：2023/3/14 22:54
 * @version: 1.0
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static int 求最终胜利者下标(int n, int m) {
        if (n == 1) {
            return 0;
        }
        return (求最终胜利者下标(n - 1, m) + m) % n;
    }

}
