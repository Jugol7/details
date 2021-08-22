package com.details.leetcodeandnowcoder.algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/path-crossing/solution/pan-duan-lu-jing-shi-fou-xiang-jiao-by-leetcode-so/
 * LeetCode上有无敌解法
 *
 * @Author zlp
 * @Description:
 * @Date 18:34:27 2021/8/18/0018
 */
public class CoordinatesSolution {

    public static void main(String[] args) {
        System.out.println(isPathCrossing1("NESNESNESNES"));
    }

    public static boolean isPathCrossing1(String path) {

        LinkedList<String> list = new LinkedList<>();
        Coordinates init = new Coordinates(0, 0);
        list.add(init.x + "" + init.y);
        char[] chars = path.toCharArray();
        Coordinates c = new Coordinates(0, 0);
        for (char aChar : chars) {
            switch (aChar) {
                case 'N':
                    c.setY(c.getY() + 1);
                    break;
                case 'S':
                    c.setY(c.getY() - 1);
                    break;
                case 'E':
                    c.setX(c.getX() + 1);
                    break;
                case 'W':
                    c.setX(c.getX() - 1);
                    break;
                default:
            }
            String s = c.x + "" + c.y;
            if (list.contains(s)) {
                return true;
            }
            list.add(s);
        }
        return false;

    }

    public static boolean isPathCrossing2(String path) {

        Set<String> set = new HashSet<>();
        int x = 0;
        int y = 0;
        set.add(x + "" + y);
        char[] chars = path.toCharArray();
        for (char aChar : chars) {
            switch (aChar) {
                case 'N':
                    y = y + 1;
                    break;
                case 'S':
                    y = y - 1;
                    break;
                case 'E':
                    x = x + 1;
                    break;
                case 'W':
                    x = x - 1;
                    break;
                default:
            }
            String s = x + "" + y;
            if (!set.add(s)) {
                return true;
            }
        }
        return false;
    }

    private static class Coordinates {
        int x;
        int y;

        private int getX() {
            return this.x;
        }


        private int getY() {
            return this.y;
        }


        private void setX(int x) {
            this.x = x;
        }


        private void setY(int y) {
            this.y = y;
        }

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}
