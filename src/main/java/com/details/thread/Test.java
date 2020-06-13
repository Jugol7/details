package com.details.thread;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * @author zlp
 * @date 17:02 2020/1/16
 */
@Slf4j
public class Test {

    private final static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {

        ThreadLocal threadLocal = new ThreadLocal();
        Object o = threadLocal.get();
        logger.info("ThreadLocal ： {}",o);
        log.info("main start");
        MyThread myThread = new MyThread();
        logger.info("线程当前状态为： {}",myThread.getState());
        myThread.start();
        logger.info("线程调用start后的状态为： {}",myThread.getState());
        //如何判断下面三局语句的输出顺序
        Thread thread = new Thread(() -> {
            log.info("thread start");
            log.info("thread end");
        });
        thread.start();
        //当使用join方法时，表示main线程需在thread线程执行完毕在继续执行main线程
        try {
            thread.join();
        } catch (InterruptedException e) {
            log.error("thread join 异常" + e);
        }
        log.info("main end");
    }
}
