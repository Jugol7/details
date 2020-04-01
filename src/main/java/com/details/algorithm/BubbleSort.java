package com.details.algorithm;

import java.util.Arrays;

/***
 * 冒泡排序
 * @author zlp
 * @date 17:24 2020/3/30
 */
public class BubbleSort {

    public static void main(String[] args) {
        int [] arr = {2,4,6,9,1,6,4,2};
        System.out.println(Arrays.toString(arr));
        int[] ints = bubbleSort(arr);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j-1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        return arr;
    }


}
