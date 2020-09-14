package com.details.leetcodeandnowcoder.algorithm;

import java.util.Arrays;

/***
 * 插入排序
 *  就是说把一个待排序的序列，分为一个有序和一个无序，一般有序的那个指的是第一个元素
 *  随后将后一个元素与有序序列中的相比较，是否需要交换位置，由此排序完成。
 * @author zlp
 * @date 14:32 2020/4/1
 */
public class InsertSort {
    private int index;

    public static void main(String[] args) {
        int[] arr = {5, 44, 2, 22, 11, 67, 88, 34};
        System.out.println(Arrays.toString(arr));
        int[] ints1 = insertSort1(arr);
        int[] ints2 = insertSort2(arr);
        System.out.println(Arrays.toString(ints1));
        System.out.println(Arrays.toString(ints2));
    }

    public static int[] insertSort1(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int index = i;
            while (index > 0) {
                //为了避免重复比较，如果比较无需变动，那么不需要再继续下去，因为前面的已经是有序的
                boolean flag = true;
                if (arr[index] < arr[index - 1]) {
                    int temp = arr[index - 1];
                    arr[index - 1] = arr[index];
                    arr[index] = temp;
                    flag = false;
                }
                index--;
                if (flag) {
                    break;
                }
            }
        }
        return arr;
    }

    public static int[] insertSort2(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
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
