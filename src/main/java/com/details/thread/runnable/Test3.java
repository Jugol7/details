package com.details.thread.runnable;

import java.util.concurrent.TimeUnit;

/***
 * sleep 和 wait 的区别
 * sleep属于Thread，wait属于Object
 * 特点：sleep 是让当前的线程对象暂停执行任务，操作的是线程对象。
 *       wait 是让正在访问当前对象的线程休眠，不是针对线程对象，而是针对线程对象要访问的资源。
 *       但是 wait 有一个前提，当前线程对象必须拥有该资源，所以 wait 方法只能在同步方法或者同步代码块，否则会抛出异常。
 *    wait释放锁，sleep不释放锁
 *
 *    唤醒wait的方式，1、指定wait的时间 wait(time); 2、使用notify()
 * @author zlp
 * @date 17:24 2020/3/3
 */
public class Test3 {
    public static void main(String[] args) {
        A a = new A();
        new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                a.test(i);
            }
        }).start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a.notifyMy();
        }).start();
    }
}

class A {
    public synchronized void test(int num) {
        if (num == 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第" + num + "个");
    }

    public synchronized void notifyMy(){
        this.notify();
    }
}
