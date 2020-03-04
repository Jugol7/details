package com.details.thread.runnable;

import java.util.concurrent.TimeUnit;

/***
 * 结果一样，都需要排队
 * @author zlp
 * @date 11:34 2020/3/3
 */
public class Test1 {
    public static void main(String[] args) {
//       testAccount();
        testAccountSy();
    }
    private static void testAccountSy(){
        AccountSy accountSy = new AccountSy();
        new Thread(accountSy,"1").start();
        new Thread(accountSy,"2").start();

        AccountSy accountSyA = new AccountSy();
        AccountSy accountSyB = new AccountSy();
        new Thread(accountSyA,"A").start();
        new Thread(accountSyB,"B").start();

    }

    private static void testAccount(){
        //一个对象
        Account account = new Account();
        new Thread(account, "1").start();
        new Thread(account, "2").start();
        //不通对象
        Account accountA = new Account();
        Account accountB = new Account();
        new Thread(accountA, "A").start();
        new Thread(accountB, "B").start();
    }
}

/**
 * 统计程序的访问量
 */
class Account implements Runnable {
    private int num = 0;

    @Override
    public void run() {
        num++;
        try {
//            Thread.sleep(2000);
            //休眠时，其他线程将进入找有num
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "第" + num + "个访问者");
    }
}

/**
 * 使用锁
 */
class AccountSy implements Runnable {
    private int num = 0;
    @Override
    public synchronized void run() {
        num++;
        try {
//            Thread.sleep(2000);
            //使用关键字synchronized会将num对象锁住，达到线程同步
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "第" + num + "个访问者");
    }
}