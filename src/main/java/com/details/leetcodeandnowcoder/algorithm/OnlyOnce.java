package com.details.leetcodeandnowcoder.algorithm;

/***
 * 一个数组中 只出现一次的数字  且只有一个  其他数字均出现两次
 * @author zlp
 * @date 17:31 2020/3/26
 */
public class OnlyOnce {

    public static void main(String[] args) {
        int [] arr = {4,1,2,1,2};
//        int [] arr = {2,2,1};
//        int [] arr = {1,0,1};
//        int method = method(arr);
//        System.out.println(method);
        int t = t(arr);
        System.out.println(t);
    }

    /**
     * leetcode解法
     * @param nums
     * @return
     */
    public static int t(int[] nums){
        int ans = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                ans = ans ^ nums[i];
            }
        }
        return ans;
    }
    public static Integer method(int [] arr){
        int sum = 0;
        for (int i = 0; i < arr.length-1;) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i] != arr[j]){
                    sum++;
                }else {
                    i = j+1;
                }
            }
            if(sum == arr.length-1){
                return arr[i];
            }
        }
        return arr[arr.length-1];
    }
}
