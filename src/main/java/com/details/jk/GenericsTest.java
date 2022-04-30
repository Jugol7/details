package com.details.jk;

import java.util.ArrayList;

public class GenericsTest {

    public static void main(String[] args) throws Exception {

        ArrayList<String> list1 = new ArrayList<>();
        list1.add("abc");

        ArrayList<Long> list2 = new ArrayList<>();
        list2.add(123L);

        System.out.println(list1.getClass() == list2.getClass());

        ArrayList<Long> list = new ArrayList<>();

        list.add(1L);

        list.getClass().getMethod("add", Object.class).invoke(list, "a");
        list.getClass().getMethod("add", new Class[]{int.class,Object.class}).invoke(list, 2,"b");

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i)+ " ");
        }
    }

}
