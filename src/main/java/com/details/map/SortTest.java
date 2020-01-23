package com.details.map;

import java.util.*;

/***
 * hash map中 entrySet（）与keySet（）用法
 * @author zlp
 * @date 16:38 2020/1/20
 */
public class SortTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("b", "b");
        map.put("a", "c");
        map.put("c", "a");
        // 通过ArrayList构造函数把map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        // 通过比较器实现比较排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> mapping1, Map.Entry<String, String> mapping2) {
                mapping1.getValue();
                return mapping1.getKey().compareTo(mapping2.getKey());
            }
        });
        for (Map.Entry<String, String> mapping : list) {
            System.out.println(mapping.getKey() + " ：" + mapping.getValue());
        }
    }
}
