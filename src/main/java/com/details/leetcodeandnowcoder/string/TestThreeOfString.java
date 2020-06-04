package com.details.leetcodeandnowcoder.string;

import org.apache.commons.lang3.StringUtils;

/**
 * string的双引号创建和new创建有何不同，为什么不推荐用+号连接两个字符串
 * stringbuffer 和stringbuilder的append方法底层实现
 * @Author zlp
 * @Date 2020/6/4 11:30
 **/
public class TestThreeOfString {

    public static void main(String[] args) {
        method1();
    }
    static void method1(){
        //  "123" 在常量池中
        String a = "123";
        String b = "123";

        String x = a + b;
        String concat = x.concat("1");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1");
        //安全的
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("2");

        String c = new String("123");
        String d = new String("123");

        //内存地址都不一样
        String s = "1";
        System.out.println(System.identityHashCode(s));
        s += "1";
        System.out.println(System.identityHashCode(s));
        s += "1";
        System.out.println(System.identityHashCode(s));
    }
}
