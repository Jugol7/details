package com.details.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 * ReentrantLock 除了可重入之外，还有一个可中断的特点：允许在某个线程等待时，主动去中断线程，不需要获取锁，但是会抛出异常.
 * @author zlp
 * @date 22:32 2020/3/10
 */
public class Test2 {
    public static void main(String[] args) {
        //创建锁对象
        Lock lock = new ReentrantLock();
        //上锁
        lock.lock();
        Thread thread = new Thread(()->{
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
//        lock.unlock();
    }
}
