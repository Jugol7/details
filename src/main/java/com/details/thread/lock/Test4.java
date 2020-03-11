package com.details.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/***
 * 哲学家吃饭简单版
 * 准备工作：一个筷子类，一个死锁，一个main
 * @author zlp
 * @date 16:48 2020/3/11
 */
public class Test4 {
    public static void main(String[] args) {
        DeadLock deadLock1 = new DeadLock(1);
        DeadLock deadLock2 = new DeadLock(2);
        new Thread(()->{
            deadLock1.lock();
        }).start();
//        TimeUnit.SECONDS.sleep();
    }
}

class Chopstick {
}

@Slf4j
class DeadLock {
    private Integer num;
    private Chopstick chopstick1 = new Chopstick();
    private Chopstick chopstick2 = new Chopstick();

    DeadLock(Integer num) {
        this.num = num;
    }

    public void lock() {
        if (num == 1) {
            log.info("拿到一只筷子" + num + "等待拿另一支");
            synchronized (chopstick1) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (chopstick2) {
                    log.info("拿到另一只筷子" + num + "用餐开始");
                }
            }
        }
        if (num == 2) {
            log.info("拿到一只筷子" + num + "等待拿另一支");
            synchronized (chopstick2) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (chopstick1) {
                    log.info("拿到另一只筷子" + num + "用餐开始");
                }
            }
        }


    }
}
