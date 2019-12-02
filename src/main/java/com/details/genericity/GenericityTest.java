package com.details.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型测试1
 * 泛型三类：泛型类、泛型接口、泛型方法
 * @author zlp
 * @date 11:14  2019/12/2
 */
public class GenericityTest {

    public static void main(String[] args) {
        //泛型旨在编译阶段有效
        List<String> list1 = new ArrayList<>();
        //只能是包装类
        List<Integer> list2 = new ArrayList<>();
        if(list1.getClass().equals(list2.getClass())){
            System.out.println("List<String>与List<Integer>类型相同");
        }
    }

}
