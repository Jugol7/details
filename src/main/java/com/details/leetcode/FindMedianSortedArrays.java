package com.details.leetcode;

import java.util.Arrays;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * @author zlp
 * @date 17:21  2019/11/22
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        int[] arr1 = {1, 2};
        int[] arr2 = {3, 4};
        Arrays.sort(arr1);
        Arrays.sort(arr2);

    }

    public static void findMedianSortedArrays(int[] arr1, int[] arr2) {
            //有序数组

    }
}
