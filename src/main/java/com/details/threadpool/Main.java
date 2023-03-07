package com.details.threadpool;

import java.util.concurrent.ExecutorService;

/**
 * <h1>可监控的线程池</h1>
 */
public class Main {

    static int test() {
        try {
            return 2;
        } finally {
            // finally代码块执行在 try里面的返回值生成之后，返回之前，所以在finally里面返回的话，会导致程序提前结束
            return 1;
        }
    }

    public static void main(String[] args) {

        System.out.println(test());


        ExecutorService executorService = ExecutorsUtil.newFixedThreadPool(
                10, "imooc-qinyi-"
        );

        Runnable runnable01 = new Reading(3, "Java 编程思想");
        Runnable runnable02 = new Reading(2, "Spring 实战");
        Runnable runnable03 = new Reading(3, "SpringBoot 实战");
        Runnable runnable04 = new Reading(1, "MySQL 权威指南");
        Runnable runnable05 = new Reading(2, "SpringCloud 实战");

        executorService.execute(runnable01);
        executorService.execute(runnable02);
        executorService.execute(runnable03);
        executorService.execute(runnable04);
        executorService.execute(runnable05);

        executorService.shutdown();
    }
}
