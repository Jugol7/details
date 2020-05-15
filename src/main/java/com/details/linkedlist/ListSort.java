package com.details.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 排序
 */
public class ListSort {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        list.add(new User(12,"zlp12"));
        list.add(new User(11,"zlp11"));
        list.add(new User(17,"zlp17"));
        Collections.sort(list);
        System.out.println(list.toString());
    }

    public static void sortList(List<Object> list){
    }

}
@Data
@NoArgsConstructor
@AllArgsConstructor
class User implements Comparable<User>{

    private int age;
    private String name;

    @Override
    public int compareTo(User o) {
        return this.age - o.age;
    }
}