package com.details.linkedlist;

import java.util.HashMap;

/**
 * Least Recently Used (LRU)
 * 最近最少使用
 * 择日再战
 * @author zlp
 * @date 2019-11-19 16:09
 */
public class LRU {
}

class LRUVO {
    private Integer key;
    private Integer value;
    private Integer capacity;

    public LRUVO(Integer capacity) {
        this.capacity = capacity;
    }

    public void set(Integer key, Integer value){
        HashMap<Integer,Integer> hashMap = new HashMap<>(capacity);
        hashMap.put(key,value);
    }


}