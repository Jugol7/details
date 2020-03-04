package com.details.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/***
 * Callable 的 call 方法有返回值，Runnable 的 run 方法没有返回值。
 * - Callable 的 call 方法可以抛出异常，Runnable 的 run 方法不能抛出异常。
 * - 在外部通过 FutureTask 的 get 方法异步获取执行结果，FutureTask 是一个可以控制的异步任务，是对 Runnable 实现的一种继承和扩展。
 * @author zlp
 * @date 11:25 2020/3/3
 */
public class Test {
    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();
        FutureTask futureTask = new FutureTask(myCallable);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            //获取MyCallable的返回值
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Callable<V> 中的泛型是指定call()的返回值类型
 */
class MyCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "I am Callable<String>";
    }
}
