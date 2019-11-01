package com.details.robticket;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 模拟抢票
 * 1.100张票
 * 2.每张票都有编码
 * 3.
 * @author zlp
 * @date 2019-10-31 17:46
 */
public class RobTicket {

    /**
     * 拿100不同的张票
     */
    public static Map<Integer,TicketInfo> getMap() {
        Map<Integer,TicketInfo> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            TicketInfo ticketInfo = new TicketInfo();
            map.put(i,ticketInfo);
        }
        return map;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,10,200, TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));
        Map<Integer,TicketInfo> map = getMap();
        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setRest(100);
        for(int i = 0; i<map.size(); i++){
            threadPoolExecutor.execute(ticketInfo);
            //这直接走一百次，再进run？
            System.out.print("getPoolSize: "+threadPoolExecutor.getPoolSize());
            System.out.print("getQueue().size: "+threadPoolExecutor.getQueue().size());
            System.out.println("getCompletedTaskCount: "+threadPoolExecutor.getCompletedTaskCount());
        }
    }




}
