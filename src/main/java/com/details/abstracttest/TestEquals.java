package com.details.abstracttest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  ==   equals()
 */
public class TestEquals {

    public static void main(String[] args) {
        int test = test(1, 3);
        System.out.println(test);
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

    /**
     * test
     * @param a
     * @param b
     * @return
     */
    public static int test(int a, int b){
        if(b == 0){
            return 0;
        }
        if(b %2 ==0){
            return test(a+a, b/2);
        }
        return test(a+a, b/2) + a;
    }


}
@AllArgsConstructor
@NoArgsConstructor
@Data
class Test{
    private int value;

}