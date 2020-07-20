package com.details.leetcodeandnowcoder.algorithm;

import java.util.Arrays;

/***
 * 插入排序
 * @author zlp
 * @date 14:32 2020/4/1
 */
public class InsertSort {
    private int index;

    public static void main(String[] args) {
        int[] arr = {5, 44, 2, 22, 11, 67, 88, 34};
        int[] ints = insertSort2(arr);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] insertSort1(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int index = i;
            while (index > 0) {
                //感觉跟冒泡类似了
                if (arr[index] < arr[index - 1]) {
                    int temp = arr[index - 1];
                    arr[index - 1] = arr[index];
                    arr[index] = temp;
                }
                index--;
            }
        }
        return arr;
    }

    public static int[] insertSort2(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int index = i;
            //是否要进行插入的值
            int val = arr[i];
            while (index > 0 && arr[index] < arr[index - 1]) {
                arr[index] = arr[index - 1];
                arr[index - 1] = val;
                index--;
            }

        }
        return arr;
    }
}
