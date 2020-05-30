package com.details.thread.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * 测试volatile关键字的可见性
 *  volatile保证了在多线程运行期间，共享变量的可见性
 * @Author zlp
 * @Date 2020/5/30 17:44
 **/
public class TestVolatile {

    /**
     * 不加volatile会死循环
     */
    private static volatile boolean flag = false;
    private static int num = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("线程A正在运行");
            while (!flag) {
            }
            System.out.println("A  终止");
            System.out.println(num);
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("线程B正在运行");
            num += 1;
            flag = true;
            System.out.println(num);
            System.out.println("B 终止");
        }, "B").start();

    }

}
