package com.details.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

/**
 * 操作本地缓存
 * 使用一个map来存储一些数据
 *
 * @author zlp
 * @date 2019-11-19 11:45
 */
public class LocalCache {

    private static HashMap<String, CacheContent> cacheMap = new HashMap<>();

    public LocalCache(){}

    /**
     * 存入缓存
     * @param key
     * @param workMillis
     * @param value
     */
    public void setLocalCache(String key, long workMillis, String value){
        long currTime = System.currentTimeMillis();
        CacheContent cacheContent = new CacheContent(workMillis,value,currTime);
        cacheMap.put(key,cacheContent);
    }

    /**
     * 获取缓存数据
     * @param key
     * @return
     */
    public String getLocalCache(String key){
        //判断生效时间
        long currTime = System.currentTimeMillis();
        CacheContent cacheContent = cacheMap.get(key);
        long createTime = cacheContent.getCreateTime();
        long workMillis = cacheContent.getWorkMillis();
        if(workMillis > 0 && workMillis < createTime-createTime){
            cacheMap.remove(key);
            return null;
        }
        return cacheContent.getContent();
    }

}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class CacheContent{
    /**
     * 生效时间
     */
    private long workMillis;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private long createTime;

}