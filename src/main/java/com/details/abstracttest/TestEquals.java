package com.details.abstracttest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.reflect.Reflection;

/**
 *  ==   equals()
 */
public class TestEquals {

    public static void main(String[] args) {

        int a = 1;
        int b = 1;
        System.out.println(a == b);
        Integer c = 1;
        Integer d = 1;
        Integer e = 2;
        System.out.println(c == d);
        System.out.println(c.equals(d));
        System.out.println(c.equals(e));
        System.out.println("------------引用-------------");
        Test t1 = new Test(7);
        Test t2 = new Test(7);
        System.out.println(t1 == t2);
        //@Data 会重写
        System.out.println(t1.equals(t2));

    }


}
@AllArgsConstructor
@NoArgsConstructor
@Data
class Test{
    private int value;

}