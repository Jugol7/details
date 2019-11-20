package com.details.leetcode;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @author zlp
 * @date 18:15  2019/11/20
 */
public class TwoNumSum {

    public static void main(String[] args) {
        int[] arr = {2, 1, 7, 7};
        int target = 9;
        int[] sumFromArray = getSumFromArray(arr, target);
//        int[] sumFromArray = getSum(arr, target);
        System.out.println(sumFromArray[0]);
        System.out.println(sumFromArray[1]);
    }

    /**
     * 时间复杂度：O(n^2)O(n2 )，
     * 对于每个元素，我们试图通过遍历数组的其余部分来寻找它所对应的目标元素，这将耗费 O(n)O(n) 的时间。因此时间复杂度为 O(n^2)O(n2)。
     * 空间复杂度：O(1)O(1)。
     *
     * @param arr
     * @param target
     * @return
     */
    public static int[] getSumFromArray(int[] arr, int target) {
        int[] result = new int[2];
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        throw new IllegalArgumentException("no two num like this");
    }

    /**
     * 采用哈希
     *
     * @param arr
     * @param target
     * @return
     */
    public static int[] getSum(int[] arr, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = target - arr[i];
            if (hashMap.containsKey(temp)) {
                return new int[]{hashMap.get(temp), i};
            }
            hashMap.put(arr[i], i);
        }
        throw new IllegalArgumentException("no two num like this");
    }
}
