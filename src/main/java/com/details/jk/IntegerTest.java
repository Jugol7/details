package com.details.jk;

/**
 * Integer 有个静态内部类 static class IntegerCache{
 *     Integer cache[];
 *     初始化一些数据：range [-128, 127] must be interned (JLS7 5.1.7)
 * }
 * 所以在这个范围内的值，直接拿的同一个值，超过这个值才回去new
 *
 * @author ：zlp
 * @date ：2022/9/4 16:21
 * @version: 1.0
 */
public class IntegerTest {

    public static void main(String[] args) {
        Integer i2 = 127;
        Integer i1 = new Integer(127);
        Integer i3 = 128;
        Integer i4 = new Integer(128);


        System.out.println(i1 == i2);
        System.out.println(i1.equals(i2));
        System.out.println(i3 == i4);
        System.out.println(i3.equals(i4));


        Integer i5 = 129;
        Integer i6 = 129;
        System.out.println(i5 == i6);
        System.out.println(i5.equals(i6));


        Integer i7 = 100;
        Integer i8 = 100;
        System.out.println(i7 == i8);
        System.out.println(i7.equals(i8));

    }

}
