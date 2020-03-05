package com.details.thread.synchronize;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/***
 * synchronized 关键字剖析之锁定的到底是谁？
 * 修饰是否带有static的方法
 * @author zlp
 * @date 9:38 2020/3/4
 */
@Slf4j
public class TestA {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    public static void test1(){
        TestSy testSy = new TestSy();
        new Thread(testSy::func1,"1").start();
        new Thread(testSy::func2,"2").start();
        new Thread(testSy::func2Sy,"2Sy").start();
    }

    public static void test2(){
        TestSy testSy1 = new TestSy();
        TestSy testSy2 = new TestSy();
        new Thread(testSy1::func1,"1").start();
        new Thread(testSy1::func2,"1").start();
        new Thread(testSy1::func2Sy,"1Sy").start();

        new Thread(testSy2::func1,"2").start();
        new Thread(testSy2::func2,"2").start();
        new Thread(testSy2::func2Sy,"2Sy").start();

    }

}

@Slf4j
class TestSy{

    public synchronized void func1(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error("线程中断，详情如下："+e);
            Thread.currentThread().interrupt();
        }
        log.info(Thread.currentThread().getName()+"func1");
    }

    /**
     * 没有关键字 sychronized 修饰的，无需考虑
     */
    public void func2(){
        log.info(Thread.currentThread().getName()+"func2");
    }

    public synchronized void func2Sy(){
        log.info(Thread.currentThread().getName()+"have sychroniaed func2");
    }

}