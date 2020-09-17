package com.details.generic;

/***
 * @author zlp
 * @date 15:14 2020/4/19
 */
public class TwoTuple<A,B> {
    //这种方式比使用 private set/get  更加安全，更加简洁明了
    final A a;
    final B b;

    public TwoTuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString(){
        return ""+a+","+b;
    }
}
