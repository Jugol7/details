package com.details.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/***
 * ConcurrentModificationException
 *      :并发修改异常
 *      原因：ArrayList线程不安全
 * @author zlp
 * @date 17:34 2020/3/14
 */
@Slf4j
public class ConcurrentModificationExceptionTest {

    public static void main(String[] args) {

        List<String> arrayList = new ArrayList<>();

        List<String> vector = new Vector();

        List<String> synList = Collections.synchronizedList(arrayList);
        //CopyOnWrite 写时复制
        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        /**
         * Set
         */
        Set<String> set = new CopyOnWriteArraySet<>();

        /**
         * Map
         */
        Map<String,String> map = new ConcurrentHashMap<>();

        list(copyOnWriteArrayList);
    }

    public static void list(List<String > list){
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    log.error(e+"");
                }
                list.add("a");
                log.info(list.toString());
            }).start();
        }
    }

}
