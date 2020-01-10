package com.details.leetcode;

import com.details.entity.user;

import java.util.Arrays;

/**
 * 16
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
 * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zlp
 * @date 16:41  2019/12/26
 */
public class ThreeSumClosest16 {

    public static void main(String[] args) {

        int[] nums = {-1,2,1,-4};
        int[] nums1 = {0,2,1,-3};
        int target = 1;
        System.out.println(threeSumClosest(nums1, target));
    }

    public static int threeSumClosest(int[] nums, int target) {
        int result = 0;
        int min = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2;i++) {
            int bakResult = nums[i] + nums[i + 1] + nums[i + 2];
            int tmp = Math.abs(target - bakResult);
            if(tmp == 0){
                return bakResult;
            }
            if(min > tmp){
                result = bakResult;
                min = tmp;
            }
        }
        return result;
    }
}
