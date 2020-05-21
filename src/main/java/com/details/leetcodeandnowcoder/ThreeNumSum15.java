package com.details.leetcodeandnowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zlp
 * @date 17:16  2019/12/25
 */
public class ThreeNumSum15 {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums1 = {-2,0,1,1,2};
        int[] nums0 = {0,0,0,0};
        List<List<Integer>> lists = threeSum(nums1);
        System.out.println(lists.size());
    }

    /**
     * 选定两个元素，那么只存在一组数据符合，因为不重复
     * 而且只能往后找
     * 排序
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if(nums == null || nums.length < 3){
            return lists;
        }
        Arrays.sort(nums);
        for (int i = 0,j=i+1; i < nums.length-2;j++) {
            int l = nums.length>j+1?j+1:nums.length;
            if(nums[i] + nums[i+1] + nums[l] == 0){
                List<Integer> listInside = new ArrayList<>();
                listInside.add(nums[i]);
                listInside.add(nums[i+1]);
                listInside.add(nums[l]);
                if(!lists.contains(listInside)){
                    lists.add(listInside);
                }
            }
            if(j == nums.length - 2){
                i++;
                j=i;
            }
        }
        return lists;
    }


}
