package com.details.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/***
 * @author zlp
 * @date 23:42 2020/4/15
 */
public class TestThreadPool {

    public static void main(String[] args) {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
//        LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<>(3);
//        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 60, TimeUnit.SECONDS, queue, handler);
        for (int i = 0; i < 8; i++) {
            MyThread thread = new MyThread();
            pool.execute(thread);
            System.out.println("线程池中存活的线程数量：" + pool.getPoolSize());
            if (!queue.isEmpty()) {
                System.out.println("阻塞队列中的线程数：" + queue.size());
            }
            if (pool.getPoolSize() == pool.getMaximumPoolSize()) {
                System.out.println("线程" + Thread.currentThread().getName() + "来时，已经满了，需要采取拒绝策略");
            }
        }
        pool.shutdown();
    }

    static class MyThread implements Runnable{
        @Override
        public void run() {
            System.out.println("当前线程："+Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

