package com.details.leetcodeandnowcoder.string;

import java.util.Collections;

/**
 *
 * 测试finally中的数据变化及返回的是哪一个？
 * @Author zlp
 * @Date 2020/5/28 9:05
 **/
public class Test {

    //static修饰的变量在类加载时的准备阶段在方法区中分配内存，在初始化时初始化，而final的不是。
    private final static int a = 1;
    private static int b = 1;
    private int c = 1;

    public static void test() {

        int i = 0;
        i = i++;
        System.out.println(i);

    }

    public static int test2() {

        int a = 0;
        try {
            a++;
            return a;
        } finally {
            a += 4;
            return a;
        }

    }

    public static int test3() {

        int a = 0;
        try {
            a++;
            return a;
        } finally {
            a += 4;
//            return a;
        }

    }

    /**
     * 求栈深度
     *  设置 虚拟机栈大小：   -Xss100k
     *  不设置，默认输出 24765
     */
    public void test4(){
            b++;
            try{
            test4();
            }catch (Throwable  e){
                System.out.println(b);
        }
    }
    public static void main(String[] args) {
        new Test().test4();
//        test();
//        System.out.println(test2());
//        System.out.println(test3());
//        System.out.println(new B().getValue());
    }

    static class A {
        protected int value;

        public A(int v) {
            setValue(v);
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            try {
                value++;
                //返回的是 11 而不是finally里value变化的值
                return value;
            } finally {
                this.setValue(value);
                System.out.println(value);
            }
        }
    }

    static class B extends A {
        public B() {
            super(5);
            int i = getValue();
            System.out.println("------------" + i);
            setValue(i - 3);
        }

        @Override
        public void setValue(int value) {
            super.setValue(2 * value);
        }
    }
}
