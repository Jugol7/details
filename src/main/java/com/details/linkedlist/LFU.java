package com.details.linkedlist;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;

/**
 * Least Frequently Used
 *实现LFU算法
 * @author zlp
 * @date 2019-11-19 16:09
 */
@Slf4j
public class LFU {

    public static void main(String[] args) {
        DataLFU<String,String> lfu = new DataLFU<>(3);
        log.info("----初始化--------");
        lfu.put("lp1","lp1");
        lfu.put("lp2","lp2");
        lfu.put("lp3","lp3");

        lfu.get("lp1");
        lfu.get("lp1");
        lfu.get("lp2");
        lfu.get("lp3");
        lfu.get("lp1");
        lfu.get("lp2");

        log.info(lfu.toString());

        log.info("----多加一个元素--------");
        lfu.put("my","my");
        log.info(lfu.toString());
    }

}

@ToString
class DataLFU<k, v> {
    private Integer cap;
    private HashMap<k, v> cache = new HashMap<>();
    private HashMap<k, Integer> count = new HashMap<>();

    public DataLFU(Integer cap){
        this.cap = cap;
    }
    public v get(k key) {
        v v = cache.get(key);
        if (null != v) {
            //更新次数
            Integer vcount = count.get(key);
            count.put(key, ++vcount);
            return v;
        }
        return null;
    }

    public void put(k key, v value) {
        v v = cache.get(key);
        if (null == v) {
            if (cap == cache.size()) {
                //删除一个元素
                removeElement();
            }
        } else {
            cache.remove(key);
        }
        count.put(key, 1);
        cache.put(key, value);
    }

    /**
     * 删除一个最访问频率最低的
     */
    private void removeElement() {
        Integer min = Collections.min(count.values());
        k kByValue = getKByValue(min);
        cache.remove(kByValue);
        count.remove(kByValue);
    }

    public k getKByValue(Integer min){
        for(k key : count.keySet()){
            if(min.equals(count.get(key))){
                return key;
            }
        }
        return null;
    }

}