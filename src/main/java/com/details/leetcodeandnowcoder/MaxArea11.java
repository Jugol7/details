package com.details.leetcodeandnowcoder;

import java.util.HashMap;
import java.util.Map;

/**
 * 11.
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * <p>
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zlp
 * @date 16:45  2019/12/16
 */
public class MaxArea11 {

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        Integer maxArea = maxArea(height);
        System.out.println(maxArea);
    }

    /**
     * 官方解答
     * O(n)  O(1)
     *
     * @param height
     * @return
     */
    public int maxAreaOffical(int[] height) {
        int maxarea = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            //谁的高度低，谁改变，这样使得后续的结构可能会更大
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxarea;
    }

    public static Integer maxArea(int[] height) {
        Integer area = 0;
        Integer result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < height.length; i++) {
            map.put(i + 1, height[i]);
        }
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                area = Math.min(map.get(i + 1), map.get(j + 1)) * (j - i);
                result = Math.max(area, result);
            }
        }
        return result;
    }
}
