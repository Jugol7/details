package com.details.jk;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownTest {

    public volatile static int volatileCount = 0;
    static AtomicInteger atomicCount=new AtomicInteger();

    public static void inc() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {

        }
        volatileCount++;
        atomicCount.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1000);

        // 同时启动 1000 个线程，去进行 i++ 计算，看看实际结果

        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CountDownTest.inc();
                    latch.countDown();
                }

            }).start();
        }
        latch.await();
        System.out.println("运行结果:Counter.volatileCount=" + CountDownTest.volatileCount);
        System.out.println("运行结果:Counter.atomicCount=" + CountDownTest.atomicCount);
    }

}
