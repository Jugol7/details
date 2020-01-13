package com.details.leetcode;

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

        int[] nums = {1,1,-1,-1,3};
        int target = -1;
        System.out.println(threeSumClosest(nums, target));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0]+nums[1]+nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length;i++) {
            int s = i+1;
            int e = nums.length-1;
            while(s < e){
                //当前结果与target比较
                int temp = nums[i]+nums[s]+nums[e];
                if(Math.abs(temp-target) < Math.abs(result - target)){
                    result = temp;
                }
                if(result > target){
                    e--;
                }else {
                    s++;
                }
            }
        }
        return result;
    }
}
