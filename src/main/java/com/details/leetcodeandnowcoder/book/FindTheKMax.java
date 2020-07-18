package com.details.leetcodeandnowcoder.book;

import com.details.leetcodeandnowcoder.algorithm.BubbleSort;

/**
 * N个数中的第k个最大的数
 * @author zlp
 * @date 2020/7/16 11:14
 */
public class FindTheKMax {

    public static void main(String[] args){

        int theKMax = findTheKMax(3);
        System.out.println(theKMax);
    }

    public static int findTheKMax(int k){
        int[] array = new int[]{1,2,3,4,9,7,5};
        if(array.length < k){
            return -1;
        }
        //1、先排序，获取第k个数
        for (int i = 0; i < array.length; i++) {
            //判断后面的是否是有序的
            boolean change = true;
            //后面是有序就不用进行比较了
            int sortBorder = array.length-1;
            int lastChangeIndex = 0;
            for (int j = 0; j < sortBorder; j++) {
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    change = false;
                    lastChangeIndex = j;
                }
            }
            sortBorder = lastChangeIndex;
            if(change){
                break;
            }
        }
        //注意下标
        return array[k-1];
        //2、先排序前k个数，在将第k个数与后面的数进行比较，



    }
}
