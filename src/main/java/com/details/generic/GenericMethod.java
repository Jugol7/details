package com.details.generic;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/***
 * @author zlp
 * @date 16:52 2020/4/19
 */
public class GenericMethod  {

    public static void main(String[] args) {
        String s = "123456";
        new GenericMethod().method(s,2,s);
    }
    public <T,Q> void method(T t,Q q,String s){
        System.out.println(t.getClass().getName());
        System.out.println(q.getClass().getName());
        System.out.println(s.getClass().getName());
    }

}
