package com.details.linkedlist;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Least Recently Used (LRU)
 * 最近最少使用
 * 择日再战
 * LinkedHashMap底层就是用的HashMap加双链表实现的，而且本身已经实现了按照访问顺序的存储。
 * 此外，LinkedHashMap中本身就实现了一个方法removeEldestEntry用于判断是否需要移除最不常读取的数，
 * 方法默认是直接返回false，不会移除元素，所以需要重写该方法。即当缓存满后就移除最不常用的数。
 * @author zlp
 * @date 2019-11-19 16:09
 */
public class LRU extends LinkedHashMap {

    private final Integer MAX_CACHE;

    public  LRU(Integer size){
        //设置一个hashmap的初始大小，最后一个true指的是让linkedhashmap按照访问顺序来进行排序，最近访问的放在头，最老访问的就在尾
        super((int)Math.ceil(size / 0.75)+1,0.75f,true);
        MAX_CACHE = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        // 当map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据
        return size() > MAX_CACHE;
    }
}