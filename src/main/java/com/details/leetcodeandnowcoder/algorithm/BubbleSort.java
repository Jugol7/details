package com.details.leetcodeandnowcoder.algorithm;

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
        int[] ints = chickWireSort(arr);
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


    public static int[] sort(int[] arr){
        //判断是否存在调换位置
        boolean change = true;
        //有序边界值，如果后面的元素是有序的，只比较到这
        int sortBorder = arr.length - 1;
        int lastChangeIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < sortBorder; j++) {
                if(arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    change = false;
                    lastChangeIndex = j;
                }
            }
            sortBorder = lastChangeIndex;
            if(change){
                break;
            }
        }
        return arr;
    }

    /**
     * 适用于在已有部分数据为有序
     * 代码量增加了一倍
     * @param arr
     * @return
     */
    public static int[] chickWireSort(int[] arr){

        int leftChangeIndex = 0;
        int rightChangeIndex = 0;
        int rightBorder = arr.length - 1;
        int leftBorder = 0;
        for (int i = 0; i < arr.length; i++) {
            boolean change = true;
            for (int j = leftBorder; j < rightBorder; j++) {
                if(arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    change = false;
                    rightChangeIndex = j;
                }
            }
            rightBorder = rightChangeIndex;
            if(change){
                break;
            }

            for (int j = rightChangeIndex; j > leftBorder; j--) {
                if(arr[j] < arr[j-1]){
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                    change = false;
                    leftChangeIndex = j;
                }
            }
            leftBorder = leftChangeIndex;
            if(change){
                break;
            }
        }
        return arr;
    }

}
