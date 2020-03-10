package com.details.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/***
 * ReentrantLock 还具备限时性的特点，可以判断某个线程在一定时间内能否获取到锁，tryLock 返回一个 boolean 的值，true 表示可以拿到锁，false 表示拿不到锁.
 * @author zlp
 * @date 22:54 2020/3/10
 */
public class Test3 {
    public static void main(String[] args) {
    }
}
class TimeLock{
    private ReentrantLock reentrantLock = new ReentrantLock();

}
