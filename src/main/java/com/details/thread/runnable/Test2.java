package com.details.thread.runnable;

import java.util.concurrent.TimeUnit;

/***
 * @author zlp
 * @date 11:52 2020/3/3
 */
public class Test2 {
    public static void main(String[] args) {
        Account2 account2 = new Account2();
        new Thread(()->{
            account2.function1();
        },"A").start();
        new Thread(()->{
            account2.function1();
        },"B").start();
    }
}

class Account2{
    private int num = 0;

    /**
     * 锁定的是num这个对象
     * 流程:
     *  1.首先线程A进入，num++，此时num=1，休眠3秒，输出
     *  2.线程B进入，num++，此时num=2，休眠3秒，输出
     *  如果没有关键字 synchronized
     *  那么进入休眠时，线程B进入，改变了num，输出的结果显示都为第2个
     */
    public synchronized void function1(){
        num++;
        try {
            //属于JUC中的方法 java.util.concurrent;  底层调用的是Thread.sleep();
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"第"+num+"个访问者");
    }

}