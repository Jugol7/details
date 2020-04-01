package com.details.algorithm;

import java.util.Arrays;

/***
 * 二分查找法（折半查找）
 * @author zlp
 * @date 10:18 2020/3/28
 */
public class BinarySearch {

    public static void main(String[] args) {
        int base = 8;
        int [] arr = {2,3,1,4,5,7,8,3};
        Integer integer = binarySerach(base, arr,0,arr.length+1);
//        Integer integer = binarySerach2(base, arr);
        System.out.println(integer);
    }


    /**
     * 递归实现二分查找法与折半查找
     * @param base
     * @param arr
     * @param start
     * @param end
     * @return
     */
    public static Integer binarySerach(int base, int[] arr,int start ,int end){
        Arrays.sort(arr);
        int mid = (start+end)/2;
        Integer integer = null;
        if(arr[mid] == base){
            integer = mid;
        }
        else if(arr[mid] > base){
            integer = binarySerach(base, arr, start, mid - 1);
        }
        else if(arr[mid] < base){
            integer = binarySerach(base,arr,mid+1,end);
        }
        return integer;
    }

    /**
     * 非递归
     * @param base
     * @param arr
     * @return
     */
    public static Integer binarySerach2(int base, int[] arr){
        int low = 0;
        int high = arr.length;
        int mid;
        while(true){
            mid = (low+high)/2;
            if(arr[mid] == base){
                return mid;
            }else if(arr[mid] > base){
                high = mid + 1;
            }else {
                low = mid - 1;
            }
        }
    }

}
