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
        quickSort1(0,arr.length-1,arr);
        System.out.println("my ways:------"+Arrays.toString(arr));
//        sort(arr, 0, arr.length);
//        System.out.println("anyone ways:------" + Arrays.toString(arr));
    }

    /**
     * 重写修改，将第一个数作为基准数，一次排序完成，将基准值归位
     * 这个 前后 后前 这个顺序有基准值，默认选择第一个为基准值，那么先比较后，反之亦然
     * @param left
     * @param right
     * @param array
     */
    public static void quickSort(int left, int right, int[] array) {
        if (left >= right) return;
        //头指针
        int start = left;
        //尾指针
        int end = right;
        //基准值
        int flag = array[left];
        while (start < end) {
            //从后往前比较
            //&& 后面是比较一次，是否又继续的可能
            while (start < end && flag < array[end]) {
                end--;
            }
            array[start] = array[end];
            //从前往后比较,默认基准值是第一个，= 避免比较
            while (start < end && flag >= array[start]) {
                start++;
            }
            array[end] = array[start];
        }
        //把基准值归位
        array[start] = flag;
        System.out.println("第"+count+"次:------"+Arrays.toString(array));
        count++;
        //从基准值开始对两边进行同样的排序操作
        quickSort(left,start-1,array);
        quickSort(start+1,right,array);
    }

    /**
     * 选择基准值为最后一个元素，先前
     * @param left
     * @param right
     * @param array
     */
    public static void quickSort1(int left, int right, int[] array) {
        if (left >= right) return;
        //头指针
        int start = left;
        //尾指针
        int end = right;
        //基准值
        int flag = array[right];
        while (start < end) {

            //从前往后比较,默认基准值是第一个，= 避免比较
            while (start < end && flag >= array[start]) {
                start++;
            }
            array[end] = array[start];
            //从后往前比较
            //&& 后面是比较一次，是否又继续的可能
            while (start < end && flag < array[end]) {
                end--;
            }
            array[start] = array[end];
        }
        //把基准值归位
        array[start] = flag;
        System.out.println("第"+count+"次:------"+Arrays.toString(array));
        count++;
        //从基准值开始对两边进行同样的排序操作
        quickSort1(left,start-1,array);
        quickSort1(start+1,right,array);
    }

    public static void sort(int[] a, int low, int high) {
        int start = low;
        int end = high - 1;
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
