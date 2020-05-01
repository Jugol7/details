package com.details.algorithm;

/***
 * @author zlp
 * @date 18:47 2020/4/29
 */
public class TestValue {


    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        swap(a,b);
        System.out.println(a+"-------"+b);
    }

    public static void swap(Integer a,Integer b){
        Integer temp = a;
        a = b;
        b = temp;
    }

}
