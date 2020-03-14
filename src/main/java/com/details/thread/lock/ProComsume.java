package com.details.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/***
 * 生产消费者模式
 * 使用synchronized
 * @author zlp
 * @date 17:10 2020/3/14
 */
public class ProComsume {

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.addLock();
            }
        }, "pro").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.delLock();
            }
        }, "del").start();
    }


    public static void useSyn() {

    }
}

@Slf4j
class Data {
    private Integer num = 0;

    /**
     * 生产
     */
    public synchronized void add() {
        //只有当num==0时才生产
        while (num != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                log.error("error:" + e);
            }
        }
        num++;
        this.notify();
        log.info(Thread.currentThread().getName() + "生产一个,num==" + num);
    }

    /**
     * 消费
     */
    public synchronized void del() {
        //只有当num!=0时才消费
        while (num == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                log.error("error:" + e);
            }
        }
        num--;
        this.notify();
        log.info(Thread.currentThread().getName() + "消费一个,num==" + num);
    }


    //-----------------使用lock
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();

    //Condition依赖于Lock，结合可实现等待、通知机制。
    public void addLock() {
        try {
            reentrantLock.lock();
            while (num != 0) {
                condition.await();
            }
            num++;
            condition.signal();
            log.info(Thread.currentThread().getName() + "生产一个,num==" + num);
        } catch (InterruptedException e) {
            log.error(""+e);
        }finally {
            reentrantLock.unlock();
        }
    }

    public void delLock() {
        try {
            reentrantLock.lock();
            while (num == 0) {
                condition.await();
            }
            num--;
            condition.signal();
            log.info(Thread.currentThread().getName() + "消费一个,num==" + num);
        } catch (InterruptedException e) {
            log.error(""+e);
        }finally {
            reentrantLock.unlock();
        }
    }

}
