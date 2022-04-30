package com.details.jk;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 首先，HashMap 类判断键 k1 和 k2 相等的条件为 (k1==null?k2==null:k1.equals(k2))==true，IdentifyHashMap 判断 k1 和 k2 相等的条件是 (k1==k2)；
 * 其次，LinkedHashMap 设置为 true 是按访问顺序遍历，
 *
 * @author zlp
 * @date 2022/04/30
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("a", "1");
        map.put("a", "2");
        map.put(new String("a"), "3");
        map.put(new String("a"), "4");
        System.out.println(map.size());

        Map<String,String> map1 = new IdentityHashMap<>();
        map1.put("a", "1");
        map1.put("a", "2");

        map1.put(new String("a"), "3");
        map1.put(new String("a"), "4");
        System.out.println(map1.size());

        Map<String,String> map2 = new LinkedHashMap<>(16,
                0.75F,true);
        map2.put("a","a");
        map2.put("b","b");
        map2.put("c","c");
        for(Map.Entry<String, String> entry:map2.entrySet()){
            System.out.print(entry.getKey()+",");
        }
        System.out.println();
        map2.put("a","a1");
        for(Map.Entry<String, String> entry:map2.entrySet()){
            System.out.print(entry.getKey()+",");
        }
    }

}
