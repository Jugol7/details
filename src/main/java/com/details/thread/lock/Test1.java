package com.details.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 * lock：对synchronized的一个升级，属于JUC的一个接口，常用的实现类是ReentrantLock（重入锁）。
 * synchronized是通过JVM实现锁机制，ReentrantLock是通过JDK实现锁机制。
 * 重入锁可以给同一个资源加多把锁，synchronized线程执行完毕之后自动释放锁，ReentrantLock需要手动释放
 *
 * 两者的区别
 *  synchronized                                 Lock
 * 1.关键字                                     接口
 * 2.JVM实现锁                                 jdk实现锁
 * 3.自动上锁，解锁                           手动上锁，解锁
 * 4.无法判断是否拿到了锁                     可以判断 tryLock()
 * 5.拿不到锁，会一直等待                     不会一直等待
 * 6.非公平锁                                可以设置为公平锁
 * 公平锁：排队，当锁没有被占用时，当前线程需要判断队列中是否有其他等待线程。
 * 非公平锁：插队，当锁没有被占用时，当前线程可以直接占用，不需要判断队列中是否有其他等待线程。
 * @author zlp
 * @date 21:56 2020/3/10
 */
@Slf4j
public class Test1 {

    public static void main(String[] args) {
//        testSyn();
        log.info("-------------------------------------------------------------------");
        testLock();
    }

    public static void testSyn(){
        Ticket ticket = new Ticket();
            for (int i = 0; i < 40; i++) {
                new Thread(ticket::saleSyn, "A").start();
                new Thread(ticket::saleSyn, "B").start();
        }
    }

    public static void testLock(){
        Ticket ticket = new Ticket();
        for (int i = 0; i < 40; i++) {
            new Thread(ticket::saleLock, "A").start();
            new Thread(ticket::saleLock, "B").start();
        }
    }

}

@Slf4j
class Ticket{
    private Integer salesNum = 0;
    private Integer lastNum = 30;
    Lock lock = new ReentrantLock();
    public synchronized void saleSyn(){
        if(lastNum > 0){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        salesNum++;
        lastNum--;
        log.info(Thread.currentThread().getName()+"卖了第"+salesNum+"张，还剩"+lastNum+"张");
    }

    public void saleLock(){
        //上锁
        lock.lock();
        if(lastNum > 0){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            salesNum++;
            lastNum--;
            log.info(Thread.currentThread().getName()+"卖了第"+salesNum+"张，还剩"+lastNum+"张");
        }
        //解锁
        lock.unlock();
    }


}