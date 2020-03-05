package com.details.thread.synchronize;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/***
 * 锁定的是static方法
 * 如果 synchronized 修饰的是静态方法，则锁定的是类，无论多少个对象调用，都会同步
 * 如果 synchronized 静态方法和实例方法同时存在，静态方法锁类，实例方法锁对象
 * @author zlp
 * @date 13:36 2020/3/5
 */
public class TestStatic {

    public static void main(String[] args) {
        TestB testB1 = new TestB();
        TestB testB2 = new TestB();

        new Thread(TestB::func1,"A").start();
        new Thread(TestB::func2,"A").start();
        new Thread(testB1::func3,"A").start();
        new Thread(testB1::func4,"A").start();
        new Thread(TestB::func1,"B").start();
        new Thread(TestB::func2,"B").start();
        new Thread(testB2::func3,"B").start();
        new Thread(testB2::func4,"B").start();

    }
}

@Slf4j
class TestB{

    public static synchronized void func1(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error("线程异常："+e);
        }
        log.info(Thread.currentThread().getName()+"----func1");
    }

    public static synchronized void func2(){
        log.info(Thread.currentThread().getName()+"----func2");
    }

    public synchronized void func3(){
        log.info(Thread.currentThread().getName()+"----no static func3");
    }

    public synchronized void func4(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error("线程异常："+e);
        }
        log.info(Thread.currentThread().getName()+"----no static func4");
    }

}