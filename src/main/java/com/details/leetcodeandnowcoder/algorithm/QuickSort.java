package com.details.leetcodeandnowcoder.algorithm;

import java.util.Arrays;

/***
 * 快速排序
 * 原理：选择一个关键值作为基准值。比基准值小的都在左边序列（一般是无序的），
 * 比基准值大的都在右边（一般是无序的）。一般选择序列的第一个元素。
 * 一次循环：从后往前比较，用基准值和最后一个值比较，如果比基准值小的交换位置，如果没有
 * 继续比较下一个，直到找到第一个比基准值小的值才交换。找到这个值之后，又从前往后开始比
 * 较，如果有比基准值大的，交换位置，如果没有继续比较下一个，直到找到第一个比基准值大的
 * 值才交换。直到从前往后的比较索引>从后往前比较的索引，结束第一次循环，此时，对于基准值
 * 来说，左右两边就是有序的了。
 * @author zlp
 * @date 17:20 2020/3/26
 */
public class QuickSort {

    public static int count = 1;

    public static void main(String[] args) {
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4};
//        int [] arr = {10,7,2,4};
//        quickSort(0,arr.length-1,arr);
//        System.out.println("my ways:------"+Arrays.toString(arr));
        sort(arr, 0, arr.length);
        System.out.println("anyone ways:------" + Arrays.toString(arr));
    }

    public static void quickSort(int left, int right, int[] array) {
        if (left >= right) return;
        int start = left;
        int end = right;
        int flag = left;
        System.out.println("第" + (count++) + "次---------" + Arrays.toString(array));
        while (left < right) {
            while ((left < right) && (array[right] > array[flag])) {
                right--;
            }
            if (array[right] < array[flag]) {
                int tmp = array[right];
                array[right] = array[flag];
                array[flag] = tmp;
                flag = right;
            }
            while ((left < right) && (array[left] < array[flag])) {
                left++;
            }
            if (array[left] > array[flag]) {
                int tmp = array[left];
                array[left] = array[flag];
                array[flag] = tmp;
                flag = left;
            }
        }
        quickSort(start, left - 1, array);
        quickSort(left + 1, end, array);
    }

    public static void sort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];
        while (end > start) {
            //从后往前比较
            while (end > start && a[end] >= key)
                //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while (end > start && a[start] <= key)
                //如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的
            //值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low) sort(a, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
        if (end < high) sort(a, end + 1, high);//右边序列。从关键值索引+1 到最后一个
    }
}
