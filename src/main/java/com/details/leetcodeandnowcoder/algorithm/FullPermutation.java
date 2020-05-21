package com.details.leetcodeandnowcoder.algorithm;

import lombok.extern.slf4j.Slf4j;

/***
 * 一列数的全排列
 * @author zlp
 * @date 20:34 2020/4/2
 */
@Slf4j
public class FullPermutation {

    public static void perm(int[] list) {
        perm(list,0);
    }
    private static void perm(int[] list, int k) {
        if (k == list.length) {
            for (int i = 0; i < list.length; i++) {
                System.out.print(list[i]);
            }
            System.out.println();
        }else{
            for (int i = k; i < list.length; i++) {
                log.info("第一个swap的k= "+k+"_ _ _ _ _i= "+i);
                swap(list, k, i);
                perm(list, k + 1);
                log.info("第二个swap的k= "+k+"_ _ _ _ _i= "+i);
                swap(list, k, i);
            }
        }
    }
    private static void swap(int[] list, int pos1, int pos2) {
        int temp = list[pos1];
        list[pos1] = list[pos2];
        list[pos2] = temp;
    }

    public static void main(String[] args) {
        int[] x = {1, 2, 3, 4, 5};
        perm(x);
    }

}
