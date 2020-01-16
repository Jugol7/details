package com.details.thread;

import lombok.extern.slf4j.Slf4j;

/***
 * @author zlp
 * @date 17:02 2020/1/16
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        log.info("main start");
        MyThread myThread = new MyThread();
        myThread.start();
        //如何判断下面三局语句的输出顺序
        Thread thread = new Thread(()->{
            log.info("thread start");
            log.info("thread end");
        });
        thread.start();
        //当使用join方法时，表示main线程需在thread线程执行完毕在继续执行main线程
        try {
            thread.join();
        } catch (InterruptedException e) {
            log.error("thread join 异常"+e);
        }
        log.info("main end");
    }


}
