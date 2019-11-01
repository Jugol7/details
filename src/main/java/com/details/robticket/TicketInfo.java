package com.details.robticket;

import lombok.Data;

/**
 * @author zlp
 * @date 2019-10-31 17:55
 */
@Data
public class TicketInfo implements Runnable {

    private int num;
    private int rest;

    @Override
    public void run() {
        //监控的位置，如果放在while里面，那么会出现最后一张票买了十次，因为已经又是个线程进入了while体。
        synchronized (this) {
            while (rest > 0) {
                num++;
                rest--;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前线程为："+Thread.currentThread().getName() + "----售出第" + num + "张票，还剩下" + rest + "张。");
            }
        }
    }
}
