package com.details.algorithm;

import java.util.Arrays;

/***
 * 快速排序
 * @author zlp
 * @date 17:20 2020/3/26
 */
public class QuickSort {

    public static int count = 1;

    public static void main(String[] args) {
//        int [] arr = {10,7,2,4,7,62,3,4};
        int [] arr = {10,7,2,4};
        quickSort(0,arr.length-1,arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int left,int right,int[] array) {
        if (left>=right) return ;
        int start=left;
        int end=right;
        int flag=left;
        System.out.println("第"+(count++)+"次---------"+Arrays.toString(array));
        while(left<right) {
            while ((left<right)&&(array[right]>array[flag])) {
                right--;
            }
            if (array[right]<array[flag]) {
                int tmp=array[right];
                array[right]=array[flag];
                array[flag]=tmp;
                flag=right;
            }
            while ((left<right)&&(array[left]<array[flag])) {
                left++;
            }
            if (array[left]>array[flag]) {
                int tmp=array[left];
                array[left]=array[flag];
                array[flag]=tmp;
                flag=left;
            }
        }
        quickSort(start, left-1, array);
        quickSort(left+1, end, array);
    }


}
