package com.details.algorithm;

import java.util.Arrays;

/***
 * 递归实现二分查找法与折半查找
 * @author zlp
 * @date 10:18 2020/3/28
 */
public class BinarySearch {

    public static void main(String[] args) {
        int base = 8;
        int [] arr = {2,3,1,4,5,7,8,3};
        Integer integer = binarySerach(base, arr,0,arr.length+1);
        System.out.println(integer);
    }


    public static Integer binarySerach(int base, int[] arr,int start ,int end){
        Arrays.sort(arr);
        int mid = (start+end)/2;
        Integer integer = null;
        if(arr[mid] == base){
            integer = arr[mid];
        }
        else if(arr[mid] > base){
            integer = binarySerach(base, arr, start, mid - 1);
        }
        else if(arr[mid] < base){
            integer = binarySerach(base,arr,mid+1,end);
        }
        return integer;
    }

}
