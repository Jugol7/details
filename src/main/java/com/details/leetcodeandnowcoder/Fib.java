package com.details.leetcodeandnowcoder;

/**
 * 斐波拉契数列
 * @Author zlp
 * @Date 2020/6/3 17:23
 **/
public class Fib {

    public static void main(String[] args) {
        Fib fib = new Fib();
        int fib1 = fib.fib(3);
        System.out.println(fib1);
    }

    int sum1 = 0, sum2 = 1, sum3;
    public int test(int n){
        for (int i = 0; i < n; i++) {
            sum3 = sum1 + sum2;
            sum1 = sum2;
            sum2 = sum3;
        }
        return sum2;
    }

    /**
     * 递归
     * @param n
     * @return
     */
    public int fib(int n) {
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 1;
        }
        return fib(n-1)+fib(n-2);
    }


}
