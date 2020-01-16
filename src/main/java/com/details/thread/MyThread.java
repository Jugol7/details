package com.details.thread;

import lombok.extern.slf4j.Slf4j;

/***
 * @author zlp
 * @date 17:01 2020/1/16
 */
@Slf4j
public class MyThread extends Thread{

    @Override
    public void run() {
        log.info("this is my thread");
    }
}
