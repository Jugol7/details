package com.details.sword;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 注意点
 * 1. 使用wait()、notify()和notifyAll()时需要先对调用对象加锁
 *
 * @Author zlp
 * @Description:
 * @Date 19:07:54 2021/8/12/0012
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws Exception {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {
        @Override
        public void run() { // 加锁，拥有lock的Monitor
            synchronized (lock) { // 当条件不满足时，继续wait，同时释放了lock的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        // 进入等待状态，只有被另外的线程通知，才能返回，继续执行（会释放对象的锁）
                        lock.wait();
                    } catch (InterruptedException e) {
                    }
                }// 条件满足时，完成工作
                System.out.println(Thread.currentThread() + " flag is false. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() { // 加锁，拥有lock的Monitor
            synchronized (lock) { // 获取lock的锁，然后进行通知，通知时不会释放lock的锁， // 直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                // SleepUtils.second(5);
            }
            // 再次加锁  应该是同一线程具有优先级
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                //  SleepUtils.second(5);
            }
        }
    }
}