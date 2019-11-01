package com.details.robticket;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟抢票
 * 1.100张票
 * 2.每张票都有编码
 *
 * @author zlp
 * @date 2019-10-31 17:46
 */
public class RobTicket {

    /**
     * 拿100不同的张票
     */
    public void getMap() {
        Map<Integer,TicketInfo> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            TicketInfo ticketInfo = new TicketInfo();
            ticketInfo.setId(i+1);
            ticketInfo.setName(ticketInfo.getId()+"张票");
            map.put(i,ticketInfo);
        }
    }





}
