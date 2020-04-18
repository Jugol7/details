package com.details.algorithm;

import java.util.HashSet;

/***
 * @author zlp
 * @date 17:57 2020/4/16
 */
public class FindRepeat {

    public static void main(String[] args) {
        FindRepeat findRepeat = new FindRepeat();
        int[] arr = {2, 3, 1, 0, 2, 5, 3};
//        findRepeat.findRepeat(arr);
        findRepeat.findRepeatByNoModify(arr);

    }

    /**
     *  * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
     *  * 请找出数组中任意一个重复的数字。例如，如果输入长度为7的数组{2, 3, 1, 0, 2, 5, 3}，那么对应的输出是重复的数字2或者3。
     * @param arr
     */
    public void findRepeat(int[] arr){
        if(arr == null ||arr.length <= 0){
            System.out.println("空数组");
            return;
        }
        HashSet hashSet = new HashSet();
        for (int value : arr) {
            if (hashSet.contains(value)) {
                System.out.println(value);
            }
            hashSet.add(value);
        }
    }


    /**
     * 在一个长度为n+1的数组里的所有数字都在1到n的范围内，所以数组中至少有一个数字是重复的。请找出数组中任意一个重复的数字，
     * 但不能修改输入的数组。例如，如果输入长度为8的数组{2, 3, 5, 4, 3, 2, 6, 7}，那么对应的输出是重复的数字2或者3。
     *
     * 根据题意，可得，必定有重复的元素，可使用二分查找
     * @param arr
     */
    public void findRepeatByNoModify(int[] arr){
        if (arr == null || arr.length <= 0) {
            return;
        }
        int low = 0;
        int high = arr.length;
        while(low < high){
            int mid = (low+high)/2;



        }

    }
}
