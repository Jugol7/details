package com.details.thread.callable;

import java.util.concurrent.ThreadFactory;

/**
 * 在创建线程池得时候，使用线程工厂，用于批量创建线程，同意设置线程参数，如守护线程，优先级
 * @Author zlp
 * @Date 2020/6/1 11:39
 **/
public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return null;
    }
}
