package com.details.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/***
 * ReentrantLock 还具备限时性的特点，可以判断某个线程在一定时间内能否获取到锁，tryLock 返回一个 boolean 的值，true 表示可以拿到锁，false 表示拿不到锁.
 * @author zlp
 * @date 22:54 2020/3/10
 */
public class Test3 {
    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();
        new Thread(timeLock::getLock,"A").start();
        new Thread(timeLock::getLock,"B").start();
    }
}

@Slf4j
class TimeLock{
    private ReentrantLock reentrantLock = new ReentrantLock();
    public void getLock(){

        try {
            //三秒内能否拿到锁。
            if(reentrantLock.tryLock(3, TimeUnit.SECONDS)){
                log.info(Thread.currentThread().getName()+"拿到了锁");
                //休眠时间
                TimeUnit.SECONDS.sleep(2);
            }else {
                log.info(Thread.currentThread().getName()+"没拿到锁");
            }
        } catch (InterruptedException e) {
            log.error("异常信息："+e);
        }finally {
            if(reentrantLock.isHeldByCurrentThread()){
                reentrantLock.unlock();
            }
        }

    }
}
