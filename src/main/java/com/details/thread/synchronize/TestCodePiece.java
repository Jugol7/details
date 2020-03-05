package com.details.thread.synchronize;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/***
 * 修饰代码块
 * @author zlp
 * @date 13:58 2020/3/5
 */
public class TestCodePiece {
    public static void main(String[] args) {
        TestCode testCode = new TestCode();
        for (int i = 0; i < 3; i++) {
            Integer num = Integer.valueOf(128);
            new Thread(()->{
                testCode.test(num);
            }).start();
        }
    }
}

@Slf4j
class TestCode{

    private Integer numInside = 2;

    public void test(Integer num){
        synchronized (num){
            log.info("-----------start");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                log.error("error--"+e);
                Thread.currentThread().interrupt();
            }
            log.info("-----------end");
        }
    }

}