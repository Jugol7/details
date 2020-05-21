package com.details.leetcodeandnowcoder.algorithm;

import java.util.Arrays;
import java.util.Date;

/**
 * 字典排序
 * 给定一个正整数，实现一个方法来求出离该整数最近的大于自身的“换位数”。
 * 什么是换位数呢？就是把一个整数各个数位的数字进行全排列，从而得到新的整数。例如53241和23541。
 * 小灰也不知道这种经过换位的整数应该如何称呼，所以姑且称其为“换位数”。
 * 题目要求写一个方法来寻找最近的且大于自身的换位数。比如下面这样：
 * 输入12345，返回12354
 * 输入12354，返回12435
 * 输入12435，返回12453
 * https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653193612&idx=1&sn=95d5bf75272aaeeb9a90bea6baa24de4&chksm=8c99f756bbee7e401db790f060cbdab4e850380f5cbd727e1dab28cb1b2bf38cecc0cc4bbfb6&scene=21#wechat_redirect
 * 15:30
 */
public class DictionarySort {

    public static void main(String[] args) {
        Date date = new Date();
        // tD 日期   tT 时间    tY 年
        System.out.printf("%tD%n",date);
        char c1 = 97;
        char c2 = 'a';
        System.out.println("qwe="+c1);
        System.out.println("qwe="+c2);
        int[] num = {1, 2, 3, 5, 3, 2, 3};
        dictionarySort(num);
    }


    public static void dictionarySort(int[] num) {
        //如果将步骤抽离为方法，那么需要保证num不受其影响，可备份
        //首先一个数字的最大是逆序 例如 54321
        //最小是顺序 12345
        //1.找到第一个打乱顺序的数字,将边界的数字调换，将逆序区域换成顺序（最小）
        int index = 0;
        int temp;
        for (int i = num.length - 2; i > 0; i--) {
            if (num[i] < num[i + 1]) {
                index = i;
                temp = num[i];
                num[i] = num[i + 1];
                num[i + 1] = temp;
                break;
            }
        }
//        int[] resultHead = Arrays.copyOfRange(num, 0, index+1);
//        int[] ints = Arrays.copyOfRange(num, index+1, num.length);
//        int[] resultEnd = BubbleSort.chickWireSort(ints);
//        int[] result = new int[num.length];
//        System.arraycopy(resultHead, 0, result, 0, resultHead.length);
//        System.arraycopy(resultEnd, 0, result, resultHead.length, result.length); 越界？
//        Arrays.toString(result);
        int[] ints = Arrays.copyOfRange(num, index + 1, num.length);

        int[] ints1 = BubbleSort.chickWireSort(ints);
        int j = 0;
        for (int i = index+1; i < num.length; i++) {
            num[i] = ints1[j++];
        }
        System.out.println(Arrays.toString(num));

    }

}
