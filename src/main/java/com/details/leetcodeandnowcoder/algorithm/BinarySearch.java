package com.details.leetcodeandnowcoder.algorithm;

import java.util.Arrays;

/***
 * 二分查找法（折半查找）
 * @author zlp
 * @date 10:18 2020/3/28
 */
public class BinarySearch {

    public static void main(String[] args) {
        int base = 81;
        int [] arr = {2,3,1,4,5,7,8,3};
//        Integer integer = binarySerach(base, arr,0,arr.length+1);
        Integer integer = binarySearch2(base, arr);
        System.out.println(integer);
//        System.out.println("---------------====------------------");
//        int i = sourceFromSc();
//        System.out.println(i);
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
    public static Integer binarySearch2(int base, int[] arr){
        int low = 0;
        int high = arr.length;
        int mid;
        //这里使用 low<high还是 low<=high
        while(low <= high){
            mid = (low+high)/2;
            if(arr[mid] == base){
                return mid;
            }else if(arr[mid] > base){
                //这里要不要+1
                high = mid + 1;
            }else {
                //这里要不要-1
                low = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 来源宋从
     * @return
     */
    public static int sourceFromSc(){
        int[]arr = {1,2,3,4,5,5,5,6,7,8,8,9};
        int target = 9;
        int start = 0;
        int end = arr.length;
        //闭区间
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (arr[mid] == target) {
                 end = mid - 1;
//                return mid;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else if (arr[mid] < target) {
                 start = mid + 1;
//                start = mid;
            }
        }
//        return -1;
         if(start>=arr.length || arr[start]!=target) return -1;
         return start;
    }

}
